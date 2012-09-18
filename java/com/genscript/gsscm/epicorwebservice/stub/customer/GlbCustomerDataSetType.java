
package com.genscript.gsscm.epicorwebservice.stub.customer;

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
 * <p>Java class for GlbCustomerDataSetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GlbCustomerDataSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GlbCustomerDataSet">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="GlbCustomer">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="GlbCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="GlbCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Address3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Zip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ResaleID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SalesRepCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TerritoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TermsCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShipViaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PrintStatements" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PrintLabels" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PrintAck" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="FinCharges" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CreditHold" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="GroupCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DiscountPercent" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="PrimPCon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="PrimBCon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="PrimSCon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="EstDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="FaxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TaxExempt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="MarkUpID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BillDay" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="OneInvPerPS" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DefaultFOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CreditIncludeOrders" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CreditReviewDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="CreditHoldDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="CreditHoldSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CreditClearUserID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CreditClearDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="CreditClearTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="EDICode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="EDITest" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="EDITranslator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Number01" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number02" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number03" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number04" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number05" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number06" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number07" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number08" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number09" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number10" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
 *                             &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="LangNameID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BorderCrossing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FormatStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTCountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="BTCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTFaxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTFormatStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ParentCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="TaxRegionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ICCust" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ContBillDay" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="EMailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShippingQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AllocPriorityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LinkPortNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="WebCustomer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CustomerType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NoContact" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="TerritoryLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CustURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PendingTerritoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ExtID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ConsolidateSO" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="Bill-Frequency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CreditIncludePI" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="GlobalCust" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="GlbCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="CustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ICTrader" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="GlobalCredIncOrd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="GlobalCredIncPI" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="GlobalCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="GlobalCreditHold" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="GlobalLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="OldCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OldCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="OldCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TaxAuthorityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ExternalDeliveryNote" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ExternalID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CheckDuplicatePO" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CreditLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="CustPILimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="GlobalCreditLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="GlobalPILimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="DocGlobalCreditLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="DocGlobalPILimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="RfqAttachAllow" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DiscountQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Skipped" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="Number11" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number12" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number13" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number14" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number15" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number16" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number17" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number18" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number19" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number20" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Date06" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date07" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date08" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date09" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date10" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date11" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date12" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date13" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date14" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date15" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date16" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date17" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date18" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date19" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date20" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="CheckBox06" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox07" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox08" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox09" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox10" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox11" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox12" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox13" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox14" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox15" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox16" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox17" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox18" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox19" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox20" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ShortChar01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AllowAltBillTo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ApplyChrg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ChrgAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="COD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CODAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="CODCheck" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CODFreight" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DeclaredAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="DeclaredIns" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandAddAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandAddLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandAutoAccept" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandCancelAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCancelLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandChangeAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandChangeDateAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandChangeDateLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandChangeLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandDateType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandDayOfWeek" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandDeliveryDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandNewLineAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandNewLineLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandQtyChangeAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandQtyChangeLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandUseSysDate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DocOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="GroundType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Hazmat" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="NotifyEMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NotifyFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="RefNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ResDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="SatDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="SatPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="TradingPartnerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VerbalConf" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PeriodicityCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DeliveryType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServAlert" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ServAOD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ServAuthNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="ServHomeDel" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ServInstruct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServPOD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ServRef1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServRef2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServRef3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServRef4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServRef5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServRelease" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ServSatDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ServSatPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ServSignature" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CreditDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="EarlyBuffer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="LateBuffer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="OverrideCarrier" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="OverrideService" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandUnitPriceDiff" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandUnitPriceDiffAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AddressVal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ExcFromVal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="RebateForm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RebateVendorNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="CreditCardOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandCheckForPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandCheckForPartAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ChangeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="ChangedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ChangeTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="ChargeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AddlHdlgFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CertOfOrigin" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CommercialInvoice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DeliveryConf" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="ETCAddrChg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="FFAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFCompName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFCountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="FFID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="IndividualPackIDs" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="IntrntlShip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LetterOfInstr" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="NonStdPkg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ShipExprtDeclartn" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="UPSQuantumView" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="UPSQVEmailType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UPSQVMemo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UPSQVShipFromName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ARCreditTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="PICreditTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="SOCreditTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="TaxMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TaxRoundRule" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="AcrossNatAcc" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="NACreditIsShare" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="NACreditPreferenceList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NACreditSharedPrc" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="NAParentCreditIsUsed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="NAParentCreditPrc" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="OverrideRlsClass" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ValidPayer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ValidShipTo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ValidSoldTo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="AllowOTS" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ManagedVendID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ManagedVendNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="ThirdPLCust" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="NARlsClassCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AutoGenPromNotes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DirectDebiting" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PartPayment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ReservePriorityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NAParentsCreditUsed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="NASharedCreditUsed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="NAOwnCreditUsed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="GlbNACreditIsShare" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="GlbNACreditSharedPrc" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="GlbNAParentCreditIsUsed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="GlbNAParentCreditPrc" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="GlbNAParentsCreditUsed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="GlbNASharedCreditUsed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="ReminderCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AllowShipTo3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="NACreditUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="OTSSaveAs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CustPartOpts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="HasBank" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PMUID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandCheckForRev" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandCheckForRevAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OrderHoldForReview" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ShipToTerrList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AcctRefNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ARInvoiceAdjITCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ARInvoiceITCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CreditMemoITCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="MiscCashRecITCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCheckCUMM" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandCheckCUMMAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCloseNoMatch" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandCloseRejSkd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandPricing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DmdCheckPartialShip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DmdCheckShipAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="InvPerPackLine" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LegalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OrgRegCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OurBankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PriceTolerance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="TaxRegReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CheckConfirmCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckDateCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckUpdateCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandCapPromiseAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCapPromiseDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCapPromiseUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandSplitSched" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DueDateCriteria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ERSOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PBTerms" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="PeriodicBilling" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PreferredBank" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SICCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="ICCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSmartString" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DeferredRev" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="RACode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCheckCfgAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCheckConfig" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LinkCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DisplayHold" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DisplayCustomerType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="GlbShipTo">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="CustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="ShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Address3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ResaleID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SalesRepCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TerritoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShipViaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PrimSCon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="FaxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TaxExempt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="EDIShipNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="LangNameID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BorderCrossing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FormatStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TaxRegionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="EMailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TerritorySelect" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PendingTerritoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CreatedByEDI" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ExternalID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TaxAuthorityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="GlbCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="GlbCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="EDICode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="GlbShipToNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="GlobalLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="OldCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OldCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="OldShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Skipped" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="Character01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Number01" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number02" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number03" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number04" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number05" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number06" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number07" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number08" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number09" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number10" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number11" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number12" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number13" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number14" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number15" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number16" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number17" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number18" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number19" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number20" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Date01" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date02" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date03" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date04" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date05" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date06" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date07" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date08" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date09" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date10" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date11" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date12" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date13" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date14" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date15" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date16" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date17" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date18" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date19" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date20" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="CheckBox01" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox02" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox03" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox04" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox05" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox06" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox07" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox08" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox09" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox10" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox11" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox12" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox13" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox14" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox15" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox16" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox17" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox18" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox19" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox20" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ShortChar01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ApplyChrg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ChrgAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="COD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CODAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="CODCheck" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CODFreight" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DeclaredAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="DeclaredIns" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandAddAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandAddLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandAutoAccept" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandCancelAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCancelLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandChangeAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandChangeDateAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandChangeDateLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandChangeLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandDateType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandDayOfWeek" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandDeliveryDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandNewLineAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandNewLineLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandQtyChangeAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandQtyChangeLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DemandUseSysDate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DocOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="GroundType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Hazmat" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="NotifyEMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NotifyFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="RefNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ResDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="SatDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="SatPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="TradingPartnerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VerbalConf" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PeriodicityCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DeliveryType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServAlert" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ServAOD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ServAuthNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="ServHomeDel" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ServInstruct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServPOD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ServRef1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServRef2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServRef3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServRef4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServRef5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ServRelease" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ServSatDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ServSatPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ServSignature" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandUseCustomerValues" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="EarlyBuffer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="LateBuffer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="OverrideCarrier" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="OverrideService" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandUnitPriceDiff" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandUnitPriceDiffAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AddressVal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ExcFromVal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CertOfOrigin" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CommercialInvoice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ConAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ConAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ConCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ConCompName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ConContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ConCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ConPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ConsigneeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ConState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ConZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCheckForPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandCheckForPartAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFCompName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="HazardousShipment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="IntrntlShip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LetterOfInstr" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ShipExprtDeclartn" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ChangeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="ChangedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ChangeTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="AddlHdlgFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DeliveryConf" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="ETCAddrChg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="FFAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FFCountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="IndividualPackIDs" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="NonStdPkg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="UPSQuantumView" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="UPSQVEmailType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UPSQVMemo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UPSQVShipFromName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="IsAlternate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="MasterCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="MasterShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCheckForRev" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandCheckForRevAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCheckPartialShip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandCheckShipAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCloseRejSkd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandPricing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OrgRegCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PriceTolerance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="TaxRegReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CheckConfirmCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckDateCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckUpdateCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DemandCapPromiseAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCapPromiseDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCapPromiseUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SICCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="ICCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSmartString" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LegalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCheckCfgAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DemandCheckConfig" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LinkShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="GlbCustBillTo">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="GlbCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="GlbCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="GlbBTCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="CustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="BTCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="DefaultBillTo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="InvoiceAddress" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="Character01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Character10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Number01" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number02" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number03" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number04" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number05" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number06" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number07" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number08" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number09" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number10" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
 *                             &lt;element name="ShortChar01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="GlobalLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ChangeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="ChangedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ChangeTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="BTLegalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OrgRegCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OurBankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TaxRegReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LinkBTCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
@XmlType(name = "GlbCustomerDataSetType", namespace = "http://epicor.com/schemas", propOrder = {
    "glbCustomerDataSet"
})
public class GlbCustomerDataSetType {

    @XmlElement(name = "GlbCustomerDataSet", required = true)
    protected GlbCustomerDataSetType.GlbCustomerDataSet glbCustomerDataSet;

    /**
     * Gets the value of the glbCustomerDataSet property.
     * 
     * @return
     *     possible object is
     *     {@link GlbCustomerDataSetType.GlbCustomerDataSet }
     *     
     */
    public GlbCustomerDataSetType.GlbCustomerDataSet getGlbCustomerDataSet() {
        return glbCustomerDataSet;
    }

    /**
     * Sets the value of the glbCustomerDataSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlbCustomerDataSetType.GlbCustomerDataSet }
     *     
     */
    public void setGlbCustomerDataSet(GlbCustomerDataSetType.GlbCustomerDataSet value) {
        this.glbCustomerDataSet = value;
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
     *         &lt;element name="GlbCustomer">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="GlbCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="GlbCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Address3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Zip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ResaleID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SalesRepCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TerritoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TermsCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShipViaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PrintStatements" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PrintLabels" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PrintAck" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="FinCharges" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CreditHold" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="GroupCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DiscountPercent" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="PrimPCon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="PrimBCon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="PrimSCon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="EstDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="FaxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TaxExempt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="MarkUpID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BillDay" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="OneInvPerPS" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DefaultFOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CreditIncludeOrders" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CreditReviewDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="CreditHoldDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="CreditHoldSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CreditClearUserID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CreditClearDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="CreditClearTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="EDICode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="EDITest" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="EDITranslator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Number01" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number02" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number03" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number04" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number05" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number06" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number07" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number08" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number09" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number10" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
     *                   &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="LangNameID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BorderCrossing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FormatStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTCountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="BTCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTFaxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTFormatStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ParentCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="TaxRegionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ICCust" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ContBillDay" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="EMailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShippingQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AllocPriorityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="LinkPortNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="WebCustomer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CustomerType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NoContact" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="TerritoryLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CustURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PendingTerritoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ExtID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ConsolidateSO" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="Bill-Frequency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CreditIncludePI" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="GlobalCust" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="GlbCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="CustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ICTrader" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="GlobalCredIncOrd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="GlobalCredIncPI" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="GlobalCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="GlobalCreditHold" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="GlobalLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="OldCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OldCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="OldCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TaxAuthorityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ExternalDeliveryNote" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ExternalID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CheckDuplicatePO" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CreditLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="CustPILimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="GlobalCreditLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="GlobalPILimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="DocGlobalCreditLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="DocGlobalPILimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="RfqAttachAllow" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DiscountQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Skipped" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="Number11" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number12" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number13" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number14" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number15" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number16" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number17" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number18" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number19" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number20" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Date06" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date07" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date08" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date09" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date10" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date11" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date12" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date13" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date14" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date15" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date16" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date17" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date18" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date19" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date20" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="CheckBox06" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox07" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox08" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox09" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox10" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox11" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox12" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox13" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox14" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox15" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox16" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox17" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox18" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox19" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox20" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ShortChar01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AllowAltBillTo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ApplyChrg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ChrgAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="COD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CODAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="CODCheck" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CODFreight" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DeclaredAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="DeclaredIns" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandAddAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandAddLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandAutoAccept" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandCancelAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCancelLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandChangeAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandChangeDateAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandChangeDateLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandChangeLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandDateType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandDayOfWeek" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandDeliveryDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandNewLineAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandNewLineLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandQtyChangeAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandQtyChangeLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandUseSysDate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DocOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="GroundType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Hazmat" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="NotifyEMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NotifyFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="RefNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ResDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="SatDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="SatPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="TradingPartnerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VerbalConf" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PeriodicityCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DeliveryType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServAlert" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ServAOD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ServAuthNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="ServHomeDel" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ServInstruct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServPOD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ServRef1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServRef2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServRef3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServRef4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServRef5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServRelease" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ServSatDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ServSatPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ServSignature" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CreditDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="EarlyBuffer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="LateBuffer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="OverrideCarrier" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="OverrideService" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandUnitPriceDiff" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandUnitPriceDiffAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AddressVal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ExcFromVal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="RebateForm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RebateVendorNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="CreditCardOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandCheckForPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandCheckForPartAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ChangeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="ChangedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ChangeTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="ChargeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AddlHdlgFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CertOfOrigin" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CommercialInvoice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DeliveryConf" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="ETCAddrChg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="FFAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFCompName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFCountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="FFID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="IndividualPackIDs" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="IntrntlShip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LetterOfInstr" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="NonStdPkg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ShipExprtDeclartn" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="UPSQuantumView" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="UPSQVEmailType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UPSQVMemo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UPSQVShipFromName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ARCreditTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="PICreditTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="SOCreditTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="TaxMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TaxRoundRule" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="AcrossNatAcc" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="NACreditIsShare" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="NACreditPreferenceList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NACreditSharedPrc" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="NAParentCreditIsUsed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="NAParentCreditPrc" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="OverrideRlsClass" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ValidPayer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ValidShipTo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ValidSoldTo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="AllowOTS" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ManagedVendID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ManagedVendNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="ThirdPLCust" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="NARlsClassCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AutoGenPromNotes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DirectDebiting" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PartPayment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ReservePriorityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NAParentsCreditUsed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="NASharedCreditUsed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="NAOwnCreditUsed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="GlbNACreditIsShare" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="GlbNACreditSharedPrc" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="GlbNAParentCreditIsUsed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="GlbNAParentCreditPrc" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="GlbNAParentsCreditUsed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="GlbNASharedCreditUsed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="ReminderCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AllowShipTo3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="NACreditUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="OTSSaveAs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CustPartOpts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="HasBank" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PMUID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandCheckForRev" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandCheckForRevAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OrderHoldForReview" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ShipToTerrList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AcctRefNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ARInvoiceAdjITCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ARInvoiceITCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CreditMemoITCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="MiscCashRecITCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCheckCUMM" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandCheckCUMMAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCloseNoMatch" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandCloseRejSkd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandPricing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DmdCheckPartialShip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DmdCheckShipAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="InvPerPackLine" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LegalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OrgRegCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OurBankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PriceTolerance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="TaxRegReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CheckConfirmCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckDateCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckUpdateCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandCapPromiseAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCapPromiseDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCapPromiseUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandSplitSched" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DueDateCriteria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ERSOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PBTerms" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="PeriodicBilling" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PreferredBank" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SICCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="ICCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSmartString" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DeferredRev" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="RACode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCheckCfgAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCheckConfig" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LinkCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DisplayHold" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DisplayCustomerType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="GlbShipTo">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="CustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="ShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Address3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ResaleID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SalesRepCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TerritoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShipViaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PrimSCon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="FaxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TaxExempt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="EDIShipNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="LangNameID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BorderCrossing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FormatStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TaxRegionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="EMailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TerritorySelect" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PendingTerritoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CreatedByEDI" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ExternalID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TaxAuthorityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="GlbCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="GlbCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="EDICode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="GlbShipToNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="GlobalLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="OldCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OldCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="OldShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Skipped" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="Character01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Number01" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number02" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number03" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number04" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number05" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number06" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number07" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number08" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number09" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number10" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number11" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number12" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number13" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number14" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number15" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number16" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number17" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number18" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number19" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number20" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Date01" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date02" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date03" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date04" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date05" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date06" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date07" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date08" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date09" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date10" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date11" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date12" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date13" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date14" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date15" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date16" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date17" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date18" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date19" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date20" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="CheckBox01" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox02" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox03" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox04" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox05" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox06" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox07" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox08" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox09" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox10" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox11" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox12" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox13" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox14" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox15" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox16" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox17" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox18" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox19" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox20" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ShortChar01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ApplyChrg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ChrgAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="COD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CODAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="CODCheck" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CODFreight" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DeclaredAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="DeclaredIns" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandAddAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandAddLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandAutoAccept" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandCancelAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCancelLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandChangeAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandChangeDateAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandChangeDateLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandChangeLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandDateType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandDayOfWeek" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandDeliveryDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandNewLineAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandNewLineLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandQtyChangeAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandQtyChangeLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DemandUseSysDate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DocOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="GroundType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Hazmat" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="NotifyEMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NotifyFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="RefNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ResDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="SatDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="SatPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="TradingPartnerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VerbalConf" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PeriodicityCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DeliveryType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServAlert" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ServAOD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ServAuthNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="ServHomeDel" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ServInstruct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServPOD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ServRef1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServRef2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServRef3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServRef4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServRef5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ServRelease" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ServSatDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ServSatPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ServSignature" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandUseCustomerValues" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="EarlyBuffer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="LateBuffer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="OverrideCarrier" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="OverrideService" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandUnitPriceDiff" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandUnitPriceDiffAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AddressVal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ExcFromVal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CertOfOrigin" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CommercialInvoice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ConAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ConAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ConCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ConCompName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ConContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ConCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ConPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ConsigneeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ConState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ConZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCheckForPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandCheckForPartAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFCompName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="HazardousShipment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="IntrntlShip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LetterOfInstr" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ShipExprtDeclartn" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ChangeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="ChangedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ChangeTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="AddlHdlgFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DeliveryConf" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="ETCAddrChg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="FFAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FFCountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="IndividualPackIDs" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="NonStdPkg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="UPSQuantumView" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="UPSQVEmailType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UPSQVMemo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UPSQVShipFromName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="IsAlternate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="MasterCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="MasterShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCheckForRev" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandCheckForRevAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCheckPartialShip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandCheckShipAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCloseRejSkd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandPricing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OrgRegCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PriceTolerance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="TaxRegReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CheckConfirmCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckDateCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckUpdateCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DemandCapPromiseAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCapPromiseDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCapPromiseUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SICCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="ICCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSmartString" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LegalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCheckCfgAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DemandCheckConfig" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LinkShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="GlbCustBillTo">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="GlbCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="GlbCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="GlbBTCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="CustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="BTCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="DefaultBillTo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="InvoiceAddress" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="Character01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Character10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Number01" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number02" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number03" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number04" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number05" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number06" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number07" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number08" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number09" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number10" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
     *                   &lt;element name="ShortChar01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="GlobalLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ChangeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="ChangedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ChangeTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="BTLegalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OrgRegCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OurBankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TaxRegReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="LinkBTCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
        "glbCustomerOrGlbShipToOrGlbCustBillTo"
    })
    public static class GlbCustomerDataSet {

        @XmlElements({
            @XmlElement(name = "GlbShipTo", type = GlbCustomerDataSetType.GlbCustomerDataSet.GlbShipTo.class),
            @XmlElement(name = "GlbCustBillTo", type = GlbCustomerDataSetType.GlbCustomerDataSet.GlbCustBillTo.class),
            @XmlElement(name = "WebServiceErrors", type = GlbCustomerDataSetType.GlbCustomerDataSet.WebServiceErrors.class),
            @XmlElement(name = "GlbCustomer", type = GlbCustomerDataSetType.GlbCustomerDataSet.GlbCustomer.class)
        })
        protected List<Object> glbCustomerOrGlbShipToOrGlbCustBillTo;

        /**
         * Gets the value of the glbCustomerOrGlbShipToOrGlbCustBillTo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the glbCustomerOrGlbShipToOrGlbCustBillTo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getGlbCustomerOrGlbShipToOrGlbCustBillTo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GlbCustomerDataSetType.GlbCustomerDataSet.GlbShipTo }
         * {@link GlbCustomerDataSetType.GlbCustomerDataSet.GlbCustBillTo }
         * {@link GlbCustomerDataSetType.GlbCustomerDataSet.WebServiceErrors }
         * {@link GlbCustomerDataSetType.GlbCustomerDataSet.GlbCustomer }
         * 
         * 
         */
        public List<Object> getGlbCustomerOrGlbShipToOrGlbCustBillTo() {
            if (glbCustomerOrGlbShipToOrGlbCustBillTo == null) {
                glbCustomerOrGlbShipToOrGlbCustBillTo = new ArrayList<Object>();
            }
            return this.glbCustomerOrGlbShipToOrGlbCustBillTo;
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
         *         &lt;element name="GlbCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="GlbCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="GlbBTCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="CustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="BTCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DefaultBillTo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="InvoiceAddress" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="Character01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Number01" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number02" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number03" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number04" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number05" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number06" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number07" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number08" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number09" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number10" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
         *         &lt;element name="ShortChar01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="GlobalLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ChangeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="ChangedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ChangeTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="BTLegalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OrgRegCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OurBankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TaxRegReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="LinkBTCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
            "glbCompany",
            "glbCustNum",
            "glbBTCustNum",
            "custNum",
            "btCustNum",
            "defaultBillTo",
            "invoiceAddress",
            "character01",
            "character02",
            "character03",
            "character04",
            "character05",
            "character06",
            "character07",
            "character08",
            "character09",
            "character10",
            "number01",
            "number02",
            "number03",
            "number04",
            "number05",
            "number06",
            "number07",
            "number08",
            "number09",
            "number10",
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
            "shortChar01",
            "shortChar02",
            "shortChar03",
            "shortChar04",
            "shortChar05",
            "shortChar06",
            "shortChar07",
            "shortChar08",
            "shortChar09",
            "shortChar10",
            "globalLock",
            "changeDate",
            "changedBy",
            "changeTime",
            "sysRowID",
            "sysRevID",
            "bitFlag",
            "btLegalName",
            "orgRegCode",
            "ourBankCode",
            "taxRegReason",
            "linkBTCustNum",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class GlbCustBillTo {

            @XmlElement(name = "Company", required = true)
            protected String company;
            @XmlElement(name = "GlbCompany", required = true)
            protected String glbCompany;
            @XmlElement(name = "GlbCustNum")
            protected int glbCustNum;
            @XmlElement(name = "GlbBTCustNum")
            protected int glbBTCustNum;
            @XmlElement(name = "CustNum")
            protected Integer custNum;
            @XmlElement(name = "BTCustNum")
            protected Integer btCustNum;
            @XmlElement(name = "DefaultBillTo")
            protected Boolean defaultBillTo;
            @XmlElement(name = "InvoiceAddress")
            protected Boolean invoiceAddress;
            @XmlElement(name = "Character01")
            protected String character01;
            @XmlElement(name = "Character02")
            protected String character02;
            @XmlElement(name = "Character03")
            protected String character03;
            @XmlElement(name = "Character04")
            protected String character04;
            @XmlElement(name = "Character05")
            protected String character05;
            @XmlElement(name = "Character06")
            protected String character06;
            @XmlElement(name = "Character07")
            protected String character07;
            @XmlElement(name = "Character08")
            protected String character08;
            @XmlElement(name = "Character09")
            protected String character09;
            @XmlElement(name = "Character10")
            protected String character10;
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
            @XmlElement(name = "Number06")
            protected BigDecimal number06;
            @XmlElement(name = "Number07")
            protected BigDecimal number07;
            @XmlElement(name = "Number08")
            protected BigDecimal number08;
            @XmlElement(name = "Number09")
            protected BigDecimal number09;
            @XmlElement(name = "Number10")
            protected BigDecimal number10;
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
            @XmlElement(name = "ShortChar06")
            protected String shortChar06;
            @XmlElement(name = "ShortChar07")
            protected String shortChar07;
            @XmlElement(name = "ShortChar08")
            protected String shortChar08;
            @XmlElement(name = "ShortChar09")
            protected String shortChar09;
            @XmlElement(name = "ShortChar10")
            protected String shortChar10;
            @XmlElement(name = "GlobalLock")
            protected Boolean globalLock;
            @XmlElement(name = "ChangeDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar changeDate;
            @XmlElement(name = "ChangedBy")
            protected String changedBy;
            @XmlElement(name = "ChangeTime")
            protected Integer changeTime;
            @XmlElement(name = "SysRowID")
            protected String sysRowID;
            @XmlElement(name = "SysRevID")
            protected Integer sysRevID;
            @XmlElement(name = "BitFlag")
            protected Integer bitFlag;
            @XmlElement(name = "BTLegalName")
            protected String btLegalName;
            @XmlElement(name = "OrgRegCode")
            protected String orgRegCode;
            @XmlElement(name = "OurBankCode")
            protected String ourBankCode;
            @XmlElement(name = "TaxRegReason")
            protected String taxRegReason;
            @XmlElement(name = "LinkBTCustNum")
            protected Integer linkBTCustNum;
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
             * Gets the value of the glbCompany property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGlbCompany() {
                return glbCompany;
            }

            /**
             * Sets the value of the glbCompany property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGlbCompany(String value) {
                this.glbCompany = value;
            }

            /**
             * Gets the value of the glbCustNum property.
             * 
             */
            public int getGlbCustNum() {
                return glbCustNum;
            }

            /**
             * Sets the value of the glbCustNum property.
             * 
             */
            public void setGlbCustNum(int value) {
                this.glbCustNum = value;
            }

            /**
             * Gets the value of the glbBTCustNum property.
             * 
             */
            public int getGlbBTCustNum() {
                return glbBTCustNum;
            }

            /**
             * Sets the value of the glbBTCustNum property.
             * 
             */
            public void setGlbBTCustNum(int value) {
                this.glbBTCustNum = value;
            }

            /**
             * Gets the value of the custNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getCustNum() {
                return custNum;
            }

            /**
             * Sets the value of the custNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setCustNum(Integer value) {
                this.custNum = value;
            }

            /**
             * Gets the value of the btCustNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getBTCustNum() {
                return btCustNum;
            }

            /**
             * Sets the value of the btCustNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setBTCustNum(Integer value) {
                this.btCustNum = value;
            }

            /**
             * Gets the value of the defaultBillTo property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDefaultBillTo() {
                return defaultBillTo;
            }

            /**
             * Sets the value of the defaultBillTo property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDefaultBillTo(Boolean value) {
                this.defaultBillTo = value;
            }

            /**
             * Gets the value of the invoiceAddress property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isInvoiceAddress() {
                return invoiceAddress;
            }

            /**
             * Sets the value of the invoiceAddress property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setInvoiceAddress(Boolean value) {
                this.invoiceAddress = value;
            }

            /**
             * Gets the value of the character01 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter01() {
                return character01;
            }

            /**
             * Sets the value of the character01 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter01(String value) {
                this.character01 = value;
            }

            /**
             * Gets the value of the character02 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter02() {
                return character02;
            }

            /**
             * Sets the value of the character02 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter02(String value) {
                this.character02 = value;
            }

            /**
             * Gets the value of the character03 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter03() {
                return character03;
            }

            /**
             * Sets the value of the character03 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter03(String value) {
                this.character03 = value;
            }

            /**
             * Gets the value of the character04 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter04() {
                return character04;
            }

            /**
             * Sets the value of the character04 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter04(String value) {
                this.character04 = value;
            }

            /**
             * Gets the value of the character05 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter05() {
                return character05;
            }

            /**
             * Sets the value of the character05 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter05(String value) {
                this.character05 = value;
            }

            /**
             * Gets the value of the character06 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter06() {
                return character06;
            }

            /**
             * Sets the value of the character06 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter06(String value) {
                this.character06 = value;
            }

            /**
             * Gets the value of the character07 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter07() {
                return character07;
            }

            /**
             * Sets the value of the character07 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter07(String value) {
                this.character07 = value;
            }

            /**
             * Gets the value of the character08 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter08() {
                return character08;
            }

            /**
             * Sets the value of the character08 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter08(String value) {
                this.character08 = value;
            }

            /**
             * Gets the value of the character09 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter09() {
                return character09;
            }

            /**
             * Sets the value of the character09 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter09(String value) {
                this.character09 = value;
            }

            /**
             * Gets the value of the character10 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter10() {
                return character10;
            }

            /**
             * Sets the value of the character10 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter10(String value) {
                this.character10 = value;
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
             * Gets the value of the number06 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber06() {
                return number06;
            }

            /**
             * Sets the value of the number06 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber06(BigDecimal value) {
                this.number06 = value;
            }

            /**
             * Gets the value of the number07 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber07() {
                return number07;
            }

            /**
             * Sets the value of the number07 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber07(BigDecimal value) {
                this.number07 = value;
            }

            /**
             * Gets the value of the number08 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber08() {
                return number08;
            }

            /**
             * Sets the value of the number08 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber08(BigDecimal value) {
                this.number08 = value;
            }

            /**
             * Gets the value of the number09 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber09() {
                return number09;
            }

            /**
             * Sets the value of the number09 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber09(BigDecimal value) {
                this.number09 = value;
            }

            /**
             * Gets the value of the number10 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber10() {
                return number10;
            }

            /**
             * Sets the value of the number10 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber10(BigDecimal value) {
                this.number10 = value;
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
             * Gets the value of the shortChar06 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar06() {
                return shortChar06;
            }

            /**
             * Sets the value of the shortChar06 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar06(String value) {
                this.shortChar06 = value;
            }

            /**
             * Gets the value of the shortChar07 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar07() {
                return shortChar07;
            }

            /**
             * Sets the value of the shortChar07 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar07(String value) {
                this.shortChar07 = value;
            }

            /**
             * Gets the value of the shortChar08 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar08() {
                return shortChar08;
            }

            /**
             * Sets the value of the shortChar08 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar08(String value) {
                this.shortChar08 = value;
            }

            /**
             * Gets the value of the shortChar09 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar09() {
                return shortChar09;
            }

            /**
             * Sets the value of the shortChar09 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar09(String value) {
                this.shortChar09 = value;
            }

            /**
             * Gets the value of the shortChar10 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar10() {
                return shortChar10;
            }

            /**
             * Sets the value of the shortChar10 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar10(String value) {
                this.shortChar10 = value;
            }

            /**
             * Gets the value of the globalLock property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isGlobalLock() {
                return globalLock;
            }

            /**
             * Sets the value of the globalLock property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setGlobalLock(Boolean value) {
                this.globalLock = value;
            }

            /**
             * Gets the value of the changeDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getChangeDate() {
                return changeDate;
            }

            /**
             * Sets the value of the changeDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setChangeDate(XMLGregorianCalendar value) {
                this.changeDate = value;
            }

            /**
             * Gets the value of the changedBy property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getChangedBy() {
                return changedBy;
            }

            /**
             * Sets the value of the changedBy property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setChangedBy(String value) {
                this.changedBy = value;
            }

            /**
             * Gets the value of the changeTime property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getChangeTime() {
                return changeTime;
            }

            /**
             * Sets the value of the changeTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setChangeTime(Integer value) {
                this.changeTime = value;
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
             * Gets the value of the btLegalName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTLegalName() {
                return btLegalName;
            }

            /**
             * Sets the value of the btLegalName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTLegalName(String value) {
                this.btLegalName = value;
            }

            /**
             * Gets the value of the orgRegCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOrgRegCode() {
                return orgRegCode;
            }

            /**
             * Sets the value of the orgRegCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOrgRegCode(String value) {
                this.orgRegCode = value;
            }

            /**
             * Gets the value of the ourBankCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOurBankCode() {
                return ourBankCode;
            }

            /**
             * Sets the value of the ourBankCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOurBankCode(String value) {
                this.ourBankCode = value;
            }

            /**
             * Gets the value of the taxRegReason property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTaxRegReason() {
                return taxRegReason;
            }

            /**
             * Sets the value of the taxRegReason property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTaxRegReason(String value) {
                this.taxRegReason = value;
            }

            /**
             * Gets the value of the linkBTCustNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getLinkBTCustNum() {
                return linkBTCustNum;
            }

            /**
             * Sets the value of the linkBTCustNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setLinkBTCustNum(Integer value) {
                this.linkBTCustNum = value;
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
         *         &lt;element name="GlbCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="GlbCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Address3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Zip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ResaleID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SalesRepCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TerritoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TermsCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShipViaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PrintStatements" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PrintLabels" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PrintAck" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="FinCharges" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CreditHold" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="GroupCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DiscountPercent" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="PrimPCon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="PrimBCon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="PrimSCon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="EstDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="FaxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TaxExempt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="MarkUpID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BillDay" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="OneInvPerPS" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DefaultFOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CreditIncludeOrders" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CreditReviewDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="CreditHoldDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="CreditHoldSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CreditClearUserID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CreditClearDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="CreditClearTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="EDICode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="EDITest" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="EDITranslator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Number01" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number02" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number03" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number04" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number05" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number06" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number07" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number08" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number09" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number10" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
         *         &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="LangNameID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BorderCrossing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FormatStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTCountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="BTCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTFaxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTFormatStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ParentCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="TaxRegionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ICCust" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ContBillDay" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="EMailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShippingQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AllocPriorityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="LinkPortNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="WebCustomer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CustomerType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NoContact" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="TerritoryLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CustURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PendingTerritoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ExtID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ConsolidateSO" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="Bill-Frequency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CreditIncludePI" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="GlobalCust" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="GlbCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="CustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ICTrader" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="GlobalCredIncOrd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="GlobalCredIncPI" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="GlobalCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="GlobalCreditHold" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="GlobalLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="OldCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OldCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="OldCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TaxAuthorityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ExternalDeliveryNote" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ExternalID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CheckDuplicatePO" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CreditLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="CustPILimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="GlobalCreditLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="GlobalPILimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="DocGlobalCreditLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="DocGlobalPILimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="RfqAttachAllow" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DiscountQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Skipped" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="Number11" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number12" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number13" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number14" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number15" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number16" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number17" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number18" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number19" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number20" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Date06" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date07" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date08" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date09" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date10" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date11" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date12" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date13" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date14" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date15" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date16" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date17" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date18" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date19" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date20" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="CheckBox06" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox07" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox08" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox09" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox10" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox11" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox12" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox13" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox14" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox15" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox16" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox17" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox18" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox19" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox20" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ShortChar01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AllowAltBillTo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ApplyChrg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ChrgAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="COD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CODAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="CODCheck" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CODFreight" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DeclaredAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="DeclaredIns" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandAddAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandAddLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandAutoAccept" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandCancelAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCancelLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandChangeAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandChangeDateAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandChangeDateLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandChangeLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandDateType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandDayOfWeek" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandDeliveryDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandNewLineAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandNewLineLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandQtyChangeAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandQtyChangeLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandUseSysDate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DocOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="GroundType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Hazmat" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="NotifyEMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NotifyFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="RefNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ResDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="SatDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="SatPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="TradingPartnerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VerbalConf" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PeriodicityCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DeliveryType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServAlert" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ServAOD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ServAuthNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="ServHomeDel" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ServInstruct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServPOD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ServRef1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServRef2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServRef3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServRef4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServRef5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServRelease" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ServSatDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ServSatPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ServSignature" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CreditDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="EarlyBuffer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="LateBuffer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="OverrideCarrier" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="OverrideService" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandUnitPriceDiff" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandUnitPriceDiffAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AddressVal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ExcFromVal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="RebateForm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="RebateVendorNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="CreditCardOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandCheckForPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandCheckForPartAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ChangeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="ChangedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ChangeTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="ChargeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AddlHdlgFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CertOfOrigin" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CommercialInvoice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DeliveryConf" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="ETCAddrChg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="FFAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFCompName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFCountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="FFID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="IndividualPackIDs" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="IntrntlShip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LetterOfInstr" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="NonStdPkg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ShipExprtDeclartn" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="UPSQuantumView" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="UPSQVEmailType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UPSQVMemo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UPSQVShipFromName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ARCreditTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="PICreditTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="SOCreditTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="TaxMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TaxRoundRule" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="AcrossNatAcc" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="NACreditIsShare" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="NACreditPreferenceList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NACreditSharedPrc" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="NAParentCreditIsUsed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="NAParentCreditPrc" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="OverrideRlsClass" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ValidPayer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ValidShipTo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ValidSoldTo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="AllowOTS" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ManagedVendID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ManagedVendNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="ThirdPLCust" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="NARlsClassCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AutoGenPromNotes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DirectDebiting" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PartPayment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ReservePriorityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NAParentsCreditUsed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="NASharedCreditUsed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="NAOwnCreditUsed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="GlbNACreditIsShare" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="GlbNACreditSharedPrc" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="GlbNAParentCreditIsUsed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="GlbNAParentCreditPrc" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="GlbNAParentsCreditUsed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="GlbNASharedCreditUsed" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="ReminderCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AllowShipTo3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="NACreditUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="OTSSaveAs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CustPartOpts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="HasBank" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PMUID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandCheckForRev" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandCheckForRevAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OrderHoldForReview" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ShipToTerrList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AcctRefNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ARInvoiceAdjITCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ARInvoiceITCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CreditMemoITCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="MiscCashRecITCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCheckCUMM" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandCheckCUMMAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCloseNoMatch" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandCloseRejSkd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandPricing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DmdCheckPartialShip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DmdCheckShipAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="InvPerPackLine" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LegalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OrgRegCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OurBankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PriceTolerance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="TaxRegReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CheckConfirmCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckDateCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckUpdateCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandCapPromiseAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCapPromiseDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCapPromiseUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandSplitSched" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DueDateCriteria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ERSOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PBTerms" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="PeriodicBilling" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PreferredBank" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SICCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="ICCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSmartString" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DeferredRev" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="RACode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCheckCfgAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCheckConfig" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LinkCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DisplayHold" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DisplayCustomerType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "glbCustID",
            "glbCustNum",
            "name",
            "address1",
            "address2",
            "address3",
            "city",
            "state",
            "zip",
            "country",
            "resaleID",
            "salesRepCode",
            "territoryID",
            "shipToNum",
            "termsCode",
            "shipViaCode",
            "printStatements",
            "printLabels",
            "printAck",
            "finCharges",
            "creditHold",
            "groupCode",
            "discountPercent",
            "primPCon",
            "primBCon",
            "primSCon",
            "comment",
            "estDate",
            "faxNum",
            "phoneNum",
            "taxExempt",
            "markUpID",
            "billDay",
            "oneInvPerPS",
            "defaultFOB",
            "creditIncludeOrders",
            "creditReviewDate",
            "creditHoldDate",
            "creditHoldSource",
            "creditClearUserID",
            "creditClearDate",
            "creditClearTime",
            "ediCode",
            "ediTest",
            "ediTranslator",
            "character01",
            "character02",
            "character03",
            "character04",
            "character05",
            "character06",
            "character07",
            "character08",
            "character09",
            "character10",
            "number01",
            "number02",
            "number03",
            "number04",
            "number05",
            "number06",
            "number07",
            "number08",
            "number09",
            "number10",
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
            "currencyCode",
            "countryNum",
            "langNameID",
            "borderCrossing",
            "formatStr",
            "btName",
            "btAddress1",
            "btAddress2",
            "btAddress3",
            "btCity",
            "btState",
            "btZip",
            "btCountryNum",
            "btCountry",
            "btPhoneNum",
            "btFaxNum",
            "btFormatStr",
            "parentCustNum",
            "taxRegionCode",
            "icCust",
            "contBillDay",
            "eMailAddress",
            "shippingQualifier",
            "allocPriorityCode",
            "linkPortNum",
            "webCustomer",
            "customerType",
            "noContact",
            "territoryLock",
            "custURL",
            "pendingTerritoryID",
            "extID",
            "consolidateSO",
            "billFrequency",
            "creditIncludePI",
            "globalCust",
            "glbCompany",
            "custID",
            "icTrader",
            "custNum",
            "globalCredIncOrd",
            "globalCredIncPI",
            "globalCurrencyCode",
            "globalCreditHold",
            "globalLock",
            "oldCompany",
            "oldCustNum",
            "oldCustID",
            "taxAuthorityCode",
            "externalDeliveryNote",
            "externalID",
            "checkDuplicatePO",
            "creditLimit",
            "custPILimit",
            "globalCreditLimit",
            "globalPILimit",
            "docGlobalCreditLimit",
            "docGlobalPILimit",
            "rfqAttachAllow",
            "discountQualifier",
            "skipped",
            "number11",
            "number12",
            "number13",
            "number14",
            "number15",
            "number16",
            "number17",
            "number18",
            "number19",
            "number20",
            "date06",
            "date07",
            "date08",
            "date09",
            "date10",
            "date11",
            "date12",
            "date13",
            "date14",
            "date15",
            "date16",
            "date17",
            "date18",
            "date19",
            "date20",
            "checkBox06",
            "checkBox07",
            "checkBox08",
            "checkBox09",
            "checkBox10",
            "checkBox11",
            "checkBox12",
            "checkBox13",
            "checkBox14",
            "checkBox15",
            "checkBox16",
            "checkBox17",
            "checkBox18",
            "checkBox19",
            "checkBox20",
            "shortChar01",
            "shortChar02",
            "shortChar03",
            "shortChar04",
            "shortChar05",
            "shortChar06",
            "shortChar07",
            "shortChar08",
            "shortChar09",
            "shortChar10",
            "allowAltBillTo",
            "applyChrg",
            "chrgAmount",
            "cod",
            "codAmount",
            "codCheck",
            "codFreight",
            "declaredAmt",
            "declaredIns",
            "demandAddAction",
            "demandAddLeadTime",
            "demandAutoAccept",
            "demandCancelAction",
            "demandCancelLeadTime",
            "demandChangeAction",
            "demandChangeDateAction",
            "demandChangeDateLeadTime",
            "demandChangeLeadTime",
            "demandDateType",
            "demandDayOfWeek",
            "demandDeliveryDays",
            "demandNewLineAction",
            "demandNewLineLeadTime",
            "demandQtyChangeAction",
            "demandQtyChangeLeadTime",
            "demandUseSysDate",
            "docOnly",
            "groundType",
            "hazmat",
            "notifyEMail",
            "notifyFlag",
            "refNotes",
            "resDelivery",
            "satDelivery",
            "satPickup",
            "tradingPartnerName",
            "verbalConf",
            "periodicityCode",
            "deliveryType",
            "servAlert",
            "servAOD",
            "servAuthNum",
            "servDeliveryDate",
            "servHomeDel",
            "servInstruct",
            "servPhone",
            "servPOD",
            "servRef1",
            "servRef2",
            "servRef3",
            "servRef4",
            "servRef5",
            "servRelease",
            "servSatDelivery",
            "servSatPickup",
            "servSignature",
            "creditDays",
            "earlyBuffer",
            "lateBuffer",
            "overrideCarrier",
            "overrideService",
            "demandUnitPriceDiff",
            "demandUnitPriceDiffAction",
            "addressVal",
            "excFromVal",
            "rebateForm",
            "rebateVendorNum",
            "creditCardOrder",
            "demandCheckForPart",
            "demandCheckForPartAction",
            "changeDate",
            "changedBy",
            "changeTime",
            "chargeCode",
            "addlHdlgFlag",
            "certOfOrigin",
            "commercialInvoice",
            "deliveryConf",
            "etcAddrChg",
            "ffAddress1",
            "ffAddress2",
            "ffAddress3",
            "ffCity",
            "ffCompName",
            "ffContact",
            "ffCountry",
            "ffCountryNum",
            "ffid",
            "ffPhoneNum",
            "ffState",
            "ffZip",
            "individualPackIDs",
            "intrntlShip",
            "letterOfInstr",
            "nonStdPkg",
            "shipExprtDeclartn",
            "upsQuantumView",
            "upsqvEmailType",
            "upsqvMemo",
            "upsqvShipFromName",
            "arCreditTotal",
            "piCreditTotal",
            "soCreditTotal",
            "taxMethod",
            "taxRoundRule",
            "sysRowID",
            "sysRevID",
            "acrossNatAcc",
            "naCreditIsShare",
            "naCreditPreferenceList",
            "naCreditSharedPrc",
            "naParentCreditIsUsed",
            "naParentCreditPrc",
            "overrideRlsClass",
            "validPayer",
            "validShipTo",
            "validSoldTo",
            "allowOTS",
            "managedVendID",
            "managedVendNum",
            "thirdPLCust",
            "naRlsClassCode",
            "autoGenPromNotes",
            "directDebiting",
            "partPayment",
            "reservePriorityCode",
            "naParentsCreditUsed",
            "naSharedCreditUsed",
            "naOwnCreditUsed",
            "bitFlag",
            "glbNACreditIsShare",
            "glbNACreditSharedPrc",
            "glbNAParentCreditIsUsed",
            "glbNAParentCreditPrc",
            "glbNAParentsCreditUsed",
            "glbNASharedCreditUsed",
            "reminderCode",
            "allowShipTo3",
            "naCreditUpdate",
            "otsSaveAs",
            "custPartOpts",
            "hasBank",
            "pmuid",
            "demandCheckForRev",
            "demandCheckForRevAction",
            "orderHoldForReview",
            "shipToTerrList",
            "acctRefNumber",
            "arInvoiceAdjITCode",
            "arInvoiceITCode",
            "creditMemoITCode",
            "miscCashRecITCode",
            "demandCheckCUMM",
            "demandCheckCUMMAction",
            "demandCloseNoMatch",
            "demandCloseRejSkd",
            "demandPricing",
            "dmdCheckPartialShip",
            "dmdCheckShipAction",
            "invPerPackLine",
            "legalName",
            "orgRegCode",
            "ourBankCode",
            "priceTolerance",
            "taxRegReason",
            "checkConfirmCapPromise",
            "checkDateCapPromise",
            "checkUpdateCapPromise",
            "demandCapPromiseAction",
            "demandCapPromiseDate",
            "demandCapPromiseUpdate",
            "demandSplitSched",
            "dueDateCriteria",
            "ersOrder",
            "pbTerms",
            "periodicBilling",
            "preferredBank",
            "sicCode",
            "icCode",
            "otSmartString",
            "deferredRev",
            "raCode",
            "demandCheckCfgAction",
            "demandCheckConfig",
            "linkCustID",
            "displayHold",
            "displayCustomerType",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class GlbCustomer {

            @XmlElement(name = "Company", required = true)
            protected String company;
            @XmlElement(name = "GlbCustID")
            protected String glbCustID;
            @XmlElement(name = "GlbCustNum")
            protected int glbCustNum;
            @XmlElement(name = "Name")
            protected String name;
            @XmlElement(name = "Address1")
            protected String address1;
            @XmlElement(name = "Address2")
            protected String address2;
            @XmlElement(name = "Address3")
            protected String address3;
            @XmlElement(name = "City")
            protected String city;
            @XmlElement(name = "State")
            protected String state;
            @XmlElement(name = "Zip")
            protected String zip;
            @XmlElement(name = "Country")
            protected String country;
            @XmlElement(name = "ResaleID")
            protected String resaleID;
            @XmlElement(name = "SalesRepCode")
            protected String salesRepCode;
            @XmlElement(name = "TerritoryID")
            protected String territoryID;
            @XmlElement(name = "ShipToNum")
            protected String shipToNum;
            @XmlElement(name = "TermsCode")
            protected String termsCode;
            @XmlElement(name = "ShipViaCode")
            protected String shipViaCode;
            @XmlElement(name = "PrintStatements")
            protected Boolean printStatements;
            @XmlElement(name = "PrintLabels")
            protected Boolean printLabels;
            @XmlElement(name = "PrintAck")
            protected Boolean printAck;
            @XmlElement(name = "FinCharges")
            protected Boolean finCharges;
            @XmlElement(name = "CreditHold")
            protected Boolean creditHold;
            @XmlElement(name = "GroupCode")
            protected String groupCode;
            @XmlElement(name = "DiscountPercent")
            protected BigDecimal discountPercent;
            @XmlElement(name = "PrimPCon")
            protected Integer primPCon;
            @XmlElement(name = "PrimBCon")
            protected Integer primBCon;
            @XmlElement(name = "PrimSCon")
            protected Integer primSCon;
            @XmlElement(name = "Comment")
            protected String comment;
            @XmlElement(name = "EstDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar estDate;
            @XmlElement(name = "FaxNum")
            protected String faxNum;
            @XmlElement(name = "PhoneNum")
            protected String phoneNum;
            @XmlElement(name = "TaxExempt")
            protected String taxExempt;
            @XmlElement(name = "MarkUpID")
            protected String markUpID;
            @XmlElement(name = "BillDay")
            protected Integer billDay;
            @XmlElement(name = "OneInvPerPS")
            protected Boolean oneInvPerPS;
            @XmlElement(name = "DefaultFOB")
            protected String defaultFOB;
            @XmlElement(name = "CreditIncludeOrders")
            protected Boolean creditIncludeOrders;
            @XmlElement(name = "CreditReviewDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar creditReviewDate;
            @XmlElement(name = "CreditHoldDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar creditHoldDate;
            @XmlElement(name = "CreditHoldSource")
            protected String creditHoldSource;
            @XmlElement(name = "CreditClearUserID")
            protected String creditClearUserID;
            @XmlElement(name = "CreditClearDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar creditClearDate;
            @XmlElement(name = "CreditClearTime")
            protected String creditClearTime;
            @XmlElement(name = "EDICode")
            protected String ediCode;
            @XmlElement(name = "EDITest")
            protected Boolean ediTest;
            @XmlElement(name = "EDITranslator")
            protected String ediTranslator;
            @XmlElement(name = "Character01")
            protected String character01;
            @XmlElement(name = "Character02")
            protected String character02;
            @XmlElement(name = "Character03")
            protected String character03;
            @XmlElement(name = "Character04")
            protected String character04;
            @XmlElement(name = "Character05")
            protected String character05;
            @XmlElement(name = "Character06")
            protected String character06;
            @XmlElement(name = "Character07")
            protected String character07;
            @XmlElement(name = "Character08")
            protected String character08;
            @XmlElement(name = "Character09")
            protected String character09;
            @XmlElement(name = "Character10")
            protected String character10;
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
            @XmlElement(name = "Number06")
            protected BigDecimal number06;
            @XmlElement(name = "Number07")
            protected BigDecimal number07;
            @XmlElement(name = "Number08")
            protected BigDecimal number08;
            @XmlElement(name = "Number09")
            protected BigDecimal number09;
            @XmlElement(name = "Number10")
            protected BigDecimal number10;
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
            @XmlElement(name = "CurrencyCode")
            protected String currencyCode;
            @XmlElement(name = "CountryNum")
            protected Integer countryNum;
            @XmlElement(name = "LangNameID")
            protected String langNameID;
            @XmlElement(name = "BorderCrossing")
            protected String borderCrossing;
            @XmlElement(name = "FormatStr")
            protected String formatStr;
            @XmlElement(name = "BTName")
            protected String btName;
            @XmlElement(name = "BTAddress1")
            protected String btAddress1;
            @XmlElement(name = "BTAddress2")
            protected String btAddress2;
            @XmlElement(name = "BTAddress3")
            protected String btAddress3;
            @XmlElement(name = "BTCity")
            protected String btCity;
            @XmlElement(name = "BTState")
            protected String btState;
            @XmlElement(name = "BTZip")
            protected String btZip;
            @XmlElement(name = "BTCountryNum")
            protected Integer btCountryNum;
            @XmlElement(name = "BTCountry")
            protected String btCountry;
            @XmlElement(name = "BTPhoneNum")
            protected String btPhoneNum;
            @XmlElement(name = "BTFaxNum")
            protected String btFaxNum;
            @XmlElement(name = "BTFormatStr")
            protected String btFormatStr;
            @XmlElement(name = "ParentCustNum")
            protected Integer parentCustNum;
            @XmlElement(name = "TaxRegionCode")
            protected String taxRegionCode;
            @XmlElement(name = "ICCust")
            protected Boolean icCust;
            @XmlElement(name = "ContBillDay")
            protected Integer contBillDay;
            @XmlElement(name = "EMailAddress")
            protected String eMailAddress;
            @XmlElement(name = "ShippingQualifier")
            protected String shippingQualifier;
            @XmlElement(name = "AllocPriorityCode")
            protected String allocPriorityCode;
            @XmlElement(name = "LinkPortNum")
            protected Integer linkPortNum;
            @XmlElement(name = "WebCustomer")
            protected Boolean webCustomer;
            @XmlElement(name = "CustomerType")
            protected String customerType;
            @XmlElement(name = "NoContact")
            protected Boolean noContact;
            @XmlElement(name = "TerritoryLock")
            protected Boolean territoryLock;
            @XmlElement(name = "CustURL")
            protected String custURL;
            @XmlElement(name = "PendingTerritoryID")
            protected String pendingTerritoryID;
            @XmlElement(name = "ExtID")
            protected String extID;
            @XmlElement(name = "ConsolidateSO")
            protected Boolean consolidateSO;
            @XmlElement(name = "Bill-Frequency")
            protected String billFrequency;
            @XmlElement(name = "CreditIncludePI")
            protected Boolean creditIncludePI;
            @XmlElement(name = "GlobalCust")
            protected Boolean globalCust;
            @XmlElement(name = "GlbCompany", required = true)
            protected String glbCompany;
            @XmlElement(name = "CustID")
            protected String custID;
            @XmlElement(name = "ICTrader")
            protected Boolean icTrader;
            @XmlElement(name = "CustNum")
            protected Integer custNum;
            @XmlElement(name = "GlobalCredIncOrd")
            protected Boolean globalCredIncOrd;
            @XmlElement(name = "GlobalCredIncPI")
            protected Boolean globalCredIncPI;
            @XmlElement(name = "GlobalCurrencyCode")
            protected String globalCurrencyCode;
            @XmlElement(name = "GlobalCreditHold")
            protected String globalCreditHold;
            @XmlElement(name = "GlobalLock")
            protected Boolean globalLock;
            @XmlElement(name = "OldCompany")
            protected String oldCompany;
            @XmlElement(name = "OldCustNum")
            protected Integer oldCustNum;
            @XmlElement(name = "OldCustID")
            protected String oldCustID;
            @XmlElement(name = "TaxAuthorityCode")
            protected String taxAuthorityCode;
            @XmlElement(name = "ExternalDeliveryNote")
            protected Boolean externalDeliveryNote;
            @XmlElement(name = "ExternalID")
            protected String externalID;
            @XmlElement(name = "CheckDuplicatePO")
            protected Boolean checkDuplicatePO;
            @XmlElement(name = "CreditLimit")
            protected BigDecimal creditLimit;
            @XmlElement(name = "CustPILimit")
            protected BigDecimal custPILimit;
            @XmlElement(name = "GlobalCreditLimit")
            protected BigDecimal globalCreditLimit;
            @XmlElement(name = "GlobalPILimit")
            protected BigDecimal globalPILimit;
            @XmlElement(name = "DocGlobalCreditLimit")
            protected BigDecimal docGlobalCreditLimit;
            @XmlElement(name = "DocGlobalPILimit")
            protected BigDecimal docGlobalPILimit;
            @XmlElement(name = "RfqAttachAllow")
            protected Boolean rfqAttachAllow;
            @XmlElement(name = "DiscountQualifier")
            protected String discountQualifier;
            @XmlElement(name = "Skipped")
            protected Boolean skipped;
            @XmlElement(name = "Number11")
            protected BigDecimal number11;
            @XmlElement(name = "Number12")
            protected BigDecimal number12;
            @XmlElement(name = "Number13")
            protected BigDecimal number13;
            @XmlElement(name = "Number14")
            protected BigDecimal number14;
            @XmlElement(name = "Number15")
            protected BigDecimal number15;
            @XmlElement(name = "Number16")
            protected BigDecimal number16;
            @XmlElement(name = "Number17")
            protected BigDecimal number17;
            @XmlElement(name = "Number18")
            protected BigDecimal number18;
            @XmlElement(name = "Number19")
            protected BigDecimal number19;
            @XmlElement(name = "Number20")
            protected BigDecimal number20;
            @XmlElement(name = "Date06")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date06;
            @XmlElement(name = "Date07")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date07;
            @XmlElement(name = "Date08")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date08;
            @XmlElement(name = "Date09")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date09;
            @XmlElement(name = "Date10")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date10;
            @XmlElement(name = "Date11")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date11;
            @XmlElement(name = "Date12")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date12;
            @XmlElement(name = "Date13")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date13;
            @XmlElement(name = "Date14")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date14;
            @XmlElement(name = "Date15")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date15;
            @XmlElement(name = "Date16")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date16;
            @XmlElement(name = "Date17")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date17;
            @XmlElement(name = "Date18")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date18;
            @XmlElement(name = "Date19")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date19;
            @XmlElement(name = "Date20")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date20;
            @XmlElement(name = "CheckBox06")
            protected Boolean checkBox06;
            @XmlElement(name = "CheckBox07")
            protected Boolean checkBox07;
            @XmlElement(name = "CheckBox08")
            protected Boolean checkBox08;
            @XmlElement(name = "CheckBox09")
            protected Boolean checkBox09;
            @XmlElement(name = "CheckBox10")
            protected Boolean checkBox10;
            @XmlElement(name = "CheckBox11")
            protected Boolean checkBox11;
            @XmlElement(name = "CheckBox12")
            protected Boolean checkBox12;
            @XmlElement(name = "CheckBox13")
            protected Boolean checkBox13;
            @XmlElement(name = "CheckBox14")
            protected Boolean checkBox14;
            @XmlElement(name = "CheckBox15")
            protected Boolean checkBox15;
            @XmlElement(name = "CheckBox16")
            protected Boolean checkBox16;
            @XmlElement(name = "CheckBox17")
            protected Boolean checkBox17;
            @XmlElement(name = "CheckBox18")
            protected Boolean checkBox18;
            @XmlElement(name = "CheckBox19")
            protected Boolean checkBox19;
            @XmlElement(name = "CheckBox20")
            protected Boolean checkBox20;
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
            @XmlElement(name = "ShortChar06")
            protected String shortChar06;
            @XmlElement(name = "ShortChar07")
            protected String shortChar07;
            @XmlElement(name = "ShortChar08")
            protected String shortChar08;
            @XmlElement(name = "ShortChar09")
            protected String shortChar09;
            @XmlElement(name = "ShortChar10")
            protected String shortChar10;
            @XmlElement(name = "AllowAltBillTo")
            protected Boolean allowAltBillTo;
            @XmlElement(name = "ApplyChrg")
            protected Boolean applyChrg;
            @XmlElement(name = "ChrgAmount")
            protected BigDecimal chrgAmount;
            @XmlElement(name = "COD")
            protected Boolean cod;
            @XmlElement(name = "CODAmount")
            protected BigDecimal codAmount;
            @XmlElement(name = "CODCheck")
            protected Boolean codCheck;
            @XmlElement(name = "CODFreight")
            protected Boolean codFreight;
            @XmlElement(name = "DeclaredAmt")
            protected BigDecimal declaredAmt;
            @XmlElement(name = "DeclaredIns")
            protected Boolean declaredIns;
            @XmlElement(name = "DemandAddAction")
            protected String demandAddAction;
            @XmlElement(name = "DemandAddLeadTime")
            protected Integer demandAddLeadTime;
            @XmlElement(name = "DemandAutoAccept")
            protected Boolean demandAutoAccept;
            @XmlElement(name = "DemandCancelAction")
            protected String demandCancelAction;
            @XmlElement(name = "DemandCancelLeadTime")
            protected Integer demandCancelLeadTime;
            @XmlElement(name = "DemandChangeAction")
            protected String demandChangeAction;
            @XmlElement(name = "DemandChangeDateAction")
            protected String demandChangeDateAction;
            @XmlElement(name = "DemandChangeDateLeadTime")
            protected Integer demandChangeDateLeadTime;
            @XmlElement(name = "DemandChangeLeadTime")
            protected Integer demandChangeLeadTime;
            @XmlElement(name = "DemandDateType")
            protected String demandDateType;
            @XmlElement(name = "DemandDayOfWeek")
            protected Integer demandDayOfWeek;
            @XmlElement(name = "DemandDeliveryDays")
            protected Integer demandDeliveryDays;
            @XmlElement(name = "DemandNewLineAction")
            protected String demandNewLineAction;
            @XmlElement(name = "DemandNewLineLeadTime")
            protected Integer demandNewLineLeadTime;
            @XmlElement(name = "DemandQtyChangeAction")
            protected String demandQtyChangeAction;
            @XmlElement(name = "DemandQtyChangeLeadTime")
            protected Integer demandQtyChangeLeadTime;
            @XmlElement(name = "DemandUseSysDate")
            protected Boolean demandUseSysDate;
            @XmlElement(name = "DocOnly")
            protected Boolean docOnly;
            @XmlElement(name = "GroundType")
            protected String groundType;
            @XmlElement(name = "Hazmat")
            protected Boolean hazmat;
            @XmlElement(name = "NotifyEMail")
            protected String notifyEMail;
            @XmlElement(name = "NotifyFlag")
            protected Boolean notifyFlag;
            @XmlElement(name = "RefNotes")
            protected String refNotes;
            @XmlElement(name = "ResDelivery")
            protected Boolean resDelivery;
            @XmlElement(name = "SatDelivery")
            protected Boolean satDelivery;
            @XmlElement(name = "SatPickup")
            protected Boolean satPickup;
            @XmlElement(name = "TradingPartnerName")
            protected String tradingPartnerName;
            @XmlElement(name = "VerbalConf")
            protected Boolean verbalConf;
            @XmlElement(name = "PeriodicityCode")
            protected Integer periodicityCode;
            @XmlElement(name = "DeliveryType")
            protected String deliveryType;
            @XmlElement(name = "ServAlert")
            protected Boolean servAlert;
            @XmlElement(name = "ServAOD")
            protected Boolean servAOD;
            @XmlElement(name = "ServAuthNum")
            protected String servAuthNum;
            @XmlElement(name = "ServDeliveryDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar servDeliveryDate;
            @XmlElement(name = "ServHomeDel")
            protected Boolean servHomeDel;
            @XmlElement(name = "ServInstruct")
            protected String servInstruct;
            @XmlElement(name = "ServPhone")
            protected String servPhone;
            @XmlElement(name = "ServPOD")
            protected Boolean servPOD;
            @XmlElement(name = "ServRef1")
            protected String servRef1;
            @XmlElement(name = "ServRef2")
            protected String servRef2;
            @XmlElement(name = "ServRef3")
            protected String servRef3;
            @XmlElement(name = "ServRef4")
            protected String servRef4;
            @XmlElement(name = "ServRef5")
            protected String servRef5;
            @XmlElement(name = "ServRelease")
            protected Boolean servRelease;
            @XmlElement(name = "ServSatDelivery")
            protected Boolean servSatDelivery;
            @XmlElement(name = "ServSatPickup")
            protected Boolean servSatPickup;
            @XmlElement(name = "ServSignature")
            protected Boolean servSignature;
            @XmlElement(name = "CreditDays")
            protected Integer creditDays;
            @XmlElement(name = "EarlyBuffer")
            protected Integer earlyBuffer;
            @XmlElement(name = "LateBuffer")
            protected Integer lateBuffer;
            @XmlElement(name = "OverrideCarrier")
            protected Boolean overrideCarrier;
            @XmlElement(name = "OverrideService")
            protected Boolean overrideService;
            @XmlElement(name = "DemandUnitPriceDiff")
            protected Boolean demandUnitPriceDiff;
            @XmlElement(name = "DemandUnitPriceDiffAction")
            protected String demandUnitPriceDiffAction;
            @XmlElement(name = "AddressVal")
            protected Boolean addressVal;
            @XmlElement(name = "ExcFromVal")
            protected Boolean excFromVal;
            @XmlElement(name = "RebateForm")
            protected String rebateForm;
            @XmlElement(name = "RebateVendorNum")
            protected Integer rebateVendorNum;
            @XmlElement(name = "CreditCardOrder")
            protected Boolean creditCardOrder;
            @XmlElement(name = "DemandCheckForPart")
            protected Boolean demandCheckForPart;
            @XmlElement(name = "DemandCheckForPartAction")
            protected String demandCheckForPartAction;
            @XmlElement(name = "ChangeDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar changeDate;
            @XmlElement(name = "ChangedBy")
            protected String changedBy;
            @XmlElement(name = "ChangeTime")
            protected Integer changeTime;
            @XmlElement(name = "ChargeCode")
            protected String chargeCode;
            @XmlElement(name = "AddlHdlgFlag")
            protected Boolean addlHdlgFlag;
            @XmlElement(name = "CertOfOrigin")
            protected Boolean certOfOrigin;
            @XmlElement(name = "CommercialInvoice")
            protected Boolean commercialInvoice;
            @XmlElement(name = "DeliveryConf")
            protected Integer deliveryConf;
            @XmlElement(name = "ETCAddrChg")
            protected Boolean etcAddrChg;
            @XmlElement(name = "FFAddress1")
            protected String ffAddress1;
            @XmlElement(name = "FFAddress2")
            protected String ffAddress2;
            @XmlElement(name = "FFAddress3")
            protected String ffAddress3;
            @XmlElement(name = "FFCity")
            protected String ffCity;
            @XmlElement(name = "FFCompName")
            protected String ffCompName;
            @XmlElement(name = "FFContact")
            protected String ffContact;
            @XmlElement(name = "FFCountry")
            protected String ffCountry;
            @XmlElement(name = "FFCountryNum")
            protected Integer ffCountryNum;
            @XmlElement(name = "FFID")
            protected String ffid;
            @XmlElement(name = "FFPhoneNum")
            protected String ffPhoneNum;
            @XmlElement(name = "FFState")
            protected String ffState;
            @XmlElement(name = "FFZip")
            protected String ffZip;
            @XmlElement(name = "IndividualPackIDs")
            protected Boolean individualPackIDs;
            @XmlElement(name = "IntrntlShip")
            protected Boolean intrntlShip;
            @XmlElement(name = "LetterOfInstr")
            protected Boolean letterOfInstr;
            @XmlElement(name = "NonStdPkg")
            protected Boolean nonStdPkg;
            @XmlElement(name = "ShipExprtDeclartn")
            protected Boolean shipExprtDeclartn;
            @XmlElement(name = "UPSQuantumView")
            protected Boolean upsQuantumView;
            @XmlElement(name = "UPSQVEmailType")
            protected String upsqvEmailType;
            @XmlElement(name = "UPSQVMemo")
            protected String upsqvMemo;
            @XmlElement(name = "UPSQVShipFromName")
            protected String upsqvShipFromName;
            @XmlElement(name = "ARCreditTotal")
            protected BigDecimal arCreditTotal;
            @XmlElement(name = "PICreditTotal")
            protected BigDecimal piCreditTotal;
            @XmlElement(name = "SOCreditTotal")
            protected BigDecimal soCreditTotal;
            @XmlElement(name = "TaxMethod")
            protected String taxMethod;
            @XmlElement(name = "TaxRoundRule")
            protected Integer taxRoundRule;
            @XmlElement(name = "SysRowID")
            protected String sysRowID;
            @XmlElement(name = "SysRevID")
            protected Integer sysRevID;
            @XmlElement(name = "AcrossNatAcc")
            protected Boolean acrossNatAcc;
            @XmlElement(name = "NACreditIsShare")
            protected Boolean naCreditIsShare;
            @XmlElement(name = "NACreditPreferenceList")
            protected String naCreditPreferenceList;
            @XmlElement(name = "NACreditSharedPrc")
            protected BigDecimal naCreditSharedPrc;
            @XmlElement(name = "NAParentCreditIsUsed")
            protected Boolean naParentCreditIsUsed;
            @XmlElement(name = "NAParentCreditPrc")
            protected BigDecimal naParentCreditPrc;
            @XmlElement(name = "OverrideRlsClass")
            protected Boolean overrideRlsClass;
            @XmlElement(name = "ValidPayer")
            protected Boolean validPayer;
            @XmlElement(name = "ValidShipTo")
            protected Boolean validShipTo;
            @XmlElement(name = "ValidSoldTo")
            protected Boolean validSoldTo;
            @XmlElement(name = "AllowOTS")
            protected Boolean allowOTS;
            @XmlElement(name = "ManagedVendID")
            protected String managedVendID;
            @XmlElement(name = "ManagedVendNum")
            protected Integer managedVendNum;
            @XmlElement(name = "ThirdPLCust")
            protected Boolean thirdPLCust;
            @XmlElement(name = "NARlsClassCode")
            protected String naRlsClassCode;
            @XmlElement(name = "AutoGenPromNotes")
            protected Boolean autoGenPromNotes;
            @XmlElement(name = "DirectDebiting")
            protected Boolean directDebiting;
            @XmlElement(name = "PartPayment")
            protected Boolean partPayment;
            @XmlElement(name = "ReservePriorityCode")
            protected String reservePriorityCode;
            @XmlElement(name = "NAParentsCreditUsed")
            protected BigDecimal naParentsCreditUsed;
            @XmlElement(name = "NASharedCreditUsed")
            protected BigDecimal naSharedCreditUsed;
            @XmlElement(name = "NAOwnCreditUsed")
            protected BigDecimal naOwnCreditUsed;
            @XmlElement(name = "BitFlag")
            protected Integer bitFlag;
            @XmlElement(name = "GlbNACreditIsShare")
            protected Boolean glbNACreditIsShare;
            @XmlElement(name = "GlbNACreditSharedPrc")
            protected BigDecimal glbNACreditSharedPrc;
            @XmlElement(name = "GlbNAParentCreditIsUsed")
            protected Boolean glbNAParentCreditIsUsed;
            @XmlElement(name = "GlbNAParentCreditPrc")
            protected BigDecimal glbNAParentCreditPrc;
            @XmlElement(name = "GlbNAParentsCreditUsed")
            protected BigDecimal glbNAParentsCreditUsed;
            @XmlElement(name = "GlbNASharedCreditUsed")
            protected BigDecimal glbNASharedCreditUsed;
            @XmlElement(name = "ReminderCode")
            protected String reminderCode;
            @XmlElement(name = "AllowShipTo3")
            protected Boolean allowShipTo3;
            @XmlElement(name = "NACreditUpdate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar naCreditUpdate;
            @XmlElement(name = "OTSSaveAs")
            protected String otsSaveAs;
            @XmlElement(name = "CustPartOpts")
            protected String custPartOpts;
            @XmlElement(name = "HasBank")
            protected Boolean hasBank;
            @XmlElement(name = "PMUID")
            protected Integer pmuid;
            @XmlElement(name = "DemandCheckForRev")
            protected Boolean demandCheckForRev;
            @XmlElement(name = "DemandCheckForRevAction")
            protected String demandCheckForRevAction;
            @XmlElement(name = "OrderHoldForReview")
            protected Boolean orderHoldForReview;
            @XmlElement(name = "ShipToTerrList")
            protected String shipToTerrList;
            @XmlElement(name = "AcctRefNumber")
            protected String acctRefNumber;
            @XmlElement(name = "ARInvoiceAdjITCode")
            protected String arInvoiceAdjITCode;
            @XmlElement(name = "ARInvoiceITCode")
            protected String arInvoiceITCode;
            @XmlElement(name = "CreditMemoITCode")
            protected String creditMemoITCode;
            @XmlElement(name = "MiscCashRecITCode")
            protected String miscCashRecITCode;
            @XmlElement(name = "DemandCheckCUMM")
            protected Boolean demandCheckCUMM;
            @XmlElement(name = "DemandCheckCUMMAction")
            protected String demandCheckCUMMAction;
            @XmlElement(name = "DemandCloseNoMatch")
            protected Boolean demandCloseNoMatch;
            @XmlElement(name = "DemandCloseRejSkd")
            protected Boolean demandCloseRejSkd;
            @XmlElement(name = "DemandPricing")
            protected String demandPricing;
            @XmlElement(name = "DmdCheckPartialShip")
            protected Boolean dmdCheckPartialShip;
            @XmlElement(name = "DmdCheckShipAction")
            protected String dmdCheckShipAction;
            @XmlElement(name = "InvPerPackLine")
            protected Boolean invPerPackLine;
            @XmlElement(name = "LegalName")
            protected String legalName;
            @XmlElement(name = "OrgRegCode")
            protected String orgRegCode;
            @XmlElement(name = "OurBankCode")
            protected String ourBankCode;
            @XmlElement(name = "PriceTolerance")
            protected BigDecimal priceTolerance;
            @XmlElement(name = "TaxRegReason")
            protected String taxRegReason;
            @XmlElement(name = "CheckConfirmCapPromise")
            protected Boolean checkConfirmCapPromise;
            @XmlElement(name = "CheckDateCapPromise")
            protected Boolean checkDateCapPromise;
            @XmlElement(name = "CheckUpdateCapPromise")
            protected Boolean checkUpdateCapPromise;
            @XmlElement(name = "DemandCapPromiseAction")
            protected String demandCapPromiseAction;
            @XmlElement(name = "DemandCapPromiseDate")
            protected String demandCapPromiseDate;
            @XmlElement(name = "DemandCapPromiseUpdate")
            protected String demandCapPromiseUpdate;
            @XmlElement(name = "DemandSplitSched")
            protected Boolean demandSplitSched;
            @XmlElement(name = "DueDateCriteria")
            protected String dueDateCriteria;
            @XmlElement(name = "ERSOrder")
            protected Boolean ersOrder;
            @XmlElement(name = "PBTerms")
            protected Integer pbTerms;
            @XmlElement(name = "PeriodicBilling")
            protected Boolean periodicBilling;
            @XmlElement(name = "PreferredBank")
            protected String preferredBank;
            @XmlElement(name = "SICCode")
            protected Integer sicCode;
            @XmlElement(name = "ICCode")
            protected String icCode;
            @XmlElement(name = "OTSmartString")
            protected Boolean otSmartString;
            @XmlElement(name = "DeferredRev")
            protected Boolean deferredRev;
            @XmlElement(name = "RACode")
            protected String raCode;
            @XmlElement(name = "DemandCheckCfgAction")
            protected String demandCheckCfgAction;
            @XmlElement(name = "DemandCheckConfig")
            protected Boolean demandCheckConfig;
            @XmlElement(name = "LinkCustID")
            protected String linkCustID;
            @XmlElement(name = "DisplayHold")
            protected String displayHold;
            @XmlElement(name = "DisplayCustomerType")
            protected String displayCustomerType;
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
             * Gets the value of the glbCustID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGlbCustID() {
                return glbCustID;
            }

            /**
             * Sets the value of the glbCustID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGlbCustID(String value) {
                this.glbCustID = value;
            }

            /**
             * Gets the value of the glbCustNum property.
             * 
             */
            public int getGlbCustNum() {
                return glbCustNum;
            }

            /**
             * Sets the value of the glbCustNum property.
             * 
             */
            public void setGlbCustNum(int value) {
                this.glbCustNum = value;
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
             * Gets the value of the address1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddress1() {
                return address1;
            }

            /**
             * Sets the value of the address1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddress1(String value) {
                this.address1 = value;
            }

            /**
             * Gets the value of the address2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddress2() {
                return address2;
            }

            /**
             * Sets the value of the address2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddress2(String value) {
                this.address2 = value;
            }

            /**
             * Gets the value of the address3 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddress3() {
                return address3;
            }

            /**
             * Sets the value of the address3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddress3(String value) {
                this.address3 = value;
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
             * Gets the value of the state property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getState() {
                return state;
            }

            /**
             * Sets the value of the state property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setState(String value) {
                this.state = value;
            }

            /**
             * Gets the value of the zip property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZip() {
                return zip;
            }

            /**
             * Sets the value of the zip property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZip(String value) {
                this.zip = value;
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
             * Gets the value of the resaleID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getResaleID() {
                return resaleID;
            }

            /**
             * Sets the value of the resaleID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setResaleID(String value) {
                this.resaleID = value;
            }

            /**
             * Gets the value of the salesRepCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSalesRepCode() {
                return salesRepCode;
            }

            /**
             * Sets the value of the salesRepCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSalesRepCode(String value) {
                this.salesRepCode = value;
            }

            /**
             * Gets the value of the territoryID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTerritoryID() {
                return territoryID;
            }

            /**
             * Sets the value of the territoryID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTerritoryID(String value) {
                this.territoryID = value;
            }

            /**
             * Gets the value of the shipToNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShipToNum() {
                return shipToNum;
            }

            /**
             * Sets the value of the shipToNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShipToNum(String value) {
                this.shipToNum = value;
            }

            /**
             * Gets the value of the termsCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTermsCode() {
                return termsCode;
            }

            /**
             * Sets the value of the termsCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTermsCode(String value) {
                this.termsCode = value;
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
             * Gets the value of the printStatements property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPrintStatements() {
                return printStatements;
            }

            /**
             * Sets the value of the printStatements property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPrintStatements(Boolean value) {
                this.printStatements = value;
            }

            /**
             * Gets the value of the printLabels property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPrintLabels() {
                return printLabels;
            }

            /**
             * Sets the value of the printLabels property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPrintLabels(Boolean value) {
                this.printLabels = value;
            }

            /**
             * Gets the value of the printAck property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPrintAck() {
                return printAck;
            }

            /**
             * Sets the value of the printAck property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPrintAck(Boolean value) {
                this.printAck = value;
            }

            /**
             * Gets the value of the finCharges property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isFinCharges() {
                return finCharges;
            }

            /**
             * Sets the value of the finCharges property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setFinCharges(Boolean value) {
                this.finCharges = value;
            }

            /**
             * Gets the value of the creditHold property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCreditHold() {
                return creditHold;
            }

            /**
             * Sets the value of the creditHold property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCreditHold(Boolean value) {
                this.creditHold = value;
            }

            /**
             * Gets the value of the groupCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGroupCode() {
                return groupCode;
            }

            /**
             * Sets the value of the groupCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGroupCode(String value) {
                this.groupCode = value;
            }

            /**
             * Gets the value of the discountPercent property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getDiscountPercent() {
                return discountPercent;
            }

            /**
             * Sets the value of the discountPercent property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setDiscountPercent(BigDecimal value) {
                this.discountPercent = value;
            }

            /**
             * Gets the value of the primPCon property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getPrimPCon() {
                return primPCon;
            }

            /**
             * Sets the value of the primPCon property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setPrimPCon(Integer value) {
                this.primPCon = value;
            }

            /**
             * Gets the value of the primBCon property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getPrimBCon() {
                return primBCon;
            }

            /**
             * Sets the value of the primBCon property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setPrimBCon(Integer value) {
                this.primBCon = value;
            }

            /**
             * Gets the value of the primSCon property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getPrimSCon() {
                return primSCon;
            }

            /**
             * Sets the value of the primSCon property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setPrimSCon(Integer value) {
                this.primSCon = value;
            }

            /**
             * Gets the value of the comment property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getComment() {
                return comment;
            }

            /**
             * Sets the value of the comment property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setComment(String value) {
                this.comment = value;
            }

            /**
             * Gets the value of the estDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getEstDate() {
                return estDate;
            }

            /**
             * Sets the value of the estDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setEstDate(XMLGregorianCalendar value) {
                this.estDate = value;
            }

            /**
             * Gets the value of the faxNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFaxNum() {
                return faxNum;
            }

            /**
             * Sets the value of the faxNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFaxNum(String value) {
                this.faxNum = value;
            }

            /**
             * Gets the value of the phoneNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPhoneNum() {
                return phoneNum;
            }

            /**
             * Sets the value of the phoneNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPhoneNum(String value) {
                this.phoneNum = value;
            }

            /**
             * Gets the value of the taxExempt property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTaxExempt() {
                return taxExempt;
            }

            /**
             * Sets the value of the taxExempt property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTaxExempt(String value) {
                this.taxExempt = value;
            }

            /**
             * Gets the value of the markUpID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMarkUpID() {
                return markUpID;
            }

            /**
             * Sets the value of the markUpID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMarkUpID(String value) {
                this.markUpID = value;
            }

            /**
             * Gets the value of the billDay property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getBillDay() {
                return billDay;
            }

            /**
             * Sets the value of the billDay property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setBillDay(Integer value) {
                this.billDay = value;
            }

            /**
             * Gets the value of the oneInvPerPS property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOneInvPerPS() {
                return oneInvPerPS;
            }

            /**
             * Sets the value of the oneInvPerPS property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOneInvPerPS(Boolean value) {
                this.oneInvPerPS = value;
            }

            /**
             * Gets the value of the defaultFOB property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDefaultFOB() {
                return defaultFOB;
            }

            /**
             * Sets the value of the defaultFOB property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDefaultFOB(String value) {
                this.defaultFOB = value;
            }

            /**
             * Gets the value of the creditIncludeOrders property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCreditIncludeOrders() {
                return creditIncludeOrders;
            }

            /**
             * Sets the value of the creditIncludeOrders property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCreditIncludeOrders(Boolean value) {
                this.creditIncludeOrders = value;
            }

            /**
             * Gets the value of the creditReviewDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getCreditReviewDate() {
                return creditReviewDate;
            }

            /**
             * Sets the value of the creditReviewDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setCreditReviewDate(XMLGregorianCalendar value) {
                this.creditReviewDate = value;
            }

            /**
             * Gets the value of the creditHoldDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getCreditHoldDate() {
                return creditHoldDate;
            }

            /**
             * Sets the value of the creditHoldDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setCreditHoldDate(XMLGregorianCalendar value) {
                this.creditHoldDate = value;
            }

            /**
             * Gets the value of the creditHoldSource property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCreditHoldSource() {
                return creditHoldSource;
            }

            /**
             * Sets the value of the creditHoldSource property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCreditHoldSource(String value) {
                this.creditHoldSource = value;
            }

            /**
             * Gets the value of the creditClearUserID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCreditClearUserID() {
                return creditClearUserID;
            }

            /**
             * Sets the value of the creditClearUserID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCreditClearUserID(String value) {
                this.creditClearUserID = value;
            }

            /**
             * Gets the value of the creditClearDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getCreditClearDate() {
                return creditClearDate;
            }

            /**
             * Sets the value of the creditClearDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setCreditClearDate(XMLGregorianCalendar value) {
                this.creditClearDate = value;
            }

            /**
             * Gets the value of the creditClearTime property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCreditClearTime() {
                return creditClearTime;
            }

            /**
             * Sets the value of the creditClearTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCreditClearTime(String value) {
                this.creditClearTime = value;
            }

            /**
             * Gets the value of the ediCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEDICode() {
                return ediCode;
            }

            /**
             * Sets the value of the ediCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEDICode(String value) {
                this.ediCode = value;
            }

            /**
             * Gets the value of the ediTest property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isEDITest() {
                return ediTest;
            }

            /**
             * Sets the value of the ediTest property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setEDITest(Boolean value) {
                this.ediTest = value;
            }

            /**
             * Gets the value of the ediTranslator property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEDITranslator() {
                return ediTranslator;
            }

            /**
             * Sets the value of the ediTranslator property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEDITranslator(String value) {
                this.ediTranslator = value;
            }

            /**
             * Gets the value of the character01 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter01() {
                return character01;
            }

            /**
             * Sets the value of the character01 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter01(String value) {
                this.character01 = value;
            }

            /**
             * Gets the value of the character02 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter02() {
                return character02;
            }

            /**
             * Sets the value of the character02 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter02(String value) {
                this.character02 = value;
            }

            /**
             * Gets the value of the character03 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter03() {
                return character03;
            }

            /**
             * Sets the value of the character03 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter03(String value) {
                this.character03 = value;
            }

            /**
             * Gets the value of the character04 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter04() {
                return character04;
            }

            /**
             * Sets the value of the character04 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter04(String value) {
                this.character04 = value;
            }

            /**
             * Gets the value of the character05 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter05() {
                return character05;
            }

            /**
             * Sets the value of the character05 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter05(String value) {
                this.character05 = value;
            }

            /**
             * Gets the value of the character06 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter06() {
                return character06;
            }

            /**
             * Sets the value of the character06 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter06(String value) {
                this.character06 = value;
            }

            /**
             * Gets the value of the character07 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter07() {
                return character07;
            }

            /**
             * Sets the value of the character07 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter07(String value) {
                this.character07 = value;
            }

            /**
             * Gets the value of the character08 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter08() {
                return character08;
            }

            /**
             * Sets the value of the character08 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter08(String value) {
                this.character08 = value;
            }

            /**
             * Gets the value of the character09 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter09() {
                return character09;
            }

            /**
             * Sets the value of the character09 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter09(String value) {
                this.character09 = value;
            }

            /**
             * Gets the value of the character10 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter10() {
                return character10;
            }

            /**
             * Sets the value of the character10 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter10(String value) {
                this.character10 = value;
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
             * Gets the value of the number06 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber06() {
                return number06;
            }

            /**
             * Sets the value of the number06 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber06(BigDecimal value) {
                this.number06 = value;
            }

            /**
             * Gets the value of the number07 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber07() {
                return number07;
            }

            /**
             * Sets the value of the number07 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber07(BigDecimal value) {
                this.number07 = value;
            }

            /**
             * Gets the value of the number08 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber08() {
                return number08;
            }

            /**
             * Sets the value of the number08 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber08(BigDecimal value) {
                this.number08 = value;
            }

            /**
             * Gets the value of the number09 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber09() {
                return number09;
            }

            /**
             * Sets the value of the number09 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber09(BigDecimal value) {
                this.number09 = value;
            }

            /**
             * Gets the value of the number10 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber10() {
                return number10;
            }

            /**
             * Sets the value of the number10 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber10(BigDecimal value) {
                this.number10 = value;
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
             * Gets the value of the currencyCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCurrencyCode() {
                return currencyCode;
            }

            /**
             * Sets the value of the currencyCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCurrencyCode(String value) {
                this.currencyCode = value;
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
             * Gets the value of the langNameID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLangNameID() {
                return langNameID;
            }

            /**
             * Sets the value of the langNameID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLangNameID(String value) {
                this.langNameID = value;
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
             * Gets the value of the formatStr property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFormatStr() {
                return formatStr;
            }

            /**
             * Sets the value of the formatStr property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFormatStr(String value) {
                this.formatStr = value;
            }

            /**
             * Gets the value of the btName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTName() {
                return btName;
            }

            /**
             * Sets the value of the btName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTName(String value) {
                this.btName = value;
            }

            /**
             * Gets the value of the btAddress1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTAddress1() {
                return btAddress1;
            }

            /**
             * Sets the value of the btAddress1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTAddress1(String value) {
                this.btAddress1 = value;
            }

            /**
             * Gets the value of the btAddress2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTAddress2() {
                return btAddress2;
            }

            /**
             * Sets the value of the btAddress2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTAddress2(String value) {
                this.btAddress2 = value;
            }

            /**
             * Gets the value of the btAddress3 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTAddress3() {
                return btAddress3;
            }

            /**
             * Sets the value of the btAddress3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTAddress3(String value) {
                this.btAddress3 = value;
            }

            /**
             * Gets the value of the btCity property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTCity() {
                return btCity;
            }

            /**
             * Sets the value of the btCity property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTCity(String value) {
                this.btCity = value;
            }

            /**
             * Gets the value of the btState property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTState() {
                return btState;
            }

            /**
             * Sets the value of the btState property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTState(String value) {
                this.btState = value;
            }

            /**
             * Gets the value of the btZip property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTZip() {
                return btZip;
            }

            /**
             * Sets the value of the btZip property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTZip(String value) {
                this.btZip = value;
            }

            /**
             * Gets the value of the btCountryNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getBTCountryNum() {
                return btCountryNum;
            }

            /**
             * Sets the value of the btCountryNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setBTCountryNum(Integer value) {
                this.btCountryNum = value;
            }

            /**
             * Gets the value of the btCountry property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTCountry() {
                return btCountry;
            }

            /**
             * Sets the value of the btCountry property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTCountry(String value) {
                this.btCountry = value;
            }

            /**
             * Gets the value of the btPhoneNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTPhoneNum() {
                return btPhoneNum;
            }

            /**
             * Sets the value of the btPhoneNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTPhoneNum(String value) {
                this.btPhoneNum = value;
            }

            /**
             * Gets the value of the btFaxNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTFaxNum() {
                return btFaxNum;
            }

            /**
             * Sets the value of the btFaxNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTFaxNum(String value) {
                this.btFaxNum = value;
            }

            /**
             * Gets the value of the btFormatStr property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTFormatStr() {
                return btFormatStr;
            }

            /**
             * Sets the value of the btFormatStr property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTFormatStr(String value) {
                this.btFormatStr = value;
            }

            /**
             * Gets the value of the parentCustNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getParentCustNum() {
                return parentCustNum;
            }

            /**
             * Sets the value of the parentCustNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setParentCustNum(Integer value) {
                this.parentCustNum = value;
            }

            /**
             * Gets the value of the taxRegionCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTaxRegionCode() {
                return taxRegionCode;
            }

            /**
             * Sets the value of the taxRegionCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTaxRegionCode(String value) {
                this.taxRegionCode = value;
            }

            /**
             * Gets the value of the icCust property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isICCust() {
                return icCust;
            }

            /**
             * Sets the value of the icCust property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setICCust(Boolean value) {
                this.icCust = value;
            }

            /**
             * Gets the value of the contBillDay property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getContBillDay() {
                return contBillDay;
            }

            /**
             * Sets the value of the contBillDay property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setContBillDay(Integer value) {
                this.contBillDay = value;
            }

            /**
             * Gets the value of the eMailAddress property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEMailAddress() {
                return eMailAddress;
            }

            /**
             * Sets the value of the eMailAddress property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEMailAddress(String value) {
                this.eMailAddress = value;
            }

            /**
             * Gets the value of the shippingQualifier property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShippingQualifier() {
                return shippingQualifier;
            }

            /**
             * Sets the value of the shippingQualifier property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShippingQualifier(String value) {
                this.shippingQualifier = value;
            }

            /**
             * Gets the value of the allocPriorityCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAllocPriorityCode() {
                return allocPriorityCode;
            }

            /**
             * Sets the value of the allocPriorityCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAllocPriorityCode(String value) {
                this.allocPriorityCode = value;
            }

            /**
             * Gets the value of the linkPortNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getLinkPortNum() {
                return linkPortNum;
            }

            /**
             * Sets the value of the linkPortNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setLinkPortNum(Integer value) {
                this.linkPortNum = value;
            }

            /**
             * Gets the value of the webCustomer property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isWebCustomer() {
                return webCustomer;
            }

            /**
             * Sets the value of the webCustomer property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setWebCustomer(Boolean value) {
                this.webCustomer = value;
            }

            /**
             * Gets the value of the customerType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCustomerType() {
                return customerType;
            }

            /**
             * Sets the value of the customerType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCustomerType(String value) {
                this.customerType = value;
            }

            /**
             * Gets the value of the noContact property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isNoContact() {
                return noContact;
            }

            /**
             * Sets the value of the noContact property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setNoContact(Boolean value) {
                this.noContact = value;
            }

            /**
             * Gets the value of the territoryLock property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isTerritoryLock() {
                return territoryLock;
            }

            /**
             * Sets the value of the territoryLock property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setTerritoryLock(Boolean value) {
                this.territoryLock = value;
            }

            /**
             * Gets the value of the custURL property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCustURL() {
                return custURL;
            }

            /**
             * Sets the value of the custURL property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCustURL(String value) {
                this.custURL = value;
            }

            /**
             * Gets the value of the pendingTerritoryID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPendingTerritoryID() {
                return pendingTerritoryID;
            }

            /**
             * Sets the value of the pendingTerritoryID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPendingTerritoryID(String value) {
                this.pendingTerritoryID = value;
            }

            /**
             * Gets the value of the extID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getExtID() {
                return extID;
            }

            /**
             * Sets the value of the extID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setExtID(String value) {
                this.extID = value;
            }

            /**
             * Gets the value of the consolidateSO property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isConsolidateSO() {
                return consolidateSO;
            }

            /**
             * Sets the value of the consolidateSO property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setConsolidateSO(Boolean value) {
                this.consolidateSO = value;
            }

            /**
             * Gets the value of the billFrequency property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBillFrequency() {
                return billFrequency;
            }

            /**
             * Sets the value of the billFrequency property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBillFrequency(String value) {
                this.billFrequency = value;
            }

            /**
             * Gets the value of the creditIncludePI property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCreditIncludePI() {
                return creditIncludePI;
            }

            /**
             * Sets the value of the creditIncludePI property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCreditIncludePI(Boolean value) {
                this.creditIncludePI = value;
            }

            /**
             * Gets the value of the globalCust property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isGlobalCust() {
                return globalCust;
            }

            /**
             * Sets the value of the globalCust property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setGlobalCust(Boolean value) {
                this.globalCust = value;
            }

            /**
             * Gets the value of the glbCompany property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGlbCompany() {
                return glbCompany;
            }

            /**
             * Sets the value of the glbCompany property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGlbCompany(String value) {
                this.glbCompany = value;
            }

            /**
             * Gets the value of the custID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCustID() {
                return custID;
            }

            /**
             * Sets the value of the custID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCustID(String value) {
                this.custID = value;
            }

            /**
             * Gets the value of the icTrader property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isICTrader() {
                return icTrader;
            }

            /**
             * Sets the value of the icTrader property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setICTrader(Boolean value) {
                this.icTrader = value;
            }

            /**
             * Gets the value of the custNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getCustNum() {
                return custNum;
            }

            /**
             * Sets the value of the custNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setCustNum(Integer value) {
                this.custNum = value;
            }

            /**
             * Gets the value of the globalCredIncOrd property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isGlobalCredIncOrd() {
                return globalCredIncOrd;
            }

            /**
             * Sets the value of the globalCredIncOrd property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setGlobalCredIncOrd(Boolean value) {
                this.globalCredIncOrd = value;
            }

            /**
             * Gets the value of the globalCredIncPI property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isGlobalCredIncPI() {
                return globalCredIncPI;
            }

            /**
             * Sets the value of the globalCredIncPI property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setGlobalCredIncPI(Boolean value) {
                this.globalCredIncPI = value;
            }

            /**
             * Gets the value of the globalCurrencyCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGlobalCurrencyCode() {
                return globalCurrencyCode;
            }

            /**
             * Sets the value of the globalCurrencyCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGlobalCurrencyCode(String value) {
                this.globalCurrencyCode = value;
            }

            /**
             * Gets the value of the globalCreditHold property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGlobalCreditHold() {
                return globalCreditHold;
            }

            /**
             * Sets the value of the globalCreditHold property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGlobalCreditHold(String value) {
                this.globalCreditHold = value;
            }

            /**
             * Gets the value of the globalLock property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isGlobalLock() {
                return globalLock;
            }

            /**
             * Sets the value of the globalLock property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setGlobalLock(Boolean value) {
                this.globalLock = value;
            }

            /**
             * Gets the value of the oldCompany property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOldCompany() {
                return oldCompany;
            }

            /**
             * Sets the value of the oldCompany property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOldCompany(String value) {
                this.oldCompany = value;
            }

            /**
             * Gets the value of the oldCustNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getOldCustNum() {
                return oldCustNum;
            }

            /**
             * Sets the value of the oldCustNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setOldCustNum(Integer value) {
                this.oldCustNum = value;
            }

            /**
             * Gets the value of the oldCustID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOldCustID() {
                return oldCustID;
            }

            /**
             * Sets the value of the oldCustID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOldCustID(String value) {
                this.oldCustID = value;
            }

            /**
             * Gets the value of the taxAuthorityCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTaxAuthorityCode() {
                return taxAuthorityCode;
            }

            /**
             * Sets the value of the taxAuthorityCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTaxAuthorityCode(String value) {
                this.taxAuthorityCode = value;
            }

            /**
             * Gets the value of the externalDeliveryNote property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isExternalDeliveryNote() {
                return externalDeliveryNote;
            }

            /**
             * Sets the value of the externalDeliveryNote property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setExternalDeliveryNote(Boolean value) {
                this.externalDeliveryNote = value;
            }

            /**
             * Gets the value of the externalID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getExternalID() {
                return externalID;
            }

            /**
             * Sets the value of the externalID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setExternalID(String value) {
                this.externalID = value;
            }

            /**
             * Gets the value of the checkDuplicatePO property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckDuplicatePO() {
                return checkDuplicatePO;
            }

            /**
             * Sets the value of the checkDuplicatePO property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckDuplicatePO(Boolean value) {
                this.checkDuplicatePO = value;
            }

            /**
             * Gets the value of the creditLimit property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getCreditLimit() {
                return creditLimit;
            }

            /**
             * Sets the value of the creditLimit property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setCreditLimit(BigDecimal value) {
                this.creditLimit = value;
            }

            /**
             * Gets the value of the custPILimit property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getCustPILimit() {
                return custPILimit;
            }

            /**
             * Sets the value of the custPILimit property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setCustPILimit(BigDecimal value) {
                this.custPILimit = value;
            }

            /**
             * Gets the value of the globalCreditLimit property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getGlobalCreditLimit() {
                return globalCreditLimit;
            }

            /**
             * Sets the value of the globalCreditLimit property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setGlobalCreditLimit(BigDecimal value) {
                this.globalCreditLimit = value;
            }

            /**
             * Gets the value of the globalPILimit property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getGlobalPILimit() {
                return globalPILimit;
            }

            /**
             * Sets the value of the globalPILimit property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setGlobalPILimit(BigDecimal value) {
                this.globalPILimit = value;
            }

            /**
             * Gets the value of the docGlobalCreditLimit property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getDocGlobalCreditLimit() {
                return docGlobalCreditLimit;
            }

            /**
             * Sets the value of the docGlobalCreditLimit property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setDocGlobalCreditLimit(BigDecimal value) {
                this.docGlobalCreditLimit = value;
            }

            /**
             * Gets the value of the docGlobalPILimit property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getDocGlobalPILimit() {
                return docGlobalPILimit;
            }

            /**
             * Sets the value of the docGlobalPILimit property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setDocGlobalPILimit(BigDecimal value) {
                this.docGlobalPILimit = value;
            }

            /**
             * Gets the value of the rfqAttachAllow property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isRfqAttachAllow() {
                return rfqAttachAllow;
            }

            /**
             * Sets the value of the rfqAttachAllow property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setRfqAttachAllow(Boolean value) {
                this.rfqAttachAllow = value;
            }

            /**
             * Gets the value of the discountQualifier property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDiscountQualifier() {
                return discountQualifier;
            }

            /**
             * Sets the value of the discountQualifier property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDiscountQualifier(String value) {
                this.discountQualifier = value;
            }

            /**
             * Gets the value of the skipped property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isSkipped() {
                return skipped;
            }

            /**
             * Sets the value of the skipped property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setSkipped(Boolean value) {
                this.skipped = value;
            }

            /**
             * Gets the value of the number11 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber11() {
                return number11;
            }

            /**
             * Sets the value of the number11 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber11(BigDecimal value) {
                this.number11 = value;
            }

            /**
             * Gets the value of the number12 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber12() {
                return number12;
            }

            /**
             * Sets the value of the number12 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber12(BigDecimal value) {
                this.number12 = value;
            }

            /**
             * Gets the value of the number13 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber13() {
                return number13;
            }

            /**
             * Sets the value of the number13 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber13(BigDecimal value) {
                this.number13 = value;
            }

            /**
             * Gets the value of the number14 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber14() {
                return number14;
            }

            /**
             * Sets the value of the number14 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber14(BigDecimal value) {
                this.number14 = value;
            }

            /**
             * Gets the value of the number15 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber15() {
                return number15;
            }

            /**
             * Sets the value of the number15 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber15(BigDecimal value) {
                this.number15 = value;
            }

            /**
             * Gets the value of the number16 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber16() {
                return number16;
            }

            /**
             * Sets the value of the number16 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber16(BigDecimal value) {
                this.number16 = value;
            }

            /**
             * Gets the value of the number17 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber17() {
                return number17;
            }

            /**
             * Sets the value of the number17 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber17(BigDecimal value) {
                this.number17 = value;
            }

            /**
             * Gets the value of the number18 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber18() {
                return number18;
            }

            /**
             * Sets the value of the number18 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber18(BigDecimal value) {
                this.number18 = value;
            }

            /**
             * Gets the value of the number19 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber19() {
                return number19;
            }

            /**
             * Sets the value of the number19 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber19(BigDecimal value) {
                this.number19 = value;
            }

            /**
             * Gets the value of the number20 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber20() {
                return number20;
            }

            /**
             * Sets the value of the number20 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber20(BigDecimal value) {
                this.number20 = value;
            }

            /**
             * Gets the value of the date06 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate06() {
                return date06;
            }

            /**
             * Sets the value of the date06 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate06(XMLGregorianCalendar value) {
                this.date06 = value;
            }

            /**
             * Gets the value of the date07 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate07() {
                return date07;
            }

            /**
             * Sets the value of the date07 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate07(XMLGregorianCalendar value) {
                this.date07 = value;
            }

            /**
             * Gets the value of the date08 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate08() {
                return date08;
            }

            /**
             * Sets the value of the date08 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate08(XMLGregorianCalendar value) {
                this.date08 = value;
            }

            /**
             * Gets the value of the date09 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate09() {
                return date09;
            }

            /**
             * Sets the value of the date09 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate09(XMLGregorianCalendar value) {
                this.date09 = value;
            }

            /**
             * Gets the value of the date10 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate10() {
                return date10;
            }

            /**
             * Sets the value of the date10 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate10(XMLGregorianCalendar value) {
                this.date10 = value;
            }

            /**
             * Gets the value of the date11 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate11() {
                return date11;
            }

            /**
             * Sets the value of the date11 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate11(XMLGregorianCalendar value) {
                this.date11 = value;
            }

            /**
             * Gets the value of the date12 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate12() {
                return date12;
            }

            /**
             * Sets the value of the date12 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate12(XMLGregorianCalendar value) {
                this.date12 = value;
            }

            /**
             * Gets the value of the date13 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate13() {
                return date13;
            }

            /**
             * Sets the value of the date13 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate13(XMLGregorianCalendar value) {
                this.date13 = value;
            }

            /**
             * Gets the value of the date14 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate14() {
                return date14;
            }

            /**
             * Sets the value of the date14 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate14(XMLGregorianCalendar value) {
                this.date14 = value;
            }

            /**
             * Gets the value of the date15 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate15() {
                return date15;
            }

            /**
             * Sets the value of the date15 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate15(XMLGregorianCalendar value) {
                this.date15 = value;
            }

            /**
             * Gets the value of the date16 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate16() {
                return date16;
            }

            /**
             * Sets the value of the date16 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate16(XMLGregorianCalendar value) {
                this.date16 = value;
            }

            /**
             * Gets the value of the date17 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate17() {
                return date17;
            }

            /**
             * Sets the value of the date17 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate17(XMLGregorianCalendar value) {
                this.date17 = value;
            }

            /**
             * Gets the value of the date18 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate18() {
                return date18;
            }

            /**
             * Sets the value of the date18 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate18(XMLGregorianCalendar value) {
                this.date18 = value;
            }

            /**
             * Gets the value of the date19 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate19() {
                return date19;
            }

            /**
             * Sets the value of the date19 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate19(XMLGregorianCalendar value) {
                this.date19 = value;
            }

            /**
             * Gets the value of the date20 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate20() {
                return date20;
            }

            /**
             * Sets the value of the date20 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate20(XMLGregorianCalendar value) {
                this.date20 = value;
            }

            /**
             * Gets the value of the checkBox06 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox06() {
                return checkBox06;
            }

            /**
             * Sets the value of the checkBox06 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox06(Boolean value) {
                this.checkBox06 = value;
            }

            /**
             * Gets the value of the checkBox07 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox07() {
                return checkBox07;
            }

            /**
             * Sets the value of the checkBox07 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox07(Boolean value) {
                this.checkBox07 = value;
            }

            /**
             * Gets the value of the checkBox08 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox08() {
                return checkBox08;
            }

            /**
             * Sets the value of the checkBox08 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox08(Boolean value) {
                this.checkBox08 = value;
            }

            /**
             * Gets the value of the checkBox09 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox09() {
                return checkBox09;
            }

            /**
             * Sets the value of the checkBox09 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox09(Boolean value) {
                this.checkBox09 = value;
            }

            /**
             * Gets the value of the checkBox10 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox10() {
                return checkBox10;
            }

            /**
             * Sets the value of the checkBox10 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox10(Boolean value) {
                this.checkBox10 = value;
            }

            /**
             * Gets the value of the checkBox11 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox11() {
                return checkBox11;
            }

            /**
             * Sets the value of the checkBox11 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox11(Boolean value) {
                this.checkBox11 = value;
            }

            /**
             * Gets the value of the checkBox12 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox12() {
                return checkBox12;
            }

            /**
             * Sets the value of the checkBox12 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox12(Boolean value) {
                this.checkBox12 = value;
            }

            /**
             * Gets the value of the checkBox13 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox13() {
                return checkBox13;
            }

            /**
             * Sets the value of the checkBox13 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox13(Boolean value) {
                this.checkBox13 = value;
            }

            /**
             * Gets the value of the checkBox14 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox14() {
                return checkBox14;
            }

            /**
             * Sets the value of the checkBox14 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox14(Boolean value) {
                this.checkBox14 = value;
            }

            /**
             * Gets the value of the checkBox15 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox15() {
                return checkBox15;
            }

            /**
             * Sets the value of the checkBox15 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox15(Boolean value) {
                this.checkBox15 = value;
            }

            /**
             * Gets the value of the checkBox16 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox16() {
                return checkBox16;
            }

            /**
             * Sets the value of the checkBox16 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox16(Boolean value) {
                this.checkBox16 = value;
            }

            /**
             * Gets the value of the checkBox17 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox17() {
                return checkBox17;
            }

            /**
             * Sets the value of the checkBox17 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox17(Boolean value) {
                this.checkBox17 = value;
            }

            /**
             * Gets the value of the checkBox18 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox18() {
                return checkBox18;
            }

            /**
             * Sets the value of the checkBox18 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox18(Boolean value) {
                this.checkBox18 = value;
            }

            /**
             * Gets the value of the checkBox19 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox19() {
                return checkBox19;
            }

            /**
             * Sets the value of the checkBox19 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox19(Boolean value) {
                this.checkBox19 = value;
            }

            /**
             * Gets the value of the checkBox20 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox20() {
                return checkBox20;
            }

            /**
             * Sets the value of the checkBox20 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox20(Boolean value) {
                this.checkBox20 = value;
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
             * Gets the value of the shortChar06 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar06() {
                return shortChar06;
            }

            /**
             * Sets the value of the shortChar06 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar06(String value) {
                this.shortChar06 = value;
            }

            /**
             * Gets the value of the shortChar07 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar07() {
                return shortChar07;
            }

            /**
             * Sets the value of the shortChar07 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar07(String value) {
                this.shortChar07 = value;
            }

            /**
             * Gets the value of the shortChar08 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar08() {
                return shortChar08;
            }

            /**
             * Sets the value of the shortChar08 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar08(String value) {
                this.shortChar08 = value;
            }

            /**
             * Gets the value of the shortChar09 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar09() {
                return shortChar09;
            }

            /**
             * Sets the value of the shortChar09 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar09(String value) {
                this.shortChar09 = value;
            }

            /**
             * Gets the value of the shortChar10 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar10() {
                return shortChar10;
            }

            /**
             * Sets the value of the shortChar10 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar10(String value) {
                this.shortChar10 = value;
            }

            /**
             * Gets the value of the allowAltBillTo property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isAllowAltBillTo() {
                return allowAltBillTo;
            }

            /**
             * Sets the value of the allowAltBillTo property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setAllowAltBillTo(Boolean value) {
                this.allowAltBillTo = value;
            }

            /**
             * Gets the value of the applyChrg property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isApplyChrg() {
                return applyChrg;
            }

            /**
             * Sets the value of the applyChrg property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setApplyChrg(Boolean value) {
                this.applyChrg = value;
            }

            /**
             * Gets the value of the chrgAmount property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getChrgAmount() {
                return chrgAmount;
            }

            /**
             * Sets the value of the chrgAmount property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setChrgAmount(BigDecimal value) {
                this.chrgAmount = value;
            }

            /**
             * Gets the value of the cod property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCOD() {
                return cod;
            }

            /**
             * Sets the value of the cod property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCOD(Boolean value) {
                this.cod = value;
            }

            /**
             * Gets the value of the codAmount property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getCODAmount() {
                return codAmount;
            }

            /**
             * Sets the value of the codAmount property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setCODAmount(BigDecimal value) {
                this.codAmount = value;
            }

            /**
             * Gets the value of the codCheck property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCODCheck() {
                return codCheck;
            }

            /**
             * Sets the value of the codCheck property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCODCheck(Boolean value) {
                this.codCheck = value;
            }

            /**
             * Gets the value of the codFreight property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCODFreight() {
                return codFreight;
            }

            /**
             * Sets the value of the codFreight property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCODFreight(Boolean value) {
                this.codFreight = value;
            }

            /**
             * Gets the value of the declaredAmt property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getDeclaredAmt() {
                return declaredAmt;
            }

            /**
             * Sets the value of the declaredAmt property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setDeclaredAmt(BigDecimal value) {
                this.declaredAmt = value;
            }

            /**
             * Gets the value of the declaredIns property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDeclaredIns() {
                return declaredIns;
            }

            /**
             * Sets the value of the declaredIns property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDeclaredIns(Boolean value) {
                this.declaredIns = value;
            }

            /**
             * Gets the value of the demandAddAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandAddAction() {
                return demandAddAction;
            }

            /**
             * Sets the value of the demandAddAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandAddAction(String value) {
                this.demandAddAction = value;
            }

            /**
             * Gets the value of the demandAddLeadTime property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandAddLeadTime() {
                return demandAddLeadTime;
            }

            /**
             * Sets the value of the demandAddLeadTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandAddLeadTime(Integer value) {
                this.demandAddLeadTime = value;
            }

            /**
             * Gets the value of the demandAutoAccept property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandAutoAccept() {
                return demandAutoAccept;
            }

            /**
             * Sets the value of the demandAutoAccept property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandAutoAccept(Boolean value) {
                this.demandAutoAccept = value;
            }

            /**
             * Gets the value of the demandCancelAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCancelAction() {
                return demandCancelAction;
            }

            /**
             * Sets the value of the demandCancelAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCancelAction(String value) {
                this.demandCancelAction = value;
            }

            /**
             * Gets the value of the demandCancelLeadTime property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandCancelLeadTime() {
                return demandCancelLeadTime;
            }

            /**
             * Sets the value of the demandCancelLeadTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandCancelLeadTime(Integer value) {
                this.demandCancelLeadTime = value;
            }

            /**
             * Gets the value of the demandChangeAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandChangeAction() {
                return demandChangeAction;
            }

            /**
             * Sets the value of the demandChangeAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandChangeAction(String value) {
                this.demandChangeAction = value;
            }

            /**
             * Gets the value of the demandChangeDateAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandChangeDateAction() {
                return demandChangeDateAction;
            }

            /**
             * Sets the value of the demandChangeDateAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandChangeDateAction(String value) {
                this.demandChangeDateAction = value;
            }

            /**
             * Gets the value of the demandChangeDateLeadTime property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandChangeDateLeadTime() {
                return demandChangeDateLeadTime;
            }

            /**
             * Sets the value of the demandChangeDateLeadTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandChangeDateLeadTime(Integer value) {
                this.demandChangeDateLeadTime = value;
            }

            /**
             * Gets the value of the demandChangeLeadTime property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandChangeLeadTime() {
                return demandChangeLeadTime;
            }

            /**
             * Sets the value of the demandChangeLeadTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandChangeLeadTime(Integer value) {
                this.demandChangeLeadTime = value;
            }

            /**
             * Gets the value of the demandDateType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandDateType() {
                return demandDateType;
            }

            /**
             * Sets the value of the demandDateType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandDateType(String value) {
                this.demandDateType = value;
            }

            /**
             * Gets the value of the demandDayOfWeek property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandDayOfWeek() {
                return demandDayOfWeek;
            }

            /**
             * Sets the value of the demandDayOfWeek property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandDayOfWeek(Integer value) {
                this.demandDayOfWeek = value;
            }

            /**
             * Gets the value of the demandDeliveryDays property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandDeliveryDays() {
                return demandDeliveryDays;
            }

            /**
             * Sets the value of the demandDeliveryDays property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandDeliveryDays(Integer value) {
                this.demandDeliveryDays = value;
            }

            /**
             * Gets the value of the demandNewLineAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandNewLineAction() {
                return demandNewLineAction;
            }

            /**
             * Sets the value of the demandNewLineAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandNewLineAction(String value) {
                this.demandNewLineAction = value;
            }

            /**
             * Gets the value of the demandNewLineLeadTime property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandNewLineLeadTime() {
                return demandNewLineLeadTime;
            }

            /**
             * Sets the value of the demandNewLineLeadTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandNewLineLeadTime(Integer value) {
                this.demandNewLineLeadTime = value;
            }

            /**
             * Gets the value of the demandQtyChangeAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandQtyChangeAction() {
                return demandQtyChangeAction;
            }

            /**
             * Sets the value of the demandQtyChangeAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandQtyChangeAction(String value) {
                this.demandQtyChangeAction = value;
            }

            /**
             * Gets the value of the demandQtyChangeLeadTime property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandQtyChangeLeadTime() {
                return demandQtyChangeLeadTime;
            }

            /**
             * Sets the value of the demandQtyChangeLeadTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandQtyChangeLeadTime(Integer value) {
                this.demandQtyChangeLeadTime = value;
            }

            /**
             * Gets the value of the demandUseSysDate property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandUseSysDate() {
                return demandUseSysDate;
            }

            /**
             * Sets the value of the demandUseSysDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandUseSysDate(Boolean value) {
                this.demandUseSysDate = value;
            }

            /**
             * Gets the value of the docOnly property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDocOnly() {
                return docOnly;
            }

            /**
             * Sets the value of the docOnly property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDocOnly(Boolean value) {
                this.docOnly = value;
            }

            /**
             * Gets the value of the groundType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGroundType() {
                return groundType;
            }

            /**
             * Sets the value of the groundType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGroundType(String value) {
                this.groundType = value;
            }

            /**
             * Gets the value of the hazmat property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isHazmat() {
                return hazmat;
            }

            /**
             * Sets the value of the hazmat property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setHazmat(Boolean value) {
                this.hazmat = value;
            }

            /**
             * Gets the value of the notifyEMail property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNotifyEMail() {
                return notifyEMail;
            }

            /**
             * Sets the value of the notifyEMail property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNotifyEMail(String value) {
                this.notifyEMail = value;
            }

            /**
             * Gets the value of the notifyFlag property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isNotifyFlag() {
                return notifyFlag;
            }

            /**
             * Sets the value of the notifyFlag property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setNotifyFlag(Boolean value) {
                this.notifyFlag = value;
            }

            /**
             * Gets the value of the refNotes property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRefNotes() {
                return refNotes;
            }

            /**
             * Sets the value of the refNotes property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRefNotes(String value) {
                this.refNotes = value;
            }

            /**
             * Gets the value of the resDelivery property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isResDelivery() {
                return resDelivery;
            }

            /**
             * Sets the value of the resDelivery property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setResDelivery(Boolean value) {
                this.resDelivery = value;
            }

            /**
             * Gets the value of the satDelivery property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isSatDelivery() {
                return satDelivery;
            }

            /**
             * Sets the value of the satDelivery property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setSatDelivery(Boolean value) {
                this.satDelivery = value;
            }

            /**
             * Gets the value of the satPickup property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isSatPickup() {
                return satPickup;
            }

            /**
             * Sets the value of the satPickup property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setSatPickup(Boolean value) {
                this.satPickup = value;
            }

            /**
             * Gets the value of the tradingPartnerName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTradingPartnerName() {
                return tradingPartnerName;
            }

            /**
             * Sets the value of the tradingPartnerName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTradingPartnerName(String value) {
                this.tradingPartnerName = value;
            }

            /**
             * Gets the value of the verbalConf property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isVerbalConf() {
                return verbalConf;
            }

            /**
             * Sets the value of the verbalConf property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setVerbalConf(Boolean value) {
                this.verbalConf = value;
            }

            /**
             * Gets the value of the periodicityCode property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getPeriodicityCode() {
                return periodicityCode;
            }

            /**
             * Sets the value of the periodicityCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setPeriodicityCode(Integer value) {
                this.periodicityCode = value;
            }

            /**
             * Gets the value of the deliveryType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDeliveryType() {
                return deliveryType;
            }

            /**
             * Sets the value of the deliveryType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDeliveryType(String value) {
                this.deliveryType = value;
            }

            /**
             * Gets the value of the servAlert property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServAlert() {
                return servAlert;
            }

            /**
             * Sets the value of the servAlert property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServAlert(Boolean value) {
                this.servAlert = value;
            }

            /**
             * Gets the value of the servAOD property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServAOD() {
                return servAOD;
            }

            /**
             * Sets the value of the servAOD property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServAOD(Boolean value) {
                this.servAOD = value;
            }

            /**
             * Gets the value of the servAuthNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServAuthNum() {
                return servAuthNum;
            }

            /**
             * Sets the value of the servAuthNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServAuthNum(String value) {
                this.servAuthNum = value;
            }

            /**
             * Gets the value of the servDeliveryDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getServDeliveryDate() {
                return servDeliveryDate;
            }

            /**
             * Sets the value of the servDeliveryDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setServDeliveryDate(XMLGregorianCalendar value) {
                this.servDeliveryDate = value;
            }

            /**
             * Gets the value of the servHomeDel property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServHomeDel() {
                return servHomeDel;
            }

            /**
             * Sets the value of the servHomeDel property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServHomeDel(Boolean value) {
                this.servHomeDel = value;
            }

            /**
             * Gets the value of the servInstruct property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServInstruct() {
                return servInstruct;
            }

            /**
             * Sets the value of the servInstruct property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServInstruct(String value) {
                this.servInstruct = value;
            }

            /**
             * Gets the value of the servPhone property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServPhone() {
                return servPhone;
            }

            /**
             * Sets the value of the servPhone property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServPhone(String value) {
                this.servPhone = value;
            }

            /**
             * Gets the value of the servPOD property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServPOD() {
                return servPOD;
            }

            /**
             * Sets the value of the servPOD property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServPOD(Boolean value) {
                this.servPOD = value;
            }

            /**
             * Gets the value of the servRef1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServRef1() {
                return servRef1;
            }

            /**
             * Sets the value of the servRef1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServRef1(String value) {
                this.servRef1 = value;
            }

            /**
             * Gets the value of the servRef2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServRef2() {
                return servRef2;
            }

            /**
             * Sets the value of the servRef2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServRef2(String value) {
                this.servRef2 = value;
            }

            /**
             * Gets the value of the servRef3 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServRef3() {
                return servRef3;
            }

            /**
             * Sets the value of the servRef3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServRef3(String value) {
                this.servRef3 = value;
            }

            /**
             * Gets the value of the servRef4 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServRef4() {
                return servRef4;
            }

            /**
             * Sets the value of the servRef4 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServRef4(String value) {
                this.servRef4 = value;
            }

            /**
             * Gets the value of the servRef5 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServRef5() {
                return servRef5;
            }

            /**
             * Sets the value of the servRef5 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServRef5(String value) {
                this.servRef5 = value;
            }

            /**
             * Gets the value of the servRelease property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServRelease() {
                return servRelease;
            }

            /**
             * Sets the value of the servRelease property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServRelease(Boolean value) {
                this.servRelease = value;
            }

            /**
             * Gets the value of the servSatDelivery property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServSatDelivery() {
                return servSatDelivery;
            }

            /**
             * Sets the value of the servSatDelivery property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServSatDelivery(Boolean value) {
                this.servSatDelivery = value;
            }

            /**
             * Gets the value of the servSatPickup property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServSatPickup() {
                return servSatPickup;
            }

            /**
             * Sets the value of the servSatPickup property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServSatPickup(Boolean value) {
                this.servSatPickup = value;
            }

            /**
             * Gets the value of the servSignature property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServSignature() {
                return servSignature;
            }

            /**
             * Sets the value of the servSignature property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServSignature(Boolean value) {
                this.servSignature = value;
            }

            /**
             * Gets the value of the creditDays property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getCreditDays() {
                return creditDays;
            }

            /**
             * Sets the value of the creditDays property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setCreditDays(Integer value) {
                this.creditDays = value;
            }

            /**
             * Gets the value of the earlyBuffer property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getEarlyBuffer() {
                return earlyBuffer;
            }

            /**
             * Sets the value of the earlyBuffer property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setEarlyBuffer(Integer value) {
                this.earlyBuffer = value;
            }

            /**
             * Gets the value of the lateBuffer property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getLateBuffer() {
                return lateBuffer;
            }

            /**
             * Sets the value of the lateBuffer property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setLateBuffer(Integer value) {
                this.lateBuffer = value;
            }

            /**
             * Gets the value of the overrideCarrier property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOverrideCarrier() {
                return overrideCarrier;
            }

            /**
             * Sets the value of the overrideCarrier property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOverrideCarrier(Boolean value) {
                this.overrideCarrier = value;
            }

            /**
             * Gets the value of the overrideService property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOverrideService() {
                return overrideService;
            }

            /**
             * Sets the value of the overrideService property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOverrideService(Boolean value) {
                this.overrideService = value;
            }

            /**
             * Gets the value of the demandUnitPriceDiff property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandUnitPriceDiff() {
                return demandUnitPriceDiff;
            }

            /**
             * Sets the value of the demandUnitPriceDiff property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandUnitPriceDiff(Boolean value) {
                this.demandUnitPriceDiff = value;
            }

            /**
             * Gets the value of the demandUnitPriceDiffAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandUnitPriceDiffAction() {
                return demandUnitPriceDiffAction;
            }

            /**
             * Sets the value of the demandUnitPriceDiffAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandUnitPriceDiffAction(String value) {
                this.demandUnitPriceDiffAction = value;
            }

            /**
             * Gets the value of the addressVal property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isAddressVal() {
                return addressVal;
            }

            /**
             * Sets the value of the addressVal property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setAddressVal(Boolean value) {
                this.addressVal = value;
            }

            /**
             * Gets the value of the excFromVal property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isExcFromVal() {
                return excFromVal;
            }

            /**
             * Sets the value of the excFromVal property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setExcFromVal(Boolean value) {
                this.excFromVal = value;
            }

            /**
             * Gets the value of the rebateForm property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRebateForm() {
                return rebateForm;
            }

            /**
             * Sets the value of the rebateForm property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRebateForm(String value) {
                this.rebateForm = value;
            }

            /**
             * Gets the value of the rebateVendorNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getRebateVendorNum() {
                return rebateVendorNum;
            }

            /**
             * Sets the value of the rebateVendorNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setRebateVendorNum(Integer value) {
                this.rebateVendorNum = value;
            }

            /**
             * Gets the value of the creditCardOrder property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCreditCardOrder() {
                return creditCardOrder;
            }

            /**
             * Sets the value of the creditCardOrder property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCreditCardOrder(Boolean value) {
                this.creditCardOrder = value;
            }

            /**
             * Gets the value of the demandCheckForPart property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandCheckForPart() {
                return demandCheckForPart;
            }

            /**
             * Sets the value of the demandCheckForPart property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandCheckForPart(Boolean value) {
                this.demandCheckForPart = value;
            }

            /**
             * Gets the value of the demandCheckForPartAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCheckForPartAction() {
                return demandCheckForPartAction;
            }

            /**
             * Sets the value of the demandCheckForPartAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCheckForPartAction(String value) {
                this.demandCheckForPartAction = value;
            }

            /**
             * Gets the value of the changeDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getChangeDate() {
                return changeDate;
            }

            /**
             * Sets the value of the changeDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setChangeDate(XMLGregorianCalendar value) {
                this.changeDate = value;
            }

            /**
             * Gets the value of the changedBy property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getChangedBy() {
                return changedBy;
            }

            /**
             * Sets the value of the changedBy property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setChangedBy(String value) {
                this.changedBy = value;
            }

            /**
             * Gets the value of the changeTime property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getChangeTime() {
                return changeTime;
            }

            /**
             * Sets the value of the changeTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setChangeTime(Integer value) {
                this.changeTime = value;
            }

            /**
             * Gets the value of the chargeCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getChargeCode() {
                return chargeCode;
            }

            /**
             * Sets the value of the chargeCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setChargeCode(String value) {
                this.chargeCode = value;
            }

            /**
             * Gets the value of the addlHdlgFlag property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isAddlHdlgFlag() {
                return addlHdlgFlag;
            }

            /**
             * Sets the value of the addlHdlgFlag property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setAddlHdlgFlag(Boolean value) {
                this.addlHdlgFlag = value;
            }

            /**
             * Gets the value of the certOfOrigin property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCertOfOrigin() {
                return certOfOrigin;
            }

            /**
             * Sets the value of the certOfOrigin property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCertOfOrigin(Boolean value) {
                this.certOfOrigin = value;
            }

            /**
             * Gets the value of the commercialInvoice property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCommercialInvoice() {
                return commercialInvoice;
            }

            /**
             * Sets the value of the commercialInvoice property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCommercialInvoice(Boolean value) {
                this.commercialInvoice = value;
            }

            /**
             * Gets the value of the deliveryConf property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDeliveryConf() {
                return deliveryConf;
            }

            /**
             * Sets the value of the deliveryConf property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDeliveryConf(Integer value) {
                this.deliveryConf = value;
            }

            /**
             * Gets the value of the etcAddrChg property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isETCAddrChg() {
                return etcAddrChg;
            }

            /**
             * Sets the value of the etcAddrChg property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setETCAddrChg(Boolean value) {
                this.etcAddrChg = value;
            }

            /**
             * Gets the value of the ffAddress1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFAddress1() {
                return ffAddress1;
            }

            /**
             * Sets the value of the ffAddress1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFAddress1(String value) {
                this.ffAddress1 = value;
            }

            /**
             * Gets the value of the ffAddress2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFAddress2() {
                return ffAddress2;
            }

            /**
             * Sets the value of the ffAddress2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFAddress2(String value) {
                this.ffAddress2 = value;
            }

            /**
             * Gets the value of the ffAddress3 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFAddress3() {
                return ffAddress3;
            }

            /**
             * Sets the value of the ffAddress3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFAddress3(String value) {
                this.ffAddress3 = value;
            }

            /**
             * Gets the value of the ffCity property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFCity() {
                return ffCity;
            }

            /**
             * Sets the value of the ffCity property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFCity(String value) {
                this.ffCity = value;
            }

            /**
             * Gets the value of the ffCompName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFCompName() {
                return ffCompName;
            }

            /**
             * Sets the value of the ffCompName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFCompName(String value) {
                this.ffCompName = value;
            }

            /**
             * Gets the value of the ffContact property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFContact() {
                return ffContact;
            }

            /**
             * Sets the value of the ffContact property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFContact(String value) {
                this.ffContact = value;
            }

            /**
             * Gets the value of the ffCountry property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFCountry() {
                return ffCountry;
            }

            /**
             * Sets the value of the ffCountry property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFCountry(String value) {
                this.ffCountry = value;
            }

            /**
             * Gets the value of the ffCountryNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getFFCountryNum() {
                return ffCountryNum;
            }

            /**
             * Sets the value of the ffCountryNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setFFCountryNum(Integer value) {
                this.ffCountryNum = value;
            }

            /**
             * Gets the value of the ffid property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFID() {
                return ffid;
            }

            /**
             * Sets the value of the ffid property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFID(String value) {
                this.ffid = value;
            }

            /**
             * Gets the value of the ffPhoneNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFPhoneNum() {
                return ffPhoneNum;
            }

            /**
             * Sets the value of the ffPhoneNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFPhoneNum(String value) {
                this.ffPhoneNum = value;
            }

            /**
             * Gets the value of the ffState property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFState() {
                return ffState;
            }

            /**
             * Sets the value of the ffState property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFState(String value) {
                this.ffState = value;
            }

            /**
             * Gets the value of the ffZip property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFZip() {
                return ffZip;
            }

            /**
             * Sets the value of the ffZip property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFZip(String value) {
                this.ffZip = value;
            }

            /**
             * Gets the value of the individualPackIDs property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isIndividualPackIDs() {
                return individualPackIDs;
            }

            /**
             * Sets the value of the individualPackIDs property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setIndividualPackIDs(Boolean value) {
                this.individualPackIDs = value;
            }

            /**
             * Gets the value of the intrntlShip property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isIntrntlShip() {
                return intrntlShip;
            }

            /**
             * Sets the value of the intrntlShip property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setIntrntlShip(Boolean value) {
                this.intrntlShip = value;
            }

            /**
             * Gets the value of the letterOfInstr property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isLetterOfInstr() {
                return letterOfInstr;
            }

            /**
             * Sets the value of the letterOfInstr property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLetterOfInstr(Boolean value) {
                this.letterOfInstr = value;
            }

            /**
             * Gets the value of the nonStdPkg property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isNonStdPkg() {
                return nonStdPkg;
            }

            /**
             * Sets the value of the nonStdPkg property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setNonStdPkg(Boolean value) {
                this.nonStdPkg = value;
            }

            /**
             * Gets the value of the shipExprtDeclartn property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isShipExprtDeclartn() {
                return shipExprtDeclartn;
            }

            /**
             * Sets the value of the shipExprtDeclartn property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setShipExprtDeclartn(Boolean value) {
                this.shipExprtDeclartn = value;
            }

            /**
             * Gets the value of the upsQuantumView property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isUPSQuantumView() {
                return upsQuantumView;
            }

            /**
             * Sets the value of the upsQuantumView property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setUPSQuantumView(Boolean value) {
                this.upsQuantumView = value;
            }

            /**
             * Gets the value of the upsqvEmailType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUPSQVEmailType() {
                return upsqvEmailType;
            }

            /**
             * Sets the value of the upsqvEmailType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUPSQVEmailType(String value) {
                this.upsqvEmailType = value;
            }

            /**
             * Gets the value of the upsqvMemo property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUPSQVMemo() {
                return upsqvMemo;
            }

            /**
             * Sets the value of the upsqvMemo property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUPSQVMemo(String value) {
                this.upsqvMemo = value;
            }

            /**
             * Gets the value of the upsqvShipFromName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUPSQVShipFromName() {
                return upsqvShipFromName;
            }

            /**
             * Sets the value of the upsqvShipFromName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUPSQVShipFromName(String value) {
                this.upsqvShipFromName = value;
            }

            /**
             * Gets the value of the arCreditTotal property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getARCreditTotal() {
                return arCreditTotal;
            }

            /**
             * Sets the value of the arCreditTotal property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setARCreditTotal(BigDecimal value) {
                this.arCreditTotal = value;
            }

            /**
             * Gets the value of the piCreditTotal property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPICreditTotal() {
                return piCreditTotal;
            }

            /**
             * Sets the value of the piCreditTotal property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPICreditTotal(BigDecimal value) {
                this.piCreditTotal = value;
            }

            /**
             * Gets the value of the soCreditTotal property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getSOCreditTotal() {
                return soCreditTotal;
            }

            /**
             * Sets the value of the soCreditTotal property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setSOCreditTotal(BigDecimal value) {
                this.soCreditTotal = value;
            }

            /**
             * Gets the value of the taxMethod property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTaxMethod() {
                return taxMethod;
            }

            /**
             * Sets the value of the taxMethod property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTaxMethod(String value) {
                this.taxMethod = value;
            }

            /**
             * Gets the value of the taxRoundRule property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getTaxRoundRule() {
                return taxRoundRule;
            }

            /**
             * Sets the value of the taxRoundRule property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setTaxRoundRule(Integer value) {
                this.taxRoundRule = value;
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
             * Gets the value of the acrossNatAcc property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isAcrossNatAcc() {
                return acrossNatAcc;
            }

            /**
             * Sets the value of the acrossNatAcc property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setAcrossNatAcc(Boolean value) {
                this.acrossNatAcc = value;
            }

            /**
             * Gets the value of the naCreditIsShare property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isNACreditIsShare() {
                return naCreditIsShare;
            }

            /**
             * Sets the value of the naCreditIsShare property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setNACreditIsShare(Boolean value) {
                this.naCreditIsShare = value;
            }

            /**
             * Gets the value of the naCreditPreferenceList property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNACreditPreferenceList() {
                return naCreditPreferenceList;
            }

            /**
             * Sets the value of the naCreditPreferenceList property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNACreditPreferenceList(String value) {
                this.naCreditPreferenceList = value;
            }

            /**
             * Gets the value of the naCreditSharedPrc property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNACreditSharedPrc() {
                return naCreditSharedPrc;
            }

            /**
             * Sets the value of the naCreditSharedPrc property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNACreditSharedPrc(BigDecimal value) {
                this.naCreditSharedPrc = value;
            }

            /**
             * Gets the value of the naParentCreditIsUsed property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isNAParentCreditIsUsed() {
                return naParentCreditIsUsed;
            }

            /**
             * Sets the value of the naParentCreditIsUsed property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setNAParentCreditIsUsed(Boolean value) {
                this.naParentCreditIsUsed = value;
            }

            /**
             * Gets the value of the naParentCreditPrc property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNAParentCreditPrc() {
                return naParentCreditPrc;
            }

            /**
             * Sets the value of the naParentCreditPrc property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNAParentCreditPrc(BigDecimal value) {
                this.naParentCreditPrc = value;
            }

            /**
             * Gets the value of the overrideRlsClass property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOverrideRlsClass() {
                return overrideRlsClass;
            }

            /**
             * Sets the value of the overrideRlsClass property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOverrideRlsClass(Boolean value) {
                this.overrideRlsClass = value;
            }

            /**
             * Gets the value of the validPayer property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isValidPayer() {
                return validPayer;
            }

            /**
             * Sets the value of the validPayer property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setValidPayer(Boolean value) {
                this.validPayer = value;
            }

            /**
             * Gets the value of the validShipTo property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isValidShipTo() {
                return validShipTo;
            }

            /**
             * Sets the value of the validShipTo property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setValidShipTo(Boolean value) {
                this.validShipTo = value;
            }

            /**
             * Gets the value of the validSoldTo property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isValidSoldTo() {
                return validSoldTo;
            }

            /**
             * Sets the value of the validSoldTo property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setValidSoldTo(Boolean value) {
                this.validSoldTo = value;
            }

            /**
             * Gets the value of the allowOTS property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isAllowOTS() {
                return allowOTS;
            }

            /**
             * Sets the value of the allowOTS property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setAllowOTS(Boolean value) {
                this.allowOTS = value;
            }

            /**
             * Gets the value of the managedVendID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getManagedVendID() {
                return managedVendID;
            }

            /**
             * Sets the value of the managedVendID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setManagedVendID(String value) {
                this.managedVendID = value;
            }

            /**
             * Gets the value of the managedVendNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getManagedVendNum() {
                return managedVendNum;
            }

            /**
             * Sets the value of the managedVendNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setManagedVendNum(Integer value) {
                this.managedVendNum = value;
            }

            /**
             * Gets the value of the thirdPLCust property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isThirdPLCust() {
                return thirdPLCust;
            }

            /**
             * Sets the value of the thirdPLCust property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setThirdPLCust(Boolean value) {
                this.thirdPLCust = value;
            }

            /**
             * Gets the value of the naRlsClassCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNARlsClassCode() {
                return naRlsClassCode;
            }

            /**
             * Sets the value of the naRlsClassCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNARlsClassCode(String value) {
                this.naRlsClassCode = value;
            }

            /**
             * Gets the value of the autoGenPromNotes property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isAutoGenPromNotes() {
                return autoGenPromNotes;
            }

            /**
             * Sets the value of the autoGenPromNotes property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setAutoGenPromNotes(Boolean value) {
                this.autoGenPromNotes = value;
            }

            /**
             * Gets the value of the directDebiting property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDirectDebiting() {
                return directDebiting;
            }

            /**
             * Sets the value of the directDebiting property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDirectDebiting(Boolean value) {
                this.directDebiting = value;
            }

            /**
             * Gets the value of the partPayment property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPartPayment() {
                return partPayment;
            }

            /**
             * Sets the value of the partPayment property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPartPayment(Boolean value) {
                this.partPayment = value;
            }

            /**
             * Gets the value of the reservePriorityCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReservePriorityCode() {
                return reservePriorityCode;
            }

            /**
             * Sets the value of the reservePriorityCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReservePriorityCode(String value) {
                this.reservePriorityCode = value;
            }

            /**
             * Gets the value of the naParentsCreditUsed property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNAParentsCreditUsed() {
                return naParentsCreditUsed;
            }

            /**
             * Sets the value of the naParentsCreditUsed property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNAParentsCreditUsed(BigDecimal value) {
                this.naParentsCreditUsed = value;
            }

            /**
             * Gets the value of the naSharedCreditUsed property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNASharedCreditUsed() {
                return naSharedCreditUsed;
            }

            /**
             * Sets the value of the naSharedCreditUsed property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNASharedCreditUsed(BigDecimal value) {
                this.naSharedCreditUsed = value;
            }

            /**
             * Gets the value of the naOwnCreditUsed property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNAOwnCreditUsed() {
                return naOwnCreditUsed;
            }

            /**
             * Sets the value of the naOwnCreditUsed property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNAOwnCreditUsed(BigDecimal value) {
                this.naOwnCreditUsed = value;
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
             * Gets the value of the glbNACreditIsShare property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isGlbNACreditIsShare() {
                return glbNACreditIsShare;
            }

            /**
             * Sets the value of the glbNACreditIsShare property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setGlbNACreditIsShare(Boolean value) {
                this.glbNACreditIsShare = value;
            }

            /**
             * Gets the value of the glbNACreditSharedPrc property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getGlbNACreditSharedPrc() {
                return glbNACreditSharedPrc;
            }

            /**
             * Sets the value of the glbNACreditSharedPrc property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setGlbNACreditSharedPrc(BigDecimal value) {
                this.glbNACreditSharedPrc = value;
            }

            /**
             * Gets the value of the glbNAParentCreditIsUsed property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isGlbNAParentCreditIsUsed() {
                return glbNAParentCreditIsUsed;
            }

            /**
             * Sets the value of the glbNAParentCreditIsUsed property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setGlbNAParentCreditIsUsed(Boolean value) {
                this.glbNAParentCreditIsUsed = value;
            }

            /**
             * Gets the value of the glbNAParentCreditPrc property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getGlbNAParentCreditPrc() {
                return glbNAParentCreditPrc;
            }

            /**
             * Sets the value of the glbNAParentCreditPrc property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setGlbNAParentCreditPrc(BigDecimal value) {
                this.glbNAParentCreditPrc = value;
            }

            /**
             * Gets the value of the glbNAParentsCreditUsed property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getGlbNAParentsCreditUsed() {
                return glbNAParentsCreditUsed;
            }

            /**
             * Sets the value of the glbNAParentsCreditUsed property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setGlbNAParentsCreditUsed(BigDecimal value) {
                this.glbNAParentsCreditUsed = value;
            }

            /**
             * Gets the value of the glbNASharedCreditUsed property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getGlbNASharedCreditUsed() {
                return glbNASharedCreditUsed;
            }

            /**
             * Sets the value of the glbNASharedCreditUsed property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setGlbNASharedCreditUsed(BigDecimal value) {
                this.glbNASharedCreditUsed = value;
            }

            /**
             * Gets the value of the reminderCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReminderCode() {
                return reminderCode;
            }

            /**
             * Sets the value of the reminderCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReminderCode(String value) {
                this.reminderCode = value;
            }

            /**
             * Gets the value of the allowShipTo3 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isAllowShipTo3() {
                return allowShipTo3;
            }

            /**
             * Sets the value of the allowShipTo3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setAllowShipTo3(Boolean value) {
                this.allowShipTo3 = value;
            }

            /**
             * Gets the value of the naCreditUpdate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getNACreditUpdate() {
                return naCreditUpdate;
            }

            /**
             * Sets the value of the naCreditUpdate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setNACreditUpdate(XMLGregorianCalendar value) {
                this.naCreditUpdate = value;
            }

            /**
             * Gets the value of the otsSaveAs property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSSaveAs() {
                return otsSaveAs;
            }

            /**
             * Sets the value of the otsSaveAs property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSSaveAs(String value) {
                this.otsSaveAs = value;
            }

            /**
             * Gets the value of the custPartOpts property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCustPartOpts() {
                return custPartOpts;
            }

            /**
             * Sets the value of the custPartOpts property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCustPartOpts(String value) {
                this.custPartOpts = value;
            }

            /**
             * Gets the value of the hasBank property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isHasBank() {
                return hasBank;
            }

            /**
             * Sets the value of the hasBank property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setHasBank(Boolean value) {
                this.hasBank = value;
            }

            /**
             * Gets the value of the pmuid property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getPMUID() {
                return pmuid;
            }

            /**
             * Sets the value of the pmuid property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setPMUID(Integer value) {
                this.pmuid = value;
            }

            /**
             * Gets the value of the demandCheckForRev property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandCheckForRev() {
                return demandCheckForRev;
            }

            /**
             * Sets the value of the demandCheckForRev property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandCheckForRev(Boolean value) {
                this.demandCheckForRev = value;
            }

            /**
             * Gets the value of the demandCheckForRevAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCheckForRevAction() {
                return demandCheckForRevAction;
            }

            /**
             * Sets the value of the demandCheckForRevAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCheckForRevAction(String value) {
                this.demandCheckForRevAction = value;
            }

            /**
             * Gets the value of the orderHoldForReview property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOrderHoldForReview() {
                return orderHoldForReview;
            }

            /**
             * Sets the value of the orderHoldForReview property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOrderHoldForReview(Boolean value) {
                this.orderHoldForReview = value;
            }

            /**
             * Gets the value of the shipToTerrList property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShipToTerrList() {
                return shipToTerrList;
            }

            /**
             * Sets the value of the shipToTerrList property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShipToTerrList(String value) {
                this.shipToTerrList = value;
            }

            /**
             * Gets the value of the acctRefNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAcctRefNumber() {
                return acctRefNumber;
            }

            /**
             * Sets the value of the acctRefNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAcctRefNumber(String value) {
                this.acctRefNumber = value;
            }

            /**
             * Gets the value of the arInvoiceAdjITCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getARInvoiceAdjITCode() {
                return arInvoiceAdjITCode;
            }

            /**
             * Sets the value of the arInvoiceAdjITCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setARInvoiceAdjITCode(String value) {
                this.arInvoiceAdjITCode = value;
            }

            /**
             * Gets the value of the arInvoiceITCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getARInvoiceITCode() {
                return arInvoiceITCode;
            }

            /**
             * Sets the value of the arInvoiceITCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setARInvoiceITCode(String value) {
                this.arInvoiceITCode = value;
            }

            /**
             * Gets the value of the creditMemoITCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCreditMemoITCode() {
                return creditMemoITCode;
            }

            /**
             * Sets the value of the creditMemoITCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCreditMemoITCode(String value) {
                this.creditMemoITCode = value;
            }

            /**
             * Gets the value of the miscCashRecITCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMiscCashRecITCode() {
                return miscCashRecITCode;
            }

            /**
             * Sets the value of the miscCashRecITCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMiscCashRecITCode(String value) {
                this.miscCashRecITCode = value;
            }

            /**
             * Gets the value of the demandCheckCUMM property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandCheckCUMM() {
                return demandCheckCUMM;
            }

            /**
             * Sets the value of the demandCheckCUMM property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandCheckCUMM(Boolean value) {
                this.demandCheckCUMM = value;
            }

            /**
             * Gets the value of the demandCheckCUMMAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCheckCUMMAction() {
                return demandCheckCUMMAction;
            }

            /**
             * Sets the value of the demandCheckCUMMAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCheckCUMMAction(String value) {
                this.demandCheckCUMMAction = value;
            }

            /**
             * Gets the value of the demandCloseNoMatch property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandCloseNoMatch() {
                return demandCloseNoMatch;
            }

            /**
             * Sets the value of the demandCloseNoMatch property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandCloseNoMatch(Boolean value) {
                this.demandCloseNoMatch = value;
            }

            /**
             * Gets the value of the demandCloseRejSkd property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandCloseRejSkd() {
                return demandCloseRejSkd;
            }

            /**
             * Sets the value of the demandCloseRejSkd property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandCloseRejSkd(Boolean value) {
                this.demandCloseRejSkd = value;
            }

            /**
             * Gets the value of the demandPricing property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandPricing() {
                return demandPricing;
            }

            /**
             * Sets the value of the demandPricing property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandPricing(String value) {
                this.demandPricing = value;
            }

            /**
             * Gets the value of the dmdCheckPartialShip property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDmdCheckPartialShip() {
                return dmdCheckPartialShip;
            }

            /**
             * Sets the value of the dmdCheckPartialShip property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDmdCheckPartialShip(Boolean value) {
                this.dmdCheckPartialShip = value;
            }

            /**
             * Gets the value of the dmdCheckShipAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDmdCheckShipAction() {
                return dmdCheckShipAction;
            }

            /**
             * Sets the value of the dmdCheckShipAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDmdCheckShipAction(String value) {
                this.dmdCheckShipAction = value;
            }

            /**
             * Gets the value of the invPerPackLine property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isInvPerPackLine() {
                return invPerPackLine;
            }

            /**
             * Sets the value of the invPerPackLine property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setInvPerPackLine(Boolean value) {
                this.invPerPackLine = value;
            }

            /**
             * Gets the value of the legalName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLegalName() {
                return legalName;
            }

            /**
             * Sets the value of the legalName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLegalName(String value) {
                this.legalName = value;
            }

            /**
             * Gets the value of the orgRegCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOrgRegCode() {
                return orgRegCode;
            }

            /**
             * Sets the value of the orgRegCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOrgRegCode(String value) {
                this.orgRegCode = value;
            }

            /**
             * Gets the value of the ourBankCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOurBankCode() {
                return ourBankCode;
            }

            /**
             * Sets the value of the ourBankCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOurBankCode(String value) {
                this.ourBankCode = value;
            }

            /**
             * Gets the value of the priceTolerance property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPriceTolerance() {
                return priceTolerance;
            }

            /**
             * Sets the value of the priceTolerance property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPriceTolerance(BigDecimal value) {
                this.priceTolerance = value;
            }

            /**
             * Gets the value of the taxRegReason property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTaxRegReason() {
                return taxRegReason;
            }

            /**
             * Sets the value of the taxRegReason property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTaxRegReason(String value) {
                this.taxRegReason = value;
            }

            /**
             * Gets the value of the checkConfirmCapPromise property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckConfirmCapPromise() {
                return checkConfirmCapPromise;
            }

            /**
             * Sets the value of the checkConfirmCapPromise property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckConfirmCapPromise(Boolean value) {
                this.checkConfirmCapPromise = value;
            }

            /**
             * Gets the value of the checkDateCapPromise property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckDateCapPromise() {
                return checkDateCapPromise;
            }

            /**
             * Sets the value of the checkDateCapPromise property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckDateCapPromise(Boolean value) {
                this.checkDateCapPromise = value;
            }

            /**
             * Gets the value of the checkUpdateCapPromise property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckUpdateCapPromise() {
                return checkUpdateCapPromise;
            }

            /**
             * Sets the value of the checkUpdateCapPromise property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckUpdateCapPromise(Boolean value) {
                this.checkUpdateCapPromise = value;
            }

            /**
             * Gets the value of the demandCapPromiseAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCapPromiseAction() {
                return demandCapPromiseAction;
            }

            /**
             * Sets the value of the demandCapPromiseAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCapPromiseAction(String value) {
                this.demandCapPromiseAction = value;
            }

            /**
             * Gets the value of the demandCapPromiseDate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCapPromiseDate() {
                return demandCapPromiseDate;
            }

            /**
             * Sets the value of the demandCapPromiseDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCapPromiseDate(String value) {
                this.demandCapPromiseDate = value;
            }

            /**
             * Gets the value of the demandCapPromiseUpdate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCapPromiseUpdate() {
                return demandCapPromiseUpdate;
            }

            /**
             * Sets the value of the demandCapPromiseUpdate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCapPromiseUpdate(String value) {
                this.demandCapPromiseUpdate = value;
            }

            /**
             * Gets the value of the demandSplitSched property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandSplitSched() {
                return demandSplitSched;
            }

            /**
             * Sets the value of the demandSplitSched property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandSplitSched(Boolean value) {
                this.demandSplitSched = value;
            }

            /**
             * Gets the value of the dueDateCriteria property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDueDateCriteria() {
                return dueDateCriteria;
            }

            /**
             * Sets the value of the dueDateCriteria property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDueDateCriteria(String value) {
                this.dueDateCriteria = value;
            }

            /**
             * Gets the value of the ersOrder property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isERSOrder() {
                return ersOrder;
            }

            /**
             * Sets the value of the ersOrder property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setERSOrder(Boolean value) {
                this.ersOrder = value;
            }

            /**
             * Gets the value of the pbTerms property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getPBTerms() {
                return pbTerms;
            }

            /**
             * Sets the value of the pbTerms property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setPBTerms(Integer value) {
                this.pbTerms = value;
            }

            /**
             * Gets the value of the periodicBilling property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPeriodicBilling() {
                return periodicBilling;
            }

            /**
             * Sets the value of the periodicBilling property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPeriodicBilling(Boolean value) {
                this.periodicBilling = value;
            }

            /**
             * Gets the value of the preferredBank property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPreferredBank() {
                return preferredBank;
            }

            /**
             * Sets the value of the preferredBank property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPreferredBank(String value) {
                this.preferredBank = value;
            }

            /**
             * Gets the value of the sicCode property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getSICCode() {
                return sicCode;
            }

            /**
             * Sets the value of the sicCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setSICCode(Integer value) {
                this.sicCode = value;
            }

            /**
             * Gets the value of the icCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getICCode() {
                return icCode;
            }

            /**
             * Sets the value of the icCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setICCode(String value) {
                this.icCode = value;
            }

            /**
             * Gets the value of the otSmartString property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOTSmartString() {
                return otSmartString;
            }

            /**
             * Sets the value of the otSmartString property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOTSmartString(Boolean value) {
                this.otSmartString = value;
            }

            /**
             * Gets the value of the deferredRev property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDeferredRev() {
                return deferredRev;
            }

            /**
             * Sets the value of the deferredRev property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDeferredRev(Boolean value) {
                this.deferredRev = value;
            }

            /**
             * Gets the value of the raCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRACode() {
                return raCode;
            }

            /**
             * Sets the value of the raCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRACode(String value) {
                this.raCode = value;
            }

            /**
             * Gets the value of the demandCheckCfgAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCheckCfgAction() {
                return demandCheckCfgAction;
            }

            /**
             * Sets the value of the demandCheckCfgAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCheckCfgAction(String value) {
                this.demandCheckCfgAction = value;
            }

            /**
             * Gets the value of the demandCheckConfig property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandCheckConfig() {
                return demandCheckConfig;
            }

            /**
             * Sets the value of the demandCheckConfig property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandCheckConfig(Boolean value) {
                this.demandCheckConfig = value;
            }

            /**
             * Gets the value of the linkCustID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLinkCustID() {
                return linkCustID;
            }

            /**
             * Sets the value of the linkCustID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLinkCustID(String value) {
                this.linkCustID = value;
            }

            /**
             * Gets the value of the displayHold property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDisplayHold() {
                return displayHold;
            }

            /**
             * Sets the value of the displayHold property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDisplayHold(String value) {
                this.displayHold = value;
            }

            /**
             * Gets the value of the displayCustomerType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDisplayCustomerType() {
                return displayCustomerType;
            }

            /**
             * Sets the value of the displayCustomerType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDisplayCustomerType(String value) {
                this.displayCustomerType = value;
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
         *         &lt;element name="CustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="ShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Address3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ResaleID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SalesRepCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TerritoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShipViaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PrimSCon" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="FaxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TaxExempt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="EDIShipNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="LangNameID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BorderCrossing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FormatStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TaxRegionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="EMailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TerritorySelect" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PendingTerritoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CreatedByEDI" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ExternalID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TaxAuthorityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="GlbCustNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="GlbCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="EDICode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="GlbShipToNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="GlobalLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="OldCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OldCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="OldShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Skipped" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="Character01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Character10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Number01" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number02" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number03" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number04" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number05" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number06" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number07" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number08" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number09" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number10" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number11" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number12" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number13" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number14" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number15" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number16" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number17" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number18" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number19" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number20" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Date01" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date02" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date03" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date04" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date05" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date06" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date07" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date08" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date09" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date10" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date11" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date12" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date13" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date14" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date15" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date16" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date17" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date18" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date19" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date20" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="CheckBox01" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox02" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox03" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox04" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox05" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox06" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox07" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox08" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox09" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox10" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox11" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox12" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox13" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox14" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox15" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox16" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox17" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox18" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox19" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox20" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ShortChar01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ApplyChrg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ChrgAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="COD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CODAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="CODCheck" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CODFreight" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DeclaredAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="DeclaredIns" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandAddAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandAddLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandAutoAccept" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandCancelAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCancelLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandChangeAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandChangeDateAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandChangeDateLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandChangeLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandDateType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandDayOfWeek" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandDeliveryDays" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandNewLineAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandNewLineLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandQtyChangeAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandQtyChangeLeadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DemandUseSysDate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DocOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="GroundType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Hazmat" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="NotifyEMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NotifyFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="RefNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ResDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="SatDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="SatPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="TradingPartnerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VerbalConf" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PeriodicityCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="DeliveryType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServAlert" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ServAOD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ServAuthNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServDeliveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="ServHomeDel" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ServInstruct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServPOD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ServRef1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServRef2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServRef3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServRef4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServRef5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ServRelease" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ServSatDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ServSatPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ServSignature" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandUseCustomerValues" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="EarlyBuffer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="LateBuffer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="OverrideCarrier" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="OverrideService" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandUnitPriceDiff" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandUnitPriceDiffAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AddressVal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ExcFromVal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CertOfOrigin" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CommercialInvoice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ConAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ConAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ConCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ConCompName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ConContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ConCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ConPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ConsigneeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ConState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ConZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCheckForPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandCheckForPartAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFCompName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="HazardousShipment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="IntrntlShip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LetterOfInstr" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ShipExprtDeclartn" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ChangeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="ChangedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ChangeTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="AddlHdlgFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DeliveryConf" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="ETCAddrChg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="FFAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FFCountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="IndividualPackIDs" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="NonStdPkg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="UPSQuantumView" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="UPSQVEmailType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UPSQVMemo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UPSQVShipFromName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="IsAlternate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="MasterCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="MasterShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCheckForRev" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandCheckForRevAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCheckPartialShip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandCheckShipAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCloseRejSkd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandPricing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OrgRegCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PriceTolerance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="TaxRegReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CheckConfirmCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckDateCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckUpdateCapPromise" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DemandCapPromiseAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCapPromiseDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCapPromiseUpdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SICCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="ICCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSmartString" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LegalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCheckCfgAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DemandCheckConfig" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LinkShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "custNum",
            "shipToNum",
            "name",
            "address1",
            "address2",
            "address3",
            "city",
            "state",
            "zip",
            "country",
            "resaleID",
            "salesRepCode",
            "territoryID",
            "shipViaCode",
            "primSCon",
            "faxNum",
            "phoneNum",
            "taxExempt",
            "ediShipNum",
            "countryNum",
            "langNameID",
            "borderCrossing",
            "formatStr",
            "taxRegionCode",
            "eMailAddress",
            "territorySelect",
            "pendingTerritoryID",
            "createdByEDI",
            "externalID",
            "taxAuthorityCode",
            "glbCustNum",
            "glbCompany",
            "ediCode",
            "glbShipToNum",
            "globalLock",
            "oldCompany",
            "oldCustNum",
            "oldShipToNum",
            "skipped",
            "character01",
            "character02",
            "character03",
            "character04",
            "character05",
            "character06",
            "character07",
            "character08",
            "character09",
            "character10",
            "number01",
            "number02",
            "number03",
            "number04",
            "number05",
            "number06",
            "number07",
            "number08",
            "number09",
            "number10",
            "number11",
            "number12",
            "number13",
            "number14",
            "number15",
            "number16",
            "number17",
            "number18",
            "number19",
            "number20",
            "date01",
            "date02",
            "date03",
            "date04",
            "date05",
            "date06",
            "date07",
            "date08",
            "date09",
            "date10",
            "date11",
            "date12",
            "date13",
            "date14",
            "date15",
            "date16",
            "date17",
            "date18",
            "date19",
            "date20",
            "checkBox01",
            "checkBox02",
            "checkBox03",
            "checkBox04",
            "checkBox05",
            "checkBox06",
            "checkBox07",
            "checkBox08",
            "checkBox09",
            "checkBox10",
            "checkBox11",
            "checkBox12",
            "checkBox13",
            "checkBox14",
            "checkBox15",
            "checkBox16",
            "checkBox17",
            "checkBox18",
            "checkBox19",
            "checkBox20",
            "shortChar01",
            "shortChar02",
            "shortChar03",
            "shortChar04",
            "shortChar05",
            "shortChar06",
            "shortChar07",
            "shortChar08",
            "shortChar09",
            "shortChar10",
            "applyChrg",
            "chrgAmount",
            "cod",
            "codAmount",
            "codCheck",
            "codFreight",
            "declaredAmt",
            "declaredIns",
            "demandAddAction",
            "demandAddLeadTime",
            "demandAutoAccept",
            "demandCancelAction",
            "demandCancelLeadTime",
            "demandChangeAction",
            "demandChangeDateAction",
            "demandChangeDateLeadTime",
            "demandChangeLeadTime",
            "demandDateType",
            "demandDayOfWeek",
            "demandDeliveryDays",
            "demandNewLineAction",
            "demandNewLineLeadTime",
            "demandQtyChangeAction",
            "demandQtyChangeLeadTime",
            "demandUseSysDate",
            "docOnly",
            "groundType",
            "hazmat",
            "notifyEMail",
            "notifyFlag",
            "refNotes",
            "resDelivery",
            "satDelivery",
            "satPickup",
            "tradingPartnerName",
            "verbalConf",
            "periodicityCode",
            "deliveryType",
            "servAlert",
            "servAOD",
            "servAuthNum",
            "servDeliveryDate",
            "servHomeDel",
            "servInstruct",
            "servPhone",
            "servPOD",
            "servRef1",
            "servRef2",
            "servRef3",
            "servRef4",
            "servRef5",
            "servRelease",
            "servSatDelivery",
            "servSatPickup",
            "servSignature",
            "demandUseCustomerValues",
            "earlyBuffer",
            "lateBuffer",
            "overrideCarrier",
            "overrideService",
            "demandUnitPriceDiff",
            "demandUnitPriceDiffAction",
            "addressVal",
            "excFromVal",
            "certOfOrigin",
            "commercialInvoice",
            "conAddress1",
            "conAddress2",
            "conCity",
            "conCompName",
            "conContact",
            "conCountry",
            "conPhoneNum",
            "consigneeID",
            "conState",
            "conZip",
            "demandCheckForPart",
            "demandCheckForPartAction",
            "ffAddress1",
            "ffAddress2",
            "ffCity",
            "ffCompName",
            "ffContact",
            "ffCountry",
            "ffid",
            "ffPhoneNum",
            "ffState",
            "ffZip",
            "hazardousShipment",
            "intrntlShip",
            "letterOfInstr",
            "shipExprtDeclartn",
            "changeDate",
            "changedBy",
            "changeTime",
            "addlHdlgFlag",
            "deliveryConf",
            "etcAddrChg",
            "ffAddress3",
            "ffCountryNum",
            "individualPackIDs",
            "nonStdPkg",
            "upsQuantumView",
            "upsqvEmailType",
            "upsqvMemo",
            "upsqvShipFromName",
            "sysRowID",
            "sysRevID",
            "bitFlag",
            "isAlternate",
            "masterCustNum",
            "masterShipToNum",
            "demandCheckForRev",
            "demandCheckForRevAction",
            "demandCheckPartialShip",
            "demandCheckShipAction",
            "demandCloseRejSkd",
            "demandPricing",
            "orgRegCode",
            "priceTolerance",
            "taxRegReason",
            "checkConfirmCapPromise",
            "checkDateCapPromise",
            "checkUpdateCapPromise",
            "demandCapPromiseAction",
            "demandCapPromiseDate",
            "demandCapPromiseUpdate",
            "sicCode",
            "icCode",
            "otSmartString",
            "legalName",
            "demandCheckCfgAction",
            "demandCheckConfig",
            "linkShipToNum",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class GlbShipTo {

            @XmlElement(name = "Company", required = true)
            protected String company;
            @XmlElement(name = "CustNum")
            protected Integer custNum;
            @XmlElement(name = "ShipToNum")
            protected String shipToNum;
            @XmlElement(name = "Name")
            protected String name;
            @XmlElement(name = "Address1")
            protected String address1;
            @XmlElement(name = "Address2")
            protected String address2;
            @XmlElement(name = "Address3")
            protected String address3;
            @XmlElement(name = "City")
            protected String city;
            @XmlElement(name = "State")
            protected String state;
            @XmlElement(name = "ZIP")
            protected String zip;
            @XmlElement(name = "Country")
            protected String country;
            @XmlElement(name = "ResaleID")
            protected String resaleID;
            @XmlElement(name = "SalesRepCode")
            protected String salesRepCode;
            @XmlElement(name = "TerritoryID")
            protected String territoryID;
            @XmlElement(name = "ShipViaCode")
            protected String shipViaCode;
            @XmlElement(name = "PrimSCon")
            protected Integer primSCon;
            @XmlElement(name = "FaxNum")
            protected String faxNum;
            @XmlElement(name = "PhoneNum")
            protected String phoneNum;
            @XmlElement(name = "TaxExempt")
            protected String taxExempt;
            @XmlElement(name = "EDIShipNum")
            protected String ediShipNum;
            @XmlElement(name = "CountryNum")
            protected Integer countryNum;
            @XmlElement(name = "LangNameID")
            protected String langNameID;
            @XmlElement(name = "BorderCrossing")
            protected String borderCrossing;
            @XmlElement(name = "FormatStr")
            protected String formatStr;
            @XmlElement(name = "TaxRegionCode")
            protected String taxRegionCode;
            @XmlElement(name = "EMailAddress")
            protected String eMailAddress;
            @XmlElement(name = "TerritorySelect")
            protected String territorySelect;
            @XmlElement(name = "PendingTerritoryID")
            protected String pendingTerritoryID;
            @XmlElement(name = "CreatedByEDI")
            protected Boolean createdByEDI;
            @XmlElement(name = "ExternalID")
            protected String externalID;
            @XmlElement(name = "TaxAuthorityCode")
            protected String taxAuthorityCode;
            @XmlElement(name = "GlbCustNum")
            protected int glbCustNum;
            @XmlElement(name = "GlbCompany", required = true)
            protected String glbCompany;
            @XmlElement(name = "EDICode")
            protected String ediCode;
            @XmlElement(name = "GlbShipToNum", required = true)
            protected String glbShipToNum;
            @XmlElement(name = "GlobalLock")
            protected Boolean globalLock;
            @XmlElement(name = "OldCompany")
            protected String oldCompany;
            @XmlElement(name = "OldCustNum")
            protected Integer oldCustNum;
            @XmlElement(name = "OldShipToNum")
            protected String oldShipToNum;
            @XmlElement(name = "Skipped")
            protected Boolean skipped;
            @XmlElement(name = "Character01")
            protected String character01;
            @XmlElement(name = "Character02")
            protected String character02;
            @XmlElement(name = "Character03")
            protected String character03;
            @XmlElement(name = "Character04")
            protected String character04;
            @XmlElement(name = "Character05")
            protected String character05;
            @XmlElement(name = "Character06")
            protected String character06;
            @XmlElement(name = "Character07")
            protected String character07;
            @XmlElement(name = "Character08")
            protected String character08;
            @XmlElement(name = "Character09")
            protected String character09;
            @XmlElement(name = "Character10")
            protected String character10;
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
            @XmlElement(name = "Number06")
            protected BigDecimal number06;
            @XmlElement(name = "Number07")
            protected BigDecimal number07;
            @XmlElement(name = "Number08")
            protected BigDecimal number08;
            @XmlElement(name = "Number09")
            protected BigDecimal number09;
            @XmlElement(name = "Number10")
            protected BigDecimal number10;
            @XmlElement(name = "Number11")
            protected BigDecimal number11;
            @XmlElement(name = "Number12")
            protected BigDecimal number12;
            @XmlElement(name = "Number13")
            protected BigDecimal number13;
            @XmlElement(name = "Number14")
            protected BigDecimal number14;
            @XmlElement(name = "Number15")
            protected BigDecimal number15;
            @XmlElement(name = "Number16")
            protected BigDecimal number16;
            @XmlElement(name = "Number17")
            protected BigDecimal number17;
            @XmlElement(name = "Number18")
            protected BigDecimal number18;
            @XmlElement(name = "Number19")
            protected BigDecimal number19;
            @XmlElement(name = "Number20")
            protected BigDecimal number20;
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
            @XmlElement(name = "Date06")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date06;
            @XmlElement(name = "Date07")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date07;
            @XmlElement(name = "Date08")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date08;
            @XmlElement(name = "Date09")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date09;
            @XmlElement(name = "Date10")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date10;
            @XmlElement(name = "Date11")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date11;
            @XmlElement(name = "Date12")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date12;
            @XmlElement(name = "Date13")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date13;
            @XmlElement(name = "Date14")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date14;
            @XmlElement(name = "Date15")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date15;
            @XmlElement(name = "Date16")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date16;
            @XmlElement(name = "Date17")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date17;
            @XmlElement(name = "Date18")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date18;
            @XmlElement(name = "Date19")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date19;
            @XmlElement(name = "Date20")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar date20;
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
            @XmlElement(name = "CheckBox06")
            protected Boolean checkBox06;
            @XmlElement(name = "CheckBox07")
            protected Boolean checkBox07;
            @XmlElement(name = "CheckBox08")
            protected Boolean checkBox08;
            @XmlElement(name = "CheckBox09")
            protected Boolean checkBox09;
            @XmlElement(name = "CheckBox10")
            protected Boolean checkBox10;
            @XmlElement(name = "CheckBox11")
            protected Boolean checkBox11;
            @XmlElement(name = "CheckBox12")
            protected Boolean checkBox12;
            @XmlElement(name = "CheckBox13")
            protected Boolean checkBox13;
            @XmlElement(name = "CheckBox14")
            protected Boolean checkBox14;
            @XmlElement(name = "CheckBox15")
            protected Boolean checkBox15;
            @XmlElement(name = "CheckBox16")
            protected Boolean checkBox16;
            @XmlElement(name = "CheckBox17")
            protected Boolean checkBox17;
            @XmlElement(name = "CheckBox18")
            protected Boolean checkBox18;
            @XmlElement(name = "CheckBox19")
            protected Boolean checkBox19;
            @XmlElement(name = "CheckBox20")
            protected Boolean checkBox20;
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
            @XmlElement(name = "ShortChar06")
            protected String shortChar06;
            @XmlElement(name = "ShortChar07")
            protected String shortChar07;
            @XmlElement(name = "ShortChar08")
            protected String shortChar08;
            @XmlElement(name = "ShortChar09")
            protected String shortChar09;
            @XmlElement(name = "ShortChar10")
            protected String shortChar10;
            @XmlElement(name = "ApplyChrg")
            protected Boolean applyChrg;
            @XmlElement(name = "ChrgAmount")
            protected BigDecimal chrgAmount;
            @XmlElement(name = "COD")
            protected Boolean cod;
            @XmlElement(name = "CODAmount")
            protected BigDecimal codAmount;
            @XmlElement(name = "CODCheck")
            protected Boolean codCheck;
            @XmlElement(name = "CODFreight")
            protected Boolean codFreight;
            @XmlElement(name = "DeclaredAmt")
            protected BigDecimal declaredAmt;
            @XmlElement(name = "DeclaredIns")
            protected Boolean declaredIns;
            @XmlElement(name = "DemandAddAction")
            protected String demandAddAction;
            @XmlElement(name = "DemandAddLeadTime")
            protected Integer demandAddLeadTime;
            @XmlElement(name = "DemandAutoAccept")
            protected Boolean demandAutoAccept;
            @XmlElement(name = "DemandCancelAction")
            protected String demandCancelAction;
            @XmlElement(name = "DemandCancelLeadTime")
            protected Integer demandCancelLeadTime;
            @XmlElement(name = "DemandChangeAction")
            protected String demandChangeAction;
            @XmlElement(name = "DemandChangeDateAction")
            protected String demandChangeDateAction;
            @XmlElement(name = "DemandChangeDateLeadTime")
            protected Integer demandChangeDateLeadTime;
            @XmlElement(name = "DemandChangeLeadTime")
            protected Integer demandChangeLeadTime;
            @XmlElement(name = "DemandDateType")
            protected String demandDateType;
            @XmlElement(name = "DemandDayOfWeek")
            protected Integer demandDayOfWeek;
            @XmlElement(name = "DemandDeliveryDays")
            protected Integer demandDeliveryDays;
            @XmlElement(name = "DemandNewLineAction")
            protected String demandNewLineAction;
            @XmlElement(name = "DemandNewLineLeadTime")
            protected Integer demandNewLineLeadTime;
            @XmlElement(name = "DemandQtyChangeAction")
            protected String demandQtyChangeAction;
            @XmlElement(name = "DemandQtyChangeLeadTime")
            protected Integer demandQtyChangeLeadTime;
            @XmlElement(name = "DemandUseSysDate")
            protected Boolean demandUseSysDate;
            @XmlElement(name = "DocOnly")
            protected Boolean docOnly;
            @XmlElement(name = "GroundType")
            protected String groundType;
            @XmlElement(name = "Hazmat")
            protected Boolean hazmat;
            @XmlElement(name = "NotifyEMail")
            protected String notifyEMail;
            @XmlElement(name = "NotifyFlag")
            protected Boolean notifyFlag;
            @XmlElement(name = "RefNotes")
            protected String refNotes;
            @XmlElement(name = "ResDelivery")
            protected Boolean resDelivery;
            @XmlElement(name = "SatDelivery")
            protected Boolean satDelivery;
            @XmlElement(name = "SatPickup")
            protected Boolean satPickup;
            @XmlElement(name = "TradingPartnerName")
            protected String tradingPartnerName;
            @XmlElement(name = "VerbalConf")
            protected Boolean verbalConf;
            @XmlElement(name = "PeriodicityCode")
            protected Integer periodicityCode;
            @XmlElement(name = "DeliveryType")
            protected String deliveryType;
            @XmlElement(name = "ServAlert")
            protected Boolean servAlert;
            @XmlElement(name = "ServAOD")
            protected Boolean servAOD;
            @XmlElement(name = "ServAuthNum")
            protected String servAuthNum;
            @XmlElement(name = "ServDeliveryDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar servDeliveryDate;
            @XmlElement(name = "ServHomeDel")
            protected Boolean servHomeDel;
            @XmlElement(name = "ServInstruct")
            protected String servInstruct;
            @XmlElement(name = "ServPhone")
            protected String servPhone;
            @XmlElement(name = "ServPOD")
            protected Boolean servPOD;
            @XmlElement(name = "ServRef1")
            protected String servRef1;
            @XmlElement(name = "ServRef2")
            protected String servRef2;
            @XmlElement(name = "ServRef3")
            protected String servRef3;
            @XmlElement(name = "ServRef4")
            protected String servRef4;
            @XmlElement(name = "ServRef5")
            protected String servRef5;
            @XmlElement(name = "ServRelease")
            protected Boolean servRelease;
            @XmlElement(name = "ServSatDelivery")
            protected Boolean servSatDelivery;
            @XmlElement(name = "ServSatPickup")
            protected Boolean servSatPickup;
            @XmlElement(name = "ServSignature")
            protected Boolean servSignature;
            @XmlElement(name = "DemandUseCustomerValues")
            protected Boolean demandUseCustomerValues;
            @XmlElement(name = "EarlyBuffer")
            protected Integer earlyBuffer;
            @XmlElement(name = "LateBuffer")
            protected Integer lateBuffer;
            @XmlElement(name = "OverrideCarrier")
            protected Boolean overrideCarrier;
            @XmlElement(name = "OverrideService")
            protected Boolean overrideService;
            @XmlElement(name = "DemandUnitPriceDiff")
            protected Boolean demandUnitPriceDiff;
            @XmlElement(name = "DemandUnitPriceDiffAction")
            protected String demandUnitPriceDiffAction;
            @XmlElement(name = "AddressVal")
            protected Boolean addressVal;
            @XmlElement(name = "ExcFromVal")
            protected Boolean excFromVal;
            @XmlElement(name = "CertOfOrigin")
            protected Boolean certOfOrigin;
            @XmlElement(name = "CommercialInvoice")
            protected Boolean commercialInvoice;
            @XmlElement(name = "ConAddress1")
            protected String conAddress1;
            @XmlElement(name = "ConAddress2")
            protected String conAddress2;
            @XmlElement(name = "ConCity")
            protected String conCity;
            @XmlElement(name = "ConCompName")
            protected String conCompName;
            @XmlElement(name = "ConContact")
            protected String conContact;
            @XmlElement(name = "ConCountry")
            protected String conCountry;
            @XmlElement(name = "ConPhoneNum")
            protected String conPhoneNum;
            @XmlElement(name = "ConsigneeID")
            protected String consigneeID;
            @XmlElement(name = "ConState")
            protected String conState;
            @XmlElement(name = "ConZip")
            protected String conZip;
            @XmlElement(name = "DemandCheckForPart")
            protected Boolean demandCheckForPart;
            @XmlElement(name = "DemandCheckForPartAction")
            protected String demandCheckForPartAction;
            @XmlElement(name = "FFAddress1")
            protected String ffAddress1;
            @XmlElement(name = "FFAddress2")
            protected String ffAddress2;
            @XmlElement(name = "FFCity")
            protected String ffCity;
            @XmlElement(name = "FFCompName")
            protected String ffCompName;
            @XmlElement(name = "FFContact")
            protected String ffContact;
            @XmlElement(name = "FFCountry")
            protected String ffCountry;
            @XmlElement(name = "FFID")
            protected String ffid;
            @XmlElement(name = "FFPhoneNum")
            protected String ffPhoneNum;
            @XmlElement(name = "FFState")
            protected String ffState;
            @XmlElement(name = "FFZip")
            protected String ffZip;
            @XmlElement(name = "HazardousShipment")
            protected Boolean hazardousShipment;
            @XmlElement(name = "IntrntlShip")
            protected Boolean intrntlShip;
            @XmlElement(name = "LetterOfInstr")
            protected Boolean letterOfInstr;
            @XmlElement(name = "ShipExprtDeclartn")
            protected Boolean shipExprtDeclartn;
            @XmlElement(name = "ChangeDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar changeDate;
            @XmlElement(name = "ChangedBy")
            protected String changedBy;
            @XmlElement(name = "ChangeTime")
            protected Integer changeTime;
            @XmlElement(name = "AddlHdlgFlag")
            protected Boolean addlHdlgFlag;
            @XmlElement(name = "DeliveryConf")
            protected Integer deliveryConf;
            @XmlElement(name = "ETCAddrChg")
            protected Boolean etcAddrChg;
            @XmlElement(name = "FFAddress3")
            protected String ffAddress3;
            @XmlElement(name = "FFCountryNum")
            protected Integer ffCountryNum;
            @XmlElement(name = "IndividualPackIDs")
            protected Boolean individualPackIDs;
            @XmlElement(name = "NonStdPkg")
            protected Boolean nonStdPkg;
            @XmlElement(name = "UPSQuantumView")
            protected Boolean upsQuantumView;
            @XmlElement(name = "UPSQVEmailType")
            protected String upsqvEmailType;
            @XmlElement(name = "UPSQVMemo")
            protected String upsqvMemo;
            @XmlElement(name = "UPSQVShipFromName")
            protected String upsqvShipFromName;
            @XmlElement(name = "SysRowID")
            protected String sysRowID;
            @XmlElement(name = "SysRevID")
            protected Integer sysRevID;
            @XmlElement(name = "BitFlag")
            protected Integer bitFlag;
            @XmlElement(name = "IsAlternate")
            protected Boolean isAlternate;
            @XmlElement(name = "MasterCustNum")
            protected Integer masterCustNum;
            @XmlElement(name = "MasterShipToNum")
            protected String masterShipToNum;
            @XmlElement(name = "DemandCheckForRev")
            protected Boolean demandCheckForRev;
            @XmlElement(name = "DemandCheckForRevAction")
            protected String demandCheckForRevAction;
            @XmlElement(name = "DemandCheckPartialShip")
            protected Boolean demandCheckPartialShip;
            @XmlElement(name = "DemandCheckShipAction")
            protected String demandCheckShipAction;
            @XmlElement(name = "DemandCloseRejSkd")
            protected Boolean demandCloseRejSkd;
            @XmlElement(name = "DemandPricing")
            protected String demandPricing;
            @XmlElement(name = "OrgRegCode")
            protected String orgRegCode;
            @XmlElement(name = "PriceTolerance")
            protected BigDecimal priceTolerance;
            @XmlElement(name = "TaxRegReason")
            protected String taxRegReason;
            @XmlElement(name = "CheckConfirmCapPromise")
            protected Boolean checkConfirmCapPromise;
            @XmlElement(name = "CheckDateCapPromise")
            protected Boolean checkDateCapPromise;
            @XmlElement(name = "CheckUpdateCapPromise")
            protected Boolean checkUpdateCapPromise;
            @XmlElement(name = "DemandCapPromiseAction")
            protected String demandCapPromiseAction;
            @XmlElement(name = "DemandCapPromiseDate")
            protected String demandCapPromiseDate;
            @XmlElement(name = "DemandCapPromiseUpdate")
            protected String demandCapPromiseUpdate;
            @XmlElement(name = "SICCode")
            protected Integer sicCode;
            @XmlElement(name = "ICCode")
            protected String icCode;
            @XmlElement(name = "OTSmartString")
            protected Boolean otSmartString;
            @XmlElement(name = "LegalName")
            protected String legalName;
            @XmlElement(name = "DemandCheckCfgAction")
            protected String demandCheckCfgAction;
            @XmlElement(name = "DemandCheckConfig")
            protected Boolean demandCheckConfig;
            @XmlElement(name = "LinkShipToNum")
            protected String linkShipToNum;
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
             * Gets the value of the custNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getCustNum() {
                return custNum;
            }

            /**
             * Sets the value of the custNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setCustNum(Integer value) {
                this.custNum = value;
            }

            /**
             * Gets the value of the shipToNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShipToNum() {
                return shipToNum;
            }

            /**
             * Sets the value of the shipToNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShipToNum(String value) {
                this.shipToNum = value;
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
             * Gets the value of the address1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddress1() {
                return address1;
            }

            /**
             * Sets the value of the address1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddress1(String value) {
                this.address1 = value;
            }

            /**
             * Gets the value of the address2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddress2() {
                return address2;
            }

            /**
             * Sets the value of the address2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddress2(String value) {
                this.address2 = value;
            }

            /**
             * Gets the value of the address3 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddress3() {
                return address3;
            }

            /**
             * Sets the value of the address3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddress3(String value) {
                this.address3 = value;
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
             * Gets the value of the state property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getState() {
                return state;
            }

            /**
             * Sets the value of the state property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setState(String value) {
                this.state = value;
            }

            /**
             * Gets the value of the zip property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZIP() {
                return zip;
            }

            /**
             * Sets the value of the zip property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZIP(String value) {
                this.zip = value;
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
             * Gets the value of the resaleID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getResaleID() {
                return resaleID;
            }

            /**
             * Sets the value of the resaleID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setResaleID(String value) {
                this.resaleID = value;
            }

            /**
             * Gets the value of the salesRepCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSalesRepCode() {
                return salesRepCode;
            }

            /**
             * Sets the value of the salesRepCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSalesRepCode(String value) {
                this.salesRepCode = value;
            }

            /**
             * Gets the value of the territoryID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTerritoryID() {
                return territoryID;
            }

            /**
             * Sets the value of the territoryID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTerritoryID(String value) {
                this.territoryID = value;
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
             * Gets the value of the primSCon property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getPrimSCon() {
                return primSCon;
            }

            /**
             * Sets the value of the primSCon property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setPrimSCon(Integer value) {
                this.primSCon = value;
            }

            /**
             * Gets the value of the faxNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFaxNum() {
                return faxNum;
            }

            /**
             * Sets the value of the faxNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFaxNum(String value) {
                this.faxNum = value;
            }

            /**
             * Gets the value of the phoneNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPhoneNum() {
                return phoneNum;
            }

            /**
             * Sets the value of the phoneNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPhoneNum(String value) {
                this.phoneNum = value;
            }

            /**
             * Gets the value of the taxExempt property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTaxExempt() {
                return taxExempt;
            }

            /**
             * Sets the value of the taxExempt property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTaxExempt(String value) {
                this.taxExempt = value;
            }

            /**
             * Gets the value of the ediShipNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEDIShipNum() {
                return ediShipNum;
            }

            /**
             * Sets the value of the ediShipNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEDIShipNum(String value) {
                this.ediShipNum = value;
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
             * Gets the value of the langNameID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLangNameID() {
                return langNameID;
            }

            /**
             * Sets the value of the langNameID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLangNameID(String value) {
                this.langNameID = value;
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
             * Gets the value of the formatStr property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFormatStr() {
                return formatStr;
            }

            /**
             * Sets the value of the formatStr property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFormatStr(String value) {
                this.formatStr = value;
            }

            /**
             * Gets the value of the taxRegionCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTaxRegionCode() {
                return taxRegionCode;
            }

            /**
             * Sets the value of the taxRegionCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTaxRegionCode(String value) {
                this.taxRegionCode = value;
            }

            /**
             * Gets the value of the eMailAddress property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEMailAddress() {
                return eMailAddress;
            }

            /**
             * Sets the value of the eMailAddress property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEMailAddress(String value) {
                this.eMailAddress = value;
            }

            /**
             * Gets the value of the territorySelect property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTerritorySelect() {
                return territorySelect;
            }

            /**
             * Sets the value of the territorySelect property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTerritorySelect(String value) {
                this.territorySelect = value;
            }

            /**
             * Gets the value of the pendingTerritoryID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPendingTerritoryID() {
                return pendingTerritoryID;
            }

            /**
             * Sets the value of the pendingTerritoryID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPendingTerritoryID(String value) {
                this.pendingTerritoryID = value;
            }

            /**
             * Gets the value of the createdByEDI property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCreatedByEDI() {
                return createdByEDI;
            }

            /**
             * Sets the value of the createdByEDI property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCreatedByEDI(Boolean value) {
                this.createdByEDI = value;
            }

            /**
             * Gets the value of the externalID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getExternalID() {
                return externalID;
            }

            /**
             * Sets the value of the externalID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setExternalID(String value) {
                this.externalID = value;
            }

            /**
             * Gets the value of the taxAuthorityCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTaxAuthorityCode() {
                return taxAuthorityCode;
            }

            /**
             * Sets the value of the taxAuthorityCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTaxAuthorityCode(String value) {
                this.taxAuthorityCode = value;
            }

            /**
             * Gets the value of the glbCustNum property.
             * 
             */
            public int getGlbCustNum() {
                return glbCustNum;
            }

            /**
             * Sets the value of the glbCustNum property.
             * 
             */
            public void setGlbCustNum(int value) {
                this.glbCustNum = value;
            }

            /**
             * Gets the value of the glbCompany property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGlbCompany() {
                return glbCompany;
            }

            /**
             * Sets the value of the glbCompany property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGlbCompany(String value) {
                this.glbCompany = value;
            }

            /**
             * Gets the value of the ediCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEDICode() {
                return ediCode;
            }

            /**
             * Sets the value of the ediCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEDICode(String value) {
                this.ediCode = value;
            }

            /**
             * Gets the value of the glbShipToNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGlbShipToNum() {
                return glbShipToNum;
            }

            /**
             * Sets the value of the glbShipToNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGlbShipToNum(String value) {
                this.glbShipToNum = value;
            }

            /**
             * Gets the value of the globalLock property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isGlobalLock() {
                return globalLock;
            }

            /**
             * Sets the value of the globalLock property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setGlobalLock(Boolean value) {
                this.globalLock = value;
            }

            /**
             * Gets the value of the oldCompany property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOldCompany() {
                return oldCompany;
            }

            /**
             * Sets the value of the oldCompany property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOldCompany(String value) {
                this.oldCompany = value;
            }

            /**
             * Gets the value of the oldCustNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getOldCustNum() {
                return oldCustNum;
            }

            /**
             * Sets the value of the oldCustNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setOldCustNum(Integer value) {
                this.oldCustNum = value;
            }

            /**
             * Gets the value of the oldShipToNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOldShipToNum() {
                return oldShipToNum;
            }

            /**
             * Sets the value of the oldShipToNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOldShipToNum(String value) {
                this.oldShipToNum = value;
            }

            /**
             * Gets the value of the skipped property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isSkipped() {
                return skipped;
            }

            /**
             * Sets the value of the skipped property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setSkipped(Boolean value) {
                this.skipped = value;
            }

            /**
             * Gets the value of the character01 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter01() {
                return character01;
            }

            /**
             * Sets the value of the character01 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter01(String value) {
                this.character01 = value;
            }

            /**
             * Gets the value of the character02 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter02() {
                return character02;
            }

            /**
             * Sets the value of the character02 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter02(String value) {
                this.character02 = value;
            }

            /**
             * Gets the value of the character03 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter03() {
                return character03;
            }

            /**
             * Sets the value of the character03 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter03(String value) {
                this.character03 = value;
            }

            /**
             * Gets the value of the character04 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter04() {
                return character04;
            }

            /**
             * Sets the value of the character04 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter04(String value) {
                this.character04 = value;
            }

            /**
             * Gets the value of the character05 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter05() {
                return character05;
            }

            /**
             * Sets the value of the character05 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter05(String value) {
                this.character05 = value;
            }

            /**
             * Gets the value of the character06 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter06() {
                return character06;
            }

            /**
             * Sets the value of the character06 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter06(String value) {
                this.character06 = value;
            }

            /**
             * Gets the value of the character07 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter07() {
                return character07;
            }

            /**
             * Sets the value of the character07 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter07(String value) {
                this.character07 = value;
            }

            /**
             * Gets the value of the character08 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter08() {
                return character08;
            }

            /**
             * Sets the value of the character08 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter08(String value) {
                this.character08 = value;
            }

            /**
             * Gets the value of the character09 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter09() {
                return character09;
            }

            /**
             * Sets the value of the character09 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter09(String value) {
                this.character09 = value;
            }

            /**
             * Gets the value of the character10 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCharacter10() {
                return character10;
            }

            /**
             * Sets the value of the character10 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCharacter10(String value) {
                this.character10 = value;
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
             * Gets the value of the number06 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber06() {
                return number06;
            }

            /**
             * Sets the value of the number06 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber06(BigDecimal value) {
                this.number06 = value;
            }

            /**
             * Gets the value of the number07 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber07() {
                return number07;
            }

            /**
             * Sets the value of the number07 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber07(BigDecimal value) {
                this.number07 = value;
            }

            /**
             * Gets the value of the number08 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber08() {
                return number08;
            }

            /**
             * Sets the value of the number08 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber08(BigDecimal value) {
                this.number08 = value;
            }

            /**
             * Gets the value of the number09 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber09() {
                return number09;
            }

            /**
             * Sets the value of the number09 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber09(BigDecimal value) {
                this.number09 = value;
            }

            /**
             * Gets the value of the number10 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber10() {
                return number10;
            }

            /**
             * Sets the value of the number10 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber10(BigDecimal value) {
                this.number10 = value;
            }

            /**
             * Gets the value of the number11 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber11() {
                return number11;
            }

            /**
             * Sets the value of the number11 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber11(BigDecimal value) {
                this.number11 = value;
            }

            /**
             * Gets the value of the number12 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber12() {
                return number12;
            }

            /**
             * Sets the value of the number12 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber12(BigDecimal value) {
                this.number12 = value;
            }

            /**
             * Gets the value of the number13 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber13() {
                return number13;
            }

            /**
             * Sets the value of the number13 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber13(BigDecimal value) {
                this.number13 = value;
            }

            /**
             * Gets the value of the number14 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber14() {
                return number14;
            }

            /**
             * Sets the value of the number14 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber14(BigDecimal value) {
                this.number14 = value;
            }

            /**
             * Gets the value of the number15 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber15() {
                return number15;
            }

            /**
             * Sets the value of the number15 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber15(BigDecimal value) {
                this.number15 = value;
            }

            /**
             * Gets the value of the number16 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber16() {
                return number16;
            }

            /**
             * Sets the value of the number16 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber16(BigDecimal value) {
                this.number16 = value;
            }

            /**
             * Gets the value of the number17 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber17() {
                return number17;
            }

            /**
             * Sets the value of the number17 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber17(BigDecimal value) {
                this.number17 = value;
            }

            /**
             * Gets the value of the number18 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber18() {
                return number18;
            }

            /**
             * Sets the value of the number18 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber18(BigDecimal value) {
                this.number18 = value;
            }

            /**
             * Gets the value of the number19 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber19() {
                return number19;
            }

            /**
             * Sets the value of the number19 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber19(BigDecimal value) {
                this.number19 = value;
            }

            /**
             * Gets the value of the number20 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNumber20() {
                return number20;
            }

            /**
             * Sets the value of the number20 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNumber20(BigDecimal value) {
                this.number20 = value;
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
             * Gets the value of the date06 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate06() {
                return date06;
            }

            /**
             * Sets the value of the date06 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate06(XMLGregorianCalendar value) {
                this.date06 = value;
            }

            /**
             * Gets the value of the date07 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate07() {
                return date07;
            }

            /**
             * Sets the value of the date07 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate07(XMLGregorianCalendar value) {
                this.date07 = value;
            }

            /**
             * Gets the value of the date08 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate08() {
                return date08;
            }

            /**
             * Sets the value of the date08 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate08(XMLGregorianCalendar value) {
                this.date08 = value;
            }

            /**
             * Gets the value of the date09 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate09() {
                return date09;
            }

            /**
             * Sets the value of the date09 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate09(XMLGregorianCalendar value) {
                this.date09 = value;
            }

            /**
             * Gets the value of the date10 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate10() {
                return date10;
            }

            /**
             * Sets the value of the date10 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate10(XMLGregorianCalendar value) {
                this.date10 = value;
            }

            /**
             * Gets the value of the date11 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate11() {
                return date11;
            }

            /**
             * Sets the value of the date11 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate11(XMLGregorianCalendar value) {
                this.date11 = value;
            }

            /**
             * Gets the value of the date12 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate12() {
                return date12;
            }

            /**
             * Sets the value of the date12 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate12(XMLGregorianCalendar value) {
                this.date12 = value;
            }

            /**
             * Gets the value of the date13 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate13() {
                return date13;
            }

            /**
             * Sets the value of the date13 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate13(XMLGregorianCalendar value) {
                this.date13 = value;
            }

            /**
             * Gets the value of the date14 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate14() {
                return date14;
            }

            /**
             * Sets the value of the date14 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate14(XMLGregorianCalendar value) {
                this.date14 = value;
            }

            /**
             * Gets the value of the date15 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate15() {
                return date15;
            }

            /**
             * Sets the value of the date15 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate15(XMLGregorianCalendar value) {
                this.date15 = value;
            }

            /**
             * Gets the value of the date16 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate16() {
                return date16;
            }

            /**
             * Sets the value of the date16 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate16(XMLGregorianCalendar value) {
                this.date16 = value;
            }

            /**
             * Gets the value of the date17 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate17() {
                return date17;
            }

            /**
             * Sets the value of the date17 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate17(XMLGregorianCalendar value) {
                this.date17 = value;
            }

            /**
             * Gets the value of the date18 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate18() {
                return date18;
            }

            /**
             * Sets the value of the date18 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate18(XMLGregorianCalendar value) {
                this.date18 = value;
            }

            /**
             * Gets the value of the date19 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate19() {
                return date19;
            }

            /**
             * Sets the value of the date19 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate19(XMLGregorianCalendar value) {
                this.date19 = value;
            }

            /**
             * Gets the value of the date20 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate20() {
                return date20;
            }

            /**
             * Sets the value of the date20 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate20(XMLGregorianCalendar value) {
                this.date20 = value;
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
             * Gets the value of the checkBox06 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox06() {
                return checkBox06;
            }

            /**
             * Sets the value of the checkBox06 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox06(Boolean value) {
                this.checkBox06 = value;
            }

            /**
             * Gets the value of the checkBox07 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox07() {
                return checkBox07;
            }

            /**
             * Sets the value of the checkBox07 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox07(Boolean value) {
                this.checkBox07 = value;
            }

            /**
             * Gets the value of the checkBox08 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox08() {
                return checkBox08;
            }

            /**
             * Sets the value of the checkBox08 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox08(Boolean value) {
                this.checkBox08 = value;
            }

            /**
             * Gets the value of the checkBox09 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox09() {
                return checkBox09;
            }

            /**
             * Sets the value of the checkBox09 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox09(Boolean value) {
                this.checkBox09 = value;
            }

            /**
             * Gets the value of the checkBox10 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox10() {
                return checkBox10;
            }

            /**
             * Sets the value of the checkBox10 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox10(Boolean value) {
                this.checkBox10 = value;
            }

            /**
             * Gets the value of the checkBox11 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox11() {
                return checkBox11;
            }

            /**
             * Sets the value of the checkBox11 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox11(Boolean value) {
                this.checkBox11 = value;
            }

            /**
             * Gets the value of the checkBox12 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox12() {
                return checkBox12;
            }

            /**
             * Sets the value of the checkBox12 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox12(Boolean value) {
                this.checkBox12 = value;
            }

            /**
             * Gets the value of the checkBox13 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox13() {
                return checkBox13;
            }

            /**
             * Sets the value of the checkBox13 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox13(Boolean value) {
                this.checkBox13 = value;
            }

            /**
             * Gets the value of the checkBox14 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox14() {
                return checkBox14;
            }

            /**
             * Sets the value of the checkBox14 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox14(Boolean value) {
                this.checkBox14 = value;
            }

            /**
             * Gets the value of the checkBox15 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox15() {
                return checkBox15;
            }

            /**
             * Sets the value of the checkBox15 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox15(Boolean value) {
                this.checkBox15 = value;
            }

            /**
             * Gets the value of the checkBox16 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox16() {
                return checkBox16;
            }

            /**
             * Sets the value of the checkBox16 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox16(Boolean value) {
                this.checkBox16 = value;
            }

            /**
             * Gets the value of the checkBox17 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox17() {
                return checkBox17;
            }

            /**
             * Sets the value of the checkBox17 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox17(Boolean value) {
                this.checkBox17 = value;
            }

            /**
             * Gets the value of the checkBox18 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox18() {
                return checkBox18;
            }

            /**
             * Sets the value of the checkBox18 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox18(Boolean value) {
                this.checkBox18 = value;
            }

            /**
             * Gets the value of the checkBox19 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox19() {
                return checkBox19;
            }

            /**
             * Sets the value of the checkBox19 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox19(Boolean value) {
                this.checkBox19 = value;
            }

            /**
             * Gets the value of the checkBox20 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckBox20() {
                return checkBox20;
            }

            /**
             * Sets the value of the checkBox20 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckBox20(Boolean value) {
                this.checkBox20 = value;
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
             * Gets the value of the shortChar06 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar06() {
                return shortChar06;
            }

            /**
             * Sets the value of the shortChar06 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar06(String value) {
                this.shortChar06 = value;
            }

            /**
             * Gets the value of the shortChar07 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar07() {
                return shortChar07;
            }

            /**
             * Sets the value of the shortChar07 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar07(String value) {
                this.shortChar07 = value;
            }

            /**
             * Gets the value of the shortChar08 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar08() {
                return shortChar08;
            }

            /**
             * Sets the value of the shortChar08 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar08(String value) {
                this.shortChar08 = value;
            }

            /**
             * Gets the value of the shortChar09 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar09() {
                return shortChar09;
            }

            /**
             * Sets the value of the shortChar09 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar09(String value) {
                this.shortChar09 = value;
            }

            /**
             * Gets the value of the shortChar10 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortChar10() {
                return shortChar10;
            }

            /**
             * Sets the value of the shortChar10 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortChar10(String value) {
                this.shortChar10 = value;
            }

            /**
             * Gets the value of the applyChrg property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isApplyChrg() {
                return applyChrg;
            }

            /**
             * Sets the value of the applyChrg property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setApplyChrg(Boolean value) {
                this.applyChrg = value;
            }

            /**
             * Gets the value of the chrgAmount property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getChrgAmount() {
                return chrgAmount;
            }

            /**
             * Sets the value of the chrgAmount property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setChrgAmount(BigDecimal value) {
                this.chrgAmount = value;
            }

            /**
             * Gets the value of the cod property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCOD() {
                return cod;
            }

            /**
             * Sets the value of the cod property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCOD(Boolean value) {
                this.cod = value;
            }

            /**
             * Gets the value of the codAmount property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getCODAmount() {
                return codAmount;
            }

            /**
             * Sets the value of the codAmount property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setCODAmount(BigDecimal value) {
                this.codAmount = value;
            }

            /**
             * Gets the value of the codCheck property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCODCheck() {
                return codCheck;
            }

            /**
             * Sets the value of the codCheck property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCODCheck(Boolean value) {
                this.codCheck = value;
            }

            /**
             * Gets the value of the codFreight property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCODFreight() {
                return codFreight;
            }

            /**
             * Sets the value of the codFreight property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCODFreight(Boolean value) {
                this.codFreight = value;
            }

            /**
             * Gets the value of the declaredAmt property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getDeclaredAmt() {
                return declaredAmt;
            }

            /**
             * Sets the value of the declaredAmt property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setDeclaredAmt(BigDecimal value) {
                this.declaredAmt = value;
            }

            /**
             * Gets the value of the declaredIns property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDeclaredIns() {
                return declaredIns;
            }

            /**
             * Sets the value of the declaredIns property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDeclaredIns(Boolean value) {
                this.declaredIns = value;
            }

            /**
             * Gets the value of the demandAddAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandAddAction() {
                return demandAddAction;
            }

            /**
             * Sets the value of the demandAddAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandAddAction(String value) {
                this.demandAddAction = value;
            }

            /**
             * Gets the value of the demandAddLeadTime property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandAddLeadTime() {
                return demandAddLeadTime;
            }

            /**
             * Sets the value of the demandAddLeadTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandAddLeadTime(Integer value) {
                this.demandAddLeadTime = value;
            }

            /**
             * Gets the value of the demandAutoAccept property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandAutoAccept() {
                return demandAutoAccept;
            }

            /**
             * Sets the value of the demandAutoAccept property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandAutoAccept(Boolean value) {
                this.demandAutoAccept = value;
            }

            /**
             * Gets the value of the demandCancelAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCancelAction() {
                return demandCancelAction;
            }

            /**
             * Sets the value of the demandCancelAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCancelAction(String value) {
                this.demandCancelAction = value;
            }

            /**
             * Gets the value of the demandCancelLeadTime property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandCancelLeadTime() {
                return demandCancelLeadTime;
            }

            /**
             * Sets the value of the demandCancelLeadTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandCancelLeadTime(Integer value) {
                this.demandCancelLeadTime = value;
            }

            /**
             * Gets the value of the demandChangeAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandChangeAction() {
                return demandChangeAction;
            }

            /**
             * Sets the value of the demandChangeAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandChangeAction(String value) {
                this.demandChangeAction = value;
            }

            /**
             * Gets the value of the demandChangeDateAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandChangeDateAction() {
                return demandChangeDateAction;
            }

            /**
             * Sets the value of the demandChangeDateAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandChangeDateAction(String value) {
                this.demandChangeDateAction = value;
            }

            /**
             * Gets the value of the demandChangeDateLeadTime property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandChangeDateLeadTime() {
                return demandChangeDateLeadTime;
            }

            /**
             * Sets the value of the demandChangeDateLeadTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandChangeDateLeadTime(Integer value) {
                this.demandChangeDateLeadTime = value;
            }

            /**
             * Gets the value of the demandChangeLeadTime property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandChangeLeadTime() {
                return demandChangeLeadTime;
            }

            /**
             * Sets the value of the demandChangeLeadTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandChangeLeadTime(Integer value) {
                this.demandChangeLeadTime = value;
            }

            /**
             * Gets the value of the demandDateType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandDateType() {
                return demandDateType;
            }

            /**
             * Sets the value of the demandDateType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandDateType(String value) {
                this.demandDateType = value;
            }

            /**
             * Gets the value of the demandDayOfWeek property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandDayOfWeek() {
                return demandDayOfWeek;
            }

            /**
             * Sets the value of the demandDayOfWeek property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandDayOfWeek(Integer value) {
                this.demandDayOfWeek = value;
            }

            /**
             * Gets the value of the demandDeliveryDays property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandDeliveryDays() {
                return demandDeliveryDays;
            }

            /**
             * Sets the value of the demandDeliveryDays property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandDeliveryDays(Integer value) {
                this.demandDeliveryDays = value;
            }

            /**
             * Gets the value of the demandNewLineAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandNewLineAction() {
                return demandNewLineAction;
            }

            /**
             * Sets the value of the demandNewLineAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandNewLineAction(String value) {
                this.demandNewLineAction = value;
            }

            /**
             * Gets the value of the demandNewLineLeadTime property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandNewLineLeadTime() {
                return demandNewLineLeadTime;
            }

            /**
             * Sets the value of the demandNewLineLeadTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandNewLineLeadTime(Integer value) {
                this.demandNewLineLeadTime = value;
            }

            /**
             * Gets the value of the demandQtyChangeAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandQtyChangeAction() {
                return demandQtyChangeAction;
            }

            /**
             * Sets the value of the demandQtyChangeAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandQtyChangeAction(String value) {
                this.demandQtyChangeAction = value;
            }

            /**
             * Gets the value of the demandQtyChangeLeadTime property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDemandQtyChangeLeadTime() {
                return demandQtyChangeLeadTime;
            }

            /**
             * Sets the value of the demandQtyChangeLeadTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDemandQtyChangeLeadTime(Integer value) {
                this.demandQtyChangeLeadTime = value;
            }

            /**
             * Gets the value of the demandUseSysDate property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandUseSysDate() {
                return demandUseSysDate;
            }

            /**
             * Sets the value of the demandUseSysDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandUseSysDate(Boolean value) {
                this.demandUseSysDate = value;
            }

            /**
             * Gets the value of the docOnly property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDocOnly() {
                return docOnly;
            }

            /**
             * Sets the value of the docOnly property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDocOnly(Boolean value) {
                this.docOnly = value;
            }

            /**
             * Gets the value of the groundType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGroundType() {
                return groundType;
            }

            /**
             * Sets the value of the groundType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGroundType(String value) {
                this.groundType = value;
            }

            /**
             * Gets the value of the hazmat property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isHazmat() {
                return hazmat;
            }

            /**
             * Sets the value of the hazmat property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setHazmat(Boolean value) {
                this.hazmat = value;
            }

            /**
             * Gets the value of the notifyEMail property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNotifyEMail() {
                return notifyEMail;
            }

            /**
             * Sets the value of the notifyEMail property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNotifyEMail(String value) {
                this.notifyEMail = value;
            }

            /**
             * Gets the value of the notifyFlag property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isNotifyFlag() {
                return notifyFlag;
            }

            /**
             * Sets the value of the notifyFlag property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setNotifyFlag(Boolean value) {
                this.notifyFlag = value;
            }

            /**
             * Gets the value of the refNotes property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRefNotes() {
                return refNotes;
            }

            /**
             * Sets the value of the refNotes property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRefNotes(String value) {
                this.refNotes = value;
            }

            /**
             * Gets the value of the resDelivery property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isResDelivery() {
                return resDelivery;
            }

            /**
             * Sets the value of the resDelivery property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setResDelivery(Boolean value) {
                this.resDelivery = value;
            }

            /**
             * Gets the value of the satDelivery property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isSatDelivery() {
                return satDelivery;
            }

            /**
             * Sets the value of the satDelivery property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setSatDelivery(Boolean value) {
                this.satDelivery = value;
            }

            /**
             * Gets the value of the satPickup property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isSatPickup() {
                return satPickup;
            }

            /**
             * Sets the value of the satPickup property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setSatPickup(Boolean value) {
                this.satPickup = value;
            }

            /**
             * Gets the value of the tradingPartnerName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTradingPartnerName() {
                return tradingPartnerName;
            }

            /**
             * Sets the value of the tradingPartnerName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTradingPartnerName(String value) {
                this.tradingPartnerName = value;
            }

            /**
             * Gets the value of the verbalConf property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isVerbalConf() {
                return verbalConf;
            }

            /**
             * Sets the value of the verbalConf property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setVerbalConf(Boolean value) {
                this.verbalConf = value;
            }

            /**
             * Gets the value of the periodicityCode property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getPeriodicityCode() {
                return periodicityCode;
            }

            /**
             * Sets the value of the periodicityCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setPeriodicityCode(Integer value) {
                this.periodicityCode = value;
            }

            /**
             * Gets the value of the deliveryType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDeliveryType() {
                return deliveryType;
            }

            /**
             * Sets the value of the deliveryType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDeliveryType(String value) {
                this.deliveryType = value;
            }

            /**
             * Gets the value of the servAlert property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServAlert() {
                return servAlert;
            }

            /**
             * Sets the value of the servAlert property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServAlert(Boolean value) {
                this.servAlert = value;
            }

            /**
             * Gets the value of the servAOD property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServAOD() {
                return servAOD;
            }

            /**
             * Sets the value of the servAOD property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServAOD(Boolean value) {
                this.servAOD = value;
            }

            /**
             * Gets the value of the servAuthNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServAuthNum() {
                return servAuthNum;
            }

            /**
             * Sets the value of the servAuthNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServAuthNum(String value) {
                this.servAuthNum = value;
            }

            /**
             * Gets the value of the servDeliveryDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getServDeliveryDate() {
                return servDeliveryDate;
            }

            /**
             * Sets the value of the servDeliveryDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setServDeliveryDate(XMLGregorianCalendar value) {
                this.servDeliveryDate = value;
            }

            /**
             * Gets the value of the servHomeDel property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServHomeDel() {
                return servHomeDel;
            }

            /**
             * Sets the value of the servHomeDel property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServHomeDel(Boolean value) {
                this.servHomeDel = value;
            }

            /**
             * Gets the value of the servInstruct property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServInstruct() {
                return servInstruct;
            }

            /**
             * Sets the value of the servInstruct property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServInstruct(String value) {
                this.servInstruct = value;
            }

            /**
             * Gets the value of the servPhone property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServPhone() {
                return servPhone;
            }

            /**
             * Sets the value of the servPhone property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServPhone(String value) {
                this.servPhone = value;
            }

            /**
             * Gets the value of the servPOD property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServPOD() {
                return servPOD;
            }

            /**
             * Sets the value of the servPOD property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServPOD(Boolean value) {
                this.servPOD = value;
            }

            /**
             * Gets the value of the servRef1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServRef1() {
                return servRef1;
            }

            /**
             * Sets the value of the servRef1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServRef1(String value) {
                this.servRef1 = value;
            }

            /**
             * Gets the value of the servRef2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServRef2() {
                return servRef2;
            }

            /**
             * Sets the value of the servRef2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServRef2(String value) {
                this.servRef2 = value;
            }

            /**
             * Gets the value of the servRef3 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServRef3() {
                return servRef3;
            }

            /**
             * Sets the value of the servRef3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServRef3(String value) {
                this.servRef3 = value;
            }

            /**
             * Gets the value of the servRef4 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServRef4() {
                return servRef4;
            }

            /**
             * Sets the value of the servRef4 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServRef4(String value) {
                this.servRef4 = value;
            }

            /**
             * Gets the value of the servRef5 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getServRef5() {
                return servRef5;
            }

            /**
             * Sets the value of the servRef5 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setServRef5(String value) {
                this.servRef5 = value;
            }

            /**
             * Gets the value of the servRelease property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServRelease() {
                return servRelease;
            }

            /**
             * Sets the value of the servRelease property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServRelease(Boolean value) {
                this.servRelease = value;
            }

            /**
             * Gets the value of the servSatDelivery property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServSatDelivery() {
                return servSatDelivery;
            }

            /**
             * Sets the value of the servSatDelivery property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServSatDelivery(Boolean value) {
                this.servSatDelivery = value;
            }

            /**
             * Gets the value of the servSatPickup property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServSatPickup() {
                return servSatPickup;
            }

            /**
             * Sets the value of the servSatPickup property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServSatPickup(Boolean value) {
                this.servSatPickup = value;
            }

            /**
             * Gets the value of the servSignature property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isServSignature() {
                return servSignature;
            }

            /**
             * Sets the value of the servSignature property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setServSignature(Boolean value) {
                this.servSignature = value;
            }

            /**
             * Gets the value of the demandUseCustomerValues property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandUseCustomerValues() {
                return demandUseCustomerValues;
            }

            /**
             * Sets the value of the demandUseCustomerValues property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandUseCustomerValues(Boolean value) {
                this.demandUseCustomerValues = value;
            }

            /**
             * Gets the value of the earlyBuffer property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getEarlyBuffer() {
                return earlyBuffer;
            }

            /**
             * Sets the value of the earlyBuffer property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setEarlyBuffer(Integer value) {
                this.earlyBuffer = value;
            }

            /**
             * Gets the value of the lateBuffer property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getLateBuffer() {
                return lateBuffer;
            }

            /**
             * Sets the value of the lateBuffer property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setLateBuffer(Integer value) {
                this.lateBuffer = value;
            }

            /**
             * Gets the value of the overrideCarrier property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOverrideCarrier() {
                return overrideCarrier;
            }

            /**
             * Sets the value of the overrideCarrier property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOverrideCarrier(Boolean value) {
                this.overrideCarrier = value;
            }

            /**
             * Gets the value of the overrideService property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOverrideService() {
                return overrideService;
            }

            /**
             * Sets the value of the overrideService property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOverrideService(Boolean value) {
                this.overrideService = value;
            }

            /**
             * Gets the value of the demandUnitPriceDiff property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandUnitPriceDiff() {
                return demandUnitPriceDiff;
            }

            /**
             * Sets the value of the demandUnitPriceDiff property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandUnitPriceDiff(Boolean value) {
                this.demandUnitPriceDiff = value;
            }

            /**
             * Gets the value of the demandUnitPriceDiffAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandUnitPriceDiffAction() {
                return demandUnitPriceDiffAction;
            }

            /**
             * Sets the value of the demandUnitPriceDiffAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandUnitPriceDiffAction(String value) {
                this.demandUnitPriceDiffAction = value;
            }

            /**
             * Gets the value of the addressVal property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isAddressVal() {
                return addressVal;
            }

            /**
             * Sets the value of the addressVal property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setAddressVal(Boolean value) {
                this.addressVal = value;
            }

            /**
             * Gets the value of the excFromVal property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isExcFromVal() {
                return excFromVal;
            }

            /**
             * Sets the value of the excFromVal property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setExcFromVal(Boolean value) {
                this.excFromVal = value;
            }

            /**
             * Gets the value of the certOfOrigin property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCertOfOrigin() {
                return certOfOrigin;
            }

            /**
             * Sets the value of the certOfOrigin property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCertOfOrigin(Boolean value) {
                this.certOfOrigin = value;
            }

            /**
             * Gets the value of the commercialInvoice property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCommercialInvoice() {
                return commercialInvoice;
            }

            /**
             * Sets the value of the commercialInvoice property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCommercialInvoice(Boolean value) {
                this.commercialInvoice = value;
            }

            /**
             * Gets the value of the conAddress1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getConAddress1() {
                return conAddress1;
            }

            /**
             * Sets the value of the conAddress1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setConAddress1(String value) {
                this.conAddress1 = value;
            }

            /**
             * Gets the value of the conAddress2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getConAddress2() {
                return conAddress2;
            }

            /**
             * Sets the value of the conAddress2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setConAddress2(String value) {
                this.conAddress2 = value;
            }

            /**
             * Gets the value of the conCity property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getConCity() {
                return conCity;
            }

            /**
             * Sets the value of the conCity property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setConCity(String value) {
                this.conCity = value;
            }

            /**
             * Gets the value of the conCompName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getConCompName() {
                return conCompName;
            }

            /**
             * Sets the value of the conCompName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setConCompName(String value) {
                this.conCompName = value;
            }

            /**
             * Gets the value of the conContact property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getConContact() {
                return conContact;
            }

            /**
             * Sets the value of the conContact property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setConContact(String value) {
                this.conContact = value;
            }

            /**
             * Gets the value of the conCountry property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getConCountry() {
                return conCountry;
            }

            /**
             * Sets the value of the conCountry property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setConCountry(String value) {
                this.conCountry = value;
            }

            /**
             * Gets the value of the conPhoneNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getConPhoneNum() {
                return conPhoneNum;
            }

            /**
             * Sets the value of the conPhoneNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setConPhoneNum(String value) {
                this.conPhoneNum = value;
            }

            /**
             * Gets the value of the consigneeID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getConsigneeID() {
                return consigneeID;
            }

            /**
             * Sets the value of the consigneeID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setConsigneeID(String value) {
                this.consigneeID = value;
            }

            /**
             * Gets the value of the conState property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getConState() {
                return conState;
            }

            /**
             * Sets the value of the conState property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setConState(String value) {
                this.conState = value;
            }

            /**
             * Gets the value of the conZip property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getConZip() {
                return conZip;
            }

            /**
             * Sets the value of the conZip property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setConZip(String value) {
                this.conZip = value;
            }

            /**
             * Gets the value of the demandCheckForPart property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandCheckForPart() {
                return demandCheckForPart;
            }

            /**
             * Sets the value of the demandCheckForPart property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandCheckForPart(Boolean value) {
                this.demandCheckForPart = value;
            }

            /**
             * Gets the value of the demandCheckForPartAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCheckForPartAction() {
                return demandCheckForPartAction;
            }

            /**
             * Sets the value of the demandCheckForPartAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCheckForPartAction(String value) {
                this.demandCheckForPartAction = value;
            }

            /**
             * Gets the value of the ffAddress1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFAddress1() {
                return ffAddress1;
            }

            /**
             * Sets the value of the ffAddress1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFAddress1(String value) {
                this.ffAddress1 = value;
            }

            /**
             * Gets the value of the ffAddress2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFAddress2() {
                return ffAddress2;
            }

            /**
             * Sets the value of the ffAddress2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFAddress2(String value) {
                this.ffAddress2 = value;
            }

            /**
             * Gets the value of the ffCity property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFCity() {
                return ffCity;
            }

            /**
             * Sets the value of the ffCity property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFCity(String value) {
                this.ffCity = value;
            }

            /**
             * Gets the value of the ffCompName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFCompName() {
                return ffCompName;
            }

            /**
             * Sets the value of the ffCompName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFCompName(String value) {
                this.ffCompName = value;
            }

            /**
             * Gets the value of the ffContact property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFContact() {
                return ffContact;
            }

            /**
             * Sets the value of the ffContact property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFContact(String value) {
                this.ffContact = value;
            }

            /**
             * Gets the value of the ffCountry property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFCountry() {
                return ffCountry;
            }

            /**
             * Sets the value of the ffCountry property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFCountry(String value) {
                this.ffCountry = value;
            }

            /**
             * Gets the value of the ffid property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFID() {
                return ffid;
            }

            /**
             * Sets the value of the ffid property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFID(String value) {
                this.ffid = value;
            }

            /**
             * Gets the value of the ffPhoneNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFPhoneNum() {
                return ffPhoneNum;
            }

            /**
             * Sets the value of the ffPhoneNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFPhoneNum(String value) {
                this.ffPhoneNum = value;
            }

            /**
             * Gets the value of the ffState property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFState() {
                return ffState;
            }

            /**
             * Sets the value of the ffState property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFState(String value) {
                this.ffState = value;
            }

            /**
             * Gets the value of the ffZip property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFZip() {
                return ffZip;
            }

            /**
             * Sets the value of the ffZip property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFZip(String value) {
                this.ffZip = value;
            }

            /**
             * Gets the value of the hazardousShipment property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isHazardousShipment() {
                return hazardousShipment;
            }

            /**
             * Sets the value of the hazardousShipment property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setHazardousShipment(Boolean value) {
                this.hazardousShipment = value;
            }

            /**
             * Gets the value of the intrntlShip property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isIntrntlShip() {
                return intrntlShip;
            }

            /**
             * Sets the value of the intrntlShip property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setIntrntlShip(Boolean value) {
                this.intrntlShip = value;
            }

            /**
             * Gets the value of the letterOfInstr property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isLetterOfInstr() {
                return letterOfInstr;
            }

            /**
             * Sets the value of the letterOfInstr property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLetterOfInstr(Boolean value) {
                this.letterOfInstr = value;
            }

            /**
             * Gets the value of the shipExprtDeclartn property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isShipExprtDeclartn() {
                return shipExprtDeclartn;
            }

            /**
             * Sets the value of the shipExprtDeclartn property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setShipExprtDeclartn(Boolean value) {
                this.shipExprtDeclartn = value;
            }

            /**
             * Gets the value of the changeDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getChangeDate() {
                return changeDate;
            }

            /**
             * Sets the value of the changeDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setChangeDate(XMLGregorianCalendar value) {
                this.changeDate = value;
            }

            /**
             * Gets the value of the changedBy property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getChangedBy() {
                return changedBy;
            }

            /**
             * Sets the value of the changedBy property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setChangedBy(String value) {
                this.changedBy = value;
            }

            /**
             * Gets the value of the changeTime property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getChangeTime() {
                return changeTime;
            }

            /**
             * Sets the value of the changeTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setChangeTime(Integer value) {
                this.changeTime = value;
            }

            /**
             * Gets the value of the addlHdlgFlag property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isAddlHdlgFlag() {
                return addlHdlgFlag;
            }

            /**
             * Sets the value of the addlHdlgFlag property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setAddlHdlgFlag(Boolean value) {
                this.addlHdlgFlag = value;
            }

            /**
             * Gets the value of the deliveryConf property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getDeliveryConf() {
                return deliveryConf;
            }

            /**
             * Sets the value of the deliveryConf property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setDeliveryConf(Integer value) {
                this.deliveryConf = value;
            }

            /**
             * Gets the value of the etcAddrChg property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isETCAddrChg() {
                return etcAddrChg;
            }

            /**
             * Sets the value of the etcAddrChg property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setETCAddrChg(Boolean value) {
                this.etcAddrChg = value;
            }

            /**
             * Gets the value of the ffAddress3 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFFAddress3() {
                return ffAddress3;
            }

            /**
             * Sets the value of the ffAddress3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFFAddress3(String value) {
                this.ffAddress3 = value;
            }

            /**
             * Gets the value of the ffCountryNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getFFCountryNum() {
                return ffCountryNum;
            }

            /**
             * Sets the value of the ffCountryNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setFFCountryNum(Integer value) {
                this.ffCountryNum = value;
            }

            /**
             * Gets the value of the individualPackIDs property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isIndividualPackIDs() {
                return individualPackIDs;
            }

            /**
             * Sets the value of the individualPackIDs property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setIndividualPackIDs(Boolean value) {
                this.individualPackIDs = value;
            }

            /**
             * Gets the value of the nonStdPkg property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isNonStdPkg() {
                return nonStdPkg;
            }

            /**
             * Sets the value of the nonStdPkg property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setNonStdPkg(Boolean value) {
                this.nonStdPkg = value;
            }

            /**
             * Gets the value of the upsQuantumView property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isUPSQuantumView() {
                return upsQuantumView;
            }

            /**
             * Sets the value of the upsQuantumView property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setUPSQuantumView(Boolean value) {
                this.upsQuantumView = value;
            }

            /**
             * Gets the value of the upsqvEmailType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUPSQVEmailType() {
                return upsqvEmailType;
            }

            /**
             * Sets the value of the upsqvEmailType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUPSQVEmailType(String value) {
                this.upsqvEmailType = value;
            }

            /**
             * Gets the value of the upsqvMemo property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUPSQVMemo() {
                return upsqvMemo;
            }

            /**
             * Sets the value of the upsqvMemo property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUPSQVMemo(String value) {
                this.upsqvMemo = value;
            }

            /**
             * Gets the value of the upsqvShipFromName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUPSQVShipFromName() {
                return upsqvShipFromName;
            }

            /**
             * Sets the value of the upsqvShipFromName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUPSQVShipFromName(String value) {
                this.upsqvShipFromName = value;
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
             * Gets the value of the isAlternate property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isIsAlternate() {
                return isAlternate;
            }

            /**
             * Sets the value of the isAlternate property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setIsAlternate(Boolean value) {
                this.isAlternate = value;
            }

            /**
             * Gets the value of the masterCustNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getMasterCustNum() {
                return masterCustNum;
            }

            /**
             * Sets the value of the masterCustNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setMasterCustNum(Integer value) {
                this.masterCustNum = value;
            }

            /**
             * Gets the value of the masterShipToNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMasterShipToNum() {
                return masterShipToNum;
            }

            /**
             * Sets the value of the masterShipToNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMasterShipToNum(String value) {
                this.masterShipToNum = value;
            }

            /**
             * Gets the value of the demandCheckForRev property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandCheckForRev() {
                return demandCheckForRev;
            }

            /**
             * Sets the value of the demandCheckForRev property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandCheckForRev(Boolean value) {
                this.demandCheckForRev = value;
            }

            /**
             * Gets the value of the demandCheckForRevAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCheckForRevAction() {
                return demandCheckForRevAction;
            }

            /**
             * Sets the value of the demandCheckForRevAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCheckForRevAction(String value) {
                this.demandCheckForRevAction = value;
            }

            /**
             * Gets the value of the demandCheckPartialShip property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandCheckPartialShip() {
                return demandCheckPartialShip;
            }

            /**
             * Sets the value of the demandCheckPartialShip property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandCheckPartialShip(Boolean value) {
                this.demandCheckPartialShip = value;
            }

            /**
             * Gets the value of the demandCheckShipAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCheckShipAction() {
                return demandCheckShipAction;
            }

            /**
             * Sets the value of the demandCheckShipAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCheckShipAction(String value) {
                this.demandCheckShipAction = value;
            }

            /**
             * Gets the value of the demandCloseRejSkd property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandCloseRejSkd() {
                return demandCloseRejSkd;
            }

            /**
             * Sets the value of the demandCloseRejSkd property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandCloseRejSkd(Boolean value) {
                this.demandCloseRejSkd = value;
            }

            /**
             * Gets the value of the demandPricing property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandPricing() {
                return demandPricing;
            }

            /**
             * Sets the value of the demandPricing property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandPricing(String value) {
                this.demandPricing = value;
            }

            /**
             * Gets the value of the orgRegCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOrgRegCode() {
                return orgRegCode;
            }

            /**
             * Sets the value of the orgRegCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOrgRegCode(String value) {
                this.orgRegCode = value;
            }

            /**
             * Gets the value of the priceTolerance property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPriceTolerance() {
                return priceTolerance;
            }

            /**
             * Sets the value of the priceTolerance property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPriceTolerance(BigDecimal value) {
                this.priceTolerance = value;
            }

            /**
             * Gets the value of the taxRegReason property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTaxRegReason() {
                return taxRegReason;
            }

            /**
             * Sets the value of the taxRegReason property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTaxRegReason(String value) {
                this.taxRegReason = value;
            }

            /**
             * Gets the value of the checkConfirmCapPromise property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckConfirmCapPromise() {
                return checkConfirmCapPromise;
            }

            /**
             * Sets the value of the checkConfirmCapPromise property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckConfirmCapPromise(Boolean value) {
                this.checkConfirmCapPromise = value;
            }

            /**
             * Gets the value of the checkDateCapPromise property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckDateCapPromise() {
                return checkDateCapPromise;
            }

            /**
             * Sets the value of the checkDateCapPromise property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckDateCapPromise(Boolean value) {
                this.checkDateCapPromise = value;
            }

            /**
             * Gets the value of the checkUpdateCapPromise property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCheckUpdateCapPromise() {
                return checkUpdateCapPromise;
            }

            /**
             * Sets the value of the checkUpdateCapPromise property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCheckUpdateCapPromise(Boolean value) {
                this.checkUpdateCapPromise = value;
            }

            /**
             * Gets the value of the demandCapPromiseAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCapPromiseAction() {
                return demandCapPromiseAction;
            }

            /**
             * Sets the value of the demandCapPromiseAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCapPromiseAction(String value) {
                this.demandCapPromiseAction = value;
            }

            /**
             * Gets the value of the demandCapPromiseDate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCapPromiseDate() {
                return demandCapPromiseDate;
            }

            /**
             * Sets the value of the demandCapPromiseDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCapPromiseDate(String value) {
                this.demandCapPromiseDate = value;
            }

            /**
             * Gets the value of the demandCapPromiseUpdate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCapPromiseUpdate() {
                return demandCapPromiseUpdate;
            }

            /**
             * Sets the value of the demandCapPromiseUpdate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCapPromiseUpdate(String value) {
                this.demandCapPromiseUpdate = value;
            }

            /**
             * Gets the value of the sicCode property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getSICCode() {
                return sicCode;
            }

            /**
             * Sets the value of the sicCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setSICCode(Integer value) {
                this.sicCode = value;
            }

            /**
             * Gets the value of the icCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getICCode() {
                return icCode;
            }

            /**
             * Sets the value of the icCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setICCode(String value) {
                this.icCode = value;
            }

            /**
             * Gets the value of the otSmartString property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOTSmartString() {
                return otSmartString;
            }

            /**
             * Sets the value of the otSmartString property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOTSmartString(Boolean value) {
                this.otSmartString = value;
            }

            /**
             * Gets the value of the legalName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLegalName() {
                return legalName;
            }

            /**
             * Sets the value of the legalName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLegalName(String value) {
                this.legalName = value;
            }

            /**
             * Gets the value of the demandCheckCfgAction property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDemandCheckCfgAction() {
                return demandCheckCfgAction;
            }

            /**
             * Sets the value of the demandCheckCfgAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDemandCheckCfgAction(String value) {
                this.demandCheckCfgAction = value;
            }

            /**
             * Gets the value of the demandCheckConfig property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDemandCheckConfig() {
                return demandCheckConfig;
            }

            /**
             * Sets the value of the demandCheckConfig property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDemandCheckConfig(Boolean value) {
                this.demandCheckConfig = value;
            }

            /**
             * Gets the value of the linkShipToNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLinkShipToNum() {
                return linkShipToNum;
            }

            /**
             * Sets the value of the linkShipToNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLinkShipToNum(String value) {
                this.linkShipToNum = value;
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
