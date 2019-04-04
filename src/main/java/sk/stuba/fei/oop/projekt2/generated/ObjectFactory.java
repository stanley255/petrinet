
package sk.stuba.fei.oop.projekt2.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the sk.stuba.fei.oop package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Id_QNAME = new QName("", "id");
    private final static QName _X_QNAME = new QName("", "x");
    private final static QName _Y_QNAME = new QName("", "y");
    private final static QName _Label_QNAME = new QName("", "label");
    private final static QName _Tokens_QNAME = new QName("", "tokens");
    private final static QName _Static_QNAME = new QName("", "static");
    private final static QName _Type_QNAME = new QName("", "type");
    private final static QName _SourceId_QNAME = new QName("", "sourceId");
    private final static QName _DestinationId_QNAME = new QName("", "destinationId");
    private final static QName _Multiplicity_QNAME = new QName("", "multiplicity");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: sk.stuba.fei.oop
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BreakPoint }
     * 
     */
    public BreakPoint createBreakPoint() {
        return new BreakPoint();
    }

    /**
     * Create an instance of {@link Place }
     * 
     */
    public Place createPlace() {
        return new Place();
    }

    /**
     * Create an instance of {@link Transition }
     * 
     */
    public Transition createTransition() {
        return new Transition();
    }

    /**
     * Create an instance of {@link Arc }
     * 
     */
    public Arc createArc() {
        return new Arc();
    }

    /**
     * Create an instance of {@link Document }
     * 
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "id")
    public JAXBElement<Short> createId(Short value) {
        return new JAXBElement<Short>(_Id_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "x")
    public JAXBElement<Short> createX(Short value) {
        return new JAXBElement<Short>(_X_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "y")
    public JAXBElement<Short> createY(Short value) {
        return new JAXBElement<Short>(_Y_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "label")
    public JAXBElement<String> createLabel(String value) {
        return new JAXBElement<String>(_Label_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "tokens")
    public JAXBElement<Short> createTokens(Short value) {
        return new JAXBElement<Short>(_Tokens_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "static")
    public JAXBElement<Boolean> createStatic(Boolean value) {
        return new JAXBElement<Boolean>(_Static_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "type")
    public JAXBElement<String> createType(String value) {
        return new JAXBElement<String>(_Type_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sourceId")
    public JAXBElement<Short> createSourceId(Short value) {
        return new JAXBElement<Short>(_SourceId_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "destinationId")
    public JAXBElement<Short> createDestinationId(Short value) {
        return new JAXBElement<Short>(_DestinationId_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "multiplicity")
    public JAXBElement<Short> createMultiplicity(Short value) {
        return new JAXBElement<Short>(_Multiplicity_QNAME, Short.class, null, value);
    }

}
