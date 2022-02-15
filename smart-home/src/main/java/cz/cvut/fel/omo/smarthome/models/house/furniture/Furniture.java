package cz.cvut.fel.omo.smarthome.models.house.furniture;

import cz.cvut.fel.omo.smarthome.reports.visitors.ConfigurationVisitor;

abstract public class Furniture {
    public void accept(ConfigurationVisitor configurationVisitor){
        configurationVisitor.visitFurniture(this);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
