
package com.genscript.gsscm.epicorwebservice.stub.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for InvoiceIStatTrnDataSetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvoiceIStatTrnDataSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InvoiceIStatTrnDataSet">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="IStatTrn">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Flow" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Period" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="CommodityCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Terms" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TransactionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Weight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="ISCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ISShipViaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BorderCrossing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FlowSpec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ISCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="SuppUnits" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="SourceModule" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="InvoiceNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="InvoiceLine" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="Posted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                             &lt;element name="VendorNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="MemoFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                             &lt;element name="ShipViaCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="FOB" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="TaxID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="InvAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="TransDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="NotReported" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ISRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ISOrigCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Number01" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number02" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number03" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number04" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number05" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Date01" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date02" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date03" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date04" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date05" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="CheckBox01" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox02" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox03" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox04" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox05" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="IntCommCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="StampID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ManualEntry" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CustIDSuppID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CommodityCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FOBDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="InvoiceNumDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShipViaWebDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShipViaDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VendorNumAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VendorNumCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VendorNumState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VendorNumZIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VendorNumAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VendorNumCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VendorNumDefaultFOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VendorNumTermsCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VendorNumAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VendorNumCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VendorNumName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VendorNumVendorID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "InvoiceIStatTrnDataSetType", namespace = "http://epicor.com/schemas", propOrder = {
    "invoiceIStatTrnDataSet"
})
public class InvoiceIStatTrnDataSetType {

    @XmlElement(name = "InvoiceIStatTrnDataSet", required = true)
    protected InvoiceIStatTrnDataSetType.InvoiceIStatTrnDataSet invoiceIStatTrnDataSet;

    /**
     * Gets the value of the invoiceIStatTrnDataSet property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceIStatTrnDataSetType.InvoiceIStatTrnDataSet }
     *     
     */
    public InvoiceIStatTrnDataSetType.InvoiceIStatTrnDataSet getInvoiceIStatTrnDataSet() {
        return invoiceIStatTrnDataSet;
    }

    /**
     * Sets the value of the invoiceIStatTrnDataSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceIStatTrnDataSetType.InvoiceIStatTrnDataSet }
     *     
     */
    public void setInvoiceIStatTrnDataSet(InvoiceIStatTrnDataSetType.InvoiceIStatTrnDataSet value) {
        this.invoiceIStatTrnDataSet = value;
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
     *         &lt;element name="IStatTrn">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Flow" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Period" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="CommodityCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Terms" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TransactionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Weight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="ISCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ISShipViaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BorderCrossing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FlowSpec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ISCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="SuppUnits" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="SourceModule" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="InvoiceNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="InvoiceLine" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="Posted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *                   &lt;element name="VendorNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="MemoFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *                   &lt;element name="ShipViaCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="FOB" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="TaxID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="InvAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="TransDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="NotReported" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ISRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ISOrigCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Number01" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number02" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number03" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number04" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number05" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Date01" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date02" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date03" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date04" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date05" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="CheckBox01" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox02" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox03" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox04" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox05" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="IntCommCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="StampID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ManualEntry" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CustIDSuppID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CommodityCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FOBDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="InvoiceNumDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShipViaWebDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShipViaDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VendorNumAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VendorNumCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VendorNumState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VendorNumZIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VendorNumAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VendorNumCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VendorNumDefaultFOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VendorNumTermsCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VendorNumAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VendorNumCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VendorNumName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VendorNumVendorID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "iStatTrnOrWebServiceErrors"
    })
    public static class InvoiceIStatTrnDataSet {

        @XmlElements({
            @XmlElement(name = "IStatTrn", type = InvoiceIStatTrnDataSetType.InvoiceIStatTrnDataSet.IStatTrn.class),
            @XmlElement(name = "WebServiceErrors", type = InvoiceIStatTrnDataSetType.InvoiceIStatTrnDataSet.WebServiceErrors.class)
        })
        protected List<Object> iStatTrnOrWebServiceErrors;

        /**
         * Gets the value of the iStatTrnOrWebServiceErrors property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the iStatTrnOrWebServiceErrors property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIStatTrnOrWebServiceErrors().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link InvoiceIStatTrnDataSetType.InvoiceIStatTrnDataSet.IStatTrn }
         * {@link InvoiceIStatTrnDataSetType.InvoiceIStatTrnDataSet.WebServiceErrors }
         * 
         * 
         */
        public List<Object> getIStatTrnOrWebServiceErrors() {
            if (iStatTrnOrWebServiceErrors == null) {
                iStatTrnOrWebServiceErrors = new ArrayList<Object>();
            }
            return this.iStatTrnOrWebServiceErrors;
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
         *         &lt;element name="Flow" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Period" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="CommodityCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Terms" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TransactionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Weight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="ISCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ISShipViaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BorderCrossing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FlowSpec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ISCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SuppUnits" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SourceModule" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="InvoiceNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="InvoiceLine" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="Posted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
         *         &lt;element name="VendorNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="MemoFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
         *         &lt;element name="ShipViaCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="FOB" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="TaxID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="InvAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="TransDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="NotReported" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ISRegion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ISOrigCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Number01" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number02" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number03" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number04" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number05" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Date01" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date02" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date03" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date04" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date05" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="CheckBox01" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox02" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox03" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox04" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox05" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="IntCommCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="StampID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ManualEntry" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CustIDSuppID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CommodityCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FOBDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="InvoiceNumDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShipViaWebDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShipViaDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VendorNumAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VendorNumCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VendorNumState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VendorNumZIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VendorNumAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VendorNumCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VendorNumDefaultFOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VendorNumTermsCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VendorNumAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VendorNumCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VendorNumName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VendorNumVendorID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "flow",
            "period",
            "commodityCode",
            "amount",
            "terms",
            "transactionType",
            "weight",
            "isCountryCode",
            "isShipViaCode",
            "borderCrossing",
            "flowSpec",
            "isCurrency",
            "description",
            "suppUnits",
            "company",
            "sourceModule",
            "invoiceNum",
            "invoiceLine",
            "posted",
            "vendorNum",
            "memoFlag",
            "shipViaCode",
            "fob",
            "taxID",
            "invAmount",
            "transDate",
            "notReported",
            "isRegion",
            "isOrigCountry",
            "shortChar01",
            "shortChar02",
            "shortChar03",
            "shortChar04",
            "shortChar05",
            "number01",
            "number02",
            "number03",
            "number04",
            "number05",
            "date01",
            "date02",
            "date03",
            "date04",
            "date05",
            "checkBox01",
            "checkBox02",
            "checkBox03",
            "checkBox04",
            "checkBox05",
            "sysRowID",
            "sysRevID",
            "intCommCode",
            "bitFlag",
            "stampID",
            "manualEntry",
            "custIDSuppID",
            "commodityCodeDescription",
            "fobDescription",
            "invoiceNumDescription",
            "shipViaWebDesc",
            "shipViaDescription",
            "vendorNumAddress3",
            "vendorNumCurrencyCode",
            "vendorNumState",
            "vendorNumZIP",
            "vendorNumAddress1",
            "vendorNumCity",
            "vendorNumDefaultFOB",
            "vendorNumTermsCode",
            "vendorNumAddress2",
            "vendorNumCountry",
            "vendorNumName",
            "vendorNumVendorID",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class IStatTrn {

            @XmlElement(name = "Flow", required = true)
            protected String flow;
            @XmlElement(name = "Period", required = true)
            protected String period;
            @XmlElement(name = "CommodityCode", required = true)
            protected String commodityCode;
            @XmlElement(name = "Amount")
            protected BigDecimal amount;
            @XmlElement(name = "Terms")
            protected String terms;
            @XmlElement(name = "TransactionType")
            protected String transactionType;
            @XmlElement(name = "Weight")
            protected BigDecimal weight;
            @XmlElement(name = "ISCountryCode")
            protected String isCountryCode;
            @XmlElement(name = "ISShipViaCode")
            protected String isShipViaCode;
            @XmlElement(name = "BorderCrossing")
            protected String borderCrossing;
            @XmlElement(name = "FlowSpec")
            protected String flowSpec;
            @XmlElement(name = "ISCurrency")
            protected String isCurrency;
            @XmlElement(name = "Description", required = true)
            protected String description;
            @XmlElement(name = "SuppUnits")
            protected BigDecimal suppUnits;
            @XmlElement(name = "Company", required = true)
            protected String company;
            @XmlElement(name = "SourceModule", required = true)
            protected String sourceModule;
            @XmlElement(name = "InvoiceNum", required = true)
            protected String invoiceNum;
            @XmlElement(name = "InvoiceLine")
            protected int invoiceLine;
            @XmlElement(name = "Posted")
            protected boolean posted;
            @XmlElement(name = "VendorNum")
            protected int vendorNum;
            @XmlElement(name = "MemoFlag")
            protected boolean memoFlag;
            @XmlElement(name = "ShipViaCode", required = true)
            protected String shipViaCode;
            @XmlElement(name = "FOB", required = true)
            protected String fob;
            @XmlElement(name = "TaxID")
            protected String taxID;
            @XmlElement(name = "InvAmount")
            protected BigDecimal invAmount;
            @XmlElement(name = "TransDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar transDate;
            @XmlElement(name = "NotReported")
            protected Boolean notReported;
            @XmlElement(name = "ISRegion")
            protected String isRegion;
            @XmlElement(name = "ISOrigCountry")
            protected String isOrigCountry;
            @XmlElement(name = "ShortChar01")
            protected String shortChar01;
            @XmlElement(name = "ShortChar02")
            protected String shortChar02;
            @XmlElement(name = "ShortChar03")
            protected String shortChar03;
            @XmlElement(name = "ShortChar04")
            protected String shortChar04;
            @XmlElement(name = "ShortChar05")
            protected String shortChar05;
            @XmlElement(name = "Number01")
            protected BigDecimal number01;
            @XmlElement(name = "Number02")
            protected BigDecimal number02;
            @XmlElement(name = "Number03")
            protected BigDecimal number03;
            @XmlElement(name = "Number04")
            protected BigDecimal number04;
            @XmlElement(name = "Number05")
            protected BigDecimal number05;
            @XmlElement(name = "Date01")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date01;
            @XmlElement(name = "Date02")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date02;
            @XmlElement(name = "Date03")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date03;
            @XmlElement(name = "Date04")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date04;
            @XmlElement(name = "Date05")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date05;
            @XmlElement(name = "CheckBox01")
            protected Boolean checkBox01;
            @XmlElement(name = "CheckBox02")
            protected Boolean checkBox02;
            @XmlElement(name = "CheckBox03")
            protected Boolean checkBox03;
            @XmlElement(name = "CheckBox04")
            protected Boolean checkBox04;
            @XmlElement(name = "CheckBox05")
            protected Boolean checkBox05;
            @XmlElement(name = "SysRowID")
            protected String sysRowID;
            @XmlElement(name = "SysRevID")
            protected Integer sysRevID;
            @XmlElement(name = "IntCommCode")
            protected String intCommCode;
            @XmlElement(name = "BitFlag")
            protected Integer bitFlag;
            @XmlElement(name = "StampID")
            protected String stampID;
            @XmlElement(name = "ManualEntry")
            protected Boolean manualEntry;
            @XmlElement(name = "CustIDSuppID")
            protected String custIDSuppID;
            @XmlElement(name = "CommodityCodeDescription")
            protected String commodityCodeDescription;
            @XmlElement(name = "FOBDescription")
            protected String fobDescription;
            @XmlElement(name = "InvoiceNumDescription")
            protected String invoiceNumDescription;
            @XmlElement(name = "ShipViaWebDesc")
            protected String shipViaWebDesc;
            @XmlElement(name = "ShipViaDescription")
            protected String shipViaDescription;
            @XmlElement(name = "VendorNumAddress3")
            protected String vendorNumAddress3;
            @XmlElement(name = "VendorNumCurrencyCode")
            protected String vendorNumCurrencyCode;
            @XmlElement(name = "VendorNumState")
            protected String vendorNumState;
            @XmlElement(name = "VendorNumZIP")
            protected String vendorNumZIP;
            @XmlElement(name = "VendorNumAddress1")
            protected String vendorNumAddress1;
            @XmlElement(name = "VendorNumCity")
            protected String vendorNumCity;
            @XmlElement(name = "VendorNumDefaultFOB")
            protected String vendorNumDefaultFOB;
            @XmlElement(name = "VendorNumTermsCode")
            protected String vendorNumTermsCode;
            @XmlElement(name = "VendorNumAddress2")
            protected String vendorNumAddress2;
            @XmlElement(name = "VendorNumCountry")
            protected String vendorNumCountry;
            @XmlElement(name = "VendorNumName")
            protected String vendorNumName;
            @XmlElement(name = "VendorNumVendorID")
            protected String vendorNumVendorID;
            @XmlElement(name = "RowIdent")
            protected String rowIdent;
            @XmlElement(name = "RowMod")
            protected String rowMod;
            @XmlElement(name = "DBRowIdent")
            protected byte[] dbRowIdent;

            /**
             * Gets the value of the flow property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFlow() {
                return flow;
            }

            /**
             * Sets the value of the flow property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFlow(String value) {
                this.flow = value;
            }

            /**
             * Gets the value of the period property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPeriod() {
                return period;
            }

            /**
             * Sets the value of the period property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPeriod(String value) {
                this.period = value;
            }

            /**
             * Gets the value of the commodityCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCommodityCode() {
                return commodityCode;
            }

            /**
             * Sets the value of the commodityCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCommodityCode(String value) {
                this.commodityCode = value;
            }

            /**
             * Gets the value of the amount property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getAmount() {
                return amount;
            }

            /**
             * Sets the value of the amount property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setAmount(BigDecimal value) {
                this.amount = value;
            }

            /**
             * Gets the value of the terms property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTerms() {
                return terms;
            }

            /**
             * Sets the value of the terms property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTerms(String value) {
                this.terms = value;
            }

            /**
             * Gets the value of the transactionType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTransactionType() {
                return transactionType;
            }

            /**
             * Sets the value of the transactionType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTransactionType(String value) {
                this.transactionType = value;
            }

            /**
             * Gets the value of the weight property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getWeight() {
                return weight;
            }

            /**
             * Sets the value of the weight property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setWeight(BigDecimal value) {
                this.weight = value;
            }

            /**
             * Gets the value of the isCountryCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getISCountryCode() {
                return isCountryCode;
            }

            /**
             * Sets the value of the isCountryCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setISCountryCode(String value) {
                this.isCountryCode = value;
            }

            /**
             * Gets the value of the isShipViaCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getISShipViaCode() {
                return isShipViaCode;
            }

            /**
             * Sets the value of the isShipViaCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setISShipViaCode(String value) {
                this.isShipViaCode = value;
            }

            /**
             * Gets the value of the borderCrossing property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBorderCrossing() {
                return borderCrossing;
            }

            /**
             * Sets the value of the borderCrossing property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBorderCrossing(String value) {
                this.borderCrossing = value;
            }

            /**
             * Gets the value of the flowSpec property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFlowSpec() {
                return flowSpec;
            }

            /**
             * Sets the value of the flowSpec property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFlowSpec(String value) {
                this.flowSpec = value;
            }

            /**
             * Gets the value of the isCurrency property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getISCurrency() {
                return isCurrency;
            }

            /**
             * Sets the value of the isCurrency property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setISCurrency(String value) {
                this.isCurrency = value;
            }

            /**
             * Gets the value of the description property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDescription() {
                return description;
            }

            /**
             * Sets the value of the description property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDescription(String value) {
                this.description = value;
            }

            /**
             * Gets the value of the suppUnits property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getSuppUnits() {
                return suppUnits;
            }

            /**
             * Sets the value of the suppUnits property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setSuppUnits(BigDecimal value) {
                this.suppUnits = value;
            }

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
             * Gets the value of the sourceModule property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSourceModule() {
                return sourceModule;
            }

            /**
             * Sets the value of the sourceModule property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSourceModule(String value) {
                this.sourceModule = value;
            }

            /**
             * Gets the value of the invoiceNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getInvoiceNum() {
                return invoiceNum;
            }

            /**
             * Sets the value of the invoiceNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setInvoiceNum(String value) {
                this.invoiceNum = value;
            }

            /**
             * Gets the value of the invoiceLine property.
             * 
             */
            public int getInvoiceLine() {
                return invoiceLine;
            }

            /**
             * Sets the value of the invoiceLine property.
             * 
             */
            public void setInvoiceLine(int value) {
                this.invoiceLine = value;
            }

            /**
             * Gets the value of the posted property.
             * 
             */
            public boolean isPosted() {
                return posted;
            }

            /**
             * Sets the value of the posted property.
             * 
             */
            public void setPosted(boolean value) {
                this.posted = value;
            }

            /**
             * Gets the value of the vendorNum property.
             * 
             */
            public int getVendorNum() {
                return vendorNum;
            }

            /**
             * Sets the value of the vendorNum property.
             * 
             */
            public void setVendorNum(int value) {
                this.vendorNum = value;
            }

            /**
             * Gets the value of the memoFlag property.
             * 
             */
            public boolean isMemoFlag() {
                return memoFlag;
            }

            /**
             * Sets the value of the memoFlag property.
             * 
             */
            public void setMemoFlag(boolean value) {
                this.memoFlag = value;
            }

            /**
             * Gets the value of the shipViaCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShipViaCode() {
                return shipViaCode;
            }

            /**
             * Sets the value of the shipViaCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShipViaCode(String value) {
                this.shipViaCode = value;
            }

            /**
             * Gets the value of the fob property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFOB() {
                return fob;
            }

            /**
             * Sets the value of the fob property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFOB(String value) {
                this.fob = value;
            }

            /**
             * Gets the value of the taxID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTaxID() {
                return taxID;
            }

            /**
             * Sets the value of the taxID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTaxID(String value) {
                this.taxID = value;
            }

            /**
             * Gets the value of the invAmount property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getInvAmount() {
                return invAmount;
            }

            /**
             * Sets the value of the invAmount property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setInvAmount(BigDecimal value) {
                this.invAmount = value;
            }

            /**
             * Gets the value of the transDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getTransDate() {
                return transDate;
            }

            /**
             * Sets the value of the transDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setTransDate(XMLGregorianCalendar value) {
                this.transDate = value;
            }

            /**
             * Gets the value of the notReported property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isNotReported() {
                return notReported;
            }

            /**
             * Sets the value of the notReported property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setNotReported(Boolean value) {
                this.notReported = value;
            }

            /**
             * Gets the value of the isRegion property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getISRegion() {
                return isRegion;
            }

            /**
             * Sets the value of the isRegion property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setISRegion(String value) {
                this.isRegion = value;
            }

            /**
             * Gets the value of the isOrigCountry property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getISOrigCountry() {
                return isOrigCountry;
            }

            /**
             * Sets the value of the isOrigCountry property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setISOrigCountry(String value) {
                this.isOrigCountry = value;
            }

            /**
             * Gets the value of the shortChar01 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar01() {
                return shortChar01;
            }

            /**
             * Sets the value of the shortChar01 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar01(String value) {
                this.shortChar01 = value;
            }

            /**
             * Gets the value of the shortChar02 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar02() {
                return shortChar02;
            }

            /**
             * Sets the value of the shortChar02 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar02(String value) {
                this.shortChar02 = value;
            }

            /**
             * Gets the value of the shortChar03 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar03() {
                return shortChar03;
            }

            /**
             * Sets the value of the shortChar03 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar03(String value) {
                this.shortChar03 = value;
            }

            /**
             * Gets the value of the shortChar04 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar04() {
                return shortChar04;
            }

            /**
             * Sets the value of the shortChar04 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar04(String value) {
                this.shortChar04 = value;
            }

            /**
             * Gets the value of the shortChar05 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar05() {
                return shortChar05;
            }

            /**
             * Sets the value of the shortChar05 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar05(String value) {
                this.shortChar05 = value;
            }

            /**
             * Gets the value of the number01 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber01() {
                return number01;
            }

            /**
             * Sets the value of the number01 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber01(BigDecimal value) {
                this.number01 = value;
            }

            /**
             * Gets the value of the number02 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber02() {
                return number02;
            }

            /**
             * Sets the value of the number02 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber02(BigDecimal value) {
                this.number02 = value;
            }

            /**
             * Gets the value of the number03 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber03() {
                return number03;
            }

            /**
             * Sets the value of the number03 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber03(BigDecimal value) {
                this.number03 = value;
            }

            /**
             * Gets the value of the number04 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber04() {
                return number04;
            }

            /**
             * Sets the value of the number04 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber04(BigDecimal value) {
                this.number04 = value;
            }

            /**
             * Gets the value of the number05 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber05() {
                return number05;
            }

            /**
             * Sets the value of the number05 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber05(BigDecimal value) {
                this.number05 = value;
            }

            /**
             * Gets the value of the date01 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate01() {
                return date01;
            }

            /**
             * Sets the value of the date01 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate01(XMLGregorianCalendar value) {
                this.date01 = value;
            }

            /**
             * Gets the value of the date02 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate02() {
                return date02;
            }

            /**
             * Sets the value of the date02 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate02(XMLGregorianCalendar value) {
                this.date02 = value;
            }

            /**
             * Gets the value of the date03 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate03() {
                return date03;
            }

            /**
             * Sets the value of the date03 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate03(XMLGregorianCalendar value) {
                this.date03 = value;
            }

            /**
             * Gets the value of the date04 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate04() {
                return date04;
            }

            /**
             * Sets the value of the date04 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate04(XMLGregorianCalendar value) {
                this.date04 = value;
            }

            /**
             * Gets the value of the date05 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate05() {
                return date05;
            }

            /**
             * Sets the value of the date05 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate05(XMLGregorianCalendar value) {
                this.date05 = value;
            }

            /**
             * Gets the value of the checkBox01 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox01() {
                return checkBox01;
            }

            /**
             * Sets the value of the checkBox01 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox01(Boolean value) {
                this.checkBox01 = value;
            }

            /**
             * Gets the value of the checkBox02 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox02() {
                return checkBox02;
            }

            /**
             * Sets the value of the checkBox02 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox02(Boolean value) {
                this.checkBox02 = value;
            }

            /**
             * Gets the value of the checkBox03 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox03() {
                return checkBox03;
            }

            /**
             * Sets the value of the checkBox03 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox03(Boolean value) {
                this.checkBox03 = value;
            }

            /**
             * Gets the value of the checkBox04 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox04() {
                return checkBox04;
            }

            /**
             * Sets the value of the checkBox04 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox04(Boolean value) {
                this.checkBox04 = value;
            }

            /**
             * Gets the value of the checkBox05 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox05() {
                return checkBox05;
            }

            /**
             * Sets the value of the checkBox05 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox05(Boolean value) {
                this.checkBox05 = value;
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

            /**
             * Gets the value of the sysRevID property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getSysRevID() {
                return sysRevID;
            }

            /**
             * Sets the value of the sysRevID property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setSysRevID(Integer value) {
                this.sysRevID = value;
            }

            /**
             * Gets the value of the intCommCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIntCommCode() {
                return intCommCode;
            }

            /**
             * Sets the value of the intCommCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIntCommCode(String value) {
                this.intCommCode = value;
            }

            /**
             * Gets the value of the bitFlag property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getBitFlag() {
                return bitFlag;
            }

            /**
             * Sets the value of the bitFlag property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setBitFlag(Integer value) {
                this.bitFlag = value;
            }

            /**
             * Gets the value of the stampID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStampID() {
                return stampID;
            }

            /**
             * Sets the value of the stampID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStampID(String value) {
                this.stampID = value;
            }

            /**
             * Gets the value of the manualEntry property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isManualEntry() {
                return manualEntry;
            }

            /**
             * Sets the value of the manualEntry property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setManualEntry(Boolean value) {
                this.manualEntry = value;
            }

            /**
             * Gets the value of the custIDSuppID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCustIDSuppID() {
                return custIDSuppID;
            }

            /**
             * Sets the value of the custIDSuppID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCustIDSuppID(String value) {
                this.custIDSuppID = value;
            }

            /**
             * Gets the value of the commodityCodeDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCommodityCodeDescription() {
                return commodityCodeDescription;
            }

            /**
             * Sets the value of the commodityCodeDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCommodityCodeDescription(String value) {
                this.commodityCodeDescription = value;
            }

            /**
             * Gets the value of the fobDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFOBDescription() {
                return fobDescription;
            }

            /**
             * Sets the value of the fobDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFOBDescription(String value) {
                this.fobDescription = value;
            }

            /**
             * Gets the value of the invoiceNumDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getInvoiceNumDescription() {
                return invoiceNumDescription;
            }

            /**
             * Sets the value of the invoiceNumDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setInvoiceNumDescription(String value) {
                this.invoiceNumDescription = value;
            }

            /**
             * Gets the value of the shipViaWebDesc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShipViaWebDesc() {
                return shipViaWebDesc;
            }

            /**
             * Sets the value of the shipViaWebDesc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShipViaWebDesc(String value) {
                this.shipViaWebDesc = value;
            }

            /**
             * Gets the value of the shipViaDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShipViaDescription() {
                return shipViaDescription;
            }

            /**
             * Sets the value of the shipViaDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShipViaDescription(String value) {
                this.shipViaDescription = value;
            }

            /**
             * Gets the value of the vendorNumAddress3 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorNumAddress3() {
                return vendorNumAddress3;
            }

            /**
             * Sets the value of the vendorNumAddress3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorNumAddress3(String value) {
                this.vendorNumAddress3 = value;
            }

            /**
             * Gets the value of the vendorNumCurrencyCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorNumCurrencyCode() {
                return vendorNumCurrencyCode;
            }

            /**
             * Sets the value of the vendorNumCurrencyCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorNumCurrencyCode(String value) {
                this.vendorNumCurrencyCode = value;
            }

            /**
             * Gets the value of the vendorNumState property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorNumState() {
                return vendorNumState;
            }

            /**
             * Sets the value of the vendorNumState property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorNumState(String value) {
                this.vendorNumState = value;
            }

            /**
             * Gets the value of the vendorNumZIP property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorNumZIP() {
                return vendorNumZIP;
            }

            /**
             * Sets the value of the vendorNumZIP property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorNumZIP(String value) {
                this.vendorNumZIP = value;
            }

            /**
             * Gets the value of the vendorNumAddress1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorNumAddress1() {
                return vendorNumAddress1;
            }

            /**
             * Sets the value of the vendorNumAddress1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorNumAddress1(String value) {
                this.vendorNumAddress1 = value;
            }

            /**
             * Gets the value of the vendorNumCity property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorNumCity() {
                return vendorNumCity;
            }

            /**
             * Sets the value of the vendorNumCity property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorNumCity(String value) {
                this.vendorNumCity = value;
            }

            /**
             * Gets the value of the vendorNumDefaultFOB property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorNumDefaultFOB() {
                return vendorNumDefaultFOB;
            }

            /**
             * Sets the value of the vendorNumDefaultFOB property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorNumDefaultFOB(String value) {
                this.vendorNumDefaultFOB = value;
            }

            /**
             * Gets the value of the vendorNumTermsCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorNumTermsCode() {
                return vendorNumTermsCode;
            }

            /**
             * Sets the value of the vendorNumTermsCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorNumTermsCode(String value) {
                this.vendorNumTermsCode = value;
            }

            /**
             * Gets the value of the vendorNumAddress2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorNumAddress2() {
                return vendorNumAddress2;
            }

            /**
             * Sets the value of the vendorNumAddress2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorNumAddress2(String value) {
                this.vendorNumAddress2 = value;
            }

            /**
             * Gets the value of the vendorNumCountry property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorNumCountry() {
                return vendorNumCountry;
            }

            /**
             * Sets the value of the vendorNumCountry property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorNumCountry(String value) {
                this.vendorNumCountry = value;
            }

            /**
             * Gets the value of the vendorNumName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorNumName() {
                return vendorNumName;
            }

            /**
             * Sets the value of the vendorNumName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorNumName(String value) {
                this.vendorNumName = value;
            }

            /**
             * Gets the value of the vendorNumVendorID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorNumVendorID() {
                return vendorNumVendorID;
            }

            /**
             * Sets the value of the vendorNumVendorID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorNumVendorID(String value) {
                this.vendorNumVendorID = value;
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
                this.dbRowIdent = value;
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
