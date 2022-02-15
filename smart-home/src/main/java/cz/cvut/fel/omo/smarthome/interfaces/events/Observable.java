package cz.cvut.fel.omo.smarthome.interfaces.events;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;

public interface Observable {

    /**
     * Attaches (subscribes) the {@link Observer} to listen for the specified {@link Event} class
     * @param observer {@link Observer} to attach
     * @param event {@link Event} to attach the {@link Observer} to
     */
    public void attach(Observer observer, Event event);

    /**
     * Detaches {@link Observer} from a single {@link Event}
     * @param observer {@link Observer} to detach
     * @param event {@link Event} to detach the {@link Observer} from
     */
    public void detach(Observer observer, Event event);

    /**
     * Detaches {@link Observer} from all {@link Event}, that it subscribes to
     * @param observer {@link Observer} to detach
     */
    public void detach(Observer observer);
}
