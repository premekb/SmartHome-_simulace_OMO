package cz.cvut.fel.omo.smarthome.models.house.devices;

import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsDoneCooking;
import cz.cvut.fel.omo.smarthome.interfaces.traits.HasCook;
import cz.cvut.fel.omo.smarthome.models.house.devices.consumption.DeviceConsumptionRate;
import cz.cvut.fel.omo.smarthome.models.house.devices.items.Food;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Person;

public class Oven extends Device implements HasCook {

    public Oven() {
        this.idleConsumptionRate = DeviceConsumptionRate.of(0,5, 2);
        this.activeConsumptionRate = DeviceConsumptionRate.of(0, 25, 5);
    }

    public void accept(Person person) {
        person.use(this);
    }

    @Override
    public void simulateOneTick(){
        super.simulateOneTick();
        if (state.isActive()) {
            cook.simulateCooking();
        }
        if (cook.isDone()) {
            publishEvent(new IsDoneCooking());
            deactivate();
        }
    }

    public void turnOn() {
        super.turnOn();
    }

    public void turnOff() {
        super.turnOff();
    }

    @Override
    public void cookFood(Food food) {
        super.activate();
        cook.startCooking(food);
    }
}
