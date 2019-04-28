package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.petrinet.PetriNet;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicInputArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicOutputArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.ResetArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Vertex;
import sk.stuba.fei.oop.projekt2.utils.GuiObjectsManipulator;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PetriNetCanvas extends Canvas implements MouseListener {

    private PetriNet petriNet;
    private List<Drawable> drawables;
    private String currentAction;
    private GuiObjectsManipulator guiObjectsManipulator;

    public PetriNetCanvas() {
        this.petriNet = new PetriNet();
        this.drawables = new ArrayList<>();
        this.currentAction = "play";
        this.guiObjectsManipulator = new GuiObjectsManipulator(this);
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
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

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (petriNet == null) { return; }
        for (Drawable drawable : drawables) {
            drawable.draw(g2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!this.currentAction.toLowerCase().equals("add arc") && !this.currentAction.toLowerCase().equals("add reset arc")) {
            guiObjectsManipulator.setStartPointPlace(null);
            guiObjectsManipulator.setStartPointTransition(null);
        }
        switch(this.currentAction.toLowerCase()) {
            case "add transition":
                guiObjectsManipulator.addTransition(e);
                break;
            case "add place":
                guiObjectsManipulator.addPlace(e);
                break;
            case "add arc":
                guiObjectsManipulator.addArc(e);
                break;
            case "add reset arc":
                guiObjectsManipulator.addResetArc(e);
                break;
            case "delete":
                guiObjectsManipulator.delete(e);
                break;
            case "play":
                guiObjectsManipulator.execute(e);
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
