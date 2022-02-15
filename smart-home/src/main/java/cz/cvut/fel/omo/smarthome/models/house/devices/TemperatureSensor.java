package cz.cvut.fel.omo.smarthome.models.house.devices;

import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsTooHot;

public class TemperatureSensor extends Sensor {

    public TemperatureSensor() {
        super();
        addRandomlyPublishedEvent(new IsTooHot());
    }
}
