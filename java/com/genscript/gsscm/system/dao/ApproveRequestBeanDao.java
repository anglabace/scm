package com.genscript.gsscm.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.RequestApproveStatusType;
import com.genscript.gsscm.system.entity.ApproveRequestBean;

@Repository
public class ApproveRequestBeanDao extends HibernateDao<ApproveRequestBean, Integer> {
	private static final String UNAPPROVED_APPROVE_REQUEST = "from ApproveRequestBean a where a.objectId =? and a.tableName =? and a.approveStatus=?";
	private static final String UNAPPROVED_REQUEST_ID = "select distinct a.objectId from ApproveRequestBean a where a.tableName =? and a.approveStatus=?";
	private static final String UNAPPROVED_REQUEST_STATUS = "from ApproveRequestBean a where a.objectId =? and a.columnName =? and a.tableName =? and a.approveStatus=?";
	private static final String GET_UNAPPROVED_REQUEST = "from ApproveRequestBean a where a.requestId =? and a.tableName =? and a.approveStatus=?";
	public List<ApproveRequestBean> getUnapprovedRequest(final Integer objectId, final String tableName){
		return find(UNAPPROVED_APPROVE_REQUEST, objectId, tableName, RequestApproveStatusType.UNPROCESSED.name());
	}
	public List<ApproveRequestBean> getApprovedRequest(final Integer objectId, final String tableName){
		return find(UNAPPROVED_APPROVE_REQUEST, objectId, tableName, RequestApproveStatusType.APPROVED.name());
	}
	public List<Integer> getUnapprovedRequestId(final String tableName){
		return find(UNAPPROVED_REQUEST_ID, tableName, RequestApproveStatusType.UNPROCESSED.name());
	}
	public List<Integer> getApprovedRequestId(final String tableName){
		return find(UNAPPROVED_REQUEST_ID, tableName, RequestApproveStatusType.APPROVED.name());
	}
	public Boolean getUnapprovedRequestStatus(final Integer objectId, final String columnName, final String tableName){
		return findUnique(UNAPPROVED_REQUEST_STATUS, objectId, columnName, tableName, RequestApproveStatusType.UNPROCESSED.name())!=null?true:false;
	}
	public List<ApproveRequestBean> getUnapprovedRequestList(final Integer requestId, final String tableName){
		return find(GET_UNAPPROVED_REQUEST, requestId, tableName, RequestApproveStatusType.UNPROCESSED.name());
	}
	public List<ApproveRequestBean> getApprovedRequestListByTableTypeStatus(String tableName){
		String hql = "from ApproveRequestBean a where a.tableName =? and a.approveStatus=? and a.columnName=?";
		return find(hql,tableName,RequestApproveStatusType.UNPROCESSED.name(),"status");
	}
	public List<ApproveRequestBean> getApprovedRequestListByTable(String tableName){
		String hql = "from ApproveRequestBean a where a.tableName =? and a.approveStatus=?";
		return find(hql,tableName,RequestApproveStatusType.UNPROCESSED.name());
	}
}
