package cz.cvut.fel.omo.smarthome.reports.visitors;

import cz.cvut.fel.omo.smarthome.models.house.Floor;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.house.Room;
import cz.cvut.fel.omo.smarthome.models.house.Window;
import cz.cvut.fel.omo.smarthome.models.house.devices.Device;
import cz.cvut.fel.omo.smarthome.models.house.devices.items.CD;
import cz.cvut.fel.omo.smarthome.models.house.furniture.Furniture;
import cz.cvut.fel.omo.smarthome.models.house.sportsequipment.SportsEquipment;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Inhabitant;
import cz.cvut.fel.omo.smarthome.reports.Action;
import cz.cvut.fel.omo.smarthome.reports.ActivityAndUsageReport;
import cz.cvut.fel.omo.smarthome.reports.HouseConfigurationReport;
import java.util.Map;
import java.util.stream.Collectors;

public class ActivityAndUsageVisitor {
    private StringBuilder reportTextBuilder = new StringBuilder();

    public void visitInhabitant(Inhabitant inhabitant){
        reportTextBuilder.append(inhabitant.toString()).append("\n");
        reportTextBuilder.append("-".repeat(inhabitant.toString().length())).append("\n");

        Map<Device, Long> usageByDevice = inhabitant.getActionList().stream()
            .collect(Collectors.groupingBy(Action::getWhat, Collectors.counting()));

        reportTextBuilder.append(usageByDevice.keySet().stream()
            .map(key -> "Used " + key + " " + usageByDevice.get(key) + "x")
            .collect(Collectors.joining("\n", "", "\n")));

        reportTextBuilder.append("\n");
    }

    public ActivityAndUsageReport getReport() {
        String title = """
            ------------ Activity and Usage Report ------------
            """;
        return new ActivityAndUsageReport(title + reportTextBuilder.toString());
    }
}
