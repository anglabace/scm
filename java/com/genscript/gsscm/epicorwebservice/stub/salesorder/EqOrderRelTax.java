
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
 *         &lt;element name="pcCurComp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="piOrderNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="piOrderLine" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="piOrderRelNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pcCalledFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="plIsNewRecord" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "pcCurComp",
    "piOrderNum",
    "piOrderLine",
    "piOrderRelNum",
    "pcCalledFrom",
    "plIsNewRecord",
    "callContextIn"
})
@XmlRootElement(name = "eqOrderRelTax")
public class EqOrderRelTax {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String pcCurComp;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int piOrderNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int piOrderLine;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int piOrderRelNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String pcCalledFrom;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean plIsNewRecord;
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
     * Gets the value of the pcCurComp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcCurComp() {
        return pcCurComp;
    }

    /**
     * Sets the value of the pcCurComp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcCurComp(String value) {
        this.pcCurComp = value;
    }

    /**
     * Gets the value of the piOrderNum property.
     * 
     */
    public int getPiOrderNum() {
        return piOrderNum;
    }

    /**
     * Sets the value of the piOrderNum property.
     * 
     */
    public void setPiOrderNum(int value) {
        this.piOrderNum = value;
    }

    /**
     * Gets the value of the piOrderLine property.
     * 
     */
    public int getPiOrderLine() {
        return piOrderLine;
    }

    /**
     * Sets the value of the piOrderLine property.
     * 
     */
    public void setPiOrderLine(int value) {
        this.piOrderLine = value;
    }

    /**
     * Gets the value of the piOrderRelNum property.
     * 
     */
    public int getPiOrderRelNum() {
        return piOrderRelNum;
    }

    /**
     * Sets the value of the piOrderRelNum property.
     * 
     */
    public void setPiOrderRelNum(int value) {
        this.piOrderRelNum = value;
    }

    /**
     * Gets the value of the pcCalledFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcCalledFrom() {
        return pcCalledFrom;
    }

    /**
     * Sets the value of the pcCalledFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcCalledFrom(String value) {
        this.pcCalledFrom = value;
    }

    /**
     * Gets the value of the plIsNewRecord property.
     * 
     */
    public boolean isPlIsNewRecord() {
        return plIsNewRecord;
    }

    /**
     * Sets the value of the plIsNewRecord property.
     * 
     */
    public void setPlIsNewRecord(boolean value) {
        this.plIsNewRecord = value;
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
