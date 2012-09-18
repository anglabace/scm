
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
 *         &lt;element name="CheckComplianceFailResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="compliant" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "checkComplianceFailResult",
    "compliant",
    "callContextOut"
})
@XmlRootElement(name = "CheckComplianceFailResponse")
public class CheckComplianceFailResponse {

    @XmlElement(name = "CheckComplianceFailResult", namespace = "http://epicor.com/webservices/")
    protected boolean checkComplianceFailResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean compliant;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the checkComplianceFailResult property.
     * 
     */
    public boolean isCheckComplianceFailResult() {
        return checkComplianceFailResult;
    }

    /**
     * Sets the value of the checkComplianceFailResult property.
     * 
     */
    public void setCheckComplianceFailResult(boolean value) {
        this.checkComplianceFailResult = value;
    }

    /**
     * Gets the value of the compliant property.
     * 
     */
    public boolean isCompliant() {
        return compliant;
    }

    /**
     * Sets the value of the compliant property.
     * 
     */
    public void setCompliant(boolean value) {
        this.compliant = value;
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
