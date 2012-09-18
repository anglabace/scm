package com.genscript.gsscm.customer.dao;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.CustomerNote;

@Repository
public class CustomerNoteDao extends HibernateDao<CustomerNote, Integer> {

	/**
	 * 通过CustNo和Type查询
	 * @author Zhang yong
	 * @param custNo
	 * @param type
	 * @return
	 */
	public List<CustomerNote> findByCustNoType (Integer custNo, String type) {
		String hql = "FROM CustomerNote WHERE custNo = ? and type = ?";
		if (StringUtils.isBlank(type)) {
			hql = "FROM CustomerNote WHERE custNo = ?";
			return this.find(hql, custNo);
		} else {
			return this.find(hql, custNo, type);
		}
	}
}
