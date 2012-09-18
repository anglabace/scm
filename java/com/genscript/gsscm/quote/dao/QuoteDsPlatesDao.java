package com.genscript.gsscm.quote.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuoteDsPlates;

@Repository
public class QuoteDsPlatesDao extends HibernateDao<QuoteDsPlates, Integer> {
	
	/**
	 * 通过quoteNo查询
	 * @author Zhang Yong
	 * add date 2011-11-02
	 * @param quoteNo
	 * @return
	 */
	public List<QuoteDsPlates> getDsPlateByQuoteNo (Integer quoteNo) {
		return this.findBy("quoteNo", quoteNo);
	}

	/**
	 * 查询唯一值
	 * @author Zhang Yong
	 * add date 2011-11-14
	 * @param quoteNo
	 * @param custNo
	 * @param code
	 * @return
	 */
	public QuoteDsPlates getDsPlate (Integer quoteNo, Integer custNo, String code) {
		String hql = "FROM QuoteDsPlates WHERE quoteNo=:quoteNo AND custNo=:custNo AND code=:code";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		map.put("custNo", custNo);
		map.put("code", code);
		return findUnique(hql, map);
	}
}
