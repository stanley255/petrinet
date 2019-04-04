package sk.stuba.fei.oop.projekt2.petrinet.components.vertices;

import sk.stuba.fei.oop.projekt2.petrinet.components.Component;

public abstract class Vertex extends Component {

    private String name;
    private short x;
    private short y;

    public Vertex(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getX() {
        return x;
    }

    public void setX(short x) {
        this.x = x;
    }

    public short getY() {
        return y;
    }

    public void setY(short y) {
        this.y = y;
    }
}
