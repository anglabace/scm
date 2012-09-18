package com.genscript.gsscm.shipment.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.order.dao.OrderPackageDao;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.entity.OrderPackage;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.quoteorder.service.QuoteOrderService;
import com.genscript.gsscm.shipment.dto.ShipmentsDTO;
import com.genscript.gsscm.shipment.dto.ShipmentsSrchDTO;
import com.genscript.gsscm.shipment.entity.ShipClerk;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.genscript.gsscm.shipment.entity.ShipmentLine;

@Repository
public class ShipmentsDao extends HibernateDao<Shipment,Integer>{
	@Autowired
	private OrderPackageDao orderPackageDao;
	@Autowired
	private QuoteOrderService quoteOrderService;
	/**
	 * 修改shipments对象记录
	 * @param shipment
	 * @return
	 * @throws Exception
	 */
	public String updateShipments(Shipment shipment)throws Exception{
		String flag = "";
			int count = this.getSession().createQuery("update Shipment set currency =:currency,priority =:priority,shippingType =:shippingType," +
					"shippingRule =:shippingRule,description =:description,modifiedBy=:modifiedBy,modifyDate=:modifyDate where shipmentId =:shipmentId")
					.setString("currency", shipment.getCurrency()).setString("priority", shipment.getPriority())
					.setString("shippingType", shipment.getShippingType()).setString("shippingRule", shipment.getShippingRule())
					.setString("description", shipment.getDescription()).setInteger("shipmentId", shipment.getShipmentId())
					.setInteger("modifiedBy", shipment.getModifiedBy()).setDate("modifyDate", shipment.getModifyDate())
					.executeUpdate();
			flag = count + "";
			return flag;
	}
	
	public void updateShipmentListToStatus(String shipmentIds,String status){
		String hql = "update Shipment set status = '"+status+"' where shipmentId in("+shipmentIds+")";
		this.batchExecute(hql);
	}
	
	/**
	 * 根据shipmentId查询并返回ShipmentDTO对象
	 * @param shipmentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ShipmentsDTO getShipmentById(String shipmentId) {
			String hql = "from Shipment where shipmentId =:shipmentId";
			Shipment shipment = (Shipment)this.getSession().createQuery(hql).setString("shipmentId", shipmentId).uniqueResult();
			
			ShipmentsDTO dto = new ShipmentsDTO();
			dto.setShipmentId(shipment.getShipmentId());
			dto.setShipmentNo(shipment.getShipmentNo());
			dto.setPriority(shipment.getPriority());
			dto.setStatus(shipment.getStatus());
			String amt = this.getAmt(shipmentId);
			dto.setShipAmt(amt);
			dto.setShippingRule(shipment.getCurrency());
			/*Customer cus = this.getCustomerName(shipment.getCustNo()+"");
			if(cus.getFirstName()==null){
				cus.setFirstName("");
			}
			if(cus.getLastName()==null){
				cus.setLastName("");
			}
			if(cus!=null)
				dto.setShipTo(cus.getFirstName()+"  "+cus.getLastName());*/
			
			List<ShipmentLine> sllist = this.getShipmentlinesList(shipmentId);
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
			for(Integer no :inList){
				if(orderNo_.equals("")){
					orderNo_ += no+"";
				}else{
					orderNo_ += ","+no+"";
				}
			}
			
				dto.setOrderNo(orderNo_); 
			List<User> listShipClerk = this.getShipPackageListByShipmentId(shipmentId);
			String shippingClerk = "";
			for(int j=0;listShipClerk != null && j<listShipClerk.size();j++){
				User u = listShipClerk.get(j);
				if(!"".equals(u.getLoginName()) && u.getLoginName() != null ){
					shippingClerk += ","+u.getEmployee().getEmployeeName();
				}
			}
			if(shippingClerk.trim().length() > 0)
				dto.setShippingClerk(shippingClerk);
			dto.setDescription(shipment.getDescription());
			dto.setCreationDate(shipment.getCreationDate());
			dto.setModifyDate(shipment.getModifyDate());
			dto.setModifyName(this.getUser(shipment.getModifiedBy()).getLoginName());
			return dto;
	}
	
	/**
	 * 根据userId获取User对象
	 * @param userId
	 * @return
	 */
	public User getUser(Integer userId){
			User user = (User)this.getSession().createQuery("from User where userId =:userId")
				.setInteger("userId", userId).uniqueResult();
			if(user == null ){
				user = new User();
			}
			return user;
	}
	
	/**
	 * 查询Shipment列表
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<Shipment> getshipmentsList(Page<Shipment> page) {
		List<Shipment> shipmentsList = null;
		String hql = "from Shipment";
		Query q = createQuery(hql);
		setPageParameter(q, page);
		shipmentsList = q.list();
		page.setResult(shipmentsList);
		return page;
	}
	
	/**
	 * 根据条件查询Shipment并返回Page对象
	 * @param page
	 * @param shipschdto
	 * @return
	 */
	public Page<Shipment> searchShipments(Page<Shipment> page, ShipmentsSrchDTO shipschdto) {
		if (shipschdto == null) {
			shipschdto = new ShipmentsSrchDTO();
		}
		Map<String, Object> map = new HashMap<String, Object>();		
		String hql = "from Shipment where status = 'Completed' ";
		if (shipschdto.getWareHouse().getWarehouseId()!=null && shipschdto.getWareHouse().getWarehouseId().intValue()>0) {	
			hql += " and wareHouse.warehouseId=:warehouseId";
			map.put("warehouseId", shipschdto.getWareHouse().getWarehouseId());
		}
		if (page.getOrder()!=null && page.getOrder().trim().length()>0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		page = this.findPage(page, hql, map);
		return page;
	}
	
	/**
	 * 查询Shipment并返回Page对象
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Shipment> searchShipments(Page<Shipment> page, List<PropertyFilter> filters) {
		return findPage(page, filters);
	}
	
	/**
	 * 查询shipment对象并返回List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getListByClert() {
		String hql = "from Shipment";
		return this.find(hql);
	}
	
	/**
	 * 查询priority并返回List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getPriority() {
		String hql = "select distinct(priority) from Shipment";
		return this.find(hql);
	}
	
	/**
	 * 查询有操作权限的用户信息并返回List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getLoginName(Integer userId) {
		//String hql = "select distinct(u) from User u,UserRole a,Role r where u.userId=a.user.userId and r.roleId=a.role.roleId";// and r.roleName = 'Shipping'
		String hql = "select user from ShipClerk clerk,User user " +
						" where clerk.clerkFunction in ('Picker/Packer/Shipping', 'All') and user.userId = clerk.clerkId";
		if(userId!=null){
			hql += " and user.userId = "+userId;
		}
		return this.find(hql);
	}
	
	/**
	 * 查询status并返回List
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	public List getStatus() {
		String hql = "select DISTINCT(status) from Shipment";
		return this.find(hql);
	}*/
	
	/**
	 * 查询shippingRule并返回List
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	public List getShippingrule() {
		String hql = "select DISTINCT(shippingRule) from Shipment";
		return this.find(hql);
	}*/
	
	/**
	 * 查询shippingType并返回List
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	public List getShippingtype() {
		String hql = "select DISTINCT(shippingType) from Shipment";
		return this.find(hql);
	}
	*/
	/**
	 * 查询currency并返回List
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	public List getCurrency() {
		String hql = "select DISTINCT(currency) from Shipment";
		return this.find(hql);
	}*/
	
	/**
	 * 查询Warehouse对象并返回List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getListByWarehouse() {
		String hql = "from Warehouse";
		return this.find(hql);
	}
	
	/**
	 * 根据warehouseId查询Warehouse对象信息
	 * @return
	 */
	public Warehouse findById(Integer warehouseId){
		String hql = "from Warehouse where warehouseId = ?";
		return this.findUnique(hql, warehouseId);
	}
	
	/**
	 * 针对美国仓库的查询列表,参数error,用于判断yes/no
	 */ 
	@SuppressWarnings("unchecked")
	public Page<ShipmentsDTO> searchu(Page<Shipment> page, ShipmentsDTO srch,String error,List<ShipMethod> shipMethodList) {
		if (srch == null) {
			srch = new ShipmentsDTO();
		}
		Page<ShipmentsDTO> pageShipmentsDTO = new Page<ShipmentsDTO>();
		pageShipmentsDTO.setPageNo(page.getPageNo());
		pageShipmentsDTO.setPageSize(page.getPageSize());
		
		String hql = "";
		hql = " select s "+
			" from Shipment s " ;
		// 条件人员编码
		if (srch.getShippingClerk()!=null && !srch.getShippingClerk().equals("0")){
			hql += " ,ShippingClerkShipmentAdjustment scsa";
		}
		
		// 条件orderRef
		if (srch.getOrderNo()!= null && srch.getOrderNo().trim().length() > 0) {	
			hql += " where s.shipmentId in(select shipments.shipmentId from ShipmentLine where order.orderNo = "+srch.getOrderNo()+" )  ";
		}else{
			hql += " where 1=1 ";
		}
		if( srch.getPoNo()!=null&&!srch.getPoNo().equals("")){
			hql += " and  s.shipmentId in(select sl.shipments.shipmentId from ShipmentLine sl,OrderErpMapping po where sl.order.orderNo = po.orderNo and po.erpUsPo="+srch.getPoNo()+" )  ";
		}
		// 条件人员编码
		if (srch.getShippingClerk()!=null && !srch.getShippingClerk().equals("0")){
			hql += " and scsa.shipmentId=s.shipmentId and scsa.shippingClerk = " + srch.getShippingClerk();
		}
		// 条件状态
		if (srch.getStatus()!=null && srch.getStatus().trim().length() > 0) {	
			hql += " and status='"+srch.getStatus()+"'";
		}
		// 条件状态
		if (srch.getOrderStatus()!=null && srch.getOrderStatus().trim().length() > 0) {	
			hql += " and s.shipmentId in(select shipments.shipmentId from ShipmentLine where order.status = '"+srch.getOrderStatus()+"' )  ";
		}
		// 条件ship时间
		if (srch.getShipDate() != null && !srch.getShipDate().equals("") ) {	
			hql += " and (shipDate between '"+srch.getShipDate()+" 00:00:00' and '"+srch.getShipDate()+" 23:59:59')";
		}
		//System.out.println(error+"???????????????????");
		if(error != null && error.equals("YES")){
			//hql += " and p.packageId in(select spe.packageId from ShipPackageErrors spe)";
			hql += " and s.shipmentId in (select shipments.shipmentId from ShipmentLine where order.orderNo in (select spe.packageId from ShipPackageErrors spe) and itemNo in (select spe.pkgLineId from ShipPackageErrors spe))";
		}
		/*if(error ==null){
			hql+=" and (1=1 or ())";
		}*/
		if(error!=null&&error.equals("NO")){
			//hql += " and p.packageId not in(select spe.packageId from ShipPackageErrors spe)";
			hql += " and s.shipmentId not in (select shipments.shipmentId from ShipmentLine where order.orderNo in (select spe.packageId from ShipPackageErrors spe) and itemNo in (select spe.pkgLineId from ShipPackageErrors spe))";
		}
		if(srch.getSendBys()!=null&&!srch.getSendBys().equals("0")){
			hql +=" and s.shipmentId in (select shipments.shipmentId from ShipPackage where sendBy ="+srch.getSendBys()+")";
		}
		// 新加一个customerNo条件
		if (srch.getCustNo()!=null && srch.getCustNo().intValue() > 0 && srch.getCustNo().toString().trim().length() > 0) {	
			hql += " and custNo='"+srch.getCustNo()+"'";
		}
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by s." + page.getOrderBy()+" "+ page.getOrder()+", s.priority " + " " + page.getOrder();
		}else
			hql += " and s.status <> 'Completed' ";
		System.out.println(hql+">>>>>>>>>>>>>>>>>>>>>>>>>>.");
		List list = this.getSession().createQuery(hql).list();
		List<ShipmentsDTO> lists = new ArrayList<ShipmentsDTO>();

		int countTemp = 0;
		ShipmentsDTO dto = null;
		List list_new = list;//new ArrayList();
	/*	for(int i=0;i<list.size();i++){
			Object[] obj = (Object[])list.get(i);
			String shipmentId = obj[0]+"";
			if(error != null && error.equals("YES")){
				String packageId = this.getPackageId(shipmentId)+"";
				if(!"0".equals(packageId)){
					String error_ = this.getError(packageId);// 是否有错误包标志
					if("YES".equals(error_)){
						list_new.add(obj);
					}
				}
			}
			else if(error != null && error.equals("NO")){
				String packageId = this.getPackageId(shipmentId)+"";
				if("0".equals(packageId)){
					list_new.add(obj);
				}else{
					String error_ = this.getError(packageId);// 是否有错误包标志
					if("NO".equals(error_)){
						list_new.add(obj);
					}
				}
			}
			else
				list_new.add(obj);
		}*/
		
		for(int i=page.getPageSize()*(page.getPageNo()-1);i<list_new.size();i++){
			String isShipVia = "1";
			Shipment obj = (Shipment)list_new.get(i);
			dto = new ShipmentsDTO();
			if(error!=null){
				dto.setError(error);
			}
			//Integer shipmentId = obj.getShipmentId();
			/*String packageId = this.getPackageId(shipmentId)+"";
			if("0".equals(packageId)){
				dto.setError("NO");
			}else{
				String error_ = this.getError(packageId);                 // 是否有错误包标志
					dto.setError(error_);                                 // 是否为空
			}*/
			/*dto.setError("NO");
			if(error!=null){
				dto.setError(error);
			}*/
			Integer shipmentIds = obj.getShipmentId();
			dto.setShipmentId(shipmentIds);
			dto.setShipmentNo(obj.getShipmentNo());                               // shipmentNo属性
			dto.setPriority(obj.getPriority());                                // priority属性
			dto.setStatus(obj.getStatus());               // status属性
			/*String amt = this.getAmt(shipmentId);                       // Amount属性(累加)
			dto.setShipAmt(amt);*/
			dto.setShippingRule(obj.getShippingRule())  ;      // shippingRule属性
			/*Customer cus = this.getCustomerName(obj[9]+"");
			if(cus.getFirstName()==null){
				cus.setFirstName("");
			}
			if(cus.getLastName()==null){
				cus.setLastName("");
			}
			if(cus!=null)
				dto.setShipTo(cus.getFirstName()+"  "+cus.getLastName());  */// shipTo属性		
//			/List<ShipmentLine> sllist = this.getShipmentlinesList(shipmentId);
			String orderNo_ = "";
			List<Integer> inList = new ArrayList<Integer>();
			for(int j=0;j<obj.getShipmentLineList().size();j++){
				ShipmentLine sl = obj.getShipmentLineList().get(j);
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
			for(Integer no :inList){
				if(orderNo_.equals("")){
					orderNo_ += no+"";
				}else{
					orderNo_ += ","+no+"";
				}
				List<OrderPackage> packageList = this.orderPackageDao.getOrderPackageByList(inList);
				Double shipAmt = quoteOrderService.getShipAmtFromPackage(packageList);
				if(shipAmt!=null){
					dto.setShipAmt(shipAmt.toString());
				}
			}
			
				dto.setOrderNo(orderNo_);                   // orderRef属性(多个逗号分隔显示)
			
			//List<ShipPackage> listShipPackage = this.getShipPackageList(shipmentId);
			/*String trackingNo = "";
			for(int j=0;listShipPackage != null && j<listShipPackage.size();j++){
				ShipPackage pack = listShipPackage.get(j);
				if((!"".equals(pack.getTrackingNo())) && (pack.getTrackingNo() != null) ){
					trackingNo += ","+pack.getTrackingNo();                
				}
			}
			if(trackingNo.trim().length() > 0)
				dto.setTrackingNo(trackingNo.substring(1));    */            // trackingNo属性(多个逗号分隔显示)
			dto.setTrackingNo(obj.getTrackingNo());
			if(obj.getShipDate()!= null){
				dto.setShipDate(obj.getShipDate()+"");                                // shipDate属性
			}
			//List<User> listShipClerk = this.getShipPackageListByShipmentId(shipmentId);
			
				/*dto.setShippingClerkId(Integer.valueOf(obj[8]+""));
				
				User user = this.getUser(dto.getShippingClerkId());*/
				if(obj.getShipClerkList()!=null){
					dto.setShippingClerk("");
					for(ShipClerk shipClerk:obj.getShipClerkList()){
						if(dto.getShippingClerk().equals("")){
							dto.setShippingClerk(shipClerk.getUser().getEmployee().getEmployeeName());
						}else{
							dto.setShippingClerk(dto.getShippingClerk()+" , "+shipClerk.getUser().getEmployee().getEmployeeName());
						}
						
						
						
					}
					
				}
				
				dto.setSendBys("");
				List<ShipPackage> shipPackageList =obj.getShipPackageList();
				List<Integer> sendByList = new ArrayList<Integer>();
				if(shipPackageList!=null&&!shipPackageList.isEmpty()){
					for(ShipPackage shipPackage:shipPackageList){
						if(isShipVia.equals("1")&&shipPackage.getShipMethod()!=null){
							for(ShipMethod shipMethod:shipMethodList){
								if(shipPackage.getShipMethod().equals(shipMethod.getMethodId())){
									isShipVia = "0";
									dto.setShipVia(shipMethod.getName());
								}
							}
							
						}
						if(shipPackage.getSendBy()!=null){
							Integer is = 0;
							for(Integer sendBy : sendByList){
								if(sendBy.equals(shipPackage.getSendBy())){
									is = 1;
								}
							}
							if(is==0){
								User user = this.getUser(shipPackage.getSendBy());
								dto.setSendBys(dto.getSendBys()+" "+user.getEmployee().getEmployeeName());
								sendByList.add(shipPackage.getSendBy());
							}
							
						}
					}
				}
			
			/*String shippingClerk = "";
			for(int j=0;listShipClerk != null && j<listShipClerk.size();j++){
				User u = listShipClerk.get(j);
				if(!"".equals(u.getLoginName()) && u.getLoginName() != null ){
					shippingClerk += "  "+u.getLoginName();
				}
			}
			if(shippingClerk.trim().length() > 0)
				dto.setShippingClerk(shippingClerk);           */// ShippingClerk属性(多个逗号分隔显示)
				
			dto.setWname(obj.getWareHouse().getName());                                        // 仓库name属性
			dto.setCustNo(obj.getCustNo());
			lists.add(dto);
			countTemp++;
			if(countTemp == 10)
				break;
		}
		
		// 设置分页
		pageShipmentsDTO.setResult(lists);
		pageShipmentsDTO.setPageNo(page.getPageNo());
		pageShipmentsDTO.setTotalCount(Long.parseLong(list_new.size()+""));
		pageShipmentsDTO.setPageSize(10);
		return pageShipmentsDTO;
	}
	
	/**
	 * 得到Customer的list
	 * @param shipmentId
	 * @return List
	 */
	private Customer getCustomerName(String cusNo){
		try {
			return this.findUnique("from Customer a where a.custNo =:cusNo ",cusNo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new Customer();
	}
	
	/**
	 * 得到累积的结果shipAmount
	 * @param shipmentId
	 * @return String
	 */
	private String getAmt(String shipmentId){
			List<Double> temp= this.find("SELECT SUM(customerCharge) FROM ShipPackage WHERE shipments.shipmentId = "+shipmentId);
			if(temp.get(0) != null){
				return "0.00";
			}
			return "0.00";
	}
	
	/**
	 * 判断并获取PackageError的yes或no
	 * @param shipmentId
	 * @return String
	 */
	public String getError(String packageId){
		try {
			//
			List<String> temp= this.find("select (case count(*) when 0 then 'NO' else 'YES' end)as error from " +
					" ShipPackageErrors spe " +
					" where spe.packageId ="+packageId );
			return temp.get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}
	
	public List getPackageId(){
		return this.find("select sp.packageId from ShipPackage sp,Shipment s,ShipPackageErrors spe where s.shipmentId=sp.shipmentId and sp.packageId=spe.packageId ");
	}
	
	/**
	 * 根据shipmentId关联到packageId,判断Error状态
	 * @param shipmentId
	 * @return
	 */
	public Integer getPackageId(String shipmentId){
		int packageId = 0;
		try{
			List<Integer> temp= this.find("select packageId from ShipPackage sp where sp.shipments.shipmentId ="+shipmentId);
			if(temp != null && temp.size() > 0)
				packageId = temp.get(0);
			else
				packageId = 0;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return packageId;
	}

	/**
	 * 得到ShipPackage的list
	 * @param shipmentId
	 * @return List
	 */
	private List<ShipPackage> getShipPackageList(String shipmentId){
			return this.find("from ShipPackage s where s.shipments.shipmentId = "+shipmentId);
	}
	
	/**
	 * 得到ShipPackage的trackingNo
	 * @param shipmentId
	 * @return List
	 */
	private List<ShipPackage> getShipPackageList_(String shipmentId){
			return this.find("select s.trackingNo from ShipPackage s where s.shipments.shipmentId = "+shipmentId);
	}
	
	/**
	 * 得到ShipPackage的list
	 * @param shipmentId
	 * @return List
	 */
	private List<User> getShipPackageListByShipmentId(String shipmentId){
		return this.find("select u from User u,ShipPackage s where u.userId=s.shippingClerk and s.shipments.shipmentId = "+shipmentId);
	}
	
	/**
	 * 得到Users的loginName
	 * @param shipmentId
	 * @return List
	 */
	private List<User> getShipPackageListByShipmentId_(String shipmentId){
		return this.find("select u.loginName from User u,ShipPackage s where u.userId=s.shippingClerk and s.shipments.shipmentId = "+shipmentId);
	}
	
	/**
	 * 得到ShipmentLines对象
	 * @param shipmentId
	 * @return List
	 */
	public List<ShipmentLine> getShipmentlinesList(String shipmentId){
		return this.find("from ShipmentLine s where s.shipments.shipmentId = "+shipmentId +" group by s.order.orderNo" );
	}
	
	/**
	 * 得到ShipmentLines的order List
	 * @param shipmentId
	 * @return List
	 */
	private List<ShipmentLine> getShipmentlinesList_(String shipmentId){
		return this.find("select s.order.orderNo from ShipmentLine s where s.shipments.shipmentId = "+shipmentId );
	}
	
	/**
	 * combineShipments功能(只针对美国仓库)
	 */
	@SuppressWarnings("unchecked")
	public List getListByNo(String shipmentNo) {
		String hql = "from Shipment where shipmentNo = ?";
		return this.find(hql,shipmentNo);
	}

	/**
	 * 保存到对象Shipment
	 * 
	 * @param s
	 */
	public void addcombine(Shipment s) {
		this.save(s);
	}
	
	/**
	 * 根据shipmentNo查询,返回Shipment对象
	 * @param shipmentNo
	 * @return
	 */
	public Shipment getShipmentsByNo(String shipmentNo){
		String hql = "from Shipment where shipmentNo = ?";
		Shipment so = null;
		List<Shipment> retList = this.find(hql, shipmentNo);
		if (retList!=null && retList.size()>0) {
			so = retList.get(0);
		}
		return so;
	}
	
	/**
	 * 根据主shipmentId查询对应的shipments的细节数据(上半部分)
	 * @param shipmentId
	 * @return
	 */
	public Shipment getshipmentDetail(Integer shipmentId){
		String hql = "from Shipment where shipmentId = ?";
		Shipment so = null;
		List<Shipment> retList = this.find(hql, shipmentId);
		if (retList!=null && retList.size()>0) {
			so = retList.get(0);
		}
		return so;

	}
	/**
	 * 根据Id查询shipment信息
	 * @param id
	 * @return
	 */
	public Shipment getShipmentsById(Serializable id){
		return this.getById((Integer)id);
	}
	
	/**
	 * 查询ShipPackage对象status并返回List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getspStatus() {
		String hql = "select DISTINCT(status) from ShipPackage ";
		return this.find(hql);
	}
	
	/**
	 * 根据拼接的字符串idStr查询并返回list集合
	 * @return
	 */
	public List<Shipment> getListByIdList(String idStr){
		String hql="from Shipment where shipmentId in("+idStr+")";
		List<Shipment> list=this.find(hql);
		return list;
	}
	
	/**
	 * 根据拼接的字符串shipmentId查询并返回list集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getListStatusByIdList(String shipmentId){
		List<String> temp=this.find("select status from Shipment where shipmentId =" +shipmentId);
		if(temp.get(0) != null){
			return temp.get(0).toString();
		}
		return "Drafted";
	}
	
	/**
	 * 根据拼接的字符串shipmentId查询并返回客户编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer getListCustByIdList(String shipmentId){
		List<Integer> temp=this.find("select custNo from Shipment where shipmentId =" +shipmentId);
		if(temp.get(0) != null){
			return temp.get(0);
		}
		return 0;
	}
	
	/*
	 * 根据shipmentid 获取orderlist
	 */
	public List<OrderMain> searchOrderMainByShipmentId(String shipMentId){
		String hql = "select DISTINCT o from OrderMain o,ShipmentLine sl where o.orderNo = sl.order.orderNo and sl.shipments.shipmentId in (" +shipMentId+")";
		return this.find(hql);
	}
	
	public List<Shipment> searchShipment(){
		String hql = "FROM Shipment where status in ('Partial Ready To Ship','Ready To Ship','Partial Shipped')";
		return this.find(hql);
	}
	
	public List<Shipment> searhcShipmentOfPackageReadyToShip(){
		String hql = "select DISTINCT sp.shipments from ShipPackage sp where sp.status='Ready To Ship'";
		return this.find(hql);
	}
	
	/*public List<Shipment> searchShipment(List<Integer> orderNoList){
		String hql = "select s from ShipmentLine sl,Shipment s where s.shipmentId=sl.shipments.shipmentId and  sl.order.orderNo in";
		if(orderNoList!=null&&!orderNoList.isEmpty()){
			String orderNos = "";
			for(Integer orderNo:orderNoList){
				if(orderNos.equals("")){
					orderNos=orderNo.toString();
				}else{
					orderNos+=","+orderNo;
				}
			}
			hql += "("+orderNos+")";
			return this.find(hql,orderNos);
		}else{
			return null;
		}
		
	}*/
	
	public void flush(){
		Session session = this.getSession();
		session.flush();
		session.clear();
	}
}