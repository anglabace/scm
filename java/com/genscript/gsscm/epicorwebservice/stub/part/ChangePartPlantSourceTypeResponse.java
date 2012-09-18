
package com.genscript.gsscm.epicorwebservice.stub.part;

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
 *         &lt;element name="ChangePartPlantSourceTypeResult" type="{http://epicor.com/schemas}PartDataSetType" minOccurs="0"/>
 *         &lt;element name="ruleMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="singleLevelConfMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "changePartPlantSourceTypeResult",
    "ruleMessage",
    "singleLevelConfMsg",
    "callContextOut"
})
@XmlRootElement(name = "ChangePartPlantSourceTypeResponse")
public class ChangePartPlantSourceTypeResponse {

    @XmlElement(name = "ChangePartPlantSourceTypeResult", namespace = "http://epicor.com/webservices/")
    protected PartDataSetType changePartPlantSourceTypeResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ruleMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String singleLevelConfMsg;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the changePartPlantSourceTypeResult property.
     * 
     * @return
     *     possible object is
     *     {@link PartDataSetType }
     *     
     */
    public PartDataSetType getChangePartPlantSourceTypeResult() {
        return changePartPlantSourceTypeResult;
    }

    /**
     * Sets the value of the changePartPlantSourceTypeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartDataSetType }
     *     
     */
    public void setChangePartPlantSourceTypeResult(PartDataSetType value) {
        this.changePartPlantSourceTypeResult = value;
    }

    /**
     * Gets the value of the ruleMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuleMessage() {
        return ruleMessage;
    }

    /**
     * Sets the value of the ruleMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuleMessage(String value) {
        this.ruleMessage = value;
    }

    /**
     * Gets the value of the singleLevelConfMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSingleLevelConfMsg() {
        return singleLevelConfMsg;
    }

    /**
     * Sets the value of the singleLevelConfMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSingleLevelConfMsg(String value) {
        this.singleLevelConfMsg = value;
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
