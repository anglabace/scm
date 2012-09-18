
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
 *         &lt;element name="GetPartXRefInfoResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="partNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serialWarning" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="questionString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="multipleMatch" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "getPartXRefInfoResult",
    "partNum",
    "serialWarning",
    "questionString",
    "multipleMatch",
    "callContextOut"
})
@XmlRootElement(name = "GetPartXRefInfoResponse")
public class GetPartXRefInfoResponse {

    @XmlElement(name = "GetPartXRefInfoResult", namespace = "http://epicor.com/webservices/")
    protected boolean getPartXRefInfoResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String partNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String serialWarning;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String questionString;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean multipleMatch;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the getPartXRefInfoResult property.
     * 
     */
    public boolean isGetPartXRefInfoResult() {
        return getPartXRefInfoResult;
    }

    /**
     * Sets the value of the getPartXRefInfoResult property.
     * 
     */
    public void setGetPartXRefInfoResult(boolean value) {
        this.getPartXRefInfoResult = value;
    }

    /**
     * Gets the value of the partNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartNum() {
        return partNum;
    }

    /**
     * Sets the value of the partNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartNum(String value) {
        this.partNum = value;
    }

    /**
     * Gets the value of the serialWarning property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialWarning() {
        return serialWarning;
    }

    /**
     * Sets the value of the serialWarning property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialWarning(String value) {
        this.serialWarning = value;
    }

    /**
     * Gets the value of the questionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestionString() {
        return questionString;
    }

    /**
     * Sets the value of the questionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestionString(String value) {
        this.questionString = value;
    }

    /**
     * Gets the value of the multipleMatch property.
     * 
     */
    public boolean isMultipleMatch() {
        return multipleMatch;
    }

    /**
     * Sets the value of the multipleMatch property.
     * 
     */
    public void setMultipleMatch(boolean value) {
        this.multipleMatch = value;
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
