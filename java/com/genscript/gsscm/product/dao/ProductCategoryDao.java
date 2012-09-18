package com.genscript.gsscm.product.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.genscript.gsscm.basedata.dto.DropDownDTO;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.ProductCategory;

@Repository
public class ProductCategoryDao extends HibernateDao<ProductCategory, Integer> {
	private static final String DEL_PDTCATS = "delete from ProductCategory c where c.categoryId in (:ids)";
	private static final String UPDATE_PDTCATSOFCATALOGID="update ProductCategory c set c.catalogId = null,c.parentCatId=null,c.previousCatId=null where c.categoryId in (:ids)";
	
	public void delPdtCatList(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DEL_PDTCATS, map);
	}
	
	public void delPdtCatListByModifyPCatId(Object ids){
		Map<String ,Object> map = Collections.singletonMap("ids",ids);
		batchExecute(UPDATE_PDTCATSOFCATALOGID,map);
	}
	
	public Page<ProductCategory> searchCategoryMapList(Page<ProductCategory> page,Map<String,ProductCategory> pdtcatMap) {
		String hql = "from ProductCategory c where c.catalogId is null and c.parentCatId is null and c.status='ACTIVE' order by modifyDate DESC";
		for(Map.Entry<String, ProductCategory> entry: pdtcatMap.entrySet()){
			if(entry.getValue()!=null){
				hql+=" and c.categoryId <> "+entry.getValue().getCategoryId();
			}
		}
		hql+=" order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	
	public Page<ProductCategory> searchCategoryByMapList(Page<ProductCategory> page,Map<String,ProductCategory> pdtcatMap,String cataId,Integer categoryLevel) {
		String hql = "from ProductCategory c where c.catalogId is null and c.parentCatId is null and c.status='ACTIVE' and c.categoryLevel ='"+categoryLevel+"' order by modifyDate DESC";
		for(Map.Entry<String, ProductCategory> entry: pdtcatMap.entrySet()){
			if(entry.getValue()!=null){
				hql+=" and c.categoryId <> "+entry.getValue().getCategoryId();
			}
		}
		hql+=" order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	
	public Page<ProductCategory> searchCategoryListS(Page<ProductCategory> page,Integer categoryLevel) {
		String hql = "from ProductCategory c where c.catalogId is null and c.parentCatId is null and c.status='ACTIVE' and c.categoryLevel='"+categoryLevel+"' order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	
	public Page<ProductCategory> searchCategorySList(Page<ProductCategory> page) {
		String hql = "from ProductCategory c where c.catalogId is null and c.parentCatId is null and c.status='ACTIVE' order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	
	public Page<ProductCategory> searchSubCategoryList(Page<ProductCategory> page,Integer categoryId){
		String hql = "from ProductCategory c where  c.parentCatId is null and c.catalogId is null order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	
	public Page<ProductCategory> searchSubCategoryLists(Page<ProductCategory> page,Integer categoryId,Integer categoryLevel){
		String hql = "from ProductCategory c where  c.parentCatId is null and c.catalogId is null and c.categoryLevel = "+categoryLevel+" order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	
	public Page<ProductCategory> searchSubCategoryMapList(Page<ProductCategory> page,Integer categoryId,Map<String,ProductCategory> map){
		String hql = "from ProductCategory c where c.status='ACTIVE' and c.parentCatId is null and c.catalogId is null";
		for(Map.Entry<String, ProductCategory> entry: map.entrySet()){
			if(entry.getValue()!=null){
				hql+=" and c.categoryId <> "+entry.getValue().getCategoryId();
			}
		}
		hql+=" order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	public Page<ProductCategory> searchSubCategoryByMapList(Page<ProductCategory> page,Integer categoryId,Integer categoryLevel,Map<String,ProductCategory> map){
		String hql = "from ProductCategory c where c.status='ACTIVE' and c.parentCatId is null and c.catalogId is null and c.categoryLevel = "+categoryLevel;
		for(Map.Entry<String, ProductCategory> entry: map.entrySet()){
			if(entry.getValue()!=null){
				hql+=" and c.categoryId <> "+entry.getValue().getCategoryId();
			}
		}
		hql+=" order by modifyDate DESC";
		return this.findPage(page, hql);
	}
	/**
	 * 获得一个ProductCategory的子级PrdouctCategory的数量.
	 * @param categoryId
	 * @return
	 */
	public Long getSubPdtCategoryCount(Integer categoryId) {
		String hql = "select count(*) from ProductCategory where parentCatId=?";
		return this.findUnique(hql, categoryId);
	}
	
	/**
	 * 获得一个catalog的productCategory的数量.
	 * @param catalogId
	 * @return
	 */
	public Long getCountByCatalogId(String catalogId) {
		String hql = "select count(*) from ProductCategory where catalogId=?";
		return this.findUnique(hql, catalogId);		
	}
	
	/**
	 * 获得用于Copy Catalog时ProductCategory list.
	 * @param catalogId
	 * @return
	 */
	public List<ProductCategory> getPdtCategoryListForCopyCatalog(String catalogId) {
		String hql = "from ProductCategory where catalogId=? and status='ACTIVE' and categoryLevel=1 order by parentCatId DESC, previousCatId DESC";
		return this.find(hql, catalogId);
	}
	
	/*
	 * 获取category 数据；根据catalogId,parentCategoryId,categoryLevel;
	 */
	public List<ProductCategory> getPdtCategoryList(String catalogId,Integer parentCatId,Integer categoryLevel){
		String hql = "from ProductCategory where catalogId='"+catalogId+"' and categoryLevel="+categoryLevel;
		if(parentCatId!=null){
			hql += " and parentCatId="+parentCatId;
		}
		return this.find(hql);
	}

    //add by zhanghuibin
    public List<DropDownDTO> getCategoryList(Integer categoryLevel){
		String hql = "select categoryId,name from ProductCategory where categoryLevel="+categoryLevel;
         List productCategories = this.find(hql);
        List<DropDownDTO> dropDownDTOList = new ArrayList<DropDownDTO>();
        for (Object ser : productCategories) {
            DropDownDTO downDTO = new DropDownDTO();
            Object[] obj = (Object[]) ser;
            downDTO.setId(obj[0].toString());
            downDTO.setName((String) obj[1]);
            dropDownDTOList.add(downDTO);
        }
		return dropDownDTOList;
	}
}
