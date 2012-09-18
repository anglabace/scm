package com.genscript.gsscm.manufacture.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.WoOperationResource;

@Repository
public class WoOperationResourceDao extends HibernateDao<WoOperationResource, Integer> {

	/**
	 * 根据operationId获得所有的Resource, 从对象WOOperationResource中查找.
	 * @param groupId
	 * @return
	 */
	public Page<WoOperationResource> getOperationResource(Page<WoOperationResource> page, Integer woOperationId) {
		String hql = "from WoOperationResource gr where gr.woOperationId=?";
		//对默认方法不支持分页的改进
		if (page.isOrderBySetted()) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, woOperationId);
	}
	
	/**
	 * 获得一个Opreation相关的Resource
	 * @param operationId
	 * @return
	 */
	public List<WoOperationResource> getAllList(Integer woOperationId) {
		String hql = "from WoOperationResource gr where gr.woOperationId=? order by seqNo asc";
		return this.find(hql, woOperationId);		
	}
	
	/**
	 * 批量删除Operation Resource
	 * @param ids
	 */
	public void delOperationResourceList(Object ids){
		String hql = "delete from WoOperationResource c where c.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}

	/**
	 * 根据WoOperation List 删除 WoResource
	 * @param woOperationIdList
	 */
	public void delWoResourceByWoOPerationList(List<Integer> woOperationIdList){
		String hql = "delete from WoOperationResource c where c.woOperationId in (:woOperationIdList)";
		Map<String,List<Integer>> map = Collections.singletonMap("woOperationIdList", woOperationIdList);
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("woOperationIdList", map.get("woOperationIdList"));
		batchExecute(hql, values);
	}
}
