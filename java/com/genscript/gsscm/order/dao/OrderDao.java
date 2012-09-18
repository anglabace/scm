package com.genscript.gsscm.order.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.common.constant.OrderStatusType;
import com.genscript.gsscm.customer.dto.SampleRequestDTO;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.manufacture.entity.ManuDocument;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.entity.OrderProcessLog;
import com.genscript.gsscm.order.entity.StatusList;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;

@Repository
public class OrderDao extends HibernateDao<OrderMain, Integer> {
	@Autowired
	private OrderProcessLogDao orderProcessLogDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrderItemDao orderItemDao;

	private static final String INACTIVE_ORDER = "update OrderMain o set o.status =?, o.statusReason =?,o.modifyDate =?,o.modifiedBy =? where o.orderNo =?";
	private static final String TOTAL_PRODUCTS_ORDERED = "select sum(oi.quantity) from OrderMain o,OrderItem oi where o.custNo =:custNo and o.orderNo = oi.orderNo and oi.type=:type and o.orderNo in (:orderNoList)";
	private static final String GET_SHIPTO_ID = "select o.shiptoAddrId from OrderMain o where o.orderNo=? and o.custNo=?";
	private static final String INACTIVE_ORDER_ITEM = "update OrderItem o set o.status=?, o.statusReason =?, o.modifyDate =?,o.modifiedBy =? where o.orderItemId =?";
	private static final String CUST_HAVE_PRMT = "select count(DISTINCT t1.cust_no) from order.order t1 left join order.order_promotion t2 on t1.order_no=t2.order_no  where t1.cust_no=? and t2.prmt_code=?";
	private static final String CUST_HAVE_CREATE_ORDER = "select order_no from order.order where cust_no=? order by creation_date";
	private static final String CREATE_DATE_RATE = "from OrderMain where creationDate>=? and creationDate<=? order by creationDate";
	// 根据cust_no 获取当前最大 的order no 值
	private static final String MaxNumberOrderno = "SELECT max( orderNo ) FROM  OrderMain  WHERE custNo =?";
	private static final String MaxorderDetail = " FROM  OrderMain  WHERE orderNo =?";
	private static final String GetNameBystatus = " from StatusList s  where s.code=? and s.type= 'o' ";
	private static final String GetSampleRequest = "SELECT oi.order_item_id, oi.name, oi.creation_date, oi.selling_note, oi.order_no, oi.item_no, sl.ship_date, p.product_id\n"
			+ "FROM order.order_items oi, product.product p, order.order o, shipping.shipment_lines sl\n"
			+ "WHERE oi.type = 'PRODUCT'\n"
			+ "AND oi.catalog_no = p.catalog_no\n"
			+ "AND oi.status <> 'CN'\n"
			+ "AND p.gift_flag = 'Y'\n"
			+ "AND oi.order_no = o.order_no\n"
			+ "AND o.cust_no =? \n"
			+ "AND sl.order_no = oi.order_no\n"
			+ "AND sl.item_no = oi.item_no";

	public Page<SampleRequestDTO> getAllSampleResquestList(
			Page<SampleRequestDTO> page, Integer custNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Query query = this.getSession().createSQLQuery(GetSampleRequest);
		query.setFirstResult(0);
		query.setMaxResults(5);
		query.setParameter(0, custNo);
		if (page.isAutoCount()) {
			long totalCount = getSampleRequestTotalByCustNo(custNo);
			page.setTotalCount(totalCount);
		}
		List result = query.list();
		page.setResult(result);
		return page;
	}

	public Long getSampleRequestTotalByCustNo(Integer custNo) {
		Long count = 0L;
		try {
			String countSql = "select count(*) from ((";
			countSql += "SELECT oi.order_item_id, oi.name, oi.creation_date, oi.selling_note, oi.order_no, oi.item_no, sl.ship_date, p.product_id\n"
					+ " FROM order.order_items oi, product.product p, order.order o, shipping.shipment_lines sl\n"
					+ " WHERE oi.type = 'PRODUCT' \n"
					+ " AND oi.catalog_no = p.catalog_no\n"
					+ " AND oi.status <> 'CN'\n"
					+ " AND p.gift_flag = 'Y'\n"
					+ " AND oi.order_no = o.order_no\n"
					+ " AND sl.order_no = oi.order_no\n"
					+ "AND sl.item_no = oi.item_no "
					+ " AND o.cust_no ="
					+ custNo + ") a)";

			SQLQuery query = this.getSession().createSQLQuery(countSql);
			// System.out.println(query);
			BigInteger temp = (BigInteger) query.uniqueResult();
			count = temp.longValue();
			// System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"OrderDao  SampleRequestTotalByCustNo error !");
		}
		return count;

	}

	/*
	 * public List getSampleRequest(Integer custNo) { Query query =
	 * this.getSession().createSQLQuery(GetSampleRequest); query.setParameter(0,
	 * custNo); return query.list(); }
	 */

	public Integer getShipToId(final Integer orderNo, final Integer custNo) {
		return findUnique(GET_SHIPTO_ID, orderNo, custNo);
	}

	/**
	 * Order分页查询
	 * 
	 * @param orderNos
	 *            实体
	 * @return Page 返回类型
	 */
	public List<OrderMain> searchOrder(List<String> orderNos) {
		String hql = "from OrderMain";
		List<OrderMain> order = null;
		if (orderNos != null && !orderNos.isEmpty()) {
			hql += " where    ";
			String hqls = "";
			for (String orderNo : orderNos) {
				if (!hqls.equals("")) {
					hqls += " or ";
				}
				hqls += " orderNo =" + orderNo;
			}
			hql += hqls;
			System.out.println(hql);
			order = this.find(hql);
		}
		return order;
	}
	
	/*
	 * 根据orderNos 
	 */
	public List<OrderMain> searchOrder(String orderNos) {
		String hql = "from OrderMain where orderNo in("+orderNos+")";
		return this.find(hql);
	}

	public long getSalesStatistics(Map<String, Object> map) {
		String hql = "select sum(o.amount) from OrderMain o where status = 'CC' AND custNo = :custNo AND creationDate>=:fromDate AND creationDate<=:toDate";
		System.out.println(map.toString());
		Object obj = this.findUnique(hql, map);
		if (obj == null) {
			return 0L;
		} else if (obj instanceof BigDecimal) {
			return ((BigDecimal) obj).longValue();
		} else {
			return (Long) obj;
		}
	}

	/**
	 * 判断某客户的订单是否优惠过
	 * 
	 * @author lizhang
	 * @param custNo
	 */
	@SuppressWarnings("unchecked")
	public boolean isHasPrmtForCust(Integer custNo, String prmtCode) {
		Query query = this.getSession().createSQLQuery(CUST_HAVE_PRMT);
		query.setParameter(0, custNo);
		query.setParameter(1, prmtCode);
		List<BigInteger> custCount = query.list();
		if (custCount != null && custCount.size() > 0
				&& custCount.get(0).longValue() > 0l) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 某个客户下过订单没
	 */
	public boolean isHasCreateOrder(Integer custNo, String orderNo) {
		Query query = this.getSession().createSQLQuery(CUST_HAVE_CREATE_ORDER);
		query.setParameter(0, custNo);
		List<Integer> custCount = query.list();
		if (custCount == null || custCount.size() == 0) {
			return false;
		}
		if (custCount != null && custCount.size() > 0
				&& orderNo.equals(String.valueOf(custCount.get(0)))) {
			return false;
		} else {
			return true;
		}

	}

	public Integer getMaxOrderNoByCustNo(Integer custNo) {
		return this.findUnique(MaxNumberOrderno, custNo);
	}

	/**
	 * 根据code获取当前状态的名称
	 * 
	 * @param code
	 * @return
	 */
	public StatusList getStatusBycode(String code) {
		return this.findUnique(GetNameBystatus, code);
	}

	/**
	 * 根据order_no 获取当前订单的详细情况
	 * 
	 * @param custNo
	 * @return
	 */
	public OrderMain getOrderMain(Integer custNo) {
		Integer orderNo = getMaxOrderNoByCustNo(custNo);
		System.out.println(orderNo);
		return this.findUnique(MaxorderDetail, orderNo);
	}

	/**
	 * 通过逐渐查询
	 * 
	 * @param orderNo
	 * @return
	 */
	public OrderMain findByOrderNo(Integer orderNo) {
		return this.findUniqueBy("orderNo", orderNo);
	}

	public void delOrder(Integer orderNo, String statusReason, Integer userId,
			String comment) {
		OrderMain order = this.getById(orderNo);
		if (order != null) {
			OrderProcessLog orderProcessLog = new OrderProcessLog();
			String status = order.getStatus();
			batchExecute(INACTIVE_ORDER, OrderStatusType.CN.name(),
					statusReason, new Date(), userId, orderNo);
			orderProcessLog.setCurrentStat(OrderStatusType.CN.name());
			orderProcessLog.setNote(comment);
			orderProcessLog.setPriorStat(status);
			orderProcessLog.setProcessDate(new Date());
			if (userId != null) {
				orderProcessLog.setProcessedBy(userDao.getById(userId));
			}
			orderProcessLog.setOrderNo(orderNo);
			orderProcessLog.setReason(statusReason);
			orderProcessLogDao.save(orderProcessLog);
			String itemHql = "from OrderItem o where o.orderNo=?";
			List<OrderItem> itemList = orderItemDao.find(itemHql, orderNo);
			if (itemList != null && itemList.size() > 0) {
				for (OrderItem item : itemList) {
					batchExecute(INACTIVE_ORDER_ITEM,
							OrderItemStatusType.CN.value(), statusReason,
							new Date(), userId, item.getOrderItemId());
					OrderProcessLog itemLog = new OrderProcessLog();
					String itemStatus = item.getStatus();
					itemLog.setOrderItemId(item.getOrderItemId());
					itemLog.setPriorStat(itemStatus);
					itemLog.setCurrentStat(OrderItemStatusType.CN.value());
					itemLog.setNote(comment);
					itemLog.setProcessDate(new Date());
					if (userId != null) {
						itemLog.setProcessedBy(userDao.getById(userId));
					}
					itemLog.setOrderNo(orderNo);
					itemLog.setReason(statusReason);
					orderProcessLogDao.save(itemLog);
				}
			}
		}
	}

	/**
	 * 更新所属custNo
	 * 
	 * @param sourceCustNo
	 * @param targetCustNo
	 */
	public void updateCustomer(Integer sourceCustNo, Integer targetCustNo,
			Integer userId) {
		String hql = "update OrderMain o set o.custNo = ?, o.modifyDate =?, o.modifiedBy =? where o.custNo =?";
		batchExecute(hql, targetCustNo, new Date(), userId, sourceCustNo);
	}

	public Long getTotalProductsOrdered(Map<String, Object> map) {
		return findUnique(TOTAL_PRODUCTS_ORDERED, map);
	}

	/**
	 * 通过orderNo查询Customer
	 * 
	 * @param orderNo
	 * @return
	 */
	public Customer findBusEmailByOrderNo(Integer orderNo) {
		List<Object> list = this.find("select b from OrderMain a, Customer b, "
				+ " where a.custNo = b.custNo and a.orderNo = ?", orderNo);
		if (list != null && list.size() > 0) {
			return (Customer) list.get(0);
		}
		return null;
	}

	/**
	 * 查询某段时间内创建的所有order
	 * 
	 * @param fromDate
	 *            开始日期
	 * @param toDate
	 *            结束日期
	 */
	public List<OrderMain> findOrderByDate(Date fromDate, Date toDate) {
		List<OrderMain> list = this.find(CREATE_DATE_RATE, fromDate, toDate);
		return list;
	}

	/**
	 * 判断某coupon是否被order用过
	 */
	public boolean couponIsUsed(String couponId, Integer orderId) {
		String sql = "select 1 from order.order t where t.coupon_id REGEXP '([^0-9]|^)"
				+ couponId + "([^0-9]|$)'";
		if (orderId != null) {
			sql = sql + "and t.order_no<>" + orderId;
		}
		List list = this.getSession().createSQLQuery(sql).list();
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	/*
	 * us的SO获取PO再获取中国的PO 再获取中国的SO
	 */
	public List<Integer> getSOCNByUSSO(Integer so) {
		String hql = "select o.orderNo from MfgOrder o,PurchaseOrder p where "
				+ " o.srcPoNo = p.orderNo and p.srcSoNo = " + so;
		return this.find(hql);
	}

	public void updateOrderStatus(String status, String orderNo) {
		String hql = "update OrderMain set status='" + status
				+ "' where orderNo in(" + orderNo + ")";
		this.batchExecute(hql);
	}

	public HashMap<Integer, HashMap<Integer, List<ManuDocument>>> getAllItemsDocumentsByorderNo(
			int sessOrderNo) {
		String sql1 = "select oi.itemNo from OrderItem oi where oi.type='SERVICE' and oi.orderNo=" // 只读取service的
				+ sessOrderNo;
		List<Integer> itemNoList = null;
		List<Integer> workordersList = null;
		List<ManuDocument> documentsNoList = null;
		itemNoList = this.find(sql1);
		HashMap<Integer, List<ManuDocument>> sdocments = new HashMap<Integer, List<ManuDocument>>();
		HashMap<Integer, HashMap<Integer, List<ManuDocument>>> Alldocments = new HashMap<Integer, HashMap<Integer, List<ManuDocument>>>();
		int size = itemNoList.size();
		if (itemNoList != null && !"".equals(itemNoList) && size > 0
				&& !itemNoList.isEmpty()) {
			for (int i = 0; i < size; i++) {
				Integer orderitemNo = itemNoList.get(i);
			//	and a.status!='Canceled'
				String sql2 = " select a.orderNo from WorkOrder a,MfgOrder b  where b.orderNo=a.soNo and a.status!='Canceled' and b.srcSoNo ="
						+ sessOrderNo + " and a.soItemNo =" + orderitemNo;
				workordersList = this.find(sql2);
				int workordersListsizes = workordersList.size();
				if (workordersList != null && !"".equals(workordersList)
						&& workordersListsizes > 0 && !workordersList.isEmpty()) {
					for (int ii = 0; ii < workordersListsizes; ii++) {
						Integer workorderNo = workordersList.get(ii);
						if (workorderNo != null && !"".equals(workorderNo)) {
							String sql3 = "From ManuDocument p where p.refId="
									+ workorderNo;
							documentsNoList = this.find(sql3);
							if (documentsNoList != null
									&& !"".equals(documentsNoList)
									&& !documentsNoList.isEmpty()) {
								sdocments.put(workorderNo, documentsNoList);
							}
						}
					}
				}
				if (sdocments != null && !"".equals(sdocments)) {
					Alldocments.put(orderitemNo, sdocments);
				}
			}
		}

		return Alldocments;
	}
	
	public  HashMap<Integer, List<ManuDocument>>  getAllItemsDocumentsByorderNo2(
			int sessOrderNo) {
		String sql1 = "select oi.itemNo from OrderItem oi where oi.type='SERVICE' and oi.orderNo=" // 只读取service的
				+ sessOrderNo;
		List<Integer> itemNoList = null;
		List<Integer> workordersList = null;
		List<ManuDocument> documentsNoList = null;
		itemNoList = this.find(sql1);
		HashMap<Integer, List<ManuDocument>> sdocments = new HashMap<Integer, List<ManuDocument>>();
		//HashMap<Integer, HashMap<Integer, List<ManuDocument>>> Alldocments = new HashMap<Integer, HashMap<Integer, List<ManuDocument>>>();
		int size = itemNoList.size();
		if (itemNoList != null && !"".equals(itemNoList) && size > 0
				&& !itemNoList.isEmpty()) {
			for (int i = 0; i < size; i++) {
				Integer orderitemNo = itemNoList.get(i);
			//	and a.status!='Canceled'
				String sql2 = " select a.orderNo from WorkOrder a,MfgOrder b  where b.orderNo=a.soNo  and b.srcSoNo ="
						+ sessOrderNo + " and a.soItemNo =" + orderitemNo;
				workordersList = this.find(sql2);
				int workordersListsizes = workordersList.size();
				if (workordersList != null && !"".equals(workordersList)
						&& workordersListsizes > 0 && !workordersList.isEmpty()) {
					//for (int ii = 0; ii < workordersListsizes; ii++) {
						Integer workorderNo = workordersList.get(0);
						if (workorderNo != null && !"".equals(workorderNo)) {
							String sql3 = "From ManuDocument p where p.refId="
									+ workorderNo;
							documentsNoList = this.find(sql3);
							if (documentsNoList != null
									&& !"".equals(documentsNoList)
									&& !documentsNoList.isEmpty()) {
								sdocments.put(workorderNo, documentsNoList);
							}
						}
					}
				}
				/*if (sdocments != null && !"".equals(sdocments)) {
					Alldocments.put(orderitemNo, sdocments);
				}*/
			}
	//	}

		return sdocments;
	}
	
	/**
	 * 通过custNo查询该客户的Tech Account Manager信息
	 * @param custNo
	 * @return
	 */
	public User getUserByCustNo (Integer orderNo) {
		String hql = "SELECT u FROM OrderMain om, Customer c, User u WHERE om.orderNo=:orderNo AND om.custNo = c.custNo AND c.techSupport = u.userId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		List<User> userlist = this.find(hql, map);
		if (userlist != null && !userlist.isEmpty()) {
			return userlist.get(0);
		}
		return null;
	}
	
}
