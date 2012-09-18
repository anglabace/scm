
package com.genscript.gsscm.epicorwebservice.stub.lot;

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
 * <p>Java class for PartLotListDataSetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartLotListDataSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PartLotListDataSet">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="PartLotList">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="LotNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="PartLotDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OnHand" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="FirstRefDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="LastRefDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="LotLaborCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="LotBurdenCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="LotMaterialCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="LotSubContCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="LotMtlBurCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
 *                             &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
 *                             &lt;element name="ShipDocAvail" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="Batch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="MfgBatch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="MfgLot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="HeatNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FirmWare" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BestBeforeDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="MfgDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="CureDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="ExpireDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="FIFOLotLaborCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="FIFOLotBurdenCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="FIFOLotMaterialCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="FIFOLotSubContCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="FIFOLotMtlBurCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="CSFLMWComOath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CSFCJ5FormNbr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CSFCJ5Vessel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CSFCJ5ApvlStart" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="CSFCJ5ApvlEnd" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="ImportNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ImportedFrom" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="ImportedFromDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TransactionSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ScrLotBurdenCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="ScrLotLaborCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="ScrLotMaterialCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="ScrLotMtlBurCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="ScrLotSubContCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Plant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OnHandQty" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="CSFCJ5Avail" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CSFLMWAvail" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PartNumPricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartNumTrackDimension" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PartNumIUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartNumSalesUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartNumTrackLots" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PartNumPartDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartNumSellingFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="PartNumTrackSerialNum" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
@XmlType(name = "PartLotListDataSetType", namespace = "http://epicor.com/schemas", propOrder = {
    "partLotListDataSet"
})
public class PartLotListDataSetType {

    @XmlElement(name = "PartLotListDataSet", required = true)
    protected PartLotListDataSetType.PartLotListDataSet partLotListDataSet;

    /**
     * Gets the value of the partLotListDataSet property.
     * 
     * @return
     *     possible object is
     *     {@link PartLotListDataSetType.PartLotListDataSet }
     *     
     */
    public PartLotListDataSetType.PartLotListDataSet getPartLotListDataSet() {
        return partLotListDataSet;
    }

    /**
     * Sets the value of the partLotListDataSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartLotListDataSetType.PartLotListDataSet }
     *     
     */
    public void setPartLotListDataSet(PartLotListDataSetType.PartLotListDataSet value) {
        this.partLotListDataSet = value;
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
     *         &lt;element name="PartLotList">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="LotNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="PartLotDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OnHand" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="FirstRefDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="LastRefDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="LotLaborCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="LotBurdenCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="LotMaterialCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="LotSubContCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="LotMtlBurCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
     *                   &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
     *                   &lt;element name="ShipDocAvail" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="Batch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="MfgBatch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="MfgLot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="HeatNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FirmWare" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BestBeforeDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="MfgDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="CureDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="ExpireDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="FIFOLotLaborCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="FIFOLotBurdenCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="FIFOLotMaterialCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="FIFOLotSubContCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="FIFOLotMtlBurCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="CSFLMWComOath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CSFCJ5FormNbr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CSFCJ5Vessel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CSFCJ5ApvlStart" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="CSFCJ5ApvlEnd" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="ImportNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ImportedFrom" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="ImportedFromDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TransactionSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ScrLotBurdenCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="ScrLotLaborCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="ScrLotMaterialCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="ScrLotMtlBurCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="ScrLotSubContCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Plant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OnHandQty" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="CSFCJ5Avail" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CSFLMWAvail" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PartNumPricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartNumTrackDimension" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PartNumIUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartNumSalesUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartNumTrackLots" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PartNumPartDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartNumSellingFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="PartNumTrackSerialNum" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
        "partLotListOrWebServiceErrors"
    })
    public static class PartLotListDataSet {

        @XmlElements({
            @XmlElement(name = "PartLotList", type = PartLotListDataSetType.PartLotListDataSet.PartLotList.class),
            @XmlElement(name = "WebServiceErrors", type = PartLotListDataSetType.PartLotListDataSet.WebServiceErrors.class)
        })
        protected List<Object> partLotListOrWebServiceErrors;

        /**
         * Gets the value of the partLotListOrWebServiceErrors property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the partLotListOrWebServiceErrors property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPartLotListOrWebServiceErrors().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PartLotListDataSetType.PartLotListDataSet.PartLotList }
         * {@link PartLotListDataSetType.PartLotListDataSet.WebServiceErrors }
         * 
         * 
         */
        public List<Object> getPartLotListOrWebServiceErrors() {
            if (partLotListOrWebServiceErrors == null) {
                partLotListOrWebServiceErrors = new ArrayList<Object>();
            }
            return this.partLotListOrWebServiceErrors;
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
         *         &lt;element name="LotNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="PartLotDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OnHand" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="FirstRefDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="LastRefDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="LotLaborCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="LotBurdenCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="LotMaterialCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="LotSubContCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="LotMtlBurCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
         *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
         *         &lt;element name="ShipDocAvail" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="Batch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="MfgBatch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="MfgLot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="HeatNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FirmWare" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BestBeforeDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="MfgDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="CureDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="ExpireDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="FIFOLotLaborCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="FIFOLotBurdenCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="FIFOLotMaterialCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="FIFOLotSubContCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="FIFOLotMtlBurCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="CSFLMWComOath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CSFCJ5FormNbr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CSFCJ5Vessel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CSFCJ5ApvlStart" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="CSFCJ5ApvlEnd" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="ImportNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ImportedFrom" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="ImportedFromDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TransactionSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ScrLotBurdenCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="ScrLotLaborCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="ScrLotMaterialCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="ScrLotMtlBurCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="ScrLotSubContCost" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Plant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OnHandQty" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="CSFCJ5Avail" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CSFLMWAvail" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PartNumPricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartNumTrackDimension" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PartNumIUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartNumSalesUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartNumTrackLots" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PartNumPartDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartNumSellingFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="PartNumTrackSerialNum" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
            "lotNum",
            "partLotDescription",
            "onHand",
            "firstRefDate",
            "lastRefDate",
            "lotLaborCost",
            "lotBurdenCost",
            "lotMaterialCost",
            "lotSubContCost",
            "lotMtlBurCost",
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
            "expirationDate",
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
            "shipDocAvail",
            "batch",
            "mfgBatch",
            "mfgLot",
            "heatNum",
            "firmWare",
            "bestBeforeDt",
            "mfgDt",
            "cureDt",
            "expireDt",
            "fifoLotLaborCost",
            "fifoLotBurdenCost",
            "fifoLotMaterialCost",
            "fifoLotSubContCost",
            "fifoLotMtlBurCost",
            "sysRowID",
            "sysRevID",
            "bitFlag",
            "csflmwComOath",
            "csfcj5FormNbr",
            "csfcj5Vessel",
            "csfcj5ApvlStart",
            "csfcj5ApvlEnd",
            "importNum",
            "importedFrom",
            "importedFromDesc",
            "transactionSource",
            "scrLotBurdenCost",
            "scrLotLaborCost",
            "scrLotMaterialCost",
            "scrLotMtlBurCost",
            "scrLotSubContCost",
            "plant",
            "onHandQty",
            "csfcj5Avail",
            "csflmwAvail",
            "partNumPricePerCode",
            "partNumTrackDimension",
            "partNumIUM",
            "partNumSalesUM",
            "partNumTrackLots",
            "partNumPartDescription",
            "partNumSellingFactor",
            "partNumTrackSerialNum",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class PartLotList {

            @XmlElement(name = "Company")
            protected String company;
            @XmlElement(name = "PartNum", required = true)
            protected String partNum;
            @XmlElement(name = "LotNum", required = true)
            protected String lotNum;
            @XmlElement(name = "PartLotDescription")
            protected String partLotDescription;
            @XmlElement(name = "OnHand")
            protected Boolean onHand;
            @XmlElement(name = "FirstRefDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar firstRefDate;
            @XmlElement(name = "LastRefDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar lastRefDate;
            @XmlElement(name = "LotLaborCost")
            protected BigDecimal lotLaborCost;
            @XmlElement(name = "LotBurdenCost")
            protected BigDecimal lotBurdenCost;
            @XmlElement(name = "LotMaterialCost")
            protected BigDecimal lotMaterialCost;
            @XmlElement(name = "LotSubContCost")
            protected BigDecimal lotSubContCost;
            @XmlElement(name = "LotMtlBurCost")
            protected BigDecimal lotMtlBurCost;
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
            @XmlElement(name = "ExpirationDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar expirationDate;
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
            @XmlElement(name = "ShipDocAvail")
            protected Boolean shipDocAvail;
            @XmlElement(name = "Batch")
            protected String batch;
            @XmlElement(name = "MfgBatch")
            protected String mfgBatch;
            @XmlElement(name = "MfgLot")
            protected String mfgLot;
            @XmlElement(name = "HeatNum")
            protected String heatNum;
            @XmlElement(name = "FirmWare")
            protected String firmWare;
            @XmlElement(name = "BestBeforeDt")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar bestBeforeDt;
            @XmlElement(name = "MfgDt")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar mfgDt;
            @XmlElement(name = "CureDt")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar cureDt;
            @XmlElement(name = "ExpireDt")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar expireDt;
            @XmlElement(name = "FIFOLotLaborCost")
            protected BigDecimal fifoLotLaborCost;
            @XmlElement(name = "FIFOLotBurdenCost")
            protected BigDecimal fifoLotBurdenCost;
            @XmlElement(name = "FIFOLotMaterialCost")
            protected BigDecimal fifoLotMaterialCost;
            @XmlElement(name = "FIFOLotSubContCost")
            protected BigDecimal fifoLotSubContCost;
            @XmlElement(name = "FIFOLotMtlBurCost")
            protected BigDecimal fifoLotMtlBurCost;
            @XmlElement(name = "SysRowID")
            protected String sysRowID;
            @XmlElement(name = "SysRevID")
            protected Integer sysRevID;
            @XmlElement(name = "BitFlag")
            protected Integer bitFlag;
            @XmlElement(name = "CSFLMWComOath")
            protected String csflmwComOath;
            @XmlElement(name = "CSFCJ5FormNbr")
            protected String csfcj5FormNbr;
            @XmlElement(name = "CSFCJ5Vessel")
            protected String csfcj5Vessel;
            @XmlElement(name = "CSFCJ5ApvlStart")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar csfcj5ApvlStart;
            @XmlElement(name = "CSFCJ5ApvlEnd")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar csfcj5ApvlEnd;
            @XmlElement(name = "ImportNum")
            protected String importNum;
            @XmlElement(name = "ImportedFrom")
            protected Integer importedFrom;
            @XmlElement(name = "ImportedFromDesc")
            protected String importedFromDesc;
            @XmlElement(name = "TransactionSource")
            protected String transactionSource;
            @XmlElement(name = "ScrLotBurdenCost")
            protected BigDecimal scrLotBurdenCost;
            @XmlElement(name = "ScrLotLaborCost")
            protected BigDecimal scrLotLaborCost;
            @XmlElement(name = "ScrLotMaterialCost")
            protected BigDecimal scrLotMaterialCost;
            @XmlElement(name = "ScrLotMtlBurCost")
            protected BigDecimal scrLotMtlBurCost;
            @XmlElement(name = "ScrLotSubContCost")
            protected BigDecimal scrLotSubContCost;
            @XmlElement(name = "Plant")
            protected String plant;
            @XmlElement(name = "OnHandQty")
            protected BigDecimal onHandQty;
            @XmlElement(name = "CSFCJ5Avail")
            protected Boolean csfcj5Avail;
            @XmlElement(name = "CSFLMWAvail")
            protected Boolean csflmwAvail;
            @XmlElement(name = "PartNumPricePerCode")
            protected String partNumPricePerCode;
            @XmlElement(name = "PartNumTrackDimension")
            protected Boolean partNumTrackDimension;
            @XmlElement(name = "PartNumIUM")
            protected String partNumIUM;
            @XmlElement(name = "PartNumSalesUM")
            protected String partNumSalesUM;
            @XmlElement(name = "PartNumTrackLots")
            protected Boolean partNumTrackLots;
            @XmlElement(name = "PartNumPartDescription")
            protected String partNumPartDescription;
            @XmlElement(name = "PartNumSellingFactor")
            protected BigDecimal partNumSellingFactor;
            @XmlElement(name = "PartNumTrackSerialNum")
            protected Boolean partNumTrackSerialNum;
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
             * Gets the value of the partLotDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPartLotDescription() {
                return partLotDescription;
            }

            /**
             * Sets the value of the partLotDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPartLotDescription(String value) {
                this.partLotDescription = value;
            }

            /**
             * Gets the value of the onHand property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOnHand() {
                return onHand;
            }

            /**
             * Sets the value of the onHand property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOnHand(Boolean value) {
                this.onHand = value;
            }

            /**
             * Gets the value of the firstRefDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getFirstRefDate() {
                return firstRefDate;
            }

            /**
             * Sets the value of the firstRefDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setFirstRefDate(XMLGregorianCalendar value) {
                this.firstRefDate = value;
            }

            /**
             * Gets the value of the lastRefDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getLastRefDate() {
                return lastRefDate;
            }

            /**
             * Sets the value of the lastRefDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setLastRefDate(XMLGregorianCalendar value) {
                this.lastRefDate = value;
            }

            /**
             * Gets the value of the lotLaborCost property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getLotLaborCost() {
                return lotLaborCost;
            }

            /**
             * Sets the value of the lotLaborCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setLotLaborCost(BigDecimal value) {
                this.lotLaborCost = value;
            }

            /**
             * Gets the value of the lotBurdenCost property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getLotBurdenCost() {
                return lotBurdenCost;
            }

            /**
             * Sets the value of the lotBurdenCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setLotBurdenCost(BigDecimal value) {
                this.lotBurdenCost = value;
            }

            /**
             * Gets the value of the lotMaterialCost property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getLotMaterialCost() {
                return lotMaterialCost;
            }

            /**
             * Sets the value of the lotMaterialCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setLotMaterialCost(BigDecimal value) {
                this.lotMaterialCost = value;
            }

            /**
             * Gets the value of the lotSubContCost property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getLotSubContCost() {
                return lotSubContCost;
            }

            /**
             * Sets the value of the lotSubContCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setLotSubContCost(BigDecimal value) {
                this.lotSubContCost = value;
            }

            /**
             * Gets the value of the lotMtlBurCost property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getLotMtlBurCost() {
                return lotMtlBurCost;
            }

            /**
             * Sets the value of the lotMtlBurCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setLotMtlBurCost(BigDecimal value) {
                this.lotMtlBurCost = value;
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
             * Gets the value of the expirationDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getExpirationDate() {
                return expirationDate;
            }

            /**
             * Sets the value of the expirationDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setExpirationDate(XMLGregorianCalendar value) {
                this.expirationDate = value;
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
             * Gets the value of the shipDocAvail property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isShipDocAvail() {
                return shipDocAvail;
            }

            /**
             * Sets the value of the shipDocAvail property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setShipDocAvail(Boolean value) {
                this.shipDocAvail = value;
            }

            /**
             * Gets the value of the batch property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBatch() {
                return batch;
            }

            /**
             * Sets the value of the batch property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBatch(String value) {
                this.batch = value;
            }

            /**
             * Gets the value of the mfgBatch property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMfgBatch() {
                return mfgBatch;
            }

            /**
             * Sets the value of the mfgBatch property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMfgBatch(String value) {
                this.mfgBatch = value;
            }

            /**
             * Gets the value of the mfgLot property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMfgLot() {
                return mfgLot;
            }

            /**
             * Sets the value of the mfgLot property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMfgLot(String value) {
                this.mfgLot = value;
            }

            /**
             * Gets the value of the heatNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHeatNum() {
                return heatNum;
            }

            /**
             * Sets the value of the heatNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHeatNum(String value) {
                this.heatNum = value;
            }

            /**
             * Gets the value of the firmWare property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFirmWare() {
                return firmWare;
            }

            /**
             * Sets the value of the firmWare property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFirmWare(String value) {
                this.firmWare = value;
            }

            /**
             * Gets the value of the bestBeforeDt property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getBestBeforeDt() {
                return bestBeforeDt;
            }

            /**
             * Sets the value of the bestBeforeDt property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setBestBeforeDt(XMLGregorianCalendar value) {
                this.bestBeforeDt = value;
            }

            /**
             * Gets the value of the mfgDt property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getMfgDt() {
                return mfgDt;
            }

            /**
             * Sets the value of the mfgDt property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setMfgDt(XMLGregorianCalendar value) {
                this.mfgDt = value;
            }

            /**
             * Gets the value of the cureDt property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getCureDt() {
                return cureDt;
            }

            /**
             * Sets the value of the cureDt property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setCureDt(XMLGregorianCalendar value) {
                this.cureDt = value;
            }

            /**
             * Gets the value of the expireDt property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getExpireDt() {
                return expireDt;
            }

            /**
             * Sets the value of the expireDt property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setExpireDt(XMLGregorianCalendar value) {
                this.expireDt = value;
            }

            /**
             * Gets the value of the fifoLotLaborCost property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getFIFOLotLaborCost() {
                return fifoLotLaborCost;
            }

            /**
             * Sets the value of the fifoLotLaborCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setFIFOLotLaborCost(BigDecimal value) {
                this.fifoLotLaborCost = value;
            }

            /**
             * Gets the value of the fifoLotBurdenCost property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getFIFOLotBurdenCost() {
                return fifoLotBurdenCost;
            }

            /**
             * Sets the value of the fifoLotBurdenCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setFIFOLotBurdenCost(BigDecimal value) {
                this.fifoLotBurdenCost = value;
            }

            /**
             * Gets the value of the fifoLotMaterialCost property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getFIFOLotMaterialCost() {
                return fifoLotMaterialCost;
            }

            /**
             * Sets the value of the fifoLotMaterialCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setFIFOLotMaterialCost(BigDecimal value) {
                this.fifoLotMaterialCost = value;
            }

            /**
             * Gets the value of the fifoLotSubContCost property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getFIFOLotSubContCost() {
                return fifoLotSubContCost;
            }

            /**
             * Sets the value of the fifoLotSubContCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setFIFOLotSubContCost(BigDecimal value) {
                this.fifoLotSubContCost = value;
            }

            /**
             * Gets the value of the fifoLotMtlBurCost property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getFIFOLotMtlBurCost() {
                return fifoLotMtlBurCost;
            }

            /**
             * Sets the value of the fifoLotMtlBurCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setFIFOLotMtlBurCost(BigDecimal value) {
                this.fifoLotMtlBurCost = value;
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
             * Gets the value of the csflmwComOath property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCSFLMWComOath() {
                return csflmwComOath;
            }

            /**
             * Sets the value of the csflmwComOath property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCSFLMWComOath(String value) {
                this.csflmwComOath = value;
            }

            /**
             * Gets the value of the csfcj5FormNbr property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCSFCJ5FormNbr() {
                return csfcj5FormNbr;
            }

            /**
             * Sets the value of the csfcj5FormNbr property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCSFCJ5FormNbr(String value) {
                this.csfcj5FormNbr = value;
            }

            /**
             * Gets the value of the csfcj5Vessel property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCSFCJ5Vessel() {
                return csfcj5Vessel;
            }

            /**
             * Sets the value of the csfcj5Vessel property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCSFCJ5Vessel(String value) {
                this.csfcj5Vessel = value;
            }

            /**
             * Gets the value of the csfcj5ApvlStart property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getCSFCJ5ApvlStart() {
                return csfcj5ApvlStart;
            }

            /**
             * Sets the value of the csfcj5ApvlStart property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setCSFCJ5ApvlStart(XMLGregorianCalendar value) {
                this.csfcj5ApvlStart = value;
            }

            /**
             * Gets the value of the csfcj5ApvlEnd property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getCSFCJ5ApvlEnd() {
                return csfcj5ApvlEnd;
            }

            /**
             * Sets the value of the csfcj5ApvlEnd property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setCSFCJ5ApvlEnd(XMLGregorianCalendar value) {
                this.csfcj5ApvlEnd = value;
            }

            /**
             * Gets the value of the importNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getImportNum() {
                return importNum;
            }

            /**
             * Sets the value of the importNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setImportNum(String value) {
                this.importNum = value;
            }

            /**
             * Gets the value of the importedFrom property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getImportedFrom() {
                return importedFrom;
            }

            /**
             * Sets the value of the importedFrom property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setImportedFrom(Integer value) {
                this.importedFrom = value;
            }

            /**
             * Gets the value of the importedFromDesc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getImportedFromDesc() {
                return importedFromDesc;
            }

            /**
             * Sets the value of the importedFromDesc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setImportedFromDesc(String value) {
                this.importedFromDesc = value;
            }

            /**
             * Gets the value of the transactionSource property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTransactionSource() {
                return transactionSource;
            }

            /**
             * Sets the value of the transactionSource property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTransactionSource(String value) {
                this.transactionSource = value;
            }

            /**
             * Gets the value of the scrLotBurdenCost property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getScrLotBurdenCost() {
                return scrLotBurdenCost;
            }

            /**
             * Sets the value of the scrLotBurdenCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setScrLotBurdenCost(BigDecimal value) {
                this.scrLotBurdenCost = value;
            }

            /**
             * Gets the value of the scrLotLaborCost property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getScrLotLaborCost() {
                return scrLotLaborCost;
            }

            /**
             * Sets the value of the scrLotLaborCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setScrLotLaborCost(BigDecimal value) {
                this.scrLotLaborCost = value;
            }

            /**
             * Gets the value of the scrLotMaterialCost property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getScrLotMaterialCost() {
                return scrLotMaterialCost;
            }

            /**
             * Sets the value of the scrLotMaterialCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setScrLotMaterialCost(BigDecimal value) {
                this.scrLotMaterialCost = value;
            }

            /**
             * Gets the value of the scrLotMtlBurCost property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getScrLotMtlBurCost() {
                return scrLotMtlBurCost;
            }

            /**
             * Sets the value of the scrLotMtlBurCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setScrLotMtlBurCost(BigDecimal value) {
                this.scrLotMtlBurCost = value;
            }

            /**
             * Gets the value of the scrLotSubContCost property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getScrLotSubContCost() {
                return scrLotSubContCost;
            }

            /**
             * Sets the value of the scrLotSubContCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setScrLotSubContCost(BigDecimal value) {
                this.scrLotSubContCost = value;
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
             * Gets the value of the csfcj5Avail property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCSFCJ5Avail() {
                return csfcj5Avail;
            }

            /**
             * Sets the value of the csfcj5Avail property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCSFCJ5Avail(Boolean value) {
                this.csfcj5Avail = value;
            }

            /**
             * Gets the value of the csflmwAvail property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCSFLMWAvail() {
                return csflmwAvail;
            }

            /**
             * Sets the value of the csflmwAvail property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCSFLMWAvail(Boolean value) {
                this.csflmwAvail = value;
            }

            /**
             * Gets the value of the partNumPricePerCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPartNumPricePerCode() {
                return partNumPricePerCode;
            }

            /**
             * Sets the value of the partNumPricePerCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPartNumPricePerCode(String value) {
                this.partNumPricePerCode = value;
            }

            /**
             * Gets the value of the partNumTrackDimension property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPartNumTrackDimension() {
                return partNumTrackDimension;
            }

            /**
             * Sets the value of the partNumTrackDimension property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPartNumTrackDimension(Boolean value) {
                this.partNumTrackDimension = value;
            }

            /**
             * Gets the value of the partNumIUM property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPartNumIUM() {
                return partNumIUM;
            }

            /**
             * Sets the value of the partNumIUM property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPartNumIUM(String value) {
                this.partNumIUM = value;
            }

            /**
             * Gets the value of the partNumSalesUM property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPartNumSalesUM() {
                return partNumSalesUM;
            }

            /**
             * Sets the value of the partNumSalesUM property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPartNumSalesUM(String value) {
                this.partNumSalesUM = value;
            }

            /**
             * Gets the value of the partNumTrackLots property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPartNumTrackLots() {
                return partNumTrackLots;
            }

            /**
             * Sets the value of the partNumTrackLots property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPartNumTrackLots(Boolean value) {
                this.partNumTrackLots = value;
            }

            /**
             * Gets the value of the partNumPartDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPartNumPartDescription() {
                return partNumPartDescription;
            }

            /**
             * Sets the value of the partNumPartDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPartNumPartDescription(String value) {
                this.partNumPartDescription = value;
            }

            /**
             * Gets the value of the partNumSellingFactor property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPartNumSellingFactor() {
                return partNumSellingFactor;
            }

            /**
             * Sets the value of the partNumSellingFactor property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPartNumSellingFactor(BigDecimal value) {
                this.partNumSellingFactor = value;
            }

            /**
             * Gets the value of the partNumTrackSerialNum property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPartNumTrackSerialNum() {
                return partNumTrackSerialNum;
            }

            /**
             * Sets the value of the partNumTrackSerialNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPartNumTrackSerialNum(Boolean value) {
                this.partNumTrackSerialNum = value;
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
