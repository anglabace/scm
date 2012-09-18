package com.genscript.gsscm.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * EXCH RATE SOURCE.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "exch_rate_source", catalog="order")
public class ExchRateSource extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rate_src_id")
	private Integer rateSrcId;
	private String name;
	private String description;
	private String defaultFlag;
	public Integer getRateSrcId() {
		return rateSrcId;
	}
	public void setRateSrcId(Integer rateSrcId) {
		this.rateSrcId = rateSrcId;
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
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
