package sk.stuba.fei.oop.projekt2.petrinet.components.vertices;

import sk.stuba.fei.oop.projekt2.petrinet.exceptions.NegativeTokenCount;


public final class Place extends Vertex {

    private int tokenCount;

    public Place(String name, int tokenCount) {
        super(name);
        this.setTokenCount(tokenCount);
    }

    public Place(int tokenCount) {
        super("");
        this.setTokenCount(tokenCount);
    }

    public Place(String name) {
        super(name);
        this.tokenCount = 0;
    }

    public Place() {
        super("");
        this.tokenCount = 0;
    }

    public int getTokenCount() {
        return tokenCount;
    }

    public void setTokenCount(int tokenCount) {
        if (tokenCount < 0) {
            throw new NegativeTokenCount();
        }
        this.tokenCount = tokenCount;
    }

    @Override
    public String toString() {
        return super.getName()+"["+super.getId()+"]"+" : "+this.getTokenCount();
    }

}
