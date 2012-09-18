
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
 *         &lt;element name="partNumList" type="{http://www.genscript.com/}ArrayOfPartItem" minOccurs="0"/>
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
    "partNumList"
})
@XmlRootElement(name = "getInventoryQtyAdjList")
public class GetInventoryQtyAdjList {

    protected ArrayOfPartItem partNumList;

    /**
     * Gets the value of the partNumList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPartItem }
     *     
     */
    public ArrayOfPartItem getPartNumList() {
        return partNumList;
    }

    /**
     * Sets the value of the partNumList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPartItem }
     *     
     */
    public void setPartNumList(ArrayOfPartItem value) {
        this.partNumList = value;
    }

}
