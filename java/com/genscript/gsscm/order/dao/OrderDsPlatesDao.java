package com.genscript.gsscm.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderDsPlates;

@Repository
public class OrderDsPlatesDao extends HibernateDao<OrderDsPlates, Integer> {
	
	/**
	 * 通过orderNo查询
	 * @author Zhang Yong
	 * add date 2011-11-01
	 * @param orderNo
	 * @return
	 */
	public List<OrderDsPlates> getDsPlateByOrderNo (Integer orderNo) {
		return this.findBy("orderNo", orderNo);
	}

	/**
	 * 查询唯一值
	 * @author Zhang Yong
	 * add date 2011-11-09
	 * @param orderNo
	 * @param custNo
	 * @param code
	 * @return
	 */
	public OrderDsPlates getDsPlate (Integer orderNo, Integer custNo, String code) {
		String hql = "FROM OrderDsPlates WHERE orderNo=:orderNo AND custNo=:custNo AND code=:code";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("custNo", custNo);
		map.put("code", code);
		return findUnique(hql, map);
	}
}
