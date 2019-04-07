package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.petrinet.PetriNet;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.Arc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.ResetArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Vertex;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PetriNetCanvas extends Canvas implements MouseListener {

    private PetriNet petriNet;
    private Map<Short,Rectangle2D> transitionRectangles;
    private final int DIAMETER = 30;
    private final int RADIUS = 15;

    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (petriNet == null) { return; }
        drawLines(g2);
        drawPlaces(g2);
        drawTransitions(g2);
    }

    private void drawLines(Graphics2D g) {
        g.setColor(new Color(0, 0, 0));
        for (Arc arc : petriNet.getArcs()) {
            Vertex startPoint = (Vertex) arc.getStartPoint();
            Vertex endPoint = (Vertex) arc.getEndPoint();
            if (arc instanceof BasicArc) {
                int[] textCoords = getMiddleCoordinates(startPoint.getX()+RADIUS,startPoint.getY()+RADIUS,endPoint.getX()+RADIUS,endPoint.getY()+RADIUS);
                g.drawString(String.valueOf(((BasicArc) arc).getWeight()),textCoords[0]-7,textCoords[1]-7); // TODO Offset
                drawArrowLine(g,startPoint.getX()+RADIUS,startPoint.getY()+RADIUS,endPoint.getX()+RADIUS,endPoint.getY()+RADIUS,15,7);
            } else if (arc instanceof ResetArc) {
                drawArrowLine(g,startPoint.getX()+RADIUS,startPoint.getY()+RADIUS,endPoint.getX()+RADIUS,endPoint.getY()+RADIUS,10,7);
                drawArrowLine(g,startPoint.getX()+RADIUS,startPoint.getY()+RADIUS,endPoint.getX()+RADIUS,endPoint.getY()+RADIUS,15,7);
            }
        }
    }

    private void drawPlaces(Graphics2D g) {
        for (Place place : petriNet.getPlaces()) {
            Ellipse2D.Double ellipse = new Ellipse2D.Double(place.getX(),place.getY(),DIAMETER,DIAMETER);
            g.setColor(new Color(187, 187, 187));
            g.fill(ellipse);
            g.setColor(new Color(0, 0, 0));
            int fontWidth = g.getFontMetrics().stringWidth(String.valueOf(place.getTokenCount()));
            g.drawString(String.valueOf(place.getTokenCount()),place.getX()+RADIUS-fontWidth/2,place.getY()+RADIUS+4);
        }
    }

    private void drawTransitions(Graphics2D g) {
        transitionRectangles = new HashMap<>();
        for (Transition transition : petriNet.getTransitions()) {
            if (petriNet.isTransitionFireable(transition.getId())) {
                g.setColor(new Color(3, 255, 19));
            } else {
                g.setColor(new Color(255, 15, 0));
            }
            Rectangle2D rectangle = new Rectangle2D.Double(transition.getX(), transition.getY(), DIAMETER, DIAMETER);
            g.fill(rectangle);
            transitionRectangles.put(transition.getId(),rectangle);
        }
    }

    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }

    private int[] getMiddleCoordinates(double x1, double y1, double x2, double y2) {
        return new int[]{(int) ((x1+x2)/2), (int) ((y1+y2)/2)};
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Map.Entry<Short, Rectangle2D> rectangleEntry : this.transitionRectangles.entrySet()) {
            Rectangle2D rectangle = rectangleEntry.getValue();
            if (rectangle.contains(e.getX(),e.getY())) {
                petriNet.fire(rectangleEntry.getKey());
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
