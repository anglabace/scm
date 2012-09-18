
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
 *         &lt;element name="ShipToETCValAddrResult" type="{http://epicor.com/schemas}ETCAddrValidationDataSetType" minOccurs="0"/>
 *         &lt;element name="statusFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="errorFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="errorMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "shipToETCValAddrResult",
    "statusFlag",
    "errorFlag",
    "errorMsg",
    "callContextOut"
})
@XmlRootElement(name = "ShipToETCValAddrResponse")
public class ShipToETCValAddrResponse {

    @XmlElement(name = "ShipToETCValAddrResult", namespace = "http://epicor.com/webservices/")
    protected ETCAddrValidationDataSetType shipToETCValAddrResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean statusFlag;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean errorFlag;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String errorMsg;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the shipToETCValAddrResult property.
     * 
     * @return
     *     possible object is
     *     {@link ETCAddrValidationDataSetType }
     *     
     */
    public ETCAddrValidationDataSetType getShipToETCValAddrResult() {
        return shipToETCValAddrResult;
    }

    /**
     * Sets the value of the shipToETCValAddrResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ETCAddrValidationDataSetType }
     *     
     */
    public void setShipToETCValAddrResult(ETCAddrValidationDataSetType value) {
        this.shipToETCValAddrResult = value;
    }

    /**
     * Gets the value of the statusFlag property.
     * 
     */
    public boolean isStatusFlag() {
        return statusFlag;
    }

    /**
     * Sets the value of the statusFlag property.
     * 
     */
    public void setStatusFlag(boolean value) {
        this.statusFlag = value;
    }

    /**
     * Gets the value of the errorFlag property.
     * 
     */
    public boolean isErrorFlag() {
        return errorFlag;
    }

    /**
     * Sets the value of the errorFlag property.
     * 
     */
    public void setErrorFlag(boolean value) {
        this.errorFlag = value;
    }

    /**
     * Gets the value of the errorMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Sets the value of the errorMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMsg(String value) {
        this.errorMsg = value;
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
