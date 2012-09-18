package com.genscript.gsscm.product.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.RoyaltyProduct;

@Repository
public class RoyaltyProductDao extends HibernateDao<RoyaltyProduct, Integer> {
	/**
	 * 通过Product'catalogNo 获得 RoyaltyProduct.
	 * @param catalogNo
	 * @return
	 */
	public RoyaltyProduct getRoyaltyProductByCataloNo(String catalogNo) {
		RoyaltyProduct royalty = null;
		String hql = " from RoyaltyProduct where catalogNo=?";
		List<RoyaltyProduct> list = this.find(hql, catalogNo);
		if (list != null && list.size()>0) {
			royalty = list.get(0);
		}
		return royalty;
	}
}
