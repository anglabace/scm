
package com.genscript.gsscm.epicorwebservice.stub.customer;

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
 *         &lt;element name="ipQVEnable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ipUpdCustUPS" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ipUPDShipToUPS" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ds" type="{http://epicor.com/schemas}CustomerDataSetType" minOccurs="0"/>
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
    "ipQVEnable",
    "ipUpdCustUPS",
    "ipUPDShipToUPS",
    "ds",
    "callContextIn"
})
@XmlRootElement(name = "SetUPSQVEnable")
public class SetUPSQVEnable {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean ipQVEnable;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean ipUpdCustUPS;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean ipUPDShipToUPS;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CustomerDataSetType ds;
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
     * Gets the value of the ipQVEnable property.
     * 
     */
    public boolean isIpQVEnable() {
        return ipQVEnable;
    }

    /**
     * Sets the value of the ipQVEnable property.
     * 
     */
    public void setIpQVEnable(boolean value) {
        this.ipQVEnable = value;
    }

    /**
     * Gets the value of the ipUpdCustUPS property.
     * 
     */
    public boolean isIpUpdCustUPS() {
        return ipUpdCustUPS;
    }

    /**
     * Sets the value of the ipUpdCustUPS property.
     * 
     */
    public void setIpUpdCustUPS(boolean value) {
        this.ipUpdCustUPS = value;
    }

    /**
     * Gets the value of the ipUPDShipToUPS property.
     * 
     */
    public boolean isIpUPDShipToUPS() {
        return ipUPDShipToUPS;
    }

    /**
     * Sets the value of the ipUPDShipToUPS property.
     * 
     */
    public void setIpUPDShipToUPS(boolean value) {
        this.ipUPDShipToUPS = value;
    }

    /**
     * Gets the value of the ds property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerDataSetType }
     *     
     */
    public CustomerDataSetType getDs() {
        return ds;
    }

    /**
     * Sets the value of the ds property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerDataSetType }
     *     
     */
    public void setDs(CustomerDataSetType value) {
        this.ds = value;
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
