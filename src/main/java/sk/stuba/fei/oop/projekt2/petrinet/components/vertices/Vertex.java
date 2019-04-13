package sk.stuba.fei.oop.projekt2.petrinet.components.vertices;

import sk.stuba.fei.oop.projekt2.petrinet.components.Component;

public abstract class Vertex extends Component {

    private String name;

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

}
