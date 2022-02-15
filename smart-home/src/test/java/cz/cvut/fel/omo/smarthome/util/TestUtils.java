package cz.cvut.fel.omo.smarthome.util;

import cz.cvut.fel.omo.smarthome.configuration.Configuration;
import cz.cvut.fel.omo.smarthome.models.OutsideWorld;
import cz.cvut.fel.omo.smarthome.models.house.House;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Field;

public class TestUtils {
    public static void loadTestConfiguration(String fileName) throws IOException, ParseException {
        InputStream inputStream = TestUtils.class
                .getClassLoader().getResourceAsStream(fileName);
        Configuration.loadFromFile(new InputStreamReader(inputStream));
    }

    public static void loadTestConfiguration() throws IOException, ParseException {
        InputStream inputStream = TestUtils.class
                .getClassLoader().getResourceAsStream("DefaultTestCFG.json");
        Configuration.loadFromFile(new InputStreamReader(inputStream));
    }

    /**
     * Remove the mocked instance from the class. It is important to clean up the class, because other tests will be confused with the mocked instance.
     * @throws Exception if the instance could not be accessible
     *
     * Inspired by:
     * https://github.com/KyryloSemenko/staticFieldMock/blob/master/src/test/java/com/example/DriverSnapshotHandlerTest.java
     */
    public static void resetSingletons() throws NoSuchFieldException, IllegalAccessException {
        resetHouse();
        resetOutsideWorld();
    }

    private static void resetHouse() throws NoSuchFieldException, IllegalAccessException {
        Field instance = House.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    private static void resetOutsideWorld() throws NoSuchFieldException, IllegalAccessException {
        Field instance = OutsideWorld.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }
}
