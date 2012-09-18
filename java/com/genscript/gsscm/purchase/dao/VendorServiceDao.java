package com.genscript.gsscm.purchase.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.purchase.entity.VendorServiceEntity;

@Repository
public class VendorServiceDao extends HibernateDao<VendorServiceEntity, Integer> {
	public void delSupplierList(Object ids) {
		String hql = "delete from VendorService c where c.id in (:ids)";
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}

}
