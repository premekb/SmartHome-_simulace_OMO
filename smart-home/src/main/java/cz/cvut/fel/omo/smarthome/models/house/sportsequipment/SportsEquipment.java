package cz.cvut.fel.omo.smarthome.models.house.sportsequipment;

import cz.cvut.fel.omo.smarthome.reports.visitors.ConfigurationVisitor;

abstract public class SportsEquipment {
    public void accept(ConfigurationVisitor configurationVisitor){
        configurationVisitor.visitSportsEquipment(this);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
