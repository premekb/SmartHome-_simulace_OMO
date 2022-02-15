package cz.cvut.fel.omo.smarthome.models.house.devices.state;

import cz.cvut.fel.omo.smarthome.models.house.devices.Device;

public class OffState extends DeviceState{
    @Override
    public void turnOn(Device device) {
        if (!device.isBroken()) device.setState(new IdleState());
    }

    @Override
    public void turnOff(Device device) {
    }

    @Override
    public void activate(Device device) {
    }

    @Override
    public void deactivate(Device device) {

    }
}
