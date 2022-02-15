package cz.cvut.fel.omo.smarthome.models.house;

import cz.cvut.fel.omo.smarthome.models.house.devices.WindowBlind;
import cz.cvut.fel.omo.smarthome.reports.visitors.ConfigurationVisitor;

public class Window {

    private WindowBlind blind;

    public WindowBlind getBlind() {
        return blind;
    }

    public void setBlind(WindowBlind blind) {
        this.blind = blind;
    }

    public void accept(ConfigurationVisitor configurationVisitor){
        configurationVisitor.visitWindow(this);
    }
}
