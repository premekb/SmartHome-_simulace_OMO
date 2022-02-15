package cz.cvut.fel.omo.smarthome.models.house.devices.items;

import java.util.Random;

public class Food extends Item {
    private final String[] TYPES = new String[]{ "Ham", "Fish", "Noodles", "Bacon", "Sausage", "Lasagna", "Chicken", "Beef", "Eggs", "Pizza" };
    private final String type = TYPES[new Random().nextInt(10)];
    private Boolean cooked = false;

    @Override
    public String toString() {
        return cooked ? "Cooked " : "" + type;
    }

    public Boolean isCooked() {
        return cooked;
    }

    /**
     * Sets the cooked state to true
     * @return itself
     */
    public Food cook() {
        this.cooked = true;
        return this;
    }
}
