package cz.cvut.fel.omo.smarthome.interfaces.events;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsBroken;
import cz.cvut.fel.omo.smarthome.factories.OrdinaryHouseFactory;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.house.Room;
import cz.cvut.fel.omo.smarthome.models.house.devices.Device;
import cz.cvut.fel.omo.smarthome.models.house.devices.TV;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Adult;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Inhabitant;
import cz.cvut.fel.omo.smarthome.util.TestUtils;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ObserverTest {

    @BeforeAll
    static void loadTestConfiguration() throws IOException, ParseException {
        TestUtils.loadTestConfiguration();
    }

    @AfterEach
    public void resetHouseSingleton() throws NoSuchFieldException, IllegalAccessException {
        TestUtils.resetSingletons();
    }

    @Test
    public void isInRoomWithSource_observerInRoomWithEventSource_true(){
        House house = new OrdinaryHouseFactory().buildHouse();
        Room room = house.getFloors().get(0).getRooms().get(0);
        Device device = new TV();
        room.addDevice(device);
        Inhabitant adult = new Adult();
        room.addInhabitant(adult);
        Event event = new IsBroken();
        device.publishEvent(event);

        boolean result = adult.isInRoomWithSource(event);

        Assertions.assertEquals(true, result);
    }

    @Test
    public void isInRoomWithSource_observerNotInRoomWithEventSource_false(){
        House house = new OrdinaryHouseFactory().buildHouse();
        Room room1 = house.getFloors().get(0).getRooms().get(0);
        Room room2 = house.getFloors().get(0).getRooms().get(1);
        Device device = new TV();
        room1.addDevice(device);
        Inhabitant adult = new Adult();
        room2.addInhabitant(adult);
        Event event = new IsBroken();
        device.publishEvent(event);

        boolean result = adult.isInRoomWithSource(event);

        Assertions.assertEquals(false, result);
    }
}
