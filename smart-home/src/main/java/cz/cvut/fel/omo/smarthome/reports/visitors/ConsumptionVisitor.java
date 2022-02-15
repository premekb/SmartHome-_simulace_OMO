package cz.cvut.fel.omo.smarthome.reports.visitors;

import cz.cvut.fel.omo.smarthome.iterators.SmartHomeIterator;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.house.devices.Device;
import cz.cvut.fel.omo.smarthome.models.house.devices.consumption.DeviceConsumption;
import cz.cvut.fel.omo.smarthome.models.house.devices.consumption.DeviceConsumptionTracker;
import cz.cvut.fel.omo.smarthome.reports.ConsumptionReport;

public class ConsumptionVisitor {
    private StringBuilder reportTextBuilder = new StringBuilder();

    private DeviceConsumption totalConsumption = DeviceConsumption.of(0, 0, 0);

    private DeviceConsumption sinceLastVisitConsumption = DeviceConsumption.of(0, 0, 0);

    public void visitDevice(Device device){
        DeviceConsumptionTracker tracker = device.getConsumptionTracker();
        sinceLastVisitConsumption = DeviceConsumption.of(sinceLastVisitConsumption, tracker.getConsumptionSinceReset());
        totalConsumption = DeviceConsumption.of(totalConsumption, tracker.getTotalConsumption());

        reportTextBuilder
                .append(device).append("\n")
                .append("- Totally consumed:\n")
                .append(tracker.getTotalConsumption());
        reportTextBuilder
                .append("- Consumed since last consumption report:\n")
                .append(tracker.getConsumptionSinceReset())
                .append("\n");

        tracker.reset();
    }

    public void visitHouse(House house){
        reportTextBuilder.append("------------ Consumption report ------------\n");
        SmartHomeIterator<Device> deviceIterator = house.getDeviceIterator();

        while(deviceIterator.hasNext()){
            deviceIterator.next().accept(this);
        }

        reportTextBuilder
                .append("\n")
                .append("Total consumption in house:\n")
                .append(totalConsumption)
                .append("\n");

        reportTextBuilder
                .append("Consumption in house since last measuring:\n")
                .append(sinceLastVisitConsumption);
    }

    public ConsumptionReport getReport(){
        return new ConsumptionReport(reportTextBuilder.toString());
    }
}
