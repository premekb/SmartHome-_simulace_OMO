package cz.cvut.fel.omo.smarthome.models.inhabitants;

import cz.cvut.fel.omo.smarthome.configuration.Configuration;
import cz.cvut.fel.omo.smarthome.iterators.SmartHomeIterator;
import cz.cvut.fel.omo.smarthome.models.OutsideWorld;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Person;
import cz.cvut.fel.omo.smarthome.simulation.Simulation;
import cz.cvut.fel.omo.smarthome.util.TestUtils;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;

public class PersonTest {

    private Simulation sim;

    private House house;

    @BeforeAll
    static void loadTestConfiguration() throws IOException, ParseException {
        TestUtils.loadTestConfiguration("SportTestCFG.json");
    }

    @AfterEach
    public void resetSingletons() throws Exception {
        TestUtils.resetSingletons();
    }

    @BeforeEach
    public void setup(){
        sim = new Simulation(Configuration.getInstance());
        house = House.getInstance();
    }

    @Test
    public void goSport_100peopleConfiguration_sportingPeopleAreNotInHouse(){
        boolean personInHouseAndOutside = false;

        sim.execute();

        SmartHomeIterator<Person>personIterator = new SmartHomeIterator<>(house, Person.class);
        ArrayList<Person> peopleOutside = OutsideWorld.getInstance().getPeople();
        while(personIterator.hasNext()){
            if (peopleOutside.contains(personIterator.next())){
                personInHouseAndOutside = true;
                break;
            }
        }

        Assertions.assertEquals(false, personInHouseAndOutside);
    }

    @Test
    public void goSport_100PeopleConfiguration_notMoreSportingPeopleThanSportEquipment(){
        Integer SPORTS_EQUIPMENT_AMOUNT = 4;
        Configuration cfg = Configuration.getInstance();
        boolean morePeopleThanSportsEquipment;

        sim.execute();
        ArrayList<Person> people = OutsideWorld.getInstance().getPeople();
        morePeopleThanSportsEquipment = people.size() > SPORTS_EQUIPMENT_AMOUNT;

        Assertions.assertEquals(false, morePeopleThanSportsEquipment);
    }
}
