package com.genscript.gsscm.manufacture.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.WoOperationComponent;

@Repository
public class WoOperationComponentDao extends HibernateDao<WoOperationComponent, Integer> {

	/**
	 * 根据operationId获得所有的Component, 从对象WOOperationComponent中查找.
	 * @param groupId
	 * @return
	 */
	public Page<WoOperationComponent> getOperationComponent(Page<WoOperationComponent> page, Integer woOperationId) {
		String hql = "from WoOperationComponent gr where gr.woOperationId=?";
		//对默认方法不支持分页的改进
		if (page.isOrderBySetted()) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, woOperationId);
	}
	
	/**
	 * 获得一个Opreation相关的Component
	 * @param operationId
	 * @return
	 */
	public List<WoOperationComponent> getAllList(Integer woOperationId) {
		String hql = "from WoOperationComponent gr where gr.woOperationId=? order by seqNo asc";
		return this.find(hql, woOperationId);		
	}
	
	/**
	 * 批量删除Operation Component
	 * @param ids
	 */
	public void delOperationComponentList(Object ids){
		String hql = "delete from WoOperationComponent c where c.id in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}

	/**
	 * 根据WoOperation List 删除 WoComponent
	 * @param woOperationIdList
	 */
	public void delWoComponentByWoOPerationList(List<Integer> woOperationIdList){
		String hql = "delete from WoOperationComponent c where c.woOperationId in (:woOperationIdList)";
		Map<String,List<Integer>> map = Collections.singletonMap("woOperationIdList", woOperationIdList);
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("woOperationIdList", map.get("woOperationIdList"));
		batchExecute(hql, values);
	}
}
