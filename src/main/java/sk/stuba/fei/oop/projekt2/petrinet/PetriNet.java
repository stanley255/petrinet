package sk.stuba.fei.oop.projekt2.petrinet;

import sk.stuba.fei.oop.projekt2.petrinet.components.Component;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.*;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.*;
import sk.stuba.fei.oop.projekt2.petrinet.exceptions.NegativeTokenCount;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public final class PetriNet {

    private Map<Short, Vertex> vertices;
    private Map<Short, Arc> arcs;

    public PetriNet() {
        this.vertices = new HashMap<>();
        this.arcs =  new HashMap<>();
    }

    public void add(Vertex vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("Null component cannot be added to Petri net!");
        }
        verifyExistingComponent(vertex);
        this.vertices.put(vertex.getId(),vertex);
    }

    public void add(Arc arc) {
        if (arc == null) {
            throw new IllegalArgumentException("Null component cannot be added to Petri net!");
        }
        verifyExistingComponent(arc);
        verifyArcVertices(arc);
        this.arcs.put(arc.getId(),arc);
    }

    public void deleteArc(Short id) {
        this.arcs.remove(id);
    }

    public void deleteJustVertex(Short id) {
        this.vertices.remove(id);
    }

    public void printPlaces() {
        for (Map.Entry<Short,Vertex> shortVertexEntry:this.vertices.entrySet()) {
            Vertex vertex = shortVertexEntry.getValue();
            if (vertex instanceof Place) {
                System.out.println(vertex);
            }
        }
    }

    public boolean isTransitionFireable(Short transitionID) {
        Transition transition = this.getTransitionFromID(transitionID);
        // Get places connected with transition
        ArrayList<Arc> transitionArcs = this.getTransitionArcs(transition);
        // Get input places
        ArrayList<Arc> inputArcs = this.filterIOArcs(transitionArcs, Input.class);
        return canAllArcsConsume(inputArcs);
    }

    public Vertex getVertex(Short vertexID) {
        if (vertices.containsKey(vertexID)) {
            return vertices.get(vertexID);
        }
        throw new IllegalArgumentException("VertexID was not found!");
    }

    public Arc getArc(Short arcID) {
        if (arcs.containsKey(arcID)) {
            return arcs.get(arcID);
        }
        throw new IllegalArgumentException("ArcID was not found!");
    }

    public boolean fire(Short transitionID) {
        Transition transition = this.getTransitionFromID(transitionID);
        // Get places connected with transition
        ArrayList<Arc> transitionArcs = this.getTransitionArcs(transition);
        // Get input places
        ArrayList<Arc> inputArcs = this.filterIOArcs(transitionArcs, Input.class);
        // Check if transition is fire-able
        if (!canAllArcsConsume(inputArcs)) {
            System.out.println("Transition "+transition+" cannot be fired!"); // This line is for quick control
            return false;
        }
        // Apply token consumption
        this.applyTokenConsumption(inputArcs);
        // Get output places
        ArrayList<Arc> outputArcs = this.filterIOArcs(transitionArcs, Output.class);
        // Increment token count of output places
        this.applyTokenProduction(outputArcs);
        System.out.println("Transition "+transition+" was fired!"); // This line is for quick control
        return true;
    }

    private void applyTokenProduction(ArrayList<Arc> inputArcs) {
        for (Arc arc : inputArcs) {
            BasicOutputArc outputArc = (BasicOutputArc) arc;
            outputArc.produceTokensToPlace();
        }
    }

    private void applyTokenConsumption(ArrayList<Arc> inputArcs) {
        for (Arc arc : inputArcs) {
            Input inputArc = (Input) arc;
            try {
                inputArc.consumeTokensFromPlace();
            } catch (NegativeTokenCount e) {
                Place place = (Place) arc.getStartPoint();
                place.setTokenCount(0);
            }
        }
    }

    private Boolean canAllArcsConsume(ArrayList<Arc> inputArcs) {
        Map<Place,Integer> consumeFromPlaces = new HashMap<Place,Integer>();
        // Creates map<Place,Integer> where int is amount of tokens in place and decrements that number based on weight
        for (Arc arc:inputArcs){
            Input input = (Input) arc;
            Place place = (Place) arc.getStartPoint();
            if (consumeFromPlaces.get(place) == null) {
                consumeFromPlaces.put(place,place.getTokenCount()-input.canConsume());
            } else {
                consumeFromPlaces.put(place,consumeFromPlaces.get(place)-input.canConsume());
            }
        }
        // Loops through map and checks for negative number occurrence
        return canAllArcsConsume(consumeFromPlaces);
    }

    private Boolean canAllArcsConsume(Map<Place,Integer> toConsumeFromPlaces) {
        for (Map.Entry<Place,Integer>placeIntegerEntry:toConsumeFromPlaces.entrySet()) {
            if (placeIntegerEntry.getValue() < 0) { return false; }
        }
        return true;
    }

    private ArrayList<Arc> getTransitionArcs(Transition transition) {
        ArrayList<Arc> filteredArcs = new ArrayList<Arc>();
        for (Map.Entry<Short,Arc>shortArcEntry:this.arcs.entrySet()) {
            Arc arc = shortArcEntry.getValue();
            if (transition == arc.getStartPoint() || transition == arc.getEndPoint()) {
                filteredArcs.add(arc);
            }
        }
        return filteredArcs;
    }

    // Method filters arc based on class interface passed (input/output)
    private ArrayList<Arc> filterIOArcs(ArrayList<Arc> arcArray ,Class ioInterface) {
        ArrayList<Arc> filteredArcs = new ArrayList<Arc>();
        for (Arc arc:arcArray) {
            if (ioInterface.isInstance(arc)) {
                filteredArcs.add(arc);
            }
        }
        return filteredArcs;
    }

    private Transition getTransitionFromID(Short transitionID) {
        try{
            Transition transToReturn = (Transition)this.vertices.get(transitionID);
            if (transToReturn == null) { throw new IllegalArgumentException("Invalid transition ID!"); }
            return transToReturn;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid transition ID!");
        }
    }

    private void verifyArcVertices(Arc arc) {
        Vertex startPoint = (Vertex) arc.getStartPoint();
        Vertex endPoint = arc.getEndPoint();
        if (this.vertices.get(startPoint.getId()) == null) {
            throw new IllegalArgumentException("Arc's start point is not added in Petri net!");
        }
        if (this.vertices.get(endPoint.getId()) == null) {
            throw new IllegalArgumentException("Arc's end point is not added in Petri net");
        }
    }

    // Since IDs are randomly generated by UUID but also truncated this function checks and changes ID if necessary
    private void changeIdToUniqueValue(Component component) {
        short newId = Short.parseShort(String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0,4));
        while (vertices.containsKey(newId) || arcs.containsKey(newId)) {
            newId = Short.parseShort(String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0,4));
        }
        component.setId(newId);
    }

    private void verifyExistingComponent(Component component) {
        if (vertices.containsKey(component.getId()) || arcs.containsKey(component.getId())) {
            changeIdToUniqueValue(component);
        }
    }

}
