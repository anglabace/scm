 package com.genscript.gsscm.order.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderService;

@Repository
public class OrderServiceDao extends HibernateDao<OrderService, Integer> {

}
