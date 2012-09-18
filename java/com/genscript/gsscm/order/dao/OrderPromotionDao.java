package com.genscript.gsscm.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderPromotion;

@Repository
public class OrderPromotionDao extends HibernateDao<OrderPromotion, Integer>{
	public OrderPromotion findByCodeAndOrder(String pmtCode,Integer orderNo) {
		String hql = "from OrderPromotion where prmtCode=? and orderNo=?";
		List<OrderPromotion> list =this.find(hql, pmtCode,orderNo);
		if(list!=null&&!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public Long findPromotionByCode(String pmtCode) {
		String hql = "select count(*) from OrderPromotion where prmtCode=?";
		List<Long> list =this.find(hql, pmtCode);
		if(list!=null&&!list.isEmpty()) {
			return list.get(0);
		}
		return 0L;
	}
}
