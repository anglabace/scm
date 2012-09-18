package com.genscript.gsscm.serv.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao; 
import com.genscript.gsscm.serv.entity.ServicePrice;

@Repository
public class ServicePriceDao extends HibernateDao<ServicePrice, Integer> {
	/**
	 * 获得一个Service Category对应的多个Service
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<ServicePrice> getServPriceListByCategoryId(Integer categoryId) {
		String hql = "from ServicePrice where categoryId=?";
		return this.find(hql, categoryId);
	}

	public List<ServicePrice> getServicePriceByCategoryIdAndCatalogId(
			String catalogId, Integer categoryId) {
		String hql = "from ServicePrice where catalogId = ? AND categoryId=?";
		return this.find(hql, catalogId, categoryId);
	}

	/**
	 * 批量删除ServicePrice.
	 * 
	 * @param categoryId
	 * @param servIdList
	 */
	public void delServicePriceList(Integer categoryId, Object servIdList) {
		String hql = "delete from ServicePrice c where c.categoryId=:categoryId and c.serviceId in (:serviceIdList)";
		Map<String, Object> map = Collections.singletonMap("serviceIdList",
				servIdList);
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("categoryId", categoryId);
		values.put("serviceIdList", map.get("serviceIdList"));
		batchExecute(hql, values);
	}

	/**
	 * 获得该category是否有相关的Product.
	 * 
	 * @param categoryId
	 * @return
	 */
	public Long getCountByCategoryId(Integer categoryId) {
		String hql = "select count(*) from ServicePrice where categoryId=?";
		return this.findUnique(hql, categoryId);
	}

	/*
	 * 获得该catalogId,serviceId相关的price
	 */
	public ServicePrice getServicePriceByCatalogIdServiceId(String catalogId,
			Integer serviceId) {
		String hql = "from ServicePrice where catalogId='" + catalogId
				+ "' and  serviceId=" + serviceId;
		return this.findUnique(hql);
	}

	// add by zhougang ----------2011 05 17
	public ServicePrice getObjectByID(Integer objectId) {
		String hql = "from  ServicePrice where priceId  =?";
		return this.findUnique(hql, objectId);
	}

	public List<ServicePrice> getServicePriceByServiceId(int servicesId) {
		System.out.println(servicesId + "##########");
		String sql = " from ServicePrice where serviceId= ?";
		return this.findUnique(sql, servicesId);
	}

	public Long checkAllServiceIdandcatalogId(String catalogId, Integer serviceKeys) {
		String hql = "select count(*) from ServicePrice where catalogId = ? AND serviceId=?";
		return this.findUnique(hql, catalogId, serviceKeys);

	}
}
