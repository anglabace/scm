
package com.genscript.gsscm.epicorwebservice.stub.invoiceService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="getInventoryQtyAdjListResult" type="{http://www.genscript.com/}ArrayOfInventoryQtyAdjItem" minOccurs="0"/>
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
    "getInventoryQtyAdjListResult"
})
@XmlRootElement(name = "getInventoryQtyAdjListResponse")
public class GetInventoryQtyAdjListResponse {

    protected ArrayOfInventoryQtyAdjItem getInventoryQtyAdjListResult;

    /**
     * Gets the value of the getInventoryQtyAdjListResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInventoryQtyAdjItem }
     *     
     */
    public ArrayOfInventoryQtyAdjItem getGetInventoryQtyAdjListResult() {
        return getInventoryQtyAdjListResult;
    }

    /**
     * Sets the value of the getInventoryQtyAdjListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInventoryQtyAdjItem }
     *     
     */
    public void setGetInventoryQtyAdjListResult(ArrayOfInventoryQtyAdjItem value) {
        this.getInventoryQtyAdjListResult = value;
    }

}
