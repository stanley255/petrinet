package sk.stuba.fei.oop.projekt2.utils;

import sk.stuba.fei.oop.projekt2.generated.Arc;
import sk.stuba.fei.oop.projekt2.generated.Document;
import sk.stuba.fei.oop.projekt2.generated.Place;
import sk.stuba.fei.oop.projekt2.generated.Transition;
import sk.stuba.fei.oop.projekt2.gui.*;
import sk.stuba.fei.oop.projekt2.petrinet.PetriNet;

import java.util.*;

public class DrawableConverter extends Converter<List<Drawable>> {

    private Document document;
    private List<Drawable> drawables = new ArrayList<>();
    private Map<Short,Place2D> places = new HashMap<>();
    private Map<Short,Transition2D> transitions = new HashMap<>();
    private PetriNet petriNet;

    private final int DIAMETER = 30;

    @Override
    List<Drawable> convert(Document document) {
        this.document = document;
        convertPlaces();
        convertTransitions();
        convertArcs();
        Collections.reverse(drawables);
        return drawables;
    }

    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    private void convertPlaces() {
        Map<Short, sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place> petriPlaces = petriNet.getPlaces();
        for (Place place : document.getPlace()) {
            Place2D place2D = new Place2D(place.getX(),place.getY(),DIAMETER,DIAMETER,place.getId(),place.getLabel(),petriPlaces.get(place.getId()));
            places.put(place.getId(),place2D);
            drawables.add(place2D);
        }
    }

    private void convertTransitions() {
        for (Transition transition : document.getTransition()) {
            boolean fireable = petriNet.isTransitionFireable(transition.getId());
            Transition2D transition2D = new Transition2D(transition.getX(),transition.getY(),DIAMETER,DIAMETER,transition.getId(),transition.getLabel(), petriNet);
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
            String direction = "input";
            basicArc2D = new BasicArc2D(startPoint.getX(),startPoint.getY(),endPoint.getX(),endPoint.getMinY(),arc.getId(),arc.getMultiplicity(),direction);
        } else {
            Transition2D startPoint = transitions.get(arc.getSourceId());
            Place2D endPoint = places.get(arc.getDestinationId());
            String direction = "output";
            basicArc2D = new BasicArc2D(startPoint.getX(),startPoint.getY(),endPoint.getX(),endPoint.getMinY(),arc.getId(),arc.getMultiplicity(),direction);
        }
        drawables.add(basicArc2D);
    }

    private void convertResetArc(Arc arc) {
        Place2D startPoint = places.get(arc.getSourceId());
        Transition2D endPoint = transitions.get(arc.getDestinationId());
        ResetArc2D resetArc2D = new ResetArc2D(startPoint.getX(),startPoint.getY(),endPoint.getX(),endPoint.getY(),arc.getId());
        drawables.add(resetArc2D);
    }

}
