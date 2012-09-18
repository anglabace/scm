package com.genscript.gsscm.quote.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuoteDsSeq;

@Repository
public class QuoteDsSeqDao extends HibernateDao<QuoteDsSeq, Integer> {
	/**
	 * 查询指定type最大的last_seq
	 * @author Zhang Yong
	 * add date 2011-11-14
	 * @param type(type类型有PRIMER、TUBE、PLATE这三种 )
	 * @return 返回值肯定不为空
	 */
	public Integer getDsSeqLast (String type) {
		String hql = "SELECT max(lastSeq) FROM QuoteDsSeq WHERE type = ?";
		Integer lastSeq = this.findUnique(hql, type);
		if (lastSeq == null) {
			lastSeq = 0;
		}
		return lastSeq;
	}
	
	/**
	 * 查询指定type和lastSeq的QuoteDsSeq
	 * @author Zhang Yong
	 * add date 2011-11-14
	 * @param type
	 * @param lastSeq
	 * @return
	 */
	public QuoteDsSeq getByTypeAndLastSeq (String type, Integer lastSeq) {
		String hql = "FROM QuoteDsSeq WHERE type=:type AND lastSeq=:lastSeq";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("lastSeq", lastSeq);
		return this.findUnique(hql, map);
	}
	
	/**
	 * 批量删除QuoteDsSeq
	 * @author Zhang Yong
	 * add date 2011-11-14
	 * @param type
	 * @param lastSeqs
	 */
	public void deleteByTypeAndLastSeqs (String type, List<Integer> lastSeqs) {
		String hql = "delete from QuoteDsSeq ds where ds.type=:type and ds.lastSeq in (:lastSeqs)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("lastSeqs", lastSeqs);
		batchExecute(hql, map);
	}
}
