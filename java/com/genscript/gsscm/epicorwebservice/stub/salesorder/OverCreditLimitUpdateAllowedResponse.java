
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
 *         &lt;element name="OverCreditLimitUpdateAllowedResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lUpdateAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "overCreditLimitUpdateAllowedResult",
    "lUpdateAllowed",
    "callContextOut"
})
@XmlRootElement(name = "OverCreditLimitUpdateAllowedResponse")
public class OverCreditLimitUpdateAllowedResponse {

    @XmlElement(name = "OverCreditLimitUpdateAllowedResult", namespace = "http://epicor.com/webservices/")
    protected boolean overCreditLimitUpdateAllowedResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean lUpdateAllowed;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the overCreditLimitUpdateAllowedResult property.
     * 
     */
    public boolean isOverCreditLimitUpdateAllowedResult() {
        return overCreditLimitUpdateAllowedResult;
    }

    /**
     * Sets the value of the overCreditLimitUpdateAllowedResult property.
     * 
     */
    public void setOverCreditLimitUpdateAllowedResult(boolean value) {
        this.overCreditLimitUpdateAllowedResult = value;
    }

    /**
     * Gets the value of the lUpdateAllowed property.
     * 
     */
    public boolean isLUpdateAllowed() {
        return lUpdateAllowed;
    }

    /**
     * Sets the value of the lUpdateAllowed property.
     * 
     */
    public void setLUpdateAllowed(boolean value) {
        this.lUpdateAllowed = value;
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
