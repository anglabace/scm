package com.genscript.gsscm.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.PropertyFilter.MatchType;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.MfgOrder;
import com.genscript.gsscm.order.entity.MfgOrderDTO;
import com.genscript.gsscm.order.entity.MfgOrderDTO2;

@Repository
public class MfgOrderDao extends HibernateDao<MfgOrder, Integer> {

	@SuppressWarnings("unchecked")
	public Page<MfgOrderDTO> searchMfgOrderDTO(Page<MfgOrderDTO> page,
			List<PropertyFilter> filters) {
		List<MfgOrderDTO> list = null;
		String sql = "SELECT  mo.*,vo.symbol,vo.amount,vo.sales_contact,vo.company_name,vo.customer_confirm_date,vo.vendor_confirm_date,vo.order_type,vo.email,vo.first_name,vo.last_name,vo.organization,vo.country,vo.state,vo.tech_support,oe.erp_nj_so"
				+ " FROM order.mfg_order mo "
				+ " LEFT JOIN order.v_all_orders vo "
				+ " ON mo.src_so_no = vo.order_no    "
				+ " LEFT JOIN order.order_erp_mapping  oe  "
				+ "  ON vo.order_no = " + " oe.order_no where 1=1 ";

		String querySql = "";
		if (filters != null && filters.size() > 0) {
			for (int i = 0; i < filters.size(); i++) {
				PropertyFilter propertyFilter = (PropertyFilter) filters.get(i);
				String qSql = getQSql(propertyFilter);
				if (propertyFilter.getPropertyName().equals("orderNo")) {
					querySql += " and  mo.src_so_no " + qSql;
				}
				if (propertyFilter.getPropertyName().equals("custNo")) {
					querySql += " and  mo.cust_no " + qSql;
				}
				if (propertyFilter.getPropertyName().equals("salesContact")) {
					querySql += " and vo.sales_contact " + qSql;
				}
				if (propertyFilter.getPropertyName().equals("status")) {
					querySql += " and mo.status " + qSql;
				}
				if (propertyFilter.getPropertyName().equals("priority")) {
					querySql += " and mo.priority " + qSql;
				}
				if (propertyFilter.getPropertyName().equals("orderDate")) {
					querySql += " and mo.order_date " + qSql;
				}
				if (propertyFilter.getPropertyName().equals("companyName")) {
					querySql += " and vo.company_name " + qSql;
				}
				if (propertyFilter.getPropertyName().equals("amount")) {
					querySql += " and vo.amount " + qSql;
				}
				if (propertyFilter.getPropertyName().equals(
						"customerConfirmDate")) {
					querySql += " and vo.customer_confirm_date " + qSql;
				}
				if (propertyFilter.getPropertyName()
						.equals("vendorConfirmDate")) {
					querySql += " and vo.vendor_confirm_date " + qSql;
				}
				if (propertyFilter.getPropertyName().equals("orderType")) {
					querySql += " and vo.order_type " + qSql;
				}
				if (propertyFilter.getPropertyName().equals("email")) {
					querySql += " and vo.email " + qSql;
				}
				if (propertyFilter.getPropertyName().equals("firstName")) {
					querySql += " and vo.first_name " + qSql;
				}
				if (propertyFilter.getPropertyName().equals("lastName")) {
					querySql += " and vo.last_name " + qSql;
				}
				if (propertyFilter.getPropertyName().equals("organization")) {
					querySql += " and vo.organization " + qSql;
				}
				if (propertyFilter.getPropertyName().equals("country")) {
					querySql += " and vo.country " + qSql;
				}
				if (propertyFilter.getPropertyName().equals("state")) {
					querySql += " and vo.state " + qSql;
				}
				if (propertyFilter.getPropertyName().equals("techSupport")) {
					querySql += " and vo.tech_support " + qSql;
				}
			}
		}

		// if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
		// hql += " order by mo.orderNo " + page.getOrder();
		// }
		list = this.getSession()
				.createSQLQuery(sql + querySql + " order by mo.order_no desc ")
				.addEntity(MfgOrderDTO.class)
				.setFirstResult((page.getPageNo() - 1) * page.getPageSize())
				.setMaxResults(page.getPageSize()).list();
		long total = 0l;
		List l2 = this
				.getSession()
				.createSQLQuery(
						" select count(*) from order.mfg_order mo LEFT JOIN order.v_all_orders vo ON mo.src_so_no = vo.order_no where 1=1  "
								+ querySql).list();
		total = Long.parseLong(l2.get(0).toString());
		page.setResult(list);
		page.setTotalCount(total);
		return page;
	}

	@SuppressWarnings("unchecked")
	public Page<MfgOrderDTO2> searchMfgOrderDTO2(Page<MfgOrderDTO2> page,
			List<PropertyFilter> filters) {
		List<MfgOrderDTO2> list = null;
		String selectSql = "SELECT  mo.order_no,mo.src_so_no,vo.sales_contact,vo.tech_support,vo.project_manager ";
		String fromSql = "FROM order.mfg_order mo join order.order vo ON mo.src_so_no = vo.order_no where 1=1 ";
		String querySql = "";
		if (filters != null && filters.size() > 0) {
			for (int i = 0; i < filters.size(); i++) {
				PropertyFilter propertyFilter = (PropertyFilter) filters.get(i);
				if (propertyFilter.getPropertyName().equals("orderNo")) {
					querySql += " and  mo.src_so_no ="
							+ propertyFilter.getPropertyValue() + " ";
				}
				if (propertyFilter.getPropertyName().equals("status")) {
					querySql += " and  mo.status ='"
							+ propertyFilter.getPropertyValue() + "'";
				}
			}
		}
		list = this
				.getSession()
				.createSQLQuery(
						selectSql + fromSql + querySql
								+ " order by mo.order_no desc ")
				.addEntity(MfgOrderDTO2.class)
				.setFirstResult((page.getPageNo() - 1) * page.getPageSize())
				.setMaxResults(page.getPageSize()).list();
		long total = 0l;
		List l2 = this.getSession()
				.createSQLQuery(" select count(*) " + fromSql + querySql)
				.list();
		total = Long.parseLong(l2.get(0).toString());
		page.setResult(list);
		page.setTotalCount(total);
		return page;
	}

	private String getQSql(PropertyFilter propertyFilter) {
		String str = "";
		if (propertyFilter.getMatchType().equals(MatchType.EQ)) {
			str = " = '" + propertyFilter.getPropertyValue() + "' ";
		}
		if (propertyFilter.getMatchType().equals(MatchType.GE)) {
			str = " >= '" + propertyFilter.getPropertyValue() + "' ";
		}
		if (propertyFilter.getMatchType().equals(MatchType.GT)) {
			str = " > '" + propertyFilter.getPropertyValue() + "' ";
		}
		if (propertyFilter.getMatchType().equals(MatchType.LE)) {
			str = " <= '" + propertyFilter.getPropertyValue() + "' ";
		}
		if (propertyFilter.getMatchType().equals(MatchType.LT)) {
			str = " < '" + propertyFilter.getPropertyValue() + "' ";
		}
		if (propertyFilter.getMatchType().equals(MatchType.LIKE)) {
			str = " like '%" + propertyFilter.getPropertyValue() + "%' ";
		}
		if (propertyFilter.getMatchType().equals(MatchType.PLIKE)) {
			str = " like '" + propertyFilter.getPropertyValue() + "%' ";
		}
		if (propertyFilter.getMatchType().equals(MatchType.SLIKE)) {
			str = " like '%" + propertyFilter.getPropertyValue() + "' ";
		}
		return str;
	}
	
	public List<Integer> findBySrcSoNo(Integer srcSoNo) {
		String hql = "select orderNo from MfgOrder where srcSoNo=?";
		return this.find(hql, srcSoNo);
	}
}
