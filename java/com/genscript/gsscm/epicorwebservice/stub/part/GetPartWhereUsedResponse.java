
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
 *         &lt;element name="GetPartWhereUsedResult" type="{http://epicor.com/schemas}PartWhereUsedDataSetType" minOccurs="0"/>
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
    "getPartWhereUsedResult",
    "morePages",
    "callContextOut"
})
@XmlRootElement(name = "GetPartWhereUsedResponse")
public class GetPartWhereUsedResponse {

    @XmlElement(name = "GetPartWhereUsedResult", namespace = "http://epicor.com/webservices/")
    protected PartWhereUsedDataSetType getPartWhereUsedResult;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected boolean morePages;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextOut;

    /**
     * Gets the value of the getPartWhereUsedResult property.
     * 
     * @return
     *     possible object is
     *     {@link PartWhereUsedDataSetType }
     *     
     */
    public PartWhereUsedDataSetType getGetPartWhereUsedResult() {
        return getPartWhereUsedResult;
    }

    /**
     * Sets the value of the getPartWhereUsedResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartWhereUsedDataSetType }
     *     
     */
    public void setGetPartWhereUsedResult(PartWhereUsedDataSetType value) {
        this.getPartWhereUsedResult = value;
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
