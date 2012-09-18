package com.genscript.gsscm.quote.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * QUOTATION TEMPLATE.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "quote_template", catalog="order")
public class QuoteTemplate extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tmpl_id")
	private Integer tmplId;
	private String tmplName;
	private String description;
	private Integer srcQuoteNo;
	private Integer owner;
	public Integer getTmplId() {
		return tmplId;
	}
	public void setTmplId(Integer tmplId) {
		this.tmplId = tmplId;
	}
	public String getTmplName() {
		return tmplName;
	}
	public void setTmplName(String tmplName) {
		this.tmplName = tmplName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSrcQuoteNo() {
		return srcQuoteNo;
	}
	public void setSrcQuoteNo(Integer srcQuoteNo) {
		this.srcQuoteNo = srcQuoteNo;
	}
	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
