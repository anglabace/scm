package com.genscript.gsscm.purchase.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.purchase.entity.PoReceivingTmp;

@Repository
public class PoReceiveingTmpDao extends HibernateDao<PoReceivingTmp, Integer> {
	
	public List<PoReceivingTmp> searchPoTmp(Integer poNo ,Integer itemNo){
		String hql = "from PoReceivingTmp where poNo = "+poNo+"  and  poLineNo = "+itemNo;
		return this.find(hql);
	}
	
	public List<PoReceivingTmp> searcPoTmp(String soNos){
		String hql = "select tmp from PoReceivingTmp tmp, PurchaseOrder po where tmp.poNo = po.orderNo and po.srcSoNo in("+soNos+")";
		return this.find(hql);
	}
	
	public void delPoTmp(String trNo){                                                                                        
		 String hql = "delete from PoReceivingTmp pt where pt.trackingNo='"+trNo+"' and not EXISTS (select 1 from ReceivingLog rl where rl.trackingNo ='"+trNo+"' )";	
	 		//Map<String, Object> map = new HashMap<String, Object>();
	 	 this.batchExecute(hql);
	}
	
	public void delTmp(String tmpId){
		String hql = "delete from PoReceivingTmp where id in("+tmpId+")";
		 this.batchExecute(hql);
	}
	
	public void flush(){
		Session session = this.getSession();
		session.flush();
		session.clear();
	}
}
