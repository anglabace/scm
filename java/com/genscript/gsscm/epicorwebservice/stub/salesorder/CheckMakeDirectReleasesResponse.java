
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
 *         &lt;element name="CheckMakeDirectReleasesResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="opIssueWarning" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "checkMakeDirectReleasesResult",
    "opIssueWarning",
    "callContextOut"
})
@XmlRootElement(name = "CheckMakeDirectReleasesResponse")
public class CheckMakeDirectReleasesResponse {

    @XmlElement(name = "CheckMakeDirectReleasesResult", namespace = "http://epicor.com/webservices/")
    protected boolean checkMakeDirectReleasesResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean opIssueWarning;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the checkMakeDirectReleasesResult property.
     * 
     */
    public boolean isCheckMakeDirectReleasesResult() {
        return checkMakeDirectReleasesResult;
    }

    /**
     * Sets the value of the checkMakeDirectReleasesResult property.
     * 
     */
    public void setCheckMakeDirectReleasesResult(boolean value) {
        this.checkMakeDirectReleasesResult = value;
    }

    /**
     * Gets the value of the opIssueWarning property.
     * 
     */
    public boolean isOpIssueWarning() {
        return opIssueWarning;
    }

    /**
     * Sets the value of the opIssueWarning property.
     * 
     */
    public void setOpIssueWarning(boolean value) {
        this.opIssueWarning = value;
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
