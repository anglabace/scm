
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
 *         &lt;element name="GlbPartsExistResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="glbPartsExist" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "glbPartsExistResult",
    "glbPartsExist",
    "callContextOut"
})
@XmlRootElement(name = "GlbPartsExistResponse")
public class GlbPartsExistResponse {

    @XmlElement(name = "GlbPartsExistResult", namespace = "http://epicor.com/webservices/")
    protected boolean glbPartsExistResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean glbPartsExist;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the glbPartsExistResult property.
     * 
     */
    public boolean isGlbPartsExistResult() {
        return glbPartsExistResult;
    }

    /**
     * Sets the value of the glbPartsExistResult property.
     * 
     */
    public void setGlbPartsExistResult(boolean value) {
        this.glbPartsExistResult = value;
    }

    /**
     * Gets the value of the glbPartsExist property.
     * 
     */
    public boolean isGlbPartsExist() {
        return glbPartsExist;
    }

    /**
     * Sets the value of the glbPartsExist property.
     * 
     */
    public void setGlbPartsExist(boolean value) {
        this.glbPartsExist = value;
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
