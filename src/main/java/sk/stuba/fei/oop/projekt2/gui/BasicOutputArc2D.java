package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicOutputArc;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public final class BasicOutputArc2D extends BasicArc2D implements Drawable {

    private BasicOutputArc arc;

    public BasicOutputArc2D(double x1, double y1, double x2, double y2, Short sourceId, Short destinationId, BasicOutputArc arc) {
        super(x1, y1, x2, y2, arc.getId(), sourceId, destinationId);
        this.arc = arc;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        drawArrowLineToEllipse(g);
        drawWeightToArc(g, arc.getWeight());
    }

    @Override
    public int getMultiplicity() {
        return arc.getWeight();
    }

    private void drawArrowLineToEllipse(Graphics2D g) {
        int[] newCoords = geometryUtils.getOffsetCoordinates((int)x1,(int)y1,(int)x2,(int)y2,RADIUS);
        Line2D.Double l = new Line2D.Double(x2,y2,x1,y1);
        Rectangle2D.Double rectangle = new Rectangle2D.Double(x1-RADIUS,y1-RADIUS,2*RADIUS,2*RADIUS);
        Point2D.Double p = geometryUtils.getIntersectionPoint(l,rectangle);
        drawArrowLine(g,(int)p.getX(),(int)p.getY(),newCoords[0],newCoords[1]);
    }

}
