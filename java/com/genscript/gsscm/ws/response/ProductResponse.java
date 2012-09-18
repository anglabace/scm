package com.genscript.gsscm.ws.response;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.SearchItemDTO;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.inventory.dto.WarehouseDTO;
import com.genscript.gsscm.product.dto.ComponentDTO;
import com.genscript.gsscm.product.dto.IntermediateDTO;
import com.genscript.gsscm.product.dto.ProductCategoryDTO;
import com.genscript.gsscm.product.dto.ProductDTO;
import com.genscript.gsscm.product.dto.ProductListBeanDTO;
import com.genscript.gsscm.product.dto.ProductPriceDTO;
import com.genscript.gsscm.product.dto.ProductRelationDTO;
import com.genscript.gsscm.product.dto.ProductStockStatDTO;
import com.genscript.gsscm.product.dto.PurchaseOrderDTO;
import com.genscript.gsscm.product.dto.RestrictShipDTO;
import com.genscript.gsscm.product.dto.RoyaltyProductDTO;
import com.genscript.gsscm.product.entity.Antibody;
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.entity.CategorySearchBean;
import com.genscript.gsscm.product.entity.Chemical;
import com.genscript.gsscm.product.entity.Enzyme;
import com.genscript.gsscm.product.entity.Gene;
import com.genscript.gsscm.product.entity.Kit;
import com.genscript.gsscm.product.entity.Molecule;
import com.genscript.gsscm.product.entity.Oligo;
import com.genscript.gsscm.product.entity.Peptide;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductBean;
import com.genscript.gsscm.product.entity.ProductCategory;
import com.genscript.gsscm.product.entity.Documents;
import com.genscript.gsscm.product.entity.ProductExtendedInfo;
import com.genscript.gsscm.product.entity.ProductListBean;
import com.genscript.gsscm.product.entity.ProductOfPdtcategoryBean;
import com.genscript.gsscm.product.entity.ProductSpecialPrice;
import com.genscript.gsscm.product.entity.ProductStdPriceBean;
import com.genscript.gsscm.product.entity.Protein;
import com.genscript.gsscm.product.entity.Royalty;
import com.genscript.gsscm.productservice.dto.CatalogDTO;
import com.genscript.gsscm.productservice.dto.SalePersonDTO;
import com.genscript.gsscm.purchase.dto.VendorDTO;
import com.genscript.gsscm.purchase.dto.VendorProductDTO;
import com.genscript.gsscm.serv.dto.ServiceCategoryDTO;
import com.genscript.gsscm.serv.dto.ServiceListBeanDTO;

@XmlType(name="ProductResponse", namespace=WsConstants.NS)
public class ProductResponse extends WsResponse {
	private PageDTO page;
	private List<ProductBean> productDTOList;
	private List<CatalogDTO> catalogList;
	private List<ProductCategory> categoryList;
	private ProductRelationDTO productRelationDTO;
	private List<Catalog> catalogs;
	private List<ProductListBean> productList;
	private CatalogDTO catalog;
	private ProductCategoryDTO productCategory;
	private List<CategorySearchBean> categorySearchBeanList;
	private ProductDTO productDTO;
	private List<IntermediateDTO> intermediateList;
	private List<ComponentDTO> componentList;
	private List<ProductSpecialPrice> specialPriceList;
	private ProductPriceDTO productPriceDTO;
	private List<DropDownDTO> dropDownDTOList;
	private List<RestrictShipDTO> restrictShipList;
	private List<WarehouseDTO> warehouseDTOList;
	private ProductStockStatDTO productStockStatDTO;
	private List<ProductStdPriceBean> stdPriceList;
	private ProductStdPriceBean productStdPriceBean;
	private List<VendorProductDTO> verdonProductList;
	private List<Product> pdtList;
	private List<PurchaseOrderDTO> purchaseOrderDTOList;
	private List<VendorDTO> vendorDTOList;
	private Integer id;
	private Integer categoryId;
	private Integer productId;
	private List<ProductCategoryDTO> productCategoryDTOList;
	private List<ServiceCategoryDTO> serviceCategoryDTOList;
	private List<ProductListBeanDTO> productListBeanDTOList;
	private List<ServiceListBeanDTO> serviceListBeanDTOList;
	private Boolean propertyApprovedStatus;
	private List<Integer> unDeleteIdList;
	private List<SalePersonDTO> salePersonList;
	private List<Royalty> royaltyList;
	private RoyaltyProductDTO royaltyProduct;
	private List<SearchItemDTO> searchItemDTOList;
	private List<ProductOfPdtcategoryBean> productOfPdtCategoryList;
	private String catalogId;
	private Antibody antibody;
	private Enzyme enzyme;
	private Chemical chemical;
	private Kit kit;
	private Molecule molecule;
	private Oligo oligo;
	private Protein protein;
	private Gene gene;
	private Peptide peptide;
	private String priceLimit;
	private ProductExtendedInfo productExtEndedInfo;
	private List<Documents> productDocument;
	public PageDTO getPage() {
		return page;
	}
	public void setPage(PageDTO page) {
		this.page = page;
	}
	public String getPriceLimit() {
		return priceLimit;
	}
	public void setPriceLimit(String priceLimit) {
		this.priceLimit = priceLimit;
	}
	public List<ProductBean> getProductDTOList() {
		return productDTOList;
	}
	public void setProductDTOList(List<ProductBean> productDTOList) {
		this.productDTOList = productDTOList;
	}
	
	public List<ProductCategory> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<ProductCategory> categoryList) {
		this.categoryList = categoryList;
	}
	public ProductRelationDTO getProductRelationDTO() {
		return productRelationDTO;
	}
	public void setProductRelationDTO(ProductRelationDTO productRelationDTO) {
		this.productRelationDTO = productRelationDTO;
	}
	public List<ProductListBean> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductListBean> productList) {
		this.productList = productList;
	}
	/**
	 * @return the catalogList
	 */
	public List<CatalogDTO> getCatalogList() {
		return catalogList;
	}
	/**
	 * @param catalogList the catalogList to set
	 */
	public void setCatalogList(List<CatalogDTO> catalogList) {
		this.catalogList = catalogList;
	}
	public List<Catalog> getCatalogs() {
		return catalogs;
	}
	public void setCatalogs(List<Catalog> catalogs) {
		this.catalogs = catalogs;
	}
	/**
	 * @return the catalog
	 */
	public CatalogDTO getCatalog() {
		return catalog;
	}
	/**
	 * @param catalog the catalog to set
	 */
	public void setCatalog(CatalogDTO catalog) {
		this.catalog = catalog;
	}
	/**
	 * @return the productCategory
	 */
	public ProductCategoryDTO getProductCategory() {
		return productCategory;
	}
	/**
	 * @param productCategory the productCategory to set
	 */
	public void setProductCategory(ProductCategoryDTO productCategory) {
		this.productCategory = productCategory;
	}
	public List<CategorySearchBean> getCategorySearchBeanList() {
		return categorySearchBeanList;
	}
	public void setCategorySearchBeanList(
			List<CategorySearchBean> categorySearchBeanList) {
		this.categorySearchBeanList = categorySearchBeanList;
	}
	/**
	 * @return the productDTO
	 */
	public ProductDTO getProductDTO() {
		return productDTO;
	}
	/**
	 * @param productDTO the productDTO to set
	 */
	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	/**
	 * @return the intermediateList
	 */
	public List<IntermediateDTO> getIntermediateList() {
		return intermediateList;
	}
	/**
	 * @param intermediateList the intermediateList to set
	 */
	public void setIntermediateList(List<IntermediateDTO> intermediateList) {
		this.intermediateList = intermediateList;
	}
	/**
	 * @return the componentList
	 */
	public List<ComponentDTO> getComponentList() {
		return componentList;
	}
	/**
	 * @param componentList the componentList to set
	 */
	public void setComponentList(List<ComponentDTO> componentList) {
		this.componentList = componentList;
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
	 * @return the productPriceDTO
	 */
	public ProductPriceDTO getProductPriceDTO() {
		return productPriceDTO;
	}
	/**
	 * @param productPriceDTO the productPriceDTO to set
	 */
	public void setProductPriceDTO(ProductPriceDTO productPriceDTO) {
		this.productPriceDTO = productPriceDTO;
	}
	public List<DropDownDTO> getDropDownDTOList() {
		return dropDownDTOList;
	}
	public void setDropDownDTOList(List<DropDownDTO> dropDownDTOList) {
		this.dropDownDTOList = dropDownDTOList;
	}
	/**
	 * @return the restrictShipList
	 */
	public List<RestrictShipDTO> getRestrictShipList() {
		return restrictShipList;
	}
	/**
	 * @param restrictShipList the restrictShipList to set
	 */
	public void setRestrictShipList(List<RestrictShipDTO> restrictShipList) {
		this.restrictShipList = restrictShipList;
	}
	public List<WarehouseDTO> getWarehouseDTOList() {
		return warehouseDTOList;
	}
	public void setWarehouseDTOList(List<WarehouseDTO> warehouseDTOList) {
		this.warehouseDTOList = warehouseDTOList;
	}
	/**
	 * @return the productStockStatDTO
	 */
	public ProductStockStatDTO getProductStockStatDTO() {
		return productStockStatDTO;
	}
	/**
	 * @param productStockStatDTO the productStockStatDTO to set
	 */
	public void setProductStockStatDTO(ProductStockStatDTO productStockStatDTO) {
		this.productStockStatDTO = productStockStatDTO;
	}
	/**
	 * @return the stdPriceList
	 */
	public List<ProductStdPriceBean> getStdPriceList() {
		return stdPriceList;
	}
	/**
	 * @param stdPriceList the stdPriceList to set
	 */
	public void setStdPriceList(List<ProductStdPriceBean> stdPriceList) {
		this.stdPriceList = stdPriceList;
	}
	/**
	 * @return the productStdPriceBean
	 */
	public ProductStdPriceBean getProductStdPriceBean() {
		return productStdPriceBean;
	}
	/**
	 * @param productStdPriceBean the productStdPriceBean to set
	 */
	public void setProductStdPriceBean(ProductStdPriceBean productStdPriceBean) {
		this.productStdPriceBean = productStdPriceBean;
	}
	/**
	 * @return the verdonProductList
	 */
	public List<VendorProductDTO> getVerdonProductList() {
		return verdonProductList;
	}
	/**
	 * @param verdonProductList the verdonProductList to set
	 */
	public void setVerdonProductList(List<VendorProductDTO> verdonProductList) {
		this.verdonProductList = verdonProductList;
	}
	public List<Product> getPdtList() {
		return pdtList;
	}
	public void setPdtList(List<Product> pdtList) {
		this.pdtList = pdtList;
	}
	/**
	 * @return the purchaseOrderDTOList
	 */
	public List<PurchaseOrderDTO> getPurchaseOrderDTOList() {
		return purchaseOrderDTOList;
	}
	/**
	 * @param purchaseOrderDTOList the purchaseOrderDTOList to set
	 */
	public void setPurchaseOrderDTOList(List<PurchaseOrderDTO> purchaseOrderDTOList) {
		this.purchaseOrderDTOList = purchaseOrderDTOList;
	}

	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
	public List<VendorDTO> getVendorDTOList() {
		return vendorDTOList;
	}
	public void setVendorDTOList(List<VendorDTO> vendorDTOList) {
		this.vendorDTOList = vendorDTOList;
	}
	public List<ProductListBeanDTO> getProductListBeanDTOList() {
		return productListBeanDTOList;
	}
	public void setProductListBeanDTOList(
			List<ProductListBeanDTO> productListBeanDTOList) {
		this.productListBeanDTOList = productListBeanDTOList;
	}
	public List<ServiceListBeanDTO> getServiceListBeanDTOList() {
		return serviceListBeanDTOList;
	}
	public void setServiceListBeanDTOList(
			List<ServiceListBeanDTO> serviceListBeanDTOList) {
		this.serviceListBeanDTOList = serviceListBeanDTOList;
	}
	public Boolean getPropertyApprovedStatus() {
		return propertyApprovedStatus;
	}
	public void setPropertyApprovedStatus(Boolean propertyApprovedStatus) {
		this.propertyApprovedStatus = propertyApprovedStatus;
	}
	/**
	 * @return the unDeleteIdList
	 */
	public List<Integer> getUnDeleteIdList() {
		return unDeleteIdList;
	}
	/**
	 * @param unDeleteIdList the unDeleteIdList to set
	 */
	public void setUnDeleteIdList(List<Integer> unDeleteIdList) {
		this.unDeleteIdList = unDeleteIdList;
	}
	public List<ProductCategoryDTO> getProductCategoryDTOList() {
		return productCategoryDTOList;
	}
	public void setProductCategoryDTOList(
			List<ProductCategoryDTO> productCategoryDTOList) {
		this.productCategoryDTOList = productCategoryDTOList;
	}
	public List<ServiceCategoryDTO> getServiceCategoryDTOList() {
		return serviceCategoryDTOList;
	}
	public void setServiceCategoryDTOList(
			List<ServiceCategoryDTO> serviceCategoryDTOList) {
		this.serviceCategoryDTOList = serviceCategoryDTOList;
	}
	/**
	 * @return the salePersonList
	 */
	public List<SalePersonDTO> getSalePersonList() {
		return salePersonList;
	}
	/**
	 * @param salePersonList the salePersonList to set
	 */
	public void setSalePersonList(List<SalePersonDTO> salePersonList) {
		this.salePersonList = salePersonList;
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
	public List<Royalty> getRoyaltyList() {
		return royaltyList;
	}
	public void setRoyaltyList(List<Royalty> royaltyList) {
		this.royaltyList = royaltyList;
	}
	public List<SearchItemDTO> getSearchItemDTOList() {
		return searchItemDTOList;
	}
	public void setSearchItemDTOList(List<SearchItemDTO> searchItemDTOList) {
		this.searchItemDTOList = searchItemDTOList;
	}
	public List<ProductOfPdtcategoryBean> getProductOfPdtCategoryList() {
		return productOfPdtCategoryList;
	}
	public void setProductOfPdtCategoryList(
			List<ProductOfPdtcategoryBean> productOfPdtCategoryList) {
		this.productOfPdtCategoryList = productOfPdtCategoryList;
	}
	/**
	 * @return the catalogId
	 */
	public String getCatalogId() {
		return catalogId;
	}
	/**
	 * @param catalogId the catalogId to set
	 */
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public Antibody getAntibody() {
		return antibody;
	}
	public void setAntibody(Antibody antibody) {
		this.antibody = antibody;
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
	public ProductExtendedInfo getProductExtEndedInfo() {
		return productExtEndedInfo;
	}
	public void setProductExtEndedInfo(ProductExtendedInfo productExtEndedInfo) {
		this.productExtEndedInfo = productExtEndedInfo;
	}
	public List<Documents> getProductDocument() {
		return productDocument;
	}
	public void setProductDocument(List<Documents> productDocument) {
		this.productDocument = productDocument;
	}
	
}
