package sk.stuba.fei.oop.projekt2.gui;

import java.awt.*;
import java.awt.geom.Line2D;

public class BasicArc2D extends Line2D.Double implements Drawable {

    private Short id;
    private int multiplicity;
    private String direction;

    public BasicArc2D(double x1, double y1, double x2, double y2, Short id, int multiplicity, String direction) {
        super(x1, y1, x2, y2);
        this.id = id;
        this.multiplicity = multiplicity;
        this.direction = direction;
    }

    @Override
    public void draw(Graphics2D g) {

    }
}
