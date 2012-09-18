package com.genscript.gsscm.tools.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.hql.FilterTranslator;
import org.hibernate.impl.SessionFactoryImpl;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuoteMain;
import com.genscript.gsscm.report.dto.ReportDataDto;
import com.genscript.gsscm.tools.dto.OrderFollowUpViewDTO;
import com.genscript.gsscm.tools.dto.OrderFollowupDTO;

@Repository
public class OrderFollowupDao extends HibernateDao<Object, Integer> {

	@SuppressWarnings("rawtypes")
	public List getQuoteByHQL(Page<OrderFollowupDTO> orderFollowupDTO,
			String hql) {
		int pageIndex = orderFollowupDTO.getPageNo();
		int pageSize = orderFollowupDTO.getPageSize();
		// pageIndex 为当前页数，pageSize为页显示数
		final int items = (pageIndex - 1) * pageSize;
		Query query = this.getSession().createSQLQuery(hql);
		// 定义从第几条开始查询
		query.setFirstResult(items);
		// 定义返回的纪录数
		query.setMaxResults(pageSize);

		String totalCount = this
				.getSession()
				.createSQLQuery(
						"select count(*) from (" + hql + " ) tmp_count_t ")
				.uniqueResult().toString();
		orderFollowupDTO.setTotalCount(Long.parseLong(totalCount));
		query.setMaxResults(pageSize);
		List quoteList = query.list();
		return quoteList;
	}

	public List getQuoteDetailBySQL(Page<ReportDataDto> reportDataDtoPage,
			String hql, String sql) {
		int pageIndex = reportDataDtoPage.getPageNo();
		int pageSize = reportDataDtoPage.getPageSize();
		// pageIndex 为当前页数，pageSize为页显示数
		final int items = (pageIndex - 1) * pageSize;
		Query query = this.getSession().createSQLQuery(hql)
				.addEntity("qu", QuoteMain.class);
		// 定义从第几条开始查询
		query.setFirstResult(items);
		// 定义返回的纪录数
		query.setMaxResults(pageSize);

		String totalCount = this
				.getSession()
				.createSQLQuery(
						"select count(*) from ( " + sql + ") tmp_count_t")
				.uniqueResult().toString();
		reportDataDtoPage.setTotalCount(Long.parseLong(totalCount));
		query.setMaxResults(pageSize);
		List quoteList = query.list();
		return quoteList;
	}

	public List getQuoteBySQL(Page<ReportDataDto> reportDataDtoPage,
			String hql, String entityClassName, Class entityClass) {
		int pageIndex = reportDataDtoPage.getPageNo();
		int pageSize = reportDataDtoPage.getPageSize();
		// pageIndex 为当前页数，pageSize为页显示数
		final int items = (pageIndex - 1) * pageSize;
		Query query = this.getSession().createSQLQuery(hql)
				.addEntity(entityClassName, entityClass);
		// 定义从第几条开始查询
		query.setFirstResult(items);
		// 定义返回的纪录数
		query.setMaxResults(pageSize);

		String totalCount = this.getSession().createSQLQuery(getCountSql(hql))
				.uniqueResult().toString();
		reportDataDtoPage.setTotalCount(Long.parseLong(totalCount));
		query.setMaxResults(pageSize);
		List quoteList = query.list();
		return quoteList;
	}

	public String getCountSql(String originalHql) {
		SessionFactoryImpl sessionFactoryImp = (SessionFactoryImpl) this
				.getSessionFactory();
		FilterTranslator filterTrans = sessionFactoryImp
				.getSettings()
				.getQueryTranslatorFactory()
				.createFilterTranslator(originalHql, originalHql,
						Collections.EMPTY_MAP, sessionFactoryImp);
		filterTrans.compile(Collections.EMPTY_MAP, false);
		return "select count(*) from (" + filterTrans.getSQLString()
				+ ") tmp_count_t";
	}

	@SuppressWarnings("rawtypes")
	public List ExcuteSql(String sql) {
		Query query = this.getSession().createSQLQuery(sql);
		List reportData = query.list();
		return reportData;
	}

	public Date getReportFromDateByHQL(String hql) {
		return (Date) this.getSession().createSQLQuery(hql).uniqueResult();
	}

	@SuppressWarnings("rawtypes")
	public List contactLists(OrderFollowUpViewDTO orderFollowUpViewDTO,
			List mainList, List sql_shiping_dateList, List locationList,
			List target_dateList, List countList) {
		Page <OrderFollowUpViewDTO> orderFollowUpViewPageBean=new Page<OrderFollowUpViewDTO>();
		List<OrderFollowUpViewDTO> allList = new ArrayList<OrderFollowUpViewDTO>();
		if (mainList != null && mainList.size() > 0) {
			int n1 = mainList.size();
			for (int i = 0; i < n1; i++) {
				Object[] o1 = (Object[]) mainList.get(i);
				if (o1[0].toString() != null && !"".equals(o1[0].toString())) {
					orderFollowUpViewDTO.setOrderNo(o1[0].toString());
				}
				if (o1[1].toString() != null && !"".equals(o1[1].toString())) {
					orderFollowUpViewDTO.setConfirmationDate(o1[1].toString());
				}
				if (o1[2].toString() != null && !"".equals(o1[2].toString())) {
					orderFollowUpViewDTO.setOrderStatus(o1[2].toString());
				}
				if (o1[3].toString() != null && !"".equals(o1[3].toString())) {
					orderFollowUpViewDTO.setMail(o1[3].toString());
				}
				if (o1[4].toString() != null && !"".equals(o1[4].toString())) {
					orderFollowUpViewDTO.setCustNo(o1[4].toString());
				}
				if (o1[5].toString() != null && !"".equals(o1[5].toString())) {
					orderFollowUpViewDTO.setPriority(o1[5].toString());
				}
				if (o1[6].toString() != null && !"".equals(o1[6].toString())) {
					orderFollowUpViewDTO.setOrganization(o1[6].toString());
				}
				if (o1[7].toString() != null && !"".equals(o1[7].toString())) {
					orderFollowUpViewDTO.setCustName(o1[7].toString());
				}

				if (o1[8].toString() != null && !"".equals(o1[8].toString())) {
					orderFollowUpViewDTO.setOrderTotal(o1[8].toString());
				}
				if (o1[9].toString() != null && !"".equals(o1[9].toString())) {
					orderFollowUpViewDTO.setTam(o1[9].toString());
				}
				if (o1[10].toString() != null && !"".equals(o1[10].toString())) {
					orderFollowUpViewDTO.setSalesManage(o1[10].toString());
				}
				if (o1[11].toString() != null && !"".equals(o1[11].toString())) {
					orderFollowUpViewDTO.setProjectManager(o1[11].toString());
				}
				if (o1[12].toString() != null && !"".equals(o1[12].toString())) {
					orderFollowUpViewDTO.setProductServiceType(o1[12]
							.toString());
				}
				if (o1[13].toString() != null && !"".equals(o1[13].toString())) {
					orderFollowUpViewDTO.setPo(o1[13].toString());
				}
				if (o1[14].toString() != null && !"".equals(o1[14].toString())) {
					orderFollowUpViewDTO.setFollowupStatus(o1[14].toString());
				}
				if (o1[15].toString() != null && !"".equals(o1[15].toString())) {
					orderFollowUpViewDTO.setFollowupDate(o1[15].toString());
				}
				if (o1[16].toString() != null && !"".equals(o1[16].toString())) {
					orderFollowUpViewDTO.setFollowupMessage(o1[16].toString());
				}
			}
			if (sql_shiping_dateList != null && sql_shiping_dateList.size() > 0) {
				orderFollowUpViewDTO.setDeliveryDate(sql_shiping_dateList
						.get(0).toString());
			}
			if (countList != null && countList.size() > 0) {
				orderFollowUpViewDTO.setNumofItems(countList.get(0).toString());
			}
			if (locationList != null && locationList.size() > 0) {
				orderFollowUpViewDTO
						.setLocation(locationList.get(0).toString());
			}
			if (target_dateList != null && target_dateList.size() > 0) {
				orderFollowUpViewDTO.setProductionTargetDate(target_dateList
						.get(0).toString());
				orderFollowUpViewDTO.setGuaranteedDeliveryDate(target_dateList
						.get(0).toString());
				orderFollowUpViewDTO.setDeliveryDate("");
			}

			allList.add(orderFollowUpViewDTO);
		}
		
		
		
		return null;
	}
}
