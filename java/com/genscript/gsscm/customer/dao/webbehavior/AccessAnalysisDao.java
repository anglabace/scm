package com.genscript.gsscm.customer.dao.webbehavior;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.PageViewType;
import com.genscript.gsscm.customer.entity.AccessAnalysis;

@Repository
public class AccessAnalysisDao extends HibernateDao<AccessAnalysis, Integer> {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<AccessAnalysis> getPageView(Page<AccessAnalysis> page,
			Integer custNo) {
		String hql = "SELECT visitPage, count(*) , min(visitTime) , max(visitTime) , avg(staySeconds) from AccessAnalysis";
		hql += " WHERE custNo=:custNo GROUP BY visitPage";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custNo", custNo);
		Query q = createQuery(hql, map);
		if (page.isAutoCount()) {
			long totalCount = getPageViewTotal(hql, custNo);
			page.setTotalCount(totalCount);
		}
		setPageParameter(q, page);
		List result = q.list();
		page.setResult(result);
		return page;
	}

	protected long getPageViewTotal(String hql, Integer custNo) {
		// TODO Auto-generated method stub
		Long count = 0L;
		try {
			String countHql = "select count(*) from ((";
			countHql += " SELECT visit_page, count(*) , min( visit_time ) , max( visit_time ) , avg( stay_seconds ) ";
			countHql += " FROM web_behavior.access_log_anls WHERE cust_no =:custNo AND visit_page IS NOT NULL AND visit_page <> '' GROUP BY visit_page) a)";
			SQLQuery query = this.getSession().createSQLQuery(countHql);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("custNo", custNo);
			query.setProperties(map);
			BigInteger temp = (BigInteger) query.uniqueResult();
			count = temp.longValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"AccessAnalysisDao countHqlResult error !");
		}
		return count;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<AccessAnalysis> getProductView(Page<AccessAnalysis> page,
			Integer custNo) {
		String hql = "SELECT categoryId, count(categoryId) , min(visitTime) , max(visitTime) from AccessAnalysis";
		hql += " WHERE custNo=:custNo and categoryType=:categoryType GROUP BY categoryId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custNo", custNo);
		map.put("categoryType", PageViewType.PRODUCT.value());
		Query q = createQuery(hql, map);
		if (page.isAutoCount()) {
			long totalCount = getViewTotalByType(hql, custNo,
					PageViewType.PRODUCT);
			page.setTotalCount(totalCount);
		}
		setPageParameter(q, page);
		List result = q.list();
		page.setResult(result);
		return page;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<AccessAnalysis> getServiceView(Page<AccessAnalysis> page,
			Integer custNo) {
		String hql = "SELECT categoryId, count(categoryId) , min(visitTime) , max(visitTime) from AccessAnalysis";
		hql += " WHERE custNo=:custNo and categoryType=:categoryType GROUP BY categoryId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custNo", custNo);
		map.put("categoryType", PageViewType.SERVICE.value());
		Query q = createQuery(hql, map);
		if (page.isAutoCount()) {
			long totalCount = getViewTotalByType(hql, custNo,
					PageViewType.SERVICE);
			page.setTotalCount(totalCount);
		}
		setPageParameter(q, page);
		List result = q.list();
		page.setResult(result);
		return page;
	}

	protected long getViewTotalByType(String hql, Integer custNo,
			PageViewType pageViewType) {
		// TODO Auto-generated method stub
		Long count = 0L;
		try {
			String countHql = "select count(*) from ((";
			countHql += " SELECT category_id, count( category_id ) , min( visit_time ) , max( visit_time ) , avg( stay_seconds ) ";
			countHql += " FROM web_behavior.access_log_anls WHERE cust_no =:custNo and category_type=:categoryType GROUP BY category_id) a)";
			SQLQuery query = this.getSession().createSQLQuery(countHql);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("custNo", custNo);
			map.put("categoryType", pageViewType.value());
			query.setProperties(map);
			BigInteger temp = (BigInteger) query.uniqueResult();
			count = temp.longValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"AccessAnalysisDao countHqlResult error !");
		}
		return count;
	}

	public long getAnalysisVisits(Integer custNo, String fromDate, String toDate) {
		Long count = 0L;
		try {
			String countHql = " SELECT COUNT(*) FROM web_behavior.access_log_anls WHERE (cust_no=:custNo) AND visit_time";
			countHql += " >:fromDate AND visit_time<=:toDate";
			SQLQuery query = this.getSession().createSQLQuery(countHql);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("custNo", custNo);
			map.put("fromDate", fromDate);
			map.put("toDate", toDate);
			query.setProperties(map);
			BigInteger temp = (BigInteger) query.uniqueResult();
			count = temp.longValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"AccessAnalysisDao countHqlResult error !");
		}
		return count;
	}

	public long getAnalysisRefer(Integer custNo, String fromDate, String toDate) {
		Long count = 0L;
		try {
			String countHql = " SELECT COUNT(*) FROM web_behavior.access_log_anls WHERE cust_no=:custNo AND refer IS NOT NULL AND TRIM(refer) <> '' AND visit_time";
			countHql += " >:fromDate AND visit_time<=:toDate";
			SQLQuery query = this.getSession().createSQLQuery(countHql);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("custNo", custNo);
			map.put("fromDate", fromDate);
			map.put("toDate", toDate);
			query.setProperties(map);
			BigInteger temp = (BigInteger) query.uniqueResult();
			count = temp.longValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"AccessAnalysisDao countHqlResult error !");
		}
		return count;
	}

	public long getAnalysisSearching(Integer custNo, String fromDate,
			String toDate) {
		Long count = 0L;
		try {
			String countHql = " SELECT COUNT(*) FROM web_behavior.access_log_anls WHERE cust_no=:custNo AND search_term IS NOT NULL AND TRIM(search_term) <> '' AND visit_time";
			countHql += " >:fromDate AND visit_time<=:toDate";
			SQLQuery query = this.getSession().createSQLQuery(countHql);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("custNo", custNo);
			map.put("fromDate", fromDate);
			map.put("toDate", toDate);
			query.setProperties(map);
			BigInteger temp = (BigInteger) query.uniqueResult();
			count = temp.longValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"AccessAnalysisDao countHqlResult error !");
		}
		return count;
	}

	public long getAnalysisPageViewed(Integer custNo, String fromDate,
			String toDate) {
		Long count = 0L;
		try {
			String countHql = "SELECT COUNT(DISTINCT visit_page) FROM web_behavior.access_log_anls WHERE cust_no=:custNo AND visit_time";
			countHql += " >:fromDate AND visit_time<=:toDate";
			SQLQuery query = this.getSession().createSQLQuery(countHql);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("custNo", custNo);
			map.put("fromDate", fromDate);
			map.put("toDate", toDate);
			query.setProperties(map);
			BigInteger temp = (BigInteger) query.uniqueResult();
			count = temp.longValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("getAnalysisPageViewed error !");
		}
		return count;
	}

	public Double getAnalysisAvgPageStays(Integer custNo, String fromDate,
			String toDate) {
		Double count = 0.0;
		try {
			String countHql = "SELECT AVG(stay_seconds) FROM web_behavior.access_log_anls WHERE cust_no=:custNo AND visit_time";
			countHql += " >:fromDate AND visit_time<=:toDate";
			SQLQuery query = this.getSession().createSQLQuery(countHql);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("custNo", custNo);
			map.put("fromDate", fromDate);
			map.put("toDate", toDate);
			query.setProperties(map);
			Object obj = query.uniqueResult();
			if (obj != null) {
				BigDecimal temp = (BigDecimal) obj;
				count = temp.doubleValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("getAnalysisAvgPageStays Error !");
		}
		return count;
	}

}
