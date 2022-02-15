package cz.cvut.fel.omo.smarthome.events.inhabitantevents.importantevents;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.events.abstractevents.ImportantEvent;
import cz.cvut.fel.omo.smarthome.interfaces.events.Observer;

public class IsSad extends ImportantEvent {
    private final String description = "Inhabitant is sad.";

    private final boolean forAllObservers = false;

    public IsSad() {
    }

    public IsSad(Event event) {
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
        return new IsSad(this);
    }

    @Override
    public void accept(Observer observer) {
        observer.notify(this);
    }
}
