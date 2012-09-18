
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
 *         &lt;element name="ipOrderNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ipLineNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ipRelNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ipTaxCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipRateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ds" type="{http://epicor.com/schemas}SalesOrderDataSetType" minOccurs="0"/>
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
    "ipOrderNum",
    "ipLineNum",
    "ipRelNum",
    "ipTaxCode",
    "ipRateCode",
    "ds",
    "callContextIn"
})
@XmlRootElement(name = "ChangeManualTaxCalc")
public class ChangeManualTaxCalc {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int ipOrderNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int ipLineNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int ipRelNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ipTaxCode;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ipRateCode;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected SalesOrderDataSetType ds;
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
     * Gets the value of the ipOrderNum property.
     * 
     */
    public int getIpOrderNum() {
        return ipOrderNum;
    }

    /**
     * Sets the value of the ipOrderNum property.
     * 
     */
    public void setIpOrderNum(int value) {
        this.ipOrderNum = value;
    }

    /**
     * Gets the value of the ipLineNum property.
     * 
     */
    public int getIpLineNum() {
        return ipLineNum;
    }

    /**
     * Sets the value of the ipLineNum property.
     * 
     */
    public void setIpLineNum(int value) {
        this.ipLineNum = value;
    }

    /**
     * Gets the value of the ipRelNum property.
     * 
     */
    public int getIpRelNum() {
        return ipRelNum;
    }

    /**
     * Sets the value of the ipRelNum property.
     * 
     */
    public void setIpRelNum(int value) {
        this.ipRelNum = value;
    }

    /**
     * Gets the value of the ipTaxCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpTaxCode() {
        return ipTaxCode;
    }

    /**
     * Sets the value of the ipTaxCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpTaxCode(String value) {
        this.ipTaxCode = value;
    }

    /**
     * Gets the value of the ipRateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpRateCode() {
        return ipRateCode;
    }

    /**
     * Sets the value of the ipRateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpRateCode(String value) {
        this.ipRateCode = value;
    }

    /**
     * Gets the value of the ds property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public SalesOrderDataSetType getDs() {
        return ds;
    }

    /**
     * Sets the value of the ds property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public void setDs(SalesOrderDataSetType value) {
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
