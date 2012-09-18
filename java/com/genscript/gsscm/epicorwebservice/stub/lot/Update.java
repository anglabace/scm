
package com.genscript.gsscm.epicorwebservice.stub.lot;

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
 *         &lt;element name="LotSelectUpdateData" type="{http://epicor.com/schemas}LotSelectUpdateDataSetType" minOccurs="0"/>
 *         &lt;element name="continueProcessingOnError" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "lotSelectUpdateData",
    "continueProcessingOnError",
    "callContextIn"
})
@XmlRootElement(name = "Update")
public class Update {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(name = "LotSelectUpdateData", namespace = "http://epicor.com/webservices/")
    protected LotSelectUpdateDataSetType lotSelectUpdateData;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean continueProcessingOnError;
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
     * Gets the value of the lotSelectUpdateData property.
     * 
     * @return
     *     possible object is
     *     {@link LotSelectUpdateDataSetType }
     *     
     */
    public LotSelectUpdateDataSetType getLotSelectUpdateData() {
        return lotSelectUpdateData;
    }

    /**
     * Sets the value of the lotSelectUpdateData property.
     * 
     * @param value
     *     allowed object is
     *     {@link LotSelectUpdateDataSetType }
     *     
     */
    public void setLotSelectUpdateData(LotSelectUpdateDataSetType value) {
        this.lotSelectUpdateData = value;
    }

    /**
     * Gets the value of the continueProcessingOnError property.
     * 
     */
    public boolean isContinueProcessingOnError() {
        return continueProcessingOnError;
    }

    /**
     * Sets the value of the continueProcessingOnError property.
     * 
     */
    public void setContinueProcessingOnError(boolean value) {
        this.continueProcessingOnError = value;
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
