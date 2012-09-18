package com.genscript.gsscm.purchase.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.purchase.dto.PurchaseOrderDTO;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipPackageLines;

@Repository
public class PurchaseOrderDao extends HibernateDao<PurchaseOrder, Integer> {
	/**
	 * 美国分页查询
	 * 
	 * @param page
	 *            分页对象
	 * @param srch
	 * @return
	 */
	public Page<PurchaseOrder> searchPo(Page<PurchaseOrder> page,
			PurchaseOrderDTO srch) {
		if (srch == null) {
			srch = new PurchaseOrderDTO();//
		}
		//Map<String, Object> map = new HashMap<String, Object>();
		String hql = "SELECT distinct w FROM PurchaseOrder w  ,OrderAddress address,PoReceivingTmp ppt ";
		if(srch.getUsPOrderNo()!=null&&!srch.getUsPOrderNo().equals("")){
			hql += " ,OrderErpMapping po ";
		}
		/*if (srch.getRecevingClerk() != "" && srch.getRecevingClerk() != null) {
			hql = hql + ",PurchaseOrderItem poi,ShipClerk sck ";
		}*/
			hql = hql
					+ ",Vendor ven  "
					+ "WHERE w.orderNo = ppt.poNo and  w.vendorNo = ven.vendorNo " 
					+ " and address.orderNo = w.srcSoNo and address.addrType='SHIP_TO' ";
			if(srch.getUserName()!=null&&srch.getUserName().equals("lucyw")){
				hql += " and address.city='nanjing' ";
			}else{
				if(srch.getUserName()!=null){
					hql += " and address.city!='nanjing' ";
				}
				if (srch.getRecevingClerk() != null && !srch.getRecevingClerk().equals("")) {
					hql += " and EXISTS(SELECT 1 FROM ShipClerkAssigned sas WHERE sas.itemType = 'SERVICE' and sas.clsId in (SELECT poi.clsId FROM PurchaseOrderItem poi "
							+ "WHERE poi.orderNo = w.orderNo "
							+ "AND poi.type = 'SERVICE' "
							/*+ " ORDER BY poi.itemNo ASC"*/
							/*+ " LIMIT 0,1"*/
							+" group by poi.orderNo"
							+")"
							+ "AND sas.shippingClerk = "+srch.getRecevingClerk()+")";
					//hql +=" and sck.clerkFunction in ('Receiver', 'All') and poi.orderNo = w.orderNo and poi.clsId in (select sas.clsId from ShipClerkAssigned sas where sas.itemType = 'SERVICE' and  sas.shippingClerk = "+srch.getRecevingClerk()+") and poi.type='SERVICE' ";
					/*hql += " and sas.shippingClerk = sck.clerkId and sck.clerkFunction in ('Receiver', 'All') and poi.clsId = sas.clsId and poi.type = sas.itemType and poi.orderNo = w.orderNo ";
					hql = hql + " and sck.clerkId=:rsId";*/
					//map.put("rsId", Integer.parseInt(srch.getRecevingClerk()));
				}
			}
			
		if(srch.getUsSOrderNo()!=null&&!srch.getUsSOrderNo().equals("")){
			hql +=" and w.srcSoNo = "+srch.getUsSOrderNo();
		}
		if (srch != null) {
			if (srch.getVendorName() != null) {
				if (srch.getVendorName() != null
						&& srch.getVendorName().length() > 0) {
					hql += " and ven.vendorName='"+srch.getVendorName()+"'";
					//map.put("vendorName", srch.getVendorName());
				}
			}
		}
		if (srch.getOrderNo() != null && srch.getOrderNo().intValue() != 0) {
			hql += " and w.orderNo="+srch.getOrderNo();
			//map.put("orderNo", srch.getOrderNo());
		}
		if (srch.getStatus() != null && srch.getStatus().trim().length() > 0) {
			hql += " and w.status='"+srch.getStatus()+"'";
			//map.put("status", srch.getStatus());
		}
		if(srch.getUsPOrderNo()!=null&&!srch.getUsPOrderNo().equals("")){
			hql += " and  po.erpUsPo =  "+srch.getUsPOrderNo()+" and po.orderNo=w.srcSoNo";
		}
		//if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += "  order by ppt.receivingTime desc ";
		//}

		page = this.findPage(page, hql);
		List list2 = this.getSession().createQuery(hql).list();
		Long count2 = 0L;
		if(list2!=null){
			count2 = Long.parseLong(list2.size()+"");
		}
		page.setTotalCount(count2);
		return page;
	}


	/**
	 * 美国，根据Package No查询，返回一个list集合
	 * 
	 * @param packageId
	 *            参数
	 * @return List 集合
	 */
	public List<ShipPackageLines> getListSpl(Integer packageId) {
		String hql = "from ShipPackageLines where shipPackages.packageId = ?";
		return this.find(hql, packageId);
	}

	/**
	 * 查询符合条件的TarckingNo
	 * 
	 * @return List 集合
	 */
	@SuppressWarnings("unchecked")
	public List getListTrackingNo() {
		List lists = null;
		lists = this
				.getSession()
				.createSQLQuery(
						"SELECT distinct tracking_no FROM  purchase.po_receiving_tmp")
				.list();
		return lists;
	}

	/**
	 * 美国，根据trackingNo查询详细信息
	 * 
	 * @param trackingNo
	 *            参数
	 * @return ShipPackage 实体对象
	 */
	public ShipPackage getShipPageInfo(String trackingNo) {
		String hql = "from ShipPackage where trackingNo = ?";
		return this.findUnique(hql, trackingNo);
	}

	/**
	 * 根据orderNo查询详细信息
	 * 
	 * @param trackingNo
	 *            参数
	 * @return ShipPackage 实体对象
	 */
	public List<OrderItem> getOrderPageDetail(Integer orderNo) {
		String hql = "from OrderItem where order.orderNo = ?";
		return this.find(hql, orderNo);
	}

	/**
	 * 查询PurchaseOrder状态
	 */
	public List<PurchaseOrder> getPoStatus() {
		String hql = "select p.status from PurchaseOrder p group by p.status";
		return this.find(hql);
	}

	/**
	 * 根据itemNo查询trackingNo
	 * 
	 * @param itemNo
	 *            参数
	 * @return List 集合
	 */
	public List<ShipPackage> getShipPackTrackNo(Integer itemNo) {
		String hql = "select sp from ShipPackageLines spl,ShipPackage sp where spl.shipPackages.packageId = sp.packageId and spl.itemNo = ?";
		return this.find(hql, itemNo);
	}

	/**
	 * 根据warehouseId查询Warehouse对象
	 * 
	 * @param id
	 * @return Warehouse
	 */
	public Warehouse getWarehouseById(Integer id) {
		Warehouse wh = (Warehouse) this.getSession().createQuery(
				"from Warehouse where warehouseId =:id").setInteger("id", id)
				.uniqueResult();
		if (wh == null)
			return new Warehouse();
		return wh;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,List<Integer>> searchOrderByVendorNo(final Page<PurchaseOrder> page, 
			final Map<String,Object> filterMap) throws Exception {
		Map<String,List<Integer>> resultMap = new HashMap<String,List<Integer>>();
		String hql = "";
		if (filterMap.get("orderNo") != null && filterMap.get("vendorNo") != null) {
			hql = "SELECT DISTINCT a.orderNo " +
			" FROM PurchaseOrder a, PurchaseOrderItem b " +
			" WHERE a.orderNo = b.orderNo " +
			" AND b.status IN ('PS', 'SH', 'VS') " +
			" And a.vendorNo =:vendorNo and a.orderNo =:orderNo order by a.orderNo";
			Query query = this.createQuery(hql)
				.setInteger("vendorNo", Integer.parseInt(filterMap.get("vendorNo").toString()))
				.setInteger("orderNo", Integer.parseInt(filterMap.get("orderNo").toString()));
			List<Integer> purchaseOrderBeanAllList = query.list();
			resultMap.put("purchaseOrderBeanAllList", purchaseOrderBeanAllList);
			int first = page.getFirst();
			int initFirst = first-1;
			query.setFirstResult(initFirst);
			query.setMaxResults(page.getPageSize());
			List<Integer> purchaseOrderBeanlist = query.list();
			resultMap.put("purchaseOrderBeanlist", purchaseOrderBeanlist);
		} else if (filterMap.get("vendorNo") != null) {
			hql = "SELECT DISTINCT a.orderNo " +
			" FROM PurchaseOrder a, PurchaseOrderItem b " +
			" WHERE a.orderNo = b.orderNo " +
			" AND b.status IN ('PS', 'SH', 'VS') " +
			" And a.vendorNo =:vendorNo order by a.orderNo";
			Query query = this.createQuery(hql)
				.setInteger("vendorNo", Integer.parseInt(filterMap.get("vendorNo").toString()));
			List<Integer> purchaseOrderBeanAllList = query.list();
			resultMap.put("purchaseOrderBeanAllList", purchaseOrderBeanAllList);
			int first = page.getFirst();
			int initFirst = first-1;
			query.setFirstResult(initFirst);
			query.setMaxResults(page.getPageSize());
			List<Integer> purchaseOrderBeanlist = query.list();
			resultMap.put("purchaseOrderBeanlist", purchaseOrderBeanlist);
		}
		return resultMap;
	}
	
	/*
	 * 根据orderNos 获取purchase
	 */
	public List<PurchaseOrder> getPurchaseOrderList(String orderNos){
		String hql = "from PurchaseOrder where srcSoNo in ("+orderNos+")";
		
		return this.find(hql);
	}
	
	/**
	 * 根据trackingNo和itemNo和orderNo查询shipPackageLines中的qty和size以及ReceivingLogs中的qty和size比较
	 * 
	 * @param trackingNo
	 * @param itemNo
	 * @param orderNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findReceiveFlagLine(Integer orderNo) {
			String sql = "select distinct line.order_no ,sum(IF(line.size=0,1,IFNULL(line.size,1)) * line.quantity),sum(line.quantity)  from purchase.purchase_order_items line ,product.service s where line.order_no = "+orderNo+" and line.type='SERVICE' and s.catalog_no=line.catalog_no and s.shippable!='N'  group by line.order_no";
			System.out.println(sql+"<<<<<<<<<<<<<<<<<<,");
			List list = this.getSession().createSQLQuery(sql).list();
			return list;
	}
	
	public void updateReceiveFlag(Integer orderNo){
		String hql="update PurchaseOrder set receivingFlag='1' where orderNo="+orderNo;
		this.getSession().createQuery(hql).executeUpdate();
	}
	
	public void updatePOStatus(Integer orderNo,String status){
		String hql="update PurchaseOrder set status='"+status+"' where orderNo="+orderNo;
		this.getSession().createQuery(hql).executeUpdate();
	}
	
	public List<PurchaseOrder> findBySrcSoNo(String srcSoNos){
		String hql = "from PurchaseOrder po where po.srcSoNo in("+srcSoNos+")";
		return this.find(hql);
	}
	
	/*
	 * 获取可以打印的po
	 */
	public Page<PurchaseOrder> searchPoOfPrint(Page<PurchaseOrder> page){
		String hql = " from PurchaseOrder po where po.orderNo in (select poi.orderNo from PurchaseOrderItem poi where poi.fileDownloaded = 1)";
		return this.findPage(page, hql);
	}
}
