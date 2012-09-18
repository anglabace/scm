
package com.genscript.gsscm.epicorwebservice.stub.lot;

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
 *         &lt;element name="CheckDupLotNumResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="vMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "checkDupLotNumResult",
    "vMessage",
    "callContextOut"
})
@XmlRootElement(name = "CheckDupLotNumResponse")
public class CheckDupLotNumResponse {

    @XmlElement(name = "CheckDupLotNumResult", namespace = "http://epicor.com/webservices/")
    protected boolean checkDupLotNumResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String vMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the checkDupLotNumResult property.
     * 
     */
    public boolean isCheckDupLotNumResult() {
        return checkDupLotNumResult;
    }

    /**
     * Sets the value of the checkDupLotNumResult property.
     * 
     */
    public void setCheckDupLotNumResult(boolean value) {
        this.checkDupLotNumResult = value;
    }

    /**
     * Gets the value of the vMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVMessage() {
        return vMessage;
    }

    /**
     * Sets the value of the vMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVMessage(String value) {
        this.vMessage = value;
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
