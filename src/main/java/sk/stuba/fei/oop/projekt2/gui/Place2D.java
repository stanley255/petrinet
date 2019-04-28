package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Place2D extends Ellipse2D.Double implements Drawable, Identifiable {

    private Short id;
    private Place place;

    private static final int TEXT_OFFSET = 14;

    public Place2D(double x, double y, double w, double h, Place place) {
        super(x, y, w, h);
        this.id = place.getId();
        this.place = place;
    }

    public Short getId() {
        return id;
    }

    public String getLabel() {
        return place.getName();
    }

    public int getTokens() {
        return place.getTokenCount();
    }

    public Place getPlace() {
        return place;
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
        int fontWidth = g.getFontMetrics().stringWidth(String.valueOf(place.getTokenCount()));
        g.drawString(String.valueOf(place.getTokenCount()),(int)x+radius-fontWidth/2,(int)y+radius+4);
    }

    private void drawLabel(Graphics2D g) {
        g.setColor(Color.BLACK);
        int radius = (int)width/2;
        int fontWidth = g.getFontMetrics().stringWidth(String.valueOf(place.getName()));
        g.drawString(String.valueOf(place.getName()),(int)x+radius-fontWidth/2,(int)getMaxY()+TEXT_OFFSET);
    }

}
