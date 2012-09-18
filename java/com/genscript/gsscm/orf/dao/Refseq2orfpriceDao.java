package com.genscript.gsscm.orf.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.orf.entity.Refseq2orfprice;

/**
 * Refseq2orfpriceDao
 * @author Zhang Yong
 * add date 2011-12-02
 */
@Repository
public class Refseq2orfpriceDao extends HibernateDao<Refseq2orfprice, Integer> {
	
	/**
	 * 通过accessionNo查询
	 * @author Zhang Yong
	 * add date 2011-12-02
	 * @param accessionNo
	 * @return
	 */
	public Refseq2orfprice getByAccessionNo (String accessionNo) {
		String hql = "FROM Refseq2orfprice WHERE accession=?";
		return this.findUnique(hql, accessionNo);
	}
}
