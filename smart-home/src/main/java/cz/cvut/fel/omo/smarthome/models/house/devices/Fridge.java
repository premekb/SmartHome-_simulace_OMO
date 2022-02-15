package cz.cvut.fel.omo.smarthome.models.house.devices;

import cz.cvut.fel.omo.smarthome.events.deviceevents.alerts.IsMakingWeirdSounds;
import cz.cvut.fel.omo.smarthome.models.house.devices.consumption.DeviceConsumptionRate;
import cz.cvut.fel.omo.smarthome.models.house.devices.items.Food;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Person;
import java.util.Random;
import java.util.Stack;

public class Fridge extends Device {
    Integer capacity = 50;
    Stack<Food> contents = new Stack<>();

    public Fridge() {
        this.idleConsumptionRate = DeviceConsumptionRate.of(0,0, 5);
        this.activeConsumptionRate = DeviceConsumptionRate.of(0, 0, 40);
        addRandomlyPublishedEvent(new IsMakingWeirdSounds());

        Random rand = new Random();
        for (int i = 0; i < rand.nextInt(25); i++) {
            storeFood(new Food());
        }
    }

    public void accept(Person person) {
        person.use(this);
    }

    public void storeFood(Food food) {
        if (contents.size() < capacity) {
            contents.push(food);
        }
    }

    public Food takeFood() {
        return contents.isEmpty() ? null : contents.pop();
    }

    public boolean isEmpty() {
        return contents.isEmpty();
    }
}
