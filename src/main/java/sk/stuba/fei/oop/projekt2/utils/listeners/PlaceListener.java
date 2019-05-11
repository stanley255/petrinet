package sk.stuba.fei.oop.projekt2.utils.listeners;

import sk.stuba.fei.oop.projekt2.gui.PetriNetCanvas;
import sk.stuba.fei.oop.projekt2.gui.Place2D;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place;

import java.awt.event.MouseEvent;

public final class PlaceListener extends ActionButtonListener {

    public PlaceListener(PetriNetCanvas canvas) {
        super(canvas);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        addPlace(e);
    }

    public void addPlace(MouseEvent e) {
        Place place = new Place();
        canvas.getPetriNet().add(place);
        Place2D place2D = new Place2D(e.getX()-DIAMETER/2,e.getY()-DIAMETER/2,DIAMETER,DIAMETER,place);
        canvas.getDrawables().add(place2D);
        canvas.repaint();
    }

}
