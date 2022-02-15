package cz.cvut.fel.omo.smarthome.models.house;

import cz.cvut.fel.omo.smarthome.reports.visitors.ConfigurationVisitor;

import java.util.ArrayList;

public class Floor {
    private ArrayList<Room> rooms;

    public Floor(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void accept(ConfigurationVisitor configurationVisitor){
        configurationVisitor.visitFloor(this);
    }
}
