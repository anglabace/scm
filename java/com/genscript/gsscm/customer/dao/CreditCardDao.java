package com.genscript.gsscm.customer.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.CreditCard;

@Repository
public class CreditCardDao extends HibernateDao<CreditCard, Integer> {

	/**
	 * 删除多个Card, 实际执行的是置状态为'INACTIVE'.
	 * 
	 * @param userId
	 * @param ids
	 * @return
	 */
	public int delCard(Integer userId, Integer id) {
		String hql = "update CreditCard c set c.status = 'INACTIVE',c.modifyDate =?,c.modifiedBy =? where c.id=?";
		return batchExecute(hql, new Date(), userId, id);
	}

	/**
	 * 获得一个customer有效的CreditCard list.
	 * 
	 * @param custNo
	 * @return
	 */
	public List<CreditCard> getCustActiveCardList(Integer custNo) {
		String hql = "from CreditCard c where c.custNo=:custNo and c.status=:status";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custNo", custNo);
		map.put("status", "ACTIVE");
		return this.find(hql, map);
	}
}
