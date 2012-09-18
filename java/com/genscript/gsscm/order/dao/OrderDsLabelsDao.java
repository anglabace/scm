package com.genscript.gsscm.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderDsLabels;

/**
 * @author Zhang Yong
 * add date 2011-11-07
 *
 */
@Repository
public class OrderDsLabelsDao extends HibernateDao<OrderDsLabels, Integer> {

	/**
	 * 查询唯一值
	 * @author Zhang Yong
	 * add date 2011-11-07
	 * @param code
	 * @param orderNo
	 * @param custNo
	 * @return
	 */
	public OrderDsLabels findByCode (String code, Integer orderNo, Integer custNo) {
		String hql = "FROM OrderDsLabels WHERE code=:code AND orderNo=:orderNo AND custNo=:custNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("orderNo", orderNo);
		map.put("custNo", custNo);
		return this.findUnique(hql, map);
	}
	
	/**
	 * 通过name、orderNo、custNo、status查询，如果status为空则去掉status条件查询
	 * @author Zhang Yong
	 * add date 2011-11-11
	 * @param name
	 * @param orderNo
	 * @param custNo
	 * @param status
	 * @return
	 */
	public List<OrderDsLabels> findByName (String name, Integer orderNo, Integer custNo, String status) {
		String hql = "FROM OrderDsLabels WHERE name=:name AND orderNo=:orderNo AND custNo=:custNo";
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(status)) {
			hql += " AND status=:status";
			map.put("status", status);
		}
		map.put("name", name);
		map.put("orderNo", orderNo);
		map.put("custNo", custNo);
		return this.find(hql, map);
	}
	
	/**
	 * 通过OrderNo、custNo查询满足条件的集合
	 * @author Zhang Yong
	 * add date 2011-11-09
	 * @param orderNo
	 * @param custNo
	 * @return
	 */
	public List<OrderDsLabels> findByOrderNoAndCustNo (Integer orderNo, Integer custNo) {
		String hql = "FROM OrderDsLabels WHERE orderNo=:orderNo AND custNo=:custNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("custNo", custNo);
		return this.find(hql, map);
	}
	
	/**
	 * 批量删除OrderDsLabels
	 * @author Zhang Yong
	 * add date 2011-11-09
	 * @param orderNo
	 * @param custNo
	 */
	public void deleteByOrderNoAndCustNo (Integer orderNo, Integer custNo) {
		String hql = "delete from OrderDsLabels odl where odl.orderNo=:orderNo and odl.custNo=:custNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("custNo", custNo);
		batchExecute(hql, map);
	}
}
