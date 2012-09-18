package com.genscript.gsscm.shipment.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.dao.OrderPackageItemDao;
import com.genscript.gsscm.order.entity.OrderPackageItem;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.shipment.dto.ShipmentLinesDTO;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.genscript.gsscm.shipment.entity.ShipmentLine;

@Repository
public class ShipmentLinesDao extends HibernateDao<ShipmentLine,Integer>{

	@Autowired
	private OrderPackageItemDao orderPackageItemDao;
	/**
	 * 美国仓库:根据shipmentId查询，返回一个list集合(new)
	 * @param page
	 * @param shipmentId
	 * @return
	 */
	public Page<ShipmentLinesDTO> searchLine(Page<ShipmentLine> page,Integer shipmentId) {
		Page<ShipmentLinesDTO> pageDto = new Page<ShipmentLinesDTO>();
			Map<String, Object> map = new HashMap<String, Object>();
			String hql = "select l,i.quantity,i.size ,i.name,i.qtyUom,i.sizeUom,i.type from ShipmentLine l,OrderItem i where l.order.orderNo = i.orderNo and l.itemNo = i.itemNo and i.status not in('CN') ";
			if (shipmentId != null && shipmentId.intValue() != 0) {
				hql += " and l.shipments.shipmentId=:shipmentId";
				map.put("shipmentId", shipmentId);
			}
			if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
				hql += " order by " + page.getOrderBy() + " " + page.getOrder();
			}
			page = this.findPage(page, hql, map);
			@SuppressWarnings("rawtypes")
			List list = page.getResult();
			ShipmentLinesDTO dto = null;
			List<ShipmentLinesDTO> listDto = new ArrayList<ShipmentLinesDTO>();
			System.out.println(list.size()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			for(int i=0;list != null && i<list.size();i++){
				Object[] obj = (Object[])list.get(i);
				ShipmentLine line = (ShipmentLine)obj[0];
				dto = new ShipmentLinesDTO();
				dto.setLineId(line.getLineId());
				dto.setStatus(line.getStatus());
				dto.setOrder(line.getOrder());
				dto.setItemNo(line.getItemNo());
				dto.setOrderQty(obj[1]+"");
				if(obj[2] == null){
					dto.setOrderSize("0.0");
				}else{
					dto.setOrderSize(obj[2]+"");
				}
				dto.setQtyUom(obj[4]==null?"":obj[4]+"");
				dto.setSizeUom(obj[5]==null?"":obj[5]+"");
				System.out.println(line.getOrder().getOrderNo());
				/*OrderItem orderItem = this.orderItemDao.getOrderItem(line.getItemNo(),line.getOrder().getOrderNo());
				if(orderItem!=null){
					dto.setItemName(orderItem.getName());
				}*/
				dto.setItemName(obj[3]+"");
				dto.setType(obj[6]+"");
				if(dto.getLineId()!=null){                                        
					List<OrderPackageItem> qut = this.orderPackageItemDao.findBy("shipmentLineId", dto.getLineId()); 
					if(qut!=null&&!qut.isEmpty()){
						dto.setQuantity(qut.get(0).getQuantity());
					}
				}
				listDto.add(dto);
				
			}
			pageDto.setPageNo(page.getPageNo());
			pageDto.setPageSize(page.getPageSize());
			pageDto.setResult(listDto);
			pageDto.setTotalCount(page.getTotalCount());
			return pageDto;
	}
	/**
	 * 根据userId查询用户信息
	 * @param userId
	 * @return
	 */
	public User getUser(Integer userId){
			User user = (User)this.getSession().createQuery("from User where userId =:userId")
				.setInteger("userId", userId).uniqueResult();
			return user;
	}
	/**
	 * 根据line对象更新shipmentLine对象的description信息
	 * @param line
	 * @return
	 * @throws Exception
	 */
	public String updateDescription(ShipmentLine line)throws Exception{
		String flag = "0";
			int count = this.getSession()
			.createQuery("update ShipmentLine set description =:description," +
					//"modifiedBy=:modifiedBy,modifyDate=:modifyDate where lineId =:lineId")//20101026
					"modifiedBy=:modifiedBy,modifyDate='2010-10-27' where lineId =:lineId")
			.setString("description", line.getDescription())
			.setInteger("lineId", line.getLineId())
			.setInteger("modifiedBy", line.getModifiedBy())
			.executeUpdate();
			flag = count + "";
			return flag;
	}
	/**
	 * 根据itemNo查询shipment line的信息
	 * @param itemNo
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	private OrderItem getOrderItemList(Integer itemNo,Integer orderNo){
			OrderItem orderItem = this.findUnique("from OrderItem where itemNo =:itemNo and orderNo=:orderNo",itemNo,orderNo);
			return orderItem;
	}*/
	
	public List<ShipmentLine> getShipmentLine(Integer itemNo,Integer orderNo){
		String hql = "from ShipmentLine where order.orderNo = "+orderNo+" and itemNo = "+itemNo;
		return this.find(hql);
	}
	
	public List<ShipmentLine> getShipmentLine1(Integer orderNo){
		String hql = "from ShipmentLine where order.orderNo = "+orderNo;
		return this.find(hql);
	}
	
	/**
	 * 美国仓库:根据shipmentId查询，返回一个list集合(old)
	 * @param  shipmentId
	 * @return List
	 */
	public Page<ShipmentLine> searchLine_(Page<ShipmentLine> page,Integer shipmentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from ShipmentLine where 1=1";
		if (shipmentId != null && shipmentId.intValue() != 0) {
			hql += " and shipments.shipmentId=:shipmentId";
			map.put("shipmentId", shipmentId);
		}
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		page = this.findPage(page, hql, map);
		return page;
	}
	
	/**
	 * 美国仓库:根据一个或多个shipmentId查询(idStr)，返回一个Page对象
	 * @param  idStr
	 * @return List
	 */
	public Page<ShipmentLinesDTO> searchShipmentLine(Page<ShipmentLine> page,String idStr) {
		
		Page<ShipmentLinesDTO> pageDto = new Page<ShipmentLinesDTO>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		String hql = "select l,i.quantity,i.size ,i.name from ShipmentLine l,OrderItem i where l.order.orderNo = i.orderNo and l.itemNo = i.itemNo and i.status not in('CN') ";
		if (idStr != null && idStr.length() != 0) {
			hql += " and l.shipments.shipmentId in ("+idStr+") ";
			map.put("idStr", idStr);
		}
		
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		page = this.findPage(page, hql, map);
		@SuppressWarnings("rawtypes")
		List list = page.getResult();
		ShipmentLinesDTO dto = null;
		List<ShipmentLinesDTO> listDto = new ArrayList<ShipmentLinesDTO>();
		System.out.println(list.size()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for(int i=0;list != null && i<list.size();i++){
			Object[] obj = (Object[])list.get(i);
			ShipmentLine line = (ShipmentLine)obj[0];
			dto = new ShipmentLinesDTO();
			dto.setLineId(line.getLineId());
			dto.setStatus(line.getStatus());
			dto.setOrder(line.getOrder());
			dto.setItemNo(line.getItemNo());
			dto.setOrderQty(obj[1]+"");
			if(obj[2] == null){
				dto.setOrderSize("0.0");
			}else{
				dto.setOrderSize(obj[2]+"");
			}
			
			System.out.println(line.getOrder().getOrderNo());
			/*OrderItem orderItem = this.orderItemDao.getOrderItem(line.getItemNo(),line.getOrder().getOrderNo());
			if(orderItem!=null){
				dto.setItemName(orderItem.getName());
			}*/
			dto.setItemName(obj[3]+"");
			if(dto.getLineId()!=null){                                        
				List<OrderPackageItem> qut = this.orderPackageItemDao.findBy("shipmentLineId", dto.getLineId()); 
				if(qut!=null&&!qut.isEmpty()){
					dto.setQuantity(qut.get(0).getQuantity());
				}
			}
			listDto.add(dto);
			
		}
		pageDto.setPageNo(page.getPageNo());
		pageDto.setPageSize(page.getPageSize());
		pageDto.setResult(listDto);
		pageDto.setTotalCount(page.getTotalCount());
		return pageDto;
	}
	/**
	 * 根据一个或多个shipementId查询并返回list集合
	 * @param shipmentId
	 * @param idStr
	 * @return
	 */
	public List<ShipmentLine> getShipmentLinesList(String shipmentId,String idStr){
		String hql = "from ShipmentLine where shipments.shipmentId in ("
			+ idStr + ") and " + "shipments.shipmentId<>" + shipmentId;
		List<ShipmentLine> list = this.find(hql);
		return list;
	}
	/**
	 * 保存shipmentLines对象信息
	 * @param shipmentLines
	 * @param shipment
	 * @return
	 */
	public ShipmentLine saveShipmentLines(ShipmentLine shipmentLines,Shipment shipment){
		ShipmentLine sl = new ShipmentLine();
		// 设置sl对象值
		sl.setOrder(shipmentLines.getOrder());
		sl.setItemNo(shipmentLines.getItemNo());
		sl.setShipments(shipment);
		sl.setStatus(shipmentLines.getStatus());
		sl.setCreatedBy(shipmentLines.getCreatedBy());
		sl.setCreationDate(shipmentLines.getCreationDate());
		sl.setModifyDate(new Date(System.currentTimeMillis()));
		sl.setModifiedBy(shipmentLines.getModifiedBy());
		// session保存
		this.getSession().save(sl);
		this.getSession().flush();
		return sl;
	}

	/**
	 * 更新shipmentLine的shipmentId和status
	 * 
	 * @param shipmentId
	 * @param idStr
	 */
	public boolean updateShipmentlines(String shipmentId, String idStr) {
			String hql = "update ShipmentLine set status='Invalid' "
					+ "where shipments.shipmentId in(" + idStr
					+ ") and shipments.shipmentId <> " + shipmentId;

			this.getSession().createQuery(hql).executeUpdate();
			return true;
	}

	/**
	 * 合并装运功能保存,更新shipments表的status
	 */
	public boolean updateShipmentsstatus(String shipmentId, String idStr,String reason) {
			String hql = "update Shipment set status = 'Invalid',description='Reason:"+reason
					+ "' where shipmentId in (" + idStr + ") and shipmentId <> "
					+ shipmentId;

			this.getSession().createQuery(hql).executeUpdate();
			return true;

	}
	
	public boolean updateShipmentsstatusToShipped(Integer shipmentId) {
		String hql = "update ShipmentLine set status = 'Shipped' where shipments.shipmentId = " + shipmentId;
		this.getSession().createQuery(hql).executeUpdate();
		return true;

	}
	
	public boolean updateShipmentstatusToShipped(String shipmentIds,String status) {
		String hql = "update ShipmentLine set status = '"+status+"' where shipments.shipmentId in (" + shipmentIds+")";
		this.getSession().createQuery(hql).executeUpdate();
		return true;

	}


	/**
	 * 合并装运保存,更新shipPackage表的status
	 */
	public boolean updatePackagestatus(String shipmentId, String idStr) {
			String hql = "update ShipPackage set shipments.shipmentId = " + shipmentId
					+ " where shipments.shipmentId in(" + idStr + ") and shipments.shipmentId <> "
					+ shipmentId;

			this.getSession().createQuery(hql).executeUpdate();
			return true;

	}
	
	/**
	 * 根据orderNo查询并返回ShipmentLines对象集合
	 * @param orderNo
	 * @return
	 */
	public List<ShipmentLine> getLineByOrderNo(Integer orderNo) {
		return  this.findBy("order.orderNo", orderNo);
	}
	
	/*
	 * 根据shipmentId 查询orderNo
	 */
	public List<Integer> getOrderNo (Integer shipmentId){
		List<ShipmentLine> lines= this.findBy("shipments.shipmentId", shipmentId);
		List<Integer> orderNo = new ArrayList<Integer>();
		if(lines!=null&&!lines.isEmpty()){
			for(ShipmentLine line : lines){
				if(orderNo==null||orderNo.isEmpty()){
					orderNo.add(line.getOrder().getOrderNo());
				}else{
					String is = "0";
					for(Integer no : orderNo){
						if(no.equals(line.getOrder().getOrderNo())){
							is="1";
						}
					}
					if(is.equals("0")){
						orderNo.add(line.getOrder().getOrderNo());
					}
				}
			}
		}
		return orderNo;
	}
	
	public List<ShipmentLine> searchShipmentLines(List<Integer> orderNoList){
		String hql = "select sl from ShipmentLine sl where sl.order.orderNo in";
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
			return this.find(hql);
		}else{
			return null;
		}
		
	}
	
}
