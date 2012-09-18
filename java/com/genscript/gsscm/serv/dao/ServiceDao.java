package com.genscript.gsscm.serv.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.Service;


@Repository
public class ServiceDao extends HibernateDao<Service, Integer> {
	/**
	 * 根据catalogNo获得Service
	 * 
	 * @param catalogNo
	 * @return
	 */
	public Service getServiceByCatalogNo(String catalogNo) {
		String hql = "from Service where catalogNo=:catalogNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		return this.findUnique(hql, map);
	}
	
	public boolean getServiceClsByCatalogNo(String catalogNo, String queryStr) {
		List<PropertyFilter> srFilterList = new ArrayList<PropertyFilter>();
		PropertyFilter catalogNoFilter = new PropertyFilter(
				"EQS_catalogNo", catalogNo);
		PropertyFilter categoryFilter = new PropertyFilter(
				"LIKES_oldCategory", queryStr);
		srFilterList.add(categoryFilter);
		srFilterList.add(catalogNoFilter);
		List<Service> servList = find(srFilterList);
		if(servList != null && servList.size() > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据catalogNo 和giftFlag获得gift Service
	 * 
	 * @param catalogNo
	 * @param giftFlag
	 * @return
	 */
	public Service getGiftServiceByCatalogNo(String catalogNo,String giftFlag) {
		String hql = "from Service where catalogNo=:catalogNo and giftFlag=:giftFlag";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		map.put("giftFlag", giftFlag);
		return this.findUnique(hql, map);
	}
	
	public Integer getServiceIdByCatalogNo(String catalogNo){
		com.genscript.gsscm.serv.entity.Service service = this.findUniqueBy("catalogNo", catalogNo);
		if(service != null){
			return service.getServiceId();
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getMyServicePriceAttributes(final Integer in) {
		final Object obj;
		HibernateTemplate hibernateTemplate = new HibernateTemplate(
				this.sessionFactory);
		obj = hibernateTemplate.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				Query query = s.createSQLQuery("{call product.get_price_attributes_sp(?,?)}");
				query.setParameter(0, in);
				String str = (String) query.uniqueResult();
				return str;
			}
		});
		return obj;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getServicePriceAttributes(final String catalogNo, final String catalogId){
		final Object obj;
		HibernateTemplate hibernateTemplate = new HibernateTemplate(this.sessionFactory);
		obj = hibernateTemplate.execute(new HibernateCallback() {
			
			@SuppressWarnings("deprecation")
			@Override
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				Connection conn = s.connection();
				CallableStatement cstmt = conn.prepareCall("{call product.sp_get_price_attributes(?,?,?)}");
				
				cstmt.setString(1, catalogNo);
				cstmt.setString(2, catalogId);
				cstmt.registerOutParameter("o_attribute_list", java.sql.Types.VARCHAR);
				cstmt.execute();
				String str = cstmt.getString("o_attribute_list");
				return str;
			}
		});
		return obj;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object CalculateServicePrice(final String catalogNo, final String catalogId, final String attributeValue){
		final Object obj;
		HibernateTemplate hibernateTemplate = new HibernateTemplate(this.sessionFactory);
		obj = hibernateTemplate.execute(new HibernateCallback() {
			
			@SuppressWarnings("deprecation")
			@Override
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				Connection conn = s.connection();
				CallableStatement cstmt = conn.prepareCall("{call product.sp_get_price(?,?,?,?,?)}");
				cstmt.setString(1, catalogNo);
				cstmt.setString(2, catalogId);
				cstmt.setString(3, attributeValue);
				cstmt.registerOutParameter("o_price", java.sql.Types.DECIMAL);
				cstmt.registerOutParameter("o_cost", java.sql.Types.DECIMAL);
				cstmt.execute();
				Double cost = cstmt.getDouble("o_cost");
				Double price = cstmt.getDouble("o_price");
				List list = new ArrayList();
				list.add(cost);
				list.add(price);
				return list;
			}
		});
		return obj;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getAntibodyPepPrice(final String catalogNo, final String catalogId, final String peptidePrice){
		final Object obj;
		HibernateTemplate hibernateTemplate = new HibernateTemplate(this.sessionFactory);
		obj = hibernateTemplate.execute(new HibernateCallback() {
			
			@SuppressWarnings("deprecation")
			@Override
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				Connection conn = s.connection();
				CallableStatement cstmt = conn.prepareCall("{call product.sp_get_antibody_pep_price(?,?,?,?)}");
				cstmt.setString(1, catalogNo);
				cstmt.setString(2, catalogId);
				cstmt.setString(3, peptidePrice);
				cstmt.registerOutParameter("o_price", java.sql.Types.VARCHAR);
				cstmt.execute();
				String price = cstmt.getString("o_price");
				return price;
			}
		});
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public List<com.genscript.gsscm.serv.entity.Service> searchServiceBreakDownList(String catalogNo,
			String name, List<String> catalogNoList) {
		Criteria criteria = this.getSession().createCriteria(com.genscript.gsscm.serv.entity.Service.class);
		if (catalogNo != null && catalogNo.trim().length()>0) {
			criteria.add(Restrictions.like("catalogNo", "%"+catalogNo+"%"));
		}
		if (catalogNoList != null && !catalogNoList.isEmpty()) {
			criteria.add(Restrictions.not(Restrictions.in("catalogNo",
					catalogNoList)));
		}
		if (name!=null && name.trim().length()>0) {
			criteria.add(Restrictions.like("name", "%"+name+"%"));
		}
		return criteria.list();
	}
	
	/**
	 * 通过catalogNo查找温度
	 * @param catalogNo
	 * @return
	 */
	public Double getTemperatureByCatalogNo (String catalogNo) {
		String hql = "select ssc.temperature from ServiceShipCondition ssc, Service s where ssc.serviceId = s.serviceId and s.catalogNo=:catalogNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		List<Double> temperatureList = this.find(hql, map);
		if (temperatureList != null && !temperatureList.isEmpty()) {
			return temperatureList.get(0);
		}
		return null;
	}
	
	/**
	 * 通过clsId查询CatalogNo列表
	 * @author Zhang Yong
	 * @param clsIdList
	 * @return
	 */
	public List<String> getCatalogNoByClsId (List<Integer> clsIdList) {
		String hql = "SELECT catalogNo FROM Service WHERE serviceClsId in(:clsIdList)";
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("clsIdList", clsIdList);
		return this.find(hql, map);
	}
}
