package cz.cvut.fel.omo.smarthome.interfaces.traits;

import cz.cvut.fel.omo.smarthome.models.house.devices.items.Food;

public interface HasCook {
    Cook cook = new Cook();

    /**
     * Starts cooking the {@link Food}
     * @param food {@link Food} to be cooked
     */
    void cookFood(Food food);

    /**
     * @return Whether the {@link HasCook} instance is currently cooking
     */
    default boolean isCooking() {
        return cook.isCooking();
    }

    /**
     * Takes the cooked {@link Food} out of the {@link HasCook} instance
     * @return The cooked {@link Food}
     */
    default Food getCookedFood() {
        return cook.getCookedFood();
    }

}
