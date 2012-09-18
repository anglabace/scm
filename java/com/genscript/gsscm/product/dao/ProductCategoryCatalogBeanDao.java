package com.genscript.gsscm.product.dao;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.ProductCategoryCatalogBean;

@Repository
public class ProductCategoryCatalogBeanDao extends HibernateDao<ProductCategoryCatalogBean, Integer>{
	
	public Page<ProductCategoryCatalogBean> getCatalogNoListByCategory(Page<ProductCategoryCatalogBean> page, String categoryNo){
		Criterion criterion1 = Restrictions.eq("categoryNo", categoryNo);
		return findPage(page, criterion1);
	}
}
