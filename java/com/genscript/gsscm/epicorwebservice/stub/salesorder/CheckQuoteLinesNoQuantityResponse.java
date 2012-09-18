
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
 *         &lt;element name="CheckQuoteLinesNoQuantityResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cQuoteLineWOQtyMsgText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "checkQuoteLinesNoQuantityResult",
    "cQuoteLineWOQtyMsgText",
    "callContextOut"
})
@XmlRootElement(name = "CheckQuoteLinesNoQuantityResponse")
public class CheckQuoteLinesNoQuantityResponse {

    @XmlElement(name = "CheckQuoteLinesNoQuantityResult", namespace = "http://epicor.com/webservices/")
    protected boolean checkQuoteLinesNoQuantityResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String cQuoteLineWOQtyMsgText;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the checkQuoteLinesNoQuantityResult property.
     * 
     */
    public boolean isCheckQuoteLinesNoQuantityResult() {
        return checkQuoteLinesNoQuantityResult;
    }

    /**
     * Sets the value of the checkQuoteLinesNoQuantityResult property.
     * 
     */
    public void setCheckQuoteLinesNoQuantityResult(boolean value) {
        this.checkQuoteLinesNoQuantityResult = value;
    }

    /**
     * Gets the value of the cQuoteLineWOQtyMsgText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCQuoteLineWOQtyMsgText() {
        return cQuoteLineWOQtyMsgText;
    }

    /**
     * Sets the value of the cQuoteLineWOQtyMsgText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCQuoteLineWOQtyMsgText(String value) {
        this.cQuoteLineWOQtyMsgText = value;
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
