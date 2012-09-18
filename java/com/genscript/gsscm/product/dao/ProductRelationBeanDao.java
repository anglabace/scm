package com.genscript.gsscm.product.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.product.entity.ProductRelationBean;

@Repository
public class ProductRelationBeanDao extends HibernateDao<ProductRelationBean, Integer> {
	
	private static final String RELATION_ITEMS = "select pr.relationId, concat(concat(pr.relationType,'<;>'),pr.catalogNo) from ProductRelationBean pr where pr.productId=?";
	
	@SuppressWarnings({"rawtypes" })
	public List<DropDownDTO> getRelationItemByProductId(Integer productId){
		List list = null;
		List<DropDownDTO> dropDownLists = new ArrayList<DropDownDTO>();
		Query query = null;
		query = createQuery(RELATION_ITEMS, productId);
		list = query.list();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				DropDownDTO downListDTO = new DropDownDTO();
				downListDTO.setId(obj[0].toString());
				downListDTO.setName((String) obj[1]);
				dropDownLists.add(downListDTO);
			}
			return dropDownLists;
		}
		return null;
	}
	

	
	@SuppressWarnings({"rawtypes" })
	public List<DropDownDTO> getRelationItemByProductIdNew(Integer productId){
		String sql = "select pr.relationId, concat(concat(pr.relationType,'-'),pr.catalogNo) from ProductRelationBean pr where pr.productId=?";
		List list = null;
		List<DropDownDTO> dropDownLists = new ArrayList<DropDownDTO>();
		Query query = null;
		query = createQuery(sql, productId);
		list = query.list();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				DropDownDTO downListDTO = new DropDownDTO();
				downListDTO.setId(obj[0].toString());
				downListDTO.setName((String) obj[1]);
				dropDownLists.add(downListDTO);
			}
			return dropDownLists;
		}
		return null;
	}
}
