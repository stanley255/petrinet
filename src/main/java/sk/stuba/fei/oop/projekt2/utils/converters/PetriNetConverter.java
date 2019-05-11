package sk.stuba.fei.oop.projekt2.utils.converters;

import sk.stuba.fei.oop.projekt2.generated.Arc;
import sk.stuba.fei.oop.projekt2.generated.Transition;
import sk.stuba.fei.oop.projekt2.petrinet.PetriNet;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicInputArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicOutputArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.ResetArc;
import sk.stuba.fei.oop.projekt2.generated.Document;
import sk.stuba.fei.oop.projekt2.generated.Place;
import sk.stuba.fei.oop.projekt2.petrinet.exceptions.FailedNetConversion;
import sk.stuba.fei.oop.projekt2.petrinet.exceptions.IllegalArcWeight;
import sk.stuba.fei.oop.projekt2.petrinet.exceptions.NegativeTokenCount;
import sk.stuba.fei.oop.projekt2.petrinet.exceptions.NullVertexConnection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PetriNetConverter extends Converter<PetriNet, Document> {

    private Document loadedNetDocument;
    private PetriNet petriNet;
    private Map<Short, sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place> places;
    private Map<Short, sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition> transitions;

    @Override
    public PetriNet convert(Document document) throws FailedNetConversion {
        this.loadedNetDocument = document;
        this.petriNet = new PetriNet();
        this.places = new HashMap<>();
        this.transitions = new HashMap<>();
        try {
            this.convertToPetriNet();
            return petriNet;
        } catch (IllegalArcWeight| NegativeTokenCount| NullVertexConnection | IllegalArgumentException e) {
            throw new FailedNetConversion();
        }
    }

    private void convertToPetriNet() {
        convertPlaces();
        convertTransitions();
        convertArcs();
    }

    private void convertPlaces() {
        List<Place> loadedPlaces = loadedNetDocument.getPlace();
        for (Place loadedPlace:loadedPlaces) {
            sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place place = new sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place();
            place.setId(loadedPlace.getId());
            place.setName(loadedPlace.getLabel());
            place.setTokenCount(loadedPlace.getTokens());
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
        Short arcID = loadedGeneratedArc.getId();
        Short sourceID = loadedGeneratedArc.getSourceId();
        Short destinationID = loadedGeneratedArc.getDestinationId();
        int weight = loadedGeneratedArc.getMultiplicity();
        if (isInputArc(loadedGeneratedArc)) {
            BasicInputArc arc = new BasicInputArc(places.get(sourceID),transitions.get(destinationID),weight);
            arc.setId(arcID);
            petriNet.add(arc);
        } else {
            BasicOutputArc arc = new BasicOutputArc(transitions.get(sourceID),places.get(destinationID),weight);
            arc.setId(arcID);
            petriNet.add(arc);
        }
    }

    private void convertToResetArc(Arc loadedGeneratedArc) {
        sk.stuba.fei.oop.projekt2.petrinet.components.arcs.ResetArc resetArc = new ResetArc(this.places.get(loadedGeneratedArc.getSourceId()),this.transitions.get(loadedGeneratedArc.getDestinationId()));
        resetArc.setId(loadedGeneratedArc.getId());
        this.petriNet.add(resetArc);
    }

    private boolean isInputArc(Arc loadedGeneratedArc) {
        return this.places.containsKey(loadedGeneratedArc.getSourceId());
    }

}
