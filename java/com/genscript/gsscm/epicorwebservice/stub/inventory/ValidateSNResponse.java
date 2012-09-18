
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
 *         &lt;element name="ValidateSNResult" type="{http://epicor.com/schemas}InventoryQtyAdjDataSetType" minOccurs="0"/>
 *         &lt;element name="isVoided" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "validateSNResult",
    "isVoided",
    "callContextOut"
})
@XmlRootElement(name = "ValidateSNResponse")
public class ValidateSNResponse {

    @XmlElement(name = "ValidateSNResult", namespace = "http://epicor.com/webservices/")
    protected InventoryQtyAdjDataSetType validateSNResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean isVoided;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the validateSNResult property.
     * 
     * @return
     *     possible object is
     *     {@link InventoryQtyAdjDataSetType }
     *     
     */
    public InventoryQtyAdjDataSetType getValidateSNResult() {
        return validateSNResult;
    }

    /**
     * Sets the value of the validateSNResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link InventoryQtyAdjDataSetType }
     *     
     */
    public void setValidateSNResult(InventoryQtyAdjDataSetType value) {
        this.validateSNResult = value;
    }

    /**
     * Gets the value of the isVoided property.
     * 
     */
    public boolean isIsVoided() {
        return isVoided;
    }

    /**
     * Sets the value of the isVoided property.
     * 
     */
    public void setIsVoided(boolean value) {
        this.isVoided = value;
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
