package com.genscript.gsscm.serv.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SERVICE RELATION VIEW.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "v_related_services", catalog="product")
public class ServiceRelationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3839406428079329087L;
	@Id
	private Integer relationId;
	private Integer serviceId;
	private String name;
	private String relationType;
	private String catalogNo;
	
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
