package sk.stuba.fei.oop.projekt2.petrinet.components;
import java.math.BigInteger;
import java.util.UUID;
public abstract class Component {

    private short id;

    public Component() {
        String idString = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0,4);
        this.id = Short.parseShort(idString);
    }

    public void setId(short id) {
        this.id = id;
    }

    public short getId() {
        return this.id;
    }

}
