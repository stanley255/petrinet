package sk.stuba.fei.oop.projekt2.utils;

import javafx.util.Pair;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GeometryUtils {

    public int[] getOffsetCoordinates(int x1, int y1, int x2, int y2, int offset) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double length = Math.sqrt(dx * dx + dy * dy);
        if (length > 0)
        {
            dx /= length;
            dy /= length;
        }
        dx *= length - offset;
        dy *= length - offset;
        int x3 = (int)(x1 + dx);
        int y3 = (int)(y1 + dy);
        return new int[]{x3,y3};
    }

    public int[] getMiddleCoordinates(double x1, double y1, double x2, double y2) {
        return new int[]{(int) ((x1+x2)/2), (int) ((y1+y2)/2)};
    }

    public Point2D.Double getIntersectionPoint(Line2D.Double line, Rectangle2D rect) {
        Line2D.Double intersectionLine;
        Line2D.Double downLine = new Line2D.Double(rect.getMinX(),rect.getMinY(),rect.getMaxX(),rect.getMinY());
        Line2D.Double upLine = new Line2D.Double(rect.getMinX(),rect.getMaxY(),rect.getMaxX(),rect.getMaxY());
        Line2D.Double leftLine = new Line2D.Double(rect.getMinX(),rect.getMaxY(),rect.getMinX(),rect.getMinY());
        Line2D.Double rightLine = new Line2D.Double(rect.getMaxX(),rect.getMaxY(),rect.getMaxX(),rect.getMinY());
        if (line.intersectsLine(upLine)) {
            intersectionLine = upLine;
        } else if (line.intersectsLine(downLine)) {
            intersectionLine = downLine;
        } else if (line.intersectsLine(leftLine)) {
            intersectionLine = leftLine;
        } else {
            intersectionLine = rightLine;
        }
        return findIntersection(line, intersectionLine);
    }

    private Point2D.Double findIntersection(Line2D l1, Line2D l2) {
        double a1 = l1.getP2().getY() - l1.getP1().getY();
        double b1 = l1.getP1().getX() - l1.getP2().getX();
        double c1 = a1 * l1.getP1().getX() + b1 * l1.getP1().getY();

        double a2 = l2.getP2().getY() - l2.getP1().getY();
        double b2 = l2.getP1().getX() - l2.getP2().getX();
        double c2 = a2 * l2.getP1().getX() + b2 * l2.getP1().getY();

        double delta = a1 * b2 - a2 * b1;
        return new Point2D.Double((b2 * c1 - b1 * c2) / delta, (a1 * c2 - a2 * c1) / delta);
    }

    public Pair<int[],int[]> getArrowCoordinates(int x1, int y1, int x2, int y2, int d, int h) {
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

        return new Pair<>(xpoints,ypoints);

    }

}
