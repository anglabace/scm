
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
 *         &lt;element name="CheckICPOReadyToSendResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cReadyToSendMsgText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "checkICPOReadyToSendResult",
    "cReadyToSendMsgText",
    "callContextOut"
})
@XmlRootElement(name = "CheckICPOReadyToSendResponse")
public class CheckICPOReadyToSendResponse {

    @XmlElement(name = "CheckICPOReadyToSendResult", namespace = "http://epicor.com/webservices/")
    protected boolean checkICPOReadyToSendResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String cReadyToSendMsgText;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the checkICPOReadyToSendResult property.
     * 
     */
    public boolean isCheckICPOReadyToSendResult() {
        return checkICPOReadyToSendResult;
    }

    /**
     * Sets the value of the checkICPOReadyToSendResult property.
     * 
     */
    public void setCheckICPOReadyToSendResult(boolean value) {
        this.checkICPOReadyToSendResult = value;
    }

    /**
     * Gets the value of the cReadyToSendMsgText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCReadyToSendMsgText() {
        return cReadyToSendMsgText;
    }

    /**
     * Sets the value of the cReadyToSendMsgText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCReadyToSendMsgText(String value) {
        this.cReadyToSendMsgText = value;
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
