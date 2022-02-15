package cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.events.abstractevents.ImportantEvent;
import cz.cvut.fel.omo.smarthome.interfaces.events.Observer;

public class IsDoneCooking extends ImportantEvent {
    private final String description = "The device is done cooking.";

    private final boolean forAllObservers = false;

    public IsDoneCooking() {
    }

    public IsDoneCooking(Event event) {
        super(event);
    }

    @Override
    public boolean shouldInformAllObservers() {
        return forAllObservers;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Event makeCopy() {
        return new IsDoneCooking(this);
    }

    @Override
    public void accept(Observer observer) {
        observer.notify(this);
    }
}
