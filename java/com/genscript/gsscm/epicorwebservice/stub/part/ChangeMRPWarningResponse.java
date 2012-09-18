
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
 *         &lt;element name="ChangeMRPWarningResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="warningMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "changeMRPWarningResult",
    "warningMsg",
    "callContextOut"
})
@XmlRootElement(name = "ChangeMRPWarningResponse")
public class ChangeMRPWarningResponse {

    @XmlElement(name = "ChangeMRPWarningResult", namespace = "http://epicor.com/webservices/")
    protected boolean changeMRPWarningResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String warningMsg;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the changeMRPWarningResult property.
     * 
     */
    public boolean isChangeMRPWarningResult() {
        return changeMRPWarningResult;
    }

    /**
     * Sets the value of the changeMRPWarningResult property.
     * 
     */
    public void setChangeMRPWarningResult(boolean value) {
        this.changeMRPWarningResult = value;
    }

    /**
     * Gets the value of the warningMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarningMsg() {
        return warningMsg;
    }

    /**
     * Sets the value of the warningMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarningMsg(String value) {
        this.warningMsg = value;
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
