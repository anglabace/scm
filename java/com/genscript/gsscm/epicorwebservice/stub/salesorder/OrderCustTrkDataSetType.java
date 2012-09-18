
package com.genscript.gsscm.epicorwebservice.stub.salesorder;

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
 * <p>Java class for OrderCustTrkDataSetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderCustTrkDataSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderCustTrkDataSet">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="OrderCustTrk">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="OrderNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="OpenOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OrderHeld" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PONum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="ShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShpConNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="PrcConNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="OrderDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="OrderHedNeedByDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="OrderHedRequestDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OrderLine" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="OpenLine" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="POLine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LineDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SellingQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="SalesUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DocUnitPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="ProdCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ProdCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OrderDtlNeedByDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="OrderDtlRequestDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="WebOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="OnCreditHold" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="RevisionNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="XPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="XRevisionNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="BTCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTCustomerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTAddressList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTContactFaxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTContactPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTContactName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BTConNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="CreditOverride" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="SoldToCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SoldToCustName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CustomerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShipToName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="POLineRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "OrderCustTrkDataSetType", namespace = "http://epicor.com/schemas", propOrder = {
    "orderCustTrkDataSet"
})
public class OrderCustTrkDataSetType {

    @XmlElement(name = "OrderCustTrkDataSet", required = true)
    protected OrderCustTrkDataSetType.OrderCustTrkDataSet orderCustTrkDataSet;

    /**
     * Gets the value of the orderCustTrkDataSet property.
     * 
     * @return
     *     possible object is
     *     {@link OrderCustTrkDataSetType.OrderCustTrkDataSet }
     *     
     */
    public OrderCustTrkDataSetType.OrderCustTrkDataSet getOrderCustTrkDataSet() {
        return orderCustTrkDataSet;
    }

    /**
     * Sets the value of the orderCustTrkDataSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderCustTrkDataSetType.OrderCustTrkDataSet }
     *     
     */
    public void setOrderCustTrkDataSet(OrderCustTrkDataSetType.OrderCustTrkDataSet value) {
        this.orderCustTrkDataSet = value;
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
     *         &lt;element name="OrderCustTrk">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="OrderNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="OpenOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OrderHeld" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PONum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="ShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShpConNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="PrcConNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="OrderDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="OrderHedNeedByDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="OrderHedRequestDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OrderLine" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="OpenLine" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="POLine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="LineDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SellingQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="SalesUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DocUnitPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="ProdCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ProdCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OrderDtlNeedByDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="OrderDtlRequestDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="WebOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="OnCreditHold" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="RevisionNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="XPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="XRevisionNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="BTCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTCustomerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTAddressList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTContactFaxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTContactPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTContactName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BTConNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="CreditOverride" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="SoldToCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SoldToCustName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CustomerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShipToName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="POLineRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "orderCustTrkOrWebServiceErrors"
    })
    public static class OrderCustTrkDataSet {

        @XmlElements({
            @XmlElement(name = "OrderCustTrk", type = OrderCustTrkDataSetType.OrderCustTrkDataSet.OrderCustTrk.class),
            @XmlElement(name = "WebServiceErrors", type = OrderCustTrkDataSetType.OrderCustTrkDataSet.WebServiceErrors.class)
        })
        protected List<Object> orderCustTrkOrWebServiceErrors;

        /**
         * Gets the value of the orderCustTrkOrWebServiceErrors property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the orderCustTrkOrWebServiceErrors property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOrderCustTrkOrWebServiceErrors().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OrderCustTrkDataSetType.OrderCustTrkDataSet.OrderCustTrk }
         * {@link OrderCustTrkDataSetType.OrderCustTrkDataSet.WebServiceErrors }
         * 
         * 
         */
        public List<Object> getOrderCustTrkOrWebServiceErrors() {
            if (orderCustTrkOrWebServiceErrors == null) {
                orderCustTrkOrWebServiceErrors = new ArrayList<Object>();
            }
            return this.orderCustTrkOrWebServiceErrors;
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
         *         &lt;element name="OrderNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="OpenOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OrderHeld" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PONum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="ShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShpConNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="PrcConNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="OrderDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="OrderHedNeedByDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="OrderHedRequestDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OrderLine" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="OpenLine" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="POLine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="LineDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SellingQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="SalesUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DocUnitPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="ProdCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ProdCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OrderDtlNeedByDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="OrderDtlRequestDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="WebOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="OnCreditHold" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="RevisionNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="XPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="XRevisionNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTCustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="BTCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTCustomerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTAddressList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTContactFaxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTContactPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTContactName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BTConNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="CreditOverride" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="SoldToCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SoldToCustName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CustomerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShipToName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="POLineRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "orderNum",
            "openOrder",
            "company",
            "orderHeld",
            "poNum",
            "custNum",
            "shipToNum",
            "shpConNum",
            "prcConNum",
            "orderDate",
            "orderHedNeedByDate",
            "orderHedRequestDate",
            "currencyCode",
            "orderLine",
            "openLine",
            "poLine",
            "partNum",
            "lineDesc",
            "sellingQuantity",
            "salesUM",
            "docUnitPrice",
            "prodCode",
            "prodCodeDescription",
            "orderDtlNeedByDate",
            "orderDtlRequestDate",
            "webOrder",
            "onCreditHold",
            "revisionNum",
            "xPartNum",
            "xRevisionNum",
            "btCustNum",
            "btCustID",
            "btCustomerName",
            "btAddressList",
            "btContactFaxNum",
            "btContactPhoneNum",
            "btContactName",
            "btConNum",
            "creditOverride",
            "soldToCustID",
            "soldToCustName",
            "customerName",
            "shipToName",
            "custID",
            "poLineRef",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class OrderCustTrk {

            @XmlElement(name = "OrderNum")
            protected Integer orderNum;
            @XmlElement(name = "OpenOrder")
            protected Boolean openOrder;
            @XmlElement(name = "Company")
            protected String company;
            @XmlElement(name = "OrderHeld")
            protected Boolean orderHeld;
            @XmlElement(name = "PONum")
            protected String poNum;
            @XmlElement(name = "CustNum")
            protected Integer custNum;
            @XmlElement(name = "ShipToNum")
            protected String shipToNum;
            @XmlElement(name = "ShpConNum")
            protected Integer shpConNum;
            @XmlElement(name = "PrcConNum")
            protected Integer prcConNum;
            @XmlElement(name = "OrderDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar orderDate;
            @XmlElement(name = "OrderHedNeedByDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar orderHedNeedByDate;
            @XmlElement(name = "OrderHedRequestDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar orderHedRequestDate;
            @XmlElement(name = "CurrencyCode")
            protected String currencyCode;
            @XmlElement(name = "OrderLine")
            protected Integer orderLine;
            @XmlElement(name = "OpenLine")
            protected Boolean openLine;
            @XmlElement(name = "POLine")
            protected String poLine;
            @XmlElement(name = "PartNum")
            protected String partNum;
            @XmlElement(name = "LineDesc")
            protected String lineDesc;
            @XmlElement(name = "SellingQuantity")
            protected BigDecimal sellingQuantity;
            @XmlElement(name = "SalesUM")
            protected String salesUM;
            @XmlElement(name = "DocUnitPrice")
            protected BigDecimal docUnitPrice;
            @XmlElement(name = "ProdCode")
            protected String prodCode;
            @XmlElement(name = "ProdCodeDescription")
            protected String prodCodeDescription;
            @XmlElement(name = "OrderDtlNeedByDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar orderDtlNeedByDate;
            @XmlElement(name = "OrderDtlRequestDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar orderDtlRequestDate;
            @XmlElement(name = "WebOrder")
            protected Boolean webOrder;
            @XmlElement(name = "OnCreditHold")
            protected Boolean onCreditHold;
            @XmlElement(name = "RevisionNum")
            protected String revisionNum;
            @XmlElement(name = "XPartNum")
            protected String xPartNum;
            @XmlElement(name = "XRevisionNum")
            protected String xRevisionNum;
            @XmlElement(name = "BTCustNum")
            protected Integer btCustNum;
            @XmlElement(name = "BTCustID")
            protected String btCustID;
            @XmlElement(name = "BTCustomerName")
            protected String btCustomerName;
            @XmlElement(name = "BTAddressList")
            protected String btAddressList;
            @XmlElement(name = "BTContactFaxNum")
            protected String btContactFaxNum;
            @XmlElement(name = "BTContactPhoneNum")
            protected String btContactPhoneNum;
            @XmlElement(name = "BTContactName")
            protected String btContactName;
            @XmlElement(name = "BTConNum")
            protected Integer btConNum;
            @XmlElement(name = "CreditOverride")
            protected Boolean creditOverride;
            @XmlElement(name = "SoldToCustID")
            protected String soldToCustID;
            @XmlElement(name = "SoldToCustName")
            protected String soldToCustName;
            @XmlElement(name = "CustomerName")
            protected String customerName;
            @XmlElement(name = "ShipToName")
            protected String shipToName;
            @XmlElement(name = "CustID")
            protected String custID;
            @XmlElement(name = "POLineRef")
            protected String poLineRef;
            @XmlElement(name = "RowIdent")
            protected String rowIdent;
            @XmlElement(name = "RowMod")
            protected String rowMod;
            @XmlElement(name = "DBRowIdent")
            protected byte[] dbRowIdent;

            /**
             * Gets the value of the orderNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getOrderNum() {
                return orderNum;
            }

            /**
             * Sets the value of the orderNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setOrderNum(Integer value) {
                this.orderNum = value;
            }

            /**
             * Gets the value of the openOrder property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOpenOrder() {
                return openOrder;
            }

            /**
             * Sets the value of the openOrder property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOpenOrder(Boolean value) {
                this.openOrder = value;
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
             * Gets the value of the orderHeld property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOrderHeld() {
                return orderHeld;
            }

            /**
             * Sets the value of the orderHeld property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOrderHeld(Boolean value) {
                this.orderHeld = value;
            }

            /**
             * Gets the value of the poNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPONum() {
                return poNum;
            }

            /**
             * Sets the value of the poNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPONum(String value) {
                this.poNum = value;
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
             * Gets the value of the shpConNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getShpConNum() {
                return shpConNum;
            }

            /**
             * Sets the value of the shpConNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setShpConNum(Integer value) {
                this.shpConNum = value;
            }

            /**
             * Gets the value of the prcConNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getPrcConNum() {
                return prcConNum;
            }

            /**
             * Sets the value of the prcConNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setPrcConNum(Integer value) {
                this.prcConNum = value;
            }

            /**
             * Gets the value of the orderDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getOrderDate() {
                return orderDate;
            }

            /**
             * Sets the value of the orderDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setOrderDate(XMLGregorianCalendar value) {
                this.orderDate = value;
            }

            /**
             * Gets the value of the orderHedNeedByDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getOrderHedNeedByDate() {
                return orderHedNeedByDate;
            }

            /**
             * Sets the value of the orderHedNeedByDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setOrderHedNeedByDate(XMLGregorianCalendar value) {
                this.orderHedNeedByDate = value;
            }

            /**
             * Gets the value of the orderHedRequestDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getOrderHedRequestDate() {
                return orderHedRequestDate;
            }

            /**
             * Sets the value of the orderHedRequestDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setOrderHedRequestDate(XMLGregorianCalendar value) {
                this.orderHedRequestDate = value;
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
             * Gets the value of the orderLine property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getOrderLine() {
                return orderLine;
            }

            /**
             * Sets the value of the orderLine property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setOrderLine(Integer value) {
                this.orderLine = value;
            }

            /**
             * Gets the value of the openLine property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOpenLine() {
                return openLine;
            }

            /**
             * Sets the value of the openLine property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOpenLine(Boolean value) {
                this.openLine = value;
            }

            /**
             * Gets the value of the poLine property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPOLine() {
                return poLine;
            }

            /**
             * Sets the value of the poLine property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPOLine(String value) {
                this.poLine = value;
            }

            /**
             * Gets the value of the partNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPartNum() {
                return partNum;
            }

            /**
             * Sets the value of the partNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPartNum(String value) {
                this.partNum = value;
            }

            /**
             * Gets the value of the lineDesc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLineDesc() {
                return lineDesc;
            }

            /**
             * Sets the value of the lineDesc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLineDesc(String value) {
                this.lineDesc = value;
            }

            /**
             * Gets the value of the sellingQuantity property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getSellingQuantity() {
                return sellingQuantity;
            }

            /**
             * Sets the value of the sellingQuantity property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setSellingQuantity(BigDecimal value) {
                this.sellingQuantity = value;
            }

            /**
             * Gets the value of the salesUM property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSalesUM() {
                return salesUM;
            }

            /**
             * Sets the value of the salesUM property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSalesUM(String value) {
                this.salesUM = value;
            }

            /**
             * Gets the value of the docUnitPrice property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getDocUnitPrice() {
                return docUnitPrice;
            }

            /**
             * Sets the value of the docUnitPrice property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setDocUnitPrice(BigDecimal value) {
                this.docUnitPrice = value;
            }

            /**
             * Gets the value of the prodCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getProdCode() {
                return prodCode;
            }

            /**
             * Sets the value of the prodCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setProdCode(String value) {
                this.prodCode = value;
            }

            /**
             * Gets the value of the prodCodeDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getProdCodeDescription() {
                return prodCodeDescription;
            }

            /**
             * Sets the value of the prodCodeDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setProdCodeDescription(String value) {
                this.prodCodeDescription = value;
            }

            /**
             * Gets the value of the orderDtlNeedByDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getOrderDtlNeedByDate() {
                return orderDtlNeedByDate;
            }

            /**
             * Sets the value of the orderDtlNeedByDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setOrderDtlNeedByDate(XMLGregorianCalendar value) {
                this.orderDtlNeedByDate = value;
            }

            /**
             * Gets the value of the orderDtlRequestDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getOrderDtlRequestDate() {
                return orderDtlRequestDate;
            }

            /**
             * Sets the value of the orderDtlRequestDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setOrderDtlRequestDate(XMLGregorianCalendar value) {
                this.orderDtlRequestDate = value;
            }

            /**
             * Gets the value of the webOrder property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isWebOrder() {
                return webOrder;
            }

            /**
             * Sets the value of the webOrder property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setWebOrder(Boolean value) {
                this.webOrder = value;
            }

            /**
             * Gets the value of the onCreditHold property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOnCreditHold() {
                return onCreditHold;
            }

            /**
             * Sets the value of the onCreditHold property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOnCreditHold(Boolean value) {
                this.onCreditHold = value;
            }

            /**
             * Gets the value of the revisionNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRevisionNum() {
                return revisionNum;
            }

            /**
             * Sets the value of the revisionNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRevisionNum(String value) {
                this.revisionNum = value;
            }

            /**
             * Gets the value of the xPartNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getXPartNum() {
                return xPartNum;
            }

            /**
             * Sets the value of the xPartNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setXPartNum(String value) {
                this.xPartNum = value;
            }

            /**
             * Gets the value of the xRevisionNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getXRevisionNum() {
                return xRevisionNum;
            }

            /**
             * Sets the value of the xRevisionNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setXRevisionNum(String value) {
                this.xRevisionNum = value;
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
             * Gets the value of the btCustID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTCustID() {
                return btCustID;
            }

            /**
             * Sets the value of the btCustID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTCustID(String value) {
                this.btCustID = value;
            }

            /**
             * Gets the value of the btCustomerName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTCustomerName() {
                return btCustomerName;
            }

            /**
             * Sets the value of the btCustomerName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTCustomerName(String value) {
                this.btCustomerName = value;
            }

            /**
             * Gets the value of the btAddressList property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTAddressList() {
                return btAddressList;
            }

            /**
             * Sets the value of the btAddressList property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTAddressList(String value) {
                this.btAddressList = value;
            }

            /**
             * Gets the value of the btContactFaxNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTContactFaxNum() {
                return btContactFaxNum;
            }

            /**
             * Sets the value of the btContactFaxNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTContactFaxNum(String value) {
                this.btContactFaxNum = value;
            }

            /**
             * Gets the value of the btContactPhoneNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTContactPhoneNum() {
                return btContactPhoneNum;
            }

            /**
             * Sets the value of the btContactPhoneNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTContactPhoneNum(String value) {
                this.btContactPhoneNum = value;
            }

            /**
             * Gets the value of the btContactName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBTContactName() {
                return btContactName;
            }

            /**
             * Sets the value of the btContactName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBTContactName(String value) {
                this.btContactName = value;
            }

            /**
             * Gets the value of the btConNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getBTConNum() {
                return btConNum;
            }

            /**
             * Sets the value of the btConNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setBTConNum(Integer value) {
                this.btConNum = value;
            }

            /**
             * Gets the value of the creditOverride property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCreditOverride() {
                return creditOverride;
            }

            /**
             * Sets the value of the creditOverride property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCreditOverride(Boolean value) {
                this.creditOverride = value;
            }

            /**
             * Gets the value of the soldToCustID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSoldToCustID() {
                return soldToCustID;
            }

            /**
             * Sets the value of the soldToCustID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSoldToCustID(String value) {
                this.soldToCustID = value;
            }

            /**
             * Gets the value of the soldToCustName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSoldToCustName() {
                return soldToCustName;
            }

            /**
             * Sets the value of the soldToCustName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSoldToCustName(String value) {
                this.soldToCustName = value;
            }

            /**
             * Gets the value of the customerName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCustomerName() {
                return customerName;
            }

            /**
             * Sets the value of the customerName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCustomerName(String value) {
                this.customerName = value;
            }

            /**
             * Gets the value of the shipToName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShipToName() {
                return shipToName;
            }

            /**
             * Sets the value of the shipToName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShipToName(String value) {
                this.shipToName = value;
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
             * Gets the value of the poLineRef property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPOLineRef() {
                return poLineRef;
            }

            /**
             * Sets the value of the poLineRef property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPOLineRef(String value) {
                this.poLineRef = value;
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
