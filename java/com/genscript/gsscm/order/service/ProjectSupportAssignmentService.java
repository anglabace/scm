package com.genscript.gsscm.order.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.order.dao.ProjectSupportAssignmentDTODao;
import com.genscript.gsscm.order.dao.ProjectSupportAssignmentDao;
import com.genscript.gsscm.order.dto.ProjectSupportAssignmentDTO;
import com.genscript.gsscm.order.entity.ProjectSupportAssignment;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;

@Service
@Transactional
public class ProjectSupportAssignmentService {
	@Autowired
	private ProjectSupportAssignmentDao projectSupportAssignmentDao;
	@Autowired
	private ProjectSupportAssignmentDTODao projectSupportAssignmentDTODao;
	@Autowired
	private UserDao userDao;
	
	public Page<ProjectSupportAssignmentDTO> searchproSupAssignment (
			Page<ProjectSupportAssignmentDTO> page, 
			ProjectSupportAssignmentDTO projectSupportAssignmentDTO) {
		return this.projectSupportAssignmentDTODao.searchproSupAssignment(page, 
				projectSupportAssignmentDTO);
	}
	
	/**
	 * 查询单条记录
	 * @param psaDto
	 * @return
	 */
	public ProjectSupportAssignmentDTO searchproSupAssignment (ProjectSupportAssignmentDTO psaDto) {
		try {
			if (psaDto != null && psaDto.getId() != null) {
				Object[] obj = this.projectSupportAssignmentDTODao.searchproSupAssignment(psaDto.getId());
				if (obj != null) {
					psaDto.setId((Integer)obj[0]);
					psaDto.setServiceTypeName(obj[1]!= null?obj[1].toString():null);
					psaDto.setProjectSupport(obj[2] != null?obj[2].toString():null);
					psaDto.setComment(obj[3] != null? obj[3].toString():null);
				}
				if (psaDto != null && psaDto.getId() != null) {
					// 建新的session
					SessionUtil.insertRow(SessionConstant.ProjectSupportAssignment.value(), psaDto.getId().toString(),
							psaDto.getId());
				}
				return psaDto;
			}
		} catch (Exception ex) {
			return null;
		}
		return null;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @throws Exception
	 */
	public void delProjectSupportAssignment (String ids) throws Exception {
		if (ids != null && !("").equals(ids)) {
			String[] idsArr = ids.split(",");
			for (String id : idsArr) {
				if (id != null && StringUtil.isNumeric(id)) {
					projectSupportAssignmentDao.delete(Integer.parseInt(id));
				}
			}
		}
	}
	
	/**
	 * 查询projectSupport列表
	 * @param page
	 * @param loginName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page findProSupPage (Page page, ProjectSupportAssignmentDTO psa) {
		if (psa != null) {
			page = this.userDao.findProSupPage(page, psa.getProjectSupport());
		} else {
			page = this.userDao.findProSupPage(page, null);
		}
		return page;
	}
	
	/**
	 * 保存ProjectSupportAssignment
	 * @param psaDto
	 * @return
	 */
	public boolean saveProjectSupportAssignment (ProjectSupportAssignmentDTO psaDto) {
		boolean status = false;
		try {
			if (psaDto != null) {
				if (psaDto.getServiceTypeName() == null || !StringUtil.isNumeric(psaDto.getServiceTypeName())) {
					return status;
				}
				if (psaDto.getProjectSupport() == null || ("").equals(psaDto.getProjectSupport())) {
					return status;
				}
				User user = this.userDao.findByLoginName(psaDto.getProjectSupport());
				if (user == null) {
					return status;
				}
				List<ProjectSupportAssignment> psaList = this.projectSupportAssignmentDao.getAll();
				if (psaList == null || psaList.size() <= 0) {
					return status;
				}
				boolean exsist = false;
				for (ProjectSupportAssignment ps: psaList) {
					if (ps.getProjectSupportId().intValue() == user.getUserId().intValue()) {
						exsist = true;
						break;
					}
				}
				if (!exsist) {
					return status;
				}
				Integer userId = SessionUtil.getUserId();
				Date nowDate = new Date();
				if (psaDto.getId() != null && psaDto.getId() > 0) {
					Object obj = SessionUtil.getRow(
							SessionConstant.ProjectSupportAssignment.value(), psaDto.getId().toString());
					if (obj != null && psaDto.getId().intValue() == ((Integer)obj).intValue()) {
						ProjectSupportAssignment dbPsa = 
							this.projectSupportAssignmentDao.findById(psaDto.getId());
						if (dbPsa != null) {
							dbPsa.setServiceClsId(Integer.parseInt(psaDto.getServiceTypeName()));
							dbPsa.setProjectSupportId(user.getUserId());
							dbPsa.setComments(psaDto.getComment());
							dbPsa.setModifyDate(nowDate);
							dbPsa.setModifiedBy(userId);
							this.projectSupportAssignmentDao.save(dbPsa);
							SessionUtil.deleteRow(
									SessionConstant.ProjectSupportAssignment.value(), psaDto.getId().toString());
							status = true;
						}
					}
				} else {
					ProjectSupportAssignment psa = new ProjectSupportAssignment();
					psa.setServiceClsId(Integer.parseInt(psaDto.getServiceTypeName()));
					psa.setProjectSupportId(user.getUserId());
					psa.setComments(psaDto.getComment());
					psa.setCreatedBy(userId);
					psa.setCreationDate(nowDate);
					psa.setModifiedBy(userId);
					psa.setModifyDate(nowDate);
					this.projectSupportAssignmentDao.save(psa);
					status = true;
				}
			}
		} catch (Exception ex) {
		}
		return status;
	}
	
}
