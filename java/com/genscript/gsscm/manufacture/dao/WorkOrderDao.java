package com.genscript.gsscm.manufacture.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.WorkOrderStatus;
import com.genscript.gsscm.common.util.Arith;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.inventory.entity.ReceivingLog;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.manufacture.dto.WorkOrderDTO;
import com.genscript.gsscm.manufacture.entity.WoBatche;
import com.genscript.gsscm.manufacture.entity.WorkGroup;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.privilege.entity.User;

@Repository
public class WorkOrderDao extends HibernateDao<WorkOrder, Integer> {

	/**
	 * 查找QC的结果页， qcGroup和qaGroup, qcClerk和qaClerk是或的查询关系
	 * @serialData 2010-12-02
	 * @author wangsf
	 * @param page
	 * @param filters
	 * @param batchNo
	 * @return
	 */
	public Page<WorkOrder> findPageByQC(final Page<WorkOrder> page,
			final List<PropertyFilter> filters, List<Integer> orderNoList,List<Integer> centerIdList,Set<Integer> soNoSet,List<Integer> workOrderNoList,String batchFunction) throws Exception{
		List<Criterion> criterionList = new ArrayList<Criterion>();
		// 判断是否有或的查询条件.
		List<PropertyFilter> delList = new ArrayList<PropertyFilter>();
		int len = filters.size();
		boolean isFilteStatus = false;
		for (int i = 0; i < len; i++) {
			PropertyFilter pf = filters.get(i);
			if (pf.getPropertyName().equals("qcGroup")) {
				Criterion criterionGroup = Restrictions.in("qcGroup", (List<Integer>)(pf.getPropertyValue()));
				Criterion criterionQa = Restrictions.in("qaGroup", (List<Integer>)(pf.getPropertyValue()));
				Criterion criterion = Restrictions.or(criterionGroup,
						criterionQa);
				criterionList.add(criterion);
				delList.add(pf);
			} else if (pf.getPropertyName().equals("qcClerk")) {
				Criterion criterionGroup = Restrictions.eq("qcClerk", Integer
						.parseInt(pf.getPropertyValue().toString()));
				Criterion criterionQa = Restrictions.eq("qaClerk", Integer
						.parseInt(pf.getPropertyValue().toString()));
				Criterion criterion = Restrictions.or(criterionGroup,
						criterionQa);
				criterionList.add(criterion);
				delList.add(pf);
			} else if(pf.getPropertyName().equals("status")) {
				isFilteStatus = true;
			} else if(pf.getPropertyName().equals("srcSoNo")) {
				delList.add(pf);
			} else if(pf.getPropertyName().equals("startOrderDate")) {
				Criterion criterionStartOrderDate = Restrictions.ge("orderDate", DateUtils.formatStr2Date(String.valueOf(pf.getPropertyValue()), "yyyy-MM-dd"));
				criterionList.add(criterionStartOrderDate);
				delList.add(pf);
			} else if(pf.getPropertyName().equals("endOrderDate")) {
				Criterion criterionEndOrderDate = Restrictions.le("orderDate", DateUtils.formatStr2Date(String.valueOf(pf.getPropertyValue()), "yyyy-MM-dd"));
				criterionList.add(criterionEndOrderDate);
				delList.add(pf);
			}
		}
		
		List<String> statusList = new ArrayList<String>();
		if(!isFilteStatus&&batchFunction!=null) {
			if("QA".equals(batchFunction)) {
				//statusList.add(WorkOrderStatus.Closed.value());
				statusList.add(WorkOrderStatus.Completed.value());
				statusList.add(WorkOrderStatus.DocumentQCFailed.value());
				statusList.add(WorkOrderStatus.ProductQCFailed.value());
				statusList.add(WorkOrderStatus.DocumentQCPassed.value());
				statusList.add(WorkOrderStatus.ProductQCPassed.value());
				statusList.add(WorkOrderStatus.ProductQCPartial.value());
				statusList.add(WorkOrderStatus.DocumentQCPartial.value());
				statusList.add(WorkOrderStatus.QCPartial.value());
				statusList.add(WorkOrderStatus.QCFailed.value());
				Criterion criterionDnaWorkOrderNo = Restrictions.in("orderNo", workOrderNoList);
				Criterion notCriterionDnaWorkOrderNo = Restrictions.not(criterionDnaWorkOrderNo);
				criterionList.add(notCriterionDnaWorkOrderNo);
			}
			else if(!isFilteStatus&&batchFunction!=null&&"QC".equals(batchFunction)) {
				statusList.add(WorkOrderStatus.New.value());
				statusList.add(WorkOrderStatus.Inprogress.value());
				//statusList.add(WorkOrderStatus.Canceled.value());
				Criterion criterionItemType = Restrictions.eq("itemType", QuoteItemType.PRODUCT.value());
				Criterion criterionNeClsId = Restrictions.ne("clsId",40);
				Criterion criterionEqClsId = Restrictions.eq("clsId",40);
				Criterion filterSevice = Restrictions.and(criterionEqClsId, criterionItemType);
				Criterion filterDna = Restrictions.or(filterSevice, criterionNeClsId);
				criterionList.add(filterDna);
			}
			else if(!isFilteStatus&&batchFunction!=null&&"QD".equals(batchFunction)) {
				statusList.add(WorkOrderStatus.New.value());
			}
			Criterion criterion1 = Restrictions.in("status",statusList);
			criterionList.add(criterion1);
		} 

		filters.removeAll(delList);// 删除原先的条件.
		//如果有限制orderNo, (根据batchNo查出来的)
		if (orderNoList != null && !orderNoList.isEmpty()) {
			Criterion criterion = Restrictions.in("orderNo", orderNoList);
			criterionList.add(criterion);
		}
		if(soNoSet!=null&&soNoSet.size()>0) {
			Criterion criterion = Restrictions.in("soNo", soNoSet);
			criterionList.add(criterion);
		}
		if(centerIdList!=null&&!centerIdList.isEmpty()) {
			Criterion criterion = Restrictions.in("workCenterId", centerIdList);
			criterionList.add(criterion);
		}
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		for (Criterion temp : criterions) {
			criterionList.add(temp);
		}
		return findPage(page, criterionList.toArray(new Criterion[criterionList
				.size()]));
	}

	/**
	 * 通过soNo和soItemNo查找对象
	 * 
	 * @param soNo
	 *            订单号
	 * @param soItemNo
	 *            订单项目号
	 */
	public List<WorkOrder> findBySNAndSIN(Integer soNo, Integer soItemNo) {
		String hql = "from WorkOrder where soNo=? and soItemNo=? order by creationDate desc";
		return this.find(hql, soNo, soItemNo);
	}
	
	public List<WorkOrder> findByPO(Integer soNO, Integer soItemNo){
		String hql ="select wo from WorkOrder wo,MfgOrder mo where wo.soNo=mo.orderNo and mo.srcSoNo = "+soNO +" and wo.soItemNo="+soItemNo;
		return this.find(hql);
	}
	
	public WorkOrder findLastByPo(Integer soNO, Integer soItemNo) {
		String hql ="select wo from WorkOrder wo,MfgOrder mo where wo.soNo=mo.orderNo and mo.srcSoNo = "+soNO +" and wo.soItemNo="+soItemNo+" order by wo.creationDate desc";
		Query query = this.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(1);
		List<WorkOrder> list = query.list();
		return list!=null&&list.size()>0?list.get(0):null;
	}

	/**
	 * workOrder 分页
	 * 
	 * @param page
	 *            page对象
	 * @param filters
	 * @return
	 */
	public Page<WorkOrder> searchWorkOrder(Page<WorkOrder> page,
			List<PropertyFilter> filters) {
		return findPage(page, filters);
	}

	/**
	 * workOrder 根据条件分页查询
	 * 
	 * @param page
	 *            page分页对象
	 * @param srch
	 *            实体对象
	 * @return page page结果
	 */
	@SuppressWarnings("unchecked")
	public Page<WorkOrderDTO> searchwo(Page<WorkOrder> page, WorkOrderDTO srch) {
		if (srch == null) {
			srch = new WorkOrderDTO();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "select wo,wob.batchNo,wob.woBatchId,wh.warehouseId,wh.name ";
		//mod by lizhang 由于wo和workGroup是多对多关系
//		if (srch.getWorkGroupName() != null
//				&& srch.getWorkGroupName().length() > 0) {
//			hql = hql + ",wg.name ";
//		}
		hql = hql
				+ "from WorkOrder wo,WoBatche wob,WoBatchDetail wobd,Warehouse wh where wo.orderNo = wobd.workOrders.orderNo and wh.warehouseId = wo.warehouseId and wob.woBatchId = wobd.woBatche.woBatchId and wob.status = 'Delivered' and wo.status<>'Received'";
		//mod by lizhang 由于wo和workGroup是多对多关系
		// 判断根据条件查询
//		if (srch.getWorkGroupName() != null
//				&& srch.getWorkGroupName().length() > 0) {
//			hql += ",WorkGroup wg and wg.id = wo.workGroupId and wg.name=:name";
//			map.put("name", srch.getWorkGroupName());
//		}
		// orderNo
		if (srch.getOrderNo() != null && srch.getOrderNo().intValue() != 0) {
			hql += " and wo.orderNo=:orderNo";
			map.put("orderNo", srch.getOrderNo());
		}

		if (srch.getOrderNo2() != null && srch.getOrderNo2().intValue() != 0) {
			hql += " and wo.orderNo=:orderNo";
			map.put("orderNo", srch.getOrderNo2());
		}
		// status
		String status="";
		if (srch.getStatus() != null && srch.getStatus().trim().length() > 0) {
			if(srch.getStatus().indexOf(", ")> -1){
				if(srch.getStatus().equals(", ")){
					hql +=" ";
				}else{
					status = srch.getStatus().substring(2, srch.getStatus().length());
					hql += " and wo.status=:status";
					map.put("status", status);
				}
			}else{
				hql += " and wo.status=:status";
				map.put("status", srch.getStatus());
			}
		}
		// order By
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		page = this.findPage(page, hql, map);
		// 获取查询结果
		List lists = page.getResult();
		List<WorkOrderDTO> listDto = new ArrayList<WorkOrderDTO>();
		Page<WorkOrderDTO> pageDto = new Page<WorkOrderDTO>();
		WorkOrderDTO dto = null;
		WorkOrder order = null;
		// for循环
		for (int i = 0; i < lists.size(); i++) {
			dto = new WorkOrderDTO();
			Object[] obj = (Object[]) lists.get(i);
			// 获取WorkOrder
			order = (WorkOrder) obj[0];
			// 获取batchNo
			String batchNo = obj[1] + "";
			Integer warehouseId = (Integer) obj[3];
			String name = obj[4] + "";
			// 查询ReceivingLogs表中qty和size和wo中qty和size比较
			List listLogs = this.getSession().createQuery(
					"from ReceivingLog where trackingNo='" + batchNo
							+ "' and orderNo =" + order.getOrderNo()
							+ " order by id ASC").list();
			int quantity = 0;
			double size = 0;
			String locationCode = "";
			// qty和size累加
			if (order.getQuantity() != 1) {
				int sumQuantity = order.getQuantity();

				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					quantity += log.getQtyReceived();
					locationCode = log.getLocationCode();
				}
				dto.setQuantity(sumQuantity - quantity);
				dto.setSize(order.getSize());
			}
			if (order.getQuantity() == 1) {
				double sumSize = order.getSize();
				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					size = Arith.add(size, log.getSizeReceived());
					//size += log.getSizeReceived();
					locationCode = log.getLocationCode();
				}
				dto.setQuantity(order.getQuantity());
				dto.setSize(Arith.sub(sumSize, size));
				System.out.println(dto.getSize()+">>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
//			String workGroupName = "";
//			if (order.getWorkGroupId() != null && order.getWorkGroupId() > 0) {
//				WorkGroup wg = this.getWorkGroupById(order.getWorkGroupId());
//				workGroupName = wg.getName();
//			}
			// 重新赋值
			//dto.setBatchNo(obj[1] + "");
			dto.setBatchNo(obj[1]==null?"":obj[1]+"");
			dto.setWoBatchId(obj[2]==null?"":obj[2]+"");
			//dto.setWoBatchId(obj[2]+"");
			dto.setOrderNo(order.getOrderNo());
			dto.setCatalogNo(order.getCatalogNo());
			dto.setQtyUom(order.getQtyUom());
			dto.setSizeUom(order.getSizeUom());
			dto.setStatus(order.getStatus());
//			dto.setWorkGroupName(workGroupName);
			dto.setSoNo(order.getSoNo());
			dto.setType(order.getType());
			dto.setOrderDate(order.getOrderDate());
			dto.setExprDate(order.getExprDate());
			dto.setCatalogNo(order.getCatalogNo());
			dto.setWarehouseId(warehouseId);
			dto.setName(name);
			dto.setLocationCode(locationCode);
			// 放到list集合
			listDto.add(dto);
		}
		pageDto.setResult(listDto);
		// 重新指定pageNo，pageSize，TotalCount
		pageDto.setPageNo(page.getPageNo());
		pageDto.setPageSize(page.getPageSize());
		pageDto.setTotalCount(page.getTotalCount());
		return pageDto;
	}

	/**
	 * 根据type查询warehouse
	 * 
	 * @param warehouseId
	 *            参数
	 * @return Warehouse 实体对象
	 */
	public Warehouse findByType(String warehouseId) {
		String hql = "from Warehouse where type = ?";
		return this.findUnique(hql, warehouseId);
	}

	/**
	 * 根据条件查询用户
	 * 
	 * @return List 集合
	 */
	public List<User> getListByReceiving(Integer userId) {
		String hql = "select user from ShipClerk clerk,User user " +
		" where clerk.clerkFunction in ('Receiver', 'All') and user.userId = clerk.clerkId";
		if(userId!=null){
			hql +=" and user.userId="+userId;
		}
		//String hql = "select u from User u,UserRole a,Role r where u.userId=a.user.userId and r.roleId=a.role.roleId and r.roleName = 'Receiving Clerk'";
		// String hql = "select * from shipping.shipping_clerk_assigned,
		// shipping.shipping_clerks where shipping_clerk_assigned.shipping_clerk
		// = shipping_clerks.clerk_id and shipping_clerks.clerk_function in
		// ('Receiver', 'All')";
		return this.find(hql);
	}

	/**
	 * 查询batchId
	 * 
	 * @return List 集合
	 */
	@SuppressWarnings("unchecked")
	public List<WoBatche> getListBatchNo() {
		String hql = "select wob from WorkOrder wo,WoBatche wob,WoBatchDetail wobd where wo.orderNo = wobd.workOrders.orderNo and wob.woBatchId = wobd.woBatche.woBatchId and wo.status = 'Delivered' and wob.status<>'Received' group by wob.batchNo";
		List<WoBatche> list = this.getSession().createQuery(hql).list();
		return list;
	}

	/**
	 * WorkOrder Receive
	 * 
	 * @param orderNo
	 *            参数
	 * @return List 集合
	 */
	@SuppressWarnings("unchecked")
	public List searchWoIDetail(Integer orderNo) {
		List<WorkOrderDTO> listDto = new ArrayList<WorkOrderDTO>();
		String hql = "select wo,wob.batchNo from WorkOrder wo,WoBatche wob,WoBatchDetail wobd where wo.orderNo = wobd.workOrders.orderNo and wob.woBatchId = wobd.woBatche.woBatchId and wob.status = 'Delivered' and wo.orderNo = ? ";
		List list = this.find(hql, orderNo);
		WorkOrderDTO dto = null;
		WorkOrder item = null;
		for (int i = 0; list != null && i < list.size(); i++) {
			dto = new WorkOrderDTO();
			Object[] obj = (Object[]) list.get(i);
			item = (WorkOrder) obj[0];
			String batchNo = obj[1] + "";
			List listLogs = this.getSession().createQuery(
					"from ReceivingLog where orderNo =" + item.getOrderNo()
							+ " and trackingNo = '" + batchNo
							+ "' order by id ASC").list();
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
			}
			// 重新赋值
			dto.setBatchNo(obj[1]==null?"":obj[1]+"");
			dto.setOrderNo(item.getOrderNo());
			dto.setSoItemNo(item.getSoItemNo());
			dto.setCatalogNo(item.getCatalogNo());
			dto.setQtyUom(item.getQtyUom());
			dto.setSizeUom(item.getSizeUom());
			dto.setLocationCode(locationCode);
			listDto.add(dto);
		}
		return listDto;
	}

	/**
	 * WorkOrder Receive All
	 * 
	 * @param page
	 *            分页对象
	 * @param orderNo
	 *            参数
	 * @return list 集合
	 */
	@SuppressWarnings("unchecked")
	public List searchwoIDetailAll(String[] orderNo) {
		List list2 = new ArrayList();
		for (int i = 0; i < orderNo.length; i++) {
			Integer orderNos = Integer.parseInt(orderNo[i]);
			List list = this.searchWoIDetail(orderNos);
			for (int j = 0; j < list.size(); j++) {
				list2.add(list.get(j));
			}
		}
		return list2;
	}

	/**
	 * 查询workOrder状态
	 * 
	 * @return List 集合
	 */
	public List<WorkOrder> getWoStatus() {
		String hql = "select w.status from WorkOrder w group by w.status";
		return this.find(hql);
	}

	/**
	 * 根据orderNo查询，返回一个workOrder
	 * 
	 * @param orderNo
	 *            参数
	 * @return workorder 实体对象
	 */
	@SuppressWarnings("unchecked")
	public WorkOrderDTO searchWoByOrderNo(Integer orderNo, String batchNo) {
		String hql = "select wo,wh.warehouseId,wh.name from WorkOrder wo,Warehouse wh where wo.warehouseId = wh.warehouseId and wo.orderNo = ?";
		WorkOrderDTO pbSearch = new WorkOrderDTO();
		List retList = this.find(hql, orderNo);
		if (retList != null) {
			Object[] obj = (Object[]) retList.get(0);
			WorkOrder item = (WorkOrder) obj[0];
			Integer warehouseId = (Integer) obj[1];
			String name = obj[2] + "";
			// 查询ReceivingLogs的qty和size和wo比较
			List listLogs = this.getSession().createQuery(
					"from ReceivingLog where orderNo =" + item.getOrderNo()
							+ " and trackingNo = '" + batchNo
							+ "' order by id ASC").list();
			int quantity = 0;
			double size = 0d;
			if (item.getQuantity() != 1) {
				int sumQuantity = item.getQuantity();

				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					quantity += log.getQtyReceived();
				}
				pbSearch.setQuantity(sumQuantity - quantity);
				pbSearch.setSize(item.getSize());
			}
			if (item.getQuantity() == 1) {
				double sumSize = item.getSize();
				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					size = Arith.add(size, log.getSizeReceived());
					//size += log.getSizeReceived();
				}
				pbSearch.setQuantity(item.getQuantity());
				pbSearch.setSize(Arith.sub(sumSize, size));
			}

			// 重新赋值
			pbSearch.setActualEnd(item.getActualEnd());
			pbSearch.setAltOrderNo(item.getAltOrderNo());
			pbSearch.setCatalogNo(item.getCatalogNo());
			pbSearch.setOrderNo(item.getOrderNo());
			pbSearch.setQtyUom(item.getQtyUom());
			pbSearch.setSizeUom(item.getSizeUom());
			pbSearch.setSoItemNo(item.getSoItemNo());
			pbSearch.setStatus(item.getStatus());
			pbSearch.setStorageLocation(item.getStorageLocation());
			pbSearch.setWarehouseId(warehouseId);
			pbSearch.setName(name);
		}
		return pbSearch;
	}

	/**
	 * workOrder Receive
	 * 
	 * @param batchNo
	 *            参数
	 * @return List 集合
	 */
	@SuppressWarnings("unchecked")
	public List searchWoByBatchNo(String batchNo) {
		List lists = null;
		List<WorkOrderDTO> listDto = new ArrayList<WorkOrderDTO>();
		String hql = "select w,wb.batchNo,wb.woBatchId from WorkOrder w ,WoBatche wb,WoBatchDetail d "
				+ "where w.orderNo=d.workOrders.orderNo and wb.woBatchId = d.woBatche.woBatchId "
				+ "and w.status = 'Delivered' and wb.batchNo = ?";
		lists = this.find(hql, batchNo);
		WorkOrderDTO dto = null;
		WorkOrder order = null;
		// for循环
		for (int i = 0; i < lists.size(); i++) {
			dto = new WorkOrderDTO();
			Object[] obj = (Object[]) lists.get(i);
			order = (WorkOrder) obj[0];
			// ReceivingLogs表查询qty和size
			List listLogs = this.getSession().createQuery(
					"from ReceivingLog where orderNo =" + order.getOrderNo()
							+ " and trackingNo = '" + batchNo
							+ "' order by id ASC").list();
			int quantity = 0;
			double size = 0d;
			String locationCode = "";
			if (order.getQuantity() != 1) {
				int sumQuantity = order.getQuantity();

				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					quantity += log.getQtyReceived();
					locationCode = log.getLocationCode();
				}
				dto.setQuantity(sumQuantity - quantity);
				dto.setSize(order.getSize());
			}
			if (order.getQuantity() == 1) {
				double sumSize = order.getSize();
				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					size = Arith.add(size, log.getSizeReceived());
					//size += log.getSizeReceived();
					locationCode = log.getLocationCode();
				}
				dto.setQuantity(order.getQuantity());
				dto.setSize(Arith.sub(sumSize, size));
			}
			// 重新赋值
			//dto.setBatchNo(obj[1] + "");
			dto.setBatchNo(obj[1]==null?"":obj[1]+"");
			dto.setWoBatchId(obj[2]==null?"":obj[2]+"");
			//dto.setWoBatchId(obj[2]+"");
			dto.setOrderNo(order.getOrderNo());
			dto.setSoItemNo(order.getSoItemNo());
			dto.setCatalogNo(order.getCatalogNo());
			dto.setQtyUom(order.getQtyUom());
			dto.setSizeUom(order.getSizeUom());
			dto.setLocationCode(locationCode);
			listDto.add(dto);
		}
		return listDto;
	}

	/**
	 * 根据orderNo查询workorder，返回一个list
	 * 
	 * @param orderNo
	 *            参数
	 * @return List 集合
	 */
	public List<WorkOrder> getListWo(Integer orderNo) {
		String hql = "from WorkOrder where orderNo = ?";
		return this.find(hql, orderNo);
	}

	/**
	 * 查询Warehouse，返回一个list
	 * 
	 * @return List 集合
	 */
	public List<Warehouse> getListByWarehouse() {
		String hql = "from Warehouse";
		return this.find(hql);
	}

	/**
	 * 更新WorkOrder的status状态
	 * 
	 * @param orderNo
	 *            orderNo
	 * @param status
	 *            状态
	 */
	public void updateWorkOrderStatus(Integer orderNo, String status) {
		String hql = "update WorkOrder set status=?  where orderNo=?";
		this.batchExecute(hql, status, orderNo);
	}
	
	/**
	 * 当WorkOrder关闭时执行动作(更新actualEnd).
	 * @param orderNo
	 */
	public void closeWorkOrder(Integer orderNo) {
		String hql = "update WorkOrder set actualEnd=?  where orderNo=?";
		this.batchExecute(hql, new Date(), orderNo);
	}

	/**
	 * 更新wo_batch的status状态
	 * 
	 * @param id
	 *            woBatchId
	 * @param status
	 *            状态
	 */
	public void updateWorkBatchStatus(Integer id, String status) {
		String hql = "update WoBatche set status=?  where woBatchId=?";
		this.batchExecute(hql, status, id);
	}

	/**
	 * 根据orderNo和itemNo查询，返回一个list集合
	 * 
	 * @param batchNo
	 *            参数
	 * @return list 集合
	 */
	@SuppressWarnings("unchecked")
	public List findWoBatchIdDetailss(String batchNo) {
		String sql = "SELECT wb.wo_batch_id,wb.status,count(wo.order_no) FROM manufacturing.work_orders wo,manufacturing.wo_batches wb,manufacturing.wo_batch_detail wbd WHERE wo.order_no = wbd.order_no AND wbd.wo_batch_id = wb.wo_batch_id  AND wo.status <> 'Received'  and wb.status <> 'Received' and wb.batch_no =:batchNo  group by wb.wo_batch_id having COUNT(wo.order_no) = 0";
		List list = this.getSession().createSQLQuery(sql).setString("batchNo",
				batchNo).list();
		return list;
	}

	/**
	 * 查询到的order_no的状态将被更新
	 * 
	 * @param orderNo
	 *            参数
	 * @return list 集合
	 */
	@SuppressWarnings("unchecked")
	public List findWoBatchDetails(String orderNo) {
		String sql = "select wo.order_no,wo.status from manufacturing.work_orders wo "
				+ "where wo.quantity-(select sum(r.qty_received) from inventory.receiving_logs r where r.order_no = wo.order_no) <= 0 "
				+ "and wo.size-(select sum(r.size_received) from inventory.receiving_logs r where r.order_no = wo.order_no) <=0 "
				+ "and wo.status <> 'Shipped' and wo.order_no in ("
				+ orderNo
				+ ")";
		List list = this.getSession().createSQLQuery(sql).list();
		return list;
	}
	
	/**
	 * 根据orderNo查询WorkOrder的Detail
	 * @param  orderNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findWorkOrderDetail(Integer orderNo) {
		String hql = "select wo,wob.batchNo,wob.woBatchId,wh.warehouseId,wh.name from " +
				"WorkOrder wo,WoBatche wob,WoBatchDetail wobd,Warehouse wh " +
				"where wo.orderNo = wobd.workOrders.orderNo and wh.warehouseId = wo.warehouseId " +
				"and wob.woBatchId = wobd.woBatche.woBatchId and wob.status = 'Delivered' " +
				"and wo.status<>'Received' and wo.orderNo=:orderNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		List lists = this.find(hql,map);
		List<WorkOrderDTO> listDto = new ArrayList<WorkOrderDTO>();
		WorkOrderDTO dto = null;
		WorkOrder order = null;
		//for循环
		for (int i = 0; i < lists.size(); i++) {
			dto = new WorkOrderDTO();
			Object[] obj = (Object[]) lists.get(i);
			//获取WorkOrder
			order = (WorkOrder) obj[0];
			//获取batchNo
			String batchNo = obj[1]+"";
			Integer warehouseId = (Integer)obj[3];
			String name = obj[4]+"";
			//查询ReceivingLogs表中qty和size和wo中qty和size比较
			List listLogs = this.getSession().createQuery("from ReceivingLog where trackingNo='"+batchNo+"' and orderNo ="+order.getOrderNo()+" order by id ASC").list();
			int quantity = 0;
			double size = 0;
			String locationCode ="";
			//qty和size累加
			if (order.getQuantity() != 1) {
				int sumQuantity = order.getQuantity();

				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					quantity += log.getQtyReceived();
					locationCode = log.getLocationCode();
				}
				dto.setQuantity(sumQuantity - quantity);
				dto.setSize(order.getSize());
			}
			if (order.getQuantity() == 1) {
				double sumSize = order.getSize();
				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					size = Arith.add(size, log.getSizeReceived());
					//size += log.getSizeReceived();
					locationCode = log.getLocationCode();
				}
				dto.setQuantity(order.getQuantity());
				dto.setSize(Arith.sub(sumSize, size));
			}
//			String workGroupName="";
//			if(order.getWorkGroupId()!=null && order.getWorkGroupId()>0){
//				WorkGroup wg = this.getWorkGroupById(order.getWorkGroupId());
//				workGroupName = wg.getName();
//			}
			//重新赋值
			//dto.setBatchNo(obj[1] + "");
			dto.setBatchNo(obj[1]==null?"":obj[1]+"");
			dto.setWoBatchId(obj[2]==null?"":obj[2]+"");
			//dto.setWoBatchId(obj[2]+"");
			dto.setOrderNo(order.getOrderNo());
			dto.setCatalogNo(order.getCatalogNo());
			dto.setQtyUom(order.getQtyUom());
			dto.setSizeUom(order.getSizeUom());
			dto.setStatus(order.getStatus());
//			dto.setWorkGroupName(workGroupName);
			dto.setSoNo(order.getSoNo());
			dto.setType(order.getType());
			dto.setOrderDate(order.getOrderDate());
			dto.setExprDate(order.getExprDate());
			dto.setCatalogNo(order.getCatalogNo());
			dto.setWarehouseId(warehouseId);
			dto.setName(name);
			dto.setLocationCode(locationCode);
			//放到list集合
			listDto.add(dto);
		}
		return listDto;
	}

	/**
	 * 根据id查询WorkGrop
	 */
	@SuppressWarnings("unchecked")
	public WorkGroup getWorkGroupById(Integer id) {
		String hql = "from WorkGroup where id = :id";
		List<WorkGroup> list = this.getSession().createQuery(hql).setInteger(
				"id", id).list();
		WorkGroup workGroup = null;
		if (list != null && list.size() > 0) {
			workGroup = (WorkGroup) list.get(0);
		}
		return workGroup;
	}
	
	/**
	 * 批量更新status.
	 * @param orderNoList id list
	 * @param status 更新后的状态
	 */
	public void batchUpdateStatus(Object orderNoList, String status){
		String hql = "update WorkOrder c set c.status='" + status + "' where c.orderNo in (:orderNoList)";
		Map<String,Object> map = Collections.singletonMap("orderNoList", orderNoList);
		batchExecute(hql, map);
	}
	
	/**
	 * 批量更新QCstatus.
	 * @param orderNoList id list
	 */
	public void batchUpdateQCStatus(Object orderNoList, String goodsQcStatus,String docQcStatus){
		StringBuffer hqlBuff = new StringBuffer("update WorkOrder c set ");
		if(StringUtils.isNotEmpty(goodsQcStatus)) {
			hqlBuff.append("c.productQc='").append(goodsQcStatus).append("'");
		} else {
			hqlBuff.append("c.productQc=null");
		}
		if(StringUtils.isNotEmpty(docQcStatus)) {
			hqlBuff.append(",c.documentQc='").append(docQcStatus).append("'");
		} else {
			hqlBuff.append(",c.documentQc=null");
		}
		hqlBuff.append(" where c.orderNo in (:orderNoList)");
		Map<String,Object> map = Collections.singletonMap("orderNoList", orderNoList);
		batchExecute(hqlBuff.toString(), map);
	}
	
	/**
	 * WorkOrder Receive 2
	 * 
	 * @param page
	 *            分页对象
	 * @param orderNo
	 *            参数
	 * @return List 集合
	 */
	@SuppressWarnings("unchecked")
		public Page<WorkOrderDTO> searchPoIDetailAll2(Page<WorkOrder> page,String orderNo){
		Page<WorkOrderDTO> pageDto = null;
		Map<String, Object> map = new HashMap<String, Object>();
		List<WorkOrderDTO> listDetails = new ArrayList<WorkOrderDTO>();
		String hql = "select wo,wob.batchNo from WorkOrder wo,WoBatche wob,WoBatchDetail wobd " +
				"where wo.orderNo = wobd.workOrders.orderNo and wob.woBatchId = wobd.woBatche.woBatchId " +
				"and wob.status = 'Delivered' ";
		if (orderNo != null) {
			hql += " and wo.orderNo in ("+orderNo+")";
		}
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		page = this.findPage(page, hql, map);
		List lists = page.getResult();
		pageDto = new Page<WorkOrderDTO>();
		WorkOrderDTO dto = null;
		WorkOrder item = null;
		for (int i = 0; lists != null && i < lists.size(); i++) {
			dto = new WorkOrderDTO();
			Object[] obj = (Object[])lists.get(i);
			item = (WorkOrder)obj[0];
			String batchNo = obj[1]+"";
			List listLogs = this.getSession().createQuery(
					"from ReceivingLog where orderNo ="
							+ item.getOrderNo()
							+ " and trackingNo = '" + batchNo+"' order by id ASC").list();
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
			}
			//重新赋值
			dto.setBatchNo(obj[1] + "");
			dto.setOrderNo(item.getOrderNo());
			dto.setSoItemNo(item.getSoItemNo());
			dto.setCatalogNo(item.getCatalogNo());
			dto.setQtyUom(item.getQtyUom());
			dto.setSizeUom(item.getSizeUom());
			dto.setLocationCode(locationCode);
			listDetails.add(dto);
		}
		pageDto.setResult(listDetails);
		pageDto.setPageNo(page.getPageNo());
		pageDto.setPageSize(page.getPageSize());
		pageDto.setTotalCount(page.getTotalCount());
		return pageDto;
	}
	
	/**
	 * WorkOrder Receive 2
	 * @param page    分页对象
	 * @param orderNo 参数
	 * @return Page   
	 */
	@SuppressWarnings("unchecked")
		public Page<WorkOrderDTO> searchWoByBatchNo2(Page<WorkOrder> page,String batchNo){
		Page<WorkOrderDTO> pageDto = null;
		Map<String, Object> map = new HashMap<String, Object>();
		List<WorkOrderDTO> listDetails = new ArrayList<WorkOrderDTO>();
		String hql = "select w,wb.batchNo,wb.woBatchId from WorkOrder w ,WoBatche wb,WoBatchDetail d " +
		"where w.orderNo=d.workOrders.orderNo and wb.woBatchId = d.woBatche.woBatchId " +
		"and w.status = 'Delivered' ";
		if (batchNo != null) {
			hql += " and wb.batchNo ='"+batchNo+"'";
		}
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		page = this.findPage(page, hql, map);
		List lists = page.getResult();
		pageDto = new Page<WorkOrderDTO>();
		WorkOrderDTO dto = null;
		WorkOrder order = null;
		//for循环
		for (int i = 0; i < lists.size(); i++) {
			dto = new WorkOrderDTO();
			Object[] obj = (Object[]) lists.get(i);
			order = (WorkOrder) obj[0];
			//ReceivingLogs表查询qty和size
			List listLogs = this.getSession().createQuery(
					"from ReceivingLog where orderNo ="
							+ order.getOrderNo() 
							+ " and trackingNo = '" + batchNo+"' order by id ASC").list();
			int quantity = 0;
			double size = 0d;
			String locationCode = "";
			if (order.getQuantity() != 1) {
				int sumQuantity = order.getQuantity();

				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					quantity += log.getQtyReceived();
					locationCode = log.getLocationCode();
				}
				dto.setQuantity(sumQuantity - quantity);
				dto.setSize(order.getSize());
			}
			if (order.getQuantity() == 1) {
				double sumSize = order.getSize();
				for (int j = 0; listLogs != null && j < listLogs.size(); j++) {
					ReceivingLog log = (ReceivingLog) listLogs.get(j);
					size = Arith.add(size, log.getSizeReceived());
					//size += log.getSizeReceived();
					locationCode = log.getLocationCode();
				}
				dto.setQuantity(order.getQuantity());
				dto.setSize(Arith.sub(sumSize, size));
			}
			//重新赋值
			dto.setBatchNo(obj[1] + "");
			dto.setWoBatchId(obj[2]+"");
			dto.setOrderNo(order.getOrderNo());
			dto.setSoItemNo(order.getSoItemNo());
			dto.setCatalogNo(order.getCatalogNo());
			dto.setQtyUom(order.getQtyUom());
			dto.setSizeUom(order.getSizeUom());
			dto.setLocationCode(locationCode);
			listDetails.add(dto);
		}
		pageDto.setResult(listDetails);
		pageDto.setPageNo(page.getPageNo());
		pageDto.setPageSize(page.getPageSize());
		pageDto.setTotalCount(page.getTotalCount());
		return pageDto;
	}
	
	public Page<WorkOrder> getWorkOrderByFilter (Page<WorkOrder> page,
			List<PropertyFilter> filters,String batchFunction) throws Exception {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		for (Criterion temp : criterions) {
			criterionList.add(temp);
		}
		if(batchFunction!=null&&"QA".equals(batchFunction)) {
			Criterion criterion1 = Restrictions.eq("status", WorkOrderStatus.Closed.value());
			Criterion criterion2 = Restrictions.eq("status", WorkOrderStatus.Completed.value());
			Criterion criterion = Restrictions.or(criterion1,
					criterion2);
			criterionList.add(criterion);
		}
		return this.findPage(page, criterions);
	}
	
	/**
	 * 根据so_no和so_item_no搜索wo返回order_no
	 */
	public List<Integer> getIdByOrderItem(Integer[] orderNoList,Integer[] itemNoList) {
		StringBuffer hqlBuf = new StringBuffer("select orderNo from WorkOrder where 1=1");
		for(int i=0;i<orderNoList.length;i++) {
			if(i==0) {
				hqlBuf.append(" and ");
			} else {
				hqlBuf.append(" or ");
			}
			hqlBuf.append("(soNo=").append(orderNoList[i]).append(" and soItemNo=").append(itemNoList[i]).append(")");
			
		}
		return this.find(hqlBuf.toString());
	}
	
	/**
	 * 根据so_no和so_item_no搜索wo返回order_no
	 */
	public List<Integer> getIdByOrderItem(Integer orderNo) {
		StringBuffer hqlBuf = new StringBuffer("select orderNo from WorkOrder where 1=1");
		
			hqlBuf.append("and  (soNo=").append(orderNo).append(")");
		
		return this.find(hqlBuf.toString());
	}
	
	/**
	 * 获取当天创建的所有Wo中最大的seqNo
	 */
	public Integer getTodayMaxSeqNo() {
		String hql = "select max(seqNo) from WorkOrder where creationDate between ? and ?";
		Date date = new Date();
		String dateStr = DateUtils.formatDate2Str(date,DateUtils.C_DATE_PATTON_DEFAULT);
		String fromDate =dateStr+" 00:00:00";
		String toDate = dateStr+" 23:59:59";
		return this.findUnique(hql, DateUtils.formatStr2Date(fromDate, null),DateUtils.formatStr2Date(toDate, null));
	}
	
	/**
	 * 获取WO的条数
	 */
	public Long getToatlFoLotNo() {
		String hql = "select sum(lotNoCount) from WorkOrder";
		return this.findUnique(hql);
	}
	
	/**
	 * 根据workcenter获取workOrder
	 */
	public List<Integer> findByCenterId(Integer centerId) {
		String hql = "select orderNo from WorkOrder where workCenterId=?";
		return this.find(hql, centerId);
	}
	
	/**
	 * 获取尚未分配Work Group的Work Order
	 */
	public List<Integer> getWONoGroup() {
		String sql = "select t.order_no from manufacturing.work_orders t where t.order_no not in(select t2.order_no from manufacturing.work_order_group t2 )";
		return this.getSession().createSQLQuery(sql).list();
	}
}
