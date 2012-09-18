
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
 *         &lt;element name="ds" type="{http://epicor.com/schemas}ETCAddrValidationDataSetType" minOccurs="0"/>
 *         &lt;element name="ds1" type="{http://epicor.com/schemas}CustomerDataSetType" minOccurs="0"/>
 *         &lt;element name="ipCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ipShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "ds",
    "ds1",
    "ipCustNum",
    "ipShipToNum",
    "callContextIn"
})
@XmlRootElement(name = "ShipToETCAfterAddrVal")
public class ShipToETCAfterAddrVal {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected ETCAddrValidationDataSetType ds;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CustomerDataSetType ds1;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int ipCustNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ipShipToNum;
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
     * Gets the value of the ds property.
     * 
     * @return
     *     possible object is
     *     {@link ETCAddrValidationDataSetType }
     *     
     */
    public ETCAddrValidationDataSetType getDs() {
        return ds;
    }

    /**
     * Sets the value of the ds property.
     * 
     * @param value
     *     allowed object is
     *     {@link ETCAddrValidationDataSetType }
     *     
     */
    public void setDs(ETCAddrValidationDataSetType value) {
        this.ds = value;
    }

    /**
     * Gets the value of the ds1 property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerDataSetType }
     *     
     */
    public CustomerDataSetType getDs1() {
        return ds1;
    }

    /**
     * Sets the value of the ds1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerDataSetType }
     *     
     */
    public void setDs1(CustomerDataSetType value) {
        this.ds1 = value;
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
     * Gets the value of the ipShipToNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpShipToNum() {
        return ipShipToNum;
    }

    /**
     * Sets the value of the ipShipToNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpShipToNum(String value) {
        this.ipShipToNum = value;
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
