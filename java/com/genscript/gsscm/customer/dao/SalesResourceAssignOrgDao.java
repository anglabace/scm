package com.genscript.gsscm.customer.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.dto.GetInfoByRegionDTO;
import com.genscript.gsscm.customer.entity.SalesResourceAssignOrg;

@Repository
public class SalesResourceAssignOrgDao extends HibernateDao<SalesResourceAssignOrg, Integer>{
	
	/**
	 * 根据salesId查找SalesResourceAssignOrg
	 * @param salesId
	 */
	public List<SalesResourceAssignOrg> findBySalesId(Integer salesId) {
		String hql = "from SalesResourceAssignOrg where salesId=?";
		return this.find(hql, salesId);
		
	}
	
	/**
	 * 根据salesId删除SalesResourceAssignOrg
	 * @param salesId
	 */
	public void deleteBySalesId(Integer salesId) {
		String hql = "delete from SalesResourceAssignOrg where salesId=?";
		this.batchExecute(hql, salesId);
	}
	
	/**
	 * 通过orgId查询SalesResourceAssignOrg
	 * @author zhang yong
	 * @param orgId
	 * @return
	 */
	public List<SalesResourceAssignOrg> findByOrgId (Integer orgId) {
		if(orgId==null) {
			return null;
		}
		String hql = "from SalesResourceAssignOrg where organization.orgId = ?";
		return this.find(hql,orgId);
	}
	
	public List<GetInfoByRegionDTO> findTerrByOrgId (Integer orgId, Integer salesGroupId) {
		List<Object> objList = null;
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT distinct(srat.salesTerritory.territoryId), srat.salesTerritory.territoryCode,srat.salesTerritory.territoryClassification "
				+ " FROM SalesResourceAssignOrg srao, SalesRep sr, SalesResourceAssignTerritory srat "
				+ " WHERE srao.organization.orgId = ? AND srao.salesId = sr.salesId ");
		if (salesGroupId != null) {
			hql.append(" AND sr.salesGroupId = ? AND srao.salesId = srat.salesId ");
			objList = this.find(hql.toString(), orgId, salesGroupId);
		} else {
			hql.append(" AND sr.salesGroupId is not null AND srao.salesId = srat.salesId");
			objList = this.find(hql.toString(), orgId);
		}
		List<GetInfoByRegionDTO> getInfoByRegionDTOList = null;
		if (objList != null && objList.size() > 0) {
			getInfoByRegionDTOList = new ArrayList<GetInfoByRegionDTO>();
			for (Object obj : objList) {
				GetInfoByRegionDTO getInfoByRegionDTO = new GetInfoByRegionDTO();
				Object[] objs = (Object[])obj;
				getInfoByRegionDTO.setId(objs[0].toString());
				getInfoByRegionDTO.setName(objs[1].toString());
				getInfoByRegionDTO.setTerritoryClassification(objs[2]!=null?objs[2].toString():null);
				getInfoByRegionDTOList.add(getInfoByRegionDTO);
			}
		}
		return getInfoByRegionDTOList;
	}

}
