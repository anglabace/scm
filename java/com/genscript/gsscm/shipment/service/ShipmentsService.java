package com.genscript.gsscm.shipment.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.ExceptionOut;
import com.genscript.gsscm.common.MimeMailService;
import com.genscript.gsscm.common.MyX509TrustManager;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.common.constant.OrderInstructionType;
import com.genscript.gsscm.common.util.Arith;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.SoapUtil;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.inventory.dao.ReservationDao;
import com.genscript.gsscm.inventory.entity.PickingLogs;
import com.genscript.gsscm.inventory.entity.Reservation;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.manufacture.dao.WorkOrderLotDao;
import com.genscript.gsscm.manufacture.dto.WorkOrderDTO;
import com.genscript.gsscm.manufacture.entity.WorkOrderLot;
import com.genscript.gsscm.order.dao.DocumentDao;
import com.genscript.gsscm.order.dao.OrderAddressDao;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.dao.OrderErpMappingDao;
import com.genscript.gsscm.order.dao.OrderGeneSynthesisDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.dao.OrderNoteDao;
import com.genscript.gsscm.order.dao.OrderPackageDao;
import com.genscript.gsscm.order.dao.OrderProcessLogDao;
import com.genscript.gsscm.order.dao.PaymentVoucherDao;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.OrderErpMapping;
import com.genscript.gsscm.order.entity.OrderGeneSynthesis;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.entity.OrderNote;
import com.genscript.gsscm.order.entity.OrderPackage;
import com.genscript.gsscm.order.entity.OrderProcessLog;
import com.genscript.gsscm.order.entity.PaymentVoucher;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.purchase.service.ReadReceiveXmlService;
import com.genscript.gsscm.quoteorder.dto.InstructionDTO;
import com.genscript.gsscm.quoteorder.service.QuoteOrderService;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.shipment.dao.CanceledShipPackageDao;
import com.genscript.gsscm.shipment.dao.PickingLogDao;
import com.genscript.gsscm.shipment.dao.ShipClerkDao;
import com.genscript.gsscm.shipment.dao.ShipMethodDao;
import com.genscript.gsscm.shipment.dao.ShipPackageDao;
import com.genscript.gsscm.shipment.dao.ShipPackageErrorsDao;
import com.genscript.gsscm.shipment.dao.ShipPackageLineDao;
import com.genscript.gsscm.shipment.dao.ShipmentLinesDao;
import com.genscript.gsscm.shipment.dao.ShipmentsDao;
import com.genscript.gsscm.shipment.dao.ShippingAnnouncementDao;
import com.genscript.gsscm.shipment.dao.ShippingDao;
import com.genscript.gsscm.shipment.dto.ShipClerkDTO;
import com.genscript.gsscm.shipment.dto.ShipPackageDTO;
import com.genscript.gsscm.shipment.dto.ShipPackageLineDTO;
import com.genscript.gsscm.shipment.dto.ShipmentLinesDTO;
import com.genscript.gsscm.shipment.dto.ShipmentsDTO;
import com.genscript.gsscm.shipment.dto.ShipmentsSrchDTO;
import com.genscript.gsscm.shipment.dto.ViewPackingSlipDTO;
import com.genscript.gsscm.shipment.entity.CanceledShipPackages;
import com.genscript.gsscm.shipment.entity.ShipClerk;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipPackageErrors;
import com.genscript.gsscm.shipment.entity.ShipPackageLines;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.genscript.gsscm.shipment.entity.ShipmentLine;
import com.genscript.gsscm.shipment.entity.ShippingAnnouncement;
import com.genscript.gsscm.ws.WSException;

@Service
@Transactional
public class ShipmentsService{
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private OrderAddressDao orderAddressDao;
	@Autowired
	private OrderDao orderDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ShippingAnnouncementDao shippingAnnouncementDao;
    @Autowired
    private ReservationDao reservationDao;
	@Autowired
	private PaymentVoucherDao paymentVoucherDao;
	@Autowired
	private ShipmentsDao shipmentsdao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private ShipPackageDao packagesdao;
	@Autowired
	private ShipmentLinesDao shipmentlinesdao;
	@Autowired
	private OrderGeneSynthesisDao orderGeneSynthesisDao;
	@Autowired
	private ShipPackageLineDao shipPageageLineDao;
	@Autowired
	private OrderItemDao orderitemsdao;
	@Autowired
	private CanceledShipPackageDao canceledShipPackageDao;
	@Autowired
	private WorkOrderLotDao workOrderLotDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrderErpMappingDao orderErpMappingDao;
	@Autowired
	private ShipPackageDao shipPackageDao;
	@Autowired
	private OrderNoteDao orderNoteDao;
	@Autowired
	private DocumentDao documentDao;
	
	@Autowired
	private ShipClerkDao shipClerkDao;
	@Autowired
	private ShipMethodDao shipMethodDao;
	@Autowired
	private QuoteOrderService quoteOrderService;
	@Autowired
	private ShippingService shippingService;
	@Autowired
	private ShipPackageErrorsDao shipPackageErrorsDao;
	@Autowired
	private ShipMethodService shipMethodService;
	@Autowired
	private OrderPackageDao orderPackageDao;
	@Autowired
	private ShippingDao shippingdao;
	@Autowired
	private ReadReceiveXmlService readReceiveXmlService;
	@Autowired
	private PickingLogDao pickingLogDao;
    @Autowired
    private OrderProcessLogDao orderProcessLogDao;
    @Autowired
	private ProductDao productDao;
	@Autowired
	private ServiceDao servDao;
	@Autowired
	private MimeMailService mimeMailService;
    
	public ShipPackageErrorsDao getShipPackageErrorsDao() {
		return shipPackageErrorsDao;
	}

	public void setShipPackageErrorsDao(ShipPackageErrorsDao shipPackageErrorsDao) {
		this.shipPackageErrorsDao = shipPackageErrorsDao;
	}
	
	public ShipmentsDao getShipmentsdao() {
		return shipmentsdao;
	}

	public void setShipmentsdao(ShipmentsDao shipmentsdao) {
		this.shipmentsdao = shipmentsdao;
	}

	public ShipmentLinesDao getShipmentlinesdao() {
		return shipmentlinesdao;
	}


	public ShipPackageDao getPackagesdao() {
		return packagesdao;
	}

	public void setPackagesdao(ShipPackageDao packagesdao) {
		this.packagesdao = packagesdao;
	}

	public void setShipmentlinesdao(ShipmentLinesDao shipmentlinesdao) {
		this.shipmentlinesdao = shipmentlinesdao;
	}

	public OrderItemDao getOrderitemdao() {
		return orderitemsdao;
	}

	public void setOrderitemdao(OrderItemDao orderitemsdao) {
		this.orderitemsdao = orderitemsdao;
	}
	
	public ShipmentsDao getShipmentsDaodao() {
		return shipmentsdao;
	}

	public void setShipmentsDaodao(ShipmentsDao shipmentsdao) {
		this.shipmentsdao = shipmentsdao;
	}

	/**
	 * 根据条件查询Shipment并返回Page对象
	 * @param page
	 * @param shipschdto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<ShipmentsDTO> searchShipments(Page<Shipment> page, ShipmentsSrchDTO shipschdto) {
		Page<ShipmentsDTO> retPage = new Page<ShipmentsDTO>();
		List<ShipmentsDTO> dtoList = new ArrayList<ShipmentsDTO>();
		page = this.shipmentsdao.searchShipments(page, shipschdto);
		if (page.getResult() != null) {
			for (Shipment ship : page.getResult()) {
				ShipmentsDTO dto = dozer.map(ship, ShipmentsDTO.class);
				dto.setWarehouse(ship.getWareHouse()); 
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}
	
	/**
	 * 查询Shipment并返回Page对象
	 * @param page
	 * @param filters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<ShipmentsDTO> searchShipments(Page<Shipment> page,
			List<PropertyFilter> filters) {
		Page<ShipmentsDTO> retPage = new Page<ShipmentsDTO>();
		List<ShipmentsDTO> dtoList = new ArrayList<ShipmentsDTO>();
		page = this.shipmentsdao.searchShipments(page, filters);
		if (page.getResult() != null) {
			for (Shipment ship : page.getResult()) {
				ShipmentsDTO dto = dozer.map(ship, ShipmentsDTO.class);
				dto.setWarehouse(ship.getWareHouse());
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}
	
	/**
	 * 根据packageId查询细节
	 * @param packageId
	 * @return ShipPackagesDTO
	 */
	public ShipPackageDTO getPckById(Integer packageId) {
		ShipPackageDTO pDto = null;
		ShipPackage app = this.packagesdao.findUniqueBy("packageId", packageId);
		
		// shippingClerk
		List<User> listShipClerk = this.packagesdao.getShipPackageListByPackageId(packageId);
		String shippingClerk = "";
		for(int j=0;listShipClerk != null && j<listShipClerk.size();j++){
			User u = listShipClerk.get(j);
			if(!"".equals(u.getLoginName()) && u.getLoginName() != null ){
				shippingClerk += ","+u.getLoginName();
				//app.setShippingClerk(u.get)
			}
		}
	/*	if(shippingClerk.trim().length() > 0)
			app.setShippingClerk(shippingClerk.substring(1));*/
		// modifydate
		app.setModifyDate(new Date());
		
		// modiby
//		List<User> ListModiby = this.packagesdao.getModibyNameByPackageId(packageId);
//		String modibyName = "";
//		for(int m=0;ListModiby != null && m<ListModiby.size();m++){
//			User u2 = ListModiby.get(m);
//			if(!"".equals(u2.getLoginName()) && u2.getLoginName() != null){
//				modibyName += ","+u2.getLoginName();
//			}
//		}
//		if(modibyName.trim().length() > 0)
//			app.setModifiedBy(modibyName.substring(1));

//		app.setModifiedBy("admin");
		if (app != null) {
			pDto = dozer.map(app, ShipPackageDTO.class);
			if(app.getShipMethod()!=null){
				pDto.setShipVia(this.shipMethodDao.getById(app.getShipMethod()).getName());
			}
			if(app.getShippingClerk()!=null){
				User user = this.userDao.getById(app.getShippingClerk());
				pDto.setShippingClerk(user.getEmployee().getEmployeeName());
			}
		}
		this.shipmentsdao.getSession().evict(app);
		return pDto;
	}

	/**
	 * 根据shipmentId查询并返回ShipmentDTO对象
	 * @param shipmentId
	 * @return
	 */
	public ShipmentsDTO getShipById(String shipmentNo) {
		ShipmentsDTO sDto = null;
		sDto = this.shipmentsdao.getShipmentById(shipmentNo);
		return sDto;
		
	}
	
	public ShipmentsDTO getShipmentsById(String shipmentId) {
		Shipment shipment = shipmentsdao.getById(Integer.valueOf(shipmentId));
		ShipmentsDTO sDto = dozer.map(shipment, ShipmentsDTO.class);
		if(shipment!=null){
			if(shipment.getModifiedBy()!=null){
				User user = this.userDao.getById(shipment.getModifiedBy());
				if(user!=null){
					sDto.setModifyName(user.getLoginName());
				}
			}
			if(shipment.getShipClerkList()!=null){
				sDto.setShippingClerk(null);
				for(ShipClerk shipClerk : shipment.getShipClerkList()){
					if(sDto.getShippingClerk()==null){
						sDto.setShippingClerk(shipClerk.getUser().getEmployee().getEmployeeName());
					}else{
						sDto.setShippingClerk(sDto.getShippingClerk()+" , "+shipClerk.getUser().getEmployee().getEmployeeName());
					}
					
					sDto.setShippingClerkId(shipment.getShippingClerk());
				}
				
			}
			
			OrderAddress address = this.getShipTo(Integer.valueOf(shipment.getShipmentNo()), null);
			sDto.setShipTo(address.getFirstName()+" "+address.getMidName()+" "+address.getLastName());	
			
			List<ShipmentLine> sllist = this.shipmentsdao.getShipmentlinesList(shipmentId);
			String orderNo_ = "";
			List<Integer> inList = new ArrayList<Integer>();
			for(int j=0;j<sllist.size();j++){
				ShipmentLine sl = sllist.get(j);
				if((!"".equals(sl.getOrder().getOrderNo())) && (sl.getOrder().getOrderNo() != null) ){
					String isAdd = "0";
					for(Integer no : inList){
						if(no.equals(sl.getOrder().getOrderNo())){
							isAdd = "1";
						}
					}
					if(isAdd.equals("0")){
						inList.add(sl.getOrder().getOrderNo());
					}
					 //35226
				}
			}
			if(inList!=null&&!inList.isEmpty()){
				for(Integer no :inList){
					if(orderNo_.equals("")){
						orderNo_ += no+"";
					}else{
						orderNo_ += ","+no+"";
					}
				}
				List<OrderPackage> packageList = this.orderPackageDao.getOrderPackageByList(inList);
				Double shipAmt = quoteOrderService.getShipAmtFromPackage(packageList);
				if(shipAmt!=null){
					sDto.setShipAmt(shipAmt.toString());
				}
			}
			sDto.setOrderNo(orderNo_);
			System.out.println("orderno_="+orderNo_);
	
		}
		
		return sDto;
		
	}
	
	/*
	 * 获取shop to 
	 */
	
	/**
	 * 根据shipmentNo查询细节
	 * @param shipmentNo
	 * @return
	 */ 
	public ShipPackageDTO getShipPkgById(String shipmentNo) {
		ShipPackageDTO sDto = null;
		ShipPackage app = this.packagesdao.findUniqueBy("shipmentNo", shipmentNo);
		if (app != null) {
			sDto = dozer.map(app, ShipPackageDTO.class);
		}
		this.packagesdao.getSession().evict(app);
		return sDto;
		
	}
	
	/**
	 * 根据lineId查询细节(美国仓库)
	 * @param lineId
	 * @return
	 */
	public ShipmentLinesDTO getLineById(Integer lineId) {
		ShipmentLinesDTO slDto = null;
		ShipmentLine app = this.shipmentlinesdao.findUniqueBy("lineId", lineId);
		if (app != null){
			slDto = dozer.map(app, ShipmentLinesDTO.class);
		}
		if(app!=null){
			if(app.getModifiedBy()!=null){
				User user = userDao.getById(app.getModifiedBy());
				if(user!=null){
					slDto.setModifiedName(user.getLoginName());
				}
			}
			if(app.getOrder()!=null&&app.getOrder().getOrderNo()!=null&&app.getItemNo()!=null){
				OrderItem orderItem = this.orderitemsdao.getOrderItem(app.getOrder().getOrderNo(), app.getItemNo());
				slDto.setOrderQty(orderItem.getQuantity()+"");
				slDto.setQtyUom(orderItem.getQtyUom());
				slDto.setOrderSize(orderItem.getSize()+"");
				slDto.setSizeUom(orderItem.getSizeUom());
				if(orderItem.getShipMethod()!=null){
					slDto.setShipMethod(this.shipMethodService.getShipMethodById(orderItem.getShipMethod()).getName());
				}
			}
			if(app.getLineId()!=null){
				List<ShipPackageLines> shipPackageList = this.shipPageageLineDao.findBy("shipmentLines.lineId", app.getLineId());
				if(shipPackageList!=null){
					Double qut = 0.0;
					Double size = 0.0;
					for(ShipPackageLines shipPackageLine:shipPackageList){
						if(shipPackageLine.getStatus().equals("Shipped")){
							qut = Arith.add(qut, shipPackageLine.getQuantity());
							size = Arith.add(size, shipPackageLine.getSize());
						}
					}
					slDto.setShippedSize(size);
					slDto.setShipQuantity(qut);
				}
			}
			if(app.getShipments()!=null&&app.getShipments().getShippingClerk()!=null){
				User user = userDao.getById(app.getShipments().getShippingClerk());
				slDto.setShipClerk(user.getEmployee().getEmployeeName());
			}
		}
		
		this.shipmentlinesdao.getSession().evict(app);
		return  slDto;
	}

	/**
	 * 根据pkgLineId查询细节(中国仓库)
	 * @param pkgLineId
	 * @return
	 */
	public ShipPackageLineDTO getLineByIdchina(Integer pkgLineId) {
		ShipPackageLineDTO splDto = null;
		ShipPackageLines app = this.shipPageageLineDao.findUniqueBy("pkgLineId",pkgLineId);
		if (app != null){
			splDto = dozer.map(app, ShipPackageLineDTO.class);
		}
		this.shipPageageLineDao.getSession().evict(app);
		System.out.println("splDto:"+splDto);
		return  splDto;
	}

	/**
	 * 查询shipment对象并返回List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getListByClert() {
		List list = this.shipmentsdao.getListByClert();
		return list;
	}
	
	/**
	 * 查询有操作权限的用户信息并返回List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getLoginName(Integer userId) {
		List list = this.shipmentsdao.getLoginName(userId);
		return list;
	}
	
	/**
	 * 查询status并返回List
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	public List getStatus(){
		List list = this.shipmentsdao.getStatus();
		return list;
	}*/
	
	/**
	 * 查询currency并返回List
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	public List getCurrency(){
		List list = this.shipmentsdao.getCurrency();
		return list;
	}*/
	
	/**
	 * 查询priority并返回List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getPriority(){
		List list = this.shipmentsdao.getPriority();
		return list;
	}
	
	/**
	 * 查询shippingRule并返回List
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	public List getShippingrule(){
		List list = this.shipmentsdao.getShippingrule();
		return list;
	}
	
	*//**
	 * 查询shippingType并返回List
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public List getShippingtype(){
		List list = this.shipmentsdao.getShippingtype();
		return list;
	}*/
	
	/**
	 * 查询Warehouse对象并返回List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getListByWareHouse() {
		List list = this.shipmentsdao.getListByWarehouse();
		return list;
	}
	
	/**
	 * 根据warehouseId查询Warehouse对象信息
	 * @param shipmentNo
	 * @return
	 */
	public Warehouse findById(Integer shipmentNo) {
		Warehouse wh = this.shipmentsdao.findById(shipmentNo);
		return wh;
	}
	
	/**
	 * 针对美国仓库的查询列表(有参数error,用于判断yes/no)
	 * @param page
	 * @param srch
	 * @param error
	 * @return
	 */
	public Page<ShipmentsDTO> searchu(Page<Shipment> page, ShipmentsDTO srch,String error)
	{
		try{
			
			if("".equals(error)){
				error = null;
			}
			List<ShipMethod> shipmethodList = this.shipMethodDao.getAll();
			Page<ShipmentsDTO> pageDTO = this.shipmentsdao.searchu(page, srch,error,shipmethodList);
			for(ShipmentsDTO dto : pageDTO.getResult()){
				OrderAddress address = this.getShipTo(Integer.valueOf(dto.getShipmentNo()), null);
				if(address!=null){
					dto.setShipTo(address.getFirstName()+" "+address.getMidName()+" "+address.getLastName()+",</br>"+address.getState()+", "+address.getCountry());
				}
				if(dto.getOrderNo()!=null&&!dto.getOrderNo().equals("")){
					String po = "";
					/*List<PurchaseOrder> list = this.purchaseOrderDao.getPurchaseOrderList(dto.getOrderNo());
					if(list!=null&&!list.isEmpty()){
						for(PurchaseOrder porder : list){
							if(po.equals("")){
								po = porder.getOrderNo()+"";
							}else{
								po += ","+porder.getOrderNo();
							}
						}
					}
					dto.setPoNo(po);*/
					List<OrderErpMapping> orderErpMappingList = orderErpMappingDao.getOrderList(dto.getOrderNo());
					if(orderErpMappingList != null && !orderErpMappingList.isEmpty()){
						for(OrderErpMapping orderErpMapping : orderErpMappingList){
							if(orderErpMapping.getErpUsPo()!=null){
								if(po.equals("")){
									po = orderErpMapping.getErpUsPo()+"";
								}else{
									po += ","+orderErpMapping.getErpUsPo();
								}
							}
							
						}
						
					}
					dto.setPoNo(po);
				}
				/*dto.setSendBys("");
				List<ShipPackage> packageList = this.shipPackageDao.findBy("shipments.shipmentId", dto.getShipmentId());
				List<Integer> sendByList = new ArrayList<Integer>();
				if(packageList!=null&&!packageList.isEmpty()){
					for(ShipPackage packages : packageList){
						if(packages!=null){
							Integer shipMethod = packages.getShipMethod();
							if(shipMethod!=null){
								ShipMethod shipMethod1 = this.shipMethodDao.getById(shipMethod);
								if(shipMethod1!=null){
									dto.setShipVia(shipMethod1.getName());
								}
							}
							if(packages.getSendBy()!=null){
								Integer is = 0;
								for(Integer sendBy : sendByList){
									if(sendBy.equals(packages.getSendBy())){
										is = 1;
									}
								}
								if(is==0){
									User user = this.userDao.getById(packages.getSendBy());
									dto.setSendBys(dto.getSendBys()+" "+user.getEmployee().getEmployeeName());
									sendByList.add(packages.getSendBy());
								}
								
							}
						}
					}
				}*/
				if(error!=null){
					String error1 = "NO";
					List<ShipPackageErrors> errors = this.shipPackageErrorsDao.getPackageErrorByShipmentId(dto.getShipmentId());
					if(errors!=null&&!errors.isEmpty()){
						error1 = "YES";
					}
					dto.setError(error1);
				}
			}
			return pageDTO;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 针对美国仓库的查询列表(无参)
	 * @param page
	 * @param srch
	 * @return
	 * @throws Exception 
	 */
	public Page<ShipmentsDTO> searchu(Page<Shipment> page, ShipmentsDTO srch,Integer userId,String userName) throws Exception
	{	
		System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		if(srch==null){
			srch = new ShipmentsDTO();
		}
		boolean isProductionManagerRole = false;
		
		if(userId!=null){
			//User user  = this.userDao.getById(userId);
			if(Constants.USERNAME_ADMIN.equals(userName)){
				srch.setShippingClerk(null);
			}else{
				srch.setShippingClerk(userId.toString());
			}
		}
		/*if (!Constants.USERNAME_ADMIN.equals(srch.getShippingClerk())) {
            isProductionManagerRole = userRoleDao.checkIsContainsManagerRole(Constants.ROLE_PRODUCTION_MANAGER);
        }else{
        	isProductionManagerRole = true;
        }
		if(isProductionManagerRole){
			srch.setShippingClerk(null);
		}*/
		List<ShipMethod> shipmethodList = this.shipMethodDao.getAll();
		Page<ShipmentsDTO> pageDTO = this.shipmentsdao.searchu(page, srch, "",shipmethodList);
		for(ShipmentsDTO dto : pageDTO.getResult()){
			OrderAddress address = this.getShipTo(Integer.valueOf(dto.getShipmentNo()), null);
			dto.setShipTo(address.getFirstName()+" "+address.getMidName()+" "+address.getLastName());
			
			//System.out.println(dto.getShipmentNo()+" "+dto.getShipTo());
		}
		return pageDTO;
		
	}
	
	/**
	 * 针对中国仓库的查询列表(有参数error,用于判断yes/no)
	 * @param page
	 * @param srch
	 * @return
	 */
	public Page<ShipPackageDTO> searchch(Page<ShipPackage> page, ShipPackageDTO srch,String error)throws Exception
	{
		Page<ShipPackageDTO> retPage = new Page<ShipPackageDTO>();
		retPage = this.packagesdao.searchch(page, srch,error);
		return retPage;
	}
	
	/**
	 * 针对中国仓库的查询列表(无参)
	 * @param page
	 * @param srch
	 * @return
	 */
	public Page<ShipPackageDTO> searchch(Page<ShipPackage> page, ShipPackageDTO srch)throws Exception{
		return this.packagesdao.searchch(page, srch,"");
	}
	
	/**
	 * 根据shipmentId查询相关的ShipmentsLines细节数据
	 * @param page
	 * @param srch
	 * @return Page
	 */
	public Page<ShipPackageDTO> searchLine(Page<ShipPackage> page, ShipPackageDTO srch)throws Exception
	{
		Page<ShipPackageDTO> retPage = new Page<ShipPackageDTO>();
		retPage = this.packagesdao.searchch(page, srch,"");
		return retPage;
	}
	
	/**
	 * 根据shipmentNo查询相关的ShipmentsLines数据(分页)
	 * @param page
	 * @param shipmentId
	 * @return Page
	 */
	public Page<ShipmentLinesDTO> searchLine(Page<ShipmentLine> page,Integer shipmentId) {
		
		Page<ShipmentLinesDTO> retPage = null;
		retPage = this.shipmentlinesdao.searchLine(page, shipmentId);
		if(retPage!=null&&retPage.getResult()!=null){
			for(ShipmentLinesDTO dto : retPage.getResult()){
				Integer qut =0;
				Double size = 0.0d;
				List<ShipPackageLines> shipPackageLines = this.shipPageageLineDao.findBy("shipmentLines.lineId", dto.getLineId());
				if(shipPackageLines!=null&&!shipPackageLines.isEmpty()){
					for(ShipPackageLines lines : shipPackageLines){
						qut += lines.getQuantity();
						if(lines.getSize()!=null){
							size = Arith.add(size, lines.getSize());
						}
					}
				}
				if(dto.getType().equals("SERVICE")){
					if(dto.getOrderQty()!=null){
						dto.setQuantity(Integer.valueOf(dto.getOrderQty()));
					}else{
						dto.setQuantity(1);
					}
					dto.setSize(size);
				}else{
					if(dto.getOrderSize()!=null){
						dto.setSize(Double.valueOf(dto.getOrderSize()));
					}else{
						dto.setSize(1.0);
					}
					dto.setQuantity(qut);
				}
			}
			
		}
		
		return retPage;
	}
	
	/**
	 * 根据shipmentNo查询相关的ShipPackages细节数据(无)
	 * @param page
	 * @param srch
	 * @return Page
	 */
	public Page<ShipPackageDTO> searchPkg(Page<ShipPackage> page, ShipPackageDTO srch)throws Exception
	{
		Page<ShipPackageDTO> retPage = new Page<ShipPackageDTO>();
		retPage = this.packagesdao.searchch(page, srch,"");
		return retPage;
	}
	
	/**
	 * 根据shipmentNo查询相关的ShipPackages数据(分页)
	 * @param page
	 * @param shipmentId
	 * @return Page
	 */
	@SuppressWarnings("unchecked")
	public Page<ShipPackageDTO> searchPkg(Page<ShipPackage> page,Integer shipmentId) {
		
		Page<ShipPackageDTO> retPage = null;
		List<ShipPackageDTO> dtoList = new ArrayList<ShipPackageDTO>();
		page = this.packagesdao.searchPkg(page, shipmentId);
		if (page.getResult() != null) {
			for (ShipPackage sp : page.getResult()) {
				ShipPackageDTO dto = dozer.map(sp, ShipPackageDTO.class); //出错
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}
	
	
	/**
	 * 获取符合条件的shipmentNo(暂时未用此方法)
	 * @param shipmentNo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getListByNo(String shipmentNo) {
		List list = this.shipmentsdao.getListByNo(shipmentNo);
		return list;
	}
	
	/**
	 * 获取符合条件的trackingNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getListByTr() {
		List list = this.packagesdao.getListByTr();
		return list;
	}
	
	/**
	 * 保存combine数据到数据库
	 * @param s
	 */
	public void addcombine(Shipment s) {
		this.shipmentsdao.addcombine(s);
	}
	
	/**
	 * 获取符合条件的shipmentNo
	 * @param shipmentNo
	 * @return
	 */
	public Shipment getShipmentsByNo(String shipmentNo){
		Shipment s = this.shipmentsdao.getShipmentsByNo(shipmentNo);
		return s;
	}
	
	/**
	 * 根据主shipmentId查询对应的shipments的细节数据(上半部分)
	 * @param shipmentId
	 * @return
	 */
	public Shipment getshipmentDetail(Integer shipmentId) {
		Shipment s = this.shipmentsdao.getshipmentDetail(shipmentId);
		return s;
	}
	
	/**
	 * 根据主shipmentId查询对应的shipments的细节数据(上半部分)
	 * @param shipmentId
	 * @return
	 *//*
	public ShipmentsDTO getshipmentDTODetail(Shipment s) {
		if(s!=null){
			ShipmentsDTO dto = this.dozer.map(s, ShipmentsDTO.class);
			OrderAddress orderAddress = this.getShipTo(orderNo, dto.getshipto);
			return dto;
		}else{
			return new ShipmentsDTO();
		}
	}
	*/
	/**
	 * 根据shipmentId查询相关的ShipmentsLines数据(分页)
	 * @param page
	 * @param shipmentId
	 * @return Page
	 */
	public Page<ShipmentLinesDTO> searchShipmentLine(Page<ShipmentLine> page,Integer shipmentId) {
		Page<ShipmentLinesDTO> retPage = null;
		retPage = this.shipmentlinesdao.searchLine(page, shipmentId);
		return retPage;
	}
	
	/**
	 * 美国仓库:根据一个或多个shipmentId查询(idStr)，返回一个Page对象
	 * @param page
	 * @param idStr
	 * @return Page
	 */
	@SuppressWarnings("unchecked")
	public Page<ShipmentLinesDTO> searchShipmentLine(Page<ShipmentLine> page,String idStr) {
		
		Page<ShipmentLinesDTO> retPage = null;
		retPage = this.shipmentlinesdao.searchShipmentLine(page, idStr);
		for(ShipmentLinesDTO dto : retPage.getResult()){
			Integer qut =0;
			Double size = 0.0d;
			List<ShipPackageLines> shipPackageLines = this.shipPageageLineDao.findBy("shipmentLines.lineId", dto.getLineId());
			for(ShipPackageLines lines : shipPackageLines){
				qut += lines.getQuantity();
				size = Arith.add(size, lines.getSize());
			}
			dto.setSize(size);
			dto.setQuantity(qut);
		}
		return retPage;
	}
	
	/**
	 * 根据条件查询ShipPackageLines数据并返回pageShipPackageLinesDTO对象
	 * @param page
	 * @param shipPackageLinesdto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<ShipPackageLineDTO> searchShipPackageLines(Page<ShipPackageLines> page,ShipPackageLineDTO shipPackageLinesdto )
	{
		Page<ShipPackageLineDTO> retPage = new Page<ShipPackageLineDTO>();
		List<ShipPackageLineDTO> dtoList = new ArrayList<ShipPackageLineDTO>();
		page = this.shipPageageLineDao.searchShipPackageLines(page, shipPackageLinesdto);
		if(page!=null)
		{
			for (ShipPackageLines shipPackageLines : page.getResult()) {
				ShipPackageLineDTO dto =dozer.map(shipPackageLines, ShipPackageLineDTO.class);
				dto.setShipPackages(shipPackageLines.getShipPackages());
				OrderItem item = this.orderitemsdao.getOrderItem(shipPackageLines.getOrderNo(),shipPackageLines.getItemNo());
				dto.setItemName(item.getName());
				PickingLogs log = this.getPickingLogsByPkgLineId(dto.getPkgLineId());
				if(log!=null){
					dto.setLotNo(log.getLotNo());
				}
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}
	
	/*
	 * 根据packagelineId 获取pickingLogs
	 */
	public PickingLogs getPickingLogsByPkgLineId(Integer id){
		List<PickingLogs> logs = this.pickingLogDao.findBy("pkgLineId", id);
		if(logs!=null&&!logs.isEmpty()){
			return logs.get(0);
		}
		return null;
	}
	/**
	 * 根据获取的packageId查询ShipPackageLines数据并返回pageShipPackageLinesDTO对象
	 * @param page
	 * @param filters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<ShipPackageLineDTO> searchShipPackageLines(Page<ShipPackageLines> page,
			List<PropertyFilter> filters) {
		Page<ShipPackageLineDTO> retPage = new Page<ShipPackageLineDTO>();
		List<ShipPackageLineDTO> dtoList = new ArrayList<ShipPackageLineDTO>();
		page = this.shipPageageLineDao.searchShipPackageLines(page, filters);
		if (page.getResult() != null) {
			for (ShipPackageLines shipPackageLines : page.getResult()) {
				ShipPackageLineDTO dto = dozer.map(shipPackageLines, ShipPackageLineDTO.class);
				dto.setShipPackages(shipPackageLines.getShipPackages());
				OrderItem item = this.orderitemsdao.getOrderItem(shipPackageLines.getOrderNo(),shipPackageLines.getItemNo());
				PickingLogs log = this.getPickingLogsByPkgLineId(dto.getPkgLineId());
				if(log!=null){
					dto.setLotNo(log.getLotNo());
				}
				dto.setItemName(item.getName());
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}
	
	/**
	 * 保存ShipPackages数据
	 * @param spdto
	 */
	public void saveShipPackages(ShipPackageDTO spdto)
	{
		this.packagesdao.saveShipPackages(spdto);
	}
	
    /**
     * 保存shipPackagelines数据
     * @param splDto
     * @param pkgLineId
     */
	public void saveShipPackagelines(ShipPackageLineDTO splDto,Integer pkgLineId)
		{
			this.shipPageageLineDao.saveShipPackagelines(splDto,pkgLineId);
			ShipPackageErrors spe=splDto.getShipPackageErrorList().get(0);
			if(spe.getId()== null)
				// 保存ShipPackageErrors对象
				this.shipPackageErrorsDao.save(spe);
			else
				// 更新ShipPackageErrors数据
				this.shipPackageErrorsDao.updteShipPackageErrors(spe);
		}
	
	/**
	 * 根据ids查询ShipPackages
	 * @param ids
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getListByTr(List<Integer> ids) {
		return this.packagesdao.getListByTr(ids);
	}
	
	/**
	 * 根据Id查询shipment信息
	 * @param shipmentId
	 * @return
	 */
	public Shipment getShipmentsById(int shipmentId){
		return this.shipmentsdao.getShipmentsById(shipmentId);
	}

	/**
	 * 根据id返回ShipPackages对象
	 * @param packageId
	 * @return
	 */
	public ShipPackage getShipPackagesByid(int packageId){
		return this.packagesdao.getShipPackageById(packageId);
	}
	
	/**
	 * 取消装运功能:更新ShipPackages表信息
	 * @param packageId
	 * @param reason
	 * @return
	 */
	public boolean changeShipPackagesByPackageId(int packageId,String reason,Integer shipmentId){
		ShipPackage shipPk = this.packagesdao.getById(packageId);
		String trNo = shipPk.getTrackingNo();
		if(this.packagesdao.updateShipPackagesByPackageId(packageId,reason)!=null&&this.shipPageageLineDao.changePackageLinesStatus(packageId)){
			
			
			
			List<ShipPackageLines> linesList = this.shipPageageLineDao.findBy("shipPackages.packageId", packageId);
			if(linesList!=null){
				this.shipPageageLineDao.getSession().evict(linesList);
				//List<Integer> shipmentLineList = new ArrayList<Integer>();
				for(ShipPackageLines packageLine:linesList){
					List<Reservation> reservtionList = this.reservationDao.getReservationList(packageLine.getOrderNo(), packageLine.getItemNo());
					if(reservtionList!=null&&!reservtionList.isEmpty()){
						OrderItem orderItem = this.orderitemsdao.getOrderItem(packageLine.getOrderNo(), packageLine.getItemNo());
						String itemType = orderItem.getType();
						Reservation reservation = reservtionList.get(0);
						if(packageLine.getQuantity()!=null){
							if(reservation.getQty()==null){
								reservation.setQty(0);
							}
							if("PRODUCT".equals(itemType)){
								reservation.setQty(reservation.getQty()+packageLine.getQuantity());
							}
							if("SERVICE".equals(itemType)){
								if(reservation.getSize()!=null&&packageLine.getSize()!=null){
									
									reservation.setSize(Arith.add(reservation.getSize(), packageLine.getSize()));
								}
							}
							
							this.reservationDao.save(reservation);
						}
						
					}else{
						Reservation reservation = new Reservation();
						reservation.setItemNo(packageLine.getItemNo());
						reservation.setOrderNo(packageLine.getOrderNo());
						reservation.setCatalogNo("");
						reservation.setQty(packageLine.getQuantity());
						reservation.setQtyUom(packageLine.getQtyUom());
						reservation.setSize(packageLine.getSize());
						reservation.setSizeUom(packageLine.getSizeUom());
						this.reservationDao.save(reservation);
					}
					packageLine.getShipmentLines().setStatus("Drafted");
					this.shipmentlinesdao.save(packageLine.getShipmentLines());
				}
				this.shipPageageLineDao.flush();
			}
			this.packagesdao.flush();
			if(shipmentId!=null){
				String status = "Drafted";
				List<ShipPackage> packageList = this.packagesdao.findBy("shipments.shipmentId", shipmentId);
				System.out.println(shipmentId);
				if(packageList!=null){
					int i = 0;
					for(ShipPackage shipPackage :packageList  ){
						if(shipPackage.getStatus()!=null&&shipPackage.getStatus().equals("Ready To Ship")){
							//System.out.println(shipPackage.getStatus());
							status = "Partial Ready To Ship";
							System.out.println(status+" = status1");
							i++;
						}
					}
					Shipment shipments = this.shipmentsdao.getById(shipmentId);
					String trNos = shipments.getTrackingNo();
					String upTrNo = "";
					if(trNos!=null&&!trNos.equals("")){
						String[] trNoss = trNos.split(",");
						for(String trno : trNoss){
							//System.out.println(trno+" trno  "+ trNo);
							if(!trno.equals(trNo)){
								//System.out.println(trno+" trno  "+ trNo);
								if(upTrNo.equals("")){
									upTrNo=trno;
								}else{
									upTrNo+=","+trno;
								}
							}
						}
					}
					if(shipments!=null&&(shipments.getStatus().equals("Shipped")||shipments.getStatus().equals("Partial Shipped"))){
						System.out.println(status+" = status2");
						status = shipments.getStatus();
					}
					System.out.println(status+" = status3");
					this.shipmentsdao.getSession().evict(shipments);
					shipments.setStatus(status);
					shipments.setTrackingNo(upTrNo);
					/*if(shipments.getShipDate()!=null){
						shipments.setShipDate(new Date());
					}*/
					//List<ShipmentLine> sline = this.shipmentlinesdao.findBy("shipments.shipmentId", shipments.getShipmentId());
					
					this.shipmentsdao.save(shipments);
				}
			}
			return true;
		}
			
		return false;
	}
	
	/**
	 * 查询ShipPackages对象status并返回List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getspStatus(){
		List list = this.shipmentsdao.getspStatus();
		return list;
	}
	
	/**
	 * 查询ViewPackingSlip数据细节
	 * @param page
	 * @param packageId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<ViewPackingSlipDTO> ViewPackingSlip(Page<ViewPackingSlipDTO> page,String packageId)
	{
		Page<ViewPackingSlipDTO> retPage=new Page<ViewPackingSlipDTO>();
		List<ViewPackingSlipDTO> list=new ArrayList<ViewPackingSlipDTO>();
		page=packagesdao.ViewPackingSlip(page,packageId);
		if(page.getResult()!=null&&page.getResult().size()>0)
		{
			for(ViewPackingSlipDTO view : page.getResult())
			{
				view.setBillTo(packagesdao.selectAddr(view.getBillTo(), "BILL_TO"));
				view.setShipTO(packagesdao.selectAddr(view.getShipTO(), "SHIP_TO"));
				view.setShipVia(packagesdao.selectShipVia(view.getShipVia()));
				ViewPackingSlipDTO retview=this.dozer.map(view, ViewPackingSlipDTO.class);
				list.add(retview);
			}
			page.setResult(null);
			retPage=this.dozer.map(page, Page.class);
			retPage.setResult(list);
		}
		return retPage;
	}
	
	public ViewPackingSlipDTO getViewPackingSlip(String packageId){
		DateFormat   df2   =   new   SimpleDateFormat( "yyyy-MM-dd "); 
		ShipPackage shipPackages = this.packagesdao.getById(Integer.valueOf(packageId));
		ViewPackingSlipDTO dto = new ViewPackingSlipDTO();
		//查询
		if(shipPackages!=null){
				List<ShipPackageLines> lineList  = this.shipPageageLineDao.findBy("shipPackages.packageId",shipPackages.getPackageId() );
				if(lineList!=null&&!lineList.isEmpty()){
					
					Integer orderNo = lineList.get(0).getOrderNo();
					OrderMain order = this.orderDao.getById(Integer.valueOf(orderNo));
					if(order.getBilltoAddrId()!=null){
						OrderAddress address = this.orderAddressDao.getById(order.getBilltoAddrId());
						if(address!=null){
							dto.setBillTo(this.shippingService.getAddressDisplay(address));
						}
					}
					dto.setShipTO(shipPackages.getShiptoAddress());
					if(shipPackages.getShipmentDate()!=null){
						SimpleDateFormat strToDate = new SimpleDateFormat ("yyyy-MM-dd");
						dto.setShippingDate(strToDate.format(shipPackages.getShipmentDate()));
					}
					dto.setCustomerNo(order.getCustNo());
					dto.setOrder(order.getOrderNo());
					if(order.getOrderDate()!=null){
						dto.setOrderDate(df2.format(order.getOrderDate()));
					}
					ShipMethod shipMethod = this.shippingService.getShipVia(null, null, shipPackages.getShipMethod(), null);
					dto.setShipVia(shipMethod.getName());
					if(shipPackages.getShipmentDate()!=null){
						dto.setShippingDate(df2.format(shipPackages.getShipmentDate()));
					}
					//System.out.println(shipPackages.getActualWeight()+":>>>>>>>>>>>>>>>");
					dto.setTotalWeightDouble(shipPackages.getActualWeight());
					dto.setOfPacakge(shipPackages.getPkgBatchSeq()+" / " + shipPackages.getPkgBatchCount());
					Double subtotal = 0.0d;
					Double discount = 0.0d;
					Double tax = 0.0d;
					Double total = 0.0d;
					dto.setSubtotal(subtotal);
					dto.setDiscount(discount);
					dto.setTax(tax);
					if(shipPackages.getCustomerCharge()!=null){
						dto.setShipping(shipPackages.getCustomerCharge().doubleValue());
					}
					if(shipPackages.getHandingFee()!=null){
						dto.setHanding(shipPackages.getHandingFee().doubleValue());
					}
					
					dto.setTotal(total);
					List<ShipPackageLineDTO> lineDTOList = new ArrayList<ShipPackageLineDTO>();
					List<WorkOrderDTO> workOrderDTOList = new ArrayList<WorkOrderDTO>();
					String orderItemLine="1";
					for(ShipPackageLines lines : lineList){
						OrderItem orderItem = this.shippingdao.getOrderItems(lines.getOrderNo(), lines.getItemNo());
						ShipPackageLineDTO lineDTO = new ShipPackageLineDTO();
						if(orderItem!=null){
							if(orderItemLine.equals("1")&&orderItem.getType().equals("SERVICE")){
								dto.setType(orderItem.getClsId()+"");
								orderItemLine="0";
							}
							//dto.setType(orderItem.getClsId()+"");
							lineDTO.setName(orderItem.getName());
							lineDTO.setQtyOrdered(orderItem.getQuantity());
							lineDTO.setQtyShipped(lines.getQuantity());
							lineDTO.setSize(lines.getSize());
							lineDTO.setItemNo(lines.getItemNo());
							lineDTO.setDescription(orderItem.getShortDesc());
							lineDTO.setUnitPrice(orderItem.getUnitPrice());
							lineDTO.setQtyUom(orderItem.getQtyUom());
							lineDTO.setSizeUom(orderItem.getSizeUom());
							lineDTO.setCatalogNO(orderItem.getCatalogNo());
							Double discB = 0.0;
							if(!orderItem.getDiscount().equals(0.0)&&!orderItem.getAmount().equals(0.0)){
								discB = Arith.div(orderItem.getDiscount(), orderItem.getAmount(),2);
							}
							Double discU = Arith.mul(lineDTO.getQtyShipped(), discB);
							lineDTO.setDisc(Arith.mul(discU, lineDTO.getUnitPrice()));
							lineDTO.setExtendedPrice(Arith.mul(lines.getQuantity(), orderItem.getUnitPrice()));
							lineDTOList.add(lineDTO);
							
							if(lineDTO.getUnitPrice()!=null&&lineDTO.getQtyShipped()!=null){
								subtotal=Arith.mul(lineDTO.getUnitPrice(), lineDTO.getQtyShipped());
								dto.setSubtotal(Arith.add(dto.getSubtotal(), subtotal));
							}
							
							/*Integer qut = 0;
							if(lineDTO.getQtyOrdered()!=null&&lineDTO.getQtyShipped()!=null){
								qut = lineDTO.getQtyShipped()/lineDTO.getQtyOrdered();
							}*/
							if(lineDTO.getDisc()!=null){
								//discount = Arith.mul(lineDTO.getDisc(), qut);
								dto.setDiscount(Arith.add(dto.getDiscount(), lineDTO.getDisc()));
							}
							
							if(orderItem.getTax()!=null){
								tax=Arith.mul(orderItem.getTax(), discB);
								dto.setTax(Arith.add(dto.getTax(), tax));
							}
							
							/*if(shipPackages.getCustomerCharge()!=null){
								shippingHanding=shipPackages.getCustomerCharge().doubleValue();
								dto.setShippingHanding(Arith.add(dto.getShippingHanding(), shippingHanding));
							}*/
							
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
					
				
					total = Arith.add(total,dto.getSubtotal());
					total = Arith.sub(total, dto.getDiscount());
					total = Arith.add(total, dto.getTax());
					total = Arith.add(dto.getShipping(), total);
					
					dto.setPo("CC");
					List<PaymentVoucher> poList = this.paymentVoucherDao.findBy("orderNo", dto.getOrder());
					if(poList!=null&&!poList.isEmpty()){
						for(PaymentVoucher pv :poList){
							if(pv.getPaymentType().equals("PO")&&pv.getPoNumber()!=null){
								dto.setPo(pv.getPoNumber().toString());
							}
						}
					}
					dto.setTotal(total);
					dto.setShipPackageLineDTO(lineDTOList);
				}
			}
		return dto;
	}

	/**
	 * 合并装运功能：将已经合并的数据保存至数据库
	 * @param shipmentId
	 * @param idStr
	 * @param reason
	 * @return boolean
	 */
	public boolean saveCombineInfo(String shipmentId,String idStr,String reason){
		boolean flag1=this.shipmentlinesdao.updateShipmentsstatus(shipmentId, idStr,reason);
		boolean flag2=this.shipmentlinesdao.updatePackagestatus(shipmentId, idStr);
		List<ShipmentLine> list=this.shipmentlinesdao.getShipmentLinesList(shipmentId, idStr);
		// 判断list是否为空
		if(list==null||list.size()==0)
			return true;
		try{
			// 循环list
			for (ShipmentLine shipmentLines : list) {
				// 保存shipmentLines对象
				ShipmentLine sl=this.shipmentlinesdao.saveShipmentLines(shipmentLines, this.getShipmentsById(Integer.parseInt(shipmentId)));
				// 根据orderNo和itemNo查询并返回ShipPackageLines对象
				ShipPackageLines spl=this.shipPageageLineDao.getShipPackageLinesByOrderNoAndItemNo(sl.getOrder().getOrderNo(), sl.getItemNo());
				if(spl!=null){
					spl.setShipmentLines(sl);
					this.shipPageageLineDao.save(spl);
				}
			}
		}catch(Exception e){
			return false;
		}
		// 更新shipmentLine的shipmentId和status
		boolean flag3=this.shipmentlinesdao.updateShipmentlines(shipmentId, idStr);
		if(flag1&&flag2&&flag3)
			return true;
		return false;
	}
	
	/**
	 * 修改shipments对象记录
	 * @param shipment
	 * @return
	 */
	public String updateShipments(Shipment shipment,List<InstructionDTO> dtoList){
		try {
			this.attachInstruction(dtoList, shipment);
			return this.shipmentsdao.updateShipments(shipment);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * 保存Order的同时保存 OrderNotes.及其关联的多个document.
	 */
	private String attachInstruction(
			List<InstructionDTO> dtoList ,Shipment shipment) {
		//List<Integer> sendMailIdList = new ArrayList<Integer>();
		//String strDateFormat = "yyyy-MM-dd";
		Date now = new Date();
		//SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		if (dtoList == null || dtoList.isEmpty()) {
			return null;
		}
		for (InstructionDTO dto : dtoList) {
			// OrderInstructionType type = dto.getOrderInstructionType();
			OrderInstructionType type = OrderInstructionType.fromValue(dto
					.getType());
			if (type == null) {
				continue;
			}
			if (dto.getDelDocIdList() != null
					&& !dto.getDelDocIdList().isEmpty()) {
				this.documentDao.delDocumentList(dto.getDelDocIdList());
			}
			if (!(type.equals(OrderInstructionType.CUST_CONFIRM_EMAIL)
					|| type.equals(OrderInstructionType.VENDOR_CONFIRM_EMAIL)
					|| type.equals(OrderInstructionType.ORDER_CHANGE_NOTIFICATION))) {
				
			
				OrderNote note = this.dozer.map(dto, OrderNote.class);
				if (SoapUtil.getIntegerFromSOAP(note.getId()) == null) {
					note.setId(null);
					if (note.getNoteDate() == null) {
						note.setNoteDate(now);
					}
				}
				//note.setOrderNo(order.getOrderNo());
				note.setCreatedBy(shipment.getModifiedBy());
				note.setModifiedBy(shipment.getModifiedBy());
				note.setCreationDate(now);
				note.setModifyDate(now);
				this.orderNoteDao.save(note);
				if (dto.getDocumentList() != null
						&& !dto.getDocumentList().isEmpty()) {
					for (Document doc : dto.getDocumentList()) {
						doc.setCreatedBy(shipment.getModifiedBy());
						doc.setModifiedBy(shipment.getModifiedBy());
						doc.setCreationDate(now);
						doc.setModifyDate(now);
						doc.setRefId(note.getId());
						doc.setRefType(DocumentType.ORDER_INST_NOTE.value());
						this.documentDao.save(doc);
					}
				}
			}

		}
		return "true";
	}
	
	/**
	 * 根据拼接的字符串idStr查询并返回list集合
	 * @param idStr
	 * @return
	 */
	public List<Shipment> getShipmengsByList(String idStr){
		return this.shipmentsdao.getListByIdList(idStr);
	}
	
	/**
	 * 根据拼接的主shipmentId查询并返回list集合
	 * @param idStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getListStatusByIdList(String shipmentId){
		return this.shipmentsdao.getListStatusByIdList(shipmentId);
	}
	
	/**
	 * 根据拼接的主shipmentId查询并返回客户编号
	 * @param idStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer getListCustByIdList(String shipmentId){
		return this.shipmentsdao.getListCustByIdList(shipmentId);
	}
	
	/**
	 * 判断并获取PackageError的yes或no
	 * @param shipmentId
	 * @return
	 */
	public String getErrorByShipmentId(String shipmentId){
		return this.shipmentsdao.getError(shipmentId);
	}

	/**
	 * 根据shipmentId分页查询ShipPackages数据
	 * @param page
	 * @param shipmentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ShipPackageDTO> searchPackage(Page<ShipPackage> page,Integer shipmentId){
		Page<ShipPackageDTO> retPage=null;
		List<ShipPackageDTO> dtoList=new ArrayList<ShipPackageDTO>();
		//page=this.packagesdao.searchPackages(page, shipmentId);
		List<ShipPackage> shipPackage = this.packagesdao.findBy("shipments.shipmentId", shipmentId);
		if(shipPackage!=null){
			for(ShipPackage sp:shipPackage){
				List<ShipPackageLines> line = this.shipPageageLineDao.findBy("shipPackages.packageId", sp.getPackageId());//this.shipmentlinesdao.findBy("shipments", sp);
				if(line!=null&&!line.isEmpty()){
					ShipPackageDTO dto=dozer.map(sp, ShipPackageDTO.class);
					if(sp.getShipMethod()!=null){
						ShipMethod shipMethod = this.shipMethodDao.getById(sp.getShipMethod());
						if(shipMethod!=null){
							dto.setShipVia(shipMethod.getName());
							dto.setCarrier(shipMethod.getCarrier());
						}
						
					}
					if(sp.getShippingClerk()!=null){
						User user = this.userDao.getById(sp.getShippingClerk());
						dto.setShippingClerk(user.getEmployee().getEmployeeName());
					}
					if(sp.getSendBy()!=null){
						User user = this.userDao.getById(sp.getSendBy());
						dto.setSendBy(user.getEmployee().getEmployeeName());
					}
					if(sp.getShipmentDate()!=null){
						SimpleDateFormat strToDate = new SimpleDateFormat ("yyyy-MM-dd");
				        // parse format String to date
						dto.setShipmentDateStr(strToDate.format(sp.getShipmentDate()));
					}
					
					dtoList.add(dto);
				}
				
				
			}
		}
		return dtoList;
	}
	
	/**
	 * 
	 * @param shipmentId
	 * @return
	 */
	public List<OrderMain> getOrderByShipmentId(String shipmentId){
		return this.shipmentsdao.searchOrderMainByShipmentId(shipmentId);
	}

	public ShipPackageLineDao getShipPageageLineDao() {
		return shipPageageLineDao;
	}

	public void setShipPageageLineDao(ShipPackageLineDao shipPageageLineDao) {
		this.shipPageageLineDao = shipPageageLineDao;
	}

	public OrderItemDao getOrderitemsdao() {
		return orderitemsdao;
	}

	public void setOrderitemsdao(OrderItemDao orderitemsdao) {
		this.orderitemsdao = orderitemsdao;
	}
	
	public List<ShipClerkDTO> getAllShipClerk(){
		List<ShipClerk> list = this.shipClerkDao.getAll();
		ShipClerkDTO dto = new ShipClerkDTO();
		List<ShipClerkDTO> dtoList = new ArrayList<ShipClerkDTO>();
		if(list!=null){
			for(ShipClerk shipClerk : list){
				dto.setShipClerk(shipClerk);
				User user = userDao.getById(shipClerk.getClerkId());
				if(user!=null){
					dto.setName(user.getLoginName());
				}
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
	
	private  String setToERPXML(String sentPackageXML,ShipPackage shipPackage){
		if(sentPackageXML.equals("")){
			sentPackageXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Ship>";
			sentPackageXML += "MingContentCanBeReplaced";
			sentPackageXML += "</Ship>"; 
		}
		String sentPackage = "";
		List<ShipPackageLines> shipPackageLineList = this.shipPageageLineDao.findBy("shipPackages.packageId", shipPackage.getPackageId());
		if(shipPackageLineList!=null&&!shipPackageLineList.isEmpty()){
			sentPackage += "<Package><head><trNo>"+shipPackage.getTrackingNo()+"</trNo></head>";
			for(ShipPackageLines shipPackageLine:shipPackageLineList ){
				OrderItem orderItem = this.orderitemsdao.getOrderItem(shipPackageLine.getOrderNo(), shipPackageLine.getItemNo());
				String itemType = orderItem.getType();
				sentPackage +="<line>";
				sentPackage +="<soNo>"+shipPackageLine.getOrderNo()+"</soNo>";
				String po = this.getPOBySO(shipPackageLine.getOrderNo());
				sentPackage +="<po>"+po+"</po>";
				sentPackage +="<itemNo>"+shipPackageLine.getItemNo()+"</itemNo>";
				if(shipPackageLine.getSize()!=null){
					sentPackage +="<size>"+shipPackageLine.getSize()+"</size>";
				}else{
					sentPackage +="<size></size>";
				}
				if("PRODUCT".equals(itemType)){
					if(shipPackageLine.getQuantity()!=null){
						sentPackage +="<qty>"+shipPackageLine.getQuantity()+"</qty>";
					}else{
						sentPackage +="<qty>0</qty>";
					}
				}else if("SERVICE".equals(itemType)){
					if(orderItem.getClsId()==3&&"SC1010".equals(orderItem.getCatalogNo())){
						OrderGeneSynthesis orderGeneSynthesis = this.orderGeneSynthesisDao.getById(orderItem.getOrderItemId());
						if(orderGeneSynthesis!=null){
							sentPackage +="<qty>"+orderGeneSynthesis.getSeqLength()+"</qty>";
						}else{
							sentPackage +="<qty>"+shipPackageLine.getQuantity()+"</qty>";
						}
					}else{
						if(shipPackageLine.getSize()!=null&&orderItem.getSize()!=null&&shipPackageLine.getSize()!=0&&orderItem.getSize()!=0){
							Double sizeS = Arith.div(shipPackageLine.getSize(), Arith.mul(orderItem.getSize(),orderItem.getQuantity()));
							sentPackage +="<qty>"+sizeS+"</qty>";
						}else{
							sentPackage +="<qty>"+shipPackageLine.getQuantity()+"</qty>";
						}
					}
					
				}else{
					sentPackage +="<qty>0</qty>";
				}
				
				List<PickingLogs> pickingLogList = this.pickingLogDao.findBy("pkgLineId", shipPackageLine.getPkgLineId());
				if(pickingLogList!=null&&!pickingLogList.isEmpty()){
					PickingLogs pickingLog = pickingLogList.get(0);
					sentPackage +="<lotNum>"+pickingLog.getLotNo()+"</lotNum>";
					sentPackage +="<location>"+pickingLog.getLocationCode()+"</location>";
				}else{
					sentPackage +="<lotNum></lotNum>";
					sentPackage +="<location></location>";
				}
				
				
				
				OrderMain order = this.orderDao.getById(shipPackageLine.getOrderNo());
				String company = "";
				if(order!=null){
					//Customer customer = this.customerDao.getById(order.getCustNo());
					/*OrderAddress orderAddress = orderAddressDao.getAddrByOrderNoAndType(purchaseOrder.getOrderNo(), "BILL_TO");
					if(orderAddress!=null){
						company = billTerritoryDao.getAccountCode(orderAddress.getCountry(), orderAddress.getState(), orderAddress.getZipCode());
					}*/
					if(order.getGsCoId()==1){
						company = "GSUS";
					}
					if(order.getGsCoId()==2){
						company = "GSNJ";
					}
					if(order.getGsCoId()==3){
						company = "GSPK";
					}
					if(order.getGsCoId()==4){
						company = "GSHK";
					}
				}
				
				sentPackage +="<company>"+company+"</company>";
				sentPackage +="</line>";
			}
			sentPackage += "</Package>";
		}	
		sentPackageXML.replace("MingContentCanBeReplaced", sentPackage+"MingContentCanBeReplaced");
		return sentPackageXML;
	}
	
	/*
	 * 是否能为SH
	 */
	private  String isSHShipment(Shipment shipment){
		Double shipSize = 0.0;
		Double orderSize = 0.0;
		Integer shipQty = 0;
		Integer orderQty = 0;
		String orderNos = "";
		List<OrderItem> orderItemList = this.orderitemsdao.searchOrderItemListByShipmentId(shipment.getShipmentId());
		List<ShipPackageLines> packageLineList = this.shipPageageLineDao.searchShipPackageLinesByShipmentId(shipment.getShipmentId());
		if(orderItemList!=null&&!orderItemList.isEmpty()){
			for(OrderItem oi : orderItemList){
				String shippable = "Y";
				if(oi.getType().equals("PRODUCT")){
					Product product = this.productDao
					.getProductByCatalogNo(oi.getCatalogNo());
					shippable = product.getShippable();
					if(!"N".equalsIgnoreCase(shippable)){
						orderQty += oi.getQuantity(); 
					}
					
				}else{
					com.genscript.gsscm.serv.entity.Service service = this.servDao
					.findUniqueBy("catalogNo", oi.getCatalogNo());
					shippable = service.getShippable();
					if(!"N".equalsIgnoreCase(shippable)){
						if(oi.getSize()==null||oi.getSize()==Double.valueOf(0.0)){
							Arith.add(orderSize,1);
						}else{
							Arith.add(orderSize,Arith.mul(oi.getQuantity(),oi.getSize()));
						}
					}
				}
				if(orderNos .equals("")){
					orderNos += oi.getOrderNo();
				}else{
					orderNos +=","+oi.getOrderNo();
				}
			}
		}
		if(packageLineList!=null&&!packageLineList.isEmpty()){
			for(ShipPackageLines pl : packageLineList){
				OrderItem oi = new OrderItem();
				if(orderItemList!=null&&!orderItemList.isEmpty()){
					for(OrderItem oi1 : orderItemList){
						if(oi1.getOrderNo().equals(pl.getOrderNo())&&oi1.getItemNo().equals(pl.getItemNo())){
							oi = oi1;
							break;
						}
					}
				
					if(oi.getType().equals("PRODUCT")){
						shipQty += pl.getQuantity();
					}else{
						if(pl.getSize()==null||pl.getSize().equals(Double.valueOf(0.0))){
							Arith.add(shipSize,1);
						}else{
							Arith.add(shipSize,pl.getSize());
						}
					}
				}
			}
		}
		System.out.println(shipSize +" "+orderSize+" "+shipQty+"  " + orderQty);
		System.out.println(shipSize.equals(orderSize) && shipQty == orderQty);
		System.out.println("???????????/");
		if(shipSize == orderSize && shipQty == orderQty){
			shipment.setStatus("Shipped");
		}else{
			shipment.setStatus("Partial Shipped");
		}
		
		return orderNos;
	}
	/*
	 * 发送邮件
	 */
	private void sendEmailPickUP(ShipPackage shipPackage ,String basePath){
		List<ShipPackageLineDTO> shipPackageLineDTOList =this.shipPageageLineDao.findShipPickUp(shipPackage.getPackageId());
		if(shipPackageLineDTOList!=null&&!shipPackageLineDTOList.isEmpty()){
			OrderMain order = this.orderDao.getById(shipPackageLineDTOList.get(0).getOrderNo());
			//.List<OrderItem> orderItem = this.orderitemsdao.findBy(propertyName, order.getOrderNo());
			String po = "CC";
			List<PaymentVoucher> poList = this.paymentVoucherDao
			.findBy("orderNo", order.getOrderNo());
			if (poList != null && !poList.isEmpty()) {
				for (PaymentVoucher pv : poList) {
					if (pv.getPaymentType().equals("PO")
							&& pv.getPoNumber() != null) {
						po= pv.getPoNumber().toString();
					}
				}
			}
			//Date date = new Date();
			Customer customer = this.customerDao.getById(order.getCustNo());
			
			try {
				String subject = "Shipping Notice Templates";
				String content = this.mimeMailService.buildShippingPickUpContent(customer.getFirstName(), 
						order.getOrderNo(), shipPackage.getTrackingNo(), po, shipPackageLineDTOList,basePath);
				List<String> fileNames = new ArrayList<String>();
				fileNames.add("estain.jpg");
				fileNames.add("button.jpg");
				fileNames.add("peptide.jpg");
				fileNames.add("gene.jpg");
				fileNames.add("genlogo.png");
				mimeMailService.sendMail(customer.getBusEmail(), subject, content, new ArrayList<String>(),fileNames,"order/");
				ShippingAnnouncement shippingAnnouncement = new ShippingAnnouncement();
				shippingAnnouncement.setCreatTime(order.getCreationDate());
				shippingAnnouncement.setMessage(content);
				shippingAnnouncement.setOrderNo(order.getOrderNo());
				shippingAnnouncement.setSendTime(new Date());
				shippingAnnouncement.setSubject(subject);
				shippingAnnouncement.setShipDate(new Date());
				shippingAnnouncement.setToEmail(customer.getBusEmail());
				shippingAnnouncement.setTrackingId(shipPackage.getTrackingNo());
				shippingAnnouncementDao.save(shippingAnnouncement);
			} catch (Exception ex) {
				WSException exDTO = exceptionUtil.getExceptionDetails(ex);
				exceptionUtil.logException(exDTO, this.getClass(), ex,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF0203", SessionUtil.getUserId());
				ExceptionOut.printException(exDTO);
			}
		}
	}
	
	public void getStatusOfFedex(String locathPath , String rePath,String basePath) throws IOException{
		SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=dateformat1.format(new Date());

		List<Shipment> shipmentList = this.shipmentsdao.searhcShipmentOfPackageReadyToShip();
		List<ShipMethod> ShipmethodList = this.shipMethodService.searchShipMethodAll();
		String sentPackageXML ="";
		String packageSH = "";
		int shipPLine = 0;
		System.out.println("start");
		if(shipmentList!=null&&!shipmentList.isEmpty()){
			for(Shipment shipment : shipmentList){
				System.out.println("shipment="+shipment.getShipmentId());
				if(shipPLine>80){
					sentPackageXML = sentPackageXML.replace("MingContentCanBeReplaced", "");
					//sent xml
					this.readReceiveXmlService.setInfoStringToXML(sentPackageXML, locathPath, rePath);
					shipPLine=0;
					sentPackageXML = "";
				}
				String shipShipmentStatus = "0";
				if(shipment.getShipPackageList()!=null&&!shipment.getShipPackageList().isEmpty()){
					for(ShipPackage shipPackage:shipment.getShipPackageList()){
						System.out.println("shipPackage="+shipPackage.getPackageId());
						if(shipPackage.getTrackingNo()!=null&&shipPackage.getStatus().equals("Ready To Ship")){
							String status = null;
							ShipMethod shipmethod = new ShipMethod();
							for(ShipMethod sm:ShipmethodList){
								if(sm.getMethodId().equals(shipPackage.getShipMethod())){
									shipmethod = sm;
									break;
								}
							}
							if(shipmethod!=null){
								String carrier = shipmethod.getCarrier();
								try {
									status = this.postReq(shipPackage.getTrackingNo(),carrier);
								} catch (Exception ex) {
									WSException exDTO = exceptionUtil.getExceptionDetails(ex);
									exceptionUtil.logException(exDTO, this.getClass(), ex,
											new Exception().getStackTrace()[0].getMethodName(),
											"INTF0203", SessionUtil.getUserId());
									ExceptionOut.printException(exDTO);
								}
							}
							System.out.println("status="+status);
							if(status!=null&&status.equalsIgnoreCase("Delivered")){
								if(packageSH.equals("")){
									packageSH+=shipPackage.getPackageId();
								}else{
									packageSH +=","+shipPackage.getPackageId();
								}
								/*
								 * 生成XML
								 */
								sentPackageXML = this.setToERPXML(sentPackageXML, shipPackage);
								shipPLine++;
								shipShipmentStatus="1";
							}
							if(status!=null&&status.equalsIgnoreCase("Picked up")){
								/*
								 * 发送邮件
								 */
								this.sendEmailPickUP(shipPackage, basePath);
							}
						}
					}
				}
				/*
				 * 保存package to sh status 时间，ShipinfoSentFlag
				 */
				/*this.shipmentsdao.getSession().evict(shipment);
				this.shipPackageDao.getSession().evict(shipment.getShipPackageList());*/
				if(!packageSH.equals("")){
					this.shipPackageDao.SaveShipPackageToSH(packageSH,date);
					this.shipPackageDao.flush();
				}
				
				if(shipShipmentStatus.equals("1")){
					//此处要看shipment 为SH 还是PSH
					String orderNos = this.isSHShipment(shipment);
					//end
					if(shipment.getShipDate()==null){
						shipment.setShipDate(new Date());
					}
					this.shipmentsdao.save(shipment);
					
					if(shipment.getStatus().equals("Shipped")){
						this.shipmentlinesdao.updateShipmentsstatusToShipped(shipment.getShipmentId());
						/*
						 * 将order与order item status to SH
						 * 还有status log
 						 */
						if(!orderNos.equals("")){
							List<OrderMain> orderList = this.orderDao.searchOrder(orderNos);
							
							for(OrderMain order : orderList){
								String prior = order.getStatus();
								/*order.setStatus("SH");
								this.orderDao.save(order);*/
								if(orderNos.equals("")){
									orderNos=order.getOrderNo()+"";
								}else{
									orderNos +=","+order.getOrderNo(); 
								}
								OrderProcessLog log = new OrderProcessLog();
					            log.setOrderNo(order.getOrderNo());
					            //log.setOrderItemId(item.getOrderItemId());
					            log.setCurrentStat(order.getStatus());
					            log.setPriorStat("SH");
					            log.setReason("Fedix return shipped.");
					            //log.setNote(dto.getStatusNote());
					            log.setProcessDate(new Date());
					            User processUser = new User();
					            processUser.setUserId(order.getModifiedBy());
					            log.setProcessedBy(processUser);
					            this.orderProcessLogDao.save(log);
								//this.readReceiveXmlService.setInfoXML(order.getOrderNo(), "Shipped", locathPath, rePath);
								List<OrderItem> orderItemList = this.orderitemsdao.getOrderItemsByOrderNo(order.getOrderNo());
								this.orderitemsdao.getSession().evict(orderItemList);
								if(orderItemList!=null&&!orderItemList.isEmpty()){
									
									for(OrderItem orderItem : orderItemList){
										if(orderItem.getStatus().equals("CC")){
											prior = orderItem.getStatus();
											/*orderItem.setStatus("SH");
											this.orderitemsdao.save(orderItem);*/
											OrderProcessLog logItem = new OrderProcessLog();
											logItem.setOrderNo(order.getOrderNo());
											logItem.setOrderItemId(orderItem.getOrderItemId());
											logItem.setCurrentStat("SH");
											logItem.setReason("Fedix return shipped.");
											logItem.setPriorStat(prior);
								            //log.setNote(dto.getStatusNote());
											logItem.setProcessDate(new Date());
								            logItem.setProcessedBy(processUser);
								            this.orderProcessLogDao.save(logItem);
										}
									}
								}
							}
							this.orderDao.updateOrderStatus("SH", orderNos);
							this.orderitemsdao.updateOrderStatus("SH","CC", orderNos);
						}
					
					}
				}
			}
		}
		if(shipPLine>0){
			sentPackageXML = sentPackageXML.replace("MingContentCanBeReplaced", "");
			//sent xml
			this.readReceiveXmlService.setInfoStringToXML(sentPackageXML, locathPath, rePath);
			shipPLine=0;
			sentPackageXML = "";
		}
	}
	
	/*
	 * fedex get status of package in shipment
	 */
	public void getStatusOfFedex11(String locathPath , String rePath,String basePath) throws IOException{
		
		
		List<Shipment> shipmentList= this.shipmentsdao.getAll();
		this.shipmentsdao.getSession().evict(shipmentList);
		int shipPLine = 0;
		String sentPackage="<?xml version=\"1.0\" encoding=\"utf-8\"?><Ship>";
		String savePackageIdsFlag = "";
		if(shipmentList!=null&&!shipmentList.isEmpty()){
			for(Shipment shipment : shipmentList){
				if(shipment.getStatus()!=null&&(shipment.getStatus().equals("Partial Ready To Ship")||shipment.getStatus().equals("Ready To Ship")||shipment.getStatus().equals("Partial Shipped"))){
					//System.out.println("shipmentId"+shipment.getShipmentId());
					List<ShipPackage> shipPackageList = this.packagesdao.findBy("shipments.shipmentId", shipment.getShipmentId());
					List<ShipPackage> shipPackageLists = new ArrayList<ShipPackage>();
					//System.out.println(">>>>>>>>>>>>>>>start");
					String shipShipmentStatus = "noshipPackage";
					List<ShipmentLine> shipmentLineList = this.shipmentlinesdao.findBy("shipments.shipmentId", shipment.getShipmentId());
					Integer lineQut = 0;
					Integer packageQut = 0;
					if(shipPackageList!=null&&!shipPackageList.isEmpty()&&shipmentLineList!=null&&!shipmentLineList.isEmpty()){
						for(ShipPackage shipPackage : shipPackageList){
							List<ShipPackageLines> shipPackageLineList = this.shipPageageLineDao.findBy("shipPackages.packageId", shipPackage.getPackageId());
							if(shipPackageLineList!=null&&!shipPackageLineList.isEmpty()){
								for(ShipPackageLines shipPackageLine:shipPackageLineList ){
									if(shipPackageLine.getQuantity()!=null){
										packageQut +=shipPackageLine.getQuantity();
									}
								}
								shipPackageLists.add(shipPackage);
							}
						}
						//System.out.println(">>>>>>>>>>>>>>>packageQut="+packageQut);
					}
					if(shipPackageLists!=null&&!shipPackageLists.isEmpty()){
						Integer i = 0;
						for(ShipPackage shipPackage : shipPackageLists){
							if(shipPLine>80&&!savePackageIdsFlag.equals("")){
								sentPackage +="</Ship>";
								//sent xml
								boolean isTrue = this.readReceiveXmlService.setInfoStringToXML(sentPackage, locathPath, rePath);
								if(isTrue){
									this.shipPackageDao.updateShipPackagesByPackageIds(savePackageIdsFlag);
								}
								this.shipPackageDao.flush();
								System.out.println("s1");
								shipPLine=0;
								savePackageIdsFlag = "";
								sentPackage="<?xml version=\"1.0\" encoding=\"utf-8\"?><Ship>";
							}
							if(shipPackage.getTrackingNo()!=null&&shipPackage.getStatus().equals("Ready To Ship")){
								//System.out.println("aaaaaaa"+shipPackage.getTrackingNo()+"  "+shipPackage.getShipments()+"   "+shipPackage.getPackageId());
								ShipMethod shipmethod = this.shipMethodDao.getById(shipPackage.getShipMethod());
								String status = null;
								if(shipmethod!=null){
									String carrier = shipmethod.getCarrier();
									try {
										status = this.postReq(shipPackage.getTrackingNo(),carrier);
									} catch (Exception ex) {
										WSException exDTO = exceptionUtil.getExceptionDetails(ex);
										exceptionUtil.logException(exDTO, this.getClass(), ex,
												new Exception().getStackTrace()[0].getMethodName(),
												"INTF0203", SessionUtil.getUserId());
										ExceptionOut.printException(exDTO);
									}
								}
								 
								if(status!=null&&status.equals("Delivered")){
									shipPackage.setShippedTime(new Date());
									shipPackage.setStatus("Shipped");
									this.packagesdao.save(shipPackage);
									this.packagesdao.flush();
									if(shipPackage.getShipinfoSentFlag()==null||!shipPackage.getShipinfoSentFlag().equals("Y")){
										
										List<ShipPackageLines> shipPackageLineList = this.shipPageageLineDao.findBy("shipPackages.packageId", shipPackage.getPackageId());
										if(shipPackageLineList!=null&&!shipPackageLineList.isEmpty()){
											sentPackage += "<Package><head><trNo>"+shipPackage.getTrackingNo()+"</trNo></head>";
											for(ShipPackageLines shipPackageLine:shipPackageLineList ){
												OrderItem orderItem = this.orderitemsdao.getOrderItem(shipPackageLine.getOrderNo(), shipPackageLine.getItemNo());
												String itemType = orderItem.getType();
												sentPackage +="<line>";
												sentPackage +="<soNo>"+shipPackageLine.getOrderNo()+"</soNo>";
												String po = this.getPOBySO(shipPackageLine.getOrderNo());
												sentPackage +="<po>"+po+"</po>";
												sentPackage +="<itemNo>"+shipPackageLine.getItemNo()+"</itemNo>";
												if(shipPackageLine.getSize()!=null){
													sentPackage +="<size>"+shipPackageLine.getSize()+"</size>";
												}else{
													sentPackage +="<size></size>";
												}
												if("PRODUCT".equals(itemType)){
													if(shipPackageLine.getQuantity()!=null){
														sentPackage +="<qty>"+shipPackageLine.getQuantity()+"</qty>";
													}else{
														sentPackage +="<qty>0</qty>";
													}
												}else if("SERVICE".equals(itemType)){
													if(orderItem.getClsId()==3&&"SC1010".equals(orderItem.getCatalogNo())){
														OrderGeneSynthesis orderGeneSynthesis = this.orderGeneSynthesisDao.getById(orderItem.getOrderItemId());
														if(orderGeneSynthesis!=null){
															sentPackage +="<qty>"+orderGeneSynthesis.getSeqLength()+"</qty>";
														}else{
															sentPackage +="<qty>"+shipPackageLine.getQuantity()+"</qty>";
														}
													}else{
														if(shipPackageLine.getSize()!=null&&orderItem.getSize()!=null&&shipPackageLine.getSize()!=0&&orderItem.getSize()!=0){
															Double sizeS = Arith.div(shipPackageLine.getSize(), Arith.mul(orderItem.getSize(),orderItem.getQuantity()));
															sentPackage +="<qty>"+sizeS+"</qty>";
														}else{
															sentPackage +="<qty>"+shipPackageLine.getQuantity()+"</qty>";
														}
													}
													
												}else{
													sentPackage +="<qty>0</qty>";
												}
												
												List<PickingLogs> pickingLogList = this.pickingLogDao.findBy("pkgLineId", shipPackageLine.getPkgLineId());
												if(pickingLogList!=null&&!pickingLogList.isEmpty()){
													PickingLogs pickingLog = pickingLogList.get(0);
													sentPackage +="<lotNum>"+pickingLog.getLotNo()+"</lotNum>";
													sentPackage +="<location>"+pickingLog.getLocationCode()+"</location>";
												}else{
													sentPackage +="<lotNum></lotNum>";
													sentPackage +="<location></location>";
												}
												
												
												
												OrderMain order = this.orderDao.getById(shipPackageLine.getOrderNo());
												String company = "";
												if(order!=null){
													//Customer customer = this.customerDao.getById(order.getCustNo());
													/*OrderAddress orderAddress = orderAddressDao.getAddrByOrderNoAndType(purchaseOrder.getOrderNo(), "BILL_TO");
													if(orderAddress!=null){
														company = billTerritoryDao.getAccountCode(orderAddress.getCountry(), orderAddress.getState(), orderAddress.getZipCode());
													}*/
													if(order.getGsCoId()==1){
														company = "GSUS";
													}
													if(order.getGsCoId()==2){
														company = "GSNJ";
													}
													if(order.getGsCoId()==3){
														company = "GSPK";
													}
													if(order.getGsCoId()==4){
														company = "GSHK";
													}
												}
												
												sentPackage +="<company>"+company+"</company>";
												sentPackage +="</line>";
											}
											sentPackage += "</Package>";
											System.out.println(sentPackage);
											shipPLine++;
										}
										if(savePackageIdsFlag.equals("")){
											savePackageIdsFlag = shipPackage.getPackageId()+"";
										}else{
											savePackageIdsFlag +=","+shipPackage.getPackageId();
										}
									}
									i++;
									
								/*}else if(status!=null&&status.equals("Picked up")){*/
									OrderMain order = this.orderDao.getById(Integer.valueOf(shipment.getShipmentNo()));
									//.List<OrderItem> orderItem = this.orderitemsdao.findBy(propertyName, order.getOrderNo());
									String po = "CC";
									List<PaymentVoucher> poList = this.paymentVoucherDao
									.findBy("orderNo", order.getOrderNo());
									if (poList != null && !poList.isEmpty()) {
										for (PaymentVoucher pv : poList) {
											if (pv.getPaymentType().equals("PO")
													&& pv.getPoNumber() != null) {
												po= pv.getPoNumber().toString();
											}
										}
									}
									//Date date = new Date();
									Customer customer = this.customerDao.getById(order.getCustNo());
									List<ShipPackageLineDTO> shipPackageLineDTOList =this.shipPageageLineDao.findShipPickUp(shipPackage.getPackageId());
									try {
										String subject = "Shipping Notice Templates";
										String content = this.mimeMailService.buildShippingPickUpContent(customer.getFirstName(), 
												order.getOrderNo(), shipPackage.getTrackingNo(), po, shipPackageLineDTOList,basePath);
										List<String> fileNames = new ArrayList<String>();
										fileNames.add("estain.jpg");
										fileNames.add("button.jpg");
										fileNames.add("peptide.jpg");
										fileNames.add("gene.jpg");
										fileNames.add("genlogo.png");
										mimeMailService.sendMail(customer.getBusEmail(), subject, content, new ArrayList<String>(),fileNames,"order/");
										ShippingAnnouncement shippingAnnouncement = new ShippingAnnouncement();
										shippingAnnouncement.setCreatTime(order.getCreationDate());
										shippingAnnouncement.setMessage(content);
										shippingAnnouncement.setOrderNo(order.getOrderNo());
										shippingAnnouncement.setSendTime(new Date());
										shippingAnnouncement.setSubject(subject);
										shippingAnnouncement.setShipDate(new Date());
										shippingAnnouncement.setToEmail(customer.getBusEmail());
										shippingAnnouncement.setTrackingId(shipPackage.getTrackingNo());
										shippingAnnouncementDao.save(shippingAnnouncement);
									} catch (Exception ex) {
										WSException exDTO = exceptionUtil.getExceptionDetails(ex);
										exceptionUtil.logException(exDTO, this.getClass(), ex,
												new Exception().getStackTrace()[0].getMethodName(),
												"INTF0203", SessionUtil.getUserId());
										ExceptionOut.printException(exDTO);
									}
								}
							}else if(shipPackage.getStatus().equals("Shipped")){
								i++;
							}
						}
						if(!i.equals(0)&&i.equals(shipPackageLists.size())){
							shipShipmentStatus = "Shipped";
						}else{
							shipShipmentStatus = "Partial Shipped";
						}
						if(i.equals(0)){
							shipShipmentStatus = "noshipPackage";
						}
					}
					
						
						
					
						
						
					if(shipmentLineList!=null){
						for(ShipmentLine shipmentLine:shipmentLineList){
							if(shipmentLine!=null&&shipmentLine.getOrder()!=null){
								OrderItem orderItem = this.orderitemsdao.searchOrderItemByOrderNoAndItemNo(shipmentLine.getOrder().getOrderNo(), shipmentLine.getItemNo());
								if (orderItem != null) {
									String shippable = "Y";
									if("PRODUCT".equals(orderItem.getType())){
										Product product = this.productDao
										.getProductByCatalogNo(orderItem
												.getCatalogNo());
										shippable = product.getShippable();
									}else if("SERVICE".equals(orderItem.getType())){
										com.genscript.gsscm.serv.entity.Service service = this.servDao
										.findUniqueBy("catalogNo", orderItem
												.getCatalogNo());
										shippable = service.getShippable();
									}
									if (orderItem.getQuantity() != null && orderItem.getStatus().equals("CC")&&!"N".equalsIgnoreCase(shippable)){
										lineQut += orderItem.getQuantity();
									}

								}
							}
						}
						//System.out.println(">>>>>>>>>>>>>>>lineQut1="+lineQut);
					}
					if(!lineQut.equals(packageQut)||lineQut.equals(0)){
						System.out.println(">>>>>>>>>>>>>>>return");
						continue;
					}
					
					//System.out.println("shipShipmentStatus="+shipShipmentStatus);
					if(shipShipmentStatus.equals("Shipped")||shipShipmentStatus.equals("Partial Shipped")){
						shipment.setStatus(shipShipmentStatus);
						if(shipment.getShipDate()==null){
							shipment.setShipDate(new Date());
						}
						this.shipmentsdao.save(shipment);
						if(shipShipmentStatus.equals("Shipped")){
							List<OrderMain> orderList = new ArrayList<OrderMain>();
							//List<ShipmentLine> shipmentLines = this.shipmentlinesdao.findBy("shipments.shipmentId", shipment.getShipmentId());
							this.shipmentlinesdao.getSession().evict(shipmentLineList);
							if(shipmentLineList!=null&&!shipmentLineList.isEmpty()){
								for(ShipmentLine shipmentLine : shipmentLineList){
									shipmentLine.setStatus(shipShipmentStatus);
									if(orderList!=null&&!orderList.isEmpty()){
										int i = 0;
										for(OrderMain order : orderList){
											if(order.getOrderNo().equals(shipmentLine.getOrder().getOrderNo())){
												i=1;
											}
										}
										if(i==0){
											orderList.add(shipmentLine.getOrder());
										}
									}else{
										orderList.add(shipmentLine.getOrder());
									}
									//this.shipmentlinesdao.save(shipmentLine);
								}
								this.shipmentlinesdao.updateShipmentsstatusToShipped(shipment.getShipmentId());
								if(orderList!=null&&!orderList.isEmpty()){
									String orderNos  = "";
									String orderItemNos  = "";
									for(OrderMain order : orderList){
										String prior = order.getStatus();
										/*order.setStatus("SH");
										this.orderDao.save(order);*/
										if(orderNos.equals("")){
											orderNos=order.getOrderNo()+"";
										}else{
											orderNos +=","+order.getOrderNo(); 
										}
										OrderProcessLog log = new OrderProcessLog();
							            log.setOrderNo(order.getOrderNo());
							            //log.setOrderItemId(item.getOrderItemId());
							            log.setCurrentStat(order.getStatus());
							            log.setPriorStat("SH");
							            log.setReason("Fedix return shipped.");
							            //log.setNote(dto.getStatusNote());
							            log.setProcessDate(new Date());
							            User processUser = new User();
							            processUser.setUserId(order.getModifiedBy());
							            log.setProcessedBy(processUser);
							            this.orderProcessLogDao.save(log);
										//this.readReceiveXmlService.setInfoXML(order.getOrderNo(), "Shipped", locathPath, rePath);
										List<OrderItem> orderItemList = this.orderitemsdao.getOrderItemsByOrderNo(order.getOrderNo());
										this.orderitemsdao.getSession().evict(orderItemList);
										if(orderItemList!=null&&!orderItemList.isEmpty()){
											
											for(OrderItem orderItem : orderItemList){
												if(orderItem.getStatus().equals("CC")){
													prior = orderItem.getStatus();
													/*orderItem.setStatus("SH");
													this.orderitemsdao.save(orderItem);*/
													if(orderItemNos.equals("")){
														orderItemNos=orderItem.getOrderItemId()+"";
													}else{
														orderItemNos +=","+orderItem.getOrderItemId(); 
													}
													OrderProcessLog logItem = new OrderProcessLog();
													logItem.setOrderNo(order.getOrderNo());
													logItem.setOrderItemId(orderItem.getOrderItemId());
													logItem.setCurrentStat("SH");
													logItem.setReason("Fedix return shipped.");
													logItem.setPriorStat(prior);
										            //log.setNote(dto.getStatusNote());
													logItem.setProcessDate(new Date());
										            logItem.setProcessedBy(processUser);
										            this.orderProcessLogDao.save(logItem);
												}
											}
										}
									}
									if(!orderNos.equals("")){
										this.orderDao.updateOrderStatus("SH", orderNos);
									}
									if(!orderItemNos.equals("")){
										orderitemsdao.updateOrderStatus("SH","CC", orderNos);
									}
								}
							}
							/*List<ShipPackage> shipPackages = this.packagesdao.findBy("shipments.shipmentId",shipment.getShipmentId());
							this.packagesdao.getSession().evict(shipPackages);
							if(shipPackages!=null&&!shipPackages.isEmpty()){
								for(ShipPackage shipPackage : shipPackages){
									shipPackage.setStatus(shipShipmentStatus);
									this.packagesdao.save(shipPackage);
									List<ShipPackageLines> shipPackageLines = this.shipPageageLineDao.findBy("shipPackages.packageId",shipPackage.getPackageId() );
									this.shipPageageLineDao.getSession().evict(shipPackageLines);
									if(shipPackageLines!=null&&!shipPackageLines.isEmpty()){
										for(ShipPackageLines shipPackageLine : shipPackageLines){
											shipPackageLine.setStatus(shipShipmentStatus);
											this.shipPageageLineDao.save(shipPackageLine);
										}
									}
								}
							}*/
						}
					}
				}
			}
			if(shipPLine>0&&!savePackageIdsFlag.equals("")){
				sentPackage +="</Ship>";
				//System.out.println("AAAAAAAAAAAAAA"+sentPackage);
				//sent xml
				boolean isTrue = this.readReceiveXmlService.setInfoStringToXML(sentPackage, locathPath, rePath);
				if(isTrue){
					this.shipPackageDao.updateShipPackagesByPackageIds(savePackageIdsFlag);
				}
				this.shipPackageDao.flush();
				shipPLine=0;
				savePackageIdsFlag = "";
				sentPackage="";
			}
			System.out.println("end");
		}
		
	}
	
	public String getReq(String url) throws Exception{
		String resultStr = null;
		StringBuffer readOneLineBuff = new StringBuffer();
		URL getUrl = new URL(url);
		if (url.startsWith("https://")) {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");

			sslContext.init(null, tm, new java.security.SecureRandom());

			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			HostnameVerifier hv = new HostnameVerifier()
			{
			      public boolean verify(String urlHostName, SSLSession session)

			      {
			             return true;
			      }

			};
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
			HttpsURLConnection connection = (HttpsURLConnection) getUrl
					.openConnection();
			connection.setSSLSocketFactory(ssf);
			connection.connect();

			// 取得输入流，并使用Reader读取
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = "";

			while ((line = reader.readLine()) != null) {
				readOneLineBuff.append(line);
			}
			connection.disconnect();        
		} else {
			HttpURLConnection connection = (HttpURLConnection) getUrl   
             .openConnection();
			connection.connect();
			// 取得输入流，并使用Reader读取   
	        BufferedReader reader = new BufferedReader(new InputStreamReader(   
	                connection.getInputStream())); 
	        String line = ""; 
	         
	        while ((line = reader.readLine()) != null) {   
	            readOneLineBuff.append(line);   
	        } 
	        connection.disconnect();
		}
        resultStr = readOneLineBuff.toString();
		return resultStr;
	}
	
	/*
	 * save shipment
	 */
	public void saveShipment(Shipment shipment){
		this.shipmentsdao.save(shipment);
	}
	/*
	 *获取ERP po 根据 中国的SO
	 */
	public String getPOBySO(Integer so){
		OrderErpMapping erpMappin = this.orderErpMappingDao.getById(so);
		if(erpMappin!=null&&erpMappin.getErpUsPo()!=null){
			return erpMappin.getErpUsPo();
		}
		return "";
	}
	public OrderAddress getShipTo (Integer orderNo ,Integer shipToId){
		if(shipToId==null){
			
			if(orderNo !=null){
				OrderMain order = this.orderDao.getById(orderNo);
				if(order!=null){
					shipToId = order.getShiptoAddrId();
				}
				
			}
			
		}
		OrderAddress address = null;
		if(shipToId!=null&&shipToId>1){
			address = this.orderAddressDao.getById(shipToId);
		}
		if(address==null){
			address = new OrderAddress();
		}
		if(address.getFirstName()==null){
			address.setFirstName("");
		}
		if(address.getMidName()==null){
			address.setMidName("");
		}
		if(address.getLastName()==null){
			address.setLastName("");
		}
		return address;
	}
	
	public List<Shipment> searchShipmentListByClick(Integer shipclick){
		return this.shipmentsdao.findBy("shippingClerk", shipclick);
	}
	
	public  String postReq(String trackingNo ,String carrier) throws Exception{
		String resultStr = null;
		StringBuffer readOneLineBuff = new StringBuffer(); 
		String url = null;
		if(carrier.equals("Fedex")){
			url = "http://www.fedex.com/Tracking?cntry_code=us&language=english&tracknumbers="+ trackingNo ;
		}else 
		if(carrier.equals("UPS")){
			url = "http://wwwapps.ups.com/tracking/tracking.cgi?tracknum="+ trackingNo ;
			return "Delivered";
		}else 
		if(carrier.equals("DHL")){
			url = "http://track.dhl-usa.com/TrackByNbr.asp?ShipmentNumber="+ trackingNo ;
			return "Delivered";
		}else 
		if(carrier.equals("USPS")){
			url = "http://www.fedex.com/Tracking?cntry_code=us&language=english&tracknumbers="+ trackingNo ;
			return "Delivered";
		}else{
			url = "http://www.fedex.com/Tracking?cntry_code=us&language=english&tracknumbers="+ trackingNo ;
			return "Delivered";
		}
		System.out.println(url);
		String param = "";
		URL postUrl = new URL(url);   
        // 打开连接   
		if(url.startsWith("https://")) {
			HttpsURLConnection connection = (HttpsURLConnection)postUrl.openConnection(); 
			connection.setDoOutput(true);     
		    connection.setDoInput(true);   
		    connection.setRequestMethod("POST");
		    connection.setUseCaches(false);
		    connection.setInstanceFollowRedirects(true);
		    connection.setRequestProperty("Content-Type","text/html;charset=utf-8"); 
		    connection.connect();  
		    DataOutputStream out = new DataOutputStream(connection   
	                .getOutputStream()); 
		    out.write(param.getBytes());   
	        out.flush();   
	        out.close();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(   
	                connection.getInputStream()));   
	        String line=null;    
	        while ((line = reader.readLine()) != null) {   
	            readOneLineBuff.append(line);   
	        } 
	        connection.disconnect();
		} else {
			HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
			connection.setDoOutput(true);     
		    connection.setDoInput(true);   
		    connection.setRequestMethod("POST");
		    connection.setUseCaches(false);
		    connection.setInstanceFollowRedirects(true);
		    connection.setRequestProperty("Content-Type","text/html"); 
		    connection.connect();  
		    DataOutputStream out = new DataOutputStream(connection   
	                .getOutputStream()); 
		    out.writeBytes(param);   
	        out.flush();   
	        out.close();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(   
	                connection.getInputStream()));   
	        String line=null;  
	        System.out.println("Start");
	        if(reader!=null){
	        	 while ((line = reader.readLine()) != null) {   
	 	            readOneLineBuff.append(line);   
	 	        } 
	        }
	        System.out.println("end");
	        connection.disconnect();
		}    
        resultStr = readOneLineBuff.toString();
        String status = null;
		String[]  str = resultStr.split("\"status\":\"");
		if(str!=null&&str.length>1){
			str = str[1].split("\",");
			if(str!=null&&str.length>1){
				status = str[0];
			}
		}
		Thread.sleep(3000);
		System.out.println("111111"+status);
		return status;
	}
	
	public List<ShipmentLine> getShipmentByOrderNo(List<Integer> orderNoList){
		if(orderNoList!=null&&!orderNoList.isEmpty()){
			return this.shipmentlinesdao.searchShipmentLines(orderNoList);
		}else{
			return null;
		}
		
	}
	
	public void saveCanceledShipPackages(CanceledShipPackages canceledShipPackages){
		this.canceledShipPackageDao.save(canceledShipPackages);
	}
}