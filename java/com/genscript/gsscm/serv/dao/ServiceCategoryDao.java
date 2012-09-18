package com.genscript.gsscm.serv.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.genscript.gsscm.basedata.dto.DropDownDTO;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.ProductCategory;
import com.genscript.gsscm.serv.entity.ServiceCategory;

@Repository
public class ServiceCategoryDao extends HibernateDao<ServiceCategory, Integer> {
	private static final String DEL_SRVCATS = "delete from ServiceCategory c where c.categoryId in (:ids)";
	private static final String UPDATE_SERVCATSOFCATALOGID = "update ServiceCategory c set c.catalogId = null,c.parentCatId=null,c.previousCatId=null where c.categoryId in (:ids)";
	/*
	 * 删除一串serviceCategory的子级
	 * @param List<Integer> id
	 * @return
	 */
	public void delServiceCatList(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DEL_SRVCATS, map);
	}
	
	public void delServiceCatListByModifyPCatId(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(UPDATE_SERVCATSOFCATALOGID, map);
	}
	
	public Page<ServiceCategory> searchCategoryMapList(Page<ServiceCategory> page,Map<String,ServiceCategory> servcatMap) {
		String hql = "from ServiceCategory c where and c.catalogId is null and c.parentCatId is null and c.status='ACTIVE' order by modifyDate DESC";
		for(Map.Entry<String, ServiceCategory> entry: servcatMap.entrySet()){
			if(entry.getValue()!=null){
				hql+=" and c.categoryId <> "+entry.getValue().getCategoryId();
			}
		}
		hql+=" order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	
	public Page<ServiceCategory> searchCategoryByMapList(Page<ServiceCategory> page,Map<String,ServiceCategory> servcatMap,Integer categoryLevel) {
		String hql = "from ServiceCategory c where and c.catalogId is null and c.parentCatId is null and c.status='ACTIVE' and categoryLevel='"+categoryLevel+"' order by modifyDate DESC";
		for(Map.Entry<String, ServiceCategory> entry: servcatMap.entrySet()){
			if(entry.getValue()!=null){
				hql+=" and c.categoryId <> "+entry.getValue().getCategoryId();
			}
		}
		hql+=" order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	
	public Page<ServiceCategory> searchCategoryLists(Page<ServiceCategory> page,Integer categoryLevel) {
		String hql = "from ServiceCategory c where c.catalogId is null and c.parentCatId is null and c.status='ACTIVE' and categoryLevel='"+categoryLevel+"'  order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	
	public Page<ServiceCategory> searchCategorySList(Page<ServiceCategory> page) {
		String hql = "from ServiceCategory c where c.catalogId is null and c.parentCatId is null and c.status='ACTIVE' order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	
	public Page<ServiceCategory> searchSubCategoryList(Page<ServiceCategory> page,Integer categoryId){
		String hql = "from ServiceCategory c where c.parentCatId is null and c.catalogId is null order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	
	public Page<ServiceCategory> searchSubCategoryLists(Page<ServiceCategory> page,Integer categoryId,Integer categoryLevel){
		String hql = "from ServiceCategory c where c.parentCatId is null and c.catalogId is null and categoryLevel="+categoryLevel+" order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	
	public Page<ServiceCategory> searchSubCategoryMapList(Page<ServiceCategory> page,Integer categoryId,Map<String,ServiceCategory> map){
		String hql = "from ServiceCategory c where c.parentCatId is null and c.catalogId is null";
		for(Map.Entry<String, ServiceCategory> entry: map.entrySet()){
			if(entry.getValue()!=null){
				hql+=" and c.categoryId <> "+entry.getValue().getCategoryId();
			}
		}
		hql+=" order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	
	public Page<ServiceCategory> searchSubCategoryByMapList(Page<ServiceCategory> page,Integer categoryId,Map<String,ServiceCategory> map,Integer categoryLevel){
		String hql = "from ServiceCategory c where c.status='ACTIVE' and c.parentCatId is null and c.catalogId is null and c.categoryLevel="+categoryLevel;
		for(Map.Entry<String, ServiceCategory> entry: map.entrySet()){
			if(entry.getValue()!=null){
				hql+=" and c.categoryId <> "+entry.getValue().getCategoryId();
			}
		}
		hql+=" order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	
	/**
	 * 获得一个ServiceCategory的子级ServiceCategory的数量.
	 * @param categoryId
	 * @return
	 */
	public Long getSubServCategoryCount(Integer categoryId) {
		String hql = "select count(*) from ServiceCategory where parentCatId=?";
		return this.findUnique(hql, categoryId);
	}
	
	/**
	 * 获得一个catalog的ServiceCategory的数量.
	 * @param catalogId
	 * @return
	 */
	public Long getCountByCatalogId(String catalogId) {
		String hql = "select count(*) from ServiceCategory where catalogId=?";
		return this.findUnique(hql, catalogId);		
	}
	
	/**
	 * 获得用于Copy Catalog时ServiceCategory list.
	 * @param catalogId
	 * @return
	 */
	public List<ServiceCategory> getServCategoryListForCopyCatalog(String catalogId) {
		String hql = "from ServiceCategory where catalogId=? and status='ACTIVE' order by parentCatId, categoryId";
		return this.find(hql, catalogId);
	}
	
	/*
	 * 获取category 数据；根据catalogId,parentCategoryId,categoryLevel;
	 */
	public List<ServiceCategory> getServiceCategoryList(String catalogId,Integer parentCatId,Integer categoryLevel){
		String hql = "from ServiceCategory where catalogId='"+catalogId+"' and categoryLevel="+categoryLevel;
		if(parentCatId!=null){
			hql += " and parentCatId="+parentCatId;
		}
		return this.find(hql);
	}

    //add by zhanghuibin
    public List<DropDownDTO> getCategoryList(Integer categoryLevel){
		String hql = " select categoryId,name from ServiceCategory where categoryLevel="+categoryLevel;
        List serviceCategories = this.find(hql);
        List<DropDownDTO> dropDownDTOList = new ArrayList<DropDownDTO>();
        for (Object ser : serviceCategories) {
            DropDownDTO downDTO = new DropDownDTO();
            Object[] obj = (Object[]) ser;
            downDTO.setId(obj[0].toString());
            downDTO.setName((String) obj[1]);
            dropDownDTOList.add(downDTO);
        }
		return dropDownDTOList;
	}
}
