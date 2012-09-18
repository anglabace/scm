
package com.genscript.gsscm.epicorwebservice.stub.lot;

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
 *         &lt;element name="GenerateNewLotNumResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="vNewLotNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "generateNewLotNumResult",
    "vNewLotNum",
    "callContextOut"
})
@XmlRootElement(name = "GenerateNewLotNumResponse")
public class GenerateNewLotNumResponse {

    @XmlElement(name = "GenerateNewLotNumResult", namespace = "http://epicor.com/webservices/")
    protected boolean generateNewLotNumResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String vNewLotNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the generateNewLotNumResult property.
     * 
     */
    public boolean isGenerateNewLotNumResult() {
        return generateNewLotNumResult;
    }

    /**
     * Sets the value of the generateNewLotNumResult property.
     * 
     */
    public void setGenerateNewLotNumResult(boolean value) {
        this.generateNewLotNumResult = value;
    }

    /**
     * Gets the value of the vNewLotNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVNewLotNum() {
        return vNewLotNum;
    }

    /**
     * Sets the value of the vNewLotNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVNewLotNum(String value) {
        this.vNewLotNum = value;
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
