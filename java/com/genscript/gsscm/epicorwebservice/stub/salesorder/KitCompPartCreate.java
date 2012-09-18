
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
 *         &lt;element name="orderLine" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="revisionNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smartString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newCustPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="responseAutoCrtPart" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "orderLine",
    "partNum",
    "revisionNum",
    "smartString",
    "newPartNum",
    "newCustPartNum",
    "responseAutoCrtPart",
    "callContextIn"
})
@XmlRootElement(name = "KitCompPartCreate")
public class KitCompPartCreate {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int orderNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int orderLine;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String partNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String revisionNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String smartString;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String newPartNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String newCustPartNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean responseAutoCrtPart;
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
     * Gets the value of the orderLine property.
     * 
     */
    public int getOrderLine() {
        return orderLine;
    }

    /**
     * Sets the value of the orderLine property.
     * 
     */
    public void setOrderLine(int value) {
        this.orderLine = value;
    }

    /**
     * Gets the value of the partNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartNum() {
        return partNum;
    }

    /**
     * Sets the value of the partNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartNum(String value) {
        this.partNum = value;
    }

    /**
     * Gets the value of the revisionNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevisionNum() {
        return revisionNum;
    }

    /**
     * Sets the value of the revisionNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevisionNum(String value) {
        this.revisionNum = value;
    }

    /**
     * Gets the value of the smartString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmartString() {
        return smartString;
    }

    /**
     * Sets the value of the smartString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmartString(String value) {
        this.smartString = value;
    }

    /**
     * Gets the value of the newPartNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewPartNum() {
        return newPartNum;
    }

    /**
     * Sets the value of the newPartNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewPartNum(String value) {
        this.newPartNum = value;
    }

    /**
     * Gets the value of the newCustPartNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewCustPartNum() {
        return newCustPartNum;
    }

    /**
     * Sets the value of the newCustPartNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewCustPartNum(String value) {
        this.newCustPartNum = value;
    }

    /**
     * Gets the value of the responseAutoCrtPart property.
     * 
     */
    public boolean isResponseAutoCrtPart() {
        return responseAutoCrtPart;
    }

    /**
     * Sets the value of the responseAutoCrtPart property.
     * 
     */
    public void setResponseAutoCrtPart(boolean value) {
        this.responseAutoCrtPart = value;
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
