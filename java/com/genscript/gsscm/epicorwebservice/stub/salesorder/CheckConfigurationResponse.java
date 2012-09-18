
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
 *         &lt;element name="CheckConfigurationResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="canGetDetails" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="needsConfiguration" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="configureRevision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reasonMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "checkConfigurationResult",
    "canGetDetails",
    "needsConfiguration",
    "configureRevision",
    "reasonMessage",
    "callContextOut"
})
@XmlRootElement(name = "CheckConfigurationResponse")
public class CheckConfigurationResponse {

    @XmlElement(name = "CheckConfigurationResult", namespace = "http://epicor.com/webservices/")
    protected boolean checkConfigurationResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean canGetDetails;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean needsConfiguration;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String configureRevision;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String reasonMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the checkConfigurationResult property.
     * 
     */
    public boolean isCheckConfigurationResult() {
        return checkConfigurationResult;
    }

    /**
     * Sets the value of the checkConfigurationResult property.
     * 
     */
    public void setCheckConfigurationResult(boolean value) {
        this.checkConfigurationResult = value;
    }

    /**
     * Gets the value of the canGetDetails property.
     * 
     */
    public boolean isCanGetDetails() {
        return canGetDetails;
    }

    /**
     * Sets the value of the canGetDetails property.
     * 
     */
    public void setCanGetDetails(boolean value) {
        this.canGetDetails = value;
    }

    /**
     * Gets the value of the needsConfiguration property.
     * 
     */
    public boolean isNeedsConfiguration() {
        return needsConfiguration;
    }

    /**
     * Sets the value of the needsConfiguration property.
     * 
     */
    public void setNeedsConfiguration(boolean value) {
        this.needsConfiguration = value;
    }

    /**
     * Gets the value of the configureRevision property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfigureRevision() {
        return configureRevision;
    }

    /**
     * Sets the value of the configureRevision property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfigureRevision(String value) {
        this.configureRevision = value;
    }

    /**
     * Gets the value of the reasonMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonMessage() {
        return reasonMessage;
    }

    /**
     * Sets the value of the reasonMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonMessage(String value) {
        this.reasonMessage = value;
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
