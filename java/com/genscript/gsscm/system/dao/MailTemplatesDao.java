package com.genscript.gsscm.system.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.system.entity.MailTemplates;

/**
 * 
 * @author zhangyong
 *
 */
@Repository
public class MailTemplatesDao extends HibernateDao<MailTemplates, Integer> {

	public List<MailTemplates> findbyFuncName (String functionName) {
		return this.find("from MailTemplates where functionName = ?",functionName);
	}
	
	/**
	 * fangquan
	 * @param template_name
	 * @param functionName
	 * @return
	 */
	public MailTemplates findby (String template_name,String functionName) {
		String hql="select m from  MailTemplates m where m.functionName = '"+functionName+"' and m.templateName='"+template_name+"'";
		return this.findUnique(hql);
	}
}
