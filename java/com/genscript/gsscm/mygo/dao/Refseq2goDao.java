package com.genscript.gsscm.mygo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.mygo.entity.Refseq2go;

@Repository
public class Refseq2goDao extends HibernateDao<Refseq2go,String> {

	/**
	 * 通过code查询列表
	 * @author Zhang Yong
	 * @param codes
	 * @return
	 */
	public List<Refseq2go> findByCodes (String codes) {
		String hql = "from Refseq2go where code in ("+codes+")";
		return this.find(hql);
	}
}
