
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
 *         &lt;element name="ProcessQuickEntryResult" type="{http://epicor.com/schemas}SalesOrderDataSetType" minOccurs="0"/>
 *         &lt;element name="lProcessComplete" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "processQuickEntryResult",
    "lProcessComplete",
    "callContextOut"
})
@XmlRootElement(name = "ProcessQuickEntryResponse")
public class ProcessQuickEntryResponse {

    @XmlElement(name = "ProcessQuickEntryResult", namespace = "http://epicor.com/webservices/")
    protected SalesOrderDataSetType processQuickEntryResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean lProcessComplete;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the processQuickEntryResult property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public SalesOrderDataSetType getProcessQuickEntryResult() {
        return processQuickEntryResult;
    }

    /**
     * Sets the value of the processQuickEntryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public void setProcessQuickEntryResult(SalesOrderDataSetType value) {
        this.processQuickEntryResult = value;
    }

    /**
     * Gets the value of the lProcessComplete property.
     * 
     */
    public boolean isLProcessComplete() {
        return lProcessComplete;
    }

    /**
     * Sets the value of the lProcessComplete property.
     * 
     */
    public void setLProcessComplete(boolean value) {
        this.lProcessComplete = value;
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
