package com.genscript.gsscm.system.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.system.entity.MessageDetail;

/**
 * The generic DAO class of MessageDetail.
 * 
 * @author Golf
 */
@Repository
public class MessageDetailDao extends HibernateDao<MessageDetail, Integer> {
	public MessageDetail getMessageDetail(Integer messageId, String language)
	{
		return findUnique("from MessageDetail where msgId=? and language=?", messageId,language);
	}
}