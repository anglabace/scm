package com.genscript.gsscm.quote.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuotePackage;
import com.genscript.gsscm.quote.entity.QuotePackageItem;

@Repository
public class QuotePackageItemDao extends HibernateDao<QuotePackageItem, Integer>{
	/**
	 * 根据quoteNo和该quote的几个item的itemNo list获得这几个QuoteItem的QuotePackage.
	 * @param orderNo
	 * @param itemNoList
	 * @return
	 */
    public List<QuotePackage> getPackagesForItemList(Integer quoteNo, Object itemNoList) {
		String hql = "from QuotePackage where packageId in (select packageId from QuotePackageItem where quoteNo=:quoteNo and itemNo in (:itemNoList) group by packageId)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		map.put("itemNoList", itemNoList);
		return this.find(hql, map);
    }
    
    /**
	 * 根据orderNo获得shipPackage list.
	 * @param orderNo
	 * @return
	 */
    public List<QuotePackage> getQuotePackageList(Integer quoteNo) {
		String hql = "from QuotePackage where quoteNo=:qutoeNo order by packageId ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		return this.find(hql, map);
    }
    
    /**
     * 删除一个Quote下的所有PackageItem.
     * @param quoteNo
     */
    public void delItemListByQuoteNo(Integer quoteNo) {
       String hql = "delete from QuotePackageItem where quoteNo=:quoteNo";	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		this.batchExecute(hql, map);
    }
   
   /**
    * 通过 packageId list批量删除Package Item.
    * @param delPackageIdList
    */
   public void delItemListByPackageIdList(List<Integer> delPackageIdList) {
       String hql = "delete from QuotePackageItem where packageId in (:delPackageIdList)";	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("delPackageIdList", delPackageIdList);
		this.batchExecute(hql, map);
   }
   
   /**
    * 通过packageId查询QuotePackageItem
    * @param packageId
    * @return
    */
   public List<QuotePackageItem> findPackageItemsByPkgId (Integer packageId) {
   	String hql = "from QuotePackageItem where packageId=:packageId";
   	Map<String, Object> map = new HashMap<String, Object>();
		map.put("packageId", packageId);
		return this.find(hql, map);
   }
}
