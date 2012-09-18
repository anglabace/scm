package com.genscript.gsscm.product.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.ProductSpecialPrice;

@Repository
public class ProductSpecialPriceDao extends HibernateDao<ProductSpecialPrice, Integer> {
	public void delSpecialPriceList(Object ids) {
		String hql = "delete from ProductSpecialPrice c where c.id in (:ids)";
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}
	
	public List<ProductSpecialPrice> getProductSpecailPricingList(Integer productId){
		Criterion criterion = Restrictions.eq("status", "ACTIVE");
		Criterion criterion2 = Restrictions.eq("productId", productId);
		return find(criterion, criterion2);
	}
	
}
