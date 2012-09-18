
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
 *         &lt;element name="GetRowsResult" type="{http://epicor.com/schemas}LotSelectUpdateDataSetType" minOccurs="0"/>
 *         &lt;element name="morePages" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "getRowsResult",
    "morePages",
    "callContextOut"
})
@XmlRootElement(name = "GetRowsResponse")
public class GetRowsResponse {

    @XmlElement(name = "GetRowsResult", namespace = "http://epicor.com/webservices/")
    protected LotSelectUpdateDataSetType getRowsResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean morePages;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the getRowsResult property.
     * 
     * @return
     *     possible object is
     *     {@link LotSelectUpdateDataSetType }
     *     
     */
    public LotSelectUpdateDataSetType getGetRowsResult() {
        return getRowsResult;
    }

    /**
     * Sets the value of the getRowsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link LotSelectUpdateDataSetType }
     *     
     */
    public void setGetRowsResult(LotSelectUpdateDataSetType value) {
        this.getRowsResult = value;
    }

    /**
     * Gets the value of the morePages property.
     * 
     */
    public boolean isMorePages() {
        return morePages;
    }

    /**
     * Sets the value of the morePages property.
     * 
     */
    public void setMorePages(boolean value) {
        this.morePages = value;
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
