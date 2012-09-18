package com.genscript.gsscm.serv.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * Intermediate.
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "service_intermediates", catalog = "product")
public class ServiceIntermediate extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7008369632411038932L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "intmd_id")
	private Integer id;
	private Integer serviceId;
	private Integer quantity;
	private String intmdCatalogNo;
	private Integer listSeq;
    private String requiredFlag;
    private String intmdKeyword;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the intmdCatalogNo
	 */
	public String getIntmdCatalogNo() {
		return intmdCatalogNo;
	}

	/**
	 * @param intmdCatalogNo the intmdCatalogNo to set
	 */
	public void setIntmdCatalogNo(String intmdCatalogNo) {
		this.intmdCatalogNo = intmdCatalogNo;
	}

	/**
	 * @return the listSeq
	 */
	public Integer getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq the listSeq to set
	 */
	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the requiredFlag
	 */
	public String getRequiredFlag() {
		return requiredFlag;
	}

	/**
	 * @param requiredFlag the requiredFlag to set
	 */
	public void setRequiredFlag(String requiredFlag) {
		this.requiredFlag = requiredFlag;
	}

	public String getIntmdKeyword() {
		return intmdKeyword;
	}

	public void setIntmdKeyword(String intmdKeyword) {
		this.intmdKeyword = intmdKeyword;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	
}
