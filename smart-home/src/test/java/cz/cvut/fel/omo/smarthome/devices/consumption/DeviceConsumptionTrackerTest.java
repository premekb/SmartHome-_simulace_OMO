package cz.cvut.fel.omo.smarthome.devices.consumption;

import cz.cvut.fel.omo.smarthome.models.house.devices.Device;
import cz.cvut.fel.omo.smarthome.models.house.devices.TV;
import cz.cvut.fel.omo.smarthome.models.house.devices.consumption.DeviceConsumptionTracker;
import cz.cvut.fel.omo.smarthome.models.house.devices.state.ActiveState;
import cz.cvut.fel.omo.smarthome.models.house.devices.state.IdleState;
import cz.cvut.fel.omo.smarthome.models.house.devices.state.OffState;
import cz.cvut.fel.omo.smarthome.util.TestUtils;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DeviceConsumptionTrackerTest {
    @BeforeAll
    static void loadTestConfiguration() throws IOException, ParseException {
        TestUtils.loadTestConfiguration();
    }

    @Test
    public void getTotalConsumption_trackerOnTv_1ElectricityTotalMeasured(){
        Device device = new TV();
        DeviceConsumptionTracker tracker = device.getConsumptionTracker();

        device.simulateOneTick();

        Integer result = tracker.getTotalConsumption().getElectricity();
        Assertions.assertEquals(1, result);
    }

    @Test
    public void reset_trackerOnTvSimulateSeveralTicksThenResetSimulateMore_2ElectricitySinceReset4ElectricityTotalMeasured(){
        Device device = new TV();
        DeviceConsumptionTracker tracker = device.getConsumptionTracker();

        device.simulateOneTick();
        device.simulateOneTick();
        device.getConsumptionTracker().reset();
        device.simulateOneTick();
        device.simulateOneTick();

        Integer totalMeasuredResult = tracker.getTotalConsumption().getElectricity();
        Integer sinceResetMeasuredResult = tracker.getConsumptionSinceReset().getElectricity();
        Assertions.assertEquals(2, sinceResetMeasuredResult);
        Assertions.assertEquals(4, totalMeasuredResult);
    }

    @Test
    public void getTotalConsumption_trackerOnTvSimulateSeveralTicksWithTvInDifferentStates_102ElectricityMeasured(){
        Device device = new TV();
        DeviceConsumptionTracker tracker = device.getConsumptionTracker();
        device.setState(new ActiveState());

        device.simulateOneTick();
        device.simulateOneTick();
        device.setState(new IdleState());
        device.simulateOneTick();
        device.simulateOneTick();
        device.setState(new OffState());
        device.simulateOneTick();

        Integer result = tracker.getTotalConsumption().getElectricity();

        Assertions.assertEquals(102, result);
    }

}
