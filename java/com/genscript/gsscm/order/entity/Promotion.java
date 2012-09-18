package com.genscript.gsscm.order.entity;

import java.math.BigDecimal;
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
 * PROMOTION.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "promotion", catalog="order")
public class Promotion extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prmt_id")
	private Integer prmtId;
	private String prmtCode;
	private String description;
	private String applyType;
	private String cumulateFlag;
	private String discType;
	private BigDecimal discPercent;
	private BigDecimal discAmount;
	private Integer discProdQty;
	private String discProd;
	private BigDecimal discPrice;
	private BigDecimal specialDiscPercent;
	private BigDecimal discCatePercent;
	private String discCategory;
	private String discCategoryType;
	private BigDecimal discOrderTotal;
	private BigDecimal discCateAmount;
	private BigDecimal cateTotalAmount;
	private BigDecimal orderTotalMin2;
	private BigDecimal orderTotalMin3;
	private String discNotation;
	private BigDecimal orderTotalMin1;
//	private BigDecimal orderTotalMax;
	private Integer custNo;
	private Integer rfmValue;
	private Integer orderSource;
	private String orderCatalogNo;
	private Date orderEffFrom;
	private Date orderEffTo;
	private String invoiceMsg;
	private String shipDiscFlag;
	private BigDecimal shipAmount;
	private Integer shipMethod;
	private BigDecimal shipOrderTotal;
	private String status;
	private Integer issuer;
	private Date issueDate;
	private Integer confirmer;
	private Date confirmDate;
	@Column(name = "company_id")
	private Integer gsCoId;
	private String custPriorityLvl;
	private Integer custJobRole;
	private String custCountry;
	private Integer custSalesTerritory;
	private String orderItemCategory;
	private String orderItemCategoryType;
	
	public Integer getPrmtId() {
		return prmtId;
	}
	public void setPrmtId(Integer prmtId) {
		this.prmtId = prmtId;
	}
	public String getPrmtCode() {
		return prmtCode;
	}
	public void setPrmtCode(String prmtCode) {
		this.prmtCode = prmtCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getCumulateFlag() {
		return cumulateFlag;
	}
	public void setCumulateFlag(String cumulateFlag) {
		this.cumulateFlag = cumulateFlag;
	}
	public String getDiscType() {
		return discType;
	}
	public void setDiscType(String discType) {
		this.discType = discType;
	}
	public BigDecimal getDiscPercent() {
		return discPercent;
	}
	public void setDiscPercent(BigDecimal discPercent) {
		this.discPercent = discPercent;
	}
	public BigDecimal getDiscAmount() {
		return discAmount;
	}
	public void setDiscAmount(BigDecimal discAmount) {
		this.discAmount = discAmount;
	}
	public Integer getDiscProdQty() {
		return discProdQty;
	}
	public void setDiscProdQty(Integer discProdQty) {
		this.discProdQty = discProdQty;
	}
	public String getDiscProd() {
		return discProd;
	}
	public void setDiscProd(String discProd) {
		this.discProd = discProd;
	}
	public BigDecimal getDiscPrice() {
		return discPrice;
	}
	public void setDiscPrice(BigDecimal discPrice) {
		this.discPrice = discPrice;
	}
	public String getDiscNotation() {
		return discNotation;
	}
	public void setDiscNotation(String discNotation) {
		this.discNotation = discNotation;
	}
	
	public BigDecimal getOrderTotalMin2() {
		return orderTotalMin2;
	}
	public void setOrderTotalMin2(BigDecimal orderTotalMin2) {
		this.orderTotalMin2 = orderTotalMin2;
	}
	public BigDecimal getOrderTotalMin3() {
		return orderTotalMin3;
	}
	public void setOrderTotalMin3(BigDecimal orderTotalMin3) {
		this.orderTotalMin3 = orderTotalMin3;
	}
	public BigDecimal getOrderTotalMin1() {
		return orderTotalMin1;
	}
	public void setOrderTotalMin1(BigDecimal orderTotalMin1) {
		this.orderTotalMin1 = orderTotalMin1;
	}
	public String getCustPriorityLvl() {
		return custPriorityLvl;
	}
	public void setCustPriorityLvl(String custPriorityLvl) {
		this.custPriorityLvl = custPriorityLvl;
	}
	public Integer getCustJobRole() {
		return custJobRole;
	}
	public void setCustJobRole(Integer custJobRole) {
		this.custJobRole = custJobRole;
	}
	public String getCustCountry() {
		return custCountry;
	}
	public void setCustCountry(String custCountry) {
		this.custCountry = custCountry;
	}
	public Integer getCustSalesTerritory() {
		return custSalesTerritory;
	}
	public void setCustSalesTerritory(Integer custSalesTerritory) {
		this.custSalesTerritory = custSalesTerritory;
	}
	public String getOrderItemCategory() {
		return orderItemCategory;
	}
	public void setOrderItemCategory(String orderItemCategory) {
		this.orderItemCategory = orderItemCategory;
	}
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public Integer getRfmValue() {
		return rfmValue;
	}
	public void setRfmValue(Integer rfmValue) {
		this.rfmValue = rfmValue;
	}
	public Integer getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}
	public String getOrderCatalogNo() {
		return orderCatalogNo;
	}
	public void setOrderCatalogNo(String orderCatalogNo) {
		this.orderCatalogNo = orderCatalogNo;
	}
	public Date getOrderEffFrom() {
		return orderEffFrom;
	}
	public void setOrderEffFrom(Date orderEffFrom) {
		this.orderEffFrom = orderEffFrom;
	}
	public Date getOrderEffTo() {
		return orderEffTo;
	}
	public void setOrderEffTo(Date orderEffTo) {
		this.orderEffTo = orderEffTo;
	}
	public String getInvoiceMsg() {
		return invoiceMsg;
	}
	public void setInvoiceMsg(String invoiceMsg) {
		this.invoiceMsg = invoiceMsg;
	}
	public String getShipDiscFlag() {
		return shipDiscFlag;
	}
	public void setShipDiscFlag(String shipDiscFlag) {
		this.shipDiscFlag = shipDiscFlag;
	}
	public BigDecimal getShipAmount() {
		return shipAmount;
	}
	public void setShipAmount(BigDecimal shipAmount) {
		this.shipAmount = shipAmount;
	}
	public Integer getShipMethod() {
		return shipMethod;
	}
	public void setShipMethod(Integer shipMethod) {
		this.shipMethod = shipMethod;
	}
	public BigDecimal getShipOrderTotal() {
		return shipOrderTotal;
	}
	public void setShipOrderTotal(BigDecimal shipOrderTotal) {
		this.shipOrderTotal = shipOrderTotal;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getIssuer() {
		return issuer;
	}
	public void setIssuer(Integer issuer) {
		this.issuer = issuer;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Integer getConfirmer() {
		return confirmer;
	}
	public void setConfirmer(Integer confirmer) {
		this.confirmer = confirmer;
	}
	public Date getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	public Integer getGsCoId() {
		return gsCoId;
	}
	public void setGsCoId(Integer gsCoId) {
		this.gsCoId = gsCoId;
	}
	public BigDecimal getSpecialDiscPercent() {
		return specialDiscPercent;
	}
	public void setSpecialDiscPercent(BigDecimal specialDiscPercent) {
		this.specialDiscPercent = specialDiscPercent;
	}
	public BigDecimal getDiscCatePercent() {
		return discCatePercent;
	}
	public void setDiscCatePercent(BigDecimal discCatePercent) {
		this.discCatePercent = discCatePercent;
	}
	public String getDiscCategory() {
		return discCategory;
	}
	public void setDiscCategory(String discCategory) {
		this.discCategory = discCategory;
	}
	public BigDecimal getDiscOrderTotal() {
		return discOrderTotal;
	}
	public void setDiscOrderTotal(BigDecimal discOrderTotal) {
		this.discOrderTotal = discOrderTotal;
	}
	public BigDecimal getDiscCateAmount() {
		return discCateAmount;
	}
	public void setDiscCateAmount(BigDecimal discCateAmount) {
		this.discCateAmount = discCateAmount;
	}
	public BigDecimal getCateTotalAmount() {
		return cateTotalAmount;
	}
	public void setCateTotalAmount(BigDecimal cateTotalAmount) {
		this.cateTotalAmount = cateTotalAmount;
	}
	public String getDiscCategoryType() {
		return discCategoryType;
	}
	public void setDiscCategoryType(String discCategoryType) {
		this.discCategoryType = discCategoryType;
	}
	public String getOrderItemCategoryType() {
		return orderItemCategoryType;
	}
	public void setOrderItemCategoryType(String orderItemCategoryType) {
		this.orderItemCategoryType = orderItemCategoryType;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
