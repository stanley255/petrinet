package sk.stuba.fei.oop.projekt2.petrinet.exceptions;

public class NullVertexConnection extends RuntimeException {

    public NullVertexConnection() {
        super("Arc cannot connect null vertex!");
    }
}
