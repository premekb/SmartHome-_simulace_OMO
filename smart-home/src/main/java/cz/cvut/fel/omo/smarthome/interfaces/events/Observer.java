package cz.cvut.fel.omo.smarthome.interfaces.events;

import cz.cvut.fel.omo.smarthome.events.abstractevents.Event;
import cz.cvut.fel.omo.smarthome.events.deviceevents.alerts.IsMakingWeirdSounds;
import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.*;
import cz.cvut.fel.omo.smarthome.events.inhabitantevents.importantevents.IsCrying;
import cz.cvut.fel.omo.smarthome.events.inhabitantevents.importantevents.IsHungry;
import cz.cvut.fel.omo.smarthome.events.inhabitantevents.importantevents.IsSad;
import cz.cvut.fel.omo.smarthome.models.house.Room;

/**
 * Interface for notifying observers about an event, they listen to.
 * If the observer wants to implement a reaction to a given event, then it needs to override the default empty method.
 *
 * To start listening to events, the subscribeEvents method needs to be overriden.
 *
 * The methods are empty by default, so that the notify method can be called on every observer without the need
 * to implement empty methods on every observer for every event, even if the observer does not listen to the event.
 *
 * Template:
 * default void notify(EventType event);
 */
public interface Observer {
    default void subscribeToEvents(){}; // Defines all events, the observer wants to listen to

    /**
     * Devices and inhabitants are both observers.
     * However if the event source and the observer is not the same room, then it would not make sense
     * if the observer would handle the event from a different room. E. g. room is too bright, a light would turn on
     * in a diffrent room. Therefore it needs to be first determined, whether an observer and the event source are in the same room,
     * and if they are not, then we need to determine if the observer can move to the room.
     * If he cannot, then a different observer has to be chosen to handle the event.
     */
    boolean canMove();

    /**
     * Checks whether the {@link Observer} instance is in the same room as the {@link Event} source
     * @param event {@link Event} to check
     * @return Whether the {@link Observer} instance is in the same room as the {@link Event} source
     */
    default boolean isInRoomWithSource(Event event) {
        return getCurrentRoom().getInhabitants().contains(event.getSource())
            || getCurrentRoom().getDevices().contains(event.getSource());
    }

    Room getCurrentRoom();

    default void notify(Event event){};

    default void notify(IsMakingWeirdSounds event){};

    default void notify(IsTooBright event){};

    default void notify(IsTooDark event){};

    default void notify(IsTooHot event){};

    default void notify(IsTooHumid event){};

    default void notify(IsCrying event){};

    default void notify(IsSad event){};

    default void notify(IsHungry event){};

    default void notify(IsBroken event){};

    default void notify(IsDoneWashing event){};

    default void notify(IsDoneCooking event){};
}
