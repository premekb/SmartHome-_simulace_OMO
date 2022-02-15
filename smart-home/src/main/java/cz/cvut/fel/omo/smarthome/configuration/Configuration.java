package cz.cvut.fel.omo.smarthome.configuration;

import cz.cvut.fel.omo.smarthome.exceptions.ParsingException;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Configuration {
    private static Reader file = null;

    private static Configuration instance;

    private Integer simulationLength = 100;

    private Integer reportRate = 50; // After how many ticks the reports should be generated

    private Integer adults = 4;

    private Integer dogs = 3;

    private Integer kids = 2;

    private Integer deviceWearRate = 1; // How much durability device loses after a tick

    private HouseType houseType = HouseType.ORDINARY;

    private Integer electricityUnitCost = 2;

    private Integer waterUnitCost = 1;

    private Integer gasUnitCost = 5;

    private Configuration() {
    }

    /**
     * Tries to parse configuration from a .json file. If a value is missing, then a default value is used.
     * @param file
     */
    private Configuration(Reader file){
        try {
            JSONObject jsonObj = (JSONObject) new JSONParser().parse(file);
            this.simulationLength = getIntFromJson(jsonObj, "simulationLength") == null ? this.simulationLength : getIntFromJson(jsonObj, "simulationLength");
            this.reportRate = getIntFromJson(jsonObj, "reportRate") == null ? this.reportRate : getIntFromJson(jsonObj, "reportRate");
            this.adults = getIntFromJson(jsonObj, "adults") == null ? this.adults : getIntFromJson(jsonObj, "adults");
            this.dogs = getIntFromJson(jsonObj, "dogs") == null ? this.dogs : getIntFromJson(jsonObj, "dogs");
            this.kids = getIntFromJson(jsonObj, "kids") == null ? this.kids : getIntFromJson(jsonObj, "kids");
            this.deviceWearRate = getIntFromJson(jsonObj, "deviceWearRate") == null ? this.deviceWearRate : getIntFromJson(jsonObj, "deviceWearRate");
            this.houseType = getStringFromJson(jsonObj, "houseType") == null ? this.houseType : HouseType.valueOf(getStringFromJson(jsonObj, "houseType"));
            this.electricityUnitCost = getIntFromJson(jsonObj, "electricityUnitCost") == null ? this.electricityUnitCost : getIntFromJson(jsonObj, "electricityUnitCost");
            this.waterUnitCost = getIntFromJson(jsonObj, "waterUnitCost") == null ? this.waterUnitCost : getIntFromJson(jsonObj, "waterUnitCost");
            this.gasUnitCost = getIntFromJson(jsonObj, "gasUnitCost") == null ? this.gasUnitCost : getIntFromJson(jsonObj, "gasUnitCost");

        } catch (IOException | ParseException e) {
            throw new ParsingException("Failed to parse Configuration from file" + file.toString());
        }
    }

    private String getStringFromJson(JSONObject jsonObj, String key){
        String value = (String) jsonObj.get(key);
        if (value == null) return null;
        return value;
    }

    private Integer getIntFromJson(JSONObject jsonObj, String key){
        Long value = (Long) jsonObj.get(key);
        if (value == null) return null;
        return value.intValue();
    }

    /**
     * @return {@link Configuration} Singleton
     */
    public static Configuration getInstance(){
        if (instance == null){
            instance = file == null ? new Configuration() : new Configuration(file);
        }

        return instance;
    }

    /**
     * Loads configuration from file
     * @param file {@link Reader}
     */
    public static void loadFromFile(Reader file){
        Configuration.file = file;
    }

    public Integer getSimulationLength() {
        return simulationLength;
    }

    public Integer getReportRate() {
        return reportRate;
    }

    public Integer getAdults() {
        return adults;
    }

    public Integer getDogs() {
        return dogs;
    }

    public Integer getKids() {
        return kids;
    }

    public Integer getDeviceWearRate() {
        return deviceWearRate;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public Integer getElectricityUnitCost() {
        return electricityUnitCost;
    }

    public Integer getWaterUnitCost() {
        return waterUnitCost;
    }

    public Integer getGasUnitCost() {
        return gasUnitCost;
    }
}
