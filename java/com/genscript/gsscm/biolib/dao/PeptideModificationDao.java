package com.genscript.gsscm.biolib.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.biolib.entity.PeptideModification;

@Repository
public class PeptideModificationDao extends HibernateDao<PeptideModification, Integer> {

	/**
	 * 通过name和type查询
	 * @author Zhang Yong
	 * @param name
	 * @param type
	 * @return List<PeptideModification>
	 */
	public List<PeptideModification> findByNameAndType (String name, String type) {
		String hql = "FROM PeptideModification WHERE name=? and type=?";
		return find(hql, name, type);
	}
}
