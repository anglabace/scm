package com.genscript.gsscm.order.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderDsPlatesDTO
 * @author Zhang Yong
 * add date 2011-11-09
 *
 */
public class OrderDsPlatesDTO {
	private Integer 	id;
	private String	 	name;
	private Integer 	nums;
	private Integer 	orderNo;
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

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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
