//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.11 at 03:03:13 AM PKT 
//


package com.spring.pms.webservices;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.spring.pms.webservices package. 
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

    private final static QName _Bill_QNAME = new QName("http://webservices.pms.spring.com", "Bill");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.spring.pms.webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Bill }
     * 
     */
    public Bill createBill() {
        return new Bill();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Bill }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.pms.spring.com", name = "Bill")
    public JAXBElement<Bill> createBill(Bill value) {
        return new JAXBElement<Bill>(_Bill_QNAME, Bill.class, null, value);
    }

}
