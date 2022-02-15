package cz.cvut.fel.omo.smarthome.simulation;

import cz.cvut.fel.omo.smarthome.configuration.Configuration;
import cz.cvut.fel.omo.smarthome.configuration.HouseType;
import cz.cvut.fel.omo.smarthome.factories.LuxuriouHouseFactory;
import cz.cvut.fel.omo.smarthome.factories.OrdinaryHouseFactory;
import cz.cvut.fel.omo.smarthome.iterators.SmartHomeIterator;
import cz.cvut.fel.omo.smarthome.models.OutsideWorld;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.house.Room;
import cz.cvut.fel.omo.smarthome.models.house.devices.Device;
import cz.cvut.fel.omo.smarthome.models.house.devices.Microwave;
import cz.cvut.fel.omo.smarthome.models.house.devices.Oven;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Adult;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Dog;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Inhabitant;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Kid;
import cz.cvut.fel.omo.smarthome.reports.Report;

import java.util.ArrayList;
import java.util.HashMap;

public class Simulation {
    private final Configuration configuration;

    private House house;

    private OutsideWorld outsideWorld = OutsideWorld.getInstance();

    private Integer currentSimulationTick = 1;

    private HashMap<Integer, ArrayList<Report>> reports = new HashMap<>();

    public Simulation(Configuration configuration) {
        this.configuration = configuration;
        initHouse();
        addInhabitants();
    }

    private void addInhabitants(){
        Room startingRoom = house.getFloors().get(0).getRooms().get(0);
        for (int i = 0; i < configuration.getAdults(); i++){
            Adult adult = new Adult();
            adult.subscribeToEvents();
            house.addInhabitant(adult);
            startingRoom.addInhabitant(adult);
        }

        for (int i = 0; i < configuration.getDogs(); i++){
            Dog dog = new Dog();
            startingRoom.addInhabitant(dog);
            house.addInhabitant(dog);
        }

        for (int i = 0; i < configuration.getKids(); i++){
            Kid kid = new Kid();
            startingRoom.addInhabitant(kid);
            house.addInhabitant(kid);
        }
    }

    private void initHouse(){
        if (configuration.getHouseType() == HouseType.ORDINARY){
            house = new OrdinaryHouseFactory().buildHouse();
        }

        else if (configuration.getHouseType() == HouseType.LUXURIOUS){
            house = new LuxuriouHouseFactory().buildHouse();
        }
    }

    /**
     * Starts the simulation
     */
    public void execute(){
        while (currentSimulationTick != configuration.getSimulationLength() + 1){
            outsideWorld.removeAllPeople();
            simulateInhabitantActivity();
            simulateDeviceActivity();
            house.handleEvents();
            currentSimulationTick++;

            if (currentSimulationTick % configuration.getReportRate() == 0) saveReports();
        }
    }

    private void simulateDeviceActivity(){
        SmartHomeIterator<Device> deviceIterator = house.getDeviceIterator();
        while (deviceIterator.hasNext()){
            Device device = deviceIterator.next();
            device.simulateOneTick();
        }
    }

    private void simulateInhabitantActivity(){
        SmartHomeIterator<Inhabitant> inhabitantIterator = house.getInhabitantIterator();
        while (inhabitantIterator.hasNext()){
            Inhabitant inhabitant = inhabitantIterator.next();
            inhabitant.simulateOneTick();
        }
    }

    public HashMap<Integer, ArrayList<Report>> getReports() {
        return reports;
    }

    private void saveReports(){
        ArrayList<Report> currentReports = new ArrayList<>();
        currentReports.add(house.getHouseConfigurationReport());
        currentReports.add(house.getConsumptionReport());
        currentReports.add(house.getEventReport());
        currentReports.add(house.getActivityAndUsageReport());
        reports.put(currentSimulationTick, currentReports);
    }

    public House getHouse() {
        return house;
    }
}
