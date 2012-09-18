
package com.genscript.gsscm.epicorwebservice.stub.invoiceService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="getPaymentListResult" type="{http://www.genscript.com/}ArrayOfPayment" minOccurs="0"/>
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
    "getPaymentListResult"
})
@XmlRootElement(name = "getPaymentListResponse")
public class GetPaymentListResponse {

    protected ArrayOfPayment getPaymentListResult;

    /**
     * Gets the value of the getPaymentListResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPayment }
     *     
     */
    public ArrayOfPayment getGetPaymentListResult() {
        return getPaymentListResult;
    }

    /**
     * Sets the value of the getPaymentListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPayment }
     *     
     */
    public void setGetPaymentListResult(ArrayOfPayment value) {
        this.getPaymentListResult = value;
    }

}
