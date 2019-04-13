package sk.stuba.fei.oop.projekt2.gui;

import javafx.util.Pair;
import sk.stuba.fei.oop.projekt2.utils.GeometryUtils;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class BasicArc2D extends Line2D.Double implements Drawable {

    private Short id;
    private int multiplicity;
    private String direction;
    private final GeometryUtils geometryUtils = new GeometryUtils();

    private final int RADIUS = 15;
    private final int ARROW_LENGTH = RADIUS;
    private final int ARROW_WIDTH = 6;
    private final int TEXT_OFFSET = 7; // TODO: DELETE

    public BasicArc2D(double x1, double y1, double x2, double y2, Short id, int multiplicity, String direction) {
        super(x1, y1, x2, y2);
        this.id = id;
        this.multiplicity = multiplicity;
        this.direction = direction;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        if (direction.equals("input")) {
            drawArrowLineToRectangle(g);
        } else {
            drawArrowLineToEllipse(g);
        }
        drawWeightToArc(g);
    }

    private void drawArrowLineToRectangle(Graphics2D g) {
        Line2D.Double l = new Line2D.Double(x1+RADIUS,y1+RADIUS,x2+RADIUS,y2+RADIUS);
        Rectangle2D.Double rectangle = new Rectangle2D.Double(x2,y2,2*RADIUS,2*RADIUS);
        Point2D.Double p = geometryUtils.getIntersectionPoint(l,rectangle);
        drawArrowLine(g,(int)x1+RADIUS,(int)y1+RADIUS,(int)p.getX(),(int)p.getY());
    }

    private void drawArrowLineToEllipse(Graphics2D g) {
        int[] newCoords = geometryUtils.getOffsetCoordinates((int)x1+RADIUS,(int)y1+RADIUS,(int)x2+RADIUS,(int)y2+RADIUS,RADIUS);
        drawArrowLine(g,(int)x1+RADIUS,(int)y1+RADIUS,newCoords[0],newCoords[1]);
    }

    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2) {
        Pair<int[],int[]> points = geometryUtils.getArrowCoordinates(x1,y1,x2,y2,ARROW_LENGTH,ARROW_WIDTH);
        int[] xpoints = points.getKey();
        int[] ypoints = points.getValue();
        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints,ypoints, 3);
    }

    private void drawWeightToArc(Graphics2D g) {
        int[] textCoords = geometryUtils.getMiddleCoordinates(x1+RADIUS,y1+RADIUS,x2+RADIUS,y2+RADIUS);
        g.drawString(String.valueOf(multiplicity),textCoords[0]-TEXT_OFFSET,textCoords[1]-TEXT_OFFSET);
    }

}
