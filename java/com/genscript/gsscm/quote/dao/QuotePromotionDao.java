package com.genscript.gsscm.quote.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuotePromotion;

@Repository
public class QuotePromotionDao extends HibernateDao<QuotePromotion, Integer>{
	
	public QuotePromotion findByCodeAndQuote(String pmtCode,Integer quoteNo) {
		String hql = "from QuotePromotion where prmtCode=? and quoteNo=?";
		List<QuotePromotion> list =this.find(hql, pmtCode,quoteNo);
		if(list!=null&&!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public Long findPromotionByCode(String pmtCode) {
		String hql = "select count(*) from QuotePromotion where prmtCode=?";
		List<Long> list =this.find(hql, pmtCode);
		if(list!=null&&!list.isEmpty()) {
			return list.get(0);
		}
		return 0L;
	}
}
