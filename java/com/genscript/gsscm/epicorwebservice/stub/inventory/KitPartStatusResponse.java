
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
 *         &lt;element name="KitPartStatusResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="kitMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "kitPartStatusResult",
    "kitMessage",
    "callContextOut"
})
@XmlRootElement(name = "KitPartStatusResponse")
public class KitPartStatusResponse {

    @XmlElement(name = "KitPartStatusResult", namespace = "http://epicor.com/webservices/")
    protected boolean kitPartStatusResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String kitMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the kitPartStatusResult property.
     * 
     */
    public boolean isKitPartStatusResult() {
        return kitPartStatusResult;
    }

    /**
     * Sets the value of the kitPartStatusResult property.
     * 
     */
    public void setKitPartStatusResult(boolean value) {
        this.kitPartStatusResult = value;
    }

    /**
     * Gets the value of the kitMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKitMessage() {
        return kitMessage;
    }

    /**
     * Sets the value of the kitMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKitMessage(String value) {
        this.kitMessage = value;
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
