
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
 *         &lt;element name="ChangeCommodityCodeResult" type="{http://epicor.com/schemas}InvoiceIStatTrnDataSetType" minOccurs="0"/>
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
    "changeCommodityCodeResult",
    "callContextOut"
})
@XmlRootElement(name = "ChangeCommodityCodeResponse")
public class ChangeCommodityCodeResponse {

    @XmlElement(name = "ChangeCommodityCodeResult", namespace = "http://epicor.com/webservices/")
    protected InvoiceIStatTrnDataSetType changeCommodityCodeResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the changeCommodityCodeResult property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceIStatTrnDataSetType }
     *     
     */
    public InvoiceIStatTrnDataSetType getChangeCommodityCodeResult() {
        return changeCommodityCodeResult;
    }

    /**
     * Sets the value of the changeCommodityCodeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceIStatTrnDataSetType }
     *     
     */
    public void setChangeCommodityCodeResult(InvoiceIStatTrnDataSetType value) {
        this.changeCommodityCodeResult = value;
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
