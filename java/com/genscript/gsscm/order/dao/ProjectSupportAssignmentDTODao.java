package com.genscript.gsscm.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.order.dto.ProjectSupportAssignmentDTO;

@Repository
public class ProjectSupportAssignmentDTODao extends HibernateDao<ProjectSupportAssignmentDTO, Integer> {

	private static final String SELECT_BY_FILTERS = "select psa.id as id, sc.name as serviceTypeName," +
			" u.loginName as projectSupport, psa.comments as comment " +
			" from ProjectSupportAssignment psa, ServiceClassification sc, User u " +
			" where psa.serviceClsId = sc.clsId and psa.projectSupportId " +
			" = u.userId "; 
	
	/**
	 * 带条件查询
	 * @param page
	 * @param projectSupportAssignmentDTO
	 * @return
	 */
	public Page<ProjectSupportAssignmentDTO> searchproSupAssignment (
			Page<ProjectSupportAssignmentDTO> page, 
			ProjectSupportAssignmentDTO projectSupportAssignmentDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sqlsbf = new StringBuffer();
		sqlsbf.append(SELECT_BY_FILTERS);
		if (projectSupportAssignmentDTO != null) {
			if (projectSupportAssignmentDTO.getProjectSupport() != null 
					&& !("").equals(projectSupportAssignmentDTO.getProjectSupport().trim())) {
				map.put("loginName", "%" + projectSupportAssignmentDTO.getProjectSupport().trim() + "%");
				sqlsbf.append(" and u.loginName like :loginName ");
			}
			if (projectSupportAssignmentDTO.getServiceTypeName() != null 
					&& StringUtil.isNumeric(projectSupportAssignmentDTO.getServiceTypeName().trim())) {
				map.put("clsId", Integer.parseInt(projectSupportAssignmentDTO.getServiceTypeName().trim()));
				sqlsbf.append(" and sc.clsId =:clsId ");
			}
		}
		if (page.isOrderBySetted()) {
			sqlsbf.append("order by " + page.getOrderBy() + " " + page.getOrder());
		}
		page = this.findPage(page, sqlsbf.toString(), map);
		return page;
	}
	
	/**
	 * 查询单条记录
	 * @param id
	 * @return
	 */
	public Object[] searchproSupAssignment (Integer id) {
		List<Object> objlist = this.find(SELECT_BY_FILTERS + " and psa.id = ?", id);
		if (objlist != null && objlist.size() > 0) {
			return (Object[]) objlist.get(0);
		}
		return null;
	}
}
