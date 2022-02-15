package cz.cvut.fel.omo.smarthome.interfaces.reports;

import cz.cvut.fel.omo.smarthome.reports.ActivityAndUsageReport;
import cz.cvut.fel.omo.smarthome.reports.ConsumptionReport;
import cz.cvut.fel.omo.smarthome.reports.EventReport;
import cz.cvut.fel.omo.smarthome.reports.HouseConfigurationReport;

public interface HasReport {

    /**
     * @return Generated {@link HouseConfigurationReport}
     */
    public HouseConfigurationReport getHouseConfigurationReport();

    /**
     * @return Generated {@link ActivityAndUsageReport}
     */
    public ActivityAndUsageReport getActivityAndUsageReport();

    /**
     * @return Generated {@link ConsumptionReport}
     */
    public ConsumptionReport getConsumptionReport();

    /**
     * @return Generated {@link EventReport}
     */
    public EventReport getEventReport();
}
