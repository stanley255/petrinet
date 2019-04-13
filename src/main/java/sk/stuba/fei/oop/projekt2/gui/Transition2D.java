package sk.stuba.fei.oop.projekt2.gui;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Transition2D extends Rectangle2D.Double implements Drawable {

    private Short id;
    private String label;
    private boolean active;

    public Transition2D(double x, double y, double w, double h, Short id, String label) {
        super(x, y, w, h);
        this.id = id;
        this.label = label;
        this.active = true;
    }

    @Override
    public void draw(Graphics2D g) {
        int w = (int) width;
        pickTransitionColor(g);
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, w);
        g.fill(rectangle);
    }

    private void pickTransitionColor(Graphics2D g) {
        if (active) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.RED);
        }
    }

}
