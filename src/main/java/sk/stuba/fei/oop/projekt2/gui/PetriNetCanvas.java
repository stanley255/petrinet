package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.petrinet.PetriNet;

import java.awt.event.MouseListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PetriNetCanvas extends Canvas {

    private PetriNet petriNet;
    private List<Drawable> drawables;
    private String currentAction;
    private String lastAction;
    private Place2D startPointPlace;
    private Transition2D startPointTransition;

    public PetriNetCanvas() {
        this.petriNet = new PetriNet();
        this.drawables = new ArrayList<>();
        this.currentAction = "play";
        this.lastAction = "play";
        this.startPointPlace = null;
        this.startPointTransition = null;
    }

    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    public PetriNet getPetriNet() {
        return this.petriNet;
    }

    public void setDrawables(List<Drawable> drawables) {
        this.drawables = drawables;
    }

    public List<Drawable> getDrawables() {
        return drawables;
    }

    public Place2D getStartPointPlace() {
        return startPointPlace;
    }

    public void setStartPointPlace(Place2D startPointPlace) {
        this.startPointPlace = startPointPlace;
    }

    public Transition2D getStartPointTransition() {
        return startPointTransition;
    }

    public void setStartPointTransition(Transition2D startPointTransition) {
        this.startPointTransition = startPointTransition;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (petriNet == null) { return; }
        for (Drawable drawable : drawables) {
            drawable.draw(g2);
        }
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        super.addMouseListener(l);
    }

    public void removeMouseListeners() {
        for (MouseListener mouseListener : getMouseListeners()) {
            removeMouseListener(mouseListener);
        }
    }

}
