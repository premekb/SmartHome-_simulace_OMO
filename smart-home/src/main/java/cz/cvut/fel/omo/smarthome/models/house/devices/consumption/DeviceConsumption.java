package cz.cvut.fel.omo.smarthome.models.house.devices.consumption;

import cz.cvut.fel.omo.smarthome.configuration.Configuration;

/**
 * Contains information about the consumption of a device in an arbitrary long time interval.
 */
public class DeviceConsumption {
    private final Integer water;

    private final Integer gas;

    private final Integer electricity;

    private DeviceConsumption(Integer water, Integer gas, Integer electricity) {
        this.water = water;
        this.gas = gas;
        this.electricity = electricity;
    }

    public static DeviceConsumption of(Integer water, Integer gas, Integer electricity){
        return new DeviceConsumption(water, gas, electricity);
    }

    public static DeviceConsumption of(DeviceConsumption consumption, DeviceConsumptionRate rate){
        return new DeviceConsumption(
                consumption.getWater() + rate.getWaterPerTick(),
                consumption.getGas() + rate.getGasPerTick(),
                consumption.getElectricity() + rate.getElectricityPerTick());
    }

    public static DeviceConsumption of(DeviceConsumption consumption1, DeviceConsumption consumption2){
        return new DeviceConsumption(
                consumption1.getWater() + consumption2.getWater(),
                consumption1.getGas() + consumption2.getGas(),
                consumption1.getElectricity() + consumption2.getElectricity());
    }

    public Integer getWater() {
        return water;
    }

    public Integer getGas() {
        return gas;
    }

    public Integer getElectricity() {
        return electricity;
    }

    @Override
    public String toString() {
        Configuration cfg = Configuration.getInstance();
        final Integer waterPrice = cfg.getWaterUnitCost();
        final Integer gasPrice = cfg.getGasUnitCost();
        final Integer electricityPrice = cfg.getElectricityUnitCost();

        return "Water: " + water + " units, Price: " + waterPrice * water + "$\n" +
                "Gas: " + gas + " units, Price: " + gasPrice * gas + "$\n" +
                "Electricity: " + electricity + " units, Price: " + electricity * electricityPrice + "$ \n";
    }
}
