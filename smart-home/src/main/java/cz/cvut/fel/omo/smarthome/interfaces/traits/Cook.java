package cz.cvut.fel.omo.smarthome.interfaces.traits;

import cz.cvut.fel.omo.smarthome.models.house.devices.items.Food;
import cz.cvut.fel.omo.smarthome.models.house.devices.state.CookState;

public class Cook {
    private CookState cookState = CookState.Off;
    private Food contents;

    /**
     * Simulates one tick of cooking
     */
    public void simulateCooking() {
        if (cookState.equals(CookState.Off)) return;

        if (cookState.equals(CookState.Done)) {
            contents.cook();
        }

        cookState = cookState.nextState();
    }

    /**
     * Starts cooking the specified {@link Food}
     * @param food {@link Food} to cook
     */
    public void startCooking(Food food) {
        if (cookState.equals(CookState.Off)) {
            cookState = CookState.Preheating;
            contents = food;
        }
    }

    /**
     * Takes the cooked {@link Food} out
     * @return The cooked {@link Food}
     */
    public Food getCookedFood() {
        Food tmp = contents;
        contents = null;
        cookState = CookState.Off;
        return tmp;
    }

    /**
     * @return Whether the {@link Food} is done cooking
     */
    public boolean isDone() {
        return contents != null && contents.isCooked();
    }

    /**
     * @return Whether it is currently cooking
     */
    public boolean isCooking() {
        return !cookState.equals(CookState.Off);
    }
}
