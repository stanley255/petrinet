package sk.stuba.fei.oop.projekt2.gui;

import java.awt.*;
import java.awt.geom.Line2D;

public class ResetArc2D extends Line2D.Double implements Drawable {

    private Short id;

    public ResetArc2D(double x1, double y1, double x2, double y2, Short id) {
        super(x1, y1, x2, y2);
        this.id = id;
    }

    @Override
    public void draw(Graphics2D g) {

    }
}
