
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
 *         &lt;element name="CheckOrderLinkToInterCompanyPOResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cICPOLinkMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "checkOrderLinkToInterCompanyPOResult",
    "cicpoLinkMessage",
    "callContextOut"
})
@XmlRootElement(name = "CheckOrderLinkToInterCompanyPOResponse")
public class CheckOrderLinkToInterCompanyPOResponse {

    @XmlElement(name = "CheckOrderLinkToInterCompanyPOResult", namespace = "http://epicor.com/webservices/")
    protected boolean checkOrderLinkToInterCompanyPOResult;
    @XmlElement(name = "cICPOLinkMessage", namespace = "http://epicor.com/webservices/")
    protected String cicpoLinkMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the checkOrderLinkToInterCompanyPOResult property.
     * 
     */
    public boolean isCheckOrderLinkToInterCompanyPOResult() {
        return checkOrderLinkToInterCompanyPOResult;
    }

    /**
     * Sets the value of the checkOrderLinkToInterCompanyPOResult property.
     * 
     */
    public void setCheckOrderLinkToInterCompanyPOResult(boolean value) {
        this.checkOrderLinkToInterCompanyPOResult = value;
    }

    /**
     * Gets the value of the cicpoLinkMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCICPOLinkMessage() {
        return cicpoLinkMessage;
    }

    /**
     * Sets the value of the cicpoLinkMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCICPOLinkMessage(String value) {
        this.cicpoLinkMessage = value;
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
