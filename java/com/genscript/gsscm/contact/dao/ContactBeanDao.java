package com.genscript.gsscm.contact.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.contact.entity.ContactBean;

@Repository
public class ContactBeanDao extends HibernateDao<ContactBean, Integer> {
	private static final String QUERY_CONTACT_ADDRESS = "select c.custNo,c.altNo,c.status,c.firstName,c.midName,c.lastName,c.state,c.country,c.zipCode,c.busEmail,c.organization.name,c.division.name,c.department.name,c.salesTerritory.territoryCode,c.salesGroup.groupCode,c.salesContact.fullName,c.techSupport.fullName,c.status,a.firstName,a.midName,a.lastName,c.busPhone from contact c left join c.addresses a where a.addrType = 'contact' and a.defaultFlag = 'Y'";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<ContactBean> getcontactList(Page<ContactBean> page) {
		Assert.notNull(page, "page can not be null");
		Integer pageSize;
		Criterion criterion = Restrictions.eq("status", "ACTIVE");
		Criteria c = createCriteria(criterion);

		if (page.isAutoCount()) {
			Long count = 0L;
			String countHql = "select count(c.contactNo) from Contact c where c.status = 'ACTIVE'";

			try {
				count = findUnique(countHql);
			} catch (Exception e) {
				throw new RuntimeException("hql can't be auto count, hql is:"
						+ countHql, e);
			}
			page.setTotalCount(Long.valueOf(count));
		}
		pageSize = page.getPageSize();
		if (pageSize != null && pageSize != 0)
			setPageParameter(c, page);
		List result = c.list();
		page.setResult(result);
		return page;
	}

	@SuppressWarnings("rawtypes")
	public Page<ContactBean> searchContact(Page<ContactBean> page) {
		Assert.notNull(page, "page can not be null");
		List contacts = null;
		List<ContactBean> contactDTO = new ArrayList<ContactBean>();
		if (page.isAutoCount()) {
			long totalCount = countHqlResult(QUERY_CONTACT_ADDRESS);
			page.setTotalCount(totalCount);
		}

		Query q = createQuery(QUERY_CONTACT_ADDRESS);
		setPageParameter(q, page);
		contacts = q.list();
		if (null != contacts && 0 < contacts.size()) {
			for (int i = 0; i < contacts.size(); i++) {
				Object[] obj = (Object[]) contacts.get(i);
				ContactBean contactMainDTO = new ContactBean();
				contactMainDTO.setContactNo(Integer
						.parseInt((obj[0] == null) ? "0" : obj[0].toString()));
				contactMainDTO.setAltNo((String) obj[1]);
				contactMainDTO.setStatus((String) obj[2]);
				contactMainDTO.setFirstName((String) obj[3]);
				contactMainDTO.setMidName((String) obj[4]);
				contactMainDTO.setLastName((String) obj[5]);
				contactMainDTO.setBusEmail((String) obj[6]);
				contactMainDTO.setOrganizationName((String) obj[7]);
				contactMainDTO.setBusPhone((String) obj[11]);
				contactDTO.add(contactMainDTO);
			}

			page.setResult(contactDTO);
			return page;
		} else {
			return null;
		}
	}

	/**
	 * 查询contact
	 * 
	 * @author modify by Zhang Yong
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<ContactBean> searchContact(Page<ContactBean> page,
			List<PropertyFilter> filters) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 点击左侧菜单时从Contact表中查询数据
		if (filters == null || filters.isEmpty()) {
			String hql = "SELECT c.contactNo, c.firstName, c.midName, c.lastName, o.name, c.country, "
					+ "c.state, c.busEmail, c.busPhone, c.status,c.creationDate FROM Contact c, Organization o "
					+ "where c.orgId = o.orgId order by c.contactNo desc";
			Query query = createQuery(hql);
			query.setFirstResult(page.getFirst() != null && page.getFirst() > 0 ? (page
					.getFirst() - 1) : 0);
			query.setMaxResults(page.getPageSize());
			@SuppressWarnings("unchecked")
			List<Object[]> objsList = query.list();
			List<ContactBean> cblist = new ArrayList<ContactBean>();
			if (objsList != null && !objsList.isEmpty()) {
				for (Object[] objs : objsList) {
					ContactBean contactBean = new ContactBean();
					contactBean.setContactNo((Integer) objs[0]);
					contactBean.setFirstName(objs[1] == null ? "" : objs[1]
							.toString());
					contactBean.setMidName(objs[2] == null ? "" : objs[2]
							.toString());
					contactBean.setLastName(objs[3] == null ? "" : objs[3]
							.toString());
					contactBean.setOrganizationName(objs[4] == null ? ""
							: objs[4].toString());
					contactBean.setCountry(objs[5] == null ? "" : objs[5]
							.toString());
					contactBean.setState(objs[6] == null ? "" : objs[6]
							.toString());
					contactBean.setBusEmail(objs[7] == null ? "" : objs[7]
							.toString());
					contactBean.setBusPhone(objs[8] == null ? "" : objs[8]
							.toString());
					contactBean.setStatus(objs[9] == null ? "" : objs[9]
							.toString());
					try {
						if (objs[10] != null) {
							contactBean.setCreationDate(sdf.parse(objs[10]
									.toString()));
						}
					} catch (Exception w) {
						w.printStackTrace();
					}

					cblist.add(contactBean);
				}
			}
			page.setResult(cblist);
			page.setTotalCount(getContactTotal());
			return page;
		} else {
			page = findPage(page, filters);
		}
		return page;
	}

	/**
	 * 查询contact的总条数
	 * 
	 * @author Zhang Yong
	 * @return
	 */
	private Long getContactTotal() {
		Long totalCount = 0l;
		String hql = "SELECT COUNT(c.contactNo) FROM Contact c ";
		List<Object> list = find(hql);
		if (list != null && !list.isEmpty()) {
			totalCount = (Long) list.get(0);
		}
		return totalCount;
	}

	@Override
	public Page<ContactBean> findPage(Page<ContactBean> page,
			List<PropertyFilter> filters) {
		return super.findPage(page, filters);
	}

}
