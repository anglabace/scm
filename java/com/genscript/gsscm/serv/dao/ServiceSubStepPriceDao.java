package com.genscript.gsscm.serv.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.ServiceSubStepPrice;

@Repository
public class ServiceSubStepPriceDao extends HibernateDao<ServiceSubStepPrice, Integer> {
    /*
      * 获得该catalogId,serviceId相关的price
      */
    public ServiceSubStepPrice getServiceSubStepPriceByCatalogIdServiceId(String catalogId, Integer stepId) {
        String hql = "from ServiceSubStepPrice where stepId=" + stepId + " and catalogId='" + catalogId + "'";
        List<ServiceSubStepPrice> list = this.find(hql);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public ServiceSubStepPrice getObjectByID(Integer objectId) {
        String hql = "from  ServiceSubStepPrice where priceId  =?";
        return this.findUnique(hql, objectId);
    }



	public ServiceSubStepPrice getPriceBystepsId(Integer stepId) {
       String sql_getBysteps = "from ServiceSubStepPrice where stepId= ? ";
        return this.findUnique(sql_getBysteps, stepId);  
    }
    
    /**
     * 表连接查询 集合
     * @author Zhang Yong
     * @param serviceId
     * @param catalogId
     * @return
     */
    public List<ServiceSubStepPrice> getPrice (Integer serviceId, String catalogId) {
    	String hql = "select sssp from ServiceSubSteps sss, ServiceSubStepPrice sssp " +
    			" where sss.serviceId=:serviceId and sss.stepId=sssp.stepId and sssp.catalogId=:catalogId";
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("serviceId", serviceId);
    	map.put("catalogId", catalogId);
    	List<ServiceSubStepPrice> list = this.find(hql, map);
    	if (list == null || list.isEmpty()) {
    		return null;
    	}
    	return list;
    }
    
    /**
     * 查询pkgService item的Price、Cost
     * @author Zhang Yong
     * add date 2011-10-25
     * @param catalogNo
     * @param catalogId
     * @param name
     * @param seqFlag
     * @return
     */
    public BigDecimal[] getPriceCost (String catalogNo, String catalogId, String name, String seqFlag) {
    	BigDecimal[] priceCost = null;
    	String hql = "SELECT sssp.retailPrice, sss.cost FROM Service s, ServiceSubSteps sss, ServiceSubStepPrice sssp "
    		+ " WHERE s.catalogNo=:catalogNo AND s.serviceId=sss.serviceId AND sss.stepId=sssp.stepId "
    		+ " AND sss.seqFlag=:seqFlag AND sss.name like :name AND sssp.catalogId=:catalogId "
    		+ " AND sssp.retailPrice is not null AND sss.cost is not null";
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("catalogNo", catalogNo);
    	map.put("seqFlag", seqFlag);
		map.put("name", "%" + name + "%");
		map.put("catalogId", catalogId);
		List<Object[]> list = this.find(hql, map);
		if (list == null || list.isEmpty()) {
			return priceCost;
		}
		for (Object[] objs : list) {
			if (objs != null) {
				priceCost = new BigDecimal[2];
				priceCost[0] = new BigDecimal(objs[0].toString());
				priceCost[1] = new BigDecimal(objs[1].toString());
				break;
			}
		}
    	return priceCost;
    }
}
