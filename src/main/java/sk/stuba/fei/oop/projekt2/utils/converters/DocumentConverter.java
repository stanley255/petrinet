package sk.stuba.fei.oop.projekt2.utils.converters;

import sk.stuba.fei.oop.projekt2.generated.Arc;
import sk.stuba.fei.oop.projekt2.generated.Document;
import sk.stuba.fei.oop.projekt2.generated.Place;
import sk.stuba.fei.oop.projekt2.generated.Transition;
import sk.stuba.fei.oop.projekt2.gui.Arc2D;
import sk.stuba.fei.oop.projekt2.gui.Drawable;
import sk.stuba.fei.oop.projekt2.gui.Place2D;
import sk.stuba.fei.oop.projekt2.gui.Transition2D;

import java.util.ArrayList;
import java.util.List;

public final class DocumentConverter extends Converter<Document, List<Drawable>> {

    private List<Drawable> drawables;

    @Override
    public Document convert(List<Drawable> drawables) {
        this.drawables = drawables;
        Document document = new Document();
        document.setTransition(getConvertedTransitions());
        document.setPlace(getConvertedPlaces());
        document.setArc(getConvertedArcs());
        return document;
    }

    private List<Arc> getConvertedArcs() {
        List<Arc2D> arcs = getFilteredDrawables(Arc2D.class);
        List<Arc> documentArcs = new ArrayList<>();
        for (Arc2D arc : arcs) {
            Arc documentArc = new Arc();
            documentArc.setId(arc.getId());
            documentArc.setType(arc.getType());
            documentArc.setMultiplicity((short) arc.getMultiplicity());
            documentArc.setSourceId(arc.getSourceId());
            documentArc.setDestinationId(arc.getDestinationId());
            documentArcs.add(documentArc);
        }
        return documentArcs;
    }

    private List<Place> getConvertedPlaces() {
        List<Place2D> places = getFilteredDrawables(Place2D.class);
        List<Place> documentTransitions = new ArrayList<>();
        for (Place2D place : places) {
            Place documentPlace = new Place();
            documentPlace.setId(place.getId());
            documentPlace.setLabel(place.getLabel());
            documentPlace.setTokens((short) place.getTokens());
            documentPlace.setX((short) place.getX());
            documentPlace.setY((short) place.getY());
            documentPlace.setStatic(false);
            documentTransitions.add(documentPlace);
        }
        return documentTransitions;
    }

    private List<Transition> getConvertedTransitions() {
        List<Transition2D> transitions = getFilteredDrawables(Transition2D.class);
        List<Transition> documentTransitions = new ArrayList<>();
        for (Transition2D transition : transitions) {
            Transition documentTransition = new Transition();
            documentTransition.setId(transition.getId());
            documentTransition.setLabel(transition.getLabel());
            documentTransition.setX((short) transition.getX());
            documentTransition.setY((short) transition.getY());
            documentTransitions.add(documentTransition);
        }
        return documentTransitions;
    }

    private <T> List<T> getFilteredDrawables(Class<T> filterClass) {
        List<T> filteredVertices = new ArrayList<>();
        for (Drawable drawable:drawables) {
            if (filterClass.isInstance(drawable)) {
                filteredVertices.add((T)drawable);
            }
        }
        return filteredVertices;
    }
}
