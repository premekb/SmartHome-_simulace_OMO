package cz.cvut.fel.omo.smarthome.models.house.devices.state;

import cz.cvut.fel.omo.smarthome.models.house.devices.Device;

abstract public class DeviceState {
    public abstract void turnOn(Device device); // off to on

    public abstract void turnOff(Device device); // on to off, or idle to off

    public abstract void activate(Device device); // idle to active

    public abstract void deactivate(Device device); // active to idle

    public boolean isActive(){ return this instanceof ActiveState;}

    public boolean isOff(){return this instanceof OffState;}

    public boolean isIdle(){return this instanceof IdleState;}
}
