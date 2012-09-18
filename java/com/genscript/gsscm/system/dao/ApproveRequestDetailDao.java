package com.genscript.gsscm.system.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.system.entity.ApproveRequestDetail;

@Repository
public class ApproveRequestDetailDao extends HibernateDao<ApproveRequestDetail, Integer>{
	private static final String GET_UNAPPROVED_REQUEST = "from ApproveRequestDetail a where a.requestId =? and a.columnName =?";
	
	public ApproveRequestDetail getUnapprovedRequest(final Integer requestId, final String columnName){
		return findUnique(GET_UNAPPROVED_REQUEST, requestId, columnName);
	}
}
