
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
 *         &lt;element name="FindPartResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="opPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opMatchType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "findPartResult",
    "opPartNum",
    "opUOM",
    "opMatchType",
    "callContextOut"
})
@XmlRootElement(name = "FindPartResponse")
public class FindPartResponse {

    @XmlElement(name = "FindPartResult", namespace = "http://epicor.com/webservices/")
    protected boolean findPartResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String opPartNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String opUOM;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String opMatchType;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the findPartResult property.
     * 
     */
    public boolean isFindPartResult() {
        return findPartResult;
    }

    /**
     * Sets the value of the findPartResult property.
     * 
     */
    public void setFindPartResult(boolean value) {
        this.findPartResult = value;
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
     * Gets the value of the opUOM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpUOM() {
        return opUOM;
    }

    /**
     * Sets the value of the opUOM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpUOM(String value) {
        this.opUOM = value;
    }

    /**
     * Gets the value of the opMatchType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpMatchType() {
        return opMatchType;
    }

    /**
     * Sets the value of the opMatchType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpMatchType(String value) {
        this.opMatchType = value;
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
