package com.genscript.gsscm.order.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.CatalogType;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.common.constant.OrderQuoteStautsType;
import com.genscript.gsscm.common.constant.OrderReturnStatusType;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.ProductServiceSaleDTO;
import com.genscript.gsscm.order.dto.SalesOrderedDTO;
import com.genscript.gsscm.order.dto.SalesRankingDTO;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.serv.entity.ServiceClass;
import com.genscript.gsscm.shipment.entity.ShipClerk;
import com.genscript.gsscm.shipment.entity.ShipmentLine;
import com.genscript.gsscm.workstation.dto.OrderItemSrchDTO;
import com.genscript.gsscm.workstation.entity.ShippingClerkAdjustment;
import com.genscript.gsscm.workstation.entity.ShippingClerkAssigned;

@Repository
public class OrderItemDao extends HibernateDao<OrderItem, Integer> {

	@Autowired
	private UserDao userDao;

	private static final String GET_ORDER_ITEM = "from OrderItem o where o.orderNo=? and o.itemNo=?";
	private static final String getQuantitySumByOrderNo = "select sum(quantity) From OrderItem WHERE orderNo=:orderNo  and  status <>'CN' ";
	private static final String getPeptideByParent = "from OrderItem where parentId=:parentId and type='SERVICE' and clsId=1";

	public Integer getOrderItemsCountByOrderNo(Integer orderNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		List<Long> custCount = this.find(getQuantitySumByOrderNo, map);
		Integer cont = 0;
		if (custCount.get(0) != null) {
			cont = custCount.get(0).intValue();
		}
		return cont;
	}

	/**
	 * 获得一个Order状态不是RT的所有OrderItem.
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<OrderItem> getOrderUnRTItemList(Integer orderNo) {
		String hql = "from OrderItem where orderNo=:orderNo AND status<>:status order by itemNo ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("status", OrderItemStatusType.RT.value());
		return this.find(hql, map);
	}

	/**
	 * 获得一个Order状态为RT的所有OrderItem.
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<OrderItem> getOrderRTItemList(Integer orderNo) {
		String hql = "from OrderItem where orderNo=:orderNo AND status=:status order by itemNo ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("status", OrderItemStatusType.RT.value());
		return this.find(hql, map);
	}
	
	/**
	 * 获得一个Order状态为CC的所有OrderItem.
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<OrderItem> getOrderCCItemList(Integer orderNo) {
		String hql = "from OrderItem where orderNo=:orderNo AND status=:status order by itemNo ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("status", OrderItemStatusType.CC.value());
		return this.find(hql, map);
	}

	/**
	 * 获得一个Order所有状态的OrderItem.
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<OrderItem> getOrderAllItemList(Integer orderNo) {
		String hql = "from OrderItem where orderNo=:orderNo order by itemNo ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		return this.find(hql, map);
	}
	
	/**
	 * 查询Order所有状态指定类型的 OrderItem.
	 * @author Zhang Yong
	 * add date 2011-09-27
	 * @param orderNo
	 * @param type
	 * @param clsId
	 * @return
	 */
	public List<OrderItem> getAllItemListByOrderNo(Integer orderNo, String type, Integer clsId) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from OrderItem where orderNo=:orderNo ");
		map.put("orderNo", orderNo);
		if (StringUtils.isNotBlank(type)) {
			hql.append(" and type=:type " );
			map.put("type", type.trim());
		}
		if (clsId != null) {
			hql.append(" and clsId=:clsId " );
			map.put("clsId", clsId);
		}
		hql.append(" order by itemNo ASC");
		return this.find(hql.toString(), map);
	}
	
	/**
	 * 查询Order所有状态指定类型的 OrderItem.
	 * @author Zhang Yong
	 * add date 2011-09-27
	 * @param custNo
	 * @param type
	 * @param clsId
	 * @return
	 */
	public List<OrderItem> getAllItemListByCustNo(Integer custNo, String type, Integer clsId) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
	    hql.append("select oi from OrderItem oi, OrderMain om where om.custNo=:custNo and oi.orderNo = om.orderNo  ");
		map.put("custNo", custNo);
		if (StringUtils.isNotBlank(type)) {
			hql.append(" and oi.type=:type " );
			map.put("type", type.trim());
		}
		if (clsId != null) {
			hql.append(" and oi.clsId=:clsId " );
			map.put("clsId", clsId);
		}
		hql.append(" order by oi.orderNo, oi.itemNo ASC");
		return this.find(hql.toString(), map);
	}
	
    /**
     * 取得一个order的所有item信息
     * 这里用于order的edit页面优化显示
     *
     */
    public List<OrderItem> getOrderAllItemDataList(Integer orederNo){
        String hql = " select {item.*},\n" +
                "        case item.type when 'SERVICE' then (select name from product.service_classification psc where psc.cls_id=item.cls_id) \n" +
                "        else (select name from product.product_classification psc where psc.cls_id=item.cls_id) end as type_text," +
                "        (select concat(cat.catalog_id,':',cat.catalog_name) from product.catalog cat where cat.catalog_id=item.catalog_id) as catalog_text, " +
                "        (select ship_date from shipping.shipment_lines sl where sl.order_no=item.order_no and item.item_no=sl.item_no) as ship_date,sa.name as status_text " +
                "    from\n" +
                "        order.order_items item \n" +
                "        left join (select name,code from order.status_list where type='" + OrderQuoteStautsType.ORDERITEM.value() +"' ) as sa on sa.code=item.status\n" +
                "    where\n" +
                "        item.order_no=" + orederNo +
                "    order by item.item_no ASC";
        Query query = this.getSession().createSQLQuery(hql).addEntity("item", OrderItem.class).addScalar("type_text", Hibernate.STRING).addScalar("catalog_text", Hibernate.STRING).addScalar("ship_date", Hibernate.STRING).addScalar("status_text", Hibernate.STRING);
        List<OrderItem> itemList = new ArrayList<OrderItem>();
        for(Object obj : query.list()){
            Object[] itemObj = (Object[])obj;
            OrderItem item = (OrderItem)itemObj[0];
            item.setTypeText(itemObj[1] == null ? item.getType() + "" : item.getType() + "-" + itemObj[1].toString());
            item.setCatalogText(itemObj[2] == null ? "" : itemObj[2].toString());
            item.setShipDate(itemObj[3] == null ? "" : itemObj[3].toString());
            item.setStatusText(itemObj[4] == null ? "" : itemObj[4].toString());
            itemList.add(item);
        }
        return itemList;
    }

	/**
	 * 获得非Canceled|Returned的itemlist for repeat order.
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<OrderItem> getItemListForRepeat(Integer orderNo) {
		String hql = "from OrderItem where orderNo=:orderNo and status<>:rtstatus and status<>:cnstatus order by itemNo ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("rtstatus", OrderItemStatusType.RT.value());
		map.put("cnstatus", OrderItemStatusType.CN.value());
		return this.find(hql, map);
	}

	/**
	 * 获得一个Order的最后一个最大ItemNo.
	 * 
	 * @param orderNo
	 * @return
	 */
	public OrderItem getLastItemByItemNo(Integer orderNo) {
		OrderItem lastItem = null;
		Page<OrderItem> page = new Page<OrderItem>();
		page.setPageNo(1);
		page.setPageSize(1);
		String hql = "from OrderItem where orderNo=:orderNo order by itemNo DESC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		List<OrderItem> itemList = this.findPage(page, hql, map).getResult();
		if (itemList != null && itemList.size() > 0) {
			lastItem = itemList.get(0);
		}
		return lastItem;
	}

	/**
	 * 获得某个Product Units On Back Order的总和.
	 * 
	 * @param catalogNo
	 * @return
	 */
	public Long getBackOrderTotal(String catalogNo) {
		String hql = "select sum(item.quantity) from OrderItem item, OrderMain po where item.orderNo=po.orderNo and po.status=:status and item.catalogNo=:catalogNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		map.put("status", "BO");
		return this.findUnique(hql, map);
	}

	public OrderItem getOrderItem(final Integer orderNo,
			final Integer orderItemNo) {
		return findUnique(GET_ORDER_ITEM, orderNo, orderItemNo);
	}

    //add by zhanghuibin
    public Object[] getGeneDetailForExcel(final Integer orderNo, final Integer orderItemNo){
        String sql = "select {item.*},(select date(max(update_date)) from order.order_status_history history where history.order_no=ord.order_no and item.order_item_id=history.order_item_id and history.current_stat='CC') " +
                " as confirm_date,(select us.login_name from system.users us where us.user_id=ord.tech_support) as tam " +
                " from order.order_items item,order.order ord where item.order_no=:order_no and item.item_no=:item_no and item.order_no=ord.order_no";
        Query query = this.getSession().createSQLQuery(sql).addEntity("item", OrderItem.class).addScalar("confirm_date", Hibernate.STRING).addScalar("tam", Hibernate.STRING).setInteger("order_no", orderNo).setInteger("item_no", orderItemNo);
        List list = query.list();
        if(list.size() == 0){
            return null;
        }
        return (Object[])query.list().get(0);
    }

	/**
	 * 获得OrderItem的数量.
	 * 
	 * @param orderNo
	 * @param shipToAddrId
	 * @return
	 */
	public Long getOrderItemCountByAddrId(Integer orderNo, Integer shiptoAddrId) {
		String hql = "select count(orderItemId) from OrderItem where orderNo=:orderNo and shiptoAddrId=:shiptoAddrId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("shiptoAddrId", shiptoAddrId);
		return this.findUnique(hql, map);
	}

	/**
	 * 获得一个Product的各类销售统计情况.
	 * 
	 * @param catalogNo
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public ProductServiceSaleDTO getProductSale(String catalogNo,
			Date fromDate, Date toDate, String type) {
		ProductServiceSaleDTO productSaleDTO = new ProductServiceSaleDTO();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		map.put("type", type);
		// 获取总销售量；
		System.out.println("获取总销售量");
		productSaleDTO.setGrossUitsSales(this.getGrossUnitsSold(map));
		System.out.println(productSaleDTO.getGrossUitsSales());
		// 获取退贷量；
		System.out.println("c获取退贷量");
		productSaleDTO.setUnitsReturned(this.getUnitsReturned(map));
		System.out.println(productSaleDTO.getUnitsReturned());
		// 总销售额；
		System.out.println("总销售额");
		productSaleDTO.setTotalSales(this.getTotalSales(map));
		System.out.println(productSaleDTO.getTotalSales());
		// 总利润
		System.out.println("总利润");
		productSaleDTO.setTotalProfit(this.getTotalProfit(map));
		System.out.println(productSaleDTO.getTotalProfit());
		// 利润率
		System.out.println("利润率");
		Double p3 = 0.0;
		if (productSaleDTO.getTotalSales() > 0.0
				&& productSaleDTO.getTotalProfit() > 0.0) {
			p3 = productSaleDTO.getTotalProfit()
					/ productSaleDTO.getTotalSales();
		}
		System.out.println("enaaaaaaaaaa");
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);
		productSaleDTO.setMargin(nf.format(p3));
		System.out.println(p3);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return productSaleDTO;
	}

	/**
	 * 获得一个Product的总利润统计情况.
	 * 
	 * @param map
	 * @return
	 */
	public Double getTotalProfit(Map<String, Object> map) {
		String hql = "select sum((item.unitPrice-item.cost) * item.quantity) from OrderItem item where status  in('cc','vc','ip','sh','ps') and " +
				"catalogNo = :catalogNo AND creationDate>=:fromDate AND creationDate<=:toDate and type = :type";
		Object obj = this.findUnique(hql, map);
		
		if (obj == null) {
			return 0.0;
		}
		return (Double) obj;

	}

	/**
	 * 获得一个Product的总销售额统计情况.
	 * 
	 * @param map
	 * @return
	 */
	public Double getTotalSales(Map<String, Object> map) {
		String hql = "select sum(item.amount) from OrderItem item where status in('cc','vc','ip','sh','ps') and " +
				"catalogNo = :catalogNo AND creationDate>=:fromDate AND creationDate<=:toDate and type = :type";
		Object obj = this.findUnique(hql, map);
		if (obj == null) {
			return 0.0;
		}
		return (Double) obj;
	}

	/**
	 * 获得一个Product的总销售量统计情况.
	 * 
	 * @param map
	 * @return
	 */
	public Long getGrossUnitsSold(Map<String, Object> map) {
		String hql = "select sum(item.quantity) from OrderItem item where status in('cc','vc','ip','sh','ps') and catalogNo =:catalogNo AND creationDate>=:fromDate AND creationDate<=:toDate and type = :type";
		Object obj = this.findUnique(hql, map);
		if (obj == null) {
			return Long.valueOf(0);
		} else {

			return (Long) obj;
		}
	}

	/**
	 * 获得一个Product的退贷量统计情况.
	 * 
	 * @param map
	 * @return
	 */
	public Long getUnitsReturned(Map<String, Object> map) {
		String hql = "select sum(item.quantity) from OrderItem item where status = 'RT' and catalogNo = :catalogNo AND creationDate>=:fromDate AND creationDate<=:toDate and type = :type";
		System.out.println(map.toString());
		Object obj = this.findUnique(hql, map);
		if (obj == null) {
			return Long.valueOf(0);
		} else {

			return (Long) obj;
		}
	}

	/**
	 * 获得一个Product的净销售额统计情况.
	 * 
	 * @param map
	 * @return
	 */
	public Double getNetSales(Map<String, Object> map) {
		String hql = "select sum(item.amount-item.discount) from OrderItem item where status in('cc','vc','ip','sh','ps') and catalogNo = :catalogNo AND creationDate>=:fromDate AND creationDate<=:toDate and type = :type";
		System.out.println(map.toString());
		Object obj = this.findUnique(hql, map);
		if (obj == null) {
			return 0.0;
		} else if (obj instanceof BigDecimal) {
			return ((BigDecimal) obj).doubleValue();
		} else {

			return (Double) obj;
		}
	}

	/**
	 * 获得一个Product的退贷损失统计情况.
	 * 
	 * @param map
	 * @return
	 */
	public Double getLossOnReturn(Map<String, Object> map) {
		String hql = "select sum(item.amount-item.discount) from OrderItem item where status = 'RT' and catalogNo = :catalogNo AND creationDate>=:fromDate AND creationDate<=:toDate and type = :type";
		System.out.println(map.toString());
		Object obj = this.findUnique(hql, map);
		if (obj == null) {
			return 0.0;
		} else if (obj instanceof BigDecimal) {
			return ((BigDecimal) obj).doubleValue();
		} else {
			return (Double) obj;
		}
	}

	/**
	 * 获得一个Product的净销售量统计情况.
	 * 
	 * @param map
	 * @return
	 */
	public Long getNetUnitsSold(Map<String, Object> map) {
		String hql = "select sum(item.quantity) from OrderItem item where status in('cc','vc','ip','sh','ps') and catalogNo = :catalogNo AND creationDate>=:fromDate AND creationDate<=:toDate and type = :type";
		Object obj = this.findUnique(hql, map);
		if (obj == null) {
			return Long.valueOf(0);
		} else {

			return (Long) obj;
		}
	}

	/*
	 * 获取Sales Person(s) Selling this Product
	 */
	@SuppressWarnings("unchecked")
	public List<SalesRankingDTO> getSalesRanking(String catalogNo, Integer top,
			String fromDate, String toDate, String type) {
		String hql = "SELECT user.employee.employeeName as salesContact, SUM(item.quantity) as sumQuantity FROM OrderMain ord, OrderItem item ,User user"
				+ " WHERE ord.orderNo = item.orderNo AND item.catalogNo = '"
				+ catalogNo
				+ "' "
				+ " and item.type='"
				+ type
				+ "' and item.status in ('cc','vc','ip','sh','ps') "
				+ " And ord.orderDate between '"
				+ fromDate
				+ "' and '"
				+ toDate
				+ "' and user.userId = ord.salesContact GROUP BY ord.salesContact "
				+ "ORDER BY SUM(item.quantity)  DESC";
		System.out.println(hql);
		List<SalesRankingDTO> obj = this.createQuery(hql).setMaxResults(top)
				.list();
		System.out.println(obj);

		return obj;
	}

	@SuppressWarnings("unchecked")
	public List<SalesOrderedDTO> getMostOrdered(Integer custNo, Integer top,
			String type) {
		String hql = "SELECT item.name as name, SUM(item.quantity) as sumQuantity FROM OrderMain ord, OrderItem item "
				+ "WHERE ord.orderNo = item.orderNo AND item.status = 'CC' AND ord.custNo = '"
				+ custNo
				+ "' "
				+ " and item.type='"
				+ type
				+ "' GROUP BY item.catalogNo "
				+ "ORDER BY SUM(item.quantity)  DESC";
		// System.out.println(hql);
		List<SalesOrderedDTO> obj = this.createQuery(hql).setMaxResults(top)
				.list();
		// System.out.println(obj);
		return obj;
	}

	@SuppressWarnings("unchecked")
	public List<SalesOrderedDTO> getOrdersBy(Integer custNo, String ordersByType) {
		StringBuilder hqlSB = new StringBuilder();
		if (ordersByType.equalsIgnoreCase("catalogNo")) {
			hqlSB.append(
					"SELECT item.catalogNo as name, COUNT(ord.orderNo) as sumQuantity FROM OrderMain ord, OrderItem item ")
					.append("WHERE ord.orderNo = item.orderNo AND ord.status = 'CC' AND ord.custNo = '")
					.append(custNo).append("' GROUP BY item.catalogNo ")
					.append("ORDER BY item.catalogNo ASC");
		} else if (ordersByType.equalsIgnoreCase("salesId")) {
			hqlSB.append(
					"SELECT user.loginName as name, COUNT(ord.orderNo) as sumQuantity FROM OrderMain ord, User user ")
					.append("WHERE ord.salesContact = user.userId AND ord.status = 'CC' AND ord.custNo = '")
					.append(custNo).append("' GROUP BY ord.salesContact ")
					.append("ORDER BY user.loginName ASC");
		} else if (ordersByType.equalsIgnoreCase("sourceId")) {
			hqlSB.append(
					"SELECT source.name as name, COUNT(ord.orderNo) as sumQuantity FROM OrderMain ord, Source source ")
					.append("WHERE ord.orderSrc = source.sourceId AND ord.status = 'CC' AND ord.custNo = '")
					.append(custNo).append("' GROUP BY ord.orderSrc ")
					.append("ORDER BY source.name ASC");
		}
		List<SalesOrderedDTO> obj = this.createQuery(hqlSB.toString()).list();
		return obj;
	}

	/**
	 * 获得一个Product的销售情况.
	 * 
	 * @param catalogNo
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public Double getProductGrossSale(String catalogNo, Date fromDate,
			Date toDate) {
		String hql = " select sum(item.amount-item.discount+item.tax) from OrderItem item, OrderMain o ";
		hql += " where item.catalogNo=:catalogNo AND item.status<>:status AND item.orderNo=o.orderNo and o.orderDate>=:fromDate AND o.orderDate<=:toDate";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		map.put("status", OrderItemStatusType.CN.value());
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		Object obj = this.findUnique(hql, map);
		if (obj == null) {
			return null;
		} else if (obj instanceof BigDecimal) {
			return ((BigDecimal) obj).doubleValue();
		} else {
			return (Double) obj;
		}
	}

	public void getProductNetSale(String catalogNo, Date fromDate, Date toDate) {
		@SuppressWarnings("unused")
		String hql = "select sum(refund + discount) from OrderReturnItem item where status in (:refund, :processed) and exchangeFlag='N'";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		map.put("status", OrderItemStatusType.CN.value());
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		map.put("refund", OrderReturnStatusType.REFUNDED.value());
		map.put("processed", OrderReturnStatusType.PROCESSED.value());
	}

	/**
	 * 获得Order某种status的所有OrderItem.
	 * 
	 * @param orderNo
	 * @param itemStatus
	 * @return
	 * @author wangsf
	 * @since 2010-10-28
	 */
	public List<OrderItem> getItemListByType(Integer orderNo,
			OrderItemStatusType itemStatus) {
		String hql = "from OrderItem where orderNo=:orderNo AND status=:status order by itemNo ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("status", itemStatus.value());
		return this.find(hql, map);
	}

	/**
	 * 获得Order不是某些status的所有OrderItem.
	 */
	public List<OrderItem> getItemListNotInType(Integer orderNo,
			List<String> itemStatus) {
		String hql = "from OrderItem where orderNo=:orderNo AND status not in(:status) order by itemNo ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("status", itemStatus);
		return this.find(hql, map);
	}

	/**
	 * 查询orderNo、itemNo、catalogNo查询唯一记录
	 * 
	 * @param orderNo
	 * @param itemNo
	 * @param catalogNo
	 * @return
	 */
	public OrderItem getOrderItem(Integer orderNo, Integer itemNo,
			String catalogNo) {
		String hql = "FROM OrderItem WHERE orderNo=:orderNo AND itemNo=:itemNo and catalogNo=:catalogNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("itemNo", itemNo);
		map.put("catalogNo", catalogNo);
		return this.findUnique(hql, map);
	}

	public OrderItem searchOrderItemByOrderNoAndItemNo(Integer orderNo,
			Integer itemNo) {
		String hql = "FROM OrderItem WHERE orderNo=:orderNo AND itemNo=:itemNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("itemNo", itemNo);
		return this.findUnique(hql, map);
	}

	public List<OrderItem> searchOrderItemByOrderNoAndCatalogNo(
			Integer orderNo, String catalogNo) {
		String hql = "FROM OrderItem WHERE orderNo=:orderNo AND catalogNo=:catalogNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("catalogNo", catalogNo);
		return this.find(hql, map);
	}

	/**
	 * 根据orderNo查询详细信息
	 * 
	 * @param orderNo
	 * @return OrderItem
	 */
	public List<OrderItem> getOrderPageDetail(Integer orderNo) {
		String hql = "from OrderItem where orderNo = ?";
		return this.find(hql, orderNo);
	}

	/**
	 * 根据orderNO分页查询
	 * 
	 * @param page
	 * @param orderNo
	 * @return
	 */
	public Page<OrderItem> getOrderPageDetail(Page<OrderItem> page,
			Integer orderNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from OrderItem where 1=1";
		if (orderNo != null && orderNo.intValue() != 0) {
			hql += " AND orderNo=:orderNo";
			map.put("orderNo", orderNo);
		}
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		page = this.findPage(page, hql, map);
		return page;
	}

	/**
	 * 分页查询OrderItems
	 * 
	 * @param page
	 *            分页对象
	 * @return Page 返回类型
	 */
	@SuppressWarnings("unchecked")
	public Page<OrderItem> getManaList(Page<OrderItem> page) {
		List<OrderItem> manaList = null;
		String hql = "from OrderItem";
		Query q = createQuery(hql);
		setPageParameter(q, page);
		manaList = q.list();
		page.setResult(manaList);
		return page;
	}

	/*
      *
      */
	public Page<OrderItemDTO> searchOrderItemOfShipment(Page<OrderItem> page,
			OrderItemSrchDTO srch) {
		if (srch == null) {
			srch = new OrderItemSrchDTO();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "select oi,sl from OrderItem oi ,ShipmentLine sl  ";
		if (srch.getPurchaseOrderNo() != null
				&& srch.getPurchaseOrderNo().intValue() != 0) {
			hql += " ,OrderErpMapping po ";
		}
		hql += " where oi.orderNo=sl.order.orderNo and oi.itemNo = sl.itemNo ";
		if (srch.getWarehouseName() == null
				|| srch.getWarehouseName().equals("")) {
			srch.setWarehouseName("1");
		}
		hql += " and oi.status='CC'";
		hql += " and sl.order.status='CC'";
		hql += " AND sl.shipments.wareHouse.warehouseId ="
				+ srch.getWarehouseName();
		map.put("warehouseId", srch.getWarehouseName());
		// 输入的salesOrderNo
		if (srch.getOrderNo() != null && srch.getOrderNo().intValue() != 0) {
			hql += " AND oi.orderNo = :orderNo";
			map.put("orderNo", srch.getOrderNo());
		}
		// 输入的PurchaseOrderNo
		if (srch.getPurchaseOrderNo() != null
				&& srch.getPurchaseOrderNo().intValue() != 0) {
			hql += " and  po.erpUsPo =  "+srch.getPurchaseOrderNo()+" and po.orderNo=oi.orderNo";
			/*hql += " AND po.orderNo = :purchaseOrderNo and po.srcSoNo = oi.orderNo ";
			map.put("purchaseOrderNo", srch.getPurchaseOrderNo());*/
		}
		// type
		if (srch.getType() != null && srch.getType().trim().length() > 0) {
			hql += " AND oi.type like :type";
			map.put("type", "%" + srch.getType() + "%");
		}
		if (srch.getClsId() != null) {
			hql += " AND oi.clsId = :clsId";
			map.put("clsId", srch.getClsId());
		}
		if (srch.getYesOrNo() != null && srch.getYesOrNo() == 0) {
			//hql += " and sl.shipments.shippingClerk is null ";
			hql += " and not exists(select 1 from ShippingClerkShipmentAdjustment scsat where sl.shipments.shipmentId = scsat.shipmentId)";
			/*
			 * hql += " and (" + "(" +
			 * "(select count(*) from ShippingClerkAdjustment aj1 where  aj1.orderNo = oi.orderNo and aj1.itemNo = oi.itemNo) = 0"
			 * + " and " +
			 * "(select count(*) from ShippingClerkAssigned as1 where as1.itemType = oi.type and as1.clsId = oi.clsId and as1.warehouseId = sl.shipments.wareHouse.warehouseId) = 0"
			 * + ")" + " or " + "(" +
			 * "(select count(*) from ShippingClerkAdjustment aj2 where  aj2.orderNo = oi.orderNo and aj2.itemNo = oi.itemNo) = 0"
			 * + " and " +
			 * "(select aj3.newClerk from ShippingClerkAdjustment aj3 where aj3.id=(select max(id) from ShippingClerkAdjustment where orderNo = oi.orderNo and itemNo = oi.itemNo)) = -1"
			 * + ")" + ")";
			 */
		}
		if (srch.getYesOrNo() != null && srch.getYesOrNo() == 1) {
			//hql += " and sl.shipments.shippingClerk is not null ";
			hql += " and exists(select 1 from ShippingClerkShipmentAdjustment scsat where sl.shipments.shipmentId = scsat.shipmentId)";
			/*
			 * hql += " and" + "(" + "(" +
			 * "(select count(aj1) from ShippingClerkAdjustment aj1 where aj1.orderNo = oi.orderNo and aj1.itemNo = oi.itemNo) = 0"
			 * + " and " +
			 * "(select count(as1) from ShippingClerkAssigned as1 where as1.itemType = oi.type and as1.clsId = oi.clsId and as1.warehouseId = sl.shipments.wareHouse.warehouseId) > 0"
			 * + ")" + " or " + "(" +
			 * "(select count(aj2) from ShippingClerkAdjustment aj2 where aj2.orderNo = oi.orderNo and aj2.itemNo = oi.itemNo) > 0"
			 * + " and " +
			 * "(select aj3.newClerk from ShippingClerkAdjustment aj3 where aj3.id=(select max(id) from ShippingClerkAdjustment where orderNo = oi.orderNo and itemNo = oi.itemNo)) <> -1"
			 * + ")" + ")";
			 */
		}

		hql += " order by sl.shipments.modifyDate desc";

		// 执行分页方法
		page = this.findPage(page, hql, map);
		// 获取Result
		List list1 = page.getResult();
		List<OrderItemDTO> listOrderItemDto = new ArrayList<OrderItemDTO>();
		Page<OrderItemDTO> pageDto = new Page<OrderItemDTO>();
		// for循环
		for (int j = 0; j < list1.size(); j++) {
			OrderItemDTO oid = new OrderItemDTO();
			Object[] obj = (Object[]) list1.get(j);
			// 获取OrderItems
			OrderItem oi = (OrderItem) obj[0];
			ShipmentLine sl = (ShipmentLine) obj[1];
			// Integer poNo = (Integer)obj[2];
			Integer warehouseId = sl.getShipments().getWareHouse()
					.getWarehouseId();
			String warehouseName = sl.getShipments().getWareHouse().getName();
			OrderMain om = sl.getOrder();
			if (om == null) {
				om = new OrderMain();
			}

			// 获取User
			oid.setComment("");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>slgetshipment"+sl.getShipments().getShipmentNo()+"  "+sl.getShipments());
			if (sl.getShipments() != null
					&& sl.getShipments().getShipClerkList() != null&&!sl.getShipments().getShipClerkList().isEmpty()) {
				String userName = "";
				for(ShipClerk shipClerk : sl.getShipments().getShipClerkList()){
					if(userName.equals("")){
						userName = shipClerk.getUser().getEmployee().getEmployeeName();
					}else{
						userName+= "<BR>"+shipClerk.getUser().getEmployee().getEmployeeName();
					}
				}
				/*User user = this.getUser(sl.getShipments().getShippingClerk());*/
				if (!userName.equals(""))
					oid.setComment(userName);
			}
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>slgetshipmentend");
			/*
			 * this.getAssignedTo(oi.getOrderNo(), oi.getItemNo(), warehouseId,
			 * oi.getType(), oi.getClsId());
			 */

			// 获取type
			String type = oi.getType();
			if (type.equals("PRODUCT")) {
				ProductClass pc = this.getProductClassification(oi.getClsId());
				if (pc != null) {
					oid.setRelationType(pc.getName());
					System.out.println(pc.getName());
				} else
					oid.setRelationType("");
			} else if (type.equals("SERVICE")) {
				ServiceClass sc = this.getServiceClassification(oi.getClsId());
				if (sc != null)
					oid.setRelationType(sc.getName());
				else
					oid.setRelationType("");
			} else {
				oid.setRelationType("");
			}

			oid.setOrderItemId(oi.getOrderItemId());
			oid.setOrderNo(oi.getOrderNo());
			oid.setItemNo(oi.getItemNo());
			oid.setName(oi.getName());
			oid.setType(oi.getType());
			oid.setWarehouseName(warehouseName);
			oid.setWarehouseId(warehouseId);
			if (sl.getShipments() != null
					&& sl.getShipments().getShipmentId() != null) {
				oid.setShippmentId(sl.getShipments().getShipmentId());
			}
			oid.setType(type);
			oid.setStatus(oi.getStatus());
			// oid.setRelationType(oi.getRelationType());
			oid.setExprDate(om.getExprDate());
			oid.setOrderDate(om.getOrderDate());
			oid.setSalesContact(om.getSalesContact());
			if (oid.getSalesContact() != null) {
				User user = userDao.getById(oid.getSalesContact());
				if (user != null) {
					if (user.getEmployee() != null) {
						oid.setSalesContactName(user.getEmployee()
								.getEmployeeName());
					}
				}
			}
			// oid.setPoOrderNo(poNo);
			oid.setOrderType(om.getOrderType());
			listOrderItemDto.add(oid);
		}
		pageDto.setResult(listOrderItemDto);
		// 重新指定pageNo，pageSize，TotalCount
		pageDto.setPageNo(page.getPageNo());
		pageDto.setPageSize(page.getPageSize());
		pageDto.setTotalCount(page.getTotalCount());
		return pageDto;
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 *            分页对象
	 * @param srch
	 *            实体对象
	 * @return Page 返回类型
	 */
	@SuppressWarnings("rawtypes")
	public Page<OrderItemDTO> searchOrderItem(Page<OrderItem> page,
			OrderItemSrchDTO srch) {
		if (srch == null) {
			srch = new OrderItemSrchDTO();
		}
		String wh = "Genscript US Warehouse";
		if (srch.getWarehouseName() == null) {
			srch.setWarehouseName(wh);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "";
		if (srch.getWarehouseName() == null
				|| srch.getWarehouseName().equals("")
				|| srch.getWarehouseName().equals("Genscript US Warehouse")) {
			hql = "select oi,wh.warehouseId,wh.name,ord from OrderItem oi,OrderMain ord,Warehouse wh,ProductClass pcf,ServiceClass scf"
					+ " WHERE EXISTS(SELECT 1 FROM Reservation r where oi.orderNo = r.orderNo and oi.itemNo = r.itemNo ) and oi.orderNo = ord.orderNo"
					+ " and ord.warehouseId = wh.warehouseId and pcf.clsId = oi.clsId and scf.clsId = oi.clsId and wh.name ='"
					+ wh + "'";
		}
		/*
		 * else if(srch.getWarehouseName().equals("Genscript US Warehouse")){
		 * hql =
		 * "select oi,wh.warehouseId,wh.name,ord from OrderItem oi,OrderMain ord,Warehouse wh,ProductClass pcf,ServiceClass scf,PurchaseOrder po"
		 * +
		 * " WHERE EXISTS(SELECT 1 FROM Reservation r where oi.orderNo = r.orderNo and oi.itemNo = r.itemNo ) and oi.orderNo = ord.orderNo"
		 * +
		 * " and ord.warehouseId = wh.warehouseId and pcf.clsId = oi.clsId and scf.clsId = oi.clsId"
		 * + " and ord.srcPoNo = po.orderNo "; }
		 */
		else if (srch.getWarehouseName().equals("Genscript NJ Warehouse")) {
			hql = "select oi,wh.warehouseId,wh.name,ord,ve.vendorName from OrderItem oi,OrderMain ord,PurchaseOrder po,Warehouse wh,ProductClass pcf,ServiceClass scf,Vendor ve"
					+ " WHERE EXISTS(SELECT 1 FROM Reservation r where oi.orderNo = r.orderNo and oi.itemNo = r.itemNo ) and oi.orderNo = ord.orderNo"
					+ " and ord.warehouseId = wh.warehouseId and pcf.clsId = oi.clsId and scf.clsId = oi.clsId and ve.vendorNo = po.vendorNo"
					+ " and ord.srcPoNo = po.orderNo";
		}

		// /////////修改begin ////////////
		// 输入的salesOrderNo
		if (srch.getOrderNo() != null && srch.getOrderNo().intValue() != 0) {
			hql += " AND oi.orderNo = :orderNo";
			map.put("orderNo", srch.getOrderNo());
		}
		// 输入的PurchaseOrderNo
		if (srch.getPurchaseOrderNo() != null
				&& srch.getPurchaseOrderNo().intValue() != 0) {
			hql += " AND po.orderNo = :purchaseOrderNo";
			map.put("purchaseOrderNo", srch.getPurchaseOrderNo());
		}
		// ///////////修改end//////////////

		// warehouseName
		if (srch.getWarehouseName() != null
				&& srch.getWarehouseName().trim().length() > 0) {
			hql += " AND wh.name like :warehouseName";
			map.put("warehouseName", "%" + srch.getWarehouseName() + "%");
		}
		// relationType
		if (srch.getRelationType() != null
				&& srch.getRelationType().trim().length() > 0) {
			hql += " AND oi.relationType like :relationType";
			map.put("relationType", "%" + srch.getRelationType() + "%");
		}
		// type
		if (srch.getType() != null && srch.getType().trim().length() > 0) {
			hql += " AND oi.type like :type";
			map.put("type", "%" + srch.getType() + "%");
		}
		// ProductClassificationName
		if (srch.getProductClassificationName() != null
				&& srch.getProductClassificationName().trim().length() > 0) {
			hql += " AND pcf.name like :productClassificationName";
			map.put("productClassificationName",
					"%" + srch.getProductClassificationName() + "%");
		}
		// ServiceClassificationName
		if (srch.getServiceClassificationName() != null
				&& srch.getServiceClassificationName().trim().length() > 0) {
			hql += " AND scf.name like :serviceClassificationName	";
			map.put("serviceClassificationName",
					"%" + srch.getServiceClassificationName() + "%");
		}

		if (srch.getYesOrNo() != null && srch.getYesOrNo() == 0) {
			hql += " and ("
					+ "("
					+ "(select count(*) from ShippingClerkAdjustment aj1 where  aj1.orderNo = oi.orderNo and aj1.itemNo = oi.itemNo) = 0"
					+ " and "
					+ "(select count(*) from ShippingClerkAssigned as1 where as1.itemType = oi.type and as1.clsId = oi.clsId and as1.warehouseId = wh.warehouseId) = 0"
					+ ")"
					+ " or "
					+ "("
					+ "(select count(*) from ShippingClerkAdjustment aj2 where  aj2.orderNo = oi.orderNo and aj2.itemNo = oi.itemNo) = 0"
					+ " and "
					+ "(select aj3.newClerk from ShippingClerkAdjustment aj3 where aj3.id=(select max(id) from ShippingClerkAdjustment where orderNo = oi.orderNo and itemNo = oi.itemNo)) = -1"
					+ ")" + ")";
		}

		if (srch.getYesOrNo() != null && srch.getYesOrNo() == 1) {
			hql += " and"
					+ "("
					+ "("
					+ "(select count(aj1) from ShippingClerkAdjustment aj1 where aj1.orderNo = oi.orderNo and aj1.itemNo = oi.itemNo) = 0"
					+ " and "
					+ "(select count(as1) from ShippingClerkAssigned as1 where as1.itemType = oi.type and as1.clsId = oi.clsId and as1.warehouseId = wh.warehouseId) > 0"
					+ ")"
					+ " or "
					+ "("
					+ "(select count(aj2) from ShippingClerkAdjustment aj2 where aj2.orderNo = oi.orderNo and aj2.itemNo = oi.itemNo) > 0"
					+ " and "
					+ "(select aj3.newClerk from ShippingClerkAdjustment aj3 where aj3.id=(select max(id) from ShippingClerkAdjustment where orderNo = oi.orderNo and itemNo = oi.itemNo)) <> -1"
					+ ")" + ")";
		}

		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		// 执行分页方法
		page = this.findPage(page, hql, map);
		// 获取Result
		List list1 = page.getResult();
		List<OrderItemDTO> listOrderItemDto = new ArrayList<OrderItemDTO>();
		Page<OrderItemDTO> pageDto = new Page<OrderItemDTO>();
		OrderItemDTO oid = null;
		// for循环
		for (int j = 0; j < list1.size(); j++) {
			oid = new OrderItemDTO();
			Object[] obj = (Object[]) list1.get(j);
			// 获取OrderItems
			OrderItem oi = (OrderItem) obj[0];
			Integer warehouseId = Integer.parseInt(obj[1] + "");
			String warehouseName = obj[2] + "";
			OrderMain om = (OrderMain) obj[3];
			String vendorName = "";
			if ("Genscript NJ Warehouse".equals(srch.getWarehouseName())) {
				vendorName = obj[4] + "";
			}
			// 获取User
			User user = this.getAssignedTo(oi.getOrderNo(), oi.getItemNo(),
					warehouseId, oi.getType(), oi.getClsId());
			if (user != null)
				oid.setComment(user.getLoginName());
			else
				oid.setComment("");
			// 获取type
			String type = oi.getType();
			if (type.equals("PRODUCT")) {
				ProductClass pc = this.getProductClassification(oi.getClsId());
				if (pc != null)
					oid.setRelationType(pc.getName());
				else
					oid.setRelationType("");
			} else if (type.equals("SERVICE")) {
				ServiceClass sc = this.getServiceClassification(oi.getClsId());
				if (sc != null)
					oid.setRelationType(sc.getName());
				else
					oid.setRelationType("");

			} else {
				oid.setRelationType("");
			}
			oid.setOrderItemId(oi.getOrderItemId());
			oid.setOrderNo(oi.getOrderNo());
			oid.setItemNo(oi.getItemNo());
			oid.setName(oi.getName());
			oid.setType(oi.getType());
			oid.setWarehouseName(warehouseName);
			oid.setWarehouseId(warehouseId);
			if ("Genscript NJ Warehouse".equals(srch.getWarehouseName())) {
				oid.setVendorName(vendorName);
			}
			oid.setType(type);
			oid.setStatus(oi.getStatus());
			oid.setRelationType(oi.getRelationType());
			oid.setExprDate(om.getExprDate());
			oid.setOrderDate(om.getOrderDate());
			oid.setSalesContact(om.getSalesContact());
			oid.setPoOrderNo(om.getSrcPoNo());
			oid.setOrderType(om.getOrderType());
			listOrderItemDto.add(oid);
		}
		pageDto.setResult(listOrderItemDto);
		// 重新指定pageNo，pageSize，TotalCount
		pageDto.setPageNo(page.getPageNo());
		pageDto.setPageSize(page.getPageSize());
		pageDto.setTotalCount(page.getTotalCount());
		return pageDto;

	}

	/**
	 * 根据clsId查询,返回ProductClassification实体
	 * 
	 * @param clsId
	 *            参数
	 * @return ProductClassification 实体
	 */
	public ProductClass getProductClassification(Integer clsId) {

		String hql = "from ProductClass where clsId =:clsId";
		return (ProductClass) this.getSession().createQuery(hql)
				.setInteger("clsId", clsId).uniqueResult();

	}

	/**
	 * 根据clsId查询,返回一个ServiceClassification
	 * 
	 * @param clsId
	 *            参数
	 * @return ServiceClass 实体
	 */
	public ServiceClass getServiceClassification(Integer clsId) {
		try {
			String hql = "from ServiceClass where clsId =:clsId";
			return (ServiceClass) this.getSession().createQuery(hql)
					.setInteger("clsId", clsId).uniqueResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ServiceClass();

	}

	/**
	 * 根据orderNo和itemNo查询ShippingClerkAdjustment
	 * 
	 * @param orderNo
	 *            参数
	 * @param itemNo
	 *            参数
	 * @return List 集合
	 */
	@SuppressWarnings("rawtypes")
	public List getNewClerk(Integer orderNo, Integer itemNo) {
		List list = null;
		try {
			String hql = "from ShippingClerkAdjustment where orderNo=? and itemNo=? order by id desc";
			list = this.find(hql, orderNo, itemNo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据orderNo查询Order
	 * 
	 * @param orderNo
	 *            参数
	 * @return Order 实体
	 */
	public OrderMain getOrder(Integer orderNo) {
		String hql = "from OrderMain where orderNo =:orderNo";
		OrderMain order = (OrderMain) this.getSession().createQuery(hql)
				.setInteger("orderNo", orderNo).uniqueResult();
		return order;
	}

	/**
	 * 根据warehouseId\itemType\clsId 查询ShippingClerkAssigned
	 * 
	 * @param warehouseId
	 *            参数
	 * @param itemType
	 *            参数
	 * @param clsId
	 *            参数
	 * @return List 集合
	 */
	@SuppressWarnings("unchecked")
	public List getShippingClerk(Integer warehouseId, String itemType,
			Integer clsId) {
		String hql = "from ShippingClerkAssigned scad where warehouseId = ? and itemType = ? and clsId = ? ";
		List list = this.find(hql, warehouseId, itemType, clsId);
		return list;
	}

	/**
	 * 分页
	 * 
	 * @param page
	 * @return
	 */

	public Page<OrderItem> searchOrderItem(Page<OrderItem> page,
			List<PropertyFilter> filters) {
		return findPage(page, filters);
	}

	/**
	 * 根据userId,查询返回User
	 * 
	 * @param userId
	 *            参数
	 * @return User 实体
	 */
	public User getUser(Integer userId) {
		String hql = "from User where userId =:userId";
		if (userId != null) {
			return (User) this.getSession().createQuery(hql)
					.setInteger("userId", userId).uniqueResult();
		} else {
			return null;
		}

	}

	/**
	 * 对list进行判断
	 * 
	 * @param item
	 * @return User
	 */

	@SuppressWarnings("unchecked")
	public User getAssignedTo(Integer orderNo, Integer itemNo,
			Integer warehouseId, String type, Integer clsId) {
		// public User getAssignedTo(OrderItem item) {
		List list2 = this.getNewClerk(orderNo, itemNo);
		User user = null;
		if (list2 != null && list2.size() > 0) {
			ShippingClerkAdjustment sca = (ShippingClerkAdjustment) list2
					.get(0);
			user = this.getUser(sca.getNewClerk());
		} else {
			List list = this.getShippingClerk(warehouseId, type, clsId);
			if (list != null && list.size() > 0) {
				ShippingClerkAssigned assign = (ShippingClerkAssigned) list
						.get(0);
				user = this.getUser(assign.getShippingClerk());
			}
		}
		return user;
	}

	/**
	 * 查询Warehouse
	 * 
	 * @return list 集合
	 */
	public List<Warehouse> getListByWarehouse() {
		String hql = "from Warehouse order by warehouseId desc";
		return this.find(hql);
	}

	/**
	 * 查询ServiceClassification
	 * 
	 * @return list 集合
	 */
	public List<ServiceClass> getListByServiceClassification() {
		String hql = "from ServiceClass";
		return this.find(hql);
	}

	/**
	 * 查询ProductClassification
	 * 
	 * @return List 集合
	 */
	public List<ProductClass> getListByProductClassification() {
		String hql = "from ProductClass";
		return this.find(hql);
	}

	/**
	 * 查询OrderItem
	 * 
	 * @return List 集合
	 */
	public List<OrderItem> getListByType() {
		String hql = "from OrderItem";
		return this.find(hql);

	}

	/**
	 * 查询PackingSlip
	 * 
	 * @param packageId
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List printPackingSlip(String packageId) {
		String sql = "select * from order.order_items s where 1=1  ";
		List list = this.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 更新OrderItem表信息
	 * 
	 * @param item
	 * @return String
	 * @throws Exception
	 */
	public String updateOrderItem(OrderItem item) throws Exception {
		String flag = "1";
		try {
			this.getSession()
					.createSQLQuery(
							"udpate order_items set quantity =:quantity and qtyUom =:qtyUom "
									+ "and size =:size and sizeUom =:sizeUom")
					.setInteger("quantity", item.getQuantity())
					.setString("qtyUom", item.getQtyUom())
					.setDouble("size", item.getSize())
					.setString("sizeUom", item.getSizeUom()).executeUpdate();
		} catch (Exception ex) {
			flag = "0";
			throw ex;
		}
		return flag;
	}

	/**
	 * order下所有的itemNo统一增加一个增量，默认为10000，目前用于保存时候临时使用，否则会出现Duplicate key 问题
	 * 
	 * @param orderNo
	 * @author zouyulu
	 */
	public void updateOrderItemNo(Integer orderNo, Integer addCount) {
		if (addCount == null) {
			addCount = 10000;
		}
		String hql = "update OrderItem set itemNo=itemNo+:addCount where orderNo=:orderNo";
		this.createQuery(hql).setInteger("addCount", addCount)
				.setInteger("orderNo", orderNo).executeUpdate();
		// this.getSession().createSQLQuery(sql).setInteger("addCount",
		// addCount).setInteger("orderNo", orderNo).executeUpdate();
	}

	/**
	 * 根据orderNo查询OrderItem
	 * 
	 * @param orderNo
	 *            参数
	 * @return List 集合
	 */
	public List<OrderItem> getOrderItemsByOrderNo(Serializable orderNo) {
		String hql = "from OrderItem where orderNo='" + orderNo + "'";
		List<OrderItem> list = this.find(hql);
		return list;
	}

	/**
	 * 根据orderNo,分页查询
	 * 
	 * @param page
	 *            分页对象
	 * @param orderNo
	 *            参数
	 * @return Page 返回类型
	 */
	@SuppressWarnings("unchecked")
	public Page<OrderItem> searchOrderItemsByOrderNo(Page<OrderItem> page,
			Serializable orderNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "select distinct(orderItems) from OrderItem orderItems,Reservation reservation "
				+ "where orderItems.orderNo=reservation.orderNo and "
				+ "orderItems.itemNo=reservation.itemNo and orderItems.orderNo='"
				+ orderNo + "'";
		page = this.findPage(page, hql, map);
		List list = this.getSession().createQuery(hql).list();

		if (list != null)
			page.setTotalCount((long) list.size());
		return page;
	}

	/**
	 * 通过主键Id查询
	 * 
	 * @param orderItemId
	 * @return
	 * @author zhangyong
	 */
	public OrderItem searchOrderItemByItemId(Integer orderItemId) {
		return this.findUniqueBy("orderItemId", orderItemId);
	}

	/**
	 * 通过ItemId查询其所有的子Item
	 * 
	 * @param parentItemId
	 * @return
	 */
	public List<OrderItem> searchOrderItemByParentItemId(Integer parentItemId) {
		return this.find("from OrderItem where parentId = ?", parentItemId);
	}

	public List<OrderItem> getPeptideByParent(Integer parentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		return this.find(getPeptideByParent, map);
	}

	/**
	 * 从olddb.aptrack_host查找host_no
	 */
	public List<String> getHostNoList(Integer orderNo, Integer itemNo) {
		String sql = "select host_no from olddb.aptrack_host where order_id = ? and item_id = ?";
		Query query = this.getSession().createSQLQuery(sql);
		query.setParameter(0, orderNo);
		query.setParameter(1, itemNo);
		List<String> hostNoList = query.list();
		return hostNoList;
	}

	/**
	 * 批量删除OrderItem.
	 * 
	 * @param ids
	 */
	public void delOrderItemList(Object ids) {
		String hql = "delete from OrderItem c where c.orderItemId in (:ids)";
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}

	public void updateOrderStatus(String status,String isStatus, String orderNo) {
		String hql = "update OrderItem set status='" + status
				+ "' where orderNo in(" + orderNo + ") and status in ('"+isStatus+"')";
		this.batchExecute(hql);
	}
	
	public void updateOrderItemStatusByOrder1(String status,String isStatus, Integer itemNoId) {
		String hql = "update OrderItem set status='" + status
				+ "' where orderItemId = "+itemNoId+" and status in('"+isStatus+"')";
		this.batchExecute(hql);
	}
    
    /**
	 * 根据order_no和item_no搜索
	 */
	public List<OrderItem> getOrderItemList(String[] orderNoList,String[] itemNoList) {
		StringBuffer hqlBuf = new StringBuffer("from OrderItem where 1=1");
		for(int i=0;i<orderNoList.length;i++) {
			if(i==0) {
				hqlBuf.append(" and ");
			} else {
				hqlBuf.append(" or ");
			}
			hqlBuf.append("(orderNo=").append(orderNoList[i]).append(" and itemNo=").append(itemNoList[i]).append(")");
			
		}
		return this.find(hqlBuf.toString());
	}
	
	/**
	 * 获取最大的targetDate
	 */
	public Date getMaxTargetDate(Integer orderNo) {
		String hql = "select max(targetDate) from OrderItem where orderNo=?";
		return this.findUnique(hql, orderNo);
	}
	
	/**
	 * 获取最大的leadtime
	 */
	public Integer getMaxLeadtime(Integer orderNo) {
		String hql = "select max(shipSchedule) from OrderItem where orderNo=?";
		return this.findUnique(hql,orderNo);
	}
	
	/**
	 * 通过OrderNo查询OrderItem的catalog信息
	 * @author Zhang Yong
	 * @param orderNo
	 * @return
	 */
	public List<Object[]> getCatalogByOrderNo (Integer orderNo) {
		String hql = "SELECT distinct(a.catalogId), concat(a.catalogId,' : ',a.catalogName) FROM Catalog a, OrderItem b where b.orderNo=? and b.catalogId = a.catalogId";
		return this.find(hql, orderNo);
	}
	
	/**
	 * 通过OrderNo查询item中product类型的，再通过catalogNo在product中查找shippable="N"的item
	 * @author Zhang Yong
	 * add date 2011-10-14
	 * @param orderNo
	 * @return
	 */
	public List<OrderItem> getProdItemList (Integer orderNo) {
		String hql = "SELECT oi FROM OrderItem oi, Product p WHERE oi.orderNo=:orderNo AND " +
				" oi.type=:type AND oi.status !=:status AND oi.catalogNo=p.catalogNo AND p.shippable !=:shippable";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("type", CatalogType.PRODUCT.value());
		map.put("shippable", "N");
		map.put("status", OrderItemStatusType.CN.value());
		return find(hql, map);
	}
	
	/**
	 * 通过OrderNo查询item中service类型的，再通过catalogNo在product中查找shippable="N"的item
	 * @author Zhang Yong
	 * add date 2011-10-14
	 * @param orderNo
	 * @return
	 */
	public List<OrderItem> getServItemList (Integer orderNo) {
		String hql = "SELECT oi FROM OrderItem oi, Service s WHERE oi.orderNo=:orderNo AND " +
				" oi.type=:type AND oi.status !=:status AND oi.catalogNo=s.catalogNo AND s.shippable !=:shippable";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("type", CatalogType.SERVICE.value());
		map.put("shippable", "N");
		map.put("status", OrderItemStatusType.CN.value());
		return find(hql, map);
	}
	
	/**
	 * 查询指定orderNo下的shipMethod不匹配shipMethodName的item
	 * @author Zhang Yong 
	 * add date 2011-10-28
	 * @param orderNo
	 * @param shipMethodName
	 * @return
	 */
	public List<OrderItem> getNotFedExItem (Integer orderNo, String shipMethodName) {
		String hql = "SELECT oi FROM OrderItem oi, ShipMethod s WHERE oi.orderNo=:orderNo " +
				"AND oi.status !=:status AND oi.shipMethod = s.methodId AND s.carrier !=:carrier";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("status", OrderItemStatusType.CN.value());
		map.put("carrier", shipMethodName);
		return find(hql, map);
	}
	
	/*
	 * 获取received没有接收的Item
	 */
	public List<OrderItem> searchNotReceivedItem(String orderNo){
		String hql = "select oi from OrderItem oi where oi.orderNo = "+orderNo+" and " +
				" oi.itemNo not in (select r.itemNo from Reservation r where r.orderNo ="+orderNo+")";
		return this.find(hql);
		
	}
	
	/*
	 * 获取过期Item
	 */
	public String searchExpiredItem(String orderNo){
		/*String sql = "(select oi.item_no,p.lead_time from  order.order_items oi,product.product p where oi.order_no ="+orderNo+"  and  oi.catalog_no = p.catalog_no)" +
				"union (select oi.item_no,s.lead_time from order.order_items oi,product.service s where oi.order_no =" +orderNo+" and oi.catalog_no = s.catalog_no)";*/
		String sql = "SELECT distinct a.item_no,IF(a.type = 'PRODUCT',b.lead_time,c.lead_time)"
		  +" FROM , order.order_items a"
		  +" LEFT JOIN  product.product b"
		  +" ON(a.type = 'PRODUCT'"
		  +"   AND a.catalog_no = b.catalog_no)"
		  +" LEFT JOIN product.service c"
		  +" ON(a.type = 'SERVICE'"
		  +" AND a.cls_id = c.service_cls_id)" 
		  +" where a.order_no="+orderNo;
		System.out.println(sql);
		List list = this.getSession().createSQLQuery(sql).list();
		String expiredItem = "";
		for(int i =0;i<list.size();i++){
			Object[] obj = (Object[])list.get(i);
			String itemNo = (String)(obj[0]+"");
			Integer leadTime = obj[1]==null?0:Integer.valueOf(obj[1]+"");
			if(leadTime>0){
				if(!expiredItem.equals("")){
					expiredItem += ",";
				}
				expiredItem +=itemNo;
			}
		}
		return expiredItem;
	}
	
	/*
	 * 根据ItemId 获取itemList
	 */
	public List<OrderItem> searchOrderItemListByItemId(String orderNo,String itemNo){
		String hql = "from OrderItem where orderNo ="+orderNo+" and itemNo in ("+itemNo+")";
		return this.find(hql);
	}
	
	/**
	 * 根据orderNO查找item的id
	 */
	public List<Integer> getItemIdsByOrderNo(Integer orderNo) {
		String hql = "select orderItemId from OrderItem where orderNo=?";
		return this.find(hql,orderNo);
	}
	
	/*
	 * 根据shipmentId得到
	 */
	public List<OrderItem> searchOrderItemListByShipmentId(Integer shipmentId){
		String hql = "select DISTINCT oi from OrderItem oi ,ShipmentLine sl where oi.orderNo = sl.order.orderNo and sl.shipments.shipmentId="+shipmentId;
		return this.find(hql);
	}
}
