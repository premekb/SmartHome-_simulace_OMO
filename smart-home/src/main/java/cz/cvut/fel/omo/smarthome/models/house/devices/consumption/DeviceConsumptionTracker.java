package cz.cvut.fel.omo.smarthome.models.house.devices.consumption;

import cz.cvut.fel.omo.smarthome.models.house.devices.Device;

/**
 * Tracks the consumption of a device.
 */
public class DeviceConsumptionTracker {
    private DeviceConsumption totalConsumption = DeviceConsumption.of(0, 0, 0);

    private DeviceConsumption consumptionSinceReset = DeviceConsumption.of(0, 0, 0);

    private final Device device;

    public DeviceConsumptionTracker(Device device) {
        this.device = device;
    }

    /**
     * Increases the consumption of the device based on its current state.
     */
    public void incrementPerTick(){
        if (device.getState().isIdle()){
            totalConsumption = DeviceConsumption.of(totalConsumption, device.getIdleConsumptionRate());
            consumptionSinceReset = DeviceConsumption.of(consumptionSinceReset, device.getIdleConsumptionRate());
        }

        else if (device.getState().isActive()){
            totalConsumption = DeviceConsumption.of(totalConsumption, device.getActiveConsumptionRate());
            consumptionSinceReset = DeviceConsumption.of(consumptionSinceReset, device.getActiveConsumptionRate());
        }
    }


    public void reset(){
        this.consumptionSinceReset = DeviceConsumption.of(0, 0, 0);
    }

    public DeviceConsumption getTotalConsumption() {
        return totalConsumption;
    }

    public DeviceConsumption getConsumptionSinceReset() {
        return consumptionSinceReset;
    }
}
