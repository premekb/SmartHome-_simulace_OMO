package cz.cvut.fel.omo.smarthome.iterators;

import cz.cvut.fel.omo.smarthome.models.house.Floor;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.house.Room;
import cz.cvut.fel.omo.smarthome.models.house.devices.Device;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class RoomIterator {
    private House house;

    private Integer floorIndex = 0;

    private Integer roomIndex = 0;

    public RoomIterator(House house) {
        this.house = house;
    }

    public boolean hasNext() {
        ArrayList<Floor> floors = house.getFloors();
        ArrayList<Room> roomsOnFloor = floors.get(floorIndex).getRooms();

        if (roomIndex < roomsOnFloor.size()) {
            return true;
        } else {
            return floorIndex < floors.size();
        }
    }

    /**
     * Returns current room and moves the iterator to next room.
     *
     * @return Room
     */
    public Room next() {
        if (hasNext()) {
            Room next = house.getFloors().get(floorIndex).getRooms().get(roomIndex);
            moveToNextRoom();
            return next;
        } else {
            throw new NoSuchElementException();
        }
    }

    private void moveToNextRoom() {
        ArrayList<Floor> floors = house.getFloors();
        ArrayList<Room> roomsOnFloor = floors.get(floorIndex).getRooms();

        if (roomIndex + 1 < roomsOnFloor.size()) {
            ++roomIndex;
        } else {
            ++floorIndex;
        }
    }

    public Integer size() {
        int count = 0;
        for (Floor floor: house.getFloors()) {
            count += floor.getRooms().size();
        }

        return count;
    }

    public Room get(Integer index) {
        int currentIndex = 0;

        for (Floor floor: house.getFloors()) {
            for (Room room: floor.getRooms()) {
                if (currentIndex == index) {
                    return room;
                }
                ++currentIndex;
            }
        }

        return null;
    }
}