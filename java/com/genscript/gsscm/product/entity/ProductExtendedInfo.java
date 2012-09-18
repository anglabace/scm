package com.genscript.gsscm.product.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * product Detail.
 * 
 * 
 * @author Mingrs
 */
@Entity
@Table(name = "product_extended_info", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductExtendedInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4115067760817160562L;
	@Id
	private Integer productId;
	private String keywords;
	private String metaDesc;
	private String keyFeatures;
	private String applSample;
	private String applications;
	private String url;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getMetaDesc() {
		return metaDesc;
	}
	public void setMetaDesc(String metaDesc) {
		this.metaDesc = metaDesc;
	}
	public String getKeyFeatures() {
		return keyFeatures;
	}
	public void setKeyFeatures(String keyFeatures) {
		this.keyFeatures = keyFeatures;
	}
	public String getApplSample() {
		return applSample;
	}
	public void setApplSample(String applSample) {
		this.applSample = applSample;
	}
	public String getApplications() {
		return applications;
	}
	public void setApplications(String applications) {
		this.applications = applications;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	} 
	
}
