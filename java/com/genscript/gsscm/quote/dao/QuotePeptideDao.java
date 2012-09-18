package com.genscript.gsscm.quote.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuotePeptide;

@Repository
public class QuotePeptideDao extends HibernateDao<QuotePeptide, Integer>{
	
	/**
	 * 批量删除quoteItemId的quote peptide.
	 * @param ids
	 */
	public void delQuoteItemList(Object ids){
		String hql = "delete from QuotePeptide c where c.quoteItemId in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}
}
