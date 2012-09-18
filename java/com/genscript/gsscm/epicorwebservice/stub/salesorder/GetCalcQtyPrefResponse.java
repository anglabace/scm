
package com.genscript.gsscm.epicorwebservice.stub.salesorder;

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
 *         &lt;element name="GetCalcQtyPrefResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="opCalcQtyPref" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "getCalcQtyPrefResult",
    "opCalcQtyPref",
    "callContextOut"
})
@XmlRootElement(name = "GetCalcQtyPrefResponse")
public class GetCalcQtyPrefResponse {

    @XmlElement(name = "GetCalcQtyPrefResult", namespace = "http://epicor.com/webservices/")
    protected boolean getCalcQtyPrefResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean opCalcQtyPref;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the getCalcQtyPrefResult property.
     * 
     */
    public boolean isGetCalcQtyPrefResult() {
        return getCalcQtyPrefResult;
    }

    /**
     * Sets the value of the getCalcQtyPrefResult property.
     * 
     */
    public void setGetCalcQtyPrefResult(boolean value) {
        this.getCalcQtyPrefResult = value;
    }

    /**
     * Gets the value of the opCalcQtyPref property.
     * 
     */
    public boolean isOpCalcQtyPref() {
        return opCalcQtyPref;
    }

    /**
     * Sets the value of the opCalcQtyPref property.
     * 
     */
    public void setOpCalcQtyPref(boolean value) {
        this.opCalcQtyPref = value;
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
