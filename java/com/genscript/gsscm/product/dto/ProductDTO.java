package com.genscript.gsscm.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.product.entity.Antibody;
import com.genscript.gsscm.product.entity.Chemical;
import com.genscript.gsscm.product.entity.Enzyme;
import com.genscript.gsscm.product.entity.Gene;
import com.genscript.gsscm.product.entity.Kit;
import com.genscript.gsscm.product.entity.Molecule;
import com.genscript.gsscm.product.entity.Oligo;
import com.genscript.gsscm.product.entity.Peptide;
import com.genscript.gsscm.product.entity.Documents;
import com.genscript.gsscm.product.entity.ProductExtendedInfo;
import com.genscript.gsscm.product.entity.ProductReference;
import com.genscript.gsscm.product.entity.ProductSpecialPrice;
import com.genscript.gsscm.product.entity.Protein;
import com.genscript.gsscm.product.entity.RestrictShip;
import com.genscript.gsscm.product.entity.SDVector;
import com.genscript.gsscm.product.entity.ShipCondition;
import com.genscript.gsscm.product.entity.StorageCondition;
import com.genscript.gsscm.purchase.dto.VendorProductDTO;

@XmlType(name = "ProductDTO", namespace = WsConstants.NS)
public class ProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5157507142592851817L;
	// private Integer productId;
	// private String catalogNo;
	// private String name;
	// private String fullName;
	// private Integer type;
	// private String shortDesc;
	// private Double size;
	// private String uom;
	// private Double listPrice;
	// private Double unitPrice;
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
	private String dimUom;
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
	private Integer retestDays;
	private Integer expirationDays;
	private String sellingNote;
	private String status;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	private Date publishDate;
    private String vtRatio;
    private String btRatio;

	private String modifyByText;
	private String createdByText;
	private ShipCondition shipCondition;
	private StorageCondition storageCondition;
	private List<IntermediateDTO> intmdList;
	private List<Integer> delIntmdIdList;
	private List<ComponentDTO> componentList;
	private List<Integer> delComIdList;
	private List<RestrictShip> restrictShipList;
	private List<Integer> delRestrictShipIdList;
	private List<ProductSpecialPrice> specialPriceList;
	private List<Integer> delSpecialPriceIdList;
	private List<VendorProductDTO> vendorProductList;
	private List<Integer> delVendorProductIdList;
	private ProductExtendedInfo productExtendedInfo;
	private List<Integer> delMoreInfo;
	//product more info product file upload
	private List<Documents> productDocumentList;
	//product more info product file del
	private List<Integer> delProductDocumentList;
 	//Product的General Tab中Cross相关信息.
	private List<ProductRelationDTO> pdtRelationList;
	private List<ProductPriceDTO> priceList;
	private List<String> delPriceIdList;
	//Product misc Tab的一些数据.
	private RoyaltyProductDTO royaltyProduct;
	//ADDED BUSINESS PROPERTIES
	private String nameApprove;
	private String nameReason;
	private String statusApprove;
	private String statusReason;
	private String unitCostApprove;
	private String unitCostReason;

	private String nameNewVal;
	private String nameOldVal;
	private String statusNewVal;
	private String statusOldVal;

	 /*
     * type 取值为 antibody,enzyme,chemical,kit,molecule,oligo,protein,gene,peptide.
     */
	private String type;
    private Antibody antibody;
	private Enzyme enzyme;
	private Chemical chemical;
	private Kit kit;
	private Molecule molecule;
	private Oligo oligo;
	private Protein protein;
	private Gene gene;
	private Peptide peptide;
	private SDVector sdvector;
	
	/*
	 * product Reference
	 */
	private List<ProductReference> productReferenceList;
	private List<Integer> delReferenceList;
	
	private Integer categoryId;
	
	public ProductDTO(){
		this.shipCondition = new ShipCondition();
		this.storageCondition	= new StorageCondition();
		this.antibody = new Antibody();
		this.enzyme = new Enzyme();
		this.chemical = new Chemical();
		this.kit = new Kit();
		this.molecule = new Molecule();
		this.oligo = new Oligo();
		this.protein = new Protein();
		this.gene = new Gene();
		this.peptide = new Peptide();
		unitCost = new BigDecimal(0);
		sellingPriceCmsn = new BigDecimal(0);
		grossProfitCmsn = new BigDecimal(0);
		unitRateCmsn = new BigDecimal(0);
		this.sdvector=new SDVector();
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

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	
	public String getType() {
		return type;
	}



    public void setType(String type) {
		this.type = type;
	}


	public Antibody getAntibody() {
		return antibody;
	}


	public void setAntibody(Antibody antibody) {
		this.antibody = antibody;
	}


	public SDVector getSdvector() {
		return sdvector;
	}

	public void setSdvector(SDVector sdvector) {
		this.sdvector = sdvector;
	}

	public Enzyme getEnzyme() {
		return enzyme;
	}


	public void setEnzyme(Enzyme enzyme) {
		this.enzyme = enzyme;
	}


	public Chemical getChemical() {
		return chemical;
	}


	public void setChemical(Chemical chemical) {
		this.chemical = chemical;
	}


	public Kit getKit() {
		return kit;
	}


	public void setKit(Kit kit) {
		this.kit = kit;
	}


	public Molecule getMolecule() {
		return molecule;
	}


	public void setMolecule(Molecule molecule) {
		this.molecule = molecule;
	}


	public Oligo getOligo() {
		return oligo;
	}


	public void setOligo(Oligo oligo) {
		this.oligo = oligo;
	}


	public Protein getProtein() {
		return protein;
	}


	public void setProtein(Protein protein) {
		this.protein = protein;
	}


	public Gene getGene() {
		return gene;
	}


	public void setGene(Gene gene) {
		this.gene = gene;
	}


	public Peptide getPeptide() {
		return peptide;
	}


	public void setPeptide(Peptide peptide) {
		this.peptide = peptide;
	}


	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * @return the catalogNo
	 */
	public String getCatalogNo() {
		return catalogNo;
	}

	/**
	 * @param catalogNo
	 *            the catalogNo to set
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
	 * @param name
	 *            the name to set
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
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the productClsId
	 */
	public Integer getProductClsId() {
		return productClsId;
	}

	/**
	 * @param productClsId
	 *            the productClsId to set
	 */
	public void setProductClsId(Integer productClsId) {
		this.productClsId = productClsId;
	}

	/**
	 * @return the shortDesc
	 */
	public String getShortDesc() {
		return shortDesc;
	}

	/**
	 * @param shortDesc
	 *            the shortDesc to set
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
	 * @param longDesc
	 *            the longDesc to set
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
	 * @param synonyms
	 *            the synonyms to set
	 */
	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}

	/**
	 * @return the size
	 */
	public Double getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
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
	 * @param uom
	 *            the uom to set
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
	 * @param altSize
	 *            the altSize to set
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
	 * @param altUom
	 *            the altUom to set
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
	 * @param length
	 *            the length to set
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
	 * @param width
	 *            the width to set
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
	 * @param height
	 *            the height to set
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
	 * @param dimUom
	 *            the dimUom to set
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
	 * @param abst
	 *            the abst to set
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
	 * @param inventoryId
	 *            the inventoryId to set
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
	 * @param purchasable
	 *            the purchasable to set
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
	 * @param shippable
	 *            the shippable to set
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
	 * @param quotable
	 *            the quotable to set
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
	 * @param sellable
	 *            the sellable to set
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
	 * @param giftFlag
	 *            the giftFlag to set
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
	 * @param returnable
	 *            the returnable to set
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
	 * @param discountable
	 *            the discountable to set
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
	 * @param invoiceable
	 *            the invoiceable to set
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
	 * @param stockable
	 *            the stockable to set
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
	 * @param taxable
	 *            the taxable to set
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
	 * @param federalTaxCls
	 *            the federalTaxCls to set
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
	 * @param stateTaxCls
	 *            the stateTaxCls to set
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
	 * @param countryTaxCls
	 *            the countryTaxCls to set
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
	 * @param breakdownFlag
	 *            the breakdownFlag to set
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
	 * @param compositeFlag
	 *            the compositeFlag to set
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
	 * @param documentFlag
	 *            the documentFlag to set
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
	 * @param lotControlFlag
	 *            the lotControlFlag to set
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
	 * @param serialControlFlag
	 *            the serialControlFlag to set
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
	 * @param versionNum
	 *            the versionNum to set
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
	 * @param upcCode
	 *            the upcCode to set
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
	 * @param prefWarehouse
	 *            the prefWarehouse to set
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
	 * @param altWarehouseFlag
	 *            the altWarehouseFlag to set
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
	 * @param prefStorage
	 *            the prefStorage to set
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
	 * @param saftyStock
	 *            the saftyStock to set
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
	 * @param minOrderQty
	 *            the minOrderQty to set
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
	 * @param unitCost
	 *            the unitCost to set
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
	 * @param sellingPriceCmsn
	 *            the sellingPriceCmsn to set
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
	 * @param grossProfitCmsn
	 *            the grossProfitCmsn to set
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
	 * @param unitRateCmsn
	 *            the unitRateCmsn to set
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
	 * @param returnPoints
	 *            the returnPoints to set
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
	 * @param priceByPoints
	 *            the priceByPoints to set
	 */
	public void setPriceByPoints(Integer priceByPoints) {
		this.priceByPoints = priceByPoints;
	}

	public String getNoticeSendType() {
		return noticeSendType;
	}

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
	 * @param noticeGenerateTime
	 *            the noticeGenerateTime to set
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
	 * @param customerInfo
	 *            the customerInfo to set
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
	 * @param leadTime
	 *            the leadTime to set
	 */
	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}
	public String getSellingNote() {
		return sellingNote;
	}

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
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the modifyByText
	 */
	public String getModifyByText() {
		return modifyByText;
	}

	/**
	 * @param modifyByText
	 *            the modifyByText to set
	 */
	public void setModifyByText(String modifyByText) {
		this.modifyByText = modifyByText;
	}

	/**
	 * @return the createdByText
	 */
	public String getCreatedByText() {
		return createdByText;
	}

	/**
	 * @param createdByText
	 *            the createdByText to set
	 */
	public void setCreatedByText(String createdByText) {
		this.createdByText = createdByText;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
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
	 * @param createdBy
	 *            the createdBy to set
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
	 * @param modifyDate
	 *            the modifyDate to set
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
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the shipCondition
	 */
	public ShipCondition getShipCondition() {
		return shipCondition;
	}

	/**
	 * @param shipCondition
	 *            the shipCondition to set
	 */
	public void setShipCondition(ShipCondition shipCondition) {
		this.shipCondition = shipCondition;
	}

	/**
	 * @return the storageCondition
	 */
	public StorageCondition getStorageCondition() {
		return storageCondition;
	}

	/**
	 * @param storageCondition
	 *            the storageCondition to set
	 */
	public void setStorageCondition(StorageCondition storageCondition) {
		this.storageCondition = storageCondition;
	}

	/**
	 * @return the qtyUom
	 */
	public String getQtyUom() {
		return qtyUom;
	}

	/**
	 * @param qtyUom
	 *            the qtyUom to set
	 */
	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}

	/**
	 * @return the intmdList
	 */
	public List<IntermediateDTO> getIntmdList() {
		return intmdList;
	}

	/**
	 * @param intmdList
	 *            the intmdList to set
	 */
	public void setIntmdList(List<IntermediateDTO> intmdList) {
		this.intmdList = intmdList;
	}

	/**
	 * @return the delIntmdIdList
	 */
	public List<Integer> getDelIntmdIdList() {
		return delIntmdIdList;
	}

	/**
	 * @param delIntmdIdList
	 *            the delIntmdIdList to set
	 */
	public void setDelIntmdIdList(List<Integer> delIntmdIdList) {
		this.delIntmdIdList = delIntmdIdList;
	}

	/**
	 * @return the componentList
	 */
	public List<ComponentDTO> getComponentList() {
		return componentList;
	}

	/**
	 * @param componentList
	 *            the componentList to set
	 */
	public void setComponentList(List<ComponentDTO> componentList) {
		this.componentList = componentList;
	}

	/**
	 * @return the delComIdList
	 */
	public List<Integer> getDelComIdList() {
		return delComIdList;
	}

	/**
	 * @param delComIdList
	 *            the delComIdList to set
	 */
	public void setDelComIdList(List<Integer> delComIdList) {
		this.delComIdList = delComIdList;
	}

	/**
	 * @return the restrictShipList
	 */
	public List<RestrictShip> getRestrictShipList() {
		return restrictShipList;
	}

	/**
	 * @param restrictShipList the restrictShipList to set
	 */
	public void setRestrictShipList(List<RestrictShip> restrictShipList) {
		this.restrictShipList = restrictShipList;
	}

	/**
	 * @return the delRestrictShipIdList
	 */
	public List<Integer> getDelRestrictShipIdList() {
		return delRestrictShipIdList;
	}

	/**
	 * @param delRestrictShipIdList the delRestrictShipIdList to set
	 */
	public void setDelRestrictShipIdList(List<Integer> delRestrictShipIdList) {
		this.delRestrictShipIdList = delRestrictShipIdList;
	}

	/**
	 * @return the specialPriceList
	 */
	public List<ProductSpecialPrice> getSpecialPriceList() {
		return specialPriceList;
	}

	/**
	 * @param specialPriceList the specialPriceList to set
	 */
	public void setSpecialPriceList(List<ProductSpecialPrice> specialPriceList) {
		this.specialPriceList = specialPriceList;
	}

	/**
	 * @return the delSpecialPriceIdList
	 */
	public List<Integer> getDelSpecialPriceIdList() {
		return delSpecialPriceIdList;
	}

	/**
	 * @param delSpecialPriceIdList the delSpecialPriceIdList to set
	 */
	public void setDelSpecialPriceIdList(List<Integer> delSpecialPriceIdList) {
		this.delSpecialPriceIdList = delSpecialPriceIdList;
	}

	/**
	 * @return the vendorProductList
	 */
	public List<VendorProductDTO> getVendorProductList() {
		return vendorProductList;
	}

	/**
	 * @param vendorProductList the vendorProductList to set
	 */
	public void setVendorProductList(List<VendorProductDTO> vendorProductList) {
		this.vendorProductList = vendorProductList;
	}

	/**
	 * @return the delVendorProductIdList
	 */
	public List<Integer> getDelVendorProductIdList() {
		return delVendorProductIdList;
	}

	/**
	 * @param delVendorProductIdList the delVendorProductIdList to set
	 */
	public void setDelVendorProductIdList(List<Integer> delVendorProductIdList) {
		this.delVendorProductIdList = delVendorProductIdList;
	}

	/**
	 * @return the pdtRelationList
	 */
	public List<ProductRelationDTO> getPdtRelationList() {
		return pdtRelationList;
	}

	/**
	 * @param pdtRelationList the pdtRelationList to set
	 */
	public void setPdtRelationList(List<ProductRelationDTO> pdtRelationList) {
		this.pdtRelationList = pdtRelationList;
	}

	public String getNameApprove() {
		return nameApprove;
	}

	public void setNameApprove(String nameApprove) {
		this.nameApprove = nameApprove;
	}

	public String getNameReason() {
		return nameReason;
	}

	public void setNameReason(String nameReason) {
		this.nameReason = nameReason;
	}

	public String getStatusApprove() {
		return statusApprove;
	}

	public void setStatusApprove(String statusApprove) {
		this.statusApprove = statusApprove;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public String getUnitCostApprove() {
		return unitCostApprove;
	}

	public void setUnitCostApprove(String unitCostApprove) {
		this.unitCostApprove = unitCostApprove;
	}

	public String getUnitCostReason() {
		return unitCostReason;
	}

	public void setUnitCostReason(String unitCostReason) {
		this.unitCostReason = unitCostReason;
	}

	public String getNameNewVal() {
		return nameNewVal;
	}

	public void setNameNewVal(String nameNewVal) {
		this.nameNewVal = nameNewVal;
	}

	public String getNameOldVal() {
		return nameOldVal;
	}

	public void setNameOldVal(String nameOldVal) {
		this.nameOldVal = nameOldVal;
	}

	public String getStatusNewVal() {
		return statusNewVal;
	}

	public void setStatusNewVal(String statusNewVal) {
		this.statusNewVal = statusNewVal;
	}

	public String getStatusOldVal() {
		return statusOldVal;
	}

	public void setStatusOldVal(String statusOldVal) {
		this.statusOldVal = statusOldVal;
	}

	/**
	 * @return the royaltyProduct
	 */
	public RoyaltyProductDTO getRoyaltyProduct() {
		return royaltyProduct;
	}

	/**
	 * @param royaltyProduct the royaltyProduct to set
	 */
	public void setRoyaltyProduct(RoyaltyProductDTO royaltyProduct) {
		this.royaltyProduct = royaltyProduct;
	}


	public ProductExtendedInfo getProductExtendedInfo() {
		return productExtendedInfo;
	}


	public void setProductExtendedInfo(ProductExtendedInfo productExtendedInfo) {
		this.productExtendedInfo = productExtendedInfo;
	}


	public List<Integer> getDelMoreInfo() {
		return delMoreInfo;
	}


	public void setDelMoreInfo(List<Integer> delMoreInfo) {
		this.delMoreInfo = delMoreInfo;
	}


	public List<Documents> getProductDocumentList() {
		return productDocumentList;
	}


	public void setProductDocumentList(List<Documents> productDocumentList) {
		this.productDocumentList = productDocumentList;
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

	public List<Integer> getDelProductDocumentList() {
		return delProductDocumentList;
	}

	public void setDelProductDocumentList(List<Integer> delProductDocumentList) {
		this.delProductDocumentList = delProductDocumentList;
	}

	public List<ProductPriceDTO> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<ProductPriceDTO> priceList) {
		this.priceList = priceList;
	}

	public List<String> getDelPriceIdList() {
		return delPriceIdList;
	}

	public void setDelPriceIdList(List<String> delPriceIdList) {
		this.delPriceIdList = delPriceIdList;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public List<ProductReference> getProductReferenceList() {
		return productReferenceList;
	}

	public void setProductReferenceList(List<ProductReference> productReferenceList) {
		this.productReferenceList = productReferenceList;
	}

	public List<Integer> getDelReferenceList() {
		return delReferenceList;
	}

	public void setDelReferenceList(List<Integer> delReferenceList) {
		this.delReferenceList = delReferenceList;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
}
