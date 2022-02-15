package cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.events.abstractevents.ImportantEvent;
import cz.cvut.fel.omo.smarthome.interfaces.events.Observer;

public class IsTooHot extends ImportantEvent {
    private final String description = "A room is too hot.";

    private final boolean forAllObservers = true;

    public IsTooHot() {
    }

    public IsTooHot(Event event) {
        super(event);
    }

    @Override
    public boolean shouldInformAllObservers() {
        return false;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Event makeCopy() {
        return new IsTooHot(this);
    }

    @Override
    public void accept(Observer observer) {
        observer.notify(this);
    }
}
