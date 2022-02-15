package cz.cvut.fel.omo.smarthome.models.house.devices.state;

import cz.cvut.fel.omo.smarthome.models.house.devices.Device;

public class ActiveState extends DeviceState{
    @Override
    public void turnOn(Device device) {

    }

    @Override
    public void turnOff(Device device) {
        device.setState(new OffState());
    }

    @Override
    public void activate(Device device) {

    }

    @Override
    public void deactivate(Device device) {
        device.setState(new IdleState());
    }
}
