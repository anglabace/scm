package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;
@Entity
@Table(name = "ds_plates", catalog = "manufacturing")
public class DsPlates extends BaseEntity  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8697220315117536234L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String plateNo;
	private String name;
	private Integer nums;
	private Integer layout;
	private String status;
	private Integer controlValue;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNums() {
		return nums;
	}
	public void setNums(Integer nums) {
		this.nums = nums;
	}
	public Integer getLayout() {
		return layout;
	}
	public void setLayout(Integer layout) {
		this.layout = layout;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getControlValue() {
		return controlValue;
	}
	public void setControlValue(Integer controlValue) {
		this.controlValue = controlValue;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	

}
