package cz.cvut.fel.omo.smarthome.models.house.devices;

import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsTooBright;
import cz.cvut.fel.omo.smarthome.events.deviceevents.importantevents.IsTooDark;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.house.Window;
import cz.cvut.fel.omo.smarthome.models.house.devices.consumption.DeviceConsumptionRate;
import cz.cvut.fel.omo.smarthome.models.inhabitants.Person;
import cz.cvut.fel.omo.smarthome.reports.visitors.ConfigurationVisitor;

public class WindowBlind extends Device {
    private final Window window;
    private boolean isOpen = false;

    public WindowBlind(Window window) {
        this.window = window;
        window.setBlind(this);
        this.idleConsumptionRate = DeviceConsumptionRate.of(0,0, 0);
        this.activeConsumptionRate = DeviceConsumptionRate.of(0, 0, 4);
    }

    @Override
    public void subscribeToEvents() {
        House.getInstance().attach(this, new IsTooBright());
        House.getInstance().attach(this, new IsTooDark());
    }

    /**
     * WindowBLind is handled through Window entity.
     * Therefore the method is empty, so that it does not appear twice in the report.
     * @param configurationVisitor
     */
    @Override
    public void accept(ConfigurationVisitor configurationVisitor) {
    }

    public void accept(Person person) {
        person.use(this);
    }

    public void open() {
        if (!isOpen) {
            super.activate();
            consumptionTracker.incrementPerTick();
            super.deactivate();
        }
    }

    public void close() {
        if (isOpen) {
            super.activate();
            consumptionTracker.incrementPerTick();
            super.deactivate();
        }
    }

    @Override
    public void notify(IsTooBright event) {
        close();
    }

    @Override
    public void notify(IsTooDark event) {
        open();
    }
}
