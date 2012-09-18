package com.genscript.gsscm.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "OrderReturnDTO", namespace = WsConstants.NS)
public class OrderReturnDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 679752891076150202L;
	private Integer rmaNo;
	private Integer orderNo;
	private String status;
	private Date exprDate;
	private Integer processedBy;
	private Date processDate;
	private BigDecimal shipRefund;
	private BigDecimal totalRefund;
	private String note;
	protected Date creationDate;
	protected Integer createdBy;
	protected Date modifyDate;
	protected Integer modifiedBy;
	private String createUser;
	private String processUser;
	private List<OrderReturnItemDTO> returnItemList;
	private List<Integer> delItemIdList;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the rmaNo
	 */
	public Integer getRmaNo() {
		return rmaNo;
	}
	/**
	 * @param rmaNo the rmaNo to set
	 */
	public void setRmaNo(Integer rmaNo) {
		this.rmaNo = rmaNo;
	}
	/**
	 * @return the orderNo
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the exprDate
	 */
	public Date getExprDate() {
		return exprDate;
	}
	/**
	 * @param exprDate the exprDate to set
	 */
	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}
	/**
	 * @return the processedBy
	 */
	public Integer getProcessedBy() {
		return processedBy;
	}
	/**
	 * @param processedBy the processedBy to set
	 */
	public void setProcessedBy(Integer processedBy) {
		this.processedBy = processedBy;
	}

	/**
	 * @return the shipRefund
	 */
	public BigDecimal getShipRefund() {
		return shipRefund;
	}
	/**
	 * @param shipRefund the shipRefund to set
	 */
	public void setShipRefund(BigDecimal shipRefund) {
		this.shipRefund = shipRefund;
	}
	/**
	 * @return the totalRefund
	 */
	public BigDecimal getTotalRefund() {
		return totalRefund;
	}
	/**
	 * @param totalRefund the totalRefund to set
	 */
	public void setTotalRefund(BigDecimal totalRefund) {
		this.totalRefund = totalRefund;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the returnItemList
	 */
	public List<OrderReturnItemDTO> getReturnItemList() {
		return returnItemList;
	}
	/**
	 * @param returnItemList the returnItemList to set
	 */
	public void setReturnItemList(List<OrderReturnItemDTO> returnItemList) {
		this.returnItemList = returnItemList;
	}
	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * @return the processUser
	 */
	public String getProcessUser() {
		return processUser;
	}
	/**
	 * @param processUser the processUser to set
	 */
	public void setProcessUser(String processUser) {
		this.processUser = processUser;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * @return the modifiedBy
	 */
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	/**
	 * @return the delItemIdList
	 */
	public List<Integer> getDelItemIdList() {
		return delItemIdList;
	}
	/**
	 * @param delItemIdList the delItemIdList to set
	 */
	public void setDelItemIdList(List<Integer> delItemIdList) {
		this.delItemIdList = delItemIdList;
	}
	/**
	 * @return the processDate
	 */
	public Date getProcessDate() {
		return processDate;
	}
	/**
	 * @param processDate the processDate to set
	 */
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
}
