package sk.stuba.fei.oop.projekt2.gui;

import javafx.util.Pair;
import sk.stuba.fei.oop.projekt2.petrinet.PetriNet;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.Arc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.ResetArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Vertex;
import sk.stuba.fei.oop.projekt2.utils.GeometryUtils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetriNetCanvas extends Canvas implements MouseListener {

    private final int DIAMETER = 30;
    private final int RADIUS = 15;
    private final int ARROW_LENGTH = RADIUS;
    private final int ARROW_WIDTH = 6;
    private final int TEXT_OFFSET = 7;
    private final Color COLOR_GREY = new Color(187, 187, 187);

    private PetriNet petriNet;
    private List<Drawable> drawables;
    private Map<Short,Rectangle2D> transitionRectangles;
    private final GeometryUtils geometryUtils = new GeometryUtils();

    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    public void setDrawables(List<Drawable> drawables) {
        this.drawables = drawables;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (petriNet == null) { return; }

        /*loadTransitionRectangles();
        drawLines(g2);
        drawPlaces(g2);
        drawTransitions(g2);*/

        for (Drawable drawable : drawables) {
            drawable.draw(g2);
        }
    }

    private void drawLines(Graphics2D g) {
        g.setColor(Color.BLACK);
        for (Arc arc : petriNet.getArcs()) {
            Vertex startPoint = (Vertex) arc.getStartPoint();
            Vertex endPoint = arc.getEndPoint();
            if (arc instanceof BasicArc) {
                drawBasicLine(g,startPoint,endPoint,((BasicArc) arc).getWeight());
            } else if (arc instanceof ResetArc) {
                drawResetLine(g,startPoint,endPoint);
            }
        }
    }

    private void drawBasicLine(Graphics2D g, Vertex startPoint, Vertex endPoint, int weight) {
        if (this.transitionRectangles.containsKey(endPoint.getId())) {
            drawArrowLineToRectangle(g,startPoint,endPoint);
        } else {
            drawArrowLineToEllipse(g,startPoint,endPoint);
        }
        drawWeightToArc(g, startPoint, endPoint, weight);
    }

    private void drawArrowLineToRectangle(Graphics2D g, Vertex startPoint, Vertex endPoint) {
        Line2D.Double l = new Line2D.Double(startPoint.getX()+RADIUS,startPoint.getY()+RADIUS,endPoint.getX()+RADIUS,endPoint.getY()+RADIUS);
        Point2D.Double p = geometryUtils.getIntersectionPoint(l,this.transitionRectangles.get(endPoint.getId()));
        drawArrowLine(g,startPoint.getX()+RADIUS,startPoint.getY()+RADIUS,(int)p.getX(),(int)p.getY());
    }

    private void drawArrowLineToEllipse(Graphics2D g, Vertex startPoint, Vertex endPoint) {
        int[] newCoords = geometryUtils.getOffsetCoordinates(startPoint.getX()+RADIUS,startPoint.getY()+RADIUS,endPoint.getX()+RADIUS,endPoint.getY()+RADIUS,RADIUS);
        drawArrowLine(g,startPoint.getX()+RADIUS,startPoint.getY()+RADIUS,newCoords[0],newCoords[1]);
    }

    private void drawWeightToArc(Graphics2D g, Vertex startPoint, Vertex endPoint, int weight) {
        int[] textCoords = geometryUtils.getMiddleCoordinates(startPoint.getX()+RADIUS,startPoint.getY()+RADIUS,endPoint.getX()+RADIUS,endPoint.getY()+RADIUS);
        g.drawString(String.valueOf(weight),textCoords[0]-TEXT_OFFSET,textCoords[1]-TEXT_OFFSET);
    }

    private void drawResetLine(Graphics2D g, Vertex startPoint, Vertex endPoint) {
        Line2D.Double l = new Line2D.Double(startPoint.getX()+RADIUS,startPoint.getY()+RADIUS,endPoint.getX()+RADIUS,endPoint.getY()+RADIUS);
        Point2D.Double p = geometryUtils.getIntersectionPoint(l,this.transitionRectangles.get(endPoint.getId()));
        drawDoubleArrowLine(g,startPoint.getX()+RADIUS,startPoint.getY()+RADIUS,(int)p.getX(),(int)p.getY());
    }

    private void drawPlaces(Graphics2D g) {
        for (Place place : petriNet.getPlaces()) {
            // Draw Ellipse
            Ellipse2D.Double ellipse = new Ellipse2D.Double(place.getX(),place.getY(),DIAMETER,DIAMETER);
            g.setColor(COLOR_GREY);
            g.fill(ellipse);
            drawTokenCount(g, place);
        }
    }

    private void drawTokenCount(Graphics2D g, Place place) {
        g.setColor(Color.BLACK);
        int fontWidth = g.getFontMetrics().stringWidth(String.valueOf(place.getTokenCount()));
        g.drawString(String.valueOf(place.getTokenCount()),place.getX()+RADIUS-fontWidth/2,place.getY()+RADIUS+4);
    }

    private void drawTransitions(Graphics2D g) {
        transitionRectangles = new HashMap<>();
        for (Transition transition : petriNet.getTransitions()) {
            // Pick transition color based on transition's ability to fire
            pickTransitionColor(g, transition);
            // Draw transition
            Rectangle2D rectangle = new Rectangle2D.Double(transition.getX(), transition.getY(), DIAMETER, DIAMETER);
            g.fill(rectangle);
            // Put transition to transitionRectangles (map to determine which transition was clicked)
            transitionRectangles.put(transition.getId(),rectangle);
        }
    }

    private void pickTransitionColor(Graphics2D g, Transition transition) {
        if (petriNet.isTransitionFireable(transition.getId())) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.RED);
        }
    }

    private void loadTransitionRectangles() {
        transitionRectangles = new HashMap<>();
        for (Transition transition : petriNet.getTransitions()) {
            Rectangle2D rectangle = new Rectangle2D.Double(transition.getX(), transition.getY(), DIAMETER, DIAMETER);
            transitionRectangles.put(transition.getId(),rectangle);
        }
    }

    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2) {
        Pair<int[],int[]> points = geometryUtils.getArrowCoordinates(x1,y1,x2,y2,ARROW_LENGTH,ARROW_WIDTH);
        int[] xpoints = points.getKey();
        int[] ypoints = points.getValue();
        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints,ypoints, 3);
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
