
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
 *         &lt;element name="ChangePartNumMasterResult" type="{http://epicor.com/schemas}SalesOrderDataSetType" minOccurs="0"/>
 *         &lt;element name="partNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lSubstitutePartExist" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lIsPhantom" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="uomCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cDeleteComponentsMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="questionString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cWarningMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="multipleMatch" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="promptToExplodeBOM" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cConfigPartMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cSubPartMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="explodeBOMerrMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cMsgType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="multiSubsAvail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="runOutQtyAvail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="callContextOut" type="{http://epicor.com/schemas}CallContextDataSetType" minOccurs="0"/>
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
    "changePartNumMasterResult",
    "partNum",
    "lSubstitutePartExist",
    "lIsPhantom",
    "uomCode",
    "cDeleteComponentsMessage",
    "questionString",
    "cWarningMessage",
    "multipleMatch",
    "promptToExplodeBOM",
    "cConfigPartMessage",
    "cSubPartMessage",
    "explodeBOMerrMessage",
    "cMsgType",
    "multiSubsAvail",
    "runOutQtyAvail",
    "callContextOut"
})
@XmlRootElement(name = "ChangePartNumMasterResponse")
public class ChangePartNumMasterResponse {

    @XmlElement(name = "ChangePartNumMasterResult", namespace = "http://epicor.com/webservices/")
    protected SalesOrderDataSetType changePartNumMasterResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String partNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean lSubstitutePartExist;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean lIsPhantom;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String uomCode;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String cDeleteComponentsMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String questionString;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String cWarningMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean multipleMatch;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean promptToExplodeBOM;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String cConfigPartMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String cSubPartMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String explodeBOMerrMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String cMsgType;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean multiSubsAvail;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean runOutQtyAvail;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the changePartNumMasterResult property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public SalesOrderDataSetType getChangePartNumMasterResult() {
        return changePartNumMasterResult;
    }

    /**
     * Sets the value of the changePartNumMasterResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public void setChangePartNumMasterResult(SalesOrderDataSetType value) {
        this.changePartNumMasterResult = value;
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
     * Gets the value of the cDeleteComponentsMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCDeleteComponentsMessage() {
        return cDeleteComponentsMessage;
    }

    /**
     * Sets the value of the cDeleteComponentsMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCDeleteComponentsMessage(String value) {
        this.cDeleteComponentsMessage = value;
    }

    /**
     * Gets the value of the questionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestionString() {
        return questionString;
    }

    /**
     * Sets the value of the questionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestionString(String value) {
        this.questionString = value;
    }

    /**
     * Gets the value of the cWarningMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCWarningMessage() {
        return cWarningMessage;
    }

    /**
     * Sets the value of the cWarningMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCWarningMessage(String value) {
        this.cWarningMessage = value;
    }

    /**
     * Gets the value of the multipleMatch property.
     * 
     */
    public boolean isMultipleMatch() {
        return multipleMatch;
    }

    /**
     * Sets the value of the multipleMatch property.
     * 
     */
    public void setMultipleMatch(boolean value) {
        this.multipleMatch = value;
    }

    /**
     * Gets the value of the promptToExplodeBOM property.
     * 
     */
    public boolean isPromptToExplodeBOM() {
        return promptToExplodeBOM;
    }

    /**
     * Sets the value of the promptToExplodeBOM property.
     * 
     */
    public void setPromptToExplodeBOM(boolean value) {
        this.promptToExplodeBOM = value;
    }

    /**
     * Gets the value of the cConfigPartMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCConfigPartMessage() {
        return cConfigPartMessage;
    }

    /**
     * Sets the value of the cConfigPartMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCConfigPartMessage(String value) {
        this.cConfigPartMessage = value;
    }

    /**
     * Gets the value of the cSubPartMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCSubPartMessage() {
        return cSubPartMessage;
    }

    /**
     * Sets the value of the cSubPartMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCSubPartMessage(String value) {
        this.cSubPartMessage = value;
    }

    /**
     * Gets the value of the explodeBOMerrMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExplodeBOMerrMessage() {
        return explodeBOMerrMessage;
    }

    /**
     * Sets the value of the explodeBOMerrMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExplodeBOMerrMessage(String value) {
        this.explodeBOMerrMessage = value;
    }

    /**
     * Gets the value of the cMsgType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCMsgType() {
        return cMsgType;
    }

    /**
     * Sets the value of the cMsgType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCMsgType(String value) {
        this.cMsgType = value;
    }

    /**
     * Gets the value of the multiSubsAvail property.
     * 
     */
    public boolean isMultiSubsAvail() {
        return multiSubsAvail;
    }

    /**
     * Sets the value of the multiSubsAvail property.
     * 
     */
    public void setMultiSubsAvail(boolean value) {
        this.multiSubsAvail = value;
    }

    /**
     * Gets the value of the runOutQtyAvail property.
     * 
     */
    public boolean isRunOutQtyAvail() {
        return runOutQtyAvail;
    }

    /**
     * Sets the value of the runOutQtyAvail property.
     * 
     */
    public void setRunOutQtyAvail(boolean value) {
        this.runOutQtyAvail = value;
    }

    /**
     * Gets the value of the callContextOut property.
     * 
     * @return
     *     possible object is
     *     {@link CallContextDataSetType }
     *     
     */
    public CallContextDataSetType getCallContextOut() {
        return callContextOut;
    }

    /**
     * Sets the value of the callContextOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link CallContextDataSetType }
     *     
     */
    public void setCallContextOut(CallContextDataSetType value) {
        this.callContextOut = value;
    }

}
