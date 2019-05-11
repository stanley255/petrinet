package sk.stuba.fei.oop.projekt2.gui;

import javafx.util.Pair;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.ResetArc;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public final class ResetArc2D extends Arc2D implements Drawable {

    private ResetArc arc; // Added for future upgrades

    public ResetArc2D(double x1, double y1, double x2, double y2, Short id, Short sourceId, Short destinationId, ResetArc arc) {
        super(x1, y1, x2, y2,id, sourceId, destinationId);
        this.arc = arc;
    }

    @Override
    public String getType() {
        return "reset";
    }

    @Override
    public int getMultiplicity() {
        return 1;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        Line2D.Double l = new Line2D.Double(x1,y1,x2,y2);
        Rectangle2D.Double rectangle = new Rectangle2D.Double(x2-RADIUS,y2-RADIUS,2*RADIUS,2*RADIUS);
        Point2D.Double p = geometryUtils.getIntersectionPoint(l,rectangle);
        drawDoubleArrowLine(g,(int)x1,(int)y1,(int)p.getX(),(int)p.getY());
    }

    private void drawDoubleArrowLine(Graphics g, int x1, int y1, int x2, int y2) {
        Pair<int[],int[]> points = geometryUtils.getArrowCoordinates(x1,y1,x2,y2,ARROW_LENGTH,ARROW_WIDTH);
        int[] xpoints = points.getKey();
        int[] ypoints = points.getValue();
        int[] newStartCoords = geometryUtils.getOffsetCoordinates(x2,y2,x1,y1,RADIUS);
        g.drawLine(newStartCoords[0], newStartCoords[1], x2, y2);
        g.fillPolygon(xpoints,ypoints, 3);
        int[] newEndPoint = geometryUtils.getMiddleCoordinates(xpoints[1],ypoints[1],xpoints[2],ypoints[2]);
        points = geometryUtils.getArrowCoordinates(x1,y1,newEndPoint[0],newEndPoint[1],ARROW_LENGTH,ARROW_WIDTH);
        g.fillPolygon(points.getKey(),points.getValue(), 3);
    }

}
