package cz.cvut.fel.omo.smarthome.models.house;

import cz.cvut.fel.omo.smarthome.models.house.devices.Device;
import cz.cvut.fel.omo.smarthome.models.house.furniture.Furniture;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Inhabitant;
import cz.cvut.fel.omo.smarthome.reports.visitors.ConfigurationVisitor;

import java.util.ArrayList;

public class Room {
    private ArrayList<Device> devices = new ArrayList<>();

    private ArrayList<Window> windows = new ArrayList<>();

    private ArrayList<Furniture> furniture = new ArrayList<>();

    private ArrayList<Inhabitant> inhabitants = new ArrayList<>();

    public void addDevice(Device device){
        devices.add(device);
        device.subscribeToEvents();
        device.setRoom(this);
    }

    public void addWindow(Window window){
        windows.add(window);
    }

    public void addFurniture(Furniture furniture){
        this.furniture.add(furniture);
    }

    public void addInhabitant(Inhabitant inhabitant){
        inhabitants.add(inhabitant);
        inhabitant.setCurrentRoom(this);
    }

    public void removeInhabitant(Inhabitant inhabitant){
        inhabitants.remove(inhabitant);
        inhabitant.setCurrentRoom(null);
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public ArrayList<Window> getWindows() {
        return windows;
    }

    public ArrayList<Furniture> getFurniture() {
        return furniture;
    }

    public ArrayList<Inhabitant> getInhabitants() {
        return inhabitants;
    }

    public ArrayList<Object> getAllObjectsInRoom(){
        ArrayList<Object> objects = new ArrayList<>();
        objects.addAll(this.inhabitants);
        objects.addAll(this.furniture);
        objects.addAll(this.windows);
        objects.addAll(this.devices);
        return objects;
    }

    public void accept(ConfigurationVisitor configurationVisitor){
        configurationVisitor.visitRoom(this);
    }
}
