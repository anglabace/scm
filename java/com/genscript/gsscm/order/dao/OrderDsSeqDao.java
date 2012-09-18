package com.genscript.gsscm.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderDsSeq;

@Repository
public class OrderDsSeqDao extends HibernateDao<OrderDsSeq, Integer> {
	/**
	 * 查询指定type最大的last_seq
	 * @author Zhang Yong
	 * add date 2011-11-07
	 * @param type(type类型有PRIMER、TUBE、PLATE这三种 )
	 * @return 返回值肯定不为空
	 */
	public Integer getDsSeqLast (String type) {
		String hql = "SELECT max(lastSeq) FROM OrderDsSeq WHERE type = ?";
		Integer lastSeq = this.findUnique(hql, type);
		if (lastSeq == null) {
			lastSeq = 0;
		}
		return lastSeq;
	}
	
	/**
	 * 查询指定type和lastSeq的OrderDsSeq
	 * @author Zhang Yong
	 * @param type
	 * @param lastSeq
	 * @return
	 */
	public OrderDsSeq getByTypeAndLastSeq (String type, Integer lastSeq) {
		String hql = "FROM OrderDsSeq WHERE type=:type AND lastSeq=:lastSeq";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("lastSeq", lastSeq);
		return this.findUnique(hql, map);
	}
	
	/**
	 * 批量删除OrderDsSeq
	 * @author Zhang Yong
	 * add date 2011-11-09
	 * @param type
	 * @param lastSeqs
	 */
	public void deleteByTypeAndLastSeqs (String type, List<Integer> lastSeqs) {
		String hql = "delete from OrderDsSeq ds where ds.type=:type and ds.lastSeq in (:lastSeqs)";
//		Map<String, Object> map = Collections.singletonMap("lastSeqs", lastSeqs);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("lastSeqs", lastSeqs);
		batchExecute(hql, map);
	}
}
