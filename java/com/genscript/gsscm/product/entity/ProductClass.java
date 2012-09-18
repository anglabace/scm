package com.genscript.gsscm.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * PRODUCT ProductClass.
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "product_classification", catalog="product")
public class ProductClass extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clsId;
	private String name;
	private String description;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the clsId
	 */
	public Integer getClsId() {
		return clsId;
	}
	/**
	 * @param clsId the clsId to set
	 */
	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
