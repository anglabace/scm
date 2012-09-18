package com.genscript.gsscm.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.inventory.dao.StorageDao;
import com.genscript.gsscm.inventory.entity.Storage;
@Service
@Transactional
public class StorageService {
	/**
	 * spring注入orderItemDao
	 */
	@Autowired
	private StorageDao storageDao;

	/**
	 * 根据Name查询，返回一个Storages实体对象
	 * @param orderItemId
	 * @return
	 */
	public Storage getStorageByName(String name){
		Storage sg = this.storageDao.getStorageByName(name);
		return sg;
	}
	
	public List<Storage> searchStorageByType(String type){
		return this.storageDao.findBy("storageType", type);
	}
	
	public List<Storage> findStorage(String warehouseId){
		String hql = "from Storage where warehouse.warehouseId = "+warehouseId;
		return this.storageDao.find(hql);
	}
	
	public StorageDao getStorageDao() {
		return storageDao;
	}

	public void setStorageDao(StorageDao storageDao) {
		this.storageDao = storageDao;
	}


}
