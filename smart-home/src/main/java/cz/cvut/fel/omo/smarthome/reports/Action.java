package cz.cvut.fel.omo.smarthome.reports;

import cz.cvut.fel.omo.smarthome.models.house.devices.Device;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Inhabitant;

public class Action {
    private final Inhabitant who;
    private final Device what;

    public Action(Inhabitant who, Device what) {
        this.who = who;
        this.what = what;
    }

    public Inhabitant getWho() {
        return who;
    }

    public Device getWhat() {
        return what;
    }
}
