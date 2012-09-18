
package com.genscript.gsscm.epicorwebservice.stub.salesorder;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SaveOTSParamsDataSetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SaveOTSParamsDataSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SaveOTSParamsDataSet">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="SaveOTSParams">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="OTSAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSCountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="OTSCustSaved" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="OTSFaxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSResaleID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSSaveAs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSSaveCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSSaved" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="OTSShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSTaxRegionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSZIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTSOverride" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
@XmlType(name = "SaveOTSParamsDataSetType", namespace = "http://epicor.com/schemas", propOrder = {
    "saveOTSParamsDataSet"
})
public class SaveOTSParamsDataSetType {

    @XmlElement(name = "SaveOTSParamsDataSet", required = true)
    protected SaveOTSParamsDataSetType.SaveOTSParamsDataSet saveOTSParamsDataSet;

    /**
     * Gets the value of the saveOTSParamsDataSet property.
     * 
     * @return
     *     possible object is
     *     {@link SaveOTSParamsDataSetType.SaveOTSParamsDataSet }
     *     
     */
    public SaveOTSParamsDataSetType.SaveOTSParamsDataSet getSaveOTSParamsDataSet() {
        return saveOTSParamsDataSet;
    }

    /**
     * Sets the value of the saveOTSParamsDataSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link SaveOTSParamsDataSetType.SaveOTSParamsDataSet }
     *     
     */
    public void setSaveOTSParamsDataSet(SaveOTSParamsDataSetType.SaveOTSParamsDataSet value) {
        this.saveOTSParamsDataSet = value;
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
     *         &lt;element name="SaveOTSParams">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="OTSAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSCountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="OTSCustSaved" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="OTSFaxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSResaleID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSSaveAs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSSaveCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSSaved" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="OTSShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSTaxRegionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSZIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTSOverride" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
        "saveOTSParamsOrWebServiceErrors"
    })
    public static class SaveOTSParamsDataSet {

        @XmlElements({
            @XmlElement(name = "WebServiceErrors", type = SaveOTSParamsDataSetType.SaveOTSParamsDataSet.WebServiceErrors.class),
            @XmlElement(name = "SaveOTSParams", type = SaveOTSParamsDataSetType.SaveOTSParamsDataSet.SaveOTSParams.class)
        })
        protected List<Object> saveOTSParamsOrWebServiceErrors;

        /**
         * Gets the value of the saveOTSParamsOrWebServiceErrors property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the saveOTSParamsOrWebServiceErrors property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSaveOTSParamsOrWebServiceErrors().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SaveOTSParamsDataSetType.SaveOTSParamsDataSet.WebServiceErrors }
         * {@link SaveOTSParamsDataSetType.SaveOTSParamsDataSet.SaveOTSParams }
         * 
         * 
         */
        public List<Object> getSaveOTSParamsOrWebServiceErrors() {
            if (saveOTSParamsOrWebServiceErrors == null) {
                saveOTSParamsOrWebServiceErrors = new ArrayList<Object>();
            }
            return this.saveOTSParamsOrWebServiceErrors;
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
         *         &lt;element name="OTSAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSAddress3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSCountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="OTSCustSaved" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="OTSFaxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSResaleID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSSaveAs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSSaveCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSSaved" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="OTSShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSTaxRegionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSZIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTSOverride" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
            "otsAddress1",
            "otsAddress2",
            "otsAddress3",
            "otsCity",
            "otsContact",
            "otsCountryNum",
            "otsCustSaved",
            "otsFaxNum",
            "otsName",
            "otsPhoneNum",
            "otsResaleID",
            "otsSaveAs",
            "otsSaveCustID",
            "otsSaved",
            "otsShipToNum",
            "otsState",
            "otsTaxRegionCode",
            "otszip",
            "otsOverride",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class SaveOTSParams {

            @XmlElement(name = "OTSAddress1")
            protected String otsAddress1;
            @XmlElement(name = "OTSAddress2")
            protected String otsAddress2;
            @XmlElement(name = "OTSAddress3")
            protected String otsAddress3;
            @XmlElement(name = "OTSCity")
            protected String otsCity;
            @XmlElement(name = "OTSContact")
            protected String otsContact;
            @XmlElement(name = "OTSCountryNum")
            protected Integer otsCountryNum;
            @XmlElement(name = "OTSCustSaved")
            protected Boolean otsCustSaved;
            @XmlElement(name = "OTSFaxNum")
            protected String otsFaxNum;
            @XmlElement(name = "OTSName")
            protected String otsName;
            @XmlElement(name = "OTSPhoneNum")
            protected String otsPhoneNum;
            @XmlElement(name = "OTSResaleID")
            protected String otsResaleID;
            @XmlElement(name = "OTSSaveAs")
            protected String otsSaveAs;
            @XmlElement(name = "OTSSaveCustID")
            protected String otsSaveCustID;
            @XmlElement(name = "OTSSaved")
            protected Boolean otsSaved;
            @XmlElement(name = "OTSShipToNum")
            protected String otsShipToNum;
            @XmlElement(name = "OTSState")
            protected String otsState;
            @XmlElement(name = "OTSTaxRegionCode")
            protected String otsTaxRegionCode;
            @XmlElement(name = "OTSZIP")
            protected String otszip;
            @XmlElement(name = "OTSOverride")
            protected Boolean otsOverride;
            @XmlElement(name = "RowIdent")
            protected String rowIdent;
            @XmlElement(name = "RowMod")
            protected String rowMod;
            @XmlElement(name = "DBRowIdent")
            protected byte[] dbRowIdent;

            /**
             * Gets the value of the otsAddress1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSAddress1() {
                return otsAddress1;
            }

            /**
             * Sets the value of the otsAddress1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSAddress1(String value) {
                this.otsAddress1 = value;
            }

            /**
             * Gets the value of the otsAddress2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSAddress2() {
                return otsAddress2;
            }

            /**
             * Sets the value of the otsAddress2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSAddress2(String value) {
                this.otsAddress2 = value;
            }

            /**
             * Gets the value of the otsAddress3 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSAddress3() {
                return otsAddress3;
            }

            /**
             * Sets the value of the otsAddress3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSAddress3(String value) {
                this.otsAddress3 = value;
            }

            /**
             * Gets the value of the otsCity property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSCity() {
                return otsCity;
            }

            /**
             * Sets the value of the otsCity property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSCity(String value) {
                this.otsCity = value;
            }

            /**
             * Gets the value of the otsContact property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSContact() {
                return otsContact;
            }

            /**
             * Sets the value of the otsContact property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSContact(String value) {
                this.otsContact = value;
            }

            /**
             * Gets the value of the otsCountryNum property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getOTSCountryNum() {
                return otsCountryNum;
            }

            /**
             * Sets the value of the otsCountryNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setOTSCountryNum(Integer value) {
                this.otsCountryNum = value;
            }

            /**
             * Gets the value of the otsCustSaved property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOTSCustSaved() {
                return otsCustSaved;
            }

            /**
             * Sets the value of the otsCustSaved property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOTSCustSaved(Boolean value) {
                this.otsCustSaved = value;
            }

            /**
             * Gets the value of the otsFaxNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSFaxNum() {
                return otsFaxNum;
            }

            /**
             * Sets the value of the otsFaxNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSFaxNum(String value) {
                this.otsFaxNum = value;
            }

            /**
             * Gets the value of the otsName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSName() {
                return otsName;
            }

            /**
             * Sets the value of the otsName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSName(String value) {
                this.otsName = value;
            }

            /**
             * Gets the value of the otsPhoneNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSPhoneNum() {
                return otsPhoneNum;
            }

            /**
             * Sets the value of the otsPhoneNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSPhoneNum(String value) {
                this.otsPhoneNum = value;
            }

            /**
             * Gets the value of the otsResaleID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSResaleID() {
                return otsResaleID;
            }

            /**
             * Sets the value of the otsResaleID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSResaleID(String value) {
                this.otsResaleID = value;
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
             * Gets the value of the otsSaveCustID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSSaveCustID() {
                return otsSaveCustID;
            }

            /**
             * Sets the value of the otsSaveCustID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSSaveCustID(String value) {
                this.otsSaveCustID = value;
            }

            /**
             * Gets the value of the otsSaved property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOTSSaved() {
                return otsSaved;
            }

            /**
             * Sets the value of the otsSaved property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOTSSaved(Boolean value) {
                this.otsSaved = value;
            }

            /**
             * Gets the value of the otsShipToNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSShipToNum() {
                return otsShipToNum;
            }

            /**
             * Sets the value of the otsShipToNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSShipToNum(String value) {
                this.otsShipToNum = value;
            }

            /**
             * Gets the value of the otsState property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSState() {
                return otsState;
            }

            /**
             * Sets the value of the otsState property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSState(String value) {
                this.otsState = value;
            }

            /**
             * Gets the value of the otsTaxRegionCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSTaxRegionCode() {
                return otsTaxRegionCode;
            }

            /**
             * Sets the value of the otsTaxRegionCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSTaxRegionCode(String value) {
                this.otsTaxRegionCode = value;
            }

            /**
             * Gets the value of the otszip property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTSZIP() {
                return otszip;
            }

            /**
             * Sets the value of the otszip property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTSZIP(String value) {
                this.otszip = value;
            }

            /**
             * Gets the value of the otsOverride property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOTSOverride() {
                return otsOverride;
            }

            /**
             * Sets the value of the otsOverride property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOTSOverride(Boolean value) {
                this.otsOverride = value;
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
