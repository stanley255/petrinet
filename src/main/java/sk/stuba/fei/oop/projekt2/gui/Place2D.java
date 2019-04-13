package sk.stuba.fei.oop.projekt2.gui;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Place2D extends Ellipse2D.Double implements Drawable{

    private Short id;
    private String label;
    private int tokens;

    public Place2D(double x, double y, double w, double h, Short id, String label, int tokens) {
        super(x, y, w, h);
        this.id = id;
        this.label = label;
        this.tokens = tokens;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(187, 187, 187));
        g.fill(this);
        drawTokenCount(g);
        drawLabel(g);
    }

    private void drawTokenCount(Graphics2D g) {
        g.setColor(Color.BLACK);
        int radius = (int)width/2;
        System.out.println("["+x+","+y+"]");
        int fontWidth = g.getFontMetrics().stringWidth(String.valueOf(tokens));
        g.drawString(String.valueOf(tokens),(int)x+radius-fontWidth/2,(int)y+radius+4);
    }

    private void drawLabel(Graphics2D g) {
        int radius = (int)width/2;
        int fontWidth = g.getFontMetrics().stringWidth(String.valueOf(label));
        g.drawString(String.valueOf(label),(int)x+radius-fontWidth/2,(int)getMaxY()+12);
    }
}
