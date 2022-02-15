package cz.cvut.fel.omo.smarthome.interfaces.events;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;

public interface EventConsumer {
    /**
     * Enqueues the {@link Event} to ba handled
     * @param source Source {@link EventPublisher} of the {@link Event}
     * @param event {@link Event} to be enqueued
     */
    void consumeEvent(EventPublisher source, Event event);
}
