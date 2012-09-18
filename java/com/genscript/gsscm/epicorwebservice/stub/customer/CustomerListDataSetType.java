
package com.genscript.gsscm.epicorwebservice.stub.customer;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomerListDataSetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerListDataSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustomerListDataSet">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="CustomerList">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TerritoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CustomerType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NoContact" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Zip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CreditHold" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="GroupCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                             &lt;element name="TerritoryTerritoryDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CustGrupGroupDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="HasBank" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="CustPartOpts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AllowShipTo3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "CustomerListDataSetType", namespace = "http://epicor.com/schemas", propOrder = {
    "customerListDataSet"
})
public class CustomerListDataSetType {

    @XmlElement(name = "CustomerListDataSet", required = true)
    protected CustomerListDataSetType.CustomerListDataSet customerListDataSet;

    /**
     * Gets the value of the customerListDataSet property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerListDataSetType.CustomerListDataSet }
     *     
     */
    public CustomerListDataSetType.CustomerListDataSet getCustomerListDataSet() {
        return customerListDataSet;
    }

    /**
     * Sets the value of the customerListDataSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerListDataSetType.CustomerListDataSet }
     *     
     */
    public void setCustomerListDataSet(CustomerListDataSetType.CustomerListDataSet value) {
        this.customerListDataSet = value;
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
     *         &lt;element name="CustomerList">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TerritoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CustomerType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NoContact" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Zip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CreditHold" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="GroupCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                   &lt;element name="TerritoryTerritoryDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CustGrupGroupDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="HasBank" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="CustPartOpts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AllowShipTo3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "customerListOrWebServiceErrors"
    })
    public static class CustomerListDataSet {

        @XmlElements({
            @XmlElement(name = "WebServiceErrors", type = CustomerListDataSetType.CustomerListDataSet.WebServiceErrors.class),
            @XmlElement(name = "CustomerList", type = CustomerListDataSetType.CustomerListDataSet.CustomerList.class)
        })
        protected List<Object> customerListOrWebServiceErrors;

        /**
         * Gets the value of the customerListOrWebServiceErrors property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the customerListOrWebServiceErrors property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCustomerListOrWebServiceErrors().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CustomerListDataSetType.CustomerListDataSet.WebServiceErrors }
         * {@link CustomerListDataSetType.CustomerListDataSet.CustomerList }
         * 
         * 
         */
        public List<Object> getCustomerListOrWebServiceErrors() {
            if (customerListOrWebServiceErrors == null) {
                customerListOrWebServiceErrors = new ArrayList<Object>();
            }
            return this.customerListOrWebServiceErrors;
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
         *         &lt;element name="CustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TerritoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CustomerType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NoContact" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Zip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CreditHold" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CustNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="GroupCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CountryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *         &lt;element name="TerritoryTerritoryDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CustGrupGroupDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ShipToNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="HasBank" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="CustPartOpts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AllowShipTo3" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="SysRowID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "custID",
            "name",
            "territoryID",
            "customerType",
            "noContact",
            "city",
            "state",
            "zip",
            "phoneNum",
            "creditHold",
            "custNum",
            "groupCode",
            "country",
            "countryNum",
            "territoryTerritoryDesc",
            "custGrupGroupDesc",
            "shipToNum",
            "hasBank",
            "custPartOpts",
            "allowShipTo3",
            "sysRowID",
            "rowIdent",
            "rowMod",
            "dbRowIdent"
        })
        public static class CustomerList {

            @XmlElement(name = "Company")
            protected String company;
            @XmlElement(name = "CustID")
            protected String custID;
            @XmlElement(name = "Name")
            protected String name;
            @XmlElement(name = "TerritoryID")
            protected String territoryID;
            @XmlElement(name = "CustomerType")
            protected String customerType;
            @XmlElement(name = "NoContact")
            protected Boolean noContact;
            @XmlElement(name = "City")
            protected String city;
            @XmlElement(name = "State")
            protected String state;
            @XmlElement(name = "Zip")
            protected String zip;
            @XmlElement(name = "PhoneNum")
            protected String phoneNum;
            @XmlElement(name = "CreditHold")
            protected Boolean creditHold;
            @XmlElement(name = "CustNum")
            protected Integer custNum;
            @XmlElement(name = "GroupCode")
            protected String groupCode;
            @XmlElement(name = "Country")
            protected String country;
            @XmlElement(name = "CountryNum")
            protected Integer countryNum;
            @XmlElement(name = "TerritoryTerritoryDesc")
            protected String territoryTerritoryDesc;
            @XmlElement(name = "CustGrupGroupDesc")
            protected String custGrupGroupDesc;
            @XmlElement(name = "ShipToNum")
            protected String shipToNum;
            @XmlElement(name = "HasBank")
            protected Boolean hasBank;
            @XmlElement(name = "CustPartOpts")
            protected String custPartOpts;
            @XmlElement(name = "AllowShipTo3")
            protected Boolean allowShipTo3;
            @XmlElement(name = "SysRowID")
            protected String sysRowID;
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
             * Gets the value of the territoryTerritoryDesc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTerritoryTerritoryDesc() {
                return territoryTerritoryDesc;
            }

            /**
             * Sets the value of the territoryTerritoryDesc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTerritoryTerritoryDesc(String value) {
                this.territoryTerritoryDesc = value;
            }

            /**
             * Gets the value of the custGrupGroupDesc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCustGrupGroupDesc() {
                return custGrupGroupDesc;
            }

            /**
             * Sets the value of the custGrupGroupDesc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCustGrupGroupDesc(String value) {
                this.custGrupGroupDesc = value;
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
