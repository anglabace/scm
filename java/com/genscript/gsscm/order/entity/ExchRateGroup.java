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
 * EXCH RATE GROUPE.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "exch_rate_group", catalog="order")
public class ExchRateGroup extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	private Integer groupId;
	private String name;
	private String description;
	@Column(name = "rate_src_id")
	private Integer rateSrcId;
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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
	public Integer getRateSrcId() {
		return rateSrcId;
	}
	public void setRateSrcId(Integer rateSrcId) {
		this.rateSrcId = rateSrcId;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
