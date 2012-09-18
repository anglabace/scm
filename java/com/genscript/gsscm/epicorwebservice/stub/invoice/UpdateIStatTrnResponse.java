
package com.genscript.gsscm.epicorwebservice.stub.invoice;

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
 *         &lt;element name="UpdateIStatTrnResult" type="{http://epicor.com/schemas}InvoiceIStatTrnDataSetType" minOccurs="0"/>
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
    "updateIStatTrnResult",
    "callContextOut"
})
@XmlRootElement(name = "UpdateIStatTrnResponse")
public class UpdateIStatTrnResponse {

    @XmlElement(name = "UpdateIStatTrnResult", namespace = "http://epicor.com/webservices/")
    protected InvoiceIStatTrnDataSetType updateIStatTrnResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the updateIStatTrnResult property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceIStatTrnDataSetType }
     *     
     */
    public InvoiceIStatTrnDataSetType getUpdateIStatTrnResult() {
        return updateIStatTrnResult;
    }

    /**
     * Sets the value of the updateIStatTrnResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceIStatTrnDataSetType }
     *     
     */
    public void setUpdateIStatTrnResult(InvoiceIStatTrnDataSetType value) {
        this.updateIStatTrnResult = value;
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
