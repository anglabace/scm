package com.genscript.gsscm.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderPkgService;

@Repository
public class OrderPkgServiceDao extends HibernateDao<OrderPkgService, Integer>{

	/**
	 * 通过主服务查找子服务
	 * @author Zhang Yong
	 * @param pkgType
	 * @param parentOrderItemId
	 * @return
	 */
	public List<OrderPkgService> findByOrderItemIds (String pkgType, Integer parentOrderItemId) {
		String hql = "select ops from OrderPkgService ops, OrderItem oi, ServiceClass sc "
			+ "where ops.orderItemId = oi.orderItemId and oi.clsId = sc.clsId and sc.name = upper(?) "
			+ "and oi.parentId = ?";
		return this.find(hql, pkgType, parentOrderItemId);
	}
}
