package com.genscript.gsscm.quote.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * QuoteDsPlatesDTO
 * @author Zhang Yong
 * add date 2011-11-14
 *
 */
public class QuoteDsPlatesDTO {
	private Integer 	id;
	private String	 	name;
	private Integer 	nums;
	private Integer 	quoteNo;
	private Integer		layout;
	private Integer		custNo;
	private String 		remark;
	private String	 	code;

	private String		sessPlateId;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

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

	public Integer getNums() {
		return nums;
	}

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

	public Integer getLayout() {
		return layout;
	}

	public void setLayout(Integer layout) {
		this.layout = layout;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSessPlateId() {
		return sessPlateId;
	}

	public void setSessPlateId(String sessPlateId) {
		this.sessPlateId = sessPlateId;
	}
}
