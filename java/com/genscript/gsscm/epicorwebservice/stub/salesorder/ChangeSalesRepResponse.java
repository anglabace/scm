
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
 *         &lt;element name="ChangeSalesRepResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="dRepRate" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="iRepSplit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cRepName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "changeSalesRepResult",
    "dRepRate",
    "iRepSplit",
    "cRepName",
    "callContextOut"
})
@XmlRootElement(name = "ChangeSalesRepResponse")
public class ChangeSalesRepResponse {

    @XmlElement(name = "ChangeSalesRepResult", namespace = "http://epicor.com/webservices/")
    protected boolean changeSalesRepResult;
    @XmlElement(namespace = "http://epicor.com/webservices/", required = true)
    protected BigDecimal dRepRate;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int iRepSplit;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String cRepName;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the changeSalesRepResult property.
     * 
     */
    public boolean isChangeSalesRepResult() {
        return changeSalesRepResult;
    }

    /**
     * Sets the value of the changeSalesRepResult property.
     * 
     */
    public void setChangeSalesRepResult(boolean value) {
        this.changeSalesRepResult = value;
    }

    /**
     * Gets the value of the dRepRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDRepRate() {
        return dRepRate;
    }

    /**
     * Sets the value of the dRepRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDRepRate(BigDecimal value) {
        this.dRepRate = value;
    }

    /**
     * Gets the value of the iRepSplit property.
     * 
     */
    public int getIRepSplit() {
        return iRepSplit;
    }

    /**
     * Sets the value of the iRepSplit property.
     * 
     */
    public void setIRepSplit(int value) {
        this.iRepSplit = value;
    }

    /**
     * Gets the value of the cRepName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCRepName() {
        return cRepName;
    }

    /**
     * Sets the value of the cRepName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCRepName(String value) {
        this.cRepName = value;
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
