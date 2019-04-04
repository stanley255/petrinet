package sk.stuba.fei.oop.projekt2.petrinet.exceptions;

public class IllegalArcWeight extends RuntimeException {

    public IllegalArcWeight() {
        super("Arc's weight cannot be 0 or less!");
    }

}
