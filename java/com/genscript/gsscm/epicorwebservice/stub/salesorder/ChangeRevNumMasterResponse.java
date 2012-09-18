
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
 *         &lt;element name="ChangeRevNumMasterResult" type="{http://epicor.com/schemas}SalesOrderDataSetType" minOccurs="0"/>
 *         &lt;element name="cConfigPartMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "changeRevNumMasterResult",
    "cConfigPartMessage",
    "callContextOut"
})
@XmlRootElement(name = "ChangeRevNumMasterResponse")
public class ChangeRevNumMasterResponse {

    @XmlElement(name = "ChangeRevNumMasterResult", namespace = "http://epicor.com/webservices/")
    protected SalesOrderDataSetType changeRevNumMasterResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String cConfigPartMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the changeRevNumMasterResult property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public SalesOrderDataSetType getChangeRevNumMasterResult() {
        return changeRevNumMasterResult;
    }

    /**
     * Sets the value of the changeRevNumMasterResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public void setChangeRevNumMasterResult(SalesOrderDataSetType value) {
        this.changeRevNumMasterResult = value;
    }

    /**
     * Gets the value of the cConfigPartMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCConfigPartMessage() {
        return cConfigPartMessage;
    }

    /**
     * Sets the value of the cConfigPartMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCConfigPartMessage(String value) {
        this.cConfigPartMessage = value;
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
