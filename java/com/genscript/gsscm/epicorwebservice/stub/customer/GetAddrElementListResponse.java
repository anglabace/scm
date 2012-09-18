
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
 *         &lt;element name="GetAddrElementListResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="addrElementList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getAddrElementListResult",
    "addrElementList",
    "callContextOut"
})
@XmlRootElement(name = "GetAddrElementListResponse")
public class GetAddrElementListResponse {

    @XmlElement(name = "GetAddrElementListResult", namespace = "http://epicor.com/webservices/")
    protected boolean getAddrElementListResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String addrElementList;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the getAddrElementListResult property.
     * 
     */
    public boolean isGetAddrElementListResult() {
        return getAddrElementListResult;
    }

    /**
     * Sets the value of the getAddrElementListResult property.
     * 
     */
    public void setGetAddrElementListResult(boolean value) {
        this.getAddrElementListResult = value;
    }

    /**
     * Gets the value of the addrElementList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrElementList() {
        return addrElementList;
    }

    /**
     * Sets the value of the addrElementList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrElementList(String value) {
        this.addrElementList = value;
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
