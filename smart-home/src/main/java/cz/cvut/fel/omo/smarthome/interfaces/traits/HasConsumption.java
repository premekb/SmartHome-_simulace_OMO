package cz.cvut.fel.omo.smarthome.interfaces.traits;

import cz.cvut.fel.omo.smarthome.models.house.devices.consumption.DeviceConsumptionRate;
import cz.cvut.fel.omo.smarthome.models.house.devices.consumption.DeviceConsumptionTracker;
import cz.cvut.fel.omo.smarthome.reports.visitors.ConsumptionVisitor;

public interface HasConsumption {

    /**
     * @return Idle {@link DeviceConsumptionRate}
     */
    DeviceConsumptionRate getIdleConsumptionRate();

    /**
     * @return Active {@link DeviceConsumptionRate}
     */
    DeviceConsumptionRate getActiveConsumptionRate();

    /**
     * @return {@link DeviceConsumptionTracker}
     */
    DeviceConsumptionTracker getConsumptionTracker();

    /**
     * Used to write to the ConsumptionReport text
     * @param consumptionVisitor {@link ConsumptionVisitor}
     */
    void accept(ConsumptionVisitor consumptionVisitor);
}
