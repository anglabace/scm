
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
 *         &lt;element name="GetPlantConfCtrlInfoResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="opSuppressWarning" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "getPlantConfCtrlInfoResult",
    "opSuppressWarning",
    "callContextOut"
})
@XmlRootElement(name = "GetPlantConfCtrlInfoResponse")
public class GetPlantConfCtrlInfoResponse {

    @XmlElement(name = "GetPlantConfCtrlInfoResult", namespace = "http://epicor.com/webservices/")
    protected boolean getPlantConfCtrlInfoResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean opSuppressWarning;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the getPlantConfCtrlInfoResult property.
     * 
     */
    public boolean isGetPlantConfCtrlInfoResult() {
        return getPlantConfCtrlInfoResult;
    }

    /**
     * Sets the value of the getPlantConfCtrlInfoResult property.
     * 
     */
    public void setGetPlantConfCtrlInfoResult(boolean value) {
        this.getPlantConfCtrlInfoResult = value;
    }

    /**
     * Gets the value of the opSuppressWarning property.
     * 
     */
    public boolean isOpSuppressWarning() {
        return opSuppressWarning;
    }

    /**
     * Sets the value of the opSuppressWarning property.
     * 
     */
    public void setOpSuppressWarning(boolean value) {
        this.opSuppressWarning = value;
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
