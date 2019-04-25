package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicOutputArc;

import java.awt.*;

public class BasicOutputArc2D extends BasicArc2D implements Drawable {

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
        int[] newCoords = geometryUtils.getOffsetCoordinates((int)x1+RADIUS,(int)y1+RADIUS,(int)x2+RADIUS,(int)y2+RADIUS,RADIUS);
        drawArrowLine(g,(int)x1+RADIUS,(int)y1+RADIUS,newCoords[0],newCoords[1]);
    }

}
