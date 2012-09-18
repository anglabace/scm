package com.genscript.gsscm.manufacture.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.WoBatchDetail;
@Repository
public class WoBatchDetailDao extends HibernateDao<WoBatchDetail, Integer> {
	
	/**
	 * 根据orderNo和打包操作查找WoBatchDetail是否有该记录
	 * @param orderNo 订单号
	 * @param batchFunction 打包操作(QC:质量控制QA:质量检测)
	 * @return
	 */
	public boolean isHasThisNo(Integer orderNo,String batchFunction) {
		boolean flg = false;
		String hql = "select count(wbd.id) from WoBatchDetail wbd where wbd.workOrders.orderNo=? and wbd.woBatche.batchFunction=?";
		if((Long)this.find(hql, orderNo,batchFunction).get(0)>0) {
			flg = true;
		}
		return flg;
	}
	
	/**
	 * 根据orderNo获得batchNo
	 * @param orderNo 订单号
	 * @param batchFunction 打包类型(QC,QA)
	 * @return
	 */
	public String getBatchNoByOrderNo(Integer orderNo,String batchFunction) {
		String hql = "select wbd.woBatche.batchNo from WoBatchDetail wbd where wbd.workOrders.orderNo=? and wbd.woBatche.batchFunction=?";
		List<String> batchNoList =  this.find(hql, orderNo,batchFunction);
		if(batchNoList!=null&&batchNoList.size()>0) {
			return batchNoList.get(0);
		} 
		return null;
	}
	
	/**
	 * 根据batchNo查找Work Order orderNo列表
	 * @serialData 2010-12-02
	 * @author wangsf
	 * @param batchNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getOrderNoListByBatchNo(String batchNo) {
		String hql = "select wbd.workOrders.orderNo from WoBatchDetail wbd where wbd.woBatche.batchNo=?";
		List list = createQuery(hql, batchNo).list();
		if(list != null){
			return (List<Integer>)list;
		}
		return null;
	}

}
