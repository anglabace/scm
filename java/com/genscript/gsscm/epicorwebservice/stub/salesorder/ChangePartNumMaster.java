
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
 *         &lt;element name="partNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lSubstitutePartExist" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lIsPhantom" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="uomCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rowType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesKitView" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="removeKitComponents" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="suppressUserPrompts" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="getPartXRefInfo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="checkPartRevisionChange" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="checkChangeKitParent" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "partNum",
    "lSubstitutePartExist",
    "lIsPhantom",
    "uomCode",
    "sysRowID",
    "rowType",
    "salesKitView",
    "removeKitComponents",
    "suppressUserPrompts",
    "getPartXRefInfo",
    "checkPartRevisionChange",
    "checkChangeKitParent",
    "ds",
    "callContextIn"
})
@XmlRootElement(name = "ChangePartNumMaster")
public class ChangePartNumMaster {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String partNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean lSubstitutePartExist;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean lIsPhantom;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String uomCode;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String sysRowID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String rowType;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean salesKitView;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean removeKitComponents;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean suppressUserPrompts;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean getPartXRefInfo;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean checkPartRevisionChange;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean checkChangeKitParent;
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
     * Gets the value of the lSubstitutePartExist property.
     * 
     */
    public boolean isLSubstitutePartExist() {
        return lSubstitutePartExist;
    }

    /**
     * Sets the value of the lSubstitutePartExist property.
     * 
     */
    public void setLSubstitutePartExist(boolean value) {
        this.lSubstitutePartExist = value;
    }

    /**
     * Gets the value of the lIsPhantom property.
     * 
     */
    public boolean isLIsPhantom() {
        return lIsPhantom;
    }

    /**
     * Sets the value of the lIsPhantom property.
     * 
     */
    public void setLIsPhantom(boolean value) {
        this.lIsPhantom = value;
    }

    /**
     * Gets the value of the uomCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUomCode() {
        return uomCode;
    }

    /**
     * Sets the value of the uomCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUomCode(String value) {
        this.uomCode = value;
    }

    /**
     * Gets the value of the sysRowID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysRowID() {
        return sysRowID;
    }

    /**
     * Sets the value of the sysRowID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysRowID(String value) {
        this.sysRowID = value;
    }

    /**
     * Gets the value of the rowType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRowType() {
        return rowType;
    }

    /**
     * Sets the value of the rowType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRowType(String value) {
        this.rowType = value;
    }

    /**
     * Gets the value of the salesKitView property.
     * 
     */
    public boolean isSalesKitView() {
        return salesKitView;
    }

    /**
     * Sets the value of the salesKitView property.
     * 
     */
    public void setSalesKitView(boolean value) {
        this.salesKitView = value;
    }

    /**
     * Gets the value of the removeKitComponents property.
     * 
     */
    public boolean isRemoveKitComponents() {
        return removeKitComponents;
    }

    /**
     * Sets the value of the removeKitComponents property.
     * 
     */
    public void setRemoveKitComponents(boolean value) {
        this.removeKitComponents = value;
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
     * Gets the value of the getPartXRefInfo property.
     * 
     */
    public boolean isGetPartXRefInfo() {
        return getPartXRefInfo;
    }

    /**
     * Sets the value of the getPartXRefInfo property.
     * 
     */
    public void setGetPartXRefInfo(boolean value) {
        this.getPartXRefInfo = value;
    }

    /**
     * Gets the value of the checkPartRevisionChange property.
     * 
     */
    public boolean isCheckPartRevisionChange() {
        return checkPartRevisionChange;
    }

    /**
     * Sets the value of the checkPartRevisionChange property.
     * 
     */
    public void setCheckPartRevisionChange(boolean value) {
        this.checkPartRevisionChange = value;
    }

    /**
     * Gets the value of the checkChangeKitParent property.
     * 
     */
    public boolean isCheckChangeKitParent() {
        return checkChangeKitParent;
    }

    /**
     * Sets the value of the checkChangeKitParent property.
     * 
     */
    public void setCheckChangeKitParent(boolean value) {
        this.checkChangeKitParent = value;
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
