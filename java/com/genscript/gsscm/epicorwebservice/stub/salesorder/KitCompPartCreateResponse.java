
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
 *         &lt;element name="KitCompPartCreateResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="newPartAlreadyExists" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "kitCompPartCreateResult",
    "newPartAlreadyExists",
    "callContextOut"
})
@XmlRootElement(name = "KitCompPartCreateResponse")
public class KitCompPartCreateResponse {

    @XmlElement(name = "KitCompPartCreateResult", namespace = "http://epicor.com/webservices/")
    protected boolean kitCompPartCreateResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean newPartAlreadyExists;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the kitCompPartCreateResult property.
     * 
     */
    public boolean isKitCompPartCreateResult() {
        return kitCompPartCreateResult;
    }

    /**
     * Sets the value of the kitCompPartCreateResult property.
     * 
     */
    public void setKitCompPartCreateResult(boolean value) {
        this.kitCompPartCreateResult = value;
    }

    /**
     * Gets the value of the newPartAlreadyExists property.
     * 
     */
    public boolean isNewPartAlreadyExists() {
        return newPartAlreadyExists;
    }

    /**
     * Sets the value of the newPartAlreadyExists property.
     * 
     */
    public void setNewPartAlreadyExists(boolean value) {
        this.newPartAlreadyExists = value;
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
