package cz.cvut.fel.omo.smarthome.interfaces.traits;

public class Temperature {
    Integer DEFAULT_STEP = 1;
    Integer temperature = 50;

    /**
     * Raises the temperature by {@link #DEFAULT_STEP}
     */
    public void raiseTemperature() {
        if (temperature + DEFAULT_STEP <= 100) {
            raiseTemperature(DEFAULT_STEP);
        }
    }

    /**
     * Raises the temperature by the specified step
     * @param step Increment by
     */
    public void raiseTemperature(Integer step) {
        if (temperature + step <= 100) {
            temperature += step;
        }
    }

    /**
     * Lowers the temperature by {@link #DEFAULT_STEP}
     */
    public void lowerTemperature() {
        if (temperature + DEFAULT_STEP >= 0) {
            lowerTemperature(DEFAULT_STEP);
        }
    }

    /**
     * Lowers the temperature by the specified step
     * @param step Decrement by
     */
    public void lowerTemperature(Integer step) {
        if (temperature + step >= 0) {
            temperature -= step;
        }
    }
}
