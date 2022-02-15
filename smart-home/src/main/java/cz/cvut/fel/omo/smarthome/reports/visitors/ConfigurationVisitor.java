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
import cz.cvut.fel.omo.smarthome.reports.HouseConfigurationReport;

public class ConfigurationVisitor {
    private StringBuilder reportTextBuilder = new StringBuilder();

    public void visitDevice(Device device){
        reportTextBuilder.append("---- ").append(device.toString()).append("\n");
    }

    public void visitHouse(House house){
        reportTextBuilder.append("- House\n");
        for (Floor floor : house.getFloors()){
            floor.accept(this);
        }
    }

    public void visitFloor(Floor floor){
        reportTextBuilder.append("-- Floor\n");
        for (Room room : floor.getRooms()){
            room.accept(this);
        }
    }

    public void visitRoom(Room room){
        reportTextBuilder.append("--- Room\n");
        for (Inhabitant inhabitant : room.getInhabitants()){
            inhabitant.accept(this);
        }

        for (Device device : room.getDevices()){
            device.accept(this);
        }

        for (Furniture furniture : room.getFurniture()){
            furniture.accept(this);
        }

        for (Window window : room.getWindows()){
            window.accept(this);
        }
    }

    public void visitInhabitant(Inhabitant inhabitant){
        reportTextBuilder.append("---- ").append(inhabitant.toString()).append(("\n"));
    }

    public void visitFurniture(Furniture furniture){
        reportTextBuilder.append("---- ").append(furniture.toString()).append("\n");
    }

    public void visitSportsEquipment(SportsEquipment sportsEquipment){
        reportTextBuilder.append("----- ").append(sportsEquipment.toString()).append(("\n"));
    }

    public void visitCD(CD cd){
        reportTextBuilder.append("----- ").append(cd.toString()).append(("\n"));
    }

    public void visitWindow(Window window){
        reportTextBuilder.append("---- Window \n");
        if (window.getBlind() != null){
            reportTextBuilder.append("----- ").append(window.getBlind()).append("\n");
        }
    }

    public HouseConfigurationReport getReport(){
        return new HouseConfigurationReport(reportTextBuilder.toString());
    }
}
