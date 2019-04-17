package sk.stuba.fei.oop.projekt2.petrinet.components.arcs;

import sk.stuba.fei.oop.projekt2.gui.BasicOutputArc2D;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition;

import java.lang.reflect.Type;

public final class BasicOutputArc extends BasicArc implements Output {

    public BasicOutputArc(Transition startPoint, Place endPoint, int weight) {
        super(startPoint, endPoint, weight);
    }

    public BasicOutputArc(Transition startPoint, Place endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    public void produceTokensToPlace() {
        Place place = (Place)this.getEndPoint();
        place.setTokenCount(place.getTokenCount() + this.getWeight());
    }

}
