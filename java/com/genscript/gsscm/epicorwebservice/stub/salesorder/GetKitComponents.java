
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
 *         &lt;element name="iPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iRevisionNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iAltMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iTargetAsm" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="orderNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="orderLine" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="iUseMethodForParts" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="regenerateKit" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="errorMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "iPartNum",
    "iRevisionNum",
    "iAltMethod",
    "iTargetAsm",
    "orderNum",
    "orderLine",
    "iUseMethodForParts",
    "regenerateKit",
    "errorMsg",
    "ds",
    "callContextIn"
})
@XmlRootElement(name = "GetKitComponents")
public class GetKitComponents {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String iPartNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String iRevisionNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String iAltMethod;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int iTargetAsm;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int orderNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int orderLine;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean iUseMethodForParts;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean regenerateKit;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String errorMsg;
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
     * Gets the value of the iPartNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPartNum() {
        return iPartNum;
    }

    /**
     * Sets the value of the iPartNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPartNum(String value) {
        this.iPartNum = value;
    }

    /**
     * Gets the value of the iRevisionNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIRevisionNum() {
        return iRevisionNum;
    }

    /**
     * Sets the value of the iRevisionNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIRevisionNum(String value) {
        this.iRevisionNum = value;
    }

    /**
     * Gets the value of the iAltMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIAltMethod() {
        return iAltMethod;
    }

    /**
     * Sets the value of the iAltMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIAltMethod(String value) {
        this.iAltMethod = value;
    }

    /**
     * Gets the value of the iTargetAsm property.
     * 
     */
    public int getITargetAsm() {
        return iTargetAsm;
    }

    /**
     * Sets the value of the iTargetAsm property.
     * 
     */
    public void setITargetAsm(int value) {
        this.iTargetAsm = value;
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
     * Gets the value of the iUseMethodForParts property.
     * 
     */
    public boolean isIUseMethodForParts() {
        return iUseMethodForParts;
    }

    /**
     * Sets the value of the iUseMethodForParts property.
     * 
     */
    public void setIUseMethodForParts(boolean value) {
        this.iUseMethodForParts = value;
    }

    /**
     * Gets the value of the regenerateKit property.
     * 
     */
    public boolean isRegenerateKit() {
        return regenerateKit;
    }

    /**
     * Sets the value of the regenerateKit property.
     * 
     */
    public void setRegenerateKit(boolean value) {
        this.regenerateKit = value;
    }

    /**
     * Gets the value of the errorMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Sets the value of the errorMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMsg(String value) {
        this.errorMsg = value;
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
