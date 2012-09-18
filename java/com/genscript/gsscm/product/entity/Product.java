package com.genscript.gsscm.product.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * PRODUCT.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "product", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 851064787954492956L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;
	private String catalogNo;
	private String name;
	private String fullName;
	private Integer productClsId;
	private String shortDesc;
	private String longDesc;
	private String synonyms;
	private String qtyUom;
	private Double size;
	private String uom;
	private Double altSize;
	private String altUom;
	private Double length;
	private Double width;
	private Double height;
	private String 	dimUom;
	@Column(name = "abstract")
	private String abst;
	private Integer inventoryId;
	private String purchasable;
	private String shippable;
	private String quotable;
	private String sellable;
	private String giftFlag;
	private String returnable;
	private String discountable;
	private String invoiceable;
	private String stockable;
	private String taxable;
	private Integer federalTaxCls;
	private Integer stateTaxCls;
	private Integer countryTaxCls;
	private String breakdownFlag;
	private String compositeFlag;
	private String documentFlag;
	private String lotControlFlag;
	private String serialControlFlag;
	private Integer versionNum;
	private String upcCode;
	private Integer prefWarehouse;
	private String altWarehouseFlag;
	private Integer prefStorage;
	private Integer saftyStock;
	private Integer minOrderQty;
	private Integer retestDays;
	private Integer expirationDays;
	private BigDecimal unitCost;
	private BigDecimal sellingPriceCmsn;
	private BigDecimal grossProfitCmsn;
	private BigDecimal unitRateCmsn;
	private Integer returnPoints;
	private Integer priceByPoints;
	private String noticeSendType;
	private String noticeGenerateTime;
	private String customerInfo;
	private Integer leadTime;
	private String sellingNote;
	private String status;
	private Date publishDate;
	@Column(insertable=true,updatable=false)
	private Date creationDate;
	@Column(insertable=true,updatable=false)
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
    private String vtRatio;
    private String btRatio;

	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getProductClsId() {
		return productClsId;
	}
	public void setProductClsId(Integer productClsId) {
		this.productClsId = productClsId;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getLongDesc() {
		return longDesc;
	}
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}
	public String getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public Double getAltSize() {
		return altSize;
	}
	public void setAltSize(Double altSize) {
		this.altSize = altSize;
	}
	public String getAltUom() {
		return altUom;
	}
	public void setAltUom(String altUom) {
		this.altUom = altUom;
	}
	public String getAbst() {
		return abst;
	}
	public void setAbst(String abst) {
		this.abst = abst;
	}
	public Integer getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getPurchasable() {
		return purchasable;
	}
	public void setPurchasable(String purchasable) {
		this.purchasable = purchasable;
	}
	public String getQuotable() {
		return quotable;
	}
	public void setQuotable(String quotable) {
		this.quotable = quotable;
	}
	
	public String getReturnable() {
		return returnable;
	}
	public void setReturnable(String returnable) {
		this.returnable = returnable;
	}
	public String getDiscountable() {
		return discountable;
	}
	public void setDiscountable(String discountable) {
		this.discountable = discountable;
	}
	public String getInvoiceable() {
		return invoiceable;
	}
	public void setInvoiceable(String invoiceable) {
		this.invoiceable = invoiceable;
	}
	public String getStockable() {
		return stockable;
	}
	public void setStockable(String stockable) {
		this.stockable = stockable;
	}
	public String getTaxable() {
		return taxable;
	}
	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}
	public String getGiftFlag() {
		return giftFlag;
	}
	public void setGiftFlag(String giftFlag) {
		this.giftFlag = giftFlag;
	}
	public Integer getFederalTaxCls() {
		return federalTaxCls;
	}
	public void setFederalTaxCls(Integer federalTaxCls) {
		this.federalTaxCls = federalTaxCls;
	}
	public Integer getStateTaxCls() {
		return stateTaxCls;
	}
	public void setStateTaxCls(Integer stateTaxCls) {
		this.stateTaxCls = stateTaxCls;
	}
	public Integer getCountryTaxCls() {
		return countryTaxCls;
	}
	public void setCountryTaxCls(Integer countryTaxCls) {
		this.countryTaxCls = countryTaxCls;
	}
	public Integer getPrefWarehouse() {
		return prefWarehouse;
	}
	public void setPrefWarehouse(Integer prefWarehouse) {
		this.prefWarehouse = prefWarehouse;
	}
	public String getAltWarehouseFlag() {
		return altWarehouseFlag;
	}
	public void setAltWarehouseFlag(String altWarehouseFlag) {
		this.altWarehouseFlag = altWarehouseFlag;
	}
	public Integer getPrefStorage() {
		return prefStorage;
	}
	public void setPrefStorage(Integer prefStorage) {
		this.prefStorage = prefStorage;
	}
	public Integer getSaftyStock() {
		return saftyStock;
	}
	public void setSaftyStock(Integer saftyStock) {
		this.saftyStock = saftyStock;
	}
	public Integer getMinOrderQty() {
		return minOrderQty;
	}
	public void setMinOrderQty(Integer minOrderQty) {
		this.minOrderQty = minOrderQty;
	}
	public BigDecimal getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
	}
	public BigDecimal getSellingPriceCmsn() {
		return sellingPriceCmsn;
	}
	public void setSellingPriceCmsn(BigDecimal sellingPriceCmsn) {
		this.sellingPriceCmsn = sellingPriceCmsn;
	}
	public BigDecimal getGrossProfitCmsn() {
		return grossProfitCmsn;
	}
	public void setGrossProfitCmsn(BigDecimal grossProfitCmsn) {
		this.grossProfitCmsn = grossProfitCmsn;
	}
	public BigDecimal getUnitRateCmsn() {
		return unitRateCmsn;
	}
	public void setUnitRateCmsn(BigDecimal unitRateCmsn) {
		this.unitRateCmsn = unitRateCmsn;
	}
	public Integer getReturnPoints() {
		return returnPoints;
	}
	public void setReturnPoints(Integer returnPoints) {
		this.returnPoints = returnPoints;
	}
	public Integer getPriceByPoints() {
		return priceByPoints;
	}
	public void setPriceByPoints(Integer priceByPoints) {
		this.priceByPoints = priceByPoints;
	}
	public String getNoticeSendType() {
		return noticeSendType;
	}
	public void setNoticeSendType(String noticeSendType) {
		this.noticeSendType = noticeSendType;
	}
	public String getNoticeGenerateTime() {
		return noticeGenerateTime;
	}
	public void setNoticeGenerateTime(String noticeGenerateTime) {
		this.noticeGenerateTime = noticeGenerateTime;
	}
	public String getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}
	public Integer getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}
	public Double getLength() {
		return length;
	}
	public void setLength(Double length) {
		this.length = length;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public String getDimUom() {
		return dimUom;
	}
	public void setDimUom(String dimUom) {
		this.dimUom = dimUom;
	}
	public String getShippable() {
		return shippable;
	}
	public void setShippable(String shippable) {
		this.shippable = shippable;
	}
	public String getSellable() {
		return sellable;
	}
	public void setSellable(String sellable) {
		this.sellable = sellable;
	}
	public String getBreakdownFlag() {
		return breakdownFlag;
	}
	public void setBreakdownFlag(String breakdownFlag) {
		this.breakdownFlag = breakdownFlag;
	}

	public String getDocumentFlag() {
		return documentFlag;
	}
	public void setDocumentFlag(String documentFlag) {
		this.documentFlag = documentFlag;
	}
	public String getLotControlFlag() {
		return lotControlFlag;
	}
	public void setLotControlFlag(String lotControlFlag) {
		this.lotControlFlag = lotControlFlag;
	}
	public String getSerialControlFlag() {
		return serialControlFlag;
	}
	public void setSerialControlFlag(String serialControlFlag) {
		this.serialControlFlag = serialControlFlag;
	}
	public String getUpcCode() {
		return upcCode;
	}
	public void setUpcCode(String upcCode) {
		this.upcCode = upcCode;
	}

	public String getSellingNote() {
		return sellingNote;
	}
	public void setSellingNote(String sellingNote) {
		this.sellingNote = sellingNote;
	}
	public Integer getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	public Integer getRetestDays() {
		return retestDays;
	}
	public void setRetestDays(Integer retestDays) {
		this.retestDays = retestDays;
	}
	public Integer getExpirationDays() {
		return expirationDays;
	}
	public void setExpirationDays(Integer expirationDays) {
		this.expirationDays = expirationDays;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the compositeFlag
	 */
	public String getCompositeFlag() {
		return compositeFlag;
	}
	/**
	 * @param compositeFlag the compositeFlag to set
	 */
	public void setCompositeFlag(String compositeFlag) {
		this.compositeFlag = compositeFlag;
	}
	/**
	 * @return the qtyUom
	 */
	public String getQtyUom() {
		return qtyUom;
	}
	/**
	 * @param qtyUom the qtyUom to set
	 */
	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

    public String getVtRatio() {
        return vtRatio;
    }

    public void setVtRatio(String vtRatio) {
        this.vtRatio = vtRatio;
    }

    public String getBtRatio() {
        return btRatio;
    }

    public void setBtRatio(String btRatio) {
        this.btRatio = btRatio;
    }
}
