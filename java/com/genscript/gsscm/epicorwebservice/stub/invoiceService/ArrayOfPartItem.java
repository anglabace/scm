
package com.genscript.gsscm.epicorwebservice.stub.invoiceService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPartItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPartItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PartItem" type="{http://www.genscript.com/}PartItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPartItem", propOrder = {
    "partItem"
})
public class ArrayOfPartItem {

    @XmlElement(name = "PartItem")
    protected List<PartItem> partItem;

    /**
     * Gets the value of the partItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartItem }
     * 
     * 
     */
    public List<PartItem> getPartItem() {
        if (partItem == null) {
            partItem = new ArrayList<PartItem>();
        }
        return this.partItem;
    }
    
    public void setPartItem(List<PartItem> partItem){
    	this.partItem = partItem;
    }

}
