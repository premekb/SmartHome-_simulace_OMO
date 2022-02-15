package cz.cvut.fel.omo.smarthome.builders;

import cz.cvut.fel.omo.smarthome.models.house.Floor;
import cz.cvut.fel.omo.smarthome.models.house.Room;
import cz.cvut.fel.omo.smarthome.models.house.Window;
import cz.cvut.fel.omo.smarthome.models.house.devices.Device;
import cz.cvut.fel.omo.smarthome.models.house.devices.WindowBlind;
import cz.cvut.fel.omo.smarthome.models.house.furniture.Furniture;

public class RoomBuilder {
    private Room room;

    public RoomBuilder() {
        room = new Room();
    }

    /**
     * Adds a device to the {@link Room}
     * @return itself
     */
    public RoomBuilder addDevice(Device device){
        room.addDevice(device);
        return this;
    }

    /**
     * Adds furniture to the {@link Room}
     * @return itself
     */
    public RoomBuilder addFurniture(Furniture furniture){
        room.addFurniture(furniture);
        return this;
    }

    /**
     * Adds a window to the {@link Room}
     * @return itself
     */
    public RoomBuilder addWindow(){
        room.addWindow(new Window());
        return this;
    }

    /**
     * Adds a window with a {@link WindowBlind} to the {@link Room}
     * @return itself
     */
    public RoomBuilder addWindow(boolean withBlind){
        Window window = new Window();

        if (withBlind){
            Device windowBlind = new WindowBlind(window);
            room.addDevice(windowBlind);
        }

        room.addWindow(window);
        return this;
    }

    /**
     * Builds the {@link Room}
     * @return {@link Room}
     */
    public Room getResult(){
        Room result = this.room;
        reset();
        return result;
    }

    /**
     * Resets itself
     */
    private void reset(){
        room = new Room();
    }
}
