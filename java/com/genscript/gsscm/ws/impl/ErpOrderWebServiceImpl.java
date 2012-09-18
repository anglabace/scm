package com.genscript.gsscm.ws.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PriceService;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.epicorwebservice.service.ErpSalesOrderService;
import com.genscript.gsscm.manufacture.service.DsPlateService;
import com.genscript.gsscm.manufacture.service.WorkOrderEntryService;
import com.genscript.gsscm.order.dto.OrderErpMappingDTO;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderLineErpMapping;
import com.genscript.gsscm.order.service.ErpOrderService;
import com.genscript.gsscm.quoteorder.service.QuoteOrderService;
import com.genscript.gsscm.serv.dto.ServiceItemPiceDTO;
import com.genscript.gsscm.ws.WSException;
import com.genscript.gsscm.ws.api.ErpOrderWebService;
import com.genscript.gsscm.ws.request.ErpOrderRequest;
import com.genscript.gsscm.ws.response.ErpOrderResponse;

@WebService(serviceName = "ERPOrderService", portName = "ERPOrderServicePort", endpointInterface = "com.genscript.gsscm.ws.api.ErpOrderWebService", targetNamespace = WsConstants.NS)
public class ErpOrderWebServiceImpl implements ErpOrderWebService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private ErpSalesOrderService erpSalesOrderService;
	@Autowired
	private PriceService priceService;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private ErpOrderService erpOrderService;
	@Autowired
	private WorkOrderEntryService workOrderEntryService;
	@Autowired
	private QuoteOrderService quoteOrderService;
	@Autowired
	private DsPlateService dsPlateService;

	@Override
	public ErpOrderResponse updateOrderPoSo(ErpOrderRequest orderRequest) {
		OrderErpMappingDTO orderErpMappingDTO = orderRequest
				.getOrderErpMappingDTO();
		Integer orderNo = orderErpMappingDTO.getOrderNo();
		List<OrderLineErpMapping> orderLineList = orderErpMappingDTO
				.getOrderLineList();
		Integer loginUserId = orderRequest.getUserId();
		Assert.notNull(loginUserId, "User id can't be null");
		Assert.notNull(orderNo, "Order Number can't be null");
		Assert.notNull(orderLineList, "Order line list can't be null");
		ErpOrderResponse erpOrderResponse = new ErpOrderResponse();
		try {
			StringBuffer itemIds = new StringBuffer("");
			List<Integer> itemIdList = erpOrderService
					.updateOrderSoPo(orderErpMappingDTO);
			System.out.println("itemIdList:"
					+ (itemIdList != null ? itemIdList.size() : 0));
			if (itemIdList != null && itemIdList.size() > 0) {
				for (Integer itemId : itemIdList) {
					itemIds.append(itemId).append(",");
				}
			}
			
			if (!itemIds.toString().equals("")) {
				itemIds = workOrderEntryService.filterItems(itemIds.toString());
				if(!itemIds.toString().equals("")) {
					this.workOrderEntryService.createWorkOrders(itemIds.toString().substring(0, itemIds.toString().length() - 1), null);
				}
			}
			erpOrderResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0109", loginUserId);
			erpOrderResponse.setHasException(true);
			erpOrderResponse.setWsException(exDTO);
		}
		return erpOrderResponse;
	}

	@Override
	public ErpOrderResponse updateCustomer(ErpOrderRequest eRPOrderRequest) {
		Integer updateCustNo = eRPOrderRequest.getCustNo();
		Integer loginUserId = eRPOrderRequest.getUserId();
		Assert.notNull(loginUserId, "User id can't be null");
		Assert.notNull(updateCustNo, "Customer Number can't be null");
		ErpOrderResponse erpOrderResponse = new ErpOrderResponse();
		try {
			Customer cust = customerDao.get(updateCustNo);
			CustomerDTO customerDTO = dozer.map(cust, CustomerDTO.class);
			erpSalesOrderService.createCustomer(customerDTO,
					updateCustNo.toString());
			erpOrderResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0109", loginUserId);
			erpOrderResponse.setHasException(true);
			erpOrderResponse.setWsException(exDTO);
		}
		return erpOrderResponse;
	}

	@Override
	public ErpOrderResponse getPeptidePrice(ErpOrderRequest eRPOrderRequest) {
		Integer loginUserId = eRPOrderRequest.getUserId();
		ErpOrderResponse erpOrderResponse = new ErpOrderResponse();
		Integer customerId = eRPOrderRequest.getCustNo();
		System.out.println(customerId+">>>>>>>>..");
		Customer customer = new Customer();
		Assert.notNull(loginUserId, "User id can't be null");
		List<ServiceItemPiceDTO> orderServiceItemPiceDTOList = eRPOrderRequest
				.getOrderServiceItemPiceDTOList();
		try {
			customer = customerDao.getById(customerId);
			List<ServiceItemPiceDTO> resultItemPiceDTOList = new ArrayList<ServiceItemPiceDTO>();
			for(ServiceItemPiceDTO serviceItemPiceDTO : orderServiceItemPiceDTOList){
				ServiceItemPiceDTO itemPiceDTO = priceService
						.getPeptidePrice(serviceItemPiceDTO);
				this.quoteOrderService.calItemDiscountPice(itemPiceDTO,serviceItemPiceDTO, customer, eRPOrderRequest.getOrderQuoteDate());
				resultItemPiceDTOList.add(itemPiceDTO);
			}
			erpOrderResponse.setServiceItemPiceDTOList(resultItemPiceDTOList);
			erpOrderResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0109", loginUserId);
			erpOrderResponse.setHasException(true);
			erpOrderResponse.setWsException(exDTO);
		}
		return erpOrderResponse;
	}

	/**
	 * 计算oligo价格，给外部的接口
	 * @author Zhang Yong
	 * add date 2011-12-17
	 */
	@Override
	public ErpOrderResponse getOligoPrice(ErpOrderRequest eRPOrderRequest) {
		Integer loginUserId = eRPOrderRequest.getUserId();
		ErpOrderResponse erpOrderResponse = new ErpOrderResponse();
		List<ServiceItemPiceDTO> orderServiceItemPiceDTOList = eRPOrderRequest.getOrderServiceItemPiceDTOList();
		Assert.notNull(loginUserId, "User id can't be null");
		try {
			List<ServiceItemPiceDTO> resultItemPiceDTOList = new ArrayList<ServiceItemPiceDTO>();
			for(ServiceItemPiceDTO serviceItemPiceDTO : orderServiceItemPiceDTOList){
				ServiceItemPiceDTO itemPiceDTO = quoteOrderService.getOligoPrice(serviceItemPiceDTO);
				resultItemPiceDTOList.add(itemPiceDTO);
			}
			erpOrderResponse.setServiceItemPiceDTOList(resultItemPiceDTOList);
			erpOrderResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0109", loginUserId);
			erpOrderResponse.setHasException(true);
			erpOrderResponse.setWsException(exDTO);
		}
		return erpOrderResponse;
	}
}
