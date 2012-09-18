package com.genscript.gsscm.shipment.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Priority;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.util.Arith;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.inventory.entity.Reservation;
import com.genscript.gsscm.inventory.entity.StockDetail;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.entity.OrderPackage;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductShipCondition;
import com.genscript.gsscm.serv.entity.Service;
import com.genscript.gsscm.serv.entity.ServiceShipCondition;
import com.genscript.gsscm.shipment.dto.ShipPackageLineDTO;
import com.genscript.gsscm.shipment.dto.ShipmentsDTO;
import com.genscript.gsscm.shipment.dto.ShippingPackageLinesDTO;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipPackageLines;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.genscript.gsscm.shipment.entity.ShipmentLine;


@Repository
public class ShippingDao extends HibernateDao<Shipment,Integer>{	
	/**
	 * 得到Order列表(以status分组显示)
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getListByClert() {
		String hql = "from OrderMain o group by o.status";
		return this.find(hql);
	}
	
	/**
	 * 列表显示符合条件的人员
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getLoginName(Integer userId) {
			//String hql = "select distinct(u) from com.genscript.gsscm.privilege.entity.User u,Shipment s,ShipPackage p where s.shipmentId=p.shipments.shipmentId and p.shippingClerk=u.userId ";
			String hql = "select user from ShipClerk clerk,User user " +
			"where clerk.clerkFunction in ('Picker/Packer/Shipping', 'All') and user.userId = clerk.clerkId";
			if(userId!=null){
				hql += " and user.userId = "+userId;
			}
			return this.find(hql);
	}
	
	/**
	 * 获取仓库的List
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getListByWareHouse() {
			String hql = "from Warehouse";
			return this.getSession().createQuery(hql).list();
	}
	/*
	 * 
	 */
	public Page<ShipmentsDTO> getShippingList(Page<Shipment> page,ShipmentsDTO srch ,Integer userId){
		if (srch == null) {
			srch = new ShipmentsDTO();
		}
		Page<ShipmentsDTO> pageShipmentDTO = new Page<ShipmentsDTO>();
		pageShipmentDTO.setPageNo(page.getPageNo());
		pageShipmentDTO.setPageSize(page.getPageSize());
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "select o.orderNo,o.status,o.orderType,o.priority,o.shiptoAddrId,s.shipmentNo,s.wareHouse.type,s.wareHouse.name,s.shipmentId  from OrderMain o,Shipment s";
		/*if(srch.getShippingClerk()!=null && Integer.parseInt(srch.getShippingClerk())>0)
		{
			hql=hql+",ShipClerkAssigned a,ShipClerk c,ShipPackage p ";
		}*/
		hql =hql+ " where o.orderNo = s.shipmentNo"
		+" and o.status='cc'"
		+" and ("
		+"exists (select 1 from Reservation r where r.orderNo = o.orderNo )"
		+" or "
		+"exists (select 1 from ShipPackageLines spl where spl.orderNo =o.orderNo and  spl.status in ('Drafted', 'Picked', 'Packed'))"
		+")";
		if(userId!=null){
			hql+=" and s.shippingClerk = "+userId; 
		}
//	 	shippingClerk
		if (srch.getShippingClerk()!=null && !srch.getShippingClerk().equals("0"))
			hql += " and p.shippingClerk = "+srch.getShippingClerk();
//			hql += "and p.shipments.shipmentId=s.shipmentId and p.shippingClerk = "+srch.getShippingClerk();
			/*hql += " and a.shippingClerk = c.clerkId and c.clerkFunction in ('Picker', 'Packer', 'Picker/Packer', 'All') and a.shippingClerk = p.shippingClerk"
					+" and i.clsId = a.clsId and i.type = a.itemType and p.shippingClerk = " + srch.getShippingClerk();*/
		// status
		if (srch.getStatus()!=null && srch.getStatus().trim().length() > 0)
			hql += " and o.status = '"+srch.getStatus().trim()+"'";
		// priority
		if(srch.getPriority() != null && srch.getPriority().trim().length() > 0)
			hql += " and o.priority = '"+srch.getPriority().trim()+"'";
		// orderType
		if(srch.getOrderType() != null && srch.getOrderType().trim().length() > 0)
			hql += " and o.orderType = '"+srch.getOrderType().trim()+"'";
		// orderNo
		if(srch.getOrderNo() != null && srch.getOrderNo().trim().length() > 0)
			hql += " and o.orderNo = '"+srch.getOrderNo().trim()+"'";
		//if (page.getOrder() != null && page.getOrder().trim().length() > 0)
			hql += " order by o.priority desc";
		page = this.findPage(page, hql, map);
		
		List list = page.getResult();
		List<ShipmentsDTO> lists = new ArrayList<ShipmentsDTO>();
		ShipmentsDTO dto = null;
		for(int i=0;i<list.size();i++){
			Object[] obj = (Object[])list.get(i);
			dto = new ShipmentsDTO();
			
			dto.setOrderNo(obj[0]+"");
			dto.setStatus(obj[1]+"");
			dto.setOrderType(obj[2]+"");
			dto.setPriority(obj[3]+"");
		/*	String addressId = obj[4]+"";
			List listAddress = this.getAddressList(addressId);
			String addressName = "";*/
			/*for(int k=0;k<listAddress.size();k++){
				OrderAddress address = (OrderAddress)listAddress.get(k);
				addressName += address.getFirstName()+" "+address.getLastName()+","+address.getAddrLine1()+","
								+address.getCity()+","+address.getState()+" "+address.getZipCode()+","+address.getCountry();
			}
			if(addressName.length() > 1)
				dto.setShipTo(addressName.substring(0,addressName.length()-1));*/
			dto.setShipmentId(Integer.parseInt(obj[8]+""));
			dto.setShipmentNo(obj[5]+"");
			dto.setWarehouseName(obj[7]+"");
			/*String methodId = obj[7]+"";
			List listMethod = this.getShipMethod(methodId);
			String methodName = "";
			
			for(int j=0;j<listMethod.size();j++){
				ShipMethod method = (ShipMethod)listMethod.get(j);
				methodName += method.getName()+",";
			}
			if(methodName.length() > 1)
				dto.setShipVia(methodName.substring(0,methodName.length()-1));
			dto.setShipmentId(Integer.parseInt(obj[9]+""));*/
			lists.add(dto);
		}
		List list2 = this.getSession().createQuery(hql).list();
		Long count2 = 0L;
		if(list2!=null){
			count2 = Long.parseLong(list2.size()+"");
		}
		pageShipmentDTO.setTotalCount(count2);
		pageShipmentDTO.setResult(lists);
		
		return pageShipmentDTO;
	}

	/**
	 * 得到美国仓库的list
	 * @param  page
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public Page<ShipmentsDTO> getShipmentList(Page<Shipment> page, ShipmentsDTO srch) {
		if (srch == null) {
			srch = new ShipmentsDTO();
		}
		Page<ShipmentsDTO> pageShipmentDTO = new Page<ShipmentsDTO>();
		pageShipmentDTO.setPageNo(page.getPageNo());
		pageShipmentDTO.setPageSize(page.getPageSize());
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "select distinct o.orderNo,o.status,o.orderType,o.priority,o.shiptoAddrId,s.shipmentNo,s.wareHouse.type,i.shipMethod,s.wareHouse.name,s.shipmentId ,s.shippingRule,s.receiveTime" +
				" from OrderMain o,OrderItem i,ShipmentLine l,Shipment s";
		if (srch.getShippingClerk()!=null && !srch.getShippingClerk().equals("0"))
		{
			hql +=",ShippingClerkShipmentAdjustment scsa";
		}
		/*if(srch.getShippingClerk()!=null && Integer.parseInt(srch.getShippingClerk())>0)
		{
			hql=hql+",ShipClerkAssigned a,ShipClerk c,ShipPackage p ";
		}*/
		if(srch.getPoNo()!=null&&!srch.getPoNo().equals("")){
			hql += " ,OrderErpMapping po ";
		}
		hql =hql+ " where o.orderNo = i.orderNo"
		+" and s.status<>'Invalid' "
		+" and o.status='cc'"
		+" and i.orderNo = l.order.orderNo"
		+" and i.itemNo = l.itemNo"
		//+" and po.srcSoNo = o.orderNo"
		+" and s.shipmentId = l.shipments.shipmentId " 
		+" and s.status not in ('Shipped','Ready To Ship')"
		/*+" and (s.shippingRule = 'Order Completed') "*/
		//+" and exists (select 1 from ShipPackage sp where sp.shipments.shipmentId = s.shipmentId and sp.status in ('Drafted', 'Picked', 'Packed'))"
		+" and o.warehouseId=1 "
		+" and ("
		+"exists (select 1 from Reservation r where r.orderNo = o.orderNo )"
		+" or "
		+"exists (select 1 from ShipPackageLines spl where spl.orderNo =o.orderNo and  spl.status in ('Drafted', 'Picked', 'Packed'))"
		+")";
//		hql += " and (s.shippingRule!='Order Completed' or (s.shippingRule='Order Completed' and ((not exists (select 1 from PurchaseOrder pco1 where pco1.srcSoNo = o.orderNo)) or (exists(select 1 from PurchaseOrder pco2 where  pco2.srcSoNo =o.orderNo and pco2.receivingFlag=1 )))))";
		if(srch.getPoNo()!=null&&!srch.getPoNo().equals("")){
			hql += " and  po.erpUsPo =  "+srch.getPoNo()+" and po.orderNo=o.orderNo";
		}
		
		if(srch.getShippingRule()!=null&&!srch.getShippingRule().equals("")){
			hql += " and s.shippingRule = '"+srch.getShippingRule()+"'";
		}
		// 	shippingClerk
		if (srch.getShippingClerk()!=null && !srch.getShippingClerk().equals("0"))
			hql += " and scsa.shipmentId= s.shipmentId and scsa.shippingClerk = "+srch.getShippingClerk();
		//hql += "and p.shipments.shipmentId=s.shipmentId and p.shippingClerk = "+srch.getShippingClerk();
			/*hql += " and a.shippingClerk = c.clerkId and c.clerkFunction in ('Picker', 'Packer', 'Picker/Packer', 'All') and a.shippingClerk = p.shippingClerk"
					+" and i.clsId = a.clsId and i.type = a.itemType and p.shippingClerk = " + srch.getShippingClerk();*/		
		// status
		if (srch.getStatus()!=null && srch.getStatus().trim().length() > 0)
			hql += " and o.status = '"+srch.getStatus().trim()+"'";
		// priority
		if(srch.getPriority() != null && srch.getPriority().trim().length() > 0)
			hql += " and o.priority = '"+srch.getPriority().trim()+"'";
		// orderType
		String[] orderType1 = null;
		if(srch.getOrderType() != null && srch.getOrderType().trim().length() > 0){
			orderType1 = srch.getOrderType().split(":");
			hql += " and (SELECT oi.clsId FROM OrderItem oi WHERE o.orderNo = oi.orderNo and oi.itemNo = 1 ) = "+orderType1[2];
			hql += " and (SELECT oi.type FROM OrderItem oi WHERE o.orderNo = oi.orderNo and oi.itemNo = 1 ) = '"+orderType1[3]+"'";
		}
			//hql +=  " and o.orderType = '"+srch.getOrderType().trim()+"'";
		// orderNo
		if(srch.getOrderNo() != null && srch.getOrderNo().trim().length() > 0)
			hql += " and o.orderNo = '"+srch.getOrderNo().trim()+"'";
		//if (page.getOrder() != null && page.getOrder().trim().length() > 0)
			hql += " order by l.shipments.modifyDate desc";
		page = this.findPage(page, hql, map);
		
		List list = page.getResult();
		List<ShipmentsDTO> lists = new ArrayList<ShipmentsDTO>();
		
		ShipmentsDTO dto = null;
		for(int i=0;i<list.size();i++){
			Object[] obj = (Object[])list.get(i);
			String isAdd = "y";
			/*if(lists!=null&&!lists.isEmpty()){
				for(ShipmentsDTO dts:lists){
					if(dts.getOrderNo().equals(obj[0]+"")){
						isAdd="y";
					}
				}
			}*/
			if(isAdd.equals("y")){
				
				dto = new ShipmentsDTO();
				//dto.setItemNo(obj[10]+"");
				dto.setOrderNo(obj[0]+"");
				dto.setStatus(obj[1]+"");
				dto.setOrderType(obj[2]+"");
				dto.setPriority(obj[3]+"");
				String addressId = obj[4]+"";
			/*	List listAddress = this.getAddressList(addressId);
				String addressName = "";*/
				/*for(int k=0;k<listAddress.size();k++){
					OrderAddress address = (OrderAddress)listAddress.get(k);
					addressName += address.getFirstName()+" "+address.getLastName()+","+address.getAddrLine1()+","
									+address.getCity()+","+address.getState()+" "+address.getZipCode()+","+address.getCountry();
				}
				if(addressName.length() > 1)
					dto.setShipTo(addressName.substring(0,addressName.length()-1));*/
				
				dto.setShipmentNo(obj[5]+"");
				dto.setWarehouseName(obj[8]+"");
				String methodId = obj[7]+"";
				List listMethod = this.getShipMethod(methodId);
				String methodName = "";
				
				for(int j=0;j<listMethod.size();j++){
					ShipMethod method = (ShipMethod)listMethod.get(j);
					methodName += method.getName()+",";
				}
				if(methodName.length() > 1)
					dto.setShipVia(methodName.substring(0,methodName.length()-1));
				dto.setShipmentId(Integer.parseInt(obj[9]+""));
				//dto.setPoNo(obj[10]+"");
				dto.setShippingRule(obj[10]+"");
				dto.setReceiveTime(obj[11]==null?"":(obj[11]+"").substring(0, (obj[11]+"").length()-2));
				lists.add(dto);
			}
			
		}
		List list2 = this.getSession().createQuery(hql).list();
		Long count2 = 0L;
		if(list2!=null){
			count2 = Long.parseLong(list2.size()+"");
		}
		pageShipmentDTO.setTotalCount(count2);
		pageShipmentDTO.setResult(lists);
		
		return pageShipmentDTO;
	}
	
	/**
	 * 得到Order的list
	 * @param 
	 * @return List
	 */
	public List<OrderMain> getTypeList(){
			return this.find("from OrderMain o group by o.orderType");
	}
	
	/**
	 * 得到Priority的list
	 * @param 
	 * @return List
	 */
	public List<Priority> getPriorityList(){
			return this.find("from OrderMain o group by o.priority");
	}
	/**
	 * 得到ShipMethod的list
	 * @param methodId
	 * @return List
	 */
	public List<ShipMethod> getShipMethod(String methodId){
			return this.find("from ShipMethod s where s.methodId = "+methodId);
	}
	
	/**
	 * 得到OrderAddresses的list
	 * @param addressId
	 * @return List
	 */
	public List<OrderAddress> getAddressList(String addressId){
			return this.find("from OrderAddress a where a.addrId = "+addressId);
	}
	
/*	
	 * 查询orderItem列表
	 
	public List<ShipmentsDTO> getOrderItemList(Integer shipmentId) {
		String hql = "select distinct(reservation.id),reservation,item from OrderItem item,Reservation reservation "
			+ "where reservation.orderNo = item.orderNo and reservation.itemNo = item.itemNo and item.orderNo " +
			  "in (select sl.order.orderNo from ShipmentLine sl where sl.shipments.shipmentId = "+shipmentId+") " +
			  "and item.status= 'CC' order by reservation.orderNo,reservation.itemNo";
	List<Object[]> list = this.getSession().createQuery(hql).list();
	List<ShipmentsDTO> listOrderItem = new ArrayList<ShipmentsDTO>();
	ShipmentsDTO dto = null;
	for (int i = 0; i < list.size(); i++) {
		Object[] obj = (Object[]) list.get(i);
		OrderItem item = (OrderItem) obj[2];
		Reservation reservation = (Reservation) obj[1];
		dto = new ShipmentsDTO();
		dto.setOrderNo(item.getOrderNo() + "");
		dto.setItemNo(item.getItemNo() + "");
		dto.setCatalogNo(item.getCatalogNo());
		dto.setItemName(item.getName().toString().substring(0,10));
//		dto.setItemName(item.getName());
		dto.setItemStatus(item.getStatus());
		dto.setReservationId(reservation.getId() + "");
		dto.setOrderItemQty(item.getQuantity() + "");
		dto.setQtyUom(item.getQtyUom());
		dto.setSizeUom(item.getSizeUom());
		int itemQty = item.getQuantity();
		List<ShipPackageLines> listLine = null;
		ShipPackageLines line = null;
		int sumQuantity = reservation.getQty();
		int tempQuantity = 0;
		listLine = this.getPackageLineList(String.valueOf(item.getOrderNo()),String.valueOf(item.getItemNo()), true);
		for (int j = 0; j < listLine.size(); j++) {
			line = listLine.get(j);
			tempQuantity += line.getQuantity();
		}
		dto.setQuantity((sumQuantity - tempQuantity) + "");
		dto.setSize(item.getSize() + "");
		if (itemQty != 1) {
			int sumQuantity = reservation.getQty();
			int tempQuantity = 0;
			listLine = this.getPackageLineList(reservation.getId(), flag);
			for (int j = 0; j < listLine.size(); j++) {
				line = listLine.get(j);
				tempQuantity += line.getQuantity();
			}
			dto.setQuantity((sumQuantity - tempQuantity) + "");
			dto.setSize(item.getSize() + "");
		}
		if (itemQty == 1) {
			Double sumSize = 0.0;
			if(reservation.getSize()!=null){
				sumSize = reservation.getSize();
			}else{
				sumSize = 0.0;
			}
			Double tempSize = 0d;
			listLine = this.getPackageLineList(reservation.getId(), flag);
			for (int j = 0; j < listLine.size(); j++) {
				line = listLine.get(j);
				tempSize += line.getSize();
			}
			dto.setQuantity(item.getQuantity() + "");
			dto.setSize((sumSize - tempSize) + "");
		}
		dto.setSort(i);
		if(Integer.parseInt(dto.getQuantity()) > 0 && Double.parseDouble(dto.getSize()) > 0){
			if(!listOrderItem.isEmpty()){
				String is = "0";
				for(ShipmentsDTO orderItem:listOrderItem){
					if(orderItem.getOrderNo().equals(dto.getOrderNo())&&orderItem.getItemNo().equals(dto.getItemNo())){
						orderItem.setQuantity((Integer.valueOf(dto.getQuantity())+Integer.valueOf(orderItem.getQuantity()))+"");
						is = "1";
					}
				}
				if(is.equals("0")){
					listOrderItem.add(dto);
				}
			}else{
				listOrderItem.add(dto);
			}
		}
			
	}
	return listOrderItem;
	}*/
	/**
	 * 查询orderItem列表
	 * 
	 * @param chks
	 * @param flag
	 * @return
	 */
	@SuppressWarnings("unchecked")
public List<ShipmentsDTO> getOrderItemList(String chks, Boolean flag) {
		String hql = "select distinct(reservation.id),reservation,item from OrderItem item,Reservation reservation "
				+ "where reservation.orderNo = item.orderNo and reservation.itemNo = item.itemNo and item.orderNo in ("
				+ chks + ") and item.status= 'CC' order by reservation.orderNo,reservation.itemNo";
		List<Object[]> list = this.getSession().createQuery(hql).list();
		List<ShipmentsDTO> listOrderItem = new ArrayList<ShipmentsDTO>();
		ShipmentsDTO dto = null;
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			OrderItem item = (OrderItem) obj[2];
			Reservation reservation = (Reservation) obj[1];
			dto = new ShipmentsDTO();
			dto.setOrderNo(item.getOrderNo() + "");
			dto.setItemNo(item.getItemNo() + "");
			dto.setCatalogNo(item.getCatalogNo());
			if(item.getName().length()>10){
				dto.setItemName(item.getName().substring(0,10));
			}else{
				dto.setItemName(item.getName());
			}
			
//			dto.setItemName(item.getName());
			dto.setItemStatus(item.getStatus());
			dto.setReservationId(reservation.getId() + "");
			dto.setOrderItemQty(item.getQuantity() + "");
			dto.setQuantity(reservation.getQty()+"");
			dto.setQtyUom(item.getQtyUom());
			dto.setSizeUom(item.getSizeUom());
			dto.setOrderItemType(item.getType());
			dto.setType(item.getType());
			//int itemQty = item.getQuantity();


			/*if (itemQty != 1) {
				int sumQuantity = reservation.getQty();
				int tempQuantity = 0;
				listLine = this.getPackageLineList(reservation.getId(), flag);
				for (int j = 0; j < listLine.size(); j++) {
					line = listLine.get(j);
					tempQuantity += line.getQuantity();
				}
				dto.setQuantity((sumQuantity - tempQuantity) + "");
				dto.setSize(item.getSize() + "");
			}
			if (itemQty == 1) {
				Double sumSize = 0.0;
				if(reservation.getSize()!=null){
					sumSize = reservation.getSize();
				}else{
					sumSize = 0.0;
				}
				Double tempSize = 0d;
				listLine = this.getPackageLineList(reservation.getId(), flag);
				for (int j = 0; j < listLine.size(); j++) {
					line = listLine.get(j);
					tempSize += line.getSize();
				}
				dto.setQuantity(item.getQuantity() + "");
				dto.setSize((sumSize - tempSize) + "");
			}*/
			dto.setSort(i);
            dto.setSize(reservation.getSize()!=null?reservation.getSize()+"":"0");
            //把所有orderNo和itemNo相同的加起来算quality
            if (!listOrderItem.isEmpty()) {
                String is = "0";
                for (ShipmentsDTO orderItem : listOrderItem) {
                    if (orderItem.getOrderNo().equals(dto.getOrderNo()) && orderItem.getItemNo().equals(dto.getItemNo())) {
                        orderItem.setQuantity((Integer.valueOf(dto.getQuantity()) + Integer.valueOf(orderItem.getQuantity())) + "");
                        is = "1";
                    }
                }
                if (is.equals("0")) {
                    listOrderItem.add(dto);
                }
            } else {
                listOrderItem.add(dto);
            }
		}
        List<ShipPackageLines> listLine = null;
	    ShipPackageLines line = null;
        for (int li = 0; li < listOrderItem.size();li++) {
            ShipmentsDTO shipmentsDTO  = listOrderItem.get(li);
            int sumQuantity = Integer.parseInt(shipmentsDTO.getQuantity());
            Double sumSize = Double.valueOf(shipmentsDTO.getSize());
            int tempQuantity = 0;
            Double tempSize = 0.0;
            //求得所有打包的item数量
            listLine = this.getPackageLineList(shipmentsDTO.getOrderNo(),shipmentsDTO.getItemNo(),  flag);
            for (int j = 0; j < listLine.size(); j++) {
                line = listLine.get(j);
                tempQuantity += line.getQuantity();
                tempSize = Arith.add(line.getSize(), tempSize);
            }
            
            //如果quality 的数量小于等于0，则不在左边显示,并判断item type 是PRODUCT，SERVICE。如果是SERVICE 则判断SIZE，如果是product 则判断qty
            if("PRODUCT".equals(shipmentsDTO.getType())){
            	 shipmentsDTO.setQuantity((sumQuantity - tempQuantity) + "");
            	 if(Integer.parseInt(shipmentsDTO.getQuantity()) <= 0){
                     listOrderItem.remove(li);
                     li--;
                 }
            }else{
            	shipmentsDTO.setSize(Arith.sub(sumSize, tempSize)+"");
            	if(Double.valueOf(shipmentsDTO.getSize()) <= 0){
                    listOrderItem.remove(li);
                    li--;
                }
            }
           
        }
		return listOrderItem;
	}
	
	/**
	 * 查询package列表
	 * @param chks
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ShipmentsDTO> getPackageList(String chks){
			String hql = "select distinct(package) from ShipPackage package,ShipPackageLines line where" +
					" line.shipPackages.packageId = package.packageId and package.status in ('Drafted','Picked','Packed') and line.orderNo in ("+chks+")";
			List<ShipPackage> list = this.getSession().createQuery(hql).list();
			List<ShipmentsDTO> listPackage = new ArrayList<ShipmentsDTO>();
			ShipmentsDTO dto = null;
			for(int i=0;i<list.size();i++){
				ShipPackage shipPackage = list.get(i);
				dto = new ShipmentsDTO();
				dto.setPackageId(shipPackage.getPackageId()+"");
				dto.setPackageNo(shipPackage.getPackageId()+"");
				dto.setPkgBatchSeq(shipPackage.getPkgBatchSeq());
				dto.setPkgBatchCount(shipPackage.getPkgBatchCount());
				dto.setShipVia(this.getShipMethod(shipPackage.getShipMethod()).getName());
				dto.setPackageStatus(shipPackage.getStatus());
				dto.setBillableWeight(shipPackage.getBillableWeight());
                dto.setActualWeight(shipPackage.getActualWeight());
				dto.setShipTo(shipPackage.getShiptoAddress());
				dto.setRcpFirstName(shipPackage.getRcpFirstName());
				dto.setRcpLastName(shipPackage.getRcpLastName());
				dto.setRcpCountry(shipPackage.getRcpCountry());
				List<ShipPackageLineDTO> listPackageLine = this.getPackageLineList(shipPackage.getPackageId(),chks);
				dto.setListPackageLine(listPackageLine);
				listPackage.add(dto);
			}
			return listPackage;
	}
	
	/**
	 * 根据packageId和orderNo得到ShipPackageLine列表
	 * @param packageId
	 * @param orderNo
	 * @return List<ShipPackageLineDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<ShipPackageLineDTO> getPackageLineList(Integer packageId,
			String orderNo) {
		String hql = "select line.orderNo,line.itemNo,line.quantity,line.size,sum(line.quantity),sum(line.size),line.qtyUom,line.sizeUom,item.catalogNo,item.quantity,line.status from ShipPackageLines line,OrderItem item where line.shipPackages.packageId =:packageId"
				+ " and line.itemNo = item.itemNo and line.orderNo = item.orderNo and line.orderNo in ("
				+ orderNo + ") group by line.itemNo,line.orderNo";

		List<Object[]> list = this.getSession().createQuery(hql).setInteger(
				"packageId", packageId).list();
		List<ShipPackageLineDTO> listLine = new ArrayList<ShipPackageLineDTO>();
		ShipPackageLineDTO dto = null;
		for (int i = 0; i < list.size(); i++) {
			dto = new ShipPackageLineDTO();
			Object[] obj = (Object[]) list.get(i);
			String quantity = obj[7] + "";
			String lineStr = "";
			dto.setOrderNo(Integer.parseInt(obj[0] + ""));
			dto.setItemNo(Integer.parseInt(obj[1] + ""));
			OrderItem oi = this.findOrderNoAndItemNo(Integer.parseInt(obj[0]
					+ ""), Integer.parseInt(obj[1] + ""));
			String catalogNo = oi.getCatalogNo();
			if (catalogNo != null && catalogNo.length() > 0) {
				if (oi.getType().equals("PRODUCT")) {
					Product po = this.findCatalogNoP(catalogNo);
					if (po != null) {
						int productId = po.getProductId();
						ProductShipCondition psc = this
								.findProductShipConditionP(productId);
						if (psc != null) {
							dto.setTemperature(psc.getTemperature() + "");
						} else {
							dto.setTemperature("");
						}
					} else {
						dto.setTemperature("");
					}
				} else if (oi.getType().equals("SERVICE")) {
					Service sc = this.findCatalogNoS(catalogNo);
					if (sc != null) {
						int serviceId = sc.getServiceId();
						ServiceShipCondition ssc = this
								.findServiceShipConditionS(serviceId);
						if (ssc != null) {
							dto.setTemperature(ssc.getTemperature() + "");
						} else {
							dto.setTemperature("");
						}
					} else {
						dto.setTemperature("");
					}
				}
			} else {
				dto.setTemperature("");
			}
			dto.setPackageId(packageId);
			dto.setOrderItemQty(Integer.parseInt(obj[9] + ""));
			dto.setQtyUom(obj[6]==null?"":obj[6] + "");
			dto.setSizeUom(obj[7]==null?"":obj[7] + "");
			dto.setStatus(obj[10] + "");
			dto.setItemName(obj[8] + "");
			if ("1".equals(quantity)) {
				
				dto.setQuantity(Integer.parseInt(obj[2] + ""));
				String size = obj[5] + "";
				if(size!=null&&size.split("[.]").length>1||size.substring(size.length()-1, size.length()).equals(".")){
					
					while(true){
						if(size.substring(size.length()-1, size.length()).equals("0")){
							size=size.substring(0, size.length()-1);
							
						}else{
							if(size.substring(size.length()-1, size.length()).equals(".")){
								size=size.substring(0, size.length()-1);
								
							}
							break;
						}
					}
				}
				lineStr = " OrderMain No " + obj[0] + " Item #" + obj[1] + ": "
				+ obj[2] + (obj[6]==null?" ":obj[6]) + size + (obj[7]==null?" ":obj[7]) + " ";
				dto.setSize(Double.parseDouble(size));
			} else {
				
				dto.setQuantity(Integer.parseInt(obj[4] + ""));
				String size = obj[3] + "";
				if(size!=null&&size.split("[.]").length>1||size.substring(size.length()-1, size.length()).equals(".")){
					
					while(true){
						if(size.substring(size.length()-1, size.length()).equals("0")){
							size=size.substring(0, size.length()-1);
							
						}else{
							if(size.substring(size.length()-1, size.length()).equals(".")){
								size=size.substring(0, size.length()-1);
								
							}
							break;
						}
					}
				}
				lineStr = " OrderMain No " + obj[0] + " Item #" + obj[1] + ": "
				+ obj[4] + (obj[6]==null?" ":obj[6])  + size + (obj[7]==null?" ":obj[7]) + " ,";
				dto.setSize(Double.parseDouble(size));
			}

			
			lineStr += (obj[8] + "");
			dto.setLineStr(lineStr);
			listLine.add(dto);
		}
		return listLine;
	}

	
	/**
	 * 根据orderNO 和 itemNo 得到ShipPackageLine
	 * @param orderNO
	 * @param itemNo
	 * @param flag
	 * @return List<ShipPackageLines>
	 */
	@SuppressWarnings("unchecked")
	public List<ShipPackageLines> getPackageLineList(String orderNO, String itemNo, Boolean flag){
			String hql = "";
			if(flag)
				hql = "from ShipPackageLines where orderNo=:orderNo and itemNo=:itemNo and status = 'Drafted'";
			else
				return new ArrayList<ShipPackageLines>();
			List<ShipPackageLines> list = this.getSession().createQuery(hql).setInteger("orderNo", Integer.valueOf(orderNO)).setInteger("itemNo", Integer.valueOf(itemNo)).list();
			if(list == null)
				return new ArrayList<ShipPackageLines>();
			return list;
	}
	
	/**
	 * 查询并返回ShipPackage的max packageId
	 * @return
	 */
	public Integer getCount(){
		try {
			String hql = "select max(packageId) from ShipPackage";
			Integer count = (Integer)this.getSession().createQuery(hql).uniqueResult();
			if(count == null)
				return 0;
			return count;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 根据methodId得到ShipMethod对象
	 * @param methodId
	 * @return ShipMethod
	 */
	public ShipMethod getShipMethod(Integer methodId){
		try {
			String hql = "from ShipMethod where methodId =:methodId";
			ShipMethod method = (ShipMethod)this.getSession().createQuery(hql).setInteger("methodId", methodId).uniqueResult();
			if(method == null)
				return new ShipMethod();
			return method;
		} catch (Exception ex) {
		}
		return new ShipMethod();
	}
	
	/**
	 * 根据custNo得到Customer对象
	 * @param custNo
	 * @return Customer
	 */
	public Customer getCustomer(Integer custNo){
		try {
			String hql = "from Customer where custNo =:custNo";
			Customer cust = (Customer)this.getSession().createQuery(hql).setInteger("custNo", custNo).uniqueResult();
			if(cust == null)
				return new Customer();
			return cust;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new Customer();
	}
	
	/**
	 * 根据orderNo和itemNo得到Reservation对象
	 * @param orderNo
	 * @param itemNo
	 * @return Reservation
	 */
	public Reservation getReservation(Integer orderNo,Integer itemNo){
		try {
			String hql = "from Reservation where orderNo =:orderNo and itemNo =:itemNo and size =:size";
			Reservation reservation = (Reservation)this.getSession().createQuery(hql)
			.setInteger("orderNo", orderNo).setInteger("itemNo", itemNo).uniqueResult();
			if(reservation == null)
				return new Reservation();
			return reservation;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new Reservation();
	}
	
	/**
	 * 根据id查询并返回 Reservation对象
	 * @param id
	 * @return
	 */
	public Reservation getReservation(String id){
		try {
			String hql = "from Reservation where id =:id";
			Reservation reservation = (Reservation)this.getSession().createQuery(hql)
				.setString("id",id).uniqueResult();
			if(reservation == null)
				return new Reservation();
			return reservation;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new Reservation();
	}
	
	public List<Reservation> getReservationList(String id){
		try {
			String hql = "from Reservation where id in("+id+")";
			return this.find(hql);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public Reservation getReservationByNo(final Integer orderNo,
			final Integer orderItemNo) {
		return findUnique("from Reservation o where o.orderNo=? and o.itemNo=?", orderNo, orderItemNo);
	}
	
	/**
	 * 根据orderNo、itemNo和size得到Reservation列表
	 * @param orderNo
	 * @param itemNo
	 * @param size
	 * @return List<Reservation>
	 */
	@SuppressWarnings("unchecked")
	public List<Reservation> getReservation(Integer orderNo,Integer itemNo,String size){
		try {
			String hql = "from Reservation where orderNo =:orderNo and itemNo =:itemNo and size =:size";
			List<Reservation> list = (List<Reservation>)this.getSession().createQuery(hql)
				.setInteger("orderNo", orderNo).setInteger("itemNo", itemNo).setString("size", size).list();
			if(list == null)
				return new ArrayList<Reservation>();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<Reservation>();
	}
	
	/**
	 * 根据orderNo和itemNo查询并返回OrderItem对象
	 * @param orderNo
	 * @param itemNo
	 * @return
	 */
	public OrderItem getOrderItems(Integer orderNo,Integer itemNo){
		try {
			String hql = "from OrderItem where orderNo =:orderNo and itemNo =:itemNo";
			OrderItem item = (OrderItem)this.getSession().createQuery(hql)
			.setInteger("orderNo", orderNo).setInteger("itemNo", itemNo).uniqueResult();
			if(item == null)
				return new OrderItem();
			return item;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new OrderItem();
	}
	
	/**
	 * 查询ShipPackageLines列表
	 * @param orderNos
	 * @param itemNos
	 * @param packageIds
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getShipPackageLinesList(String[] orderNos,String[] itemNos,String[] packageIds){
		String hql="select oi.orderNo,oi.itemNo,oi.catalogNo,oi.name,oi.status,oi.quantity,r.id,spl.quantity,spl.size,r.size,r.qty " +
				"from ShipPackageLines spl,Reservation r,OrderItem oi where "+
			"spl.reservationId=r.id and r.orderNo=oi.orderNo and r.itemNo=oi.itemNo and " +
			"spl.orderNo=:orderNo and spl.itemNo=:itemNo and spl.shipPackages.packageId=:packageId and spl.status='Drafted'";
		List<ShipmentsDTO> list1=new ArrayList<ShipmentsDTO>();
		for(int i=0;i<orderNos.length;i++){
			List list=this.getSession().createQuery(hql).setString("orderNo", orderNos[i]).setString("itemNo", itemNos[i]).setString("packageId",packageIds[i]).list();
			for(int j=0;j<list.size();j++){
				Object[] o=(Object[])list.get(j);
				ShipmentsDTO dto=new ShipmentsDTO();
				dto.setOrderNo(o[0].toString());
				dto.setItemNo(o[1].toString());
				dto.setCatalogNo(o[2].toString());
				dto.setItemName(o[3].toString());
				dto.setItemStatus(o[4].toString());
				dto.setOrderItemQty(o[5].toString());
				dto.setReservationId(o[6].toString());
				dto.setSplQuantity(o[7].toString());
				dto.setSplSize(o[8].toString());
				dto.setQuantity(o[10].toString());
				dto.setSize(o[9].toString());
				list1.add(dto);
			}
		}
		return list1;
	}
	
	/**
	 * 根据packageId查询 ShipPackage
	 * @param  packageId
	 * @return List
	 */
	public List<ShipPackage> getPackageId(String packageId) {
		List<ShipPackage> list = this
					.find("select distinct(package) from ShipPackage package,ShipPackageLines line where line.shipPackages.packageId = package.packageId and package.packageId="
							+ packageId);
		return list;
	}
	/**
	 * 根据packageId查询 ShipPackageLines
	 * @param  packageId
	 * @return List
	 */
	public List<ShipPackageLines> getPackageLineId(String packageId) {
		List<ShipPackageLines> list = this
					.find("select distinct(line) from ShipPackage package,ShipPackageLines line where line.shipPackages.packageId = package.packageId and line.shipPackages.packageId="
							+ packageId);
		return list;
	}
	/******************************************************************************************************
	 * dingtf
	 * 
	 * 
	 ******************************************************************************************************/
	/**
	 * 从列表中获取orderNo,根据orderNo查询ShipPackages和ShipPackageLines
	 */
	@SuppressWarnings("unchecked")
	public List<ShipPackage> getPackageTn(String orderNo) {
		String hql = "select distinct(package.packageId) from ShipPackage package,ShipPackageLines line where line.shipPackages.packageId = package.packageId and package.status = 'Packed' and line.orderNo in ("+orderNo+")";
		List<ShipPackage> list = this.getSession().createQuery(hql).list();
		return list;
	}

    //add by zhanghuibin
    @SuppressWarnings("unchecked")
	public List<ShipPackage> getPackageTnList(String orderNo) {
		String hql = "select distinct(package.packageId),package.shipMethod,package.trackingNo from ShipPackage package,ShipPackageLines line where line.shipPackages.packageId = package.packageId and package.status = 'Packed' and line.orderNo in ("+orderNo+")";
		List<ShipPackage> list = this.getSession().createQuery(hql).list();
		return list;
	}
	/**
	 * 根据orderNo查询ShipPackage和ShipPackageLines
	 * @param  orderNo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<ShipPackage> getPackagesAndPackageLine(String orderNo) {
		String hql = "select distinct(package),line.orderNo,line.itemNo from ShipPackage package,ShipPackageLines line where line.shipPackages.packageId = package.packageId and package.status = 'Packed' and line.orderNo in ("+orderNo+") group by package.packageId";
		List<ShipPackage> list = this.getSession().createQuery(hql).list();
		return list;
	}

	/**
	 * 根据packageId删除ShipPackages
	 * @param  packageId 参数
	 * @return String    返回String
	 * @throws Exception 异常处理
	 */
	public String deletePackage(Integer packageId)throws Exception{
		String flag = "0";
		try {
			String hql = "delete from ShipPackage where packageId =:packageId";
			Integer count = this.getSession().createQuery(hql).setInteger("packageId", packageId).executeUpdate();
			if(count > 0)
				flag = "1";
			this.getSession().flush();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return flag;
	}
	
	/**
	 * 根据packageId删除ShipPackageLines对象
	 * @param  packageId 参数
	 * @return String    String类型
	 * @throws Exception 异常
	 */
	public String deletePackageLine(Integer packageId)throws Exception{
		String flag = "0";
		try {
			String hql = "delete from ShipPackageLines l where l.shipPackages.packageId =:packageId";
			Integer count = this.getSession().createQuery(hql).setInteger("packageId", packageId).executeUpdate();
			if(count > 0)
				flag = "1";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return flag;
	}
	/**
	 * 根据packageId和orderNo以及itemNo删除ShipPackageLines
	 * @param  packageId 参数
	 * @param  orderNo   参数
	 * @param  itemNo    参数
	 * @return String    类型
	 * @throws Exception 异常
	 */
	public String deletePackageLine(String packageId,String orderNo,String itemNo)throws Exception{
		String flag = "0";
		try {
			String hql = "delete from ShipPackageLines where shipPackages.packageId =:packageId and orderNo =:orderNo and itemNo =:itemNo";
			Integer count = this.getSession().createQuery(hql).setString("packageId", packageId)
				.setString("orderNo", orderNo).setString("itemNo", itemNo).executeUpdate();
			if(count > 0)
				flag = "1";
			this.getSession().flush();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return flag;
	}
	/**
	 * 根据orderNo查询OrderPackages
	 * @param  orderNo       参数
	 * @return OrderPackages 实体对象
	 */
	public OrderPackage getOrderPackageByOrderNo(String orderNo){
			String hql = "from OrderPackage p where p.orderNo =:orderNo";
			OrderPackage oPackage = (OrderPackage)this.getSession().createQuery(hql)
				.setString("orderNo", orderNo).uniqueResult();
			return oPackage;
	}


	/**
	 * For remove
	 * @param  orderNo   参数
	 * @param  itemNo    参数
	 * @param  packageId 参数
	 * @return String    返回类型
	 */
	@SuppressWarnings("unchecked")
	public List getShiimentsDtoList(String orderNo,String itemNo,String packageId){
		String hql="select oi.orderNo,oi.itemNo,oi.catalogNo,oi.name,oi.status,oi.quantity,r.id,spl.quantity,spl.size,r.size,r.qty " +
				"from ShipPackageLines spl,Reservation r,OrderItem oi where "+
			"spl.reservationId=r.id and r.orderNo=oi.orderNo and r.itemNo=oi.itemNo and " +
			"spl.orderNo=:orderNo and spl.itemNo=:itemNo and spl.shipPackages.packageId=:packageId and spl.status='Drafted'";
		List<ShipmentsDTO> list1=new ArrayList<ShipmentsDTO>();
		List list=this.getSession().createQuery(hql).setString("orderNo", orderNo).setString("itemNo", itemNo).setString("packageId",packageId).list();
		for(int j=0;j<list.size();j++){
			Object[] o=(Object[])list.get(j);
			ShipmentsDTO dto=new ShipmentsDTO();
			dto.setOrderNo(o[0]+"");
			dto.setItemNo(o[1]+"");
			dto.setCatalogNo(o[2]+"");
			dto.setItemName(o[3]+"");
			dto.setItemStatus(o[4]+"");
			dto.setOrderItemQty(o[5]+"");
			dto.setReservationId(o[6]+"");
			dto.setSplQuantity(o[7]+"");
			dto.setSplSize(o[8]+"");
			dto.setQuantity(o[10]+"");
			dto.setSize(o[9]+"");
			list1.add(dto);
		}
		return list1;
	}
	
	/**
	 * for print pick list
	 * @param  id   参数
	 * @return List 返回类型
	 */
	@SuppressWarnings("unchecked")
	public List getSPLSAndSPS(Serializable id){
		String hql="from ShipPackageLines spl,ShipPackage sp where spl.shipPackages.packageId in ("
			+id+") and spl.status in('Drafted') and spl.shipPackages.packageId=sp.packageId";
		List list=this.getSession().createQuery(hql).list();
		return list;
	}
	
	/**
	 * 根据orderNo和itemNos查询OrderItem和Reservation
	 * @param  orderNo 参数
	 * @param  itemNos 参数
	 * @return List    返回类型
	 */
	@SuppressWarnings("unchecked")
	public List<ShipmentsDTO> getNewOrderItemList(String orderNo, String itemNos) {
			String hql = "select item,reservation from OrderItem item,Reservation reservation "
					+ "where reservation.orderNo = item.orderNo and reservation.itemNo = item.itemNo and item.orderNo ="
					+ orderNo + " and item.itemNo in (" + itemNos + ")";
			List<Object[]> list = this.getSession().createQuery(hql).list();
			List<ShipmentsDTO> listOrderItem = new ArrayList<ShipmentsDTO>();
			ShipmentsDTO dto = null;
			//for循环
			for(int i=0;i<list.size();i++){
				//获取值
				Object[] obj = (Object[])list.get(i);
				//获取orderItems
				OrderItem item = (OrderItem)obj[0];
				//获取Reservation
				Reservation reservation = (Reservation)obj[1];
				dto = new ShipmentsDTO();
				dto.setOrderNo(item.getOrderNo()+"");
				dto.setItemNo(item.getItemNo()+"");
				dto.setCatalogNo(item.getCatalogNo());
				dto.setItemName(item.getName().substring(0,10));
				dto.setItemStatus(item.getStatus());
				dto.setReservationId(reservation.getId()+"");
				dto.setOrderItemQty(item.getQuantity()+"");
				int itemQty = item.getQuantity();
				List<ShipPackageLines> listLine = null;
				ShipPackageLines line = null;
				//判断qty不等于1的时候
				if(itemQty != 1){
					int sumQuantity = reservation.getQty();
					int tempQuantity = 0;
					//根据id查询
					listLine = this.getPackageLineList(dto.getOrderNo(),dto.getItemNo(), true);
					//for循环
					for(int j=0;j<listLine.size();j++){
						line = listLine.get(j);
						//获取qty
						tempQuantity += line.getQuantity();
					}
					//把qty和size减掉
					dto.setQuantity((sumQuantity-tempQuantity)+"");
					dto.setSize(item.getSize()+"");
				}
				//判断qty等于1的时候
				if(itemQty == 1){
					//获取size
					Double sumSize = reservation.getSize();
					Double tempSize = 0d;
					//根据id查询
					listLine = this.getPackageLineList(dto.getOrderNo(),dto.getItemNo(),true);
					//for循环
					for(int j=0;j<listLine.size();j++){
						line = listLine.get(j);
						tempSize += line.getSize();
					}
					//把qty和size减掉
					dto.setQuantity(item.getQuantity()+"");
					dto.setSize((sumSize - tempSize)+"");
				}
					dto.setSort(i);
					listOrderItem.add(dto);
			}
			return listOrderItem;
	}
	
	/**
	 * for print pick list
	 * 更新shipment信息
	 * @param id
	 * @return
	 */
	public int updateShipment(Serializable shipmentId){
		String hql2="update Shipment set status='Ready To Ship' where shipmentId="+shipmentId;
		return this.getSession().createQuery(hql2).executeUpdate();
	}
	
	/**
	 * 根据orderNo和itemNo查询ShipmentLines
	 * @param  orderNo       参数
	 * @param  itemNo        参数
	 * @return ShipmentLine 实体对象
	 */
	public ShipmentLine getLineByOrderNoAndItemNo(String orderNo,String itemNo){
			String hql = "from ShipmentLine s where s.order.orderNo =:orderNo and s.itemNo =:itemNo and s.status<>'Invalid'";
			ShipmentLine line = (ShipmentLine)this.getSession().createQuery(hql)
				.setString("orderNo", orderNo).setString("itemNo", itemNo).uniqueResult();
			return line;
	}
	/**
	 * 根据packageId查询ShipPackage
	 * @param  packageId    参数
	 * @return ShipPackage 返回实体对象
	 */
	public ShipPackage getPackage(String packageId){
			String hql = "from ShipPackage where packageId =:packageId";
			ShipPackage packages = (ShipPackage)this.getSession().createQuery(hql)
				.setString("packageId", packageId).uniqueResult();
			return packages;
	}
	
	/**
	 * 根据packageId得到PackageLine的list
	 * @param packageIds
	 * @return List<ShipPackageLines> 
	 */
	@SuppressWarnings("unchecked")
	public List<ShipPackageLines> getShipPackageLines(String packageIds){
		String hql = "from ShipPackageLines where shipPackages.packageId in (" + packageIds + ")";
		List<ShipPackageLines> listShipPackageLines = this.getSession().createQuery(hql).list();
		if(listShipPackageLines == null)
			return new ArrayList<ShipPackageLines>();
		return listShipPackageLines;
	}
	
	/**
	 * 根据warehouseType得到Warehouse对象
	 * @param type
	 * @return Warehouse
	 */
	public Warehouse getWarehouseByType(String type){
		String hql = "from Warehouse where type =:type";
		Warehouse warehouse = (Warehouse)this.getSession().createQuery(hql).setString("type", type).uniqueResult();
		if(warehouse == null)
			return new Warehouse();
		return warehouse;
	}
	
	/**
	 * 获取Reservation信息
	 */
	public StockDetail getStockDetailByReservationId(Integer reservationId){
		String hql = "select de from Reservation re,StockDetail de where re.stockDetailId = de.id and re.id =:reservationId";
		StockDetail detail = (StockDetail)this.getSession().createQuery(hql).setInteger("reservationId", reservationId).uniqueResult();
		if(detail == null)
			return new StockDetail();
		return detail;
	}
	
	/**
	 * 更新shipPackage信息
	 * @param packageId
	 * @param status
	 */
	public void updateShipPackage(Integer packageId,String status){
		String hql = "update ShipPackage set status =:status where packageId =:packageId";
		this.getSession().createQuery(hql).setString("status", status).setInteger("packageId", packageId).executeUpdate();
	}
	
	/**
	 * 更新shipPackageline信息
	 * @param packageId
	 * @param status
	 */
	public void updateShipPackageLine(Integer packageId,String status){
		String hql = "update ShipPackageLines set status =:status where shipPackages.packageId =:packageId";
		this.getSession().createQuery(hql).setString("status", status).setInteger("packageId", packageId).executeUpdate();
	}
	
	/**
	 * 根据orderNo和ItemNo查询order
	 * @param orderNo
	 * @param itemNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public OrderItem findOrderNoAndItemNo(Integer orderNo,Integer itemNo){
		String hql = "from OrderItem where orderNo = :orderNo and itemNo = :itemNo";
		List list = this.getSession().createQuery(hql).setInteger("orderNo", orderNo).setInteger("itemNo", itemNo).list();
		OrderItem oi = null;
		if(list!=null && list.size()>0){
			oi = (OrderItem)list.get(0);
		}
		return oi;
	}
	@SuppressWarnings("unchecked")
	public Product findCatalogNoP(String catalogNo){
		String hql = "from Product where catalogNo = :catalogNo";
		List list = this.getSession().createQuery(hql).setString("catalogNo", catalogNo).list();
		Product po = null;
		if(list!=null && list.size()>0){
			po = (Product)list.get(0);
		}
		return po;
	} 
	@SuppressWarnings("unchecked")
	public ProductShipCondition findProductShipConditionP(Integer productId){
		String hql = "from ProductShipCondition where productId = :productId";
		List list = this.getSession().createQuery(hql).setInteger("productId", productId).list();
		ProductShipCondition po = null;
		if(list!=null && list.size()>0){
			po = (ProductShipCondition)list.get(0);
		}
		return po;
	} 
	@SuppressWarnings("unchecked")
	public Service findCatalogNoS(String catalogNo){
		String hql = "from Service where catalogNo = :catalogNo";
		List list = this.getSession().createQuery(hql).setString("catalogNo", catalogNo).list();
		Service sc = null;
		if(list!=null && list.size()>0){
			sc = (Service)list.get(0);
		}
		return sc;
	} 
	@SuppressWarnings("unchecked")
	public ServiceShipCondition findServiceShipConditionS(Integer serviceId){
		String hql = "from ServiceShipCondition where serviceId = :serviceId";
		List list = this.getSession().createQuery(hql).setInteger("serviceId", serviceId).list();
		ServiceShipCondition sc = null;
		if(list!=null && list.size()>0){
			sc = (ServiceShipCondition)list.get(0);
		}
		return sc;
	} 
	
	/**
	 * 根据OrderNo去Order表中,查询CustNo
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> findListOrderNo(String OrderNo){
		String hql = "select custNo from OrderMain where orderNo in('"+OrderNo+"')";
		List<Integer> list= this.getSession().createQuery(hql).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<ShippingPackageLinesDTO> findListShippingLinesDTO(String packageIds){
		String hql = "select sl , oi, o from ShipPackageLines sl,OrderItem oi,OrderMain o where o.orderNo = oi.orderNo and sl.orderNo = oi.orderNo and sl.itemNo=oi.itemNo and sl.shipPackages.packageId in ("+packageIds+")";
		List<Object[]> list = this.getSession().createQuery(hql).list();
		List<ShippingPackageLinesDTO> shipPackageLineList = new ArrayList<ShippingPackageLinesDTO>();
		for(int i=0;i<list.size();i++){
			//获取值
			Object[] obj = (Object[])list.get(i);
			//获取orderItems
			ShipPackageLines spl = (ShipPackageLines)obj[0];
			OrderItem oi = (OrderItem)obj[1];
			OrderMain o = (OrderMain)obj[2];
			ShippingPackageLinesDTO dto =new ShippingPackageLinesDTO();
			dto.setLineId(spl.getPkgLineId());
			dto.setCatalogNo(oi.getCatalogNo());
			dto.setStatus(spl.getShipPackages().getStatus());
			dto.setType(oi.getType());
			dto.setQty(spl.getQuantity());
			dto.setCompany(o.getGsCoId().toString());
			shipPackageLineList.add(dto);
		}
		return shipPackageLineList;
	}
	
	public void flush(){
		Session session = this.getSession();
		session.flush();
		session.clear();
	}
}