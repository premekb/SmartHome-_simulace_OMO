package cz.cvut.fel.omo.smarthome.events.abstractevents;

import cz.cvut.fel.omo.smarthome.interfaces.events.EventPublisher;
import cz.cvut.fel.omo.smarthome.interfaces.events.Observer;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Adult;
import cz.cvut.fel.omo.smarthome.reports.visitors.EventVisitor;

import java.util.List;

abstract public class Event{
    protected EventPublisher source;

    protected List<Observer> handledBy;

    public Event() {
    }

    public Event(Event event){
        this.source = event.source;
        this.handledBy = event.handledBy;
    }

    public abstract void accept(Observer observer);

    public abstract String getDescription();

    /**
     * Not all events need to be handled by all its observers.
     * E.g. Kid cries => only one adult needs to take care of it.
     *
     * However some need to be handled by all
     * E.g. Room is too bright => all window blinds should react.
     *
     * @return boolean if all observers should be informed
     */
    public abstract boolean shouldInformAllObservers();

    public abstract Event makeCopy();

    public abstract void accept(EventVisitor visitor);

    public EventPublisher getSource() {
        return source;
    }

    public List<Observer> getHandledBy() {
        return handledBy;
    }

    public boolean isHandled(){
        return handledBy != null;
    }

    public void setSource(EventPublisher source) {
        this.source = source;
    }

    public void setHandledBy(List<Observer> handledBy) {
        this.handledBy = handledBy;
    }
}
