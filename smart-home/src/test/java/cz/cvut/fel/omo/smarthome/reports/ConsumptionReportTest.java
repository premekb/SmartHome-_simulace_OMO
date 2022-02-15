package cz.cvut.fel.omo.smarthome.reports;

import cz.cvut.fel.omo.smarthome.configuration.Configuration;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.simulation.Simulation;
import cz.cvut.fel.omo.smarthome.util.TestUtils;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ConsumptionReportTest {
    @BeforeAll
    static void loadTestConfiguration() throws IOException, ParseException {
        TestUtils.loadTestConfiguration();
    }

    @Test
    public void houseGetConsumptionReport_defaultConfigurationNoConsumption_noExceptionThrown() {
        Configuration cfg = Configuration.getInstance();
        Simulation simulation = new Simulation(cfg);
        House house = simulation.getHouse();
        ConsumptionReport report = house.getConsumptionReport();
        System.out.println(report);
    }

    @Test
    public void houseGetConsumptionReport_defaultConfigurationExecuteSimulation_noExceptionThrown() {
        Configuration cfg = Configuration.getInstance();
        Simulation simulation = new Simulation(cfg);
        simulation.execute();
        House house = simulation.getHouse();
        ConsumptionReport report = house.getConsumptionReport();
        System.out.println(report);
    }
}
