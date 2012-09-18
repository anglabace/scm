package com.genscript.gsscm.serv.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.RoyaltyService;

@Repository
public class RoyaltyServiceDao extends HibernateDao<RoyaltyService, Integer> {

	/**
	 * 通过Service'catalogNo 获得 RoyaltyService.
	 * @param catalogNo
	 * @return
	 */
	public RoyaltyService getRoyaltyServiceByCataloNo(String catalogNo) {
		RoyaltyService royalty = null;
		String hql = " from RoyaltyService where catalogNo=?";
		List<RoyaltyService> list = this.find(hql, catalogNo);
		if (list != null && list.size()>0) {
			royalty = list.get(0);
		}
		return royalty;
	}
}
