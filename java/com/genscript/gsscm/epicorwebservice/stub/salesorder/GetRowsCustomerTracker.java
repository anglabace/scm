
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
 *         &lt;element name="whereClauseOrderHed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClauseOrderHedAttch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClauseOHOrderMsc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClauseOrderDtl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClauseOrderDtlAttch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClauseOrderMsc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClauseOrderRel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClauseOrderRepComm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClauseSelectedSerialNumbers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClauseSerialNumberSearch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClauseSNFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="absolutePage" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "whereClauseOrderHed",
    "whereClauseOrderHedAttch",
    "whereClauseOHOrderMsc",
    "whereClauseOrderDtl",
    "whereClauseOrderDtlAttch",
    "whereClauseOrderMsc",
    "whereClauseOrderRel",
    "whereClauseOrderRepComm",
    "whereClauseSelectedSerialNumbers",
    "whereClauseSerialNumberSearch",
    "whereClauseSNFormat",
    "pageSize",
    "absolutePage",
    "callContextIn"
})
@XmlRootElement(name = "GetRowsCustomerTracker")
public class GetRowsCustomerTracker {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClauseOrderHed;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClauseOrderHedAttch;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClauseOHOrderMsc;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClauseOrderDtl;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClauseOrderDtlAttch;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClauseOrderMsc;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClauseOrderRel;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClauseOrderRepComm;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClauseSelectedSerialNumbers;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClauseSerialNumberSearch;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClauseSNFormat;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int pageSize;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int absolutePage;
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
     * Gets the value of the whereClauseOrderHed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClauseOrderHed() {
        return whereClauseOrderHed;
    }

    /**
     * Sets the value of the whereClauseOrderHed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClauseOrderHed(String value) {
        this.whereClauseOrderHed = value;
    }

    /**
     * Gets the value of the whereClauseOrderHedAttch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClauseOrderHedAttch() {
        return whereClauseOrderHedAttch;
    }

    /**
     * Sets the value of the whereClauseOrderHedAttch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClauseOrderHedAttch(String value) {
        this.whereClauseOrderHedAttch = value;
    }

    /**
     * Gets the value of the whereClauseOHOrderMsc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClauseOHOrderMsc() {
        return whereClauseOHOrderMsc;
    }

    /**
     * Sets the value of the whereClauseOHOrderMsc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClauseOHOrderMsc(String value) {
        this.whereClauseOHOrderMsc = value;
    }

    /**
     * Gets the value of the whereClauseOrderDtl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClauseOrderDtl() {
        return whereClauseOrderDtl;
    }

    /**
     * Sets the value of the whereClauseOrderDtl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClauseOrderDtl(String value) {
        this.whereClauseOrderDtl = value;
    }

    /**
     * Gets the value of the whereClauseOrderDtlAttch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClauseOrderDtlAttch() {
        return whereClauseOrderDtlAttch;
    }

    /**
     * Sets the value of the whereClauseOrderDtlAttch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClauseOrderDtlAttch(String value) {
        this.whereClauseOrderDtlAttch = value;
    }

    /**
     * Gets the value of the whereClauseOrderMsc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClauseOrderMsc() {
        return whereClauseOrderMsc;
    }

    /**
     * Sets the value of the whereClauseOrderMsc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClauseOrderMsc(String value) {
        this.whereClauseOrderMsc = value;
    }

    /**
     * Gets the value of the whereClauseOrderRel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClauseOrderRel() {
        return whereClauseOrderRel;
    }

    /**
     * Sets the value of the whereClauseOrderRel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClauseOrderRel(String value) {
        this.whereClauseOrderRel = value;
    }

    /**
     * Gets the value of the whereClauseOrderRepComm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClauseOrderRepComm() {
        return whereClauseOrderRepComm;
    }

    /**
     * Sets the value of the whereClauseOrderRepComm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClauseOrderRepComm(String value) {
        this.whereClauseOrderRepComm = value;
    }

    /**
     * Gets the value of the whereClauseSelectedSerialNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClauseSelectedSerialNumbers() {
        return whereClauseSelectedSerialNumbers;
    }

    /**
     * Sets the value of the whereClauseSelectedSerialNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClauseSelectedSerialNumbers(String value) {
        this.whereClauseSelectedSerialNumbers = value;
    }

    /**
     * Gets the value of the whereClauseSerialNumberSearch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClauseSerialNumberSearch() {
        return whereClauseSerialNumberSearch;
    }

    /**
     * Sets the value of the whereClauseSerialNumberSearch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClauseSerialNumberSearch(String value) {
        this.whereClauseSerialNumberSearch = value;
    }

    /**
     * Gets the value of the whereClauseSNFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClauseSNFormat() {
        return whereClauseSNFormat;
    }

    /**
     * Sets the value of the whereClauseSNFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClauseSNFormat(String value) {
        this.whereClauseSNFormat = value;
    }

    /**
     * Gets the value of the pageSize property.
     * 
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the value of the pageSize property.
     * 
     */
    public void setPageSize(int value) {
        this.pageSize = value;
    }

    /**
     * Gets the value of the absolutePage property.
     * 
     */
    public int getAbsolutePage() {
        return absolutePage;
    }

    /**
     * Sets the value of the absolutePage property.
     * 
     */
    public void setAbsolutePage(int value) {
        this.absolutePage = value;
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
