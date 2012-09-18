
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
 *         &lt;element name="CheckComplianceOrderFailResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="compliantMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "checkComplianceOrderFailResult",
    "compliantMsg",
    "callContextOut"
})
@XmlRootElement(name = "CheckComplianceOrderFailResponse")
public class CheckComplianceOrderFailResponse {

    @XmlElement(name = "CheckComplianceOrderFailResult", namespace = "http://epicor.com/webservices/")
    protected boolean checkComplianceOrderFailResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String compliantMsg;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the checkComplianceOrderFailResult property.
     * 
     */
    public boolean isCheckComplianceOrderFailResult() {
        return checkComplianceOrderFailResult;
    }

    /**
     * Sets the value of the checkComplianceOrderFailResult property.
     * 
     */
    public void setCheckComplianceOrderFailResult(boolean value) {
        this.checkComplianceOrderFailResult = value;
    }

    /**
     * Gets the value of the compliantMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompliantMsg() {
        return compliantMsg;
    }

    /**
     * Sets the value of the compliantMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompliantMsg(String value) {
        this.compliantMsg = value;
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
