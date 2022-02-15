package cz.cvut.fel.omo.smarthome.events.inhabitantevents.alerts;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Alert;
import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;

public class IsWaitingForSportsEquipment extends Alert {
    private final String description = "Inhabitant is waiting for sports equipment.";

    private final boolean forAllObservers = false;

    public IsWaitingForSportsEquipment() {
    }

    public IsWaitingForSportsEquipment(Event event) {
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
        return new IsWaitingForSportsEquipment(this);
    }
}
