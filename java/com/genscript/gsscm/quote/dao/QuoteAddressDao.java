package com.genscript.gsscm.quote.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuoteAddress;

@Repository
public class QuoteAddressDao extends HibernateDao<QuoteAddress, Integer>{

	private static final String ADDRESS_LIST = "from QuoteAddress q where q.quoteNo=?";
	
	public List<QuoteAddress> getQuoteAddressList(final Integer quoteNo){
		return find(ADDRESS_LIST, quoteNo);
	}
	
	public List<QuoteAddress> getAddrByQuoteNo(Integer quoteNo) {
		String hql = "select o from QuoteAddress o where o.quoteNo=?";
		return this.find(hql, quoteNo);
	}
	
	public QuoteAddress getAddrByQuoteNoAndType(Integer quoteNo,String addrType) {
		String hql = "select o from QuoteAddress o where o.quoteNo=? and addrType=?";
		return this.findUnique(hql, quoteNo,addrType);
	}
}
