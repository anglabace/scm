package com.genscript.gsscm.ws.api;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.ws.request.ProductRequest;
import com.genscript.gsscm.ws.response.ProductResponse;

@WebService(name = "ProductService", targetNamespace = WsConstants.NS)
public interface ProductWebService {

	public ProductResponse searchProduct(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getSpecialCatalogList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getCatalogList(@WebParam(name="productRequest")ProductRequest productRequest); 
	public ProductResponse getProductRelationList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse searchCategoryList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse searchCatalogList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse searchProductList(@WebParam(name="productRequest")ProductRequest productRequest); 
	public ProductResponse getCatalogDetail(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse saveCatalog(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse delCatalog(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getPdtCategoryDetail(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse savePdtCategory(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse delPdtCategory(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse searchCategoryView(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getProductDetail(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse saveProduct(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getIntermediateList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getComponentList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getSpecialPricingList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getProductPriceWithCatalog(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getRelationItemByProductId(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getPdtRelationDetail(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getPdtRestrictShip(@WebParam(name="productRequest")ProductRequest productRequest);	
	public ProductResponse getPdtStockInfo(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse searchStdPriceList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getWarehouseList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getPdtSupplierList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getProductList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getPdtPurchaseOrderList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getAllSuppliersList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getManagerTaskList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse checkPropertyApproved(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getDelPdtCategory(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse approveManageTask(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse rejectManageTask(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getStandardPriceByCatalog(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getTopSalePerson(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getRoyaltyProduct(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getPdtRoyaltyList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getSearchItemInfo(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getProductOfPdtCategoryList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse copyNewCatalog(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getPdtDetail(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse delProductList(@WebParam(name="productRequest")ProductRequest productRequest);
	public ProductResponse getPdtMoreInfo(@WebParam(name="productRequest")ProductRequest productRequest);
}