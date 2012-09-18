package com.genscript.gsscm.serv.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.dto.ServiceRelationItemDTO;
import com.genscript.gsscm.serv.entity.ServiceItemBean;
import com.genscript.gsscm.serv.entity.ServiceRelation;

@Repository
public class ServiceRelationDao extends HibernateDao<ServiceRelation, Integer> {
	private static final String ASSOCIATED_ITEM_ID = "select s.rltServiceId from ServiceRelation s where s.relationType=? and s.serviceId =?";
	
	@SuppressWarnings("unchecked")
	public List<Integer> getAssociatedItemIdList(final Integer serviceId){
		List<Integer> idList = createQuery(ASSOCIATED_ITEM_ID, "Associated-Sub", serviceId).list();
		return idList;
	}
	@Autowired
	private ServiceItemBeanDao serviceItemBeanDao;
	public List<ServiceRelationItemDTO> getRelateServiceBean(Integer serviceId) {
		List<PropertyFilter> srFilterList = new ArrayList<PropertyFilter>();
		PropertyFilter srIdFilter = new PropertyFilter(
				"EQI_serviceId", serviceId);
		PropertyFilter srTypeFilter = new PropertyFilter(
				"NES_relationType", "Associated-Sub");
		srFilterList.add(srIdFilter);
		srFilterList.add(srTypeFilter);
		List<ServiceRelation> serviceRelations = find(srFilterList);
		List<ServiceRelationItemDTO> serviceRelationItemList = new ArrayList<ServiceRelationItemDTO>();
		if (serviceRelations != null) {
			ServiceRelationItemDTO serviceRelationItemDTO = null;
			for (ServiceRelation sr : serviceRelations) {
				Integer srId = sr.getRltServiceId();
				List<ServiceItemBean> serviceItemBeanList;
				serviceItemBeanList = serviceItemBeanDao.findBy("serviceId", srId);
				
				for(ServiceItemBean serviceItemBean : serviceItemBeanList){
					serviceRelationItemDTO = new ServiceRelationItemDTO();
					serviceRelationItemDTO.setCatalogNo(serviceItemBean.getCatalogNo());
					serviceRelationItemDTO.setCatalogId(serviceItemBean.getCatalogId());
					serviceRelationItemDTO.setItemName(serviceItemBean.getName());
					serviceRelationItemDTO.setQtyUom(serviceItemBean.getQtyUom());
					serviceRelationItemDTO.setRelateInfo(sr.getRelateInfo());
					serviceRelationItemDTO.setRelationType(sr.getRelationType());
					serviceRelationItemDTO.setSize(serviceItemBean.getSize());
					serviceRelationItemDTO.setType(serviceItemBean.getType());
					serviceRelationItemDTO.setUom(serviceItemBean.getUom());
					serviceRelationItemList.add(serviceRelationItemDTO);
				}
			}
		}
		return serviceRelationItemList;
	}
}
