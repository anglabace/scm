
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
 *         &lt;element name="vName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vRowid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "vName",
    "vRowid",
    "vAddress1",
    "vZip",
    "callContextIn"
})
@XmlRootElement(name = "CheckDupCustomer")
public class CheckDupCustomer {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String vName;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String vRowid;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String vAddress1;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String vZip;
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
     * Gets the value of the vName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVName() {
        return vName;
    }

    /**
     * Sets the value of the vName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVName(String value) {
        this.vName = value;
    }

    /**
     * Gets the value of the vRowid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVRowid() {
        return vRowid;
    }

    /**
     * Sets the value of the vRowid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVRowid(String value) {
        this.vRowid = value;
    }

    /**
     * Gets the value of the vAddress1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVAddress1() {
        return vAddress1;
    }

    /**
     * Sets the value of the vAddress1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVAddress1(String value) {
        this.vAddress1 = value;
    }

    /**
     * Gets the value of the vZip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVZip() {
        return vZip;
    }

    /**
     * Sets the value of the vZip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVZip(String value) {
        this.vZip = value;
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
