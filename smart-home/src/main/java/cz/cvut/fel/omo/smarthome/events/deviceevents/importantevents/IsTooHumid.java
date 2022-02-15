package cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.events.abstractevents.ImportantEvent;
import cz.cvut.fel.omo.smarthome.interfaces.events.Observer;

public class IsTooHumid extends ImportantEvent {
    private final String description = "Humidity in a room is too high.";

    private final boolean forAllObservers = true;

    public IsTooHumid() {
    }

    public IsTooHumid(Event event) {
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
        return new IsTooHumid(this);
    }

    @Override
    public void accept(Observer observer) {
        observer.notify(this);
    }
}
