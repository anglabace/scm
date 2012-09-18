
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
 *         &lt;element name="SkipGlbPartResult" type="{http://epicor.com/schemas}GlbPartDataSetType" minOccurs="0"/>
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
    "skipGlbPartResult",
    "callContextOut"
})
@XmlRootElement(name = "SkipGlbPartResponse")
public class SkipGlbPartResponse {

    @XmlElement(name = "SkipGlbPartResult", namespace = "http://epicor.com/webservices/")
    protected GlbPartDataSetType skipGlbPartResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the skipGlbPartResult property.
     * 
     * @return
     *     possible object is
     *     {@link GlbPartDataSetType }
     *     
     */
    public GlbPartDataSetType getSkipGlbPartResult() {
        return skipGlbPartResult;
    }

    /**
     * Sets the value of the skipGlbPartResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlbPartDataSetType }
     *     
     */
    public void setSkipGlbPartResult(GlbPartDataSetType value) {
        this.skipGlbPartResult = value;
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
