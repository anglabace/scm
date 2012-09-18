
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
 *         &lt;element name="glbCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="glbCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ds" type="{http://epicor.com/schemas}GlbCustomerDataSetType" minOccurs="0"/>
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
    "glbCompany",
    "glbCustNum",
    "ds",
    "callContextIn"
})
@XmlRootElement(name = "PreLinkGlbCustomer")
public class PreLinkGlbCustomer {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String glbCompany;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int glbCustNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected GlbCustomerDataSetType ds;
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
     * Gets the value of the glbCompany property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlbCompany() {
        return glbCompany;
    }

    /**
     * Sets the value of the glbCompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlbCompany(String value) {
        this.glbCompany = value;
    }

    /**
     * Gets the value of the glbCustNum property.
     * 
     */
    public int getGlbCustNum() {
        return glbCustNum;
    }

    /**
     * Sets the value of the glbCustNum property.
     * 
     */
    public void setGlbCustNum(int value) {
        this.glbCustNum = value;
    }

    /**
     * Gets the value of the ds property.
     * 
     * @return
     *     possible object is
     *     {@link GlbCustomerDataSetType }
     *     
     */
    public GlbCustomerDataSetType getDs() {
        return ds;
    }

    /**
     * Sets the value of the ds property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlbCustomerDataSetType }
     *     
     */
    public void setDs(GlbCustomerDataSetType value) {
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
