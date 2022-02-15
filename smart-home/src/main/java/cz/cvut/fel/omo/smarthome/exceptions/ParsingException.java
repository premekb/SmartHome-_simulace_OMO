package cz.cvut.fel.omo.smarthome.exceptions;

public class ParsingException extends RuntimeException {
    public ParsingException(String errorMessage) {
        super(errorMessage);
    }
}
