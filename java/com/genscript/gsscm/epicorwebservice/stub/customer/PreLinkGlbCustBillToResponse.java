
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
 *         &lt;element name="PreLinkGlbCustBillToResult" type="{http://epicor.com/schemas}GlbCustomerDataSetType" minOccurs="0"/>
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
    "preLinkGlbCustBillToResult",
    "vMessage",
    "callContextOut"
})
@XmlRootElement(name = "PreLinkGlbCustBillToResponse")
public class PreLinkGlbCustBillToResponse {

    @XmlElement(name = "PreLinkGlbCustBillToResult", namespace = "http://epicor.com/webservices/")
    protected GlbCustomerDataSetType preLinkGlbCustBillToResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String vMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the preLinkGlbCustBillToResult property.
     * 
     * @return
     *     possible object is
     *     {@link GlbCustomerDataSetType }
     *     
     */
    public GlbCustomerDataSetType getPreLinkGlbCustBillToResult() {
        return preLinkGlbCustBillToResult;
    }

    /**
     * Sets the value of the preLinkGlbCustBillToResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlbCustomerDataSetType }
     *     
     */
    public void setPreLinkGlbCustBillToResult(GlbCustomerDataSetType value) {
        this.preLinkGlbCustBillToResult = value;
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
