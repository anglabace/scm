package com.genscript.gsscm.product.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.RoyaltyLicense;

@Repository
public class RoyaltyLicenseDao extends HibernateDao<RoyaltyLicense, Integer> {
	public RoyaltyLicense getLicenseByRoyalty(Integer royaltyId) {
		String hql = " from RoyaltyLicense where royaltyId=?";
		RoyaltyLicense license = this.findUnique(hql, royaltyId);
		return license;
	}
}
