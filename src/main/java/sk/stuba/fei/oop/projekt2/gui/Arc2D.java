package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.utils.GeometryUtils;

import java.awt.geom.Line2D;

public abstract class Arc2D extends Line2D.Double implements Identifiable {

    protected Short id;
    protected Short sourceId;
    protected Short destinationId;
    protected final GeometryUtils geometryUtils = new GeometryUtils();

    protected final int RADIUS = 15;
    protected final int ARROW_LENGTH = 8;
    protected final int ARROW_WIDTH = 5;
    protected final int RECT_SIZE = 17;

    protected Arc2D(double x1, double y1, double x2, double y2, Short id, Short sourceId, Short destinationId) {
        super(x1, y1, x2, y2);
        this.id = id;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
    }

    public abstract String getType();

    public abstract int getMultiplicity();

    public Short getId() {
        return id;
    }

    public Short getSourceId() {
        return sourceId;
    }

    public Short getDestinationId() {
        return destinationId;
    }
}
