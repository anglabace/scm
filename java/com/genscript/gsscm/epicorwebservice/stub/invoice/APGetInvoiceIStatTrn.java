
package com.genscript.gsscm.epicorwebservice.stub.invoice;

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
 *         &lt;element name="inVendorNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="inInvoiceNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inInvoiceLine" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="forTracker" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "inVendorNum",
    "inInvoiceNum",
    "inInvoiceLine",
    "forTracker",
    "callContextIn"
})
@XmlRootElement(name = "APGetInvoiceIStatTrn")
public class APGetInvoiceIStatTrn {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int inVendorNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String inInvoiceNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int inInvoiceLine;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean forTracker;
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
     * Gets the value of the inVendorNum property.
     * 
     */
    public int getInVendorNum() {
        return inVendorNum;
    }

    /**
     * Sets the value of the inVendorNum property.
     * 
     */
    public void setInVendorNum(int value) {
        this.inVendorNum = value;
    }

    /**
     * Gets the value of the inInvoiceNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInInvoiceNum() {
        return inInvoiceNum;
    }

    /**
     * Sets the value of the inInvoiceNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInInvoiceNum(String value) {
        this.inInvoiceNum = value;
    }

    /**
     * Gets the value of the inInvoiceLine property.
     * 
     */
    public int getInInvoiceLine() {
        return inInvoiceLine;
    }

    /**
     * Sets the value of the inInvoiceLine property.
     * 
     */
    public void setInInvoiceLine(int value) {
        this.inInvoiceLine = value;
    }

    /**
     * Gets the value of the forTracker property.
     * 
     */
    public boolean isForTracker() {
        return forTracker;
    }

    /**
     * Sets the value of the forTracker property.
     * 
     */
    public void setForTracker(boolean value) {
        this.forTracker = value;
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
