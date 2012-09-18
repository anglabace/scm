
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
 *         &lt;element name="CompanyID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ds" type="{http://epicor.com/schemas}GlbSugPOChgDataSetType" minOccurs="0"/>
 *         &lt;element name="iOrderNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="iOrderLine" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="iOrderRel" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "ds",
    "iOrderNum",
    "iOrderLine",
    "iOrderRel",
    "callContextIn"
})
@XmlRootElement(name = "CreateGlbSugPOChg")
public class CreateGlbSugPOChg {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected GlbSugPOChgDataSetType ds;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int iOrderNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int iOrderLine;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int iOrderRel;
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
     * Gets the value of the ds property.
     * 
     * @return
     *     possible object is
     *     {@link GlbSugPOChgDataSetType }
     *     
     */
    public GlbSugPOChgDataSetType getDs() {
        return ds;
    }

    /**
     * Sets the value of the ds property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlbSugPOChgDataSetType }
     *     
     */
    public void setDs(GlbSugPOChgDataSetType value) {
        this.ds = value;
    }

    /**
     * Gets the value of the iOrderNum property.
     * 
     */
    public int getIOrderNum() {
        return iOrderNum;
    }

    /**
     * Sets the value of the iOrderNum property.
     * 
     */
    public void setIOrderNum(int value) {
        this.iOrderNum = value;
    }

    /**
     * Gets the value of the iOrderLine property.
     * 
     */
    public int getIOrderLine() {
        return iOrderLine;
    }

    /**
     * Sets the value of the iOrderLine property.
     * 
     */
    public void setIOrderLine(int value) {
        this.iOrderLine = value;
    }

    /**
     * Gets the value of the iOrderRel property.
     * 
     */
    public int getIOrderRel() {
        return iOrderRel;
    }

    /**
     * Sets the value of the iOrderRel property.
     * 
     */
    public void setIOrderRel(int value) {
        this.iOrderRel = value;
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
