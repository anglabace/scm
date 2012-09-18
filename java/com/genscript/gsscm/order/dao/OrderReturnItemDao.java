package com.genscript.gsscm.order.dao;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.OrderReturnStatusType;
import com.genscript.gsscm.order.entity.OrderReturnItem;

@Repository
public class OrderReturnItemDao extends HibernateDao<OrderReturnItem, Integer>{
	private static final String DEL_ITEMLIST = "delete from OrderReturnItem c where c.itemId in (:ids)";
	public void delItemList(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DEL_ITEMLIST, map);
	}
	/**
	 * 获得某个Product Unprocessed Returns 的总和.
	 * @param catalogNo
	 * @return
	 */
	public Long getUnprocessedTotal(String catalogNo) {
		String hql = "select sum(item.returnQty) from OrderReturnItem item, OrderReturn po where item.rmaNo=po.rmaNo and po.status=:status and item.catalogNo=:catalogNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		map.put("status", OrderReturnStatusType.UNPROCESSED.value());
		return this.findUnique(hql, map);
	}

	/**
	 * 获得一个OrderReturn的退单明细(用于生成退货的Order).
	 * @param rmaNo
	 * @return
	 */
	public List<OrderReturnItem> getReplaceItem(Integer rmaNo) {
		String hql = "from OrderReturnItem item where item.rmaNo=? and item.status=? and item.exchangeFlag='Y'";		
		return this.find(hql, rmaNo, OrderReturnStatusType.PROCESSED.value());
	}
	
	/**
	 * Process OrderReturnItem, 实际操作为更新status为'PROCESSED'.
	 * @param ids
	 * @param userId
	 */
	public void processReturnItemList(Integer rmaNo, Integer userId){
        String hql = "update OrderReturnItem c set c.status=?, c.modifyDate =?,c.modifiedBy =? where c.rmaNo=?";
		batchExecute(hql,OrderReturnStatusType.PROCESSED.value(), new Date(), userId, rmaNo);
	}
}
