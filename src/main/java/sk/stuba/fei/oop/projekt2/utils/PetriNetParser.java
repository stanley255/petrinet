package sk.stuba.fei.oop.projekt2.utils;

import sk.stuba.fei.oop.projekt2.generated.Arc;
import sk.stuba.fei.oop.projekt2.generated.Transition;
import sk.stuba.fei.oop.projekt2.petrinet.PetriNet;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Vertex;
import sk.stuba.fei.oop.projekt2.generated.Document;
import sk.stuba.fei.oop.projekt2.generated.Place;


import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

public class PetriNetParser {

    private Document loadedNetDocument;
    private PetriNet petriNet;

    public PetriNetParser(File file) throws JAXBException {
        this.loadedNetDocument = new XMLLoader().loadDocumentFromXML(file);
        this.petriNet = new PetriNet();
        try {
            this.convertToPetriNet();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void convertToPetriNet() {
        convertPlaces();
        convertTransitions();
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
        }
    }

    private void convertArcs() {
        List<Arc> loadedArcs = loadedNetDocument.getArc();
        // TODO
    }

}
