package com.genscript.gsscm.manufacture.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.QaGroup;

@Repository
public class QaGroupDao extends HibernateDao<QaGroup, Integer> {
	
	/**
	 * 根据filters里保存的条件查询QaGroup并分页
	 * @param qaGroupPage 分页对象
	 * @param filters 查询条件
	 * @param criterion clerkName过滤条件
	 * @return
	 */
	public Page<QaGroup> findPageByFilter(final Page<QaGroup> qaGroupPage,final List<PropertyFilter> filters,Criterion criterion) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		if(criterion!=null) {
			criterionList.add(criterion);
		}
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		for (Criterion temp : criterions) {
			criterionList.add(temp);
		}
		return findPage(qaGroupPage, criterionList.toArray(new Criterion[criterionList
				.size()]));
		
	}
	
	public List<QaGroup> findBy(final String propertyName, final Object value,final String order,String orderBy) {
		String hql = "from QaGroup where "+propertyName+"=? order by "+orderBy+" "+order;
		return this.find(hql, value);
	}
	
	/**
	 * 查询所有WorkCenter对象
	 */
	public List<QaGroup> findAll(String order,String orderBy) {
		String hql = "from QaGroup order by "+orderBy+" "+order;
		return this.find(hql);
	}
	
	public List<QaGroup> findByAssigned(List<Integer> clsIdList,List<String> itemType) {
		StringBuffer hql = new StringBuffer("select distinct t from QaGroup t,QaGroupAssigned t2  where 1=1 and t.id=t2.qaGroup.id and t.groupFunction='QC' ");
		if(clsIdList!=null&&clsIdList.size()>0) {
			for(int i = 0;i<clsIdList.size();i++) {
				if(i==0) {
					hql.append(" and ((t2.itemType='").append(itemType.get(i)).append("' and t2.clsId=").append(clsIdList.get(i)).append(") ");
				} else {
					hql.append(" or (t2.itemType='").append(itemType.get(i)).append("' and t2.clsId=").append(clsIdList.get(i)).append(") ");
				}
				
			}
			hql.append(")");
		}
		return this.find(hql.toString());
	}

}
