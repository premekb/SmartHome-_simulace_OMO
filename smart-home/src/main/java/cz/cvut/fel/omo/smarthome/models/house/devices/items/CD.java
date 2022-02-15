package cz.cvut.fel.omo.smarthome.models.house.devices.items;

import java.util.Random;

public class CD extends Item {
    Integer number = new Random().nextInt();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + number.toString();
    }
}
