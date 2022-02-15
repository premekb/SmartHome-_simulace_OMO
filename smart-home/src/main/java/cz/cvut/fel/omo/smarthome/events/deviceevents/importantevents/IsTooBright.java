package cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.events.abstractevents.ImportantEvent;
import cz.cvut.fel.omo.smarthome.interfaces.events.Observer;

public class IsTooBright extends ImportantEvent {
    private final String description = "A room is too bright.";

    private final boolean forAllObservers = true;

    public IsTooBright() {
    }

    public IsTooBright(Event event) {
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
        return new IsTooBright(this);
    }

    @Override
    public void accept(Observer observer) {
        observer.notify(this);
    }
}
