
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
 *         &lt;element name="ipOrderLine" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ipQuoteNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ipQuoteLine" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ipQtyNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "ipOrderLine",
    "ipQuoteNum",
    "ipQuoteLine",
    "ipQtyNum",
    "ds",
    "callContextIn"
})
@XmlRootElement(name = "CreateLineMiscChargesFromQuote")
public class CreateLineMiscChargesFromQuote {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int ipOrderNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int ipOrderLine;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int ipQuoteNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int ipQuoteLine;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int ipQtyNum;
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
     * Gets the value of the ipOrderLine property.
     * 
     */
    public int getIpOrderLine() {
        return ipOrderLine;
    }

    /**
     * Sets the value of the ipOrderLine property.
     * 
     */
    public void setIpOrderLine(int value) {
        this.ipOrderLine = value;
    }

    /**
     * Gets the value of the ipQuoteNum property.
     * 
     */
    public int getIpQuoteNum() {
        return ipQuoteNum;
    }

    /**
     * Sets the value of the ipQuoteNum property.
     * 
     */
    public void setIpQuoteNum(int value) {
        this.ipQuoteNum = value;
    }

    /**
     * Gets the value of the ipQuoteLine property.
     * 
     */
    public int getIpQuoteLine() {
        return ipQuoteLine;
    }

    /**
     * Sets the value of the ipQuoteLine property.
     * 
     */
    public void setIpQuoteLine(int value) {
        this.ipQuoteLine = value;
    }

    /**
     * Gets the value of the ipQtyNum property.
     * 
     */
    public int getIpQtyNum() {
        return ipQtyNum;
    }

    /**
     * Sets the value of the ipQtyNum property.
     * 
     */
    public void setIpQtyNum(int value) {
        this.ipQtyNum = value;
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
