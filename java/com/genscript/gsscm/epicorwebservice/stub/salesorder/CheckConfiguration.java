
package com.genscript.gsscm.epicorwebservice.stub.salesorder;

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
 *         &lt;element name="CompanyID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="orderLine" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sourcePart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceRev" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="findRevision" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="callContextIn" type="{http://epicor.com/schemas}CallContextDataSetType" minOccurs="0"/>
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
    "companyID",
    "orderNum",
    "orderLine",
    "sourcePart",
    "sourceRev",
    "findRevision",
    "callContextIn"
})
@XmlRootElement(name = "CheckConfiguration")
public class CheckConfiguration {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int orderNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int orderLine;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String sourcePart;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String sourceRev;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean findRevision;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextIn;

    /**
     * Gets the value of the companyID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyID() {
        return companyID;
    }

    /**
     * Sets the value of the companyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyID(String value) {
        this.companyID = value;
    }

    /**
     * Gets the value of the orderNum property.
     * 
     */
    public int getOrderNum() {
        return orderNum;
    }

    /**
     * Sets the value of the orderNum property.
     * 
     */
    public void setOrderNum(int value) {
        this.orderNum = value;
    }

    /**
     * Gets the value of the orderLine property.
     * 
     */
    public int getOrderLine() {
        return orderLine;
    }

    /**
     * Sets the value of the orderLine property.
     * 
     */
    public void setOrderLine(int value) {
        this.orderLine = value;
    }

    /**
     * Gets the value of the sourcePart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourcePart() {
        return sourcePart;
    }

    /**
     * Sets the value of the sourcePart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourcePart(String value) {
        this.sourcePart = value;
    }

    /**
     * Gets the value of the sourceRev property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceRev() {
        return sourceRev;
    }

    /**
     * Sets the value of the sourceRev property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceRev(String value) {
        this.sourceRev = value;
    }

    /**
     * Gets the value of the findRevision property.
     * 
     */
    public boolean isFindRevision() {
        return findRevision;
    }

    /**
     * Sets the value of the findRevision property.
     * 
     */
    public void setFindRevision(boolean value) {
        this.findRevision = value;
    }

    /**
     * Gets the value of the callContextIn property.
     * 
     * @return
     *     possible object is
     *     {@link CallContextDataSetType }
     *     
     */
    public CallContextDataSetType getCallContextIn() {
        return callContextIn;
    }

    /**
     * Sets the value of the callContextIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link CallContextDataSetType }
     *     
     */
    public void setCallContextIn(CallContextDataSetType value) {
        this.callContextIn = value;
    }

}
