package com.genscript.gsscm.customer.dao.webbehavior;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.Visit;

@Repository
public class VisitDao extends HibernateDao<Visit, Integer> {
	public Long getVisitTotal(Integer custNo) {
		String hql = "select count(*) from Visit where custNo=:custNo";		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custNo", custNo);
		return this.findUnique(hql, map);
	}
	
	public Long getVisitPagesTotal(Integer custNo) {
		String hql = "select sum(pageVisited) from Visit where custNo=:custNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custNo", custNo);
		return this.findUnique(hql, map);
	}
	
	public Visit getFirstVisit(Integer custNo) {
		Visit visit = null;
		String hql = "from Visit where custNo=:custNo order by inTime asc";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custNo", custNo);
		Page<Visit> page = new Page<Visit>();
		page.setPageNo(1);
		page.setPageSize(1);
		page = this.findPage(page, hql, map);
		if (page.getResult()!=null && !page.getResult().isEmpty()) {
			visit = page.getResult().get(0);
		}
		return visit;
	}
	
	public Visit getLastVisit(Integer custNo) {
		Visit visit = null;
		String hql = "from Visit where custNo=:custNo order by inTime desc";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custNo", custNo);
		Page<Visit> page = new Page<Visit>();
		page.setPageNo(1);
		page.setPageSize(1);
		page = this.findPage(page, hql, map);
		if (page.getResult()!=null && !page.getResult().isEmpty()) {
			visit = page.getResult().get(0);
		}
		return visit;
	}
	
	public Long getTotalVisitTime(Integer custNo) {
		String hql = "select sum(staySeconds) from Visit where custNo=:custNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custNo", custNo);
		return this.findUnique(hql, map);
	}
}
