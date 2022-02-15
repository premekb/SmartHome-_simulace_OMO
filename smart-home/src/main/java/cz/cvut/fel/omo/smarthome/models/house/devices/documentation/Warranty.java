package cz.cvut.fel.omo.smarthome.models.house.devices.documentation;

import cz.cvut.fel.omo.smarthome.models.house.devices.Device;

public class Warranty {
    private final Device device;

    private Warranty(Device device){
        this.device = device;
    }

    public static Warranty downloadWarranty(Device device){
        return new Warranty(device);
    }

    public Device getDevice() {
        return device;
    }
}
