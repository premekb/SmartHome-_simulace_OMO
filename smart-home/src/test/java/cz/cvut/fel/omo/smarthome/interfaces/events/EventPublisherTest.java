package cz.cvut.fel.omo.smarthome.interfaces.events;

import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Kid;
import cz.cvut.fel.omo.smarthome.util.TestUtils;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * This test is mostly copied from an example below on how to mock a singleton.
 * https://github.com/KyryloSemenko/staticFieldMock/blob/master/src/test/java/com/example/DriverSnapshotHandlerTest.java
 */
public class EventPublisherTest {

    private House house;

    @BeforeAll
    static void loadTestConfiguration() throws IOException, ParseException {
        TestUtils.loadTestConfiguration();
    }

    @SuppressWarnings("javadoc")
    @BeforeEach
    public void setUp() {
        house = Mockito.mock(House.class);
        setMock(house);
        //Mockito.when(formatter.formatTachoIcon()).thenReturn(MOCKED_URL);
    }


    @AfterEach
    public void resetSingletons() throws Exception {
        TestUtils.resetSingletons();
    }

    /**
     * Set a mock to the {@link House} instance
     * Throws {@link RuntimeException} in case if reflection failed, see a {@link Field#set(Object, Object)} method description.
     * @param mock the mock to be inserted to a class
     */
    private void setMock(House mock) {
        try {
            Field instance = House.class.getDeclaredField("instance");
            instance.setAccessible(true);
            instance.set(instance, mock);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void publishRandomEvent_mockedHouse_houseConsumeEventCalledWithCorrectEventPublisher(){
        EventPublisher kid = new Kid();
        kid.publishRandomEvent();

        Mockito.verify(house).consumeEvent(Mockito.any(Kid.class), Mockito.any());
    }
}
