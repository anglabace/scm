package com.genscript.gsscm.serv.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.ServiceIntermediate;

@Repository
public class ServiceIntermediateDao extends
		HibernateDao<ServiceIntermediate, Integer> {
	private static final String DEL_INTMD = "delete from ServiceIntermediate c where c.id in (:ids)";

	public void delIntmdList(Object ids) {
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DEL_INTMD, map);
	}

	/**
	 * 获得ServiceIntermediate
	 * 
	 * @param serviceId
	 * @param intmdKeyword
	 * @return
	 */
	public ServiceIntermediate getIntmd(Integer serviceId, String intmdKeyword) {
		String hql = " from ServiceIntermediate where serviceId=? and intmdKeyword=?";
		List<ServiceIntermediate> list = find(hql, serviceId, intmdKeyword);
		if(list!=null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 获得ServiceIntermediate
	 * 
	 * @param serviceId
	 * @param intmdKeyword
	 * @return
	 */
	public List<ServiceIntermediate> getIntmdList(Integer serviceId, String intmdKeyword) {
		String hql = " from ServiceIntermediate where serviceId=? and intmdKeyword=?";
		return find(hql, serviceId, intmdKeyword);
	}
	
	/**
	 * 通过intmdCatalogNo查询
	 * @author Zhang Yong
	 * @param catalogNos
	 * @return
	 */
	public List<ServiceIntermediate> getIntmdByCatalogNo (String catalogNos) {
		String hql = "FROM ServiceIntermediate WHERE intmdCatalogNo IN("+catalogNos+") ";
		return find(hql);
	}
	
	/**
	 * 通过serviceId查找集合
	 * @author Zhang Yong
	 * @param serviceId
	 * @return
	 */
	public List<ServiceIntermediate> getIntmdByServiceId (Integer serviceId) {
		String hql = "FROM ServiceIntermediate WHERE serviceId=? ORDER BY listSeq";
		return find(hql, serviceId);
	}
	
	/**
	 * 查询可以自动添加为子服务的集合
	 * @author Zhang Yong
	 * @param serviceId
	 * @param requiredFlag
	 * @return
	 */
	public List<ServiceIntermediate> getIntermediate (Integer serviceId, String requiredFlag) {
		String hql = "From ServiceIntermediate where serviceId=:serviceId and requiredFlag=:requiredFlag";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("serviceId", serviceId);
        map.put("requiredFlag", requiredFlag);
        List<ServiceIntermediate> list = find(hql, map);
        if (list == null || list.isEmpty()) {
        	return null;
        }
        return list;
	}
}
