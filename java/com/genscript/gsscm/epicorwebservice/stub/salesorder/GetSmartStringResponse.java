
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
 *         &lt;element name="GetSmartStringResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="smartString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createPart" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="promptForPartNum" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="notifyOfExistingPart" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="newPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createCustPart" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="promptForCustPartNum" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="newCustPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="promptForAutoCreatePart" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "getSmartStringResult",
    "smartString",
    "createPart",
    "promptForPartNum",
    "notifyOfExistingPart",
    "newPartNum",
    "createCustPart",
    "promptForCustPartNum",
    "newCustPartNum",
    "promptForAutoCreatePart",
    "callContextOut"
})
@XmlRootElement(name = "GetSmartStringResponse")
public class GetSmartStringResponse {

    @XmlElement(name = "GetSmartStringResult", namespace = "http://epicor.com/webservices/")
    protected boolean getSmartStringResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String smartString;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean createPart;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean promptForPartNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean notifyOfExistingPart;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String newPartNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean createCustPart;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean promptForCustPartNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String newCustPartNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean promptForAutoCreatePart;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the getSmartStringResult property.
     * 
     */
    public boolean isGetSmartStringResult() {
        return getSmartStringResult;
    }

    /**
     * Sets the value of the getSmartStringResult property.
     * 
     */
    public void setGetSmartStringResult(boolean value) {
        this.getSmartStringResult = value;
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
     * Gets the value of the createPart property.
     * 
     */
    public boolean isCreatePart() {
        return createPart;
    }

    /**
     * Sets the value of the createPart property.
     * 
     */
    public void setCreatePart(boolean value) {
        this.createPart = value;
    }

    /**
     * Gets the value of the promptForPartNum property.
     * 
     */
    public boolean isPromptForPartNum() {
        return promptForPartNum;
    }

    /**
     * Sets the value of the promptForPartNum property.
     * 
     */
    public void setPromptForPartNum(boolean value) {
        this.promptForPartNum = value;
    }

    /**
     * Gets the value of the notifyOfExistingPart property.
     * 
     */
    public boolean isNotifyOfExistingPart() {
        return notifyOfExistingPart;
    }

    /**
     * Sets the value of the notifyOfExistingPart property.
     * 
     */
    public void setNotifyOfExistingPart(boolean value) {
        this.notifyOfExistingPart = value;
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
     * Gets the value of the createCustPart property.
     * 
     */
    public boolean isCreateCustPart() {
        return createCustPart;
    }

    /**
     * Sets the value of the createCustPart property.
     * 
     */
    public void setCreateCustPart(boolean value) {
        this.createCustPart = value;
    }

    /**
     * Gets the value of the promptForCustPartNum property.
     * 
     */
    public boolean isPromptForCustPartNum() {
        return promptForCustPartNum;
    }

    /**
     * Sets the value of the promptForCustPartNum property.
     * 
     */
    public void setPromptForCustPartNum(boolean value) {
        this.promptForCustPartNum = value;
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
     * Gets the value of the promptForAutoCreatePart property.
     * 
     */
    public boolean isPromptForAutoCreatePart() {
        return promptForAutoCreatePart;
    }

    /**
     * Sets the value of the promptForAutoCreatePart property.
     * 
     */
    public void setPromptForAutoCreatePart(boolean value) {
        this.promptForAutoCreatePart = value;
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
