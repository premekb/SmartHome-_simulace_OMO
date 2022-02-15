package cz.cvut.fel.omo.smarthome.events.inhabitantevents.importantevents;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.events.abstractevents.ImportantEvent;
import cz.cvut.fel.omo.smarthome.interfaces.events.Observer;

public class IsHungry extends ImportantEvent {
    private final String description = "Inhabitant is hungry.";

    private final boolean forAllObservers = false;

    public IsHungry() {
    }

    public IsHungry(Event event) {
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
        return new IsHungry(this);
    }

    @Override
    public void accept(Observer observer) {
        observer.notify(this);
    }
}
