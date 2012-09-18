
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
 *         &lt;element name="CheckAltMethodForDeleteResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="opMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "checkAltMethodForDeleteResult",
    "opMessage",
    "callContextOut"
})
@XmlRootElement(name = "CheckAltMethodForDeleteResponse")
public class CheckAltMethodForDeleteResponse {

    @XmlElement(name = "CheckAltMethodForDeleteResult", namespace = "http://epicor.com/webservices/")
    protected boolean checkAltMethodForDeleteResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String opMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the checkAltMethodForDeleteResult property.
     * 
     */
    public boolean isCheckAltMethodForDeleteResult() {
        return checkAltMethodForDeleteResult;
    }

    /**
     * Sets the value of the checkAltMethodForDeleteResult property.
     * 
     */
    public void setCheckAltMethodForDeleteResult(boolean value) {
        this.checkAltMethodForDeleteResult = value;
    }

    /**
     * Gets the value of the opMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpMessage() {
        return opMessage;
    }

    /**
     * Sets the value of the opMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpMessage(String value) {
        this.opMessage = value;
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
