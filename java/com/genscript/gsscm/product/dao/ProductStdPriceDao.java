package com.genscript.gsscm.product.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.StatusType;
import com.genscript.gsscm.product.entity.ProductStdPriceBean;

@Repository
public class ProductStdPriceDao extends
		HibernateDao<ProductStdPriceBean, Integer> {
	@SuppressWarnings("unchecked")
	public List<ProductStdPriceBean> searchStdPriceList(String catalogNo,
			String name, List<String> catalogNoList) {
		Criteria criteria = this.getSession().createCriteria(ProductStdPriceBean.class);
		if (catalogNo != null && catalogNo.trim().length()>0) {
			criteria.add(Restrictions.like("catalogNo", "%"+catalogNo+"%"));
		}
		if (catalogNoList != null && !catalogNoList.isEmpty()) {
			criteria.add(Restrictions.not(Restrictions.in("catalogNo",
					catalogNoList)));
		}
		if (name!=null && name.trim().length()>0) {
			criteria.add(Restrictions.like("name", "%"+name+"%"));
		}
                //add by Lichun Cui 2010-10-222
                criteria.add(Restrictions.eq("baseCatalog", "Y"));
                criteria.add(Restrictions.eq("catalogStatus", StatusType.ACTIVE.value()));
                criteria.add(Restrictions.eq("status",  StatusType.ACTIVE.value()));
		return criteria.list();
	}
}
