
package com.genscript.gsscm.epicorwebservice.stub.customer;

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
 *         &lt;element name="GetBillDayListResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="billDayList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getBillDayListResult",
    "billDayList",
    "callContextOut"
})
@XmlRootElement(name = "GetBillDayListResponse")
public class GetBillDayListResponse {

    @XmlElement(name = "GetBillDayListResult", namespace = "http://epicor.com/webservices/")
    protected boolean getBillDayListResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String billDayList;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the getBillDayListResult property.
     * 
     */
    public boolean isGetBillDayListResult() {
        return getBillDayListResult;
    }

    /**
     * Sets the value of the getBillDayListResult property.
     * 
     */
    public void setGetBillDayListResult(boolean value) {
        this.getBillDayListResult = value;
    }

    /**
     * Gets the value of the billDayList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillDayList() {
        return billDayList;
    }

    /**
     * Sets the value of the billDayList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillDayList(String value) {
        this.billDayList = value;
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
