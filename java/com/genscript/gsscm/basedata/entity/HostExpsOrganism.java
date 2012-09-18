package com.genscript.gsscm.basedata.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * HOST EXPS ORGANISM.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "host_exps_organism", catalog="biolib")
public class HostExpsOrganism implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 643194149147683230L;
	@Id
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
