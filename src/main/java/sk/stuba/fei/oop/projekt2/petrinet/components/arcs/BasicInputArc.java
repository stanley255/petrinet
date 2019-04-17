package sk.stuba.fei.oop.projekt2.petrinet.components.arcs;

import sk.stuba.fei.oop.projekt2.gui.BasicInputArc2D;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition;

import java.lang.reflect.Type;

public final class BasicInputArc extends BasicArc implements Input {

    public BasicInputArc(Place startPoint, Transition endPoint, int weight) {
        super(startPoint, endPoint, weight);
    }

    public BasicInputArc(Place startPoint, Transition endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    public void consumeTokensFromPlace() {
        Place place = (Place)this.getStartPoint();
        place.setTokenCount(place.getTokenCount() - this.getWeight());
    }

    @Override
    public int canConsume() {
        return this.weight;
    }

}
