//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.10.07 at 03:07:12 AM HST 
//


package edu.hawaii.myisern.researchers.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Name"/>
 *         &lt;element ref="{}Organization"/>
 *         &lt;element ref="{}Email"/>
 *         &lt;element ref="{}Picture-Link"/>
 *         &lt;element ref="{}Bio-Statement"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "organization",
    "email",
    "pictureLink",
    "bioStatement"
})
@XmlRootElement(name = "Researcher")
public class Researcher {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Organization", required = true)
    protected String organization;
    @XmlElement(name = "Email", required = true)
    protected String email;
    @XmlElement(name = "Picture-Link", required = true)
    protected String pictureLink;
    @XmlElement(name = "Bio-Statement", required = true)
    protected String bioStatement;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the organization property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Sets the value of the organization property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganization(String value) {
        this.organization = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the pictureLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPictureLink() {
        return pictureLink;
    }

    /**
     * Sets the value of the pictureLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPictureLink(String value) {
        this.pictureLink = value;
    }

    /**
     * Gets the value of the bioStatement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBioStatement() {
        return bioStatement;
    }

    /**
     * Sets the value of the bioStatement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBioStatement(String value) {
        this.bioStatement = value;
    }

}
