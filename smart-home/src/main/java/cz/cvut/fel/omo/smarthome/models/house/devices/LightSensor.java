package cz.cvut.fel.omo.smarthome.models.house.devices;

import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsTooBright;
import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsTooDark;

public class LightSensor extends Sensor {

    public LightSensor() {
        super();
        addRandomlyPublishedEvent(new IsTooBright());
        addRandomlyPublishedEvent(new IsTooDark());
    }
}
