package sk.stuba.fei.oop.projekt2.petrinet.exceptions;

public class FailedNetConversion extends RuntimeException {

    public FailedNetConversion() {
        super("Failed to convert from XML to PetriNet!");
    }
}
