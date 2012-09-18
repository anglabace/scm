package com.genscript.gsscm.quote.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuoteDsLabels;

/**
 * @author Zhang Yong
 * add date 2011-11-14
 *
 */
@Repository
public class QuoteDsLabelsDao extends HibernateDao<QuoteDsLabels, Integer> {

	/**
	 * 查询唯一值
	 * @author Zhang Yong
	 * add date 2011-11-07
	 * @param code
	 * @param quoteNo
	 * @param custNo
	 * @return
	 */
	public QuoteDsLabels findByCode (String code, Integer quoteNo, Integer custNo) {
		String hql = "FROM QuoteDsLabels WHERE code=:code AND quoteNo=:quoteNo AND custNo=:custNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("quoteNo", quoteNo);
		map.put("custNo", custNo);
		return this.findUnique(hql, map);
	}
	
	/**
	 * 通过name、quoteNo、custNo、status查询，如果status为空则去掉status条件查询
	 * @author Zhang Yong
	 * add date 2011-11-11
	 * @param name
	 * @param quoteNo
	 * @param custNo
	 * @param status
	 * @return
	 */
	public List<QuoteDsLabels> findByName (String name, Integer quoteNo, Integer custNo, String status) {
		String hql = "FROM QuoteDsLabels WHERE name=:name AND quoteNo=:quoteNo AND custNo=:custNo";
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(status)) {
			hql += " AND status=:status";
			map.put("status", status);
		}
		map.put("name", name);
		map.put("quoteNo", quoteNo);
		map.put("custNo", custNo);
		return this.find(hql, map);
	}
	
	/**
	 * 通过quoteNo、custNo查询满足条件的集合
	 * @author Zhang Yong
	 * add date 2011-11-09
	 * @param quoteNo
	 * @param custNo
	 * @return
	 */
	public List<QuoteDsLabels> findByOrderNoAndCustNo (Integer quoteNo, Integer custNo) {
		String hql = "FROM QuoteDsLabels WHERE quoteNo=:quoteNo AND custNo=:custNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		map.put("custNo", custNo);
		return this.find(hql, map);
	}
	
	/**
	 * 批量删除QuoteDsLabels
	 * @author Zhang Yong
	 * add date 2011-11-09
	 * @param quoteNo
	 * @param custNo
	 */
	public void deleteByOrderNoAndCustNo (Integer quoteNo, Integer custNo) {
		String hql = "delete from QuoteDsLabels odl where odl.quoteNo=:quoteNo and odl.custNo=:custNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		map.put("custNo", custNo);
		batchExecute(hql, map);
	}
}
