
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
 *         &lt;element name="ConstructExampleStringResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="opExampleStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "constructExampleStringResult",
    "opExampleStr",
    "callContextOut"
})
@XmlRootElement(name = "ConstructExampleStringResponse")
public class ConstructExampleStringResponse {

    @XmlElement(name = "ConstructExampleStringResult", namespace = "http://epicor.com/webservices/")
    protected boolean constructExampleStringResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String opExampleStr;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the constructExampleStringResult property.
     * 
     */
    public boolean isConstructExampleStringResult() {
        return constructExampleStringResult;
    }

    /**
     * Sets the value of the constructExampleStringResult property.
     * 
     */
    public void setConstructExampleStringResult(boolean value) {
        this.constructExampleStringResult = value;
    }

    /**
     * Gets the value of the opExampleStr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpExampleStr() {
        return opExampleStr;
    }

    /**
     * Sets the value of the opExampleStr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpExampleStr(String value) {
        this.opExampleStr = value;
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
