package cz.cvut.fel.omo.smarthome.models.house.devices;

import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsTooHumid;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.house.devices.consumption.DeviceConsumptionRate;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Person;

public class Dehumidifier extends Device {
    public Dehumidifier() {
        this.idleConsumptionRate = DeviceConsumptionRate.of(0,0, 1);
        this.activeConsumptionRate = DeviceConsumptionRate.of(0, 0, 10);
    }

    @Override
    public void subscribeToEvents() {
        House.getInstance().attach(this, new IsTooHumid());
    }

    public void accept(Person person) {
        person.use(this);
    }

    public void turnOn() {
        super.turnOn();
    }

    public void turnOff() {
        super.turnOff();
    }

    public void start() {
        super.activate();
    }

    public void stop() {
        super.deactivate();
    }

    @Override
    public void notify(IsTooHumid event) {
        start();
    }
}
