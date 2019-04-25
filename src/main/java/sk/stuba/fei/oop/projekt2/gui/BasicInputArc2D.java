package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicInputArc;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class BasicInputArc2D extends BasicArc2D implements Drawable {

    private BasicInputArc arc;

    public BasicInputArc2D(double x1, double y1, double x2, double y2, BasicInputArc arc) {
        super(x1, y1, x2, y2, arc.getId());
        this.arc = arc;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        drawArrowLineToRectangle(g);
        super.drawWeightToArc(g, arc.getWeight());
    }

    @Override
    public int getMultiplicity() {
        return arc.getWeight();
    }

    private void drawArrowLineToRectangle(Graphics2D g) {
        Line2D.Double l = new Line2D.Double(x1+RADIUS,y1+RADIUS,x2+RADIUS,y2+RADIUS);
        Rectangle2D.Double rectangle = new Rectangle2D.Double(x2,y2,2*RADIUS,2*RADIUS);
        Point2D.Double p = geometryUtils.getIntersectionPoint(l,rectangle);
        super.drawArrowLine(g,(int)x1+RADIUS,(int)y1+RADIUS,(int)p.getX(),(int)p.getY());
    }

}
