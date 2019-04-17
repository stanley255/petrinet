package sk.stuba.fei.oop.projekt2.gui;

import javafx.util.Pair;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.ResetArc;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ResetArc2D extends Arc2D implements Drawable {

    private ResetArc arc; // Added for future upgrades

    public ResetArc2D(double x1, double y1, double x2, double y2, Short id, ResetArc arc) {
        super(x1, y1, x2, y2,id);
        this.arc = arc;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        Line2D.Double l = new Line2D.Double(x1+RADIUS,y1+RADIUS,x2+RADIUS,y2+RADIUS);
        Rectangle2D.Double rectangle = new Rectangle2D.Double(x2,y2,2*RADIUS,2*RADIUS);
        Point2D.Double p = geometryUtils.getIntersectionPoint(l,rectangle);
        drawDoubleArrowLine(g,(int)x1+RADIUS,(int)y1+RADIUS,(int)p.getX(),(int)p.getY());
    }

    private void drawDoubleArrowLine(Graphics g, int x1, int y1, int x2, int y2) {
        Pair<int[],int[]> points = geometryUtils.getArrowCoordinates(x1,y1,x2,y2,ARROW_LENGTH,ARROW_WIDTH);
        int[] xpoints = points.getKey();
        int[] ypoints = points.getValue();
        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints,ypoints, 3);
        int[] newEndPoint = geometryUtils.getMiddleCoordinates(xpoints[1],ypoints[1],xpoints[2],ypoints[2]);
        points = geometryUtils.getArrowCoordinates(x1,y1,newEndPoint[0],newEndPoint[1],ARROW_LENGTH,ARROW_WIDTH);
        g.fillPolygon(points.getKey(),points.getValue(), 3);
    }

}