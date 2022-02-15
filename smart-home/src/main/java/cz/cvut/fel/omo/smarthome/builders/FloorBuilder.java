package cz.cvut.fel.omo.smarthome.builders;

import cz.cvut.fel.omo.smarthome.factories.RoomFactory;
import cz.cvut.fel.omo.smarthome.models.house.Floor;
import cz.cvut.fel.omo.smarthome.models.house.Room;

import java.util.ArrayList;

public class FloorBuilder {
    private ArrayList<Room> rooms = new ArrayList<>();

    private RoomFactory roomFactory = new RoomFactory();

    /**
     * Adds a kitchen to the {@link Floor}
     * @return itself
     */
    public FloorBuilder addKitchen(){
        rooms.add(roomFactory.makeKitchen());
        return this;
    }

    /**
     * Adds a cellar to the {@link Floor}
     * @return itself
     */
    public FloorBuilder addCellar(){
        rooms.add(roomFactory.makeCellar());
        return this;
    }

    /**
     * Adds a living room to the {@link Floor}
     * @return itself
     */
    public FloorBuilder addLivingRoom(){
        rooms.add(roomFactory.makeLivingRoom());
        return this;
    }

    /**
     * Adds a TV room to the {@link Floor}
     * @return itself
     */
    public FloorBuilder addTVRoom(){
        rooms.add(roomFactory.makeTVRoom());
        return this;
    }

    /**
     * Adds a bed room to the {@link Floor}
     * @return itself
     */
    public FloorBuilder addBedroom() {
        rooms.add(roomFactory.makeBedroom());
        return this;
    }

    /**
     * Builds the {@link Floor}
     * @return {@link Floor}
     */
    public Floor getResult(){
        return new Floor(rooms);
    }
}
