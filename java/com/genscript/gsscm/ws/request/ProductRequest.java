package com.genscript.gsscm.ws.request;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.constant.CatalogType;
import com.genscript.gsscm.common.constant.RequestApproveType;
import com.genscript.gsscm.common.constant.SearchProperty;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.entity.CustomerPriceBean;
import com.genscript.gsscm.product.dto.ProductCategoryDTO;
import com.genscript.gsscm.product.dto.ProductDTO;
import com.genscript.gsscm.product.entity.Antibody;
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.entity.CategorySearchBean;
import com.genscript.gsscm.product.entity.Chemical;
import com.genscript.gsscm.product.entity.Component;
import com.genscript.gsscm.product.entity.Enzyme;
import com.genscript.gsscm.product.entity.Gene;
import com.genscript.gsscm.product.entity.Intermediate;
import com.genscript.gsscm.product.entity.Kit;
import com.genscript.gsscm.product.entity.Molecule;
import com.genscript.gsscm.product.entity.Oligo;
import com.genscript.gsscm.product.entity.Peptide;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductBean;
import com.genscript.gsscm.product.entity.ProductCategory;
import com.genscript.gsscm.product.entity.ProductListBean;
import com.genscript.gsscm.product.entity.ProductOfPdtcategoryBean;
import com.genscript.gsscm.product.entity.Protein;
import com.genscript.gsscm.productservice.dto.CatalogDTO;

@XmlType(name="ProductRequest",namespace=WsConstants.NS)
public class ProductRequest extends WsRequest{
	private List<SearchProperty> searchPropertyList;
	private Page<ProductBean> pageProduct;
	private Page<Product> pagePdt;
	private Page<Catalog> pageCatalog;
	private Page<ProductCategory> pageCategory;
	private Page<ProductListBean> pgProduct;
	private CatalogType catalogType;
	private Integer custNo;
	private CustomerPriceBean customerPriceBean;
	private List<String> catalogNoList;
	private Integer paramId;
	private CatalogDTO catalogDTO;
	private ProductCategoryDTO productCategory;
	private Page<CategorySearchBean> pageCategorySearchBean;
	private ProductDTO product;
    private Page<Intermediate> pageIntermediate;	
    private Page<Component> pageComponent;
    private String catalogId;
    private Integer productId;
    private String catalogNo;
    private String name;
    private RequestApproveType requestApproveType;
    private Integer objectId;
    private String columnName;
    private List<Integer> categoryIdList;
    private String rejectReason;
    private List<Integer> requestIdList;
    private Integer topCount;
    private Integer lastDays;
    private List<Integer> productListId;
    private Page<ProductOfPdtcategoryBean> pageProductOfPdtcategoryBean;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CatalogType getCatalogType() {
		return catalogType;
	}

	public void setCatalogType(CatalogType catalogType) {
		this.catalogType = catalogType;
	}

	public Page<Catalog> getPageCatalog() {
		return pageCatalog;
	}

	public void setPageCatalog(Page<Catalog> pageCatalog) {
		this.pageCatalog = pageCatalog;
	}


	public Page<ProductListBean> getPgProduct() {
		return pgProduct;
	}

	public void setPgProduct(Page<ProductListBean> pgProduct) {
		this.pgProduct = pgProduct;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public CustomerPriceBean getCustomerPriceBean() {
		return customerPriceBean;
	}

	public void setCustomerPriceBean(CustomerPriceBean customerPriceBean) {
		this.customerPriceBean = customerPriceBean;
	}

	public Page<ProductCategory> getPageCategory() {
		return pageCategory;
	}

	public void setPageCategory(Page<ProductCategory> pageCategory) {
		this.pageCategory = pageCategory;
	}

	public List<String> getCatalogNoList() {
		return catalogNoList;
	}

	public void setCatalogNoList(List<String> catalogNoList) {
		this.catalogNoList = catalogNoList;
	}

	public List<SearchProperty> getSearchPropertyList() {
		return searchPropertyList;
	}

	public void setSearchPropertyList(List<SearchProperty> searchPropertyList) {
		this.searchPropertyList = searchPropertyList;
	}

	public Page<ProductBean> getPageProduct() {
		return pageProduct;
	}

	public void setPageProduct(Page<ProductBean> pageProduct) {
		this.pageProduct = pageProduct;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the paramId
	 */
	public Integer getParamId() {
		return paramId;
	}

	public Page<CategorySearchBean> getPageCategorySearchBean() {
		return pageCategorySearchBean;
	}

	public void setPageCategorySearchBean(
			Page<CategorySearchBean> pageCategorySearchBean) {
		this.pageCategorySearchBean = pageCategorySearchBean;
	}

	/**
	 * @param paramId the paramId to set
	 */
	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}

	/**
	 * @return the catalogDTO
	 */
	public CatalogDTO getCatalogDTO() {
		return catalogDTO;
	}

	/**
	 * @param catalogDTO the catalogDTO to set
	 */
	public void setCatalogDTO(CatalogDTO catalogDTO) {
		this.catalogDTO = catalogDTO;
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

	/**
	 * @return the product
	 */
	public ProductDTO getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	/**
	 * @return the pageIntermediate
	 */
	public Page<Intermediate> getPageIntermediate() {
		return pageIntermediate;
	}

	/**
	 * @param pageIntermediate the pageIntermediate to set
	 */
	public void setPageIntermediate(Page<Intermediate> pageIntermediate) {
		this.pageIntermediate = pageIntermediate;
	}

	/**
	 * @return the pageComponent
	 */
	public Page<Component> getPageComponent() {
		return pageComponent;
	}

	/**
	 * @param pageComponent the pageComponent to set
	 */
	public void setPageComponent(Page<Component> pageComponent) {
		this.pageComponent = pageComponent;
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

	public Integer getProductId() {
		return productId;
	}

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

	public Page<Product> getPagePdt() {
		return pagePdt;
	}

	public void setPagePdt(Page<Product> pagePdt) {
		this.pagePdt = pagePdt;
	}

	public RequestApproveType getRequestApproveType() {
		return requestApproveType;
	}

	public void setRequestApproveType(RequestApproveType requestApproveType) {
		this.requestApproveType = requestApproveType;
	}

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the categoryIdList
	 */
	public List<Integer> getCategoryIdList() {
		return categoryIdList;
	}

	/**
	 * @param categoryIdList the categoryIdList to set
	 */
	public void setCategoryIdList(List<Integer> categoryIdList) {
		this.categoryIdList = categoryIdList;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public List<Integer> getRequestIdList() {
		return requestIdList;
	}

	public void setRequestIdList(List<Integer> requestIdList) {
		this.requestIdList = requestIdList;
	}

	/**
	 * @return the topCount
	 */
	public Integer getTopCount() {
		return topCount;
	}

	/**
	 * @param topCount the topCount to set
	 */
	public void setTopCount(Integer topCount) {
		this.topCount = topCount;
	}

	/**
	 * @return the lastDays
	 */
	public Integer getLastDays() {
		return lastDays;
	}

	/**
	 * @param lastDays the lastDays to set
	 */
	public void setLastDays(Integer lastDays) {
		this.lastDays = lastDays;
	}

	public Page<ProductOfPdtcategoryBean> getPageProductOfPdtcategoryBean() {
		return pageProductOfPdtcategoryBean;
	}

	public void setPageProductOfPdtcategoryBean(
			Page<ProductOfPdtcategoryBean> pageProductOfPdtcategoryBean) {
		this.pageProductOfPdtcategoryBean = pageProductOfPdtcategoryBean;
	}

	public List<Integer> getProductListId() {
		return productListId;
	}

	public void setProductListId(List<Integer> productListId) {
		this.productListId = productListId;
	}
	
}
