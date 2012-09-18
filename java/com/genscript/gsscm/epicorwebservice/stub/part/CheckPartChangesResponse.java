
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
 *         &lt;element name="CheckPartChangesResult" type="{http://epicor.com/schemas}PartDataSetType" minOccurs="0"/>
 *         &lt;element name="cPartChangedMsgText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cPartSNChangedMsgText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "checkPartChangesResult",
    "cPartChangedMsgText",
    "cPartSNChangedMsgText",
    "callContextOut"
})
@XmlRootElement(name = "CheckPartChangesResponse")
public class CheckPartChangesResponse {

    @XmlElement(name = "CheckPartChangesResult", namespace = "http://epicor.com/webservices/")
    protected PartDataSetType checkPartChangesResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String cPartChangedMsgText;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String cPartSNChangedMsgText;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the checkPartChangesResult property.
     * 
     * @return
     *     possible object is
     *     {@link PartDataSetType }
     *     
     */
    public PartDataSetType getCheckPartChangesResult() {
        return checkPartChangesResult;
    }

    /**
     * Sets the value of the checkPartChangesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartDataSetType }
     *     
     */
    public void setCheckPartChangesResult(PartDataSetType value) {
        this.checkPartChangesResult = value;
    }

    /**
     * Gets the value of the cPartChangedMsgText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPartChangedMsgText() {
        return cPartChangedMsgText;
    }

    /**
     * Sets the value of the cPartChangedMsgText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPartChangedMsgText(String value) {
        this.cPartChangedMsgText = value;
    }

    /**
     * Gets the value of the cPartSNChangedMsgText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPartSNChangedMsgText() {
        return cPartSNChangedMsgText;
    }

    /**
     * Sets the value of the cPartSNChangedMsgText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPartSNChangedMsgText(String value) {
        this.cPartSNChangedMsgText = value;
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
