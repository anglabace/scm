package com.genscript.gsscm.manufacture.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

@Entity
@Table(name = "qa_group_assigned", catalog = "manufacturing")
public class QaGroupAssigned extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String itemType;
	private Integer clsId;
	@ManyToOne(cascade = { CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinColumn(name = "qa_group_id")
	private QaGroup qaGroup;
	@Transient
	private String clsName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public Integer getClsId() {
		return clsId;
	}
	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}
	public String getClsName() {
		return clsName;
	}
	public void setClsName(String clsName) {
		this.clsName = clsName;
	}
	public QaGroup getQaGroup() {
		return qaGroup;
	}
	public void setQaGroup(QaGroup qaGroup) {
		this.qaGroup = qaGroup;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	

}
