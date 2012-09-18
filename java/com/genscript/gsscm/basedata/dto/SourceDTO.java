package com.genscript.gsscm.basedata.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "SourceDTO", namespace = WsConstants.NS)
public class SourceDTO {
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
	private BigDecimal adRate;
	private Integer adInsCount;
	private String urlRefer;
	private String invoiceMsg;
	private String comment;
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public BigDecimal getAdRate() {
		return adRate;
	}
	public void setAdRate(BigDecimal adRate) {
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
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
