package sk.stuba.fei.oop.projekt2.utils.listeners;

import sk.stuba.fei.oop.projekt2.gui.PetriNetCanvas;
import sk.stuba.fei.oop.projekt2.gui.Transition2D;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition;

import java.awt.event.MouseEvent;

public final class TransitionListener extends ActionButtonListener {

    public TransitionListener(PetriNetCanvas canvas) {
        super(canvas);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        addTransition(e);
    }

    public void addTransition(MouseEvent e) {
        Transition transition = new Transition();
        canvas.getPetriNet().add(transition);
        Transition2D transition2D = new Transition2D(e.getX()-DIAMETER/2,e.getY()-DIAMETER/2,DIAMETER,DIAMETER,transition,canvas.getPetriNet());
        canvas.getDrawables().add(transition2D);
        canvas.repaint();
    }

}
