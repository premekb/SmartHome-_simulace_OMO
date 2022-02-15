package cz.cvut.fel.omo.smarthome.models;

import cz.cvut.fel.omo.smarthome.models.inhabitants.Inhabitant;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Person;

import java.util.ArrayList;

public class OutsideWorld {
    private ArrayList<Person> people = new ArrayList<>();

    private static OutsideWorld instance;

    private OutsideWorld() {
    }

    public static OutsideWorld getInstance(){
        if (instance == null){
            instance = new OutsideWorld();
        }

        return instance;
    }

    public void addPerson(Person person){
        person.setOutside();
        people.add(person);
    }

    public void removeAllPeople(){
        while (people.size() != 0){
            Person person = people.get(0);
            people.remove(person);
            person.returnHome();
        }
    }

    public ArrayList<Person> getPeople() {
        return people;
    }
}
