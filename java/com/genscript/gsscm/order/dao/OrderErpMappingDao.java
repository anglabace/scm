package com.genscript.gsscm.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderErpMapping;

@Repository
public class OrderErpMappingDao extends HibernateDao<OrderErpMapping, Integer>{
	/*
	 * 根据orderNos 获取
	 */
	public List<OrderErpMapping> getOrderList(String orderNos){
		String hql = "from OrderErpMapping where orderNo in ("+orderNos+")";
		
		return this.find(hql);
	}
}
