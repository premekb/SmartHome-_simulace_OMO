package cz.cvut.fel.omo.smarthome.models.inhabitants;

import cz.cvut.fel.omo.smarthome.util.NameGenerator;

public abstract class Animal extends Inhabitant{
    public Animal() {
        super();
        name = NameGenerator.getAnimalName();
    }
}
