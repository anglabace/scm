
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
 *         &lt;element name="ipPONum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipOpenRel" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ipReNbr" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ipPreserve" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ipCalc" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "ipPONum",
    "ipOpenRel",
    "ipReNbr",
    "ipPreserve",
    "ipCalc",
    "callContextIn"
})
@XmlRootElement(name = "CopyOrder")
public class CopyOrder {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int orderNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ipPONum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean ipOpenRel;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean ipReNbr;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean ipPreserve;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean ipCalc;
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
     * Gets the value of the ipPONum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpPONum() {
        return ipPONum;
    }

    /**
     * Sets the value of the ipPONum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpPONum(String value) {
        this.ipPONum = value;
    }

    /**
     * Gets the value of the ipOpenRel property.
     * 
     */
    public boolean isIpOpenRel() {
        return ipOpenRel;
    }

    /**
     * Sets the value of the ipOpenRel property.
     * 
     */
    public void setIpOpenRel(boolean value) {
        this.ipOpenRel = value;
    }

    /**
     * Gets the value of the ipReNbr property.
     * 
     */
    public boolean isIpReNbr() {
        return ipReNbr;
    }

    /**
     * Sets the value of the ipReNbr property.
     * 
     */
    public void setIpReNbr(boolean value) {
        this.ipReNbr = value;
    }

    /**
     * Gets the value of the ipPreserve property.
     * 
     */
    public boolean isIpPreserve() {
        return ipPreserve;
    }

    /**
     * Sets the value of the ipPreserve property.
     * 
     */
    public void setIpPreserve(boolean value) {
        this.ipPreserve = value;
    }

    /**
     * Gets the value of the ipCalc property.
     * 
     */
    public boolean isIpCalc() {
        return ipCalc;
    }

    /**
     * Sets the value of the ipCalc property.
     * 
     */
    public void setIpCalc(boolean value) {
        this.ipCalc = value;
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
