package com.genscript.gsscm.order.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * ORDER NOTES.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "order_notes", catalog="order")
public class OrderNote extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id")
	private Integer id;
	private Integer orderNo;
	private String type;
	private String description;
	private String docFlag;
	private Date noteDate;
	private String source;
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDocFlag() {
		return docFlag;
	}
	public void setDocFlag(String docFlag) {
		this.docFlag = docFlag;
	}
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
	 * @return the noteDate
	 */
	public Date getNoteDate() {
		return noteDate;
	}
	/**
	 * @param noteDate the noteDate to set
	 */
	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

}
