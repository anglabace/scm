
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
 *         &lt;element name="ChkForNeedsLotAttrsResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="needsLotAttrs" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "chkForNeedsLotAttrsResult",
    "needsLotAttrs",
    "callContextOut"
})
@XmlRootElement(name = "ChkForNeedsLotAttrsResponse")
public class ChkForNeedsLotAttrsResponse {

    @XmlElement(name = "ChkForNeedsLotAttrsResult", namespace = "http://epicor.com/webservices/")
    protected boolean chkForNeedsLotAttrsResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean needsLotAttrs;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the chkForNeedsLotAttrsResult property.
     * 
     */
    public boolean isChkForNeedsLotAttrsResult() {
        return chkForNeedsLotAttrsResult;
    }

    /**
     * Sets the value of the chkForNeedsLotAttrsResult property.
     * 
     */
    public void setChkForNeedsLotAttrsResult(boolean value) {
        this.chkForNeedsLotAttrsResult = value;
    }

    /**
     * Gets the value of the needsLotAttrs property.
     * 
     */
    public boolean isNeedsLotAttrs() {
        return needsLotAttrs;
    }

    /**
     * Sets the value of the needsLotAttrs property.
     * 
     */
    public void setNeedsLotAttrs(boolean value) {
        this.needsLotAttrs = value;
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
