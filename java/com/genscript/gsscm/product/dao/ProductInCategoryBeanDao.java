package com.genscript.gsscm.product.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.ProductInCategoryBean;
@Repository
public class ProductInCategoryBeanDao extends HibernateDao<ProductInCategoryBean, Integer>{
	
	public Double getUnitPriceByBaseCatalog(String catalogId, Integer productId){
		Criterion criterion1 = Restrictions.eq("catalogId", catalogId);
		Criterion criterion2 = Restrictions.eq("productId", productId);
		ProductInCategoryBean bean = findUnique(criterion1,criterion2);
		if(bean != null){
			return bean.getUnitPrice();
		}
		return null;
	}
	public ProductInCategoryBean getBeanByBaseCatalog(String catalogId, Integer productId){
		Criterion criterion1 = Restrictions.eq("catalogId", catalogId);
		Criterion criterion2 = Restrictions.eq("productId", productId);
		return findUnique(criterion1,criterion2);
	}
	/**
	 * 根据catalogNo和catalogId查找视图
	 * @author lizhang
	 * @param catalogNo
	 * @param catalogId
	 * @return
	 */
	public ProductInCategoryBean getBeanByCatalog(String catalogNo,String catalogId) {
		Criterion criterion1 = Restrictions.eq("catalogId", catalogId);
		Criterion criterion2 = Restrictions.eq("catalogNo", catalogNo);
		List<ProductInCategoryBean> list = find(criterion1,criterion2);
		if(list!=null&&!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
