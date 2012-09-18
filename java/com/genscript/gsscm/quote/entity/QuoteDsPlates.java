package com.genscript.gsscm.quote.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * QuoteDsPlates
 * @author Zhang Yong
 * add date 2011-11-02
 */
@Entity
@Table(name = "quote_ds_plates", catalog = "order")
public class QuoteDsPlates extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -132499370781933742L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer 	id;
	private String	 	name;
	private Integer 	nums;
	private Integer 	quoteNo;
	private Integer		layout;
	private Integer		custNo;
	private String 		remark;
	private String	 	code;
	
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
	 * @return the nums
	 */
	public Integer getNums() {
		return nums;
	}

	/**
	 * @param nums the nums to set
	 */
	public void setNums(Integer nums) {
		this.nums = nums;
	}

	/**
	 * @return the quoteNo
	 */
	public Integer getQuoteNo() {
		return quoteNo;
	}

	/**
	 * @param quoteNo the quoteNo to set
	 */
	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}

	/**
	 * @return the layout
	 */
	public Integer getLayout() {
		return layout;
	}

	/**
	 * @param layout the layout to set
	 */
	public void setLayout(Integer layout) {
		this.layout = layout;
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
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
