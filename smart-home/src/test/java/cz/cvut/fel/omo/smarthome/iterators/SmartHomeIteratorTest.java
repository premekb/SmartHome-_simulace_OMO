package cz.cvut.fel.omo.smarthome.iterators;

import cz.cvut.fel.omo.smarthome.configuration.Configuration;
import cz.cvut.fel.omo.smarthome.factories.OrdinaryHouseFactory;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.house.devices.Device;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Adult;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Dog;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Inhabitant;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Kid;
import cz.cvut.fel.omo.smarthome.simulation.Simulation;
import cz.cvut.fel.omo.smarthome.util.TestUtils;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;

public class SmartHomeIteratorTest {
    @BeforeAll
    static void loadTestConfiguration() throws IOException, ParseException {
        TestUtils.loadTestConfiguration();
    }

    @BeforeEach
    public void resetSingletons() throws Exception {
        TestUtils.resetSingletons();
    }

    @Test
    public void nextDevice_defaultHouseConfiguration_17devices(){
        Configuration cfg = Configuration.getInstance();
        Simulation simulation = new Simulation(cfg);
        SmartHomeIterator<Device> iterator = simulation.getHouse().getDeviceIterator();
        Integer deviceCount = 0;

        while (iterator.hasNext()){
            Device device = iterator.next();
            deviceCount++;
        }

        Assertions.assertEquals(32, deviceCount);
    }

    @Test
    public void nextInhabitant_defaultHouseConfiguration_4ADults2Kids3Dogs(){
        Configuration cfg = Configuration.getInstance();
        Simulation simulation = new Simulation(cfg);
        SmartHomeIterator<Inhabitant> iterator = simulation.getHouse().getInhabitantIterator();
        Integer adultCount = 0;
        Integer kidCount = 0;
        Integer dogCount = 0;

        while (iterator.hasNext()){
            Inhabitant inhabitant = iterator.next();
            if (inhabitant instanceof Adult) adultCount++;
            if (inhabitant instanceof Dog) dogCount++;
            if (inhabitant instanceof Kid) kidCount++;
        }

        Assertions.assertEquals(4, adultCount);
        Assertions.assertEquals(2, kidCount);
        Assertions.assertEquals(3, dogCount);
    }

    @Test
    public void hasNextInhabitant_emptyHouse_FalseImmediately(){
        House house = (new OrdinaryHouseFactory()).buildHouse();
        SmartHomeIterator<Inhabitant> iterator = house.getInhabitantIterator();

        boolean hasNext = iterator.hasNext();


        Assertions.assertEquals(false, hasNext);
    }

    @Test
    public void nextInhabitant_customlyPlacedInhabitans_2Inhabitants(){
        Integer inhabitantCount = 0;
        House house = (new OrdinaryHouseFactory()).buildHouse();
        house.getFloors().get(0).getRooms().get(0).addInhabitant(new Adult());
        house.getFloors().get(1).getRooms().get(1).addInhabitant(new Adult());

        SmartHomeIterator<Inhabitant> iterator = house.getInhabitantIterator();
        while (iterator.hasNext()){
            Inhabitant inhabitant = iterator.next();
            inhabitantCount++;
        }

        Assertions.assertEquals(2, inhabitantCount);
    }
}
