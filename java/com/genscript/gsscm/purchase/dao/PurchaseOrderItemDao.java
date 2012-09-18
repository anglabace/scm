package com.genscript.gsscm.purchase.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.util.Arith;
import com.genscript.gsscm.inventory.entity.ReceivingLog;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.purchase.dto.PurchaseOrderItemDTO;
import com.genscript.gsscm.purchase.entity.PoReceivingTmp;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.purchase.entity.PurchaseOrderItem;
import com.genscript.gsscm.shipment.entity.ShipPackageLines;

@Repository
public class PurchaseOrderItemDao extends
		HibernateDao<PurchaseOrderItem, Integer> {

	/**
	 * 获得某个Product待采购进的总和.
	 * 
	 * @param catalogNo
	 * @return
	 */
	public Long getCommitedItemTotal(String catalogNo) {
		String hql = "select sum(item.quantity) from PurchaseOrderItem item, PurchaseOrder po where item.orderNo=po.orderNo and po.status=:status and item.catalogNo=:catalogNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		map.put("status", "NEW");
		return this.findUnique(hql, map);
	}

	/**
	 * 获得关于某个Product的采购(Purchase)相关信息.
	 * 
	 * @param catalogNo
	 * @return
	 */
	public List<Object[]> getPdtPurchaseOrderList(String catalogNo) {
		String hql = "select item.orderNo, sum(item.quantity) from PurchaseOrderItem item where item.catalogNo=:catalogNo group by item.orderNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		return this.find(hql, map);
	}

	/**
	 * 获得一个Purchase Order所有状态的OrderItem.
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<PurchaseOrderItem> getPurchaseOrderAllItemList(Integer orderNo) {
		String hql = "from PurchaseOrderItem where orderNo=:orderNo order by itemNo ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		return this.find(hql, map);
	}

	/**
	 * PurchaseOrderItems
	 * 
	 * @param page
	 *            分页对象
	 * @param orderNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List searchPoIDetail(Integer orderNo) {
		List<PurchaseOrderItemDTO> listDetails = new ArrayList<PurchaseOrderItemDTO>();
		List list = null;
		String hql = "SELECT poi,ppt FROM PurchaseOrderItem poi, PoReceivingTmp ppt "
			+ "WHERE poi.orderNo = ppt.poNo and poi.itemNo = ppt.poLineNo and poi.orderNo = ?";
		/*String hql = "SELECT poi,sp.trackingNo FROM PurchaseOrderItem poi,OrderMain o,ShipPackage sp,ShipPackageLines spl "
				+ "WHERE o.srcPoNo = poi.orderNo AND spl.orderNo = o.orderNo "
				+ "AND spl.itemNo = poi.itemNo AND sp.packageId = spl.shipPackages.packageId and spl.status = 'Shipped' "
				+ "and poi.orderNo = ?";*/
		list = this.find(hql, orderNo);

		PurchaseOrderItem item = null;
		PoReceivingTmp tmp = null;
		// for循环
		for (int i = 0; list != null && i < list.size(); i++) {
			// 获取Object
			Object[] obj = (Object[]) list.get(i);
			// 从Object获取PurchaseOrderItems
			item = (PurchaseOrderItem) obj[0];
			tmp = (PoReceivingTmp) obj[1];
			// 获取trackingNo
			
			// 查询ReceivingLogs
			/*List listLogs = this.getSession().createQuery(
					"from ReceivingLog where orderNo =" + item.getOrderNo()
							+ " and itemNo = " + item.getItemNo()
							+ " order by id ASC").list();
			int quantity = 0;
			double size = 0d;
			String locationCode = "";
*/
			PurchaseOrderItemDTO poi = new PurchaseOrderItemDTO();
		/*	if (item.getQuantity() != 1) {
				int sumQuantity = item.getQuantity();

				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					quantity += log.getQtyReceived();
					locationCode = log.getLocationCode();
				}
				poi.setQuantity(sumQuantity - quantity);
				poi.setSize(item.getSize());
			}
			if (item.getQuantity() == 1) {
				double sumSize = item.getSize();
				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					size = Arith.add(size, log.getSizeReceived());
					//size += log.getSizeReceived();
					locationCode = log.getLocationCode();
				}
				poi.setQuantity(item.getQuantity());
				poi.setSize(Arith.sub(sumSize, size));
			}*/
			// 重新赋值
			poi.setQuantity(tmp.getQty());
			poi.setSize(tmp.getSize());
			poi.setQtyUom(item.getQtyUom());
			poi.setSizeUom(item.getSizeUom());
			PurchaseOrder po = new PurchaseOrder();
			po.setOrderNo(item.getOrderNo());
			poi.setPurchaseOrder(po);
			poi.setItemNo(item.getItemNo());
			poi.setCatalogNo(item.getCatalogNo());
			poi.setStatus(item.getStatus());
			poi.setOrderItemId(item.getId());
			poi.setTrackingNo(tmp.getTrackingNo()==null?"":tmp.getTrackingNo());
			//poi.setLocationCode(locationCode);
			listDetails.add(poi);
		}
		return listDetails;
	}

	/**
	 * PurchaseOrderItems
	 * 
	 * @param page
	 *            分页对象
	 * @param trackingNo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PurchaseOrderItemDTO> searchPoIDetails(String trackingNo) {
		List<PurchaseOrderItemDTO> listDetails = new ArrayList<PurchaseOrderItemDTO>();
		List list = new ArrayList();
		String hql = "select poi,ppt from PurchaseOrderItem poi,PurchaseOrder po ,PoReceivingTmp ppt where po.orderNo = poi.orderNo and ppt.poNo = po.orderNo and ppt.poLineNo = poi.itemNo " +
				" and ppt.trackingNo ="+ trackingNo;
		/*String sql = "select i,s.packageId,l.pkgLineId from PurchaseOrderItem i,OrderMain o,ShipPackage s,"
				+ "ShipPackageLines l where o.srcPoNo = i.orderNo "
				+ "and l.orderNo = o.orderNo and l.itemNo = i.itemNo "
				+ "and s.packageId = l.shipPackages.packageId and l.status = 'Shipped' "
				+ "and s.trackingNo='" + trackingNo + "'";*/

		list = this.getSession().createQuery(hql).list();
		PurchaseOrderItem item = null;
		PoReceivingTmp tmp = null;
		for (int i = 0; list != null && i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			item = (PurchaseOrderItem)obj[0];
			tmp = (PoReceivingTmp)obj[1];
			PurchaseOrderItemDTO poi = new PurchaseOrderItemDTO();
			/*List listLogs = this.getSession().createQuery(
					"from ReceivingLog where orderNo =" + item.getOrderNo()
							+ " and itemNo = " + item.getItemNo()
							+ " order by id ASC").list();
			int quantity = 0;
			double size = 0d;
			String locationCode = "";
			
			if (item.getQuantity() != 1) {
				int sumQuantity = item.getQuantity();

				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					quantity += log.getQtyReceived();
					locationCode = log.getLocationCode();
				}
				poi.setQuantity(sumQuantity - quantity);
				poi.setSize(item.getSize());
			}
			if (item.getQuantity() == 1) {
				double sumSize = item.getSize();
				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					size = Arith.add(size, log.getSizeReceived());
					//size += log.getSizeReceived();
					locationCode = log.getLocationCode();
				}
				poi.setQuantity(item.getQuantity());
				poi.setSize(Arith.sub(sumSize, size));
			}*/
			poi.setQuantity(tmp.getQty());
			poi.setSize(tmp.getSize());
			poi.setQtyUom(item.getQtyUom());
			poi.setSizeUom(item.getSizeUom());
			PurchaseOrder po = new PurchaseOrder();
			po.setOrderNo(item.getOrderNo());
			poi.setPurchaseOrder(po);
			poi.setItemNo(item.getItemNo());
			poi.setCatalogNo(item.getCatalogNo());
			poi.setStatus(item.getStatus());
			poi.setOrderItemId(item.getId());
			poi.setTrackingNo(trackingNo==null?"":trackingNo);
			//poi.setLocationCode(locationCode);
			listDetails.add(poi);
		}
		return listDetails;
	}

	/**
	 * 根据orderNo分页查询 detail信息
	 * 
	 * @param page
	 *            分页对象
	 * @param orderNo
	 *            参数
	 * @return Page
	 */
	@SuppressWarnings("unchecked")
	public Page<PurchaseOrderItemDTO> getPoPage(Page<PurchaseOrderItem> page,
			Integer orderNo) {
		Page<PurchaseOrderItemDTO> pageDto = null;
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "select poi,ppt,po from PurchaseOrderItem poi,PurchaseOrder po ,PoReceivingTmp ppt where po.orderNo = poi.orderNo and ppt.poNo = po.orderNo and ppt.poLineNo = poi.itemNo ";
		/*String hql = "select poi,spl,sp.trackingNo,sp.packageId,po from PurchaseOrderItem poi,OrderMain o,ShipPackage sp,ShipPackageLines spl,PurchaseOrder po "
				+ "where o.srcPoNo = poi.orderNo and spl.orderNo = o.orderNo and spl.itemNo = poi.itemNo and po.orderNo = poi.orderNo "
				+ "and sp.packageId = spl.shipPackages.packageId and spl.status = 'Shipped'";*/
		if (orderNo != null && orderNo.intValue() != 0) {
			hql += " and poi.orderNo=" + orderNo;
		}
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		page = this.findPage(page, hql, map);
		List lists = page.getResult();

		pageDto = new Page<PurchaseOrderItemDTO>();
		List<PurchaseOrderItemDTO> listDto = new ArrayList<PurchaseOrderItemDTO>();
		PurchaseOrderItemDTO dto = null;
		PoReceivingTmp tmp = null;
		PurchaseOrderItem item = null;
		//ShipPackageLines line = null;
		PurchaseOrder po = null;
		for (int i = 0; lists != null && i < lists.size(); i++) {
			dto = new PurchaseOrderItemDTO();
			
			Object[] obj = (Object[]) lists.get(i);
			item = (PurchaseOrderItem) obj[0];
			tmp = (PoReceivingTmp) obj[1];
			po = (PurchaseOrder) obj[2];
			/*line = (ShipPackageLines) obj[1];
			po = (PurchaseOrder) obj[4];*/

			/*List listLogs = this.getSession().createQuery(
					"from ReceivingLog where orderNo =" + item.getOrderNo()
							+ " and itemNo = " + item.getItemNo()
							+ " order by id ASC").list();*/
			dto.setReceId(tmp.getId());
			dto.setQuantity(tmp.getQty());
			dto.setSize(tmp.getSize());
			dto.setSizeUom(item.getSizeUom());
			dto.setTrackingNo(tmp.getTrackingNo());
			/*int quantity = 0;
			double size = 0;*/
			String locationCode = "";
			/*if (item.getQuantity() != 1) {
				int sumQuantity = item.getQuantity();

				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					quantity += log.getQtyReceived();
					locationCode = log.getLocationCode();
				}
				dto.setQuantity(sumQuantity - quantity);
				dto.setSize(item.getSize());
				System.out.println(dto.getSize()+"<<<<<<<<<<<<<<<<<<<<");
			}
			if (item.getQuantity() == 1) {
				double sumSize = item.getSize();
				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					size = Arith.add(size, log.getSizeReceived());
					//size += log.getSizeReceived();
					locationCode = log.getLocationCode();
				}
				dto.setQuantity(item.getQuantity());
				System.out.println(sumSize+"   "+size);
				dto.setSize(Arith.sub(sumSize, size));
				System.out.println(dto.getSize()+">>>>>>>>>>>>>>>>>>>>>");
			}*/
			/*dto.setTrackingNo(obj[2]==null?"":obj[2]+"");
			dto.setPackageId(obj[3]==null?"":obj[3]+"");*/
			/*dto.setPkgLineId(line.getPkgLineId() + "");*/
			dto.setOrderItemId(item.getId());
			dto.setPurchaseOrder(po);
			dto.setItemNo(item.getItemNo());
			dto.setCatalogNo(item.getCatalogNo());
			dto.setName(item.getName());
			dto.setStatus(item.getStatus());
			dto.setQtyUom(item.getQtyUom());//(line.getQtyUom());
			dto.setSizeUom(item.getSizeUom());//(line.getSizeUom());
			dto.setLocationCode(locationCode);
			
			listDto.add(dto);
		}
		pageDto.setResult(listDto);
		pageDto.setPageNo(page.getPageNo());
		pageDto.setPageSize(page.getPageSize());
		pageDto.setTotalCount(page.getTotalCount());
		return pageDto;
	}

	/**
	 * 根据orderNo 查询PurchaseOrderItems Receive All信息
	 * 
	 * @param page
	 *            分页对象
	 * @param orderNo
	 *            参数
	 * @return List 集合
	 */
	@SuppressWarnings("unchecked")
	public List searchPoIDetailAll(String[] orderNo) {
		List list2 = new ArrayList();
		for (int i = 0; i < orderNo.length; i++) {
			Integer orderNos = Integer.parseInt(orderNo[i]);
			List list = this.searchPoIDetail(orderNos);
			for (int j = 0; j < list.size(); j++) {
				list2.add(list.get(j));
			}
		}
		return list2;
	}

	/**
	 * 根据orderItemId查询，返回一个PurchaseOrderItems实体对象
	 * 
	 * @param orderItemId
	 *            参数
	 * @return PurchaseOrderItems 实体对象
	 */
	@SuppressWarnings("unchecked")
	public PurchaseOrderItemDTO getPurchaseByItemId(Integer orderItemId) {
		PurchaseOrderItemDTO poi = new PurchaseOrderItemDTO();
		String hql = "select poi,po,ppt from PurchaseOrderItem poi,PurchaseOrder po,PoReceivingTmp ppt "
				+ "where po.orderNo = poi.orderNo "
				+ " and ppt.poNo = po.orderNo and ppt.poLineNo = poi.itemNo "
				+ " and poi.id=:orderItemId";
		List list = this.getSession().createQuery(hql).setInteger(
				"orderItemId", orderItemId).list();
		if (list != null && list.size() > 0) {
			Object[] obj = (Object[]) list.get(0);
			PurchaseOrderItem item = (PurchaseOrderItem) obj[0];
			PurchaseOrder po = (PurchaseOrder) obj[1];
			PoReceivingTmp tmp  = (PoReceivingTmp) obj[2];
			/*List listLogs = this.getSession().createQuery(
					"from ReceivingLog where orderNo =" + item.getOrderNo()
							+ " and itemNo = " + item.getItemNo()).list();*/
			poi.setQuantity(tmp.getQty());
			poi.setSize(tmp.getSize());
			poi.setQtyUom(item.getQtyUom());
			poi.setSizeUom(item.getSizeUom());
			/*int quantity = 0;
			double size = 0d;
			if (item.getQuantity() != 1) {
				int sumQuantity = item.getQuantity();

				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					quantity += log.getQtyReceived();
				}
				poi.setQuantity(sumQuantity - quantity);
				poi.setSize(item.getSize());
			}
			if (item.getQuantity() == 1) {
				double sumSize = item.getSize();
				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					size = Arith.add(size, log.getSizeReceived());
					//size += log.getSizeReceived();
				}
				poi.setQuantity(item.getQuantity());
				poi.setSize(Arith.sub(sumSize, size));
			}*/
			poi.setTmp(tmp);
			poi.setOrderItemId(item.getId());
			poi.setPurchaseOrder(po);
			poi.setItemNo(item.getItemNo());
			poi.setName(item.getName());
			poi.setCatalogNo(item.getCatalogNo());
			poi.setStatus(item.getStatus());
			poi.setOrderItemId(item.getId());
			poi.setQuantityTemp(item.getQuantity());
		}
		return poi;
	}
	
	@SuppressWarnings("unchecked")
	public List<PurchaseOrderItemDTO> searchPurchaseByItemIdList(String orderItemId) {
		
		String hql = "select poi,po,ppt,o,oi from PurchaseOrderItem poi,PurchaseOrder po,PoReceivingTmp ppt ,OrderMain o,OrderItem oi"
				+ " where po.orderNo = poi.orderNo "
				+ " and ppt.poNo = po.orderNo and ppt.poLineNo = poi.itemNo "
				+ " and oi.orderNo = po.srcSoNo and poi.itemNo=oi.itemNo and o.orderNo = oi.orderNo"
				+ " and poi.id in("+orderItemId+")";
		List list = this.find(hql);
		List<PurchaseOrderItemDTO> poiList = new ArrayList<PurchaseOrderItemDTO>();
		if (list != null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++){
				PurchaseOrderItemDTO poi = new PurchaseOrderItemDTO();
				Object[] obj = (Object[]) list.get(i);
				PurchaseOrderItem item = (PurchaseOrderItem) obj[0];
				PurchaseOrder po = (PurchaseOrder) obj[1];
				PoReceivingTmp tmp  = (PoReceivingTmp) obj[2];
				OrderMain order = (OrderMain)obj[3];
				OrderItem orderItem = (OrderItem)obj[4];
				poi.setOrder(order);
				poi.setOrderItem(orderItem);
				/*List listLogs = this.getSession().createQuery(
						"from ReceivingLog where orderNo =" + item.getOrderNo()
								+ " and itemNo = " + item.getItemNo()).list();*/
				poi.setQuantity(tmp.getQty());
				poi.setSize(tmp.getSize());
				poi.setQtyUom(item.getQtyUom());
				poi.setSizeUom(item.getSizeUom());
				/*int quantity = 0;
				double size = 0d;
				if (item.getQuantity() != 1) {
					int sumQuantity = item.getQuantity();

					for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
						ReceivingLog log = (ReceivingLog) listLogs.get(j);
						quantity += log.getQtyReceived();
					}
					poi.setQuantity(sumQuantity - quantity);
					poi.setSize(item.getSize());
				}
				if (item.getQuantity() == 1) {
					double sumSize = item.getSize();
					for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
						ReceivingLog log = (ReceivingLog) listLogs.get(j);
						size = Arith.add(size, log.getSizeReceived());
						//size += log.getSizeReceived();
					}
					poi.setQuantity(item.getQuantity());
					poi.setSize(Arith.sub(sumSize, size));
				}*/
				poi.setTmp(tmp);
				poi.setOrderItemId(item.getId());
				poi.setPurchaseOrder(po);
				poi.setPorderItem(item);
				poi.setItemNo(item.getItemNo());
				poi.setName(item.getName());
				poi.setCatalogNo(item.getCatalogNo());
				poi.setStatus(item.getStatus());
				poi.setOrderItemId(item.getId());
				poi.setQuantityTemp(item.getQuantity());
				poiList.add(poi);
			}
			
		}
		return poiList;
	}

	/**
	 * 根据orderNO查询，返回一个对象
	 * 
	 * @param orderNo
	 *            参数
	 * @return PurchaseOrderItems 实体
	 */
	public PurchaseOrderItem findByNoAndItemNo(Integer orderNo, Integer itemNo) {
		PurchaseOrderItem purchaseOrderItems = null;
		String hql = "from PurchaseOrderItem where orderNo=:orderNo and itemNo=:itemNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("itemNo", itemNo);
		List<PurchaseOrderItem> list = this.find(hql, map);
		if (list != null&list.size()>0 && list.get(0) != null) {
			purchaseOrderItems = list.get(0);
		}
		return purchaseOrderItems;
	}
	/**
	 * 根据orderNo查询PurchaseOrder的Detail
	 * @param  orderNo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List findPurchaseOrderDetail(Integer orderNo) {
		String hql = "select poi,po,ppt from PurchaseOrderItem poi,PurchaseOrder po,PoReceivingTmp ppt "
			+ "where po.orderNo = poi.orderNo "
			+ " and ppt.poNo = po.orderNo and ppt.poLineNo = poi.itemNo "
			+ " and po.orderNo=:orderNo";
		/*String hql = "select poi,spl,sp.trackingNo,sp.packageId,po from PurchaseOrderItem poi,OrderMain o,ShipPackage sp,ShipPackageLines spl,PurchaseOrder po "
				+ "where o.srcPoNo = poi.orderNo and spl.orderNo = o.orderNo and spl.itemNo = poi.itemNo and po.orderNo = poi.orderNo "
				+ "and sp.packageId = spl.shipPackages.packageId and spl.status = 'Shipped' and poi.orderNo=:orderNo";*/
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		List lists = this.find(hql,map);
		List<PurchaseOrderItemDTO> listDto = new ArrayList<PurchaseOrderItemDTO>();
		PurchaseOrderItemDTO dto = null;
		PurchaseOrderItem item = null;
		PoReceivingTmp tmp = null;
		PurchaseOrder po = null;
		for (int i = 0; lists != null && i < lists.size(); i++) {
			dto = new PurchaseOrderItemDTO();
			Object[] obj = (Object[]) lists.get(i);
			item = (PurchaseOrderItem) obj[0];
			tmp = (PoReceivingTmp) obj[2];
			po = (PurchaseOrder) obj[1];
			
			/*List listLogs = this.getSession().createQuery(
					"from ReceivingLog where orderNo =" + item.getOrderNo()
							+ " and itemNo = " + item.getItemNo()
							+ " order by id ASC").list();
			int quantity = 0;
			double size = 0;
			String locationCode = "";
			if (item.getQuantity() != 1) {
				int sumQuantity = item.getQuantity();

				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					quantity += log.getQtyReceived();
					locationCode = log.getLocationCode();
				}
				dto.setQuantity(sumQuantity - quantity);
				dto.setSize(item.getSize());
			}
			if (item.getQuantity() == 1) {
				double sumSize = item.getSize();
				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					size = Arith.add(size, log.getSizeReceived());
					//size += log.getSizeReceived();
					locationCode = log.getLocationCode();
				}
				dto.setQuantity(item.getQuantity());
				dto.setSize(Arith.sub(sumSize, size));
			}*/
			dto.setTrackingNo(tmp.getTrackingNo());
			dto.setQuantity(tmp.getQty());
			dto.setSize(tmp.getSize());
			dto.setReceId(tmp.getId());
			
			dto.setOrderItemId(item.getId());
			dto.setPurchaseOrder(po);
			dto.setItemNo(item.getItemNo());
			dto.setName(item.getName());
			dto.setCatalogNo(item.getCatalogNo());
			dto.setStatus(item.getStatus());
			dto.setQtyUom(item.getQtyUom());
			dto.setSizeUom(item.getSizeUom());
			//dto.setLocationCode(locationCode);
			listDto.add(dto);
		}
		return listDto;
	}
	/**
	 * 根据orderNo 查询PurchaseOrderItems Receive All信息
	 * 
	 * @param page
	 *            分页对象
	 * @param orderNo
	 *            参数
	 * @return List 集合
	 */
	@SuppressWarnings("unchecked")
		public Page<PurchaseOrderItemDTO> searchPoIDetailAll2(Page<PurchaseOrderItem> page,String orderNo){
		Page<PurchaseOrderItemDTO> pageDto = null;
		Map<String, Object> map = new HashMap<String, Object>();
		//List<PurchaseOrderItemDTO> listDetails = new ArrayList<PurchaseOrderItemDTO>();
		String hql = "select poi,ppt,po from PurchaseOrderItem poi,PurchaseOrder po ,PoReceivingTmp ppt where po.orderNo = poi.orderNo and ppt.poNo = po.orderNo and ppt.poLineNo = poi.itemNo ";
		/*String hql = "SELECT poi,sp.trackingNo FROM PurchaseOrderItem poi,OrderMain o,ShipPackage sp,ShipPackageLines spl "
				+ "WHERE o.srcPoNo = poi.orderNo AND spl.orderNo = o.orderNo "
				+ "AND spl.itemNo = poi.itemNo AND sp.packageId = spl.shipPackages.packageId and spl.status = 'Shipped' ";*/
		if (orderNo != null) {
			hql += " and poi.orderNo in ("+orderNo+")";
		}
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		page = this.findPage(page, hql, map);
		List lists = page.getResult();

		pageDto = new Page<PurchaseOrderItemDTO>();
		List<PurchaseOrderItemDTO> listDto = new ArrayList<PurchaseOrderItemDTO>();
		PurchaseOrderItemDTO dto = null;
		PoReceivingTmp tmp = null;
		PurchaseOrderItem item = null;
		//ShipPackageLines line = null;
		PurchaseOrder po = null;
		for (int i = 0; lists != null && i < lists.size(); i++) {
			dto = new PurchaseOrderItemDTO();
			
			Object[] obj = (Object[]) lists.get(i);
			item = (PurchaseOrderItem) obj[0];
			tmp = (PoReceivingTmp) obj[1];
			po = (PurchaseOrder) obj[2];
			/*line = (ShipPackageLines) obj[1];
			po = (PurchaseOrder) obj[4];*/

			/*List listLogs = this.getSession().createQuery(
					"from ReceivingLog where orderNo =" + item.getOrderNo()
							+ " and itemNo = " + item.getItemNo()
							+ " order by id ASC").list();*/
			dto.setReceId(tmp.getId());
			dto.setReceQty(tmp.getQty());
			dto.setReceSize(tmp.getSize());
			dto.setTrackingNo(tmp.getTrackingNo());
		/*	int quantity = 0;
			double size = 0;
			String locationCode = "";
			if (item.getQuantity() != 1) {
				int sumQuantity = item.getQuantity();

				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					quantity += log.getQtyReceived();
					locationCode = log.getLocationCode();
				}
				dto.setQuantity(sumQuantity - quantity);
				dto.setSize(item.getSize());
				System.out.println(dto.getSize()+"<<<<<<<<<<<<<<<<<<<<");
			}
			if (item.getQuantity() == 1) {
				double sumSize = item.getSize();
				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					size = Arith.add(size, log.getSizeReceived());
					//size += log.getSizeReceived();
					locationCode = log.getLocationCode();
				}
				dto.setQuantity(item.getQuantity());
				System.out.println(sumSize+"   "+size);
				dto.setSize(Arith.sub(sumSize, size));
				System.out.println(dto.getSize()+">>>>>>>>>>>>>>>>>>>>>");
			}*/
			dto.setQuantity(tmp.getQty());
			dto.setSize(tmp.getSize());
			dto.setQtyUom(item.getQtyUom());
			dto.setSizeUom(item.getSizeUom());
			/*dto.setTrackingNo(obj[2]==null?"":obj[2]+"");
			dto.setPackageId(obj[3]==null?"":obj[3]+"");*/
			/*dto.setPkgLineId(line.getPkgLineId() + "");*/
			dto.setOrderItemId(item.getId());
			dto.setPurchaseOrder(po);
			dto.setItemNo(item.getItemNo());
			dto.setCatalogNo(item.getCatalogNo());
			dto.setName(item.getName());
			dto.setStatus(item.getStatus());
			dto.setQtyUom(item.getQtyUom());//(line.getQtyUom());
			dto.setSizeUom(item.getSizeUom());//(line.getSizeUom());
			//dto.setLocationCode(locationCode);
			
			listDto.add(dto);
		}
		pageDto.setResult(listDto);
		pageDto.setPageNo(page.getPageNo());
		pageDto.setPageSize(page.getPageSize());
		pageDto.setTotalCount(page.getTotalCount());
		return pageDto;
	}
	@SuppressWarnings("unchecked")
	public Page<PurchaseOrderItemDTO> searchPoIDetails2(Page<PurchaseOrderItem> page,String trackingNo){
		Page<PurchaseOrderItemDTO> pageDto = null;
		Map<String, Object> map = new HashMap<String, Object>();
		List<PurchaseOrderItemDTO> listDetails = new ArrayList<PurchaseOrderItemDTO>();
		String hql = "select poi,ppt,po from PurchaseOrderItem poi,PurchaseOrder po ,PoReceivingTmp ppt where po.orderNo = poi.orderNo and ppt.poNo = po.orderNo and ppt.poLineNo = poi.itemNo ";
		if (trackingNo != null) {
			hql += " and ppt.trackingNo ='"+trackingNo+"'";
		}
		/*String hql = "select i,s.packageId,l.pkgLineId from PurchaseOrderItem i,OrderMain o,ShipPackage s,"
			+ "ShipPackageLines l where o.srcPoNo = i.orderNo "
			+ "and l.orderNo = o.orderNo and l.itemNo = i.itemNo "
			+ "and s.packageId = l.shipPackages.packageId and l.status = 'Shipped' ";
			//+ "and s.trackingNo='" + trackingNo + "'";
		if (trackingNo != null) {
			hql += " and s.trackingNo ='"+trackingNo+"'";
		}
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}*/
		page = this.findPage(page, hql, map);
		List lists = page.getResult();
		pageDto = new Page<PurchaseOrderItemDTO>();
		PurchaseOrderItem item = null;
		PoReceivingTmp tmp = null;
		//ShipPackageLines line = null;
		
		PurchaseOrder po = null;
		for (int i = 0; lists != null && i < lists.size(); i++) {
			PurchaseOrderItemDTO poi = new PurchaseOrderItemDTO();
			Object[] obj = (Object[]) lists.get(i);
			item = (PurchaseOrderItem) obj[0];
			tmp = (PoReceivingTmp) obj[1];
			po = (PurchaseOrder) obj[2];
			/*line = (ShipPackageLines) obj[1];
			po = (PurchaseOrder) obj[4];*/
			
			poi.setReceId(tmp.getId());
			poi.setReceQty(tmp.getQty());
			poi.setReceSize(tmp.getSize());
			poi.setTrackingNo(tmp.getTrackingNo());
			/*List listLogs = this.getSession().createQuery(
					"from ReceivingLog where orderNo =" + item.getOrderNo()
							+ " and itemNo = " + item.getItemNo()
							+ " order by id ASC").list();*/
			/*int quantity = 0;
			double size = 0d;
			String locationCode = "";
			
			if (item.getQuantity() != 1) {
				int sumQuantity = item.getQuantity();

				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					quantity += log.getQtyReceived();
					locationCode = log.getLocationCode();
				}
				poi.setQuantity(sumQuantity - quantity);
				poi.setSize(item.getSize());
			}
			if (item.getQuantity() == 1) {
				double sumSize = item.getSize();
				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					size = Arith.add(size, log.getSizeReceived());
					//size += log.getSizeReceived();
					locationCode = log.getLocationCode();
				}
				poi.setQuantity(item.getQuantity());
				poi.setSize(Arith.sub(sumSize, size));
			}*/
			poi.setQuantity(tmp.getQty());
			poi.setSize(tmp.getSize());
			poi.setQtyUom(item.getQtyUom());
			poi.setSizeUom(item.getSizeUom());
			po.setOrderNo(item.getOrderNo());
			poi.setPurchaseOrder(po);
			poi.setItemNo(item.getItemNo());
			poi.setName(item.getName());
			poi.setQtyUom(item.getQtyUom());
			poi.setCatalogNo(item.getCatalogNo());
			poi.setStatus(item.getStatus());
			poi.setOrderItemId(item.getId());
			poi.setTrackingNo(trackingNo==null?"":trackingNo);
			//poi.setLocationCode(locationCode);
			listDetails.add(poi);
		}
		pageDto.setResult(listDetails);
		pageDto.setPageNo(page.getPageNo());
		pageDto.setPageSize(page.getPageSize());
		pageDto.setTotalCount(page.getTotalCount());
		return pageDto;
	}
	
	public List<PurchaseOrderItem> searchPurchaseOrderItem (Integer orderNo,Integer itemNo){
		String hql = "from PurchaseOrderItem p where p.orderNo="+orderNo+" and itemNo="+itemNo;
		return this.find(hql);
	}
	
	/*
	 * 根据orderNo 获取可打印Item
	 */
	public List<PurchaseOrderItem> searchPurchaseOrderItemOfPrint(Integer orderNo){
		String hql = "from PurchaseOrderItem p where p.orderNo = "+orderNo+" and p.fileDownloaded = 1";
		return this.find(hql);
	}
	
	public void updateOrderItemFileDownloaded(String orderItems ,String value){
		String hql="update PurchaseOrderItem set fileDownloaded='"+value+"' where id in ("+orderItems+")";
		this.getSession().createQuery(hql).executeUpdate();
	}
}
