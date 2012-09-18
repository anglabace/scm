
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
 *         &lt;element name="PreSetInventoryQtyAdjResult" type="{http://epicor.com/schemas}InventoryQtyAdjDataSetType" minOccurs="0"/>
 *         &lt;element name="requiresUserInput" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "preSetInventoryQtyAdjResult",
    "requiresUserInput",
    "callContextOut"
})
@XmlRootElement(name = "PreSetInventoryQtyAdjResponse")
public class PreSetInventoryQtyAdjResponse {

    @XmlElement(name = "PreSetInventoryQtyAdjResult", namespace = "http://epicor.com/webservices/")
    protected InventoryQtyAdjDataSetType preSetInventoryQtyAdjResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean requiresUserInput;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the preSetInventoryQtyAdjResult property.
     * 
     * @return
     *     possible object is
     *     {@link InventoryQtyAdjDataSetType }
     *     
     */
    public InventoryQtyAdjDataSetType getPreSetInventoryQtyAdjResult() {
        return preSetInventoryQtyAdjResult;
    }

    /**
     * Sets the value of the preSetInventoryQtyAdjResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link InventoryQtyAdjDataSetType }
     *     
     */
    public void setPreSetInventoryQtyAdjResult(InventoryQtyAdjDataSetType value) {
        this.preSetInventoryQtyAdjResult = value;
    }

    /**
     * Gets the value of the requiresUserInput property.
     * 
     */
    public boolean isRequiresUserInput() {
        return requiresUserInput;
    }

    /**
     * Sets the value of the requiresUserInput property.
     * 
     */
    public void setRequiresUserInput(boolean value) {
        this.requiresUserInput = value;
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
