package cz.cvut.fel.omo.smarthome.events.inhabitantevents.alerts;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Alert;
import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;

public class IsSporting extends Alert {
    private final String description = "Inhabitant is sporting.";

    private final boolean forAllObservers = false;

    public IsSporting() {
    }

    public IsSporting(Event event) {
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
        return new IsSporting(this);
    }
}
