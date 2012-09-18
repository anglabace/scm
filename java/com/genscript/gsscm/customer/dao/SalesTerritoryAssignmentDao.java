package com.genscript.gsscm.customer.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.customer.entity.SalesTerritoryAssignment;

@Repository
public class SalesTerritoryAssignmentDao extends HibernateDao<SalesTerritoryAssignment, Integer> {
	
	public List<SalesTerritoryAssignment> findByTerritoryId(Integer territoryId) {
		String hql = "from SalesTerritoryAssignment where territoryId=?";
		return this.find(hql, territoryId);
	}
	
	/**
	 * 根据territoryId删除SalesTerritoryAssignment
	 * @param territoryId
	 */
	public void deleteByTerritoryId(Integer territoryId) {
		String hql ="delete  from SalesTerritoryAssignment  where territoryId=?";
		this.batchExecute(hql, territoryId);
		
	}
	
	/**
	 * 根据一些条件搜索SalesTerritoryAssignment
	 */
	public List<SalesTerritoryAssignment> find_country_State_zip(String countryCode,String stateCode,String zipCode) {
		StringBuffer hql = new StringBuffer("from SalesTerritoryAssignment where 1=1 ");
		if(countryCode==null||StringUtils.isEmpty(countryCode)) {
			return null;
		}
		if(countryCode!=null&&StringUtils.isNotEmpty(countryCode)) {
			hql.append(" and countryCode='").append(countryCode).append("'");
		}
		if(stateCode!=null&&StringUtils.isNotEmpty(stateCode)) {
			hql.append(" and (stateCode is null or stateCode='").append(stateCode).append("')");
		}
		if(zipCode!=null&&StringUtils.isNotEmpty(zipCode)) {
			if(StringUtil.isNumeric(zipCode)) {
				hql.append(" and (fromZip is null or fromZip=''  or fromZip <=").append(Integer.parseInt(zipCode)).append(")").append(" and (toZip is null or toZip='' or toZip >=").append(Integer.parseInt(zipCode)).append(")");
			} else {
				hql.append(" and (fromZip is null or fromZip=''  or fromZip ='").append(zipCode).append("')").append(" and (toZip is null or toZip='' or toZip= '").append(zipCode).append("')");
			}
			
		}
		return this.find(hql.toString());
	}

}
