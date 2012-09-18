
package com.genscript.gsscm.epicorwebservice.stub.part;

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
 *         &lt;element name="ChangePartProdCodeResult" type="{http://epicor.com/schemas}PartDataSetType" minOccurs="0"/>
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
    "changePartProdCodeResult",
    "callContextOut"
})
@XmlRootElement(name = "ChangePartProdCodeResponse")
public class ChangePartProdCodeResponse {

    @XmlElement(name = "ChangePartProdCodeResult", namespace = "http://epicor.com/webservices/")
    protected PartDataSetType changePartProdCodeResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the changePartProdCodeResult property.
     * 
     * @return
     *     possible object is
     *     {@link PartDataSetType }
     *     
     */
    public PartDataSetType getChangePartProdCodeResult() {
        return changePartProdCodeResult;
    }

    /**
     * Sets the value of the changePartProdCodeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartDataSetType }
     *     
     */
    public void setChangePartProdCodeResult(PartDataSetType value) {
        this.changePartProdCodeResult = value;
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
