package cz.cvut.fel.omo.smarthome.configuration;

public enum HouseType {
    LUXURIOUS("LUXURIOUS"),
    ORDINARY("ORDINARY");

    private final String type;

    HouseType(String type) {
        this.type = type;
    }
}
