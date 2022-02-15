package cz.cvut.fel.omo.smarthome.models.inhabitants;

import cz.cvut.fel.omo.smarthome.iterators.SmartHomeIterator;
import cz.cvut.fel.omo.smarthome.models.OutsideWorld;
import cz.cvut.fel.omo.smarthome.models.house.House;
import cz.cvut.fel.omo.smarthome.models.house.devices.AC;
import cz.cvut.fel.omo.smarthome.models.house.devices.AudioVideoReceiver;
import cz.cvut.fel.omo.smarthome.models.house.devices.Dehumidifier;
import cz.cvut.fel.omo.smarthome.models.house.devices.Device;
import cz.cvut.fel.omo.smarthome.models.house.devices.Dishwasher;
import cz.cvut.fel.omo.smarthome.models.house.devices.Fridge;
import cz.cvut.fel.omo.smarthome.models.house.devices.Light;
import cz.cvut.fel.omo.smarthome.models.house.devices.Microwave;
import cz.cvut.fel.omo.smarthome.models.house.devices.Oven;
import cz.cvut.fel.omo.smarthome.models.house.devices.Sensor;
import cz.cvut.fel.omo.smarthome.models.house.devices.TV;
import cz.cvut.fel.omo.smarthome.models.house.devices.WindowBlind;
import cz.cvut.fel.omo.smarthome.models.house.furniture.SportsEquipmentRack;
import cz.cvut.fel.omo.smarthome.models.house.sportsequipment.SportsEquipment;
import cz.cvut.fel.omo.smarthome.util.NameGenerator;

import java.sql.SQLOutput;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public abstract class Person extends Inhabitant {
    Map.Entry<SportsEquipment, SportsEquipmentRack> borrowedSportsEquipment;

    private boolean waitingForSport = false;
    protected boolean isBusy = false;

    public Person() {
        name = NameGenerator.getPersonName();
    }

    @Override
    public void simulateOneTick() {
        super.simulateOneTick();
        if (waitingForSport || wantsToSport()){
            waitingForSport = false;
            goSport();
        } else {
            if (this.currentRoom.getDevices().size() > 0) {
                ArrayList<Device> devices = this.currentRoom
                    .getDevices();
                Device randomDevice = devices.get(rand.nextInt(devices.size()));
                randomDevice.accept(this);
            }
        }
    }

    private void goSport(){
        if (borrowSportsEquipment()){
            currentRoom.removeInhabitant(this);
            OutsideWorld.getInstance().addPerson(this);
            House.getInstance().detach(this);
        }

        else{
            waitingForSport = true;
        }
    }

    /**
     *
     * @return false if no sports equipment is available
     */
    private boolean borrowSportsEquipment(){
        House house = House.getInstance();
        SmartHomeIterator<SportsEquipmentRack> sportsEquipmentRackIterator = house.getSportsEquipmentRackIterator();
        while (sportsEquipmentRackIterator.hasNext()){
            SportsEquipmentRack rack = sportsEquipmentRackIterator.next();
            if (!rack.isEmpty()){
                this.borrowedSportsEquipment = new AbstractMap.SimpleEntry<SportsEquipment, SportsEquipmentRack>(rack.takeSportsEquipment(), rack);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the borrowed {@link SportsEquipment}
     */
    public void returnSportsEquipment(){
        if (borrowedSportsEquipment != null){
            SportsEquipment equipment = borrowedSportsEquipment.getKey();
            SportsEquipmentRack rack = borrowedSportsEquipment.getValue();
            rack.addSportsEquipment(equipment);
            borrowedSportsEquipment = null;
        }
    }

    /**
     * Returns the {@link Inhabitant} home, returns the {@link SportsEquipment} and subscribes back to all the Events
     */
    public void returnHome(){
        waitingForSport = false;
        goToRoom(House.getInstance().getRoomIterator().get(0));
        returnSportsEquipment();
        subscribeToEvents();
    }

    private boolean wantsToSport() {
        return rand.nextBoolean() && !isBusy; // 50% chance if not busy
    }

    public abstract void use(AC ac);

    public abstract void use(AudioVideoReceiver audioVideoReceiver);

    public abstract void use(Dehumidifier dehumidifier);

    public abstract void use(Dishwasher dishwasher);

    public abstract void use(Fridge fridge);

    public abstract void use(Light light);

    public abstract void use(Microwave microwave);

    public abstract void use(Oven oven);

    public abstract void use(TV tv);

    public abstract void use(WindowBlind windowBlind);

    public abstract void use(Sensor sensor);
}
