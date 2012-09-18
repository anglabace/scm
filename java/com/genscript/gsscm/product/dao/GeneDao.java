package com.genscript.gsscm.product.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.Gene;

@Repository
public class GeneDao extends HibernateDao<Gene, Integer> {

	/**
	 * 通过accessionNo查询Gene列表
	 * @author Zhang Yong
	 * @param accessionNo
	 * @return
	 */
	public List<Gene> findByAccessionNo (String accessionNo) {
		String hql = "from Gene where accessionNo = ?";
		return this.find(hql,accessionNo);
	}
}
