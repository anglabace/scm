package com.genscript.gsscm.ws.api;



import javax.jws.WebParam;
import javax.jws.WebService;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.ws.request.ServRequest;
import com.genscript.gsscm.ws.response.ServResponse;

@WebService(name = "ServService", targetNamespace = WsConstants.NS)
public interface ServWebService {
	public ServResponse searchServList(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse searchServiceList(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse getServiceRelationList(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse getSearchItemInfo(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse searchCategoryList(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse saveServCategory(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse getServCategoryDetail(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse delServCategory(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse getServiceOfServCategoryList(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse searchCategoryView(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse getDelServCategory(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse getIntmdService(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse getServiceCatlogId(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse delServiceList(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse getServiceDetail(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse getRelationItemByServiceId(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse checkPropertyApproved(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse saveService(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse getServRestrictShip(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse getServStockInfo(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse getServRelationDetail(@WebParam(name="servRequest")ServRequest servRequest);
	/*
	 * 根据serviceId查询service_intermediates表数据。
	 * @param serviceId,page。
	 */
	public ServResponse getIntermediateList(@WebParam(name="servRequest")ServRequest servRequest);
	/*
	 * service中没有该功能
	 */
	public ServResponse getComponentList(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse searchAssociatedItem(@WebParam(name="servRequest")ServRequest servRequest);
	/*
	 * 获取service 中的数据
	 * @param catalogNo ， name ,catalogNoList
	 * @return List<Service>
	 * 因为要与product相关接口保持统一，所以用了seachStdPriceList为接口名。
	 * 在product相关接口中，是price关联的表结构，所以使用product_standard_price_view视图;
	 * 而service 中没有相关price 所以只需要查询service表中数据。
	 * 页面为services基本信息，中break-down的tab中点击add按钮后的seach按钮功能;
	 */
	public ServResponse searchStdPriceList(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse getRoyaltyService(@WebParam(name="servRequest")ServRequest servRequest);
	public ServResponse getServSupplierList(@WebParam(name="servRequest")ServRequest servRequest);
}