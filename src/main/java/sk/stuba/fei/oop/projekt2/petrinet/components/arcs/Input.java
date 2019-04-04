package sk.stuba.fei.oop.projekt2.petrinet.components.arcs;

public interface Input {

    public abstract void consumeTokensFromPlace();

    public abstract int canConsume();

}
