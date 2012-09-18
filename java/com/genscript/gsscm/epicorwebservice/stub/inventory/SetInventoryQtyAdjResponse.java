
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
 *         &lt;element name="SetInventoryQtyAdjResult" type="{http://epicor.com/schemas}InventoryQtyAdjDataSetType" minOccurs="0"/>
 *         &lt;element name="partTranPKs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "setInventoryQtyAdjResult",
    "partTranPKs",
    "callContextOut"
})
@XmlRootElement(name = "SetInventoryQtyAdjResponse")
public class SetInventoryQtyAdjResponse {

    @XmlElement(name = "SetInventoryQtyAdjResult", namespace = "http://epicor.com/webservices/")
    protected InventoryQtyAdjDataSetType setInventoryQtyAdjResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String partTranPKs;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the setInventoryQtyAdjResult property.
     * 
     * @return
     *     possible object is
     *     {@link InventoryQtyAdjDataSetType }
     *     
     */
    public InventoryQtyAdjDataSetType getSetInventoryQtyAdjResult() {
        return setInventoryQtyAdjResult;
    }

    /**
     * Sets the value of the setInventoryQtyAdjResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link InventoryQtyAdjDataSetType }
     *     
     */
    public void setSetInventoryQtyAdjResult(InventoryQtyAdjDataSetType value) {
        this.setInventoryQtyAdjResult = value;
    }

    /**
     * Gets the value of the partTranPKs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartTranPKs() {
        return partTranPKs;
    }

    /**
     * Sets the value of the partTranPKs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartTranPKs(String value) {
        this.partTranPKs = value;
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
