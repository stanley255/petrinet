package sk.stuba.fei.oop.projekt2.utils.listeners;

import sk.stuba.fei.oop.projekt2.gui.Drawable;
import sk.stuba.fei.oop.projekt2.gui.PetriNetCanvas;
import sk.stuba.fei.oop.projekt2.gui.Place2D;
import sk.stuba.fei.oop.projekt2.gui.Transition2D;

import java.awt.event.MouseAdapter;

public abstract class ActionButtonListener extends MouseAdapter {

    protected PetriNetCanvas canvas;

    protected final int DIAMETER = 30;
    protected final int RADIUS = 15;

    public ActionButtonListener(PetriNetCanvas canvas) {
        this.canvas = canvas;
    }

    protected boolean isVertex(Drawable drawable) {
        try {
            Place2D place = (Place2D) drawable;
            return true;
        } catch (ClassCastException ex1) {
            try {
                Transition2D transition = (Transition2D) drawable;
                return true;
            } catch (ClassCastException ex2) {
                return false;
            }
        }
    }

}
