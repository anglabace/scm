package com.genscript.gsscm.basedata.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.entity.PbCountry;

@Repository
public class PbCountryDao extends HibernateDao<PbCountry, Integer> {

	@SuppressWarnings("rawtypes")
	public List getListWithState() {
		List list = null;
		String hql = "SELECT p.country_code, p.name, count(state_id) FROM common.country p left join common.state s on s.country_id=p.country_id  ";
		hql += " group by p.country_code,p.name order by p.country_code";
		SQLQuery query = this.getSession().createSQLQuery(hql);
		list = query.list();
		return list;
	}
	
	/**
	 * 查找pb_country的结果页，
	 * @param salesProjectManagerAssignmentPage
	 * @param filters
	 * @return
	 */
	public Page<PbCountry> findPageBySomeFilter(final Page<PbCountry> countryPage,
			final List<PropertyFilter> filters,List<String> countryCodeList) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		// 判断是否有或的查询条件.
		List<PropertyFilter> delList = new ArrayList<PropertyFilter>();
		int len = filters.size();
		for (int i = 0; i < len; i++) {
			PropertyFilter pf = filters.get(i);
			if(pf.getPropertyName().equals("status")) {
				delList.add(pf);
			}
		}
		filters.removeAll(delList);// 删除原先的条件.
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		for (Criterion temp : criterions) {
			criterionList.add(temp);
		}
		if (countryCodeList != null && !countryCodeList.isEmpty()) {
			Criterion criterion = Restrictions.in("countryCode", countryCodeList);
			criterionList.add(criterion);
		}
		return findPage(countryPage, criterionList.toArray(new Criterion[criterionList
				.size()]));
	}
	
	/**
	 * 查询所有记录，结果集是按continent升序
	 * @author Zhang Yong
	 * @return
	 */
	public List<String> getAllPbCountry () {
		String hql = "SELECT DISTINCT(p.continent) FROM PbCountry p Order By p.continent";
		return find(hql);
	}

}
