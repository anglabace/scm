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
 * ServiceComponent.
 * 
 * 
 * @author mingrs
 */
@Entity
@Table(name = "service_components", catalog = "product")
public class ServiceComponent extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4351345163652990652L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "component_id")
	private Integer id;
	private Integer serviceId;
	private String cpntCatalogNo;
	private Double quantity;
	private Integer listSeq;

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

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getCpntCatalogNo() {
		return cpntCatalogNo;
	}

	public void setCpntCatalogNo(String cpntCatalogNo) {
		this.cpntCatalogNo = cpntCatalogNo;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Integer getListSeq() {
		return listSeq;
	}

	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}

}
