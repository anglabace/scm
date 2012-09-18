
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
 *         &lt;element name="NegativeInventoryTestResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="pcNeqQtyAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pcMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "negativeInventoryTestResult",
    "pcNeqQtyAction",
    "pcMessage",
    "callContextOut"
})
@XmlRootElement(name = "NegativeInventoryTestResponse")
public class NegativeInventoryTestResponse {

    @XmlElement(name = "NegativeInventoryTestResult", namespace = "http://epicor.com/webservices/")
    protected boolean negativeInventoryTestResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String pcNeqQtyAction;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String pcMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the negativeInventoryTestResult property.
     * 
     */
    public boolean isNegativeInventoryTestResult() {
        return negativeInventoryTestResult;
    }

    /**
     * Sets the value of the negativeInventoryTestResult property.
     * 
     */
    public void setNegativeInventoryTestResult(boolean value) {
        this.negativeInventoryTestResult = value;
    }

    /**
     * Gets the value of the pcNeqQtyAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcNeqQtyAction() {
        return pcNeqQtyAction;
    }

    /**
     * Sets the value of the pcNeqQtyAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcNeqQtyAction(String value) {
        this.pcNeqQtyAction = value;
    }

    /**
     * Gets the value of the pcMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcMessage() {
        return pcMessage;
    }

    /**
     * Sets the value of the pcMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcMessage(String value) {
        this.pcMessage = value;
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
