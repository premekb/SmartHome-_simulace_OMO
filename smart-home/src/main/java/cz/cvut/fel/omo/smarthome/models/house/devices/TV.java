package cz.cvut.fel.omo.smarthome.models.house.devices;

import cz.cvut.fel.omo.smarthome.models.house.devices.consumption.DeviceConsumptionRate;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Person;

public class TV extends Device{
    public TV() {
        this.idleConsumptionRate = DeviceConsumptionRate.of(0,0, 1);
        this.activeConsumptionRate = DeviceConsumptionRate.of(0, 0, 50);
    }

    @Override
    public void activate() {
        super.activate();
    }

    @Override
    public void deactivate() {
        super.deactivate();
    }

    public void accept(Person person) {
        person.use(this);
    }

    public void turnOn() {
        super.activate();
    }

    public void turnOff() {
        super.deactivate();
    }
}
