
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
 *         &lt;element name="ipPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipRevisionNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipForRevision" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "ipPartNum",
    "ipRevisionNum",
    "ipForRevision",
    "callContextIn"
})
@XmlRootElement(name = "CheckConfigForDelete")
public class CheckConfigForDelete {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ipPartNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ipRevisionNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean ipForRevision;
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
     * Gets the value of the ipPartNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpPartNum() {
        return ipPartNum;
    }

    /**
     * Sets the value of the ipPartNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpPartNum(String value) {
        this.ipPartNum = value;
    }

    /**
     * Gets the value of the ipRevisionNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpRevisionNum() {
        return ipRevisionNum;
    }

    /**
     * Sets the value of the ipRevisionNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpRevisionNum(String value) {
        this.ipRevisionNum = value;
    }

    /**
     * Gets the value of the ipForRevision property.
     * 
     */
    public boolean isIpForRevision() {
        return ipForRevision;
    }

    /**
     * Sets the value of the ipForRevision property.
     * 
     */
    public void setIpForRevision(boolean value) {
        this.ipForRevision = value;
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
