
package com.genscript.gsscm.epicorwebservice.stub.inventory;

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
 * <p>Java class for InventoryQtyAdjDataSetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InventoryQtyAdjDataSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InventoryQtyAdjDataSet">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="InventoryQtyAdj">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="WareHseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OnHandQty" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="BinNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AdjustQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="ReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LotNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Reference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UnitOfMeasure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TransDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="ReasonType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SerialNoQty" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="TempSerialNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="ReasonCodeReq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="AllowNegQty" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LegalNumberMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="StkUOMCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="EnableSN" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="OnHandUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TranDocTypeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartTrackSerialNum" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PartSellingFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="PartPricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartTrackDimension" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PartIUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartSalesUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartTrackLots" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PartPartDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ReasonCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="WareHseDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="WhseBinDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="LegalNumGenOpts">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="LegalNumberID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="TransYear" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="TransYearSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DspTransYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShowDspTransYear" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="Prefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PrefixList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NumberSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="EnablePrefix" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="EnableSuffix" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="NumberOption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DocumentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="GenerationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TransPeriod" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="PeriodPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShowTransPeriod" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LegalNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="SelectedSerialNumbers">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="SerialNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Scrapped" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ScrappedReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Voided" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="Reference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ReasonCodeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ReasonCodeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="SNPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SNBaseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SourceRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TransType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PassedInspection" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="Deselected" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="KitWhseList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RawSerialNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="KBLbrAction" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="KBLbrActionDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PreventDeselect" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="XRefPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="XRefPartType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="SNFormat">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="SNFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SNBaseDataType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NumberOfDigits" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="LeadingZeroes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="SNPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="HasSerialNumbers" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="Plant" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="SNMask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SNMaskSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SNMaskPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SNLastUsedSeq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartPricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartTrackDimension" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PartIUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartSalesUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartTrackLots" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PartPartDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartSellingFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="PartTrackSerialNum" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="SerialMaskMask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SerialMaskDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SerialMaskMaskType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="SerialMaskExample" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "InventoryQtyAdjDataSetType", namespace = "http://epicor.com/schemas", propOrder = {
    "inventoryQtyAdjDataSet"
})
public class InventoryQtyAdjDataSetType {

    @XmlElement(name = "InventoryQtyAdjDataSet", required = true)
    protected InventoryQtyAdjDataSetType.InventoryQtyAdjDataSet inventoryQtyAdjDataSet;

    /**
     * Gets the value of the inventoryQtyAdjDataSet property.
     * 
     * @return
     *     possible object is
     *     {@link InventoryQtyAdjDataSetType.InventoryQtyAdjDataSet }
     *     
     */
    public InventoryQtyAdjDataSetType.InventoryQtyAdjDataSet getInventoryQtyAdjDataSet() {
        return inventoryQtyAdjDataSet;
    }

    /**
     * Sets the value of the inventoryQtyAdjDataSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link InventoryQtyAdjDataSetType.InventoryQtyAdjDataSet }
     *     
     */
    public void setInventoryQtyAdjDataSet(InventoryQtyAdjDataSetType.InventoryQtyAdjDataSet value) {
        this.inventoryQtyAdjDataSet = value;
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
     *         &lt;element name="InventoryQtyAdj">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="WareHseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OnHandQty" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="BinNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AdjustQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="ReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="LotNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Reference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UnitOfMeasure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TransDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="ReasonType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SerialNoQty" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="TempSerialNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="ReasonCodeReq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="AllowNegQty" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LegalNumberMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="StkUOMCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="EnableSN" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="OnHandUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TranDocTypeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartTrackSerialNum" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PartSellingFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="PartPricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartTrackDimension" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PartIUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartSalesUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartTrackLots" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PartPartDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ReasonCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="WareHseDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="WhseBinDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="LegalNumGenOpts">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="LegalNumberID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="TransYear" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="TransYearSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DspTransYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShowDspTransYear" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="Prefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PrefixList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NumberSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="EnablePrefix" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="EnableSuffix" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="NumberOption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DocumentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="GenerationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TransPeriod" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="PeriodPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShowTransPeriod" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LegalNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="SelectedSerialNumbers">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="SerialNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Scrapped" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ScrappedReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Voided" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="Reference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ReasonCodeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ReasonCodeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="SNPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SNBaseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SourceRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TransType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PassedInspection" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="Deselected" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="KitWhseList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RawSerialNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="KBLbrAction" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="KBLbrActionDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PreventDeselect" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="XRefPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="XRefPartType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RowIdent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RowMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DBRowIdent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="SNFormat">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="SNFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SNBaseDataType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NumberOfDigits" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="LeadingZeroes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="SNPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="HasSerialNumbers" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="Plant" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="SNMask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SNMaskSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SNMaskPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SNLastUsedSeq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartPricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartTrackDimension" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PartIUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartSalesUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartTrackLots" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PartPartDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartSellingFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="PartTrackSerialNum" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="SerialMaskMask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SerialMaskDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SerialMaskMaskType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="SerialMaskExample" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "inventoryQtyAdjOrLegalNumGenOptsOrSelectedSerialNumbers"
    })
    public static class InventoryQtyAdjDataSet {

        @XmlElements({
            @XmlElement(name = "InventoryQtyAdj", type = InventoryQtyAdjDataSetType.InventoryQtyAdjDataSet.InventoryQtyAdj.class),
            @XmlElement(name = "SNFormat", type = InventoryQtyAdjDataSetType.InventoryQtyAdjDataSet.SNFormat.class),
            @XmlElement(name = "LegalNumGenOpts", type = InventoryQtyAdjDataSetType.InventoryQtyAdjDataSet.LegalNumGenOpts.class),
            @XmlElement(name = "SelectedSerialNumbers", type = InventoryQtyAdjDataSetType.InventoryQtyAdjDataSet.SelectedSerialNumbers.class),
            @XmlElement(name = "WebServiceErrors", type = InventoryQtyAdjDataSetType.InventoryQtyAdjDataSet.WebServiceErrors.class)
        })
        protected List<Object> inventoryQtyAdjOrLegalNumGenOptsOrSelectedSerialNumbers;

        /**
         * Gets the value of the inventoryQtyAdjOrLegalNumGenOptsOrSelectedSerialNumbers property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the inventoryQtyAdjOrLegalNumGenOptsOrSelectedSerialNumbers property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInventoryQtyAdjOrLegalNumGenOptsOrSelectedSerialNumbers().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link InventoryQtyAdjDataSetType.InventoryQtyAdjDataSet.InventoryQtyAdj }
         * {@link InventoryQtyAdjDataSetType.InventoryQtyAdjDataSet.SNFormat }
         * {@link InventoryQtyAdjDataSetType.InventoryQtyAdjDataSet.LegalNumGenOpts }
         * {@link InventoryQtyAdjDataSetType.InventoryQtyAdjDataSet.SelectedSerialNumbers }
         * {@link InventoryQtyAdjDataSetType.InventoryQtyAdjDataSet.WebServiceErrors }
         * 
         * 
         */
        public List<Object> getInventoryQtyAdjOrLegalNumGenOptsOrSelectedSerialNumbers() {
            if (inventoryQtyAdjOrLegalNumGenOptsOrSelectedSerialNumbers == null) {
                inventoryQtyAdjOrLegalNumGenOptsOrSelectedSerialNumbers = new ArrayList<Object>();
            }
            return this.inventoryQtyAdjOrLegalNumGenOptsOrSelectedSerialNumbers;
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
         *         &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="WareHseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OnHandQty" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="BinNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AdjustQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="ReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="LotNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Reference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UnitOfMeasure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TransDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="ReasonType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SerialNoQty" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="TempSerialNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="ReasonCodeReq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="AllowNegQty" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LegalNumberMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="StkUOMCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="EnableSN" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="OnHandUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TranDocTypeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartTrackSerialNum" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PartSellingFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="PartPricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartTrackDimension" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PartIUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartSalesUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartTrackLots" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PartPartDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ReasonCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="WareHseDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="WhseBinDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "partNum",
            "wareHseCode",
            "onHandQty",
            "binNum",
            "adjustQuantity",
            "reasonCode",
            "lotNum",
            "reference",
            "unitOfMeasure",
            "transDate",
            "reasonType",
            "serialNoQty",
            "tempSerialNo",
            "reasonCodeReq",
            "allowNegQty",
            "legalNumberMessage",
            "stkUOMCode",
            "enableSN",
            "onHandUOM",
            "tranDocTypeID",
            "partTrackSerialNum",
            "partSellingFactor",
            "partPricePerCode",
            "partTrackDimension",
            "partIUM",
            "partSalesUM",
            "partTrackLots",
            "partPartDescription",
            "reasonCodeDescription",
            "wareHseDescription",
            "whseBinDescription",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class InventoryQtyAdj {

            @XmlElement(name = "Company")
            protected String company;
            @XmlElement(name = "PartNum", required = true)
            protected String partNum;
            @XmlElement(name = "WareHseCode")
            protected String wareHseCode;
            @XmlElement(name = "OnHandQty")
            protected BigDecimal onHandQty;
            @XmlElement(name = "BinNum")
            protected String binNum;
            @XmlElement(name = "AdjustQuantity")
            protected BigDecimal adjustQuantity;
            @XmlElement(name = "ReasonCode")
            protected String reasonCode;
            @XmlElement(name = "LotNum")
            protected String lotNum;
            @XmlElement(name = "Reference")
            protected String reference;
            @XmlElement(name = "UnitOfMeasure")
            protected String unitOfMeasure;
            @XmlElement(name = "TransDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar transDate;
            @XmlElement(name = "ReasonType")
            protected String reasonType;
            @XmlElement(name = "SerialNoQty")
            protected Integer serialNoQty;
            @XmlElement(name = "TempSerialNo")
            protected Integer tempSerialNo;
            @XmlElement(name = "ReasonCodeReq")
            protected Boolean reasonCodeReq;
            @XmlElement(name = "AllowNegQty")
            protected Boolean allowNegQty;
            @XmlElement(name = "LegalNumberMessage")
            protected String legalNumberMessage;
            @XmlElement(name = "StkUOMCode")
            protected String stkUOMCode;
            @XmlElement(name = "EnableSN")
            protected Boolean enableSN;
            @XmlElement(name = "OnHandUOM")
            protected String onHandUOM;
            @XmlElement(name = "TranDocTypeID")
            protected String tranDocTypeID;
            @XmlElement(name = "PartTrackSerialNum")
            protected Boolean partTrackSerialNum;
            @XmlElement(name = "PartSellingFactor")
            protected BigDecimal partSellingFactor;
            @XmlElement(name = "PartPricePerCode")
            protected String partPricePerCode;
            @XmlElement(name = "PartTrackDimension")
            protected Boolean partTrackDimension;
            @XmlElement(name = "PartIUM")
            protected String partIUM;
            @XmlElement(name = "PartSalesUM")
            protected String partSalesUM;
            @XmlElement(name = "PartTrackLots")
            protected Boolean partTrackLots;
            @XmlElement(name = "PartPartDescription")
            protected String partPartDescription;
            @XmlElement(name = "ReasonCodeDescription")
            protected String reasonCodeDescription;
            @XmlElement(name = "WareHseDescription")
            protected String wareHseDescription;
            @XmlElement(name = "WhseBinDescription")
            protected String whseBinDescription;
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
             * Gets the value of the wareHseCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getWareHseCode() {
                return wareHseCode;
            }

            /**
             * Sets the value of the wareHseCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setWareHseCode(String value) {
                this.wareHseCode = value;
            }

            /**
             * Gets the value of the onHandQty property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getOnHandQty() {
                return onHandQty;
            }

            /**
             * Sets the value of the onHandQty property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setOnHandQty(BigDecimal value) {
                this.onHandQty = value;
            }

            /**
             * Gets the value of the binNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBinNum() {
                return binNum;
            }

            /**
             * Sets the value of the binNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBinNum(String value) {
                this.binNum = value;
            }

            /**
             * Gets the value of the adjustQuantity property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getAdjustQuantity() {
                return adjustQuantity;
            }

            /**
             * Sets the value of the adjustQuantity property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setAdjustQuantity(BigDecimal value) {
                this.adjustQuantity = value;
            }

            /**
             * Gets the value of the reasonCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReasonCode() {
                return reasonCode;
            }

            /**
             * Sets the value of the reasonCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReasonCode(String value) {
                this.reasonCode = value;
            }

            /**
             * Gets the value of the lotNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLotNum() {
                return lotNum;
            }

            /**
             * Sets the value of the lotNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLotNum(String value) {
                this.lotNum = value;
            }

            /**
             * Gets the value of the reference property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReference() {
                return reference;
            }

            /**
             * Sets the value of the reference property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReference(String value) {
                this.reference = value;
            }

            /**
             * Gets the value of the unitOfMeasure property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUnitOfMeasure() {
                return unitOfMeasure;
            }

            /**
             * Sets the value of the unitOfMeasure property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUnitOfMeasure(String value) {
                this.unitOfMeasure = value;
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
             * Gets the value of the reasonType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReasonType() {
                return reasonType;
            }

            /**
             * Sets the value of the reasonType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReasonType(String value) {
                this.reasonType = value;
            }

            /**
             * Gets the value of the serialNoQty property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getSerialNoQty() {
                return serialNoQty;
            }

            /**
             * Sets the value of the serialNoQty property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setSerialNoQty(Integer value) {
                this.serialNoQty = value;
            }

            /**
             * Gets the value of the tempSerialNo property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getTempSerialNo() {
                return tempSerialNo;
            }

            /**
             * Sets the value of the tempSerialNo property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setTempSerialNo(Integer value) {
                this.tempSerialNo = value;
            }

            /**
             * Gets the value of the reasonCodeReq property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isReasonCodeReq() {
                return reasonCodeReq;
            }

            /**
             * Sets the value of the reasonCodeReq property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setReasonCodeReq(Boolean value) {
                this.reasonCodeReq = value;
            }

            /**
             * Gets the value of the allowNegQty property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isAllowNegQty() {
                return allowNegQty;
            }

            /**
             * Sets the value of the allowNegQty property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setAllowNegQty(Boolean value) {
                this.allowNegQty = value;
            }

            /**
             * Gets the value of the legalNumberMessage property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLegalNumberMessage() {
                return legalNumberMessage;
            }

            /**
             * Sets the value of the legalNumberMessage property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLegalNumberMessage(String value) {
                this.legalNumberMessage = value;
            }

            /**
             * Gets the value of the stkUOMCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStkUOMCode() {
                return stkUOMCode;
            }

            /**
             * Sets the value of the stkUOMCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStkUOMCode(String value) {
                this.stkUOMCode = value;
            }

            /**
             * Gets the value of the enableSN property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isEnableSN() {
                return enableSN;
            }

            /**
             * Sets the value of the enableSN property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setEnableSN(Boolean value) {
                this.enableSN = value;
            }

            /**
             * Gets the value of the onHandUOM property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOnHandUOM() {
                return onHandUOM;
            }

            /**
             * Sets the value of the onHandUOM property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOnHandUOM(String value) {
                this.onHandUOM = value;
            }

            /**
             * Gets the value of the tranDocTypeID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTranDocTypeID() {
                return tranDocTypeID;
            }

            /**
             * Sets the value of the tranDocTypeID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTranDocTypeID(String value) {
                this.tranDocTypeID = value;
            }

            /**
             * Gets the value of the partTrackSerialNum property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPartTrackSerialNum() {
                return partTrackSerialNum;
            }

            /**
             * Sets the value of the partTrackSerialNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPartTrackSerialNum(Boolean value) {
                this.partTrackSerialNum = value;
            }

            /**
             * Gets the value of the partSellingFactor property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPartSellingFactor() {
                return partSellingFactor;
            }

            /**
             * Sets the value of the partSellingFactor property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPartSellingFactor(BigDecimal value) {
                this.partSellingFactor = value;
            }

            /**
             * Gets the value of the partPricePerCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPartPricePerCode() {
                return partPricePerCode;
            }

            /**
             * Sets the value of the partPricePerCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPartPricePerCode(String value) {
                this.partPricePerCode = value;
            }

            /**
             * Gets the value of the partTrackDimension property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPartTrackDimension() {
                return partTrackDimension;
            }

            /**
             * Sets the value of the partTrackDimension property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPartTrackDimension(Boolean value) {
                this.partTrackDimension = value;
            }

            /**
             * Gets the value of the partIUM property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPartIUM() {
                return partIUM;
            }

            /**
             * Sets the value of the partIUM property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPartIUM(String value) {
                this.partIUM = value;
            }

            /**
             * Gets the value of the partSalesUM property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPartSalesUM() {
                return partSalesUM;
            }

            /**
             * Sets the value of the partSalesUM property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPartSalesUM(String value) {
                this.partSalesUM = value;
            }

            /**
             * Gets the value of the partTrackLots property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPartTrackLots() {
                return partTrackLots;
            }

            /**
             * Sets the value of the partTrackLots property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPartTrackLots(Boolean value) {
                this.partTrackLots = value;
            }

            /**
             * Gets the value of the partPartDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPartPartDescription() {
                return partPartDescription;
            }

            /**
             * Sets the value of the partPartDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPartPartDescription(String value) {
                this.partPartDescription = value;
            }

            /**
             * Gets the value of the reasonCodeDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReasonCodeDescription() {
                return reasonCodeDescription;
            }

            /**
             * Sets the value of the reasonCodeDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReasonCodeDescription(String value) {
                this.reasonCodeDescription = value;
            }

            /**
             * Gets the value of the wareHseDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getWareHseDescription() {
                return wareHseDescription;
            }

            /**
             * Sets the value of the wareHseDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setWareHseDescription(String value) {
                this.wareHseDescription = value;
            }

            /**
             * Gets the value of the whseBinDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getWhseBinDescription() {
                return whseBinDescription;
            }

            /**
             * Sets the value of the whseBinDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setWhseBinDescription(String value) {
                this.whseBinDescription = value;
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
         *         &lt;element name="LegalNumberID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="TransYear" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="TransYearSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DspTransYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShowDspTransYear" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="Prefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PrefixList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NumberSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="EnablePrefix" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="EnableSuffix" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="NumberOption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DocumentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="GenerationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TransPeriod" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="PeriodPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShowTransPeriod" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LegalNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "legalNumberID",
            "transYear",
            "transYearSuffix",
            "dspTransYear",
            "showDspTransYear",
            "prefix",
            "prefixList",
            "numberSuffix",
            "enablePrefix",
            "enableSuffix",
            "numberOption",
            "documentDate",
            "generationType",
            "description",
            "transPeriod",
            "periodPrefix",
            "showTransPeriod",
            "legalNumber",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class LegalNumGenOpts {

            @XmlElement(name = "Company", required = true)
            protected String company;
            @XmlElement(name = "LegalNumberID", required = true)
            protected String legalNumberID;
            @XmlElement(name = "TransYear")
            protected Integer transYear;
            @XmlElement(name = "TransYearSuffix")
            protected String transYearSuffix;
            @XmlElement(name = "DspTransYear")
            protected String dspTransYear;
            @XmlElement(name = "ShowDspTransYear")
            protected Boolean showDspTransYear;
            @XmlElement(name = "Prefix")
            protected String prefix;
            @XmlElement(name = "PrefixList")
            protected String prefixList;
            @XmlElement(name = "NumberSuffix")
            protected String numberSuffix;
            @XmlElement(name = "EnablePrefix")
            protected Boolean enablePrefix;
            @XmlElement(name = "EnableSuffix")
            protected Boolean enableSuffix;
            @XmlElement(name = "NumberOption")
            protected String numberOption;
            @XmlElement(name = "DocumentDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar documentDate;
            @XmlElement(name = "GenerationType")
            protected String generationType;
            @XmlElement(name = "Description")
            protected String description;
            @XmlElement(name = "TransPeriod")
            protected Integer transPeriod;
            @XmlElement(name = "PeriodPrefix")
            protected String periodPrefix;
            @XmlElement(name = "ShowTransPeriod")
            protected Boolean showTransPeriod;
            @XmlElement(name = "LegalNumber")
            protected String legalNumber;
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
             * Gets the value of the legalNumberID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLegalNumberID() {
                return legalNumberID;
            }

            /**
             * Sets the value of the legalNumberID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLegalNumberID(String value) {
                this.legalNumberID = value;
            }

            /**
             * Gets the value of the transYear property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getTransYear() {
                return transYear;
            }

            /**
             * Sets the value of the transYear property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setTransYear(Integer value) {
                this.transYear = value;
            }

            /**
             * Gets the value of the transYearSuffix property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTransYearSuffix() {
                return transYearSuffix;
            }

            /**
             * Sets the value of the transYearSuffix property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTransYearSuffix(String value) {
                this.transYearSuffix = value;
            }

            /**
             * Gets the value of the dspTransYear property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDspTransYear() {
                return dspTransYear;
            }

            /**
             * Sets the value of the dspTransYear property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDspTransYear(String value) {
                this.dspTransYear = value;
            }

            /**
             * Gets the value of the showDspTransYear property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isShowDspTransYear() {
                return showDspTransYear;
            }

            /**
             * Sets the value of the showDspTransYear property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setShowDspTransYear(Boolean value) {
                this.showDspTransYear = value;
            }

            /**
             * Gets the value of the prefix property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPrefix() {
                return prefix;
            }

            /**
             * Sets the value of the prefix property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPrefix(String value) {
                this.prefix = value;
            }

            /**
             * Gets the value of the prefixList property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPrefixList() {
                return prefixList;
            }

            /**
             * Sets the value of the prefixList property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPrefixList(String value) {
                this.prefixList = value;
            }

            /**
             * Gets the value of the numberSuffix property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNumberSuffix() {
                return numberSuffix;
            }

            /**
             * Sets the value of the numberSuffix property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNumberSuffix(String value) {
                this.numberSuffix = value;
            }

            /**
             * Gets the value of the enablePrefix property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isEnablePrefix() {
                return enablePrefix;
            }

            /**
             * Sets the value of the enablePrefix property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setEnablePrefix(Boolean value) {
                this.enablePrefix = value;
            }

            /**
             * Gets the value of the enableSuffix property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isEnableSuffix() {
                return enableSuffix;
            }

            /**
             * Sets the value of the enableSuffix property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setEnableSuffix(Boolean value) {
                this.enableSuffix = value;
            }

            /**
             * Gets the value of the numberOption property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNumberOption() {
                return numberOption;
            }

            /**
             * Sets the value of the numberOption property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNumberOption(String value) {
                this.numberOption = value;
            }

            /**
             * Gets the value of the documentDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDocumentDate() {
                return documentDate;
            }

            /**
             * Sets the value of the documentDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDocumentDate(XMLGregorianCalendar value) {
                this.documentDate = value;
            }

            /**
             * Gets the value of the generationType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGenerationType() {
                return generationType;
            }

            /**
             * Sets the value of the generationType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGenerationType(String value) {
                this.generationType = value;
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
             * Gets the value of the transPeriod property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getTransPeriod() {
                return transPeriod;
            }

            /**
             * Sets the value of the transPeriod property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setTransPeriod(Integer value) {
                this.transPeriod = value;
            }

            /**
             * Gets the value of the periodPrefix property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPeriodPrefix() {
                return periodPrefix;
            }

            /**
             * Sets the value of the periodPrefix property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPeriodPrefix(String value) {
                this.periodPrefix = value;
            }

            /**
             * Gets the value of the showTransPeriod property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isShowTransPeriod() {
                return showTransPeriod;
            }

            /**
             * Sets the value of the showTransPeriod property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setShowTransPeriod(Boolean value) {
                this.showTransPeriod = value;
            }

            /**
             * Gets the value of the legalNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLegalNumber() {
                return legalNumber;
            }

            /**
             * Sets the value of the legalNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLegalNumber(String value) {
                this.legalNumber = value;
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
         *         &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SNFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SNBaseDataType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NumberOfDigits" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="LeadingZeroes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SNPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="HasSerialNumbers" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="Plant" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SNMask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SNMaskSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SNMaskPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SNLastUsedSeq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartPricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartTrackDimension" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PartIUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartSalesUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartTrackLots" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PartPartDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartSellingFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="PartTrackSerialNum" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="SerialMaskMask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SerialMaskDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SerialMaskMaskType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="SerialMaskExample" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "partNum",
            "snFormat",
            "snBaseDataType",
            "numberOfDigits",
            "leadingZeroes",
            "company",
            "snPrefix",
            "hasSerialNumbers",
            "plant",
            "snMask",
            "snMaskSuffix",
            "snMaskPrefix",
            "snLastUsedSeq",
            "sysRowID",
            "partPricePerCode",
            "partTrackDimension",
            "partIUM",
            "partSalesUM",
            "partTrackLots",
            "partPartDescription",
            "partSellingFactor",
            "partTrackSerialNum",
            "serialMaskMask",
            "serialMaskDescription",
            "serialMaskMaskType",
            "serialMaskExample",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class SNFormat {

            @XmlElement(name = "PartNum", required = true)
            protected String partNum;
            @XmlElement(name = "SNFormat")
            protected String snFormat;
            @XmlElement(name = "SNBaseDataType")
            protected String snBaseDataType;
            @XmlElement(name = "NumberOfDigits")
            protected Integer numberOfDigits;
            @XmlElement(name = "LeadingZeroes")
            protected Boolean leadingZeroes;
            @XmlElement(name = "Company", required = true)
            protected String company;
            @XmlElement(name = "SNPrefix")
            protected String snPrefix;
            @XmlElement(name = "HasSerialNumbers")
            protected Boolean hasSerialNumbers;
            @XmlElement(name = "Plant", required = true)
            protected String plant;
            @XmlElement(name = "SNMask")
            protected String snMask;
            @XmlElement(name = "SNMaskSuffix")
            protected String snMaskSuffix;
            @XmlElement(name = "SNMaskPrefix")
            protected String snMaskPrefix;
            @XmlElement(name = "SNLastUsedSeq")
            protected String snLastUsedSeq;
            @XmlElement(name = "SysRowID")
            protected String sysRowID;
            @XmlElement(name = "PartPricePerCode")
            protected String partPricePerCode;
            @XmlElement(name = "PartTrackDimension")
            protected Boolean partTrackDimension;
            @XmlElement(name = "PartIUM")
            protected String partIUM;
            @XmlElement(name = "PartSalesUM")
            protected String partSalesUM;
            @XmlElement(name = "PartTrackLots")
            protected Boolean partTrackLots;
            @XmlElement(name = "PartPartDescription")
            protected String partPartDescription;
            @XmlElement(name = "PartSellingFactor")
            protected BigDecimal partSellingFactor;
            @XmlElement(name = "PartTrackSerialNum")
            protected Boolean partTrackSerialNum;
            @XmlElement(name = "SerialMaskMask")
            protected String serialMaskMask;
            @XmlElement(name = "SerialMaskDescription")
            protected String serialMaskDescription;
            @XmlElement(name = "SerialMaskMaskType")
            protected Integer serialMaskMaskType;
            @XmlElement(name = "SerialMaskExample")
            protected String serialMaskExample;
            @XmlElement(name = "RowIdent")
            protected String rowIdent;
            @XmlElement(name = "RowMod")
            protected String rowMod;
            @XmlElement(name = "DBRowIdent")
            protected byte[] dbRowIdent;

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
             * Gets the value of the snFormat property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSNFormat() {
                return snFormat;
            }

            /**
             * Sets the value of the snFormat property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSNFormat(String value) {
                this.snFormat = value;
            }

            /**
             * Gets the value of the snBaseDataType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSNBaseDataType() {
                return snBaseDataType;
            }

            /**
             * Sets the value of the snBaseDataType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSNBaseDataType(String value) {
                this.snBaseDataType = value;
            }

            /**
             * Gets the value of the numberOfDigits property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getNumberOfDigits() {
                return numberOfDigits;
            }

            /**
             * Sets the value of the numberOfDigits property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setNumberOfDigits(Integer value) {
                this.numberOfDigits = value;
            }

            /**
             * Gets the value of the leadingZeroes property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isLeadingZeroes() {
                return leadingZeroes;
            }

            /**
             * Sets the value of the leadingZeroes property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLeadingZeroes(Boolean value) {
                this.leadingZeroes = value;
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
             * Gets the value of the snPrefix property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSNPrefix() {
                return snPrefix;
            }

            /**
             * Sets the value of the snPrefix property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSNPrefix(String value) {
                this.snPrefix = value;
            }

            /**
             * Gets the value of the hasSerialNumbers property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isHasSerialNumbers() {
                return hasSerialNumbers;
            }

            /**
             * Sets the value of the hasSerialNumbers property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setHasSerialNumbers(Boolean value) {
                this.hasSerialNumbers = value;
            }

            /**
             * Gets the value of the plant property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPlant() {
                return plant;
            }

            /**
             * Sets the value of the plant property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPlant(String value) {
                this.plant = value;
            }

            /**
             * Gets the value of the snMask property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSNMask() {
                return snMask;
            }

            /**
             * Sets the value of the snMask property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSNMask(String value) {
                this.snMask = value;
            }

            /**
             * Gets the value of the snMaskSuffix property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSNMaskSuffix() {
                return snMaskSuffix;
            }

            /**
             * Sets the value of the snMaskSuffix property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSNMaskSuffix(String value) {
                this.snMaskSuffix = value;
            }

            /**
             * Gets the value of the snMaskPrefix property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSNMaskPrefix() {
                return snMaskPrefix;
            }

            /**
             * Sets the value of the snMaskPrefix property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSNMaskPrefix(String value) {
                this.snMaskPrefix = value;
            }

            /**
             * Gets the value of the snLastUsedSeq property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSNLastUsedSeq() {
                return snLastUsedSeq;
            }

            /**
             * Sets the value of the snLastUsedSeq property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSNLastUsedSeq(String value) {
                this.snLastUsedSeq = value;
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
             * Gets the value of the partPricePerCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPartPricePerCode() {
                return partPricePerCode;
            }

            /**
             * Sets the value of the partPricePerCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPartPricePerCode(String value) {
                this.partPricePerCode = value;
            }

            /**
             * Gets the value of the partTrackDimension property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPartTrackDimension() {
                return partTrackDimension;
            }

            /**
             * Sets the value of the partTrackDimension property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPartTrackDimension(Boolean value) {
                this.partTrackDimension = value;
            }

            /**
             * Gets the value of the partIUM property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPartIUM() {
                return partIUM;
            }

            /**
             * Sets the value of the partIUM property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPartIUM(String value) {
                this.partIUM = value;
            }

            /**
             * Gets the value of the partSalesUM property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPartSalesUM() {
                return partSalesUM;
            }

            /**
             * Sets the value of the partSalesUM property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPartSalesUM(String value) {
                this.partSalesUM = value;
            }

            /**
             * Gets the value of the partTrackLots property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPartTrackLots() {
                return partTrackLots;
            }

            /**
             * Sets the value of the partTrackLots property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPartTrackLots(Boolean value) {
                this.partTrackLots = value;
            }

            /**
             * Gets the value of the partPartDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPartPartDescription() {
                return partPartDescription;
            }

            /**
             * Sets the value of the partPartDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPartPartDescription(String value) {
                this.partPartDescription = value;
            }

            /**
             * Gets the value of the partSellingFactor property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPartSellingFactor() {
                return partSellingFactor;
            }

            /**
             * Sets the value of the partSellingFactor property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPartSellingFactor(BigDecimal value) {
                this.partSellingFactor = value;
            }

            /**
             * Gets the value of the partTrackSerialNum property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPartTrackSerialNum() {
                return partTrackSerialNum;
            }

            /**
             * Sets the value of the partTrackSerialNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPartTrackSerialNum(Boolean value) {
                this.partTrackSerialNum = value;
            }

            /**
             * Gets the value of the serialMaskMask property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSerialMaskMask() {
                return serialMaskMask;
            }

            /**
             * Sets the value of the serialMaskMask property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSerialMaskMask(String value) {
                this.serialMaskMask = value;
            }

            /**
             * Gets the value of the serialMaskDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSerialMaskDescription() {
                return serialMaskDescription;
            }

            /**
             * Sets the value of the serialMaskDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSerialMaskDescription(String value) {
                this.serialMaskDescription = value;
            }

            /**
             * Gets the value of the serialMaskMaskType property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getSerialMaskMaskType() {
                return serialMaskMaskType;
            }

            /**
             * Sets the value of the serialMaskMaskType property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setSerialMaskMaskType(Integer value) {
                this.serialMaskMaskType = value;
            }

            /**
             * Gets the value of the serialMaskExample property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSerialMaskExample() {
                return serialMaskExample;
            }

            /**
             * Sets the value of the serialMaskExample property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSerialMaskExample(String value) {
                this.serialMaskExample = value;
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
         *         &lt;element name="SerialNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Scrapped" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ScrappedReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Voided" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="Reference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ReasonCodeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ReasonCodeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SNPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SNBaseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SourceRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TransType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PassedInspection" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="Deselected" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="KitWhseList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="RawSerialNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="KBLbrAction" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="KBLbrActionDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PreventDeselect" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="XRefPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="XRefPartType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "serialNumber",
            "scrapped",
            "scrappedReasonCode",
            "voided",
            "reference",
            "reasonCodeType",
            "reasonCodeDesc",
            "partNum",
            "snPrefix",
            "snBaseNumber",
            "sourceRowID",
            "transType",
            "passedInspection",
            "deselected",
            "kitWhseList",
            "rawSerialNum",
            "kbLbrAction",
            "kbLbrActionDesc",
            "preventDeselect",
            "xRefPartNum",
            "xRefPartType",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class SelectedSerialNumbers {

            @XmlElement(name = "Company", required = true)
            protected String company;
            @XmlElement(name = "SerialNumber", required = true)
            protected String serialNumber;
            @XmlElement(name = "Scrapped")
            protected Boolean scrapped;
            @XmlElement(name = "ScrappedReasonCode")
            protected String scrappedReasonCode;
            @XmlElement(name = "Voided")
            protected Boolean voided;
            @XmlElement(name = "Reference")
            protected String reference;
            @XmlElement(name = "ReasonCodeType")
            protected String reasonCodeType;
            @XmlElement(name = "ReasonCodeDesc")
            protected String reasonCodeDesc;
            @XmlElement(name = "PartNum", required = true)
            protected String partNum;
            @XmlElement(name = "SNPrefix")
            protected String snPrefix;
            @XmlElement(name = "SNBaseNumber")
            protected String snBaseNumber;
            @XmlElement(name = "SourceRowID")
            protected String sourceRowID;
            @XmlElement(name = "TransType")
            protected String transType;
            @XmlElement(name = "PassedInspection")
            protected Boolean passedInspection;
            @XmlElement(name = "Deselected")
            protected Boolean deselected;
            @XmlElement(name = "KitWhseList")
            protected String kitWhseList;
            @XmlElement(name = "RawSerialNum")
            protected String rawSerialNum;
            @XmlElement(name = "KBLbrAction")
            protected Integer kbLbrAction;
            @XmlElement(name = "KBLbrActionDesc")
            protected String kbLbrActionDesc;
            @XmlElement(name = "PreventDeselect")
            protected Boolean preventDeselect;
            @XmlElement(name = "XRefPartNum")
            protected String xRefPartNum;
            @XmlElement(name = "XRefPartType")
            protected String xRefPartType;
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
             * Gets the value of the serialNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSerialNumber() {
                return serialNumber;
            }

            /**
             * Sets the value of the serialNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSerialNumber(String value) {
                this.serialNumber = value;
            }

            /**
             * Gets the value of the scrapped property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isScrapped() {
                return scrapped;
            }

            /**
             * Sets the value of the scrapped property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setScrapped(Boolean value) {
                this.scrapped = value;
            }

            /**
             * Gets the value of the scrappedReasonCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getScrappedReasonCode() {
                return scrappedReasonCode;
            }

            /**
             * Sets the value of the scrappedReasonCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setScrappedReasonCode(String value) {
                this.scrappedReasonCode = value;
            }

            /**
             * Gets the value of the voided property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isVoided() {
                return voided;
            }

            /**
             * Sets the value of the voided property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setVoided(Boolean value) {
                this.voided = value;
            }

            /**
             * Gets the value of the reference property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReference() {
                return reference;
            }

            /**
             * Sets the value of the reference property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReference(String value) {
                this.reference = value;
            }

            /**
             * Gets the value of the reasonCodeType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReasonCodeType() {
                return reasonCodeType;
            }

            /**
             * Sets the value of the reasonCodeType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReasonCodeType(String value) {
                this.reasonCodeType = value;
            }

            /**
             * Gets the value of the reasonCodeDesc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReasonCodeDesc() {
                return reasonCodeDesc;
            }

            /**
             * Sets the value of the reasonCodeDesc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReasonCodeDesc(String value) {
                this.reasonCodeDesc = value;
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
             * Gets the value of the snPrefix property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSNPrefix() {
                return snPrefix;
            }

            /**
             * Sets the value of the snPrefix property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSNPrefix(String value) {
                this.snPrefix = value;
            }

            /**
             * Gets the value of the snBaseNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSNBaseNumber() {
                return snBaseNumber;
            }

            /**
             * Sets the value of the snBaseNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSNBaseNumber(String value) {
                this.snBaseNumber = value;
            }

            /**
             * Gets the value of the sourceRowID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSourceRowID() {
                return sourceRowID;
            }

            /**
             * Sets the value of the sourceRowID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSourceRowID(String value) {
                this.sourceRowID = value;
            }

            /**
             * Gets the value of the transType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTransType() {
                return transType;
            }

            /**
             * Sets the value of the transType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTransType(String value) {
                this.transType = value;
            }

            /**
             * Gets the value of the passedInspection property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPassedInspection() {
                return passedInspection;
            }

            /**
             * Sets the value of the passedInspection property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPassedInspection(Boolean value) {
                this.passedInspection = value;
            }

            /**
             * Gets the value of the deselected property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDeselected() {
                return deselected;
            }

            /**
             * Sets the value of the deselected property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDeselected(Boolean value) {
                this.deselected = value;
            }

            /**
             * Gets the value of the kitWhseList property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKitWhseList() {
                return kitWhseList;
            }

            /**
             * Sets the value of the kitWhseList property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKitWhseList(String value) {
                this.kitWhseList = value;
            }

            /**
             * Gets the value of the rawSerialNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRawSerialNum() {
                return rawSerialNum;
            }

            /**
             * Sets the value of the rawSerialNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRawSerialNum(String value) {
                this.rawSerialNum = value;
            }

            /**
             * Gets the value of the kbLbrAction property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getKBLbrAction() {
                return kbLbrAction;
            }

            /**
             * Sets the value of the kbLbrAction property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setKBLbrAction(Integer value) {
                this.kbLbrAction = value;
            }

            /**
             * Gets the value of the kbLbrActionDesc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKBLbrActionDesc() {
                return kbLbrActionDesc;
            }

            /**
             * Sets the value of the kbLbrActionDesc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKBLbrActionDesc(String value) {
                this.kbLbrActionDesc = value;
            }

            /**
             * Gets the value of the preventDeselect property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPreventDeselect() {
                return preventDeselect;
            }

            /**
             * Sets the value of the preventDeselect property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPreventDeselect(Boolean value) {
                this.preventDeselect = value;
            }

            /**
             * Gets the value of the xRefPartNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getXRefPartNum() {
                return xRefPartNum;
            }

            /**
             * Sets the value of the xRefPartNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setXRefPartNum(String value) {
                this.xRefPartNum = value;
            }

            /**
             * Gets the value of the xRefPartType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getXRefPartType() {
                return xRefPartType;
            }

            /**
             * Sets the value of the xRefPartType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setXRefPartType(String value) {
                this.xRefPartType = value;
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
