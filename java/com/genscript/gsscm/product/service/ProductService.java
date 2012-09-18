package com.genscript.gsscm.product.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dao.CurrencyDao;
import com.genscript.gsscm.basedata.dao.PbCountryDao;
import com.genscript.gsscm.basedata.dao.PbStateDao;
import com.genscript.gsscm.basedata.dao.SpecDropDownListDao;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.SearchItemDTO;
import com.genscript.gsscm.basedata.entity.PbCountry;
import com.genscript.gsscm.basedata.entity.PbCurrency;
import com.genscript.gsscm.basedata.entity.PbState;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.constant.CatalogType;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.RequestApproveStatusType;
import com.genscript.gsscm.common.constant.RequestApproveType;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.genscript.gsscm.common.events.NewPartEvent;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.SoapUtil;
import com.genscript.gsscm.common.util.WordPOIUtil;
import com.genscript.gsscm.customer.dao.CustomerSpecialPriceDao;
import com.genscript.gsscm.customer.dao.SourceDao;
import com.genscript.gsscm.customer.dto.AnalysisReport;
import com.genscript.gsscm.customer.entity.CustomerPriceBean;
import com.genscript.gsscm.customer.entity.Source;
import com.genscript.gsscm.epicorwebservice.service.ErpSalesOrderService;
import com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType.PartDataSet.PartWhse;
import com.genscript.gsscm.inventory.dao.WarehouseDao;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.dao.OrderReturnItemDao;
import com.genscript.gsscm.order.dto.ProductServiceSaleDTO;
import com.genscript.gsscm.order.dto.SalesRankingDTO;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.dao.UserRoleDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.AntibodyDao;
import com.genscript.gsscm.product.dao.CatalogDao;
import com.genscript.gsscm.product.dao.CatalogNORulesDao;
import com.genscript.gsscm.product.dao.CategorySearchBeanDao;
import com.genscript.gsscm.product.dao.ChemicalDao;
import com.genscript.gsscm.product.dao.ComponentDao;
import com.genscript.gsscm.product.dao.DocumentProDao;
import com.genscript.gsscm.product.dao.DocumentVersionDao;
import com.genscript.gsscm.product.dao.DocumentsDao;
import com.genscript.gsscm.product.dao.DsPriceDao;
import com.genscript.gsscm.product.dao.EnzymeDao;
import com.genscript.gsscm.product.dao.GeneDao;
import com.genscript.gsscm.product.dao.IntermediateDao;
import com.genscript.gsscm.product.dao.KitDao;
import com.genscript.gsscm.product.dao.MarketingGroupDao;
import com.genscript.gsscm.product.dao.MarketingStaffDao;
import com.genscript.gsscm.product.dao.MoleculeDao;
import com.genscript.gsscm.product.dao.OligoDao;
import com.genscript.gsscm.product.dao.PeptideDao;
import com.genscript.gsscm.product.dao.ProductBeanDao;
import com.genscript.gsscm.product.dao.ProductCategoryCatalogBeanDao;
import com.genscript.gsscm.product.dao.ProductCategoryDao;
import com.genscript.gsscm.product.dao.ProductClassDao;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.dao.ProductDocumentsDao;
import com.genscript.gsscm.product.dao.ProductExtendedInfoDao;
import com.genscript.gsscm.product.dao.ProductInCategoryBeanDao;
import com.genscript.gsscm.product.dao.ProductListBeanDao;
import com.genscript.gsscm.product.dao.ProductOfPdtcategoryBeanDao;
import com.genscript.gsscm.product.dao.ProductPriceDao;
import com.genscript.gsscm.product.dao.ProductReferenceDao;
import com.genscript.gsscm.product.dao.ProductRelationBeanDao;
import com.genscript.gsscm.product.dao.ProductRelationDao;
import com.genscript.gsscm.product.dao.ProductSpecialPriceDao;
import com.genscript.gsscm.product.dao.ProductStdPriceDao;
import com.genscript.gsscm.product.dao.ProteinDao;
import com.genscript.gsscm.product.dao.RestrictShipDao;
import com.genscript.gsscm.product.dao.RoyaltyDao;
import com.genscript.gsscm.product.dao.RoyaltyLicenseDao;
import com.genscript.gsscm.product.dao.RoyaltyProductDao;
import com.genscript.gsscm.product.dao.SDVectorDao;
import com.genscript.gsscm.product.dao.ShipConditionDao;
import com.genscript.gsscm.product.dao.StorageConditionDao;
import com.genscript.gsscm.product.dto.ComponentDTO;
import com.genscript.gsscm.product.dto.IntermediateDTO;
import com.genscript.gsscm.product.dto.MarketingStaffDTO;
import com.genscript.gsscm.product.dto.ProductCategoryDTO;
import com.genscript.gsscm.product.dto.ProductDTO;
import com.genscript.gsscm.product.dto.ProductListBeanDTO;
import com.genscript.gsscm.product.dto.ProductPriceDTO;
import com.genscript.gsscm.product.dto.ProductPriceListBeanDTO;
import com.genscript.gsscm.product.dto.ProductRelationDTO;
import com.genscript.gsscm.product.dto.ProductRelationItemDTO;
import com.genscript.gsscm.product.dto.ProductReportSrchDTO;
import com.genscript.gsscm.product.dto.ProductSpecialPriceDTO;
import com.genscript.gsscm.product.dto.ProductStockStatDTO;
import com.genscript.gsscm.product.dto.PurchaseOrderDTO;
import com.genscript.gsscm.product.dto.RestrictShipDTO;
import com.genscript.gsscm.product.dto.RoyaltyProductDTO;
import com.genscript.gsscm.product.entity.Antibody;
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.entity.CatalogNORules;
import com.genscript.gsscm.product.entity.CategorySearchBean;
import com.genscript.gsscm.product.entity.Chemical;
import com.genscript.gsscm.product.entity.Component;
import com.genscript.gsscm.product.entity.DocumentPro;
import com.genscript.gsscm.product.entity.DocumentVersion;
import com.genscript.gsscm.product.entity.Documents;
import com.genscript.gsscm.product.entity.DsPrice;
import com.genscript.gsscm.product.entity.Enzyme;
import com.genscript.gsscm.product.entity.Gene;
import com.genscript.gsscm.product.entity.Intermediate;
import com.genscript.gsscm.product.entity.Kit;
import com.genscript.gsscm.product.entity.MarketingGroup;
import com.genscript.gsscm.product.entity.MarketingStaff;
import com.genscript.gsscm.product.entity.Molecule;
import com.genscript.gsscm.product.entity.Oligo;
import com.genscript.gsscm.product.entity.Peptide;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductBean;
import com.genscript.gsscm.product.entity.ProductCategory;
import com.genscript.gsscm.product.entity.ProductCategoryCatalogBean;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.product.entity.ProductDocuments;
import com.genscript.gsscm.product.entity.ProductExtendedInfo;
import com.genscript.gsscm.product.entity.ProductInCategoryBean;
import com.genscript.gsscm.product.entity.ProductListBean;
import com.genscript.gsscm.product.entity.ProductOfPdtcategoryBean;
import com.genscript.gsscm.product.entity.ProductPrice;
import com.genscript.gsscm.product.entity.ProductReference;
import com.genscript.gsscm.product.entity.ProductRelation;
import com.genscript.gsscm.product.entity.ProductSpecialPrice;
import com.genscript.gsscm.product.entity.ProductStdPriceBean;
import com.genscript.gsscm.product.entity.Protein;
import com.genscript.gsscm.product.entity.RestrictShip;
import com.genscript.gsscm.product.entity.Royalty;
import com.genscript.gsscm.product.entity.RoyaltyLicense;
import com.genscript.gsscm.product.entity.RoyaltyProduct;
import com.genscript.gsscm.product.entity.SDVector;
import com.genscript.gsscm.product.entity.ShipCondition;
import com.genscript.gsscm.product.entity.StorageCondition;
import com.genscript.gsscm.productservice.dto.CatalogDTO;
import com.genscript.gsscm.productservice.dto.SalePersonDTO;
import com.genscript.gsscm.purchase.dao.PurchaseOrderDao;
import com.genscript.gsscm.purchase.dao.PurchaseOrderItemDao;
import com.genscript.gsscm.purchase.dao.VendorDao;
import com.genscript.gsscm.purchase.dao.VendorProductDao;
import com.genscript.gsscm.purchase.dto.VendorDTO;
import com.genscript.gsscm.purchase.dto.VendorProductDTO;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.purchase.entity.Vendor;
import com.genscript.gsscm.purchase.entity.VendorProduct;
import com.genscript.gsscm.serv.dao.PriceRuleGroupsDao;
import com.genscript.gsscm.serv.dao.ServiceCategoryDao;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.serv.dao.ServiceListBeanDao;
import com.genscript.gsscm.serv.dao.ServicePriceDao;
import com.genscript.gsscm.serv.dao.ServiceSubStepPriceDao;
import com.genscript.gsscm.serv.dao.ServiceSubStepsDao;
import com.genscript.gsscm.serv.dto.ServiceCategoryDTO;
import com.genscript.gsscm.serv.dto.ServiceListBeanDTO;
import com.genscript.gsscm.serv.dto.ServicePriceDTO;
import com.genscript.gsscm.serv.entity.PriceRuleGroups;
import com.genscript.gsscm.serv.entity.ServiceCategory;
import com.genscript.gsscm.serv.entity.ServiceListBean;
import com.genscript.gsscm.serv.entity.ServicePrice;
import com.genscript.gsscm.serv.entity.ServiceSubStepPrice;
import com.genscript.gsscm.serv.entity.ServiceSubSteps;
import com.genscript.gsscm.system.dao.ApproveRequestBeanDao;
import com.genscript.gsscm.system.dao.ApproveRequestDao;
import com.genscript.gsscm.system.dao.ApproveRequestDetailDao;
import com.genscript.gsscm.system.dao.EmarketingGroupAssignedDao;
import com.genscript.gsscm.system.entity.ApproveRequest;
import com.genscript.gsscm.system.entity.ApproveRequestBean;
import com.genscript.gsscm.system.entity.ApproveRequestDetail;
import com.opensymphony.xwork2.ActionContext;

@Service
@Transactional
public class ProductService implements ApplicationContextAware {

	@Autowired
	private ProductBeanDao productBeanDao;
	@Autowired
	private CatalogDao catalogDao;
	@Autowired
	private ProductRelationDao productRelationDao;
	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private SDVectorDao sdvectorDao;
	@Autowired
	private ServiceCategoryDao serviceCategoryDao;
	@Autowired
	private ProductListBeanDao productListBeanDao;
	@Autowired
	private ServiceListBeanDao serviceListBeanDao;
	@Autowired
	private ProductClassDao productClassDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CategorySearchBeanDao categorySearchBeanDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductReferenceDao productReferenceDao;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private ShipConditionDao shipConditionDao;
	@Autowired
	private StorageConditionDao storageConditionDao;
	@Autowired
	private IntermediateDao intermediateDao;
	@Autowired
	private ProductStdPriceDao productStdPriceDao;
	@Autowired
	private ComponentDao componentDao;
	@Autowired
	private ProductSpecialPriceDao productSpecialPriceDao;
	@Autowired
	private ProductPriceDao productPriceDao;
	@Autowired
	private ProductRelationBeanDao productRelationBeanDao;
	@Autowired
	private CurrencyDao currencyDao;
	@Autowired
	private RestrictShipDao restrictShipDao;
	@Autowired
	private PurchaseOrderItemDao purchaseOrderItemDao;
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	@Autowired
	private WarehouseDao warehouseDao;
	@Autowired
	private SourceDao sourceDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private OrderReturnItemDao orderReturnItemDao;
	@Autowired
	private PbCountryDao pbCountryDao;
	@Autowired
	private PbStateDao pbStateDao;
	@Autowired
	private VendorProductDao vendorProductDao;
	@Autowired
	private VendorDao vendorDao;
	@Autowired
	private SpecDropDownListDao specDropDownListDao;
	@Autowired
	private AntibodyDao antibodyDao;
	@Autowired
	private EnzymeDao enzymeDao;
	@Autowired
	private ChemicalDao chemicalDao;
	@Autowired
	private KitDao kitDao;
	@Autowired
	private MoleculeDao moleculeDao;
	@Autowired
	private OligoDao oligoDao;
	@Autowired
	private ProteinDao proteinDao;
	@Autowired
	private GeneDao geneDao;
	@Autowired
	private PeptideDao peptideDao;
	@Autowired
	private DocumentsDao documentDao;
	@Autowired
	private DocumentProDao documentdao;
	@Autowired
	private DocumentVersionDao documentVersionDao;
	@Autowired
	private ProductDocumentsDao productDocumentDao;
	@Autowired
	private CustomerSpecialPriceDao customerSpecialPriceDao;
	@Autowired
	private ApproveRequestDao approveRequestDao;
	@Autowired
	private ApproveRequestBeanDao approveRequestBeanDao;
	@Autowired
	private ApproveRequestDetailDao approveRequestDetailDao;
	@Autowired
	private RoyaltyDao royaltyDao;
	@Autowired
	private RoyaltyProductDao royaltyProductDao;
	@Autowired
	private ProductOfPdtcategoryBeanDao productOfPdtcategoryBeanDao;
	@Autowired
	private RoyaltyLicenseDao royaltyLicenseDao;
	@Autowired
	private ServicePriceDao servicePriceDao;
	@Autowired
	private ServiceSubStepPriceDao serviceSubStepPriceDao;
	@Autowired
	private ServiceSubStepsDao serviceSubStepsDao;
	@Autowired
	private MarketingGroupDao marketingGroupDao;
	@Autowired
	private MarketingStaffDao marketingStaffDao;
	@Autowired
	private ProductExtendedInfoDao pdtExtInfoDao;
	@Autowired
	private ProductInCategoryBeanDao productInCategoryBeanDao;
	@Autowired
	private ProductCategoryCatalogBeanDao productCategoryCatalogBeanDao;
	@Autowired
	private PriceRuleGroupsDao priceRuleGroupsDao;
	@Autowired
	private CatalogNORulesDao catalogNORulesDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired(required = false)
	private DozerBeanMapper dozer;
	private ApplicationContext context;

	@Autowired
	private FileService fileService;
	private Service sev;
	@Autowired
	private ErpSalesOrderService erpSalesOrderService;
	@Autowired
	private EmarketingGroupAssignedDao emarketingGroupAssignedDao;
	@Autowired
	private DsPriceDao dsPriceDao;

	@Transactional(readOnly = true)
	public Long getCountBycatalogIdandproductId(String catalogId,
			Integer productId) {

		return productPriceDao.checkAllproductIdandcatalogId(catalogId,
				productId);
	}

	@Transactional(readOnly = true)
	public Page<ProductBean> searchProduct(Page<ProductBean> page,
			List<PropertyFilter> filters, Integer custNo, String listType) {
		List<String> catalogNoList = customerSpecialPriceDao
				.getSpecialPriceCatalogNoList(custNo, listType);
		return productBeanDao.findPageExceptCatalogNo(page, filters,
				catalogNoList);
	}

	@Transactional(readOnly = true)
	public Page<ProductBean> searchProduct(Page<ProductBean> page,
			Integer custNo, String listType) {
		List<String> catalogNoList = customerSpecialPriceDao
				.getSpecialPriceCatalogNoList(custNo, listType);
		return productBeanDao.findPageExceptCatalogNo(page, catalogNoList);
	}

	@Transactional(readOnly = true)
	public Page<ProductBean> searchProduct(Page<ProductBean> page,
			final Map<String, String> filterParamMap, Integer custNo,
			String listType) {
		List<String> catalogNoList = customerSpecialPriceDao
				.getSpecialPriceCatalogNoList(custNo, listType);
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return productBeanDao.findPageExceptCatalogNo(page, filterList,
				catalogNoList);
	}

	@Transactional(readOnly = true)
	public Page<Product> getProductList(Page<Product> page,
			List<PropertyFilter> filters) {
		return productDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<Product> getProductList(Page<Product> page) {
		return productDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public List<ProductInCategoryBean> getProductPricingList(String catalogNo) {
		Criterion criterion = Restrictions.eq("catalogStatus", "ACTIVE");
		Criterion criterion3 = Restrictions.eq("categoryStatus", "ACTIVE");
		Criterion criterion2 = Restrictions.eq("catalogNo", catalogNo);
		return productInCategoryBeanDao.find(criterion, criterion2, criterion3);
	}

	@Transactional(readOnly = true)
	public List<ProductSpecialPriceDTO> getProductSpecailPricingList(
			Integer productId) {
		List<ProductSpecialPriceDTO> dtoList = new ArrayList<ProductSpecialPriceDTO>();
		List<ProductSpecialPrice> list = productSpecialPriceDao
				.getProductSpecailPricingList(productId);
		if (list != null && !list.isEmpty()) {
			for (ProductSpecialPrice specialPrice : list) {
				ProductSpecialPriceDTO dto = dozer.map(specialPrice,
						ProductSpecialPriceDTO.class);
				if (specialPrice.getSourceId() != null) {
					Source source = sourceDao.getById(specialPrice
							.getSourceId());
					if (source != null) {
						dto.setSourceKey(source.getCode());
					}
				}
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	@Transactional(readOnly = true)
	public ProductOfPdtcategoryBean getProductPricingDetail(Integer id) {
		return productOfPdtcategoryBeanDao.findUniqueBy("catalogId", id);
	}

	@Transactional(readOnly = true)
	public Page<Product> getProductList(Page<Product> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return productDao.findPage(page, filterList);
	}

	@Transactional(readOnly = true)
	public List<Catalog> getSpecailCatalogList() {
		return catalogDao.getSpecialCatalogList();
	}

	@Transactional(readOnly = true)
	public String getBaseCatalogId() {
		return catalogDao.getBaseCatalogId();
	}

	@Transactional(readOnly = true)
	public List<Catalog> getCatalogList(String catalogType) {
		return catalogDao.getCatalogList(catalogType);
	}

	/**
	 * 获得一个Product的Special Price list暂不分页.;
	 * 
	 * @param productId
	 * @return
	 */
	@Transactional
	public List<ProductSpecialPrice> getProductSpecialPrice(Integer productId) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filter = new PropertyFilter("EQI_productId", productId);
		filterList.add(filter);
		return this.productSpecialPriceDao.find(filterList);

	}

	@Transactional
	public List<ProductPrice> getPdtPriceListWithCatalog() {
		return productPriceDao.getPdtPriceListWithCatalog();
	}

	@Transactional(readOnly = true)
	public ProductRelationDTO getProductRelations(
			CustomerPriceBean customerPriceBean, Integer custNo,
			List<String> catalogNoList) {
		if (customerPriceBean != null) {
			ProductRelationDTO productRelationDTO = null;
			productRelationDTO = new ProductRelationDTO();
			List<ProductRelationItemDTO> productBeanList = productRelationDao
					.getRelateProductBean(customerPriceBean.getProductId(),
							custNo, catalogNoList);
			productRelationDTO.setName(customerPriceBean.getName());
			productRelationDTO.setProductRelationItemDTOList(productBeanList);
			return productRelationDTO;
		}
		return null;
	}

	/**
	 * 获得一个ProductReleation的详细信息.
	 * 
	 * @param relateId
	 * @return
	 */
	@Transactional(readOnly = true)
	public ProductRelationDTO getProductRelationDetail(Integer relateId) {
		ProductRelationDTO dto = null;
		ProductRelation pdtRlt = this.productRelationDao.getById(relateId);
		if (pdtRlt != null) {
			dto = this.dozer.map(pdtRlt, ProductRelationDTO.class);
			Product pdt = this.productDao.getById(pdtRlt.getRltProductId());
			dto.setRltName(pdt.getName());
			dto.setRltCatalogNo(pdt.getCatalogNo());
		}
		return dto;
	}

	/**
	 * 获得一个产品的RestrictShip(禁运) list
	 * 
	 * @param productId
	 * @return
	 */
	public List<RestrictShipDTO> getProductRestrictShipList(Integer productId) {
		List<RestrictShipDTO> dtoList = new ArrayList<RestrictShipDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filter = new PropertyFilter("EQI_productId", productId);
		filterList.add(filter);
		List<RestrictShip> dbList = this.restrictShipDao.find(filterList);
		Map<String, String> countryMap = new HashMap<String, String>();
		Map<String, String> stateMap = new HashMap<String, String>();
		if (dbList != null) {
			for (RestrictShip dbShip : dbList) {
				RestrictShipDTO dto = this.dozer.map(dbShip,
						RestrictShipDTO.class);
				if (countryMap.containsKey(dbShip.getCountry())) {
					dto.setCountryName(countryMap.get(dbShip.getCountry()));
				} else {
					PbCountry country = this.pbCountryDao.findUniqueBy(
							"countryCode", dbShip.getCountry());
					if (country != null) {
						dto.setCountryName(country.getName());
						countryMap.put(country.getCountryCode(),
								country.getName());
					}
				}
				if (stateMap.containsKey(dbShip.getState())) {
					dto.setStateName(stateMap.get(dbShip.getState()));
				} else {
					PbState state = this.pbStateDao.findUniqueBy("stateCode",
							dbShip.getState());
					if (state != null) {
						dto.setStateName(state.getName());
						stateMap.put(state.getStateCode(), state.getName());
					}
				}
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	@Transactional(readOnly = true)
	public Page<Catalog> searchCatalogList(Page<Catalog> page,
			List<PropertyFilter> filters) throws Exception {
		// Map<String, Object> session =
		// ActionContext.getContext().getSession();
		// String userName = (String)
		// session.get(StrutsActionContant.USER_NAME);
		// Integer userId = (Integer) session.get(StrutsActionContant.USER_ID);
		// if (filters == null) {
		// filters = new ArrayList<PropertyFilter>();
		// }
		// if (!Constants.USERNAME_ADMIN.equals(userName)) {
		// boolean isProductionManagerRole =
		// userRoleDao.checkIsContainsManagerRole(Constants.ROLE_PRODUCTION_MANAGER);
		// if (!isProductionManagerRole) {
		// PropertyFilter pf = new PropertyFilter("EQI_createdBy",
		// Integer.parseInt(userId.toString()));
		// filters.add(pf);
		// return catalogDao.findPage(page, filters);
		// }
		// }
		return catalogDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<Catalog> searchCatalogList(Page<Catalog> page) {
		return catalogDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<Catalog> searchCatalogList(Page<Catalog> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return catalogDao.findPage(page, filterList);
	}

	@Transactional(readOnly = true)
	public Page<ProductCategory> searchCategoryList(Page<ProductCategory> page,
			List<PropertyFilter> filters) throws Exception {
		// Map<String, Object> session =
		// ActionContext.getContext().getSession();
		// Object userName = session.get(StrutsActionContant.USER_NAME);
		// Object userId = session.get(StrutsActionContant.USER_ID);
		// if (filters == null) {
		// filters = new ArrayList<PropertyFilter>();
		// }
		// if (!Constants.USERNAME_ADMIN.equals(userName)) {
		// boolean isProductionManagerRole =
		// userRoleDao.checkIsContainsManagerRole(Constants.ROLE_PRODUCTION_MANAGER);
		// if (!isProductionManagerRole) {
		// PropertyFilter pf = new PropertyFilter("EQI_createdBy",
		// Integer.parseInt(userId.toString()));
		// filters.add(pf);
		// }
		// }
		return productCategoryDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<ProductCategory> searchCategorySList(
			Page<ProductCategory> page, String values, Integer categoryLevel) {
		return productCategoryDao.searchCategoryListS(page, categoryLevel);
	}

	@Transactional(readOnly = true)
	public Page<ProductCategory> searchCategoryMapList(
			Page<ProductCategory> page, String values, Integer categoryLevel,
			Map<String, ProductCategory> pdtcatMap) {
		return productCategoryDao.searchCategoryByMapList(page, pdtcatMap,
				values, categoryLevel);
	}

	@Transactional(readOnly = true)
	public Page<ProductCategory> searchSubCategoryList(
			Page<ProductCategory> page, Integer categoryLevel,
			Integer categoryId) {
		return productCategoryDao.searchSubCategoryLists(page, categoryId,
				categoryLevel);
	}

	@Transactional(readOnly = true)
	public Page<ProductCategory> searchSubCategoryMapList(
			Page<ProductCategory> page, Integer categoryId,
			Integer categoryLevel, Map<String, ProductCategory> map) {
		return productCategoryDao.searchSubCategoryByMapList(page, categoryId,
				categoryLevel, map);
	}

	@Transactional(readOnly = true)
	public Page<ProductCategory> searchCategoryList(Page<ProductCategory> page) {
		return productCategoryDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<ProductCategory> searchCategoryList(Page<ProductCategory> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return productCategoryDao.findPage(page, filterList);
	}

	@Transactional(readOnly = true)
	public Page<ProductListBean> searchProductList(Page<ProductListBean> page,
			List<PropertyFilter> filters) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String userName = (String) session.get(StrutsActionContant.USER_NAME);
		Integer userId = (Integer) session.get(StrutsActionContant.USER_ID);
		if (filters == null) {
			filters = new ArrayList<PropertyFilter>();
		}
		String catalogNos = null;
		if (!Constants.USERNAME_ADMIN.equals(userName)) {
			boolean isProductionManagerRole = userRoleDao
					.checkIsContainsManagerRole(Constants.ROLE_PRODUCTION_MANAGER);
			if (!isProductionManagerRole) {
				List<Integer> clsIdList = emarketingGroupAssignedDao
						.getClsIdByStaffIdAndType(userId,
								QuoteItemType.PRODUCT.value());
				if (clsIdList == null || clsIdList.isEmpty()) {
					return page;
				}
				List<String> catalogNoList = productDao
						.getCatalogNoByClsId(clsIdList);
				if (catalogNoList == null || catalogNoList.isEmpty()) {
					return page;
				}
				StringBuffer catalogNoSbf = new StringBuffer();
				Iterator<String> catalogItem = catalogNoList.iterator();
				while (catalogItem.hasNext()) {
					catalogNoSbf.append("'" + catalogItem.next() + "',");
				}
				catalogNos = catalogNoSbf.toString();
				catalogNos = catalogNos.substring(0, catalogNos.length() - 1);
				// PropertyFilter pf = new PropertyFilter("EQI_createdBy",
				// Integer.parseInt(userId.toString()));
				// filters.add(pf);
			}
		}
		// return productListBeanDao.findPage(page, filters);
		return productListBeanDao.searchProductList_new(page, filters,
				catalogNos);
	}

	@Transactional(readOnly = true)
	public Page<ProductListBean> searchProductList(Page<ProductListBean> page) {
		return productListBeanDao.getProductList(page);
	}

	@Transactional(readOnly = true)
	public Page<ProductListBean> searchPageProductList(
			Page<ProductListBean> page, List<PropertyFilter> filters) {
		return productListBeanDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public List<ProductListBean> searchAllProductListOfFilter(
			List<PropertyFilter> filters) {

		return this.productListBeanDao.find(filters);
	}

	@Transactional(readOnly = true)
	public Page<ProductListBean> searchProductList(Page<ProductListBean> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return productListBeanDao.findPage(page, filterList);
	}

	public String getProductPriceSymbol(String currencyCode) {
		return this.currencyDao.getCurrencySymbol(currencyCode);
	}

	/**
	 * 批量删除Catalog.
	 * 
	 * @param idList
	 */
	public void delCatalogList(List<Integer> idList) {
		for (Integer id : idList) {
			Map<Integer, String> delResult = this.delCatalogResult(id);
			if (delResult != null) {
				throw new BussinessException(delResult.get(id));
			}
			this.catalogDao.delById(id);
		}
	}

	/**
	 * 批量删除Catalog.
	 * 
	 * @param idList
	 */
	public boolean delCatalogListStr(List<String> idList) {
		for (String idStr : idList) {
			Integer id = Integer.valueOf(idStr);
			Map<Integer, String> delResult = this.delCatalogResult(id);
			if (delResult != null) {
				throw new BussinessException(delResult.get(id));
			}
			this.catalogDao.delById(id);
		}
		return true;
	}

	/**
	 * 批量删除Product Categories. 同时成功或失败.
	 * 
	 * @param pdtCategoryIdList
	 */
	public void delPdtCategoryList(List<Integer> pdtCategoryIdList) {
		for (Integer pdtCategoryId : pdtCategoryIdList) {
			Map<Integer, String> delResult = this
					.delPdtCategoryResult(pdtCategoryId);
			if (delResult != null) {
				throw new BussinessException(delResult.get(pdtCategoryId));
			}
			this.productCategoryDao.delete(pdtCategoryId);
		}
	}

	/**
	 * 批量删除Product Categories. 同时成功或失败.
	 * 
	 * @param pdtCategoryIdList
	 */
	public void delPdtCategoryListStr(List<String> pdtCategoryIdList) {
		for (String pdtCategoryStrId : pdtCategoryIdList) {
			Integer pdtCategoryId = Integer.valueOf(pdtCategoryStrId);
			Map<Integer, String> delResult = this
					.delPdtCategoryResult(pdtCategoryId);
			if (delResult != null) {
				throw new BussinessException(delResult.get(pdtCategoryId));
			}
			this.productCategoryDao.delete(pdtCategoryId);
		}
	}

	/**
	 * 获得一个Product的基本信息.
	 * 
	 * @param productId
	 * @return
	 */
	public ProductDTO getProductDetail(Integer productId) {
		Product product = this.productDao.getById(productId);
		this.productDao.getSession().evict(product);
		ProductDTO dto = this.dozer.map(product, ProductDTO.class);
		User createUser = this.userDao.getById(product.getCreatedBy());
		if (createUser != null) {
			dto.setCreatedByText(createUser.getLoginName());
		}
		User modifyUser = this.userDao.get(product.getModifiedBy());
		if (modifyUser != null) {
			dto.setModifyByText(modifyUser.getLoginName());
		}
		ShipCondition shipCondition = this.shipConditionDao
				.getShipCondition(productId);
		dto.setShipCondition(shipCondition);
		// List<RestrictShip> restrictShipList=this.restrictShipDao.
		StorageCondition storageCondition = this.storageConditionDao
				.getStorageCondition(productId);
		dto.setStorageCondition(storageCondition);
		return dto;
	}

	/**
	 * 根据主健获得 for product模块.
	 * 
	 * @param id
	 * @return
	 */
	public CatalogDTO getCatalogDetail(Integer id) {
		Catalog catalog = this.catalogDao.getWithoutCached(id);
		CatalogDTO dto = this.getCatalogDetail(catalog);
		return dto;
	}

	/**
	 * 根据catalogId获得. for Order Item 模块.
	 * 
	 * @param catalogId
	 * @return
	 */
	public CatalogDTO getCatalogDetail(String catalogId) {
		Catalog catalog = this.catalogDao.getCatalogByCatalogId(catalogId);
		CatalogDTO dto = this.getCatalogDetail(catalog);
		return dto;
	}

	private CatalogDTO getCatalogDetail(Catalog catalog) {
		CatalogDTO dto = this.dozer.map(catalog, CatalogDTO.class);
		User createUser = this.userDao.getById(catalog.getCreatedBy());
		if (createUser != null) {
			dto.setCreateUser(createUser.getLoginName());
		}
		User modifyUser = this.userDao.get(catalog.getModifiedBy());
		if (modifyUser != null) {
			dto.setModifyUser(modifyUser.getLoginName());
		}
		if (catalog.getPublisher() != null) {
			User publicshUser = this.userDao.getById(catalog.getPublisher());
			if (publicshUser != null) {
				dto.setPublishUser(publicshUser.getLoginName());
			}
		}
		return dto;
	}

	public ProductCategory getProductCategoryDetail(Integer id) {
		ProductCategory category = this.productCategoryDao.findUniqueBy(
				"categoryId", id);
		this.productCategoryDao.getSession().evict(category);
		return category;
	}

	public ProductCategoryDTO getPdtCategoryDetail(Integer id) {
		ProductCategory category = this.productCategoryDao.findUniqueBy(
				"categoryId", id);
		ProductCategoryDTO dto = this.dozer.map(category,
				ProductCategoryDTO.class);
		User createUser = this.userDao.getById(category.getCreatedBy());
		if (createUser != null) {
			dto.setCreateUser(createUser.getLoginName());
		}
		User modifyUser = this.userDao.get(category.getModifiedBy());
		if (modifyUser != null) {
			dto.setModifyUser(modifyUser.getLoginName());
		}
		if (category.getParentCatId() != null) {
			ProductCategory parentCat = this.productCategoryDao
					.getById(category.getParentCatId());
			if (parentCat != null) {
				dto.setParentCatName(parentCat.getName());
				dto.setParentCatNo(parentCat.getCategoryNo());
			}
		}
		if (category.getPreviousCatId() != null) {
			ProductCategory prvCat = this.productCategoryDao.getById(category
					.getPreviousCatId());
			if (prvCat != null) {
				dto.setPreviousCatName(prvCat.getName());
				dto.setPreviousCatNo(prvCat.getCategoryNo());
			}
		}
		return dto;
	}

	public String getPdtCatalogNo(Integer id) {
		String catalogNo = "";
		Product product = this.productDao.getById(id);
		if (product != null) {
			catalogNo = product.getCatalogNo();
		}
		return catalogNo;
	}

	/**
	 * For saveCatalog
	 * 
	 * @param catalog
	 */
	private void saveOrUpdateCat(Catalog catalog) {
		Date now = new Date();
		catalog.setCreationDate(now);
		catalog.setCreatedBy(catalog.getModifiedBy());
		catalog.setModifyDate(now);
		this.catalogDao.save(catalog);
	}

	/**
	 * For saveCatalog
	 * 
	 * @param catalog
	 * @param pdtCatList
	 * @param delPdtCatIdList
	 */
	private void attachPdtCat(Catalog catalog,
			List<ProductCategory> pdtCatList, List<Integer> delPdtCatIdList) {
		if (delPdtCatIdList != null && delPdtCatIdList.get(0) != null) {

			for (Integer pdtCategoryId : delPdtCatIdList) {
				Map<Integer, String> delResult = this
						.delPdtCategoryResult(pdtCategoryId);
				if (delResult != null) {
					throw new BussinessException(delResult.get(pdtCategoryId));
				}
			}

			this.productCategoryDao
					.delPdtCatListByModifyPCatId(delPdtCatIdList);
		}
		if (pdtCatList == null || pdtCatList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (ProductCategory cat : pdtCatList) {
			if (cat != null) {
				cat.setModifyDate(now);
				cat.setModifiedBy(catalog.getModifiedBy());
				if (cat.getCategoryId() == null
						|| cat.getCategoryId().intValue() == 0) {
					cat.setCategoryId(null);
					cat.setCreatedBy(catalog.getModifiedBy());
					cat.setCreationDate(now);
				} else {// 修改时需要判断它的catalogId是否发生变化， 如果有则递归修改其子节点.
					ProductCategory dbCat = this.productCategoryDao.getById(cat
							.getCategoryId());
					String dbCatalogId = dbCat.getCatalogId();
					if (dbCatalogId == null) {
						if (cat.getCategoryId() != null) {// catalogId发生变化.
							this.updateSubCat(cat.getCategoryId(),
									catalog.getCatalogId());
						}
					} else {
						if (!dbCatalogId.equals(cat.getCatalogId())) {// catalogId发生变化.
							this.updateSubCat(cat.getCategoryId(),
									catalog.getCatalogId());
						}
					}
					this.productCategoryDao.getSession().evict(dbCat);
				}
				cat.setCatalogId(catalog.getCatalogId());
				if (cat.getParentCatId() == null
						|| cat.getParentCatId().intValue() == 0) {
					cat.setParentCatId(null);
				}
				cat.setCategoryLevel(1);
				this.productCategoryDao.save(cat);
			}
		}
	}

	private void updateSubCat(Integer parentCatId, final String catalogId) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_parentCatId",
				parentCatId);
		filterList.add(quoteFilter);
		List<ProductCategory> catList = this.productCategoryDao
				.find(filterList);
		if (catList != null) {
			for (ProductCategory cat : catList) {
				cat.setCatalogId(catalogId);
				this.productCategoryDao.save(cat);
				this.updateSubCat(cat.getCategoryId(), catalogId);// 递归调用.
			}
		}
	}

	/**
	 * For saveCatalog
	 * 
	 * @param catalog
	 * @param serviceCatList
	 * @param delServiceCatIdList
	 */
	private void attachSrvCat(Catalog catalog,
			List<ServiceCategory> serviceCatList,
			List<Integer> delServiceCatIdList) {
		if (delServiceCatIdList != null && delServiceCatIdList.get(0) != null) {

			for (Integer pdtCategoryId : delServiceCatIdList) {
				Map<Integer, String> delResult = this
						.delServCategoryResult(pdtCategoryId);
				if (delResult != null) {
					throw new BussinessException(delResult.get(pdtCategoryId));
				}
			}

			this.serviceCategoryDao
					.delServiceCatListByModifyPCatId(delServiceCatIdList);
		}
		if (serviceCatList == null || serviceCatList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (ServiceCategory cat : serviceCatList) {
			if (cat != null) {
				cat.setModifyDate(now);
				cat.setModifiedBy(catalog.getModifiedBy());
				if (cat.getCategoryId() == null
						|| cat.getCategoryId().intValue() == 0) {
					cat.setCategoryId(null);
					cat.setCreatedBy(catalog.getModifiedBy());
					cat.setCreationDate(now);
				} else {// 修改时需要判断它的catalogId是否发生变化， 如果有则递归修改其子节点.
					ServiceCategory dbCat = this.serviceCategoryDao.getById(cat
							.getCategoryId());
					String dbCatalogId = dbCat.getCatalogId();
					if (dbCatalogId == null) {
						if (cat.getCategoryId() != null) {// catalogId发生变化.
							this.updateSubServCat(cat.getCategoryId(),
									catalog.getCatalogId());
						}
					} else {
						if (!dbCatalogId.equals(cat.getCatalogId())) {// catalogId发生变化.
							this.updateSubServCat(cat.getCategoryId(),
									catalog.getCatalogId());
						}
					}
					this.serviceCategoryDao.getSession().evict(dbCat);
				}
				cat.setCatalogId(catalog.getCatalogId());
				if (cat.getParentCatId() == null
						|| cat.getParentCatId().intValue() == 0) {
					cat.setParentCatId(null);
				}
				cat.setCategoryLevel(1);
				this.serviceCategoryDao.save(cat);
			}
		}
	}

	private void updateSubServCat(Integer parentCatId, final String catalogId) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_parentCatId",
				parentCatId);
		filterList.add(quoteFilter);
		List<ServiceCategory> catList = this.serviceCategoryDao
				.find(filterList);
		if (catList != null) {
			for (ServiceCategory cat : catList) {
				cat.setCatalogId(catalogId);
				this.serviceCategoryDao.save(cat);
				this.updateSubServCat(cat.getCategoryId(), catalogId);// 递归调用.
			}
		}
	}

	/**
	 * 在保存Product的同时保存或更新它关联的RoyaltyProduct.
	 * 
	 * @param product
	 * @param royaltyDTO
	 * @param userId
	 */
	private void attachPdtRoyalty(Product product,
			RoyaltyProductDTO royaltyDTO, Integer userId) {
		if (royaltyDTO == null || royaltyDTO.getRoyaltyId() == null) {
			return;
		}
		RoyaltyProduct royaltyProduct = this.dozer.map(royaltyDTO,
				RoyaltyProduct.class);
		if (royaltyDTO.getRoyaltyId() != null
				&& royaltyDTO.getLicenseId() == null) {
			RoyaltyLicense license = this.royaltyLicenseDao
					.getLicenseByRoyalty(royaltyDTO.getLicenseId());
			if (license != null) {
				royaltyProduct.setLicenseId(license.getLicenseId());
			}
		}
		royaltyProduct.setCatalogNo(product.getCatalogNo());
		royaltyProduct.setCreatedBy(userId);
		royaltyProduct.setModifiedBy(userId);
		royaltyProduct.setCreationDate(new Date());
		royaltyProduct.setModifyDate(new Date());
		this.royaltyProductDao.save(royaltyProduct);
	}

	/**
	 * Save Catalog base information and ProductCategory list, ServiceCategory
	 * list.
	 * 
	 * @param dto
	 * @param loginUserId
	 * @return
	 */
	// Modified by LiFeng
	public Catalog saveCatalog(CatalogDTO dto, Integer loginUserId) {
		if (dto.getId() == null) {
			Catalog dbCatalog = this.catalogDao.findUniqueBy("catalogId",
					dto.getCatalogId());
			if (dbCatalog != null) {
				this.catalogDao.getSession().evict(dbCatalog);
				throw new BussinessException(
						BussinessException.ERR_CATALOGID_UNIQUE);
			}
		}
		/*
		 * 数据库中只有一条数据的defaultFalg字段等于"Y" 只有Status 等于“ACTIVE”才能存入。
		 */
		if (dto.getDefaultFlag().equals("Y")) {
			if (dto.getStatus().equals("ACTIVE")) {
				Catalog catelogCount = this.catalogDao.findUniqueBy(
						"defaultFlag", "Y");
				if (catelogCount != null) {
					if (dto.getCatalogId() == null
							|| !(dto.getCatalogId().equals(catelogCount
									.getCatalogId()))) {
						this.catalogDao.getSession().evict(catelogCount);
						throw new BussinessException(
								BussinessException.ERR_CATALOGDEFAULTFLAG_REPEATY);
					}
				}
				this.catalogDao.getSession().evict(catelogCount);
			} else {
				throw new BussinessException(
						BussinessException.ERR_CATALOGDEFAULTFLAG_STATUS_NOTMATCH);
			}
		}
		Catalog catalog = this.dozer.map(dto, Catalog.class);
		// Integer objectId = catalog.getId();// for approved.
		catalog.setModifiedBy(loginUserId);
		this.saveOrUpdateCat(catalog);
		this.attachPdtCat(catalog, dto.getPdtCatList(),
				dto.getDelPdtCatIdList());
		this.attachSrvCat(catalog, dto.getServiceCatList(),
				dto.getDelServiceCatIdList());
		// with lifeng's approved module.
		Integer requestId = null;
		// if (objectId != null) {
		// ApproveRequest appRequest = approveRequestDao.countApproveRequest(
		// RequestApproveType.Catalog.name(), objectId);
		// if (appRequest != null) {
		// requestId = appRequest.getRequestId();
		// approveRequestDao.getSession().evict(appRequest);
		// }
		// }
		catalogApproved(dto, loginUserId, catalog, requestId);
		return catalog;
	}

	/*
	 * del prodct catalog 批量修改status 为INACTIVE
	 */
	public void delCatalogApproved(String[] objectIds, Integer userId,
			String approved, String approvedReason) {
		for (int i = 0; i < objectIds.length; i++) {
			Integer requestId = null;
			CatalogDTO dto = new CatalogDTO();
			dto.setStatusApprove(approved);
			dto.setStatusReason(approvedReason);
			Catalog catalog = this.catalogDao.getById(Integer
					.valueOf(objectIds[i]));
			catalogApproved(dto, userId, catalog, requestId);
		}
	}

	private void catalogApproved(CatalogDTO dto, Integer loginUserId,
			Catalog catalog, Integer requestId) {

		if (dto.getNameApprove() != null || dto.getStatusApprove() != null) {

			ApproveRequest approveRequest = new ApproveRequest();

			ApproveRequestDetail approveRequestDetail1, approveRequestDetail2;
			approveRequestDetail1 = approveRequestDetailDao
					.getUnapprovedRequest(requestId, "catalogName");
			approveRequestDetailDao.getSession().evict(approveRequestDetail1);
			if (dto.getNameApprove() == null
					|| dto.getNameApprove().length() < 1) {
				if (approveRequestDetail1 != null) {
					approveRequest.getApproveRequestDetails().add(
							approveRequestDetail1);
				}
			} else {
				if (approveRequestDetail1 == null) {
					approveRequestDetail1 = new ApproveRequestDetail();
				}
				approveRequestDetail1.setColumnName("catalogName");
				approveRequestDetail1.setOldValue(catalog.getCatalogName());
				approveRequestDetail1.setNewValue(dto.getNameApprove());
				approveRequestDetail1.setReason(dto.getNameReason());
				approveRequest.getApproveRequestDetails().add(
						approveRequestDetail1);
			}

			approveRequestDetail2 = approveRequestDetailDao
					.getUnapprovedRequest(requestId, "status");
			System.out.println(approveRequestDetail2);
			approveRequestDetailDao.getSession().evict(approveRequestDetail2);
			if (dto.getStatusApprove() == null
					|| dto.getStatusApprove().length() < 1) {
				if (approveRequestDetail2 != null) {
					approveRequest.getApproveRequestDetails().add(
							approveRequestDetail2);
				}

			} else {
				if (approveRequestDetail2 == null) {
					approveRequestDetail2 = new ApproveRequestDetail();
				}
				approveRequestDetail2.setColumnName("status");
				approveRequestDetail2.setOldValue(catalog.getStatus());
				approveRequestDetail2.setNewValue(dto.getStatusApprove());
				approveRequestDetail2.setReason(dto.getStatusReason());
				approveRequest.getApproveRequestDetails().add(
						approveRequestDetail2);
			}

			if (requestId != null) {
				approveRequest.setRequestId(requestId);
			} else {
				approveRequest.setRequestId(null);
			}
			approveRequest.setObjectId(catalog.getId());
			approveRequest.setTableName(RequestApproveType.Catalog.name());
			approveRequest
					.setApproveStatus(RequestApproveStatusType.UNPROCESSED
							.name());
			approveRequest.setRequestedBy(loginUserId);
			approveRequest.setRequestDate(new Date());
			approveRequestDao.save(approveRequest);
		}
	}

	/**
	 * 保存Product的同时保存它的StorageCondition
	 * 
	 * @param product
	 * @param storageCondition
	 * @param userId
	 */
	private void attachStorageCondition(Product product,
			StorageCondition storageCondition, Integer userId) {
		Date now = new Date();
		storageCondition.setProductId(product.getProductId());
		storageCondition.setCreatedBy(userId);
		storageCondition.setModifiedBy(userId);
		storageCondition.setCreationDate(now);
		storageCondition.setModifyDate(now);
		this.storageConditionDao.save(storageCondition);
	}

	/**
	 * 保存Product同时保存它的ShipCondition.
	 * 
	 * @param product
	 * @param shipCondition
	 * @param userId
	 */
	private void attachShipCondition(Product product,
			ShipCondition shipCondition, Integer userId) {
		Date now = new Date();
		shipCondition.setProductId(product.getProductId());
		shipCondition.setCreatedBy(userId);
		shipCondition.setModifiedBy(userId);
		shipCondition.setCreationDate(now);
		shipCondition.setModifyDate(now);
		if (shipCondition.getShipWeightUom() == null) {
			shipCondition.setShipWeightUom("lbs");
		}
		this.shipConditionDao.save(shipCondition);
	}

	/**
	 * 保存Product的同时保存intermediate list, 删除intermediate list.
	 * 
	 * @param product
	 * @param intmdList
	 * @param delIntmdIdList
	 * @param userId
	 */
	private void attachPdtIntmd(Product product,
			List<IntermediateDTO> intmdList, List<Integer> delIntmdIdList,
			Integer userId) {
		if (delIntmdIdList != null && delIntmdIdList.get(0) != null) {
			this.intermediateDao.delIntmdList(delIntmdIdList);
		}
		if (intmdList == null || intmdList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (IntermediateDTO dto : intmdList) {
			Intermediate intmd = this.dozer.map(dto, Intermediate.class);
			intmd.setProductId(product.getProductId());
			intmd.setModifyDate(now);
			intmd.setModifiedBy(userId);
			if (SoapUtil.getIntegerFromSOAP(intmd.getId()) == null) {
				intmd.setId(null);
				intmd.setCreatedBy(userId);
				intmd.setCreationDate(now);
			}
			this.intermediateDao.save(intmd);
		}
	}

	/**
	 * 保存Product的同时保存Component list, 删除Component list.
	 * 
	 * @param product
	 * @param componentList
	 * @param delComIdList
	 * @param userId
	 */
	private void attachPdtComponent(Product product,
			List<ComponentDTO> componentList, List<Integer> delComIdList,
			Integer userId) {
		if (delComIdList != null && delComIdList.get(0) != null) {
			this.componentDao.delComponentList(delComIdList);
		}
		if (componentList == null || componentList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (ComponentDTO dto : componentList) {
			Component intmd = this.dozer.map(dto, Component.class);
			intmd.setProductId(product.getProductId());
			intmd.setModifyDate(now);
			intmd.setModifiedBy(userId);
			if (SoapUtil.getIntegerFromSOAP(intmd.getId()) == null) {
				intmd.setId(null);
				intmd.setCreatedBy(userId);
				intmd.setCreationDate(now);
			}
			this.componentDao.save(intmd);
		}
	}

	/**
	 * 保存Product的同时保存RestrictShip list, 删除RestrictShip list.
	 * 
	 * @param product
	 * @param restrictShipList
	 * @param delRestrictShipIdList
	 * @param userId
	 */
	private void attachPdtRestrictShip(Product product,
			List<RestrictShip> restrictShipList,
			List<Integer> delRestrictShipIdList, Integer userId) {
		if (delRestrictShipIdList != null
				&& delRestrictShipIdList.get(0) != null) {
			this.restrictShipDao.delShipList(delRestrictShipIdList);
		}
		if (restrictShipList == null || restrictShipList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (RestrictShip ship : restrictShipList) {
			ship.setProductId(product.getProductId());
			ship.setModifyDate(now);
			ship.setModifiedBy(userId);
			if (SoapUtil.getIntegerFromSOAP(ship.getId()) == null) {
				ship.setId(null);
				ship.setCreatedBy(userId);
				ship.setCreationDate(now);
			}
			this.restrictShipDao.save(ship);
		}
	}

	/**
	 * 保存Product的同时保存ProductSpecialPrice list, 删除ProductSpecialPrice list.
	 * 
	 * @param product
	 * @param specialPriceList
	 * @param delIdList
	 * @param userId
	 */
	private void attachPdtSpecialPrice(Product product,
			List<ProductSpecialPrice> specialPriceList,
			List<Integer> delIdList, Integer userId) {
		if (delIdList != null && delIdList.get(0) != null) {
			this.productSpecialPriceDao.delSpecialPriceList(delIdList);
		}
		if (specialPriceList == null || specialPriceList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (ProductSpecialPrice specialPrice : specialPriceList) {
			specialPrice.setProductId(product.getProductId());
			specialPrice.setModifyDate(now);
			specialPrice.setModifiedBy(userId);
			if (SoapUtil.getIntegerFromSOAP(specialPrice.getId()) == null) {
				specialPrice.setId(null);
				specialPrice.setCreatedBy(userId);
				specialPrice.setCreationDate(now);
			}
			this.productSpecialPriceDao.save(specialPrice);
		}
	}

	private void attachPdtPrice(Product product,
			List<ProductPriceDTO> priceList, Integer productId,
			List<String> delIdList, Integer userId) {
		if (delIdList != null && delIdList.get(0) != null) {
			this.productPriceDao.delPriceList(productId, delIdList);
		}
		if (priceList == null || priceList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (ProductPriceDTO priceDTO : priceList) {
			ProductPrice price = dozer.map(priceDTO, ProductPrice.class);
			if (price.getCatalogId() == null) {
				ProductCategory category = this.getProductCategoryDetail(price
						.getCategoryId());
				price.setCatalogId(category.getCatalogId());
			}
			price.setProductId(productId);
			price.setModifyDate(now);
			price.setModifiedBy(userId);
			if (SoapUtil.getIntegerFromSOAP(price.getPriceId()) == null) {
				price.setPriceId(null);
				price.setCreatedBy(userId);
				price.setCreationDate(now);
			}
			this.productPriceDao.save(price);
		}
	}

	/**
	 * 保存Product的同时保存VendorProduct list(供应商相关信息), 删除VendorProduct list.
	 * 
	 * @param product
	 * @param supplierList
	 * @param delIdList
	 * @param userId
	 */
	private void attachPdtSupplier(Product product,
			List<VendorProductDTO> supplierList, List<Integer> delIdList,
			Integer userId) {
		if (supplierList == null || supplierList.get(0) == null) {

		} else {
			Date now = new Date();
			for (VendorProductDTO dto : supplierList) {
				VendorProduct vp = this.dozer.map(dto, VendorProduct.class);
				vp.setCatalogNo(product.getCatalogNo());
				vp.setModifyDate(now);
				vp.setModifiedBy(userId);
				if (SoapUtil.getIntegerFromSOAP(vp.getId()) == null) {
					vp.setId(null);
					vp.setCreatedBy(userId);
					vp.setCreationDate(now);
				}
				this.vendorProductDao.save(vp);
			}
		}
		if (delIdList != null && delIdList.get(0) != null) {
			this.vendorProductDao.delSupplierList(delIdList);
		}
	}

	private void attachPdtRelation(Product product,
			List<ProductRelationDTO> pdtRelationList, Integer userId) {
		if (pdtRelationList == null || pdtRelationList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (ProductRelationDTO dto : pdtRelationList) {
			System.out.println(dto.getName());
			ProductRelation vp = this.dozer.map(dto, ProductRelation.class);
			vp.setProductId(product.getProductId());
			vp.setModifyDate(now);
			vp.setModifiedBy(userId);
			vp.setCreatedBy(userId);
			vp.setCreationDate(now);
			this.productRelationDao.save(vp);
		}
	}

	/**
	 * 保存Product
	 * 
	 * @param productDTO
	 * @return
	 */
	public Product saveProduct(ProductDTO productDTO, Integer userId,
			String ruleId, String path) {
		boolean isAdd = false;
		if (productDTO.getProductId() == null || productDTO.getProductId() == 0) {
			isAdd = true;
		}
		Product product = this.dozer.map(productDTO, Product.class);
		Product dbProduct = this.productDao.findUniqueBy("catalogNo",
				product.getCatalogNo());
		if (dbProduct != null
				&& (product.getProductId() == null || !(product.getProductId()
						.equals(dbProduct.getProductId())))) {
			System.out.println("ruleId:" + ruleId);
			if (ruleId != null && !ruleId.equals("") && !ruleId.equals("null")) {
				System.out.println("ruleId:" + ruleId);
				this.saveCatalogNoRules(Integer.valueOf(ruleId));
			}
			// this.productDao.getSession().evict(dbProduct);
			throw new BussinessException(
					BussinessException.ERR_PRODUCT_CATALOGNO_UNIQUE);
		}
		this.productDao.getSession().evict(dbProduct);
		Integer objectId = product.getProductId();// for approved.
		Date now = new Date();

		product.setModifiedBy(userId);
		if (product.getProductId() == null) {
			product.setCreatedBy(userId);
			product.setCreationDate(now);
		}
		product.setModifyDate(now);
		if (product.getDimUom() == null) {
			product.setDimUom("inches");
		}
		this.productDao.save(product);
		if (ruleId != null && !ruleId.equals("") && !ruleId.equals("null")) {
			System.out.println("ruleId:" + ruleId);
			this.saveCatalogNoRules(Integer.valueOf(ruleId));
		}
		this.attachShipCondition(product, productDTO.getShipCondition(), userId);
		this.attachStorageCondition(product, productDTO.getStorageCondition(),
				userId);
		this.attachPdtRestrictShip(product, productDTO.getRestrictShipList(),
				productDTO.getDelRestrictShipIdList(), userId);
		this.attachPdtIntmd(product, productDTO.getIntmdList(),
				productDTO.getDelIntmdIdList(), userId);
		this.attachPdtComponent(product, productDTO.getComponentList(),
				productDTO.getDelComIdList(), userId);

		this.attachPdtSpecialPrice(product, productDTO.getSpecialPriceList(),
				productDTO.getDelSpecialPriceIdList(), userId);
		this.attachPdtSupplier(product, productDTO.getVendorProductList(),
				productDTO.getDelVendorProductIdList(), userId);
		this.attachPdtRelation(product, productDTO.getPdtRelationList(), userId);
		this.attachPdtRoyalty(product, productDTO.getRoyaltyProduct(), userId);
		this.attachProductMoreInfo(productDTO.getProductExtendedInfo(), userId,
				product.getProductId());

		this.attachPdtPrice(dbProduct, productDTO.getPriceList(),
				product.getProductId(), productDTO.getDelPriceIdList(), userId);
		this.attachProductReference(productDTO.getProductReferenceList(),
				product.getProductId(), userId,
				productDTO.getDelReferenceList());
		String productType = productDTO.getType();
		if (productType != null) {
			productType = productType.toLowerCase();
			Integer productId = product.getProductId();
			if ("peptide".equals(productType)) {
				this.attachPdtOfPeptide(productId, productDTO.getPeptide(),
						userId);
			} else if ("antibody".equals(productType)) {
				this.attachPdtOfAntibody(productId, productDTO.getAntibody(),
						userId);
			} else if ("enzyme".equals(productType)) {
				this.attachPdtOfEnzyme(productId, productDTO.getEnzyme(),
						userId);
			} else if ("gene".equals(productType)) {
				this.attachPdtOfGene(productId, productDTO.getGene(), userId);
			} else if ("kit".equals(productType)) {
				this.attachPdtOfKit(productId, productDTO.getKit(), userId);
			} else if ("oligo".equals(productType)) {
				this.attachPdtOfOligo(productId, productDTO.getOligo(), userId);
			} else if ("protein".equals(productType)) {
				this.attachPdtOfProtein(productId, productDTO.getProtein(),
						userId);
			} else if ("molecule".equals(productType)) {
				this.attachPdtOfMolecule(productId, productDTO.getMolecule(),
						userId);
			} else if ("chemicals".equals(productType)) {
				this.attachPdtOfChemical(productId, productDTO.getChemical(),
						userId);
			}
		}

		// with lifeng's approved module.
		Integer requestId = null;
		productApproved(productDTO, userId, product, objectId, requestId);

		Integer priceRequestId = null;
		if (productDTO.getPriceList() != null
				&& productDTO.getPriceList().size() > 0) {
			for (ProductPriceDTO priceDTO : productDTO.getPriceList()) {
				Integer objectPriceId = priceDTO.getPriceId();
				productPriceApproved(productDTO, userId, priceDTO,
						objectPriceId, priceRequestId);
			}
		}
		String wordString = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(new Date());
		if (path != null && !path.equals("")) {
			try {
				if (product.getProductClsId().equals(2)) {// Peptide Rp打头的产品
					String readPath = path + "wordModel//Emodel.doc";
					wordString = WordPOIUtil.readWordFileToString(readPath);
					Peptide peptide = this.peptideDao.getById(product
							.getProductId());
					if (peptide != null) {
						if (peptide.getPhoenixpeptideCatNo() == null) {
							wordString = wordString
									.replaceAll("catalogNo", " ");
						} else {
							wordString = wordString.replaceAll("catalogNo", " "
									+ product.getCatalogNo());
						}
						if (peptide.getMolecularFormula() == null) {
							wordString = wordString.replaceAll(
									"productformula", " ");
						} else {
							wordString = wordString.replaceAll(
									"productformula",
									" " + peptide.getMolecularFormula());

						}

						if (peptide.getSequenceShortening() == null) {
							wordString = wordString.replaceAll("tttttt", "");
						} else {
							wordString = wordString.replaceAll("tttttt", " "
									+ peptide.getSequenceShortening());
						}
						if (peptide.getCasNo() == null) {
							wordString = wordString.replaceAll("ssaaa", " ");
						} else {
							wordString = wordString.replaceAll("ssaaa", " "
									+ peptide.getCasNo());
						}

						if (peptide.getMolecularWeight() == null) {
							wordString = wordString
									.replaceAll("productmw", " ");
						} else {
							wordString = wordString.replaceAll("productmw", " "
									+ peptide.getMolecularWeight());
						}
						if (peptide.getSpecificity() == null) {
							wordString = wordString.replaceAll("ag", "");
						} else {
							wordString = wordString.replaceAll("ag", ""
									+ peptide.getSpecificity());
						}

						if (peptide.getPurity() == null) {
							wordString = wordString.replaceAll("productPurity",
									" ");
						} else {
							wordString = wordString.replaceAll("productPurity",
									" " + peptide.getPurity());
						}

						if (peptide.getSequence() == null) {
							wordString = wordString.replaceAll(
									"productsequence", " ");
						} else {
							wordString = wordString.replaceAll(
									"productsequence",
									" " + peptide.getSequence());

						}
						if (peptide.getCterminal() == null) {
							wordString = wordString.replaceAll(
									"productcterminal", " ");
						} else {
							wordString = wordString.replaceAll(
									"productcterminal",
									" " + peptide.getCterminal());

						}
						if (peptide.getNterminal() == null) {
							wordString = wordString.replaceAll(
									"productnterminal", " ");
						} else {
							wordString = wordString.replaceAll(
									"productnterminal",
									" " + peptide.getNterminal());

						}
						if (peptide.getConcentration() == null) {
							wordString = wordString.replaceAll(
									"productchemicalbridge", "");
						} else {
							wordString = wordString.replaceAll(
									"productchemicalbridge",
									"" + peptide.getConcentration());
						}

						if (peptide.getSpecificActivity() == null) {
							wordString = wordString.replaceAll("bbbb", " ");
						} else {
							wordString = wordString.replaceAll("bbbb", " "
									+ peptide.getSpecificActivity());
						}

						if (peptide.getEndotoxinLevel() == null) {
							wordString = wordString.replaceAll(
									"productendotoxinlevel", " ");
						} else {
							wordString = wordString.replaceAll(
									"productendotoxinlevel",
									" " + peptide.getEndotoxinLevel());
						}

						if (peptide.getConcentration() == null) {
							wordString = wordString.replaceAll(
									"productconcentration", " ");
						} else {
							wordString = wordString.replaceAll(
									"productconcentration",
									" " + peptide.getConcentration());
						}

						if (peptide.getQualityControl() == null) {
							wordString = wordString.replaceAll(
									"productqualitycontrol", " ");
						} else {
							wordString = wordString.replaceAll(
									"productqualitycontrol",
									" " + peptide.getQualityControl());
						}
						if (productDTO.getStorageCondition().getComment() == null) {
							wordString = wordString.replaceAll(
									"productstorage", " ");
						} else {
							wordString = wordString.replaceAll(
									"productstorage", " "
											+ productDTO.getStorageCondition()
													.getComment());
						}

						if (product.getShortDesc() == null) {
							wordString = wordString.replaceAll("productnotes",
									" ");
						} else {
							wordString = wordString.replaceAll("productnotes",
									" " + product.getShortDesc());
						}

					} else {

						wordString = wordString.replaceAll("productformula",
								" ");

						wordString = wordString.replaceAll("ssaaa", " ");

						wordString = wordString.replaceAll("tttttt", " ");

						wordString = wordString.replaceAll("productmw", " ");

						wordString = wordString
								.replaceAll("productPurity", " ");
						wordString = wordString.replaceAll("bbbb", "");

						wordString = wordString.replaceAll("productsequence",
								" ");

						wordString = wordString.replaceAll("productcterminal",
								" ");

						wordString = wordString.replaceAll("ag", " ");

						wordString = wordString.replaceAll(
								"productendotoxinlevel", " ");

						wordString = wordString.replaceAll(
								"productconcentration", " ");

						wordString = wordString.replaceAll(
								"productqualitycontrol", " ");

						wordString = wordString.replaceAll("productstorage",
								" ");

						wordString = wordString.replaceAll("productnotes", " ");

					}

					if (product.getSize() == null) {
						wordString = wordString.replaceAll("sizes", " ");
					} else {
						String size = " " + product.getSize().toString() + " "
								+ product.getUom();
						if (product.getAltSize() != null) {
							size += " or " + product.getAltSize().toString()
									+ " " + product.getAltUom();
						}
						wordString = wordString.replaceAll("sizes", size);
					}

					if (product.getName() == null) {
						wordString = wordString.replaceAll("name", " ");
					} else {
						wordString = wordString.replaceAll("name", " "
								+ product.getName());
					}

					if (product.getCatalogNo() == null) {
						wordString = wordString.replaceAll("catalogNo", " ");
					} else {
						wordString = wordString.replaceAll("catalogNo", " "
								+ product.getCatalogNo());
					}

					if (productDTO.getStorageCondition().getComment() == null) {
						wordString = wordString.replaceAll("productstorage",
								" ");
					} else {
						wordString = wordString.replaceAll("productstorage",
								" "
										+ productDTO.getStorageCondition()
												.getComment());
					}
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String dateStr = df.format(new Date());
					wordString = wordString.replaceAll("datetime", " Version: "
							+ dateStr);
					String writeP = this.fileService.getUploadPath();
					String writePath = writeP + "productFile_notes/"
							+ product.getCatalogNo() + "_" + s + ".doc";
					System.out.println(writePath);
					WordPOIUtil.writeWordFile(writePath, wordString);
				}
				if (product.getProductClsId().equals(6)) {// Antibody
					String readPath = path + "wordModel//Amodel.doc";
					wordString = WordPOIUtil.readWordFileToString(readPath);
					Antibody antibody = this.antibodyDao.getById(product
							.getProductId());
					ProductExtendedInfo productExtendedInfo = this.pdtExtInfoDao
							.getById(product.getProductId());
					if (product.getName() == null) {
						wordString = wordString.replaceAll("name", " ");
					} else {
						wordString = wordString.replaceAll("name", " "
								+ product.getName());
					}
					if (product.getCatalogNo() == null) {
						wordString = wordString.replaceAll("catalogNo", " ");
					} else {
						wordString = wordString.replaceAll("catalogNo", " "
								+ product.getCatalogNo());
					}
					if (product.getSize() == null) {
						wordString = wordString.replaceAll("productSize", " ");
					} else {
						String size = " " + product.getSize().toString() + " "
								+ product.getUom();
						if (product.getAltSize() != null) {
							size += " or " + product.getAltSize().toString()
									+ " " + product.getAltUom();
						}
						wordString = wordString.replaceAll("productSize", size);
					}
					if (product.getSellingNote() == null) {
						wordString = wordString.replaceAll("sellingNote", " ");
					} else {
						wordString = wordString.replaceAll("sellingNote", " "
								+ product.getSellingNote());
					}
					if (antibody != null) {
						if (antibody.getHostSpecies() == null) {
							wordString = wordString.replaceAll("hostSpecies",
									" ");
						} else {
							wordString = wordString.replaceAll("hostSpecies",
									" " + antibody.getHostSpecies());
						}
						if (antibody.getConjugation() == null) {
							wordString = wordString.replaceAll(
									"productConjugation", " ");
						} else {
							wordString = wordString.replaceAll(
									"productConjugation",
									" " + antibody.getConjugation());
						}
						if (antibody.getImmunogen() == null) {
							wordString = wordString.replaceAll(
									"productImmunogen", " ");
						} else {
							wordString = wordString.replaceAll(
									"productImmunogen",
									" " + antibody.getImmunogen());
						}
						if (antibody.getAntigenSpecies() == null) {
							wordString = wordString.replaceAll(
									"antigenSpecies", " ");
						} else {
							wordString = wordString.replaceAll(
									"antigenSpecies",
									" " + antibody.getAntigenSpecies());
						}
						if (antibody.getSpeciesReactivity() == null) {
							wordString = wordString.replaceAll(
									"speciesReactivity", " ");
						} else {
							wordString = wordString.replaceAll(
									"speciesReactivity",
									" " + antibody.getSpeciesReactivity());
						}
						if (antibody.getSubclass() == null) {
							wordString = wordString.replaceAll(
									"productSubclass", " ");
						} else {
							wordString = wordString.replaceAll(
									"productSubclass",
									" " + antibody.getSubclass());
						}
						if (antibody.getPurification() == null) {
							wordString = wordString.replaceAll(
									"productPurification", " ");
						} else {
							wordString = wordString.replaceAll(
									"productPurification",
									" " + antibody.getPurification());
						}
						if (antibody.getConcentration() == null) {
							wordString = wordString.replaceAll(
									"productConcentration", " ");
						} else {
							wordString = wordString.replaceAll(
									"productConcentration",
									" " + antibody.getConcentration());
						}
						if (antibody.getConcentration() == null) {
							wordString = wordString.replaceAll(
									"productSpecificity", " ");
						} else {
							wordString = wordString.replaceAll(
									"productSpecificity",
									" " + antibody.getSpecificity());
						}
					} else {

						wordString = wordString.replaceAll("hostSpecies", " ");
						wordString = wordString.replaceAll("productImmunogen",
								" ");
						wordString = wordString.replaceAll("antigenSpecies",
								" ");
						wordString = wordString.replaceAll("speciesReactivity",
								" ");
						wordString = wordString.replaceAll("productSubclass",
								" ");
						wordString = wordString.replaceAll(
								"productPurification", " ");
						wordString = wordString.replaceAll(
								"productConcentration", " ");
					}

					if (productDTO.getStorageCondition().getTemperature() == null) {
						wordString = wordString.replaceAll("temperature", " ");
					} else {
						String str = "Store at "
								+ productDTO.getStorageCondition()
										.getTemperature().toString() + " °C";
						if (productDTO.getStorageCondition().getComment() != null) {
							str += "/ "
									+ productDTO.getStorageCondition()
											.getComment();
						}
						wordString = wordString.replaceAll("temperature", str);
					}

					if (productExtendedInfo != null) {
						if (productExtendedInfo.getApplications() == null) {
							wordString = wordString.replaceAll(
									"productApplication", " ");
						} else {
							wordString = wordString
									.replaceAll(
											"productApplication",
											" "
													+ productExtendedInfo
															.getApplications());
						}

					} else {
						wordString = wordString.replaceAll(
								"productApplication", " ");
					}

				}

				if (product.getProductClsId().equals(3)) {
					String readPath = path + "wordModel//Zmodel.doc";

					wordString = WordPOIUtil.readWordFileToString(readPath);
					Protein protein = this.proteinDao.getById(product
							.getProductId());
					if (protein != null) {
						if (product.getName() == null) {
							wordString = wordString.replaceAll(
									"measuredMolecularWeight", " ");
						} else {
							wordString = wordString.replaceAll(
									"measuredMolecularWeight",
									" " + protein.getMolecularWeight());
						}
						if (product.getName() == null) {
							wordString = wordString.replaceAll("productPurty",
									" ");
						} else {
							wordString = wordString.replaceAll("productPurty",
									" " + protein.getPurity());
						}
						if (product.getName() == null) {
							wordString = wordString.replaceAll(
									"proudctEndotoxinLevel", " ");
						} else {
							wordString = wordString.replaceAll(
									"proudctEndotoxinLevel",
									" " + protein.getEndotoxinLevel());
						}
						if (product.getName() == null) {
							wordString = wordString.replaceAll(
									"productQuantitation", " ");
						} else {
							wordString = wordString.replaceAll(
									"productQuantitation",
									" " + protein.getQuantitation());
						}
						if (product.getName() == null) {
							wordString = wordString.replaceAll(
									"productSpecificActivity", " ");
						} else {
							wordString = wordString.replaceAll(
									"productSpecificActivity",
									" " + protein.getSpecificActivity());
						}

						if (product.getName() == null) {
							wordString = wordString.replaceAll(
									"productFormulation", " ");
						} else {
							wordString = wordString.replaceAll(
									"productFormulation",
									" " + protein.getFormulation());
						}
						if (product.getName() == null) {
							wordString = wordString.replaceAll(
									"productReconstitution", " ");
						} else {
							wordString = wordString.replaceAll(
									"productReconstitution",
									" " + protein.getReconstitution());
						}
						if (product.getName() == null) {
							wordString = wordString.replaceAll(
									"productSequence", " ");
						} else {
							wordString = wordString.replaceAll(
									"productSequence",
									" " + protein.getSequence());
						}
						if (product.getName() == null) {
							wordString = wordString.replaceAll("productDimers",
									" ");
						} else {
							wordString = wordString.replaceAll("productDimers",
									" " + protein.getDimers());
						}
						if (product.getName() == null) {
							wordString = wordString.replaceAll(
									"productSequencAnalysis", " ");
						} else {
							wordString = wordString.replaceAll(
									"productSequencAnalysis",
									" " + protein.getSequenceAnalysis());
						}
						if (product.getName() == null) {
							wordString = wordString.replaceAll("productSource",
									" ");
						} else {
							wordString = wordString.replaceAll("productSource",
									" " + protein.getSource());
						}

					} else {
						wordString = wordString.replaceAll(
								"measuredMolecularWeight", " ");

						wordString = wordString.replaceAll("productPurty", " ");

						wordString = wordString.replaceAll(
								"proudctEndotoxinLevel", " ");

						wordString = wordString.replaceAll(
								"productQuantitation", " ");

						wordString = wordString.replaceAll(
								"productSpecificActivity", " ");

						wordString = wordString.replaceAll(
								"productFormulation", " ");

						wordString = wordString.replaceAll(
								"productReconstitution", " ");

						wordString = wordString.replaceAll("productSequence",
								" ");

						wordString = wordString
								.replaceAll("productDimers", " ");

						wordString = wordString.replaceAll(
								"productSequencAnalysis", " ");

						wordString = wordString
								.replaceAll("productSource", " ");

					}
					if (product.getName() == null) {
						wordString = wordString.replaceAll("name", " ");
					} else {
						wordString = wordString.replaceAll("name", " "
								+ product.getName());
					}
					if (product.getCatalogNo() == null) {
						wordString = wordString.replaceAll("catalogNo", " ");
					} else {
						wordString = wordString.replaceAll("catalogNo", " "
								+ product.getCatalogNo());
					}
					if (product.getName() == null) {
						wordString = wordString.replaceAll(
								"productDescription", " ");
					} else {
						wordString = wordString.replaceAll(
								"productDescription",
								" " + product.getShortDesc());
					}
					if (product.getName() == null) {
						wordString = wordString.replaceAll("productStorage",
								" ");
					} else {
						wordString = wordString.replaceAll("productStorage",
								" "
										+ productDTO.getStorageCondition()
												.getComment());
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = df.format(new Date());
			wordString = wordString.replaceAll("datetime", " Version: "
					+ dateStr);
			String writeP = this.fileService.getUploadPath();
			String writePath = writeP + "productFile_notes/"
					+ product.getCatalogNo() + "_" + s + ".doc"; 
			WordPOIUtil.writeWordFile(writePath, wordString);
			Integer loginUserId = SessionUtil.getUserId();
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			Documents document = null;
			if (product.getProductId() != null
					&& !"".equals(product.getProductId())) {
				// 从Documents表中取得这个老版本
				document = this.documentdao.getByProductId(product
						.getProductId());
				String dates = "";
				dates = df.format(now);

				String filePath = "productFile_notes/" + product.getCatalogNo()
						+ "_" + s + ".doc";
				String docname = product.getCatalogNo() + "_" + s + ".doc";
				if (document.getDocId() != null) {
					DocumentVersion ver = this.dozer.map(document,
							DocumentVersion.class);
					ver.setVersion(document.getVersion());
					ver.setDocFilePath(filePath);
					ver.setDocId(document.getDocId());
					ver.setCreatedBy(userId);
					ver.setCreationDate(now);
					ver.setModifiedBy(userId);
					ver.setModifyDate(now);
					this.documentVersionDao.save(ver);
					String sql = "";
					sql = "update   `product`.`documents` set  doc_name='"
							+ docname
							+ "',version='"
							+ format.format(now)
							+ "',doc_type='Document-DATASHEET',"
							+ "doc_file_type='DOC',doc_file_name='"
							+ docname
							+ "',"
							+ "doc_file_path='"
							+ filePath
							+ "',description='new document for datesheet !',old_flag='4',"
							+ "internal_flag='1',validate_flag='1',creation_date='"
							+ dates + "',created_by='" + loginUserId
							+ "',modify_date='" + dates + "',modified_by='"
							+ loginUserId + "' where doc_id="
							+ document.getDocId();
					System.out.println(sql);
					ClassPathResource cr = new ClassPathResource(
							"application.properties");
					Properties pros = new Properties();
					try {
						pros.load(cr.getInputStream());
					} catch (IOException e3) {
						e3.printStackTrace();
					}
					String user = pros.getProperty("jdbc.username");
					String password = pros.getProperty("jdbc.password");
					String url = pros.getProperty("jdbc.url");
					String driver = pros.getProperty("jdbc.driver");
					Connection con = null;
					Statement stmt = null;
					int s1 = 0;
					try {
						Class.forName(driver);
						con = DriverManager.getConnection(url, user, password);
						stmt = con.createStatement(
								java.sql.ResultSet.TYPE_FORWARD_ONLY,
								java.sql.ResultSet.CONCUR_READ_ONLY);
						s1 = stmt.executeUpdate(sql);
						if (s1 != -1) {
							System.out.println(" create it ok ~");
						}
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e2) {
						e2.printStackTrace();
					} finally {
						try {
							if (stmt != null)
								stmt.close();
							if (con != null)
								con.close();
						} catch (SQLException e) {
							System.out.println(e.toString());
						}
					}
				} else {
					Documents entity = new Documents();
					entity.setCreatedBy(loginUserId);
					entity.setCreationDate(now);
					entity.setModifiedBy(userId);
					entity.setModifyDate(now);
					entity.setDocFileName(docname);
					entity.setDocFilePath(filePath);
					entity.setDocFileType("DOC");
					entity.setDocName(docname);
					entity.setInternalFlag("1");
					entity.setValidateFlag("1");
					entity.setOldFlag("4");
					entity.setDocType("Document-DATASHEET");
					entity.setDescription("new document for datesheet !");
					this.documentDao.save(entity);
					ProductDocuments pd = new ProductDocuments();
					pd.setCreatedBy(userId);
					pd.setCreationDate(now);
					pd.setModifiedBy(userId);
					pd.setModifyDate(now);
					pd.setDocId(entity.getDocId());
					pd.setProductId(product.getProductId());
					this.productDocumentDao.save(pd);
				}

			}

		}
		if (isAdd) {
			ApplicationEvent evt = new NewPartEvent(this, productDTO);
			context.publishEvent(evt);
		} else {
			ApplicationEvent evt = new NewPartEvent(this, productDTO);
			context.publishEvent(evt);
		}
		return product;

	}

	/*
	 * del product 批量修改status 为INACTIVE
	 */
	public void delProductListApproved(String[] objectIds, Integer userId,
			String approved, String approvedReason) {
		for (int i = 0; i < objectIds.length; i++) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setStatusApprove(approved);
			productDTO.setStatusReason(approvedReason);
			Product product = this.productDao.getById(Integer
					.valueOf(objectIds[i]));
			Integer requestId = null;
			productApproved(productDTO, userId, product,
					Integer.valueOf(objectIds[i]), requestId);
			/*
			 * Integer requestId = null; Integer objectId =
			 * Integer.valueOf(objectIds[i]); boolean statusAppr =
			 * this.checkPropertyApproved
			 * (objectId,RequestApproveType.status.name
			 * (),RequestApproveType.Product.name()); if(!statusAppr){
			 * ProductDTO productDTO = this.getProductDetail(objectId); Product
			 * product = this.dozer.map(productDTO, Product.class);
			 * if(!product.getStatus().equals(approved)){ if (objectId!= null) {
			 * ApproveRequest appRequest =
			 * approveRequestDao.countApproveRequest(
			 * RequestApproveType.Product.name(), objectId); if (appRequest !=
			 * null) { requestId = appRequest.getRequestId();
			 * approveRequestDao.getSession().evict(appRequest); } }
			 * productDTO.setStatusApprove(approved);
			 * productDTO.setStatusReason(approvedReason);
			 * productApproved(productDTO, userId, product, objectId,
			 * requestId); } }
			 */
		}
	}

	/*
	 * del product 不可修改
	 */
	public List<Integer> getApprovedRequestListByTableTypeStatus(
			String tableName) {
		List<ApproveRequestBean> approveRequestBean = this.approveRequestBeanDao
				.getApprovedRequestListByTableTypeStatus(tableName);
		List<Integer> list = new ArrayList<Integer>();
		for (ApproveRequestBean bean : approveRequestBean) {
			list.add(bean.getObjectId());
		}
		return list;
	}

	private void productApproved(ProductDTO productDTO, Integer userId,
			Product product, Integer objectId, Integer requestId) {
		if (productDTO.getNameApprove() != null
				|| productDTO.getStatusApprove() != null
				|| productDTO.getUnitCost() != null) {
			ApproveRequest approveRequest = new ApproveRequest();

			ApproveRequestDetail approveRequestDetail1, approveRequestDetail2, approveRequestDetail3;
			approveRequestDetail1 = approveRequestDetailDao
					.getUnapprovedRequest(requestId, "name");
			approveRequestDetailDao.getSession().evict(approveRequestDetail1);
			if (productDTO.getNameApprove() == null
					|| productDTO.getNameApprove().length() < 1) {
				if (approveRequestDetail1 != null) {
					approveRequest.getApproveRequestDetails().add(
							approveRequestDetail1);
				}
			} else {
				if (approveRequestDetail1 == null) {
					approveRequestDetail1 = new ApproveRequestDetail();
				}
				approveRequestDetail1.setColumnName("name");
				approveRequestDetail1.setOldValue(product.getName());
				approveRequestDetail1.setNewValue(productDTO.getNameApprove());
				approveRequestDetail1.setReason(productDTO.getNameReason());
				approveRequest.getApproveRequestDetails().add(
						approveRequestDetail1);
			}

			approveRequestDetail2 = approveRequestDetailDao
					.getUnapprovedRequest(requestId, "status");
			approveRequestDetailDao.getSession().evict(approveRequestDetail2);
			if (productDTO.getStatusApprove() == null
					|| productDTO.getStatusApprove().length() < 1) {
				if (approveRequestDetail2 != null) {
					approveRequest.getApproveRequestDetails().add(
							approveRequestDetail2);
				}
			} else {
				if (approveRequestDetail2 == null) {
					approveRequestDetail2 = new ApproveRequestDetail();
				}
				approveRequestDetail2.setColumnName("status");
				approveRequestDetail2.setOldValue(product.getStatus());
				approveRequestDetail2
						.setNewValue(productDTO.getStatusApprove());
				approveRequestDetail2.setReason(productDTO.getStatusReason());
				approveRequest.getApproveRequestDetails().add(
						approveRequestDetail2);
			}
			// unitCost修改保存
			approveRequestDetail3 = approveRequestDetailDao
					.getUnapprovedRequest(requestId, "unitCost");
			approveRequestDetailDao.getSession().evict(approveRequestDetail3);
			if (productDTO.getUnitCostApprove() == null
					|| productDTO.getUnitCostApprove().length() < 1) {
				if (approveRequestDetail3 != null) {
					approveRequest.getApproveRequestDetails().add(
							approveRequestDetail3);
				}
			} else {
				if (approveRequestDetail3 == null) {
					approveRequestDetail3 = new ApproveRequestDetail();
				}
				approveRequestDetail3.setColumnName("unitCost");
				if (product.getUnitCost() == null) {
					approveRequestDetail3.setOldValue(null);
				} else {
					approveRequestDetail3.setOldValue(String.valueOf(product
							.getUnitCost()));
				}
				approveRequestDetail3.setNewValue(productDTO
						.getUnitCostApprove());
				approveRequestDetail3.setReason(productDTO.getUnitCostReason());
				approveRequest.getApproveRequestDetails().add(
						approveRequestDetail3);
			}

			if (requestId != null) {
				approveRequest.setRequestId(requestId);
			} else {
				approveRequest.setRequestId(null);
			}
			approveRequest.setObjectId(objectId);
			approveRequest.setTableName(RequestApproveType.Product.name());
			approveRequest
					.setApproveStatus(RequestApproveStatusType.UNPROCESSED
							.name());
			approveRequest.setRequestedBy(userId);
			approveRequest.setRequestDate(new Date());
			approveRequestDao.save(approveRequest);
		}
	}

	private void productPriceApproved(ProductDTO productDTO, Integer userId,
			ProductPriceDTO productPriceDTO, Integer objectId, Integer requestId) {
		if (productPriceDTO.getProductPriceApprove() != null) {
			ApproveRequest approveRequest = new ApproveRequest();

			ApproveRequestDetail approveRequestDetail;
			approveRequestDetail = approveRequestDetailDao
					.getUnapprovedRequest(requestId, "standardPrice");
			approveRequestDetailDao.getSession().evict(approveRequestDetail);
			if (productPriceDTO.getProductPriceApprove() == null) {
				if (approveRequestDetail != null) {
					approveRequest.getApproveRequestDetails().add(
							approveRequestDetail);
				}
			} else {
				if (approveRequestDetail == null) {
					approveRequestDetail = new ApproveRequestDetail();
				}
				approveRequestDetail.setColumnName("standardPrice");
				approveRequestDetail.setOldValue(productPriceDTO
						.getStandardPrice().toString());
				approveRequestDetail.setNewValue(productPriceDTO
						.getProductPriceApprove().toString());
				approveRequestDetail.setReason(productPriceDTO
						.getProductPriceReason());
				approveRequest.getApproveRequestDetails().add(
						approveRequestDetail);
			}

			if (requestId != null) {
				approveRequest.setRequestId(requestId);
			} else {
				approveRequest.setRequestId(null);
			}
			approveRequest.setObjectId(objectId);
			approveRequest.setTableName(RequestApproveType.ProductPrice.name());
			approveRequest
					.setApproveStatus(RequestApproveStatusType.UNPROCESSED
							.name());
			approveRequest.setRequestedBy(userId);
			approveRequest.setRequestDate(new Date());
			approveRequestDao.save(approveRequest);
		}
	}

	/**
	 * Save ProductCategory base information and Sub-ProductCategory list,
	 * Product list.
	 * 
	 * @param dto
	 * @param loginUserId
	 * @return
	 */
	public ProductCategory savePdtCategory(ProductCategoryDTO dto,
			Integer loginUserId) {
		ProductCategory category = this.dozer.map(dto, ProductCategory.class);
		ProductCategory dbCategory = this.productCategoryDao.findUniqueBy(
				"categoryNo", category.getCategoryNo());
		if (dbCategory != null
				&& (category.getCategoryId() == null || !(category
						.getCategoryId().equals(dbCategory.getCategoryId())))) {
			// this.productCategoryDao.getSession().evict(dbCategory);
			throw new BussinessException(
					BussinessException.ERR_PRODUCTCATEGORY_CATEGORYNO_UNIQUE);
		}
		this.productCategoryDao.getSession().evict(dbCategory);
		Integer objectId = category.getCategoryId();// for approve request.
		category.setModifiedBy(loginUserId);
		this.saveOrUpdatePdtCategory(category);
		if (dto.getSubCategoryLists() != null
				&& !dto.getSubCategoryLists().isEmpty()) {
			this.attachSubPdtCategoryLists(category, dto.getSubCategoryLists(),
					dto.getDelSubCategoryIdList());
		} else {
			this.attachSubPdtCategoryList(category, dto.getSubCategoryList(),
					dto.getDelSubCategoryIdList());
		}
		this.attachPdtCatProduct(category, dto.getProductPriceList(),
				dto.getDelProductPriceIdList(), loginUserId);
		// with lifeng's approved module.
		Integer requestId = null;
		// if (objectId != null) {
		// ApproveRequest appRequest = approveRequestDao.countApproveRequest(
		// RequestApproveType.ProductCategory.name(), objectId);
		// if (appRequest != null) {
		// requestId = appRequest.getRequestId();
		// approveRequestDao.getSession().evict(appRequest);
		// }
		// }
		categoryApproved(dto, loginUserId, category, objectId, requestId);

		return category;
	}

	/*
	 * del category 批量修改status 为INACTIVE
	 */
	public void delCategoryApproved(String[] objectIds, Integer userId,
			String approved, String approvedReason) {
		for (int i = 0; i < objectIds.length; i++) {
			Integer requestId = null;
			ProductCategoryDTO dto = new ProductCategoryDTO();
			dto.setStatusApprove(approved);
			ProductCategory category = this.productCategoryDao.getById(Integer
					.valueOf(objectIds[i]));
			categoryApproved(dto, userId, category,
					Integer.valueOf(objectIds[i]), requestId);
		}
	}

	public ProductCategory saveOrUpdateProductCategory(
			ProductCategory productCategory, Integer loginUserId) {
		ProductCategoryDTO dto = this.dozer.map(productCategory,
				ProductCategoryDTO.class);
		return this.savePdtCategory(dto, loginUserId);
	}

	private void categoryApproved(ProductCategoryDTO dto, Integer loginUserId,
			ProductCategory category, Integer objectId, Integer requestId) {
		if (dto.getNameApprove() != null || dto.getStatusApprove() != null) {
			ApproveRequest approveRequest = new ApproveRequest();

			ApproveRequestDetail approveRequestDetail1, approveRequestDetail2;
			approveRequestDetail1 = approveRequestDetailDao
					.getUnapprovedRequest(requestId, "name");
			approveRequestDetailDao.getSession().evict(approveRequestDetail1);
			if (dto.getNameApprove() == null
					|| dto.getNameApprove().length() < 1) {
				if (approveRequestDetail1 != null) {
					approveRequest.getApproveRequestDetails().add(
							approveRequestDetail1);
				}
			} else {
				if (approveRequestDetail1 == null) {
					approveRequestDetail1 = new ApproveRequestDetail();
				}
				approveRequestDetail1.setColumnName("name");
				approveRequestDetail1.setOldValue(category.getName());
				approveRequestDetail1.setNewValue(dto.getNameApprove());
				approveRequestDetail1.setReason(dto.getNameReason());
				approveRequest.getApproveRequestDetails().add(
						approveRequestDetail1);
			}

			approveRequestDetail2 = approveRequestDetailDao
					.getUnapprovedRequest(requestId, "status");
			approveRequestDetailDao.getSession().evict(approveRequestDetail2);
			if (dto.getStatusApprove() == null
					|| dto.getStatusApprove().length() < 1) {
				if (approveRequestDetail2 != null) {
					approveRequest.getApproveRequestDetails().add(
							approveRequestDetail2);
				}
			} else {
				if (approveRequestDetail2 == null) {
					approveRequestDetail2 = new ApproveRequestDetail();
				}
				approveRequestDetail2.setColumnName("status");
				approveRequestDetail2.setOldValue(category.getStatus());
				approveRequestDetail2.setNewValue(dto.getStatusApprove());
				approveRequestDetail2.setReason(dto.getStatusReason());
				approveRequest.getApproveRequestDetails().add(
						approveRequestDetail2);
			}

			if (requestId != null) {
				approveRequest.setRequestId(requestId);
			} else {
				approveRequest.setRequestId(null);
			}
			approveRequest.setObjectId(objectId);
			approveRequest.setTableName(RequestApproveType.ProductCategory
					.name());
			approveRequest
					.setApproveStatus(RequestApproveStatusType.UNPROCESSED
							.name());
			approveRequest.setRequestedBy(loginUserId);
			approveRequest.setRequestDate(new Date());
			approveRequestDao.save(approveRequest);
		}
	}

	/**
	 * A part of For Save ProductCategory: base information.
	 * 
	 * @param category
	 */
	private void saveOrUpdatePdtCategory(ProductCategory category) {
		if (category.getCatalogId() == null
				|| category.getCatalogId().trim().length() < 1) {
			category.setCatalogId(null);
		}
		Date now = new Date();
		if (category.getCategoryId() == null
				|| category.getCategoryId().intValue() == 0) {
			category.setCategoryId(null);
			category.setCreationDate(now);
			category.setCreatedBy(category.getModifiedBy());
		}
		if (category.getParentCatId() == null
				|| category.getParentCatId().intValue() == 0) {
			category.setParentCatId(null);
		}
		category.setModifyDate(now);
		this.productCategoryDao.save(category);
	}

	/**
	 * 删除ProductCategory的同时新增， 修改和删除它的sub product category.
	 * 
	 * @param pdtCategory
	 * @param subCatList
	 * @param delCatIdList
	 */
	private void attachSubPdtCategoryList(ProductCategory pdtCategory,
			List<ProductCategoryDTO> subCatList, List<Integer> delCatIdList) {
		if (delCatIdList != null && delCatIdList.get(0) != null) {

			for (Integer pdtCategoryId : delCatIdList) {
				Map<Integer, String> delResult = this
						.delPdtCategoryResult(pdtCategoryId);
				if (delResult != null) {
					throw new BussinessException(delResult.get(pdtCategoryId));
				}
			}

			this.productCategoryDao.delPdtCatListByModifyPCatId(delCatIdList);
		}
		if (subCatList == null || subCatList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (ProductCategoryDTO dto : subCatList) {
			ProductCategory cat = this.dozer.map(dto, ProductCategory.class);
			cat.setModifyDate(now);
			cat.setModifiedBy(pdtCategory.getModifiedBy());
			if (cat.getCategoryId() == null
					|| cat.getCategoryId().intValue() == 0) {
				cat.setCategoryId(null);
				cat.setCreatedBy(pdtCategory.getModifiedBy());
				cat.setCreationDate(now);
			}
			cat.setCatalogId(pdtCategory.getCatalogId());
			cat.setParentCatId(pdtCategory.getCategoryId());
			this.productCategoryDao.save(cat);
			List<ProductCategory> subCategroyList = this.productCategoryDao
					.findBy("parentCatId", cat.getCategoryId());
			this.productCategoryDao.getSession().evict(subCategroyList);
			if (subCategroyList != null && !subCategroyList.isEmpty()) {
				for (ProductCategory pc : subCategroyList) {
					pc.setCatalogId(pdtCategory.getCatalogId());
					this.productCategoryDao.save(pc);
				}
			}
		}
	}

	/**
	 * 删除ProductCategory的同时新增， 修改和删除它的sub product category.
	 * 
	 * @param pdtCategory
	 * @param subCatLists
	 * @param delCatIdList
	 */
	private void attachSubPdtCategoryLists(ProductCategory pdtCategory,
			List<ProductCategory> subCatLists, List<Integer> delCatIdList) {
		if (delCatIdList != null && delCatIdList.get(0) != null) {

			for (Integer pdtCategoryId : delCatIdList) {
				Map<Integer, String> delResult = this
						.delPdtCategoryResult(pdtCategoryId);
				if (delResult != null) {
					throw new BussinessException(delResult.get(pdtCategoryId));
				}
			}

			this.productCategoryDao.delPdtCatListByModifyPCatId(delCatIdList);
		}
		if (subCatLists == null || subCatLists.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (ProductCategory cat : subCatLists) {
			cat.setModifyDate(now);
			cat.setModifiedBy(pdtCategory.getModifiedBy());
			if (cat.getCategoryId() == null
					|| cat.getCategoryId().intValue() == 0) {
				cat.setCategoryId(null);
				cat.setCreatedBy(pdtCategory.getModifiedBy());
				cat.setCreationDate(now);
			}
			cat.setCatalogId(pdtCategory.getCatalogId());
			cat.setParentCatId(pdtCategory.getCategoryId());
			this.productCategoryDao.save(cat);
			List<ProductCategory> subCategroyList = this.productCategoryDao
					.findBy("parentCatId", cat.getCategoryId());
			this.productCategoryDao.getSession().evict(subCategroyList);
			if (subCategroyList != null && !subCategroyList.isEmpty()) {
				for (ProductCategory pc : subCategroyList) {
					pc.setCatalogId(pdtCategory.getCatalogId());
					this.productCategoryDao.save(pc);
				}
			}
		}
	}

	/**
	 * 更新ProductCategory的同时新增， 修改和删除它的相关联product.
	 * 
	 * @param pdtCategory
	 * @param productPriceList
	 * @param delProductPriceIdList
	 * @param userId
	 */
	private void attachPdtCatProduct(ProductCategory pdtCategory,
			List<ProductPriceDTO> productPriceList,
			List<Integer> delProductPriceIdList, Integer userId) {
		if (delProductPriceIdList != null
				&& delProductPriceIdList.get(0) != null) {
			this.productPriceDao.delProductPriceList(
					pdtCategory.getCategoryId(), delProductPriceIdList);
		}
		if (productPriceList == null || productPriceList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (ProductPriceDTO dto : productPriceList) {
			ProductPrice target = this.dozer.map(dto, ProductPrice.class);
			target.setModifyDate(now);
			target.setModifiedBy(userId);
			if (SoapUtil.getIntegerFromSOAP(target.getPriceId()) == null) {
				target.setPriceId(null);
				target.setCreatedBy(userId);
				target.setCreationDate(now);
			}
			System.out.println(pdtCategory.getCatalogId());
			System.out.println(pdtCategory.getCategoryId());

			target.setCatalogId(pdtCategory.getCatalogId());
			target.setCategoryId(pdtCategory.getCategoryId());
			target.setStatus(pdtCategory.getStatus());// 取ProductCategory的status;
			this.productPriceDao.save(target);
		}
	}

	@Transactional(readOnly = true)
	public Page<CategorySearchBean> searchCategoryBean(
			Page<CategorySearchBean> page, List<PropertyFilter> filters) {
		// categorySearchBeanDao.findMyALlProduct(page,filters);
		return categorySearchBeanDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<CategorySearchBean> searchCategoryBean(
			Page<CategorySearchBean> page) {
		return categorySearchBeanDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<CategorySearchBean> searchCategoryBean(
			Page<CategorySearchBean> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return categorySearchBeanDao.findPage(page, filterList);
	}

	/**
	 * 获得一个产品的中间产品列表.
	 * 
	 * @param page
	 * @param productId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<IntermediateDTO> getIntermediateList(Page<Intermediate> page,
			Integer productId) {
		List<IntermediateDTO> dtoList = new ArrayList<IntermediateDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_productId",
				productId);
		filterList.add(orgFilter);
		page = this.intermediateDao.findPage(page, filterList);
		List<Intermediate> resultList = page.getResult();
		if (resultList != null) {
			for (Intermediate intermediate : resultList) {
				IntermediateDTO dto = dozer.map(intermediate,
						IntermediateDTO.class);
				Product temp = this.productDao.findUniqueBy("catalogNo",
						intermediate.getIntmdCatalogNo());
				if (temp != null) {
					dto.setItem(temp.getName());
					dto.setLeadTime(temp.getLeadTime());
					ProductStdPriceBean stdPrice = this
							.getProductPrice(productId);
					if (stdPrice != null) {
						dto.setPrice(stdPrice.getUnitPrice());
						dto.setSymbol(stdPrice.getSymbol());
					}
				}
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		Page<IntermediateDTO> dtoPage = this.dozer.map(page, Page.class);
		dtoPage.setResult(dtoList);
		return dtoPage;
	}

	/**
	 * 获得一个产品的组件子产品列表.
	 * 
	 * @param page
	 * @param productId
	 * @return
	 */

	@Transactional(readOnly = true)
	public Page<ComponentDTO> getComponentList(Page<Component> page,
			Integer productId) {
		List<ComponentDTO> dtoList = new ArrayList<ComponentDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_productId",
				productId);
		filterList.add(orgFilter);
		page = this.componentDao.findPage(page, filterList);
		List<Component> resultList = page.getResult();
		if (resultList != null) {
			for (Component component : resultList) {
				ComponentDTO dto = dozer.map(component, ComponentDTO.class);
				Product temp = this.productDao.findUniqueBy("catalogNo",
						component.getCpntCatalogNo());
				String specification = temp.getShortDesc();
				if (temp != null) {
					dto.setItem(temp.getName());
					dto.setLeadTime(temp.getLeadTime());
					dto.setSpecification(specification);
					ProductListBean stdPrice = this
							.getProductListBean(component.getCpntCatalogNo());
					if (stdPrice != null) {
						dto.setPrice(stdPrice.getUnitPrice());
						dto.setSymbol(stdPrice.getSymbol());
					}

				}
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		Page<ComponentDTO> dtoPage = this.dozer.map(page, Page.class);
		dtoPage.setResult(dtoList);
		return dtoPage;
	}

	/**
	 * 获得某个产品的价格: 根据defalutFlag=Y, status=ACTIVE, 从catalog表中取得catalogId
	 * 根据catalogId 和 productId从product_price表中取得standard_price;
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public ProductStdPriceBean getProductPrice(Integer productId) {
		ProductStdPriceBean stdPrice = this.productStdPriceDao
				.getById(productId);
		return stdPrice;
	}

	/**
	 * 根据name, catalogNo查找StandardPrice list.
	 * 
	 * @param catalogNo
	 * @param name
	 * @return
	 */
	public List<ProductListBean> searchProductList(String catalogNo,
			String name, List<String> catalogNoList) {
		return productListBeanDao.searchProductList(catalogNo, name,
				catalogNoList);
	}

	/**
	 * 根据name, catalogNo查找StandardPrice list.
	 * 
	 * @param catalogNo
	 * @param name
	 * @return
	 */
	public List<ProductStdPriceBean> searchStdPriceList(String catalogNo,
			String name, List<String> catalogNoList) {
		return productStdPriceDao.searchStdPriceList(catalogNo, name,
				catalogNoList);
	}

	/**
	 * 根据ProductId和catalogId获得ProductPrice 及该Price对应的currency.
	 * 
	 * @param productId
	 * @param catalogId
	 * @return
	 */
	@Transactional(readOnly = true)
	public ProductPriceDTO getProductPriceWithCatalog(Integer productId,
			String catalogId) {
		ProductPriceDTO dto = new ProductPriceDTO();
		ProductPrice pdtPrice = null;
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filter = new PropertyFilter("EQI_productId", productId);
		filterList.add(filter);
		PropertyFilter catfilter = new PropertyFilter("EQS_catalogId",
				catalogId);
		filterList.add(catfilter);
		List<ProductPrice> pdtPriceList = this.productPriceDao.find(filterList);
		if (pdtPriceList != null && !pdtPriceList.isEmpty()) {
			pdtPrice = pdtPriceList.get(0);
			dto.setStandardPrice(pdtPrice.getStandardPrice());
			dto.setLimitPrice(pdtPrice.getLimitPrice());
		}
		Catalog catalog = this.catalogDao.findUniqueBy("catalogId", catalogId);
		dto.setCurrencyCode(catalog.getCurrencyCode());
		PbCurrency pbCurrency = this.currencyDao.findUniqueBy("currencyCode",
				catalog.getCurrencyCode());
		if (pbCurrency != null) {
			dto.setSymbol(pbCurrency.getSymbol());
		}
		return dto;
	}

	@Transactional(readOnly = true)
	public ProductPriceDTO getStandardPriceByCatalog(final String catalogNo) {
		ProductStdPriceBean productStdPriceBean = productStdPriceDao
				.findUniqueBy("catalogNo", catalogNo);
		return dozer.map(productStdPriceBean, ProductPriceDTO.class);
	}

	@Transactional(readOnly = true)
	public ProductListBean getProductListBean(final String catalogNo) {
		ProductListBean productListBean = productListBeanDao.findUniqueBy(
				"catalogNo", catalogNo);
		return productListBean;
	}

	@Transactional(readOnly = true)
	public List<DropDownDTO> getRelationItemByProductId(Integer productId) {
		return productRelationBeanDao.getRelationItemByProductIdNew(productId);
	}

	@Transactional(readOnly = true)
	public List<Warehouse> getWarehouseList() {
		return warehouseDao.getAll();
	}

	/**
	 * 获得一个Product的库存(Inventory Stock)统计信息.
	 * 
	 * @param productId
	 * @return
	 */
	public ProductStockStatDTO getProductStockStat(Integer productId) {
		ProductStockStatDTO dto = new ProductStockStatDTO();
		Product product = this.productDao.getById(productId);
		String catalogNo = product.getCatalogNo();
		// 从Stock表中获得Stock Total.
		// Long stockTotal = this.stockDao.getProductStockTotal(catalogNo);
		// if (stockTotal != null) {
		// dto.setStockTotal(stockTotal.intValue());
		// }
		// Long commitedTotal = this.purchaseOrderItemDao
		// .getCommitedItemTotal(catalogNo);
		// if (commitedTotal != null) {
		// dto.setCommitedTotal(commitedTotal.intValue());
		// }
		Long backOrderTotal = this.orderItemDao.getBackOrderTotal(catalogNo);
		if (backOrderTotal != null) {
			dto.setBackOrderTotal(backOrderTotal.intValue());
		}
		Long unProcessedTotal = this.orderReturnItemDao
				.getUnprocessedTotal(catalogNo);
		if (unProcessedTotal != null) {
			dto.setUnProcessedTotal(unProcessedTotal.intValue());
		}
		// 从ERP中获取数据
		PartWhse part = erpSalesOrderService.getPartInfoByCatalogNo(catalogNo);
		if (part != null) {
			if (part.getOnHandQty() != null) {
				dto.setStockTotal(part.getOnHandQty().intValue());
			}
			if (part.getAllocatedQty() != null) {
				dto.setCommitedTotal(part.getAllocatedQty().intValue());
			}
		}

		return dto;
	}

	/**
	 * 获得一个Product产品的所有Supplier(Vendor).
	 * 
	 * @param catalogNo
	 * @return
	 */
	public List<VendorProductDTO> getPdtSupplierList(String catalogNo) {
		List<VendorProductDTO> dtoList = new ArrayList<VendorProductDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filter = new PropertyFilter("EQS_catalogNo", catalogNo);
		filterList.add(filter);
		List<VendorProduct> dbList = this.vendorProductDao.find(filterList);
		if (dbList != null) {
			for (VendorProduct src : dbList) {
				VendorProductDTO dto = this.dozer.map(src,
						VendorProductDTO.class);
				Vendor vendorTemp = this.vendorDao.getById(src.getVendorNo());
				dto.setVendorName(vendorTemp.getVendorName());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/**
	 * 获得关于某个Product的采购(Purchase)相关信息.
	 * 
	 * @param catalogNo
	 * @return
	 */
	public List<PurchaseOrderDTO> getPdtPurchaseOrderList(String catalogNo) {
		List<PurchaseOrderDTO> dtoList = new ArrayList<PurchaseOrderDTO>();
		List<Object[]> list = this.purchaseOrderItemDao
				.getPdtPurchaseOrderList(catalogNo);
		for (int i = 0; i < list.size(); i++) {
			Object[] temp = list.get(i);
			PurchaseOrder po = this.purchaseOrderDao.getById((Integer) temp[0]);
			PurchaseOrderDTO dto = this.dozer.map(po, PurchaseOrderDTO.class);
			dto.setCatalogQty(((Long) temp[1]).intValue());
			Vendor vendorTemp = this.vendorDao.getById(po.getVendorNo());
			dto.setVendorName(vendorTemp.getVendorName());
			dtoList.add(dto);
		}
		return dtoList;
	}

	public List<VendorDTO> getAllSuppliesList(String name) {
		List<VendorDTO> dtoList = new ArrayList<VendorDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filter1 = new PropertyFilter("EQS_status", "ACTIVE");
		if (name != null) {
			PropertyFilter filter2 = new PropertyFilter("LIKES_vendorName",
					name);
			filterList.add(filter2);
		}
		filterList.add(filter1);

		List<Vendor> dbList = vendorDao.find(filterList);
		if (dbList != null) {
			for (Vendor vendor : dbList) {
				VendorDTO dto = this.dozer.map(vendor, VendorDTO.class);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	@Transactional(readOnly = true)
	public List<CatalogDTO> getCatalogApproveList() {
		List<Integer> objectIdList = approveRequestBeanDao
				.getUnapprovedRequestId(RequestApproveType.Catalog.name());
		if (objectIdList != null && objectIdList.size() > 0) {
			List<CatalogDTO> catalogDTOList = new ArrayList<CatalogDTO>();
			for (Integer objectId : objectIdList) {
				Catalog catalog = catalogDao.get(objectId);
				CatalogDTO catalogDTO = new CatalogDTO();
				catalogDTO = dozer.map(catalog, CatalogDTO.class);
				List<ApproveRequestBean> approveRequestBeanList = approveRequestBeanDao
						.getUnapprovedRequest(objectId,
								RequestApproveType.Catalog.name());
				if (approveRequestBeanList != null
						&& approveRequestBeanList.size() > 0) {
					for (ApproveRequestBean approveRequestBean : approveRequestBeanList) {
						if (catalogDTO.getRequestId() != null
								&& !catalogDTO.getRequestId().equals(
										approveRequestBean.getRequestId())) {
							catalogDTOList.add(catalogDTO);
							catalogDTO = dozer.map(catalog, CatalogDTO.class);
						}
						if (approveRequestBean.getColumnName().equals(
								"catalogName")) {
							catalogDTO.setNameNewVal(approveRequestBean
									.getNewValue());
							catalogDTO.setNameOldVal(approveRequestBean
									.getOldValue());
							catalogDTO.setNameReason(approveRequestBean
									.getReason());
							catalogDTO.setRequestId(approveRequestBean
									.getRequestId());
						}
						if (approveRequestBean.getColumnName().equals("status")) {
							catalogDTO.setStatusNewVal(approveRequestBean
									.getNewValue());
							catalogDTO.setStatusOldVal(approveRequestBean
									.getOldValue());
							catalogDTO.setStatusReason(approveRequestBean
									.getReason());
							catalogDTO.setRequestId(approveRequestBean
									.getRequestId());
						}

					}
					catalogDTOList.add(catalogDTO);
				}
			}
			return catalogDTOList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<ProductCategoryDTO> getProductCategoryApproveList() {
		List<Integer> objectIdList = approveRequestBeanDao
				.getUnapprovedRequestId(RequestApproveType.ProductCategory
						.name());
		if (objectIdList != null && objectIdList.size() > 0) {
			List<ProductCategoryDTO> categoryDTOList = new ArrayList<ProductCategoryDTO>();
			for (Integer objectId : objectIdList) {
				ProductCategory category = productCategoryDao.get(objectId);
				ProductCategoryDTO categoryDTO = new ProductCategoryDTO();
				categoryDTO = dozer.map(category, ProductCategoryDTO.class);
				List<ApproveRequestBean> approveRequestBeanList = approveRequestBeanDao
						.getUnapprovedRequest(objectId,
								RequestApproveType.ProductCategory.name());
				if (approveRequestBeanList != null
						&& approveRequestBeanList.size() > 0) {
					for (ApproveRequestBean approveRequestBean : approveRequestBeanList) {
						if (categoryDTO.getRequestId() != null
								&& !categoryDTO.getRequestId().equals(
										approveRequestBean.getRequestId())) {
							categoryDTOList.add(categoryDTO);
							categoryDTO = dozer.map(category,
									ProductCategoryDTO.class);
						}
						if (approveRequestBean.getColumnName().equals("name")) {
							categoryDTO.setNameNewVal(approveRequestBean
									.getNewValue());
							categoryDTO.setNameOldVal(approveRequestBean
									.getOldValue());
							categoryDTO.setNameReason(approveRequestBean
									.getReason());
							categoryDTO.setRequestId(approveRequestBean
									.getRequestId());
						}
						if (approveRequestBean.getColumnName().equals("status")) {
							categoryDTO.setStatusNewVal(approveRequestBean
									.getNewValue());
							categoryDTO.setStatusOldVal(approveRequestBean
									.getOldValue());
							categoryDTO.setStatusReason(approveRequestBean
									.getReason());
							categoryDTO.setRequestId(approveRequestBean
									.getRequestId());
						}

					}
					categoryDTOList.add(categoryDTO);
				}
			}
			return categoryDTOList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<ServiceCategoryDTO> getServiceCategoryApproveList() {
		List<Integer> objectIdList = approveRequestBeanDao
				.getUnapprovedRequestId(RequestApproveType.ServiceCategory
						.name());
		if (objectIdList != null && objectIdList.size() > 0) {
			List<ServiceCategoryDTO> categoryDTOList = new ArrayList<ServiceCategoryDTO>();
			for (Integer objectId : objectIdList) {
				ServiceCategory category = serviceCategoryDao.get(objectId);
				ServiceCategoryDTO categoryDTO = new ServiceCategoryDTO();
				categoryDTO = dozer.map(category, ServiceCategoryDTO.class);
				List<ApproveRequestBean> approveRequestBeanList = approveRequestBeanDao
						.getUnapprovedRequest(objectId,
								RequestApproveType.ServiceCategory.name());
				if (approveRequestBeanList != null
						&& approveRequestBeanList.size() > 0) {
					for (ApproveRequestBean approveRequestBean : approveRequestBeanList) {
						if (categoryDTO.getRequestId() != null
								&& !categoryDTO.getRequestId().equals(
										approveRequestBean.getRequestId())) {
							categoryDTOList.add(categoryDTO);
							categoryDTO = dozer.map(category,
									ServiceCategoryDTO.class);
						}
						if (approveRequestBean.getColumnName().equals("name")) {
							categoryDTO.setNameNewVal(approveRequestBean
									.getNewValue());
							categoryDTO.setNameOldVal(approveRequestBean
									.getOldValue());
							categoryDTO.setNameReason(approveRequestBean
									.getReason());
							categoryDTO.setRequestId(approveRequestBean
									.getRequestId());
						}
						if (approveRequestBean.getColumnName().equals("status")) {
							categoryDTO.setStatusNewVal(approveRequestBean
									.getNewValue());
							categoryDTO.setStatusOldVal(approveRequestBean
									.getOldValue());
							categoryDTO.setStatusReason(approveRequestBean
									.getReason());
							categoryDTO.setRequestId(approveRequestBean
									.getRequestId());
						}

					}
					categoryDTOList.add(categoryDTO);
				}
			}
			return categoryDTOList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<ProductListBeanDTO> getProductApproveList() {
		List<Integer> objectIdList = approveRequestBeanDao
				.getUnapprovedRequestId(RequestApproveType.Product.name());
		if (objectIdList != null && objectIdList.size() > 0) {
			List<ProductListBeanDTO> productDTOList = new ArrayList<ProductListBeanDTO>();
			for (Integer objectId : objectIdList) {
				ProductListBean productListBean = productListBeanDao
						.get(objectId);
				ProductListBeanDTO productListBeanDTO = new ProductListBeanDTO();
				productListBeanDTO = dozer.map(productListBean,
						ProductListBeanDTO.class);
				List<ApproveRequestBean> approveRequestBeanList = approveRequestBeanDao
						.getUnapprovedRequest(objectId,
								RequestApproveType.Product.name());
				if (approveRequestBeanList != null
						&& approveRequestBeanList.size() > 0) {
					for (ApproveRequestBean approveRequestBean : approveRequestBeanList) {
						if (productListBeanDTO.getRequestId() != null
								&& !productListBeanDTO.getRequestId().equals(
										approveRequestBean.getRequestId())) {
							productDTOList.add(productListBeanDTO);
							productListBeanDTO = dozer.map(productListBean,
									ProductListBeanDTO.class);
						}
						if (approveRequestBean.getColumnName().equals("name")) {
							productListBeanDTO.setNameNewVal(approveRequestBean
									.getNewValue());
							productListBeanDTO.setNameOldVal(approveRequestBean
									.getOldValue());
							productListBeanDTO.setNameReason(approveRequestBean
									.getReason());
							productListBeanDTO.setRequestId(approveRequestBean
									.getRequestId());
						} else if (approveRequestBean.getColumnName().equals(
								"status")) {
							productListBeanDTO
									.setStatusNewVal(approveRequestBean
											.getNewValue());
							productListBeanDTO
									.setStatusOldVal(approveRequestBean
											.getOldValue());
							productListBeanDTO
									.setStatusReason(approveRequestBean
											.getReason());
							productListBeanDTO.setRequestId(approveRequestBean
									.getRequestId());
						} else if (approveRequestBean.getColumnName().equals(
								"unitCost")) {
							productListBeanDTO
									.setUnitCostNewVal(approveRequestBean
											.getNewValue());
							productListBeanDTO
									.setUnitCostOldVal(approveRequestBean
											.getOldValue());
							productListBeanDTO
									.setUnitCostReason(approveRequestBean
											.getReason());
							productListBeanDTO.setRequestId(approveRequestBean
									.getRequestId());
						}

					}
					productDTOList.add(productListBeanDTO);
				}
			}
			return productDTOList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<ProductPriceListBeanDTO> getProductPriceApproveList() {
		List<Integer> objectIdList = approveRequestBeanDao
				.getUnapprovedRequestId(RequestApproveType.ProductPrice.name());
		if (objectIdList != null && objectIdList.size() > 0) {
			List<ProductPriceListBeanDTO> productPriceDTOList = new ArrayList<ProductPriceListBeanDTO>();
			for (Integer objectId : objectIdList) {
				ProductPrice productPrice = productPriceDao.get(objectId);
				String catalogId = productPrice.getCatalogId();
				Integer productId = productPrice.getProductId();
				ProductInCategoryBean productInCategoryBean = productInCategoryBeanDao
						.getBeanByBaseCatalog(catalogId, productId);
				if (productInCategoryBean != null) {
					ProductPriceListBeanDTO productPriceListBeanDTO = new ProductPriceListBeanDTO();
					productPriceListBeanDTO = dozer.map(productInCategoryBean,
							ProductPriceListBeanDTO.class);
					List<ApproveRequestBean> approveRequestBeanList = approveRequestBeanDao
							.getUnapprovedRequest(objectId,
									RequestApproveType.ProductPrice.name());
					if (approveRequestBeanList != null
							&& approveRequestBeanList.size() > 0) {
						for (ApproveRequestBean approveRequestBean : approveRequestBeanList) {
							if (approveRequestBean.getColumnName().equals(
									"standardPrice")) {
								productPriceListBeanDTO
										.setPriceNewVal(approveRequestBean
												.getNewValue());
								productPriceListBeanDTO
										.setPriceOldVal(approveRequestBean
												.getOldValue());
								productPriceListBeanDTO
										.setPriceReason(approveRequestBean
												.getReason());
								productPriceListBeanDTO
										.setRequestId(approveRequestBean
												.getRequestId());
							}

						}
						productPriceDTOList.add(productPriceListBeanDTO);
					}
				}
			}
			return productPriceDTOList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<ServicePriceDTO> getServicePriceApproveList() {
		List<ApproveRequestBean> objectIdList = approveRequestBeanDao
				.getApprovedRequestListByTable(RequestApproveType.ServicePrice
						.name());
		List<ApproveRequestBean> objectIdList2 = approveRequestBeanDao
				.getApprovedRequestListByTable(RequestApproveType.SubServicePrice
						.name());
		System.out.println(objectIdList.size());
		System.out.println(objectIdList2.size());

		if (objectIdList != null && objectIdList.size() > 0) {
			List<ServicePriceDTO> servicePriceDTOList = new ArrayList<ServicePriceDTO>();
			for (ApproveRequestBean object : objectIdList) {
				ServicePriceDTO dto = new ServicePriceDTO();
				dto.setRequestId(object.getRequestId());

				System.out.println("object.getRequestId()=="
						+ object.getRequestId());
				System.out
						.println("RequestApproveType.ServicePriceGroup.name()=="
								+ RequestApproveType.ServicePriceGroup.name());
				System.out.println("object.getColumnName()=="
						+ object.getColumnName());

				if (object.getColumnName() != null) {
					if (object.getColumnName().equals(
							RequestApproveType.ServicePriceGroup.name())) {
						PriceRuleGroups group = this.priceRuleGroupsDao
								.getById(Integer.valueOf(object.getNewValue()));
						if (group != null) {
							dto.setPriceNewVal(group.getGroupName());
						}
					} else {
						dto.setPriceNewVal(object.getNewValue());
					}
					dto.setPriceReason(object.getReason());
					dto.setRequestDate(object.getRequestDate());
					dto.setRequestedBy(object.getRequestedBy());
					com.genscript.gsscm.serv.entity.Service serv = null;
					ServiceCategory serviceCategory = null;

					if (!object.getColumnName().equals(
							RequestApproveType.SubServicePrice.name())) {
						ServicePrice servicePrice = servicePriceDao
								.getById(object.getObjectId());

						if (servicePrice != null) {
							if (servicePrice.getStandardPrice() != null) {
								dto.setPriceOldVal(servicePrice
										.getStandardPrice().toString());
							} else if (servicePrice.getPriceRuleGroup() != null) {
								PriceRuleGroups group = this.priceRuleGroupsDao
										.getById(Integer.valueOf(servicePrice
												.getPriceRuleGroup()));
								if (group != null) {
									dto.setPriceOldVal(group.getGroupName());
								}
							}
							dto.setLimitPrice(servicePrice.getLimitPrice());
							dto.setCatalogId(servicePrice.getCatalogId());
							serv = serviceDao.getById(servicePrice
									.getServiceId());
							if (serv != null) {
								dto.setServiceName(serv.getName());
								dto.setCatalogNo(serv.getCatalogNo());
							}
							serviceCategory = serviceCategoryDao
									.getById(servicePrice.getCategoryId());
							if (serviceCategory != null) {
								dto.setCategoryName(serviceCategory.getName());
							}
						}

					} else {
						ServiceSubStepPrice price = this.serviceSubStepPriceDao
								.getById(object.getObjectId());
						if (price != null) {
							dto.setPriceOldVal(price.getRetailPrice()
									.toString());
							dto.setLimitPrice(price.getLimitPrice());

							dto.setCatalogId(price.getCatalogId());
							System.out.println("price=" + price.getStepId());
							ServiceSubSteps subService = serviceSubStepsDao
									.getById(price.getStepId());
							System.out.println("stepId="
									+ subService.getStepId());
							if (subService != null) {
								serv = serviceDao.getById(subService
										.getServiceId());
								System.out.println("serv="
										+ serv.getServiceId());
								if (serv != null) {
									dto.setServiceName(serv.getName());
									dto.setCatalogNo(serv.getCatalogNo());
								}
							}
						}
					}
					if (dto != null) {
						servicePriceDTOList.add(dto);
					}
				}
			}
			return servicePriceDTOList;
		}
		if (objectIdList2 != null && objectIdList2.size() > 0) {
			List<ServicePriceDTO> servicePriceDTOList = new ArrayList<ServicePriceDTO>();
			for (ApproveRequestBean object : objectIdList2) {
				ServicePriceDTO dto = new ServicePriceDTO();
				dto.setRequestId(object.getRequestId());
				/*
				 * System.out.println("object.getRequestId()==" +
				 * object.getRequestId()); System.out
				 * .println("RequestApproveType.ServicePriceGroup.name()==" +
				 * RequestApproveType.ServicePriceGroup.name());
				 * System.out.println("object.getColumnName()==" +
				 * object.getColumnName());
				 */
				if (object.getColumnName() != null) {
					if (object.getColumnName().equals(
							RequestApproveType.ServicePriceGroup.name())) {
						PriceRuleGroups group = this.priceRuleGroupsDao
								.getById(Integer.valueOf(object.getNewValue()));
						if (group != null) {
							dto.setPriceNewVal(group.getGroupName());
						}
					} else {
						dto.setPriceNewVal(object.getNewValue());
					}
					dto.setPriceReason(object.getReason());
					dto.setRequestDate(object.getRequestDate());
					dto.setRequestedBy(object.getRequestedBy());
					com.genscript.gsscm.serv.entity.Service serv = null;
					ServiceCategory serviceCategory = null;

					if (!object.getColumnName().equals(
							RequestApproveType.SubServicePrice.name())) {
						ServicePrice servicePrice = servicePriceDao
								.getById(object.getObjectId());

						if (servicePrice != null) {
							if (servicePrice.getStandardPrice() != null) {
								dto.setPriceOldVal(servicePrice
										.getStandardPrice().toString());
							} else if (servicePrice.getPriceRuleGroup() != null) {
								PriceRuleGroups group = this.priceRuleGroupsDao
										.getById(Integer.valueOf(servicePrice
												.getPriceRuleGroup()));
								if (group != null) {
									dto.setPriceOldVal(group.getGroupName());
								}
							}
							dto.setLimitPrice(servicePrice.getLimitPrice());
							dto.setCatalogId(servicePrice.getCatalogId());
							serv = serviceDao.getById(servicePrice
									.getServiceId());
							if (serv != null) {
								dto.setServiceName(serv.getName());
								dto.setCatalogNo(serv.getCatalogNo());
							}
							serviceCategory = serviceCategoryDao
									.getById(servicePrice.getCategoryId());
							if (serviceCategory != null) {
								dto.setCategoryName(serviceCategory.getName());
							}
						}

					} else {
						ServiceSubStepPrice price = this.serviceSubStepPriceDao
								.getById(object.getObjectId());
						if (price != null) {
							dto.setPriceOldVal(price.getRetailPrice()
									.toString());
							dto.setLimitPrice(price.getLimitPrice());

							dto.setCatalogId(price.getCatalogId());
							System.out.println("price=" + price.getStepId());
							ServiceSubSteps subService = serviceSubStepsDao
									.getById(price.getStepId());
							System.out.println("stepId="
									+ subService.getStepId());
							if (subService != null) {
								serv = serviceDao.getById(subService
										.getServiceId());
								System.out.println("serv="
										+ serv.getServiceId());
								if (serv != null) {
									dto.setServiceName(serv.getName());
									dto.setCatalogNo(serv.getCatalogNo());
								}
							}
						}
					}
					if (dto != null) {
						servicePriceDTOList.add(dto);
					}
				}
			}
			return servicePriceDTOList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<ProductPriceListBeanDTO> getProductPriceApproveList(
			Integer sessProductId) {
		List<Integer> objectIdList = approveRequestBeanDao
				.getApprovedRequestId(RequestApproveType.ProductPrice.name());
		List<ProductPriceListBeanDTO> productPriceDTOList = new ArrayList<ProductPriceListBeanDTO>();
		String catalogId = "";
		Integer productId = 0;
		if (objectIdList != null && objectIdList.size() > 0) {
			// System.out.println(objectIdList.size());
			ProductPrice productPrice = new ProductPrice();
			for (Integer objectId : objectIdList) {
				productPrice = productPriceDao.getObjectByID(objectId);
				if (productPrice != null) {
					if (productPrice.getCatalogId() != null
							&& productPrice.getProductId() != null) {
						catalogId = productPrice.getCatalogId();
						productId = productPrice.getProductId();
						if (sessProductId.equals(productId)) {
							ProductInCategoryBean productInCategoryBean = productInCategoryBeanDao
									.getBeanByBaseCatalog(catalogId, productId);
							if (productInCategoryBean != null) {
								List<ApproveRequestBean> approveRequestBeanList = approveRequestBeanDao
										.getApprovedRequest(objectId,
												RequestApproveType.ProductPrice
														.name());
								if (approveRequestBeanList != null
										&& approveRequestBeanList.size() > 0) {
									for (ApproveRequestBean approveRequestBean : approveRequestBeanList) {
										ProductPriceListBeanDTO productPriceListBeanDTO = new ProductPriceListBeanDTO();
										productPriceListBeanDTO = dozer.map(
												productInCategoryBean,
												ProductPriceListBeanDTO.class);
										if (approveRequestBean.getColumnName()
												.equals("standardPrice")) {
											productPriceListBeanDTO
													.setPriceNewVal(approveRequestBean
															.getNewValue());
											productPriceListBeanDTO
													.setPriceOldVal(approveRequestBean
															.getOldValue());
											productPriceListBeanDTO
													.setPriceReason(approveRequestBean
															.getReason());
											productPriceListBeanDTO
													.setRequestId(approveRequestBean
															.getRequestId());
											productPriceListBeanDTO
													.setRequestDate(approveRequestBean
															.getRequestDate());
											productPriceListBeanDTO
													.setRequestedBy(approveRequestBean
															.getRequestedBy());
										}
										productPriceDTOList
												.add(productPriceListBeanDTO);
									}

								}
							}
						}
					}
				}
			}
		}
		return productPriceDTOList;
	}

	@Transactional(readOnly = true)
	public List<ServiceListBeanDTO> getServiceApproveList() {
		List<Integer> objectIdList = approveRequestBeanDao
				.getUnapprovedRequestId(RequestApproveType.Service.name());
		if (objectIdList != null && objectIdList.size() > 0) {
			List<ServiceListBeanDTO> serviceDTOList = new ArrayList<ServiceListBeanDTO>();
			for (Integer objectId : objectIdList) {
				ServiceListBean serviceListBean = serviceListBeanDao
						.get(objectId);
				System.out.println(objectId);
				ServiceListBeanDTO serviceListBeanDTO = new ServiceListBeanDTO();
				serviceListBeanDTO = dozer.map(serviceListBean,
						ServiceListBeanDTO.class);
				List<ApproveRequestBean> approveRequestBeanList = approveRequestBeanDao
						.getUnapprovedRequest(objectId,
								RequestApproveType.Service.name());
				if (approveRequestBeanList != null
						&& approveRequestBeanList.size() > 0) {
					for (ApproveRequestBean approveRequestBean : approveRequestBeanList) {
						if (approveRequestBean.getColumnName().equals("name")) {
							serviceListBeanDTO.setNameNewVal(approveRequestBean
									.getNewValue());
							serviceListBeanDTO.setNameOldVal(approveRequestBean
									.getOldValue());
							serviceListBeanDTO.setNameReason(approveRequestBean
									.getReason());
							serviceListBeanDTO.setRequestId(approveRequestBean
									.getRequestId());
						}
						if (approveRequestBean.getColumnName().equals("status")) {
							serviceListBeanDTO
									.setStatusNewVal(approveRequestBean
											.getNewValue());
							serviceListBeanDTO
									.setStatusOldVal(approveRequestBean
											.getOldValue());
							serviceListBeanDTO
									.setStatusReason(approveRequestBean
											.getReason());
							serviceListBeanDTO.setRequestId(approveRequestBean
									.getRequestId());
						}

					}
					serviceDTOList.add(serviceListBeanDTO);
				}
			}
			return serviceDTOList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public Boolean checkPropertyApproved(final Integer objectId,
			final String columnName, final String tableName) {
		return approveRequestBeanDao.getUnapprovedRequestStatus(objectId,
				columnName, tableName);
	}

	/**
	 * 判断ProductCategory是否可以删除. 返回的 id list是不能删除的.
	 * 
	 * @param categoryIdList
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Integer> getDelPdtCategory(List<Integer> categoryIdList) {
		List<Integer> dtoList = new ArrayList<Integer>();// 不能删除的.
		for (Integer id : categoryIdList) {
			Map<Integer, String> retMap = this.delPdtCategoryResult(id);
			if (retMap != null) {
				dtoList.add(id);
			}
		}
		return dtoList;
	}

	private Map<Integer, String> delServCategoryResult(Integer pdtCategoryId) {
		Map<Integer, String> retMap = null;
		// 判断该category是否有子级category.
		Long subCount = this.serviceCategoryDao
				.getSubServCategoryCount(pdtCategoryId);

		if (subCount.intValue() > 0) {
			retMap = new HashMap<Integer, String>();
			retMap.put(pdtCategoryId,
					BussinessException.DEL_PDTCATE_ERROR_HASSUB);
			return retMap;
		}
		// 判断该category是否有product与之关联.
		Long productCount = this.servicePriceDao
				.getCountByCategoryId(pdtCategoryId);
		if (productCount.intValue() > 0) {
			retMap = new HashMap<Integer, String>();
			retMap.put(pdtCategoryId,
					BussinessException.DEL_PDTCATE_ERROR_HASPDTS);
			return retMap;
		}
		// 判断该Category是否已经Approved
		if (checkPropertyApproved(pdtCategoryId, "name",
				RequestApproveType.ServiceCategory.name())) {
			retMap = new HashMap<Integer, String>();
			retMap.put(pdtCategoryId,
					BussinessException.DEL_PDTCATE_ERROR_HASAPPREQ);
			return retMap;
		}
		if (checkPropertyApproved(pdtCategoryId, "status",
				RequestApproveType.ServiceCategory.name())) {
			retMap = new HashMap<Integer, String>();
			retMap.put(pdtCategoryId,
					BussinessException.DEL_PDTCATE_ERROR_HASAPPREQ);
			return retMap;
		}
		return null;
	}

	private Map<Integer, String> delPdtCategoryResult(Integer pdtCategoryId) {
		Map<Integer, String> retMap = null;
		// 判断该category是否有子级category.
		Long subCount = this.productCategoryDao
				.getSubPdtCategoryCount(pdtCategoryId);
		if (subCount.intValue() > 0) {
			retMap = new HashMap<Integer, String>();
			retMap.put(pdtCategoryId,
					BussinessException.DEL_PDTCATE_ERROR_HASSUB);
			return retMap;
		}
		// 判断该category是否有product与之关联.
		Long productCount = this.productPriceDao
				.getCountByCategoryId(pdtCategoryId);
		if (productCount.intValue() > 0) {
			retMap = new HashMap<Integer, String>();
			retMap.put(pdtCategoryId,
					BussinessException.DEL_PDTCATE_ERROR_HASPDTS);
			return retMap;
		}
		// 判断该Category是否已经Approved
		if (checkPropertyApproved(pdtCategoryId, "name",
				RequestApproveType.ProductCategory.name())) {
			retMap = new HashMap<Integer, String>();
			retMap.put(pdtCategoryId,
					BussinessException.DEL_PDTCATE_ERROR_HASAPPREQ);
			return retMap;
		}
		if (checkPropertyApproved(pdtCategoryId, "status",
				RequestApproveType.ProductCategory.name())) {
			retMap = new HashMap<Integer, String>();
			retMap.put(pdtCategoryId,
					BussinessException.DEL_PDTCATE_ERROR_HASAPPREQ);
			return retMap;
		}
		return null;
	}

	/**
	 * 判断Catalog是否能删除.
	 * 
	 * @param id
	 *            catalog表的主键.
	 * @return null则可以删除, 否则返回该id及不能删除的原因.
	 */
	private Map<Integer, String> delCatalogResult(Integer id) {
		Map<Integer, String> retMap = null;
		Catalog catalog = this.catalogDao.getById(id);
		if (catalog == null) {
			return null;
		}
		Long pdtCategoryCount = this.productCategoryDao
				.getCountByCatalogId(catalog.getCatalogId());
		if (pdtCategoryCount.intValue() > 0) {
			retMap = new HashMap<Integer, String>();
			retMap.put(id, BussinessException.DEL_CATALOG_ERROR_HASPDTCATE);
			return retMap;
		}
		boolean bApprove = checkPropertyApproved(id, "catalogName",
				RequestApproveType.Catalog.name())
				|| checkPropertyApproved(id, "status",
						RequestApproveType.Catalog.name());
		if (bApprove) {
			retMap = new HashMap<Integer, String>();
			retMap.put(id, BussinessException.DEL_CATALOG_ERROR_HASAPPREQ);
			return retMap;
		}
		return null;
	}

	public void rejectManageTask(final List<Integer> requestIds,
			final String rejectReason, final Integer userId) {
		if (requestIds != null && requestIds.size() > 0) {
			approveRequestDao.rejectRequest(rejectReason, requestIds, userId);
		}
	}

	public void approveManageTask(final List<Integer> requestIds,
			final Integer userId) {
		if (requestIds != null && requestIds.size() > 0) {
			for (Integer requestId : requestIds) {
				ApproveRequest approveRequest = approveRequestDao
						.get(requestId);
				approveRequest
						.setApproveStatus(RequestApproveStatusType.APPROVED
								.name());
				approveRequest.setProcessedBy(userId);
				approveRequest.setProcessDate(new Date());
				String requestApproveType = approveRequest.getTableName();
				List<ApproveRequestBean> approveRequestBeanList = approveRequestBeanDao
						.getUnapprovedRequestList(requestId, requestApproveType);
				if (approveRequestBeanList != null
						&& approveRequestBeanList.size() > 0) {
					Catalog catalog = null;
					ProductCategory category = null;
					ServiceCategory serviceCategory = null;
					Product product = null;
					com.genscript.gsscm.serv.entity.Service serv = null;
					ProductPrice productPrice = null;
					ServicePrice servicePrice = null;
					ServiceSubStepPrice serviceSubStepPrice = null;
					Integer objectId = approveRequest.getObjectId();
					if (RequestApproveType.Catalog.name().equals(
							requestApproveType)) {
						catalog = catalogDao.get(objectId);
					}
					if (RequestApproveType.ProductCategory.name().equals(
							requestApproveType)) {
						category = productCategoryDao.get(objectId);
					}
					if (RequestApproveType.ServiceCategory.name().equals(
							requestApproveType)) {
						serviceCategory = serviceCategoryDao.get(objectId);
					}
					if (RequestApproveType.Product.name().equals(
							requestApproveType)) {
						product = productDao.get(objectId);
					}
					if (RequestApproveType.Service.name().equals(
							requestApproveType)) {
						serv = serviceDao.get(objectId);
					}
					if (RequestApproveType.ProductPrice.name().equals(
							requestApproveType)) {
						productPrice = productPriceDao.get(objectId);
					}

					for (ApproveRequestBean approveRequestBean : approveRequestBeanList) {
						String columnName = approveRequestBean.getColumnName();
						String newVal = approveRequestBean.getNewValue();

						if (RequestApproveType.Catalog.name().equals(
								requestApproveType)) {
							if (columnName.equals("catalogName")) {
								catalog.setCatalogName(newVal);
							}
							if (columnName.equals("status")) {
								catalog.setStatus(newVal);
							}
							catalog.setModifiedBy(userId);
							catalog.setPublisher(userId);
							catalog.setPublishDate(new Date());
						}

						if (RequestApproveType.ProductCategory.name().equals(
								requestApproveType)) {
							if (columnName.equals("name")) {
								category.setName(newVal);
							}
							if (columnName.equals("status")) {
								category.setStatus(newVal);
							}
							category.setModifiedBy(userId);
						}
						if (RequestApproveType.ServiceCategory.name().equals(
								requestApproveType)) {
							if (columnName.equals("name")) {
								serviceCategory.setName(newVal);
							}
							if (columnName.equals("status")) {
								serviceCategory.setStatus(newVal);
							}
							serviceCategory.setModifiedBy(userId);
						}

						if (RequestApproveType.Product.name().equals(
								requestApproveType)) {
							if (columnName.equals("name")) {
								product.setName(newVal);
							}
							if (columnName.equals("status")) {
								product.setPublishDate(new Date());
								product.setStatus(newVal);
							}
							if (columnName.equals("unitCost")) {
								product.setUnitCost(BigDecimal.valueOf(Double
										.parseDouble(newVal)));
							}
							product.setModifiedBy(userId);
						}

						if (RequestApproveType.Service.name().equals(
								requestApproveType)) {
							if (columnName.equals("name")) {
								serv.setName(newVal);
							}
							if (columnName.equals("status")) {
								serv.setPublishDate(new Date());
								serv.setStatus(newVal);
							}
							serv.setModifiedBy(userId);
						}

						if (RequestApproveType.ProductPrice.name().equals(
								requestApproveType)) {
							if (columnName.equals("standardPrice")) {
								productPrice.setStandardPrice(Double
										.parseDouble(newVal));
							}
							productPrice.setModifiedBy(userId);
						}

						if (RequestApproveType.ServicePrice.name().equals(
								requestApproveType)) {
							if (RequestApproveType.SubServicePrice.name()
									.equals(columnName)) {
								serviceSubStepPrice = this.serviceSubStepPriceDao
										.getById(objectId);
								serviceSubStepPrice.setRetailPrice(Double
										.parseDouble(newVal));
								this.serviceSubStepPriceDao
										.save(serviceSubStepPrice);
							} else {
								servicePrice = this.servicePriceDao
										.getById(objectId);
								if (RequestApproveType.ServicePriceGroup.name()
										.equals(columnName)) {
									servicePrice.setPriceRuleGroup(Integer
											.valueOf(newVal));
									servicePrice.setStandardPrice(null);
								} else {
									servicePrice.setStandardPrice(Double
											.parseDouble(newVal));
									servicePrice.setPriceRuleGroup(null);
								}
								this.servicePriceDao.save(servicePrice);
							}
						}
					}
					if (RequestApproveType.Catalog.name().equals(
							requestApproveType)) {
						catalogDao.save(catalog);

					}
					if (RequestApproveType.ProductCategory.name().equals(
							requestApproveType)) {
						productCategoryDao.save(category);
					}
					if (RequestApproveType.ServiceCategory.name().equals(
							requestApproveType)) {
						serviceCategoryDao.save(serviceCategory);
					}
					if (RequestApproveType.Product.name().equals(
							requestApproveType)) {
						ProductExtendedInfo productExtendedInfo = this
								.getPdtExtendedInfo(product.getProductId());
						if (productExtendedInfo != null) {
							List<DropDownDTO> dropDownDTO = specDropDownListDao
									.getSpecDropDownList(SpecDropDownListName.PRODUCT_CLASSIFICATION
											.value());

							String clsIdStr = "";
							for (int i = 0; i < dropDownDTO.size(); i++) {
								if (dropDownDTO
										.get(i)
										.getId()
										.equals(product.getProductClsId()
												.toString())) {
									clsIdStr = dropDownDTO.get(i).getName();
								}
							}
							clsIdStr = clsIdStr.toLowerCase();
							if (clsIdStr.equals("chemicals")) {
								clsIdStr = "chemical";
							}
							String productCatalogNo = product.getCatalogNo();
							productCatalogNo = productCatalogNo.replaceAll(
									"[^0-9a-zA-Z]*[^0-9a-zA-Z]", "_");
							String productName = product.getName();
							productName = productName.replaceAll(
									"[^0-9a-zA-Z]*[^0-9a-zA-Z]", "_");
							String url = "";
							if (clsIdStr.equals("gene")) {
								url = "http://www.genscript.com/product_001/gene/code/"
										+ productCatalogNo
										+ "/category/gene/"
										+ productName + ".html";
							} else {
								url = "http://www.genscript.com/" + clsIdStr
										+ "/" + productCatalogNo + "-"
										+ productName + ".html";
							}

							productExtendedInfo.setUrl(url);
						}
						productDao.save(product);
					}
					if (RequestApproveType.Service.name().equals(
							requestApproveType)) {
						serviceDao.save(serv);
					}
					if (RequestApproveType.ProductPrice.name().equals(
							requestApproveType)) {
						productPriceDao.save(productPrice);
					}
				}
			}
		}
	}

	/**
	 * 获得一个产品的一段时间内， 按销售数量排名的销售员.
	 * 
	 * @param topCount
	 *            取前几位
	 * @param lastDays
	 *            最近的几天
	 * @return
	 */
	// TODO 现在提供的只是伪代码.
	public List<SalePersonDTO> getTopSalePerson(String catalogNo,
			Integer topCount, Integer lastDays) {
		List<SalePersonDTO> dtoList = new ArrayList<SalePersonDTO>();
		for (int i = 0; i < topCount; i++) {
			SalePersonDTO dto = new SalePersonDTO();
			dto.setSalesName("salesName" + i);
			dto.setSellQuality(120 - i);
			dtoList.add(dto);
		}
		return dtoList;
	}

	/**
	 * 获得一个Product的RoyaltyProduct(版权税)信息.
	 * 
	 * @param catalogNo
	 * @return
	 */
	public RoyaltyProductDTO getRoyaltyProduct(String catalogNo) {
		RoyaltyProductDTO dto = null;
		RoyaltyProduct royalty = this.royaltyProductDao
				.getRoyaltyProductByCataloNo(catalogNo);
		if (royalty != null) {
			dto = this.dozer.map(royalty, RoyaltyProductDTO.class);
			dto.setRoyaltyName(royaltyDao.getById(royalty.getRoyaltyId())
					.getName());
		}
		return dto;
	}

	public List<AnalysisReport> getPdtSalesReport(ProductReportSrchDTO srchDTO,
			String salesPeriodBasedOn) throws ParseException {
		List<AnalysisReport> dtoList = new ArrayList<AnalysisReport>();
		/*
		 * PdtSalesTotal pdtSalesTotal = null; List<PdtSalesReport> gridDataList
		 * = new ArrayList<PdtSalesReport>();
		 */
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		// System.out.println(srchDTO.getBeginDate());
		String fromDateStr = dateFmt.format(srchDTO.getBeginDate());
		String toDateStr = dateFmt.format(srchDTO.getEndDate());
		Date dEndDate = dateFmt.parse(toDateStr);
		Date fromDate = dateFmt.parse(fromDateStr);
		String catalogNo = srchDTO.getCatalogNO();
		int period = srchDTO.getPeriod();
		Date toDate = this.getToDate(srchDTO);
		boolean bLoop = true;
		for (; bLoop;) {
			String tempFromDate = dateFmt.format(fromDate);
			String tempToDate = dateFmt.format(toDate);
			// 实际发生到数据库的数据要比显示为DTO的大一天， 否则取不到<=后面的数据.
			String actToDate = dateFmt.format(DateUtils.defineDayBefore2Object(
					toDate, 1, DateUtils.C_DATE_PATTON_DEFAULT, new Date()));
			if (tempToDate.equals(srchDTO.getEndDate())) {
				bLoop = false;
				actToDate = tempToDate;
			} else if (!toDate.before(dEndDate)) {
				bLoop = false;
				tempToDate = toDateStr;
				actToDate = tempToDate;
			}
			AnalysisReport report = new AnalysisReport();
			report.setFromDate(tempFromDate);
			report.setToDate(tempToDate);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("catalogNo", catalogNo);
			map.put("fromDate", dateFmt.parse(tempFromDate));
			map.put("toDate", dateFmt.parse(actToDate));
			map.put("type", QuoteItemType.PRODUCT.value());
			System.out.println(salesPeriodBasedOn);
			if (salesPeriodBasedOn.equals("netSales")) {
				report.setVisit(this.orderItemDao.getNetSales(map).longValue());
			} else if (salesPeriodBasedOn.equals("grossSales")) {
				report.setVisit(this.orderItemDao.getTotalSales(map)
						.longValue());
			} else if (salesPeriodBasedOn.equals("lossOnReturn")) {
				report.setVisit(this.orderItemDao.getLossOnReturn(map)
						.longValue());
			} else if (salesPeriodBasedOn.equals("netUnitsSold")) {
				report.setVisit(this.orderItemDao.getNetUnitsSold(map));
			} else if (salesPeriodBasedOn.equals("grossUnitsSold")) {
				report.setVisit(this.orderItemDao.getGrossUnitsSold(map));
			} else if (salesPeriodBasedOn.equals("unitsReturned")) {
				report.setVisit(this.orderItemDao.getUnitsReturned(map));
			}
			// report.setRefered(accessAnalysisDao.getAnalysisRefer(custNo,
			// tempFromDate, actToDate));
			// report.setSearching(accessAnalysisDao.getAnalysisSearching(custNo,
			// tempFromDate, actToDate));
			// report.setPageView(accessAnalysisDao.getAnalysisPageViewed(custNo,
			// tempFromDate, actToDate));
			// report.setAvgViewTime(accessAnalysisDao.getAnalysisAvgPageStays(
			// custNo, tempFromDate, actToDate));
			dtoList.add(report);
			// 下次循环从本次截止日期的后一天开始.
			fromDate = DateUtils.defineDayBefore2Object(toDate, 1,
					DateUtils.C_DATE_PATTON_DEFAULT, new Date());
			if (period == 1) {
				toDate = DateUtils.defineDayBefore2Object(toDate, 1,
						DateUtils.C_DATE_PATTON_DEFAULT, new Date());
			} else if (period == 7) {// by week
				toDate = DateUtils.getWeekEndDay(fromDate);
			} else if (period == 30) {// by month
				toDate = DateUtils.getMonthEndDay(fromDate);
			} else if (period == 90) {// by quarter
				toDate = DateUtils.getQuarterEndDay(fromDate);
			} else if (period == 365) {// by year
				toDate = DateUtils.getYearEndDay(fromDate);
			} else {
				toDate = DateUtils.defineDayBefore2Object(toDate, period - 1,
						DateUtils.C_DATE_PATTON_DEFAULT, new Date());
			}
		}
		return dtoList;
	}

	/**
	 * 获得第一次循环的截止日期.
	 * 
	 * @param srchDTO
	 * @return
	 * @throws ParseException
	 */
	private Date getToDate(final ProductReportSrchDTO srchDTO)
			throws ParseException {
		Date toDate = null;
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		String fromDateStr = dateFmt.format(srchDTO.getBeginDate());
		String toDateStr = dateFmt.format(srchDTO.getEndDate());
		Date dEndDate = dateFmt.parse(toDateStr);
		Date fromDate = dateFmt.parse(fromDateStr);
		int period = srchDTO.getPeriod();
		if (period == 1) {
			toDate = fromDate;
		} else if (period == 7) {// by week
			toDate = DateUtils.getWeekEndDay(fromDate);
		} else if (period == 30) {// by month
			toDate = DateUtils.getMonthEndDay(fromDate);
		} else if (period == 90) {// by quarter
			toDate = DateUtils.getQuarterEndDay(fromDate);
		} else if (period == 365) {// by year
			toDate = DateUtils.getYearEndDay(fromDate);
		} else {
			toDate = DateUtils.defineDayBefore2Object(fromDate, period - 1,
					DateUtils.C_DATE_PATTON_DEFAULT, new Date());
		}
		if (!toDate.before(dEndDate)) {
			toDate = dEndDate;
		}
		return toDate;
	}

	@Transactional(readOnly = true)
	public List<Royalty> getPdtRoyaltyList(String name) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filter = new PropertyFilter("LIKES_name", name);
		filterList.add(filter);
		List<Royalty> royaltyList = royaltyDao.find(filterList);
		return royaltyList;
	}

	@Transactional(readOnly = true)
	public List<SearchItemDTO> getSearchItemInfo(
			final List<String> catalogNoList) {
		if (catalogNoList != null && catalogNoList.size() > 0) {
			List<SearchItemDTO> searchItemDTOList = new ArrayList<SearchItemDTO>();
			for (String catalogNo : catalogNoList) {
				Product product = productDao.findUniqueBy("catalogNo",
						catalogNo);
				SearchItemDTO searchItemDTO = new SearchItemDTO();
				searchItemDTO.setCatalogNo(catalogNo);
				if (product != null) {
					searchItemDTO.setPrefStorage(product.getPrefStorage());
					searchItemDTO.setPrefWarehouse(product.getPrefWarehouse());
					searchItemDTO.setClsId(product.getProductClsId());
					searchItemDTO.setClsName(productClassDao.get(
							product.getProductClsId()).getName());
					searchItemDTO.setCustomerInfo(product.getCustomerInfo());
					searchItemDTO.setFullDesc(product.getLongDesc());
					searchItemDTO.setDescription(product.getShortDesc());
					searchItemDTO.setScheduleShip(product.getLeadTime());
					searchItemDTO.setTaxStatus(product.getTaxable());
					searchItemDTO.setSellingNote(product.getSellingNote());
					if (product.getUnitCost() != null)
						searchItemDTO.setCost(product.getUnitCost()
								.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue());
					searchItemDTO.setName(product.getName());
					searchItemDTO.setUom(product.getUom());
				}
				searchItemDTOList.add(searchItemDTO);
			}
			return searchItemDTOList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public SearchItemDTO getSearchItemInfo(final String catalogNo) {
		if (StringUtils.isNotBlank(catalogNo)) {
			SearchItemDTO searchItemDTO = new SearchItemDTO();
			Product product = productDao.findUniqueBy("catalogNo", catalogNo);
			searchItemDTO.setCatalogNo(catalogNo);
			if (product != null) {
				searchItemDTO.setPrefStorage(product.getPrefStorage());
				searchItemDTO.setPrefWarehouse(product.getPrefWarehouse());
				searchItemDTO.setClsId(product.getProductClsId());
				searchItemDTO.setClsName(productClassDao.get(
						product.getProductClsId()).getName());
				searchItemDTO.setCustomerInfo(product.getCustomerInfo());
				searchItemDTO.setFullDesc(product.getLongDesc());
				searchItemDTO.setDescription(product.getShortDesc());
				searchItemDTO.setScheduleShip(product.getLeadTime());
				searchItemDTO.setTaxStatus(product.getTaxable());
				searchItemDTO.setSellingNote(product.getSellingNote());
				if (product.getUnitCost() != null)
					searchItemDTO.setCost(product.getUnitCost()
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue());
				searchItemDTO.setName(product.getName());
				searchItemDTO.setUom(product.getUom());
			}
			return searchItemDTO;
		}
		return null;
	}

	public ProductOfPdtcategoryBean getProductOfPdtCatagory(Integer productId,
			Integer categoryId) {
		return this.productOfPdtcategoryBeanDao
				.getProductOfPdtCategoryBeanByProductIdAndCategoryId(productId,
						categoryId);
	}

	@Transactional(readOnly = true)
	public Page<ProductOfPdtcategoryBean> getProductOfPdtCategoryList(
			Page<ProductOfPdtcategoryBean> page, List<PropertyFilter> filters) {
		return productOfPdtcategoryBeanDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<ProductOfPdtcategoryBean> getProductOfPdtCategoryList(
			Page<ProductOfPdtcategoryBean> page) {
		return productOfPdtcategoryBeanDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<ProductOfPdtcategoryBean> getProductOfPdtCategoryList(
			Page<ProductOfPdtcategoryBean> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return productOfPdtcategoryBeanDao.findPage(page, filterList);
	}

	// ----add by zhou gang 2011 6 21
	@Transactional(readOnly = true)
	public List<ProductOfPdtcategoryBean> getProductOfPdtcategoryLisst(
			Integer sessionCategoryId) {
		List<ProductOfPdtcategoryBean> productOfPdtcategoryBeanlist = new ArrayList<ProductOfPdtcategoryBean>();
		productOfPdtcategoryBeanlist = productOfPdtcategoryBeanDao
				.getAllcategorylist(sessionCategoryId);
		return productOfPdtcategoryBeanlist;
	}

	/**
	 * Copy from a catalog save as a new catalog.
	 * 同时复制ProductCategory及ProductPrice, 复制ServiceCategory及其ServicePrice.
	 * 
	 * @param catalogDTO
	 *            catalog from
	 * @param loginUserId
	 * @return
	 */
	public Catalog copyNewCatalog(CatalogDTO catalogDTO, Integer loginUserId) {
		Catalog dbCatalog = this.catalogDao.findUniqueBy("catalogId",
				catalogDTO.getCatalogId());
		if (dbCatalog != null) {
			this.catalogDao.getSession().evict(dbCatalog);
			throw new BussinessException(
					BussinessException.ERR_CATALOGID_UNIQUE);
		}
		Catalog catalog = this.catalogDao.getById(catalogDTO.getId());
		this.catalogDao.getSession().evict(catalog);
		String oldCatalogId = catalog.getCatalogId();
		String newCatalogId = catalogDTO.getCatalogId();
		catalog.setId(null);
		catalog.setDefaultFlag("N");
		// 以页面传过来的catalogId, catalogName为值, 不用原来catalog的.
		catalog.setCatalogId(newCatalogId);
		catalog.setCatalogName(catalogDTO.getCatalogName());
		catalog.setDescription(catalogDTO.getDescription());
		catalog.setStatus("INACTIVE");
		catalog.setCurrencyCode(catalogDTO.getCurrencyCode());
		catalog.setPublishZone(catalogDTO.getPublishZone());
		catalog.setPublisher(null);
		catalog.setPublishDate(null);
		catalog.setCreatedBy(loginUserId);
		catalog.setCreationDate(new Date());
		catalog.setModifiedBy(loginUserId);
		catalog.setModifyDate(new Date());
		this.catalogDao.save(catalog);
		// Copy catalog's product_category及其对应的product_category's product_price
		// 1.business util
		List<ProductCategory> pdtCategoryList = this.productCategoryDao
				.getPdtCategoryList(oldCatalogId, null, 1);

		/*
		 * List<ProductCategory> pdtCategoryList = this.productCategoryDao
		 * .getPdtCategoryListForCopyCatalog(oldCatalogId);
		 */
		if (pdtCategoryList != null) {
			for (int i = 0; i < pdtCategoryList.size(); i++) {
				ProductCategory pdtCategory = this.dozer.map(
						pdtCategoryList.get(i), ProductCategory.class);
				String[] categoryNO = pdtCategory.getCategoryNo().split(
						"_" + oldCatalogId);
				pdtCategory.setCategoryNo(categoryNO[0] + "_"
						+ catalog.getCatalogId());
				Integer category1Id = pdtCategory.getCategoryId();
				pdtCategory.setCategoryId(null);
				pdtCategory.setCatalogId(newCatalogId);
				pdtCategory.setCreatedBy(loginUserId);
				pdtCategory.setCreationDate(new Date());
				pdtCategory.setModifiedBy(loginUserId);
				pdtCategory.setModifyDate(new Date());
				this.productCategoryDao.save(pdtCategory);
				List<ProductCategory> pdtCategory2List = this.productCategoryDao
						.getPdtCategoryList(oldCatalogId, category1Id, 2);
				if (pdtCategory2List != null) {
					for (int i2 = 0; i2 < pdtCategory2List.size(); i2++) {
						ProductCategory pdtCategory2 = this.dozer
								.map(pdtCategory2List.get(i2),
										ProductCategory.class);
						String[] category2NO = pdtCategory2.getCategoryNo()
								.split("_" + oldCatalogId);
						pdtCategory2.setCategoryNo(category2NO[0] + "_"
								+ catalog.getCatalogId());
						Integer category2Id = pdtCategory2.getCategoryId();
						pdtCategory2.setCategoryId(null);
						pdtCategory2
								.setParentCatId(pdtCategory.getCategoryId());
						pdtCategory2.setCatalogId(newCatalogId);
						pdtCategory2.setCreatedBy(loginUserId);
						pdtCategory2.setCreationDate(new Date());
						pdtCategory2.setModifiedBy(loginUserId);
						pdtCategory2.setModifyDate(new Date());
						this.productCategoryDao.save(pdtCategory2);
						List<ProductCategory> pdtCategory3List = this.productCategoryDao
								.getPdtCategoryList(oldCatalogId, category2Id,
										3);
						if (pdtCategory3List != null) {
							for (int i3 = 0; i3 < pdtCategory3List.size(); i3++) {
								ProductCategory pdtCategory3 = this.dozer.map(
										pdtCategory3List.get(i3),
										ProductCategory.class);
								String[] category3NO = pdtCategory3
										.getCategoryNo().split(
												"_" + oldCatalogId);
								pdtCategory3.setCategoryNo(category3NO[0] + "_"
										+ catalog.getCatalogId());
								Integer category3Id = pdtCategory3
										.getCategoryId();
								pdtCategory3.setCategoryId(null);
								pdtCategory3.setParentCatId(pdtCategory2
										.getCategoryId());
								pdtCategory3.setCatalogId(newCatalogId);
								pdtCategory3.setCreatedBy(loginUserId);
								pdtCategory3.setCreationDate(new Date());
								pdtCategory3.setModifiedBy(loginUserId);
								pdtCategory3.setModifyDate(new Date());
								this.productCategoryDao.save(pdtCategory3);
								List<ProductPrice> productPriceList = this.productPriceDao
										.getProductPriceByCategoryIdAndCatalogId(
												oldCatalogId, category3Id);
								if (productPriceList != null) {
									for (ProductPrice productPrice : productPriceList) {
										ProductPrice pp = this.dozer.map(
												productPrice,
												ProductPrice.class);
										pp.setPriceId(null);
										pp.setCategoryId(pdtCategory3
												.getCategoryId());
										pp.setCatalogId(newCatalogId);
										pp.setCreatedBy(loginUserId);
										pp.setCreationDate(new Date());
										pp.setModifiedBy(loginUserId);
										pp.setModifyDate(new Date());
										this.productPriceDao.save(pp);
									}
								}
							}
						}

					}
				}
				/*
				 * this.attachCopyPdtCategory(newCatalogId, pdtCategory,
				 * loginUserId, map);
				 */
			}
		}

		List<ServiceCategory> serviceCategoryList = this.serviceCategoryDao
				.getServiceCategoryList(oldCatalogId, null, 1);

		/*
		 * List<ProductCategory> pdtCategoryList = this.productCategoryDao
		 * .getPdtCategoryListForCopyCatalog(oldCatalogId);
		 */
		if (serviceCategoryList != null) {
			for (int i = 0; i < serviceCategoryList.size(); i++) {
				ServiceCategory category = this.dozer.map(
						serviceCategoryList.get(i), ServiceCategory.class);
				String[] categoryNO = category.getCategoryNo().split(
						"_" + oldCatalogId);
				category.setCategoryNo(categoryNO[0] + "_"
						+ catalog.getCatalogId());
				Integer category1Id = category.getCategoryId();
				category.setCategoryId(null);
				category.setCatalogId(newCatalogId);
				category.setCreatedBy(loginUserId);
				category.setCreationDate(new Date());
				category.setModifiedBy(loginUserId);
				category.setModifyDate(new Date());
				this.serviceCategoryDao.save(category);
				List<ServiceCategory> category2List = this.serviceCategoryDao
						.getServiceCategoryList(oldCatalogId, category1Id, 2);
				if (category2List != null) {
					for (int i2 = 0; i2 < category2List.size(); i2++) {
						ServiceCategory category2 = this.dozer.map(
								category2List.get(i2), ServiceCategory.class);
						String[] category2NO = category2.getCategoryNo().split(
								"_" + oldCatalogId);
						category2.setCategoryNo(category2NO[0] + "_"
								+ catalog.getCatalogId());
						Integer category2Id = category2.getCategoryId();
						category2.setCategoryId(null);
						category2.setParentCatId(category.getCategoryId());
						category2.setCatalogId(newCatalogId);
						category2.setCreatedBy(loginUserId);
						category2.setCreationDate(new Date());
						category2.setModifiedBy(loginUserId);
						category2.setModifyDate(new Date());
						this.serviceCategoryDao.save(category2);
						List<ServiceCategory> category3List = this.serviceCategoryDao
								.getServiceCategoryList(oldCatalogId,
										category2Id, 3);
						if (category3List != null) {
							for (int i3 = 0; i3 < category3List.size(); i3++) {
								ServiceCategory category3 = this.dozer.map(
										category3List.get(i3),
										ServiceCategory.class);
								String[] category3NO = category3
										.getCategoryNo().split(
												"_" + oldCatalogId);
								category3.setCategoryNo(category3NO[0] + "_"
										+ catalog.getCatalogId());
								Integer category3Id = category3.getCategoryId();
								category3.setCategoryId(null);
								category3.setParentCatId(category2
										.getCategoryId());
								category3.setCatalogId(newCatalogId);
								category3.setCreatedBy(loginUserId);
								category3.setCreationDate(new Date());
								category3.setModifiedBy(loginUserId);
								category3.setModifyDate(new Date());
								this.serviceCategoryDao.save(category3);
								List<ServicePrice> servicePriceList = this.servicePriceDao
										.getServicePriceByCategoryIdAndCatalogId(
												oldCatalogId, category3Id);
								if (servicePriceList != null) {
									for (ServicePrice servicePrice : servicePriceList) {
										ServicePrice pp = this.dozer.map(
												servicePrice,
												ServicePrice.class);
										pp.setPriceId(null);
										pp.setCategoryId(category3
												.getCategoryId());
										pp.setCatalogId(newCatalogId);
										pp.setCreatedBy(loginUserId);
										pp.setCreationDate(new Date());
										pp.setModifiedBy(loginUserId);
										pp.setModifyDate(new Date());
										this.servicePriceDao.save(pp);
									}
								}
							}
						}

					}
				}
				/*
				 * this.attachCopyPdtCategory(newCatalogId, pdtCategory,
				 * loginUserId, map);
				 */
			}
		}
		/*
		 * // Copy catalog's service_category及其对应的service_category's
		 * servcie_price List<ServiceCategory> servCategoryList =
		 * this.serviceCategoryDao
		 * .getServCategoryListForCopyCatalog(oldCatalogId); if
		 * (servCategoryList != null) { Map<String, Integer> map = new
		 * HashMap<String, Integer>(); for (ServiceCategory servCategory :
		 * servCategoryList) {
		 * servCategory.setCategoryNo(servCategory.getCategoryNo
		 * ()+"_"+catalog.getCatalogId());
		 * this.attachCopyServCategory(newCatalogId, servCategory, loginUserId,
		 * map); } }
		 */
		return catalog;
	}

	/**
	 * Copy catalog's product_category及其对应的product_category's product_price
	 * 
	 * @param newCatalogId
	 * @param pdtCategory
	 * @param loginUserId
	 * @return
	 */
	private ProductCategory attachCopyPdtCategory(String newCatalogId,
			ProductCategory pdtCategory, Integer loginUserId,
			Map<String, Integer> map) {
		this.productCategoryDao.getSession().evict(pdtCategory);
		Integer oldCategoryId = pdtCategory.getCategoryId();
		String key = "Id" + oldCategoryId;
		if (map.containsKey(key)) {
			return null;
		}
		map.put(key, oldCategoryId);
		/*
		 * if (pdtCategory.getParentCatId() != null &&
		 * pdtCategory.getParentCatId().intValue() != 0) { ProductCategory
		 * pCategory = this.productCategoryDao
		 * .getById(pdtCategory.getParentCatId()); pCategory.setCategoryNo("");
		 * pdtCategory
		 * .setCategoryNo(oldCatalogId+"_"+pdtCategory.getCategoryNo()
		 * );//须将categoryNo改变 oldCatalogId没有值，须要从别处读来。 ProductCategory
		 * pNewCategory = this.attachCopyPdtCategory( newCatalogId, pCategory,
		 * loginUserId, map); if (pNewCategory != null) {
		 * pdtCategory.setParentCatId(pNewCategory.getCategoryId()); } }
		 */
		/*
		 * if (pdtCategory.getPreviousCatId() != null &&
		 * pdtCategory.getPreviousCatId().intValue() != 0) { ProductCategory
		 * prevCategory = this.productCategoryDao
		 * .getById(pdtCategory.getPreviousCatId());
		 * pdtCategory.setCategoryNo(oldCatalogId
		 * +"_"+pdtCategory.getCategoryNo());//须将categoryNo改变
		 * oldCatalogId没有值，须要从别处读来。 ProductCategory pNewCategory =
		 * this.attachCopyPdtCategory( newCatalogId, prevCategory, loginUserId,
		 * map); if (pNewCategory != null) {
		 * pdtCategory.setPreviousCatId(pNewCategory.getCategoryId()); } }
		 */
		pdtCategory.setCategoryId(null);
		pdtCategory.setCatalogId(newCatalogId);
		pdtCategory.setCreatedBy(loginUserId);
		pdtCategory.setCreationDate(new Date());
		pdtCategory.setModifiedBy(loginUserId);
		pdtCategory.setModifyDate(new Date());
		this.productCategoryDao.save(pdtCategory);
		this.productCategoryDao.getSession().evict(pdtCategory);
		// Copy catalog's product_category其对应的product_price
		this.attachCopyPdtPrice(oldCategoryId, pdtCategory.getCategoryId(),
				newCatalogId, loginUserId);
		key = "Id" + pdtCategory.getCategoryId();
		map.put(key, pdtCategory.getCategoryId());
		return pdtCategory;
	}

	/**
	 * Copy catalog's product_category其对应的product_price
	 * 
	 * @param oldCategoryId
	 *            原来ProductCategory的categoryId.
	 * @param newCategoryId
	 * @param newCatalogId
	 * @param loginUserId
	 */
	private void attachCopyPdtPrice(Integer oldCategoryId,
			Integer newCategoryId, String newCatalogId, Integer loginUserId) {
		List<ProductPrice> pdtPriceList = this.productPriceDao
				.getPdtPriceListByCategoryId(oldCategoryId);
		System.out.println(pdtPriceList.size());
		for (ProductPrice pdtPrice : pdtPriceList) {
			this.productPriceDao.getSession().evict(pdtPrice);
			pdtPrice.setPriceId(null);
			pdtPrice.setCatalogId(newCatalogId);
			pdtPrice.setCategoryId(newCategoryId);
			pdtPrice.setCreatedBy(loginUserId);
			pdtPrice.setCreationDate(new Date());
			pdtPrice.setModifiedBy(loginUserId);
			pdtPrice.setModifyDate(new Date());
			System.out.println(pdtPrice.toString());
			System.out.println(pdtPrice.getProductId());
			this.productPriceDao.save(pdtPrice);
		}
	}

	/**
	 * Copy catalog's service_category及其对应的service_price
	 * 
	 * @param newCatalogId
	 * @param servCategory
	 * @param loginUserId
	 * @return
	 */
	private ServiceCategory attachCopyServCategory(String newCatalogId,
			ServiceCategory servCategory, Integer loginUserId,
			Map<String, Integer> map) {
		this.serviceCategoryDao.getSession().evict(servCategory);
		Integer oldCategoryId = servCategory.getCategoryId();
		if (map.containsKey("Id" + oldCategoryId)) {
			return null;
		} else {
			map.put("Id" + oldCategoryId, oldCategoryId);
		}
		if (servCategory.getParentCatId() != null
				&& servCategory.getParentCatId().intValue() != 0) {
			ServiceCategory pCategory = this.serviceCategoryDao
					.getById(servCategory.getParentCatId());
			ServiceCategory pNewCategory = this.attachCopyServCategory(
					newCatalogId, pCategory, loginUserId, map);
			if (pNewCategory != null) {
				servCategory.setParentCatId(pNewCategory.getCategoryId());
			}
		}
		if (servCategory.getPreviousCatId() != null
				&& servCategory.getPreviousCatId().intValue() != 0) {
			ServiceCategory prevCategory = this.serviceCategoryDao
					.getById(servCategory.getPreviousCatId());
			ServiceCategory pNewCategory = this.attachCopyServCategory(
					newCatalogId, prevCategory, loginUserId, map);
			if (pNewCategory != null) {
				servCategory.setPreviousCatId(pNewCategory.getCategoryId());
			}
		}
		servCategory.setCategoryId(null);
		servCategory.setCatalogId(newCatalogId);
		servCategory.setCreatedBy(loginUserId);
		servCategory.setCreationDate(new Date());
		servCategory.setModifiedBy(loginUserId);
		servCategory.setModifyDate(new Date());
		this.serviceCategoryDao.save(servCategory);
		// Copy catalog's service_category其对应的service_price
		this.attachCopyServPrice(oldCategoryId, servCategory.getCategoryId(),
				newCatalogId, loginUserId);
		return servCategory;
	}

	/**
	 * Copy catalog's service_category其对应的service_price
	 * 
	 * @param oldCategoryId
	 * @param newCategoryId
	 * @param catalogId
	 * @param loginUserId
	 */
	private void attachCopyServPrice(Integer oldCategoryId,
			Integer newCategoryId, String catalogId, Integer loginUserId) {
		List<ServicePrice> servPriceList = this.servicePriceDao
				.getServPriceListByCategoryId(oldCategoryId);
		for (ServicePrice servPrice : servPriceList) {
			this.servicePriceDao.getSession().evict(servPrice);
			servPrice.setPriceId(null);
			servPrice.setCatalogId(catalogId);
			servPrice.setCategoryId(newCategoryId);
			servPrice.setCreatedBy(loginUserId);
			servPrice.setCreationDate(new Date());
			servPrice.setModifiedBy(loginUserId);
			servPrice.setModifyDate(new Date());
			this.servicePriceDao.save(servPrice);
		}
	}

	/*
	 * 查询与product type对应该的表数据
	 * 
	 * @param productId
	 */
	public Antibody getAntibodyDetail(Integer productId) {

		return antibodyDao.getById(productId);
	}

	private void attachPdtOfAntibody(Integer productId, Antibody antibody,
			Integer userId) {
		System.out.println(antibody.getProductId() + "><<<<<<<<<<<,");
		if (antibody != null) {
			Date now = new Date();
			antibody.setProductId(productId);
			if (antibody.getCreatedBy() == null) {
				antibody.setCreatedBy(userId);
				antibody.setCreationDate(now);
			}
			antibody.setModifiedBy(userId);
			antibody.setModifyDate(now);
			this.antibodyDao.save(antibody);
			System.out.println("++++++++++++++++++++++++++++++++++++=");
		}
	}

	public Enzyme getEnzymeDetail(Integer productId) {
		return this.enzymeDao.getById(productId);
	}

	private void attachPdtOfEnzyme(Integer productId, Enzyme enzyme,
			Integer userId) {
		if (enzyme != null) {
			Date now = new Date();
			enzyme.setProductId(productId);
			if (enzyme.getCreatedBy() == null) {
				enzyme.setCreatedBy(userId);
				enzyme.setCreationDate(now);
			}
			enzyme.setModifiedBy(userId);
			enzyme.setModifyDate(now);
			this.enzymeDao.save(enzyme);
		}
	}

	public Chemical getChemicalDetail(Integer productId) {
		return this.chemicalDao.getById(productId);
	}

	private void attachPdtOfChemical(Integer productId, Chemical chemical,
			Integer userId) {
		if (chemical != null) {
			Date now = new Date();
			chemical.setProductId(productId);
			if (chemical.getCreatedBy() == null) {
				chemical.setCreatedBy(userId);
				chemical.setCreationDate(now);
			}
			chemical.setModifiedBy(userId);
			chemical.setModifyDate(now);
			this.chemicalDao.save(chemical);
		}
	}

	public Kit getKitDetail(Integer productId) {
		return this.kitDao.getById(productId);
	}

	public SDVector getSDVector(Integer productId) {
		return this.sdvectorDao.getById(productId);
	}

	private void attachPdtOfKit(Integer productId, Kit kit, Integer userId) {
		if (kit != null) {
			Date now = new Date();
			kit.setProductId(productId);
			if (kit.getCreatedBy() == null) {
				kit.setCreatedBy(userId);
				kit.setCreationDate(now);
			}
			kit.setModifiedBy(userId);
			kit.setModifyDate(now);
			this.kitDao.save(kit);
		}
	}

	public Molecule getMoleculeDetail(Integer productId) {
		return this.moleculeDao.getById(productId);
	}

	private void attachPdtOfMolecule(Integer productId, Molecule molecule,
			Integer userId) {
		if (molecule != null) {
			Date now = new Date();
			molecule.setProductId(productId);
			if (molecule.getCreatedBy() == null) {
				molecule.setCreatedBy(userId);
				molecule.setCreationDate(now);
			}
			molecule.setModifiedBy(userId);
			molecule.setModifyDate(now);
			this.moleculeDao.save(molecule);
		}
	}

	public Oligo getOligoDetail(Integer productId) {
		return this.oligoDao.getById(productId);
	}

	private void attachPdtOfOligo(Integer productId, Oligo oligo, Integer userId) {
		if (oligo != null) {
			Date now = new Date();
			oligo.setProductId(productId);
			if (oligo.getCreatedBy() == null) {
				oligo.setCreatedBy(userId);
				oligo.setCreationDate(now);
			}
			oligo.setModifiedBy(userId);
			oligo.setModifyDate(now);
			this.oligoDao.save(oligo);
		}
	}

	public Peptide getPeptideDetail(Integer productId) {
		return this.peptideDao.getById(productId);
	}

	private void attachPdtOfPeptide(Integer productId, Peptide peptide,
			Integer userId) {
		if (peptide != null) {
			Date now = new Date();
			peptide.setProductId(productId);
			if (peptide.getCreatedBy() == null) {
				peptide.setCreatedBy(userId);
				peptide.setCreationDate(now);
			}
			peptide.setModifiedBy(userId);
			peptide.setModifyDate(now);
			this.peptideDao.save(peptide);
		}
	}

	public Protein getProteinDetail(Integer productId) {
		return this.proteinDao.getById(productId);
	}

	private void attachPdtOfProtein(Integer productId, Protein protein,
			Integer userId) {
		if (protein != null) {
			Date now = new Date();
			protein.setProductId(productId);
			if (protein.getCreatedBy() == null) {
				protein.setCreatedBy(userId);
				protein.setCreationDate(now);
			}
			protein.setModifiedBy(userId);
			protein.setModifyDate(now);
			this.proteinDao.save(protein);
		}
	}

	public Gene getGeneDetail(Integer productId) {
		return this.geneDao.getById(productId);
	}

	private void attachPdtOfGene(Integer productId, Gene gene, Integer userId) {
		if (gene != null) {
			Date now = new Date();
			gene.setProductId(productId);
			if (gene.getCreatedBy() == null) {
				gene.setCreatedBy(userId);
				gene.setCreationDate(now);
			}
			gene.setModifiedBy(userId);
			gene.setModifyDate(now);
			this.geneDao.save(gene);
		}
	}

	// 查询与product type对应该的表数据 结束

	/**
	 * 批量删除product. 成功或失败.
	 * 
	 * @param delServiceListId
	 */
	@Transactional(readOnly = true)
	public void delProductList(List<Integer> delServiceListId) {
		if (delServiceListId != null) {
			for (Integer id : delServiceListId) {
				Product product = productDao.getById(id);
				productDao.getSession().evict(product);
				product.setStatus("INACTIVE");
				// productDao.save(product);

			}
		}
	}

	/**
	 * 批量Approved删除product. 成功或失败.
	 * 
	 * @param delServiceListId
	 */
	@Transactional(readOnly = true)
	public void delApprovedProductList(List<Integer> delServiceListId) {
		if (delServiceListId != null) {
			for (Integer id : delServiceListId) {

			}
		}
	}

	/*
	 * 获得Service的more info
	 * 
	 * @PARAM productId @return
	 */
	public ProductExtendedInfo getPdtExtendedInfo(Integer productId) {
		ProductExtendedInfo pdtExtInfo = pdtExtInfoDao.getById(productId);
		return pdtExtInfo;
	}

	/*
	 * 保存或修改,删除prdouct的more info @param productExtendedInfo @return
	 */
	private void attachProductMoreInfo(ProductExtendedInfo pdtExtInfo,
			Integer userId, Integer productId) {
		if (pdtExtInfo != null) {
			pdtExtInfo.setProductId(productId);
			this.pdtExtInfoDao.save(pdtExtInfo);
		}
		/*
		 * if (ids != null) { this.productDocumentDao.delPdtDocList(ids); } if
		 * (productDocumentList != null) { for (Documents pdtDoc :
		 * productDocumentList) { Date now = new Date();
		 * pdtDoc.setProductId(productId); if (pdtDoc.getCreatedBy() == null) {
		 * pdtDoc.setCreatedBy(userId); pdtDoc.setCreationDate(now); }
		 * pdtDoc.setModifiedBy(userId); pdtDoc.setModifyDate(now);
		 * this.productDocumentDao.save(pdtDoc); } }
		 */
	}

	/*
	 * 获得product vendor_supplier detail
	 */
	public VendorProduct getVendorProductDetail(Integer id) {
		VendorProduct vendorProduct = this.vendorProductDao.getById(id);
		return vendorProduct;
	}

	/*
	 * 获得product more_info product_files
	 * 
	 * 
	 * public List<Documents> getProductDocument(Integer productId) { return
	 * productDocumentDao.findBy("productId", productId); }
	 */

	/*
	 * 获得一个Product的各类销售统计情况.
	 */
	public ProductServiceSaleDTO getProductSale(String catalogNo,
			Date fromDate, Date toDate) throws ParseException {
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		String fromDateStr = dateFmt.format(fromDate);
		String toDateStr = dateFmt.format(toDate);
		Date dEndDate = dateFmt.parse(toDateStr);
		Date fromDateS = dateFmt.parse(fromDateStr);
		return orderItemDao.getProductSale(catalogNo, fromDateS, dEndDate,
				QuoteItemType.PRODUCT.value());
	}

	/*
	 * 获取Sales Person(s) Selling this Product
	 */
	public List<SalesRankingDTO> getSalesRanking(String catalogNo, Integer top,
			Date fromDate, Date toDate) {
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		String fromDateStr = dateFmt.format(fromDate);
		String toDateStr = dateFmt.format(toDate);
		return orderItemDao.getSalesRanking(catalogNo, top, fromDateStr,
				toDateStr, QuoteItemType.PRODUCT.value());
	}

	/**
	 * 获得所有的Product Class list.
	 * 
	 * @return
	 * @author wangsf
	 * @serialData 2010-10-19 13:40
	 */
	@Transactional(readOnly = true)
	public List<ProductClass> getAllProductClass() {
		return this.productClassDao.findAll(Page.ASC, "name");
	}

	/**
	 * 获得具体Product Class
	 * 
	 * @param clsId
	 * @return
	 * @author wangsf
	 * @serialData 2010-10-19 14:52
	 */
	public ProductClass getProductClass(Integer clsId) {
		return this.productClassDao.get(clsId);
	}

	@Transactional(readOnly = true)
	public List<ProductCategory> getPdtCategoryListByCatalog(String catalogId) {
		return productCategoryDao.getPdtCategoryListForCopyCatalog(catalogId);
	}

	@Transactional(readOnly = true)
	public List<ProductCategory> getPdtCategoryListByCatalogAndCategory(
			String catalogId, Integer parentCatId, Integer categoryLevel) {
		return productCategoryDao.getPdtCategoryList(catalogId, parentCatId,
				categoryLevel);
	}

	@Transactional(readOnly = true)
	public List<Catalog> getFilterCatalogList(String catalogType,
			List<String> catalogIdList) {
		return catalogDao.getFilterCatalogList(catalogType, catalogIdList);
	}

	@Transactional(readOnly = true)
	public ProductPrice getProductPrice(String catalogId, Integer categoryId,
			Integer productId) {
		ProductPrice productPrice = productPriceDao.getProductPrice(catalogId,
				categoryId, productId);
		productPriceDao.getSession().evict(productPrice);
		return productPrice;
	}

	@Transactional(readOnly = true)
	public Catalog getCatalogByCatalogId(String catalogId) {
		return catalogDao.getCatalogByCatalogId(catalogId);
	}

	@Transactional(readOnly = true)
	public ProductSpecialPrice getProductSpecialPriceById(Integer id) {
		return productSpecialPriceDao.getById(id);
	}

	@Transactional(readOnly = true)
	public Double getUnitPriceByBaseCatalog(String catalogId, Integer productId) {
		return productInCategoryBeanDao.getUnitPriceByBaseCatalog(catalogId,
				productId);
	}

	public List<MarketingStaffDTO> searchMarketingStaffByGroup(Integer group) {
		List<MarketingStaffDTO> dtoList = new ArrayList<MarketingStaffDTO>();
		List<MarketingStaff> list = this.marketingStaffDao.findBy(
				"marketingGroup", group);
		for (MarketingStaff mark : list) {
			MarketingStaffDTO dto = new MarketingStaffDTO();
			dto.setMarketingStaff(mark);
			User user = this.userDao.getById(mark.getStaffId());
			if (user != null) {
				dto.setUserName(user.getLoginName());
			}
			dtoList.add(dto);
		}
		return dtoList;
	}

	public List<MarketingGroup> searchAllMarketingGroup() {
		return this.marketingGroupDao.getAll();
	}

	/**
	 * 查找一个产品的运输条件(ShipCondition)
	 * 
	 * @param catalogNo
	 * @return
	 * @author wangsf
	 * @since 2010-12-16
	 */
	public ShipCondition queryShipCondition(String catalogNo) {
		Product product = productDao.getProductByCatalogNo(catalogNo);
		ShipCondition shipCondition = this.shipConditionDao.get(product
				.getProductId());
		return shipCondition;
	}

	/**
	 * 查找一个产品的存储条件(StorageCondition)
	 * 
	 * @param catalogNo
	 * @return
	 * @author wangsf
	 * @since 2010-12-16
	 */
	public StorageCondition queryStorageCondition(String catalogNo) {
		Product product = productDao.getProductByCatalogNo(catalogNo);
		StorageCondition storageCondition = this.storageConditionDao
				.get(product.getProductId());
		return storageCondition;
	}

	@Transactional(readOnly = true)
	public Page<Product> getGiftableCatalog(Page<Product> page) {
		return productDao.getGiftableCatalog(page);
	}

	@Transactional(readOnly = true)
	public Page<ProductCategory> getAllProductCategory(
			Page<ProductCategory> page) {
		return productCategoryDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public List<CatalogNORules> getCatalogNoRules(String type) {
		return this.catalogNORulesDao.findBy("itemType", type);
	}

	@Transactional(readOnly = true)
	public List<CatalogNORules> getCatalogId(Integer categoryId, String type) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();

		PropertyFilter categoryIdFilter = new PropertyFilter("EQI_categoryId",
				categoryId);
		PropertyFilter typeFilter = new PropertyFilter("EQS_itemType", type);
		filters.add(categoryIdFilter);
		filters.add(typeFilter);
		return this.catalogNORulesDao.find(filters);
	}

	public String saveCatalogNoRules(Integer id) {
		System.out.println(">>>>>>>>>>>>>>." + id);
		CatalogNORules catalogNoRules = this.catalogNORulesDao.getById(id);
		this.catalogNORulesDao.getSession().evict(catalogNoRules);
		Integer currentSeq = Integer.valueOf(catalogNoRules.getCurrentSeq());
		boolean isFalse = true;
		while (isFalse) {
			String seq = currentSeq.toString();
			if (seq.length() < catalogNoRules.getCurrentSeq().length()) {
				int len = catalogNoRules.getCurrentSeq().length()
						- seq.length();
				for (int i = 0; i < len; i++) {
					seq = "0" + seq;
				}
			}
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<" + seq);
			String no = catalogNoRules.getPrefix() + seq;
			if (catalogNoRules.getItemType()
					.equals(CatalogType.PRODUCT.value())) {
				System.out.println("<<<<<<<<<<<<<<<>>>>>>>>>>>>>>><<<<<<<,"
						+ no);
				List<Product> product = this.productDao.findBy("catalogNo", no);
				if (product == null || product.isEmpty()) {
					System.out.println("<><><><><><><><," + seq);
					catalogNoRules.setCurrentSeq(seq);
					this.catalogNORulesDao.save(catalogNoRules);
					this.catalogNORulesDao.flush();
					System.out.println("<><><><><><><><>," + seq);
					isFalse = false;
				} else {
					currentSeq++;
				}
				this.productDao.getSession().evict(product);
			} else if (catalogNoRules.getItemType().equals(
					CatalogType.SERVICE.value())) {
				List<com.genscript.gsscm.serv.entity.Service> serv = this.serviceDao
						.findBy("catalogNo", no);
				if (serv == null || serv.isEmpty()) {
					catalogNoRules.setCurrentSeq(seq);
					this.catalogNORulesDao.save(catalogNoRules);
					this.catalogNORulesDao.flush();
					isFalse = false;
				} else {
					currentSeq++;
				}
				this.productDao.getSession().evict(serv);
			} else {
				isFalse = false;
			}
		}
		return catalogNoRules.getCurrentSeq();
	}

	/**
	 * 判断是否是gift item
	 * 
	 * @param catalogNo
	 * @return
	 */
	public boolean isGiftProduct(String catalogNo) {
		Product product = this.productDao.getProductByCatalogNo(catalogNo);
		if (product == null) {
			return false;
		}
		if ("Y".equalsIgnoreCase(product.getGiftFlag())) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * public boolean checkCatalogNo(String catalogNo,String type){
	 * if(type.equals(CatalogType.PRODUCT.value())){ List<Product> product =
	 * this.productDao.findBy("catalogNo", catalogNo);
	 * if(product==null||product.isEmpty()){ return true; }else{ return false; }
	 * }else if(type.equals(CatalogType.SERVICE.value())){ List<Product> product
	 * = this.productDao.findBy("catalogNo", catalogNo);
	 * if(product==null||product.isEmpty()){ return true; }else{ return false; }
	 * } return false; }
	 */

	/*
	 * 新增product document 首先 需要取得oldflag 的值 说明： zhougang 2011 9 8 // oldflag 1:
	 * bigfile and smallfile ===old oldflag 2:bigfile change to new and
	 * smallfile ==oldfile oldflag 3:bigfile is old and smallfile ==new oldflag
	 * 4 bigfile and smallfile are new
	 */
	public Integer saveProductDocument(Documents documents,
			List<Integer> productIdList, List<Integer> delProductIdList,
			Integer userId) {
		DocumentPro document = this.dozer.map(documents, DocumentPro.class);
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		if (document.getDocId() != null) {
			Documents doc = new Documents();
			doc = this.documentDao.getById(document.getDocId());
			this.documentDao.getSession().evict(doc);
			if (((doc.getImageFilePath() == null && document.getImageFilePath() != null) || !doc
					.getImageFilePath().equals(document.getImageFilePath()))
					|| ((doc.getDocFilePath() == null && document
							.getDocFilePath() != null) || !doc.getDocFilePath()
							.equals(document.getDocFilePath()))
					|| !doc.getDescription().equals(document.getDescription())
					|| !doc.getDocName().equals(document.getDocName())
					|| !doc.getDocType().equals(document.getDocType())
					|| !doc.getInternalFlag()
							.equals(document.getInternalFlag())
					|| !doc.getNote().equals(document.getNote())
					|| !doc.getValidateFlag()
							.equals(document.getValidateFlag())) {
				if (document.getImageFilePath() != null
						&& document.getImageFilePath().equals("")) {
					document.setImageFilePath(null);
				}
				if (document.getDocFilePath() != null
						&& document.getDocFilePath().equals("")) {
					document.setDocFilePath(null);
				}
				String oldflag = "";
				String olddocName = "";
				String oldImageName = ""; // 默认情况下 img 为空的
				String newdocName = "";
				String newImageName = "";
				if (document != null && doc != null) {
					newdocName = document.getDocFileName();
					newImageName = document.getImageFileName();
					oldflag = doc.getOldFlag();
					olddocName = doc.getDocFileName();
					oldImageName = doc.getImageFileName();
					if (oldflag != null && !"".equals(oldflag)) {
						if (oldflag.equals("1")) {// 第一次修改 1234
							if (newdocName != null && !"".equals(newdocName)) {// 旧文件可能被修改了
								if (newdocName.equals(olddocName)
										&& (newImageName != null && !""
												.equals(newImageName))) {
									document.setOldFlag("3");
								} else if (newdocName.equals(olddocName)
										&& (newImageName == null || ""
												.equals(newImageName))) {
									document.setOldFlag("1");

								} else if (!newdocName.equals(olddocName)
										&& (newImageName != null && !""
												.equals(newImageName))) {
									document.setOldFlag("4");
								} else if (!newdocName.equals(olddocName)
										&& (newImageName == null || ""
												.equals(newImageName))) {
									document.setOldFlag("2");
								}
							} else if (newdocName == null
									&& "".equals(newdocName)) {// 旧文件被删除了
								if ((newImageName == null && ""
										.equals(newImageName))) {
									document.setOldFlag("2");
								} else {
									document.setOldFlag("4");
								}
							}

						} else if (oldflag.equals("2")) {// 第二次修改之后 再进来了 img
															// 依旧是空值
							if ((newImageName == null && ""
									.equals(newImageName))) {
								document.setOldFlag("2");
							} else {
								document.setOldFlag("4");// 960_20051003104712.doc
							}
						} else if (oldflag.equals("3")) {// doc 是旧值 img 不是空值了
							if (newdocName == null || "".equals(newdocName)) {
								document.setOldFlag("4");
							} else {
								if (newdocName.equals(olddocName)) {
									document.setOldFlag("3");
								} else {
									document.setOldFlag("4");
								}
							}
						} else if (oldflag.equals("4")) {
							document.setOldFlag("4");
						}

					}
				}
				DocumentVersion ver = this.dozer
						.map(doc, DocumentVersion.class);
				document.setVersion(format.format(now));
				this.documentVersionDao.save(ver);
				if (document.getCreatedBy() == null) {
					document.setCreatedBy(userId);
					document.setCreationDate(now);
				}
				document.setModifiedBy(userId);
				document.setModifyDate(now);
				if (document.getOldFlag().equals("1")
						|| document.getOldFlag().equals("3")) {
					document.setValidateFlag("0");
				} else {
					document.setValidateFlag("1");
				}
				this.documentdao.save(document);
			}
		} else {
			if (document.getCreatedBy() == null) {
				document.setCreatedBy(userId);
				document.setCreationDate(now);

			}
			document.setOldFlag("4");
			document.setModifiedBy(userId);
			document.setModifyDate(now);
			document.setValidateFlag("1");
			document.setVersion(format.format(now));
			this.documentdao.save(document);
		}
		if (delProductIdList != null && !delProductIdList.isEmpty()) {

			this.productDocumentDao.delPdtDocList(delProductIdList,
					document.getDocId());
		}
		if (productIdList != null && !productIdList.isEmpty()) {
			for (Integer id : productIdList) {
				ProductDocuments pd = new ProductDocuments();
				if (pd.getCreatedBy() == null) {
					pd.setCreatedBy(userId);
					pd.setCreationDate(now);
				}
				pd.setModifiedBy(userId);
				pd.setModifyDate(now);
				pd.setDocId(document.getDocId());
				pd.setProductId(Integer.valueOf(id));
				this.productDocumentDao.save(pd);
			}
		}
		Integer id = document.getDocId();
		return id;
	}

	/*
	 * 查询document详细信息
	 */
	public Documents getDocument(Integer docId) {
		return this.documentDao.getById(docId);
	}

	/*
	 * 查询所有 document
	 */
	public Page<Documents> searchAllDocument(Page<Documents> page,
			String catalogNo, String name, String type, String dec) {
		return this.documentDao.searchDocumentsPage(page, catalogNo, name,
				type, dec);
	}

	/*
	 * 查询product中的document;
	 */
	public List<Documents> searchDocumentByProductId(Integer productId) {
		return this.documentDao.searchProductDocuments(productId);
	}

	/*
	 * 查询document中的product;
	 */
	public List<Product> searchProductByDocumentId(Integer docId) {
		return this.productDao.searchDocumentsProduct(docId);
	}

	/*
	 * 查询不在document 中的product
	 */
	public Page<Product> searchProductNotInDoucment(Page<Product> page,
			Integer docId, String catalogNo, String name, String categoryName) {
		// return
		// this.productOfPdtcategoryBeanDao.searchNotInDocumentsProduct(page,
		// docId, catalogNo, name, categoryName);
		return this.productDao.searchNotInDocumentsProduct(page, docId,
				catalogNo, name, categoryName);
	}

	/*
	 * 查询document version
	 */
	public List<DocumentVersion> searchProductVersionByName(Integer docId) {

		return this.documentVersionDao.findBy("docId", docId);
	}

	/*
	 * 查询Product Reference 根据productId
	 */
	public List<ProductReference> searchProductReferenceByProductId(
			Integer productId) {
		return this.productReferenceDao.findBy("productId", productId);
	}

	/*
	 * 查询product reference 详细信息；
	 */
	public ProductReference getProductRefereeceById(Integer id) {
		return this.productReferenceDao.getById(id);
	}

	/*
	 * 保存product reference
	 */
	private void attachProductReference(List<ProductReference> list,
			Integer productId, Integer userId, List<Integer> delReference) {
		Date now = new Date();
		if (list != null && !list.isEmpty()) {
			for (ProductReference pr : list) {
				if (pr != null) {
					if (pr.getCreatedBy() == null) {
						pr.setCreatedBy(userId);
						pr.setCreationDate(now);
					}
					pr.setModifiedBy(userId);
					pr.setModifyDate(now);
					pr.setProductId(productId);
					this.productReferenceDao.save(pr);
				}
			}

		}
		if (delReference != null && !delReference.isEmpty()) {
			this.productReferenceDao.delReferenceList(delReference);
		}

	}

	/*
	 * 获取user
	 */
	public User getUser(Integer userId) {
		return this.userDao.getById(userId);
	}

	@Transactional(readOnly = true)
	public Page<ProductCategoryCatalogBean> getCatalogNoListByCategory(
			Page<ProductCategoryCatalogBean> page, String categoryNo) {
		return productCategoryCatalogBeanDao.getCatalogNoListByCategory(page,
				categoryNo);
	}

	@Transactional(readOnly = true)
	public BigDecimal getUnitCost(String catalogNo) {
		return productDao.getUnitCost(catalogNo);
	}

	/*
	 * 获取subcategory
	 */

	// @Transactional(readOnly = true)
	// public Page<ProductBean> getCatalogNoListByCategory(Page<ProductBean>
	// page, String categoryNo){
	// return productBeanDao.getCatalogNoListByCategory(page, categoryNo);
	// }

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;

	}

	public List<Documents> getDocuments() {
		return null;
	}
	
	/**
	 * 查询DsPrice
	 * @author Zhang Yong
	 * add date 2011-11-08
	 * @param sampleType
	 * @param primerType
	 * @param itemNum
	 * @return
	 */
	public DsPrice getDsPrice (String sampleType, String primerType, Integer itemNum) {
		return dsPriceDao.getDsPrice(sampleType, primerType, itemNum);
	}

    /*
    * add  by zhanghuibin
    * */
    public ProductDTO getProductById(Integer productId){
        Product product = this.productDao.getById(productId);
        ProductDTO productDto = dozer.map(product, ProductDTO.class);
        return productDto;
    }

    public Integer getGiftProductNumByNos(List<String> catalogNos){
        return this.productDao.getGiftProductNumByNos(catalogNos);
    }

    public Integer getGiftProductTimesByNos(int custNo){
        return this.productDao.getGiftProductTimeByNos(custNo);
    }
}
