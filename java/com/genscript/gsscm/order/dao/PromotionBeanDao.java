package com.genscript.gsscm.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.PromotionBean;

@Repository
public class PromotionBeanDao extends HibernateDao<PromotionBean, Integer>{
	private static final String GET_PRMTCODE_LIST = "select p.prmtCode from PromotionBean p where p.orderSource=?";
	public PromotionBean getPromotionDetail(Integer id){
		return getById(id);
	}
	
	public List<String> getPrmtCdListBySourceId(Integer sourceId){
		List<String> list = find(GET_PRMTCODE_LIST, sourceId);
		return list;
	}
}
