
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
 *         &lt;element name="ETCValidateAddressResult" type="{http://epicor.com/schemas}ETCAddrValidationDataSetType" minOccurs="0"/>
 *         &lt;element name="statusFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="errorFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="errorMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exceptionFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "etcValidateAddressResult",
    "statusFlag",
    "errorFlag",
    "errorMsg",
    "exceptionFlag",
    "callContextOut"
})
@XmlRootElement(name = "ETCValidateAddressResponse")
public class ETCValidateAddressResponse {

    @XmlElement(name = "ETCValidateAddressResult", namespace = "http://epicor.com/webservices/")
    protected ETCAddrValidationDataSetType etcValidateAddressResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean statusFlag;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean errorFlag;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String errorMsg;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean exceptionFlag;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the etcValidateAddressResult property.
     * 
     * @return
     *     possible object is
     *     {@link ETCAddrValidationDataSetType }
     *     
     */
    public ETCAddrValidationDataSetType getETCValidateAddressResult() {
        return etcValidateAddressResult;
    }

    /**
     * Sets the value of the etcValidateAddressResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ETCAddrValidationDataSetType }
     *     
     */
    public void setETCValidateAddressResult(ETCAddrValidationDataSetType value) {
        this.etcValidateAddressResult = value;
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
     * Gets the value of the exceptionFlag property.
     * 
     */
    public boolean isExceptionFlag() {
        return exceptionFlag;
    }

    /**
     * Sets the value of the exceptionFlag property.
     * 
     */
    public void setExceptionFlag(boolean value) {
        this.exceptionFlag = value;
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
