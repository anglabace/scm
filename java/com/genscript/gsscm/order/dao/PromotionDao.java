package com.genscript.gsscm.order.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.Promotion;

@Repository
public class PromotionDao extends HibernateDao<Promotion, Integer> {
	private static final String DEL_PROMOTIONS = "delete from Promotion p where p.prmtId in (:ids)";
	
	public int delPromotions(Object ids) {
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		return batchExecute(DEL_PROMOTIONS, map);
	}
	
}
