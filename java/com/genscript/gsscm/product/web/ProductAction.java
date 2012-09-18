package com.genscript.gsscm.product.web;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dto.CountryDTO;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.dto.OptionItemDTO;
import com.genscript.gsscm.basedata.dto.SearchAttributeDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.CommonSearchService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.ProductDetailType;
import com.genscript.gsscm.common.constant.RequestApproveType;
import com.genscript.gsscm.common.constant.SearchType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SessionPdtServ;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.AnalysisReport;
import com.genscript.gsscm.epicorwebservice.service.ErpSalesOrderService;
import com.genscript.gsscm.order.dto.ProductServiceSaleDTO;
import com.genscript.gsscm.order.dto.SalesRankingDTO;
import com.genscript.gsscm.product.dto.CatalogNORulesDTO;
import com.genscript.gsscm.product.dto.ComponentDTO;
import com.genscript.gsscm.product.dto.ProductCategoryDTO;
import com.genscript.gsscm.product.dto.ProductDTO;
import com.genscript.gsscm.product.dto.ProductListBeanDTO;
import com.genscript.gsscm.product.dto.ProductPriceDTO;
import com.genscript.gsscm.product.dto.ProductPriceListBeanDTO;
import com.genscript.gsscm.product.dto.ProductRelationDTO;
import com.genscript.gsscm.product.dto.ProductReportSrchDTO;
import com.genscript.gsscm.product.dto.ProductSpecialPriceDTO;
import com.genscript.gsscm.product.dto.PurchaseOrderDTO;
import com.genscript.gsscm.product.dto.RestrictShipDTO;
import com.genscript.gsscm.product.dto.RoyaltyProductDTO;
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.entity.CatalogNORules;
import com.genscript.gsscm.product.entity.DocumentVersion;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.Documents;
import com.genscript.gsscm.product.entity.ProductCategory;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.product.entity.ProductExtendedInfo;
import com.genscript.gsscm.product.entity.ProductInCategoryBean;
import com.genscript.gsscm.product.entity.ProductListBean;
import com.genscript.gsscm.product.entity.ProductOfPdtcategoryBean;
import com.genscript.gsscm.product.entity.ProductPrice;
import com.genscript.gsscm.product.entity.ProductReference;
import com.genscript.gsscm.product.entity.ProductSpecialPrice;
import com.genscript.gsscm.product.entity.ProductStdPriceBean;
import com.genscript.gsscm.product.entity.RestrictShip;
import com.genscript.gsscm.product.entity.Royalty;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.productservice.dto.CatalogDTO;
import com.genscript.gsscm.purchase.dao.VendorDao;
import com.genscript.gsscm.purchase.dto.VendorDTO;
import com.genscript.gsscm.purchase.dto.VendorProductDTO;
import com.genscript.gsscm.purchase.entity.Vendor;
import com.genscript.gsscm.purchase.entity.VendorProduct;
import com.genscript.gsscm.serv.dto.ServiceCategoryDTO;
import com.genscript.gsscm.serv.dto.ServiceListBeanDTO;
import com.genscript.gsscm.serv.dto.ServicePriceDTO;
import com.genscript.gsscm.serv.entity.ServiceCategory;
import com.genscript.gsscm.serv.service.ServService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.Action;

/*
 *2010-8-23 13:32:42
 *by mingrs
 */

@Results({
		@Result(name = Action.SUCCESS, location = "product/product/product_list.jsp"),
		@Result(name = "search", location = "product/product/product_search.jsp"),
		@Result(name = "pdtOfCategory", location = "product/product/category_pdt_list.jsp"),
		@Result(name = "catPdtAddAct", location = "product/product/category_pdt_add.jsp"),
		@Result(name = "productCreateForm", location = "product/product/pdtServ_create_form.jsp"),
		@Result(name = "showCrossCreateFormAct", location = "product/product/pdtServ_cross_info.jsp"),
		@Result(name = "peptide", location = "product/product/detail/product_peptide.jsp"),
		@Result(name = "antibody", location = "product/product/detail/product_antibody.jsp"),
		@Result(name = "enzyme", location = "product/product/detail/product_enzyme.jsp"),
		@Result(name = "none", location = "product/product/detail/product_none.jsp"),
		@Result(name = "gene", location = "product/product/detail/product_gene.jsp"),
		@Result(name = "vector", location = "product/product/detail/product_sds.jsp"),
		@Result(name = "kit", location = "product/product/detail/product_kit.jsp"),
		@Result(name = "oligo", location = "product/product/detail/product_oligo.jsp"),
		@Result(name = "protein", location = "product/product/detail/product_protein.jsp"),
		@Result(name = "molecule", location = "product/product/detail/product_molecule.jsp"),
		@Result(name = "chemicals", location = "product/product/detail/product_chemical.jsp"),
		@Result(name = "showEditSuppler", location = "product/product/pdtServ_supplier_edit.jsp"),
		@Result(name = "supplier", location = "product/product/pdtServ_supplier.jsp"),
		@Result(name = "showSupplierPikcerAct", location = "product/product/pdtServ_supplier_picker.jsp"),
		@Result(name = "showMoreInfo", location = "product/product/product_moreInfo.jsp"),
		@Result(name = "showMiscAct", location = "product/product/pdtServ_misc.jsp"),
		@Result(name = "showRoyaltiesList", location = "product/product/product_royalty_list.jsp"),
		@Result(name = "showProductSales", location = "product/product/product_sales.jsp"),
		@Result(name = "searchStdPriceList", location = "product/product/product_crossCtgNo_list.jsp"),
		@Result(name = "managerTaskList", location = "product/manager_task_list.jsp"),
		@Result(name = "excel", location = "product/product/product_excel_list.jsp"),
		@Result(name = "catalogNoRules", location = "product/product/catalog_no_rules_list.jsp"),
		@Result(name = "productNotInDocument", location = "product/product/setups/document_add_product.jsp"),
		@Result(name = "documentVersion", location = "product/product/setups/document_version.jsp")

})
public class ProductAction extends BaseAction<ProductDTO> {

	/**
     *
     */
	private static final long serialVersionUID = -7567311788434877864L;

	private String urllink;

	@Autowired
	private DozerBeanMapper dozer;

	@Autowired
	private ExceptionService exceptionUtil;

	@Autowired
	private ProductService productService;

	@Autowired
	private ErpSalesOrderService erpSalesOrderService;

	@Autowired
	private ServService servService;

	@Autowired
	private CommonSearchService commonSearchService;

	@Autowired
	private PublicService publicService;
	@Autowired
	private VendorDao vendorDao;

	/**
	 * 列表页面中的page
	 */
	private Page<Product> page;
	private Page<ProductOfPdtcategoryBean> pageOfCategory;
	private Page<ProductListBean> pageBean;

	private String callBackName;
	/**
	 * 列表页面中的数据
	 */
	private ProductDTO productDTO;
	private Integer id;// productId;
	private ProductDTO entity;
	private ProductRelationDTO crossDetail;
	private String type; // product detail 显示对应的页面;
	private String supplierId; // product supplier edit or new
	// 如果supplierId为空为new 否edit
	private VendorProduct vendorProduct;
	private ProductExtendedInfo productExtendedInfo;
	private Integer defaultTab;// product save 后显示的页面；
	private RoyaltyProductDTO royolty;// RoyaltyProduct(版权税)信息
	private ProductServiceSaleDTO productSaleDTO;// 显示一个Product的各类销售统计情况；

	private List<Royalty> royaltyList;
	private List<SearchAttributeDTO> attrList;
	private List<CountryDTO> countryList;
	private List<ProductClass> ProductTypeList;
	private List<DropDownListDTO> arrDropdownList;
	private List<PbDropdownListOptions> pbOptionItemList;
	private List<DropDownDTO> dropDownDTOList;
	private List<PbDropdownListOptions> sendOrderedOption;
	private List<PbDropdownListOptions> generateNoticeOption;
	private List<OptionItemDTO> OptionsDropdownList;
	private List<VendorProductDTO> verdonProductList;
	private List<PurchaseOrderDTO> purchaseOrderList;
	private List<VendorDTO> vendorDTOList;
	private List<Documents> productDoc;
	private List<SalesRankingDTO> salesRankingList;
	private List<ProductStdPriceBean> productStdPriceBeanList;
	private List<DropDownDTO> dropDownDTO;
	private List<ProductListBean> excelResult;
	private List<ProductReference> productReferenceList;

	private Date fromDate;// 开始时间
	private Date toDate;// 结束时间
	private String isFalse;// 是否是需要统计
	private String salesPeriod;//
	private Integer top;// 查询前几名sales
	private String lastDays;// 查询名次，时间段
	private Integer periodType;// 统计图片时间点
	private String salesPeriodBasedOn;// 统计图片数据类型

	private String sessionProductId;
	private String supplierIdStr;
	private String delFileProduct;

	private Integer categoryId;
	private String catalogId;
	private String catalogNo;

	private String propName;
	private String srchOperator;
	private String propValue;
	private String symbolPrice;
	private String enforcePriceLimit;

	private String relationId;// Cross-Sell,UP-Sell,Substitute,Promote Items
	// 修改或增加
	private String name;// search cross product
	private String categoryName;
	private List<String> catalogNoList;// search cross product not catalog

	private String miscName;// getPdtRoyaltyList(name)参数

	private String vendorCatalog;// product detail type peptide
	private String vendor;
	private String vendorName;

	private String delProduct;

	/*
	 * Supply Related Info
	 */
	private String leadTime;
	private String saftyStock;
	private String minOrderQty;
	private String unitCost;

	/*
	 * Manager's Task List
	 */
	private String approveType;// search manager's task 类型
	private List<CatalogDTO> catalogDTOList;// 返回catalog类型数据
	private List<ProductCategoryDTO> productCategoryDTOList;// 返回productCategory类型数据
	private List<ServiceCategoryDTO> serviceCategoryDTOList;// 返回serviceCategory类型数据
	private List<ProductListBeanDTO> productDTOList;// 返回product类型数据
	private List<ServiceListBeanDTO> serviceDTOList;// 返回service类型数据
	private List<ProductPriceListBeanDTO> productPriceDTOList;
	private List<ServicePriceDTO> servicePriceDTOList;
	private String selectIds;// 选择用于处理的数据
	private String rejectReason;

	/*
	 * product approved;
	 */
	private String approved;
	private String approvedType;
	private String approvedReason;
	private Boolean nameAppr;
	private Boolean statusAppr;
	private Boolean unitCostAppr;
	private String approvedStatusList;// del product 批量修改status 为INACTIVE
	private List<Integer> statusOfapproved;//
	// 获取从approved 列表页面点来的请求
	private String approvedMethod;
	private Integer requestId;
	private String approvedName;
	private String approvedStatus;
	private String approvedUnitCost;
	private String approvedPrice;

	// 获取catalog no可使用的数据;
	private List<CatalogNORulesDTO> catalogNoRulesDTOList;
	// 获取从非Product列表页面点过来的请求--Zhang Yong
	private String operation_method;

	// document 中的Id
	private Integer docId;
	private List<DocumentVersion> documentVersionList;

	// catalogRuleNo
	private Integer ruleId;
	// ============== add by zhou gang
	private String priceLimitDate;
	private String standrdPricesDate;
	private String productKey;
	private String sessionCategoryId;
	// ===========
	// 文件处理
	@Autowired
	private FileService fileService;
	/**
	 * 实际上传文件
	 */
	private File upload;
	/**
	 * 文件的内容类型
	 */
	private String uploadContentType;
	/**
	 * 上传文件名
	 */
	private String uploadFileName;

	private String productFilesType;// 对应product_documents doc_type;

	public ProductDTO getModel() {
		/**
		 * 向跳转页面绑定ProductDTO类型的输出数据。
		 */
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		/**
		 * 对请求进行二次绑定，获取对应的catalogDTO数据;
		 */
		if (id != null) {
			entity = this.productService.getProductDetail(id);
		} else {
			entity = new ProductDTO();
		}
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 在session中保存category删除product的信息；
	 */
	@SuppressWarnings("unchecked")
	public String delProductToCategory() {
		if (delProduct != null) {
			String[] delPdt = delProduct.split(",");
			Object obj = SessionUtil.getRow(SessionPdtServ.DelProduct.value(),
					sessionCategoryId);
			List<Integer> delPdtList;
			if (obj != null) {
				delPdtList = (List<Integer>) obj;
			} else {
				delPdtList = new ArrayList<Integer>();
			}
			// List<Integer> delPdtList = new ArrayList<Integer>();
			for (int i = 0; i < delPdt.length; i++) {
				if (SessionUtil.getOneRow(SessionPdtServ.Product.value(),
						sessionCategoryId, delPdt[i]) == null) {
					delPdtList.add(Integer.valueOf(delPdt[i]));
				} else {
					SessionUtil.updateOneRow(SessionPdtServ.Product.value(),
							sessionCategoryId, delPdt[i], null);
				}
			}
			if (!delPdtList.isEmpty()) {
				if (obj == null) {
					SessionUtil.insertRow(SessionPdtServ.DelProduct.value(),
							sessionCategoryId, delPdtList);
				} else {
					SessionUtil.updateRow(SessionPdtServ.DelProduct.value(),
							sessionCategoryId, delPdtList);
				}
			}
			Struts2Util.renderText(SUCCESS);
		} else {
			Struts2Util.renderText(ERROR);
		}
		return null;
	}

	/*
	 * 在SESSION中保存category增加product的信息
	 */
	/*
	 * public String addProductToCategory(){ pageOfCategory = new
	 * Page<ProductOfPdtcategoryBean>(20); //ProductPriceDTO productPriceDTO =
	 * this.productService.getProductPrice(Integer.valueOf(productKey));
	 * if(SessionUtil.getOneRow(SessionPdtServ.Product.value(),
	 * sessionCategoryId, productKey)==null){ ProductCategoryDTO
	 * productCategoryDTO =
	 * this.productService.getPdtCategoryDetail(Integer.valueOf
	 * (sessionCategoryId)); Map<String, String> filterMap = new HashMap<String,
	 * String>(); filterMap.put("EQI_productId", productKey);
	 * filterMap.put("EQS_catalogId", productCategoryDTO.getCatalogId()); if
	 * (!pageOfCategory.isOrderBySetted()) {
	 * pageOfCategory.setOrderBy("productId");
	 * pageOfCategory.setOrder(Page.DESC); }
	 * pageOfCategory=this.productService.getProductOfPdtCategoryList
	 * (pageOfCategory, filterMap);
	 * if(pageOfCategory==null||pageOfCategory.getResult().isEmpty()){
	 * ProductPriceDTO productPriceDTO = new ProductPriceDTO();
	 * productPriceDTO.setCatalogId(productCategoryDTO.getCatalogId());
	 * productPriceDTO.setCategoryId(productCategoryDTO.getCategoryId());
	 * productPriceDTO.setProductId(Integer.valueOf(productKey));
	 * if(standrdPricesDate!=null){
	 * productPriceDTO.setStandardPrice(Double.valueOf(standrdPricesDate)); }
	 * if(priceLimitDate!=null){
	 * productPriceDTO.setLimitPrice(Double.valueOf(priceLimitDate)); }
	 * productPriceDTO.setStatus(productCategoryDTO.getStatus());
	 * //ProductCategory productCategory =
	 * (ProductCategory)this.dozer.map(productCategoryDTO,
	 * ProductCategory.class);
	 * SessionUtil.updateOneRow(SessionPdtServ.Product.value(),
	 * sessionCategoryId, productKey, productPriceDTO);
	 * Struts2Util.renderText(SUCCESS); }else{
	 * Struts2Util.renderText("The product have been in the category list!"); }
	 * }else{
	 * Struts2Util.renderText("The product have been in the product list!!"); }
	 * return null; }
	 */

	/*
	 * Cross-Sell,UP-Sell,Substitute,Promote Items add
	 */
	public String showCrossCreateFormAct() {
		this.pbOptionItemList = this.publicService
				.getDropdownList("RELATION_ITEM_TYPE");
		if (relationId == null || relationId.equals("")
				|| "null".equals(relationId)) {
			crossDetail = new ProductRelationDTO();
		} else {
			Object obj = SessionUtil.getOneRow(
					SessionPdtServ.ProductRelation.value(), sessionProductId,
					relationId.toString());
			if (obj == null) {
				crossDetail = this.productService
						.getProductRelationDetail(Integer.valueOf(relationId));
			} else {
				crossDetail = (ProductRelationDTO) obj;
			}
		}
		return "showCrossCreateFormAct";
	}

	/**
	 * 获得Product的列表. For(Product's add related selling item item catalog no tab.
	 */
	public String searchStdPriceList() {
		if ((catalogNo == null || catalogNo.equals(""))
				&& (name == null || name.equals(""))) {
			this.productStdPriceBeanList = new ArrayList<ProductStdPriceBean>();
		} else {
			this.productStdPriceBeanList = this.productService
					.searchStdPriceList(catalogNo, name, catalogNoList);
		}
		return "searchStdPriceList";
	}

	/*
	 * save session Cross-Sell,UP-Sell,Substitute,Promote Items
	 */
	public String saveSessionCrossAct() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		String crossKey;
		if (crossDetail.getRelationId() == null) {
			crossKey = SessionUtil.generateTempId();
		} else {
			crossKey = crossDetail.getRelationId().toString();
		}
		if (crossDetail.getDisplayOnlyBo() == null) {
			crossDetail.setDisplayOnlyBo("N");
		}
		if (crossDetail.getMandatoryFlag() == null) {
			crossDetail.setMandatoryFlag("N");
		}
		SessionUtil.updateOneRow(SessionPdtServ.ProductRelation.value(),
				sessionProductId, crossKey, crossDetail);
		rt.put("message", SUCCESS);
		rt.put("id", crossKey);
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * product detail
	 */
	public String productDetail() throws Exception {
		if (type == null || "".equals(type)) {
			return "gene";
		} else {
			type = type.toLowerCase();
			productDTO = new ProductDTO();
			if ("peptide".equals(type)) {
				if (productDTO.getPeptide().getGmpFlag() == null) {
					productDTO.getPeptide().setGmpFlag(0);
				}
				productDTO.setPeptide(this.productService.getPeptideDetail(id));
			} else if ("antibody".equals(type)) {
				productDTO.setAntibody(this.productService
						.getAntibodyDetail(id));
			} else if ("enzyme".equals(type)) {
				productDTO.setEnzyme(this.productService.getEnzymeDetail(id));
			} else if ("gene".equals(type)) {
				productDTO.setGene(this.productService.getGeneDetail(id));
			} else if ("kit".equals(type)) {
				productDTO.setKit(this.productService.getKitDetail(id));
			} else if ("oligo".equals(type)) {
				productDTO.setOligo(this.productService.getOligoDetail(id));
			} else if ("protein".equals(type)) {
				productDTO.setProtein(this.productService.getProteinDetail(id));
			} else if ("molecule".equals(type)) {
				productDTO.setMolecule(this.productService
						.getMoleculeDetail(id));
			} else if ("chemicals".equals(type)) {
				productDTO.setChemical(this.productService
						.getChemicalDetail(id));
			} else if ("vector".equals(type)) {
				productDTO.setSdvector(this.productService.getSDVector(id));
				String catalogNO2 = this.productService.getPdtCatalogNo(id);

				if (catalogNO2 != null && !"".equals(catalogNO2)) {
					urllink = "http://www.genscript.com/product_001/marker/op/showezmap/code/"
							+ catalogNO2 + "/" + catalogNO2 + ".html";
					System.out.println(urllink);
				}

			} else {
				return "none";
			}
		}
		return type;
	}

	/*
	 * del session supplier
	 */
	@SuppressWarnings("unchecked")
	public String delSupplierSession() {
		if (supplierIdStr != null) {
			String[] delPdt = supplierIdStr.split(",");
			Object obj = SessionUtil
					.getRow(SessionPdtServ.DelProductSupplier.value(),
							sessionProductId);
			List<Integer> delList;
			if (obj != null) {
				delList = (List<Integer>) obj;
			} else {
				delList = new ArrayList<Integer>();
			}
			// List<Integer> delPdtList = new ArrayList<Integer>();
			for (int i = 0; i < delPdt.length; i++) {
				if (SessionUtil.getOneRow(
						SessionPdtServ.ProductSupplier.value(),
						sessionProductId, delPdt[i]) == null) {
					delList.add(Integer.valueOf(delPdt[i]));
				} else {
					Object supplierMap = SessionUtil.getOneRow(
							SessionPdtServ.ProductSupplier.value(),
							sessionProductId, delPdt[i]);
					VendorProduct supplieries = (VendorProduct) supplierMap;
					if (supplieries.getId() == null) {
						SessionUtil.updateOneRow(
								SessionPdtServ.ProductSupplier.value(),
								sessionProductId, delPdt[i], null);
					} else {
						delList.add(Integer.valueOf(delPdt[i]));
					}
				}
			}
			if (!delList.isEmpty()) {
				if (obj == null) {
					SessionUtil.insertRow(
							SessionPdtServ.DelProductSupplier.value(),
							sessionProductId, delList);
				} else {
					SessionUtil.updateRow(
							SessionPdtServ.DelProductSupplier.value(),
							sessionProductId, delList);
				}
			}
			Struts2Util.renderText(SUCCESS);
		} else {
			Struts2Util.renderText(ERROR);
		}
		return null;
	}

	/*
	 * session save supplier
	 */
	public String saveSupplierSession() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		String supplierKey;
		String isRemove = "flase";
		if (this.supplierId == null && vendorProduct.getId() == null) {
			supplierKey = SessionUtil.generateTempId();
		} else {
			if (vendorProduct.getId() != null) {
				supplierKey = vendorProduct.getId().toString();
			} else {
				supplierKey = this.supplierId;
			}
			isRemove = "true";
		}
		SessionUtil.updateOneRow(SessionPdtServ.ProductSupplier.value(),
				sessionProductId, supplierKey, vendorProduct);
		rt.put("message", SUCCESS);
		rt.put("id", supplierKey);
		rt.put("isRemove", isRemove);
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * product supplier picker
	 */
	public String showSupplierPikcerAct() {
		vendorDTOList = this.productService.getAllSuppliesList(vendorName);
		return "showSupplierPikcerAct";
	}

	/*
	 * product supplier edit or new;
	 */
	public String showEditSuppler() {
		if (supplierId != null) {
			Object obj = SessionUtil.getOneRow(
					SessionPdtServ.ProductSupplier.value(), sessionProductId,
					supplierId);
			if (obj != null) {
				vendorProduct = (VendorProduct) obj;
				Vendor vendorTemp = this.vendorDao.getById(vendorProduct
						.getVendorNo());
				vendorName = vendorTemp.getVendorName();
			} else {
				vendorProduct = this.productService
						.getVendorProductDetail(Integer.valueOf(supplierId));
				Vendor vendorTemp = this.vendorDao.getById(vendorProduct
						.getVendorNo());
				vendorName = vendorTemp.getVendorName();
			}

		}
		return "showEditSuppler";
	}

	/*
	 * product Supplier List
	 */
	public String productOfSupplierList() {
		verdonProductList = this.productService.getPdtSupplierList(catalogNo);
		if (sessionProductId != null) {
			// 判断sessionProductId是否数字
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(sessionProductId);
			if (isNum.matches()) {
				this.unitCostAppr = productService.checkPropertyApproved(
						Integer.parseInt(sessionProductId),
						RequestApproveType.unitCost.name(),
						RequestApproveType.Product.name());
			}

		}
		purchaseOrderList = this.erpSalesOrderService
				.getPurchaseOrderList(catalogNo);

		/*
		 * this.productService .getPdtPurchaseOrderList(catalogNo);
		 */
		return "supplier";
	}

	/*
	 * product more info
	 */
	public String showMoreInfo() {
		if (id != null) {
			productExtendedInfo = this.productService.getPdtExtendedInfo(id);
			productDoc = this.productService.searchDocumentByProductId(id);
			// productDoc = this.productService.getProductDocument(id);
			this.productReferenceList = this.productService
					.searchProductReferenceByProductId(id);
			this.pbOptionItemList = this.publicService
					.getDropdownList("PRODUCT_FILE_TYPE");
		} else {
			productExtendedInfo = new ProductExtendedInfo();
			productDoc = new ArrayList<Documents>();
			this.pbOptionItemList = this.publicService
					.getDropdownList("PRODUCT_FILE_TYPE");
		}

		return "showMoreInfo";
	}

	/*
	 * 
	 * 
	 * @SuppressWarnings("unchecked") public String delFileProductSession()
	 * throws Exception{ if(this.delFileProduct!=null){ String[] delfileProducts
	 * = delFileProduct.split(","); Object obj =
	 * SessionUtil.getRow(SessionPdtServ.DelFileProductMoreInfo.value(),
	 * sessionProductId); List<Integer> delList; if(obj!=null){ delList =
	 * (List<Integer>)obj; }else{ delList = new ArrayList<Integer>(); }
	 * //List<Integer> delPdtList = new ArrayList<Integer>(); for(int
	 * i=0;i<delfileProducts.length;i++){
	 * if(SessionUtil.getOneRow(SessionPdtServ.FileProductMoreInfo.value(),
	 * sessionProductId, delfileProducts[i])==null){
	 * delList.add(Integer.valueOf(delfileProducts[i])); }else{
	 * SessionUtil.updateOneRow(SessionPdtServ.FileProductMoreInfo.value(),
	 * sessionProductId, delfileProducts[i],null ); } } if(!delList.isEmpty()){
	 * if(obj==null){
	 * SessionUtil.insertRow(SessionPdtServ.DelFileProductMoreInfo.value(),
	 * sessionProductId, delList); }else{
	 * SessionUtil.updateRow(SessionPdtServ.DelFileProductMoreInfo.value(),
	 * sessionProductId, delList); } } Struts2Util.renderText(SUCCESS); }else{
	 * Struts2Util.renderText(ERROR); } return null; }
	 * 
	 * 
	 * product moreInfo upload files
	 * 
	 * public String saveProductFilesSession() throws Exception{
	 * if(upload==null){
	 * Struts2Util.renderHtml("The upload document does not exist!"); }else{
	 * ProductDocuments pdtDocument = new ProductDocuments(); String newFileName
	 * = SessionUtil.generateTempId(); String defExt =
	 * fileService.getExtension(uploadFileName); if(defExt == null){
	 * Struts2Util.renderHtml("The document extension is not correct!!"); }else{
	 * pdtDocument.setDocName(uploadFileName);
	 * pdtDocument.setDocType(productFilesType);
	 * pdtDocument.setFileType(defExt);
	 * //pdtDocument.setFileType(uploadContentType);
	 * pdtDocument.setFilePath("productFile_notes/"+newFileName+"."+defExt);
	 * SessionUtil.updateOneRow(SessionPdtServ.FileProductMoreInfo.value(),
	 * sessionProductId, newFileName, pdtDocument);
	 * fileService.uploadFile(upload, uploadContentType,
	 * newFileName+"."+defExt,"productFile_notes");
	 * Struts2Util.renderHtml(SUCCESS+",");
	 * Struts2Util.renderHtml(newFileName+",");
	 * if(productFilesType.substring(0,8).equals("Document")){
	 * Struts2Util.renderHtml(productFilesType.substring(9)+","); }else{
	 * Struts2Util.renderHtml(productFilesType+","); }
	 * Struts2Util.renderHtml(uploadFileName+",");
	 * if(productFilesType.substring(0,8).equals("Document")){
	 * Struts2Util.renderHtml(productFilesType.substring(0,8)); }else{
	 * Struts2Util.renderHtml(productFilesType); } } } return null; }
	 */

	/*
	 * product misc
	 */
	public String showMiscAct() {
		if (catalogNo != null) {
			this.royolty = this.productService.getRoyaltyProduct(catalogNo);
		} else {
			this.royolty = new RoyaltyProductDTO();
		}
		System.out.println(sessionProductId);
		if (sessionProductId != null) {
			productDTO = productService.getProductDetail(Integer
					.parseInt(sessionProductId));
		}
		this.generateNoticeOption = this.publicService
				.getDropdownList("PROD_NT_GEN_TIME");
		this.sendOrderedOption = this.publicService
				.getDropdownList("PROD_NT_SEND_TYPE");
		return "showMiscAct";
	}

	/*
	 * product misc royalty
	 */
	public String showRoyaltiesList() {
		this.royaltyList = this.productService.getPdtRoyaltyList(miscName);
		return "showRoyaltiesList";
	}

	/*
    *
    */

	/*
	 * product sales
	 */
	public String showProductSales() {
		if (catalogNo != null && isFalse.equals("yes")) {
			if (salesPeriod != null) {
				if (salesPeriod.equals("lastWeek")) {
					fromDate = DateUtils.formatStr2Date(
							DateUtils.getPreviousMonday(),
							DateUtils.C_DATE_PATTON_DEFAULT);
					toDate = DateUtils.formatStr2Date(
							DateUtils.getPreviousSunday(),
							DateUtils.C_DATE_PATTON_DEFAULT);
				} else if (salesPeriod.equals("thisWeek")) {
					fromDate = DateUtils.formatStr2Date(
							DateUtils.getCurrentMonday(),
							DateUtils.C_DATE_PATTON_DEFAULT);
					toDate = DateUtils.getWeekEndDay(new Date());
				} else if (salesPeriod.equals("lastMonth")) {
					fromDate = DateUtils.formatStr2Date(
							DateUtils.getLastMonth(),
							DateUtils.C_DATE_PATTON_DEFAULT);
					toDate = DateUtils.formatStr2Date(
							DateUtils.getLastDayOfMonth(fromDate),
							DateUtils.C_DATE_PATTON_DEFAULT);
				} else if (salesPeriod.equals("thisMonth")) {
					fromDate = DateUtils.formatStr2Date(
							DateUtils.getFirstDayOfMonth(new Date()),
							DateUtils.C_DATE_PATTON_DEFAULT);
					toDate = DateUtils.formatStr2Date(
							DateUtils.getLastDayOfMonth(new Date()),
							DateUtils.C_DATE_PATTON_DEFAULT);
				} else if (salesPeriod.equals("lastQuarter")) {
					fromDate = DateUtils.getQuarterStartDay(DateUtils
							.defineMonthBefore2Date(new Date(), -3));
					toDate = DateUtils.getQuarterEndDay(DateUtils
							.defineMonthBefore2Date(new Date(), -3));
				} else if (salesPeriod.equals("thisQuarter")) {
					fromDate = DateUtils.getQuarterStartDay(new Date());
					toDate = DateUtils.getQuarterEndDay(new Date());
				} else if (salesPeriod.equals("last6Months")) {
					fromDate = DateUtils.getLast6MonthsStartDay(DateUtils
							.defineMonthBefore2Date(new Date(), -6));
					toDate = DateUtils.getLast6MonthsEndDay(DateUtils
							.defineMonthBefore2Date(new Date(), -6));
				} else if (salesPeriod.equals("lastYear")) {
					fromDate = DateUtils.getYearStartDay(DateUtils
							.defineMonthBefore2Date(new Date(), -12));
					toDate = DateUtils.getYearEndDay(DateUtils
							.defineMonthBefore2Date(new Date(), -12));
				} else if (salesPeriod.equals("thisYear")) {
					fromDate = DateUtils.getYearStartDay(new Date());
					toDate = DateUtils.getYearEndDay(new Date());
				}
			}
			toDate = DateUtils.defineDayBefore2Date(toDate, 1);
			try {
				productSaleDTO = this.productService.getProductSale(catalogNo,
						fromDate, toDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				productSaleDTO.setPicName(this.searchAnalysis());
				System.out.println(productSaleDTO.getPicName()
						+ ">>>>>>>>>>>>>>>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("end");
			Struts2Util.renderJson(productSaleDTO);
			return null;
		}
		return "showProductSales";
	}

	/*
	 * Sales Person(s) Selling this Product
	 */
	public String showSalesRanking() {
		if (top == null) {
			top = 5;
		}
		if (lastDays.equals("lastYear")) {
			fromDate = DateUtils.getYearStartDay(DateUtils
					.defineMonthBefore2Date(new Date(), -12));
			toDate = DateUtils.getYearEndDay(DateUtils.defineMonthBefore2Date(
					new Date(), -12));
		} else if (lastDays.equals("lastWeek")) {
			fromDate = DateUtils.formatStr2Date(DateUtils.getPreviousMonday(),
					DateUtils.C_DATE_PATTON_DEFAULT);
			toDate = DateUtils.formatStr2Date(DateUtils.getPreviousSunday(),
					DateUtils.C_DATE_PATTON_DEFAULT);
		} else if (lastDays.equals("lastMonth")) {
			fromDate = DateUtils.formatStr2Date(DateUtils.getLastMonth(),
					DateUtils.C_DATE_PATTON_DEFAULT);
			toDate = DateUtils.formatStr2Date(
					DateUtils.getLastDayOfMonth(fromDate),
					DateUtils.C_DATE_PATTON_DEFAULT);
		} else if (lastDays.equals("last3Months")) {
			fromDate = DateUtils.getQuarterStartDay(DateUtils
					.defineMonthBefore2Date(new Date(), -3));
			toDate = DateUtils.getQuarterEndDay(DateUtils
					.defineMonthBefore2Date(new Date(), -3));
		} else if (lastDays.equals("last6Months")) {
			fromDate = DateUtils.getLast6MonthsStartDay(DateUtils
					.defineMonthBefore2Date(new Date(), -6));
			toDate = DateUtils.getLast6MonthsEndDay(DateUtils
					.defineMonthBefore2Date(new Date(), -6));
		}
		System.out.println(fromDate + "  " + toDate);
		this.salesRankingList = this.productService.getSalesRanking(catalogNo,
				top, fromDate, toDate);
		Struts2Util.renderJson(salesRankingList);
		return null;
	}

	/*
	 * product sales 图片显示;
	 */
	private String searchAnalysis() throws Exception {
		ProductReportSrchDTO srchDTO = new ProductReportSrchDTO();
		List<AnalysisReport> analysisReportList;
		srchDTO.setCatalogNO(catalogNo);
		srchDTO.setBeginDate(fromDate);
		srchDTO.setEndDate(toDate);
		srchDTO.setPeriod(periodType);
		System.out.println("srchDTO: " + srchDTO);
		analysisReportList = this.productService.getPdtSalesReport(srchDTO,
				salesPeriodBasedOn);
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		for (int i = 0; i < analysisReportList.size(); i++) {
			if (analysisReportList.get(i).getVisit() == 0) {
				dataSet.addValue(null, "", String.valueOf(i + 1));
			} else {
				dataSet.addValue(analysisReportList.get(i).getVisit(), "",
						String.valueOf(i + 1));
			}
		}
		JFreeChart chart = null;

		if (periodType == 1) {
			chart = ChartFactory.createBarChart(null, "Day",
					salesPeriodBasedOn, dataSet, PlotOrientation.VERTICAL,
					false, false, false);
		} else if (periodType == 7) {
			chart = ChartFactory.createBarChart(null, "Week",
					salesPeriodBasedOn, dataSet, PlotOrientation.VERTICAL,
					false, false, false);
		} else if (periodType == 30) {
			chart = ChartFactory.createBarChart(null, "Month",
					salesPeriodBasedOn, dataSet, PlotOrientation.VERTICAL,
					false, false, false);
		} else if (periodType == 90) {
			chart = ChartFactory.createBarChart(null, "Quarter",
					salesPeriodBasedOn, dataSet, PlotOrientation.VERTICAL,
					false, false, false);
		} else {
			chart = ChartFactory.createBarChart(null, "Year",
					salesPeriodBasedOn, dataSet, PlotOrientation.VERTICAL,
					false, false, false);
		}
		// Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);
		// chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
		// RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		// chart.setAntiAlias(false);
		CategoryPlot plot = chart.getCategoryPlot();// 设置图的高级属性
		CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesPaint(0, Color.BLUE);
		// BarRenderer3D renderer = new BarRenderer3D();//3D属性修改
		// CategoryAxis domainAxis = plot.getDomainAxis();//对X轴做操作
		// ValueAxis rAxis = plot.getRangeAxis();//对Y轴做操作
		// domainAxis.setLabelFont(labelFont);
		// domainAxis.setTickLabelPaint(Color.GREEN);//X轴的标题文字颜色
		// domainAxis.setAxisLinePaint(Color.BLUE);//X轴横线颜色
		// rAxis.setLabelPaint(Color.GREEN);
		// rAxis.setTickMarkPaint(Color.BLUE);

		plot.setBackgroundPaint(Color.LIGHT_GRAY);
		plot.setDomainGridlinePaint(Color.BLACK);
		plot.setDomainGridlinesVisible(true);
		// 设置网格横线颜色
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlinesVisible(true);
		String picName = SessionUtil.generateTempId();
		FileOutputStream fos = null;

		try {
			String rootPath = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("\\");
			System.out.println("rootPath: " + rootPath);
			System.out.println(picName + "<<<<<<<<<<<<<<<<<<<");
			fos = new FileOutputStream(
					"/usr/local/jboss/server/default/deploy/ROOT.war"
							+ "/images/temp/" + picName + ".png");
			ChartUtilities.writeChartAsPNG(fos, chart, 730, 200, null);
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return picName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genscript.gsscm.common.web.BaseAction#input() product修改与新建;
	 */
	@Override
	public String input() throws Exception {
		System.out.println(id);
		System.out.println(type);

		if (id == null) {
			sessionProductId = SessionUtil.generateTempId();
			// *********** Add By Zhang Yong Start
			// *****************************//
			// 释放application中的订单锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
			// *********** Add By Zhang Yong End *****************************//
		} else {
			// *********** Add By Zhang Yong Start
			// *****************************//
			// 判断将要修改的单号是否正在被操作
			String editUrl = "product/product!input.action?id=" + id;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
			}
			// *********** Add By Zhang Yong End *****************************//
			sessionProductId = id.toString();
		}
		if (defaultTab == null) {
			defaultTab = 0;
		}
		SessionUtil.deleteRow(SessionPdtServ.ProductSupplier.value(),
				sessionProductId);
		SessionUtil.deleteRow(SessionPdtServ.DelProductSupplier.value(),
				sessionProductId);
		SessionUtil.deleteRow(SessionPdtServ.FileProductMoreInfo.value(),
				sessionProductId);
		SessionUtil.deleteRow(SessionPdtServ.RestrictShipList.value(),
				sessionProductId);
		SessionUtil.deleteRow(SessionPdtServ.ProductRelation.value(),
				sessionProductId);
		SessionUtil.deleteRow(SessionPdtServ.ProductApprovedName.value(),
				sessionProductId);
		SessionUtil.deleteRow(SessionPdtServ.ProductApprovedNameReason.value(),
				sessionProductId);
		SessionUtil.deleteRow(SessionPdtServ.ProductApprovedStatus.value(),
				sessionProductId);
		SessionUtil.deleteRow(
				SessionPdtServ.ProductApprovedStatusReason.value(),
				sessionProductId);
		SessionUtil.deleteRow(SessionPdtServ.ProductPriceApproved.value(),
				sessionProductId);
		SessionUtil.deleteRow(
				SessionPdtServ.ProductPriceApprovedReason.value(),
				sessionProductId);
		SessionUtil.deleteRow(SessionConstant.ProductPricing.value(),
				sessionProductId);
		SessionUtil.deleteRow(SessionPdtServ.DelProductPrice.value(),
				sessionProductId);
		SessionUtil.deleteRow(SessionPdtServ.SpecailPrice.value(),
				sessionProductId);
		SessionUtil.deleteRow(SessionPdtServ.DelSpecailPrice.value(),
				sessionProductId);
		SessionUtil.deleteRow(SessionPdtServ.ComponentList.value(),
				sessionProductId);
		SessionUtil.deleteRow(SessionPdtServ.DelComponentList.value(),
				sessionProductId);
		List<SpecDropDownListName> specDropDownListNames = new ArrayList<SpecDropDownListName>();
		specDropDownListNames.add(SpecDropDownListName.PRODUCT_CLASSIFICATION);
		specDropDownListNames.add(SpecDropDownListName.TAX_STATUS_COUNTRY);
		specDropDownListNames.add(SpecDropDownListName.TAX_STATUS_NATIONAL);
		specDropDownListNames.add(SpecDropDownListName.TAX_STATUS_STATE);
		this.dropDownDTOList = this.productService
				.getRelationItemByProductId(id);
		this.arrDropdownList = this.publicService
				.getSpecDropDownList(specDropDownListNames);
		this.pbOptionItemList = this.publicService
				.getDropdownList("PACKAGE_TYPE");
		this.nameAppr = productService.checkPropertyApproved(
				entity.getProductId(), RequestApproveType.name.name(),
				RequestApproveType.Product.name());
		this.statusAppr = productService.checkPropertyApproved(
				entity.getProductId(), RequestApproveType.status.name(),
				RequestApproveType.Product.name());

		return "productCreateForm";
	}

	/*
	 * 查询product_list_view视图中的数据 productCategory add product
	 */
	public String catPdtAddAct() throws Exception {
		Map<String, String> filterMap = new HashMap<String, String>();
		// 获得分页请求相关数据：如第几页
		PagerUtil<ProductListBean> pagerUtil = new PagerUtil<ProductListBean>();
		pageBean = pagerUtil.getRequestPage();
		if (propValue == null) {
			filterMap.put("EQS_catalogNo", "0");
		} else {
			if (propName.equals("checkType")) {
				propName = "type";
			}
			filterMap.put(srchOperator + "S_" + propName, propValue);
		}
		if (!pageBean.isOrderBySetted()) {
			pageBean.setOrderBy("productId");
			pageBean.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		if (pageBean.getPageSize() == null
				|| pageBean.getPageSize().intValue() < 1) {
			pageBean.setPageSize(20);
		}
		pageBean = this.productService.searchProductList(pageBean, filterMap);
		Catalog catalog = this.productService.getCatalogByCatalogId(catalogId);
		dropDownDTO = publicService
				.getSpecDropDownList(SpecDropDownListName.PRODUCT_CLASSIFICATION);
		this.enforcePriceLimit = catalog.getPriceLimit();
		this.symbolPrice = this.productService.getProductPriceSymbol(catalog
				.getCurrencyCode());
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(pageBean);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "catPdtAddAct";
	}

	/**
	 * 查询页面上的基本数据, 进入查询页面，高级查询。
	 * 
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception {

		attrList = commonSearchService
				.getSearchAttributeList(SearchType.PRODUCT);
		countryList = publicService.getCountryList();
		ProductTypeList = publicService.getProductTypeList();
		return "search";
	}

	public List<ProductClass> getProductTypeList() {
		return ProductTypeList;
	}

	public void setProductTypeList(List<ProductClass> productTypeList) {
		ProductTypeList = productTypeList;
	}

	public String listExcel() {
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());

		this.excelResult = this.productService
				.searchAllProductListOfFilter(filters);
		return "excel";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genscript.gsscm.common.web.BaseAction#list()
	 */
	@Override
	public String list() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<ProductListBean> pagerUtil = new PagerUtil<ProductListBean>();
		pageBean = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (pageBean.getPageSize() == null
				|| pageBean.getPageSize().intValue() < 1) {
			pageBean.setPageSize(15);
		}
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (!pageBean.isOrderBySetted()) {
			pageBean.setOrderBy("productId");
			pageBean.setOrder(Page.DESC);
		}
		pageBean = this.productService.searchProductList(pageBean, filters);
		this.statusOfapproved = this.productService
				.getApprovedRequestListByTableTypeStatus(RequestApproveType.Product
						.name());
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(pageBean);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return SUCCESS;
	}

	private String shortDesc;
	private String symbol;
	private String names;
	private String size;
	private String status1;

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public boolean checkall(List<ProductOfPdtcategoryBean> plist, Integer key) {
		boolean flag = false;
		if (plist.size() > 0) {
			for (int i = 0; i < plist.size(); i++) {
				ProductOfPdtcategoryBean pdd = plist.get(i);
				if (pdd.getProductId().equals(key)) {
					return true;
				}
			}
		}
		return flag;
	}

	public String addProductToCategory() {
		Map<String, Object> rt = new HashMap<String, Object>();
		List<ProductOfPdtcategoryBean> listcategroty = this.productService
				.getProductOfPdtcategoryLisst(Integer
						.parseInt(sessionCategoryId));
		String productkey[] = productKey.split(",");
		String standrdpricesdate[] = standrdPricesDate.split(",");
		String pricelimitdate[] = new String[1000];
		if (priceLimitDate != null) {
			pricelimitdate = priceLimitDate.split(",");
		}
		String status1s[] = new String[1000];
		if (status1 != null) {
			status1s = status1.split(",");
		}
		int size = 0;
		if (listcategroty != null) {
			size = listcategroty.size();
		}
		int lengths = 0;
		if (productkey != null) {
			lengths = productkey.length;
		}
		ProductPriceDTO productPriceDTO = new ProductPriceDTO();
		ProductOfPdtcategoryBean productOfPdtcategoryBean = new ProductOfPdtcategoryBean();
		if (size == 1) {
			if (lengths == 1) {
				// 只有一条数据的时候
				productOfPdtcategoryBean = (ProductOfPdtcategoryBean) listcategroty
						.get(0);
				if (productOfPdtcategoryBean.getProductId().toString()
						.equals(productkey[0].toString())) {
					rt.put("message", "error");
					Struts2Util.renderJson(rt);
					return null;
				}
			} else if (lengths > 1) {
				for (int i = 0; i < lengths; i++) {
					if (!checkall(listcategroty,
							Integer.parseInt(productkey[i]))) {
						Long d = productService
								.getCountBycatalogIdandproductId(catalogId,
										Integer.valueOf(productkey[0]));
						System.out.println(d.toString());
						if (d == 0) {
							productPriceDTO.setCatalogId(catalogId);
							productPriceDTO.setCategoryId(Integer
									.valueOf(sessionCategoryId));
							productPriceDTO.setProductId(Integer
									.valueOf(productkey[i]));

							if (standrdpricesdate[i] != null) {
								productPriceDTO.setStandardPrice(Double
										.valueOf(standrdpricesdate[i]));
							}
							if (pricelimitdate[i] != null) {
								productPriceDTO.setLimitPrice(Double
										.valueOf(pricelimitdate[i]));
							}
							productPriceDTO.setStatus(status1s[i]);
							SessionUtil.updateOneRow(
									SessionPdtServ.Product.value(),
									sessionCategoryId, productkey[i],
									productPriceDTO);
							rt.put("message", "ok");
							Struts2Util.renderJson(rt);
							return null;
						} else {
							rt.put("message", "error");
							Struts2Util.renderJson(rt);
							return null;
						}

					}
				}
			}
		}
		if (size > 1) {
			if (lengths == 1) {
				for (int i = 0; i < size;) {
					ProductOfPdtcategoryBean pdd = listcategroty.get(i);
					if (pdd.getProductId().equals(productkey[0])) {
						rt.put("message", "error");
						Struts2Util.renderJson(rt);
						return null;
					} else {
						Long d = productService
								.getCountBycatalogIdandproductId(catalogId,
										Integer.valueOf(productkey[0]));
						System.out.println(d.toString());
						if (d == 0) {
							productPriceDTO.setCatalogId(catalogId);
							productPriceDTO.setCategoryId(Integer
									.valueOf(sessionCategoryId));
							productPriceDTO.setProductId(Integer
									.valueOf(productkey[0]));

							if (standrdpricesdate[0] != null) {
								productPriceDTO.setStandardPrice(Double
										.valueOf(standrdpricesdate[0]));
							}
							if (pricelimitdate[0] != null) {
								productPriceDTO.setLimitPrice(Double
										.valueOf(pricelimitdate[0]));
							}
							productPriceDTO.setStatus(status1s[0]);
							SessionUtil.updateOneRow(
									SessionPdtServ.Product.value(),
									sessionCategoryId, productkey[0],
									productPriceDTO);
							rt.put("message", "ok");
							Struts2Util.renderJson(rt);
							return null;
						} else {
							rt.put("message", "error");
							Struts2Util.renderJson(rt);
							return null;

						}
					}
				}
			}
			int count = 0;
			int count2 = 0;
			if (lengths > 1) {
				for (int i = 0; i < lengths; i++) {
					if (!checkall(listcategroty,
							Integer.parseInt(productkey[i]))) {
						Long d = productService
								.getCountBycatalogIdandproductId(catalogId,
										Integer.valueOf(productkey[i]));
						System.out.println(d.toString());
						if (d == 0) {
							productPriceDTO.setCatalogId(catalogId);
							productPriceDTO.setCategoryId(Integer
									.valueOf(sessionCategoryId));
							productPriceDTO.setProductId(Integer
									.valueOf(productkey[i]));

							if (standrdpricesdate[i] != null) {
								productPriceDTO.setStandardPrice(Double
										.valueOf(standrdpricesdate[i]));
							}
							if (pricelimitdate[i] != null) {
								productPriceDTO.setLimitPrice(Double
										.valueOf(pricelimitdate[i]));
							}
							productPriceDTO.setStatus(status1s[i]);
							SessionUtil.updateOneRow(
									SessionPdtServ.Product.value(),
									sessionCategoryId, productkey[i],
									productPriceDTO);
							count2++;
						} else {
							count++;
						}
					}
				}
				if (count2 == lengths && count == 0) {
					rt.put("message", "ok");
				} else if (count2 < lengths && count >= 1) {
					rt.put("message", "some");
				} else if (count2 == 0 && count == lengths) {
					rt.put("message", "error");

				}
				Struts2Util.renderJson(rt);
				return null;

			}
		}

		if (size == 0) {
			for (int i = 0; i < lengths; i++) {
				productPriceDTO.setCatalogId(catalogId);
				productPriceDTO.setCategoryId(Integer
						.valueOf(sessionCategoryId));
				productPriceDTO.setProductId(Integer.valueOf(productkey[i]));

				if (standrdpricesdate[i] != null) {
					productPriceDTO.setStandardPrice(Double
							.valueOf(standrdpricesdate[i]));
				}
				if (pricelimitdate[i] != null) {
					productPriceDTO.setLimitPrice(Double
							.valueOf(pricelimitdate[i]));
				}
				productPriceDTO.setStatus(status1s[i]);
				SessionUtil.updateOneRow(SessionPdtServ.Product.value(),
						sessionCategoryId, productkey[i], productPriceDTO);
			}
			rt.put("message", "ok");
			Struts2Util.renderJson(rt);
			return null;
		}
		return null;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@SuppressWarnings("unchecked")
	public String productOfPdtCategoryList() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<ProductOfPdtcategoryBean> pagerUtil = new PagerUtil<ProductOfPdtcategoryBean>();
		pageOfCategory = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (pageOfCategory.getPageSize() == null
				|| pageOfCategory.getPageSize().intValue() < 1) {
			pageOfCategory.setPageSize(15);
		}
		Map<String, String> filterMap = new HashMap<String, String>();
		String categoryId = ServletActionContext.getRequest().getParameter(
				"categoryId");
		String catalogNo = ServletActionContext.getRequest().getParameter(
				"catalogNo");
		String name = ServletActionContext.getRequest().getParameter("name");
		if (categoryId == null || "".equals(categoryId)) {
			categoryId = "0";
		}
		if (catalogNo != null && !"".equals(catalogNo)) {
			filterMap.put("EQS_catalogNo", catalogNo);
		}
		if (name != null && !"".equals(name)) {
			filterMap.put("EQS_name", name);
		}
		filterMap.put("EQI_categoryId", categoryId);/*
													 * pageOfCategory.setOrderBy(
													 * "productId");
													 * pageOfCategory
													 * .setOrder(Page.DESC);
													 */
		pageOfCategory = this.productService.getProductOfPdtCategoryList(
				pageOfCategory, filterMap);
		Catalog catalog = this.productService.getCatalogByCatalogId(catalogId);
		this.symbolPrice = this.productService.getProductPriceSymbol(catalog
				.getCurrencyCode());
		/*
		 * 将session中对应删除信息，在LIST时去除。
		 */
		Object obj = SessionUtil.getRow(SessionPdtServ.DelProduct.value(),
				sessionCategoryId);
		if (obj != null) {
			List<Integer> delList = (List<Integer>) obj;
			for (int j = 0; j < delList.size(); j++) {
				for (int i = 0; i < pageOfCategory.getResult().size(); i++) {
					if (pageOfCategory.getResult().get(i).getProductId()
							.equals(delList.get(j))) {
						pageOfCategory.getResult().remove(i);
					}
				}
			}
		}
		/*
		 * 
		 * 将session中对应save信息，在LIST中增加，只增加在第一页。其他页面没有。
		 */
		obj = SessionUtil.getRow(SessionPdtServ.Product.value(),
				sessionCategoryId);
		if (obj != null) {
			if (pageOfCategory.getPageNo() == 1) {
				Map<String, ProductPriceDTO> productPriceDTOMap = (Map<String, ProductPriceDTO>) obj;
				for (Map.Entry<String, ProductPriceDTO> entry : productPriceDTOMap
						.entrySet()) {
					ProductPriceDTO productCategory = productPriceDTOMap
							.get(entry.getKey());
					if (productCategory != null) {
						ProductDTO product = this.productService
								.getProductDetail(productCategory
										.getProductId());
						ProductOfPdtcategoryBean bean = this.dozer.map(product,
								ProductOfPdtcategoryBean.class);
						List<DropDownDTO> dropDownDTO = publicService
								.getSpecDropDownList(SpecDropDownListName.PRODUCT_CLASSIFICATION);
						for (DropDownDTO dropDown : dropDownDTO) {
							if (dropDown.getId().equals(
									product.getProductClsId().toString())) {
								bean.setType(dropDown.getName());
							}
						}
						pageOfCategory.getResult().add(bean);
					}
				}
			}
		}
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(pageOfCategory);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		if ("catalogCategoryList".equals(callBackName)) {
			return "catalogCategoryList";
		}
		return "pdtOfCategory";
	}

	private String PcreationDate;

	public String getPcreationDate() {
		return PcreationDate;
	}

	public void setPcreationDate(String pcreationDate) {
		PcreationDate = pcreationDate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String save() {
		PcreationDate = ServletActionContext.getRequest().getParameter(
				"PcreationDate2");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (PcreationDate != null && !"".equals(PcreationDate)) {
				productDTO.setCreationDate(format1.parse(PcreationDate));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String modifyDate = ServletActionContext.getRequest().getParameter(
				"modifyDate");
		String publishDate = ServletActionContext.getRequest().getParameter(
				"publishDate");
		try {
			if (modifyDate != null && !"".equals(modifyDate)) {
				productDTO.setModifyDate(format1.parse(modifyDate));
			}
			if (publishDate != null && !"".equals(publishDate)) {
				productDTO.setPublishDate(format1.parse(publishDate));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Integer loginUserId = SessionUtil.getUserId();
		Map<String, Object> rt = new HashMap<String, Object>();
		// *********** Add By Zhang Yong Start *****************************//
		// 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
		if (sessionProductId != null && !("").equals(sessionProductId)) {
			String editUrl = "product/product!input.action?id="
					+ sessionProductId;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
				rt.put("message",
						"Save product fail,the product is editing by "
								+ operation_method);
				rt.put("id", sessionProductId);
				rt.put("type", type.toLowerCase());
				Struts2Util.renderJson(rt);
				return null;
			}
		}

		// *********** Add By Zhang Yong End *****************************//
		try {
			if (productDTO.getInvoiceable() == null) {
				productDTO.setInvoiceable("Y");
			}
			if (productDTO.getTaxable() == null) {
				productDTO.setTaxable("Y");
			}
			if (productDTO.getDiscountable() == null) {
				productDTO.setDiscountable("Y");
			}
			if (productDTO.getQuotable() == null) {
				productDTO.setQuotable("Y");
			}
			if (productDTO.getLotControlFlag() == null) {
				productDTO.setLotControlFlag("Y");
			}
			if (productDTO.getSellable() == null) {
				productDTO.setSellable("Y");
			}
			if (productDTO.getShippable() == null) {
				productDTO.setShippable("Y");
			}
			if (productDTO.getPurchasable() == null) {
				productDTO.setPurchasable("Y");
			}
			if (productDTO.getReturnable() == null) {
				productDTO.setReturnable("Y");
			}
			if (productDTO.getGiftFlag() == null) {
				productDTO.setGiftFlag("N");
			}
			if (productDTO.getStockable() == null) {
				productDTO.setStockable("Y");
			}
			if (productDTO.getSerialControlFlag() == null) {
				productDTO.setSerialControlFlag("Y");
			}
			if (productDTO.getCompositeFlag() == null) {
				productDTO.setCompositeFlag("Y");
			}

			if (productDTO.getRoyaltyProduct() != null) {
				if (productDTO.getRoyaltyProduct().getGrossProfitCmsn() != null
						&& !"".equals(productDTO.getRoyaltyProduct()
								.getGrossProfitCmsn())) {
					String a = productDTO.getRoyaltyProduct()
							.getGrossProfitCmsn().toString();
					BigDecimal bd = new BigDecimal(a);
					productDTO.getRoyaltyProduct().setGrossProfitCmsn(bd);
				} else {
					productDTO.getRoyaltyProduct().setGrossProfitCmsn(
							BigDecimal.ZERO);
				}
				if (productDTO.getRoyaltyProduct().getSellingPriceCmsn() != null
						&& !"".equals(productDTO.getRoyaltyProduct()
								.getSellingPriceCmsn())) {
					String b = productDTO.getRoyaltyProduct()
							.getSellingPriceCmsn().toString();
					BigDecimal bd = new BigDecimal(b);
					productDTO.getRoyaltyProduct().setSellingPriceCmsn(bd);
				} else {
					productDTO.getRoyaltyProduct().setSellingPriceCmsn(
							BigDecimal.ZERO);
				}
				if (productDTO.getRoyaltyProduct().getUnitRateCmsn() != null
						&& !"".equals(productDTO.getRoyaltyProduct()
								.getUnitRateCmsn())) {
					String c = productDTO.getRoyaltyProduct().getUnitRateCmsn()
							.toString();
					BigDecimal bd = new BigDecimal(c);
					productDTO.getRoyaltyProduct().setUnitRateCmsn(bd);
				} else {
					productDTO.getRoyaltyProduct().setUnitRateCmsn(
							BigDecimal.ZERO);
				}

				if (productDTO.getUnitCost() != null
						&& !"".equals(productDTO.getUnitCost())) {
					String UnitCost = productDTO.getUnitCost().toString();
					BigDecimal bd = new BigDecimal(UnitCost);
					productDTO.setUnitCost(bd);
				} else {
					productDTO.setUnitCost(BigDecimal.ZERO);
				}
			}

			if (productDTO.getGrossProfitCmsn() != null
					&& !"".equals(productDTO.getGrossProfitCmsn())) {
				String GrossProfitCmsn = productDTO.getGrossProfitCmsn()
						.toString();
				BigDecimal bd = new BigDecimal(GrossProfitCmsn);
				productDTO.setGrossProfitCmsn(bd);
			}

			if (productDTO.getSellingPriceCmsn() != null
					&& !"".equals(productDTO.getSellingPriceCmsn())) {
				String SellingPriceCmsn = productDTO.getSellingPriceCmsn()
						.toString();
				BigDecimal bd = new BigDecimal(SellingPriceCmsn);
				productDTO.setSellingPriceCmsn(bd);
			}

			if (productDTO.getUnitRateCmsn() != null
					&& !"".equals(productDTO.getUnitRateCmsn())) {
				String UnitRateCmsn = productDTO.getUnitRateCmsn().toString();
				BigDecimal bd = new BigDecimal(UnitRateCmsn);
				productDTO.setUnitRateCmsn(bd);
			}

			if (this.vendor != null) {
				if (this.vendor
						.equals(ProductDetailType.AMERCANPEPTIDE.value())) {
					productDTO.getPeptide().setAmericanpeptideCatNo(
							this.vendorCatalog);
				} else if (this.vendor
						.equals(ProductDetailType.ANASPEC.value())) {
					productDTO.getPeptide().setAnaspecCatNo(this.vendorCatalog);
				} else if (this.vendor.equals(ProductDetailType.BACHEM.value())) {
					productDTO.getPeptide().setBachemCatNo(this.vendorCatalog);
				} else if (this.vendor.equals(ProductDetailType.PHOENIXPEPTIDE
						.value())) {
					productDTO.getPeptide().setPhoenixpeptideCatNo(
							this.vendorCatalog);
				}
			}
			productDTO.setType(type.toLowerCase());
			Object obj = SessionUtil.getRow(
					SessionPdtServ.ProductSupplier.value(), sessionProductId);
			if (obj != null) {
				Map<String, VendorProduct> vendorProductMap = (Map<String, VendorProduct>) obj;
				List<VendorProductDTO> vendorProductList = new ArrayList<VendorProductDTO>();
				for (Map.Entry<String, VendorProduct> entry : vendorProductMap
						.entrySet()) {
					VendorProduct vendorProduct = vendorProductMap.get(entry
							.getKey());
					if (vendorProduct != null) {
						vendorProductList.add(dozer.map(vendorProduct,
								VendorProductDTO.class));
					}
				}
				SessionUtil.deleteRow(SessionPdtServ.ProductSupplier.value(),
						sessionProductId);
				if (!vendorProductList.isEmpty()) {
					productDTO.setVendorProductList(vendorProductList);
				}
			}

			obj = SessionUtil.getRow(SessionPdtServ.DelProductSupplier.value(),
					sessionProductId);
			if (obj != null) {
				List<Integer> delList = (List<Integer>) obj;
				if (!delList.isEmpty()) {
					productDTO.setDelVendorProductIdList(delList);
				}
				SessionUtil.deleteRow(
						SessionPdtServ.DelProductSupplier.value(),
						sessionProductId);
			}

			Map<String, RestrictShipDTO> map = (Map<String, RestrictShipDTO>) SessionUtil
					.getRow(SessionPdtServ.RestrictShipList.value(),
							sessionProductId);
			List<RestrictShip> restrictShipList = new ArrayList<RestrictShip>();
			if (map != null) {
				// System.out.println("Map Size:"+map.size());
				for (int i = 0; i < map.keySet().size(); i++) {
					restrictShipList.add(dozer.map(
							map.get((String) map.keySet().toArray()[i]),
							RestrictShip.class));
				}
			}
			// System.out.println("RestrictShip Size"+restrictShipList.size());
			if (restrictShipList.size() > 0)
				productDTO.setRestrictShipList(restrictShipList);
			List<Integer> delShipList = (List<Integer>) SessionUtil.getRow(
					SessionPdtServ.DelRestrictShipList.value(),
					sessionProductId);
			if (delShipList != null && delShipList.size() > 0) {
				productDTO.setDelRestrictShipIdList(delShipList);
			}
			// set product more info Extended Info
			if (productExtendedInfo != null) {
				List<DropDownDTO> dropDownDTO = publicService
						.getSpecDropDownList(SpecDropDownListName.PRODUCT_CLASSIFICATION);
				String clsIdStr = "";
				for (int i = 0; i < dropDownDTO.size(); i++) {
					if (dropDownDTO.get(i).getId()
							.equals(productDTO.getProductClsId().toString())) {
						clsIdStr = dropDownDTO.get(i).getName();
					}
				}
				clsIdStr = clsIdStr.toLowerCase();
				if (clsIdStr.equals("chemicals")) {
					clsIdStr = "chemical";
				}
				String sizeStr = ServletActionContext.getRequest()
						.getParameter("sizeStr");
				System.out.println("-" + sizeStr + "-" + productDTO.getUom());
				String[] productCatalogNos = productDTO.getCatalogNo().split(
						"-" + sizeStr + "-" + productDTO.getUom());
				String productCatalogNo = productCatalogNos[0].replaceAll(
						"[^0-9a-zA-Z]*[^0-9a-zA-Z]", "_");
				System.out.println(productCatalogNo);
				String productName = productDTO.getName();
				productName = productName.replaceAll(
						"[^0-9a-zA-Z]*[^0-9a-zA-Z]", "_");
				String url = "";

				if (clsIdStr.equals("gene")) {
					url = "http://www.genscript.com/product_001/gene/code/"
							+ productCatalogNo + "/category/gene/"
							+ productName + ".html";
				} else {
					url = "http://www.genscript.com/" + clsIdStr + "/"
							+ productCatalogNo + "-" + productName + ".html";
				}

				productExtendedInfo.setUrl(url);
				productDTO.setProductExtendedInfo(productExtendedInfo);
			}
			// set product more info Product Files
			obj = SessionUtil.getRow(
					SessionPdtServ.FileProductMoreInfo.value(),
					sessionProductId);
			if (obj != null) {
				Map<String, Documents> pdtDocumentMap = (Map<String, Documents>) obj;
				List<Documents> pdtDocumentList = new ArrayList<Documents>();
				for (Map.Entry<String, Documents> entry : pdtDocumentMap
						.entrySet()) {
					Documents pdtDocument = pdtDocumentMap.get(entry.getKey());
					if (pdtDocument != null) {
						pdtDocumentList.add(pdtDocument);
					}
				}
				/*
				 * SessionUtil.deleteRow(
				 * SessionPdtServ.FileProductMoreInfo.value(),
				 * sessionProductId);
				 */
				if (!pdtDocumentList.isEmpty()) {
					productDTO.setProductDocumentList(pdtDocumentList);
				}
			}
			// set product more info del product files
			obj = SessionUtil.getRow(
					SessionPdtServ.DelFileProductMoreInfo.value(),
					sessionProductId);
			if (obj != null) {
				List<Integer> delList = (List<Integer>) obj;
				if (!delList.isEmpty()) {
					productDTO.setDelMoreInfo(delList);
				}
				SessionUtil.deleteRow(
						SessionPdtServ.DelFileProductMoreInfo.value(),
						sessionProductId);
			}
			obj = SessionUtil.getRow(SessionPdtServ.ComponentList.value(),
					sessionProductId);
			if (obj != null) {
				Map<String, ComponentDTO> componentMap = (Map<String, ComponentDTO>) obj;
				List<ComponentDTO> componentList = new ArrayList<ComponentDTO>();
				String cmpsStr = Struts2Util.getParameter("cmpsStr");
				if (org.apache.commons.lang.StringUtils.isNotBlank(cmpsStr)
						&& org.apache.commons.lang.StringUtils
								.isNotEmpty(cmpsStr)) {
					String[] cmpsQty = cmpsStr.split("<=>");
					for (int i = 0; i < cmpsQty.length; i++) {
						String[] tempComp = cmpsQty[i].split("<->");

						if (org.apache.commons.lang.StringUtils
								.isNotBlank(tempComp[1].trim())) {
							if (componentMap.containsKey(tempComp[1].trim())) {
								ComponentDTO temp = componentMap
										.get(tempComp[1]);
								if (org.apache.commons.lang.StringUtils
										.isNumeric(tempComp[0].trim()))
									temp.setListSeq(Integer
											.parseInt(tempComp[0].trim()));
								temp.setQuantity(Double.parseDouble(tempComp[2]
										.trim()));
								componentMap.remove(tempComp[1].trim());
								componentMap.put(tempComp[1].trim(), temp);
							}
						}
					}
				}
				int i = 1;
				for (Map.Entry<String, ComponentDTO> entry : componentMap
						.entrySet()) {
					ComponentDTO component = componentMap.get(entry.getKey());
					if (component != null) {
						// System.out.println("==========================KEY 3:"+entry.getKey());
						// System.out.println("==========================QTY 3:"+component.getQuantity());
						// component.setListSeq(i);
						componentList.add(component);
						i++;
					}
				}
				SessionUtil.deleteRow(SessionPdtServ.ComponentList.value(),
						sessionProductId);
				if (!componentList.isEmpty())
					productDTO.setComponentList(componentList);
			}
			obj = SessionUtil.getRow(SessionPdtServ.DelComponentList.value(),
					sessionProductId);
			if (obj != null) {
				List<Integer> delList = (List<Integer>) obj;
				if (!delList.isEmpty()) {
					productDTO.setDelComIdList(delList);
				}
				SessionUtil.deleteRow(SessionPdtServ.DelComponentList.value(),
						sessionProductId);
			}

			obj = SessionUtil.getRow(SessionPdtServ.ProductRelation.value(),
					sessionProductId);
			if (obj != null) {
				Map<String, ProductRelationDTO> productRelationMap = (Map<String, ProductRelationDTO>) obj;
				List<ProductRelationDTO> productRelationList = new ArrayList<ProductRelationDTO>();
				for (Map.Entry<String, ProductRelationDTO> entry : productRelationMap
						.entrySet()) {
					ProductRelationDTO roductRelation = productRelationMap
							.get(entry.getKey());
					if (roductRelation != null) {
						productRelationList.add(roductRelation);
					}
				}
				SessionUtil.deleteRow(SessionPdtServ.ProductRelation.value(),
						sessionProductId);
				if (!productRelationList.isEmpty()) {
					productDTO.setPdtRelationList(productRelationList);
				}
			}

			// set product prices
			List<ProductPriceDTO> pdtPriceList = new ArrayList<ProductPriceDTO>();
			obj = SessionUtil.getRow(SessionConstant.ProductPricing.value(),
					sessionProductId);
			if (obj != null) {
				Map<String, ProductInCategoryBean> pdtPriceBeanMap = (Map<String, ProductInCategoryBean>) obj;

				for (Map.Entry<String, ProductInCategoryBean> entry : pdtPriceBeanMap
						.entrySet()) {
					ProductInCategoryBean pdtPriceBean = pdtPriceBeanMap
							.get(entry.getKey());
					if (pdtPriceBean != null) {
						ProductPriceDTO price = new ProductPriceDTO();
						String catalogId = pdtPriceBean.getCatalogId();

						Integer categoryId = pdtPriceBean.getCategoryId();
						ProductPrice productPrice = productService
								.getProductPrice(catalogId, categoryId,
										Integer.parseInt(sessionProductId));

						if (productPrice == null) {
							price.setPriceId(null);
						} else {
							obj = SessionUtil
									.getRow(SessionPdtServ.ProductPriceApproved
											.value(), catalogId);
							if (obj != null) {
								price.setProductPriceApprove(Double
										.parseDouble(obj.toString()));
								SessionUtil.deleteRow(
										SessionPdtServ.ProductPriceApproved
												.value(), catalogId);
							}
							obj = SessionUtil.getRow(
									SessionPdtServ.ProductPriceApprovedReason
											.value(), catalogId);
							if (obj != null) {
								price.setProductPriceReason(obj.toString());
								SessionUtil
										.deleteRow(
												SessionPdtServ.ProductPriceApprovedReason
														.value(), catalogId);
							}
							price.setPriceId(productPrice.getPriceId());
						}
						price.setCatalogId(catalogId);
						price.setCategoryId(categoryId);
						price.setProductId(pdtPriceBean.getProductId());
						price.setLimitPrice(pdtPriceBean.getLimitPrice());
						price.setStandardPrice(pdtPriceBean.getUnitPrice());
						price.setStatus("ACTIVE");
						pdtPriceList.add(price);
					}
				}
				SessionUtil.deleteRow(SessionConstant.ProductPricing.value(),
						sessionProductId);
			}
			if (this.productDTO.getCategoryId() != null) {
				ProductPriceDTO price = new ProductPriceDTO();
				price.setCategoryId(productDTO.getCategoryId());
				price.setStatus("INACTIVE");
				price.setStandardPrice(0.0);
				pdtPriceList.add(price);
			}

			if (!pdtPriceList.isEmpty()) {
				productDTO.setPriceList(pdtPriceList);
			}
			// set product del product price
			obj = SessionUtil.getRow(SessionPdtServ.DelProductPrice.value(),
					sessionProductId);
			if (obj != null) {
				List<String> delList = (List<String>) obj;
				if (!delList.isEmpty()) {

					productDTO.setDelPriceIdList(delList);
				}
				SessionUtil.deleteRow(SessionPdtServ.DelProductPrice.value(),
						sessionProductId);
			}

			// set product special prices
			obj = SessionUtil.getRow(SessionPdtServ.SpecailPrice.value(),
					sessionProductId);
			if (obj != null) {
				Map<String, ProductSpecialPriceDTO> spcPriceDTOMap = (Map<String, ProductSpecialPriceDTO>) obj;
				List<ProductSpecialPrice> spcPriceList = new ArrayList<ProductSpecialPrice>();
				for (Map.Entry<String, ProductSpecialPriceDTO> entry : spcPriceDTOMap
						.entrySet()) {
					ProductSpecialPriceDTO spcPriceDTO = spcPriceDTOMap
							.get(entry.getKey());
					if (spcPriceDTO != null) {
						ProductSpecialPrice price = new ProductSpecialPrice();
						Integer id = spcPriceDTO.getId();
						price = dozer.map(spcPriceDTO,
								ProductSpecialPrice.class);
						spcPriceList.add(price);
					}
				}
				SessionUtil.deleteRow(SessionPdtServ.SpecailPrice.value(),
						sessionProductId);
				if (!spcPriceList.isEmpty()) {
					productDTO.setSpecialPriceList(spcPriceList);
				}
			}
			// set product del product special price
			obj = SessionUtil.getRow(SessionPdtServ.DelSpecailPrice.value(),
					sessionProductId);
			if (obj != null) {
				List<Integer> delList = (List<Integer>) obj;
				if (!delList.isEmpty()) {
					productDTO.setDelSpecialPriceIdList(delList);
				}
				SessionUtil.deleteRow(SessionPdtServ.DelSpecailPrice.value(),
						sessionProductId);
			}

			// 获取product approved;
			obj = SessionUtil.getRow(
					SessionPdtServ.ProductApprovedName.value(),
					sessionProductId);
			if (obj != null) {
				productDTO.setNameApprove(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ProductApprovedName.value(),
						sessionProductId);
			}
			obj = SessionUtil.getRow(
					SessionPdtServ.ProductApprovedNameReason.value(),
					sessionProductId);
			if (obj != null) {
				productDTO.setNameReason(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ProductApprovedNameReason.value(),
						sessionProductId);
			}
			obj = SessionUtil.getRow(
					SessionPdtServ.ProductApprovedStatus.value(),
					sessionProductId);
			if (obj != null) {
				productDTO.setStatusApprove(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ProductApprovedStatus.value(),
						sessionProductId);
			}
			obj = SessionUtil.getRow(
					SessionPdtServ.ProductApprovedStatusReason.value(),
					sessionProductId);
			if (obj != null) {
				productDTO.setStatusReason(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ProductApprovedStatusReason.value(),
						sessionProductId);
			}
			/** unitCost修改请求 **/
			obj = SessionUtil.getRow(SessionPdtServ.ApprovedUnitCost.value(),
					sessionProductId);
			if (obj != null) {
				productDTO.setUnitCostApprove(obj.toString());
				SessionUtil.deleteRow(SessionPdtServ.ApprovedUnitCost.value(),
						sessionProductId);
			}
			obj = SessionUtil.getRow(
					SessionPdtServ.ApprovedUnitCostReason.value(),
					sessionProductId);
			if (obj != null) {
				productDTO.setUnitCostReason(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ApprovedUnitCostReason.value(),
						sessionProductId);
			}

			String referenceList = ServletActionContext.getRequest()
					.getParameter("referenceList");
			List<ProductReference> productReferenceList = new ArrayList<ProductReference>();
			if (referenceList != null && !referenceList.equals("")) {
				String[] references = referenceList.split("<;>");
				for (int i = 0; i < references.length; i++) {
					if (references[i] != null && !references[i].equals("")) {
						String[] reference = references[i].split("<,>");
						if (reference != null && reference.length == 3) {
							ProductReference pr = new ProductReference();
							if (!reference[0].equals("")) {
								pr = this.productService
										.getProductRefereeceById(Integer
												.valueOf(reference[0]));
							} else {
								pr.setId(null);
							}
							pr.setReference(reference[1]);
							pr.setUrl(reference[2]);
							productReferenceList.add(pr);
						}
					}
				}

			}
			String delReferenceId = Struts2Util.getParameter("delReferenceId");
			if (delReferenceId != null) {
				String[] dels = delReferenceId.split("<;>");
				if (dels.length > 0) {
					List<Integer> delList = new ArrayList<Integer>();
					Pattern pattern = Pattern.compile("[0-9]*");
					for (int i = 0; i < dels.length; i++) {
						if (dels[i] != null && !dels[i].equals("")) {
							Matcher isNum = pattern.matcher(dels[i]);
							if (isNum.matches()) {
								delList.add(Integer.valueOf(dels[i]));
							}
						}
					}
					if (!delList.isEmpty()) {
						productDTO.setDelReferenceList(delList);
					}
				}
			}
			productDTO.setProductReferenceList(productReferenceList);
			String ruleId = ServletActionContext.getRequest().getParameter(
					"catalogNoRuleId");
			String path = ServletActionContext.getServletContext().getRealPath(
					"/");
			System.out.println(path
					+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
			Product product = this.productService.saveProduct(productDTO,
					loginUserId, ruleId, path);
			SessionUtil.deleteRow(SessionPdtServ.RestrictShipList.value(),
					sessionProductId);
			rt.put("message", "The catalog #" + product.getName()
					+ " is saved successfully!");
			rt.put("id", product.getProductId());
			rt.put("type", productDTO.getType());
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	public String delProductListApproved() {
		Map<String, Object> rt = new HashMap<String, Object>();
		if (this.approvedStatusList == null
				|| "".equals(this.approvedStatusList)) {
			rt.put("message", "You have not chosen any valid data!!!");
		} else {
			String[] statustList = this.approvedStatusList.split(",");
			Integer userId = SessionUtil.getUserId();
			this.productService.delProductListApproved(statustList, userId,
					approved, approvedReason);
			rt.put("message", SUCCESS);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * product Approved session save
	 * 
	 * @param type , name ,reason
	 */
	public String productApprovedSaveSession() {
		Map<String, Object> rt = new HashMap<String, Object>();
		if (SessionPdtServ.ProductApprovedName.value().equals(approvedType)) {
			if (SessionUtil.getRow(SessionPdtServ.ProductApprovedName.value(),
					sessionProductId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ProductApprovedName.value(),
						sessionProductId, approved);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ProductApprovedName.value(),
						sessionProductId, approved);
			}
			if (SessionUtil.getRow(
					SessionPdtServ.ProductApprovedNameReason.value(),
					sessionProductId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ProductApprovedNameReason.value(),
						sessionProductId, approvedReason);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ProductApprovedNameReason.value(),
						sessionProductId, approvedReason);
			}

		} else if (SessionPdtServ.ProductApprovedStatus.value().equals(
				approvedType)) {
			if (SessionUtil.getRow(
					SessionPdtServ.ProductApprovedStatus.value(),
					sessionProductId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ProductApprovedStatus.value(),
						sessionProductId, approved);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ProductApprovedStatus.value(),
						sessionProductId, approved);
			}
			if (SessionUtil.getRow(
					SessionPdtServ.ProductApprovedStatusReason.value(),
					sessionProductId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ProductApprovedStatusReason.value(),
						sessionProductId, approvedReason);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ProductApprovedStatusReason.value(),
						sessionProductId, approvedReason);
			}
		} else if (SessionPdtServ.ApprovedUnitCost.value().equals(approvedType)) {
			// 修改unitCost值保存在session
			if (SessionUtil.getRow(SessionPdtServ.ApprovedUnitCost.value(),
					sessionProductId) == null) {
				SessionUtil.insertRow(SessionPdtServ.ApprovedUnitCost.value(),
						sessionProductId, approved);
			} else {
				SessionUtil.updateRow(SessionPdtServ.ApprovedUnitCost.value(),
						sessionProductId, approved);
			}
			if (SessionUtil.getRow(
					SessionPdtServ.ApprovedUnitCostReason.value(),
					sessionProductId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ApprovedUnitCostReason.value(),
						sessionProductId, approvedReason);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ApprovedUnitCostReason.value(),
						sessionProductId, approvedReason);
			}
		}
		rt.put("message", SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * manager's tasks
	 */
	public String showManagerTaskList() {
		if (approveType == null || "".equals(approveType)
				|| "null".equals(approveType)) {
			approveType = RequestApproveType.Catalog.value();
		}
		if (RequestApproveType.Catalog.value().equals(approveType)) {
			catalogDTOList = productService.getCatalogApproveList();
		}
		if (RequestApproveType.ProductCategory.value().equals(approveType)) {
			productCategoryDTOList = productService
					.getProductCategoryApproveList();

		}
		if (RequestApproveType.ServiceCategory.value().equals(approveType)) {
			serviceCategoryDTOList = productService
					.getServiceCategoryApproveList();

		}
		if (RequestApproveType.Product.value().equals(approveType)) {
			productDTOList = productService.getProductApproveList();

		}
		if (RequestApproveType.Service.value().equals(approveType)) {
			serviceDTOList = productService.getServiceApproveList();
		}
		if (RequestApproveType.ProductPrice.value().equals(approveType)) {
			productPriceDTOList = productService.getProductPriceApproveList();
		}
		if (RequestApproveType.ServicePrice.value().equals(approveType)) {
			servicePriceDTOList = productService.getServicePriceApproveList();
			// System.out.println(servicePriceDTOList.size());

		}
		return "managerTaskList";
	}

	/*
	 * approve manager's tasks
	 */
	public String approveManageTask() {
		Integer userId = SessionUtil.getUserId();
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			String[] token = selectIds.split(",");
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < token.length; i++) {
				list.add(Integer.valueOf(token[i]));
			}
			productService.approveManageTask(list, userId);
			rt.put("message", SUCCESS);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * reject manager's tasks
	 */
	public String rejectManageTask() {
		Integer userId = SessionUtil.getUserId();
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			String[] token = selectIds.split(",");
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < token.length; i++) {
				list.add(Integer.valueOf(token[i]));
			}
			productService.rejectManageTask(list, rejectReason, userId);
			rt.put("message", SUCCESS);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
    *
    */
	public String searchCatalogNoRules() {
		List<CatalogNORules> catalogNoRulesList = new ArrayList<CatalogNORules>();
		if (type != null) {
			// System.out.println(type + "<<<<<<<<<<<<<<<<");
			// if (type == "SERVICE") {
			catalogNoRulesList = this.servService.getCatalogNoRules(type);
			// }
			// if (type == "PRODUCT") {
			// catalogNoRulesList = this.productService
			// .getCatalogNoRules(type);
			// }
			// System.out.println(catalogNoRulesList.size());
			if (catalogNoRulesList != null) {
				for (CatalogNORules catalogNoRules : catalogNoRulesList) {
					CatalogNORulesDTO dto = this.dozer.map(catalogNoRules,
							CatalogNORulesDTO.class);
					if (catalogNoRulesDTOList == null) {
						catalogNoRulesDTOList = new ArrayList<CatalogNORulesDTO>();
						this.catalogNoRulesDTOList.add(dto);
					} else {
						String isAdd = "1";
						for (CatalogNORulesDTO ruleDTO : catalogNoRulesDTOList) {
							if (dto.getCategoryId().equals(
									ruleDTO.getCategoryId())) {
								isAdd = "0";
							}
						}
						if (isAdd.equals("1")) {
							this.catalogNoRulesDTOList.add(dto);
						}
					}
				}
				if (catalogNoRulesDTOList != null) {
					for (CatalogNORulesDTO dto : catalogNoRulesDTOList) {
						if (dto.getItemType().equals("SERVICE")) {
							ServiceCategory category = this.servService
									.getServiceCategoryDetail(dto
											.getCategoryId());
							dto.setCategoryNo(category.getCategoryNo());
						} else {
							ProductCategory category = this.productService
									.getProductCategoryDetail(dto
											.getCategoryId());
							dto.setCategoryNo(category.getCategoryNo());
						}
					}
				} else {
					catalogNoRulesDTOList = new ArrayList<CatalogNORulesDTO>();
				}

				/*
				 * if (catalogNoRulesDTOList != null) { for (CatalogNORulesDTO
				 * dto : catalogNoRulesDTOList) { if
				 * (dto.getItemType().equals("SERVICE")) { ServiceCategory
				 * category = this.servService .getServiceCategoryDetail(dto
				 * .getCategoryId()); if (category != null) {
				 * dto.setCategryNo(category.getCategoryNo()); } } else {
				 * ProductCategory category = this.productService
				 * .getProductCategoryDetail(dto .getCategoryId()); if (category
				 * != null) { dto.setCategryNo(category.getCategoryNo()); } } }
				 * }
				 */
			}
		}
		return "catalogNoRules";
	}

	public String searchCatalogNoRulesPrefix() {
		Map<String, Object> rt = new HashMap<String, Object>();
		List<CatalogNORules> catalogNoRulesList = this.productService
				.getCatalogId(categoryId, type);
		rt.put("catalogNoRulesList", catalogNoRulesList);
		Struts2Util.renderJson(rt);
		return null;
	}

	public String searchCatalogNoRulesCurrentSeq() {
		Map<String, Object> rt = new HashMap<String, Object>();
		String currentSeq = this.productService.saveCatalogNoRules(ruleId);
		rt.put("currentSeq", currentSeq);
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * 查询product document version
	 */

	public String searchProductVersionList() {
		// String
		// names=ServletActionContext.getRequest().getParameter("name").trim();
		if (docId != null && !"".equals(docId)) {
			this.documentVersionList = this.productService
					.searchProductVersionByName(docId);
		}
		return "documentVersion";
	}

	/*
	 * 查询可以增加到document 的product
	 */
	public String searchProductNotInDocument() {
		// 获得分页请求相关数据：如第几页
		PagerUtil<Product> pagerUtil = new PagerUtil<Product>();
		page = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null || page.getPageSize().intValue() < 1) {
			page.setPageSize(15);
		}
		page = this.productService.searchProductNotInDoucment(page, docId,
				catalogNo, name, categoryName);
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "productNotInDocument";
	}

	// -- 页面属性访问函数 --//

	/**
	 * list页面显示product分页列表.
	 */
	public Page<Product> getPage() {
		return page;
	}

	public Page<ProductListBean> getPageBean() {
		return pageBean;
	}

	public Page<ProductOfPdtcategoryBean> getPageOfCategory() {
		return pageOfCategory;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getSrchOperator() {
		return srchOperator;
	}

	public void setSrchOperator(String srchOperator) {
		this.srchOperator = srchOperator;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	public List<SearchAttributeDTO> getAttrList() {
		return attrList;
	}

	public void setAttrList(List<SearchAttributeDTO> attrList) {
		this.attrList = attrList;
	}

	public List<CountryDTO> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryDTO> countryList) {
		this.countryList = countryList;
	}

	public String getCallBackName() {
		return callBackName;
	}

	public void setCallBackName(String callBackName) {
		this.callBackName = callBackName;
	}

	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<DropDownListDTO> getArrDropdownList() {
		return arrDropdownList;
	}

	public void setArrDropdownList(List<DropDownListDTO> arrDropdownList) {
		this.arrDropdownList = arrDropdownList;
	}

	public List<PbDropdownListOptions> getPbOptionItemList() {
		return pbOptionItemList;
	}

	public void setPbOptionItemList(List<PbDropdownListOptions> pbOptionItemList) {
		this.pbOptionItemList = pbOptionItemList;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public String getDelProduct() {
		return delProduct;
	}

	public void setDelProduct(String delProduct) {
		this.delProduct = delProduct;
	}

	public String getSessionCategoryId() {
		return sessionCategoryId;
	}

	public void setSessionCategoryId(String sessionCategoryId) {
		this.sessionCategoryId = sessionCategoryId;
	}

	public List<DropDownDTO> getDropDownDTOList() {
		return dropDownDTOList;
	}

	public void setDropDownDTOList(List<DropDownDTO> dropDownDTOList) {
		this.dropDownDTOList = dropDownDTOList;
	}

	public ProductRelationDTO getCrossDetail() {
		return crossDetail;
	}

	public void setCrossDetail(ProductRelationDTO crossDetail) {
		this.crossDetail = crossDetail;
	}

	public List<OptionItemDTO> getOptionsDropdownList() {
		return OptionsDropdownList;
	}

	public void setOptionsDropdownList(List<OptionItemDTO> optionsDropdownList) {
		OptionsDropdownList = optionsDropdownList;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVendorCatalog() {
		return vendorCatalog;
	}

	public void setVendorCatalog(String vendorCatalog) {
		this.vendorCatalog = vendorCatalog;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public List<VendorProductDTO> getVerdonProductList() {
		return verdonProductList;
	}

	public void setVerdonProductList(List<VendorProductDTO> verdonProductList) {
		this.verdonProductList = verdonProductList;
	}

	public String getCatalogNo() {
		return catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	public List<PurchaseOrderDTO> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	public void setPurchaseOrderList(List<PurchaseOrderDTO> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}

	public String getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}

	public String getSaftyStock() {
		return saftyStock;
	}

	public void setSaftyStock(String saftyStock) {
		this.saftyStock = saftyStock;
	}

	public String getMinOrderQty() {
		return minOrderQty;
	}

	public void setMinOrderQty(String minOrderQty) {
		this.minOrderQty = minOrderQty;
	}

	public String getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(String unitCost) {
		this.unitCost = unitCost;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public List<VendorDTO> getVendorDTOList() {
		return vendorDTOList;
	}

	public void setVendorDTOList(List<VendorDTO> vendorDTOList) {
		this.vendorDTOList = vendorDTOList;
	}

	public VendorProduct getVendorProduct() {
		return vendorProduct;
	}

	public void setVendorProduct(VendorProduct vendorProduct) {
		this.vendorProduct = vendorProduct;
	}

	public String getSessionProductId() {
		return sessionProductId;
	}

	public void setSessionProductId(String sessionProductId) {
		this.sessionProductId = sessionProductId;
	}

	public String getSupplierIdStr() {
		return supplierIdStr;
	}

	public void setSupplierIdStr(String supplierIdStr) {
		this.supplierIdStr = supplierIdStr;
	}

	public ProductExtendedInfo getProductExtendedInfo() {
		return productExtendedInfo;
	}

	public void setProductExtendedInfo(ProductExtendedInfo productExtendedInfo) {
		this.productExtendedInfo = productExtendedInfo;
	}

	public List<Documents> getProductDoc() {
		return productDoc;
	}

	public void setProductDoc(List<Documents> productDoc) {
		this.productDoc = productDoc;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getProductFilesType() {
		return productFilesType;
	}

	public void setProductFilesType(String productFilesType) {
		this.productFilesType = productFilesType;
	}

	public String getDelFileProduct() {
		return delFileProduct;
	}

	public void setDelFileProduct(String delFileProduct) {
		this.delFileProduct = delFileProduct;
	}

	public Integer getDefaultTab() {
		return defaultTab;
	}

	public void setDefaultTab(Integer defaultTab) {
		this.defaultTab = defaultTab;
	}

	public RoyaltyProductDTO getRoyolty() {
		return royolty;
	}

	public void setRoyolty(RoyaltyProductDTO royolty) {
		this.royolty = royolty;
	}

	public List<PbDropdownListOptions> getSendOrderedOption() {
		return sendOrderedOption;
	}

	public void setSendOrderedOption(
			List<PbDropdownListOptions> sendOrderedOption) {
		this.sendOrderedOption = sendOrderedOption;
	}

	public List<PbDropdownListOptions> getGenerateNoticeOption() {
		return generateNoticeOption;
	}

	public void setGenerateNoticeOption(
			List<PbDropdownListOptions> generateNoticeOption) {
		this.generateNoticeOption = generateNoticeOption;
	}

	public List<Royalty> getRoyaltyList() {
		return royaltyList;
	}

	public void setRoyaltyList(List<Royalty> royaltyList) {
		this.royaltyList = royaltyList;
	}

	public String getMiscName() {
		return miscName;
	}

	public void setMiscName(String miscName) {
		this.miscName = miscName;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getIsFalse() {
		return isFalse;
	}

	public void setIsFalse(String isFalse) {
		this.isFalse = isFalse;
	}

	public String getSalesPeriod() {
		return salesPeriod;
	}

	public void setSalesPeriod(String salesPeriod) {
		this.salesPeriod = salesPeriod;
	}

	public ProductServiceSaleDTO getProductSaleDTO() {
		return productSaleDTO;
	}

	public void setProductSaleDTO(ProductServiceSaleDTO productSaleDTO) {
		this.productSaleDTO = productSaleDTO;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public List<SalesRankingDTO> getSalesRankingList() {
		return salesRankingList;
	}

	public void setSalesRankingList(List<SalesRankingDTO> salesRankingList) {
		this.salesRankingList = salesRankingList;
	}

	public String getLastDays() {
		return lastDays;
	}

	public void setLastDays(String lastDays) {
		this.lastDays = lastDays;
	}

	public Integer getPeriodType() {
		return periodType;
	}

	public void setPeriodType(Integer periodType) {
		this.periodType = periodType;
	}

	public String getSalesPeriodBasedOn() {
		return salesPeriodBasedOn;
	}

	public void setSalesPeriodBasedOn(String salesPeriodBasedOn) {
		this.salesPeriodBasedOn = salesPeriodBasedOn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getCatalogNoList() {
		return catalogNoList;
	}

	public void setCatalogNoList(List<String> catalogNoList) {
		this.catalogNoList = catalogNoList;
	}

	public List<ProductStdPriceBean> getProductStdPriceBeanList() {
		return productStdPriceBeanList;
	}

	public void setProductStdPriceBeanList(
			List<ProductStdPriceBean> productStdPriceBeanList) {
		this.productStdPriceBeanList = productStdPriceBeanList;
	}

	public String getApproveType() {
		return approveType;
	}

	public void setApproveType(String approveType) {
		this.approveType = approveType;
	}

	public List<CatalogDTO> getCatalogDTOList() {
		return catalogDTOList;
	}

	public void setCatalogDTOList(List<CatalogDTO> catalogDTOList) {
		this.catalogDTOList = catalogDTOList;
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

	public List<ProductListBeanDTO> getProductDTOList() {
		return productDTOList;
	}

	public void setProductDTOList(List<ProductListBeanDTO> productDTOList) {
		this.productDTOList = productDTOList;
	}

	public List<ServiceListBeanDTO> getServiceDTOList() {
		return serviceDTOList;
	}

	public void setServiceDTOList(List<ServiceListBeanDTO> serviceDTOList) {
		this.serviceDTOList = serviceDTOList;
	}

	public String getSelectIds() {
		return selectIds;
	}

	public void setSelectIds(String selectIds) {
		this.selectIds = selectIds;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getApprovedType() {
		return approvedType;
	}

	public void setApprovedType(String approvedType) {
		this.approvedType = approvedType;
	}

	public String getApprovedReason() {
		return approvedReason;
	}

	public void setApprovedReason(String approvedReason) {
		this.approvedReason = approvedReason;
	}

	public Boolean getNameAppr() {
		return nameAppr;
	}

	public void setNameAppr(Boolean nameAppr) {
		this.nameAppr = nameAppr;
	}

	public Boolean getStatusAppr() {
		return statusAppr;
	}

	public void setStatusAppr(Boolean statusAppr) {
		this.statusAppr = statusAppr;
	}

	public Boolean getUnitCostAppr() {
		return unitCostAppr;
	}

	public void setUnitCostAppr(Boolean unitCostAppr) {
		this.unitCostAppr = unitCostAppr;
	}

	public String getApprovedStatusList() {
		return approvedStatusList;
	}

	public void setApprovedStatusList(String approvedStatusList) {
		this.approvedStatusList = approvedStatusList;
	}

	public String getStandrdPricesDate() {
		return standrdPricesDate;
	}

	public void setStandrdPricesDate(String standrdPricesDate) {
		this.standrdPricesDate = standrdPricesDate;
	}

	public String getPriceLimitDate() {
		return priceLimitDate;
	}

	public void setPriceLimitDate(String priceLimitDate) {
		this.priceLimitDate = priceLimitDate;
	}

	public List<ProductPriceListBeanDTO> getProductPriceDTOList() {
		return productPriceDTOList;
	}

	public void setProductPriceDTOList(
			List<ProductPriceListBeanDTO> productPriceDTOList) {
		this.productPriceDTOList = productPriceDTOList;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operationMethod) {
		operation_method = operationMethod;
	}

	public String getSymbolPrice() {
		return symbolPrice;
	}

	public void setSymbolPrice(String symbolPrice) {
		this.symbolPrice = symbolPrice;
	}

	public String getEnforcePriceLimit() {
		return enforcePriceLimit;
	}

	public void setEnforcePriceLimit(String enforcePriceLimit) {
		this.enforcePriceLimit = enforcePriceLimit;
	}

	public String getApprovedMethod() {
		return approvedMethod;
	}

	public void setApprovedMethod(String approvedMethod) {
		this.approvedMethod = approvedMethod;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getApprovedName() {
		return approvedName;
	}

	public void setApprovedName(String approvedName) {
		this.approvedName = approvedName;
	}

	public String getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

	public String getApprovedUnitCost() {
		return approvedUnitCost;
	}

	public void setApprovedUnitCost(String approvedUnitCost) {
		this.approvedUnitCost = approvedUnitCost;
	}

	public String getApprovedPrice() {
		return approvedPrice;
	}

	public void setApprovedPrice(String approvedPrice) {
		this.approvedPrice = approvedPrice;
	}

	public List<Integer> getStatusOfapproved() {
		return statusOfapproved;
	}

	public void setStatusOfapproved(List<Integer> statusOfapproved) {
		this.statusOfapproved = statusOfapproved;
	}

	public List<DropDownDTO> getDropDownDTO() {
		return dropDownDTO;
	}

	public void setDropDownDTO(List<DropDownDTO> dropDownDTO) {
		this.dropDownDTO = dropDownDTO;
	}

	public List<ProductListBean> getExcelResult() {
		return excelResult;
	}

	public void setExcelResult(List<ProductListBean> excelResult) {
		this.excelResult = excelResult;
	}

	public List<CatalogNORulesDTO> getCatalogNoRulesDTOList() {
		return catalogNoRulesDTOList;
	}

	public void setCatalogNoRulesDTOList(
			List<CatalogNORulesDTO> catalogNoRulesDTOList) {
		this.catalogNoRulesDTOList = catalogNoRulesDTOList;
	}

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public List<ProductReference> getProductReferenceList() {
		return productReferenceList;
	}

	public void setProductReferenceList(
			List<ProductReference> productReferenceList) {
		this.productReferenceList = productReferenceList;
	}

	public List<ServicePriceDTO> getServicePriceDTOList() {
		return servicePriceDTOList;
	}

	public void setServicePriceDTOList(List<ServicePriceDTO> servicePriceDTOList) {
		this.servicePriceDTOList = servicePriceDTOList;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<DocumentVersion> getDocumentVersionList() {
		return documentVersionList;
	}

	public void setDocumentVersionList(List<DocumentVersion> documentVersionList) {
		this.documentVersionList = documentVersionList;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public String getDataSheetName() {
		String dataSheetName = null;
		if (id != null) {

			String writeP = this.fileService.getUploadPath();
			File f = new File(writeP + "productFile_notes//");
			System.out.println(writeP + "//productFile_notes//");
			if (!f.isFile()) {

				File[] files = f.listFiles();
				if (files.length > 0) {
					for (File file : files) {
						String fileName = file.getName();
						if (fileName.split(this.catalogNo).length > 1) {
							if (dataSheetName == null) {
								dataSheetName = fileName;
							} else {
								int i = dataSheetName.compareTo(fileName);
								if (i < 0) {
									dataSheetName = fileName;
								}
							}
						}
					}

				}
			}
		}

		return dataSheetName;
	}

	public String getUrllink() {
		return urllink;
	}

	public void setUrllink(String urllink) {
		this.urllink = urllink;
	}
}
