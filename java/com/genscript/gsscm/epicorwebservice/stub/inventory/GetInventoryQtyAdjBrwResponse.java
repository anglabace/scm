
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
 *         &lt;element name="GetInventoryQtyAdjBrwResult" type="{http://epicor.com/schemas}InventoryQtyAdjBrwDataSetType" minOccurs="0"/>
 *         &lt;element name="primaryBin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getInventoryQtyAdjBrwResult",
    "primaryBin",
    "callContextOut"
})
@XmlRootElement(name = "GetInventoryQtyAdjBrwResponse")
public class GetInventoryQtyAdjBrwResponse {

    @XmlElement(name = "GetInventoryQtyAdjBrwResult", namespace = "http://epicor.com/webservices/")
    protected InventoryQtyAdjBrwDataSetType getInventoryQtyAdjBrwResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String primaryBin;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the getInventoryQtyAdjBrwResult property.
     * 
     * @return
     *     possible object is
     *     {@link InventoryQtyAdjBrwDataSetType }
     *     
     */
    public InventoryQtyAdjBrwDataSetType getGetInventoryQtyAdjBrwResult() {
        return getInventoryQtyAdjBrwResult;
    }

    /**
     * Sets the value of the getInventoryQtyAdjBrwResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link InventoryQtyAdjBrwDataSetType }
     *     
     */
    public void setGetInventoryQtyAdjBrwResult(InventoryQtyAdjBrwDataSetType value) {
        this.getInventoryQtyAdjBrwResult = value;
    }

    /**
     * Gets the value of the primaryBin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryBin() {
        return primaryBin;
    }

    /**
     * Sets the value of the primaryBin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryBin(String value) {
        this.primaryBin = value;
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
