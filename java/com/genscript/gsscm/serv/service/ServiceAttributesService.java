package com.genscript.gsscm.serv.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.serv.dao.ServiceAttributesDao;
import com.genscript.gsscm.serv.entity.ServiceAttributes;

@Service
@Transactional
public class ServiceAttributesService {
	@Autowired
	private ServiceAttributesDao serviceAttributesDao;
	
	public Page<ServiceAttributes> searchAttributeList(Page<ServiceAttributes> page, List<PropertyFilter> filters){
		return this.serviceAttributesDao.findPage(page, filters);
	}
	
	public ServiceAttributes getAttributeDetailById(Integer id){
		return this.serviceAttributesDao.getById(id);
	}
	
	public List<ServiceAttributes> getAttributeListByclsId(Integer clsId){
		return this.serviceAttributesDao.findBy("clsId", clsId);
	}
	
	public void saveAttribute(ServiceAttributes entity){
		this.serviceAttributesDao.save(entity);
	}
	
	public void delAttribute(List<String> ids){
		List<Integer> idList = new ArrayList<Integer>();
		for(String id :ids){
			idList.add(Integer.valueOf(id));
		}
		this.serviceAttributesDao.delAttributes(idList);
	}
}
