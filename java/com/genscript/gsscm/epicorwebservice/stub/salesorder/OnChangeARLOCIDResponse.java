
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
 *         &lt;element name="OnChangeARLOCIDResult" type="{http://epicor.com/schemas}SalesOrderDataSetType" minOccurs="0"/>
 *         &lt;element name="opOverwriteValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "onChangeARLOCIDResult",
    "opOverwriteValue",
    "opFieldName",
    "opMsg",
    "callContextOut"
})
@XmlRootElement(name = "OnChangeARLOCIDResponse")
public class OnChangeARLOCIDResponse {

    @XmlElement(name = "OnChangeARLOCIDResult", namespace = "http://epicor.com/webservices/")
    protected SalesOrderDataSetType onChangeARLOCIDResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String opOverwriteValue;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String opFieldName;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String opMsg;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the onChangeARLOCIDResult property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public SalesOrderDataSetType getOnChangeARLOCIDResult() {
        return onChangeARLOCIDResult;
    }

    /**
     * Sets the value of the onChangeARLOCIDResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public void setOnChangeARLOCIDResult(SalesOrderDataSetType value) {
        this.onChangeARLOCIDResult = value;
    }

    /**
     * Gets the value of the opOverwriteValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpOverwriteValue() {
        return opOverwriteValue;
    }

    /**
     * Sets the value of the opOverwriteValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpOverwriteValue(String value) {
        this.opOverwriteValue = value;
    }

    /**
     * Gets the value of the opFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpFieldName() {
        return opFieldName;
    }

    /**
     * Sets the value of the opFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpFieldName(String value) {
        this.opFieldName = value;
    }

    /**
     * Gets the value of the opMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpMsg() {
        return opMsg;
    }

    /**
     * Sets the value of the opMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpMsg(String value) {
        this.opMsg = value;
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
