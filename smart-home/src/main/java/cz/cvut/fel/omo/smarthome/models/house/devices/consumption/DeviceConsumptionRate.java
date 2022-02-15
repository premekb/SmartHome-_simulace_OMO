package cz.cvut.fel.omo.smarthome.models.house.devices.consumption;

/**
 * Contains information about how much a device conumes in one ticků
 */
public class DeviceConsumptionRate {
    private final Integer waterPerTick;

    private final Integer gasPerTick;

    private final Integer electricityPerTick;

    private DeviceConsumptionRate(Integer waterPerTick, Integer gasPerTick, Integer electricityPerTick) {
        this.gasPerTick = gasPerTick;
        this.electricityPerTick = electricityPerTick;
        this.waterPerTick = waterPerTick;
    }

    public static DeviceConsumptionRate of(Integer waterPerTick, Integer gasPerTick, Integer electricityPerTick){
        return new DeviceConsumptionRate(waterPerTick, gasPerTick, electricityPerTick);
    }

    public Integer getGasPerTick() {
        return gasPerTick;
    }

    public Integer getElectricityPerTick() {
        return electricityPerTick;
    }

    public Integer getWaterPerTick() {
        return waterPerTick;
    }
}
