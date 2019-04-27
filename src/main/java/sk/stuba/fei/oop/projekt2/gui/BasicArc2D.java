package sk.stuba.fei.oop.projekt2.gui;

import javafx.util.Pair;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.Arc;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class BasicArc2D extends Arc2D {

    public BasicArc2D(double x1, double y1, double x2, double y2, Short id, Short sourceId, Short destinationId) {
        super(x1, y1, x2, y2, id, sourceId, destinationId);
    }

    @Override
    public String getType() {
        return "regular";
    }

    protected void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2) {
        Pair<int[],int[]> points = geometryUtils.getArrowCoordinates(x1,y1,x2,y2,ARROW_LENGTH,ARROW_WIDTH);
        int[] xpoints = points.getKey();
        int[] ypoints = points.getValue();
        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints,ypoints, 3);
    }

    protected void drawWeightToArc(Graphics2D g, int multiplicity) {
        if (multiplicity == 1) {
            return;
        }
        Rectangle2D rect = getWeightBackgroundRectangle();
        g.fillRect((int)rect.getMinX(),(int)rect.getMinY(), RECT_SIZE, RECT_SIZE);
        int fontWidth = g.getFontMetrics().stringWidth(String.valueOf(multiplicity));
        int fontHeight =g.getFontMetrics().getFont().getSize()/2;
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(multiplicity),(int)rect.getCenterX()-fontWidth/2,(int)rect.getY()+2*fontHeight);
    }

    protected Rectangle2D.Double getWeightBackgroundRectangle() {
        int[] lineCenterCoordinates = geometryUtils.getMiddleCoordinates(x1,y1,x2,y2);
        int rectX = lineCenterCoordinates[0]- RECT_SIZE /2;
        int rectY = lineCenterCoordinates[1]- RECT_SIZE /2;
        return new Rectangle2D.Double(rectX,rectY, RECT_SIZE, RECT_SIZE);
    }

}
