package cz.cvut.fel.omo.smarthome.models.house.devices;

import cz.cvut.fel.omo.smarthome.exceptions.IllegalOperationException;
import cz.cvut.fel.omo.smarthome.models.house.devices.consumption.DeviceConsumptionRate;
import cz.cvut.fel.omo.smarthome.models.house.devices.items.CD;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Person;
import java.util.Optional;

public class AudioVideoReceiver extends Device {
    private Optional<CD> cd = Optional.empty();

    public AudioVideoReceiver() {
        this.idleConsumptionRate = DeviceConsumptionRate.of(0,0, 7);
        this.activeConsumptionRate = DeviceConsumptionRate.of(0, 0, 55);
    }

    public void accept(Person person) {
        person.use(this);
    }

    public void insertCD(CD cd) {
        if (this.cd.isPresent()) throw new IllegalOperationException("Unable to insert CD");

        this.cd = Optional.of(cd);
    }

    public boolean hasCD() {
        return cd.isPresent();
    }

    public CD removeCD() throws IllegalOperationException {
        if (cd.isEmpty()) throw new IllegalOperationException("Unable to remove CD");

        CD tmp = cd.get();
        cd = Optional.empty();
        return tmp;
    }

    public void turnOn(){
        super.turnOn();
    };

    public void turnOff(){
        super.turnOff();
    };

    public void play(){
        super.activate();
    };

    public void stop(){
        super.deactivate();
    };
}
