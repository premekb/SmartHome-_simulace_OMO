package cz.cvut.fel.omo.smarthome.models.inhabitants;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.interfaces.events.EventPublisher;
import cz.cvut.fel.omo.smarthome.interfaces.events.Observer;
import cz.cvut.fel.omo.smarthome.iterators.RoomIterator;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.house.Room;
import cz.cvut.fel.omo.smarthome.models.house.devices.Device;
import cz.cvut.fel.omo.smarthome.reports.Action;
import cz.cvut.fel.omo.smarthome.reports.visitors.ActivityAndUsageVisitor;
import cz.cvut.fel.omo.smarthome.reports.visitors.ConfigurationVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


abstract public class Inhabitant implements EventPublisher, Observer {
    protected final Random rand = new Random();

    protected String name;

    protected Room currentRoom;

    private boolean isOutside = true;

    private List<Action> actionList = new ArrayList<>();

    public List<Action> getActionList() {
        return actionList;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    public void accept(ConfigurationVisitor configurationVisitor){
        configurationVisitor.visitInhabitant(this);
    }


    public void accept(ActivityAndUsageVisitor activityAndUsageVisitor) {
        if (this.actionList.isEmpty()) return;

        activityAndUsageVisitor.visitInhabitant(this);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + name;
    }

    public void simulateOneTick(){
        double probability = this.rand.nextDouble();

        if (probability <= 0.1) { // 10% chance
            publishRandomEvent();
        } else if (probability <= 0.7) { // 70% chance
            goToRandomRoom();
        }
    }

    private void goToRandomRoom() {
        RoomIterator roomIterator = House.getInstance().getRoomIterator();
        Integer randomIndex = rand.nextInt(roomIterator.size());
        goToRoom(roomIterator.get(randomIndex));
    }

    /**
     * Moves the {@link Inhabitant} to the specified {@link Room}
     * @param room {@link Room} to move the {@link Inhabitant} to
     */
    public void goToRoom(Room room){
        if (this.currentRoom != null){
            this.currentRoom.removeInhabitant(this);
        }
        isOutside = false;
        currentRoom = room;
        room.addInhabitant(this);
    }

    public void setCurrentRoom(Room currentRoom) {
        this.isOutside = false;
        this.currentRoom = currentRoom;
    }

    public void setOutside(){
        this.isOutside = true;
        this.currentRoom = null;
    }

    public boolean isOutside(){
        return isOutside;
    }

    public void logUsage(Device device) {
        actionList.add(new Action(this, device));
    }

    @Override
    public Room getCurrentRoom() {
        return currentRoom;
    }
}
