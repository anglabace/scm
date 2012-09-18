
package com.genscript.gsscm.epicorwebservice.stub.inventory;

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
 *         &lt;element name="GetAvailTranDocTypesResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="listAvailTranDocTypes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getAvailTranDocTypesResult",
    "listAvailTranDocTypes",
    "callContextOut"
})
@XmlRootElement(name = "GetAvailTranDocTypesResponse")
public class GetAvailTranDocTypesResponse {

    @XmlElement(name = "GetAvailTranDocTypesResult", namespace = "http://epicor.com/webservices/")
    protected boolean getAvailTranDocTypesResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String listAvailTranDocTypes;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the getAvailTranDocTypesResult property.
     * 
     */
    public boolean isGetAvailTranDocTypesResult() {
        return getAvailTranDocTypesResult;
    }

    /**
     * Sets the value of the getAvailTranDocTypesResult property.
     * 
     */
    public void setGetAvailTranDocTypesResult(boolean value) {
        this.getAvailTranDocTypesResult = value;
    }

    /**
     * Gets the value of the listAvailTranDocTypes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListAvailTranDocTypes() {
        return listAvailTranDocTypes;
    }

    /**
     * Sets the value of the listAvailTranDocTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListAvailTranDocTypes(String value) {
        this.listAvailTranDocTypes = value;
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
