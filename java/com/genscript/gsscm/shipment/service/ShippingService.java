package com.genscript.gsscm.shipment.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.ExceptionOut;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.common.util.Arith;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.FilelUtil;
import com.genscript.gsscm.common.util.PrintUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dao.CustomerNoteDao;
import com.genscript.gsscm.customer.dao.NoteDocumentDao;
import com.genscript.gsscm.customer.dao.OrganizationNoteDao;
import com.genscript.gsscm.customer.dao.PaymentTermDao;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.entity.CustomerNote;
import com.genscript.gsscm.customer.entity.NoteDocument;
import com.genscript.gsscm.customer.entity.OrganizationNote;
import com.genscript.gsscm.customer.entity.PaymentTerm;
import com.genscript.gsscm.inventory.dao.WarehouseDao;
import com.genscript.gsscm.inventory.entity.PickingLogs;
import com.genscript.gsscm.inventory.entity.Reservation;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.manufacture.dao.ManuDocumentDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderLotDao;
import com.genscript.gsscm.manufacture.dto.WorkOrderDTO;
import com.genscript.gsscm.manufacture.entity.ManuDocument;
import com.genscript.gsscm.manufacture.entity.WorkOrderLot;
import com.genscript.gsscm.order.dao.OrderAddressDao;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.dao.OrderErpMappingDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.dao.OrderNoteDao;
import com.genscript.gsscm.order.dao.OrderPackageDao;
import com.genscript.gsscm.order.dao.OrderPackageItemDao;
import com.genscript.gsscm.order.dao.OrderProcessLogDao;
import com.genscript.gsscm.order.dao.PaymentVoucherDao;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.OrderErpMapping;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.entity.OrderPackage;
import com.genscript.gsscm.order.entity.OrderPackageItem;
import com.genscript.gsscm.order.entity.OrderProcessLog;
import com.genscript.gsscm.order.entity.PaymentVoucher;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.DocumentsDao;
import com.genscript.gsscm.product.dao.ProductClassDao;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.dao.ShipConditionDao;
import com.genscript.gsscm.product.entity.Documents;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.product.entity.ProductShipCondition;
import com.genscript.gsscm.product.entity.ShipCondition;
import com.genscript.gsscm.purchase.dao.PurchaseOrderDao;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.quoteorder.dto.InstructionDTO;
import com.genscript.gsscm.serv.dao.ServiceClassificationDao;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.serv.dao.ServiceShipConditionDao;
import com.genscript.gsscm.serv.entity.ServiceClassification;
import com.genscript.gsscm.serv.entity.ServiceShipCondition;
import com.genscript.gsscm.shipment.dao.PickingLogDao;
import com.genscript.gsscm.shipment.dao.ShipPackageDao;
import com.genscript.gsscm.shipment.dao.ShipPackageLineDao;
import com.genscript.gsscm.shipment.dao.ShipZoneDao;
import com.genscript.gsscm.shipment.dao.ShipmentLinesDao;
import com.genscript.gsscm.shipment.dao.ShipmentsDao;
import com.genscript.gsscm.shipment.dao.ShippingChargeLogDao;
import com.genscript.gsscm.shipment.dao.ShippingDao;
import com.genscript.gsscm.shipment.dao.ShippingPackagesDao;
import com.genscript.gsscm.shipment.dto.BankCardDTO;
import com.genscript.gsscm.shipment.dto.PrintPickListDTO;
import com.genscript.gsscm.shipment.dto.PrintPickListsDTO;
import com.genscript.gsscm.shipment.dto.ShipDocDTO;
import com.genscript.gsscm.shipment.dto.ShipPackageDTO;
import com.genscript.gsscm.shipment.dto.ShipPackageLineDTO;
import com.genscript.gsscm.shipment.dto.ShipmentsDTO;
import com.genscript.gsscm.shipment.dto.ShippingPackageLinesDTO;
import com.genscript.gsscm.shipment.dto.ViewPackingSlipDTO;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipPackageLines;
import com.genscript.gsscm.shipment.entity.ShipZone;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.genscript.gsscm.shipment.entity.ShipmentLine;
import com.genscript.gsscm.shipment.entity.ShippingChargeLog;
import com.genscript.gsscm.ws.WSException;

@Service
@Transactional
public class ShippingService {
	@Autowired
	private ShippingDao shippingdao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private ShipPackageLineDao lineDao;
	@Autowired
	private ShippingPackagesDao packagesdao;
	@Autowired
	private OrderItemDao orderItemsDao;
	@Autowired
	private OrderNoteDao orderNotesDao;
	@Autowired
	private OrderPackageDao orderPackageDao;
	@Autowired
	private ShipPackageLineDao shipPageageLineDao;
	@Autowired
	private PickingLogDao pickingLogDao;
	@Autowired
	private OrderAddressDao orderAddressDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderErpMappingDao orderErpMappingDao;
	@Autowired
	private OrderProcessLogDao orderProcessLogDao;
	@Autowired
	private ShipmentsDao shipmentDao;
	@Autowired
	private ShipPackageDao shipPackageDao;
	@Autowired
	private WarehouseDao warehouseDao;
	@Autowired
	private ShipMethodService shipMethodService;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ServiceDao servDao;
	@Autowired
	private ShipConditionDao productShipConditionDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private PaymentTermDao paymentTermDao;
	@Autowired
	private ShipZoneDao shipZoneDao;
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	@Autowired
	private ShipmentLinesDao shipmentLinesDao;
	@Autowired
	private WorkOrderDao workOrderDao;
	@Autowired
	private ManuDocumentDao manuDocumentDao;
	@Autowired
	private ServiceShipConditionDao serviceShipConditionDao;
	@Autowired
	private PaymentVoucherDao paymentVoucherDao;
	@Autowired
	private DocumentsDao documentsDao;

	@Autowired
	private ShipPackageLineService shipPackageLineService;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private PublicService publicService;

	@Autowired
	private ShippingChargeLogDao shippingChargeLogDao;
	@Autowired
	private OrderPackageItemDao orderPackageItemDao;

	@Autowired
	private ShipmentLinesService shipmentLineService;
	@Autowired
	private ProductClassDao productClassDao;
	@Autowired
	private ServiceClassificationDao serviceClassificationDao;
	@Autowired
	private OrganizationNoteDao organizationNoteDao;
	@Autowired
	private CustomerNoteDao customerNoteDao;
	@Autowired
	private NoteDocumentDao noteDocumentDao;
	@Autowired
	private WorkOrderLotDao workOrderLotDao;

	@Autowired
	private FileService fileService;

	public OrderItemDao getOrderItemsDao() {
		return orderItemsDao;
	}

	public void setOrderItemsDao(OrderItemDao orderItemsDao) {
		this.orderItemsDao = orderItemsDao;
	}

	public OrderNoteDao getOrderNotesDao() {
		return orderNotesDao;
	}

	public void setOrderNotesDao(OrderNoteDao orderNotesDao) {
		this.orderNotesDao = orderNotesDao;
	}

	/**
	 * 寰楀埌Order鍒楄〃(浠tatus鍒嗙粍鏄剧ず)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getListByClert() {
		List list = this.shippingdao.getListByClert();
		return list;
	}

	/**
	 * 列表显示符合条件的人员
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getLoginName(Integer userId) {
		List list = this.shippingdao.getLoginName(userId);
		return list;
	}

	/**
	 * 得到Order的list
	 * 
	 * @return
	 */
	public List<OrderMain> getTypeList() {
		return this.shippingdao.getTypeList();
	}

	/**
	 * 得到Priority的list
	 * 
	 * @return
	 */
	public List<PbDropdownListOptions> getPriorityList() {
		return this.publicService.getDropdownList("ORDER_PRIORITY");
		// return this.shippingdao.getPriorityList();
	}

	/**
	 * 获取仓库的List
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getListByWareHouse() {
		List list = this.warehouseDao.getAll();
		return list;
	}

	/*
	 * shipping 是否是绿色定单
	 */
	public String getGreenAccFlag(Integer orderNo) {
		List<Customer> customerList = this.customerDao
				.searchCustomerByOrderNo(orderNo);

		return customerList.get(0).getGreenAccFlag();
	}

	/**
	 * 得到美国仓库的list
	 * 
	 * @param page
	 * @param srch
	 * @return
	 */
	public Page<ShipmentsDTO> getShipmentList(Page<Shipment> page,
			ShipmentsDTO srch) {
		Page<ShipmentsDTO> retPage = new Page<ShipmentsDTO>();
		try {
			Date date = new Date();
			/*
			 * Page<OrderMain> orderPage = new Page<OrderMain>(); if
			 * (!orderPage.isOrderBySetted()) { orderPage.setOrderBy("orderNo");
			 * orderPage.setOrder(Page.DESC); } // 设置默认每页显示记录条数 if
			 * (orderPage.getPageSize() == null ||
			 * orderPage.getPageSize().intValue() < 1) {
			 * orderPage.setPageSize(20); }
			 * orderPage.setPageNo(page.getPageNo());
			 * orderPage.setPageSize(page.getPageSize()); orderPage =
			 * this.orderDao.searchOrderByShipmentLine(orderPage);
			 * retPage.setTotalCount(orderPage.getTotalCount());
			 * retPage.setPageSize(orderPage.getPageSize());
			 * if(orderPage.getResult()!=null){ List<ShipmentsDTO> retList = new
			 * ArrayList<ShipmentsDTO>(); for(OrderMain
			 * order:orderPage.getResult()){ ShipmentsDTO dto = new
			 * ShipmentsDTO(); //dto.setItemNo(obj[10]+"");
			 * dto.setOrderNo(order.getOrderNo()+"");
			 * dto.setStatus(order.getStatus());
			 * dto.setOrderType(order.getOrderType());
			 * dto.setPriority(order.getPriority()); List<ShipmentLine>
			 * shipmentLineList = this.shipmentLinesDao.findBy("order.orderNo",
			 * order.getOrderNo()); if(shipmentLineList!=null){
			 * dto.setShipmentId
			 * (shipmentLineList.get(0).getShipments().getShipmentId());
			 * dto.setShipmentNo
			 * (shipmentLineList.get(0).getShipments().getShipmentNo());
			 * dto.setWarehouseName
			 * (shipmentLineList.get(0).getShipments().getWareHouse
			 * ().getName()); } List<OrderItem> orderItemlist =
			 * this.orderItemsDao.findBy("orderNo",
			 * Integer.valueOf(dto.getOrderNo())); OrderItem orderItem = null;
			 * if(orderItemlist!=null&&orderItemlist.get(0)!=null){ orderItem =
			 * orderItemlist.get(0); if(orderItem.getShipMethod()!=null){
			 * dto.setShipVia
			 * (this.shipMethodService.getShipMethodById(orderItem.
			 * getShipMethod()).getName()); } } OrderAddress orderAddresses =
			 * this.getOrderAddresses(null, null, orderItem);
			 * if(orderAddresses!=null){
			 * dto.setShipTo(orderAddresses.getFirstName
			 * ()+" "+orderAddresses.getLastName());
			 * 
			 * } retList.add(dto); } retPage.setResult(retList); }
			 */
			retPage = this.shippingdao.getShipmentList(page, srch);
			if (retPage.getResult() != null) {
				for (ShipmentsDTO dto : retPage.getResult()) {
					if (dto.getOrderNo() != null) {
						List<OrderItem> orderItemlist = this.orderItemsDao
								.findBy("orderNo", Integer.valueOf(dto
										.getOrderNo()));
						OrderItem orderItem = null;
						Integer leadTime=null;
						if (orderItemlist != null
								&& orderItemlist.get(0) != null) {
							orderItem = orderItemlist.get(0);
							if (orderItem.getShipMethod() != null) {
								dto.setShipVia(this.shipMethodService
										.getShipMethodById(
												orderItem.getShipMethod())
										.getName());
							}
							if (orderItem.getClsId() != null
									&& orderItem.getType() != null) {
								if (orderItem.getType().equals("PRODUCT")) {
									ProductClass pc = this.productClassDao
											.getById(orderItem.getClsId());
									if (pc != null) {
										dto.setServiceType("PRODUCT - "
												+ pc.getName());
									}
								} else if (orderItem.getType()
										.equals("SERVICE")) {
									ServiceClassification sc = this.serviceClassificationDao
											.getById(orderItem.getClsId());
									if (sc != null) {
										dto.setServiceType("SERVICE - "
												+ sc.getName());
									}
								}
							}
							for (OrderItem item : orderItemlist) {
								if (item.getType().equals("PRODUCT")) {
									Product product = this.productDao
											.findUniqueBy("catalogNo", item
													.getCatalogNo());
									if (product != null
											&& product.getLeadTime() != null
											&& (leadTime==null||product.getLeadTime() < leadTime)) {
										leadTime = product.getLeadTime();
									}
								} else if (item.getType().equals("SERVICE")) {
									com.genscript.gsscm.serv.entity.Service service = this.servDao
											.findUniqueBy("catalogNo", item
													.getCatalogNo());
									if (service != null
											&& service.getLeadTime() != null
											&& (leadTime==null||service.getLeadTime() < leadTime)) {
										leadTime = service.getLeadTime();
									}
								}
							}
						}

						String greenAccFlag = this.getGreenAccFlag(Integer
								.valueOf(dto.getOrderNo()));
						dto.setGreenAccFlag(greenAccFlag);
						OrderAddress orderAddresses = this.getOrderAddresses(
								null, null, orderItem);
						if (orderAddresses != null) {
							dto.setShipTo(orderAddresses.getFirstName() + " "
									+ orderAddresses.getLastName() + ",</br> "
									+ orderAddresses.getState() + ", "
									+ orderAddresses.getCountry());

						}
						OrderProcessLog log = this.orderProcessLogDao
								.getOrderLastConfirm(Integer.valueOf(dto
										.getOrderNo()));
						Integer overDue = 0;
						if (log != null && log.getProcessDate() != null) {
							overDue = Integer.valueOf(DateUtils.caculate2Days(
									date, log.getProcessDate()));
							overDue = overDue - leadTime;
						}
						if (overDue > 0) {
							dto.setOverDuo(overDue.toString());
						} else {
							dto.setOverDuo("0");
						}
						List<PurchaseOrder> poList = this.purchaseOrderDao
								.findBy("srcSoNo", Integer.valueOf(dto
										.getOrderNo()));
						if (poList != null && !poList.isEmpty()) {
							dto.setPoNo(poList.get(0).getOrderNo().toString());
							dto.setReceivingFlag(poList.get(0).getReceivingFlag()==null?"0":poList.get(0).getReceivingFlag());
						}
						OrderErpMapping orderErpMapping = orderErpMappingDao
								.getById(Integer.valueOf(dto.getOrderNo()));
						if (orderErpMapping != null
								&& orderErpMapping.getErpUsPo() != null) {
							dto.setUsPoNo(orderErpMapping.getErpUsPo());
						}
						// System.out.println(dto.getOverDuo());
						// dto.setShipTo(this.getShipTo(Integer.valueOf(dto.getOrderNo()),
						// Integer.valueOf(dto.getItemNo()), null));
					}
				}
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return retPage;
	}

	/**
	 * Remove按钮功能
	 * 
	 * @param orderNo
	 * @param itemNo
	 * @param packageId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ShipmentsDTO> getShipmentsDTOList(String orderNo,
			String itemNo, String packageId) {
		return this.shippingdao.getShiimentsDtoList(orderNo, itemNo, packageId);
	}

	/**
	 * 保存ShipPackage、ShipPackageLine对象
	 * 
	 * @param list
	 * @param packageIds
	 * @param listOip
	 * @param userId
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String saveShipping(List<ShipmentsDTO> list, String packageIds,
			List listOip, Integer userId, Integer shipmentId,
			List<ShipmentsDTO> packagelist) {
		String flag = "0";
		// java.util.Date date = new java.util.Date();
		// Date date_ = new Date(date.getTime());
		try {
			/*
			 * for(ShipmentsDTO dto : list){ if(dto!=null){
			 * if(dto.getIsNew()!=null&&dto.getIsNew().equals("1")){
			 * if(dto.getListPackageLine()!=null){ ShipPackage packages = new
			 * ShipPackage();
			 * if(dto.getIsNew()!=null&&dto.getIsNew().equals("1")){ Integer
			 * count = this.getCount(); packages.setPackageId(count+1);
			 * packages.setPackageNo(count+1+""); }else{
			 * packages.setPackageId(Integer.parseInt(dto.getPackageId())); }
			 * packages.setShiptoAddress(dto.getShipTo());
			 * if(dto.getWarehouseId()==null){ packages.setWarehouseId(1);
			 * }else{ packages.setWarehouseId(dto.getWarehouseId()); }
			 * packages.setInvoicedFlag("Y");
			 * packages.setShippingClerk(dto.getShippingClerk());
			 * packages.setStatus(dto.getPackageStatus());
			 * packages.setCompanyId(1);
			 * packages.setBillableWeight(dto.getBillableWeight());
			 * packages.setShipMethod(dto.getShipmentId());
			 * packages.setCreatedBy(userId+"");
			 * packages.setCreationDate(date_);
			 * packages.setModifiedBy(userId+""); packages.setModifyDate(date_);
			 * if(shipmentId!=null){ Shipment shipment =
			 * this.shipmentDao.getById(shipmentId);
			 * packages.setShipments(shipment); }
			 * System.out.println("??>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			 * this.packagesdao.saveShipPackage(packages);
			 * System.out.println("?????????????????????????????/");
			 * //this.shippingdao.flush();
			 * System.out.println("??>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			 * //this.shipPackageDao.save(shipPackage); for(ShipPackageLineDTO
			 * lineDTO:dto.getListPackageLine()){ ShipPackageLines line =
			 * this.dozer.map(lineDTO, ShipPackageLines.class); ShipmentLine
			 * shipmentLine =
			 * this.shippingdao.getLineByOrderNoAndItemNo(dto.getOrderNo(),
			 * dto.getItemNo()); line = new ShipPackageLines();
			 * line.setShipmentLines(shipmentLine);
			 * line.setShipPackages(packages); line.setCreatedBy(userId);
			 * line.setCreationDate(date_); line.setModifiedBy(userId);
			 * line.setModifyDate(date_); if(dto.getReservationId()==null){
			 * line.setReservationId(1); }else{
			 * line.setReservationId(Integer.parseInt(dto.getReservationId()));
			 * } this.shipPageageLineDao.save(line); } } } } }
			 */
			if (packageIds != null && !"".equals(packageIds)) {
				String[] packageId = packageIds.split(",");
				for (int j = 0; j < packageId.length; j++) {
					this.shippingdao.deletePackageLine(Integer
							.parseInt(packageId[j]));
					this.shippingdao.deletePackage(Integer
							.parseInt(packageId[j]));
				}
			}

			for (int i = 0; listOip != null && i < listOip.size(); i++) {
				String str = listOip.get(i) + "";
				String[] strs = str.split(",");
				this.shippingdao.deletePackageLine(strs[2], strs[0], strs[1]);
			}
			ShipPackage packages = null;
			ShipPackageLines pline = null;
			int packageSeq = 1;
			Integer packageId = null;
			for (int i = 0; list != null && i < list.size(); i++) {
				ShipmentsDTO dto = list.get(i);
				java.util.Date date = new java.util.Date();
				Date date_ = new Date(date.getTime());
				if ("line".equals(dto.getType())) {
					ShipmentLine line = this.shippingdao
							.getLineByOrderNoAndItemNo(dto.getOrderNo(), dto
									.getItemNo());
					pline = new ShipPackageLines();
					if (packageId != null) {
						pline.setShipPackages(new ShipPackage());
						pline.getShipPackages().setPackageId(packageId);
					} else {
						pline = new ShipPackageLines();
						pline.setShipPackages(dto.getPackages());
					}
					pline.setShipmentLines(line);
					pline.setReservationId(Integer.parseInt(dto
							.getReservationId()));
					pline.setOrderNo(Integer.parseInt(dto.getOrderNo()));
					pline.setItemNo(Integer.parseInt(dto.getItemNo()));
					pline.setQuantity(Integer.parseInt(dto.getQuantity()));
					pline.setQtyUom(dto.getQtyUom());
					pline.setSize(Double.parseDouble(dto.getSize()));
					pline.setSizeUom(dto.getSizeUom());
					pline.setStatus("Drafted");
					pline.setCreationDate(date_);
					pline.setCreatedBy(userId);
					pline.setModifyDate(date_);
					pline.setModifiedBy(userId);
					List<ShipPackageLines> shipPackageLines = 
						this.lineDao.searchShipPackageLinesOfPack(pline.getShipPackages().getPackageId(), pline.getOrderNo(), pline.getItemNo());
					this.lineDao.getSession().evict(shipPackageLines);
					if(shipPackageLines==null||shipPackageLines.isEmpty()){
						this.lineDao.saveShipPackageLine(pline);
					}else{
						pline = shipPackageLines.get(0);
						if("SERVICE".equalsIgnoreCase(dto.getOrderItemType())){
							pline.setSize(Arith.add((pline.getSize()==null?0.0:pline.getSize()),Double.parseDouble(dto.getSize())));
						}else{
							pline.setQuantity(pline.getQuantity()+Integer.parseInt(dto.getQuantity()));
						}
					}
					this.lineDao.saveShipPackageLine(pline);
					//this.lineDao.flush();
				} else {
					packages = new ShipPackage();
					if (dto.getIsNew() != null && dto.getIsNew().equals("1")) {
						Integer count = this.getCount();

						packages.setPackageNo(count + 1 + "");
					} else {
						packages.setPackageId(Integer.parseInt(dto
								.getPackageId()));
					}
					packages.setShiptoAddress(dto.getShipTo());
					packages.setDeliveryType(dto.getDeliveryType());
					packages.setWarehouseId(dto.getWarehouseId());
					packages.setInvoicedFlag("Y");
					packages.setShippingClerk(dto.getShippingClerkId());
					packages.setStatus(dto.getPackageStatus());
					packages.setZone(dto.getZone());
					packages.setCompanyId(1);
					packages.setShipmentDate(date_);
					packages.setPkgBatchSeq(dto.getPkgBatchSeq());
					packages.setPkgBatchCount(dto.getPkgBatchCount());
					// add by zhanghuibin add rcp_*
					packages.setRcpCity(dto.getRcpCity());
					packages.setRcpCountry(dto.getRcpCountry());
					packages.setRcpFirstName(dto.getRcpFirstName());
					packages.setRcpLastName(dto.getRcpLastName());
					packages.setRcpMidName(dto.getRcpMidName());
					packages.setRcpMobile(dto.getRcpMobile());
					packages.setRcpOrgName(dto.getRcpOrgName());
					packages.setRcpPhone(dto.getRcpPhone());
					packages.setRcpState(dto.getRcpState());
					packages.setRcpZipCode(dto.getRcpZipCode());
					packages.setRcpBusEmail(dto.getRcpBusEmail());
					packages.setRcpAddrClass(dto.getRcpAddrClass());
					packages.setRcpAddrLine1(dto.getRcpAddrLine1());
					packages.setRcpAddrLine2(dto.getRcpAddrLine2());
					packages.setRcpAddrLine3(dto.getRcpAddrLine3());
					packages.setRcpFax(dto.getRcpFax());
					packages.setRcpTitle(dto.getRcpTitle());
					if (dto.getListPackageLine() != null
							&& !dto.getListPackageLine().isEmpty()) {
						/*
						 * packages.setInsuranceValue(new
						 * BigDecimal(Arith.mul(dto.getListPackageLine().size(),
						 * 10)).setScale(2, BigDecimal.ROUND_HALF_UP)); }else{
						 */
						packages.setInsuranceValue(new BigDecimal(10.00));
					}
					
					/*
					 * 如果weight
					 */
					if (dto.getBillableWeight() == null
							|| dto.getBillableWeight() <= 0.0) {
						dto.setBillableWeight(1.0);
					}

					packages.setShipMethod(dto.getShipMethodId());
					// packages.setBaseCharge(0);
					packages.setDeliveryConfirmFee(new BigDecimal(0.00));
					// packages.setPackagingFee(new BigDecimal(0.00));
					packages.setInsuranceCharge(packages.getInsuranceValue());
					packages.setAdtlCustomerCharge(new BigDecimal(0.00));
					packages.setActlCarrCharge(new BigDecimal(0.00));
					// packages.setCarrierCharge(carrierCharge);

					if (userId != null) {
						User user = this.userDao.getById(userId);
						if (user != null) {
							packages.setPacker(user.getEmployee()
									.getEmployeeName());
						}
					}
					packages.setCreatedBy(userId + "");
					packages.setCreationDate(date_);
					packages.setModifiedBy(userId + "");
					packages.setModifyDate(date_);
					if (shipmentId != null) {
						Shipment shipment = this.shipmentDao
								.getById(shipmentId);
						packages.setShipments(shipment);
						packages.setShippingClerk(shipment.getShippingClerk());
					}
					/*
					 * 获取shipping fee 和handing fee
					 */

					// packages.setBillableWeight(weight);
					// packages.setActualWeight(weight);
					packages.setBillableWeight(dto.getBillableWeight());
					ShipPackage shipPackage = this.packagesdao
							.findByPackageId(Integer.parseInt(dto
									.getPackageId()));
					if (shipPackage == null
							|| shipPackage.getActualWeight() == shipPackage
									.getBillableWeight()) {
						packages.setActualWeight(dto.getBillableWeight());
					}
					if (packages.getActualWeight() > 1) {
						packages.setPackageType("Package");
					} else {
						packages.setPackageType("Envelop");
					}
					if (packages.getActualWeight() > 20) {
						packages.setPackageType("Box");
					}
					if (packages.getActualWeight() > 40) {
						packages.setPackageType("Your package");
					}
					if (dto.getCustomerCharge() == null)
						continue;
					packages.setCustomerCharge(new BigDecimal(dto
							.getCustomerCharge()));
					packages.setCarrierCharge(packages.getCustomerCharge());
					packages.setBaseCharge(packages.getCustomerCharge());
					packages.setActlCarrCharge(packages.getCustomerCharge());
					packages.setHandingFee(new BigDecimal(dto.getHandingFee()));
					packages.setPackagingFee(packages.getHandingFee());
					packages.setActlCarrCharge(new BigDecimal(Arith.add(
							packages.getCustomerCharge().doubleValue(),
							packages.getHandingFee().doubleValue())));
					
					if(packages.getRcpBusEmail()!=null&&packages.getRcpBusEmail().length()>7&&packages.getRcpBusEmail().substring(packages.getRcpBusEmail().length()-7, packages.getRcpBusEmail().length()).toUpperCase().equals("VWR.COM")){
						packages.setCiItemDesc("Same as Item real name.");
						packages.setCiItemDescFromorder("Y");
						packages.setCiInsuranceFromorder("Y");
					}else{
						packages.setCiItemDesc("Synthesized salt samples in plastic tube, HTS code is 3822005090,for research use only, not for resale.");
						if (packages.getRcpCountry() != null&&packages.getRcpCountry().equals("AU")){
							String type = "SERVICE";
							String clsIdName = "Gene";
							if(dto.getListPackageLine()!=null&&!dto.getListPackageLine().isEmpty()){
								ShipPackageLineDTO lineDTO = dto.getListPackageLine().get(0);
								if(lineDTO!=null){
									OrderItem orderItem = this.orderItemsDao.getOrderItem(lineDTO.getOrderNo(), lineDTO.getItemNo());
									if(orderItem!=null){
										type = orderItem.getType();
										Integer clsId = orderItem.getClsId();
										if(clsId!=null){
											if(clsId.equals(3)||clsId.equals(4)||clsId.equals(5)
													||clsId.equals(6)||clsId.equals(7)||clsId.equals(8)
													||clsId.equals(9)||clsId.equals(10)||clsId.equals(38)){
												clsIdName="Gene";
											}else if(clsId.equals(1)||clsId.equals(39)||clsId.equals(30)||clsId.equals(31)){
												clsIdName = "Peptide";
											}else if(clsId.equals(2)||clsId.equals(13)){
												clsIdName = "Protein";
											}else if(clsId.equals(11)||clsId.equals(12)||clsId.equals(28)||clsId.equals(36)){
												clsIdName = "Antibody";
											}else{
												clsIdName = "Other";
											}
										}
									}
								}
							}
							//把ITEM中的类型    
							if(type.equals("PRODUCT")){
								packages.setCiItemDesc("\"laboratory reagent\"  HTS: 3822005090,for research use only, not for resale.");
							}else{//service
								if(clsIdName.equals("Gene")){
									packages.setCiItemDesc("\"Purified plasmid DNA sample\" , HTS code is 2934991800,for research use only, not for resale.");
								}else if(clsIdName.equals("Peptide")){
									packages.setCiItemDesc("\"Fully synthetic  Purified Peptide sample\", HTS code is 2924198000,for research use only, not for resale.");
								}else if(clsIdName.equals("Protein")){
									packages.setCiItemDesc("\"protein solution\" , HTS code is 3504001000,for research use only, not for resale.");
								}else if(clsIdName.equals("Antibody")){
									packages.setCiItemDesc("\"Purified antibody sample\" , HTS code is 3002100190,for research use only, not for resale.");
								}else if(clsIdName.equals("Other")){
									packages.setCiItemDesc("Other");
								}else{
									packages.setCiItemDesc("Other");
								}
							}
						}else{
							if(packages.getRcpCountry() != null&&(packages.getRcpCountry().equals("CA")||packages.getRcpCountry().equals("IN")||packages.getRcpCountry().equals("TH"))){
								//packages.setCiItemDescFromorder("Y");
								packages.setCiInsuranceFromorder("Y");
							}
						}	
					}
				
						
					
				

					/*
					 * set CustomerCharge
					 */
					/*
					 * if(dto.getCustomerCharge()!=null){
					 * packages.setCustomerCharge(new
					 * BigDecimal(dto.getCustomerCharge()));
					 * packages.setCarrierCharge(new
					 * BigDecimal(dto.getCustomerCharge()));
					 * packages.setBaseCharge(new
					 * BigDecimal(dto.getCustomerCharge()).setScale(2,
					 * BigDecimal.ROUND_HALF_UP));
					 * packages.setActlCarrCharge(new
					 * BigDecimal(dto.getCustomerCharge()).setScale(2,
					 * BigDecimal.ROUND_HALF_UP)); }else{ BigDecimal costTotal =
					 * new BigDecimal(0); BigDecimal handingFee = new
					 * BigDecimal(0); BigDecimal charge =
					 * BigDecimal.valueOf(0.0); BigDecimal charges =
					 * BigDecimal.valueOf(0.0);
					 * //System.out.println("lineLeagth="
					 * +dto.getListPackageLine().size()); List<Integer> clsIds =
					 * new ArrayList<Integer>(); List<String> itemTypes = new
					 * ArrayList<String>(); List<Integer> totalQtys = new
					 * ArrayList<Integer>(); List<Double> weights = new
					 * ArrayList<Double>(); Double packageAmt = 0.0; String
					 * country= ""; OrderMain order = new OrderMain(); Double
					 * orderAmount = 0.0;
					 * if(dto!=null&&dto.getListPackageLine()!=
					 * null&&!dto.getListPackageLine().isEmpty()){
					 * List<OrderItemDTO> itemDTOList = new
					 * ArrayList<OrderItemDTO>(); Integer orderNo = null;
					 * for(ShipPackageLineDTO lineDTO :
					 * dto.getListPackageLine()){ OrderItem orderItem =
					 * this.getOrderItem(lineDTO.getOrderNo(),
					 * lineDTO.getItemNo()); Double price =
					 * Arith.mul(orderItem.getUnitPrice
					 * (),lineDTO.getQuantity()); orderAmount =
					 * Arith.add(orderAmount,price); OrderItemDTO orderItemDTO =
					 * this.dozer.map(orderItem, OrderItemDTO.class); orderNo =
					 * orderItem.getOrderNo(); OrderAddress orderAddress = null;
					 * System.out.println();
					 * if(orderItemDTO.getShiptoAddrId()!=null
					 * &&orderAddress==null){ orderAddress =
					 * this.orderAddressDao
					 * .getById(orderItemDTO.getShiptoAddrId());
					 * orderItemDTO.setShipToAddress(orderAddress); country =
					 * orderAddress.getCountry(); }
					 * if(orderItemDTO.getShipSchedule()==null){
					 * if(orderItemDTO.getType().equals("PRODUCT")){ Product
					 * product = this.productDao.findUniqueBy("catalogNo",
					 * dto.getCatalogNo());
					 * orderItemDTO.setShipSchedule(product.getLeadTime());
					 * }else if(orderItemDTO.getType().equals("SERVICE")){
					 * com.genscript.gsscm.serv.entity.Service service =
					 * this.servDao.findUniqueBy("catalogNo",
					 * dto.getCatalogNo());
					 * orderItemDTO.setShipSchedule(service.getLeadTime()); } }
					 * itemDTOList.add(orderItemDTO); } order =
					 * this.orderService.getOrder(orderNo);
					 * this.orderService.checkAutoCalPackage( itemDTOList,
					 * packages.getWarehouseId(), packages.getCompanyId());
					 * 
					 * // 对OrderItem list进行分组. Map<String, List<OrderItemDTO>>
					 * packageMap = new HashMap<String, List<OrderItemDTO>>();
					 * int size = itemDTOList.size(); for (int k = 0; k < size;
					 * k++) { OrderItemDTO itemDTO = itemDTOList.get(k); String
					 * key = itemDTO.getType()+itemDTO.getShipSchedule(); if
					 * (packageMap.containsKey(key)) {// 更改已存在Package中的OrderItem
					 * list. List<OrderItemDTO> packageItemList =
					 * packageMap.get(key); packageItemList.add(itemDTO);
					 * packageMap.put(key, packageItemList); } else {//
					 * 新增一个Package. List<OrderItemDTO> packageItemList = new
					 * ArrayList<OrderItemDTO>(); packageItemList.add(itemDTO);
					 * packageMap.put(key, packageItemList); } } //
					 * 先产生内存中的各单个Package及PackageItem list. List<PackageHelper>
					 * packageTempList = new ArrayList<PackageHelper>();
					 * Iterator<Map.Entry<String, List<OrderItemDTO>>> it =
					 * packageMap .entrySet().iterator(); while (it.hasNext()) {
					 * Map.Entry<String, List<OrderItemDTO>> entry = it.next();
					 * List<OrderItemDTO> packageItemList = entry.getValue();
					 * PackageHelper packageHelper =
					 * this.orderService.genPackagePublic(packageItemList,
					 * packages.getWarehouseId());
					 * packageTempList.add(packageHelper); } //
					 * 产生内存中已排序的多个Package list.
					 * System.out.println("countty="+country); ShipPackage
					 * shipPackage = this.orderService.genPackageListPublic(
					 * packageTempList, packages.getCompanyId(),
					 * packages.getWarehouseId(),country,
					 * order.getOrderCurrency(),null,
					 * order.getExchRateDate(),orderAmount
					 * ,order.getBaseCurrency()); costTotal =
					 * shipPackage.getCustomerCharge(); handingFee =
					 * shipPackage.getHandingFee(); for(ShipPackageLineDTO
					 * plDto:dto.getListPackageLine()){ OrderItem orderItem =
					 * this.getOrderItem(plDto.getOrderNo(), plDto.getItemNo());
					 * OrderAddress orderAddress = this.getOrderAddresses(null,
					 * null, orderItem); String country =
					 * orderAddress.getCountry(); Double weight = 0.0; Double
					 * price =
					 * Arith.mul(orderItem.getUnitPrice(),plDto.getQuantity());
					 * Arith.add(packageAmt,price); if
					 * (orderItem.getType().equals("PRODUCT")) { Product po =
					 * this
					 * .shippingdao.findCatalogNoP(orderItem.getCatalogNo());
					 * ProductShipCondition psc = this.shippingdao
					 * .findProductShipConditionP(po.getProductId());
					 * if("US".equals(country)){ weight =
					 * psc.getDomShipWeight(); }else{ weight =
					 * psc.getIntlShipWeight(); } } else if
					 * (orderItem.getType().equals("SERVICE")) {
					 * com.genscript.gsscm.serv.entity.Service sc =
					 * this.shippingdao
					 * .findCatalogNoS(orderItem.getCatalogNo());
					 * ServiceShipCondition ssc = this.shippingdao
					 * .findServiceShipConditionS(sc.getServiceId());
					 * if("US".equals(country)){ weight =
					 * ssc.getDomShipWeight(); }else{ weight =
					 * ssc.getIntlShipWeight(); } } if(clsIds.isEmpty()){
					 * clsIds.add(orderItem.getClsId());
					 * itemTypes.add(orderItem.getType());
					 * totalQtys.add(plDto.getQuantity()); weights.add(weight);
					 * }else{ int is = 0; for(Integer f = 0 ;
					 * f<clsIds.size();f++){
					 * if(clsIds.get(f).equals(orderItem.getClsId
					 * ())&&itemTypes.get(f).equals(orderItem.getType())){
					 * totalQtys.set(f, totalQtys.get(f)+plDto.getQuantity());
					 * if(weights.get(f)<weight){ weights.set(f, weight); } is =
					 * 1; } } if(is==0){ clsIds.add(orderItem.getClsId());
					 * itemTypes.add(orderItem.getType());
					 * totalQtys.add(plDto.getQuantity()); weights.add(weight);
					 * } } } for(Integer f = 0 ; f<clsIds.size();f++){ charges =
					 * charges.add(this.orderQuoteUtil.getShippingFee(packages.
					 * getShipMethod(), packages.getWarehouseId(),
					 * weights.get(f), packages.getZone() , clsIds.get(f),
					 * itemTypes.get(f), totalQtys.get(f).intValue(),
					 * packageAmt, packageSeq)); } //BigDecimal charge
					 * =BigDecimal.valueOf(0.0);//
					 * this.orderQuoteUtil.getShippingFee
					 * (packages.getShipMethod(), packages.getWarehouseId(),
					 * packages.getBillableWeight(), packages.getZone() //,
					 * clsId, itemType, totalQty, dto.getShipAmt(), packageSeq);
					 * Double deliveryConfirmFee= 0.0; Double packagingFee=0.0;
					 * Double actlCarrCharge =
					 * BigDecimal.valueOf(charges.doubleValue()).add(
					 * BigDecimal.valueOf(deliveryConfirmFee))
					 * .add(BigDecimal.valueOf(packagingFee)) .doubleValue();
					 * System.out.println("actlCarrCharge=" +actlCarrCharge); if
					 * (actlCarrCharge == null) { costTotal =
					 * costTotal.add(BigDecimal.valueOf(0)); } else { costTotal
					 * = costTotal.add(BigDecimal.valueOf(actlCarrCharge)); } }
					 * packages.setCustomerCharge(costTotal);
					 * packages.setCarrierCharge(costTotal);
					 * packages.setBaseCharge(costTotal);
					 * packages.setActlCarrCharge(costTotal);
					 * packages.setHandingFee(handingFee); }
					 */

					packageSeq++;
					this.packagesdao.saveShipPackage(packages);
					packageId = packages.getPackageId();
					this.shippingdao.flush();
				}
			}
			for (ShipmentsDTO packagesR : packagelist) {

				ShipPackage shipPackage = this.shipPackageDao.getById(Integer
						.valueOf(packagesR.getPackageId()));
				if (shipPackage != null) {
					shipPackage
							.setBillableWeight(packagesR.getBillableWeight());
					shipPackage.setActualWeight(packagesR.getBillableWeight());
					if (packagesR.getCustomerCharge() == null) {
						packagesR.setCustomerCharge(0.0);
					}
					shipPackage.setCustomerCharge(new BigDecimal(packagesR
							.getCustomerCharge()));
					shipPackage.setCarrierCharge(shipPackage
							.getCustomerCharge());
					shipPackage.setBaseCharge(shipPackage.getCustomerCharge());
					shipPackage.setActlCarrCharge(shipPackage
							.getCustomerCharge());
					if (packagesR.getHandingFee() == null) {
						packagesR.setHandingFee(0.0);
					}
					shipPackage.setHandingFee(new BigDecimal(packagesR
							.getHandingFee()));
					shipPackage.setPackagingFee(shipPackage.getHandingFee());
					shipPackage.setActlCarrCharge(new BigDecimal(Arith.add(
							shipPackage.getCustomerCharge().doubleValue(),
							shipPackage.getHandingFee().doubleValue())));
					this.shipPackageDao.save(shipPackage);
				}
			}
			flag = "1";
		} catch (Exception ex) {
			ex.printStackTrace();
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return flag;
	}

	/**
	 * 根据orderNo和itemNo查询并返回OrderItem对象
	 * 
	 * @param orderNo
	 * @param itemNo
	 * @return
	 */
	public OrderItem getOrderItem(Integer orderNo, Integer itemNo) {
		try {
			return this.shippingdao.getOrderItems(orderNo, itemNo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new OrderItem();
	}

	/**
	 * 中国仓库的shipping列表
	 * 
	 * @param page
	 * @param srch
	 * @return
	 */
	public Page<ShipPackageDTO> getShippackagesList(Page<ShipPackage> page,
			ShipPackageDTO srch) {
		return this.packagesdao.getShippackagesList(page, srch);
	}

	/**
	 * 根据id查询并返回 Reservation对象
	 * 
	 * @param id
	 * @return
	 */
	public Reservation getReservation(String id) {
		return this.shippingdao.getReservation(id);
	}
	
	public List<Reservation> getReservationList(String id) {
		return this.shippingdao.getReservationList(id);
	}

	/**
	 * 查询并返回ShipPackages的max packageId
	 * 
	 * @return Integer
	 */
	public Integer getCount() {
		return this.shippingdao.getCount();
	}

	/**
	 * 查询orderItem列表
	 * 
	 * @param chks
	 * @param flag
	 * @return
	 */
	public List<ShipmentsDTO> getOrderItemList(String chks, Boolean flag) {
		return this.shippingdao.getOrderItemList(chks, flag);
	}

	/**
	 * 查询package列表
	 * 
	 * @param chks
	 * @return
	 */
	public List<ShipmentsDTO> getPackageList(String chks) {
		List<ShipmentsDTO> shipmentsList = this.shippingdao
				.getPackageList(chks);
		if (shipmentsList != null) {
			for (ShipmentsDTO dto : shipmentsList) {
				if (dto.getShipTo() != null) {
					dto
							.setShipToAddress(dto.getShipTo().replace("&#13;&#10;","<br/>"));
				}
			}
		}
		return shipmentsList;
	}

	/**
	 * 查询ShipPackageLines列表
	 * 
	 * @param orderNos
	 * @param itemNos
	 * @param packageIds
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ShipmentsDTO> getShipPackageLinesDTO(String[] orderNos,
			String[] itemNos, String[] packageIds) {
		return this.shippingdao.getShipPackageLinesList(orderNos, itemNos,
				packageIds);
	}

	/*********************************************************************************************
	 * dingtf
	 * 
	 * 
	 ********************************************************************************************/
	@SuppressWarnings("unchecked")
	public List getPackageTn(String orderNo) {
		List list = this.shippingdao.getPackageTn(orderNo);
		return list;
	}

	// add by zhanghuibin
	@SuppressWarnings("unchecked")
	public List getPackageTnList(String orderNo) {
		List list = this.shippingdao.getPackageTnList(orderNo);
		return list;
	}

	/**
	 * 根据orderNo查询ShipPackages和ShipPackageLines
	 * 
	 * @param orderNo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<ShipPackage> getPackagesAndPackageLine(String orderNo) {
		List list = this.shippingdao.getPackagesAndPackageLine(orderNo);
		return list;
	}

	/**
	 * 根据packageId查询 Package
	 * 
	 * @param packageId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getPackageId(String packageId) {
		List sp = this.shippingdao.getPackageId(packageId);
		return sp;
	}

	/**
	 * 根据packageId查询 ShipPackageLines
	 * 
	 * @param packageId
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<ShipPackageLines> getPackageLineId(String packageId) {
		List spkl = this.shippingdao.getPackageLineId(packageId);
		return spkl;
	}

	/**
	 * 根据packageId查询Package
	 * 
	 * @param packageId
	 * @return List
	 */
	// @SuppressWarnings("unchecked")
	// public List getPackageIds(String packageId[],String trackingNo[]) {
	// List sp = null;
	// ShipPackage sps= null;
	// for (int i = 0; i < packageId.length&&i<trackingNo.length; i++) {
	// sp = this.shippingdao.getPackageId(packageId[i]);
	// if(sp !=null && sp.size()>0){
	// sps = (ShipPackage)sp.get(0);
	// }
	// sps.setTrackingNo(trackingNo[i]);
	// sps.setStatus("Ready To Ship");
	// this.packagesdao.updatePackId(sps);
	// }
	// return sp;
	// }
	/**
	 * 根据packageId查询Package
	 * 
	 * @param packageId
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getPackageIds(String packageId[], String trackingNo[],
			Integer shipmentId, Integer userId, String[] shipMethod) {
		List sp = null;
		ShipPackage sps = null;
		shipmentId = null;
		String trackingNos = "";
		for (int i = 0; i < packageId.length && i < trackingNo.length; i++) {
			sp = this.shippingdao.getPackageId(packageId[i]);
			if (sp != null && sp.size() > 0) {
				sps = (ShipPackage) sp.get(0);
			}
			sps.setTrackingNo(trackingNo[i]);
			if (shipMethod != null) {
				sps.setShipMethod(Integer.parseInt(shipMethod[i]));
			}
			sps.setShipmentDate(new Date());
			sps.setSendBy(userId);
			sps.setStatus("Ready To Ship");
			this.packagesdao.updatePackId(sps);
			if (sps.getShipments() != null) {
				shipmentId = sps.getShipments().getShipmentId();
			}
			if (trackingNos.equals("")) {
				trackingNos = trackingNo[i];
			} else {
				trackingNos += "," + trackingNo[i];
			}
		}
		this.shippingdao.flush();
		/*
		 * shipments status 修改
		 */
		if (shipmentId != null) {
			String status = "";
			List<ShipPackage> packageList = this.shipPackageDao.findBy(
					"shipments.shipmentId", shipmentId);
			// System.out.println(shipmentId);
			if (packageList != null) {
				//Integer i = 0;
				//Integer packageqty = 0;
				for (ShipPackage shipPackage : packageList) {
					if (shipPackage.getStatus() != null
							&& (shipPackage.getStatus().equals("Ready To Ship") || shipPackage
									.getStatus().equals("Shipped"))) {
						// System.out.println(shipPackage.getStatus());
						status = "Partial Ready To Ship";
						//i++;
					}
				}
				String chks = "";
				String orderNos = this.shipmentLineService
						.getOrderByShipmentId(shipmentId);
				if (orderNos != "") {
					chks = orderNos;
				}
				List<ShipmentsDTO> dtoList = this.getOrderItemList(chks, true);
				List<ShipmentsDTO> listPackage = this.getPackageList(chks);
				if ((dtoList == null || dtoList.isEmpty())
						&& (listPackage == null || listPackage.isEmpty())) {
					status = "Ready To Ship";
				}
				if (!status.equals("")) {
					Shipment shipments = this.shipmentDao.getById(shipmentId);
					if (shipments != null
							&& !shipments.getStatus().equals("Shiped")&&!shipments.getStatus().equals("Partial Shiped")) {
						this.shipmentDao.getSession().evict(shipments);
						shipments.setModifyDate(new Date());
						shipments.setModifiedBy(userId);
						shipments.setStatus(status);
						/*if (shipments.getShipDate() == null) {
							shipments.setShipDate(new Date());
						}*/
						if (shipments.getTrackingNo() == null
								|| shipments.getTrackingNo().equals("")) {
							shipments.setTrackingNo(trackingNos);
						} else {
							shipments.setTrackingNo(shipments.getTrackingNo()
									+ "," + trackingNos);
						}
						this.shipmentDao.save(shipments);
						if (shipments.getStatus().equals("Ready To Ship")) {
							List<ShipmentLine> slList = this.shipmentLinesDao
									.findBy("shipments.shipmentId", shipments
											.getShipmentId());
							if (slList != null && !slList.isEmpty()) {
								for (ShipmentLine line : slList) {
									this.lineDao
											.modyfyShipmentLinesByLineId(line
													.getLineId());
								}
							}

						}

					}
				}

			}
		}
		return sp;
	}

	/**
	 * 鏍规嵁packageId鏌ヨPackage
	 * 
	 * @param packageId
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getPackageLineIds(String packageId[], String trackingNo[]) {
		List sp = null;
		ShipPackageLines sps = null;
		for (int i = 0; i < packageId.length && i < trackingNo.length; i++) {
			sp = this.shippingdao.getPackageLineId(packageId[i]);
			if (sp != null && sp.size() > 0) {
				sps = (ShipPackageLines) sp.get(0);
			}
			sps.setStatus("Ready To Ship");
			this.lineDao.saveShipPackageLines(sps);
		}
		return sp;
	}

	/**
	 * 鏍规嵁packageId鏌ヨ璇︾粏淇℃伅
	 * 
	 * @param packageId
	 * @return
	 */
	public ShipPackage findByPackageId(Integer packageId) {
		ShipPackage sps = this.packagesdao.findByPackageId(packageId);
		if (sps != null) {

			if (sps.getAdtlCustomerCharge() != null) {
				sps.setAdtlCustomerCharge(sps.getAdtlCustomerCharge().setScale(
						2, BigDecimal.ROUND_HALF_UP));
			}
			if (sps.getBaseCharge() != null) {
				sps.setBaseCharge(sps.getBaseCharge().setScale(2,
						BigDecimal.ROUND_HALF_UP));
			}
			if (sps.getCarrierCharge() != null) {
				sps.setCarrierCharge(sps.getCarrierCharge().setScale(2,
						BigDecimal.ROUND_HALF_UP));
			}
			if (sps.getCustomerCharge() != null) {
				sps.setCustomerCharge(sps.getCustomerCharge().setScale(2,
						BigDecimal.ROUND_HALF_UP));
			}
			if (sps.getDeliveryConfirmFee() != null) {
				sps.setDeliveryConfirmFee(sps.getDeliveryConfirmFee().setScale(
						2, BigDecimal.ROUND_HALF_UP));
			}
			if (sps.getInsuranceCharge() != null) {
				sps.setInsuranceCharge(sps.getInsuranceCharge().setScale(2,
						BigDecimal.ROUND_HALF_UP));
			}
			if (sps.getInsuranceValue() != null) {
				sps.setInsuranceValue(sps.getInsuranceValue().setScale(2,
						BigDecimal.ROUND_HALF_UP));
			}
			if (sps.getPackagingFee() != null) {
				sps.setPackagingFee(sps.getPackagingFee().setScale(2,
						BigDecimal.ROUND_HALF_UP));
			}
			if (sps.getActlCarrCharge() != null) {
				sps.setActlCarrCharge(sps.getActlCarrCharge().setScale(2,
						BigDecimal.ROUND_HALF_UP));

			}
		}
		return sps;
	}

	/**
	 * 根据orderNo、itemNo得到OrderItem的列表
	 * 
	 * @param orderNo
	 * @param itemNos
	 * @return List<ShipmentsDTO>
	 */
	public List<ShipmentsDTO> getNewOrderItemList(String orderNo, String itemNos) {
		return this.shippingdao.getNewOrderItemList(orderNo, itemNos);
	}

	/**
	 * View Shipment Instruction
	 * 
	 * @param orderNos
	 * @return
	 */
	public HashMap<String, Object> getOrderNotesByOrderNosS(String orderNos)
			throws SQLException {
		return this.orderNotesDao.getOrderNotesByOrderNo(orderNos);
	}

	/**
	 * For print pick list
	 * 
	 * @param packagesId
	 * @param userId
	 * @param lotNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean printPickListModify(String packagesId, Integer userId,
			String lotNo) {
		try {
			List list = this.shippingdao.getSPLSAndSPS(packagesId);
			List<ShipPackageLines> spls = new ArrayList<ShipPackageLines>();
			List<ShipPackage> sps = new ArrayList<ShipPackage>();
			for (int i = 0; i < list.size(); ++i) {
				Object[] o = (Object[]) list.get(i);
				ShipPackageLines spl = (ShipPackageLines) o[0];
				spls.add(spl);
				ShipPackage sp = (ShipPackage) o[1];
				sps.add(sp);
			}
			PickingLogs log = null;
			java.util.Date date = new java.util.Date();
			List<String> lotNoList = new ArrayList<String>();
			List<String> lineIdList = new ArrayList<String>();
			List<String> locationCodeList = new ArrayList<String>();
			System.out.println("<><>" + lotNo);
			if (lotNo != null && !lotNo.equals("")) {
				String[] lotNos = lotNo.split(";");

				if (lotNos.length > 0) {
					for (String no : lotNos) {
						String[] nos = no.split(",");
						if (nos.length > 0) {
							lotNoList.add(nos[1]);
							lineIdList.add(nos[0]);
							locationCodeList.add(nos[2]);
						}
					}
				}
			}
			for (int j = 0; j < spls.size(); ++j) {
				spls.get(j).setStatus("Picked");
				// this.lineDao.save(spls.get(j));
				ShipPackageLines line = spls.get(j);
				this.shippingdao.updateShipPackageLine(line.getShipPackages()
						.getPackageId(), "Picked");
				/*
				 * ShipmentLine sl1 = spls.get(j).getShipmentLines();
				 * System.out.println(sl1.toString()+">>>>>>>>>>>>>>>"); if (sl1
				 * != null && sl1.getLineId() != null) {
				 * System.out.println(sl1.getLineId()+">>>>>>>>>>>>>>>");
				 * this.lineDao.modyfyShipmentLinesByLineId(sl1.getLineId()); }
				 */

				// ShipPackageLines line = spls.get(j);
				List<PickingLogs> logList = this.pickingLogDao.findBy(
						"pkgLineId", line.getPkgLineId());
				if (logList == null || logList.isEmpty()) {
					log = new PickingLogs();
				} else {
					log = logList.get(0);
				}

				// StockDetail detail =
				// this.shippingdao.getStockDetailByReservationId(line.getReservationId());
				// log.setCatalogNo(detail.getCatalogNo());
				// log.setLocationCode(detail.getLocationCode());
				log.setPickedBy(userId);
				log.setPickingDate(date);
				log.setPickingQty(line.getQuantity());
				log.setPickingSize(line.getSize());
				log.setPackageId(line.getShipPackages().getPackageId());
				log.setPkgLineId(line.getPkgLineId());
				// log.setStorageId(detail.getStorageId());
				log.setWarehouseId(line.getShipPackages().getWarehouseId());
				for (int k = 0; k < lineIdList.size(); k++) {
					if (lineIdList.get(k)
							.equals(line.getPkgLineId().toString())) {
						log.setLotNo(lotNoList.get(k));
						log.setLocationCode(locationCodeList.get(k));
					}
				}
				this.pickingLogDao.save(log);
			}
			this.shippingdao.flush();
			for (int k = 0; k < sps.size(); ++k) {
				if (k > 0 && sps.get(k) == sps.get(k - 1)) {
					continue;
				}
				// sps.get(k).setStatus("Picked");
				// this.packagesdao.save(sps.get(k));
				ShipPackage pkg = (ShipPackage) sps.get(k);
				this.shippingdao
						.updateShipPackage(pkg.getPackageId(), "Picked");
				if (sps != null && sps.get(k) != null
						&& sps.get(k).getShipments() != null) {
					Integer shipmentId = null;
					// Integer shipmentId =
					// sps.get(k).getShipments().getShipmentId();
					if (shipmentId != null && !shipmentId.equals("")) {
						this.shippingdao.updateShipment(shipmentId);
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 根据orderNo获取OrderItems列表（分页）
	 * 
	 * @param page
	 * @param orderNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<OrderItem> getOrderItemsByOrderNo(Page<OrderItem> page,
			String orderNo) {
		Page<OrderItem> retPage = null;
		List<OrderItem> list = new ArrayList<OrderItem>();
		page = this.orderItemsDao.searchOrderItemsByOrderNo(page, orderNo);
		if (page.getResult() != null) {
			for (OrderItem oi : page.getResult()) {
				OrderItem oi1 = dozer.map(oi, OrderItem.class);
				list.add(oi1);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(list);
		return retPage;
	}

	/**
	 * 返回packageId数组
	 * 
	 * @param orderNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String[] getPackageTns(String orderNo) {

		int i = 0;
		List list = packagesdao.getPackageTn(orderNo,
				"Picked','Packed','Ready To Ship','Drafted");
		String[] packageId = new String[list.size()];
		for (Object id : list) {
			packageId[i] = id.toString();
			i++;
		}
		return packageId;
	}

	/**
	 * 鏍规嵁shippments鐨剆tatus鏇存柊order_item鍜宱rder鐨剆tatus
	 * 
	 * @author lizhang
	 */
	public void updateStatus() {
		String status = null;// 将要被更新的状态
		Integer orderNo = null;
		Integer itemNo = null;
		Integer quantity = null;// 将要更新状态order_item数量
		Integer quantitied = null;// 已经更新状态的order_item数量
		/** 以上变量赋值待定 **/
		OrderItem orderItems = this.orderItemsDao.getOrderItem(orderNo, itemNo);
		if (orderItems != null
				&& orderItems.getQuantity() <= (quantity + quantitied)) {
			// 前提：quantitied和quantity所对应的order_item的status一样
			orderItems.setStatus(status);
			this.orderItemsDao.save(orderItems);
		}
		OrderMain order = this.orderDao.getById(orderNo);
		List<OrderItem> orderItemsList = this.orderItemsDao.findBy("orderNo",
				orderNo);
		boolean flg = false;
		if (orderItemsList != null && orderItemsList.size() > 0) {
			for (OrderItem orderItem : orderItemsList) {
				if (!orderItem.getOrderItemId().equals(
						orderItems.getOrderItemId())
						&& !status.equals(orderItem.getStatus())
						&& !"CN".equals(orderItem.getStatus())) {
					flg = true;
					break;
				}
			}
		}
		if (!flg) {
			order.setStatus(status);
		}
		this.orderDao.save(order);

	}

	/**
	 * 
	 */

	/**
	 * 返回packageId数组
	 * 
	 * @param orderNo
	 * @return
	 */
	// @SuppressWarnings("unchecked")
	// public String[] getTrackingNo(String orderNo) {
	// int i=0;
	// List list=packagesdao.getPackageTn(orderNo,
	// "Picked','Packed','Ready to ship");
	// String[] trackingNo=new String[list.size()];
	// for(Object id :list)
	// {
	// trackingNo[i]=id.toString();
	// i++;
	// }
	// return trackingNo;
	// }
	/**
	 * PrintPickList()绗竴涓寜閽殑鏌ヨ
	 * 
	 * @param lineIds
	 * @return
	 */
	public List<PrintPickListDTO> printPickList(String lineIds) {
		List<PrintPickListDTO> list = new ArrayList<PrintPickListDTO>();
		list = this.packagesdao.printPickList(lineIds);// 调用dao

		/*
		 * OrderAddressDTO orderAddressDTO =
		 * orderService.getAddress(Integer.parseInt(orderNo)); billToAddr =
		 * orderAddressDTO.getBillToAddr(); shipToAddr =
		 * orderAddressDTO.getShipToAddr(); if(list!=null) {
		 * for(PrintPickListDTO ppl:list) {
		 * ppl.setBillTo(this.getAddressDisplay(billToAddr));
		 * ppl.setShipTo(this.getAddressDisplay(shipToAddr));
		 * ppl.setShipVia(packagesdao.selectShipVia(ppl.getShipVia())); } }
		 */
		return list;
	}

	/*
	 * 获取shipPackageLine 与 orderItem
	 */
	public List<ShippingPackageLinesDTO> searchShipPackageLineOrderItem(
			String packageIds) {
		return this.shippingdao.findListShippingLinesDTO(packageIds);
	}
	
	public List<Warehouse> searchWarehouseAll(){
		return  this.warehouseDao.getAll();
	}

	/**
	 * PrintPickList()第一个按钮的查询
	 * 
	 * @param shipmentId
	 * @return
	 */
	public List<PrintPickListsDTO> printPickLists(Integer shipmentId,
			String packageIds) {
		List<String> packageList = new ArrayList<String>();
		if (packageIds != null && !packageIds.equals("")
				&& !packageIds.equals("null")) {
			packageList = Arrays.asList(packageIds.split(","));
		}
		List<ShipPackage> shipPackageList = this.shipPackageDao.findBy(
				"shipments.shipmentId", shipmentId);
		List<Warehouse> warehouseList = this.searchWarehouseAll();
		List<ShipMethod> shipMethodList =  this.shipMethodService.getShipMethodList();
		//List<ShipPackageLines> shipPackageLinesListAll = this.shipPageageLineDao.find("shipPackages.shipments.shipmentId",shipmentId);
		List<PrintPickListsDTO> listDTO = new ArrayList<PrintPickListsDTO>();
		if (shipPackageList != null && !shipPackageList.isEmpty()) {
			
			for (ShipPackage shipPackage : shipPackageList) {
				String isAdd = "0";
				if (packageList != null && !packageList.isEmpty()) {
					for (String packageIdStr : packageList) {
						if ((shipPackage.getPackageId().toString())
								.equals(packageIdStr)) {
							isAdd = "1";
						}
					}
				}
				if (isAdd.equals("0")) {
					continue;
				}
				if (shipPackage.getStatus().equals("Picked")
						|| shipPackage.getStatus().equals("Drafted")
						|| shipPackage.getStatus().equals("Packed")) {
					List<PrintPickListDTO> list = this.shipPageageLineDao.searchShipPackageLineAndPickingLog(shipPackage.getPackageId());
					PrintPickListsDTO dto = new PrintPickListsDTO();
					dto.setShipPackage(shipPackage);
					/*List<ShipPackageLines> shipPackageLinesList = this.shipPageageLineDao
							.findBy("shipPackages.packageId", shipPackage
									.getPackageId());*/
					String lineIds = "";
					Integer orderNoI = null;
					String orderNos = "";
					Map<Integer, Integer> orderNoMap = new HashMap<Integer, Integer>();
					if (list != null
							&& !list.isEmpty()) {
						for (PrintPickListDTO line : list) {
							
							if (lineIds.equals("")) {
								lineIds = line.getLineId();
								orderNoI = line.getOrderNo();
							} else{
								lineIds +=(","+line.getLineId());
							}
							orderNoMap
									.put(line.getOrderNo(), line.getOrderNo());
						}
						for (Map.Entry<Integer, Integer> entry : orderNoMap
								.entrySet()) {
							if (orderNos.equals("")) {
								orderNos = entry.getKey() + "";
							} else {
								orderNos += "," + entry.getKey();
							}
						}
						shipPackage.setOrderNos(orderNos);
						/*List<PrintPickListDTO> list = this
								.printPickList(lineIds);*/
						  List<PickingLogs> logList = pickingLogDao.searchPickingLogsList(lineIds);
						  if (list != null && !list.isEmpty()) {
							for (PrintPickListDTO picDto : list) {
								for(PickingLogs logs:logList){
									if (logs != null && logs.getPkgLineId().toString().equals(picDto.getLineId())) {
										picDto.setLotNo(logs.getLotNo());
										picDto.setStorageLocation(logs.getLocationCode());
										System.out.println(">>>>>>>>//");
										break;
									}
								}
								
								
								System.out.println(">>>>>>>>//"
										+ picDto.getStorageLocation());
							}
						}
						List<OrderAddress> orderAddressList = this.orderAddressDao.getAddrByOrderNo(orderNoI);
						dto.setPrintPickListDTOList(list);
						if (dto.getShipPackage().getWarehouseId() != null) {
							
							for(Warehouse warehouse:warehouseList){
								if (warehouse.getWarehouseId().equals(dto.getShipPackage().getWarehouseId())) {
									dto.setWarehouseName(warehouse.getName());
								}
								
								/*if (shipPackage.getWarehouseId() != null) {
									Warehouse w = this.getWarehouse(shipPackage
											.getWarehouseId());
									if (w != null) {
										dto.setWarehouseName(w.getName());
									}
								}
*/
							}
							
						}
						for(OrderAddress address : orderAddressList){
							
								if (address != null&&address.getAddrType().equals("BILL_TO")) {
									dto
											.setBillToAddress(this
													.getAddressDisplay(address)
													.replace("&#13;&#10;", "<br/>"));
								}
							
							
								if (address != null&&address.getAddrType().equals("SHIP_TO")) {
									dto
											.setShipToAddress(this
													.getAddressDisplay(address)
													.replace("&#13;&#10;", "<br/>"));
								}
							
						}
						
						if (shipPackage.getShipMethod() != null) {
							for(ShipMethod shipmethod:shipMethodList){
								if (shipmethod.getMethodId().equals(shipPackage.getShipMethod())) {
									dto.setShipVia(shipmethod.getName());
								}
							}
							
						}
						
						listDTO.add(dto);
					}

				}

			}

		}
		return listDTO;
	}

	/**
	 * 根据orderNo查状态(第一个按钮和第二个按钮共用)
	 * 
	 * @param orderNo
	 * @param status
	 * @return
	 */
	public Boolean SelectPackageIdAndStatus(String orderNo, String status) {
		return packagesdao.SelectPackageIdAndStatus(orderNo, status);
	}

	/**
	 * 更新第二个按钮
	 * 
	 * @param orderNo
	 */
	public void updateViewStatus(String orderNo) {
		packagesdao.updateStatus(orderNo);
	}
	/*
	 * tool中打印packing slip
	 */
	public List<ViewPackingSlipDTO> ViewPackingSlip(String orderNo,String itemNo){
		List<ViewPackingSlipDTO> list = new ArrayList<ViewPackingSlipDTO>();
		if(orderNo!=null&&!orderNo.equals("")){
			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd ");
			ViewPackingSlipDTO dto = new ViewPackingSlipDTO();
			OrderMain order = this.orderDao.getById(Integer.valueOf(orderNo));
			dto.setOrder(order.getOrderNo());
			dto.setOrderDate(df2.format(order.getOrderDate()));
/*			ShipMethod shipMethod = this.getShipVia(order.getOrderNo(), null,
					null, null);
			dto.setShipVia(shipMethod.getName());*/
			dto.setOfPacakge("1/1");
			if (order.getBilltoAddrId() != null) {
				OrderAddress address = this.orderAddressDao
						.getById(order.getBilltoAddrId());
				if (address != null) {
					dto.setBillTo(this.getAddressDisplay(address)
							.replace("&#13;&#10;", "<br/>"));
				}
			}
			dto.setCustomerNo(order.getCustNo());
			Customer customer = this.customerDao.getById(dto
					.getCustomerNo());
			if (customer != null
					&& customer.getPrefPaymentTerm() != null) {
				PaymentTerm term = this.paymentTermDao
						.getById(Integer.valueOf(customer
								.getPrefPaymentTerm()));
				if (term != null) {
					dto.setTerms(term.getName());
				}
			} else {
				dto.setTerms("");
			}
			if (order.getShiptoAddrId() != null) {
				OrderAddress address = this.orderAddressDao
						.getById(order.getShiptoAddrId());
				if (address != null) {
					dto.setShipTO(this.getAddressDisplay(address)
							.replace("&#13;&#10;", "<br/>"));
				}
			}
			dto.setPo("CC");
			List<PaymentVoucher> poList = this.paymentVoucherDao
					.findBy("orderNo", dto.getOrder());
			if (poList != null && !poList.isEmpty()) {
				for (PaymentVoucher pv : poList) {
					if (pv.getPaymentType().equals("PO")
							&& pv.getPoNumber() != null) {
						dto.setPo(pv.getPoNumber().toString());
					}
				}
			}
			List<OrderItem> itemList = this.orderItemsDao.searchOrderItemListByItemId(orderNo,itemNo);
			if(itemList!=null&&!itemList.isEmpty()){
				OrderItem item = itemList.get(0);
				ShipMethod shipMethod = this.getShipVia(null, null,
						item.getShipMethod(), null);
				dto.setShipVia(shipMethod.getName());
				Double totalWeight = 0.0;  
				List<ShipPackageLineDTO> shipLineDTOList = new ArrayList<ShipPackageLineDTO>();
				for(OrderItem orderItem:itemList){
					ShipPackageLineDTO lineDTO = new ShipPackageLineDTO();
					if (orderItem.getType().equals("SERVICE")) {
						com.genscript.gsscm.serv.entity.Service serv = this.servDao
								.getServiceByCatalogNo(orderItem
										.getCatalogNo());
						if (serv.getGiftFlag() == null
								|| !serv.getGiftFlag().equals("Y")) {
							lineDTO.setGiftFlag(null);
						}
						if (serv.getGiftFlag() != null
								&& (serv.getGiftFlag().equals("Y") || serv
										.getGiftFlag().equals("y"))) {
							lineDTO.setGiftFlag("Y");
						}
					}
					if (orderItem.getType().equals("PRODUCT")) {
						Product product = this.productDao
								.getProductByCatalogNo(orderItem
										.getCatalogNo());
						if (product.getGiftFlag() == null
								|| !product.getGiftFlag().equals(
										"Y")) {
							lineDTO.setGiftFlag(null);
						}
						if (product.getGiftFlag() != null
								&& (product.getGiftFlag().equals(
										"Y") || product
										.getGiftFlag().equals("y"))) {
							lineDTO.setGiftFlag("Y");
						}
					}
					lineDTO.setItemNo(orderItem.getItemNo());
					lineDTO.setName(orderItem.getName());
					lineDTO.setQtyShipped(orderItem.getQuantity());
					lineDTO.setQtyUom(orderItem.getQtyUom());
					lineDTO.setSize(orderItem.getSize());
					lineDTO.setSizeUom(orderItem.getSizeUom());
					lineDTO.setCatalogNO(orderItem.getCatalogNo());
					shipLineDTOList.add(lineDTO);
				}
				dto.setShipPackageLineDTO(shipLineDTOList);
			}
			list.add(dto);
		}
		return list;
	}
	
	/**
	 * ViewPackingSlip(第二个按钮的查询)
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<ViewPackingSlipDTO> ViewPackingSlip(String orderNo,
			Integer shippmentId) {
		System.out.println(orderNo + " " + shippmentId);
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd ");
		List<ShipPackage> shipPackagesList = this.shipPackageDao.findBy(
				"shipments.shipmentId", shippmentId);

		// 鏌ヨ
		List<ViewPackingSlipDTO> list = new ArrayList<ViewPackingSlipDTO>();
		if (shipPackagesList != null && !shipPackagesList.isEmpty()) {
			for (ShipPackage shipPackages : shipPackagesList) {
				if (shipPackages.getStatus().equals("Picked")
						|| shipPackages.getStatus().equals("Packed")) {
					List<ShipPackageLines> lineList = this.lineDao.findBy(
							"shipPackages.packageId", shipPackages
									.getPackageId());
					if (lineList != null && !lineList.isEmpty()) {
						OrderMain order = this.orderDao.getById(lineList.get(0)
								.getOrderNo());
						ViewPackingSlipDTO dto = new ViewPackingSlipDTO();
						if (order.getBilltoAddrId() != null) {
							OrderAddress address = this.orderAddressDao
									.getById(order.getBilltoAddrId());
							if (address != null) {
								dto.setBillTo(this.getAddressDisplay(address)
										.replace("&#13;&#10;", "<br/>"));
							}
						}
						dto.setCustomerNo(order.getCustNo());
						Customer customer = this.customerDao.getById(dto
								.getCustomerNo());
						if (customer != null
								&& customer.getPrefPaymentTerm() != null) {
							PaymentTerm term = this.paymentTermDao
									.getById(Integer.valueOf(customer
											.getPrefPaymentTerm()));
							if (term != null) {
								dto.setTerms(term.getName());
							}
						} else {
							dto.setTerms("");
						}
						if (order.getShiptoAddrId() != null) {
							OrderAddress address = this.orderAddressDao
									.getById(order.getShiptoAddrId());
							if (address != null) {
								dto.setShipTO(this.getAddressDisplay(address)
										.replace("&#13;&#10;", "<br/>"));
							}
						}

						if (shipPackages.getShipmentDate() != null) {
							SimpleDateFormat strToDate = new SimpleDateFormat(
									"yyyy-MM-dd");
							dto.setShippingDate(strToDate.format(shipPackages
									.getShipmentDate()));
						}
						dto.setCustomerNo(order.getCustNo());
						dto.setOrder(order.getOrderNo());
						if (order.getOrderDate() != null) {
							dto.setOrderDate(df2.format(order.getOrderDate()));
						}
						ShipMethod shipMethod = this.getShipVia(null, null,
								shipPackages.getShipMethod(), null);
						dto.setShipVia(shipMethod.getName());
						if (shipPackages.getShipmentDate() != null) {
							dto.setShippingDate(df2.format(shipPackages
									.getShipmentDate()));
						}
						dto
								.setTotalWeightDouble(shipPackages
										.getActualWeight());
						// dto.setOfPacakge(shipPackages.getPkgBatchSeq()+" / "
						// + shipPackages.getPkgBatchCount());
						// dto.setOfPacakge(i + "/" + shipPackagesList.size());
						Double subtotal = 0.0d;
						Double discount = 0.0d;
						Double tax = 0.0d;
						Double total = 0.0d;
						dto.setSubtotal(subtotal);
						dto.setDiscount(discount);
						dto.setTax(tax);
						dto.setShipping(shipPackages.getCustomerCharge()
								.doubleValue());
						dto.setHanding(shipPackages.getHandingFee()
								.doubleValue());
						dto.setTotal(total);
						List<ShipPackageLineDTO> lineDTOList = new ArrayList<ShipPackageLineDTO>();
						List<WorkOrderDTO> workOrderDTOList = new ArrayList<WorkOrderDTO>();
						String orderItemLine="1";
						for (ShipPackageLines lines : lineList) {
							OrderItem orderItem = this.shippingdao
									.getOrderItems(lines.getOrderNo(), lines
											.getItemNo());
							
							ShipPackageLineDTO lineDTO = new ShipPackageLineDTO();
							if (orderItem != null) {
								if(orderItemLine.equals("1")&&orderItem.getType().equals("SERVICE")){
									dto.setType(orderItem.getClsId()+"");
									orderItemLine="0";
								}
								
								lineDTO.setName(orderItem.getName());
								lineDTO.setQtyOrdered(orderItem.getQuantity());
								lineDTO.setQtyShipped(lines.getQuantity());
								lineDTO.setSize(lines.getSize());
								lineDTO.setItemNo(lines.getItemNo());
								lineDTO
										.setDescription(orderItem
												.getShortDesc());
								lineDTO.setUnitPrice(orderItem.getUnitPrice());
								lineDTO.setQtyUom(orderItem.getQtyUom());
								lineDTO.setSizeUom(orderItem.getSizeUom());
								lineDTO.setCatalogNO(orderItem.getCatalogNo());
								if (orderItem.getType().equals("SERVICE")) {
									com.genscript.gsscm.serv.entity.Service serv = this.servDao
											.getServiceByCatalogNo(orderItem
													.getCatalogNo());
									if (serv.getGiftFlag() == null
											|| !serv.getGiftFlag().equals("Y")) {
										lineDTO.setGiftFlag(null);
									}
									if (serv.getGiftFlag() != null
											&& (serv.getGiftFlag().equals("Y") || serv
													.getGiftFlag().equals("y"))) {
										lineDTO.setGiftFlag("Y");
									}
								}
								if (orderItem.getType().equals("PRODUCT")) {
									Product product = this.productDao
											.getProductByCatalogNo(orderItem
													.getCatalogNo());
									if (product.getGiftFlag() == null
											|| !product.getGiftFlag().equals(
													"Y")) {
										lineDTO.setGiftFlag(null);
									}
									if (product.getGiftFlag() != null
											&& (product.getGiftFlag().equals(
													"Y") || product
													.getGiftFlag().equals("y"))) {
										lineDTO.setGiftFlag("Y");
									}
								}
								Double discB = 0.0;
								if (!orderItem.getDiscount().equals(0.0)
										&& !orderItem.getAmount().equals(0.0)) {
									discB = Arith.div(orderItem.getDiscount(),
											orderItem.getAmount(), 2);
								}
								Double discU = Arith.mul(lineDTO
										.getQtyShipped(), discB);
								lineDTO.setDisc(Arith.mul(discU, lineDTO
										.getUnitPrice()));
								lineDTO.setExtendedPrice(Arith.mul(lines
										.getQuantity(), orderItem
										.getUnitPrice()));
								lineDTOList.add(lineDTO);

								if (lineDTO.getUnitPrice() != null
										&& lineDTO.getQtyShipped() != null) {
									subtotal = Arith.mul(
											lineDTO.getUnitPrice(), lineDTO
													.getQtyShipped());
									dto.setSubtotal(Arith.add(
											dto.getSubtotal(), subtotal));
								}
								if (lineDTO.getDisc() != null) {
									// discount = Arith.mul(lineDTO.getDisc(),
									// qut);
									dto.setDiscount(Arith.add(
											dto.getDiscount(), lineDTO
													.getDisc()));
								}

								if (orderItem.getTax() != null) {
									tax = Arith.mul(orderItem.getTax(), discB);
									dto.setTax(Arith.add(dto.getTax(), tax));
								}

								/*
								 * if(shipPackages.getCustomerCharge()!=null){
								 * shippingHanding
								 * =shipPackages.getCustomerCharge
								 * ().doubleValue();
								 * dto.setShippingHanding(Arith
								 * .add(dto.getShippingHanding(),
								 * shippingHanding)); }
								 */

								dto.setTotal(Arith.add(dto.getTotal(), total));

							}
							
							WorkOrderDTO workOrder = new WorkOrderDTO();
							workOrder.setSoNo(lines.getOrderNo());
							workOrder.setSoItemNo(lines.getItemNo());
							List<WorkOrderLot> workOrderLotList = this.workOrderLotDao.searchWorkOrderLotByOrderNoAndItemNo(lines.getOrderNo(),lines.getItemNo());
							if(workOrderLotList!=null&&!workOrderLotList.isEmpty()){
								workOrder.setWorkOrderLotList(workOrderLotList);
								workOrder.setLotLength(workOrderLotList.size());
								
								//System.out.println("mingrs="+workOrderLotList.size());
							}else{
								workOrder.setLotLength(1);
							}
							if(workOrderLotList==null){
								workOrder.setWorkOrderLotList(new ArrayList<WorkOrderLot>());
							}
							workOrderDTOList.add(workOrder);
						}
						if(workOrderDTOList!=null&&!workOrderDTOList.isEmpty()){
							dto.setWorkOrderList(workOrderDTOList);
						}
						total = Arith.add(total, dto.getSubtotal());
						total = Arith.sub(total, dto.getDiscount());
						total = Arith.add(total, dto.getTax());
						total = Arith.add(dto.getShipping(), total);
						dto.setPo("CC");
						List<PaymentVoucher> poList = this.paymentVoucherDao
								.findBy("orderNo", dto.getOrder());
						if (poList != null && !poList.isEmpty()) {
							for (PaymentVoucher pv : poList) {
								if (pv.getPaymentType().equals("PO")
										&& pv.getPoNumber() != null) {
									dto.setPo(pv.getPoNumber().toString());
								}
							}
						}

						/*
						 * List<PurchaseOrder> purchaseList =
						 * this.purchaseOrderDao.findBy("srcSoNo",
						 * dto.getOrder());
						 * if(purchaseList!=null&&!purchaseList.isEmpty()){
						 * dto.setPo(purchaseList.get(0).getOrderNo()); }
						 */
						dto.setTotal(total);
						dto.setShipPackageLineDTO(lineDTOList);
						list.add(dto);
					}
				}
			}
		}
		// list=packagesdao.ViewPackingSlip(orderNo);
		/*
		 * if(list!=null) { for(int i=0;i<list.size();i++) { ViewPackingSlipDTO
		 * view = list.get(i); Integer size = list.size();
		 * view.setOfPacakge((i+1)+" / " + size);
		 * view.setBillTo(packagesdao.selectAddr(view.getBillTo(), "BILL_TO"));
		 * view.setShipTO(packagesdao.selectAddr(view.getShipTO(), "SHIP_TO"));
		 * view.setShipVia(packagesdao.selectShipVia(view.getShipVia())); } }
		 * Collections.sort(list);
		 */
		int i = 0;
		for (ViewPackingSlipDTO slipDTO : list) {
			i++;
			slipDTO.setOfPacakge(i + "/" + list.size());
		}
		return list;
	}

	/**
	 * 鏍规嵁orderNo鏌ヨOrderPackage
	 * 
	 * @param orderNo
	 *            参数
	 * @return OrderPackage 实体对象
	 */
	public List<OrderPackage> getOrderPackageByOrderNo(String orderNo) {
		try {
			return this.orderPackageDao.getOrderPackageList1(orderNo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 更新ShipPackages中信息
	 * 
	 * @param shipPackage
	 */
	public void updatePackId(ShipPackage shipPackage) {
		this.packagesdao.updatePackId(shipPackage);
	}

	/**
	 * 根据id返回ShipPackages对象
	 * 
	 * @param packageId
	 * @return
	 */
	public ShipPackage getShipPackagesByid(int packageId) {
		return this.packagesdao.getShipPackageById(packageId);
	}

	/**
	 * 鏍规嵁id淇敼ShipPackages status瀵硅薄
	 * 
	 * @return
	 */
	public void setPackageStatus(Integer shipmentId, String type) {
		List<ShipPackage> shipPackagesList = this.packagesdao.findBy(
				"shipments.shipmentId", shipmentId);
		this.packagesdao.getSession().evict(shipPackagesList);
		for (ShipPackage shipPackage : shipPackagesList) {

			if (shipPackage != null) {
				String status = shipPackage.getStatus();
				if (status != null) {
					if (status.equals("Drafted")
							&& type.equals("PrintPickList")) {
						shipPackage.setStatus("Picked");
						this.shipPackageLineService
								.uptReservationBypackageId(new String[] { shipPackage
										.getPackageId().toString() });
					} else if (status.equals("Picked")
							&& type.equals("PrintPackingSlip")) {
						shipPackage.setStatus("Packed");
					}
					this.packagesdao.save(shipPackage);
				}
			}
		}
	}

	/**
	 * 閫氳繃涓婚敭packageId鏌ヨ
	 * 
	 * @param packageIds
	 * @return
	 * @throws Exception
	 * @author zhangyong
	 */
	public List<ShipPackage> getShipPackagesBypackageIds(String[] packageIds)
			throws Exception {
		List<ShipPackage> shipPackageList = new ArrayList<ShipPackage>();
		String packageid = "";
		//String[] packageIdsArr = packageIds.split(",");
		for (int i = 0; i < packageIds.length; i++) {
			packageid = packageIds[i];
			ShipPackage shipPackage = packagesdao.findUniqueBy("packageId",
					Integer.parseInt(packageIds[i]));
			if (shipPackage != null) {
				shipPackageList.add(shipPackage);
			}
		}
		// 取得order 的国家以及customer的email ,和order 表关联，读取order_address 表，可取出country
		// 以及 bus_email add by zhanghuibin
		if (!"".equals(packageid)) {
			OrderAddress orderAdd = packagesdao.getOrderAddress(packageid);
			String country = orderAdd.getCountry();
			String busEmail = orderAdd.getBusEmail();
			if ("IN".equals(country) || "CA".equals(country)
					|| "TH".equals(country)
					|| busEmail.toUpperCase().endsWith("VWR.COM")) {
				if (shipPackageList.size() > 0) {
					shipPackageList.get(0).setFlag("Y");
				}
			}
		}
		return shipPackageList;
	}

	/**
	 * 取消装运功能:更新ShipPackages表信息
	 * 
	 * @param packageId
	 * @param reason
	 * @return
	 */
	public boolean changeShipPackagesByPackageId(int packageId, String reason) {
		if (this.packagesdao.updateShipPackagesByPackageId(packageId, reason) != null
				&& this.shipPageageLineDao.changePackageLinesStatus(packageId))
			return true;
		return false;
	}

	/**
	 * 为调用银行的接口查询
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public BankCardDTO selectForBand(String orderNo) {
		List<ShipPackage> packageList = packagesdao.getPackageTn1(orderNo,
				"Packed");
		List<Integer> packageID = new ArrayList<Integer>();
		List<ShipPackage> packageLists = new ArrayList<ShipPackage>();
		Double customerChage = 0.0;
		if (packageList != null && !packageList.isEmpty()) {
			for (ShipPackage shipPackage : packageList) {
				// if(shipPackage.getTrackingNo()!=null&&!shipPackage.getTrackingNo().equals("")){
				List<ShippingChargeLog> shippingCargeLogList = this.shippingChargeLogDao
						.findBy("packageId", shipPackage.getPackageId());
				if (shippingCargeLogList == null
						|| shippingCargeLogList.isEmpty()) {
					packageID.add(shipPackage.getPackageId());
					packageLists.add(shipPackage);
					customerChage += (shipPackage.getActlCarrCharge()
							.add(shipPackage.getAdtlCustomerCharge()))
							.doubleValue();
				}
				// }
			}
		}
		BankCardDTO bank = new BankCardDTO();
		bank.setPackageIds(packageLists);
		if (packageID != null && !packageID.isEmpty()) {
			packagesdao.selectCustomerCreditCards(packageID, bank);
			packagesdao.selectOrderAddress(packageID, bank);
		}
		bank.setCustomerCharge(customerChage);
		// packagesdao.selectPackagesCustomerCharge(packageID, bank);
		return bank;
	}

	/**
	 * 根据packageId得到PackageLine的list
	 * 
	 * @param packageIds
	 * @return List<ShipPackageLines>
	 */
	public List<ShipPackageLines> getShipPackageLines(String packageIds) {
		return this.shippingdao.getShipPackageLines(packageIds);
	}

	/**
	 * 根据OrderNo去Order表中,查询CustNo
	 */
	public String findListOrderNo(String OrderNo) {
		List<Integer> custList = this.shippingdao.findListOrderNo(OrderNo);
		StringBuffer sbf = new StringBuffer();
		for (int i = 0; i < custList.size(); i++) {
			sbf.append(custList.get(i));
			sbf.append(",");
		}
		return sbf.toString().substring(0, sbf.toString().length() - 1);
	}

	/*
	 * 根据warehouseId查询warehouse
	 */
	public Warehouse getWarehouse(Integer id) {
		return this.warehouseDao.getById(id);
	}

	/*
	 * 设定package中的ship via,ship to,Weight
	 */
	public void setPackage(List<ShipmentsDTO> listPackage) {
		if (listPackage != null) {
			for (ShipmentsDTO dto : listPackage) {
				if (dto != null) {
					if (dto.getListPackageLine() != null
							&& !dto.getListPackageLine().isEmpty()
							&& dto.getListPackageLine().get(0) != null) {
						Integer orderNo = dto.getListPackageLine().get(0)
								.getOrderNo();
						Integer itemNo = dto.getListPackageLine().get(0)
								.getItemNo();
						if (orderNo != null) {
							OrderMain order = this.orderDao.getById(orderNo);
							if (order != null) {
								dto.setWarehouseId(order.getWarehouseId());
							}
						}
						String zipCode = null;
						if (orderNo != null && itemNo != null) {
							OrderItem item = this.getOrderItem(orderNo, itemNo);
							OrderAddress orderAddresses = this
									.getOrderAddresses(null, null, item);
							if (orderAddresses != null) {
								dto.setShipToAddress(orderAddresses
										.getFirstName()
										+ " " + orderAddresses.getLastName());
								System.out.println(dto.getShipToAddress()
										+ "???????????///");
								dto.setShipTo(this
										.getAddressDisplay(orderAddresses));
								dto.setDeliveryType(orderAddresses
										.getAddrClass());
								dto.setRcpFirstName(orderAddresses
										.getFirstName());
								dto.setRcpMidName(orderAddresses.getMidName());
								dto
										.setRcpLastName(orderAddresses
												.getLastName());
								dto.setRcpCity(orderAddresses.getCity());
								dto.setRcpCountry(orderAddresses.getCountry());
								dto.setRcpMobile(orderAddresses.getMobile());
								// dto.setRcpOrgName(orderAddresses.getOrganization().getName());
								dto.setRcpOrgName(orderAddresses.getOrgName());
								dto.setRcpPhone(orderAddresses.getBusPhone());
								dto.setRcpState(orderAddresses.getState());
								dto.setRcpZipCode(orderAddresses.getZipCode());
								dto.setRcpBusEmail(orderAddresses.getBusEmail());
								dto.setRcpAddrClass(orderAddresses.getAddrClass());
				                dto.setRcpAddrLine1(orderAddresses.getAddrLine1());
				                dto.setRcpAddrLine2(orderAddresses.getAddrLine2());
				                dto.setRcpAddrLine3(orderAddresses.getAddrLine3());
				                dto.setRcpFax(orderAddresses.getFax());
				                dto.setRcpTitle(orderAddresses.getTitle());
								zipCode = orderAddresses.getZipCode();
							}

							ShipMethod shipMethod = this.getShipVia(null, null,
									null, item);
							dto.setShipVia(shipMethod.getName());
							dto.setShipMethodId(shipMethod.getMethodId());
						}

						if (dto.getShipMethodId() != null
								&& dto.getWarehouseId() != null
								&& zipCode != null) {
							ShipZone shipZone = this.shipZoneDao.getShipZone(
									dto.getShipMethodId(),
									dto.getWarehouseId(), zipCode, dto
											.getRcpCountry());
							if (shipZone != null) {
								dto.setZone(shipZone.getZoneCode());
							}
						}

						if (dto.getShipmentId() != null) {
							Shipment shipment = this.shipmentDao.getById(dto
									.getShipmentId());
							if (shipment != null) {
								dto.setShippingClerkId(shipment
										.getShippingClerk());
							}
						}

						/*
						 * Double weight=0.0d; for(ShipPackageLineDTO
						 * shipPackageLineDTO : dto.getListPackageLine()){
						 * Double weightThis =
						 * this.getWeight(shipPackageLineDTO.getOrderNo(),
						 * shipPackageLineDTO.getItemNo()); Double allweigthThis
						 * = weightThis; if(
						 * shipPackageLineDTO.getQuantity()!=null||
						 * shipPackageLineDTO.getQuantity()>1){ allweigthThis =
						 * Arith.mul(weightThis,
						 * shipPackageLineDTO.getQuantity());
						 * System.out.println(
						 * allweigthThis+">>>>>>>>>>>>>>>>>>>>>>."); } weight =
						 * Arith.add(weight, allweigthThis);
						 * 
						 * } dto.setBillableWeight(weight);
						 */

						if (dto.getListPackageLine() != null
								&& !dto.getListPackageLine().isEmpty()) {
							Double costTotal = 0.0;
							Double handingFee = 0.0;
							Double weight = 0.0;
							List<Integer> orderNoList = new ArrayList<Integer>();
							for (ShipPackageLineDTO lineDto : dto
									.getListPackageLine()) {
								if (orderNoList == null
										|| orderNoList.isEmpty()) {
									orderNoList.add(lineDto.getOrderNo());
								} else {
									String isAdd = "1";
									for (Integer orderNo1 : orderNoList) {
										if (orderNo1.equals(lineDto
												.getOrderNo())) {
											isAdd = "0";
										}
									}
									if (isAdd.equals("1")) {
										orderNoList.add(lineDto.getOrderNo());
									}
								}
							}
							if (orderNoList != null && !orderNoList.isEmpty()) {
								String orderNos = "";
								for (Integer orderNo1 : orderNoList) {
									if (orderNos.equals("")) {
										orderNos = orderNo1.toString();
									} else {
										orderNos += "," + orderNo1.toString();
									}
								}
								if (!orderNos.equals("")) {

									List<OrderPackage> orderPackageList = this.orderPackageDao
											.getOrderPackageList1(orderNos);
									if (orderPackageList != null
											&& !orderPackageList.isEmpty()) {
										for (OrderPackage orderPackage : orderPackageList) {
											weight = Arith
													.add(
															weight,
															orderPackage
																	.getBillableWeight());
											if (orderPackage != null
													&& orderPackage
															.getCustomerCharge() != null) {
												costTotal = Arith
														.add(
																costTotal,
																orderPackage
																		.getCustomerCharge());
											}
											if (orderPackage != null
													&& orderPackage
															.getHandlingFee() != null) {
												handingFee = Arith
														.add(
																handingFee,
																orderPackage
																		.getHandlingFee());
											}
										}
									}
								}

								costTotal = Arith.sub(costTotal, handingFee);
							}
							dto.setBillableWeight(weight);
							// add by zhanghuibin 判断actualWeight是否被修改
							ShipPackage shipPackage = this.packagesdao
									.findByPackageId(Integer.parseInt(dto
											.getPackageId()));
							if (shipPackage == null
									|| shipPackage.getActualWeight() == shipPackage
											.getBillableWeight()) {
								dto.setActualWeight(weight);
							}
							dto.setCustomerCharge(costTotal);
							dto.setHandingFee(handingFee);
						}
					} else {
						dto.setShipTo("");
						dto.setShipVia("");
						dto.setBillableWeight(0.0d);
					}
				}
				if (dto.getListPackageLine() != null) {
					List<OrderItemDTO> orderItemDTOList = new ArrayList<OrderItemDTO>();
					for (ShipPackageLineDTO line : dto.getListPackageLine()) {
						if (line != null && line.getItemNo() != null
								&& line.getOrderNo() != null) {
							String isBreak = "0";
							OrderItem orderItem = this.orderItemsDao
									.getOrderItem(line.getOrderNo(), line
											.getItemNo());
							for(OrderItemDTO itemDto : orderItemDTOList){
								if(itemDto.getCatalogNo().equals(orderItem.getCatalogNo())){
									line.setTemperature(itemDto.getTemperature()+"");
									isBreak = "1";
									break;
								}
							}
							if(isBreak.equals("1")){
								break;
							}
							OrderItemDTO orderItemDTO = new OrderItemDTO();
							orderItemDTO.setOrderNo(orderItem.getOrderNo());
							orderItemDTO.setItemNo(orderItem.getItemNo());
							orderItemDTO.setType(orderItem.getType());
							orderItemDTO.setCatalogNo(orderItem.getCatalogNo());
							if (orderItem != null
									&& orderItem.getCatalogNo() != null) {
								String catalogNo = orderItem.getCatalogNo();
								if (catalogNo != null && catalogNo.length() > 0) {
									if (orderItem.getType().equals("PRODUCT")) {
										Product po = this.shippingdao
												.findCatalogNoP(catalogNo);
										if (po != null) {
											int productId = po.getProductId();
											ProductShipCondition psc = this.shippingdao
													.findProductShipConditionP(productId);
											if (psc != null) {
												line.setTemperature(psc
														.getTemperature()
														+ "");
												orderItemDTO.setTemperature(psc.getTemperature());
											} else {
												line.setTemperature("");
											}
										} else {
											line.setTemperature("");
										}
									} else if (orderItem.getType().equals(
											"SERVICE")) {
										com.genscript.gsscm.serv.entity.Service sc = this.shippingdao
												.findCatalogNoS(catalogNo);
										if (sc != null) {
											int serviceId = sc.getServiceId();
											ServiceShipCondition ssc = this.shippingdao
													.findServiceShipConditionS(serviceId);
											orderItemDTO.setTemperature(ssc.getTemperature());
											if (ssc != null) {
												line.setTemperature(ssc
														.getTemperature()
														+ "");
											} else {
												line.setTemperature("");
											}
										} else {
											line.setTemperature("");
										}
									}
								} else {
									line.setTemperature("");
								}
							}
							orderItemDTOList.add(orderItemDTO);
						}
					}
				}
			}
		}
	}

	public String getTemperature(String type, String catalogNo) {
		if (("PRODUCT").equals(type)) {
			Product po = this.shippingdao.findCatalogNoP(catalogNo);
			if (po != null) {
				int productId = po.getProductId();
				ProductShipCondition psc = this.shippingdao
						.findProductShipConditionP(productId);
				if (psc != null) {
					return psc.getTemperature() + "";
				} else {
					return "";
				}
			} else {
				return "";
			}
		} else if (("SERVICE").equals(type)) {
			com.genscript.gsscm.serv.entity.Service sc = this.shippingdao
					.findCatalogNoS(catalogNo);
			if (sc != null) {
				int serviceId = sc.getServiceId();
				ServiceShipCondition ssc = this.shippingdao
						.findServiceShipConditionS(serviceId);
				if (ssc != null) {
					return ssc.getTemperature() + "";
				} else {
					return "";
				}
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public String getAddressDisplay(OrderAddress orderAddress) {
		List<String> tmpList = new ArrayList<String>();
		tmpList.add(orderAddress.getFirstName() + " "
				+ orderAddress.getLastName());
		// tmpList.add(orderAddress.getOrganization().getName());
		tmpList.add(orderAddress.getOrgName());
		String addrStr = "";
		if (!StringUtils.isEmpty(orderAddress.getAddrLine1())) {
			addrStr = orderAddress.getAddrLine1();
		}
		if (!StringUtils.isEmpty(orderAddress.getAddrLine2())) {
			addrStr = addrStr + ", " + orderAddress.getAddrLine2();
		}
		if (!StringUtils.isEmpty(orderAddress.getAddrLine3())) {
			addrStr = addrStr + ", " + orderAddress.getAddrLine3();
		}
		tmpList.add(addrStr);
		String cityStr = "";
		if (!StringUtils.isEmpty(orderAddress.getCity())) {
			cityStr = orderAddress.getCity();
		}
		if (!StringUtils.isEmpty(orderAddress.getState())) {
			if (cityStr.equalsIgnoreCase("")) {
				cityStr = orderAddress.getState() + " "
						+ orderAddress.getZipCode();
			} else {
				cityStr = cityStr + ", " + orderAddress.getState() + " "
						+ orderAddress.getZipCode();
			}
		}
		if (!StringUtils.isEmpty(orderAddress.getCountry())) {
			if (cityStr.equalsIgnoreCase("")) {
				cityStr = orderAddress.getCountry();
			} else {
				cityStr = cityStr + ", " + orderAddress.getCountry();
			}
		}
		tmpList.add(cityStr);
		return StringUtils.join(tmpList.toArray(), "&#13;&#10;");
	}

	public OrderAddress getOrderAddresses(Integer orderNo, Integer itemNo,
			OrderItem item) {
		if (orderNo != null && itemNo == null && item == null) {
			OrderMain order = this.orderDao.getById(orderNo);
			if (order != null) {
				OrderAddress orderAddress = this.orderAddressDao.getById(order
						.getShiptoAddrId());
				return orderAddress;
			}
		}
		if (orderNo != null && itemNo != null) {
			item = this.getOrderItem(orderNo, itemNo);
		}
		if (item != null) {
			OrderAddress orderAddresses = this.orderAddressDao.getById(item
					.getShiptoAddrId());
			if (orderAddresses != null) {
				return orderAddresses;
			}
		}
		return null;
	}

	public ShipMethod getShipVia(Integer orderNo, Integer itemNo,
			Integer shipMethodId, OrderItem item) {
		if (orderNo != null && itemNo != null) {
			item = this.getOrderItem(orderNo, itemNo);
		}
		if(orderNo!=null){
			List<OrderItem> itemList = this.orderItemsDao.findBy("orderNo", orderNo);
			if(itemList!=null&&!itemList.isEmpty()){
				item = itemList.get(0);
			}
		}
		if (item != null) {
			shipMethodId = item.getShipMethod();
		}
		if (shipMethodId != null) {
			ShipMethod shipMethod = this.shipMethodService
					.getShipMethodById(shipMethodId);
			return shipMethod;
		}
		return null;
	}

	public Double getWeight(Integer orderNo, Integer itemNo) {
		if (orderNo != null && itemNo != null) {
			OrderItem item = this.getOrderItem(orderNo, itemNo);
			if (item != null) {
				if (item.getType().equals("PRODUCT")) {
					Product product = this.productDao.findUniqueBy("catalogNo",
							item.getCatalogNo());
					if (product != null) {
						ShipCondition productShipCondition = this.productShipConditionDao
								.getById(product.getProductId());
						if (productShipCondition != null) {
							return productShipCondition.getDomShipWeight();
						}

					}

				} else if (item.getType().equals("SERVICE")) {
					com.genscript.gsscm.serv.entity.Service servie = this.servDao
							.findUniqueBy("catalogNo", item.getCatalogNo());
					if (servie != null) {
						ServiceShipCondition serviceShipCondition = this.serviceShipConditionDao
								.getById(servie.getServiceId());
						if (serviceShipCondition != null) {
							return serviceShipCondition.getDomShipWeight();
						}
					}
				}
			}
		}
		return 0.0d;
	}

	public List<ManuDocument> getDocumentList(Integer[] orderNoList,
			Integer[] itemNoList) {
		List<Integer> ids = this.workOrderDao.getIdByOrderItem(orderNoList,
				itemNoList);
		if (ids != null && !ids.isEmpty()) {
			return this.manuDocumentDao.getDocumentList(ids,
					DocumentType.MANU_WORKORDER);
		} else {
			return null;
		}

	}
	
	public List<ManuDocument> getDocumentList(Integer orderNo) {
		List<Integer> ids = this.workOrderDao.getIdByOrderItem(orderNo);
		if (ids != null && !ids.isEmpty()) {
			return this.manuDocumentDao.getDocumentList(ids,
					DocumentType.MANU_WORKORDER);
		} else {
			return null;
		}

	}

	public InstructionDTO getNotes(Integer id, String type) {
		InstructionDTO dto = new InstructionDTO();
		CustomerNote noteDTO = null;
		OrganizationNote orgNote = null;
		DocumentType docType = null;
		if ("CUSTOMER".equals(type)) {
			noteDTO = this.customerNoteDao.get(id);
			docType = DocumentType.CUSTOMER_NOTE_TYPE;
			dto.setType("Shipment Notes");
			dto.setDescription(noteDTO.getDescription());
		} else {
			orgNote = this.organizationNoteDao.get(id);
			docType = DocumentType.ORGANIZATION_NOTE;
			dto.setType("Shipment Notes");
			dto.setDescription(orgNote.getDescription());
		}
		/*
		 * User createUser = this.userDao.getById(note.getCreatedBy()); if
		 * (createUser != null) { dto.setCreateUser(createUser.getLoginName());
		 * }
		 */
		List<NoteDocument> docList = this.noteDocumentDao.getDocumentList(id,
				docType);
		List<Document> orderDocList = new ArrayList<Document>();
		for (NoteDocument custDocument : docList) {
			Document document = new Document();
			document = dozer.map(custDocument, Document.class);
			orderDocList.add(document);
		}
		dto.setDocumentList(orderDocList);
		return dto;
	}

	public List<ShipPackageLines> getLineQutByPackage(Integer packageId) {
		List<ShipPackageLines> lines = this.shipPageageLineDao
				.getLineQutByPackage(packageId);
		// 取得order的每个item的unitPrice
		for (ShipPackageLines line : lines) {
			Integer orderNo = line.getOrderNo();
			List<OrderItem> items = orderItemsDao.getOrderAllItemList(orderNo);
			for (OrderItem item : items) {
				if (line.getItemNo().compareTo(item.getItemNo()) == 0) {
					line.setUnitPrice(item.getUnitPrice());
					line.setShortDesc(item.getShortDesc());
					continue;
				}
			}
		}
		return lines;
	}

	public Integer getCNSOByUSSO(Integer so) {
		List<Integer> soList = this.orderDao.getSOCNByUSSO(so);
		if (soList != null && !soList.isEmpty()) {
			return soList.get(0);
		}
		return null;
	}

	public boolean checkOrderPackageItem(Integer orderNo, Integer itemNo,
			Integer oPackageId) {
		List<OrderPackageItem> orderPackageList = orderPackageItemDao
				.getPackagesForItem(orderNo, itemNo);
		if (orderPackageList != null && !orderPackageList.isEmpty()
				&& oPackageId.equals(orderPackageList.get(0).getPackageId())) {
			return true;
		}
		return false;
	}

	public List<ShipDocDTO> getProductDocument(String[] orderNoList,
			String[] itemNoList) {
		List<OrderItem> orderItemList = this.orderItemsDao.getOrderItemList(
				orderNoList, itemNoList);
		if (orderItemList != null && !orderItemList.isEmpty()) {
			List<ShipDocDTO> shipDocList = new ArrayList<ShipDocDTO>();
			for (OrderItem orderItem : orderItemList) {
				ShipDocDTO shipDocDTO = new ShipDocDTO();
				shipDocDTO.setCatalogNo(orderItem.getCatalogNo());
				shipDocDTO.setItemNo(orderItem.getItemNo().toString());
				shipDocDTO.setOrderNo(orderItem.getOrderNo().toString());
				String type = "'Document-DATASHEET','Document-PROTOCOL','Document-TECHNICAL_MANUAL'";
				String fistCodeCatalog = orderItem.getCatalogNo().substring(0,1);
				String doubleCodeCatalog = orderItem.getCatalogNo().substring(
						0, 2);
				if (fistCodeCatalog.equals("A")
						|| doubleCodeCatalog.equals("RP")
						|| fistCodeCatalog.equals("Z")
						|| fistCodeCatalog.equals("C")
						|| fistCodeCatalog.equals("M")
						|| doubleCodeCatalog.equals("DA")
						|| fistCodeCatalog.equals("B")
						|| fistCodeCatalog.equals("D")) {
					type = "'Document-DATASHEET'";
				} else if (fistCodeCatalog.equals("L")) {
					type = "'Document-PROTOCOL'";
				} else if (fistCodeCatalog.equals("E")) {
					if(orderItem.getCatalogNo().equals("E00007")||orderItem.getCatalogNo().equals("E00012")||orderItem.getCatalogNo().equals("E00019")){
						type = "'Document-PROTOCOL','Document-DATASHEET'";
					}else{
						type = "'Document-PROTOCOL'";
					}
					
				} else if (doubleCodeCatalog.equals("SD")) {
					type = "'Document-PRODUCTINFO'";
				}

				List<Documents> documentsList = this.documentsDao
						.getProductDocumentByOrderItemList(orderItem, type);
				if (documentsList != null && !documentsList.isEmpty()) {
					List<ManuDocument> manuDocumentList = new ArrayList<ManuDocument>();
					for (Documents documents : documentsList) {
						ManuDocument manuDoc = new ManuDocument();
						manuDoc.setFilePath(documents.getDocFilePath());
						manuDoc.setDocName(documents.getDocFileName());
						if (documents.getOldFlag() != null) {
							manuDoc.setIsOldProdctFile(documents.getOldFlag());
						}
						manuDocumentList.add(manuDoc);
					}
					if (manuDocumentList != null && !manuDocumentList.isEmpty()) {
						shipDocDTO.setManuDoc(manuDocumentList);
					} else {
						shipDocDTO.setManuDoc(null);
					}
				}
				shipDocList.add(shipDocDTO);
			}
			return shipDocList;
		}
		return null;
	}
	
	/*
	 * 将文件copy 到指定文件夹下。并进行打包成zip文件。
	 */
	public String copyToPath(String fileName,String oldFlag,String newPath ){
		//newPath = "/tmp/mingrs";
		//System.out.println(fileName+">>>>>>>>>>>");
		if (fileName != null && fileName.length() > 4) {
			String http = fileName.substring(0, 4);
			if (http.equals("http")) {
				return "true";
				//return PrintUtil.printPanelDesktop(fileName);
			}
		}
		File fileDoc = new File(newPath+"/"+fileName);
		if(fileDoc.exists()){
			return "true";
		}
		String printPath = "";
		if(oldFlag==null){
			printPath = fileService.getUploadPath();
		}else{
			if (oldFlag.equals("1") || oldFlag.equals("3")) {
				printPath = fileService.getOldUploadPath();
			} else if (oldFlag.equals("2") || oldFlag.equals("4")) {
				printPath = fileService.getUploadPath();
			}
		}
		try {
			FilelUtil.copyToNewFile(printPath+fileName, newPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(printPath+fileName+">>>>>>>>>>>");
		return "true";
	}
	
	/*
	 * 打印 对应的文件。
	 */
	public String printPanel(String fileName, String oldFlag) {
		System.out.println("fileName1="+fileName);
		if(fileName==null||fileName.length()<5){
			return "success";
		}
		String fileName3 = fileName.substring(fileName.length()-3, fileName.length());
		String fileName4 = fileName.substring(fileName.length()-4, fileName.length());
		/*if(!(fileName3.equalsIgnoreCase("doc")||fileName3.equalsIgnoreCase("pdf")||fileName4.equalsIgnoreCase("docx"))){
			return "success";
		}*/
		if (fileName != null && fileName.length() > 4) {
			String http = fileName.substring(0, 4);
			if (http.equals("http")) {
				return "success";
				//return PrintUtil.printPanelDesktop(fileName);
			}
		}
		String printPath = "";
		if(oldFlag==null){
			printPath = fileService.getUploadPath();
		}else{
			if (oldFlag.equals("1") || oldFlag.equals("3")) {
				printPath = fileService.getOldUploadPath();
			} else if (oldFlag.equals("2") || oldFlag.equals("4")) {
				printPath = fileService.getUploadPath();
			}
		}
		//System.out.println("fileName="+printPath+fileName);
		//return PrintUtil.printPanelDesktop(printPath+fileName);
		System.out.println("fileName="+fileName);
		return PrintUtil.printLinux(printPath+fileName,fileName);
	}
	
	public String getNotReceivedItem(String orderNo){
		String notReceivedItem = "";
		List<OrderItem> orderItemList = this.orderItemsDao.searchNotReceivedItem(orderNo);
		if(orderItemList!=null&&!orderItemList.isEmpty()){
			for(OrderItem oi:orderItemList){
				if(!notReceivedItem.equals("")){
					notReceivedItem+=",";
				}
				notReceivedItem +=oi.getItemNo();
			}
		}
		return notReceivedItem;
	}
	
	public String getExpiredItem(String orderNo){
		List<OrderItem> orderItemList = this.orderItemsDao.findBy("orderNo", Integer.valueOf(orderNo));
		String expiredItem="";
		if(orderItemList!=null&&!orderItemList.isEmpty()){
			Date date = new Date();
			OrderProcessLog log = this.orderProcessLogDao
			.getOrderLastConfirm(Integer.valueOf(orderNo));
			for(OrderItem item : orderItemList){
				Integer leadTime = 0;
				
				if (item.getType().equals("PRODUCT")) {
					Product product = this.productDao
							.findUniqueBy("catalogNo", item
									.getCatalogNo());
					if (product != null
							&& product.getLeadTime() != null) {
						leadTime = product.getLeadTime();
					}
				} else if (item.getType().equals("SERVICE")) {
					com.genscript.gsscm.serv.entity.Service service = this.servDao
							.findUniqueBy("catalogNo", item
									.getCatalogNo());
					if (service != null
							&& service.getLeadTime() != null) {
						leadTime = service.getLeadTime();
					}
				}
				Integer overDue = 0;
				if (log != null && log.getProcessDate() != null) {
					overDue = Integer.valueOf(DateUtils.caculate2Days(
							date, log.getProcessDate()));
					overDue = overDue - leadTime;
				}
				if (overDue > 0) {
					if(!expiredItem.equals("")){
						expiredItem+=",";
					}
					expiredItem +=item.getItemNo();
				}
			}
		}
		
		return expiredItem;
	}

	public void saveShippingChargLogDao(ShippingChargeLog log) {
		this.shippingChargeLogDao.save(log);
	}

	public void saveShipPackageByPackageId(Integer packageId, String trackingNo) {
		this.shipPackageDao.updateShipPackagesByPackageIdNo(packageId,
				trackingNo);
	}

	public ShippingDao getShippingdao() {
		return shippingdao;
	}

	public void setShippingdao(ShippingDao shippingdao) {
		this.shippingdao = shippingdao;
	}

	public ShipPackageLineDao getLineDao() {
		return lineDao;
	}

	public void setLineDao(ShipPackageLineDao lineDao) {
		this.lineDao = lineDao;
	}

	public ShippingPackagesDao getPackagesdao() {
		return packagesdao;
	}

	public void setPackagesdao(ShippingPackagesDao packagesdao) {
		this.packagesdao = packagesdao;
	}

	public DozerBeanMapper getDozer() {
		return dozer;
	}

	public void setDozer(DozerBeanMapper dozer) {
		this.dozer = dozer;
	}
}