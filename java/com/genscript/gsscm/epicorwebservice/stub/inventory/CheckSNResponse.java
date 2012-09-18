
package com.genscript.gsscm.epicorwebservice.stub.inventory;

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
 *         &lt;element name="CheckSNResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="serialNumbersRequired" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "checkSNResult",
    "serialNumbersRequired",
    "callContextOut"
})
@XmlRootElement(name = "CheckSNResponse")
public class CheckSNResponse {

    @XmlElement(name = "CheckSNResult", namespace = "http://epicor.com/webservices/")
    protected boolean checkSNResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean serialNumbersRequired;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the checkSNResult property.
     * 
     */
    public boolean isCheckSNResult() {
        return checkSNResult;
    }

    /**
     * Sets the value of the checkSNResult property.
     * 
     */
    public void setCheckSNResult(boolean value) {
        this.checkSNResult = value;
    }

    /**
     * Gets the value of the serialNumbersRequired property.
     * 
     */
    public boolean isSerialNumbersRequired() {
        return serialNumbersRequired;
    }

    /**
     * Sets the value of the serialNumbersRequired property.
     * 
     */
    public void setSerialNumbersRequired(boolean value) {
        this.serialNumbersRequired = value;
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
