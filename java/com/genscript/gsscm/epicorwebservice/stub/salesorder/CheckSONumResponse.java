
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
 *         &lt;element name="CheckSONumResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="opFoundSO" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="opMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "checkSONumResult",
    "opFoundSO",
    "opMessage",
    "callContextOut"
})
@XmlRootElement(name = "CheckSONumResponse")
public class CheckSONumResponse {

    @XmlElement(name = "CheckSONumResult", namespace = "http://epicor.com/webservices/")
    protected boolean checkSONumResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean opFoundSO;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String opMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the checkSONumResult property.
     * 
     */
    public boolean isCheckSONumResult() {
        return checkSONumResult;
    }

    /**
     * Sets the value of the checkSONumResult property.
     * 
     */
    public void setCheckSONumResult(boolean value) {
        this.checkSONumResult = value;
    }

    /**
     * Gets the value of the opFoundSO property.
     * 
     */
    public boolean isOpFoundSO() {
        return opFoundSO;
    }

    /**
     * Sets the value of the opFoundSO property.
     * 
     */
    public void setOpFoundSO(boolean value) {
        this.opFoundSO = value;
    }

    /**
     * Gets the value of the opMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpMessage() {
        return opMessage;
    }

    /**
     * Sets the value of the opMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpMessage(String value) {
        this.opMessage = value;
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
