
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
 *         &lt;element name="vCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="vCustType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vBTName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vBTAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vBTAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vBTAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vBTCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vBTState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "vCustNum",
    "vCustType",
    "vName",
    "vAddress1",
    "vAddress2",
    "vAddress3",
    "vCity",
    "vState",
    "vbtName",
    "vbtAddress1",
    "vbtAddress2",
    "vbtAddress3",
    "vbtCity",
    "vbtState",
    "callContextIn"
})
@XmlRootElement(name = "CheckEFFieldLength")
public class CheckEFFieldLength {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int vCustNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String vCustType;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String vName;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String vAddress1;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String vAddress2;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String vAddress3;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String vCity;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String vState;
    @XmlElement(name = "vBTName", namespace = "http://epicor.com/webservices/")
    protected String vbtName;
    @XmlElement(name = "vBTAddress1", namespace = "http://epicor.com/webservices/")
    protected String vbtAddress1;
    @XmlElement(name = "vBTAddress2", namespace = "http://epicor.com/webservices/")
    protected String vbtAddress2;
    @XmlElement(name = "vBTAddress3", namespace = "http://epicor.com/webservices/")
    protected String vbtAddress3;
    @XmlElement(name = "vBTCity", namespace = "http://epicor.com/webservices/")
    protected String vbtCity;
    @XmlElement(name = "vBTState", namespace = "http://epicor.com/webservices/")
    protected String vbtState;
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
     * Gets the value of the vCustNum property.
     * 
     */
    public int getVCustNum() {
        return vCustNum;
    }

    /**
     * Sets the value of the vCustNum property.
     * 
     */
    public void setVCustNum(int value) {
        this.vCustNum = value;
    }

    /**
     * Gets the value of the vCustType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCustType() {
        return vCustType;
    }

    /**
     * Sets the value of the vCustType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVCustType(String value) {
        this.vCustType = value;
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
     * Gets the value of the vAddress2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVAddress2() {
        return vAddress2;
    }

    /**
     * Sets the value of the vAddress2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVAddress2(String value) {
        this.vAddress2 = value;
    }

    /**
     * Gets the value of the vAddress3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVAddress3() {
        return vAddress3;
    }

    /**
     * Sets the value of the vAddress3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVAddress3(String value) {
        this.vAddress3 = value;
    }

    /**
     * Gets the value of the vCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVCity() {
        return vCity;
    }

    /**
     * Sets the value of the vCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVCity(String value) {
        this.vCity = value;
    }

    /**
     * Gets the value of the vState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVState() {
        return vState;
    }

    /**
     * Sets the value of the vState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVState(String value) {
        this.vState = value;
    }

    /**
     * Gets the value of the vbtName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBTName() {
        return vbtName;
    }

    /**
     * Sets the value of the vbtName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVBTName(String value) {
        this.vbtName = value;
    }

    /**
     * Gets the value of the vbtAddress1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBTAddress1() {
        return vbtAddress1;
    }

    /**
     * Sets the value of the vbtAddress1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVBTAddress1(String value) {
        this.vbtAddress1 = value;
    }

    /**
     * Gets the value of the vbtAddress2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBTAddress2() {
        return vbtAddress2;
    }

    /**
     * Sets the value of the vbtAddress2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVBTAddress2(String value) {
        this.vbtAddress2 = value;
    }

    /**
     * Gets the value of the vbtAddress3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBTAddress3() {
        return vbtAddress3;
    }

    /**
     * Sets the value of the vbtAddress3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVBTAddress3(String value) {
        this.vbtAddress3 = value;
    }

    /**
     * Gets the value of the vbtCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBTCity() {
        return vbtCity;
    }

    /**
     * Sets the value of the vbtCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVBTCity(String value) {
        this.vbtCity = value;
    }

    /**
     * Gets the value of the vbtState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBTState() {
        return vbtState;
    }

    /**
     * Sets the value of the vbtState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVBTState(String value) {
        this.vbtState = value;
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
