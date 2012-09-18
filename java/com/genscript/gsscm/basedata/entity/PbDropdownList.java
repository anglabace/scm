package com.genscript.gsscm.basedata.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
//The table name and class name does not redefine the table with the same name.
@Table(name = "dropdown_list", catalog="common")
//The default cache strategy.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PbDropdownList implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3462082051466206613L;
	private Integer listId;
	private String listName;
	private String description;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	private List<PbDropdownListOptions> pbDropdownListOptions = new ArrayList<PbDropdownListOptions>();
	
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "list_id")
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy(value="listId")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<PbDropdownListOptions> getPbOptionItems() {
		return pbDropdownListOptions;
	}
	public void setPbOptionItems(List<PbDropdownListOptions> pbDropdownListOptions) {
		this.pbDropdownListOptions = pbDropdownListOptions;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "list_id")
	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
