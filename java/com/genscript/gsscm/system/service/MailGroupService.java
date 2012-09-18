package com.genscript.gsscm.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.system.dao.DepartmentSystemDao;
import com.genscript.gsscm.system.dao.MailAddressDao;
import com.genscript.gsscm.system.dao.MailGroupDao;
import com.genscript.gsscm.system.dto.MailAddressDTO;
import com.genscript.gsscm.system.dto.MailGroupDTO;
import com.genscript.gsscm.system.entity.DepartmentSystem;
import com.genscript.gsscm.system.entity.MailAddress;
import com.genscript.gsscm.system.entity.MailGroup;

@Service
@Transactional
public class MailGroupService {
	@Autowired
	private MailAddressDao mailAddressDao;
	@Autowired
	private MailGroupDao mailGroupDao;
	@Autowired
	private DepartmentSystemDao departmentDao;
	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly = true)
	public Page<MailGroup> searchMailGroup(Page<MailGroup> page,List<PropertyFilter> filters){
		return this.mailGroupDao.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public MailGroupDTO getMailGroupById(Integer id){
		if(id!=null){
			MailGroup mailGroup = this.mailGroupDao.getById(id);
			MailGroupDTO dto = new MailGroupDTO();
			dto.setMailGroup(mailGroup);
			User createUser = this.userDao.getById(mailGroup.getCreatedBy());
			if (createUser != null) {
				dto.setCreatedByText(createUser.getLoginName());
			}
			User modifyUser = this.userDao.get(mailGroup.getModifiedBy());
			if (modifyUser != null) {
				dto.setModifiedByText(modifyUser.getLoginName());
			}
			return dto;
		}else{
			return new MailGroupDTO();
		}
	}
	
	public MailGroup saveMailGroup(MailGroupDTO mailGroupDTO,Integer userId,List<Integer> addMailAddressList,List<Integer> delMailAddressList){
		if(mailGroupDTO!=null){
			Date now = new Date();
			if(mailGroupDTO.getMailGroup()!=null){
				MailGroup mailGroup = mailGroupDTO.getMailGroup();
				mailGroup.setModifiedBy(userId);
				mailGroup.setModifyDate(now);
				if(mailGroup.getCreatedBy()==null){
					mailGroup.setCreatedBy(userId);
					mailGroup.setCreationDate(now);
				}
				this.mailGroupDao.save(mailGroup);
				if(addMailAddressList!=null&&!addMailAddressList.isEmpty()){
					for(Integer mailAddress : addMailAddressList){
						if(mailAddress!=null){
							MailAddress address = new MailAddress();
							address.setUser(new User());
							address.getUser().setUserId(mailAddress);
							address.setGroupId(mailGroup.getGroupId());
							address.setModifiedBy(userId);
							address.setModifyDate(now);
							if(address.getCreatedBy()==null){
								address.setCreatedBy(userId);
								address.setCreationDate(now);
							}
							this.mailAddressDao.save(address);
						}
					}
				}
				if(delMailAddressList!=null&&!delMailAddressList.isEmpty()){
					this.mailAddressDao.delMailAddress(delMailAddressList);
				}
			}
		}
		return null;
	}
	
	public List<MailAddress> searchMailAddress (Integer groupId){
		return this.mailAddressDao.findBy("groupId", groupId);
	}
	
	public MailAddressDTO getMailAddressById(Integer id){
		MailAddressDTO dto = new MailAddressDTO();
		MailAddress mailAddress = this.mailAddressDao.getById(id);
		if(mailAddress!=null){
			dto.setMailAddress(mailAddress);
			
		}
		User createUser = this.userDao.getById(mailAddress.getCreatedBy());
		if (createUser != null) {
			dto.setCreatedByText(createUser.getLoginName());
		}
		User modifyUser = this.userDao.get(mailAddress.getModifiedBy());
		if (modifyUser != null) {
			dto.setModifiedByText(modifyUser.getLoginName());
		}
		if(mailAddress.getUser()!=null){
			DepartmentSystem departMent = this.departmentDao.getById(mailAddress.getUser().getDeptId());
			if(departMent!=null){
				dto.setDeptName(departMent.getName());
			}
		}
		return  dto;
	}
	
	public Long getMailAddressCountByUserIdGroupId(Integer userId,Integer groupId){
		return this.mailAddressDao.getCountMilAddressByUserIdGroupId(userId, groupId);
	}
	
	public List<DepartmentSystem> getDepartmentList(){
		return this.departmentDao.getAll();
	}
	
	public boolean delMailGroup(List<String> delMailGroupIdList){
		List<Integer> del = new ArrayList<Integer>();
		if(delMailGroupIdList!=null&&!delMailGroupIdList.isEmpty()){
			for(int i=0;i<delMailGroupIdList.size();i++){
				Long count = this.mailAddressDao.getCountMilAddressByUserIdGroupId(null, Integer.valueOf(delMailGroupIdList.get(i)));
				del.add(Integer.valueOf(delMailGroupIdList.get(i)));
				if(count>0){
					return false;
				}
			}
		}
		if(del!=null&&!del.isEmpty()){
			this.mailGroupDao.delMailGroup(del);
		}
		
		return true;
	}
}
