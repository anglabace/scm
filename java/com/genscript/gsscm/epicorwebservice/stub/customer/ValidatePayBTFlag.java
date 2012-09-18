
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
 *         &lt;element name="ipPayBTFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipValCust" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ipValShipTo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ipCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ipShipTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "ipPayBTFlag",
    "ipValCust",
    "ipValShipTo",
    "ipCustNum",
    "ipShipTo",
    "callContextIn"
})
@XmlRootElement(name = "ValidatePayBTFlag")
public class ValidatePayBTFlag {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ipPayBTFlag;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean ipValCust;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean ipValShipTo;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int ipCustNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ipShipTo;
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
     * Gets the value of the ipPayBTFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpPayBTFlag() {
        return ipPayBTFlag;
    }

    /**
     * Sets the value of the ipPayBTFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpPayBTFlag(String value) {
        this.ipPayBTFlag = value;
    }

    /**
     * Gets the value of the ipValCust property.
     * 
     */
    public boolean isIpValCust() {
        return ipValCust;
    }

    /**
     * Sets the value of the ipValCust property.
     * 
     */
    public void setIpValCust(boolean value) {
        this.ipValCust = value;
    }

    /**
     * Gets the value of the ipValShipTo property.
     * 
     */
    public boolean isIpValShipTo() {
        return ipValShipTo;
    }

    /**
     * Sets the value of the ipValShipTo property.
     * 
     */
    public void setIpValShipTo(boolean value) {
        this.ipValShipTo = value;
    }

    /**
     * Gets the value of the ipCustNum property.
     * 
     */
    public int getIpCustNum() {
        return ipCustNum;
    }

    /**
     * Sets the value of the ipCustNum property.
     * 
     */
    public void setIpCustNum(int value) {
        this.ipCustNum = value;
    }

    /**
     * Gets the value of the ipShipTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpShipTo() {
        return ipShipTo;
    }

    /**
     * Sets the value of the ipShipTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpShipTo(String value) {
        this.ipShipTo = value;
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
