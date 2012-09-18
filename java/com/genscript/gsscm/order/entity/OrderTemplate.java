package com.genscript.gsscm.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * ORDER TEMPLATE.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "order_template", catalog="order")
public class OrderTemplate extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tmpl_id")
	private Integer tmplId;
	private String tmplName;
	private String description;
	private Integer srcOrderNo;
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
	public Integer getSrcOrderNo() {
		return srcOrderNo;
	}
	public void setSrcOrderNo(Integer srcOrderNo) {
		this.srcOrderNo = srcOrderNo;
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
