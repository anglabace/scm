
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
 *         &lt;element name="GetDisabledFieldsResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fieldList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getDisabledFieldsResult",
    "fieldList",
    "callContextOut"
})
@XmlRootElement(name = "GetDisabledFieldsResponse")
public class GetDisabledFieldsResponse {

    @XmlElement(name = "GetDisabledFieldsResult", namespace = "http://epicor.com/webservices/")
    protected boolean getDisabledFieldsResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String fieldList;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the getDisabledFieldsResult property.
     * 
     */
    public boolean isGetDisabledFieldsResult() {
        return getDisabledFieldsResult;
    }

    /**
     * Sets the value of the getDisabledFieldsResult property.
     * 
     */
    public void setGetDisabledFieldsResult(boolean value) {
        this.getDisabledFieldsResult = value;
    }

    /**
     * Gets the value of the fieldList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldList() {
        return fieldList;
    }

    /**
     * Sets the value of the fieldList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldList(String value) {
        this.fieldList = value;
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
