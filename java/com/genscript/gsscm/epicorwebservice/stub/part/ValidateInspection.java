
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
 *         &lt;element name="ipProposedInspPlan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipProposedSpecId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iptable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ds" type="{http://epicor.com/schemas}PartDataSetType" minOccurs="0"/>
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
    "ipProposedInspPlan",
    "ipProposedSpecId",
    "iptable",
    "ds",
    "callContextIn"
})
@XmlRootElement(name = "ValidateInspection")
public class ValidateInspection {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ipProposedInspPlan;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ipProposedSpecId;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String iptable;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected PartDataSetType ds;
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
     * Gets the value of the ipProposedInspPlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpProposedInspPlan() {
        return ipProposedInspPlan;
    }

    /**
     * Sets the value of the ipProposedInspPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpProposedInspPlan(String value) {
        this.ipProposedInspPlan = value;
    }

    /**
     * Gets the value of the ipProposedSpecId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpProposedSpecId() {
        return ipProposedSpecId;
    }

    /**
     * Sets the value of the ipProposedSpecId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpProposedSpecId(String value) {
        this.ipProposedSpecId = value;
    }

    /**
     * Gets the value of the iptable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIptable() {
        return iptable;
    }

    /**
     * Sets the value of the iptable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIptable(String value) {
        this.iptable = value;
    }

    /**
     * Gets the value of the ds property.
     * 
     * @return
     *     possible object is
     *     {@link PartDataSetType }
     *     
     */
    public PartDataSetType getDs() {
        return ds;
    }

    /**
     * Sets the value of the ds property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartDataSetType }
     *     
     */
    public void setDs(PartDataSetType value) {
        this.ds = value;
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
