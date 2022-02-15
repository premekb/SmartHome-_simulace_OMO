package cz.cvut.fel.omo.smarthome.models.house.devices;

import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsTooBright;
import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsTooDark;
import cz.cvut.fel.omo.smarthome.interfaces.traits.Brightness;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.house.devices.consumption.DeviceConsumptionRate;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Person;

public class Light extends Device {
    private Brightness brightness = new Brightness();

    public Light() {
        this.idleConsumptionRate = DeviceConsumptionRate.of(0,0, 0);
        this.activeConsumptionRate = DeviceConsumptionRate.of(0, 0, 20);
    }

    @Override
    public void subscribeToEvents() {
        House.getInstance().attach(this, new IsTooBright());
        House.getInstance().attach(this, new IsTooDark());
    }

    public Brightness getBrightness() {
        return brightness;
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

    @Override
    public void notify(IsTooBright event) {
        turnOff();
        brightness.lowerBrightness();
    }

    @Override
    public void notify(IsTooDark event) {
        turnOn();
        brightness.raiseBrightness();
    }
}
