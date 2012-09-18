package com.genscript.gsscm.purchase.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dto.CustomerPickerDTO;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.OrderStatusType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.inventory.dao.WarehouseDao;
import com.genscript.gsscm.manufacture.dao.WorkCenterAssignedDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderDao;
import com.genscript.gsscm.manufacture.entity.WorkCenterAssigned;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.order.dao.MfgOrderDao;
import com.genscript.gsscm.order.dao.MfgOrderItemDao;
import com.genscript.gsscm.order.dao.OrderAddressDao;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.dao.OrderErpMappingDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.entity.MfgOrder;
import com.genscript.gsscm.order.entity.MfgOrderDTO;
import com.genscript.gsscm.order.entity.MfgOrderItem;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.OrderErpMapping;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.product.dao.ProductClassDao;
import com.genscript.gsscm.product.dto.PurchaseOrderDTO;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.purchase.dao.PurchaseOrderBeanDao;
import com.genscript.gsscm.purchase.dao.PurchaseOrderDao;
import com.genscript.gsscm.purchase.dao.PurchaseOrderItemDao;
import com.genscript.gsscm.purchase.dao.VendorDao;
import com.genscript.gsscm.purchase.dto.SalesOrderDTO;
import com.genscript.gsscm.purchase.dto.VendorDTO;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.purchase.entity.PurchaseOrderBean;
import com.genscript.gsscm.purchase.entity.PurchaseOrderItem;
import com.genscript.gsscm.purchase.entity.Vendor;
import com.genscript.gsscm.serv.dao.ServiceClassificationDao;
import com.genscript.gsscm.serv.entity.ServiceClassification;
import com.genscript.gsscm.shipment.service.ShippingService;
import com.genscript.gsscm.systemsetting.dao.BillTerritoryDao;

@Service
@Transactional
public class PurchaseService {
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	@Autowired
	private VendorDao vendorDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private PurchaseOrderItemDao purchaseOrderItemDao;
	@Autowired
	private PurchaseOrderBeanDao purchaseOrderBeanDao;
	@Autowired
	private WarehouseDao warehouseDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private WorkOrderDao workOrderDao;
	@Autowired
	private WorkCenterAssignedDao workCenterAssignedDao;
	@Autowired
	private OrderErpMappingDao orderErpMappingDao;
	@Autowired
	private MfgOrderDao mfgOrderDao;
	@Autowired
	private MfgOrderItemDao mfgOrderItemDao;
	@Autowired
	private BillTerritoryDao billTerritoryDao;
	@Autowired
	private ProductClassDao productClassDao;
	@Autowired
	private OrderAddressDao orderAddressDao;
	@Autowired
	private ServiceClassificationDao serviceClassificationDao;
	@Autowired
	private ShippingService shippingService;
	
	private OrderItem orderItem;

	@Transactional(readOnly = true)
	public PurchaseOrder getPurchaseOrder(Integer orderNo) {
		return this.purchaseOrderDao.getById(orderNo);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<VendorDTO> getVendorList(final PageDTO pagerDTO,
			final String name) {
		Page<VendorDTO> vendorDTOPager = new Page<VendorDTO>();
		Page<Vendor> vendorPager = dozer.map(pagerDTO, Page.class);
		if (name == null || name.trim().length() < 1) {
			vendorPager = vendorDao.getAll(vendorPager);
		} else {
			List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
			PropertyFilter filter = new PropertyFilter("LIKES_vendorName", name);
			filterList.add(filter);
			vendorPager = vendorDao.findPage(vendorPager, filterList);
		}
		List<Vendor> vendorList = vendorPager.getResult();
		List<VendorDTO> dtoList = new ArrayList<VendorDTO>();
		for (Vendor vendor : vendorList) {
			VendorDTO dto = dozer.map(vendor, VendorDTO.class);
			dtoList.add(dto);
		}
		vendorPager.setResult(null);
		vendorDTOPager = dozer.map(vendorPager, Page.class);
		vendorDTOPager.setResult(dtoList);
		return vendorDTOPager;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<CustomerPickerDTO> getCustomerList(final PageDTO pagerDTO) {
		Page<CustomerPickerDTO> customerDTOPager = new Page<CustomerPickerDTO>();
		Page<Customer> customerPager = dozer.map(pagerDTO, Page.class);
		customerPager = customerDao.findPage(customerPager);
		List<Customer> customerList = customerPager.getResult();
		List<CustomerPickerDTO> dtoList = new ArrayList<CustomerPickerDTO>();
		for (Customer customer : customerList) {
			CustomerPickerDTO dto = dozer
					.map(customer, CustomerPickerDTO.class);
			StringBuilder sb = new StringBuilder();
			sb.append(customer.getFirstName());
			if (StringUtils.isNotEmpty(customer.getLastName())) {
				sb.append(" ");
			}
			sb.append(customer.getLastName());
			if (StringUtils.isNotEmpty(customer.getMidName())) {
				sb.append(", ");
				sb.append(customer.getMidName());
			}
			dto.setCustName(sb.toString());
			dtoList.add(dto);
		}
		customerPager.setResult(null);
		customerDTOPager = dozer.map(customerPager, Page.class);
		customerDTOPager.setResult(dtoList);
		return customerDTOPager;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<CustomerPickerDTO> getCustomerList(final PageDTO pagerDTO,
			String custName, Integer custNo) {
		Page<CustomerPickerDTO> customerDTOPager = new Page<CustomerPickerDTO>();
		Page<Customer> customerPager = dozer.map(pagerDTO, Page.class);
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		if (StringUtils.isNotBlank(custName)) {
			PropertyFilter filter = new PropertyFilter(
					"LIKES_firstName_OR_lastName", custName);
			filterList.add(filter);
		}
		if (custNo != null) {
			PropertyFilter filter = new PropertyFilter("EQI_custNo", custNo);
			filterList.add(filter);
		}
		customerPager = customerDao.findPage(customerPager, filterList);
		List<Customer> customerList = customerPager.getResult();
		List<CustomerPickerDTO> dtoList = new ArrayList<CustomerPickerDTO>();
		for (Customer customer : customerList) {
			CustomerPickerDTO dto = dozer
					.map(customer, CustomerPickerDTO.class);
			StringBuilder sb = new StringBuilder();
			sb.append(customer.getFirstName());
			if (StringUtils.isNotEmpty(customer.getLastName())) {
				sb.append(" ");
			}
			sb.append(customer.getLastName());
			if (StringUtils.isNotEmpty(customer.getMidName())) {
				sb.append(", ");
				sb.append(customer.getMidName());
			}
			dto.setCustName(sb.toString());
			dtoList.add(dto);
		}
		customerPager.setResult(null);
		customerDTOPager = dozer.map(customerPager, Page.class);
		customerDTOPager.setResult(dtoList);
		return customerDTOPager;
	}

	@Transactional(readOnly = true)
	public Page<PurchaseOrderBean> searchPurchaseOrderBean(
			final Page<PurchaseOrderBean> page,
			final List<PropertyFilter> filters) {
		Page<PurchaseOrderBean> pageBean = purchaseOrderBeanDao.findPage(page, filters);
		List<PurchaseOrderBean> list = pageBean.getResult();
		List<PurchaseOrderBean> retList = new LinkedList<PurchaseOrderBean>();
		if(list != null && list.size() > 0){
			for(PurchaseOrderBean bean : list){
				Integer srcOrderNo = bean.getSrcSoNo();
				OrderErpMapping orderErpMapping = orderErpMappingDao.getById(srcOrderNo);
				if(orderErpMapping != null && orderErpMapping.getErpUsPo() != null){
					bean.setErpUsPo(orderErpMapping.getErpUsPo());
				}
				retList.add(bean);
			}
			pageBean.setResult(retList);
		}
		return pageBean;
	}

	@Transactional(readOnly = true)
	public Page<PurchaseOrderBean> searchPurchaseOrderBean(
			final Page<PurchaseOrderBean> page) {
		Page<PurchaseOrderBean> pageBean = purchaseOrderBeanDao.findPage(page);
		List<PurchaseOrderBean> list = pageBean.getResult();
		List<PurchaseOrderBean> retList = new LinkedList<PurchaseOrderBean>();
		if(list != null && list.size() > 0){
			for(PurchaseOrderBean bean : list){
				Integer srcOrderNo = bean.getSrcSoNo();
				OrderErpMapping orderErpMapping = orderErpMappingDao.getById(srcOrderNo);
				if(orderErpMapping != null && orderErpMapping.getErpUsPo() != null){
					bean.setErpUsPo(orderErpMapping.getErpUsPo());
				}
				retList.add(bean);
			}
			pageBean.setResult(retList);
		}
		return pageBean;
	}

	@Transactional(readOnly = true)
	public List<PurchaseOrderItem> getPurchaseOrderAllItemList(Integer orderNo) {
		return purchaseOrderItemDao.getPurchaseOrderAllItemList(orderNo);
	}

	public String saveSalesOrder(SalesOrderDTO salesOrderDTO,
			List<Integer> orderNoList) throws CloneNotSupportedException {
		StringBuffer result = new StringBuffer();
		Integer userId = SessionUtil.getUserId();
		Date now = new Date();

		if (orderNoList != null && orderNoList.size() > 0) {
			for (Integer orderNo : orderNoList) {
				PurchaseOrder purchaseOrder = purchaseOrderDao.getById(orderNo);
				Integer srcOrderNo = purchaseOrder.getSrcSoNo();
				OrderMain order = orderDao.getById(srcOrderNo);
				OrderMain neworder = (OrderMain) order.clone();
				neworder.setOrderNo(null);
				neworder.setCustNo(salesOrderDTO.getCustNo());
				neworder.setPriority(salesOrderDTO.getPriority());
				neworder.setWarehouseId(salesOrderDTO.getWarehouseId());
				neworder.setExprDate(salesOrderDTO.getExpectedDate());
				neworder.setOrderCurrency(salesOrderDTO.getCurrency());
				neworder.setSubTotal(new BigDecimal(salesOrderDTO.getSubTotal()));
				neworder.setStatus("CC");
				neworder.setCreationDate(now);
				neworder.setModifyDate(now);
				neworder.setCreatedBy(userId);
				neworder.setModifiedBy(userId);
				orderDao.save(neworder);

				List<OrderItem> orderItemList = orderItemDao
						.getOrderAllItemList(srcOrderNo);
				if (orderItemList != null && orderItemList.size() > 0) {
					for (OrderItem orderItem : orderItemList) {
						OrderItem item = (OrderItem) orderItem.clone();
						orderItemDao.getSession().evict(orderItem);
						item.setOrderItemId(null);
						item.setOrderNo(neworder.getOrderNo());
						item.setStatus("CC");
						item.setCreationDate(now);
						item.setModifyDate(now);
						item.setCreatedBy(userId);
						item.setModifiedBy(userId);
						orderItemDao.save(item);
						// *****add by lizhang generate work
						// order*************//
						if (!"CC".equals(orderItem.getStatus())) {
							result.append(orderItem.getOrderItemId())
									.append(" corresponding workorder create fail,status is not CC!\r");
							continue;
						}
						if (!"SERVICE".equalsIgnoreCase(orderItem.getType())) {
							result.append(orderItem.getOrderItemId())
									.append(" corresponding workorder create fail,type is not Service!\r");
							continue;
						}
						List<WorkOrder> workOrderList = this.workOrderDao.findByPO(srcOrderNo,orderItem.getItemNo());
						if(workOrderList==null) {
							workOrderList = this.workOrderDao.findBySNAndSIN(srcOrderNo,orderItem.getItemNo());
						}
						WorkOrder workOrder = workOrderList!=null&&workOrderList.size()>0?workOrderList.get(0):null;
						String message = judgeItem(orderItem.getOrderNo(),orderItem.getItemNo());
						if (workOrder == null&&!"no".equals(message)) {
							workOrder = new WorkOrder();
							if(message!=null&&!"ok".equals(message)) {
								workOrder.setAltOrderNo(message);
							}
							workOrder.setType("Standard");
							workOrder.setStatus("New");
							workOrder.setSource("SALES ORDER");
							workOrder.setSoNo(srcOrderNo);
							workOrder.setSoItemNo(orderItem.getItemNo());
							String type = orderItem.getType();
							Integer clsId = orderItem.getClsId();
							WorkCenterAssigned workCenterAssigned = this.workCenterAssignedDao
									.findByTypeAndCId(type, clsId,
											order.getWarehouseId());
							if (workCenterAssigned == null
									|| workCenterAssigned.getWorkCenter() == null) {
								result.append(orderItem.getOrderItemId())
										.append(" corresponding workorder create fail!WorkCenter is null\r");
								continue;
							}
							workOrder
									.setWorkCenterId(workCenterAssigned != null
											&& workCenterAssigned
													.getWorkCenter() != null ? workCenterAssigned
											.getWorkCenter().getId() : 0);
							workOrder.setWarehouseId(order.getWarehouseId());
							workOrder.setItemType(type);
							workOrder.setClsId(clsId);
							workOrder.setOrderDate(new java.sql.Date(new Date().getTime()));
							workOrder.setExprDate(new java.sql.Date(DateUtils.dayBefore2Date(15).getTime()));
							workOrder.setPriority("Medium");
							workOrder.setCatalogNo(orderItem.getCatalogNo());
							workOrder.setItemName(orderItem.getName());
							workOrder.setQuantity(orderItem.getQuantity());
							workOrder.setQtyUom(orderItem.getQtyUom());
							workOrder.setSize(orderItem.getSize());
							workOrder.setSizeUom(orderItem.getSizeUom());
							if (order.getGsCoId() != null) {
								workOrder.setCompanyId(Short.parseShort(String
										.valueOf(order.getGsCoId())));
							}
							workOrder.setCreatedBy(SessionUtil.getUserId());
							Integer seqNo = this.workOrderDao.getTodayMaxSeqNo();
							workOrder.setSeqNo(seqNo!=null?seqNo++:1);
						}
						workOrder.setModifyDate(new Date());
						workOrder.setModifiedBy(SessionUtil.getUserId());
						this.workOrderDao.save(workOrder);
						if(StringUtils.isEmpty(workOrder.getAltOrderNo())) {
							workOrder.setAltOrderNo(String.valueOf(workOrder.getOrderNo()));
							this.workOrderDao.save(workOrder);
						}
						// **********************************end******************//
					}
				}
			}
		}
		return result.toString();
	}

	public void savePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO,
			List<Integer> orderNoList) {
		Integer userId = SessionUtil.getUserId();
		Date now = new Date();

		if (orderNoList != null && orderNoList.size() > 0) {
			for (Integer orderNo : orderNoList) {
				// save PurchaseOrder
				PurchaseOrder purchaseOrder = dozer.map(purchaseOrderDTO,
						PurchaseOrder.class);
				purchaseOrder.setStatus("CC");
				purchaseOrder.setSrcSoNo(orderNo);
				purchaseOrder.setOrderType("Standard Order");
				Integer warehouseId = purchaseOrder.getWarehouseId();
				Integer companyId = warehouseDao.getById(warehouseId)
						.getCompanyId();
				purchaseOrder.setCompanyId(companyId);
				purchaseOrder.setCreatedBy(userId);
				purchaseOrder.setModifiedBy(userId);
				purchaseOrder.setCreationDate(now);
				purchaseOrder.setModifyDate(now);
				purchaseOrderDao.save(purchaseOrder);

				// update OrderMain
				// OrderMain order = orderDao.getById(orderNo);
				// order.setStatus(OrderStatusType.VC.value());
				// order.setModifiedBy(userId);
				// order.setModifyDate(now);

				// save PurchaseOrderItem
				List<OrderItem> orderItemList = orderItemDao
						.getOrderAllItemList(orderNo);
				if (orderItemList != null && orderItemList.size() > 0) {
					for (OrderItem orderItem : orderItemList) {
						PurchaseOrderItem purchaseOrderItem = new PurchaseOrderItem();
						purchaseOrderItem
								.setCatalogNo(orderItem.getCatalogNo());
						purchaseOrderItem.setClsId(orderItem.getClsId());
						purchaseOrderItem.setCost(orderItem.getCost());
						purchaseOrderItem.setItemNo(orderItem.getItemNo());
						purchaseOrderItem
								.setOrderNo(purchaseOrder.getOrderNo());
						purchaseOrderItem.setQtyUom(orderItem.getQtyUom());
						purchaseOrderItem.setQuantity(orderItem.getQuantity());
						purchaseOrderItem.setSize(orderItem.getSize());
						purchaseOrderItem.setType(orderItem.getType());
						purchaseOrderItem.setStatus(OrderStatusType.VC.value());
						purchaseOrderItem.setCreatedBy(userId);
						purchaseOrderItem.setModifiedBy(userId);
						purchaseOrderItem.setCreationDate(now);
						purchaseOrderItem.setModifyDate(now);
						purchaseOrderItemDao.save(purchaseOrderItem);
					}
				}
			}
		}
	}

	public Integer savePurchaseOrder(OrderMain order, Integer orderNo) {
		Integer userId = SessionUtil.getUserId();
		Date now = new Date();

		// save PurchaseOrder
		PurchaseOrder purchaseOrder = dozer.map(order, PurchaseOrder.class);
		purchaseOrder.setStatus("NW");
		purchaseOrder.setOrderNo(null);
		purchaseOrder.setSrcSoNo(orderNo);
		purchaseOrder.setOrderType("Standard Order");
		Integer warehouseId = purchaseOrder.getWarehouseId();
		Integer companyId = warehouseDao.getById(warehouseId).getCompanyId();
		purchaseOrder.setCompanyId(companyId);
		purchaseOrder.setCurrency(order.getOrderCurrency());
		purchaseOrder.setVendorNo(1);
		purchaseOrder.setCreatedBy(userId);
		purchaseOrder.setModifiedBy(userId);
		purchaseOrder.setPurchaseContact(userId);
		purchaseOrder.setCreationDate(now);
		purchaseOrder.setModifyDate(now);
		purchaseOrder.setReceivingFlag("0");
		purchaseOrderDao.save(purchaseOrder);

		// update OrderMain
		// OrderMain order = orderDao.getById(orderNo);
		// order.setStatus(OrderStatusType.VC.value());
		// order.setModifiedBy(userId);
		// order.setModifyDate(now);

		// save PurchaseOrderItem
		double grandTotal = 0.00;
		List<OrderItem> orderItemList = orderItemDao
				.getOrderAllItemList(orderNo);
		if (orderItemList != null && orderItemList.size() > 0) {
			for (OrderItem orderItem : orderItemList) {
				PurchaseOrderItem purchaseOrderItem = new PurchaseOrderItem();
				purchaseOrderItem.setCatalogNo(orderItem.getCatalogNo());
				purchaseOrderItem.setClsId(orderItem.getClsId());
				purchaseOrderItem.setName(orderItem.getName());
				purchaseOrderItem.setCost(orderItem.getCost());
				purchaseOrderItem.setItemNo(orderItem.getItemNo());
				purchaseOrderItem.setOrderNo(purchaseOrder.getOrderNo());
				purchaseOrderItem.setQtyUom(orderItem.getQtyUom());
				purchaseOrderItem.setQuantity(orderItem.getQuantity());
				purchaseOrderItem.setSize(orderItem.getSize());
				purchaseOrderItem.setType(orderItem.getType());
				purchaseOrderItem.setStatus(OrderStatusType.OP.value());
				purchaseOrderItem.setSizeUom(orderItem.getSizeUom());
				purchaseOrderItem.setUnitPrice(orderItem.getUnitPrice());
				purchaseOrderItem.setDiscount(orderItem.getDiscount());
				purchaseOrderItem.setTax(orderItem.getTax());
				purchaseOrderItem.setCreatedBy(userId);
				purchaseOrderItem.setModifiedBy(userId);
				purchaseOrderItem.setCreationDate(now);
				purchaseOrderItem.setModifyDate(now);
				if(orderItem.getQuantity() != null && orderItem.getUnitPrice() != null){
					grandTotal += orderItem.getQuantity()*orderItem.getUnitPrice();
				}
				purchaseOrderItemDao.save(purchaseOrderItem);
				orderItemDao.getSession().evict(orderItem);
			}
		}
		purchaseOrder.setSubTotal(grandTotal);
		return purchaseOrder.getOrderNo();
	}

	public void saveNanjingSalesOrder(OrderMain order, Integer orderNo, Integer srcPoNo) {
		Integer userId = SessionUtil.getUserId();
		Date now = new Date();

		//Customer customer = customerDao.getById(order.getCustNo());
		String accountCode;
		
		// save PurchaseOrder
		MfgOrder mfgOrder = dozer.map(order, MfgOrder.class);
		mfgOrder.setStatus("NW");
		mfgOrder.setOrderNo(null);
		mfgOrder.setSrcSoNo(orderNo);
		mfgOrder.setSrcPoNo(srcPoNo);
		Integer billAddrToId = order.getBilltoAddrId();
		OrderAddress billToAddr = orderAddressDao.findByAddrId(billAddrToId);
		accountCode = billTerritoryDao.getAccountCode(billToAddr.getCountry(), billToAddr.getState(), billToAddr.getZipCode());
		mfgOrder.setBillAccCode(accountCode);
//		if(customer != null){
//			accountCode = billTerritoryDao.getAccountCode(customer.getCountry(), customer.getState(), customer.getZipCode());
//			mfgOrder.setBillAccCode(accountCode);
//		}
		
		//mfgOrder.setOrderType("Standard Order");
//		Integer warehouseId = mfgOrder.getWarehouseId();
//		Integer companyId = warehouseDao.getById(warehouseId).getCompanyId();
		mfgOrder.setCompanyId(2);
		//mfgOrder.setCurrency(order.getOrderCurrency());
		//mfgOrder.setVendorNo(1);
		mfgOrder.setCreatedBy(userId);
		mfgOrder.setModifiedBy(userId);
		mfgOrder.setCreationDate(now);
		mfgOrder.setModifyDate(now);
		mfgOrderDao.save(mfgOrder);

		// update OrderMain
		// OrderMain order = orderDao.getById(orderNo);
		// order.setStatus(OrderStatusType.VC.value());
		// order.setModifiedBy(userId);
		// order.setModifyDate(now);

		// save PurchaseOrderItem
		double grandTotal = 0.00;
		String type = "";
		String clsName = "";
		List<OrderItem> orderItemList = orderItemDao
				.getOrderAllItemList(orderNo);
		if (orderItemList != null && orderItemList.size() > 0) {
			for (OrderItem orderItem : orderItemList) {
				MfgOrderItem mfgOrderItem = new MfgOrderItem();
				mfgOrderItem.setCatalogNo(orderItem.getCatalogNo());
				mfgOrderItem.setClsId(orderItem.getClsId());
				mfgOrderItem.setUnitPrice(orderItem.getUnitPrice());
				mfgOrderItem.setItemNo(orderItem.getItemNo());
				mfgOrderItem.setOrderNo(mfgOrder.getOrderNo());
				mfgOrderItem.setQtyUom(orderItem.getQtyUom());
				mfgOrderItem.setQuantity(orderItem.getQuantity());
				mfgOrderItem.setSize(orderItem.getSize());
				mfgOrderItem.setType(orderItem.getType());
				mfgOrderItem.setStatus(OrderStatusType.VC.value());
				mfgOrderItem.setCreatedBy(userId);
				mfgOrderItem.setModifiedBy(userId);
				mfgOrderItem.setCreationDate(now);
				mfgOrderItem.setModifyDate(now);
				if(orderItem.getQuantity() != null && orderItem.getCost() != null){
					grandTotal += orderItem.getQuantity()*orderItem.getCost();
				}
				mfgOrderItemDao.save(mfgOrderItem);
				orderItemDao.getSession().evict(orderItem);
			}
			for(int i = 0; i<orderItemList.size();i++){
				orderItem = orderItemList.get(i);
				if(orderItem.getStatus() != "CN"){
					type = orderItem.getType();
					Integer clsId = orderItem.getClsId();
					if("PRODUCT".equalsIgnoreCase(orderItem.getType())){
						ProductClass productClass = productClassDao.getById(clsId);
						if(productClass != null){
							clsName = productClass.getName();
						}
					}else{
						ServiceClassification serviceClassification = serviceClassificationDao.getById(clsId);
						if(serviceClassification != null){
							clsName = serviceClassification.getName();
						}
					}
					break;
				}
			}
		}
		mfgOrder.setSubTotal(grandTotal);
		mfgOrder.setType(type+ " - " + clsName);
	}
	
	/**
	 * 判断当前item能否生成work order
	 */
	private String judgeItem(Integer orderNo,Integer itemNo) {
		OrderItem item = this.orderItemDao.getOrderItem(orderNo, itemNo);
		if(QuoteItemType.PRODUCT.value().equals(item.getType())) {
			return "ok";
		}else if(item.getParentId()==null||item.getParentId()==0) {
			return "ok";
		}
		OrderItem parentOrderItem = this.orderItemDao.getById(item.getParentId());
		if(parentOrderItem==null) {
			return "ok";
		}
		if(parentOrderItem.getClsId()==2||parentOrderItem.getClsId()==14||parentOrderItem.getClsId()==16||
				parentOrderItem.getClsId()==18||parentOrderItem.getClsId()==32) {
			List<WorkOrder> workOrderList = this.workOrderDao.findByPO(parentOrderItem.getOrderNo(),parentOrderItem.getItemNo());
			if(workOrderList==null) {
				workOrderList = this.workOrderDao.findBySNAndSIN(parentOrderItem.getOrderNo(), parentOrderItem.getItemNo());
			}
			WorkOrder workOrder = workOrderList!=null&&workOrderList.size()>0?workOrderList.get(0):null;
			if(workOrder==null) {
				return "no";
			} else {
				String index = "";
				if(item.getCatalogNo().indexOf("-")!=-1) {
					index = item.getCatalogNo().split("-")[1];
				}
				return String.valueOf(workOrder.getOrderNo())+"-"+index;
			}
		}
		return "ok";
	}
	
	
	@Transactional(readOnly = true)
	public Page<MfgOrderDTO> searchMfgOrderDTO(Page<MfgOrderDTO> page,List<PropertyFilter> filters){
		return mfgOrderDao.searchMfgOrderDTO(page, filters);
	}
	/*
	 * 
	 */
	public Page<com.genscript.gsscm.purchase.dto.PurchaseOrderDTO> searchPurchaseOrderOfPrint(Page<com.genscript.gsscm.purchase.dto.PurchaseOrderDTO> pageDTO){
		Page<PurchaseOrder> page = new Page<PurchaseOrder>();
		//
		page.setPageNo(pageDTO.getPageNo());
		page.setPageSize(pageDTO.getPageSize());
		
		page = this.purchaseOrderDao.searchPoOfPrint(page);
		if(page.getResult()!=null&&!page.getResult().isEmpty()){
			List<com.genscript.gsscm.purchase.dto.PurchaseOrderDTO> dtoList = new ArrayList<com.genscript.gsscm.purchase.dto.PurchaseOrderDTO>();
			for(PurchaseOrder po: page.getResult()){
				com.genscript.gsscm.purchase.dto.PurchaseOrderDTO dto = new com.genscript.gsscm.purchase.dto.PurchaseOrderDTO();
				dto = this.dozer.map(po, com.genscript.gsscm.purchase.dto.PurchaseOrderDTO.class);
				String greenAccFlag = this.shippingService.getGreenAccFlag(dto.getSrcSoNo());
				dto.setGreenAccFlag(greenAccFlag);
				dtoList.add(dto);
			}
			pageDTO.setResult(dtoList);
		}
		
		pageDTO.setPageNo(page.getPageNo());
		pageDTO.setTotalCount(page.getTotalCount());
		pageDTO.setPageSize(10);
		return  pageDTO;
	}
	
	public List<PurchaseOrderItem> searchPurchaseOrderItemOfPrint(Integer orderNo){
		return this.purchaseOrderItemDao.searchPurchaseOrderItemOfPrint(orderNo);
	}

}