package cz.cvut.fel.omo.smarthome.events.deviceevents.alerts;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Alert;
import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;

public class IsMakingWeirdSounds extends Alert {
    private final String description = "A device is making weird sounds.";

    private final boolean forAllObservers = false;

    public IsMakingWeirdSounds() {
    }

    public IsMakingWeirdSounds(Event event) {
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
        return new IsMakingWeirdSounds(this);
    }
}
