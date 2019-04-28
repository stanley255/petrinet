package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.petrinet.PetriNet;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Transition2D extends Rectangle2D.Double implements Drawable, Executable, Identifiable {

    private Short id;
    private Transition transition;
    private PetriNet petriNet;

    private static final int TEXT_OFFSET = 17;

    public Transition2D(double x, double y, double w, double h, Transition transition, PetriNet petriNet) {
        super(x, y, w, h);
        this.id = transition.getId();
        this.transition = transition;
        this.petriNet = petriNet;
    }

    public Transition getTransition() {
        return transition;
    }

    @Override
    public void onClick() {
        petriNet.fire(id);
    }

    @Override
    public void draw(Graphics2D g) {
        int w = (int) width;
        pickTransitionColor(g);
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, w);
        g.fill(rectangle);
        drawLabel(g);
    }

    private void pickTransitionColor(Graphics2D g) {
        if (petriNet.isTransitionFireable(id)) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.RED);
        }
    }

    private void drawLabel(Graphics2D g) {
        g.setColor(Color.BLACK);
        int radius = (int)width/2;
        int fontWidth = g.getFontMetrics().stringWidth(String.valueOf(transition.getName()));
        g.drawString(String.valueOf(transition.getName()),(int)x+radius-fontWidth/2,(int)getMaxY()+ TEXT_OFFSET);
    }

    public Short getId() {
        return id;
    }

    public String getLabel() {
        return transition.getName();
    }

}
