
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
 *         &lt;element name="VerifySendICPOSuggResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cVerifySendMsgText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "verifySendICPOSuggResult",
    "cVerifySendMsgText",
    "callContextOut"
})
@XmlRootElement(name = "VerifySendICPOSuggResponse")
public class VerifySendICPOSuggResponse {

    @XmlElement(name = "VerifySendICPOSuggResult", namespace = "http://epicor.com/webservices/")
    protected boolean verifySendICPOSuggResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String cVerifySendMsgText;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the verifySendICPOSuggResult property.
     * 
     */
    public boolean isVerifySendICPOSuggResult() {
        return verifySendICPOSuggResult;
    }

    /**
     * Sets the value of the verifySendICPOSuggResult property.
     * 
     */
    public void setVerifySendICPOSuggResult(boolean value) {
        this.verifySendICPOSuggResult = value;
    }

    /**
     * Gets the value of the cVerifySendMsgText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCVerifySendMsgText() {
        return cVerifySendMsgText;
    }

    /**
     * Sets the value of the cVerifySendMsgText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCVerifySendMsgText(String value) {
        this.cVerifySendMsgText = value;
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
