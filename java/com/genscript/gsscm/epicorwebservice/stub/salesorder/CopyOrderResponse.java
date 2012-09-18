
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
 *         &lt;element name="CopyOrderResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="newOrderNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="outMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "copyOrderResult",
    "newOrderNum",
    "outMessage",
    "callContextOut"
})
@XmlRootElement(name = "CopyOrderResponse")
public class CopyOrderResponse {

    @XmlElement(name = "CopyOrderResult", namespace = "http://epicor.com/webservices/")
    protected boolean copyOrderResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int newOrderNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String outMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the copyOrderResult property.
     * 
     */
    public boolean isCopyOrderResult() {
        return copyOrderResult;
    }

    /**
     * Sets the value of the copyOrderResult property.
     * 
     */
    public void setCopyOrderResult(boolean value) {
        this.copyOrderResult = value;
    }

    /**
     * Gets the value of the newOrderNum property.
     * 
     */
    public int getNewOrderNum() {
        return newOrderNum;
    }

    /**
     * Sets the value of the newOrderNum property.
     * 
     */
    public void setNewOrderNum(int value) {
        this.newOrderNum = value;
    }

    /**
     * Gets the value of the outMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutMessage() {
        return outMessage;
    }

    /**
     * Sets the value of the outMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutMessage(String value) {
        this.outMessage = value;
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
