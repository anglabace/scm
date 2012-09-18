package com.genscript.gsscm.product.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.ProductReference;

@Repository
public class ProductReferenceDao extends HibernateDao<ProductReference, Integer>{

	/*
	 * 删除一串reference
	 * @param List<Integer> ids
	 * @return
	 */
	public void delReferenceList(Object ids){
		String del_rece = "delete from ProductReference c where c.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_rece, map);
	}
}
