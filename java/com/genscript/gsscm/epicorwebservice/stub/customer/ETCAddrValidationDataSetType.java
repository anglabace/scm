
package com.genscript.gsscm.epicorwebservice.stub.customer;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ETCAddrValidationDataSetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ETCAddrValidationDataSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ETCAddrValidationDataSet">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="ETCAddress">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Line1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Line2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Line3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AddrSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AddrSourceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UpdateAddr" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UpdateAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="RequestID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="AddressCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AddressType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CarrierRoute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ValidCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ValidCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="County" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FipsCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ValidLine1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ValidLine2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ValidLine3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ValidLine4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ValidPostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PostNet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ValidRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ResultCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ResultSeq" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="CarrierRouteDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AddressTypeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="InvoiceNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="InvoiceLine" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="ETCMessage">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Details" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Helplink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="RefersTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Severity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Summary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="AddrSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AddrSourceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RequestID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="WebServiceErrors">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="TableName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ErrorLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ErrorType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ErrorText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ETCAddrValidationDataSetType", namespace = "http://epicor.com/schemas", propOrder = {
    "etcAddrValidationDataSet"
})
public class ETCAddrValidationDataSetType {

    @XmlElement(name = "ETCAddrValidationDataSet", required = true)
    protected ETCAddrValidationDataSetType.ETCAddrValidationDataSet etcAddrValidationDataSet;

    /**
     * Gets the value of the etcAddrValidationDataSet property.
     * 
     * @return
     *     possible object is
     *     {@link ETCAddrValidationDataSetType.ETCAddrValidationDataSet }
     *     
     */
    public ETCAddrValidationDataSetType.ETCAddrValidationDataSet getETCAddrValidationDataSet() {
        return etcAddrValidationDataSet;
    }

    /**
     * Sets the value of the etcAddrValidationDataSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link ETCAddrValidationDataSetType.ETCAddrValidationDataSet }
     *     
     */
    public void setETCAddrValidationDataSet(ETCAddrValidationDataSetType.ETCAddrValidationDataSet value) {
        this.etcAddrValidationDataSet = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice maxOccurs="unbounded" minOccurs="0">
     *         &lt;element name="ETCAddress">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Line1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Line2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Line3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AddrSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AddrSourceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UpdateAddr" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UpdateAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="RequestID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="AddressCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AddressType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CarrierRoute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ValidCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ValidCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="County" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FipsCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ValidLine1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ValidLine2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ValidLine3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ValidLine4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ValidPostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PostNet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ValidRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ResultCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ResultSeq" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="CarrierRouteDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AddressTypeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="InvoiceNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="InvoiceLine" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="ETCMessage">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Details" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Helplink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="RefersTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Severity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Summary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="AddrSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AddrSourceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RequestID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="WebServiceErrors">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="TableName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ErrorLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ErrorType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ErrorText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "etcAddressOrETCMessageOrWebServiceErrors"
    })
    public static class ETCAddrValidationDataSet {

        @XmlElements({
            @XmlElement(name = "ETCAddress", type = ETCAddrValidationDataSetType.ETCAddrValidationDataSet.ETCAddress.class),
            @XmlElement(name = "ETCMessage", type = ETCAddrValidationDataSetType.ETCAddrValidationDataSet.ETCMessage.class),
            @XmlElement(name = "WebServiceErrors", type = ETCAddrValidationDataSetType.ETCAddrValidationDataSet.WebServiceErrors.class)
        })
        protected List<Object> etcAddressOrETCMessageOrWebServiceErrors;

        /**
         * Gets the value of the etcAddressOrETCMessageOrWebServiceErrors property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the etcAddressOrETCMessageOrWebServiceErrors property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getETCAddressOrETCMessageOrWebServiceErrors().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ETCAddrValidationDataSetType.ETCAddrValidationDataSet.ETCAddress }
         * {@link ETCAddrValidationDataSetType.ETCAddrValidationDataSet.ETCMessage }
         * {@link ETCAddrValidationDataSetType.ETCAddrValidationDataSet.WebServiceErrors }
         * 
         * 
         */
        public List<Object> getETCAddressOrETCMessageOrWebServiceErrors() {
            if (etcAddressOrETCMessageOrWebServiceErrors == null) {
                etcAddressOrETCMessageOrWebServiceErrors = new ArrayList<Object>();
            }
            return this.etcAddressOrETCMessageOrWebServiceErrors;
        }


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
         *         &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Line1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Line2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Line3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AddrSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AddrSourceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UpdateAddr" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UpdateAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="RequestID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="AddressCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AddressType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CarrierRoute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ValidCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ValidCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="County" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FipsCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ValidLine1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ValidLine2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ValidLine3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ValidLine4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ValidPostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PostNet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ValidRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ResultCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ResultSeq" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="CarrierRouteDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AddressTypeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="InvoiceNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="InvoiceLine" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
            "company",
            "city",
            "country",
            "line1",
            "line2",
            "line3",
            "postalCode",
            "region",
            "addrSource",
            "addrSourceID",
            "updateAddr",
            "transactionID",
            "updateAllowed",
            "requestID",
            "addressCode",
            "addressType",
            "carrierRoute",
            "validCity",
            "validCountry",
            "county",
            "fipsCode",
            "validLine1",
            "validLine2",
            "validLine3",
            "validLine4",
            "validPostalCode",
            "postNet",
            "validRegion",
            "resultCode",
            "resultSeq",
            "carrierRouteDesc",
            "addressTypeDesc",
            "otsCountry",
            "countryNum",
            "invoiceNum",
            "invoiceLine",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class ETCAddress {

            @XmlElement(name = "Company", required = true)
            protected String company;
            @XmlElement(name = "City")
            protected String city;
            @XmlElement(name = "Country")
            protected String country;
            @XmlElement(name = "Line1")
            protected String line1;
            @XmlElement(name = "Line2")
            protected String line2;
            @XmlElement(name = "Line3")
            protected String line3;
            @XmlElement(name = "PostalCode")
            protected String postalCode;
            @XmlElement(name = "Region")
            protected String region;
            @XmlElement(name = "AddrSource")
            protected String addrSource;
            @XmlElement(name = "AddrSourceID")
            protected String addrSourceID;
            @XmlElement(name = "UpdateAddr")
            protected Boolean updateAddr;
            @XmlElement(name = "TransactionID")
            protected String transactionID;
            @XmlElement(name = "UpdateAllowed")
            protected Boolean updateAllowed;
            @XmlElement(name = "RequestID", required = true)
            protected String requestID;
            @XmlElement(name = "AddressCode")
            protected String addressCode;
            @XmlElement(name = "AddressType")
            protected String addressType;
            @XmlElement(name = "CarrierRoute")
            protected String carrierRoute;
            @XmlElement(name = "ValidCity")
            protected String validCity;
            @XmlElement(name = "ValidCountry")
            protected String validCountry;
            @XmlElement(name = "County")
            protected String county;
            @XmlElement(name = "FipsCode")
            protected String fipsCode;
            @XmlElement(name = "ValidLine1")
            protected String validLine1;
            @XmlElement(name = "ValidLine2")
            protected String validLine2;
            @XmlElement(name = "ValidLine3")
            protected String validLine3;
            @XmlElement(name = "ValidLine4")
            protected String validLine4;
            @XmlElement(name = "ValidPostalCode")
            protected String validPostalCode;
            @XmlElement(name = "PostNet")
            protected String postNet;
            @XmlElement(name = "ValidRegion")
            protected String validRegion;
            @XmlElement(name = "ResultCode")
            protected String resultCode;
            @XmlElement(name = "ResultSeq")
            protected Integer resultSeq;
            @XmlElement(name = "CarrierRouteDesc")
            protected String carrierRouteDesc;
            @XmlElement(name = "AddressTypeDesc")
            protected String addressTypeDesc;
            @XmlElement(name = "OTSCountry")
            protected String otsCountry;
            @XmlElement(name = "CountryNum")
            protected Integer countryNum;
            @XmlElement(name = "InvoiceNum")
            protected Integer invoiceNum;
            @XmlElement(name = "InvoiceLine")
            protected Integer invoiceLine;
            @XmlElement(name = "RowIdent")
            protected String rowIdent;
            @XmlElement(name = "RowMod")
            protected String rowMod;
            @XmlElement(name = "DBRowIdent")
            protected byte[] dbRowIdent;

            /**
             * Gets the value of the company property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCompany() {
                return company;
            }

            /**
             * Sets the value of the company property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCompany(String value) {
                this.company = value;
            }

            /**
             * Gets the value of the city property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCity() {
                return city;
            }

            /**
             * Sets the value of the city property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCity(String value) {
                this.city = value;
            }

            /**
             * Gets the value of the country property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCountry() {
                return country;
            }

            /**
             * Sets the value of the country property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCountry(String value) {
                this.country = value;
            }

            /**
             * Gets the value of the line1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLine1() {
                return line1;
            }

            /**
             * Sets the value of the line1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLine1(String value) {
                this.line1 = value;
            }

            /**
             * Gets the value of the line2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLine2() {
                return line2;
            }

            /**
             * Sets the value of the line2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLine2(String value) {
                this.line2 = value;
            }

            /**
             * Gets the value of the line3 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLine3() {
                return line3;
            }

            /**
             * Sets the value of the line3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLine3(String value) {
                this.line3 = value;
            }

            /**
             * Gets the value of the postalCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPostalCode() {
                return postalCode;
            }

            /**
             * Sets the value of the postalCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPostalCode(String value) {
                this.postalCode = value;
            }

            /**
             * Gets the value of the region property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRegion() {
                return region;
            }

            /**
             * Sets the value of the region property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRegion(String value) {
                this.region = value;
            }

            /**
             * Gets the value of the addrSource property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddrSource() {
                return addrSource;
            }

            /**
             * Sets the value of the addrSource property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddrSource(String value) {
                this.addrSource = value;
            }

            /**
             * Gets the value of the addrSourceID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddrSourceID() {
                return addrSourceID;
            }

            /**
             * Sets the value of the addrSourceID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddrSourceID(String value) {
                this.addrSourceID = value;
            }

            /**
             * Gets the value of the updateAddr property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isUpdateAddr() {
                return updateAddr;
            }

            /**
             * Sets the value of the updateAddr property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setUpdateAddr(Boolean value) {
                this.updateAddr = value;
            }

            /**
             * Gets the value of the transactionID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTransactionID() {
                return transactionID;
            }

            /**
             * Sets the value of the transactionID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTransactionID(String value) {
                this.transactionID = value;
            }

            /**
             * Gets the value of the updateAllowed property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isUpdateAllowed() {
                return updateAllowed;
            }

            /**
             * Sets the value of the updateAllowed property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setUpdateAllowed(Boolean value) {
                this.updateAllowed = value;
            }

            /**
             * Gets the value of the requestID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRequestID() {
                return requestID;
            }

            /**
             * Sets the value of the requestID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRequestID(String value) {
                this.requestID = value;
            }

            /**
             * Gets the value of the addressCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddressCode() {
                return addressCode;
            }

            /**
             * Sets the value of the addressCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddressCode(String value) {
                this.addressCode = value;
            }

            /**
             * Gets the value of the addressType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddressType() {
                return addressType;
            }

            /**
             * Sets the value of the addressType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddressType(String value) {
                this.addressType = value;
            }

            /**
             * Gets the value of the carrierRoute property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCarrierRoute() {
                return carrierRoute;
            }

            /**
             * Sets the value of the carrierRoute property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCarrierRoute(String value) {
                this.carrierRoute = value;
            }

            /**
             * Gets the value of the validCity property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValidCity() {
                return validCity;
            }

            /**
             * Sets the value of the validCity property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValidCity(String value) {
                this.validCity = value;
            }

            /**
             * Gets the value of the validCountry property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValidCountry() {
                return validCountry;
            }

            /**
             * Sets the value of the validCountry property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValidCountry(String value) {
                this.validCountry = value;
            }

            /**
             * Gets the value of the county property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCounty() {
                return county;
            }

            /**
             * Sets the value of the county property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCounty(String value) {
                this.county = value;
            }

            /**
             * Gets the value of the fipsCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFipsCode() {
                return fipsCode;
            }

            /**
             * Sets the value of the fipsCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFipsCode(String value) {
                this.fipsCode = value;
            }

            /**
             * Gets the value of the validLine1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValidLine1() {
                return validLine1;
            }

            /**
             * Sets the value of the validLine1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValidLine1(String value) {
                this.validLine1 = value;
            }

            /**
             * Gets the value of the validLine2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValidLine2() {
                return validLine2;
            }

            /**
             * Sets the value of the validLine2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValidLine2(String value) {
                this.validLine2 = value;
            }

            /**
             * Gets the value of the validLine3 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValidLine3() {
                return validLine3;
            }

            /**
             * Sets the value of the validLine3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValidLine3(String value) {
                this.validLine3 = value;
            }

            /**
             * Gets the value of the validLine4 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValidLine4() {
                return validLine4;
            }

            /**
             * Sets the value of the validLine4 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValidLine4(String value) {
                this.validLine4 = value;
            }

            /**
             * Gets the value of the validPostalCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValidPostalCode() {
                return validPostalCode;
            }

            /**
             * Sets the value of the validPostalCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValidPostalCode(String value) {
                this.validPostalCode = value;
            }

            /**
             * Gets the value of the postNet property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPostNet() {
                return postNet;
            }

            /**
             * Sets the value of the postNet property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPostNet(String value) {
                this.postNet = value;
            }

            /**
             * Gets the value of the validRegion property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValidRegion() {
                return validRegion;
            }

            /**
             * Sets the value of the validRegion property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValidRegion(String value) {
                this.validRegion = value;
            }

            /**
             * Gets the value of the resultCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getResultCode() {
                return resultCode;
            }

            /**
             * Sets the value of the resultCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setResultCode(String value) {
                this.resultCode = value;
            }

            /**
             * Gets the value of the resultSeq property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getResultSeq() {
                return resultSeq;
            }

            /**
             * Sets the value of the resultSeq property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setResultSeq(Integer value) {
                this.resultSeq = value;
            }

            /**
             * Gets the value of the carrierRouteDesc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCarrierRouteDesc() {
                return carrierRouteDesc;
            }

            /**
             * Sets the value of the carrierRouteDesc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCarrierRouteDesc(String value) {
                this.carrierRouteDesc = value;
            }

            /**
             * Gets the value of the addressTypeDesc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddressTypeDesc() {
                return addressTypeDesc;
            }

            /**
             * Sets the value of the addressTypeDesc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddressTypeDesc(String value) {
                this.addressTypeDesc = value;
            }

            /**
             * Gets the value of the otsCountry property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSCountry() {
                return otsCountry;
            }

            /**
             * Sets the value of the otsCountry property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSCountry(String value) {
                this.otsCountry = value;
            }

            /**
             * Gets the value of the countryNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getCountryNum() {
                return countryNum;
            }

            /**
             * Sets the value of the countryNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setCountryNum(Integer value) {
                this.countryNum = value;
            }

            /**
             * Gets the value of the invoiceNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getInvoiceNum() {
                return invoiceNum;
            }

            /**
             * Sets the value of the invoiceNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setInvoiceNum(Integer value) {
                this.invoiceNum = value;
            }

            /**
             * Gets the value of the invoiceLine property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getInvoiceLine() {
                return invoiceLine;
            }

            /**
             * Sets the value of the invoiceLine property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setInvoiceLine(Integer value) {
                this.invoiceLine = value;
            }

            /**
             * Gets the value of the rowIdent property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRowIdent() {
                return rowIdent;
            }

            /**
             * Sets the value of the rowIdent property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRowIdent(String value) {
                this.rowIdent = value;
            }

            /**
             * Gets the value of the rowMod property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRowMod() {
                return rowMod;
            }

            /**
             * Sets the value of the rowMod property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRowMod(String value) {
                this.rowMod = value;
            }

            /**
             * Gets the value of the dbRowIdent property.
             * 
             * @return
             *     possible object is
             *     byte[]
             */
            public byte[] getDBRowIdent() {
                return dbRowIdent;
            }

            /**
             * Sets the value of the dbRowIdent property.
             * 
             * @param value
             *     allowed object is
             *     byte[]
             */
            public void setDBRowIdent(byte[] value) {
                this.dbRowIdent = ((byte[]) value);
            }

        }


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
         *         &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Details" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Helplink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="RefersTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Severity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Summary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="AddrSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AddrSourceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="RequestID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
            "company",
            "details",
            "helplink",
            "name",
            "refersTo",
            "severity",
            "source",
            "summary",
            "transactionID",
            "addrSource",
            "addrSourceID",
            "requestID",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class ETCMessage {

            @XmlElement(name = "Company", required = true)
            protected String company;
            @XmlElement(name = "Details")
            protected String details;
            @XmlElement(name = "Helplink")
            protected String helplink;
            @XmlElement(name = "Name", required = true)
            protected String name;
            @XmlElement(name = "RefersTo")
            protected String refersTo;
            @XmlElement(name = "Severity")
            protected String severity;
            @XmlElement(name = "Source")
            protected String source;
            @XmlElement(name = "Summary")
            protected String summary;
            @XmlElement(name = "TransactionID", required = true)
            protected String transactionID;
            @XmlElement(name = "AddrSource")
            protected String addrSource;
            @XmlElement(name = "AddrSourceID")
            protected String addrSourceID;
            @XmlElement(name = "RequestID", required = true)
            protected String requestID;
            @XmlElement(name = "RowIdent")
            protected String rowIdent;
            @XmlElement(name = "RowMod")
            protected String rowMod;
            @XmlElement(name = "DBRowIdent")
            protected byte[] dbRowIdent;

            /**
             * Gets the value of the company property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCompany() {
                return company;
            }

            /**
             * Sets the value of the company property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCompany(String value) {
                this.company = value;
            }

            /**
             * Gets the value of the details property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDetails() {
                return details;
            }

            /**
             * Sets the value of the details property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDetails(String value) {
                this.details = value;
            }

            /**
             * Gets the value of the helplink property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHelplink() {
                return helplink;
            }

            /**
             * Sets the value of the helplink property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHelplink(String value) {
                this.helplink = value;
            }

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the refersTo property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRefersTo() {
                return refersTo;
            }

            /**
             * Sets the value of the refersTo property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRefersTo(String value) {
                this.refersTo = value;
            }

            /**
             * Gets the value of the severity property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSeverity() {
                return severity;
            }

            /**
             * Sets the value of the severity property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSeverity(String value) {
                this.severity = value;
            }

            /**
             * Gets the value of the source property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSource() {
                return source;
            }

            /**
             * Sets the value of the source property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSource(String value) {
                this.source = value;
            }

            /**
             * Gets the value of the summary property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSummary() {
                return summary;
            }

            /**
             * Sets the value of the summary property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSummary(String value) {
                this.summary = value;
            }

            /**
             * Gets the value of the transactionID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTransactionID() {
                return transactionID;
            }

            /**
             * Sets the value of the transactionID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTransactionID(String value) {
                this.transactionID = value;
            }

            /**
             * Gets the value of the addrSource property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddrSource() {
                return addrSource;
            }

            /**
             * Sets the value of the addrSource property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddrSource(String value) {
                this.addrSource = value;
            }

            /**
             * Gets the value of the addrSourceID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddrSourceID() {
                return addrSourceID;
            }

            /**
             * Sets the value of the addrSourceID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddrSourceID(String value) {
                this.addrSourceID = value;
            }

            /**
             * Gets the value of the requestID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRequestID() {
                return requestID;
            }

            /**
             * Sets the value of the requestID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRequestID(String value) {
                this.requestID = value;
            }

            /**
             * Gets the value of the rowIdent property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRowIdent() {
                return rowIdent;
            }

            /**
             * Sets the value of the rowIdent property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRowIdent(String value) {
                this.rowIdent = value;
            }

            /**
             * Gets the value of the rowMod property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRowMod() {
                return rowMod;
            }

            /**
             * Sets the value of the rowMod property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRowMod(String value) {
                this.rowMod = value;
            }

            /**
             * Gets the value of the dbRowIdent property.
             * 
             * @return
             *     possible object is
             *     byte[]
             */
            public byte[] getDBRowIdent() {
                return dbRowIdent;
            }

            /**
             * Sets the value of the dbRowIdent property.
             * 
             * @param value
             *     allowed object is
             *     byte[]
             */
            public void setDBRowIdent(byte[] value) {
                this.dbRowIdent = ((byte[]) value);
            }

        }


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
         *         &lt;element name="TableName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ErrorLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ErrorType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ErrorText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "tableName",
            "errorLevel",
            "errorType",
            "errorText",
            "sysRowID"
        })
        public static class WebServiceErrors {

            @XmlElement(name = "TableName")
            protected String tableName;
            @XmlElement(name = "ErrorLevel")
            protected String errorLevel;
            @XmlElement(name = "ErrorType")
            protected String errorType;
            @XmlElement(name = "ErrorText")
            protected String errorText;
            @XmlElement(name = "SysRowID")
            protected String sysRowID;

            /**
             * Gets the value of the tableName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTableName() {
                return tableName;
            }

            /**
             * Sets the value of the tableName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTableName(String value) {
                this.tableName = value;
            }

            /**
             * Gets the value of the errorLevel property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getErrorLevel() {
                return errorLevel;
            }

            /**
             * Sets the value of the errorLevel property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setErrorLevel(String value) {
                this.errorLevel = value;
            }

            /**
             * Gets the value of the errorType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getErrorType() {
                return errorType;
            }

            /**
             * Sets the value of the errorType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setErrorType(String value) {
                this.errorType = value;
            }

            /**
             * Gets the value of the errorText property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getErrorText() {
                return errorText;
            }

            /**
             * Sets the value of the errorText property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setErrorText(String value) {
                this.errorText = value;
            }

            /**
             * Gets the value of the sysRowID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSysRowID() {
                return sysRowID;
            }

            /**
             * Sets the value of the sysRowID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSysRowID(String value) {
                this.sysRowID = value;
            }

        }

    }

}
