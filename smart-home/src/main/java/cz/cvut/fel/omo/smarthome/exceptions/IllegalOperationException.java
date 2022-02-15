package cz.cvut.fel.omo.smarthome.exceptions;

public class IllegalOperationException extends RuntimeException {
    public IllegalOperationException(String errorMessage) {
        super(errorMessage);
    }
}
