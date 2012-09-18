package com.genscript.gsscm.quote.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SERVICE QUOTE TEMPLATE LIST VIEW.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "v_cust_service_of_quote_tmpl", catalog="order")
public class SrchServiceQuoteTemplateBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7377973414976761640L;
	@Column(name="tmpl_name")
	@Id
	private String name;
	private Integer owner;
	private Integer custNo;
	private Integer srcQuoteNo;
	private Integer itemNo;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public Integer getSrcQuoteNo() {
		return srcQuoteNo;
	}
	public void setSrcQuoteNo(Integer srcQuoteNo) {
		this.srcQuoteNo = srcQuoteNo;
	}
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
}
