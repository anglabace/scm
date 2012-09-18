package com.genscript.gsscm.serv.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.serv.entity.ServiceRelationBean;

@Repository
public class ServiceRelationBeanDao extends HibernateDao<ServiceRelationBean, Integer> {
private static final String RELATION_ITEMS = "select sr.relationId, concat(concat(sr.relationType,'<;>'),sr.catalogNo) from ServiceRelationBean sr where sr.serviceId=?";
	

	@SuppressWarnings("rawtypes")
	public List<DropDownDTO> getRelationItemByServiceId(Integer serviceId){
		List list = null;
		List<DropDownDTO> dropDownLists = new ArrayList<DropDownDTO>();
		Query query = null;
		query = createQuery(RELATION_ITEMS, serviceId);
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
	
	@SuppressWarnings("unchecked")
	public List<DropDownDTO> getRelationItemByServiceIdNew(Integer serviceId){
		String sql = "select sr.relationId, concat(concat(sr.relationType,'-'),sr.catalogNo) from ServiceRelationBean sr where sr.serviceId=?";
		List list = null;
		List<DropDownDTO> dropDownLists = new ArrayList<DropDownDTO>();
		Query query = null;
		query = createQuery(sql, serviceId);
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
