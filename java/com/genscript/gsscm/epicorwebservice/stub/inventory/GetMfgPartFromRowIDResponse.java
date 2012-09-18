
package com.genscript.gsscm.epicorwebservice.stub.inventory;

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
 *         &lt;element name="GetMfgPartFromRowIDResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="opPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opMfgNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="opMfgPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getMfgPartFromRowIDResult",
    "opPartNum",
    "opMfgNum",
    "opMfgPartNum",
    "callContextOut"
})
@XmlRootElement(name = "GetMfgPartFromRowIDResponse")
public class GetMfgPartFromRowIDResponse {

    @XmlElement(name = "GetMfgPartFromRowIDResult", namespace = "http://epicor.com/webservices/")
    protected boolean getMfgPartFromRowIDResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String opPartNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int opMfgNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String opMfgPartNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the getMfgPartFromRowIDResult property.
     * 
     */
    public boolean isGetMfgPartFromRowIDResult() {
        return getMfgPartFromRowIDResult;
    }

    /**
     * Sets the value of the getMfgPartFromRowIDResult property.
     * 
     */
    public void setGetMfgPartFromRowIDResult(boolean value) {
        this.getMfgPartFromRowIDResult = value;
    }

    /**
     * Gets the value of the opPartNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpPartNum() {
        return opPartNum;
    }

    /**
     * Sets the value of the opPartNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpPartNum(String value) {
        this.opPartNum = value;
    }

    /**
     * Gets the value of the opMfgNum property.
     * 
     */
    public int getOpMfgNum() {
        return opMfgNum;
    }

    /**
     * Sets the value of the opMfgNum property.
     * 
     */
    public void setOpMfgNum(int value) {
        this.opMfgNum = value;
    }

    /**
     * Gets the value of the opMfgPartNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpMfgPartNum() {
        return opMfgPartNum;
    }

    /**
     * Sets the value of the opMfgPartNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpMfgPartNum(String value) {
        this.opMfgPartNum = value;
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
