package com.genscript.gsscm.system.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.RequestApproveStatusType;
import com.genscript.gsscm.system.entity.ApproveRequest;

@Repository
public class ApproveRequestDao extends HibernateDao<ApproveRequest, Integer>{
	private static final String COUNT_APPROVE_REQUEST = " from ApproveRequest a where a.tableName =? and a.objectId =?";
	

	
	public ApproveRequest countApproveRequest(final String tableName, final Integer objectId){
		return findUnique(COUNT_APPROVE_REQUEST, tableName, objectId);
	}
	
	public void rejectRequest(final String rejectReason,final List<Integer> ids, final Integer userId){
		for(Integer id : ids){
			ApproveRequest approveRequest = get(id);
			approveRequest.setApproveStatus(RequestApproveStatusType.REJECTED.name());
			approveRequest.setRejectReason(rejectReason);
			approveRequest.setProcessedBy(userId);
			approveRequest.setProcessDate(new Date());
		}
	}
	
	public void approveRequest(final List<Integer> ids, final Integer userId){
		for(Integer id : ids){
			ApproveRequest approveRequest = get(id);
			approveRequest.setApproveStatus(RequestApproveStatusType.APPROVED.name());
			approveRequest.setProcessedBy(userId);
			approveRequest.setProcessDate(new Date());
		}
	}
}
