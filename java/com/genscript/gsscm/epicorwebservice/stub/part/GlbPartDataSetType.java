
package com.genscript.gsscm.epicorwebservice.stub.part;

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
 * <p>Java class for GlbPartDataSetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GlbPartDataSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GlbPartDataSet">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="GlbPart">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SearchWord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PartDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ClassID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="IUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NonStock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PurchasingFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="UnitPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="PricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="InternalUnitPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="InternalPricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ProdCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="MfgComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PurComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CostMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UserChar1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UserChar2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UserChar3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UserChar4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UserDate1" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="UserDate2" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="UserDate3" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="UserDate4" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="UserDecimal1" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="UserDecimal2" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="UserDecimal3" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="UserDecimal4" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="UserInteger1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="UserInteger2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="TaxCatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="InActive" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LowLevelCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="Method" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="TrackLots" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
 *                             &lt;element name="TrackDimension" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DefaultDim" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TrackSerialNum" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CommodityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="WarrantyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PhantomBOM" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="SalesUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SellingFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="MtlBurRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="NetWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="UsePartRev" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PartsPerContainer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="PartLength" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="PartWidth" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="PartHeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="LotShelfLife" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="WebPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="RunOut" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="SubPart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Diameter" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Gravity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="OnHold" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="OnHoldDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="OnHoldReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AnalysisCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="GlbCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="GlbPartNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Mtl-AnalysisCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="GlobalPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="GlobalLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ISSuppUnitsFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="OldCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OldPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PDMObjID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SNPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ImageFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SNFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SNBaseDataType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ISOrigCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Constrained" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="UPCCode1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UPCCode2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UPCCode3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="EDICode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *                             &lt;element name="ConsolidatedPurchasing" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="WebInStock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="PurchasingFactorDirection" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SellingFactorDirection" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="MDPV" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="ReturnableContainer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NetVolume" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="RecDocReq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="ShipDocReq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="QtyBearing" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="AESExp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ECCNNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ExpLicNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ExpLicType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="HazClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="HazGvrnmtID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="HazItem" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="HazPackInstr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="HazSub" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="HazTechName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="HTS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NAFTAOrigCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NAFTAPref" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NAFTAProd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OrigCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SchedBcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UseHTSDesc" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="OwnershipStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RCOverThreshold" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="RCUnderThreshold" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="RevChargeMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UOMClassID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SNMask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SNMaskExample" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SNMaskPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SNMaskSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LotAppendDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LotBatch" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LotBeforeDt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LotCureDt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LotDigits" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="LotExpDt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LotFirmware" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LotHeat" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LotLeadingZeros" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LotMfgBatch" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LotMfgDt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LotMfgLot" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="LotNxtNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="LotPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LotSeqID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LotUseGlobalSeq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="NetVolumeUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NetWeightUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SNLastUsedSeq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UseMaskSeq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="BuyToOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DropShip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="ExtConfig" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="IsConfigured" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="RefCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="InspPlanType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CSFCJ5" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CSFLMW" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="GrossWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="GrossWeightUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BasePartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FSAssetClassCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FSPricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FSSalesUnitPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="RcvInspectionReq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="DispPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LinkPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TypeCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ProdCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="StockPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
@XmlType(name = "GlbPartDataSetType", namespace = "http://epicor.com/schemas", propOrder = {
    "glbPartDataSet"
})
public class GlbPartDataSetType {

    @XmlElement(name = "GlbPartDataSet", required = true)
    protected GlbPartDataSetType.GlbPartDataSet glbPartDataSet;

    /**
     * Gets the value of the glbPartDataSet property.
     * 
     * @return
     *     possible object is
     *     {@link GlbPartDataSetType.GlbPartDataSet }
     *     
     */
    public GlbPartDataSetType.GlbPartDataSet getGlbPartDataSet() {
        return glbPartDataSet;
    }

    /**
     * Sets the value of the glbPartDataSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlbPartDataSetType.GlbPartDataSet }
     *     
     */
    public void setGlbPartDataSet(GlbPartDataSetType.GlbPartDataSet value) {
        this.glbPartDataSet = value;
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
     *         &lt;element name="GlbPart">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SearchWord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PartDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ClassID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="IUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NonStock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PurchasingFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="UnitPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="PricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="InternalUnitPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="InternalPricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ProdCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="MfgComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PurComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CostMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UserChar1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UserChar2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UserChar3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UserChar4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UserDate1" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="UserDate2" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="UserDate3" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="UserDate4" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="UserDecimal1" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="UserDecimal2" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="UserDecimal3" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="UserDecimal4" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="UserInteger1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="UserInteger2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="TaxCatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="InActive" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LowLevelCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="Method" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="TrackLots" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
     *                   &lt;element name="TrackDimension" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DefaultDim" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TrackSerialNum" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CommodityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="WarrantyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PhantomBOM" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="SalesUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SellingFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="MtlBurRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="NetWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="UsePartRev" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PartsPerContainer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="PartLength" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="PartWidth" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="PartHeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="LotShelfLife" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="WebPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="RunOut" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="SubPart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Diameter" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Gravity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="OnHold" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="OnHoldDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="OnHoldReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AnalysisCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="GlbCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="GlbPartNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Mtl-AnalysisCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="GlobalPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="GlobalLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ISSuppUnitsFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="OldCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OldPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PDMObjID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SNPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ImageFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SNFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SNBaseDataType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ISOrigCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Constrained" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="UPCCode1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UPCCode2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UPCCode3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="EDICode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
     *                   &lt;element name="ConsolidatedPurchasing" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="WebInStock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="PurchasingFactorDirection" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SellingFactorDirection" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="MDPV" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="ReturnableContainer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NetVolume" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="RecDocReq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="ShipDocReq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="QtyBearing" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="AESExp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ECCNNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ExpLicNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ExpLicType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="HazClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="HazGvrnmtID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="HazItem" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="HazPackInstr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="HazSub" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="HazTechName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="HTS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NAFTAOrigCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NAFTAPref" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NAFTAProd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OrigCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SchedBcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UseHTSDesc" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="OwnershipStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RCOverThreshold" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="RCUnderThreshold" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="RevChargeMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UOMClassID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SNMask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SNMaskExample" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SNMaskPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SNMaskSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="LotAppendDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="LotBatch" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LotBeforeDt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LotCureDt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LotDigits" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="LotExpDt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LotFirmware" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LotHeat" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LotLeadingZeros" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LotMfgBatch" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LotMfgDt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LotMfgLot" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="LotNxtNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="LotPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="LotSeqID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="LotUseGlobalSeq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="NetVolumeUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NetWeightUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SNLastUsedSeq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UseMaskSeq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="BuyToOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DropShip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="ExtConfig" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="IsConfigured" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="RefCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="InspPlanType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CSFCJ5" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CSFLMW" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="GrossWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="GrossWeightUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BasePartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FSAssetClassCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FSPricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="FSSalesUnitPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="RcvInspectionReq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="DispPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="LinkPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TypeCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ProdCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="StockPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
        "glbPartOrWebServiceErrors"
    })
    public static class GlbPartDataSet {

        @XmlElements({
            @XmlElement(name = "GlbPart", type = GlbPartDataSetType.GlbPartDataSet.GlbPart.class),
            @XmlElement(name = "WebServiceErrors", type = GlbPartDataSetType.GlbPartDataSet.WebServiceErrors.class)
        })
        protected List<Object> glbPartOrWebServiceErrors;

        /**
         * Gets the value of the glbPartOrWebServiceErrors property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the glbPartOrWebServiceErrors property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getGlbPartOrWebServiceErrors().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GlbPartDataSetType.GlbPartDataSet.GlbPart }
         * {@link GlbPartDataSetType.GlbPartDataSet.WebServiceErrors }
         * 
         * 
         */
        public List<Object> getGlbPartOrWebServiceErrors() {
            if (glbPartOrWebServiceErrors == null) {
                glbPartOrWebServiceErrors = new ArrayList<Object>();
            }
            return this.glbPartOrWebServiceErrors;
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
         *         &lt;element name="PartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SearchWord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PartDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ClassID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="IUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NonStock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PurchasingFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="UnitPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="PricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="InternalUnitPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="InternalPricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ProdCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="MfgComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PurComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CostMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UserChar1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UserChar2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UserChar3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UserChar4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UserDate1" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="UserDate2" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="UserDate3" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="UserDate4" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="UserDecimal1" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="UserDecimal2" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="UserDecimal3" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="UserDecimal4" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="UserInteger1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="UserInteger2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="TaxCatID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="InActive" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LowLevelCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="Method" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="TrackLots" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
         *         &lt;element name="TrackDimension" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DefaultDim" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TrackSerialNum" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CommodityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="WarrantyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PhantomBOM" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="SalesUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SellingFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="MtlBurRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="NetWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="UsePartRev" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PartsPerContainer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="PartLength" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="PartWidth" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="PartHeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="LotShelfLife" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="WebPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="RunOut" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="SubPart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Diameter" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Gravity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="OnHold" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="OnHoldDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="OnHoldReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AnalysisCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="GlbCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="GlbPartNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Mtl-AnalysisCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="GlobalPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="GlobalLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ISSuppUnitsFactor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="OldCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OldPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PDMObjID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SNPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ImageFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SNFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SNBaseDataType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ISOrigCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Constrained" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="UPCCode1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UPCCode2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UPCCode3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="EDICode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
         *         &lt;element name="ConsolidatedPurchasing" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="WebInStock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="PurchasingFactorDirection" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SellingFactorDirection" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="MDPV" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="ReturnableContainer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NetVolume" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="RecDocReq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="ShipDocReq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="QtyBearing" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="AESExp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ECCNNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ExpLicNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ExpLicType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="HazClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="HazGvrnmtID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="HazItem" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="HazPackInstr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="HazSub" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="HazTechName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="HTS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NAFTAOrigCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NAFTAPref" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NAFTAProd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OrigCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SchedBcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UseHTSDesc" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="OwnershipStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="RCOverThreshold" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="RCUnderThreshold" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="RevChargeMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UOMClassID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SNMask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SNMaskExample" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SNMaskPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SNMaskSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="LotAppendDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="LotBatch" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LotBeforeDt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LotCureDt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LotDigits" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="LotExpDt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LotFirmware" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LotHeat" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LotLeadingZeros" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LotMfgBatch" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LotMfgDt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LotMfgLot" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="LotNxtNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="LotPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="LotSeqID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="LotUseGlobalSeq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="NetVolumeUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NetWeightUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SNLastUsedSeq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UseMaskSeq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="BuyToOrder" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DropShip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="ExtConfig" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="IsConfigured" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="RefCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="InspPlanType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CSFCJ5" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CSFLMW" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="GrossWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="GrossWeightUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BasePartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FSAssetClassCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FSPricePerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="FSSalesUnitPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="RcvInspectionReq" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="DispPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="LinkPartNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TypeCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ProdCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="StockPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
            "searchWord",
            "partDescription",
            "classID",
            "ium",
            "pum",
            "typeCode",
            "nonStock",
            "purchasingFactor",
            "unitPrice",
            "pricePerCode",
            "internalUnitPrice",
            "internalPricePerCode",
            "prodCode",
            "mfgComment",
            "purComment",
            "costMethod",
            "userChar1",
            "userChar2",
            "userChar3",
            "userChar4",
            "userDate1",
            "userDate2",
            "userDate3",
            "userDate4",
            "userDecimal1",
            "userDecimal2",
            "userDecimal3",
            "userDecimal4",
            "userInteger1",
            "userInteger2",
            "taxCatID",
            "inActive",
            "lowLevelCode",
            "method",
            "trackLots",
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
            "trackDimension",
            "defaultDim",
            "trackSerialNum",
            "commodityCode",
            "warrantyCode",
            "phantomBOM",
            "salesUM",
            "sellingFactor",
            "mtlBurRate",
            "netWeight",
            "usePartRev",
            "partsPerContainer",
            "partLength",
            "partWidth",
            "partHeight",
            "lotShelfLife",
            "webPart",
            "runOut",
            "subPart",
            "diameter",
            "gravity",
            "onHold",
            "onHoldDate",
            "onHoldReasonCode",
            "analysisCode",
            "glbCompany",
            "glbPartNum",
            "mtlAnalysisCode",
            "globalPart",
            "globalLock",
            "isSuppUnitsFactor",
            "oldCompany",
            "oldPartNum",
            "pdmObjID",
            "snPrefix",
            "imageFileName",
            "snFormat",
            "snBaseDataType",
            "isOrigCountry",
            "constrained",
            "upcCode1",
            "upcCode2",
            "upcCode3",
            "ediCode",
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
            "consolidatedPurchasing",
            "webInStock",
            "purchasingFactorDirection",
            "sellingFactorDirection",
            "mdpv",
            "returnableContainer",
            "netVolume",
            "recDocReq",
            "shipDocReq",
            "qtyBearing",
            "aesExp",
            "eccnNumber",
            "expLicNumber",
            "expLicType",
            "hazClass",
            "hazGvrnmtID",
            "hazItem",
            "hazPackInstr",
            "hazSub",
            "hazTechName",
            "hts",
            "naftaOrigCountry",
            "naftaPref",
            "naftaProd",
            "origCountry",
            "schedBcode",
            "useHTSDesc",
            "ownershipStatus",
            "rcOverThreshold",
            "rcUnderThreshold",
            "revChargeMethod",
            "uomClassID",
            "snMask",
            "snMaskExample",
            "snMaskPrefix",
            "snMaskSuffix",
            "lotAppendDate",
            "lotBatch",
            "lotBeforeDt",
            "lotCureDt",
            "lotDigits",
            "lotExpDt",
            "lotFirmware",
            "lotHeat",
            "lotLeadingZeros",
            "lotMfgBatch",
            "lotMfgDt",
            "lotMfgLot",
            "lotNxtNum",
            "lotPrefix",
            "lotSeqID",
            "lotUseGlobalSeq",
            "netVolumeUOM",
            "netWeightUOM",
            "snLastUsedSeq",
            "useMaskSeq",
            "sysRowID",
            "sysRevID",
            "buyToOrder",
            "dropShip",
            "bitFlag",
            "extConfig",
            "isConfigured",
            "refCategory",
            "inspPlanType",
            "csfcj5",
            "csflmw",
            "grossWeight",
            "grossWeightUOM",
            "basePartNum",
            "fsAssetClassCode",
            "fsPricePerCode",
            "fsSalesUnitPrice",
            "rcvInspectionReq",
            "dispPartNum",
            "linkPartNum",
            "typeCodeDescription",
            "prodCodeDescription",
            "stockPart",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class GlbPart {

            @XmlElement(name = "Company", required = true)
            protected String company;
            @XmlElement(name = "PartNum")
            protected String partNum;
            @XmlElement(name = "SearchWord")
            protected String searchWord;
            @XmlElement(name = "PartDescription")
            protected String partDescription;
            @XmlElement(name = "ClassID")
            protected String classID;
            @XmlElement(name = "IUM")
            protected String ium;
            @XmlElement(name = "PUM")
            protected String pum;
            @XmlElement(name = "TypeCode")
            protected String typeCode;
            @XmlElement(name = "NonStock")
            protected Boolean nonStock;
            @XmlElement(name = "PurchasingFactor")
            protected BigDecimal purchasingFactor;
            @XmlElement(name = "UnitPrice")
            protected BigDecimal unitPrice;
            @XmlElement(name = "PricePerCode")
            protected String pricePerCode;
            @XmlElement(name = "InternalUnitPrice")
            protected BigDecimal internalUnitPrice;
            @XmlElement(name = "InternalPricePerCode")
            protected String internalPricePerCode;
            @XmlElement(name = "ProdCode")
            protected String prodCode;
            @XmlElement(name = "MfgComment")
            protected String mfgComment;
            @XmlElement(name = "PurComment")
            protected String purComment;
            @XmlElement(name = "CostMethod")
            protected String costMethod;
            @XmlElement(name = "UserChar1")
            protected String userChar1;
            @XmlElement(name = "UserChar2")
            protected String userChar2;
            @XmlElement(name = "UserChar3")
            protected String userChar3;
            @XmlElement(name = "UserChar4")
            protected String userChar4;
            @XmlElement(name = "UserDate1")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar userDate1;
            @XmlElement(name = "UserDate2")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar userDate2;
            @XmlElement(name = "UserDate3")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar userDate3;
            @XmlElement(name = "UserDate4")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar userDate4;
            @XmlElement(name = "UserDecimal1")
            protected BigDecimal userDecimal1;
            @XmlElement(name = "UserDecimal2")
            protected BigDecimal userDecimal2;
            @XmlElement(name = "UserDecimal3")
            protected BigDecimal userDecimal3;
            @XmlElement(name = "UserDecimal4")
            protected BigDecimal userDecimal4;
            @XmlElement(name = "UserInteger1")
            protected Integer userInteger1;
            @XmlElement(name = "UserInteger2")
            protected Integer userInteger2;
            @XmlElement(name = "TaxCatID")
            protected String taxCatID;
            @XmlElement(name = "InActive")
            protected Boolean inActive;
            @XmlElement(name = "LowLevelCode")
            protected Integer lowLevelCode;
            @XmlElement(name = "Method")
            protected Boolean method;
            @XmlElement(name = "TrackLots")
            protected Boolean trackLots;
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
            @XmlElement(name = "TrackDimension")
            protected Boolean trackDimension;
            @XmlElement(name = "DefaultDim")
            protected String defaultDim;
            @XmlElement(name = "TrackSerialNum")
            protected Boolean trackSerialNum;
            @XmlElement(name = "CommodityCode")
            protected String commodityCode;
            @XmlElement(name = "WarrantyCode")
            protected String warrantyCode;
            @XmlElement(name = "PhantomBOM")
            protected Boolean phantomBOM;
            @XmlElement(name = "SalesUM")
            protected String salesUM;
            @XmlElement(name = "SellingFactor")
            protected BigDecimal sellingFactor;
            @XmlElement(name = "MtlBurRate")
            protected BigDecimal mtlBurRate;
            @XmlElement(name = "NetWeight")
            protected BigDecimal netWeight;
            @XmlElement(name = "UsePartRev")
            protected Boolean usePartRev;
            @XmlElement(name = "PartsPerContainer")
            protected Integer partsPerContainer;
            @XmlElement(name = "PartLength")
            protected BigDecimal partLength;
            @XmlElement(name = "PartWidth")
            protected BigDecimal partWidth;
            @XmlElement(name = "PartHeight")
            protected BigDecimal partHeight;
            @XmlElement(name = "LotShelfLife")
            protected Integer lotShelfLife;
            @XmlElement(name = "WebPart")
            protected Boolean webPart;
            @XmlElement(name = "RunOut")
            protected Boolean runOut;
            @XmlElement(name = "SubPart")
            protected String subPart;
            @XmlElement(name = "Diameter")
            protected BigDecimal diameter;
            @XmlElement(name = "Gravity")
            protected BigDecimal gravity;
            @XmlElement(name = "OnHold")
            protected Boolean onHold;
            @XmlElement(name = "OnHoldDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar onHoldDate;
            @XmlElement(name = "OnHoldReasonCode")
            protected String onHoldReasonCode;
            @XmlElement(name = "AnalysisCode")
            protected String analysisCode;
            @XmlElement(name = "GlbCompany", required = true)
            protected String glbCompany;
            @XmlElement(name = "GlbPartNum", required = true)
            protected String glbPartNum;
            @XmlElement(name = "Mtl-AnalysisCode")
            protected String mtlAnalysisCode;
            @XmlElement(name = "GlobalPart")
            protected Boolean globalPart;
            @XmlElement(name = "GlobalLock")
            protected Boolean globalLock;
            @XmlElement(name = "ISSuppUnitsFactor")
            protected BigDecimal isSuppUnitsFactor;
            @XmlElement(name = "OldCompany")
            protected String oldCompany;
            @XmlElement(name = "OldPartNum")
            protected String oldPartNum;
            @XmlElement(name = "PDMObjID")
            protected String pdmObjID;
            @XmlElement(name = "SNPrefix")
            protected String snPrefix;
            @XmlElement(name = "ImageFileName")
            protected String imageFileName;
            @XmlElement(name = "SNFormat")
            protected String snFormat;
            @XmlElement(name = "SNBaseDataType")
            protected String snBaseDataType;
            @XmlElement(name = "ISOrigCountry")
            protected String isOrigCountry;
            @XmlElement(name = "Constrained")
            protected Boolean constrained;
            @XmlElement(name = "UPCCode1")
            protected String upcCode1;
            @XmlElement(name = "UPCCode2")
            protected String upcCode2;
            @XmlElement(name = "UPCCode3")
            protected String upcCode3;
            @XmlElement(name = "EDICode")
            protected String ediCode;
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
            @XmlElement(name = "ConsolidatedPurchasing")
            protected Boolean consolidatedPurchasing;
            @XmlElement(name = "WebInStock")
            protected Boolean webInStock;
            @XmlElement(name = "PurchasingFactorDirection")
            protected String purchasingFactorDirection;
            @XmlElement(name = "SellingFactorDirection")
            protected String sellingFactorDirection;
            @XmlElement(name = "MDPV")
            protected Integer mdpv;
            @XmlElement(name = "ReturnableContainer")
            protected String returnableContainer;
            @XmlElement(name = "NetVolume")
            protected BigDecimal netVolume;
            @XmlElement(name = "RecDocReq")
            protected Boolean recDocReq;
            @XmlElement(name = "ShipDocReq")
            protected Boolean shipDocReq;
            @XmlElement(name = "QtyBearing")
            protected Boolean qtyBearing;
            @XmlElement(name = "AESExp")
            protected String aesExp;
            @XmlElement(name = "ECCNNumber")
            protected String eccnNumber;
            @XmlElement(name = "ExpLicNumber")
            protected String expLicNumber;
            @XmlElement(name = "ExpLicType")
            protected String expLicType;
            @XmlElement(name = "HazClass")
            protected String hazClass;
            @XmlElement(name = "HazGvrnmtID")
            protected String hazGvrnmtID;
            @XmlElement(name = "HazItem")
            protected Boolean hazItem;
            @XmlElement(name = "HazPackInstr")
            protected String hazPackInstr;
            @XmlElement(name = "HazSub")
            protected String hazSub;
            @XmlElement(name = "HazTechName")
            protected String hazTechName;
            @XmlElement(name = "HTS")
            protected String hts;
            @XmlElement(name = "NAFTAOrigCountry")
            protected String naftaOrigCountry;
            @XmlElement(name = "NAFTAPref")
            protected String naftaPref;
            @XmlElement(name = "NAFTAProd")
            protected String naftaProd;
            @XmlElement(name = "OrigCountry")
            protected String origCountry;
            @XmlElement(name = "SchedBcode")
            protected String schedBcode;
            @XmlElement(name = "UseHTSDesc")
            protected Boolean useHTSDesc;
            @XmlElement(name = "OwnershipStatus")
            protected String ownershipStatus;
            @XmlElement(name = "RCOverThreshold")
            protected BigDecimal rcOverThreshold;
            @XmlElement(name = "RCUnderThreshold")
            protected BigDecimal rcUnderThreshold;
            @XmlElement(name = "RevChargeMethod")
            protected String revChargeMethod;
            @XmlElement(name = "UOMClassID")
            protected String uomClassID;
            @XmlElement(name = "SNMask")
            protected String snMask;
            @XmlElement(name = "SNMaskExample")
            protected String snMaskExample;
            @XmlElement(name = "SNMaskPrefix")
            protected String snMaskPrefix;
            @XmlElement(name = "SNMaskSuffix")
            protected String snMaskSuffix;
            @XmlElement(name = "LotAppendDate")
            protected String lotAppendDate;
            @XmlElement(name = "LotBatch")
            protected Boolean lotBatch;
            @XmlElement(name = "LotBeforeDt")
            protected Boolean lotBeforeDt;
            @XmlElement(name = "LotCureDt")
            protected Boolean lotCureDt;
            @XmlElement(name = "LotDigits")
            protected Integer lotDigits;
            @XmlElement(name = "LotExpDt")
            protected Boolean lotExpDt;
            @XmlElement(name = "LotFirmware")
            protected Boolean lotFirmware;
            @XmlElement(name = "LotHeat")
            protected Boolean lotHeat;
            @XmlElement(name = "LotLeadingZeros")
            protected Boolean lotLeadingZeros;
            @XmlElement(name = "LotMfgBatch")
            protected Boolean lotMfgBatch;
            @XmlElement(name = "LotMfgDt")
            protected Boolean lotMfgDt;
            @XmlElement(name = "LotMfgLot")
            protected Boolean lotMfgLot;
            @XmlElement(name = "LotNxtNum")
            protected Integer lotNxtNum;
            @XmlElement(name = "LotPrefix")
            protected String lotPrefix;
            @XmlElement(name = "LotSeqID")
            protected String lotSeqID;
            @XmlElement(name = "LotUseGlobalSeq")
            protected Boolean lotUseGlobalSeq;
            @XmlElement(name = "NetVolumeUOM")
            protected String netVolumeUOM;
            @XmlElement(name = "NetWeightUOM")
            protected String netWeightUOM;
            @XmlElement(name = "SNLastUsedSeq")
            protected String snLastUsedSeq;
            @XmlElement(name = "UseMaskSeq")
            protected Boolean useMaskSeq;
            @XmlElement(name = "SysRowID")
            protected String sysRowID;
            @XmlElement(name = "SysRevID")
            protected Integer sysRevID;
            @XmlElement(name = "BuyToOrder")
            protected Boolean buyToOrder;
            @XmlElement(name = "DropShip")
            protected Boolean dropShip;
            @XmlElement(name = "BitFlag")
            protected Integer bitFlag;
            @XmlElement(name = "ExtConfig")
            protected Boolean extConfig;
            @XmlElement(name = "IsConfigured")
            protected Boolean isConfigured;
            @XmlElement(name = "RefCategory")
            protected String refCategory;
            @XmlElement(name = "InspPlanType")
            protected String inspPlanType;
            @XmlElement(name = "CSFCJ5")
            protected Boolean csfcj5;
            @XmlElement(name = "CSFLMW")
            protected Boolean csflmw;
            @XmlElement(name = "GrossWeight")
            protected BigDecimal grossWeight;
            @XmlElement(name = "GrossWeightUOM")
            protected String grossWeightUOM;
            @XmlElement(name = "BasePartNum")
            protected String basePartNum;
            @XmlElement(name = "FSAssetClassCode")
            protected String fsAssetClassCode;
            @XmlElement(name = "FSPricePerCode")
            protected String fsPricePerCode;
            @XmlElement(name = "FSSalesUnitPrice")
            protected BigDecimal fsSalesUnitPrice;
            @XmlElement(name = "RcvInspectionReq")
            protected Boolean rcvInspectionReq;
            @XmlElement(name = "DispPartNum")
            protected String dispPartNum;
            @XmlElement(name = "LinkPartNum")
            protected String linkPartNum;
            @XmlElement(name = "TypeCodeDescription")
            protected String typeCodeDescription;
            @XmlElement(name = "ProdCodeDescription")
            protected String prodCodeDescription;
            @XmlElement(name = "StockPart")
            protected Boolean stockPart;
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
             * Gets the value of the searchWord property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSearchWord() {
                return searchWord;
            }

            /**
             * Sets the value of the searchWord property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSearchWord(String value) {
                this.searchWord = value;
            }

            /**
             * Gets the value of the partDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPartDescription() {
                return partDescription;
            }

            /**
             * Sets the value of the partDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPartDescription(String value) {
                this.partDescription = value;
            }

            /**
             * Gets the value of the classID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getClassID() {
                return classID;
            }

            /**
             * Sets the value of the classID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setClassID(String value) {
                this.classID = value;
            }

            /**
             * Gets the value of the ium property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIUM() {
                return ium;
            }

            /**
             * Sets the value of the ium property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIUM(String value) {
                this.ium = value;
            }

            /**
             * Gets the value of the pum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPUM() {
                return pum;
            }

            /**
             * Sets the value of the pum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPUM(String value) {
                this.pum = value;
            }

            /**
             * Gets the value of the typeCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTypeCode() {
                return typeCode;
            }

            /**
             * Sets the value of the typeCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTypeCode(String value) {
                this.typeCode = value;
            }

            /**
             * Gets the value of the nonStock property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isNonStock() {
                return nonStock;
            }

            /**
             * Sets the value of the nonStock property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setNonStock(Boolean value) {
                this.nonStock = value;
            }

            /**
             * Gets the value of the purchasingFactor property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPurchasingFactor() {
                return purchasingFactor;
            }

            /**
             * Sets the value of the purchasingFactor property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPurchasingFactor(BigDecimal value) {
                this.purchasingFactor = value;
            }

            /**
             * Gets the value of the unitPrice property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getUnitPrice() {
                return unitPrice;
            }

            /**
             * Sets the value of the unitPrice property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setUnitPrice(BigDecimal value) {
                this.unitPrice = value;
            }

            /**
             * Gets the value of the pricePerCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPricePerCode() {
                return pricePerCode;
            }

            /**
             * Sets the value of the pricePerCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPricePerCode(String value) {
                this.pricePerCode = value;
            }

            /**
             * Gets the value of the internalUnitPrice property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getInternalUnitPrice() {
                return internalUnitPrice;
            }

            /**
             * Sets the value of the internalUnitPrice property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setInternalUnitPrice(BigDecimal value) {
                this.internalUnitPrice = value;
            }

            /**
             * Gets the value of the internalPricePerCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getInternalPricePerCode() {
                return internalPricePerCode;
            }

            /**
             * Sets the value of the internalPricePerCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setInternalPricePerCode(String value) {
                this.internalPricePerCode = value;
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
             * Gets the value of the mfgComment property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMfgComment() {
                return mfgComment;
            }

            /**
             * Sets the value of the mfgComment property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMfgComment(String value) {
                this.mfgComment = value;
            }

            /**
             * Gets the value of the purComment property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPurComment() {
                return purComment;
            }

            /**
             * Sets the value of the purComment property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPurComment(String value) {
                this.purComment = value;
            }

            /**
             * Gets the value of the costMethod property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCostMethod() {
                return costMethod;
            }

            /**
             * Sets the value of the costMethod property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCostMethod(String value) {
                this.costMethod = value;
            }

            /**
             * Gets the value of the userChar1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUserChar1() {
                return userChar1;
            }

            /**
             * Sets the value of the userChar1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUserChar1(String value) {
                this.userChar1 = value;
            }

            /**
             * Gets the value of the userChar2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUserChar2() {
                return userChar2;
            }

            /**
             * Sets the value of the userChar2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUserChar2(String value) {
                this.userChar2 = value;
            }

            /**
             * Gets the value of the userChar3 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUserChar3() {
                return userChar3;
            }

            /**
             * Sets the value of the userChar3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUserChar3(String value) {
                this.userChar3 = value;
            }

            /**
             * Gets the value of the userChar4 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUserChar4() {
                return userChar4;
            }

            /**
             * Sets the value of the userChar4 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUserChar4(String value) {
                this.userChar4 = value;
            }

            /**
             * Gets the value of the userDate1 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getUserDate1() {
                return userDate1;
            }

            /**
             * Sets the value of the userDate1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setUserDate1(XMLGregorianCalendar value) {
                this.userDate1 = value;
            }

            /**
             * Gets the value of the userDate2 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getUserDate2() {
                return userDate2;
            }

            /**
             * Sets the value of the userDate2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setUserDate2(XMLGregorianCalendar value) {
                this.userDate2 = value;
            }

            /**
             * Gets the value of the userDate3 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getUserDate3() {
                return userDate3;
            }

            /**
             * Sets the value of the userDate3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setUserDate3(XMLGregorianCalendar value) {
                this.userDate3 = value;
            }

            /**
             * Gets the value of the userDate4 property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getUserDate4() {
                return userDate4;
            }

            /**
             * Sets the value of the userDate4 property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setUserDate4(XMLGregorianCalendar value) {
                this.userDate4 = value;
            }

            /**
             * Gets the value of the userDecimal1 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getUserDecimal1() {
                return userDecimal1;
            }

            /**
             * Sets the value of the userDecimal1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setUserDecimal1(BigDecimal value) {
                this.userDecimal1 = value;
            }

            /**
             * Gets the value of the userDecimal2 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getUserDecimal2() {
                return userDecimal2;
            }

            /**
             * Sets the value of the userDecimal2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setUserDecimal2(BigDecimal value) {
                this.userDecimal2 = value;
            }

            /**
             * Gets the value of the userDecimal3 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getUserDecimal3() {
                return userDecimal3;
            }

            /**
             * Sets the value of the userDecimal3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setUserDecimal3(BigDecimal value) {
                this.userDecimal3 = value;
            }

            /**
             * Gets the value of the userDecimal4 property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getUserDecimal4() {
                return userDecimal4;
            }

            /**
             * Sets the value of the userDecimal4 property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setUserDecimal4(BigDecimal value) {
                this.userDecimal4 = value;
            }

            /**
             * Gets the value of the userInteger1 property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getUserInteger1() {
                return userInteger1;
            }

            /**
             * Sets the value of the userInteger1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setUserInteger1(Integer value) {
                this.userInteger1 = value;
            }

            /**
             * Gets the value of the userInteger2 property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getUserInteger2() {
                return userInteger2;
            }

            /**
             * Sets the value of the userInteger2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setUserInteger2(Integer value) {
                this.userInteger2 = value;
            }

            /**
             * Gets the value of the taxCatID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTaxCatID() {
                return taxCatID;
            }

            /**
             * Sets the value of the taxCatID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTaxCatID(String value) {
                this.taxCatID = value;
            }

            /**
             * Gets the value of the inActive property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isInActive() {
                return inActive;
            }

            /**
             * Sets the value of the inActive property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setInActive(Boolean value) {
                this.inActive = value;
            }

            /**
             * Gets the value of the lowLevelCode property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getLowLevelCode() {
                return lowLevelCode;
            }

            /**
             * Sets the value of the lowLevelCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setLowLevelCode(Integer value) {
                this.lowLevelCode = value;
            }

            /**
             * Gets the value of the method property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isMethod() {
                return method;
            }

            /**
             * Sets the value of the method property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setMethod(Boolean value) {
                this.method = value;
            }

            /**
             * Gets the value of the trackLots property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isTrackLots() {
                return trackLots;
            }

            /**
             * Sets the value of the trackLots property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setTrackLots(Boolean value) {
                this.trackLots = value;
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
             * Gets the value of the trackDimension property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isTrackDimension() {
                return trackDimension;
            }

            /**
             * Sets the value of the trackDimension property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setTrackDimension(Boolean value) {
                this.trackDimension = value;
            }

            /**
             * Gets the value of the defaultDim property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDefaultDim() {
                return defaultDim;
            }

            /**
             * Sets the value of the defaultDim property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDefaultDim(String value) {
                this.defaultDim = value;
            }

            /**
             * Gets the value of the trackSerialNum property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isTrackSerialNum() {
                return trackSerialNum;
            }

            /**
             * Sets the value of the trackSerialNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setTrackSerialNum(Boolean value) {
                this.trackSerialNum = value;
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
             * Gets the value of the warrantyCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getWarrantyCode() {
                return warrantyCode;
            }

            /**
             * Sets the value of the warrantyCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setWarrantyCode(String value) {
                this.warrantyCode = value;
            }

            /**
             * Gets the value of the phantomBOM property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isPhantomBOM() {
                return phantomBOM;
            }

            /**
             * Sets the value of the phantomBOM property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setPhantomBOM(Boolean value) {
                this.phantomBOM = value;
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
             * Gets the value of the sellingFactor property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getSellingFactor() {
                return sellingFactor;
            }

            /**
             * Sets the value of the sellingFactor property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setSellingFactor(BigDecimal value) {
                this.sellingFactor = value;
            }

            /**
             * Gets the value of the mtlBurRate property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getMtlBurRate() {
                return mtlBurRate;
            }

            /**
             * Sets the value of the mtlBurRate property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setMtlBurRate(BigDecimal value) {
                this.mtlBurRate = value;
            }

            /**
             * Gets the value of the netWeight property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNetWeight() {
                return netWeight;
            }

            /**
             * Sets the value of the netWeight property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNetWeight(BigDecimal value) {
                this.netWeight = value;
            }

            /**
             * Gets the value of the usePartRev property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isUsePartRev() {
                return usePartRev;
            }

            /**
             * Sets the value of the usePartRev property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setUsePartRev(Boolean value) {
                this.usePartRev = value;
            }

            /**
             * Gets the value of the partsPerContainer property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getPartsPerContainer() {
                return partsPerContainer;
            }

            /**
             * Sets the value of the partsPerContainer property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setPartsPerContainer(Integer value) {
                this.partsPerContainer = value;
            }

            /**
             * Gets the value of the partLength property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPartLength() {
                return partLength;
            }

            /**
             * Sets the value of the partLength property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPartLength(BigDecimal value) {
                this.partLength = value;
            }

            /**
             * Gets the value of the partWidth property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPartWidth() {
                return partWidth;
            }

            /**
             * Sets the value of the partWidth property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPartWidth(BigDecimal value) {
                this.partWidth = value;
            }

            /**
             * Gets the value of the partHeight property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPartHeight() {
                return partHeight;
            }

            /**
             * Sets the value of the partHeight property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPartHeight(BigDecimal value) {
                this.partHeight = value;
            }

            /**
             * Gets the value of the lotShelfLife property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getLotShelfLife() {
                return lotShelfLife;
            }

            /**
             * Sets the value of the lotShelfLife property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setLotShelfLife(Integer value) {
                this.lotShelfLife = value;
            }

            /**
             * Gets the value of the webPart property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isWebPart() {
                return webPart;
            }

            /**
             * Sets the value of the webPart property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setWebPart(Boolean value) {
                this.webPart = value;
            }

            /**
             * Gets the value of the runOut property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isRunOut() {
                return runOut;
            }

            /**
             * Sets the value of the runOut property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setRunOut(Boolean value) {
                this.runOut = value;
            }

            /**
             * Gets the value of the subPart property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSubPart() {
                return subPart;
            }

            /**
             * Sets the value of the subPart property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSubPart(String value) {
                this.subPart = value;
            }

            /**
             * Gets the value of the diameter property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getDiameter() {
                return diameter;
            }

            /**
             * Sets the value of the diameter property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setDiameter(BigDecimal value) {
                this.diameter = value;
            }

            /**
             * Gets the value of the gravity property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getGravity() {
                return gravity;
            }

            /**
             * Sets the value of the gravity property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setGravity(BigDecimal value) {
                this.gravity = value;
            }

            /**
             * Gets the value of the onHold property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOnHold() {
                return onHold;
            }

            /**
             * Sets the value of the onHold property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOnHold(Boolean value) {
                this.onHold = value;
            }

            /**
             * Gets the value of the onHoldDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getOnHoldDate() {
                return onHoldDate;
            }

            /**
             * Sets the value of the onHoldDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setOnHoldDate(XMLGregorianCalendar value) {
                this.onHoldDate = value;
            }

            /**
             * Gets the value of the onHoldReasonCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOnHoldReasonCode() {
                return onHoldReasonCode;
            }

            /**
             * Sets the value of the onHoldReasonCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOnHoldReasonCode(String value) {
                this.onHoldReasonCode = value;
            }

            /**
             * Gets the value of the analysisCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAnalysisCode() {
                return analysisCode;
            }

            /**
             * Sets the value of the analysisCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAnalysisCode(String value) {
                this.analysisCode = value;
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
             * Gets the value of the glbPartNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGlbPartNum() {
                return glbPartNum;
            }

            /**
             * Sets the value of the glbPartNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGlbPartNum(String value) {
                this.glbPartNum = value;
            }

            /**
             * Gets the value of the mtlAnalysisCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMtlAnalysisCode() {
                return mtlAnalysisCode;
            }

            /**
             * Sets the value of the mtlAnalysisCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMtlAnalysisCode(String value) {
                this.mtlAnalysisCode = value;
            }

            /**
             * Gets the value of the globalPart property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isGlobalPart() {
                return globalPart;
            }

            /**
             * Sets the value of the globalPart property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setGlobalPart(Boolean value) {
                this.globalPart = value;
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
             * Gets the value of the isSuppUnitsFactor property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getISSuppUnitsFactor() {
                return isSuppUnitsFactor;
            }

            /**
             * Sets the value of the isSuppUnitsFactor property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setISSuppUnitsFactor(BigDecimal value) {
                this.isSuppUnitsFactor = value;
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
             * Gets the value of the oldPartNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOldPartNum() {
                return oldPartNum;
            }

            /**
             * Sets the value of the oldPartNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOldPartNum(String value) {
                this.oldPartNum = value;
            }

            /**
             * Gets the value of the pdmObjID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPDMObjID() {
                return pdmObjID;
            }

            /**
             * Sets the value of the pdmObjID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPDMObjID(String value) {
                this.pdmObjID = value;
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
             * Gets the value of the imageFileName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getImageFileName() {
                return imageFileName;
            }

            /**
             * Sets the value of the imageFileName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setImageFileName(String value) {
                this.imageFileName = value;
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
             * Gets the value of the constrained property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isConstrained() {
                return constrained;
            }

            /**
             * Sets the value of the constrained property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setConstrained(Boolean value) {
                this.constrained = value;
            }

            /**
             * Gets the value of the upcCode1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUPCCode1() {
                return upcCode1;
            }

            /**
             * Sets the value of the upcCode1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUPCCode1(String value) {
                this.upcCode1 = value;
            }

            /**
             * Gets the value of the upcCode2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUPCCode2() {
                return upcCode2;
            }

            /**
             * Sets the value of the upcCode2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUPCCode2(String value) {
                this.upcCode2 = value;
            }

            /**
             * Gets the value of the upcCode3 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUPCCode3() {
                return upcCode3;
            }

            /**
             * Sets the value of the upcCode3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUPCCode3(String value) {
                this.upcCode3 = value;
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
             * Gets the value of the consolidatedPurchasing property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isConsolidatedPurchasing() {
                return consolidatedPurchasing;
            }

            /**
             * Sets the value of the consolidatedPurchasing property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setConsolidatedPurchasing(Boolean value) {
                this.consolidatedPurchasing = value;
            }

            /**
             * Gets the value of the webInStock property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isWebInStock() {
                return webInStock;
            }

            /**
             * Sets the value of the webInStock property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setWebInStock(Boolean value) {
                this.webInStock = value;
            }

            /**
             * Gets the value of the purchasingFactorDirection property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPurchasingFactorDirection() {
                return purchasingFactorDirection;
            }

            /**
             * Sets the value of the purchasingFactorDirection property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPurchasingFactorDirection(String value) {
                this.purchasingFactorDirection = value;
            }

            /**
             * Gets the value of the sellingFactorDirection property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSellingFactorDirection() {
                return sellingFactorDirection;
            }

            /**
             * Sets the value of the sellingFactorDirection property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSellingFactorDirection(String value) {
                this.sellingFactorDirection = value;
            }

            /**
             * Gets the value of the mdpv property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getMDPV() {
                return mdpv;
            }

            /**
             * Sets the value of the mdpv property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setMDPV(Integer value) {
                this.mdpv = value;
            }

            /**
             * Gets the value of the returnableContainer property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReturnableContainer() {
                return returnableContainer;
            }

            /**
             * Sets the value of the returnableContainer property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReturnableContainer(String value) {
                this.returnableContainer = value;
            }

            /**
             * Gets the value of the netVolume property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNetVolume() {
                return netVolume;
            }

            /**
             * Sets the value of the netVolume property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNetVolume(BigDecimal value) {
                this.netVolume = value;
            }

            /**
             * Gets the value of the recDocReq property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isRecDocReq() {
                return recDocReq;
            }

            /**
             * Sets the value of the recDocReq property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setRecDocReq(Boolean value) {
                this.recDocReq = value;
            }

            /**
             * Gets the value of the shipDocReq property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isShipDocReq() {
                return shipDocReq;
            }

            /**
             * Sets the value of the shipDocReq property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setShipDocReq(Boolean value) {
                this.shipDocReq = value;
            }

            /**
             * Gets the value of the qtyBearing property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isQtyBearing() {
                return qtyBearing;
            }

            /**
             * Sets the value of the qtyBearing property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setQtyBearing(Boolean value) {
                this.qtyBearing = value;
            }

            /**
             * Gets the value of the aesExp property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAESExp() {
                return aesExp;
            }

            /**
             * Sets the value of the aesExp property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAESExp(String value) {
                this.aesExp = value;
            }

            /**
             * Gets the value of the eccnNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getECCNNumber() {
                return eccnNumber;
            }

            /**
             * Sets the value of the eccnNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setECCNNumber(String value) {
                this.eccnNumber = value;
            }

            /**
             * Gets the value of the expLicNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getExpLicNumber() {
                return expLicNumber;
            }

            /**
             * Sets the value of the expLicNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setExpLicNumber(String value) {
                this.expLicNumber = value;
            }

            /**
             * Gets the value of the expLicType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getExpLicType() {
                return expLicType;
            }

            /**
             * Sets the value of the expLicType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setExpLicType(String value) {
                this.expLicType = value;
            }

            /**
             * Gets the value of the hazClass property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHazClass() {
                return hazClass;
            }

            /**
             * Sets the value of the hazClass property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHazClass(String value) {
                this.hazClass = value;
            }

            /**
             * Gets the value of the hazGvrnmtID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHazGvrnmtID() {
                return hazGvrnmtID;
            }

            /**
             * Sets the value of the hazGvrnmtID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHazGvrnmtID(String value) {
                this.hazGvrnmtID = value;
            }

            /**
             * Gets the value of the hazItem property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isHazItem() {
                return hazItem;
            }

            /**
             * Sets the value of the hazItem property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setHazItem(Boolean value) {
                this.hazItem = value;
            }

            /**
             * Gets the value of the hazPackInstr property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHazPackInstr() {
                return hazPackInstr;
            }

            /**
             * Sets the value of the hazPackInstr property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHazPackInstr(String value) {
                this.hazPackInstr = value;
            }

            /**
             * Gets the value of the hazSub property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHazSub() {
                return hazSub;
            }

            /**
             * Sets the value of the hazSub property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHazSub(String value) {
                this.hazSub = value;
            }

            /**
             * Gets the value of the hazTechName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHazTechName() {
                return hazTechName;
            }

            /**
             * Sets the value of the hazTechName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHazTechName(String value) {
                this.hazTechName = value;
            }

            /**
             * Gets the value of the hts property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHTS() {
                return hts;
            }

            /**
             * Sets the value of the hts property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHTS(String value) {
                this.hts = value;
            }

            /**
             * Gets the value of the naftaOrigCountry property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNAFTAOrigCountry() {
                return naftaOrigCountry;
            }

            /**
             * Sets the value of the naftaOrigCountry property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNAFTAOrigCountry(String value) {
                this.naftaOrigCountry = value;
            }

            /**
             * Gets the value of the naftaPref property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNAFTAPref() {
                return naftaPref;
            }

            /**
             * Sets the value of the naftaPref property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNAFTAPref(String value) {
                this.naftaPref = value;
            }

            /**
             * Gets the value of the naftaProd property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNAFTAProd() {
                return naftaProd;
            }

            /**
             * Sets the value of the naftaProd property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNAFTAProd(String value) {
                this.naftaProd = value;
            }

            /**
             * Gets the value of the origCountry property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOrigCountry() {
                return origCountry;
            }

            /**
             * Sets the value of the origCountry property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOrigCountry(String value) {
                this.origCountry = value;
            }

            /**
             * Gets the value of the schedBcode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSchedBcode() {
                return schedBcode;
            }

            /**
             * Sets the value of the schedBcode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSchedBcode(String value) {
                this.schedBcode = value;
            }

            /**
             * Gets the value of the useHTSDesc property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isUseHTSDesc() {
                return useHTSDesc;
            }

            /**
             * Sets the value of the useHTSDesc property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setUseHTSDesc(Boolean value) {
                this.useHTSDesc = value;
            }

            /**
             * Gets the value of the ownershipStatus property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOwnershipStatus() {
                return ownershipStatus;
            }

            /**
             * Sets the value of the ownershipStatus property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOwnershipStatus(String value) {
                this.ownershipStatus = value;
            }

            /**
             * Gets the value of the rcOverThreshold property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getRCOverThreshold() {
                return rcOverThreshold;
            }

            /**
             * Sets the value of the rcOverThreshold property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setRCOverThreshold(BigDecimal value) {
                this.rcOverThreshold = value;
            }

            /**
             * Gets the value of the rcUnderThreshold property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getRCUnderThreshold() {
                return rcUnderThreshold;
            }

            /**
             * Sets the value of the rcUnderThreshold property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setRCUnderThreshold(BigDecimal value) {
                this.rcUnderThreshold = value;
            }

            /**
             * Gets the value of the revChargeMethod property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRevChargeMethod() {
                return revChargeMethod;
            }

            /**
             * Sets the value of the revChargeMethod property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRevChargeMethod(String value) {
                this.revChargeMethod = value;
            }

            /**
             * Gets the value of the uomClassID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUOMClassID() {
                return uomClassID;
            }

            /**
             * Sets the value of the uomClassID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUOMClassID(String value) {
                this.uomClassID = value;
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
             * Gets the value of the snMaskExample property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSNMaskExample() {
                return snMaskExample;
            }

            /**
             * Sets the value of the snMaskExample property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSNMaskExample(String value) {
                this.snMaskExample = value;
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
             * Gets the value of the lotAppendDate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLotAppendDate() {
                return lotAppendDate;
            }

            /**
             * Sets the value of the lotAppendDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLotAppendDate(String value) {
                this.lotAppendDate = value;
            }

            /**
             * Gets the value of the lotBatch property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isLotBatch() {
                return lotBatch;
            }

            /**
             * Sets the value of the lotBatch property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLotBatch(Boolean value) {
                this.lotBatch = value;
            }

            /**
             * Gets the value of the lotBeforeDt property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isLotBeforeDt() {
                return lotBeforeDt;
            }

            /**
             * Sets the value of the lotBeforeDt property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLotBeforeDt(Boolean value) {
                this.lotBeforeDt = value;
            }

            /**
             * Gets the value of the lotCureDt property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isLotCureDt() {
                return lotCureDt;
            }

            /**
             * Sets the value of the lotCureDt property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLotCureDt(Boolean value) {
                this.lotCureDt = value;
            }

            /**
             * Gets the value of the lotDigits property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getLotDigits() {
                return lotDigits;
            }

            /**
             * Sets the value of the lotDigits property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setLotDigits(Integer value) {
                this.lotDigits = value;
            }

            /**
             * Gets the value of the lotExpDt property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isLotExpDt() {
                return lotExpDt;
            }

            /**
             * Sets the value of the lotExpDt property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLotExpDt(Boolean value) {
                this.lotExpDt = value;
            }

            /**
             * Gets the value of the lotFirmware property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isLotFirmware() {
                return lotFirmware;
            }

            /**
             * Sets the value of the lotFirmware property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLotFirmware(Boolean value) {
                this.lotFirmware = value;
            }

            /**
             * Gets the value of the lotHeat property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isLotHeat() {
                return lotHeat;
            }

            /**
             * Sets the value of the lotHeat property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLotHeat(Boolean value) {
                this.lotHeat = value;
            }

            /**
             * Gets the value of the lotLeadingZeros property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isLotLeadingZeros() {
                return lotLeadingZeros;
            }

            /**
             * Sets the value of the lotLeadingZeros property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLotLeadingZeros(Boolean value) {
                this.lotLeadingZeros = value;
            }

            /**
             * Gets the value of the lotMfgBatch property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isLotMfgBatch() {
                return lotMfgBatch;
            }

            /**
             * Sets the value of the lotMfgBatch property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLotMfgBatch(Boolean value) {
                this.lotMfgBatch = value;
            }

            /**
             * Gets the value of the lotMfgDt property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isLotMfgDt() {
                return lotMfgDt;
            }

            /**
             * Sets the value of the lotMfgDt property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLotMfgDt(Boolean value) {
                this.lotMfgDt = value;
            }

            /**
             * Gets the value of the lotMfgLot property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isLotMfgLot() {
                return lotMfgLot;
            }

            /**
             * Sets the value of the lotMfgLot property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLotMfgLot(Boolean value) {
                this.lotMfgLot = value;
            }

            /**
             * Gets the value of the lotNxtNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getLotNxtNum() {
                return lotNxtNum;
            }

            /**
             * Sets the value of the lotNxtNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setLotNxtNum(Integer value) {
                this.lotNxtNum = value;
            }

            /**
             * Gets the value of the lotPrefix property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLotPrefix() {
                return lotPrefix;
            }

            /**
             * Sets the value of the lotPrefix property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLotPrefix(String value) {
                this.lotPrefix = value;
            }

            /**
             * Gets the value of the lotSeqID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLotSeqID() {
                return lotSeqID;
            }

            /**
             * Sets the value of the lotSeqID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLotSeqID(String value) {
                this.lotSeqID = value;
            }

            /**
             * Gets the value of the lotUseGlobalSeq property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isLotUseGlobalSeq() {
                return lotUseGlobalSeq;
            }

            /**
             * Sets the value of the lotUseGlobalSeq property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLotUseGlobalSeq(Boolean value) {
                this.lotUseGlobalSeq = value;
            }

            /**
             * Gets the value of the netVolumeUOM property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNetVolumeUOM() {
                return netVolumeUOM;
            }

            /**
             * Sets the value of the netVolumeUOM property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNetVolumeUOM(String value) {
                this.netVolumeUOM = value;
            }

            /**
             * Gets the value of the netWeightUOM property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNetWeightUOM() {
                return netWeightUOM;
            }

            /**
             * Sets the value of the netWeightUOM property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNetWeightUOM(String value) {
                this.netWeightUOM = value;
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
             * Gets the value of the useMaskSeq property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isUseMaskSeq() {
                return useMaskSeq;
            }

            /**
             * Sets the value of the useMaskSeq property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setUseMaskSeq(Boolean value) {
                this.useMaskSeq = value;
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
             * Gets the value of the buyToOrder property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isBuyToOrder() {
                return buyToOrder;
            }

            /**
             * Sets the value of the buyToOrder property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setBuyToOrder(Boolean value) {
                this.buyToOrder = value;
            }

            /**
             * Gets the value of the dropShip property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDropShip() {
                return dropShip;
            }

            /**
             * Sets the value of the dropShip property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDropShip(Boolean value) {
                this.dropShip = value;
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
             * Gets the value of the extConfig property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isExtConfig() {
                return extConfig;
            }

            /**
             * Sets the value of the extConfig property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setExtConfig(Boolean value) {
                this.extConfig = value;
            }

            /**
             * Gets the value of the isConfigured property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isIsConfigured() {
                return isConfigured;
            }

            /**
             * Sets the value of the isConfigured property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setIsConfigured(Boolean value) {
                this.isConfigured = value;
            }

            /**
             * Gets the value of the refCategory property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRefCategory() {
                return refCategory;
            }

            /**
             * Sets the value of the refCategory property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRefCategory(String value) {
                this.refCategory = value;
            }

            /**
             * Gets the value of the inspPlanType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getInspPlanType() {
                return inspPlanType;
            }

            /**
             * Sets the value of the inspPlanType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setInspPlanType(String value) {
                this.inspPlanType = value;
            }

            /**
             * Gets the value of the csfcj5 property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCSFCJ5() {
                return csfcj5;
            }

            /**
             * Sets the value of the csfcj5 property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCSFCJ5(Boolean value) {
                this.csfcj5 = value;
            }

            /**
             * Gets the value of the csflmw property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isCSFLMW() {
                return csflmw;
            }

            /**
             * Sets the value of the csflmw property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setCSFLMW(Boolean value) {
                this.csflmw = value;
            }

            /**
             * Gets the value of the grossWeight property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getGrossWeight() {
                return grossWeight;
            }

            /**
             * Sets the value of the grossWeight property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setGrossWeight(BigDecimal value) {
                this.grossWeight = value;
            }

            /**
             * Gets the value of the grossWeightUOM property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGrossWeightUOM() {
                return grossWeightUOM;
            }

            /**
             * Sets the value of the grossWeightUOM property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGrossWeightUOM(String value) {
                this.grossWeightUOM = value;
            }

            /**
             * Gets the value of the basePartNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBasePartNum() {
                return basePartNum;
            }

            /**
             * Sets the value of the basePartNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBasePartNum(String value) {
                this.basePartNum = value;
            }

            /**
             * Gets the value of the fsAssetClassCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFSAssetClassCode() {
                return fsAssetClassCode;
            }

            /**
             * Sets the value of the fsAssetClassCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFSAssetClassCode(String value) {
                this.fsAssetClassCode = value;
            }

            /**
             * Gets the value of the fsPricePerCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFSPricePerCode() {
                return fsPricePerCode;
            }

            /**
             * Sets the value of the fsPricePerCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFSPricePerCode(String value) {
                this.fsPricePerCode = value;
            }

            /**
             * Gets the value of the fsSalesUnitPrice property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getFSSalesUnitPrice() {
                return fsSalesUnitPrice;
            }

            /**
             * Sets the value of the fsSalesUnitPrice property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setFSSalesUnitPrice(BigDecimal value) {
                this.fsSalesUnitPrice = value;
            }

            /**
             * Gets the value of the rcvInspectionReq property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isRcvInspectionReq() {
                return rcvInspectionReq;
            }

            /**
             * Sets the value of the rcvInspectionReq property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setRcvInspectionReq(Boolean value) {
                this.rcvInspectionReq = value;
            }

            /**
             * Gets the value of the dispPartNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDispPartNum() {
                return dispPartNum;
            }

            /**
             * Sets the value of the dispPartNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDispPartNum(String value) {
                this.dispPartNum = value;
            }

            /**
             * Gets the value of the linkPartNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLinkPartNum() {
                return linkPartNum;
            }

            /**
             * Sets the value of the linkPartNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLinkPartNum(String value) {
                this.linkPartNum = value;
            }

            /**
             * Gets the value of the typeCodeDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTypeCodeDescription() {
                return typeCodeDescription;
            }

            /**
             * Sets the value of the typeCodeDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTypeCodeDescription(String value) {
                this.typeCodeDescription = value;
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
             * Gets the value of the stockPart property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isStockPart() {
                return stockPart;
            }

            /**
             * Sets the value of the stockPart property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setStockPart(Boolean value) {
                this.stockPart = value;
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
