package com.genscript.gsscm.inventory.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.inventory.entity.Storage;

@Repository
public class StorageDao extends HibernateDao<Storage, Integer> {
	/**
	 * 根据Name查询，返回一个Storages实体对象
	 * @param orderItemId
	 * @return
	 */
	public Storage getStorageByName(String name){
		String hql = "from Storage where name = ?";
		Storage pbSearch = null;
		List<Storage> retList = this.find(hql, name);
		if (retList!=null && retList.size()>0) {
			pbSearch = retList.get(0);
		}
		return pbSearch;

	}
	/*
	 * 减去实际库存接口
	 */
	public void uptStorage (String type,String catalogNo,Integer qut){
		String hql = "from Storage where type ="+type +" and catalogNo=" +catalogNo;
		List<Storage> storageList = this.find(hql);
		if(storageList!=null){
			Storage  storage = this.get(0);
		}
	}
}
