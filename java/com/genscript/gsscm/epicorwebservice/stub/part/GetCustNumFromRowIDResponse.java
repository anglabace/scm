
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
 *         &lt;element name="GetCustNumFromRowIDResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="opPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "getCustNumFromRowIDResult",
    "opPartNum",
    "opCustNum",
    "callContextOut"
})
@XmlRootElement(name = "GetCustNumFromRowIDResponse")
public class GetCustNumFromRowIDResponse {

    @XmlElement(name = "GetCustNumFromRowIDResult", namespace = "http://epicor.com/webservices/")
    protected boolean getCustNumFromRowIDResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String opPartNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int opCustNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the getCustNumFromRowIDResult property.
     * 
     */
    public boolean isGetCustNumFromRowIDResult() {
        return getCustNumFromRowIDResult;
    }

    /**
     * Sets the value of the getCustNumFromRowIDResult property.
     * 
     */
    public void setGetCustNumFromRowIDResult(boolean value) {
        this.getCustNumFromRowIDResult = value;
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
     * Gets the value of the opCustNum property.
     * 
     */
    public int getOpCustNum() {
        return opCustNum;
    }

    /**
     * Sets the value of the opCustNum property.
     * 
     */
    public void setOpCustNum(int value) {
        this.opCustNum = value;
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
