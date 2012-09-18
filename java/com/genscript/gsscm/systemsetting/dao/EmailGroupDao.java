package com.genscript.gsscm.systemsetting.dao;


import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.privilege.entity.EmailGroup;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmailGroupDao extends HibernateDao<EmailGroup, Integer> {

    public List<EmailGroup> getStateList(){
		List<EmailGroup> stateList=null;
		String hql="from mail_group s where 1=1";
		stateList=this.find(hql);
		return stateList;
	}

    public void batchDelEmaiGroup(Integer groupId){
        String sql = " delete from EmailGroup where groupId = :groupId ";
        int retsult = this.getSession().createQuery(sql).setParameter("groupId",  groupId).executeUpdate();
    }

    public Page<EmailGroup> findPageByFilter(final Page<EmailGroup> emailGroupPage,final List<PropertyFilter> filters) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		for (Criterion temp : criterions) {
			criterionList.add(temp);
		}
		return findPage(emailGroupPage, criterionList.toArray(new Criterion[criterionList.size()]));
	}

    public EmailGroup getEmailGroupByName(String name){
        String hql = "from EmailGroup where groupName=:groupName";
        Query query = this.getSession().createQuery(hql).setParameter("groupName", name);
        List<EmailGroup> groupList = query.list();
        return groupList.get(0);
    }
    
    public EmailGroup getEmailGroupByNameAndFunction(String name,String function){
        String hql = "from EmailGroup where groupName=? and groupFunction=?";
        List<EmailGroup> groupList =  this.find(hql, name,function);
        return groupList!=null&&groupList.size()>0?groupList.get(0):null;
    }
}
