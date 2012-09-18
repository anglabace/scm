
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
 *         &lt;element name="ChangeSellingQtyMasterResult" type="{http://epicor.com/schemas}SalesOrderDataSetType" minOccurs="0"/>
 *         &lt;element name="pcMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pcNeqQtyAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opWarningMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cSellingQuantityChangedMsgText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "changeSellingQtyMasterResult",
    "pcMessage",
    "pcNeqQtyAction",
    "opWarningMsg",
    "cSellingQuantityChangedMsgText",
    "callContextOut"
})
@XmlRootElement(name = "ChangeSellingQtyMasterResponse")
public class ChangeSellingQtyMasterResponse {

    @XmlElement(name = "ChangeSellingQtyMasterResult", namespace = "http://epicor.com/webservices/")
    protected SalesOrderDataSetType changeSellingQtyMasterResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String pcMessage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String pcNeqQtyAction;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String opWarningMsg;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String cSellingQuantityChangedMsgText;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the changeSellingQtyMasterResult property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public SalesOrderDataSetType getChangeSellingQtyMasterResult() {
        return changeSellingQtyMasterResult;
    }

    /**
     * Sets the value of the changeSellingQtyMasterResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOrderDataSetType }
     *     
     */
    public void setChangeSellingQtyMasterResult(SalesOrderDataSetType value) {
        this.changeSellingQtyMasterResult = value;
    }

    /**
     * Gets the value of the pcMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcMessage() {
        return pcMessage;
    }

    /**
     * Sets the value of the pcMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcMessage(String value) {
        this.pcMessage = value;
    }

    /**
     * Gets the value of the pcNeqQtyAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcNeqQtyAction() {
        return pcNeqQtyAction;
    }

    /**
     * Sets the value of the pcNeqQtyAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcNeqQtyAction(String value) {
        this.pcNeqQtyAction = value;
    }

    /**
     * Gets the value of the opWarningMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpWarningMsg() {
        return opWarningMsg;
    }

    /**
     * Sets the value of the opWarningMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpWarningMsg(String value) {
        this.opWarningMsg = value;
    }

    /**
     * Gets the value of the cSellingQuantityChangedMsgText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCSellingQuantityChangedMsgText() {
        return cSellingQuantityChangedMsgText;
    }

    /**
     * Sets the value of the cSellingQuantityChangedMsgText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCSellingQuantityChangedMsgText(String value) {
        this.cSellingQuantityChangedMsgText = value;
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
