
package com.genscript.gsscm.epicorwebservice.stub.customer;

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
 *         &lt;element name="GlbCustomersExistResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="glbCustomersExist" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "glbCustomersExistResult",
    "glbCustomersExist",
    "callContextOut"
})
@XmlRootElement(name = "GlbCustomersExistResponse")
public class GlbCustomersExistResponse {

    @XmlElement(name = "GlbCustomersExistResult", namespace = "http://epicor.com/webservices/")
    protected boolean glbCustomersExistResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean glbCustomersExist;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the glbCustomersExistResult property.
     * 
     */
    public boolean isGlbCustomersExistResult() {
        return glbCustomersExistResult;
    }

    /**
     * Sets the value of the glbCustomersExistResult property.
     * 
     */
    public void setGlbCustomersExistResult(boolean value) {
        this.glbCustomersExistResult = value;
    }

    /**
     * Gets the value of the glbCustomersExist property.
     * 
     */
    public boolean isGlbCustomersExist() {
        return glbCustomersExist;
    }

    /**
     * Sets the value of the glbCustomersExist property.
     * 
     */
    public void setGlbCustomersExist(boolean value) {
        this.glbCustomersExist = value;
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
