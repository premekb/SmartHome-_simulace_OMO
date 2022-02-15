package cz.cvut.fel.omo.smarthome.factories;

import cz.cvut.fel.omo.smarthome.builders.FloorBuilder;
import cz.cvut.fel.omo.smarthome.interfaces.factories.AbstractHouseFactory;
import cz.cvut.fel.omo.smarthome.models.house.Floor;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.vehicles.Car;

public class LuxuriouHouseFactory implements AbstractHouseFactory {
    @Override
    public House buildHouse() {
        House house = House.getInstance();
        FloorBuilder floorBuilder = new FloorBuilder();

        Floor underground = floorBuilder.
                addCellar().
                addCellar().
                getResult();
        house.addFloor(underground);

        Floor eatingFloor = floorBuilder.
                addKitchen().
                addKitchen().
                getResult();
        house.addFloor(eatingFloor);

        Floor livingFloor = floorBuilder.
                addLivingRoom().
                addTVRoom().
                addBedroom().
                addBedroom().
                addBedroom().
                addBedroom().
                getResult();
        house.addFloor(livingFloor);

        house.addVehicle(new Car());
        house.addVehicle(new Car());

        return house;
    }
}
