
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
 *         &lt;element name="PartIsSalesKitResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isSalesKit" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isPhantom" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "partIsSalesKitResult",
    "isSalesKit",
    "isPhantom",
    "callContextOut"
})
@XmlRootElement(name = "PartIsSalesKitResponse")
public class PartIsSalesKitResponse {

    @XmlElement(name = "PartIsSalesKitResult", namespace = "http://epicor.com/webservices/")
    protected boolean partIsSalesKitResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean isSalesKit;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean isPhantom;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the partIsSalesKitResult property.
     * 
     */
    public boolean isPartIsSalesKitResult() {
        return partIsSalesKitResult;
    }

    /**
     * Sets the value of the partIsSalesKitResult property.
     * 
     */
    public void setPartIsSalesKitResult(boolean value) {
        this.partIsSalesKitResult = value;
    }

    /**
     * Gets the value of the isSalesKit property.
     * 
     */
    public boolean isIsSalesKit() {
        return isSalesKit;
    }

    /**
     * Sets the value of the isSalesKit property.
     * 
     */
    public void setIsSalesKit(boolean value) {
        this.isSalesKit = value;
    }

    /**
     * Gets the value of the isPhantom property.
     * 
     */
    public boolean isIsPhantom() {
        return isPhantom;
    }

    /**
     * Sets the value of the isPhantom property.
     * 
     */
    public void setIsPhantom(boolean value) {
        this.isPhantom = value;
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
