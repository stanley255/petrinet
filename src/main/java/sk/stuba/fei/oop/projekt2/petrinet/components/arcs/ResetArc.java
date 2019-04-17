package sk.stuba.fei.oop.projekt2.petrinet.components.arcs;

import sk.stuba.fei.oop.projekt2.gui.ResetArc2D;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition;

import java.lang.reflect.Type;

public final class ResetArc extends Arc<Place,Transition> implements Input {

    public ResetArc(Place startPoint, Transition endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    public void consumeTokensFromPlace() {
        Place place = this.getStartPoint();
        place.setTokenCount(0);
    }

    @Override
    public int canConsume() {
        return 0;
    }

}
