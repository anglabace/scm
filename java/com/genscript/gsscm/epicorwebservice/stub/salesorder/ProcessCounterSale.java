
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
 *         &lt;element name="iOrderNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lGeneratePackingSlip" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lGenerateInvoice" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lReadyToShip" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "iOrderNum",
    "lGeneratePackingSlip",
    "lGenerateInvoice",
    "lReadyToShip",
    "ds",
    "callContextIn"
})
@XmlRootElement(name = "ProcessCounterSale")
public class ProcessCounterSale {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int iOrderNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean lGeneratePackingSlip;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean lGenerateInvoice;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean lReadyToShip;
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
     * Gets the value of the iOrderNum property.
     * 
     */
    public int getIOrderNum() {
        return iOrderNum;
    }

    /**
     * Sets the value of the iOrderNum property.
     * 
     */
    public void setIOrderNum(int value) {
        this.iOrderNum = value;
    }

    /**
     * Gets the value of the lGeneratePackingSlip property.
     * 
     */
    public boolean isLGeneratePackingSlip() {
        return lGeneratePackingSlip;
    }

    /**
     * Sets the value of the lGeneratePackingSlip property.
     * 
     */
    public void setLGeneratePackingSlip(boolean value) {
        this.lGeneratePackingSlip = value;
    }

    /**
     * Gets the value of the lGenerateInvoice property.
     * 
     */
    public boolean isLGenerateInvoice() {
        return lGenerateInvoice;
    }

    /**
     * Sets the value of the lGenerateInvoice property.
     * 
     */
    public void setLGenerateInvoice(boolean value) {
        this.lGenerateInvoice = value;
    }

    /**
     * Gets the value of the lReadyToShip property.
     * 
     */
    public boolean isLReadyToShip() {
        return lReadyToShip;
    }

    /**
     * Sets the value of the lReadyToShip property.
     * 
     */
    public void setLReadyToShip(boolean value) {
        this.lReadyToShip = value;
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
