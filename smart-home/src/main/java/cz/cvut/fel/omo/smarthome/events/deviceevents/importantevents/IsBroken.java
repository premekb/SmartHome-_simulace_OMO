package cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.events.abstractevents.ImportantEvent;
import cz.cvut.fel.omo.smarthome.interfaces.events.Observer;

public class IsBroken extends ImportantEvent {
    private final String description = "A device is broken.";

    private final boolean forAllObservers = false;

    public IsBroken() {
    }

    public IsBroken(Event event) {
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
        return new IsBroken(this);
    }

    @Override
    public void accept(Observer observer) {
        observer.notify(this);
    }
}
