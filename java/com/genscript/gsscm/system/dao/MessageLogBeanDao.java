package com.genscript.gsscm.system.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.system.entity.MessageLogBean;

@Repository
public class MessageLogBeanDao extends HibernateDao<MessageLogBean, Integer> {

}
