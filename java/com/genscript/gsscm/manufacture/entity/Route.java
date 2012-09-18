package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * Route Model.
 * 
 * 
 * @author Wangsf.
 */

@Entity
@Table(name = "routings", catalog="manufacturing")
public class Route extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2016643701390627231L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="routing_id")
	private Integer id;
	private String name;
	private String description;
	@Column(name="warehouse")
	private Integer warehouseId;
	private String itemType;
	private Integer clsId;
	private String defaultFlag;
	private String costRollupFlag;
	private String comment;
	private String status;
	//显示用， 非数据库属性
	@Transient
	private String modifyUser;
	@Transient
	private String warehouseName;
	@Transient
	private String production;
	@Transient
	private List<Integer> delRouteOperationIdList;
	@Transient
	private List<RouteOperation> routeOperationList;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getDefaultFlag() {
		return defaultFlag;
	}
	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	public String getCostRollupFlag() {
		return costRollupFlag;
	}
	public void setCostRollupFlag(String costRollupFlag) {
		this.costRollupFlag = costRollupFlag;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
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
	public String getProduction() {
		return production;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	public List<Integer> getDelRouteOperationIdList() {
		return delRouteOperationIdList;
	}
	public void setDelRouteOperationIdList(List<Integer> delRouteOperationIdList) {
		this.delRouteOperationIdList = delRouteOperationIdList;
	}
	public List<RouteOperation> getRouteOperationList() {
		return routeOperationList;
	}
	public void setRouteOperationList(List<RouteOperation> routeOperationList) {
		this.routeOperationList = routeOperationList;
	}

}
