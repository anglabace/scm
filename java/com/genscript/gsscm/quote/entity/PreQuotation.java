package com.genscript.gsscm.quote.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pre_quotation", catalog="olddb")
public class PreQuotation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer preQuotationId;
	private Integer reqId;
	private Integer initiator;
	private Date initDate;
	private Integer convertedBy;
	private Date convertedDate;
	private Integer quotationId;
	public Integer getPreQuotationId() {
		return preQuotationId;
	}
	public void setPreQuotationId(Integer preQuotationId) {
		this.preQuotationId = preQuotationId;
	}
	public Integer getReqId() {
		return reqId;
	}
	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}
	public Integer getInitiator() {
		return initiator;
	}
	public void setInitiator(Integer initiator) {
		this.initiator = initiator;
	}
	public Date getInitDate() {
		return initDate;
	}
	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}
	public Integer getConvertedBy() {
		return convertedBy;
	}
	public void setConvertedBy(Integer convertedBy) {
		this.convertedBy = convertedBy;
	}
	public Date getConvertedDate() {
		return convertedDate;
	}
	public void setConvertedDate(Date convertedDate) {
		this.convertedDate = convertedDate;
	}
	public Integer getQuotationId() {
		return quotationId;
	}
	public void setQuotationId(Integer quotationId) {
		this.quotationId = quotationId;
	}
	

}
