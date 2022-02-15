package cz.cvut.fel.omo.smarthome.models.house;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.interfaces.events.EventConsumer;
import cz.cvut.fel.omo.smarthome.interfaces.events.EventPublisher;
import cz.cvut.fel.omo.smarthome.interfaces.events.Observable;
import cz.cvut.fel.omo.smarthome.interfaces.events.Observer;
import cz.cvut.fel.omo.smarthome.interfaces.reports.HasReport;
import cz.cvut.fel.omo.smarthome.iterators.RoomIterator;
import cz.cvut.fel.omo.smarthome.iterators.SmartHomeIterator;
import cz.cvut.fel.omo.smarthome.models.house.devices.Device;
import cz.cvut.fel.omo.smarthome.models.house.furniture.SportsEquipmentRack;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Inhabitant;
import cz.cvut.fel.omo.smarthome.models.vehicles.Vehicle;
import cz.cvut.fel.omo.smarthome.reports.ActivityAndUsageReport;
import cz.cvut.fel.omo.smarthome.reports.ConsumptionReport;
import cz.cvut.fel.omo.smarthome.reports.EventReport;
import cz.cvut.fel.omo.smarthome.reports.HouseConfigurationReport;
import cz.cvut.fel.omo.smarthome.reports.visitors.ActivityAndUsageVisitor;
import cz.cvut.fel.omo.smarthome.reports.visitors.ConfigurationVisitor;
import cz.cvut.fel.omo.smarthome.reports.visitors.ConsumptionVisitor;
import cz.cvut.fel.omo.smarthome.reports.visitors.EventVisitor;

import java.util.*;

public class House implements EventConsumer, Observable, HasReport {
    private ArrayList<Floor> floors = new ArrayList<>();

    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    private HashMap<Class<? extends Event>, ArrayList<Observer>> observers = new HashMap<>();

    private Queue<Event> unhandledEvents = new LinkedList<>();

    private Queue<Event> handledEvents = new LinkedList<>();

    private final List<Inhabitant> inhabitants = new ArrayList<>();

    private static House instance;

    private House() {
    }

    public static House getInstance() {
        if (instance == null){
            instance = new House();
        }

        return instance;
    }

    @Override
    public void attach(Observer observer, Event event) {
        ArrayList<Observer> listOfObservers = observers.get(event.getClass());
        if (listOfObservers == null){
            listOfObservers = new ArrayList<>();
            listOfObservers.add(observer);
            observers.put(event.getClass(), listOfObservers);
        }

        else{
            listOfObservers.add(observer);
        }
    }

    @Override
    public void consumeEvent(EventPublisher source, Event event) {
        event.setSource(source);
        unhandledEvents.add(event);
    }

    /**
     * Event handling happens in a following way:
     * 1. Dequeue one by one all unhandled events from the unhandledEvents queue.
     * 2. If there are some observers registered to listen to that event, then choose one of them randomly or add all of them
     * depending on, whether all observers should be informed about the event
     * and let him handle the event.
     * 2.1 If the observer is a device, then ensure that the device is in the same room as the source of the event.
     * 3. Mark the event as handled
     * 4. Enqueue the events again which could not be handled by any observer.
     */
    public void  handleEvents(){
        ArrayList<Event> noObserverFound = new ArrayList<>();
        Event event;
        while (!unhandledEvents.isEmpty()){
            event = unhandledEvents.remove();
            List<Observer> listOfObservers = observers.get(event.getClass());
            boolean handled = handleEvent(event, listOfObservers);
            if (!handled) noObserverFound.add(event);
        }
        unhandledEvents.addAll(noObserverFound);
    }

    /**
     * Processes a single event
     *
     * @return true if event was handled
     */
    private boolean handleEvent(Event event, List<Observer> observers){
        if (observers == null || observers.size() == 0) return false;

        Random rand = new Random();
        ArrayList<Observer> handlingObservers = new ArrayList<>();
        Collections.shuffle(observers); // To randomize the handling observer if only one needs to be selected

        for (Observer observer : observers){
            if (observer.canMove() || observer.isInRoomWithSource(event)){
                event.accept(observer);
                handlingObservers.add(observer);
                if (!event.shouldInformAllObservers()) break;
            }
        }

        markEventHandled(event, handlingObservers);
        return true;
    }

    private void markEventHandled(Event event, List<Observer> handlers){
        event.setHandledBy(handlers);
        handledEvents.add(event);
    }

    public void addFloor(Floor floor){
        floors.add(floor);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    @Override
    public void detach(Observer observer, Event event) {
        observers.get(event.getClass()).remove(observer);
    }

    /**
     * Detaches observer from all events it is subscribed to.
     * @param observerToRemove
     */
    @Override
    public void detach(Observer observerToRemove) {
        for (ArrayList<Observer> registeredObservers : observers.values()){
            if (registeredObservers.contains(observerToRemove)) registeredObservers.remove(observerToRemove);
        }
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public SmartHomeIterator<Inhabitant> getInhabitantIterator() { return new SmartHomeIterator<Inhabitant>(this, Inhabitant.class);}

    public SmartHomeIterator<Device> getDeviceIterator() { return new SmartHomeIterator<Device>(this, Device.class);}

    public SmartHomeIterator<SportsEquipmentRack> getSportsEquipmentRackIterator() {
        return new SmartHomeIterator<SportsEquipmentRack>(this, SportsEquipmentRack.class);
    }

    public RoomIterator getRoomIterator() {
        return new RoomIterator(this);
    }

    public Queue<Event> getUnhandledEvents() {
        return unhandledEvents;
    }

    public Queue<Event> getHandledEvents() {
        return handledEvents;
    }

    @Override
    public HouseConfigurationReport getHouseConfigurationReport() {
        ConfigurationVisitor configurationVisitor = new ConfigurationVisitor();
        this.accept(configurationVisitor);
        return configurationVisitor.getReport();
    }

    @Override
    public ActivityAndUsageReport getActivityAndUsageReport() {
        ActivityAndUsageVisitor activityAndUsageVisitor = new ActivityAndUsageVisitor();
        for (Inhabitant inhabitant: inhabitants) {
            inhabitant.accept(activityAndUsageVisitor);
        }
        return activityAndUsageVisitor.getReport();
    }

    @Override
    public ConsumptionReport getConsumptionReport() {
        ConsumptionVisitor consumptionVisitor = new ConsumptionVisitor();
        this.accept(consumptionVisitor);
        return consumptionVisitor.getReport();
    }

    @Override
    public EventReport getEventReport() {
        EventVisitor eventVisitor = new EventVisitor();
        this.accept(eventVisitor);
        return eventVisitor.getReport();
    }

    public void accept(ConfigurationVisitor configurationVisitor){
        configurationVisitor.visitHouse(this);
    }

    public void accept(ConsumptionVisitor consumptionVisitor){
        consumptionVisitor.visitHouse(this);
    }

    public void accept(EventVisitor eventVisitor){
        eventVisitor.visitHouse(this);
    }

    public void addInhabitant(Inhabitant inhabitant) {
        inhabitants.add(inhabitant);
    }

    public List<Inhabitant> getInhabitants() {
        return Collections.unmodifiableList(inhabitants);
    }
}
