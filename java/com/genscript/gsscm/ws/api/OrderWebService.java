package com.genscript.gsscm.ws.api;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.ws.request.OrderRequest;
import com.genscript.gsscm.ws.response.OrderResponse;

@WebService(name = "OrderService", targetNamespace = WsConstants.NS)
public interface OrderWebService {

	public OrderResponse searchOrderList(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getOrderDetail(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse delOrder(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getItemStatusHist(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getOrderAddress(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getOrderStatusHist(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse calculateOrderItemPrice(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getAddressListByType(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getInstructionList(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse saveOrder(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getItemDiscount(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getPayInfoList(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getItemStockQty(@WebParam(name="orderRequest")OrderRequest orderRequest);	
	public OrderResponse getItemOtherInfo(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse saveOrderTemplate(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse updateOrderStatus(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getOrderItemList(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getUnProcessedReturnList(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getUnProcessedReturn(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse saveOrderReturn(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse delOrderReturn(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse processOrderReturn(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getOrderTotals(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse calculateReturnRefund(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse searchItemPrice(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getExpirationDate(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getProcessedReturnItemList(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getTotalProducts(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse replaceOrder(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse changeCurrency(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse repeatOrder(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse calShippingPackage(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getOrderShipPackageList(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getPackageListForItemList(@WebParam(name="orderRequest")OrderRequest orderRequest);	
	public OrderResponse getPackageDetail(@WebParam(name="orderRequest")OrderRequest orderRequest);	
	public OrderResponse getShippingTotal(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getShippingTotalByPage(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getItemOtherInfoForNew(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getItemMoreDetail(@WebParam(name="orderRequest")OrderRequest orderRequest);	
	public OrderResponse delOrderTemplate(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getTemplateList(@WebParam(name="orderRequest")OrderRequest orderRequest);
	public OrderResponse getPrePayment(@WebParam(name="orderRequest")OrderRequest orderRequest);
}
