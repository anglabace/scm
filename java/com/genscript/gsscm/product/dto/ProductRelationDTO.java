package com.genscript.gsscm.product.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;
/**
 * Product的General Tab中Cross相关信息.
 * @author wangsf
 *
 */
@XmlType(name = "ProductRelationDTO", namespace = WsConstants.NS)
public class ProductRelationDTO {
	private Integer relationId;
	private Integer productId;
	private String relationType;
	private Integer rltProductId;
	private String lookupFlag;
	private String displayOnlyBo;
	private String mandatoryFlag;
	private Date effFrom;
	private Date effTo;
	private BigDecimal standardPrice;
	private String discount;
	private BigDecimal unitPrice;
	private String relateInfo;
	//其他业务会使用， 非ProductRelation表里的栏位.
	private String rltCatalogNo;
	private String rltName;
	private String name;
	private List<ProductRelationItemDTO> productRelationItemDTOList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ProductRelationItemDTO> getProductRelationItemDTOList() {
		return productRelationItemDTOList;
	}
	public void setProductRelationItemDTOList(
			List<ProductRelationItemDTO> productRelationItemDTOList) {
		this.productRelationItemDTOList = productRelationItemDTOList;
	}
	/**
	 * @return the relationId
	 */
	public Integer getRelationId() {
		return relationId;
	}
	/**
	 * @param relationId the relationId to set
	 */
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * @return the relationType
	 */
	public String getRelationType() {
		return relationType;
	}
	/**
	 * @param relationType the relationType to set
	 */
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	/**
	 * @return the rltProductId
	 */
	public Integer getRltProductId() {
		return rltProductId;
	}
	/**
	 * @param rltProductId the rltProductId to set
	 */
	public void setRltProductId(Integer rltProductId) {
		this.rltProductId = rltProductId;
	}
	/**
	 * @return the lookupFlag
	 */
	public String getLookupFlag() {
		return lookupFlag;
	}
	/**
	 * @param lookupFlag the lookupFlag to set
	 */
	public void setLookupFlag(String lookupFlag) {
		this.lookupFlag = lookupFlag;
	}
	/**
	 * @return the displayOnlyBo
	 */
	public String getDisplayOnlyBo() {
		return displayOnlyBo;
	}
	/**
	 * @param displayOnlyBo the displayOnlyBo to set
	 */
	public void setDisplayOnlyBo(String displayOnlyBo) {
		this.displayOnlyBo = displayOnlyBo;
	}
	/**
	 * @return the mandatoryFlag
	 */
	public String getMandatoryFlag() {
		return mandatoryFlag;
	}
	/**
	 * @param mandatoryFlag the mandatoryFlag to set
	 */
	public void setMandatoryFlag(String mandatoryFlag) {
		this.mandatoryFlag = mandatoryFlag;
	}
	/**
	 * @return the effFrom
	 */
	public Date getEffFrom() {
		return effFrom;
	}
	/**
	 * @param effFrom the effFrom to set
	 */
	public void setEffFrom(Date effFrom) {
		this.effFrom = effFrom;
	}
	/**
	 * @return the effTo
	 */
	public Date getEffTo() {
		return effTo;
	}
	/**
	 * @param effTo the effTo to set
	 */
	public void setEffTo(Date effTo) {
		this.effTo = effTo;
	}
	/**
	 * @return the standardPrice
	 */
	public BigDecimal getStandardPrice() {
		return standardPrice;
	}
	/**
	 * @param standardPrice the standardPrice to set
	 */
	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}
	/**
	 * @return the discount
	 */
	public String getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	/**
	 * @return the unitPrice
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the relateInfo
	 */
	public String getRelateInfo() {
		return relateInfo;
	}
	/**
	 * @param relateInfo the relateInfo to set
	 */
	public void setRelateInfo(String relateInfo) {
		this.relateInfo = relateInfo;
	}
	/**
	 * @return the rltCatalogNo
	 */
	public String getRltCatalogNo() {
		return rltCatalogNo;
	}
	/**
	 * @param rltCatalogNo the rltCatalogNo to set
	 */
	public void setRltCatalogNo(String rltCatalogNo) {
		this.rltCatalogNo = rltCatalogNo;
	}
	/**
	 * @return the rltName
	 */
	public String getRltName() {
		return rltName;
	}
	/**
	 * @param rltName the rltName to set
	 */
	public void setRltName(String rltName) {
		this.rltName = rltName;
	}
	
}
