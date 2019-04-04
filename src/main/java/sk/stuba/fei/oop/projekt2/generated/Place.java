
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
 *         &lt;element ref="{}x"/&gt;
 *         &lt;element ref="{}y"/&gt;
 *         &lt;element ref="{}label"/&gt;
 *         &lt;element ref="{}tokens"/&gt;
 *         &lt;element ref="{}static"/&gt;
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
    "x",
    "y",
    "label",
    "tokens",
    "_static"
})
@XmlRootElement(name = "place")
public class Place {

    protected short id;
    protected short x;
    protected short y;
    @XmlElement(required = true)
    protected String label;
    protected short tokens;
    @XmlElement(name = "static")
    protected boolean _static;

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
     * Gets the value of the x property.
     * 
     */
    public short getX() {
        return x;
    }

    /**
     * Sets the value of the x property.
     * 
     */
    public void setX(short value) {
        this.x = value;
    }

    /**
     * Gets the value of the y property.
     * 
     */
    public short getY() {
        return y;
    }

    /**
     * Sets the value of the y property.
     * 
     */
    public void setY(short value) {
        this.y = value;
    }

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the tokens property.
     * 
     */
    public short getTokens() {
        return tokens;
    }

    /**
     * Sets the value of the tokens property.
     * 
     */
    public void setTokens(short value) {
        this.tokens = value;
    }

    /**
     * Gets the value of the static property.
     * 
     */
    public boolean isStatic() {
        return _static;
    }

    /**
     * Sets the value of the static property.
     * 
     */
    public void setStatic(boolean value) {
        this._static = value;
    }

}
