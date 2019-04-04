package sk.stuba.fei.oop.projekt2.petrinet.exceptions;

public class NegativeTokenCount extends RuntimeException {

    public NegativeTokenCount() {
        super("Place's token count cannot be negative number!");
    }

}
