//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.10.07 at 03:07:12 AM HST 
//


package edu.hawaii.myisern.researchers.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.hawaii.myisern.researchers.jaxb package. 
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

    private final static QName _Name_QNAME = new QName("", "Name");
    private final static QName _Email_QNAME = new QName("", "Email");
    private final static QName _BioStatement_QNAME = new QName("", "Bio-Statement");
    private final static QName _Organization_QNAME = new QName("", "Organization");
    private final static QName _PictureLink_QNAME = new QName("", "Picture-Link");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.hawaii.myisern.researchers.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Researcher }
     * 
     */
    public Researcher createResearcher() {
        return new Researcher();
    }

    /**
     * Create an instance of {@link Researchers }
     * 
     */
    public Researchers createResearchers() {
        return new Researchers();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Email")
    public JAXBElement<String> createEmail(String value) {
        return new JAXBElement<String>(_Email_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Bio-Statement")
    public JAXBElement<String> createBioStatement(String value) {
        return new JAXBElement<String>(_BioStatement_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Organization")
    public JAXBElement<String> createOrganization(String value) {
        return new JAXBElement<String>(_Organization_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Picture-Link")
    public JAXBElement<String> createPictureLink(String value) {
        return new JAXBElement<String>(_PictureLink_QNAME, String.class, null, value);
    }

}
