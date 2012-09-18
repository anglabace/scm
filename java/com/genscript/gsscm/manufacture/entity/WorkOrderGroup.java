package com.genscript.gsscm.manufacture.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

@Entity
@Table(name = "work_order_group", catalog="manufacturing")
public class WorkOrderGroup extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer workGroupId;
	private Integer orderNo;
	private Integer workGroupSpvs;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWorkGroupId() {
		return workGroupId;
	}
	public void setWorkGroupId(Integer workGroupId) {
		this.workGroupId = workGroupId;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getWorkGroupSpvs() {
		return workGroupSpvs;
	}
	public void setWorkGroupSpvs(Integer workGroupSpvs) {
		this.workGroupSpvs = workGroupSpvs;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	

}
