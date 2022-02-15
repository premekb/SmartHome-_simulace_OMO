package cz.cvut.fel.omo.smarthome.models.house.devices.documentation;

import cz.cvut.fel.omo.smarthome.models.house.devices.Device;

public class Manual {
    private Class<? extends Device> deviceType;

    public Manual(Device deviceType) {
        this.deviceType = deviceType.getClass();
    }

    public Class<? extends Device> getDeviceType() {
        return deviceType;
    }
}
