
package com.genscript.gsscm.epicorwebservice.stub.part;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="CompanyID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartAttch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartCOO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartDim" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartLangDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartPlant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartWhse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartBinInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartRestriction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartRestrictSubst" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartRev" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartRevAttch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartAudit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartRevInspPlan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartRevInspPlanAttch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartRevInspVend" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartSubs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClauseEntityGLC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClauseTaxExempt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="whereClausePartPC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="absolutePage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="callContextIn" type="{http://epicor.com/schemas}CallContextDataSetType" minOccurs="0"/>
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
    "companyID",
    "whereClausePart",
    "whereClausePartAttch",
    "whereClausePartCOO",
    "whereClausePartDim",
    "whereClausePartLangDesc",
    "whereClausePartPlant",
    "whereClausePartWhse",
    "whereClausePartBinInfo",
    "whereClausePartRestriction",
    "whereClausePartRestrictSubst",
    "whereClausePartRev",
    "whereClausePartRevAttch",
    "whereClausePartAudit",
    "whereClausePartRevInspPlan",
    "whereClausePartRevInspPlanAttch",
    "whereClausePartRevInspVend",
    "whereClausePartSubs",
    "whereClausePartUOM",
    "whereClauseEntityGLC",
    "whereClauseTaxExempt",
    "whereClausePartPC",
    "pageSize",
    "absolutePage",
    "callContextIn"
})
@XmlRootElement(name = "GetRows")
public class GetRows {

    @XmlElement(name = "CompanyID", namespace = "http://epicor.com/webservices/")
    protected String companyID;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePart;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartAttch;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartCOO;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartDim;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartLangDesc;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartPlant;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartWhse;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartBinInfo;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartRestriction;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartRestrictSubst;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartRev;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartRevAttch;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartAudit;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartRevInspPlan;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartRevInspPlanAttch;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartRevInspVend;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartSubs;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartUOM;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClauseEntityGLC;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClauseTaxExempt;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected String whereClausePartPC;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int pageSize;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected int absolutePage;
    @XmlElement(namespace = "http://epicor.com/webservices/")
    protected CallContextDataSetType callContextIn;

    /**
     * Gets the value of the companyID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyID() {
        return companyID;
    }

    /**
     * Sets the value of the companyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyID(String value) {
        this.companyID = value;
    }

    /**
     * Gets the value of the whereClausePart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePart() {
        return whereClausePart;
    }

    /**
     * Sets the value of the whereClausePart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePart(String value) {
        this.whereClausePart = value;
    }

    /**
     * Gets the value of the whereClausePartAttch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartAttch() {
        return whereClausePartAttch;
    }

    /**
     * Sets the value of the whereClausePartAttch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartAttch(String value) {
        this.whereClausePartAttch = value;
    }

    /**
     * Gets the value of the whereClausePartCOO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartCOO() {
        return whereClausePartCOO;
    }

    /**
     * Sets the value of the whereClausePartCOO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartCOO(String value) {
        this.whereClausePartCOO = value;
    }

    /**
     * Gets the value of the whereClausePartDim property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartDim() {
        return whereClausePartDim;
    }

    /**
     * Sets the value of the whereClausePartDim property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartDim(String value) {
        this.whereClausePartDim = value;
    }

    /**
     * Gets the value of the whereClausePartLangDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartLangDesc() {
        return whereClausePartLangDesc;
    }

    /**
     * Sets the value of the whereClausePartLangDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartLangDesc(String value) {
        this.whereClausePartLangDesc = value;
    }

    /**
     * Gets the value of the whereClausePartPlant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartPlant() {
        return whereClausePartPlant;
    }

    /**
     * Sets the value of the whereClausePartPlant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartPlant(String value) {
        this.whereClausePartPlant = value;
    }

    /**
     * Gets the value of the whereClausePartWhse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartWhse() {
        return whereClausePartWhse;
    }

    /**
     * Sets the value of the whereClausePartWhse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartWhse(String value) {
        this.whereClausePartWhse = value;
    }

    /**
     * Gets the value of the whereClausePartBinInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartBinInfo() {
        return whereClausePartBinInfo;
    }

    /**
     * Sets the value of the whereClausePartBinInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartBinInfo(String value) {
        this.whereClausePartBinInfo = value;
    }

    /**
     * Gets the value of the whereClausePartRestriction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartRestriction() {
        return whereClausePartRestriction;
    }

    /**
     * Sets the value of the whereClausePartRestriction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartRestriction(String value) {
        this.whereClausePartRestriction = value;
    }

    /**
     * Gets the value of the whereClausePartRestrictSubst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartRestrictSubst() {
        return whereClausePartRestrictSubst;
    }

    /**
     * Sets the value of the whereClausePartRestrictSubst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartRestrictSubst(String value) {
        this.whereClausePartRestrictSubst = value;
    }

    /**
     * Gets the value of the whereClausePartRev property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartRev() {
        return whereClausePartRev;
    }

    /**
     * Sets the value of the whereClausePartRev property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartRev(String value) {
        this.whereClausePartRev = value;
    }

    /**
     * Gets the value of the whereClausePartRevAttch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartRevAttch() {
        return whereClausePartRevAttch;
    }

    /**
     * Sets the value of the whereClausePartRevAttch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartRevAttch(String value) {
        this.whereClausePartRevAttch = value;
    }

    /**
     * Gets the value of the whereClausePartAudit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartAudit() {
        return whereClausePartAudit;
    }

    /**
     * Sets the value of the whereClausePartAudit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartAudit(String value) {
        this.whereClausePartAudit = value;
    }

    /**
     * Gets the value of the whereClausePartRevInspPlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartRevInspPlan() {
        return whereClausePartRevInspPlan;
    }

    /**
     * Sets the value of the whereClausePartRevInspPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartRevInspPlan(String value) {
        this.whereClausePartRevInspPlan = value;
    }

    /**
     * Gets the value of the whereClausePartRevInspPlanAttch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartRevInspPlanAttch() {
        return whereClausePartRevInspPlanAttch;
    }

    /**
     * Sets the value of the whereClausePartRevInspPlanAttch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartRevInspPlanAttch(String value) {
        this.whereClausePartRevInspPlanAttch = value;
    }

    /**
     * Gets the value of the whereClausePartRevInspVend property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartRevInspVend() {
        return whereClausePartRevInspVend;
    }

    /**
     * Sets the value of the whereClausePartRevInspVend property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartRevInspVend(String value) {
        this.whereClausePartRevInspVend = value;
    }

    /**
     * Gets the value of the whereClausePartSubs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartSubs() {
        return whereClausePartSubs;
    }

    /**
     * Sets the value of the whereClausePartSubs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartSubs(String value) {
        this.whereClausePartSubs = value;
    }

    /**
     * Gets the value of the whereClausePartUOM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartUOM() {
        return whereClausePartUOM;
    }

    /**
     * Sets the value of the whereClausePartUOM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartUOM(String value) {
        this.whereClausePartUOM = value;
    }

    /**
     * Gets the value of the whereClauseEntityGLC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClauseEntityGLC() {
        return whereClauseEntityGLC;
    }

    /**
     * Sets the value of the whereClauseEntityGLC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClauseEntityGLC(String value) {
        this.whereClauseEntityGLC = value;
    }

    /**
     * Gets the value of the whereClauseTaxExempt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClauseTaxExempt() {
        return whereClauseTaxExempt;
    }

    /**
     * Sets the value of the whereClauseTaxExempt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClauseTaxExempt(String value) {
        this.whereClauseTaxExempt = value;
    }

    /**
     * Gets the value of the whereClausePartPC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereClausePartPC() {
        return whereClausePartPC;
    }

    /**
     * Sets the value of the whereClausePartPC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereClausePartPC(String value) {
        this.whereClausePartPC = value;
    }

    /**
     * Gets the value of the pageSize property.
     * 
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the value of the pageSize property.
     * 
     */
    public void setPageSize(int value) {
        this.pageSize = value;
    }

    /**
     * Gets the value of the absolutePage property.
     * 
     */
    public int getAbsolutePage() {
        return absolutePage;
    }

    /**
     * Sets the value of the absolutePage property.
     * 
     */
    public void setAbsolutePage(int value) {
        this.absolutePage = value;
    }

    /**
     * Gets the value of the callContextIn property.
     * 
     * @return
     *     possible object is
     *     {@link CallContextDataSetType }
     *     
     */
    public CallContextDataSetType getCallContextIn() {
        return callContextIn;
    }

    /**
     * Sets the value of the callContextIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link CallContextDataSetType }
     *     
     */
    public void setCallContextIn(CallContextDataSetType value) {
        this.callContextIn = value;
    }

}
