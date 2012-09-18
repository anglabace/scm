package com.genscript.gsscm.quote.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.dozer.DozerBeanMapper;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.dto.QuoteMainBeanDTO;
import com.genscript.gsscm.quote.entity.QuoteMainBean;


/*
 * update by zhou gang  2011 12 7
 */
@Repository
public class QuoteMainBeanDao extends HibernateDao<QuoteMainBean, Integer> {
	@Autowired(required = false)
	private DozerBeanMapper dozer;

	public Page<QuoteMainBean> getQuotesByFilter(Page<QuoteMainBean> page,
			List<Criterion> criterionList, List<PropertyFilter> filters) {
	 	if (filters != null && filters.size() > 0) {
			Criterion[] criterions = buildPropertyFilterCriterions(filters);
			for (Criterion temp : criterions) {
				criterionList.add(temp);
			}
			return this.findPage(page,
					criterionList.toArray(new Criterion[criterionList.size()]));
		} else { 
			String hql = "SELECT qm.quoteNo, qm.status, qm.custNo, cm.firstName, cm.midName, cm.lastName, "
					+ " cm.addrLine1, cm.addrLine2, cm.addrLine3, cm.city, cm.state, cm.zipCode, cm.country, "
					+ " qm.quoteDate, qm.exchRateDate, qm.amount, u.loginName, qm.orderNo, u2.loginName, pc.symbol"  
					+ " FROM QuoteMain qm, Customer cm, User u, User u2, PbCurrency pc"
					+ " where qm.custNo = cm.custNo and qm.salesContact = u.userId  "
					+ " and qm.createdBy = u2.userId and qm.quoteCurrency = pc.currencyCode  "  
					+ " ORDER BY qm.quoteNo desc";
			Query query = createQuery(hql);
			query.setFirstResult(page.getFirst() != null && page.getFirst() > 0 ? (page
					.getFirst() - 1) : 0);
			query.setMaxResults(page.getPageSize());
			@SuppressWarnings("unchecked")
			List<Object[]> objsList = query.list();
			List<QuoteMainBean> omlist = new ArrayList<QuoteMainBean>();
			if (objsList != null && !objsList.isEmpty()) {
				for (Object[] objs : objsList) {
					QuoteMainBeanDTO qmb = new QuoteMainBeanDTO();
					QuoteMainBean quoteMainBean = new QuoteMainBean();
					qmb.setQuoteNo((Integer) objs[0]);
					qmb.setStatus(objs[1] != null ? objs[1].toString() : null);
					qmb.setCustNo((Integer) objs[2]);
					qmb.setFirstName(objs[3] != null ? objs[3].toString()
							: null);
					qmb.setMidName(objs[4] != null ? objs[4].toString() : null);
					qmb.setLastName(objs[5] != null ? objs[5].toString() : null);
					qmb.setAddrLine1(objs[6] != null ? objs[6].toString()
							: null);
					qmb.setAddrLine2(objs[7] != null ? objs[7].toString()
							: null);
					qmb.setAddrLine3(objs[8] != null ? objs[8].toString()
							: null);
					qmb.setCity(objs[9] != null ? objs[9].toString() : null);
					qmb.setState(objs[10] != null ? objs[10].toString() : null);
					qmb.setZipCode(objs[11] != null ? objs[11].toString()
							: null);
					qmb.setCountry(objs[12] != null ? objs[12].toString()
							: null);
					qmb.setQuoteDate(objs[13] != null ? (Date) objs[13] : null);
					qmb.setExprDate(objs[14] != null ? (Date) objs[14] : null);
					qmb.setAmount(objs[15] != null ? ((BigDecimal) objs[15])
							.doubleValue() : 0d);
					qmb.setSalesContact(objs[16] != null ? objs[16].toString()
							: null);
					qmb.setOrderNo(objs[17] != null
							&& StringUtils.isNotBlank(objs[17].toString())
							&& StringUtils.isNumeric(objs[17].toString()) ? Integer
							.parseInt(objs[17].toString()) : null);
					qmb.setCreatedBy(objs[18] != null ? objs[18].toString()
							: null);
					qmb.setSymbol(objs[19] != null ? objs[19].toString() : null); 
					quoteMainBean = dozer.map(qmb, QuoteMainBean.class);
					omlist.add(quoteMainBean);
				}
			}
			page.setResult(omlist);
			page.setTotalCount(getQuoteTotal());
			return page;
		}
	}

	/**
	 * 获得quote的总条数
	 * 
	 * @author Zhang Yong
	 * @return
	 */
	private Long getQuoteTotal() {
		Long totalCount = 0l;
		String hql = "SELECT COUNT(qm.quoteNo) FROM QuoteMain qm ";
		List<Object> list = find(hql);
		if (list != null && !list.isEmpty()) {
			totalCount = (Long) list.get(0);
		}
		return totalCount;
	}
}
