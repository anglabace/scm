package com.genscript.gsscm.accounting.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dao.CurrencyDao;
import com.genscript.gsscm.customer.dao.CustomerSpecialPriceDao;
import com.genscript.gsscm.customer.dao.SourceDao;
import com.genscript.gsscm.customer.entity.Source;
import com.genscript.gsscm.product.dao.CatalogDao;
import com.genscript.gsscm.product.dao.ProductBeanDao;
import com.genscript.gsscm.product.dao.ProductCategoryDao;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.dao.ProductInCategoryBeanDao;
import com.genscript.gsscm.product.dao.ProductListBeanDao;
import com.genscript.gsscm.product.dao.ProductSpecialPriceDao;
import com.genscript.gsscm.product.dto.ProductSpecialPriceDTO;
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductBean;
import com.genscript.gsscm.product.entity.ProductCategory;
import com.genscript.gsscm.product.entity.ProductInCategoryBean;
import com.genscript.gsscm.product.entity.ProductListBean;
import com.genscript.gsscm.product.entity.ProductSpecialPrice;
import com.genscript.gsscm.system.dao.ApproveRequestBeanDao;
import com.genscript.gsscm.system.entity.ApproveRequestBean;

@Service
@Transactional
public class ArProductService {

	@Autowired
	private ProductBeanDao productBeanDao;
	@Autowired
	private CatalogDao catalogDao;
	@Autowired
	private ProductCategoryDao productCategoryDao;
//	@Autowired
//	private ServiceCategoryDao serviceCategoryDao;
	@Autowired
	private ProductListBeanDao productListBeanDao;
//	@Autowired
//	private ServiceListBeanDao serviceListBeanDao;
	@Autowired
	private ProductDao productDao;
//	@Autowired
//	private ServiceDao serviceDao;
	@Autowired
	private ProductSpecialPriceDao productSpecialPriceDao;
	@Autowired
	private CurrencyDao currencyDao;
	@Autowired
	private SourceDao sourceDao;

	@Autowired
	private CustomerSpecialPriceDao customerSpecialPriceDao;
	@Autowired
	private ApproveRequestBeanDao approveRequestBeanDao;
//	@Autowired
//	private ServicePriceDao servicePriceDao;
	@Autowired
	private ProductInCategoryBeanDao productInCategoryBeanDao;
	@Autowired(required = false)
	private DozerBeanMapper dozer;

	@Transactional(readOnly = true)
	public Page<ProductBean> searchProduct(Page<ProductBean> page,
			List<PropertyFilter> filters, Integer custNo) {
		List<String> catalogNoList = customerSpecialPriceDao
				.getSpecialPriceCatalogNoList(custNo, null);
		return productBeanDao.findPageExceptCatalogNo(page, filters,
				catalogNoList);
	}

	@Transactional(readOnly = true)
	public Page<ProductBean> searchProduct(Page<ProductBean> page,
			Integer custNo) {
		List<String> catalogNoList = customerSpecialPriceDao
				.getSpecialPriceCatalogNoList(custNo, null);
		return productBeanDao.findPageExceptCatalogNo(page, catalogNoList);
	}

	@Transactional(readOnly = true)
	public Page<ProductBean> searchProduct(Page<ProductBean> page,
			final Map<String, String> filterParamMap, Integer custNo) {
		List<String> catalogNoList = customerSpecialPriceDao
				.getSpecialPriceCatalogNoList(custNo, null);
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
	public List<ProductInCategoryBean> getProductPricingList(String catalogNo){
		Criterion criterion = Restrictions.eq("catalogStatus", "ACTIVE");
		Criterion criterion2 = Restrictions.eq("catalogNo", catalogNo);
		return productInCategoryBeanDao.find(criterion, criterion2);
	}
	
	@Transactional(readOnly = true)
	public List<ProductSpecialPriceDTO> getProductSpecailPricingList(Integer productId){
		List<ProductSpecialPriceDTO> dtoList = new ArrayList<ProductSpecialPriceDTO>();
		List<ProductSpecialPrice> list = productSpecialPriceDao.getProductSpecailPricingList(productId);
		if(list!=null && !list.isEmpty()){
		for(ProductSpecialPrice specialPrice : list){
			ProductSpecialPriceDTO dto = dozer.map(specialPrice, ProductSpecialPriceDTO.class);
			if(specialPrice.getSourceId() != null){
			Source source = sourceDao.getById(specialPrice.getSourceId());
			if(source != null){
				dto.setSourceKey(source.getCode());
				}
			}
			dtoList.add(dto);
		}
		}
		return dtoList;
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

	

	@Transactional(readOnly = true)
	public Page<Catalog> searchCatalogList(Page<Catalog> page,
			List<PropertyFilter> filters) {
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
			List<PropertyFilter> filters) {
		return productCategoryDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<ProductCategory> searchCategorySList(
			Page<ProductCategory> page, String values) {
		return productCategoryDao.searchCategorySList(page);
	}

	@Transactional(readOnly = true)
	public Page<ProductCategory> searchCategoryMapList(
			Page<ProductCategory> page, String values,
			Map<String, ProductCategory> pdtcatMap) {
		return productCategoryDao
				.searchCategoryMapList(page, pdtcatMap);
	}

	@Transactional(readOnly = true)
	public Page<ProductCategory> searchSubCategoryList(
			Page<ProductCategory> page, Integer categoryId) {
		return productCategoryDao.searchSubCategoryList(page, categoryId);
	}

	@Transactional(readOnly = true)
	public Page<ProductCategory> searchSubCategoryMapList(
			Page<ProductCategory> page, Integer categoryId,
			Map<String, ProductCategory> map) {
		return productCategoryDao.searchSubCategoryMapList(page, categoryId,
				map);
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
			List<PropertyFilter> filters) {
		return productListBeanDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<ProductListBean> searchProductList(Page<ProductListBean> page) {
		return productListBeanDao.findPage(page);
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
	
	public String getProductPriceSymbol(String currencyCode){
		return this.currencyDao.getCurrencySymbol(currencyCode);
	}



	




	/*
	 * del product 不可修改	
	 */
	public List<Integer> getApprovedRequestListByTableTypeStatus(String tableName){
		List<ApproveRequestBean> approveRequestBean = this.approveRequestBeanDao.getApprovedRequestListByTableTypeStatus(tableName);
		List<Integer> list = new ArrayList<Integer>();
		for(ApproveRequestBean bean : approveRequestBean){
			list.add(bean.getObjectId());
		}
		return list;
	}

	
}
