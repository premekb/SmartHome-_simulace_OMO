package cz.cvut.fel.omo.smarthome.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NameGenerator {
    private final static Random rand = new Random();

    private final static List<String> firstNames = List.of(
            "Konrad", "Greta", "Herbert", "Helmut", "Uwe", "Friedrich", "Wilhem",
            "Hans", "Gunther", "Reiner", "Gerhard", "Reinhard", "Norbert", "Wilfried",
            "Sigfried", "Heinrich", "Ingo", "Bernhard", "Udo", "Franz", "Fritz"
    );

    private final static List<String> surnames = List.of(
            "Schmidt", "Schneider", "Fischer", "Neumann", "Wolf", "Klein",
            "Hofmann", "Hartmann", "Schmitz", "Meier", "Herrmann", "Kaiser",
            "Fuchs", "Vogel", "Berger", "Beck", "Roth", "Winter", "Ludwig"
    );

    public static String getPersonName(){
        return firstNames.get(rand.nextInt(firstNames.size())) + " " + surnames.get(rand.nextInt(surnames.size()));
    }

    public static String getAnimalName(){
        return surnames.get(rand.nextInt(surnames.size()));
    }
}
