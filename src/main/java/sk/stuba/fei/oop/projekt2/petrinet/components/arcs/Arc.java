package sk.stuba.fei.oop.projekt2.petrinet.components.arcs;

import sk.stuba.fei.oop.projekt2.petrinet.components.Component;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.*;
import sk.stuba.fei.oop.projekt2.petrinet.exceptions.NullVertexConnection;

public abstract class Arc <T,S extends Vertex> extends Component {

    private T startPoint;
    private S endPoint;

    Arc(T startPoint, S endPoint) {
        if (startPoint == null || endPoint == null) {
            throw new NullVertexConnection();
        }
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public T getStartPoint() {
        return startPoint;
    }

    public S getEndPoint() {
        return endPoint;
    }

}
