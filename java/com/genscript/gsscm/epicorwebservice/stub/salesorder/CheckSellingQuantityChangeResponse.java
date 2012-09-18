
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
 *         &lt;element name="CheckSellingQuantityChangeResult" type="{http://epicor.com/schemas}SalesOrderDataSetType" minOccurs="0"/>
 *         &lt;element name="cSellingQuantityChangedMsgText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "checkSellingQuantityChangeResult",
    "cSellingQuantityChangedMsgText",
    "callContextOut"
})
@XmlRootElement(name = "CheckSellingQuantityChangeResponse")
public class CheckSellingQuantityChangeResponse {

    @XmlElement(name = "CheckSellingQuantityChangeResult", namespace = "http://epicor.com/webservices/")
    protected SalesOrderDataSetType checkSellingQuantityChangeResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String cSellingQuantityChangedMsgText;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the checkSellingQuantityChangeResult property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public SalesOrderDataSetType getCheckSellingQuantityChangeResult() {
        return checkSellingQuantityChangeResult;
    }

    /**
     * Sets the value of the checkSellingQuantityChangeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public void setCheckSellingQuantityChangeResult(SalesOrderDataSetType value) {
        this.checkSellingQuantityChangeResult = value;
    }

    /**
     * Gets the value of the cSellingQuantityChangedMsgText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCSellingQuantityChangedMsgText() {
        return cSellingQuantityChangedMsgText;
    }

    /**
     * Sets the value of the cSellingQuantityChangedMsgText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCSellingQuantityChangedMsgText(String value) {
        this.cSellingQuantityChangedMsgText = value;
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
