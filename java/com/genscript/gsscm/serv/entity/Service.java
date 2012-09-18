package com.genscript.gsscm.serv.entity;

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
 * Service.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "service", catalog="product")
public class Service extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 851064787954492956L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer serviceId;
	private String catalogNo;
	private String name;
	private String fullName;
	private Integer serviceClsId;
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
	private String url;
	private String status;
	private Date publishDate;

    private String vtRatio;
    private String btRatio;
    private String oldCategory;

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

    /**
	 * @return the serviceId
	 */
	public Integer getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	/**
	 * @return the catalogNo
	 */
	public String getCatalogNo() {
		return catalogNo;
	}
	/**
	 * @param catalogNo the catalogNo to set
	 */
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
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
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	/**
	 * @return the shortDesc
	 */
	public String getShortDesc() {
		return shortDesc;
	}
	/**
	 * @param shortDesc the shortDesc to set
	 */
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	/**
	 * @return the longDesc
	 */
	public String getLongDesc() {
		return longDesc;
	}
	/**
	 * @param longDesc the longDesc to set
	 */
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}
	/**
	 * @return the synonyms
	 */
	public String getSynonyms() {
		return synonyms;
	}
	/**
	 * @param synonyms the synonyms to set
	 */
	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
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
	/**
	 * @return the size
	 */
	public Double getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(Double size) {
		this.size = size;
	}
	/**
	 * @return the uom
	 */
	public String getUom() {
		return uom;
	}
	/**
	 * @param uom the uom to set
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}
	/**
	 * @return the altSize
	 */
	public Double getAltSize() {
		return altSize;
	}
	/**
	 * @param altSize the altSize to set
	 */
	public void setAltSize(Double altSize) {
		this.altSize = altSize;
	}
	/**
	 * @return the altUom
	 */
	public String getAltUom() {
		return altUom;
	}
	/**
	 * @param altUom the altUom to set
	 */
	public void setAltUom(String altUom) {
		this.altUom = altUom;
	}
	/**
	 * @return the length
	 */
	public Double getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(Double length) {
		this.length = length;
	}
	/**
	 * @return the width
	 */
	public Double getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(Double width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public Double getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(Double height) {
		this.height = height;
	}
	/**
	 * @return the dimUom
	 */
	public String getDimUom() {
		return dimUom;
	}
	/**
	 * @param dimUom the dimUom to set
	 */
	public void setDimUom(String dimUom) {
		this.dimUom = dimUom;
	}
	/**
	 * @return the abst
	 */
	public String getAbst() {
		return abst;
	}
	/**
	 * @param abst the abst to set
	 */
	public void setAbst(String abst) {
		this.abst = abst;
	}
	/**
	 * @return the inventoryId
	 */
	public Integer getInventoryId() {
		return inventoryId;
	}
	/**
	 * @param inventoryId the inventoryId to set
	 */
	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}
	/**
	 * @return the purchasable
	 */
	public String getPurchasable() {
		return purchasable;
	}
	/**
	 * @param purchasable the purchasable to set
	 */
	public void setPurchasable(String purchasable) {
		this.purchasable = purchasable;
	}
	/**
	 * @return the shippable
	 */
	public String getShippable() {
		return shippable;
	}
	/**
	 * @param shippable the shippable to set
	 */
	public void setShippable(String shippable) {
		this.shippable = shippable;
	}
	/**
	 * @return the quotable
	 */
	public String getQuotable() {
		return quotable;
	}
	/**
	 * @param quotable the quotable to set
	 */
	public void setQuotable(String quotable) {
		this.quotable = quotable;
	}
	/**
	 * @return the sellable
	 */
	public String getSellable() {
		return sellable;
	}
	/**
	 * @param sellable the sellable to set
	 */
	public void setSellable(String sellable) {
		this.sellable = sellable;
	}
	/**
	 * @return the giftFlag
	 */
	public String getGiftFlag() {
		return giftFlag;
	}
	/**
	 * @param giftFlag the giftFlag to set
	 */
	public void setGiftFlag(String giftFlag) {
		this.giftFlag = giftFlag;
	}
	/**
	 * @return the returnable
	 */
	public String getReturnable() {
		return returnable;
	}
	/**
	 * @param returnable the returnable to set
	 */
	public void setReturnable(String returnable) {
		this.returnable = returnable;
	}
	/**
	 * @return the discountable
	 */
	public String getDiscountable() {
		return discountable;
	}
	/**
	 * @param discountable the discountable to set
	 */
	public void setDiscountable(String discountable) {
		this.discountable = discountable;
	}
	/**
	 * @return the invoiceable
	 */
	public String getInvoiceable() {
		return invoiceable;
	}
	/**
	 * @param invoiceable the invoiceable to set
	 */
	public void setInvoiceable(String invoiceable) {
		this.invoiceable = invoiceable;
	}
	/**
	 * @return the stockable
	 */
	public String getStockable() {
		return stockable;
	}
	/**
	 * @param stockable the stockable to set
	 */
	public void setStockable(String stockable) {
		this.stockable = stockable;
	}
	/**
	 * @return the taxable
	 */
	public String getTaxable() {
		return taxable;
	}
	/**
	 * @param taxable the taxable to set
	 */
	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}
	/**
	 * @return the federalTaxCls
	 */
	public Integer getFederalTaxCls() {
		return federalTaxCls;
	}
	/**
	 * @param federalTaxCls the federalTaxCls to set
	 */
	public void setFederalTaxCls(Integer federalTaxCls) {
		this.federalTaxCls = federalTaxCls;
	}
	/**
	 * @return the stateTaxCls
	 */
	public Integer getStateTaxCls() {
		return stateTaxCls;
	}
	/**
	 * @param stateTaxCls the stateTaxCls to set
	 */
	public void setStateTaxCls(Integer stateTaxCls) {
		this.stateTaxCls = stateTaxCls;
	}
	/**
	 * @return the countryTaxCls
	 */
	public Integer getCountryTaxCls() {
		return countryTaxCls;
	}
	/**
	 * @param countryTaxCls the countryTaxCls to set
	 */
	public void setCountryTaxCls(Integer countryTaxCls) {
		this.countryTaxCls = countryTaxCls;
	}
	/**
	 * @return the breakdownFlag
	 */
	public String getBreakdownFlag() {
		return breakdownFlag;
	}
	/**
	 * @param breakdownFlag the breakdownFlag to set
	 */
	public void setBreakdownFlag(String breakdownFlag) {
		this.breakdownFlag = breakdownFlag;
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
	 * @return the documentFlag
	 */
	public String getDocumentFlag() {
		return documentFlag;
	}
	/**
	 * @param documentFlag the documentFlag to set
	 */
	public void setDocumentFlag(String documentFlag) {
		this.documentFlag = documentFlag;
	}
	/**
	 * @return the lotControlFlag
	 */
	public String getLotControlFlag() {
		return lotControlFlag;
	}
	/**
	 * @param lotControlFlag the lotControlFlag to set
	 */
	public void setLotControlFlag(String lotControlFlag) {
		this.lotControlFlag = lotControlFlag;
	}
	/**
	 * @return the serialControlFlag
	 */
	public String getSerialControlFlag() {
		return serialControlFlag;
	}
	/**
	 * @param serialControlFlag the serialControlFlag to set
	 */
	public void setSerialControlFlag(String serialControlFlag) {
		this.serialControlFlag = serialControlFlag;
	}
	/**
	 * @return the versionNum
	 */
	public Integer getVersionNum() {
		return versionNum;
	}
	/**
	 * @param versionNum the versionNum to set
	 */
	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}
	/**
	 * @return the upcCode
	 */
	public String getUpcCode() {
		return upcCode;
	}
	/**
	 * @param upcCode the upcCode to set
	 */
	public void setUpcCode(String upcCode) {
		this.upcCode = upcCode;
	}
	/**
	 * @return the prefWarehouse
	 */
	public Integer getPrefWarehouse() {
		return prefWarehouse;
	}
	/**
	 * @param prefWarehouse the prefWarehouse to set
	 */
	public void setPrefWarehouse(Integer prefWarehouse) {
		this.prefWarehouse = prefWarehouse;
	}
	/**
	 * @return the altWarehouseFlag
	 */
	public String getAltWarehouseFlag() {
		return altWarehouseFlag;
	}
	/**
	 * @param altWarehouseFlag the altWarehouseFlag to set
	 */
	public void setAltWarehouseFlag(String altWarehouseFlag) {
		this.altWarehouseFlag = altWarehouseFlag;
	}
	/**
	 * @return the prefStorage
	 */
	public Integer getPrefStorage() {
		return prefStorage;
	}
	/**
	 * @param prefStorage the prefStorage to set
	 */
	public void setPrefStorage(Integer prefStorage) {
		this.prefStorage = prefStorage;
	}
	/**
	 * @return the saftyStock
	 */
	public Integer getSaftyStock() {
		return saftyStock;
	}
	/**
	 * @param saftyStock the saftyStock to set
	 */
	public void setSaftyStock(Integer saftyStock) {
		this.saftyStock = saftyStock;
	}
	/**
	 * @return the minOrderQty
	 */
	public Integer getMinOrderQty() {
		return minOrderQty;
	}
	/**
	 * @param minOrderQty the minOrderQty to set
	 */
	public void setMinOrderQty(Integer minOrderQty) {
		this.minOrderQty = minOrderQty;
	}
	/**
	 * @return the unitCost
	 */
	public BigDecimal getUnitCost() {
		return unitCost;
	}
	/**
	 * @param unitCost the unitCost to set
	 */
	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
	}
	/**
	 * @return the sellingPriceCmsn
	 */
	public BigDecimal getSellingPriceCmsn() {
		return sellingPriceCmsn;
	}
	/**
	 * @param sellingPriceCmsn the sellingPriceCmsn to set
	 */
	public void setSellingPriceCmsn(BigDecimal sellingPriceCmsn) {
		this.sellingPriceCmsn = sellingPriceCmsn;
	}
	/**
	 * @return the grossProfitCmsn
	 */
	public BigDecimal getGrossProfitCmsn() {
		return grossProfitCmsn;
	}
	/**
	 * @param grossProfitCmsn the grossProfitCmsn to set
	 */
	public void setGrossProfitCmsn(BigDecimal grossProfitCmsn) {
		this.grossProfitCmsn = grossProfitCmsn;
	}
	/**
	 * @return the unitRateCmsn
	 */
	public BigDecimal getUnitRateCmsn() {
		return unitRateCmsn;
	}
	/**
	 * @param unitRateCmsn the unitRateCmsn to set
	 */
	public void setUnitRateCmsn(BigDecimal unitRateCmsn) {
		this.unitRateCmsn = unitRateCmsn;
	}
	/**
	 * @return the returnPoints
	 */
	public Integer getReturnPoints() {
		return returnPoints;
	}
	/**
	 * @param returnPoints the returnPoints to set
	 */
	public void setReturnPoints(Integer returnPoints) {
		this.returnPoints = returnPoints;
	}
	/**
	 * @return the priceByPoints
	 */
	public Integer getPriceByPoints() {
		return priceByPoints;
	}
	/**
	 * @param priceByPoints the priceByPoints to set
	 */
	public void setPriceByPoints(Integer priceByPoints) {
		this.priceByPoints = priceByPoints;
	}
	/**
	 * @return the noticeSendType
	 */
	public String getNoticeSendType() {
		return noticeSendType;
	}
	/**
	 * @param noticeSendType the noticeSendType to set
	 */
	public void setNoticeSendType(String noticeSendType) {
		this.noticeSendType = noticeSendType;
	}
	/**
	 * @return the noticeGenerateTime
	 */
	public String getNoticeGenerateTime() {
		return noticeGenerateTime;
	}
	/**
	 * @param noticeGenerateTime the noticeGenerateTime to set
	 */
	public void setNoticeGenerateTime(String noticeGenerateTime) {
		this.noticeGenerateTime = noticeGenerateTime;
	}
	/**
	 * @return the customerInfo
	 */
	public String getCustomerInfo() {
		return customerInfo;
	}
	/**
	 * @param customerInfo the customerInfo to set
	 */
	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}
	/**
	 * @return the leadTime
	 */
	public Integer getLeadTime() {
		return leadTime;
	}
	/**
	 * @param leadTime the leadTime to set
	 */
	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}
	/**
	 * @return the sellingNote
	 */
	public String getSellingNote() {
		return sellingNote;
	}
	/**
	 * @param sellingNote the sellingNote to set
	 */
	public void setSellingNote(String sellingNote) {
		this.sellingNote = sellingNote;
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
	public Integer getServiceClsId() {
		return serviceClsId;
	}
	public void setServiceClsId(Integer serviceClsId) {
		this.serviceClsId = serviceClsId;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getOldCategory() {
		return oldCategory;
	}

	public void setOldCategory(String oldCategory) {
		this.oldCategory = oldCategory;
	}
	
}
