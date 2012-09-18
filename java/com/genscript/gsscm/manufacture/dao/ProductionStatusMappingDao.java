package com.genscript.gsscm.manufacture.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.Operation;
import com.genscript.gsscm.manufacture.entity.ProductionStatusMapping;

@Repository
public class ProductionStatusMappingDao extends
		HibernateDao<ProductionStatusMapping, Integer> {
	/**
	 * 分页
	 * 
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<ProductionStatusMapping> findPageForCenter(
			Page<ProductionStatusMapping> page,
			final List<PropertyFilter> filters, boolean flg) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		if (flg) {
			Criterion idCriterion = Restrictions.ne("id", -1);
			criterionList.add(idCriterion);
		}
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		for (Criterion temp : criterions) {
			criterionList.add(temp);
		}

		return findPage(page,
				criterionList.toArray(new Criterion[criterionList.size()]));
	}

	/**
	 * 删除
	 * 
	 * @param quoteNo
	 * @param statusReason
	 * @param userId
	 * @param comment
	 */
	public void delProduction(String produtionIds) {
		String hql = "update from ProductionStatusMapping p set p.status='Inactive' where p.id in ("
				+ produtionIds + ")";
		batchExecute(hql);
	}

	/**
	 * 保存
	 */

	public void saveProduction(ProductionStatusMapping productionStatusMapping)
			throws Exception {
		Assert.notNull(productionStatusMapping, "entity can not be empty");
		this.getSession().save(productionStatusMapping);
		logger.debug("save entity: {}", productionStatusMapping);
	}

	/**
	 * 更新
	 */
	public void updateProduction(ProductionStatusMapping productionStatusMapping)
			throws Exception {
		Assert.notNull(productionStatusMapping, "entity can not be empty");
		this.getSession().update(productionStatusMapping);
		logger.debug("update entity: {}", productionStatusMapping);
	}


	/**
	 * 根据ID查询对象
	 */
	public ProductionStatusMapping getProductionStatusMapping(Integer id) {
		Criterion criterion = Restrictions.idEq(id);
		return this.findUnique(criterion);
	}
}
