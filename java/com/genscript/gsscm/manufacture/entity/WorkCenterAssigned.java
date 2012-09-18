package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;

import javax.persistence.Column;
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
/**
 * WorkCenterAssigned.
 * 
 * 
 * @author lizhang.
 */

@Entity
@Table(name = "work_center_assigned", catalog="manufacturing")
public class WorkCenterAssigned extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "work_center_id")
	private WorkCenter workCenter;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "warehouse_id")
	private WareHouses wareHouses;
	private String itemType;
	private Integer clsId;
	@Transient
	private String clsName;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public WorkCenterAssigned() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public WorkCenter getWorkCenter() {
		return workCenter;
	}
	public void setWorkCenter(WorkCenter workCenter) {
		this.workCenter = workCenter;
	}
	public WareHouses getWareHouses() {
		return wareHouses;
	}
	public void setWareHouses(WareHouses wareHouses) {
		this.wareHouses = wareHouses;
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


}
