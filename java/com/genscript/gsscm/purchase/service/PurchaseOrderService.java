package com.genscript.gsscm.purchase.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.MimeMailService;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.inventory.dao.ReceivingLogDao;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.dao.OrderErpMappingDao;
import com.genscript.gsscm.order.entity.OrderErpMapping;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.ProductClassDao;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.purchase.dto.PurchaseOrderDTO;
import com.genscript.gsscm.purchase.dao.PurchaseOrderDao;
import com.genscript.gsscm.purchase.dao.PurchaseOrderItemDao;
import com.genscript.gsscm.purchase.dao.VendorDao;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.purchase.entity.PurchaseOrderItem;
import com.genscript.gsscm.purchase.entity.Vendor;
import com.genscript.gsscm.serv.dao.ServiceClassificationDao;
import com.genscript.gsscm.serv.entity.ServiceClassification;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipPackageLines;
@Service
@Transactional
public class PurchaseOrderService {
	/**
	 * spring注入purchaseOrderDao对象
	 */
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	@Autowired
	private PurchaseOrderItemDao purchaseOrderItemDao;
	@Autowired
	private ProductClassDao productClassDao;
	@Autowired
	private ServiceClassificationDao serviceClassificationDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ReceivingLogDao receivingLogDao;
	@Autowired
	private OrderErpMappingDao orderErpMappingDao;
    @Autowired
	private MimeMailService mimeMailService;
	
	@Autowired
	private VendorDao vendorDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DozerBeanMapper dozer;


	/**
	 * 查询po表中status，返回一个list集合
	 * @return
	 */
	public List<PurchaseOrder> getPoStatus() {
		List<PurchaseOrder> list = this.purchaseOrderDao.getPoStatus();
		return list;
	}
	/**
	 * po，根据条件查询
	 * @param page
	 * @param srch
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<PurchaseOrderDTO> searchPo(Page<PurchaseOrder> page, PurchaseOrderDTO srch)
	{
		Page<PurchaseOrderDTO> retPage = new Page<PurchaseOrderDTO>();
		List<PurchaseOrderDTO> dtoList = new ArrayList<PurchaseOrderDTO>();
		page = this.purchaseOrderDao.searchPo(page, srch);
		if (page.getResult() != null) {
			for (PurchaseOrder po : page.getResult()) {
				PurchaseOrderDTO dto = dozer.map(po, PurchaseOrderDTO.class);
				if(po.getStatus().equals("OP")){
					dto.setStatus("Open");
				}
				Warehouse wh = this.purchaseOrderDao.getWarehouseById(po.getWarehouseId());
				Vendor vendor = this.vendorDao.getVendorByNo(po.getVendorNo());
				dto.setWarehouse(wh);
				dto.setVendors(vendor);
				dto.setSrcSoNo(po.getSrcSoNo());
				dto.setSubTotal(po.getSubTotal());
				dto.setPurchaseContact(po.getPurchaseContact());
				
				if(po.getPurchaseContact()!=null){
					User user = this.userDao.getById(po.getPurchaseContact());
					if(user!=null&&user.getEmployee()!=null){
						dto.setPurchaseContactName(user.getEmployee().getEmployeeName());
					}
				}
				List<PurchaseOrderItem> pOrderItemList = this.purchaseOrderItemDao.searchPurchaseOrderItem(dto.getOrderNo(), 1);
				if(pOrderItemList!=null&&!pOrderItemList.isEmpty()){
					PurchaseOrderItem item = pOrderItemList.get(0);
					if(item.getType().equals("PRODUCT")){
						ProductClass pc = this.productClassDao.getById(item.getClsId());
						if(pc!=null){
							dto.setServiceType(pc.getName());
						}
					}else{
						ServiceClassification sc = this.serviceClassificationDao.getById(item.getClsId());
						if(sc!=null){
							dto.setServiceType(sc.getName());
						}
					}
				}
				dto.setOrderType(po.getOrderType());
				List<Customer> customerList = this.customerDao.searchCustomerByOrderNo(dto.getSrcSoNo());
				if(customerList!=null&&!customerList.isEmpty()){
					dto.setGreenAccFlag(customerList.get(0).getGreenAccFlag());
				}
				OrderErpMapping orderErpMapping = orderErpMappingDao.getById(dto.getSrcSoNo());
				if(orderErpMapping != null && orderErpMapping.getErpUsPo() != null){
					dto.setUsPOrderNo(orderErpMapping.getErpUsPo());
				}
				OrderMain order = this.orderDao.getById(dto.getSrcSoNo());
				if(order!=null){
					User user = this.userDao.getById(order.getTechSupport());
					if(user !=null){
						dto.setTam(user.getFirstName()+" "+ user.getLastName());
					}
				}
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}
	/**
	 * 美国，根据trackingNo查询详细信息
	 * @param trackingNo
	 * @return
	 */
	public ShipPackage getShipPageInfo(String trackingNo) {
		ShipPackage sp = this.purchaseOrderDao.getShipPageInfo(trackingNo);
		return sp;
	}
	/**
	 * 美国，根据Package No查询，返回一个list集合
	 * @param  packageId
	 * @return List
	 */
	public List<ShipPackageLines> getListSpl(Integer packageId) {
		List<ShipPackageLines> list = this.purchaseOrderDao.getListSpl(packageId);
		return list;
	}
	/**
	 * 根据orderNo查询详细信息
	 * @param  trackingNo 
	 * @return ShipPackage
	 */
	public List<OrderItem> getOrderPageDetail(Integer orderNo) {
		List<OrderItem> list = this.purchaseOrderDao.getOrderPageDetail(orderNo);
		return list;
	}
	/**
	 * 查询符合条件的TarckingNo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getListTrackingNo() {
		List list = this.purchaseOrderDao.getListTrackingNo();
		return list;
	}
	/**
	 * 根据itemNo查询trackingNo
	 * @param orderNo
	 * @param itemNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getShipPackTrackNo(Integer itemNo){
		List list = this.purchaseOrderDao.getShipPackTrackNo(itemNo);
		return list;
	}
	
	/*
	 * 发送邮件通知;
	 */
	public void sendMail(String miss){
		String[] misss = miss.split(",");
		Map<String ,String > orderNoMap =new  HashMap<String,String>();
		String orderEmail = "";
		String content="Hi:<br/>";
		String subject = "SCM receiving PO";
		for(int i = 0;i<misss.length;i++){
			
			if(misss[i]!=null){
				String[] missss = misss[i].split("_");
				orderNoMap.put(missss[2], missss[2]);
				if(missss.length>=6){
					content += missss[0]+", Order No:"+missss[2]+", PONO:"+missss[1]+", item No:"+missss[3]+", Qty Shipped:"+missss[4]+", Qty Received:"+missss[5]+"</br>";
				}
			}
		}
		for(Map.Entry<String,String> entry:orderNoMap.entrySet()){
			String orderNo = entry.getValue().toString();
			OrderMain order  = this.orderDao.getById(Integer.valueOf(orderNo));
			if(order!=null){
				User user = this.userDao.getById(order.getSalesContact());
				if(user!=null&&user.getEmail()!=null&&!user.getEmail().equals("")){
					if(orderEmail.equals("")){
						orderEmail = user.getEmail();
					}else{
						orderEmail +=","+user.getEmail();
					}
				}
			}
		}
		System.out.println("orderEmail="+orderEmail);
		System.out.println("content="+content);
		if(!orderEmail.equals("")){
			mimeMailService.sendMail(orderEmail, subject, content, new ArrayList<String>());
		}
		
	}
	
	public void savePO(PurchaseOrder po){
		this.purchaseOrderDao.save(po);
	}
	
	public void updatePO(Integer po,String status){
		this.purchaseOrderDao.updatePOStatus(po, status);
	}
	
	public PurchaseOrder getPurchaseOrderById(Integer id){
		return this.purchaseOrderDao.getById(id);
	}
	
	public PurchaseOrder getPurchaseOrderBySoId(Integer id){
		List<PurchaseOrder> orderList = this.purchaseOrderDao.findBy("srcSoNo", id);
		if(orderList!=null&&!orderList.isEmpty()){
			return orderList.get(0);
		}else{
			return null;
		}
	}
	
	public boolean findReceiveLineFlag(Integer orderNo){
		 //List<String> orderNoList = new ArrayList<String>();
		 List listP = this.purchaseOrderDao.findReceiveFlagLine(orderNo);
		 String sizeP = null;
		 if(listP!=null&&!listP.isEmpty()){
			 sizeP = ((Object[])listP.get(0))[1]+"";
			 if(sizeP==null||sizeP.equals("0")||sizeP.equals("null")){
				 sizeP = ((Object[])listP.get(0))[2]+"";
			 }
		 }
		 List listR = this.receivingLogDao.findReceiveFlagLine(orderNo);
		 String sizeR = null;
		 if(listR!=null&&!listR.isEmpty()){
			 sizeR = ((Object[])listR.get(0))[1]+"";
		 }
		 if(sizeP==null||sizeR==null||!(Double.valueOf(sizeR)>=Double.valueOf(sizeP))){
			return false; 
		 }
		 return true;
	}
	public void updateReceiveFlag(Integer orderNo){
		this.purchaseOrderDao.updateReceiveFlag(orderNo);
	}
	
	public List<PurchaseOrder> findBySrcSoNos(String srcSoNos){
		return this.purchaseOrderDao.findBySrcSoNo(srcSoNos);
	}
	
	public void updatePurchaseItemFileDownloaded(String orderItems,String value){
		this.purchaseOrderItemDao.updateOrderItemFileDownloaded(orderItems, value);
	}
}
