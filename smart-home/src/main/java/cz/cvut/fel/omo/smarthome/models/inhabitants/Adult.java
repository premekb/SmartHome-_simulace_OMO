package cz.cvut.fel.omo.smarthome.models.inhabitants;

import cz.cvut.fel.omo.smarthome.events.deviceevents.alerts.IsMakingWeirdSounds;
import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsBroken;
import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsDoneCooking;
import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsDoneWashing;
import cz.cvut.fel.omo.smarthome.events.inhabitantevents.importantevents.IsCrying;
import cz.cvut.fel.omo.smarthome.events.inhabitantevents.importantevents.IsHungry;
import cz.cvut.fel.omo.smarthome.events.inhabitantevents.importantevents.IsSad;
import cz.cvut.fel.omo.smarthome.exceptions.IllegalOperationException;
import cz.cvut.fel.omo.smarthome.interfaces.traits.HasCook;
import cz.cvut.fel.omo.smarthome.iterators.SmartHomeIterator;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.house.devices.AC;
import cz.cvut.fel.omo.smarthome.models.house.devices.AudioVideoReceiver;
import cz.cvut.fel.omo.smarthome.models.house.devices.Dehumidifier;
import cz.cvut.fel.omo.smarthome.models.house.devices.Device;
import cz.cvut.fel.omo.smarthome.models.house.devices.Dishwasher;
import cz.cvut.fel.omo.smarthome.models.house.devices.Fridge;
import cz.cvut.fel.omo.smarthome.models.house.devices.Light;
import cz.cvut.fel.omo.smarthome.models.house.devices.Microwave;
import cz.cvut.fel.omo.smarthome.models.house.devices.Oven;
import cz.cvut.fel.omo.smarthome.models.house.devices.Sensor;
import cz.cvut.fel.omo.smarthome.models.house.devices.TV;
import cz.cvut.fel.omo.smarthome.models.house.devices.WindowBlind;
import cz.cvut.fel.omo.smarthome.models.house.devices.documentation.Manual;
import cz.cvut.fel.omo.smarthome.models.house.devices.documentation.ManualPool;
import cz.cvut.fel.omo.smarthome.models.house.devices.documentation.Warranty;
import cz.cvut.fel.omo.smarthome.models.house.devices.items.CD;
import cz.cvut.fel.omo.smarthome.models.house.devices.items.Food;
import java.util.Optional;

public class Adult extends Person {
        public Adult() {
        super();
        addRandomlyPublishedEvent(new IsSad());
    }

    @Override
    public void use(AC ac) {
        int choice = rand.nextInt(6); 
        switch (choice) {
            case 0 -> ac.turnOn();
            case 1 -> ac.turnOff();
            case 2 -> ac.getTemperature().lowerTemperature();
            case 3 -> ac.getTemperature().raiseTemperature();
            case 4 -> ac.start();
            case 5 -> ac.stop();
        }

        logUsage(ac);
    }

    @Override
    public void use(AudioVideoReceiver audioVideoReceiver) {
        int choice = rand.nextInt(5);
        switch (choice) {
            case 0 -> audioVideoReceiver.turnOff();
            case 1 -> audioVideoReceiver.turnOn();
            case 2 -> {
                if (audioVideoReceiver.hasCD()) {
                    audioVideoReceiver.removeCD();
                }
                audioVideoReceiver.insertCD(new CD());
            }
            case 3 -> audioVideoReceiver.play();
            case 4 -> audioVideoReceiver.stop();
        }

        logUsage(audioVideoReceiver);
    }

    @Override
    public void use(Dehumidifier dehumidifier) {
        int choice = rand.nextInt(4);
        switch (choice) {
            case 0 -> dehumidifier.turnOn();
            case 1 -> dehumidifier.turnOff();
            case 2 -> dehumidifier.start();
            case 3 -> dehumidifier.stop();
        }

        logUsage(dehumidifier);
    }

    @Override
    public void use(Dishwasher dishwasher) {
        int choice = rand.nextInt(2);
        switch (choice) {
            case 0 -> {
                dishwasher.start();
                isBusy = true;
                House.getInstance().attach(this, new IsDoneWashing());
            }
            case 1 -> dishwasher.stop();
        }

        logUsage(dishwasher);
    }

    @Override
    public void use(Fridge fridge) {
        int choice = rand.nextInt(2);
        switch (choice) {
            case 0 -> fridge.takeFood();
            case 1 -> fridge.storeFood(new Food());
        }

        logUsage(fridge);
    }

    @Override
    public void use(Light light) {
        int choice = rand.nextInt(4);
        switch (choice) {
            case 0 -> light.turnOff();
            case 1 -> light.turnOn();
            case 2 -> light.getBrightness().lowerBrightness();
            case 3 -> light.getBrightness().raiseBrightness();
        }

        logUsage(light);
    }

    @Override
    public void use(Microwave microwave) {
        int choice = rand.nextInt(3);
        switch (choice) {
            case 0 -> microwave.turnOff();
            case 1 -> microwave.turnOn();
            case 2 -> getFoodFromFridge().ifPresent(food -> {
                if (microwave.isCooking()) return;
                microwave.cookFood(food);
                isBusy = true;
                House.getInstance().attach(this, new IsDoneCooking());
            });
        }

        logUsage(microwave);
    }

    @Override
    public void use(Oven oven) {
        int choice = rand.nextInt(3);
        switch (choice) {
            case 0 -> oven.turnOff();
            case 1 -> oven.turnOn();
            case 2 -> getFoodFromFridge().ifPresent(food -> {
                if (oven.isCooking()) return;
                oven.cookFood(food);
                isBusy = true;
                House.getInstance().attach(this, new IsDoneCooking());
            });
        }

        logUsage(oven);
    }

    private Optional<Food> getFoodFromFridge() {
        SmartHomeIterator<Fridge> iterator = new SmartHomeIterator<>(House.getInstance(), Fridge.class);
        while (iterator.hasNext()) {
            Fridge fridge = iterator.next();
            if (!fridge.isEmpty()) {
                return Optional.of(fridge.takeFood());
            }
        }

        return Optional.empty();
    }

    @Override
    public void use(TV tv) {
        int choice = rand.nextInt(2);
        switch (choice) {
            case 0 -> tv.turnOn();
            case 1 -> tv.turnOff();
        }

        logUsage(tv);
    }

    @Override
    public void use(WindowBlind windowBlind) {
        int choice = rand.nextInt(2);
        switch (choice) {
            case 0 -> windowBlind.open();
            case 1 -> windowBlind.close();
        }

        logUsage(windowBlind);
    }

    @Override
    public void use(Sensor sensor) {}

    private Manual findManual(Device device){
        return ManualPool.getManual(device);
    }

    @Override
    public void subscribeToEvents() {
        House house = House.getInstance();
        house.attach(this, new IsCrying());
        house.attach(this, new IsHungry());
        house.attach(this, new IsMakingWeirdSounds());
        house.attach(this, new IsSad());
        house.attach(this, new IsBroken());
    }

    @Override
    public void notify(IsBroken event) {
        Device brokenDevice = (Device) event.getSource();
        Manual manual = findManual(brokenDevice);
        Warranty warranty = brokenDevice.getWarranty();
        brokenDevice.repair(manual, warranty);
    }

    @Override
    public void notify(IsDoneWashing event){
        Dishwasher dishwasher = (Dishwasher) event.getSource();
        dishwasher.stop();
        isBusy = false;
        House.getInstance().detach(this, event);
    }

    @Override
    public void notify(IsDoneCooking event){
        HasCook device = (HasCook) event.getSource();
        device.getCookedFood();
        isBusy = false;
        House.getInstance().detach(this, event);
    }
}
