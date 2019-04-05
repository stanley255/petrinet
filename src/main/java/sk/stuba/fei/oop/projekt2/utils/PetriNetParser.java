package sk.stuba.fei.oop.projekt2.utils;

import sk.stuba.fei.oop.projekt2.generated.Arc;
import sk.stuba.fei.oop.projekt2.generated.Transition;
import sk.stuba.fei.oop.projekt2.petrinet.PetriNet;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicInputArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicOutputArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.ResetArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Vertex;
import sk.stuba.fei.oop.projekt2.generated.Document;
import sk.stuba.fei.oop.projekt2.generated.Place;


import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetriNetParser {

    private Document loadedNetDocument;
    private PetriNet petriNet;
    private Map<Short, sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place> places;
    private Map<Short, sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition> transitions;


    public PetriNetParser(File file) throws JAXBException {
        this.loadedNetDocument = new XMLLoader().loadDocumentFromXML(file);
        this.petriNet = new PetriNet();
        this.places = new HashMap<Short, sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place>();
        this.transitions = new HashMap<Short, sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition>();
        try {
            this.convertToPetriNet();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void convertToPetriNet() {
        convertPlaces();
        convertTransitions();
        convertArcs();
        petriNet.printPlaces();
    }

    private void convertPlaces() {
        List<Place> loadedPlaces = loadedNetDocument.getPlace();
        for (Place loadedPlace:loadedPlaces) {
            sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place place = new sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place();
            place.setId(loadedPlace.getId());
            place.setName(loadedPlace.getLabel());
            place.setTokenCount(loadedPlace.getTokens());
            place.setX(loadedPlace.getX());
            place.setY(loadedPlace.getY());
            this.petriNet.add(place);
            this.places.put(place.getId(),place);
        }
    }

    private void convertTransitions() {
        List<Transition> loadedTransitions = loadedNetDocument.getTransition();
        for (Transition loadedTransition : loadedTransitions) {
            sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition transition = new sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition();
            transition.setId(loadedTransition.getId());
            transition.setName(loadedTransition.getLabel());
            transition.setX(loadedTransition.getX());
            transition.setY(loadedTransition.getY());
            this.petriNet.add(transition);
            this.transitions.put(transition.getId(),transition);
        }
    }

    private void convertArcs() {
        List<Arc> loadedGeneratedArcs = loadedNetDocument.getArc();
        for (Arc loadedGeneratedArc : loadedGeneratedArcs) {
            if (loadedGeneratedArc.getType().equals("regular")) {
                convertToRegularArc(loadedGeneratedArc);
            } else if (loadedGeneratedArc.getType().equals("reset")) {
                convertToResetArc(loadedGeneratedArc);
            }
        }
    }

    private void convertToRegularArc(Arc loadedGeneratedArc) {
        Short sourceID = loadedGeneratedArc.getSourceId();
        Short destinationID = loadedGeneratedArc.getDestinationId();
        if (isInputArc(loadedGeneratedArc)) {
            BasicInputArc arc = new BasicInputArc(places.get(sourceID),transitions.get(destinationID));
            petriNet.add(arc);
        } else {
            BasicOutputArc arc = new BasicOutputArc(transitions.get(sourceID),places.get(destinationID));
            petriNet.add(arc);
        }
    }

    private void convertToResetArc(Arc loadedGeneratedArc) {
        sk.stuba.fei.oop.projekt2.petrinet.components.arcs.ResetArc resetArc = new ResetArc(this.places.get(loadedGeneratedArc.getSourceId()),this.transitions.get(loadedGeneratedArc.getDestinationId()));
        this.petriNet.add(resetArc);
    }

    private boolean isInputArc(Arc loadedGeneratedArc) {
        return this.places.containsKey(loadedGeneratedArc.getId());
    }

}
