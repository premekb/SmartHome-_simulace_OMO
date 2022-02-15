package cz.cvut.fel.omo.smarthome.models.house.devices;

import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsDoneWashing;
import cz.cvut.fel.omo.smarthome.models.house.devices.consumption.DeviceConsumptionRate;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Person;
import java.util.Random;

public class Dishwasher extends Device {
    Random rand = new Random();

    public Dishwasher() {
        this.idleConsumptionRate = DeviceConsumptionRate.of(0,0, 1);
        this.activeConsumptionRate = DeviceConsumptionRate.of(100, 0, 10);
    }

    public void accept(Person person) {
        person.use(this);
    }

    @Override
    public void simulateOneTick(){
        super.simulateOneTick();
        simulateWashing();
    }

    private void simulateWashing() {
        if (state.isActive() && rand.nextBoolean()) {
            deactivate();
            publishEvent(new IsDoneWashing());
        }
    }

    public void start() {
        super.activate();
    }

    public void stop() {
        super.deactivate();
    }
}
