package com.genscript.gsscm.quote.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * quote_ds_seq
 * @author Zhang Yong
 * add date 2011-11-14
 */
@Entity
@Table(name = "quote_ds_seq", catalog = "order")
public class QuoteDsSeq implements Serializable {
	private static final long serialVersionUID = 8433197520153990447L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String 	type;
	private Integer	lastSeq;
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
	 * @return the lastSeq
	 */
	public Integer getLastSeq() {
		return lastSeq;
	}
	/**
	 * @param lastSeq the lastSeq to set
	 */
	public void setLastSeq(Integer lastSeq) {
		this.lastSeq = lastSeq;
	}
}
