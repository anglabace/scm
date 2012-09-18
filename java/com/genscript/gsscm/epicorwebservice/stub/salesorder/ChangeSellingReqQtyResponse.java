
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
 *         &lt;element name="ChangeSellingReqQtyResult" type="{http://epicor.com/schemas}SalesOrderDataSetType" minOccurs="0"/>
 *         &lt;element name="opWarningMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "changeSellingReqQtyResult",
    "opWarningMsg",
    "callContextOut"
})
@XmlRootElement(name = "ChangeSellingReqQtyResponse")
public class ChangeSellingReqQtyResponse {

    @XmlElement(name = "ChangeSellingReqQtyResult", namespace = "http://epicor.com/webservices/")
    protected SalesOrderDataSetType changeSellingReqQtyResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String opWarningMsg;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the changeSellingReqQtyResult property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public SalesOrderDataSetType getChangeSellingReqQtyResult() {
        return changeSellingReqQtyResult;
    }

    /**
     * Sets the value of the changeSellingReqQtyResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public void setChangeSellingReqQtyResult(SalesOrderDataSetType value) {
        this.changeSellingReqQtyResult = value;
    }

    /**
     * Gets the value of the opWarningMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpWarningMsg() {
        return opWarningMsg;
    }

    /**
     * Sets the value of the opWarningMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpWarningMsg(String value) {
        this.opWarningMsg = value;
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
