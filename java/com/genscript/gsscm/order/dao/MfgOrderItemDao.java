package com.genscript.gsscm.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.order.entity.MfgOrderItem;
import com.genscript.gsscm.order.entity.OrderItem;

@Repository
public class MfgOrderItemDao extends HibernateDao<MfgOrderItem, Integer>{
	
	private static final String getPeptideByParent="from MfgOrderItem where parentId=:parentId and type='SERVICE' and clsId=1";
	
	 /**
     * 获得MfgOrder某种status的所有MfgOrderItem.
     *
     * @param orderNo
     * @param itemStatus
     * @return
     * @author wangsf
     * @since 2010-10-28
     */
    public List<MfgOrderItem> getItemListByType(Integer orderNo,
                                             OrderItemStatusType itemStatus) {
        String hql = "from MfgOrderItem where orderNo=:orderNo AND status=:status order by itemNo ASC";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", orderNo);
        map.put("status", itemStatus.value());
        return this.find(hql, map);
    }
    
    /**
     * 获得MfgOrder不是某些status的所有MfgOrderItem.
     */
    public List<MfgOrderItem> getItemListNotInType(Integer orderNo,
			List<String> itemStatus) {
		String hql = "from MfgOrderItem where orderNo=:orderNo AND status not in(:status) order by itemNo ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("status", itemStatus);
		return this.find(hql, map);
	}
    
    public MfgOrderItem searchOrderItemByOrderNoAndItemNo(Integer orderNo, Integer itemNo){
   	 String hql = "FROM MfgOrderItem WHERE orderNo=:orderNo AND itemNo=:itemNo";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", orderNo);
        map.put("itemNo", itemNo);
        return this.findUnique(hql, map);
   }
    
    public List<MfgOrderItem> searchOrderItemByOrderNoAndCatalogNo(Integer orderNo, String catalogNo) {
   	 String hql = "FROM MfgOrderItem WHERE orderNo=:orderNo AND catalogNo=:catalogNo";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", orderNo);
        map.put("catalogNo", catalogNo);
        return this.find(hql, map);
   }
    
    public List<MfgOrderItem> getPeptideByParent(Integer parentId) {
   	 Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentId", parentId);
   	return this.find(getPeptideByParent, map);
   }

}
