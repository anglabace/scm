
package com.genscript.gsscm.epicorwebservice.stub.salesorder;

import java.math.BigDecimal;
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
 *         &lt;element name="ds" type="{http://epicor.com/schemas}SalesOrderDataSetType" minOccurs="0"/>
 *         &lt;element name="negInvTest" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="chgDtlWhse" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="suppressUserPrompts" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="pcPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pcWhseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pcBinNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pcLotNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pcDimCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pdDimConvFactor" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="pdSellingQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
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
    "ds",
    "negInvTest",
    "chgDtlWhse",
    "suppressUserPrompts",
    "pcPartNum",
    "pcWhseCode",
    "pcBinNum",
    "pcLotNum",
    "pcDimCode",
    "pdDimConvFactor",
    "pdSellingQuantity",
    "callContextIn"
})
@XmlRootElement(name = "ChangeWhseCodeMaster")
public class ChangeWhseCodeMaster {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected SalesOrderDataSetType ds;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean negInvTest;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean chgDtlWhse;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean suppressUserPrompts;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String pcPartNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String pcWhseCode;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String pcBinNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String pcLotNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String pcDimCode;
    @XmlElement(namespace = "http://epicor.com/webservices/", required = true)
    protected BigDecimal pdDimConvFactor;
    @XmlElement(namespace = "http://epicor.com/webservices/", required = true)
    protected BigDecimal pdSellingQuantity;
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
     * Gets the value of the negInvTest property.
     * 
     */
    public boolean isNegInvTest() {
        return negInvTest;
    }

    /**
     * Sets the value of the negInvTest property.
     * 
     */
    public void setNegInvTest(boolean value) {
        this.negInvTest = value;
    }

    /**
     * Gets the value of the chgDtlWhse property.
     * 
     */
    public boolean isChgDtlWhse() {
        return chgDtlWhse;
    }

    /**
     * Sets the value of the chgDtlWhse property.
     * 
     */
    public void setChgDtlWhse(boolean value) {
        this.chgDtlWhse = value;
    }

    /**
     * Gets the value of the suppressUserPrompts property.
     * 
     */
    public boolean isSuppressUserPrompts() {
        return suppressUserPrompts;
    }

    /**
     * Sets the value of the suppressUserPrompts property.
     * 
     */
    public void setSuppressUserPrompts(boolean value) {
        this.suppressUserPrompts = value;
    }

    /**
     * Gets the value of the pcPartNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcPartNum() {
        return pcPartNum;
    }

    /**
     * Sets the value of the pcPartNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcPartNum(String value) {
        this.pcPartNum = value;
    }

    /**
     * Gets the value of the pcWhseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcWhseCode() {
        return pcWhseCode;
    }

    /**
     * Sets the value of the pcWhseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcWhseCode(String value) {
        this.pcWhseCode = value;
    }

    /**
     * Gets the value of the pcBinNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcBinNum() {
        return pcBinNum;
    }

    /**
     * Sets the value of the pcBinNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcBinNum(String value) {
        this.pcBinNum = value;
    }

    /**
     * Gets the value of the pcLotNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcLotNum() {
        return pcLotNum;
    }

    /**
     * Sets the value of the pcLotNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcLotNum(String value) {
        this.pcLotNum = value;
    }

    /**
     * Gets the value of the pcDimCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcDimCode() {
        return pcDimCode;
    }

    /**
     * Sets the value of the pcDimCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcDimCode(String value) {
        this.pcDimCode = value;
    }

    /**
     * Gets the value of the pdDimConvFactor property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPdDimConvFactor() {
        return pdDimConvFactor;
    }

    /**
     * Sets the value of the pdDimConvFactor property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPdDimConvFactor(BigDecimal value) {
        this.pdDimConvFactor = value;
    }

    /**
     * Gets the value of the pdSellingQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPdSellingQuantity() {
        return pdSellingQuantity;
    }

    /**
     * Sets the value of the pdSellingQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPdSellingQuantity(BigDecimal value) {
        this.pdSellingQuantity = value;
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
