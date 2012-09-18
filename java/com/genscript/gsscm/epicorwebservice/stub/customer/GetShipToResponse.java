
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
 *         &lt;element name="GetShipToResult" type="{http://epicor.com/schemas}CustomerDataSetType" minOccurs="0"/>
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
    "getShipToResult",
    "callContextOut"
})
@XmlRootElement(name = "GetShipToResponse")
public class GetShipToResponse {

    @XmlElement(name = "GetShipToResult", namespace = "http://epicor.com/webservices/")
    protected CustomerDataSetType getShipToResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the getShipToResult property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerDataSetType }
     *     
     */
    public CustomerDataSetType getGetShipToResult() {
        return getShipToResult;
    }

    /**
     * Sets the value of the getShipToResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerDataSetType }
     *     
     */
    public void setGetShipToResult(CustomerDataSetType value) {
        this.getShipToResult = value;
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