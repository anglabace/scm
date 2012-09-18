
package com.genscript.gsscm.epicorwebservice.stub.invoiceService;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Payment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Payment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PMUID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PayMathodName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InvoiceNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TranAmt" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TranType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AppliedAmt" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TranDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="CmsOrderNum" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Payment", propOrder = {
    "company",
    "pmuid",
    "payMathodName",
    "invoiceNum",
    "tranAmt",
    "tranType",
    "custId",
    "appliedAmt",
    "tranDate",
    "cmsOrderNum"
})
public class Payment {

    @XmlElement(name = "Company")
    protected String company;
    @XmlElement(name = "PMUID")
    protected int pmuid;
    @XmlElement(name = "PayMathodName")
    protected String payMathodName;
    @XmlElement(name = "InvoiceNum")
    protected int invoiceNum;
    @XmlElement(name = "TranAmt", required = true)
    protected BigDecimal tranAmt;
    @XmlElement(name = "TranType")
    protected String tranType;
    @XmlElement(name = "CustId")
    protected String custId;
    @XmlElement(name = "AppliedAmt", required = true)
    protected BigDecimal appliedAmt;
    @XmlElement(name = "TranDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar tranDate;
    @XmlElement(name = "CmsOrderNum", required = true)
    protected BigDecimal cmsOrderNum;

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
     * Gets the value of the pmuid property.
     * 
     */
    public int getPMUID() {
        return pmuid;
    }

    /**
     * Sets the value of the pmuid property.
     * 
     */
    public void setPMUID(int value) {
        this.pmuid = value;
    }

    /**
     * Gets the value of the payMathodName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayMathodName() {
        return payMathodName;
    }

    /**
     * Sets the value of the payMathodName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayMathodName(String value) {
        this.payMathodName = value;
    }

    /**
     * Gets the value of the invoiceNum property.
     * 
     */
    public int getInvoiceNum() {
        return invoiceNum;
    }

    /**
     * Sets the value of the invoiceNum property.
     * 
     */
    public void setInvoiceNum(int value) {
        this.invoiceNum = value;
    }

    /**
     * Gets the value of the tranAmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    /**
     * Sets the value of the tranAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTranAmt(BigDecimal value) {
        this.tranAmt = value;
    }

    /**
     * Gets the value of the tranType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranType() {
        return tranType;
    }

    /**
     * Sets the value of the tranType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranType(String value) {
        this.tranType = value;
    }

    /**
     * Gets the value of the custId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustId() {
        return custId;
    }

    /**
     * Sets the value of the custId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustId(String value) {
        this.custId = value;
    }

    /**
     * Gets the value of the appliedAmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAppliedAmt() {
        return appliedAmt;
    }

    /**
     * Sets the value of the appliedAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAppliedAmt(BigDecimal value) {
        this.appliedAmt = value;
    }

    /**
     * Gets the value of the tranDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTranDate() {
        return tranDate;
    }

    /**
     * Sets the value of the tranDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTranDate(XMLGregorianCalendar value) {
        this.tranDate = value;
    }

    /**
     * Gets the value of the cmsOrderNum property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCmsOrderNum() {
        return cmsOrderNum;
    }

    /**
     * Sets the value of the cmsOrderNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCmsOrderNum(BigDecimal value) {
        this.cmsOrderNum = value;
    }

}
