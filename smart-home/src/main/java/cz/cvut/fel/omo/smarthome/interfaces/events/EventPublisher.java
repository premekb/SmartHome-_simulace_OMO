package cz.cvut.fel.omo.smarthome.interfaces.events;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.models.house.House;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public interface EventPublisher {
    HashMap<Class<? extends EventPublisher>, ArrayList<Event>> canPublishRandomly = new HashMap<>();


    /**
     * Publishes (fires) an {@link Event}
     * @param event {@link Event} to be published
     */
    default void publishEvent(Event event){
        House.getInstance().consumeEvent(this, event);
    }

    /**
     * Publishes a random {@link Event}
     */
    default void publishRandomEvent() {
        if (canPublishRandomlyAtleastOneEvent()) publishEvent(getRandomEvent());
    }

    /**
     * Registers an {@link Event} to be published randomly with {@link #publishRandomEvent()}
     * @param event
     */
    default void addRandomlyPublishedEvent(Event event){
        ArrayList<Event> eventsThisCanPublish = canPublishRandomly.get(this.getClass());
        if (eventsThisCanPublish == null){
            eventsThisCanPublish = new ArrayList<>();
            canPublishRandomly.put(this.getClass(), eventsThisCanPublish);
        }

        for (Event e : eventsThisCanPublish) {
            if (e.getClass().equals(event.getClass())) return;
        }

        eventsThisCanPublish.add(event);
    }

    /**
     * @return Whether the {@link EventPublisher} instance has any {@link Event}s registered to be published randomly
     */
    default boolean canPublishRandomlyAtleastOneEvent(){
        ArrayList<Event> events = canPublishRandomly.get(this.getClass());
        return events != null && events.size() != 0;
    }

    private Event getRandomEvent(){
        Random rand = new Random();
        ArrayList<Event> events = canPublishRandomly.get(this.getClass());
        Integer randomEventIndex = rand.nextInt(events.size());
        Event randomEvent = events.get(randomEventIndex).makeCopy();
        return randomEvent;
    }
}
