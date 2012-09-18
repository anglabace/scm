package com.genscript.gsscm.customer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * CustomerNote.
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "customer_notes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomerNote extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2711972863237847000L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id")
	private Integer id;
	private Integer custNo;
	@Column(name="note_type")
	private String type;
	private String description;
	private String docFlag;
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
	 * @return the custNo
	 */
	public Integer getCustNo() {
		return custNo;
	}
	/**
	 * @param custNo the custNo to set
	 */
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	/**
	 * @return the docFlag
	 */
	public String getDocFlag() {
		return docFlag;
	}
	/**
	 * @param docFlag the docFlag to set
	 */
	public void setDocFlag(String docFlag) {
		this.docFlag = docFlag;
	}
}
