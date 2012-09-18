
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
 *         &lt;element name="ds" type="{http://epicor.com/schemas}CustomerDataSetType" minOccurs="0"/>
 *         &lt;element name="iProposedTaxRgnCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="iShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "iProposedTaxRgnCode",
    "iCustNum",
    "iShipToNum",
    "callContextIn"
})
@XmlRootElement(name = "OnChangeShipToTaxRegion")
public class OnChangeShipToTaxRegion {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CustomerDataSetType ds;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String iProposedTaxRgnCode;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int iCustNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String iShipToNum;
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
     * Gets the value of the iProposedTaxRgnCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIProposedTaxRgnCode() {
        return iProposedTaxRgnCode;
    }

    /**
     * Sets the value of the iProposedTaxRgnCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIProposedTaxRgnCode(String value) {
        this.iProposedTaxRgnCode = value;
    }

    /**
     * Gets the value of the iCustNum property.
     * 
     */
    public int getICustNum() {
        return iCustNum;
    }

    /**
     * Sets the value of the iCustNum property.
     * 
     */
    public void setICustNum(int value) {
        this.iCustNum = value;
    }

    /**
     * Gets the value of the iShipToNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIShipToNum() {
        return iShipToNum;
    }

    /**
     * Sets the value of the iShipToNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIShipToNum(String value) {
        this.iShipToNum = value;
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
