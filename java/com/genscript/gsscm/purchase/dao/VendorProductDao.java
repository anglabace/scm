package com.genscript.gsscm.purchase.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.purchase.entity.VendorProduct;

@Repository
public class VendorProductDao extends HibernateDao<VendorProduct, Integer> {
	public void delSupplierList(Object ids) {
		String hql = "delete from VendorProduct c where c.id in (:ids)";
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}

}
