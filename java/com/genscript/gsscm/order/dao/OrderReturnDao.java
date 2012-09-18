package com.genscript.gsscm.order.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderReturn;

@Repository
public class OrderReturnDao extends HibernateDao<OrderReturn, Integer>{
	private static final String AMOUNT_BY_ORDER = "select sum(c.shipRefund),sum(c.totalRefund) from OrderReturn c where c.orderNo=?";

	/**
	 * 批量删除OrderReturn, 实际操作为更新status为'INVALID'.
	 * @param ids
	 * @param userId
	 */
	public void delOrderReturnList(Integer rmaNo, Integer userId){
        String hql = "update OrderReturn c set c.status='INVALID', c.modifyDate =?,c.modifiedBy =? where c.rmaNo=?";
		batchExecute(hql, new Date(), userId, rmaNo);
	}

	/**
	 * Process OrderReturn, 实际操作为更新status为'PROCESSED'.
	 * @param ids
	 * @param userId
	 */
	public void processOrderReturnList(Integer rmaNo, Integer userId){
        String hql = "update OrderReturn c set c.status='PROCESSED', c.processDate =?,c.processedBy =? where c.rmaNo=?";
		batchExecute(hql, new Date(), userId, rmaNo);
	}
	

	@SuppressWarnings("rawtypes")
	public List getAmountByOrder(Integer orderNo){
		List list = createQuery(AMOUNT_BY_ORDER, orderNo).list();
		return list;
	}
}
