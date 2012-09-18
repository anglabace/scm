package com.genscript.gsscm.basedata.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * PUBLIC SEARCH CONDITIONS.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "search_conditions", catalog="common")
public class PbSearchCondition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1449285885476109309L;
	private Integer conditionId;
	private PbSearch pbSearch;
	private PbSearchAttribute pbSearchAttribute;
	private String operator;
	private String value1;
	private String value2;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "condition_id")
	public Integer getConditionId() {
		return conditionId;
	}
	public void setConditionId(Integer conditionId) {
		this.conditionId = conditionId;
	}
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="search_id")
	public PbSearch getPbSearch() {
		return pbSearch;
	}
	public void setPbSearch(PbSearch pbSearch) {
		this.pbSearch = pbSearch;
	}
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="attribute_id")
	public PbSearchAttribute getPbSearchAttribute() {
		return pbSearchAttribute;
	}
	public void setPbSearchAttribute(PbSearchAttribute pbSearchAttribute) {
		this.pbSearchAttribute = pbSearchAttribute;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
