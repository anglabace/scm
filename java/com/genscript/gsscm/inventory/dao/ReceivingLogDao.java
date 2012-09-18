package com.genscript.gsscm.inventory.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.inventory.dto.ReceivingLogDTO;
import com.genscript.gsscm.inventory.entity.ReceivingLog;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.purchase.entity.PurchaseOrderItem;
import com.genscript.gsscm.serv.dao.ServiceDao;


@Repository
public class ReceivingLogDao extends HibernateDao<ReceivingLog, Integer> {
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ServiceDao serviceDao;
	
	/**
	 * 添加到receivingLogs数据
	 * 
	 * @param rl
	 */
	public void addReceivingLogs(ReceivingLog rl) {
		this.save(rl);
		this.flush();
	}
	/**
	 * 根据trackingNo查询ReceivingLogs、ShipPackageLines
	 * @param  trackingNo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List findReShipLines(String trackingNo){
		String hql = "select r from ReceivingLog r,ShipPackageLines spl where r.orderNo=spl.orderNo and r.itemNo = spl.itemNo and r.trackingNo = ?";
		return  this.find(hql,trackingNo);
	}
	
	public List<ReceivingLog> findReShipLinesByOrderAndItemNo(Integer orderNo,Integer itemNo){
		String hql = "select r from ReceivingLog r where r.orderNo="+orderNo+" and r.itemNo = "+itemNo;
		return  this.find(hql);
	}
	/**
	 * 根据开始时间，和结束时间，查询receiving_logs中order_no
	 * @param  reveivingDate
	 * @param  reveivingDates
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<ReceivingLog> getLogs(String reveivingDate,String reveivingDates,String type,String clerk) {
		String sql = "";
		if(type == "MANUFACTURING" || type.equals("MANUFACTURING")){ // 判断传过来的type为MANUFACTURING
			sql = "select s.order_no from inventory.receiving_logs s where s.order_type = 'Work Order' ";
		}
		else if(type == "SALES" || type.equals("SALES")){ // 判断传过来的type为SALES
			sql = "select s.order_no from inventory.receiving_logs s where s.order_type = 'Purchase Order' ";
			if(clerk!=null&&!clerk.equals("null")&&!clerk.equals("")){
				sql +="and s.received_by = "+clerk;
			}
			System.out.println("sql="+sql);
		}
		
		else if(type==null ||type==""){ 
			// 如果为其他查询所有
			sql = "select s.order_no from inventory.receiving_logs s where 1=1  ";
		}
		if(reveivingDate != null){
			sql += " and s.reveiving_date >= '"+reveivingDate+" 00:00:00' ";
		}
		if(reveivingDates !=null){
			sql +=" and  s.reveiving_date <= '"+reveivingDates+" 23:59:59'";
		}
		sql +=" group by s.order_no";
		List<ReceivingLog> list = this.getSession().createSQLQuery(sql).list();
		return list;
	}
	/**
	 * 根据orderNo查询ReceivingLogs数据
	 * @param orderNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getLogsByOrderNo(String reveivingDate,String reveivingDates,String type,String orderNo,String clerk){
		List<ReceivingLogDTO> listDto = new ArrayList<ReceivingLogDTO>();
		String hql ="";
		if(type.equals("MANUFACTURING")){ // 判断传过来的type为MANUFACTURING
			hql = "from ReceivingLog s where s.orderType = 'Work Order' ";
		}else if(type.equals("SALES")){ // 判断传过来的type为SALES
			hql = "from ReceivingLog s where s.orderType = 'Purchase Order' ";
			if(clerk!=null&&!clerk.equals("null")&&!clerk.equals("")){
				hql +=" and s.receivedBy = "+clerk;
			}
			System.out.println("hql="+hql);
		}
		
		if(reveivingDate != null ){
			hql += " and s.reveivingDate >= '"+reveivingDate+" 00:00:00' ";
		}
		if(reveivingDates !=null){
			hql +="  and s.reveivingDate <= '"+reveivingDates+" 23:59:59'";
		}
		hql +=" and s.orderNo in("+orderNo+")";
		
		List list = this.getSession().createQuery(hql).list();
		ReceivingLogDTO dto = null;
		ReceivingLog rl = null;
		PurchaseOrderItem item = null;
		WorkOrder workOrder = null;
		List lists = null;
		if(type.equals("SALES")){
			String hql2 = "select poi from PurchaseOrderItem poi "
				+ "where poi.orderNo in ("+orderNo+")";
			lists = this.getSession().createQuery(hql2).list();
		}
		List lists2 = null;
		if(type.equals("MANUFACTURING")){
			String hql3 = "select wo from " +
			"WorkOrder wo,WoBatche wob,WoBatchDetail wobd,Warehouse wh " +
			"where wo.orderNo = wobd.workOrders.orderNo and wh.warehouseId = wo.warehouseId " +
			"and wob.woBatchId = wobd.woBatche.woBatchId and wo.orderNo in ("+orderNo+")";
			lists2 = this.getSession().createQuery(hql3).list();
		}
		for (int i = 0; list != null && i < list.size(); i++) {
			dto = new ReceivingLogDTO();
			rl = (ReceivingLog)list.get(i);
			User user = this.getUserName(rl.getReceivedBy());
			//重新赋值
			dto.setOrderNo(rl.getOrderNo());
			dto.setItemNo(rl.getItemNo());
			dto.setCatalogNo(rl.getCatalogNo());
			dto.setQtyUom(rl.getQtyUom());
			dto.setQtyReceived(rl.getQtyReceived());
			dto.setSizeUom(rl.getSizeUom());
			dto.setSizeReceived(rl.getSizeReceived());
			dto.setReveivingDate(rl.getReveivingDate());
			dto.setUserName(user.getLoginName());
			if(type.equals("SALES")){
				for (int j = 0; lists !=null && j < lists.size(); j++) {
					item = (PurchaseOrderItem)lists.get(j);
					if(item.getOrderNo().equals(dto.getOrderNo()) && item.getItemNo().equals(dto.getItemNo())){
						if(item.getType().equals("PRODUCT")){
							Product product = this.productDao.getProductByCatalogNo(dto.getCatalogNo());
							if(product!=null){
								dto.setName(product.getName());
							}
						}else {
							com.genscript.gsscm.serv.entity.Service service = this.serviceDao.getServiceByCatalogNo(dto.getCatalogNo());
							if(service!=null){
								dto.setName(service.getName());
							}
						}
						dto.setSumQty(item.getQuantity());
						dto.setSumSize(item.getSize());
					}
				}

			}
			if(type.equals("MANUFACTURING")){
				for (int j = 0; lists2 !=null && j < lists2.size(); j++) {
					workOrder = (WorkOrder)lists2.get(j);
					if(workOrder.getOrderNo().equals(dto.getOrderNo())){
						dto.setSumQty(workOrder.getQuantity());
						dto.setSumSize(workOrder.getSize());
					}
				}

			}
//			dto.setName(name);
			listDto.add(dto);
		}
		return listDto;
	}
	/**
	 * 根据reveivingBy查询UserName
	 * @param receiveBy
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public User getUserName(Integer receiveBy){
		String hql = "from User where userId=:receiveBy";
		List list = this.getSession().createQuery(hql).setInteger("receiveBy", receiveBy).list();
		User user = null;
		if(list !=null && list.size()>0){
			user = (User)list.get(0);
		}
		return user;
	}
	
	public List findReceiveFlagLine(Integer orderNo ){
		String sql = "select distinct line.order_no ,sum(line.size_received* line.qty_received)  from inventory.receiving_logs line where line.order_no = "+orderNo+"  group by line.order_no";
		List list = this.getSession().createSQLQuery(sql).list();
		return list;
	}
	
	public void flush(){
		Session session = this.getSession();
		session.flush();
		session.clear();
	}
}
