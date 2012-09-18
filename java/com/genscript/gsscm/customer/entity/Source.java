package com.genscript.gsscm.customer.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * SOURCE.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "marketing_source")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Source extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 985134634326832438L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "source_id")
	private Integer sourceId;
	private String code;
	private String name;
	private String description;
	private String deptNo;
	private String catalogNo;
	private String catalogId;
	private String campaignCode;
	private BigDecimal listCost;
	private BigDecimal postageCost;
	private BigDecimal printingCost;
	private BigDecimal mailingCost;
	private Date adEffFrom;
	private Date adEffTo;
	private String adDescription;
	private String adPublisher;
	private Float adRate;
	private Integer adInsCount;
	private String urlRefer;
	private String invoiceMsg;
	private String comment;
 //-----------add by zhou gang  2011 5 -9 ------------// 
    private String status;
 //-----------add by zhou gang  2011 5 -9 ------------//
  
   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getCampaignCode() {
		return campaignCode;
	}
	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}
	public BigDecimal getListCost() {
		return listCost;
	}
	public void setListCost(BigDecimal listCost) {
		this.listCost = listCost;
	}
	public BigDecimal getPostageCost() {
		return postageCost;
	}
	public void setPostageCost(BigDecimal postageCost) {
		this.postageCost = postageCost;
	}
	public BigDecimal getPrintingCost() {
		return printingCost;
	}
	public void setPrintingCost(BigDecimal printingCost) {
		this.printingCost = printingCost;
	}
	public BigDecimal getMailingCost() {
		return mailingCost;
	}
	public void setMailingCost(BigDecimal mailingCost) {
		this.mailingCost = mailingCost;
	}
	public Date getAdEffFrom() {
		return adEffFrom;
	}
	public void setAdEffFrom(Date adEffFrom) {
		this.adEffFrom = adEffFrom;
	}
	public Date getAdEffTo() {
		return adEffTo;
	}
	public void setAdEffTo(Date adEffTo) {
		this.adEffTo = adEffTo;
	}
	public String getAdDescription() {
		return adDescription;
	}
	public void setAdDescription(String adDescription) {
		this.adDescription = adDescription;
	}
	public String getAdPublisher() {
		return adPublisher;
	}
	public void setAdPublisher(String adPublisher) {
		this.adPublisher = adPublisher;
	}
	public Float getAdRate() {
		return adRate;
	}
	public void setAdRate(Float adRate) {
		this.adRate = adRate;
	}
	public Integer getAdInsCount() {
		return adInsCount;
	}
	public void setAdInsCount(Integer adInsCount) {
		this.adInsCount = adInsCount;
	}
	public String getUrlRefer() {
		return urlRefer;
	}
	public void setUrlRefer(String urlRefer) {
		this.urlRefer = urlRefer;
	}
	public String getInvoiceMsg() {
		return invoiceMsg;
	}
	public void setInvoiceMsg(String invoiceMsg) {
		this.invoiceMsg = invoiceMsg;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Source(){}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
