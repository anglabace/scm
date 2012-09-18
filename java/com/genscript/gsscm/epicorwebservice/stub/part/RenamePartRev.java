
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
 *         &lt;element name="CompanyID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourcePartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourcePartRevNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetPartRevNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetPartRevDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="callContextIn" type="{http://epicor.com/schemas}CallContextDataSetType" minOccurs="0"/>
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
    "companyID",
    "sourcePartNum",
    "sourcePartRevNum",
    "targetPartRevNum",
    "targetPartRevDescription",
    "callContextIn"
})
@XmlRootElement(name = "RenamePartRev")
public class RenamePartRev {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String sourcePartNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String sourcePartRevNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String targetPartRevNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String targetPartRevDescription;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextIn;

    /**
     * Gets the value of the companyID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyID() {
        return companyID;
    }

    /**
     * Sets the value of the companyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyID(String value) {
        this.companyID = value;
    }

    /**
     * Gets the value of the sourcePartNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourcePartNum() {
        return sourcePartNum;
    }

    /**
     * Sets the value of the sourcePartNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourcePartNum(String value) {
        this.sourcePartNum = value;
    }

    /**
     * Gets the value of the sourcePartRevNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourcePartRevNum() {
        return sourcePartRevNum;
    }

    /**
     * Sets the value of the sourcePartRevNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourcePartRevNum(String value) {
        this.sourcePartRevNum = value;
    }

    /**
     * Gets the value of the targetPartRevNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetPartRevNum() {
        return targetPartRevNum;
    }

    /**
     * Sets the value of the targetPartRevNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetPartRevNum(String value) {
        this.targetPartRevNum = value;
    }

    /**
     * Gets the value of the targetPartRevDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetPartRevDescription() {
        return targetPartRevDescription;
    }

    /**
     * Sets the value of the targetPartRevDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetPartRevDescription(String value) {
        this.targetPartRevDescription = value;
    }

    /**
     * Gets the value of the callContextIn property.
     * 
     * @return
     *     possible object is
     *     {@link CallContextDataSetType }
     *     
     */
    public CallContextDataSetType getCallContextIn() {
        return callContextIn;
    }

    /**
     * Sets the value of the callContextIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link CallContextDataSetType }
     *     
     */
    public void setCallContextIn(CallContextDataSetType value) {
        this.callContextIn = value;
    }

}
