package sk.stuba.fei.oop.projekt2.utils.converters;

import sk.stuba.fei.oop.projekt2.generated.Arc;
import sk.stuba.fei.oop.projekt2.generated.Document;
import sk.stuba.fei.oop.projekt2.generated.Place;
import sk.stuba.fei.oop.projekt2.generated.Transition;
import sk.stuba.fei.oop.projekt2.gui.*;
import sk.stuba.fei.oop.projekt2.petrinet.PetriNet;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicInputArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicOutputArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.ResetArc;

import java.util.*;

public final class DrawableConverter extends Converter<List<Drawable>, Document> {

    private Document document;
    private List<Drawable> drawables = new ArrayList<>();
    private Map<Short,Place2D> places = new HashMap<>();
    private Map<Short,Transition2D> transitions = new HashMap<>();
    private PetriNet petriNet;

    private final int RADIUS = 15;
    private final int DIAMETER = 30;

    @Override
    public List<Drawable> convert(Document document) {
        this.document = document;
        convertPlaces();
        convertTransitions();
        convertArcs();
        return drawables;
    }

    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    private void convertPlaces() {
        for (Place place : document.getPlace()) {
            Place2D place2D = new Place2D(place.getX(),place.getY(),DIAMETER,DIAMETER,(sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place) petriNet.getVertex(place.getId()));
            places.put(place.getId(),place2D);
            drawables.add(place2D);
        }
    }

    private void convertTransitions() {
        for (Transition transition : document.getTransition()) {
            Transition2D transition2D = new Transition2D(transition.getX(),transition.getY(),DIAMETER,DIAMETER, (sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition) petriNet.getVertex(transition.getId()),petriNet);
            transitions.put(transition.getId(),transition2D);
            drawables.add(transition2D);
        }
    }

    private void convertArcs() {
        for (Arc arc : document.getArc()) {
            if (arc.getType().equals("regular")) {
                convertBasicArc(arc);
            } else if (arc.getType().equals("reset")) {
                convertResetArc(arc);
            }
        }
    }

    private void convertBasicArc(Arc arc) {
        BasicArc2D basicArc2D;
        if (places.containsKey(arc.getSourceId())) {
            Place2D startPoint = places.get(arc.getSourceId());
            Transition2D endPoint = transitions.get(arc.getDestinationId());
            basicArc2D = new BasicInputArc2D(startPoint.getX()+RADIUS,startPoint.getY()+RADIUS,endPoint.getX()+RADIUS,endPoint.getMinY()+RADIUS,startPoint.getId(),endPoint.getId(),(BasicInputArc) petriNet.getArc(arc.getId()));
        } else {
            Transition2D startPoint = transitions.get(arc.getSourceId());
            Place2D endPoint = places.get(arc.getDestinationId());
            basicArc2D = new BasicOutputArc2D(startPoint.getX()+RADIUS,startPoint.getY()+RADIUS,endPoint.getX()+RADIUS,endPoint.getMinY()+RADIUS,startPoint.getId(),endPoint.getId(),(BasicOutputArc) petriNet.getArc(arc.getId()));
        }
        drawables.add((Drawable) basicArc2D);
    }

    private void convertResetArc(Arc arc) {
        Place2D startPoint = places.get(arc.getSourceId());
        Transition2D endPoint = transitions.get(arc.getDestinationId());
        ResetArc2D resetArc2D = new ResetArc2D(startPoint.getX()+RADIUS,startPoint.getY()+RADIUS,endPoint.getX()+RADIUS,endPoint.getY()+RADIUS,arc.getId(),startPoint.getId(),endPoint.getId(),(ResetArc) petriNet.getArc(arc.getId()));
        drawables.add(resetArc2D);
    }

}
