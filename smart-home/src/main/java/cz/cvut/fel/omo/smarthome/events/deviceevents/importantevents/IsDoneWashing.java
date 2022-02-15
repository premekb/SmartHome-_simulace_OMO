package cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.events.abstractevents.ImportantEvent;
import cz.cvut.fel.omo.smarthome.interfaces.events.Observer;

public class IsDoneWashing extends ImportantEvent {
    private final String description = "The dishwasher is done washing.";

    private final boolean forAllObservers = false;

    public IsDoneWashing() {
    }

    public IsDoneWashing(Event event) {
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
        return new IsDoneWashing(this);
    }

    @Override
    public void accept(Observer observer) {
        observer.notify(this);
    }
}
