
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
 * <p>Java class for GlbSugPOChgDataSetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GlbSugPOChgDataSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GlbSugPOChgDataSet">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="GlbSugPOChg">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="PONum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="POLine" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="PORelNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="SuggestionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BuyerID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RequireDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="SourceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SurplusQty" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="CancelReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Plant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ConNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VendorChange" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="OrderNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="OrderLine" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="OrderRelNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="ExtCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="TransDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="GlbCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CheckBox01" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox02" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox03" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox04" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CheckBox05" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="Date01" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date02" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date03" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date04" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Date05" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Number01" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number02" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number03" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number04" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="Number05" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="ShortChar01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShortChar05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="ABCCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NewShipByDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="NewOurQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="SuggestionStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DisplaySuggestionStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="IUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="SalesIUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "GlbSugPOChgDataSetType", namespace = "http://epicor.com/schemas", propOrder = {
    "glbSugPOChgDataSet"
})
public class GlbSugPOChgDataSetType {

    @XmlElement(name = "GlbSugPOChgDataSet", required = true)
    protected GlbSugPOChgDataSetType.GlbSugPOChgDataSet glbSugPOChgDataSet;

    /**
     * Gets the value of the glbSugPOChgDataSet property.
     * 
     * @return
     *     possible object is
     *     {@link GlbSugPOChgDataSetType.GlbSugPOChgDataSet }
     *     
     */
    public GlbSugPOChgDataSetType.GlbSugPOChgDataSet getGlbSugPOChgDataSet() {
        return glbSugPOChgDataSet;
    }

    /**
     * Sets the value of the glbSugPOChgDataSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlbSugPOChgDataSetType.GlbSugPOChgDataSet }
     *     
     */
    public void setGlbSugPOChgDataSet(GlbSugPOChgDataSetType.GlbSugPOChgDataSet value) {
        this.glbSugPOChgDataSet = value;
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
     *         &lt;element name="GlbSugPOChg">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="PONum" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="POLine" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="PORelNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="SuggestionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BuyerID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RequireDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="SourceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SurplusQty" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="CancelReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Plant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ConNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VendorChange" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="OrderNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="OrderLine" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="OrderRelNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="ExtCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="TransDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="GlbCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CheckBox01" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox02" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox03" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox04" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CheckBox05" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="Date01" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date02" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date03" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date04" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Date05" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Number01" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number02" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number03" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number04" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="Number05" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="ShortChar01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShortChar05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="ABCCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NewShipByDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="NewOurQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="SuggestionStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DisplaySuggestionStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="IUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="SalesIUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "glbSugPOChgOrWebServiceErrors"
    })
    public static class GlbSugPOChgDataSet {

        @XmlElements({
            @XmlElement(name = "GlbSugPOChg", type = GlbSugPOChgDataSetType.GlbSugPOChgDataSet.GlbSugPOChg.class),
            @XmlElement(name = "WebServiceErrors", type = GlbSugPOChgDataSetType.GlbSugPOChgDataSet.WebServiceErrors.class)
        })
        protected List<Object> glbSugPOChgOrWebServiceErrors;

        /**
         * Gets the value of the glbSugPOChgOrWebServiceErrors property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the glbSugPOChgOrWebServiceErrors property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getGlbSugPOChgOrWebServiceErrors().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GlbSugPOChgDataSetType.GlbSugPOChgDataSet.GlbSugPOChg }
         * {@link GlbSugPOChgDataSetType.GlbSugPOChgDataSet.WebServiceErrors }
         * 
         * 
         */
        public List<Object> getGlbSugPOChgOrWebServiceErrors() {
            if (glbSugPOChgOrWebServiceErrors == null) {
                glbSugPOChgOrWebServiceErrors = new ArrayList<Object>();
            }
            return this.glbSugPOChgOrWebServiceErrors;
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
         *         &lt;element name="PONum" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="POLine" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="PORelNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="SuggestionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BuyerID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="RequireDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="SourceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SurplusQty" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="CancelReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Plant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ConNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VendorChange" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="OrderNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="OrderLine" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="OrderRelNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="ExtCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="TransDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="GlbCompany" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CheckBox01" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox02" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox03" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox04" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CheckBox05" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="Date01" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date02" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date03" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date04" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Date05" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Number01" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number02" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number03" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number04" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="Number05" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="ShortChar01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShortChar05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SysRevID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="BitFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="ABCCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NewShipByDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="NewOurQuantity" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="SuggestionStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DisplaySuggestionStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="IUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="SalesIUM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "poNum",
            "poLine",
            "poRelNum",
            "suggestionCode",
            "buyerID",
            "requireDate",
            "sourceName",
            "surplusQty",
            "cancelReason",
            "plant",
            "conNum",
            "comment",
            "vendorChange",
            "orderNum",
            "orderLine",
            "orderRelNum",
            "extCompany",
            "transDate",
            "glbCompany",
            "checkBox01",
            "checkBox02",
            "checkBox03",
            "checkBox04",
            "checkBox05",
            "date01",
            "date02",
            "date03",
            "date04",
            "date05",
            "number01",
            "number02",
            "number03",
            "number04",
            "number05",
            "shortChar01",
            "shortChar02",
            "shortChar03",
            "shortChar04",
            "shortChar05",
            "sysRowID",
            "sysRevID",
            "bitFlag",
            "abcCode",
            "newShipByDate",
            "newOurQuantity",
            "suggestionStatus",
            "displaySuggestionStatus",
            "ium",
            "salesIUM",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class GlbSugPOChg {

            @XmlElement(name = "Company", required = true)
            protected String company;
            @XmlElement(name = "PONum")
            protected int poNum;
            @XmlElement(name = "POLine")
            protected int poLine;
            @XmlElement(name = "PORelNum")
            protected int poRelNum;
            @XmlElement(name = "SuggestionCode")
            protected String suggestionCode;
            @XmlElement(name = "BuyerID")
            protected String buyerID;
            @XmlElement(name = "RequireDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar requireDate;
            @XmlElement(name = "SourceName")
            protected String sourceName;
            @XmlElement(name = "SurplusQty")
            protected BigDecimal surplusQty;
            @XmlElement(name = "CancelReason")
            protected String cancelReason;
            @XmlElement(name = "Plant")
            protected String plant;
            @XmlElement(name = "ConNum")
            protected Integer conNum;
            @XmlElement(name = "Comment")
            protected String comment;
            @XmlElement(name = "VendorChange")
            protected Boolean vendorChange;
            @XmlElement(name = "OrderNum")
            protected Integer orderNum;
            @XmlElement(name = "OrderLine")
            protected Integer orderLine;
            @XmlElement(name = "OrderRelNum")
            protected Integer orderRelNum;
            @XmlElement(name = "ExtCompany", required = true)
            protected String extCompany;
            @XmlElement(name = "TransDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar transDate;
            @XmlElement(name = "GlbCompany")
            protected String glbCompany;
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
            @XmlElement(name = "SysRowID")
            protected String sysRowID;
            @XmlElement(name = "SysRevID")
            protected Integer sysRevID;
            @XmlElement(name = "BitFlag")
            protected Integer bitFlag;
            @XmlElement(name = "ABCCode")
            protected String abcCode;
            @XmlElement(name = "NewShipByDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar newShipByDate;
            @XmlElement(name = "NewOurQuantity")
            protected BigDecimal newOurQuantity;
            @XmlElement(name = "SuggestionStatus")
            protected String suggestionStatus;
            @XmlElement(name = "DisplaySuggestionStatus")
            protected String displaySuggestionStatus;
            @XmlElement(name = "IUM")
            protected String ium;
            @XmlElement(name = "SalesIUM")
            protected String salesIUM;
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
             * Gets the value of the poNum property.
             * 
             */
            public int getPONum() {
                return poNum;
            }

            /**
             * Sets the value of the poNum property.
             * 
             */
            public void setPONum(int value) {
                this.poNum = value;
            }

            /**
             * Gets the value of the poLine property.
             * 
             */
            public int getPOLine() {
                return poLine;
            }

            /**
             * Sets the value of the poLine property.
             * 
             */
            public void setPOLine(int value) {
                this.poLine = value;
            }

            /**
             * Gets the value of the poRelNum property.
             * 
             */
            public int getPORelNum() {
                return poRelNum;
            }

            /**
             * Sets the value of the poRelNum property.
             * 
             */
            public void setPORelNum(int value) {
                this.poRelNum = value;
            }

            /**
             * Gets the value of the suggestionCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSuggestionCode() {
                return suggestionCode;
            }

            /**
             * Sets the value of the suggestionCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSuggestionCode(String value) {
                this.suggestionCode = value;
            }

            /**
             * Gets the value of the buyerID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBuyerID() {
                return buyerID;
            }

            /**
             * Sets the value of the buyerID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBuyerID(String value) {
                this.buyerID = value;
            }

            /**
             * Gets the value of the requireDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getRequireDate() {
                return requireDate;
            }

            /**
             * Sets the value of the requireDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setRequireDate(XMLGregorianCalendar value) {
                this.requireDate = value;
            }

            /**
             * Gets the value of the sourceName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSourceName() {
                return sourceName;
            }

            /**
             * Sets the value of the sourceName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSourceName(String value) {
                this.sourceName = value;
            }

            /**
             * Gets the value of the surplusQty property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getSurplusQty() {
                return surplusQty;
            }

            /**
             * Sets the value of the surplusQty property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setSurplusQty(BigDecimal value) {
                this.surplusQty = value;
            }

            /**
             * Gets the value of the cancelReason property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCancelReason() {
                return cancelReason;
            }

            /**
             * Sets the value of the cancelReason property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCancelReason(String value) {
                this.cancelReason = value;
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
             * Gets the value of the conNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getConNum() {
                return conNum;
            }

            /**
             * Sets the value of the conNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setConNum(Integer value) {
                this.conNum = value;
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
             * Gets the value of the vendorChange property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isVendorChange() {
                return vendorChange;
            }

            /**
             * Sets the value of the vendorChange property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setVendorChange(Boolean value) {
                this.vendorChange = value;
            }

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
             * Gets the value of the orderRelNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getOrderRelNum() {
                return orderRelNum;
            }

            /**
             * Sets the value of the orderRelNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setOrderRelNum(Integer value) {
                this.orderRelNum = value;
            }

            /**
             * Gets the value of the extCompany property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getExtCompany() {
                return extCompany;
            }

            /**
             * Sets the value of the extCompany property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setExtCompany(String value) {
                this.extCompany = value;
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
             * Gets the value of the abcCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getABCCode() {
                return abcCode;
            }

            /**
             * Sets the value of the abcCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setABCCode(String value) {
                this.abcCode = value;
            }

            /**
             * Gets the value of the newShipByDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getNewShipByDate() {
                return newShipByDate;
            }

            /**
             * Sets the value of the newShipByDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setNewShipByDate(XMLGregorianCalendar value) {
                this.newShipByDate = value;
            }

            /**
             * Gets the value of the newOurQuantity property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getNewOurQuantity() {
                return newOurQuantity;
            }

            /**
             * Sets the value of the newOurQuantity property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setNewOurQuantity(BigDecimal value) {
                this.newOurQuantity = value;
            }

            /**
             * Gets the value of the suggestionStatus property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSuggestionStatus() {
                return suggestionStatus;
            }

            /**
             * Sets the value of the suggestionStatus property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSuggestionStatus(String value) {
                this.suggestionStatus = value;
            }

            /**
             * Gets the value of the displaySuggestionStatus property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDisplaySuggestionStatus() {
                return displaySuggestionStatus;
            }

            /**
             * Sets the value of the displaySuggestionStatus property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDisplaySuggestionStatus(String value) {
                this.displaySuggestionStatus = value;
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
             * Gets the value of the salesIUM property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSalesIUM() {
                return salesIUM;
            }

            /**
             * Sets the value of the salesIUM property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSalesIUM(String value) {
                this.salesIUM = value;
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
