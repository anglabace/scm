package com.genscript.gsscm.manufacture.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.OperationResource;

@Repository
public class OperationResourceDao extends HibernateDao<OperationResource, Integer> {

	/**
	 * 根据operationId获得所有的Resource, 从对象Operation Resource中查找.
	 * @param groupId
	 * @return
	 */
	public Page<OperationResource> getOperationResource(Page<OperationResource> page, Integer operationId) {
		String hql = "from OperationResource gr where gr.operationId=?";
		//对默认方法不支持分页的改进
		if (page.isOrderBySetted()) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, operationId);
	}
	
	/**
	 * 获得一个Opreation相关的Resource
	 * @param operationId
	 * @return
	 */
	public List<OperationResource> getAllList(Integer operationId) {
		String hql = "from OperationResource gr where gr.operationId=? order by seqNo asc";
		return this.find(hql, operationId);		
	}
	
	/**
	 * 批量删除Operation Resource
	 * @param ids
	 */
	public void delOperationResourceList(Object ids){
		String hql = "delete from OperationResource c where c.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}
	
	/**
	 * 有operationId和resourceId获取OperationResource对象
	 */
	public OperationResource findByOpAndRE(Integer operationId,Integer resourceId) {
		String hql="from OperationResource where operationId=? and resource.resourceId=?";
		List<OperationResource> list = this.find(hql, operationId,resourceId);
		return list==null||list.size()==0?null:list.get(0);
	}

}
