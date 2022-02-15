package cz.cvut.fel.omo.smarthome.iterators;

import cz.cvut.fel.omo.smarthome.models.house.Floor;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.house.Room;

import java.util.ArrayList;

public class SmartHomeIterator<T> {
    private House house;

    private Integer floorIndex;

    private Integer roomIndex;

    private Integer devicesInRoomIndex;

    private boolean moreObjectsLeft;

    private ArrayList<T> objectsInRoom;

    private final Class<T> iteratorObjectClass;

    public SmartHomeIterator(House house, Class<T> iteratorObjectClass) {
        this.house = house;
        this.iteratorObjectClass = iteratorObjectClass;
        floorIndex = 0;
        roomIndex = 0;
        devicesInRoomIndex = 0;
        moreObjectsLeft = true;
        moveToNextObject();
    }

    public boolean hasNext() {
        return moreObjectsLeft;
    }

    /**
     * Returns current object and moves the iterator to next object.
     *
     * @return Inhabitant
     */
    public T next() {
        T toReturn = objectsInRoom.get(devicesInRoomIndex);
        moveToNextObject();
        return toReturn;
    }

    /**
     * Moves the iterator to next Inhabitant
     */
    private void moveToNextObject() {
        if (objectsInRoom != null && devicesInRoomIndex < objectsInRoom.size() - 1){
            devicesInRoomIndex++;
            return;
        }

        ArrayList<Floor> floors = house.getFloors();


        for (int fi = floorIndex; fi < floors.size(); fi++){
            ArrayList<Room> roomsOnFloor = floors.get(fi).getRooms();
            for (int ri = roomIndex; ri < roomsOnFloor.size(); ri++){
                ArrayList<T> objectsInRoom = getTObjectsInRoom(roomsOnFloor.get(ri));
                if (objectsInRoom.size() != 0){
                    saveIteratorState(objectsInRoom, ri, fi);
                    return;
                }
            }
        }

        this.moreObjectsLeft = false;
    }

    private void saveIteratorState(ArrayList<T> devices, Integer roomIndex, Integer floorIndex){
        this.objectsInRoom = devices;
        this.devicesInRoomIndex = 0;
        roomIndex++;
        this.roomIndex = roomIndex;
        this.floorIndex = floorIndex;
    }

    private ArrayList<T> getTObjectsInRoom(Room room){
        ArrayList<Object> allObjects = room.getAllObjectsInRoom();
        ArrayList<T> result = new ArrayList<>();
        for (Object obj : allObjects){
            if (iteratorObjectClass.isInstance(obj)){
                result.add((T) obj);
            }
        }
        return result;
    }
}