package com.genscript.gsscm.quote.dao;
 
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.QuoteItemStatusType;
import com.genscript.gsscm.quote.entity.QuoteItem;

@Repository
public class QuoteItemDao extends HibernateDao<QuoteItem, Integer> {
	private static final String getQuantitySumByOrderNo = "select sum(quantity) From QuoteItem WHERE quoteNo=:quoteNo  and  status <>'CN' ";

	public Integer getQuoteItemsCountByQuoteNo(Integer quoteNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		List<Long> custCount = this.find(getQuantitySumByOrderNo, map);
		Integer cont = 0;
		if (custCount.get(0) != null) {
			cont = custCount.get(0).intValue();
		}
		return cont;
	}

	public List<QuoteItem> getItemList(Integer quoteNo) {
		String hql = "from QuoteItem where quoteNo=:quoteNo order by itemNo ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		return this.find(hql, map);
	}
	
	/**
	 * 通过parentId查询查询
	 * @author Zhang Yong
	 * modify date 2011-12-06
	 * @param parentId
	 * @return
	 */
	public List<QuoteItem> getByParentId(Integer parentId){
		String hql = "from QuoteItem where parentId=? ";
		return this.find(hql, parentId);
	}

	/**
	 * quote下所有的itemNo统一增加一个增量，默认为10000，目前用于保存时候临时使用，否则会出现Duplicate key 问题
	 * 
	 * @param quoteNo
	 * @author zouyulu
	 */
	public void updateQuoteItemNo(Integer quoteNo, Integer addCount) {
		if (addCount == null) {
			addCount = 10000;
		}
		String hql = "update QuoteItem set itemNo=itemNo+:addCount where quoteNo=:quoteNo";
		this.createQuery(hql).setInteger("addCount", addCount)
				.setInteger("quoteNo", quoteNo).executeUpdate();
		// this.getSession().createSQLQuery(sql).setInteger("addCount",
		// addCount).setInteger("orderNo", orderNo).executeUpdate();
	}

	/**
	 * 获得非Canceled|Returned的itemlist for repeat quote.
	 * 
	 * @param quoteNo
	 * @return
	 */
	public List<QuoteItem> getItemListForRepeat(Integer quoteNo) {
		String hql = "from QuoteItem where quoteNo=:quoteNo and status<>:cnstatus order by itemNo ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		map.put("cnstatus", QuoteItemStatusType.CN.value());
		return this.find(hql, map);
	}

	/**
	 * 获得QuoteItem的数量.
	 * 
	 * @param quoteNo
	 * @param shiptoAddrId
	 * @return
	 */
	public Long getQuoteItemCountByAddrId(Integer quoteNo, Integer shiptoAddrId) {
		String hql = "select count(quoteItemId) from QuoteItem where quoteNo=:quoteNo and shiptoAddrId=:shiptoAddrId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		map.put("shiptoAddrId", shiptoAddrId);
		return this.findUnique(hql, map);
	}

	/**
	 * 获得一个Quote所有状态的QuoteItem.
	 * 
	 * @param quoteNo
	 * @return
	 */
	public List<QuoteItem> getQuoteAllItemList(Integer quoteNo) {
		String hql = "from QuoteItem where quoteNo=:quoteNo order by itemNo ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		return this.find(hql, map);
	}

	/**
	 * 获得Order不是某些status的所有OrderItem.
	 */
	public List<QuoteItem> getItemListNotInType(Integer quoteNo,
			List<String> itemStatus) {
		String hql = "from QuoteItem where quoteNo=:quoteNo AND status not in(:status) order by itemNo ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		map.put("status", itemStatus);
		return this.find(hql, map);
	}

    /*
    * add by zhanghuibin
    *
    * 这里默认添加的quote item保存在数据库里的item_no 顺序即为按主服务、子服务、主服务、子服务...的顺序的
    * */
    @SuppressWarnings("unchecked")
	public List<QuoteItem> getItemListForPrintTXT(Integer quoteNo,
			List<String> itemStatus) {
		String hql = "select {qu.*} from (" + 
                "select * from order.quote_items WHERE cls_id in (3,4,5,6,7,8,9,10,38) and quote_no=:quoteNo AND status not in(:status)" +
                "union " +
                "select * from order.quote_items where cls_id in (4,9,10) and parent_item_id is not null " +
                "and parent_item_id in (select quote_item_id from order.quote_items where cls_id in (3,4,5,6,7,8,9,10,38) ) and quote_no=:quoteNo AND status not in(:status)" +
                ") as qu  order by item_no ASC";
        Query query =this.getSession().createSQLQuery(hql).addEntity("qu", QuoteItem.class).setParameter("quoteNo", quoteNo).setParameter("status", itemStatus);
		return query.list();
	}

	/**
	 * 通过主键查询
	 * 
	 * @param quoteItemId
	 * @return
	 * @author zhangyong
	 */
	public QuoteItem searchQuoteItemByItemId(Integer quoteItemId) {
		return this.findUniqueBy("quoteItemId", quoteItemId);
	}

	/**
	 * 批量删除OrderItem.
	 * 
	 * @param ids
	 */
	public void delQuoteItemList(Object ids) {
		String hql = "delete from QuoteItem c where c.quoteItemId in (:ids)";
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}

	public QuoteItem getQuoteItem(final Integer quoteNo,
			final Integer quoteItemNo) {
		String hql = "from QuoteItem q where q.quoteNo=:quoteNo and q.itemNo=:itemNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		map.put("itemNo", quoteItemNo);
		List<QuoteItem> quoteItemList = find(hql, map);
		if (quoteItemList != null && !quoteItemList.isEmpty()) {
			return quoteItemList.get(0);
		}
		return null;
	}

	public List<QuoteItem> getAllItemsByquoteNo(int quoteNo) {
		String hql = "from QuoteItem q where q.quoteNo=:quoteNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		List<QuoteItem> quoteItemList = find(hql, map);
		if (quoteItemList != null && !quoteItemList.isEmpty()) {
			return quoteItemList;
		} 
		return null;
	}
	
	/**
	 * 通过quoteNo查询QuoteItem的catalog信息
	 * @author Zhang Yong
	 * @param quoteNo
	 * @return
	 */
	public List<Object[]> getCatalogByOrderNo (Integer quoteNo) {
		String hql = "SELECT distinct(a.catalogId), concat(a.catalogId,' : ',a.catalogName) FROM Catalog a, QuoteItem b where b.quoteNo=? and b.catalogId = a.catalogId";
		return this.find(hql, quoteNo);
	}
	
	/**
	 * 查询指定quoteNo下的shipMethod不匹配shipMethodName的item
	 * @author Zhang Yong 
	 * add date 2011-10-28
	 * @param quoteNo
	 * @param shipMethodName
	 * @return
	 */
	public List<QuoteItem> getNotFedExItem (Integer quoteNo, String shipMethodName) {
		String hql = "SELECT qi FROM QuoteItem qi, ShipMethod s WHERE qi.quoteNo=:quoteNo " +
				"AND qi.status !=:status AND qi.shipMethod = s.methodId AND s.carrier !=:carrier";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		map.put("status", QuoteItemStatusType.CN.value());
		map.put("carrier", shipMethodName);
		return find(hql, map);
	}
}
