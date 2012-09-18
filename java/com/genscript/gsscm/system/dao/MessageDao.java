package com.genscript.gsscm.system.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.system.entity.Message;

/**
 * The generic DAO class of Message.
 * 
 * @author Golf
 */
@Repository
public class MessageDao extends HibernateDao<Message, Integer> {

}