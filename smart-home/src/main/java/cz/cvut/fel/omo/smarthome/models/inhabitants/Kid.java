package cz.cvut.fel.omo.smarthome.models.inhabitants;

import cz.cvut.fel.omo.smarthome.events.inhabitantevents.importantevents.IsCrying;
import cz.cvut.fel.omo.smarthome.events.inhabitantevents.importantevents.IsHungry;
import cz.cvut.fel.omo.smarthome.models.house.devices.AC;
import cz.cvut.fel.omo.smarthome.models.house.devices.AudioVideoReceiver;
import cz.cvut.fel.omo.smarthome.models.house.devices.Dehumidifier;
import cz.cvut.fel.omo.smarthome.models.house.devices.Dishwasher;
import cz.cvut.fel.omo.smarthome.models.house.devices.Fridge;
import cz.cvut.fel.omo.smarthome.models.house.devices.Light;
import cz.cvut.fel.omo.smarthome.models.house.devices.Microwave;
import cz.cvut.fel.omo.smarthome.models.house.devices.Oven;
import cz.cvut.fel.omo.smarthome.models.house.devices.Sensor;
import cz.cvut.fel.omo.smarthome.models.house.devices.TV;
import cz.cvut.fel.omo.smarthome.models.house.devices.WindowBlind;

public class Kid extends Person{
    public Kid() {
        super();
        addRandomlyPublishedEvent(new IsHungry());
        addRandomlyPublishedEvent(new IsCrying());
    }

    @Override
    public void use(AC ac) {

    }

    @Override
    public void use(AudioVideoReceiver audioVideoReceiver) {

    }

    @Override
    public void use(Dehumidifier dehumidifier) {

    }

    @Override
    public void use(Dishwasher dishwasher) {

    }

    @Override
    public void use(Fridge fridge) {

    }

    @Override
    public void use(Light light) {

    }

    @Override
    public void use(Microwave microwave) {

    }

    @Override
    public void use(Oven oven) {

    }

    @Override
    public void use(TV tv) {

    }

    @Override
    public void use(WindowBlind windowBlind) {

    }

    @Override
    public void use(Sensor sensor) {

    }
}
