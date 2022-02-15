package cz.cvut.fel.omo.smarthome.interfaces.factories;

import cz.cvut.fel.omo.smarthome.models.house.House;

public interface AbstractHouseFactory {

    /**
     * Build the {@link House}
     * @return Built {@link House}
     */
    public House buildHouse();
}
