package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;

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
 * WoOperationComponent.
 * 
 * @author Wangsf.
 */

@Entity
@Table(name = "wo_operation_components", catalog = "manufacturing")
public class WoOperationComponent extends BaseEntity implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2016643701390627231L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer woOperationId;
	@Column(name="sequence_no")
	private Integer seqNo;
	private String storageLoc;
	private String componentName;
	private String uom;
	private String description;
	private Integer quantityUsed;
	private Integer perItemQuantity;
	private String currencyCode;
	private Double cost;
	private String costBasis;
	@Transient
	private String modifyUser;
	
	public Object clone() {
		WoOperationComponent cloneTemp = null;
		try {
			cloneTemp = (WoOperationComponent) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return cloneTemp;
	}
	
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
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getStorageLoc() {
		return storageLoc;
	}
	public void setStorageLoc(String storageLoc) {
		this.storageLoc = storageLoc;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuantityUsed() {
		return quantityUsed;
	}
	public void setQuantityUsed(Integer quantityUsed) {
		this.quantityUsed = quantityUsed;
	}
	public Integer getPerItemQuantity() {
		return perItemQuantity;
	}
	public void setPerItemQuantity(Integer perItemQuantity) {
		this.perItemQuantity = perItemQuantity;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getCostBasis() {
		return costBasis;
	}
	public void setCostBasis(String costBasis) {
		this.costBasis = costBasis;
	}
	public Integer getWoOperationId() {
		return woOperationId;
	}
	public void setWoOperationId(Integer woOperationId) {
		this.woOperationId = woOperationId;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	
}
