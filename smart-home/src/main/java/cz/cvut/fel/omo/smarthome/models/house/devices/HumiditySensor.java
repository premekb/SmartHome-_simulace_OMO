package cz.cvut.fel.omo.smarthome.models.house.devices;

import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsTooHumid;

public class HumiditySensor extends Sensor {

    public HumiditySensor() {
        super();
        addRandomlyPublishedEvent(new IsTooHumid());
    }
}
