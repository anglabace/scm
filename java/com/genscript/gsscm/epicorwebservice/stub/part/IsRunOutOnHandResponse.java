
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
 *         &lt;element name="IsRunOutOnHandResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isRunOutOnHand" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "isRunOutOnHandResult",
    "isRunOutOnHand",
    "callContextOut"
})
@XmlRootElement(name = "IsRunOutOnHandResponse")
public class IsRunOutOnHandResponse {

    @XmlElement(name = "IsRunOutOnHandResult", namespace = "http://epicor.com/webservices/")
    protected boolean isRunOutOnHandResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean isRunOutOnHand;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the isRunOutOnHandResult property.
     * 
     */
    public boolean isIsRunOutOnHandResult() {
        return isRunOutOnHandResult;
    }

    /**
     * Sets the value of the isRunOutOnHandResult property.
     * 
     */
    public void setIsRunOutOnHandResult(boolean value) {
        this.isRunOutOnHandResult = value;
    }

    /**
     * Gets the value of the isRunOutOnHand property.
     * 
     */
    public boolean isIsRunOutOnHand() {
        return isRunOutOnHand;
    }

    /**
     * Sets the value of the isRunOutOnHand property.
     * 
     */
    public void setIsRunOutOnHand(boolean value) {
        this.isRunOutOnHand = value;
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
