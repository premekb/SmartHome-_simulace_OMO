package cz.cvut.fel.omo.smarthome.models.house.devices;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsTooHot;
import cz.cvut.fel.omo.smarthome.interfaces.traits.Temperature;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.house.devices.consumption.DeviceConsumptionRate;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Person;

public class AC extends Device {
    Temperature temperature = new Temperature();

    public AC() {
        this.idleConsumptionRate = DeviceConsumptionRate.of(5,0, 8);
        this.activeConsumptionRate = DeviceConsumptionRate.of(20, 0, 70);
    }

    @Override
    public void subscribeToEvents() {
        House.getInstance().attach(this, new IsTooHot());
    }

    public void accept(Person person) {
        person.use(this);
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void turnOn(){
        super.turnOn();
    }

    public void turnOff(){
        super.turnOff();
    }

    public void start(){
        super.activate();
    }

    public void stop(){
        super.deactivate();
    }

    @Override
    public void notify(IsTooHot event) {
        start();
        temperature.lowerTemperature();
    }
}
