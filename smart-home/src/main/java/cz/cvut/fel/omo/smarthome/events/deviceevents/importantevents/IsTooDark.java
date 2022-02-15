package cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.events.abstractevents.ImportantEvent;
import cz.cvut.fel.omo.smarthome.interfaces.events.Observer;

public class IsTooDark extends ImportantEvent {
    private final String description = "A room is too dark.";

    private final boolean forAllObservers = true;

    public IsTooDark() {
    }

    public IsTooDark(Event event) {
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
        return new IsTooDark(this);
    }

    @Override
    public void accept(Observer observer) {
        observer.notify(this);
    }
}
