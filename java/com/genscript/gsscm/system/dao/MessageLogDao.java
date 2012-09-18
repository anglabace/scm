package com.genscript.gsscm.system.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.system.entity.MessageLog;

/**
 * The generic DAO class of MessageLog.
 * 
 * @author Golf
 */
@Repository
public class MessageLogDao extends HibernateDao<MessageLog, Integer>{

}