package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.petrinet.PetriNet;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Transition2D extends Rectangle2D.Double implements Drawable, Executable {

    private Short id;
    private String label;
    private PetriNet petriNet;

    public Transition2D(double x, double y, double w, double h, Short id, String label, PetriNet petriNet) {
        super(x, y, w, h);
        this.id = id;
        this.label = label;
        this.petriNet = petriNet;
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
        int fontWidth = g.getFontMetrics().stringWidth(String.valueOf(label));
        g.drawString(String.valueOf(label),(int)x+radius-fontWidth/2,(int)getMaxY()+12);
    }

}
