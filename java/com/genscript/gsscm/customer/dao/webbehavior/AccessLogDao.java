package com.genscript.gsscm.customer.dao.webbehavior;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.AccessLog;

@Repository
public class AccessLogDao extends HibernateDao<AccessLog, Integer> {

}
