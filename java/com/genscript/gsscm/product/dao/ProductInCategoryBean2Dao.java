package com.genscript.gsscm.product.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.ProductInCategoryBean2;
@Repository
public class ProductInCategoryBean2Dao extends HibernateDao<ProductInCategoryBean2, Integer>{
	
	/**
	 * 根据catalogNo和catalogId查找视图
	 * @author lizhang
	 * @param catalogNo
	 * @param catalogId
	 * @return
	 */
	public ProductInCategoryBean2 getBeanByCatalog(String catalogNo,String catalogId) {
		Criterion criterion1 = Restrictions.eq("catalogId", catalogId);
		Criterion criterion2 = Restrictions.eq("catalogNo", catalogNo);
		List<ProductInCategoryBean2> list = find(criterion1,criterion2);
		if(list!=null&&!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
