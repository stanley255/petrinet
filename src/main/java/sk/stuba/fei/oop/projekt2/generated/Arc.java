
package sk.stuba.fei.oop.projekt2.generated;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{}id"/&gt;
 *         &lt;element ref="{}type"/&gt;
 *         &lt;element ref="{}sourceId"/&gt;
 *         &lt;element ref="{}destinationId"/&gt;
 *         &lt;element ref="{}multiplicity"/&gt;
 *         &lt;element ref="{}breakPoint" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "type",
    "sourceId",
    "destinationId",
    "multiplicity",
    "breakPoint"
})
@XmlRootElement(name = "arc")
public class Arc {

    protected short id;
    @XmlElement(required = true)
    protected String type;
    protected short sourceId;
    protected short destinationId;
    protected short multiplicity;
    protected BreakPoint breakPoint;

    /**
     * Gets the value of the id property.
     * 
     */
    public short getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(short value) {
        this.id = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the sourceId property.
     * 
     */
    public short getSourceId() {
        return sourceId;
    }

    /**
     * Sets the value of the sourceId property.
     * 
     */
    public void setSourceId(short value) {
        this.sourceId = value;
    }

    /**
     * Gets the value of the destinationId property.
     * 
     */
    public short getDestinationId() {
        return destinationId;
    }

    /**
     * Sets the value of the destinationId property.
     * 
     */
    public void setDestinationId(short value) {
        this.destinationId = value;
    }

    /**
     * Gets the value of the multiplicity property.
     * 
     */
    public short getMultiplicity() {
        return multiplicity;
    }

    /**
     * Sets the value of the multiplicity property.
     * 
     */
    public void setMultiplicity(short value) {
        this.multiplicity = value;
    }

    /**
     * Gets the value of the breakPoint property.
     * 
     * @return
     *     possible object is
     *     {@link BreakPoint }
     *     
     */
    public BreakPoint getBreakPoint() {
        return breakPoint;
    }

    /**
     * Sets the value of the breakPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link BreakPoint }
     *     
     */
    public void setBreakPoint(BreakPoint value) {
        this.breakPoint = value;
    }

}
