
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
 *         &lt;element name="ProcessCounterSaleResult" type="{http://epicor.com/schemas}SalesOrderDataSetType" minOccurs="0"/>
 *         &lt;element name="cPackNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iInvoiceNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="opMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "processCounterSaleResult",
    "cPackNum",
    "iInvoiceNum",
    "opMessage",
    "callContextOut"
})
@XmlRootElement(name = "ProcessCounterSaleResponse")
public class ProcessCounterSaleResponse {

    @XmlElement(name = "ProcessCounterSaleResult", namespace = "http://epicor.com/webservices/")
    protected SalesOrderDataSetType processCounterSaleResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String cPackNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int iInvoiceNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String opMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the processCounterSaleResult property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public SalesOrderDataSetType getProcessCounterSaleResult() {
        return processCounterSaleResult;
    }

    /**
     * Sets the value of the processCounterSaleResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public void setProcessCounterSaleResult(SalesOrderDataSetType value) {
        this.processCounterSaleResult = value;
    }

    /**
     * Gets the value of the cPackNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPackNum() {
        return cPackNum;
    }

    /**
     * Sets the value of the cPackNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPackNum(String value) {
        this.cPackNum = value;
    }

    /**
     * Gets the value of the iInvoiceNum property.
     * 
     */
    public int getIInvoiceNum() {
        return iInvoiceNum;
    }

    /**
     * Sets the value of the iInvoiceNum property.
     * 
     */
    public void setIInvoiceNum(int value) {
        this.iInvoiceNum = value;
    }

    /**
     * Gets the value of the opMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpMessage() {
        return opMessage;
    }

    /**
     * Sets the value of the opMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpMessage(String value) {
        this.opMessage = value;
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
