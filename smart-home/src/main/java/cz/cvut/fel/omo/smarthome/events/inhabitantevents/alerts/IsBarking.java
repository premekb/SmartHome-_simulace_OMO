package cz.cvut.fel.omo.smarthome.events.inhabitantevents.alerts;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Alert;
import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;

public class IsBarking extends Alert {
    private final String description = "Dog is barking.";

    private final boolean forAllObservers = false;

    public IsBarking() {
    }

    public IsBarking(Event event) {
        super(event);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean shouldInformAllObservers() {
        return forAllObservers;
    }

    @Override
    public Event makeCopy() {
        return new IsBarking(this);
    }
}
