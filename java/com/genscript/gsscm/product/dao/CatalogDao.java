package com.genscript.gsscm.product.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.CatalogType;
import com.genscript.gsscm.product.entity.Catalog;

@Repository
public class CatalogDao extends HibernateDao<Catalog, Integer> {
	private static final String GET_BASE_CATALOG = "select c.catalogId from Catalog c where c.defaultFlag='Y' and c.status='ACTIVE'";

	
	public String getBaseCatalogId(){
		return findUnique(GET_BASE_CATALOG);
	}
	public Catalog getWithoutCached(Integer id){
		return (Catalog)createCriteria(Restrictions.eq("id", id)).uniqueResult();
	}
	
	/**
	 * 根据catalogId获得Catalog.
	 * @param catalogId
	 * @return
	 */
	public Catalog getCatalogByCatalogId(String catalogId) {
		String hql = " from Catalog where catalogId=?";
		return this.findUnique(hql, catalogId);
	}


	
	public List<Catalog> getSpecialCatalogList(){
		//Criterion criterion1 = Restrictions.eq("defaultFlag", "Y");
		Criterion criterion2 = Restrictions.eq("status", "ACTIVE");
        String hql="from Catalog where status='ACTIVE' order by defaultFlag desc";
        return  this.find(hql);
//		return find(criterion2);
	}
	
	/**
	 * 返回baseCatalogId
	 * @return
	 */
	public String getBaseCatalogList(){
		Criterion criterion1 = Restrictions.eq("defaultFlag", "Y");
		Criterion criterion2 = Restrictions.eq("status", "ACTIVE");
		List<Catalog> catalogList = find(criterion1,criterion2);
		if(catalogList.size() > 0){
			return catalogList.get(0).getCatalogId();
		}else{
			return null;
		}
	}
	
	public Catalog getBaseCatalog(){
		Criterion criterion1 = Restrictions.eq("defaultFlag", "Y");
		Criterion criterion2 = Restrictions.eq("status", "ACTIVE");
		List<Catalog> catalogList = find(criterion1,criterion2);
		if(catalogList.size() > 0){
			return catalogList.get(0);
		}else{
			return null;
		}
	}
	
	public List<Catalog> getCatalogList(String catalogType){
		Criterion criterion;
		Criterion criterion1,criterion2,criterion3;
		criterion3 = Restrictions.eq("status", "ACTIVE");
		if (catalogType.equals(CatalogType.PRODUCT.value())){
			criterion1 = Restrictions.eq("type", CatalogType.PRODUCT.value());
			criterion2 = Restrictions.eq("type", "ALL");
		}else {
			criterion1 = Restrictions.eq("type", CatalogType.SERVICE.value());
			criterion2 = Restrictions.eq("type", "ALL");
			
		}
		criterion = Restrictions.or(criterion1, criterion2);
		return find(criterion,criterion3);
	}
	
	public List<Catalog> getFilterCatalogList(String catalogType, List<String> catalogIdList){
		Criterion criterion;
		List<Criterion> criterionList = new ArrayList<Criterion>();
		Criterion criterion1,criterion2,criterion3;
		criterion3 = Restrictions.eq("status", "ACTIVE");
		if (catalogType.equals(CatalogType.PRODUCT.value())){
			criterion1 = Restrictions.eq("type", CatalogType.PRODUCT.value());
			criterion2 = Restrictions.eq("type", "ALL");
		}else {
			criterion1 = Restrictions.eq("type", CatalogType.SERVICE.value());
			criterion2 = Restrictions.eq("type", "ALL");
			
		}
		criterion = Restrictions.or(criterion1, criterion2);
		criterionList.add(criterion);
		criterionList.add(criterion3);
		if (catalogIdList != null && !catalogIdList.isEmpty()) {
			criterion1 = Restrictions.not(Restrictions.in("catalogId",
					catalogIdList));
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(criterion1);
			criterionList.add(disjunction);
		}
		Criterion[] criterions = criterionList
		.toArray(new Criterion[criterionList.size()]);
		return find(criterions);
	}
	
	/**
	 * 根据id删除catalog.
	 * @param delId
	 */
    public void delById(Integer delId) {
        String hql = "delete from Catalog where id=:delId)";	
 		Map<String, Object> map = new HashMap<String, Object>();
 		map.put("delId", delId);
 		this.batchExecute(hql, map);
    }
}
