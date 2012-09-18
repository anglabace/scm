
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
 *         &lt;element name="SetMRPRecalcFlagResult" type="{http://epicor.com/schemas}PartDataSetType" minOccurs="0"/>
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
    "setMRPRecalcFlagResult",
    "callContextOut"
})
@XmlRootElement(name = "SetMRPRecalcFlagResponse")
public class SetMRPRecalcFlagResponse {

    @XmlElement(name = "SetMRPRecalcFlagResult", namespace = "http://epicor.com/webservices/")
    protected PartDataSetType setMRPRecalcFlagResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the setMRPRecalcFlagResult property.
     * 
     * @return
     *     possible object is
     *     {@link PartDataSetType }
     *     
     */
    public PartDataSetType getSetMRPRecalcFlagResult() {
        return setMRPRecalcFlagResult;
    }

    /**
     * Sets the value of the setMRPRecalcFlagResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartDataSetType }
     *     
     */
    public void setSetMRPRecalcFlagResult(PartDataSetType value) {
        this.setMRPRecalcFlagResult = value;
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
