package com.genscript.gsscm.ws.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.SearchItemDTO;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.RequestApproveType;
import com.genscript.gsscm.common.constant.SearchProperty;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.entity.CustomerPriceBean;
import com.genscript.gsscm.inventory.dto.WarehouseDTO;
import com.genscript.gsscm.inventory.entity.Warehouse;
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
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.entity.CategorySearchBean;
import com.genscript.gsscm.product.entity.Component;
import com.genscript.gsscm.product.entity.Intermediate;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductBean;
import com.genscript.gsscm.product.entity.ProductCategory;
import com.genscript.gsscm.product.entity.Documents;
import com.genscript.gsscm.product.entity.ProductExtendedInfo;
import com.genscript.gsscm.product.entity.ProductListBean;
import com.genscript.gsscm.product.entity.ProductOfPdtcategoryBean;
import com.genscript.gsscm.product.entity.ProductSpecialPrice;
import com.genscript.gsscm.product.entity.ProductStdPriceBean;
import com.genscript.gsscm.product.entity.Royalty;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.productservice.dto.CatalogDTO;
import com.genscript.gsscm.productservice.dto.SalePersonDTO;
import com.genscript.gsscm.purchase.dto.VendorDTO;
import com.genscript.gsscm.purchase.dto.VendorProductDTO;
import com.genscript.gsscm.serv.dto.ServiceCategoryDTO;
import com.genscript.gsscm.serv.dto.ServiceListBeanDTO;
import com.genscript.gsscm.ws.WSException;
import com.genscript.gsscm.ws.api.ProductWebService;
import com.genscript.gsscm.ws.request.ProductRequest;
import com.genscript.gsscm.ws.response.ProductResponse;

@WebService(serviceName = "ProductService", portName = "ProductServicePort", endpointInterface = "com.genscript.gsscm.ws.api.ProductWebService", targetNamespace = WsConstants.NS)
public class ProductWebServiceImpl implements ProductWebService {

	@Autowired
	private ProductService productService;

	@Autowired
	private DozerBeanMapper dozer;

	@Autowired
	private ExceptionService exceptionUtil;

	@Override
	public ProductResponse searchProduct(ProductRequest productRequest) {
		Integer userId = productRequest.getUserId();
		Page<ProductBean> page = null;
		ProductResponse productResponse = new ProductResponse();
		Map<String, String> filterMap = new HashMap<String, String>();
		Page<ProductBean> pageProduct = productRequest.getPageProduct();
		Integer custNo = productRequest.getCustNo();
		if (!pageProduct.isOrderBySetted()) {
			pageProduct.setOrderBy("productId");
			pageProduct.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchPropList = productRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = productService.searchProduct(pageProduct, custNo, null);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propName = searchProperty.getPropertyName();
					String srchOperator = searchProperty.getSearchOperator()
							.name();
					String propType = searchProperty.getSearchPropertyType()
							.name();
					String propValue1 = searchProperty.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(propType).append("_")
							.append(propName);
					filterMap.put(srchStr.toString(), propValue1);
				}
				page = productService.searchProduct(pageProduct, filterMap,
						custNo, null);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<ProductBean> productList = page.getResult();
			// List<ProductDTO> prodDTOList = new ArrayList<ProductDTO>();
			// for(ProductBean product : productList){
			// prodDTOList.add(dozer.map(product, ProductDTO.class));
			// }
			productResponse.setPage(pageDTO);
			productResponse.setProductDTOList(productList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse getSpecialCatalogList(ProductRequest productRequest) {
		ProductResponse productResponse = new ProductResponse();
		Integer userId = productRequest.getUserId();

		try {
			Assert.notNull(userId, "user id can't be null");
			List<CatalogDTO> dtoList = new ArrayList<CatalogDTO>();
			List<Catalog> catalogList = this.productService
					.getSpecailCatalogList();
			for (Catalog po : catalogList) {
				CatalogDTO dto = new CatalogDTO();
				dto.setCatalogId(po.getCatalogId());
				dto.setCatalogName(po.getCatalogName());
				dtoList.add(dto);
			}
			productResponse.setCatalogList(dtoList);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse getCatalogList(ProductRequest productRequest) {
		ProductResponse productResponse = new ProductResponse();
		Integer userId;
		userId = productRequest.getUserId();
		String catalogType;
		try {
			Assert.notNull(userId, "user id can't be null");
			catalogType = productRequest.getCatalogType().value();
			Assert.notNull(userId, "catalog type can't be null");
			List<CatalogDTO> dtoList = new ArrayList<CatalogDTO>();
			List<Catalog> catalogList = this.productService
					.getCatalogList(catalogType);
			for (Catalog po : catalogList) {
				CatalogDTO dto = new CatalogDTO();
				dto.setCatalogId(po.getCatalogId());
				dto.setCatalogName(po.getCatalogName());
				dtoList.add(dto);
			}
			productResponse.setCatalogList(dtoList);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse getProductRelationList(ProductRequest productRequest) {
		ProductResponse productResponse = new ProductResponse();
		Integer userId, custNo;
		List<String> catalogList;
		CustomerPriceBean customerPriceBean;
		userId = productRequest.getUserId();
		custNo = productRequest.getCustNo();
		try {
			Assert.notNull(userId, "user id can't be null");
			Assert.notNull(custNo, "customer number can't be null");
			customerPriceBean = productRequest.getCustomerPriceBean();
			catalogList = productRequest.getCatalogNoList();
			Assert
					.notNull(customerPriceBean,
							"customerPriceBean can't be null");
			ProductRelationDTO dto = new ProductRelationDTO();
			dto = this.productService.getProductRelations(customerPriceBean,
					custNo, catalogList);

			productResponse.setProductRelationDTO(dto);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse searchCatalogList(ProductRequest productRequest) {
		Integer userId;
		Page<Catalog> pageCatalog;
		Page<Catalog> page = null;
		ProductResponse productResponse = new ProductResponse();
		userId = productRequest.getUserId();
		Map<String, String> filterMap = new HashMap<String, String>();
		pageCatalog = productRequest.getPageCatalog();
		if (!pageCatalog.isOrderBySetted()) {
			pageCatalog.setOrderBy("id");
			pageCatalog.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchPropList = productRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = productService.searchCatalogList(pageCatalog);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propName = searchProperty.getPropertyName();
					String srchOperator = searchProperty.getSearchOperator()
							.name();
					String propType = searchProperty.getSearchPropertyType()
							.name();
					String propValue1 = searchProperty.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(propType).append("_")
							.append(propName);
					filterMap.put(srchStr.toString(), propValue1);
				}
				page = productService.searchCatalogList(pageCatalog, filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<Catalog> catalogList = page.getResult();
			productResponse.setPage(pageDTO);
			productResponse.setCatalogs(catalogList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse searchCategoryList(ProductRequest productRequest) {
		Integer userId;
		Page<ProductCategory> pageCategory;
		Page<ProductCategory> page = null;
		ProductResponse productResponse = new ProductResponse();
		userId = productRequest.getUserId();
		Map<String, String> filterMap = new HashMap<String, String>();
		pageCategory = productRequest.getPageCategory();
		if (!pageCategory.isOrderBySetted()) {
			pageCategory.setOrderBy("id");
			pageCategory.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchPropList = productRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = productService.searchCategoryList(pageCategory);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propName = searchProperty.getPropertyName();
					String srchOperator = searchProperty.getSearchOperator()
							.name();
					String propType;
					propType = searchProperty.getSearchPropertyType().name();
					String propValue1 = searchProperty.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(propType).append("_")
							.append(propName);
					filterMap.put(srchStr.toString(), propValue1);
				}
				page = productService.searchCategoryList(pageCategory,
						filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<ProductCategory> categoryList = page.getResult();
			productResponse.setPage(pageDTO);
			productResponse.setCategoryList(categoryList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse searchProductList(ProductRequest productRequest) {
		Integer userId;
		Page<ProductListBean> pageProduct;
		Page<ProductListBean> page = null;
		ProductResponse productResponse = new ProductResponse();
		userId = productRequest.getUserId();
		Map<String, String> filterMap = new HashMap<String, String>();
		pageProduct = productRequest.getPgProduct();
		if (!pageProduct.isOrderBySetted()) {
			pageProduct.setOrderBy("id");
			pageProduct.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchPropList = productRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = productService.searchProductList(pageProduct);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propName = searchProperty.getPropertyName();
					String srchOperator = searchProperty.getSearchOperator()
							.name();
					String propType = searchProperty.getSearchPropertyType()
							.name();
					String propValue1 = searchProperty.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(propType).append("_")
							.append(propName);
					filterMap.put(srchStr.toString(), propValue1);
				}
				page = productService.searchProductList(pageProduct, filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<ProductListBean> productList = page.getResult();
			if(productRequest.getCatalogId()!=null){
				/*
				 * 将priceLimit 提供给前台页面，前页面根据priceLimit决定price limit标签是否可写.
				 */
				CatalogDTO catalog = productService.getCatalogDetail(productRequest.getCatalogId());
				productResponse.setPriceLimit(catalog.getPriceLimit());
			}
			productResponse.setPage(pageDTO);
			productResponse.setProductList(productList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 获得一个Catalog的基本信息.
	 */
	@Override
	public ProductResponse getCatalogDetail(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			Integer id = productRequest.getParamId();
			String catalogId = productRequest.getCatalogId();
			CatalogDTO catalog = null;
			if (id != null) {
				catalog = this.productService.getCatalogDetail(id);
			} else {
				catalog = this.productService.getCatalogDetail(catalogId);
			}
			productResponse.setCatalog(catalog);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 保存或更新Catalog的基本信息, 并同时新增或修改ProductCategory, 删除ProductCategory.
	 */
	@Override
	public ProductResponse saveCatalog(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			CatalogDTO dto = productRequest.getCatalogDTO();
			Catalog catalog = this.productService.saveCatalog(dto, loginUserId);
			productResponse.setId(catalog.getId());// 返回的是主键.
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 获得一个产品Category的详细信息.
	 */
	@Override
	public ProductResponse getPdtCategoryDetail(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			Integer catalogId = productRequest.getParamId();
			ProductCategoryDTO dto = this.productService
					.getPdtCategoryDetail(catalogId);
			productResponse.setProductCategory(dto);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 保存或修改一个ProductCategory的基本信息， 并同时新增或修改Sub ProductCategory list , 删除Sub
	 * ProductCategory list.
	 */
	@Override
	public ProductResponse savePdtCategory(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			ProductCategoryDTO dto = productRequest.getProductCategory();
			ProductCategory category = this.productService.savePdtCategory(dto,
					loginUserId);
			productResponse.setCategoryId(category.getCategoryId());// 返回的是主键.
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse searchCategoryView(ProductRequest productRequest) {
		Integer userId;
		Page<CategorySearchBean> pageCategorySearchBean;
		Page<CategorySearchBean> page = null;
		ProductResponse productResponse = new ProductResponse();
		userId = productRequest.getUserId();
		Map<String, String> filterMap = new HashMap<String, String>();
		pageCategorySearchBean = productRequest.getPageCategorySearchBean();
		if (!pageCategorySearchBean.isOrderBySetted()) {
			pageCategorySearchBean.setOrderBy("id");
			pageCategorySearchBean.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchPropList = productRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = productService
						.searchCategoryBean(pageCategorySearchBean);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propName = searchProperty.getPropertyName();
					String srchOperator = searchProperty.getSearchOperator()
							.name();
					String propType = searchProperty.getSearchPropertyType()
							.name();
					String propValue1 = searchProperty.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(propType).append("_")
							.append(propName);
					filterMap.put(srchStr.toString(), propValue1);
				}
				page = productService.searchCategoryBean(
						pageCategorySearchBean, filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<CategorySearchBean> categorySearchBeanList = page.getResult();
			productResponse.setPage(pageDTO);
			productResponse.setCategorySearchBeanList(categorySearchBeanList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 批量删除Catalog.
	 */
	@Override
	public ProductResponse delCatalog(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			List<Integer> requestIdList = productRequest.getRequestIdList();
			this.productService.delCatalogList(requestIdList);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 批量删除Product Categories.
	 * 
	 * @param productRequest
	 *            /categoryIdList
	 */
	@Override
	public ProductResponse delPdtCategory(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			List<Integer> categoryIdList = productRequest.getCategoryIdList();
			this.productService.delPdtCategoryList(categoryIdList);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 获得一个产品的详细信息及ShipCondition, StorageCondition.
	 */
	@Override
	public ProductResponse getProductDetail(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			Integer productId = productRequest.getParamId();
			ProductDTO dto = this.productService.getProductDetail(productId);
			productResponse.setProductDTO(dto);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 保存Product的基本信息.
	 */
	@Override
	public ProductResponse saveProduct(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			ProductDTO dto = productRequest.getProduct();
			Product product = this.productService.saveProduct(dto, loginUserId,"","");
			productResponse.setProductId(product.getProductId());// 返回的是主键.
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 获得一个产品的Break Down list.并分页显示.
	 */
	@Override
	public ProductResponse getIntermediateList(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			Integer productId = productRequest.getParamId();
			Page<Intermediate> page = productRequest.getPageIntermediate();
			Page<IntermediateDTO> dtoPage = this.productService
					.getIntermediateList(page, productId);
			List<IntermediateDTO> intermediateList = dtoPage.getResult();
			PageDTO pagerInfo = (PageDTO) dozer.map(page, PageDTO.class);
			productResponse.setIntermediateList(intermediateList);
			productResponse.setPage(pagerInfo);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 获得一个产品的Component list for 'Composite tab'.
	 */
	@Override
	public ProductResponse getComponentList(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			Integer productId = productRequest.getParamId();
			Page<Component> page = productRequest.getPageComponent();
			Page<ComponentDTO> dtoPage = this.productService.getComponentList(
					page, productId);
			List<ComponentDTO> componentList = dtoPage.getResult();
			PageDTO pagerInfo = (PageDTO) dozer.map(page, PageDTO.class);
			productResponse.setComponentList(componentList);
			productResponse.setPage(pagerInfo);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 获得一个产品的Product Price list for 'Pricing tab'.
	 */
	@Override
	public ProductResponse getSpecialPricingList(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			Integer productId = productRequest.getParamId();
			List<ProductSpecialPrice> specialPriceList = this.productService
					.getProductSpecialPrice(productId);
			productResponse.setSpecialPriceList(specialPriceList);
			ProductStdPriceBean stdPrice = this.productService
					.getProductPrice(productId);
			productResponse.setProductStdPriceBean(stdPrice);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 获得一个产品的Product Price部分信息. for 'Pricing tab'.
	 */
	@Override
	public ProductResponse getProductPriceWithCatalog(
			ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			Integer productId = productRequest.getParamId();
			String catalogId = productRequest.getCatalogId();
			ProductPriceDTO dto = this.productService
					.getProductPriceWithCatalog(productId, catalogId);
			productResponse.setProductPriceDTO(dto);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse getRelationItemByProductId(
			ProductRequest productRequest) {
		ProductResponse productResponse = new ProductResponse();
		Integer userId = productRequest.getUserId();
		Integer productId = productRequest.getProductId();
		try {
			Assert.notNull(userId, "user id can't be null");
			Assert.notNull(productId, "product id can't be null");
			List<DropDownDTO> dropDownDTOList = productService
					.getRelationItemByProductId(productId);
			productResponse.setDropDownDTOList(dropDownDTOList);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0201", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 根据relationId获得ProductRelation的详细信息.
	 */
	@Override
	public ProductResponse getPdtRelationDetail(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			Integer relationId = productRequest.getParamId();
			ProductRelationDTO dto = this.productService
					.getProductRelationDetail(relationId);
			productResponse.setProductRelationDTO(dto);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 获得一个产品的RestrictShip(禁运) list
	 * 
	 * @param productId
	 * @return
	 */
	@Override
	public ProductResponse getPdtRestrictShip(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			Integer productId = productRequest.getParamId();
			List<RestrictShipDTO> restrictShipList = this.productService
					.getProductRestrictShipList(productId);
			productResponse.setRestrictShipList(restrictShipList);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse getWarehouseList(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			List<Warehouse> warehouseList = productService.getWarehouseList();
			List<WarehouseDTO> warehouseDTOList = new ArrayList<WarehouseDTO>();
			for (Warehouse warehouse : warehouseList) {
				warehouseDTOList.add(dozer.map(warehouse, WarehouseDTO.class));
			}
			productResponse.setWarehouseDTOList(warehouseDTOList);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 获得一个产品的Stock(Inventory)的统计信息.
	 */
	@Override
	public ProductResponse getPdtStockInfo(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			Integer productId = productRequest.getParamId();
			ProductStockStatDTO productStockStatDTO = this.productService
					.getProductStockStat(productId);
			productResponse.setProductStockStatDTO(productStockStatDTO);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 获得Product的列表. For(Product's Break-down, Composite tab.
	 */
	@Override
	public ProductResponse searchStdPriceList(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			String catalogNo = productRequest.getCatalogNo();
			String name = productRequest.getName();
			List<String> catalogNoList = productRequest.getCatalogNoList();
			List<ProductStdPriceBean> stdPriceList = this.productService
					.searchStdPriceList(catalogNo, name, catalogNoList);
			productResponse.setStdPriceList(stdPriceList);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 获得一个Product产品的所有Supplier(Vendor).
	 */
	@Override
	public ProductResponse getPdtSupplierList(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			String catalogNo = productRequest.getCatalogNo();
			List<VendorProductDTO> verdonProductList = this.productService
					.getPdtSupplierList(catalogNo);
			productResponse.setVerdonProductList(verdonProductList);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse getProductList(ProductRequest productRequest) {
		Integer userId = productRequest.getUserId();
		Page<Product> page = null;
		ProductResponse productResponse = new ProductResponse();
		Map<String, String> filterMap = new HashMap<String, String>();
		Page<Product> pageProduct = productRequest.getPagePdt();
		if (!pageProduct.isOrderBySetted()) {
			pageProduct.setOrderBy("productId");
			pageProduct.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchPropList = productRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = productService.getProductList(pageProduct);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propName = searchProperty.getPropertyName();
					String srchOperator = searchProperty.getSearchOperator()
							.name();
					String propType = searchProperty.getSearchPropertyType()
							.name();
					String propValue1 = searchProperty.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(propType).append("_")
							.append(propName);
					filterMap.put(srchStr.toString(), propValue1);
				}
				page = productService.getProductList(pageProduct, filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<Product> productList = page.getResult();
			productResponse.setPage(pageDTO);
			productResponse.setPdtList(productList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 获得关于某个Product的采购(Purchase)订单相关信息.
	 * 
	 * @param catalogNo
	 * @return
	 */
	@Override
	public ProductResponse getPdtPurchaseOrderList(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			String catalogNo = productRequest.getCatalogNo();
			List<PurchaseOrderDTO> dtoList = this.productService
					.getPdtPurchaseOrderList(catalogNo);
			productResponse.setPurchaseOrderDTOList(dtoList);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse getAllSuppliersList(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		String vendorName = productRequest.getName();
		try {
			List<VendorDTO> vendorDTOList = productService
					.getAllSuppliesList(vendorName);
			productResponse.setVendorDTOList(vendorDTOList);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse getManagerTaskList(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		RequestApproveType requestApproveType = productRequest
				.getRequestApproveType();
		try {
			if (RequestApproveType.Catalog == requestApproveType) {
				List<CatalogDTO> catalogDTOList = productService
						.getCatalogApproveList();
				productResponse.setCatalogList(catalogDTOList);
			}
			if (RequestApproveType.ProductCategory == requestApproveType) {
				List<ProductCategoryDTO> categoryDTOList = productService
						.getProductCategoryApproveList();
				productResponse.setProductCategoryDTOList(categoryDTOList);
			}
			if (RequestApproveType.ServiceCategory == requestApproveType) {
				List<ServiceCategoryDTO> categoryDTOList = productService
						.getServiceCategoryApproveList();
				productResponse.setServiceCategoryDTOList(categoryDTOList);
			}
			if (RequestApproveType.Product == requestApproveType) {
				List<ProductListBeanDTO> productDTOList = productService
						.getProductApproveList();
				productResponse.setProductListBeanDTOList(productDTOList);
			}
			if (RequestApproveType.Service == requestApproveType) {
				List<ServiceListBeanDTO> serviceDTOList = productService
						.getServiceApproveList();
				productResponse.setServiceListBeanDTOList(serviceDTOList);
			}
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse checkPropertyApproved(ProductRequest productRequest) {
		Integer userId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		RequestApproveType requestApproveType = productRequest
				.getRequestApproveType();
		Integer objectId = productRequest.getObjectId();
		String columnName = productRequest.getColumnName();
		try {
			Assert.notNull(userId, "user id can't be null");
			Assert.notNull(requestApproveType,
					"request approve type can't be null");
			Assert.notNull(columnName, "column name can't be null");
			Assert.notNull(objectId, "object id can't be null");
			Boolean propertyApprovedStatus = productService
					.checkPropertyApproved(objectId, columnName,
							requestApproveType.name());
			productResponse.setPropertyApprovedStatus(propertyApprovedStatus);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 判断ProductCategory是否可以删除. 返回的 id list是不能删除的.
	 */
	@Override
	public ProductResponse getDelPdtCategory(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			List<Integer> categoryIdList = productRequest.getCategoryIdList();
			List<Integer> unDeleteIdList = this.productService
					.getDelPdtCategory(categoryIdList);
			productResponse.setUnDeleteIdList(unDeleteIdList);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse approveManageTask(ProductRequest productRequest) {
		Integer userId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		List<Integer> requestIds = productRequest.getRequestIdList();
		try {
			Assert.notNull(userId, "user id can't be null");
			productService.approveManageTask(requestIds, userId);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse rejectManageTask(ProductRequest productRequest) {
		Integer userId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		String rejectReason = productRequest.getRejectReason();
		List<Integer> requestIds = productRequest.getRequestIdList();
		try {
			Assert.notNull(userId, "user id can't be null");
			productService.rejectManageTask(requestIds, rejectReason, userId);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse getStandardPriceByCatalog(
			ProductRequest productRequest) {
		Integer userId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		String catalogNo = productRequest.getCatalogNo();
		try {
			Assert.notNull(userId, "user id can't be null");
			ProductPriceDTO productPriceDTO = productService
					.getStandardPriceByCatalog(catalogNo);
			productResponse.setProductPriceDTO(productPriceDTO);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 获得一个产品的一段时间内， 按销售数量排名的销售员.(For sales statstics's tab);
	 */
	@Override
	public ProductResponse getTopSalePerson(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			String catalogNo = productRequest.getCatalogNo();
			Integer topCount = productRequest.getTopCount();
			Integer lastDays = productRequest.getLastDays();
			List<SalePersonDTO> salePersonList = this.productService
					.getTopSalePerson(catalogNo, topCount, lastDays);
			productResponse.setSalePersonList(salePersonList);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * 获得一个Product的RoyaltyProduct(版权税)信息.(For misc tab);
	 */
	@Override
	public ProductResponse getRoyaltyProduct(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			String catalogNo = productRequest.getCatalogNo();
			RoyaltyProductDTO royalty = this.productService
					.getRoyaltyProduct(catalogNo);
			productResponse.setRoyaltyProduct(royalty);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse getPdtRoyaltyList(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		String name = productRequest.getName();
		try {
			
			List<Royalty> royaltyList = productService
					.getPdtRoyaltyList(name);
			productResponse.setRoyaltyList(royaltyList);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse getSearchItemInfo(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		List<String> catalogNoList = productRequest.getCatalogNoList();
		try {
			List<SearchItemDTO> searchItemDTOList = productService.getSearchItemInfo(catalogNoList);
			productResponse.setSearchItemDTOList(searchItemDTOList);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse getProductOfPdtCategoryList(
			ProductRequest productRequest) {
		Integer userId;
		Page<ProductOfPdtcategoryBean> pageProductOfPdtcategoryBean;
		Page<ProductOfPdtcategoryBean> page = null;
		ProductResponse productResponse = new ProductResponse();
		userId = productRequest.getUserId();
		Map<String, String> filterMap = new HashMap<String, String>();
		pageProductOfPdtcategoryBean = productRequest.getPageProductOfPdtcategoryBean();
		try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchPropList = productRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = productService.getProductOfPdtCategoryList(pageProductOfPdtcategoryBean);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propName = searchProperty.getPropertyName();
					String srchOperator = searchProperty.getSearchOperator()
							.name();
					String propType = searchProperty.getSearchPropertyType()
							.name();
					String propValue1 = searchProperty.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(propType).append("_")
							.append(propName);
					filterMap.put(srchStr.toString(), propValue1);
				}
				page = productService.getProductOfPdtCategoryList(pageProductOfPdtcategoryBean, filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<ProductOfPdtcategoryBean> productList = page.getResult();
			productResponse.setPage(pageDTO);
			productResponse.setProductOfPdtCategoryList(productList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	/**
	 * Copy from a catalog save as a new catalog. 同时复制ProductCategory及ProductPrice, 复制ServiceCategory及其ServicePrice.
	 */
	@Override
	public ProductResponse copyNewCatalog(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			CatalogDTO catalogDTO = productRequest.getCatalogDTO();
			Catalog catalog = this.productService.copyNewCatalog(catalogDTO, loginUserId);
			productResponse.setCatalogId(catalog.getCatalogId());
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse getPdtDetail(ProductRequest productRequest) {
		Integer userId = productRequest.getUserId();
		Integer productId = productRequest.getParamId();
		String productType = productRequest.getType();
		ProductResponse productResponse = new ProductResponse();
		try {
			Assert.notNull(userId, "user id can't be null");
			if(productType!=null){
				productType = productType.toLowerCase();
				if("peptide".equals(productType)){
					productResponse.setPeptide(this.productService.getPeptideDetail(productId));
				}else if("antibody".equals(productType)){
					productResponse.setAntibody(this.productService.getAntibodyDetail(productId));
				}else if("enzyme".equals(productType)){
					productResponse.setEnzyme(this.productService.getEnzymeDetail(productId));
				}else if("gene".equals(productType)){
					productResponse.setGene(this.productService.getGeneDetail(productId));
				}else if("kit".equals(productType)){
					productResponse.setKit(this.productService.getKitDetail(productId));
				}else if("oligo".equals(productType)){
					productResponse.setOligo(this.productService.getOligoDetail(productId));
				}else if("protein".equals(productType)){
					productResponse.setProtein(this.productService.getProteinDetail(productId));
				}else if("molecule".equals(productType)){
					productResponse.setMolecule(this.productService.getMoleculeDetail(productId));
				}else if("chemical".equals(productType)){
					productResponse.setChemical(this.productService.getChemicalDetail(productId));
				}
			}
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse delProductList(ProductRequest productRequest) {
		Integer loginUserId = productRequest.getUserId();
		ProductResponse productResponse = new ProductResponse();
		try {
			List<Integer> delProductListId = productRequest.getProductListId();
			this.productService.delProductList(delProductListId);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

	@Override
	public ProductResponse getPdtMoreInfo(ProductRequest productRequest) {
		Integer userId = productRequest.getUserId();
		Integer productId = productRequest.getParamId();
		ProductResponse productResponse = new ProductResponse();
		try {
			Assert.notNull(userId, "user id can't be null");
			ProductExtendedInfo pdtExtInfo = this.productService.getPdtExtendedInfo(productId);
			//List<Documents> productDoc = this.productService.getProductDocument(productId);
			productResponse.setProductExtEndedInfo(pdtExtInfo);
			//productResponse.setProductDocument(productDoc);
			productResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", userId);
			productResponse.setHasException(true);
			productResponse.setWsException(exDTO);
		}
		return productResponse;
	}

}
