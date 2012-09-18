
package com.genscript.gsscm.epicorwebservice.stub.salesorder;

import java.math.BigDecimal;
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
 *         &lt;element name="ipWhseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipBinNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ipTranType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "ipWhseCode",
    "ipBinNum",
    "ipQuantity",
    "ipTranType",
    "ipRowID",
    "callContextIn"
})
@XmlRootElement(name = "GetSelectSerialNumbersParams")
public class GetSelectSerialNumbersParams {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ipPartNum;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ipWhseCode;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ipBinNum;
    @XmlElement(namespace = "http://epicor.com/webservices/", required = true)
    protected BigDecimal ipQuantity;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ipTranType;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String ipRowID;
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
     * Gets the value of the ipWhseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpWhseCode() {
        return ipWhseCode;
    }

    /**
     * Sets the value of the ipWhseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpWhseCode(String value) {
        this.ipWhseCode = value;
    }

    /**
     * Gets the value of the ipBinNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpBinNum() {
        return ipBinNum;
    }

    /**
     * Sets the value of the ipBinNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpBinNum(String value) {
        this.ipBinNum = value;
    }

    /**
     * Gets the value of the ipQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIpQuantity() {
        return ipQuantity;
    }

    /**
     * Sets the value of the ipQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIpQuantity(BigDecimal value) {
        this.ipQuantity = value;
    }

    /**
     * Gets the value of the ipTranType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpTranType() {
        return ipTranType;
    }

    /**
     * Sets the value of the ipTranType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpTranType(String value) {
        this.ipTranType = value;
    }

    /**
     * Gets the value of the ipRowID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpRowID() {
        return ipRowID;
    }

    /**
     * Sets the value of the ipRowID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpRowID(String value) {
        this.ipRowID = value;
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
