package com.genscript.gsscm.manufacture.dao;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.dto.WorkOrderOperationBean;
import com.genscript.gsscm.manufacture.entity.WorkOrderOperation;

@Repository
public class WorkOrderOperationDao extends HibernateDao<WorkOrderOperation, Integer> {

	/**
	 * 获得一个WorkOrder相关的Operation.
	 * @param operationId
	 * @return
	 */
	public List<WorkOrderOperation> getAllList(Integer workOrderNo) {
		String hql = "from WorkOrderOperation gr where gr.workOrderNo=? order by seqNo asc";
		return this.find(hql, workOrderNo);		
	}
	
	/**
	 * 批量删除WorkOrder Operation.
	 * @param ids
	 */
	public void delWorkOrderOperationList(Object ids){
		String hql = "delete from WorkOrderOperation c where c.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}
	
	/**
	 * 对Wo Operation设置他们的exptdStartDate, exptdEndDate, 因为它的seqNo-1的wo operation的status已经变为了Completed
	 * @author wangsf
	 * @serialData 2010-12-24
	 * @param workOrderNo
	 * @param seqNoList
	 */
	public void startScheduleOperateion(Integer workOrderNo, Integer seqNo, Date exptdStartDate, Date exptdEndDate) {
		String hql = "update WorkOrderOperation set exptdStartDate=:exptdStartDate, exptdEndDate=:exptdEndDate where workOrderNo=:workOrderNo and seqNo=:seqNo";
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("workOrderNo", workOrderNo);
		values.put("seqNo", seqNo);
		values.put("exptdStartDate", exptdStartDate);
		values.put("exptdEndDate", exptdEndDate);
		batchExecute(hql, values);
	}

	/**
	 * 完成一个Work order后批量更新wo operation的实际完成时间
	 * @author wangsf
	 * @serialData 2010-12-24
	 * @param workOrderNo
	 */
	public void batchCompleted(Integer workOrderNo) {
		String hql = "update WorkOrderOperation set actualEndDate=:actualEndDate where workOrderNo=:workOrderNo";
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("workOrderNo", workOrderNo);
		values.put("actualEndDate", new Date());
		batchExecute(hql, values);
	}
	
	/**
	 * 
	 */
	public List<WorkOrderOperation> getWorkOrderOPerationList(WorkOrderOperationBean workOrderOperationBean,Integer customFlag,Set<Integer> orderNoList,Set<Integer> soNoSet) {
		String selectHql = "select distinct t.* ";
		String fromHql = "from manufacturing.wo_operations t left join manufacturing.operations t1 on t.src_operation_id=t1.operation_id  left join manufacturing.work_orders t2 on t.order_no = t2.order_no ";
		StringBuffer whereHql = new StringBuffer("where 1=1 ");
		whereHql.append(" and t2.status in('New','In Production') ");
		if(StringUtils.isNotEmpty(workOrderOperationBean.getAltOrderNo())) {
			String[] altOrderNoArray = workOrderOperationBean.getAltOrderNo().split(",");
			if(altOrderNoArray.length==1) {
				whereHql.append(" and t2.alt_order_no='").append(altOrderNoArray[0]).append("'");
			} else {
				whereHql.append(" and t2.alt_order_no in(");
				for(int i =0;i<altOrderNoArray.length;i++) {
					if(i==0) {
						whereHql.append("'").append(altOrderNoArray[i]).append("'");
					} else {
						whereHql.append(",'").append(altOrderNoArray[i]).append("'");
					}
				}
				whereHql.append(")");
			}
		}
		if(soNoSet!=null) {
			whereHql.append(" and t2.so_no in (");
			for(Integer soNo:soNoSet) {
				whereHql.append(soNo).append(","); 
			}
			whereHql.deleteCharAt(whereHql.length()-1);
			whereHql.append(") ");
		}
		if(workOrderOperationBean.getSoNo()!=null) {
			whereHql.append(" and t2.so_no=").append(workOrderOperationBean.getSoNo());
		}
		if(orderNoList!=null&&orderNoList.size()>0) {
			whereHql.append(" and t2.order_no in (");
			for(Integer orderNo:orderNoList) {
				whereHql.append(orderNo).append(","); 
			}
			whereHql.deleteCharAt(whereHql.length()-1);
			whereHql.append(") ");
		}
		if(StringUtils.isNotEmpty(workOrderOperationBean.getExptdStartDate())) {
			whereHql.append(" and t.exptd_start_date>=").append(workOrderOperationBean.getExptdStartDate());
		}
		if(StringUtils.isNotEmpty(workOrderOperationBean.getExptdEndDate())) {
			whereHql.append(" and t.exptd_end_date<=").append(workOrderOperationBean.getExptdEndDate());
		}
		if(StringUtils.isNotEmpty(workOrderOperationBean.getCustomStartDate())) {
			whereHql.append(" and t.custom_start_date>=").append(workOrderOperationBean.getCustomStartDate());
		}
		if(StringUtils.isNotEmpty(workOrderOperationBean.getCustomEndDate())) {
			whereHql.append(" and t.custom_end_date<=").append(workOrderOperationBean.getCustomEndDate());
		}
		if(StringUtils.isEmpty(workOrderOperationBean.getStatus())||workOrderOperationBean.getStatus().equals("0")) {
			if(customFlag==null) {
				whereHql.append(" and t.status in('New','In Production')");
			} else {
				whereHql.append(" and t.status in('New','In Production','Hold')");
			}
		} else {
			whereHql.append(" and t.status='").append(workOrderOperationBean.getStatus()).append("'");
		}
		if(StringUtils.isNotEmpty(workOrderOperationBean.getOperationName())) {
			whereHql.append(" and t1.name like '%").append(workOrderOperationBean.getOperationName()).append("%'");
		}
		List list =  this.getSession().createSQLQuery(selectHql+fromHql+whereHql.toString()).addEntity("t",WorkOrderOperation.class).list();
		return list;
	}
	
	public List<WorkOrderOperation> getWorkOrderOPerationList(Set<Integer> soNoSet) {
		String selectHql = "select distinct t.* ";
		String fromHql = "from manufacturing.wo_operations t left join manufacturing.operations t1 on t.src_operation_id=t1.operation_id  left join manufacturing.work_orders t2 on t.order_no = t2.order_no ";
		StringBuffer whereHql = new StringBuffer("where 1=1 ");
		whereHql.append(" and t2.status!='Cancel'");
		if(soNoSet!=null&&soNoSet.size()>0) {
			whereHql.append(" and t2.so_no in (");
			for(Integer soNo:soNoSet) {
				whereHql.append(soNo).append(",");
			}
			whereHql = new StringBuffer(whereHql.substring(0, whereHql.length()-1)).append(")");
		}
		whereHql.append(" order by t2.order_no,t.seq_no");
		List list =  this.getSession().createSQLQuery(selectHql+fromHql+whereHql.toString()).addEntity("t",WorkOrderOperation.class).list();
		return list;
	}
	
	/**
	 * 根据seqNo workOrderNo查找WorkOrderOperation
	 */
	public WorkOrderOperation findWoOperation(Integer seqNo,Integer workOrderNo) {
		String hql = "from WorkOrderOperation where seqNo=? and workOrderNo=?";
		return this.findUnique(hql, seqNo,workOrderNo);
	}
	
	/**
	 * 查找某workOrder中小于当前seqNo的所有WorkOrderOperation
	 */
	public List<WorkOrderOperation> findWoOperationList(Integer seqNo,Integer workOrderNo) {
		String hql = "from WorkOrderOperation where seqNo<? and workOrderNo=?";
		return this.find(hql, seqNo,workOrderNo);
	}
	
	/**
	 * 根据operationName workOrderNo查找WorkOrderOperation
	 */
	public WorkOrderOperation findWoOperation(String operationName,Integer workOrderNo) {
		String hql = "from WorkOrderOperation where workOrderNo=? and operation.name=?";
		return this.findUnique(hql,workOrderNo,operationName);
	}
	
	/**
	 * 获取work order所有的workOrderOperation中最大的seqNo
	 */
	public Integer getMaxSeq(Integer workOrderNo) {
		String hql = "select max(seqNo) from WorkOrderOperation where workOrderNo=?";
		return this.findUnique(hql, workOrderNo);
	}
	
	public List<Integer> findAllWOperIdByWoAndOper(List<Integer> workOrderNo,Integer operationId) {
		String hql = "select id from  WorkOrderOperation where workOrderNo in(:workOrderNoList) and operationId=:operationId";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("workOrderNoList", workOrderNo);
		map.put("operationId", operationId);
		return this.find(hql, map);
	}
	
	/**
	 * 批量更新work order operation的状态
	 */
	public void batchWorkOrderOperation(List<Integer> workOrderNo,Integer operationId) {
		String hql = "update WorkOrderOperation set status='Completed' where workOrderNo in(:workOrderNoList) and operation.id=:operationId";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("workOrderNoList", workOrderNo);
		map.put("operationId", operationId);
		this.batchExecute(hql, map);
	}
	
	/**
	 * 批量更新work order operation的comment
	 */
	public void updateWorkOrderOperationsComment(Integer id,String comment) {
		String hql = "update WorkOrderOperation set comment=:comment where id=:id";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("comment", comment);
		this.batchExecute(hql, map);
	}
}
