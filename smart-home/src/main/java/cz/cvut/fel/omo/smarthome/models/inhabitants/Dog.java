package cz.cvut.fel.omo.smarthome.models.inhabitants;

import cz.cvut.fel.omo.smarthome.events.inhabitantevents.alerts.IsBarking;

public class Dog extends Animal {
    public Dog() {
        super();
        addRandomlyPublishedEvent(new IsBarking());
    }
}
