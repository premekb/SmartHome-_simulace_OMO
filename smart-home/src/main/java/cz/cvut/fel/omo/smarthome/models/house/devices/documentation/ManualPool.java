package cz.cvut.fel.omo.smarthome.models.house.devices.documentation;

import cz.cvut.fel.omo.smarthome.models.house.devices.Device;

import java.util.HashMap;

public class ManualPool {
    private static HashMap<Class, Manual> manualHashMap = new HashMap<>();

    public static Manual getManual(Device device){
        if (manualHashMap.containsKey(device.getClass())){
            return manualHashMap.get(device.getClass());
        }

        else{
            Manual manual = createPooledManual(device);
            manualHashMap.put(device.getClass(), manual);
            return manual;
        }
    }


    private static Manual createPooledManual(Device device){
        return new Manual(device);
    }
}
