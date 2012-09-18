package com.genscript.gsscm.quote.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderPackage;
import com.genscript.gsscm.quote.entity.QuotePackage;

@Repository
public class QuotePackageDao extends HibernateDao<QuotePackage, Integer>{
    /**
     * 获得一个package的总的quantity(所有packageItem quantity的总和).
     * @param packageId
     * @return
     */
    public Long getTotalQty(Integer packageId) {
    	String hql = "select sum(quantity) from QuotePackageItem where packageId=?";
    	return this.findUnique(hql, packageId);
    }
    
    /**
     * 删除一个Quote所有的Package list.
     * @param quoteNo
     */
    public void delQuotePackageListByQuoteNo(Integer quoteNo) {
        String hql = "delete from QuotePackage where quoteNo=:quoteNo";	
 		Map<String, Object> map = new HashMap<String, Object>();
 		map.put("quoteNo", quoteNo);
 		this.batchExecute(hql, map);
     }
    
	/**
	 * 根据quoteNo获得quotePackage list.
	 * @param quoteNo
	 * @return
	 */
    public List<QuotePackage> getQuotePackageList(Integer quoteNo) {
		String hql = "from QuotePackage where quoteNo=:quoteNo order by packageId ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		return this.find(hql, map);
    }
    
    /**
     * 删除一个Quote所有的Package list.
     * @param quoteNo
     */
    public void delPackageListByQuoteNo(Integer quoteNo) {
        String hql = "delete from QuotePackage where quoteNo=:quoteNo";	
 		Map<String, Object> map = new HashMap<String, Object>();
 		map.put("quoteNo", quoteNo);
 		this.batchExecute(hql, map);
     }
    
    /**
     * 获得一个Quote所有Package的数量.
     * @param quoteNo
     * @return
     */
    public Long getQuotePackageCount(Integer quoteNo) {
		String hql = "select count(*) from QuotePackage where quoteNo=?";
		return this.findUnique(hql, quoteNo);
    }
    
    /**
     * 获得一个Quote所有Package的weight之和.
     * @param quoteNo
     * @return
     */
    public Double getQuotePackageWeight(Integer quoteNo) {
		String hql = "select sum(billableWeight) from QuotePackage where quoteNo=?";
		return this.findUnique(hql, quoteNo);
    }
    
    /**
     * 获得一个Quote所有Package的Customer charge之和.
     * @param quoteNo
     * @return
     */
    public Double getQuotePackageCost(Integer quoteNo) {
		String hql = "select sum(customerCharge) from QuotePackage where quoteNo=?";
		return this.findUnique(hql, quoteNo);
    }
    
    /**
     * 获得一个Quote所有Package中zone的类别数.
     * @param quoteNo
     * @return
     */
    public Long getQuotePackageZoneCount(Integer quoteNo) {
		String hql = "select count(distinct zone) from QuotePackage where quoteNo=?";
		return this.findUnique(hql, quoteNo);
    }
    
    /**
     * 获得一个Quote的第一个Package.
     * @param quoteNo
     * @return
     */
    public QuotePackage getQuoteFirstPackage(Integer quoteNo) {
    	Page<QuotePackage> page = new Page<QuotePackage>();
    	page.setPageNo(1);
    	page.setPageSize(1);
    	String hql = "from QuotePackage where quoteNo=? order by packageId asc";
    	List<QuotePackage> list =  this.findPage(page, hql, quoteNo).getResult();
    	if (list != null && !list.isEmpty()) {
    		return list.get(0);
    	}
    	return null;
    }
    
    /**
     * 获得一个Quote所有Package中ship method的类别数.
     * @param quoteNo
     * @return
     */
    public Long getQuotePackageShipMethodCount(Integer quoteNo) {
		String hql = "select count(distinct shipMethod) from QuotePackage where quoteNo=?";
		return this.findUnique(hql, quoteNo);
    }
    
    /**
     * 获得一个Quote所有Package的ship method list.
     * @param quoteNo
     * @return
     */
    public List<Integer> getQuoteShipMethodList(Integer quoteNo) {
    	String hql = "select shipMethod from QuotePackage where quoteNo=?";
    	return this.find(hql, quoteNo);
    }
    
    /**
     * 批量删除多个QuotePackage.
     * @param delPackageIdList
     */
    public void delQuotePackageList(List<Integer> delPackageIdList) {
        String hql = "delete from QuotePackage where packageId in (:delPackageIdList)";	
 		Map<String, Object> map = new HashMap<String, Object>();
 		map.put("delPackageIdList", delPackageIdList);
 		this.batchExecute(hql, map);
    }
}
