package com.genscript.gsscm.manufacture.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dao.CurrencyDao;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.QaGroupAssignedType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.genscript.gsscm.common.constant.WoOperStatus;
import com.genscript.gsscm.common.constant.WorkCenterAssignedType;
import com.genscript.gsscm.common.constant.WorkOrderStatus;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.inventory.dao.WarehouseDao;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.manufacture.dao.GroupResourceDao;
import com.genscript.gsscm.manufacture.dao.ManufacturingClerksBeanDao;
import com.genscript.gsscm.manufacture.dao.ManufacturingClerksDao;
import com.genscript.gsscm.manufacture.dao.OperationDao;
import com.genscript.gsscm.manufacture.dao.OperationResourceDao;
import com.genscript.gsscm.manufacture.dao.ResourceDao;
import com.genscript.gsscm.manufacture.dao.RouteDao;
import com.genscript.gsscm.manufacture.dao.RouteOperationDao;
import com.genscript.gsscm.manufacture.dao.WorkCenterAssignedDao;
import com.genscript.gsscm.manufacture.dao.WorkCenterDao;
import com.genscript.gsscm.manufacture.dao.WorkGroupDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderOperationDao;
import com.genscript.gsscm.manufacture.dto.WorkOrderDTO;
import com.genscript.gsscm.manufacture.entity.GroupResource;
import com.genscript.gsscm.manufacture.entity.ManufacturingClerks;
import com.genscript.gsscm.manufacture.entity.ManufacturingClerksBean;
import com.genscript.gsscm.manufacture.entity.Operation;
import com.genscript.gsscm.manufacture.entity.OperationResource;
import com.genscript.gsscm.manufacture.entity.Resource;
import com.genscript.gsscm.manufacture.entity.Route;
import com.genscript.gsscm.manufacture.entity.RouteOperation;
import com.genscript.gsscm.manufacture.entity.WorkCenter;
import com.genscript.gsscm.manufacture.entity.WorkCenterAssigned;
import com.genscript.gsscm.manufacture.entity.WorkGroup;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.manufacture.entity.WorkOrderOperation;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.dao.UserRoleDao;
import com.genscript.gsscm.privilege.dto.UserSrchDTO;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.ProductClassDao;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.serv.dao.ServiceClassDao;
import com.genscript.gsscm.serv.entity.ServiceClass;
import com.genscript.gsscm.system.dao.DepartmentSystemDao;
import com.genscript.gsscm.system.entity.DepartmentSystem;
import com.opensymphony.xwork2.ActionContext;

@Service
@Transactional
public class SetupService {
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private WorkGroupDao workGroupDao;
	@Autowired
	private WorkCenterDao workCenterDao;
	@Autowired
	private OperationDao operationDao;
	@Autowired
	private GroupResourceDao groupResourceDao;
	@Autowired
	private OperationResourceDao operationResourceDao;
	@Autowired
	private RouteOperationDao routeOperationDao;
	@Autowired
	private RouteDao routeDao;
	@Autowired
	private ServiceClassDao serviceClassDao;
	@Autowired
	private ProductClassDao productClassDao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CurrencyDao currencyDao;
	@Autowired
	private WarehouseDao warehouseDao;
	@Autowired
	private ManufacturingClerksBeanDao manufacturingClerksBeanDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private WorkCenterAssignedDao workCenterAssignedDao;
	@Autowired
	private DepartmentSystemDao departmentSystemDao;
	@Autowired
	private ManufacturingClerksDao manufacturingClerksDao;
	@Autowired
	private WorkOrderOperationDao workOrderOPerationDao;
	@Autowired
	private WorkOrderDao workOrderDao;

	/**
	 * 新增或修改Resource, 取当前时间作为creationDate, ModifyDate.
	 * 
	 * @param resourceDTO
	 * @param loginUserId
	 *            当前登录用户
	 */
	public void saveResource(Resource resource, Integer loginUserId) {
		if("ACTIVE".equals(resource.getStatus())) {
			List<Resource> dbResourceList = this.resourceDao.findByResorceNo(resource.getResourceNo());
			if(dbResourceList!=null) {
				for(Resource dbResource :dbResourceList) {
					this.resourceDao.getSession().evict(dbResource);
					Integer userDept = dbResource.getUserDept();
					if(userDept==null) {
						userDept = 0;
					}
					if(resource.getResourceId()==null) {
						if(userDept.intValue()==0) {
							throw new BussinessException(
									BussinessException.ERR_RESOURCENO_UNIQUE);
						} else if(resource.getUserDept().intValue()==0) {
							throw new BussinessException(
									BussinessException.ERR_RESOURCENO_UNIQUE);
						} else if(userDept.intValue()==resource.getUserDept().intValue()) {
							throw new BussinessException(
									BussinessException.ERR_RESOURCENO_UNIQUE);
						}
					} else {
						if(resource.getResourceId().intValue()!=dbResource.getResourceId().intValue()) {
							if(userDept.intValue()==0) {
								throw new BussinessException(
										BussinessException.ERR_RESOURCENO_UNIQUE);
							} else if(resource.getUserDept().intValue()==0) {
								throw new BussinessException(
										BussinessException.ERR_RESOURCENO_UNIQUE);
							} else if(userDept.intValue()==resource.getUserDept().intValue()) {
								throw new BussinessException(
										BussinessException.ERR_RESOURCENO_UNIQUE);
							} 
						}
					}
				}
			}
		}
		if(resource.getCost()==null) {
			resource.setCost(0.0);
		}
		resource.setCreatedBy(loginUserId);
		resource.setModifiedBy(loginUserId);
		resource.setCreationDate(new Date());
		resource.setModifyDate(new Date());
		this.resourceDao.save(resource);
	}

	/**
	 * 根据id主健查找Resource, 并根据modifyId找出modifyUser(从User表中的loginName得到).
	 * 
	 * @param id
	 * @return
	 */
	public Resource getResource(Integer id) {
		Resource resource = this.resourceDao.getById(id);
		User temp = this.userDao.getById(resource.getModifiedBy());
		if (temp != null) {
			resource.setModifyUser(temp.getLoginName());
		}
		return resource;
	}

	/**
	 * 根据条件查找并分页显示, 如果查找条件为null, 则返回所有结果并分页显示.
	 * 
	 * @param dtoPage
	 * @param filters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<Resource> searchResourcePage(Page<Resource> dtoPage,
			List<PropertyFilter> filters) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		Set<Integer> centerIdSet = currentUserGetWorkCenter(Constants.ROLE_MANUFACTURINGSETUPS_MANAGER);
		Page<Resource> page = this.dozer.map(dtoPage, Page.class);
		if(centerIdSet==null) {
			return page;
		}
		if(filters==null) {
			filters = new ArrayList<PropertyFilter>();
		}
		if (!Constants.USERNAME_ADMIN.equals(userName)) {
			// 判断当前用户是否含有生产部经理角色
			boolean productionManager = userRoleDao
					.checkIsContainsManagerRole(Constants.ROLE_MANUFACTURINGSETUPS_MANAGER);
			if (!productionManager) {
				PropertyFilter createdByFilter = new PropertyFilter("EQI_createdBy",
						SessionUtil.getUserId());
				filters.add(createdByFilter);
			} 
		}
		for(PropertyFilter propertyFilter:filters) {
			if(propertyFilter.getPropertyName().equals("userDept")&&"0".equals(String.valueOf(propertyFilter.getPropertyValue()))) {
				if(centerIdSet.size()==0) {
					filters.remove(propertyFilter);
				} else {
					centerIdSet.add(0);
					filters.remove(propertyFilter);
				}
				break;
			} else if(propertyFilter.getPropertyName().equals("userDept")) {
				centerIdSet.clear();
				centerIdSet.add(Integer.parseInt(String.valueOf(propertyFilter.getPropertyValue())));
				filters.remove(propertyFilter);
				break;
			}
			if(propertyFilter.getPropertyName().equals("userDeptName")) {
				List<Integer> wokCenterIdList = this.workCenterDao.findByName(String.valueOf(propertyFilter.getPropertyValue()));
				if(wokCenterIdList==null||wokCenterIdList.size()==0) {
					return page;
				}
				if(centerIdSet.size()==0) {
					centerIdSet.addAll(wokCenterIdList);
				} else {
					centerIdSet.retainAll(wokCenterIdList);
					if(centerIdSet==null||centerIdSet.size()==0) {
						return page;
					}
				}
				filters.remove(propertyFilter);
				break;
			}
		}
		page = this.resourceDao.findPage(page, filters,centerIdSet);
		List<Resource> dtoList = new ArrayList<Resource>();
		for (Resource resource : page.getResult()) {
			User temp = this.userDao.getById(resource.getModifiedBy());
			if (temp != null) {
				resource.setModifyUser(temp.getLoginName());
			}
			if(resource.getCurrency()!=null) {
				resource.setCurrencySymbol(currencyDao.getCurrencySymbol(resource
						.getCurrency()));
			}
			if(resource.getUserDept()!=null&&resource.getUserDept().intValue()!=0) {
				WorkCenter workCenter = this.workCenterDao.getById(resource.getUserDept());
				resource.setUserDeptName(workCenter!=null?workCenter.getName():"");
			} else if(resource.getUserDept()!=null&&resource.getUserDept().intValue()==0) {
				resource.setUserDeptName("All");
			}
			dtoList.add(resource);
		}
		page.setResult(null);
		dtoPage = this.dozer.map(page, Page.class);
		dtoPage.setResult(dtoList);
		return dtoPage;
	}

	/**
	 * 批量删除Resource.
	 * 
	 * @param resourceIdList
	 */
	public void delResource(List<Integer> resourceIdList) {
		if (resourceIdList != null && !resourceIdList.isEmpty()) {
			this.resourceDao.batchUpdateStatus(resourceIdList,
					StatusType.INACTIVE.value);
		}
	}

	/**
	 * 保存Group同时保存Group Resource.
	 * 
	 * @param delGroupResIdList
	 * @param groupResList
	 * @param loginUserId
	 */
	private void attachResourceWithGroup(List<Integer> delGroupResIdList,
			List<GroupResource> groupResList, Integer loginUserId) {
		if (delGroupResIdList != null && !delGroupResIdList.isEmpty()) {
			this.groupResourceDao.delGroupResourceList(delGroupResIdList);
		}
		if (groupResList == null || groupResList.isEmpty()) {
			return;
		}
		for (GroupResource groupResource : groupResList) {
			if(groupResource.getId()==null) {
				groupResource.setCreatedBy(loginUserId);
				groupResource.setCreationDate(new Date());
			}
			groupResource.setModifiedBy(loginUserId);
			groupResource.setModifyDate(new Date());
			this.groupResourceDao.save(groupResource);
		}
	}

	/**
	 * 新增或修改WorkGroup, 先把WorkGroup类型转为entity, 取当前时间作为creationDate, ModifyDate.
	 * 
	 * @param workGroup
	 * @param loginUserId
	 *            当前登录用户
	 */
	public void saveWorkGroup(WorkGroup workGroup, Integer loginUserId) {
		workGroup.setCreatedBy(loginUserId);
		workGroup.setModifiedBy(loginUserId);
		workGroup.setCreationDate(new Date());
		workGroup.setModifyDate(new Date());
		this.workGroupDao.save(workGroup);
		this.attachResourceWithGroup(workGroup.getDelGroupResIdList(),
				workGroup.getGroupResList(), loginUserId);
	}

	/**
	 * 根据id主健查找WorkGroup, 并根据supervisor找出modifyUser(从User表中的loginName得到).
	 * 
	 * @param id
	 * @return
	 */
	public WorkGroup getWorkGroup(Integer id) {
		WorkGroup workGroup = this.workGroupDao.getById(id);
		getOtherInfoForGroup(workGroup);
		return workGroup;
	}

	/**
	 * 根据条件查找并分页显示, 如果查找条件为null, 则返回所有结果并分页显示.
	 * 
	 * @param dtoPage
	 * @param filters
	 * @return
	 */
	public Page<WorkGroup> searchWorkGroupPage(Page<WorkGroup> page,
			List<PropertyFilter> filters) throws Exception {
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			filters = new ArrayList<PropertyFilter>();
			PropertyFilter statusFilter = new PropertyFilter("EQS_status","ACTIVE");
			filters.add(statusFilter);
		} else {
			boolean flg = true;
			for(PropertyFilter propertyFilter:filters) {
				if("EQS_status".equals(propertyFilter.getPropertyName())) {
					flg = false;
					break;
				}
			}
			if(flg) {
				PropertyFilter statusFilter = new PropertyFilter("EQS_status","ACTIVE");
				filters.add(statusFilter);
			}
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		List<Integer> workGroupIdList = new ArrayList<Integer>();
		if (!Constants.USERNAME_ADMIN.equals(userName)) {
			// 判断当前用户是否含有生产部经理角色
			boolean productionManager = userRoleDao
					.checkIsContainsManagerRole(Constants.ROLE_MANUFACTURINGSETUPS_MANAGER);
			if (!productionManager) {
				List<ManufacturingClerks> manufacturingClerksList = this.manufacturingClerksDao.findBy("clerkId", SessionUtil.getUserId());
				if(manufacturingClerksList==null||manufacturingClerksList.size()==0) {
					PropertyFilter createdByFilter = new PropertyFilter("EQI_createdBy",
							SessionUtil.getUserId());
					filters.add(createdByFilter);
					return this.workGroupDao.findPage(page,filters);
				}
				for(ManufacturingClerks manufacturingClerks:manufacturingClerksList) {
					if(manufacturingClerks.getWorkGroupId()!=null) {
						workGroupIdList.add(manufacturingClerks.getWorkGroupId());
					}
				}
				if(workGroupIdList.size()==0) {
					PropertyFilter createdByFilter = new PropertyFilter("EQI_createdBy",
							SessionUtil.getUserId());
					filters.add(createdByFilter);
					return this.workGroupDao.findPage(page,filters);
				}
			} else {
				List<WorkCenter> centerList = this.workCenterDao.findBy("supervisor", session.get(StrutsActionContant.USER_ID));
				List<WorkGroup> groupList = new ArrayList<WorkGroup>();
				if(centerList==null||centerList.size()==0) {
					PropertyFilter createdByFilter = new PropertyFilter("EQI_createdBy",
							SessionUtil.getUserId());
					filters.add(createdByFilter);
					return this.workGroupDao.findPage(page,filters);
				}
				for(WorkCenter workCenter:centerList) {
					List<WorkGroup> list = this.workGroupDao.getGroupListByCenter(workCenter.getId());
					groupList.addAll(list);
				}
				if(groupList==null||groupList.size()==0) {
					PropertyFilter createdByFilter = new PropertyFilter("EQI_createdBy",
							SessionUtil.getUserId());
					filters.add(createdByFilter);
					return this.workGroupDao.findPage(page,filters);
				}
				for(WorkGroup workGroup:groupList) {
					workGroupIdList.add(workGroup.getId());
				}
			}
			
		}
		page = this.workGroupDao.findPage(page, filters,workGroupIdList);
		if (page != null && page.getResult() != null
				&& page.getResult().size() > 0) {
			for (WorkGroup workGroup : page.getResult()) {
				getOtherInfoForGroup(workGroup);
			}
		}
		return page;
	}

	/**
	 * 查找一个WorkCenter可选的Work Group list.
	 * 
	 * @param page
	 * @param filters
	 * @param workCenterId
	 * @return
	 */
	public Page<WorkGroup> selectForCenter(Page<WorkGroup> page,
			List<PropertyFilter> filters, Integer workCenterId) {
		page = this.workGroupDao.findPage(page, filters, workCenterId);
		for (WorkGroup workGroup : page.getResult()) {
			getOtherInfoForGroup(workGroup);
		}
		return page;
	}

	/**
	 * 批量删除WorkGroup.
	 * 
	 * @param resourceIdList
	 */
	public void delWorkGroup(List<Integer> idList) {
		if (idList != null && !idList.isEmpty()) {
			this.workGroupDao.batchUpdateStatus(idList,
					StatusType.INACTIVE.value);
		}
	}

	/**
	 * 根据(work)groupId获得所有的Resource, 从对象GroupResource中查找.
	 * 
	 * @param groupId
	 * @return
	 */
	public List<GroupResource> getGroupResourceList(Integer groupId) {
		List<GroupResource> list =  this.groupResourceDao.getGroupResourceList(groupId);
		for(GroupResource groupResource:list) {
			groupResource.setQuantity(groupResource.getQuantity().setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		return list;
	}

	/**
	 * 根据id主健查找WorkCenter, 并根据supervisor找出modifyUser(从User表中的loginName得到).
	 * 
	 * @param id
	 * @return
	 */
	public WorkCenter getWorkCenter(Integer id) {
		WorkCenter workCenter = this.workCenterDao.getById(id);
		getOtherInfoForCenter(workCenter);
		return workCenter;
	}

	/**
	 * 新增或修改WorkCenter, 先把WorkCenter类型转为entity, 取当前时间作为creationDate, ModifyDate.
	 * 
	 * @param workCenter
	 * @param sessWorkCenterId
	 *            2011-1-6 mod by lizhang add workCenterAssigned
	 */
	@SuppressWarnings("unchecked")
	public void saveWorkCenter(WorkCenter workCenter,
			List<Integer> attachGroupIdList, List<Integer> dettachGroupIdList,
			String sessWorkCenterId) {
		Integer loginUserId = SessionUtil.getUserId();
		if (workCenter != null && workCenter.getId() != null) {
			this.workCenterAssignedDao.deleteByCenterId(workCenter.getId());
		}
		if (workCenter.getId() == null) {
			workCenter.setCreatedBy(SessionUtil.getUserId());
		}
		workCenter.setModifiedBy(loginUserId);
		workCenter.setModifyDate(new Date());
		this.workCenterDao.save(workCenter);
		if (attachGroupIdList != null && !attachGroupIdList.isEmpty()) {
			this.workGroupDao.batchAttachCenter(attachGroupIdList, workCenter
					.getId());
		}
		if (dettachGroupIdList != null && !dettachGroupIdList.isEmpty()) {
			this.workGroupDao.batchDetachCenter(dettachGroupIdList);
		}
		List<WorkCenterAssigned> workCenterAssignedList = (List<WorkCenterAssigned>) SessionUtil
				.getRow(SessionConstant.WorkCenterAssigned.value(),
						sessWorkCenterId);
		if (workCenterAssignedList != null && workCenterAssignedList.size() > 0) {
			for (WorkCenterAssigned workCenterAssigned : workCenterAssignedList) {
				workCenterAssigned.setWorkCenter(workCenter);
				workCenterAssigned.setId(null);
				this.workCenterAssignedDao.save(workCenterAssigned);
			}
		}
		SessionUtil.deleteRow(SessionConstant.WorkCenterAssigned.value(),
				sessWorkCenterId);
	}

	/**
	 * 批量删除WorkCenter.
	 * 
	 * @param resourceIdList
	 */
	public void delWorkCenter(List<Integer> idList) {
		if (idList != null && !idList.isEmpty()) {
			this.workCenterDao.batchUpdateStatus(idList,
					StatusType.INACTIVE.value);
			for (Integer id : idList) {
				this.workCenterAssignedDao.deleteByCenterId(id);
			}
		}

	}

	/**
	 * 根据条件查找并分页显示, 如果查找条件为null, 则返回所有结果并分页显示.
	 * 
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<WorkCenter> searchWorkCenterPage(Page<WorkCenter> page,
			List<PropertyFilter> filters) throws Exception {
		if (filters == null || filters.isEmpty()) {
			filters = new ArrayList<PropertyFilter>();
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		Set<Integer> centerIdSet = new HashSet<Integer>();
		if (!Constants.USERNAME_ADMIN.equals(userName)) {
			// 判断当前用户是否含有生产部经理角色
			boolean productionManager = userRoleDao
					.checkIsContainsManagerRole(Constants.ROLE_MANUFACTURINGSETUPS_MANAGER);
			if (!productionManager) {
				List<ManufacturingClerks> manufacturingClerksList = this.manufacturingClerksDao.findBy("clerkId", SessionUtil.getUserId());
				if(manufacturingClerksList==null||manufacturingClerksList.size()==0) {
					PropertyFilter createdByFilter = new PropertyFilter("EQI_createdBy",
							SessionUtil.getUserId());
					filters.add(createdByFilter);
					return this.workCenterDao.findPage(page, filters);
				}
				for(ManufacturingClerks manufacturingClerks:manufacturingClerksList) {
					if(manufacturingClerks.getWorkCenterId()!=null) {
						centerIdSet.add(manufacturingClerks.getWorkCenterId());
					}
				}
				if(centerIdSet.size()==0) {
					PropertyFilter createdByFilter = new PropertyFilter("EQI_createdBy",
							SessionUtil.getUserId());
					filters.add(createdByFilter);
					return this.workCenterDao.findPage(page, filters);
				}

			} else {
				List<WorkCenter> centerList = this.workCenterDao.findBy("supervisor", session.get(StrutsActionContant.USER_ID));
				if(centerList==null||centerList.size()==0) {
					PropertyFilter createdByFilter = new PropertyFilter("EQI_createdBy",
							SessionUtil.getUserId());
					filters.add(createdByFilter);
					return this.workCenterDao.findPage(page, filters);
				}
				for(WorkCenter workCenter:centerList) {
					centerIdSet.add(workCenter.getId());
				}
			}
		}
		page = this.workCenterDao.findPageForCenter(page, filters,centerIdSet);
		if (page != null && page.getResult() != null
				&& page.getResult().size() > 0) {
			for (WorkCenter workCenter : page.getResult()) {
				getOtherInfoForCenter(workCenter);
			}
		}
		return page;
	}

	/**
	 * 根据workCenterId获得WorkGroup list.
	 * 
	 * @param workCenterId
	 * @return
	 */
	public List<WorkGroup> getGroupListByCenter(Integer workCenterId,String roleName) throws Exception{
		Set<Integer> groupIdSet = currentUserGetWorkGroup(roleName,workCenterId);
		List<WorkGroup> result = new ArrayList<WorkGroup>();
		if(groupIdSet!=null&&groupIdSet.size()==0) {
			result = this.workGroupDao.getGroupListByCenter(workCenterId);
		} else if(groupIdSet!=null&&groupIdSet.size()>0){
			result = this.workGroupDao.findByIds(SessionUtil.convertSet2List(groupIdSet));
		}
		for (WorkGroup workGroup : result) {
			getOtherInfoForGroup(workGroup);
		}
		return result;
	}
	
	/**
	 * 当前session中的用户能看到的WorkGroup
	 * @return
	 */
	private Set<Integer> currentUserGetWorkGroup(String roleName,Integer workCenterId) {
		Set<Integer> groupIdSet = new HashSet<Integer>();
		if (!Constants.USERNAME_ADMIN.equals(SessionUtil.getUserName())) {//不是超级管理员
			
			// 判断当前用户是否含有roleName角色
			if (!userRoleDao.checkIsContainsManagerRole(roleName)) {
				// 查询用户所在组Id
				List<ManufacturingClerksBean> manufacturingClerksBeanList = manufacturingClerksBeanDao.findBy("clerkId", SessionUtil.getUserId());
				if(manufacturingClerksBeanList==null||manufacturingClerksBeanList.size()==0) {
					return null;
				}
				for(ManufacturingClerksBean manufacturingClerksBean:manufacturingClerksBeanList) {
					if (manufacturingClerksBean.getWorkGroupId() == null||workCenterId.intValue()!=manufacturingClerksBean.getWorkCenterId().intValue()) {
						continue;
					}
					groupIdSet.add(manufacturingClerksBean.getWorkGroupId());
				}
				if(groupIdSet.isEmpty()) {
					return null;
				}
			}
		} 
		return groupIdSet;
	}
	
	/**
	 * 填充workgroup其他非数据库属性
	 */
	private void getOtherInfoForGroup(WorkGroup workGroup) {
		if(workGroup.getSupervisor()!=null) {
			User superUser = this.userDao.getById(workGroup.getSupervisor());
			workGroup.setSuperName(superUser!=null?((superUser.getFirstName()!=null?superUser.getFirstName():"")+(" "+superUser.getLastName()!=null?superUser.getLastName():"")):"");
		}
		User temp = this.userDao.getById(workGroup.getModifiedBy());
		workGroup.setModifyUser(temp!=null?temp.getLoginName():"");
	}
	
	/**
	 * 根据workCenterId获得WorkGroup list.
	 * 
	 * @param workCenterId
	 * @return
	 */
	public List<WorkGroup> getGroupListByCenter(Integer workCenterId){
		List<WorkGroup> result = this.workGroupDao.getGroupListByCenter(workCenterId);
		for (WorkGroup workGroup : result) {
			getOtherInfoForGroup(workGroup);
		}
		return result;
	}


	@Transactional(readOnly = true)
	public List<OperationResource> getAllOperationResource(Integer operationId) {
		List<OperationResource> list = this.operationResourceDao.getAllList(operationId);
		for(OperationResource operationResource:list) {
			operationResource.setQuantity(operationResource.getQuantity().setScale(2,BigDecimal.ROUND_HALF_UP));
		}
		return list;
	}

	@Transactional(readOnly = true)
	public OperationResource getOperationResource(Integer id) {
		return this.operationResourceDao.get(id);
	}

	/**
	 * 根据id主健查找Operation, 并根据supervisor找出modifyUser(从User表中的loginName得到).
	 * 
	 * @param id
	 * @return
	 */
	public Operation getOperation(Integer id) {
		Operation operation = this.operationDao.get(id);
		if(operation.getSupervisor()!=null) {
			User superUser = this.userDao.getById(operation.getSupervisor());
			if (superUser != null) {
				operation.setSuperName(superUser.getFirstName()!=null?superUser.getFirstName():""+" "+superUser.getLastName()!=null?superUser.getLastName():"");
			}
		}
		User temp = this.userDao.getById(operation.getModifiedBy());
		if (temp != null) {
			operation.setModifyUser(temp.getLoginName());
		}
		return operation;
	}

	/**
	 * 新增或修改Operation, 先把Operation类型转为entity, 取当前时间作为creationDate, ModifyDate.
	 * 
	 * @param workGroup
	 * @param loginUserId
	 *            当前登录用户
	 */
	public void saveOperation(Operation operation, Integer loginUserId) {
		operation.setCreatedBy(loginUserId);
		operation.setModifiedBy(loginUserId);
		operation.setCreationDate(new Date());
		operation.setModifyDate(new Date());
		this.operationDao.save(operation);
		if (operation.getDelOperationResIdList() != null
				&& !operation.getDelOperationResIdList().isEmpty()) {
			this.operationResourceDao.delOperationResourceList(operation
					.getDelOperationResIdList());
		}
		if (operation.getOperationResList() == null) {
			return;
		}
		for (OperationResource ors : operation.getOperationResList()) {
			// ors.setSeqNo(seqNo);
			ors.setCreatedBy(loginUserId);
			ors.setModifiedBy(loginUserId);
			ors.setCreationDate(new Date());
			ors.setModifyDate(new Date());
			ors.setOperationId(operation.getId());
			if (ors.getId() == null) {
				this.operationResourceDao.save(ors);
			} else {
				this.operationResourceDao.getSession().merge(ors);
			}
		}

	}
	
	/**
	 * 批量修改woOperation的状态
	 */
	public String batchOperate(List<String> selWoIdList,String status,String sessId,String comments,String customStartDate) {
		if(selWoIdList==null||selWoIdList.size()==0) {
			return "error";
		}
		Map<String, WorkOrderOperation> sessMap = (LinkedHashMap<String, WorkOrderOperation>) SessionUtil
		.getRow(SessionConstant.WorkOrderOperation.value(), sessId);
		if(sessMap==null||sessMap.size()==0) {
			return "error";
		}
		Map<Integer,Date> seqNoMap = new HashMap<Integer,Date>();
		for(String woKey:selWoIdList) {
			WorkOrderOperation workOrderOperation = sessMap.get(woKey);
			if(workOrderOperation==null||workOrderOperation.getOperation()==null) {
				continue;
			}
			workOrderOperation.setStatus(status);
			workOrderOperation.getOperation().setComment(comments);
			if(StringUtils.isNotEmpty(customStartDate)) {
				workOrderOperation.setCustomStartDate(new java.sql.Date(DateUtils.formatStr2Date(customStartDate,"yyyy-MM-dd HH:mm").getTime()));
			}
			if(WoOperStatus.Completed.value().equals(status)){
				if(workOrderOperation.getActualEndDate()==null) {
					workOrderOperation.setActualEndDate(new java.sql.Date(new Date().getTime()));
					seqNoMap.put(workOrderOperation.getSeqNo()+1,new Date());
				}
			}
		}
		if(WoOperStatus.Completed.value().equals(status)) {
			boolean isChangeStatus = true;
			for (Entry<String, WorkOrderOperation> entry : sessMap
					.entrySet()) {
				WorkOrderOperation workOrderOPeration = entry.getValue();
				if(isChangeStatus&&workOrderOPeration!=null&&(workOrderOPeration.getStatus()==null||!WoOperStatus.Completed.value().equals(workOrderOPeration.getStatus()))) {
					isChangeStatus = false;
				}
				if(seqNoMap.containsKey(workOrderOPeration.getSeqNo())) {
					workOrderOPeration.setActualStartDate(new java.sql.Date(seqNoMap.get(workOrderOPeration.getSeqNo()).getTime()));
				}
			}
			if(isChangeStatus) {
				Map<String, Object> session = ActionContext.getContext().getSession();
				WorkOrderDTO workOrder = (WorkOrderDTO)session.get(sessId);
				workOrder.setStatus(WorkOrderStatus.Completed.value());
				session.put(sessId, workOrder);
				return "complete";
			} else {
				Map<String, Object> session = ActionContext.getContext().getSession();
				WorkOrderDTO workOrder = (WorkOrderDTO)session.get(sessId);
				workOrder.setStatus(WorkOrderStatus.Inprogress.value());
				if(workOrder.getActualStart()==null) {
					workOrder.setActualStart(new java.sql.Date(new Date().getTime()));
				}
				session.put(sessId, workOrder);
				return "inprogress";
			}
		}
		if(WoOperStatus.Inprogress.value().equals(status)) {
			Map<String, Object> session = ActionContext.getContext().getSession();
			WorkOrderDTO workOrder = (WorkOrderDTO)session.get(sessId);
			workOrder.setStatus(WorkOrderStatus.Inprogress.value());
			if(workOrder.getActualStart()==null) {
				workOrder.setActualStart(new java.sql.Date(new Date().getTime()));
			}
			session.put(sessId, workOrder);
			return "inprogress";
		}
		return "success";
	}
	
	/**
	 * 批量修改woOperation
	 */
	public String batchUpdateWoOperation(String woOperationIds,String status,String comments,String customStartDate) {
		if(StringUtils.isEmpty(woOperationIds)) {
			return "error";
		}
		String[] woOperationIdArray = woOperationIds.split(",");
		for(String woOperationIdStr:woOperationIdArray) {
			WorkOrderOperation workOrderOperation = this.workOrderOPerationDao.getById(Integer.parseInt(woOperationIdStr));
			if(workOrderOperation==null||workOrderOperation.getOperation()==null) {
				continue;
			}
			workOrderOperation.setStatus(status);
			workOrderOperation.getOperation().setComment(comments);
			if(StringUtils.isNotEmpty(customStartDate)) {
				workOrderOperation.setCustomStartDate(new java.sql.Date(DateUtils.formatStr2Date(customStartDate,"yyyy-MM-dd HH:mm").getTime()));
			}
			Integer workOrderNo = workOrderOperation.getWorkOrderNo();
			WorkOrder workOrder = this.workOrderDao.getById(workOrderNo);
			if(WoOperStatus.Completed.value().equals(status)){
				Integer seqNo = this.workOrderOPerationDao.getMaxSeq(workOrderNo);
				if(workOrderOperation.getActualEndDate()==null) {
					workOrderOperation.setActualEndDate(new java.sql.Date(new Date().getTime()));
				}
				if(workOrder.getActualStart()==null) {
					workOrder.setActualStart(new java.sql.Date(new Date().getTime()));
				}
				if(seqNo.intValue()==workOrderOperation.getSeqNo().intValue()) {
					workOrder.setStatus(status);
					if(workOrder.getActualEnd()==null) {
						workOrder.setActualEnd(new java.sql.Date(new Date().getTime()));
					}
				} else {
					WorkOrderOperation nextWorkOrderOperation = this.workOrderOPerationDao.findWoOperation(workOrderOperation.getSeqNo()+1, workOrderNo);
					if(nextWorkOrderOperation!=null&&nextWorkOrderOperation.getActualStartDate()==null) {
						nextWorkOrderOperation.setActualStartDate(new java.sql.Date(new Date().getTime()));
						this.workOrderOPerationDao.save(nextWorkOrderOperation);
					}
				}
				
			} else if(WoOperStatus.Inprogress.value().endsWith(status)) {
				if(workOrderOperation.getActualStartDate()==null) {
					workOrderOperation.setActualStartDate(new java.sql.Date(new Date().getTime()));
				}
				if(WoOperStatus.New.value().equals(workOrder.getStatus())) {
					workOrder.setStatus(status);
					if(workOrder.getActualStart()==null) {
						workOrder.setActualStart(new java.sql.Date(new Date().getTime()));
					}
				}
			}
			this.workOrderDao.save(workOrder);
			this.workOrderOPerationDao.save(workOrderOperation);
		}
		return "success";
	}
	
	/**
	 * 批量修改woOperation
	 */
	public String batchUpdateWoOperation(List<WorkOrderOperation> list) {
		if(list.size()==0) {
			return "No one WorkOrderOperation exist.";
		}
		for(WorkOrderOperation workOrderOperation:list) {
			WorkOrderOperation workOrderOperationDb = workOrderOPerationDao.findWoOperation(workOrderOperation.getOperation().getName(),workOrderOperation.getWorkOrderNo());
			if(workOrderOperationDb==null) {
				return "WorkOrderNo:"+workOrderOperation.getWorkOrderNo()
						+" and OperationName:"+workOrderOperation.getOperation().getName()
						+" associated Work order Operation not exist.";
			}
			String status = workOrderOperation.getStatus();
			if(!WoOperStatus.isExist(status)) {
				return "The status in the row of WorkOrderNo:"+workOrderOperation.getWorkOrderNo()
					+" and OperationName:"+workOrderOperation.getOperation().getName()+" is wrong.";
			}
			workOrderOperationDb.setStatus(status);
			Integer workOrderNo = workOrderOperationDb.getWorkOrderNo();
			WorkOrder workOrder = this.workOrderDao.getById(workOrderNo);
			List<WorkOrderOperation> preWoOperationList = workOrderOPerationDao.findWoOperationList(workOrderOperationDb.getSeqNo(),workOrderNo);
			for(WorkOrderOperation woOp:preWoOperationList) {
				if(woOp.getActualStartDate()==null) {
					woOp.setActualStartDate(new java.sql.Date(new Date().getTime()));
				}
				if(woOp.getActualEndDate()==null) {
					woOp.setActualEndDate(new java.sql.Date(new Date().getTime()));
				}
				woOp.setStatus(WoOperStatus.Completed.value());
				woOp.setModifiedBy(SessionUtil.getUserId());
				woOp.setModifyDate(new Date());
				this.workOrderOPerationDao.save(woOp);
			}
			if(WoOperStatus.Completed.value().equals(status)){
				Integer seqNo = this.workOrderOPerationDao.getMaxSeq(workOrderNo);
				if(workOrderOperationDb.getActualEndDate()==null) {
					workOrderOperationDb.setActualEndDate(new java.sql.Date(new Date().getTime()));
				}
				if(workOrder.getActualStart()==null) {
					workOrder.setActualStart(new java.sql.Date(new Date().getTime()));
				}
				if(seqNo.intValue()==workOrderOperationDb.getSeqNo().intValue()) {
					workOrder.setStatus(status);
					if(workOrder.getActualEnd()==null) {
						workOrder.setActualEnd(new java.sql.Date(new Date().getTime()));
					}
				} 
			} else {
				if(workOrderOperationDb.getActualStartDate()==null) {
					workOrderOperationDb.setActualStartDate(new java.sql.Date(new Date().getTime()));
				}
				if(WoOperStatus.New.value().equals(workOrder.getStatus())) {
					workOrder.setStatus(status);
					if(workOrder.getActualStart()==null) {
						workOrder.setActualStart(new java.sql.Date(new Date().getTime()));
					}
				}
			}
			workOrderOperationDb.setModifiedBy(SessionUtil.getUserId());
			workOrderOperationDb.setModifyDate(new Date());
			workOrder.setModifiedBy(SessionUtil.getUserId());
			workOrder.setModifyDate(new Date());
			this.workOrderDao.save(workOrder);
			this.workOrderOPerationDao.save(workOrderOperationDb);
		}
		return "Success";
	}

	/**
	 * 批量删除Operation.
	 * 
	 * @param resourceIdList
	 */
	public void delOperation(List<Integer> idList) {
		if (idList != null && !idList.isEmpty()) {
			this.operationDao.batchUpdateStatus(idList,
					StatusType.INACTIVE.value);
		}
	}

	/**
	 * 根据条件查找并分页显示, 如果查找条件为null, 则返回所有结果并分页显示.
	 * 
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Operation> searchOperationPage(Page<Operation> page,
			List<PropertyFilter> filters,String selectFlg) throws Exception {
		if (filters == null || filters.isEmpty()) {
			filters = new ArrayList<PropertyFilter>();
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		if (!Constants.USERNAME_ADMIN.equals(userName)&&!"Y".equals(selectFlg)) {
			// 判断当前用户是否含有生产部经理角色
			boolean productionManager = userRoleDao
					.checkIsContainsManagerRole(Constants.ROLE_MANUFACTURINGSETUPS_MANAGER);
			if (!productionManager) {
				return page;
			}
			// Object userId = session.get(StrutsActionContant.USER_ID);
			// //查询用户所在组Id
			// ManufacturingClerksBean manufacturingClerksBean
			// = manufacturingClerksBeanDao.findUniqueBy("clerkId", userId);
			// if (manufacturingClerksBean == null ||
			// manufacturingClerksBean.getWorkGroupId() == null) {
			// return page;
			// }
			// //查询用户所在组对象获得workCenterId
			// WorkGroup workGroup = workGroupDao.findUniqueBy("id",
			// manufacturingClerksBean.getWorkGroupId());
			// if (workGroup == null || workGroup.getWorkCenterId() == null) {
			// return page;
			// }
			// PropertyFilter workCenterFilter = new
			// PropertyFilter("EQI_workCenterId",
			// workGroup.getWorkCenterId());
			// filters.add(workCenterFilter);
		}
		page = this.operationDao.findPage(page, filters,true);
		if (page != null && page.getResult() != null
				&& page.getResult().size() > 0) {
			for (Operation operation : page.getResult()) {
				if(operation.getSupervisor()!=null) {
					User superUser = this.userDao
					.getById(operation.getSupervisor());
					if (superUser != null) {
						operation.setSuperName(superUser.getFirstName()!=null?superUser.getFirstName():""+" "+superUser.getLastName()!=null?superUser.getLastName():"");
					}
				}
				User temp = this.userDao.getById(operation.getModifiedBy());
				if (temp != null) {
					operation.setModifyUser(temp.getLoginName());
				}
				if (operation.getWorkCenterId() != null) {
					WorkCenter workCenter = this.workCenterDao.get(operation
							.getWorkCenterId());
					if (workCenter != null) {
						operation.setWorkCenterName(workCenter.getName());
					}
				}
			}
		}
		return page;
	}

	/**
	 * 根据条件查找并分页显示, 如果查找条件为null, 则返回所有结果并分页显示.
	 * 
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Operation> searchOperationPageForRoute(Page<Operation> page,
			List<PropertyFilter> filters) throws Exception {
		if (filters == null || filters.isEmpty()) {
			filters = new ArrayList<PropertyFilter>();
		}
		page = this.operationDao.findPage(page, filters,true);
		if (page != null && page.getResult() != null
				&& page.getResult().size() > 0) {
			for (Operation operation : page.getResult()) {
				if(operation.getSupervisor()!=null) {
					User superUser = this.userDao
					.getById(operation.getSupervisor());
					if (superUser != null) {
						operation.setSuperName(superUser.getLoginName());
					}
				}
				User temp = this.userDao.getById(operation.getModifiedBy());
				if (temp != null) {
					operation.setModifyUser(temp.getLoginName());
				}
				if (operation.getWorkCenterId() != null) {
					WorkCenter workCenter = this.workCenterDao.get(operation
							.getWorkCenterId());
					if (workCenter != null) {
						operation.setWorkCenterName(workCenter.getName());
					}
				}
			}
		}
		return page;
	}

	/**
	 * 获得所有WorkCenter, 供新增、修改Operations选择相应的WorkCenter.
	 * 
	 * @return
	 */
	public List<WorkCenter> getAllWorkCenter(String roleName){
		if(StringUtils.isEmpty(roleName)) {
			return  this.workCenterDao.findAll(Page.ASC,"name");
		}
		List<WorkCenter> result = new ArrayList<WorkCenter>();
		Set<Integer> centerIdSet = currentUserGetWorkCenter(roleName);
		if(centerIdSet==null) {
			return result;
		} else if(centerIdSet.size()==0){
			return  this.workCenterDao.findAll(Page.ASC,"name");
		}
		return  this.workCenterDao.findByIds(SessionUtil.convertSet2List(centerIdSet));
	}
	
	/**
	 * 获得所有WorkCenter, 供新增、修改Operations选择相应的WorkCenter.
	 * 
	 * @return
	 */
	public List<WorkCenter> getAllWorkCenter() {
		return  this.workCenterDao.findAll(Page.ASC,"name");
	}

	@Transactional(readOnly = true)
	public List<RouteOperation> getAllRouteOperation(Integer routingId) {
		List<RouteOperation> list = this.routeOperationDao
				.getAllList(routingId);
		for (RouteOperation ro : list) {
			Operation operation = ro.getOperation();
			if(operation.getSupervisor()!=null) {
				User superUser = this.userDao.getById(operation.getSupervisor());
				if (superUser != null) {
					operation.setSuperName(superUser.getLoginName());
				}
			}
			User temp = this.userDao.getById(operation.getModifiedBy());
			if (temp != null) {
				operation.setModifyUser(temp.getLoginName());
			}
			if (operation.getWorkCenterId() != null) {
				WorkCenter workCenter = this.workCenterDao.getById(operation
						.getWorkCenterId());
				if (workCenter != null) {
					operation.setWorkCenterName(workCenter.getName());
				}
			}
		}
		return list;
	}

	@Transactional(readOnly = true)
	public List<Route> getAllRoute() {
		return this.routeDao.findAll(Page.ASC,"name");
	}

	/**
	 * 根据id主健查找Route, 并根据modifyId找出modifyUser(从User表中的loginName得到).
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Route getRoute(Integer id) {
		Route route = this.routeDao.get(id);
		User temp = this.userDao.getById(route.getModifiedBy());
		if (temp != null) {
			route.setModifyUser(temp.getLoginName());
		}
		return route;
	}

	/**
	 * 新增或修改Route 取当前时间作为creationDate, ModifyDate.
	 * 
	 * @param Route
	 * @param loginUserId
	 *            当前登录用户
	 */
	public void saveRoute(Route route, Integer loginUserId) {
		route.setCreatedBy(loginUserId);
		route.setModifiedBy(loginUserId);
		route.setCreationDate(new Date());
		route.setModifyDate(new Date());
		this.routeDao.save(route);
		if("Y".equals(route.getDefaultFlag())) {
			this.routeDao.batchUpdateDefaultFlag(route);
		}
		if (route.getDelRouteOperationIdList() != null
				&& !route.getDelRouteOperationIdList().isEmpty()) {
			this.routeOperationDao.delRouteOperationList(route
					.getDelRouteOperationIdList());
		}

		for (RouteOperation ors : route.getRouteOperationList()) {

			ors.setRoutingId(route.getId());

			if (ors.getId() == null) {
				this.routeOperationDao.save(ors);
			} else {
				this.routeOperationDao.getSession().merge(ors);
			}
		}
	}

	/**
	 * 批量删除Route.
	 * 
	 * @param idList
	 */
	public void delRoute(List<Integer> idList) {
		if (idList != null && !idList.isEmpty()) {
			this.routeDao.batchUpdateStatus(idList, StatusType.INACTIVE.value);
		}
	}

	/**
	 * 根据条件查找并分页显示, 如果查找条件为null, 则返回所有结果并分页显示.
	 * 
	 * @param page
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Route> searchRoutePage(Page<Route> page,
			List<PropertyFilter> filters) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		if (!Constants.USERNAME_ADMIN.equals(userName)) {
			// 判断当前用户是否含有生产部经理角色
			boolean productionManager = userRoleDao
					.checkIsContainsManagerRole(Constants.ROLE_MANUFACTURINGSETUPS_MANAGER);
			if (!productionManager) {
				return page;
			}
		}
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			page = this.routeDao.getAll(page);
		} else {
			List<PropertyFilter> newFilters = new ArrayList<PropertyFilter>();
			Iterator<PropertyFilter> item = filters.iterator();
			while (item != null && item.hasNext()) {
				PropertyFilter filter = item.next();
				String filterName = filter.getPropertyName();
				String filterValue = filter.getPropertyValue()==null?"":filter.getPropertyValue().toString();
				if (("production").equalsIgnoreCase(filterName)) {
					if (filterValue.contains("-")) {
						String[] typeClsIds = filterValue.split("-");
						PropertyFilter itemTypeFilter = new PropertyFilter("LIKES_itemType",typeClsIds[0]);
						PropertyFilter clsIdFilter = new PropertyFilter("EQI_clsId",Integer.parseInt(typeClsIds[1]));
						newFilters.add(itemTypeFilter);
						newFilters.add(clsIdFilter);
					}
				} else {
					newFilters.add(filter);
				}
			}
			page = this.routeDao.findPage(page, newFilters);
		}
		for (Route route : page.getResult()) {
			User temp = this.userDao.getById(route.getModifiedBy());
			if (temp != null) {
				route.setModifyUser(temp.getLoginName());
			}
			if (route.getWarehouseId() != null) {
				Warehouse warehouse = this.warehouseDao.get(route
						.getWarehouseId());
				if (warehouse != null) {
					route.setWarehouseName(warehouse.getName());
				}
			}
			if (route.getItemType() != null&&route.getClsId()!=null) {
				if (QuoteItemType.SERVICE.value().equals(route.getItemType().toUpperCase())) {
					ServiceClass servClass = this.serviceClassDao.get(route
							.getClsId());
					route.setProduction(QuoteItemType.SERVICE.value() + " - " + servClass.getName());
				} else {
					ProductClass pdtClass = this.productClassDao.get(route
							.getClsId());
					route.setProduction(QuoteItemType.PRODUCT.value() + " - " + pdtClass.getName());
				}
			}
		}
		return page;
	}
	
	/**
	 * 获得Routing页面Production下拉框内容
	 * @author Zhang Yong
	 * @return
	 */
	public List<String[]> getProductionList () {
		List<String[]> productionList = new ArrayList<String[]>();
		List<Object[]> typeClsIdList = this.routeDao.findTypeClsId();
		if (typeClsIdList == null) {
			return productionList;
		}
		List<Integer> serviceClsIds = new ArrayList<Integer>();
		List<Integer> productClsIds = new ArrayList<Integer>();
		for (Object[] objs : typeClsIdList) {
			String itemType = objs[0].toString();
			Integer clsId = Integer.parseInt(objs[1].toString());
			if (QuoteItemType.SERVICE.value().equalsIgnoreCase(itemType)) {
				serviceClsIds.add(clsId);
			} else if (QuoteItemType.PRODUCT.value().equalsIgnoreCase(itemType)) {
				productClsIds.add(clsId);
			}
		}
		if (!serviceClsIds.isEmpty()) {
			List<ServiceClass> scList = this.serviceClassDao.findByIds(serviceClsIds);
			if (scList != null && !scList.isEmpty()) {
				for (ServiceClass sc : scList) {
					String[] strs = new String[2];
					strs[0] = QuoteItemType.SERVICE.value() + "-" + sc.getClsId();
					strs[1] = QuoteItemType.SERVICE.value() + "-" + sc.getName();
					productionList.add(strs);
				}
			}
		}
		if (!productClsIds.isEmpty()) {
			List<ProductClass> pdList = this.productClassDao.findByIds(productClsIds);
			if (pdList != null && !pdList.isEmpty()) {
				for (ProductClass pd : pdList) {
					String[] strs = new String[2];
					strs[0] = QuoteItemType.PRODUCT.value() + "-" + pd.getClsId();
					strs[1] = QuoteItemType.PRODUCT.value() + "-" + pd.getName();
					productionList.add(strs);
				}
			}
		}
		return productionList;
	}

	/**
	 * 根据operationId获得WorkGroup list.
	 * 
	 * @param operationId
	 * @return
	 * 
	 * public List<WorkGroup> getGroupListByCenter(Integer operationId) { List<WorkGroup>
	 * groupList = this.workGroupDao .getGroupListByCenter(operationId); for
	 * (WorkGroup workGroup : groupList) { User superUser =
	 * this.userDao.getById(workGroup.getSupervisor()); if (superUser != null) {
	 * workGroup.setSuperName(superUser.getLoginName()); } User temp =
	 * this.userDao.getById(workGroup.getModifiedBy()); if (temp != null) {
	 * workGroup.setModifyUser(temp.getLoginName()); } } return groupList; }
	 */

	enum StatusType {
		ACTIVE("ACTIVE"), INACTIVE("INACTIVE");
		private final String value;

		StatusType(String v) {
			value = v;
		}

		public String getValue() {
			return this.value;
		}
	}

	
	/**
	 * 获取workcenter分页对象
	 * @param page workcenter分页对象
	 * @return Page<WorkCenter>
	 */
	public Page<WorkCenter> searchWorkCenterPage(Page<WorkCenter> page){
		PagerUtil<WorkCenter> pagerUtil = new PagerUtil<WorkCenter>();
		page = pagerUtil.getRequestPage();// 获得分页请求相关数据：如第几页
		if (!page.isOrderBySetted()) {// 设置默认排序
			page.setOrderBy("name");
			page.setOrder(Page.ASC);
		}
		page.setPageSize(20);// 设置默认每页显示记录条数
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());//搜索条件
		if (filters == null) {
			filters = new ArrayList<PropertyFilter>();
		}
		Set<Integer> centerIdSet = currentUserGetWorkCenter(Constants.ROLE_WOENTRY_MANAGER);
		if(centerIdSet==null) {
			return page;
		}
		// 查询workCenter列表
		page = this.workCenterDao.findPageForCenter(page,filters,centerIdSet);
		for (WorkCenter workCenter : page.getResult()) {
			getOtherInfoForCenter(workCenter);
		}
		return page;
	}
	
	/**
	 * 当前session中的用户能看到的WorkCenter
	 * @return
	 */
	private Set<Integer> currentUserGetWorkCenter(String roleName) {
		Set<Integer> centerIdSet = new HashSet<Integer>();
		if (!Constants.USERNAME_ADMIN.equals(SessionUtil.getUserName())) {//不是超级管理员
			
			// 判断当前用户是否含有roleName角色
			if (!userRoleDao.checkIsContainsManagerRole(roleName)) {
				// 查询用户所在组Id
				List<ManufacturingClerksBean> manufacturingClerksBeanList = manufacturingClerksBeanDao.findBy("clerkId", SessionUtil.getUserId());
				if(manufacturingClerksBeanList==null||manufacturingClerksBeanList.size()==0) {
					return null;
				}
				for(ManufacturingClerksBean manufacturingClerksBean:manufacturingClerksBeanList) {
					if (manufacturingClerksBean.getWorkCenterId() == null) {
						continue;
					}
					centerIdSet.add(manufacturingClerksBean.getWorkCenterId());
				}
				if(centerIdSet.isEmpty()) {
					return null;
				}

			} else {
				List<WorkCenter> centerList = this.workCenterDao.findBy("supervisor", SessionUtil.getUserId());
				if(centerList!=null&&centerList.size()>0) {
					for(WorkCenter workCenter:centerList) {
						centerIdSet.add(workCenter.getId());
					}
				} else {
					List<ManufacturingClerks> manufacturingClerksList = this.manufacturingClerksDao.findBy("clerkId", SessionUtil.getUserId());
					if(manufacturingClerksList==null||manufacturingClerksList.size()==0) {
						return null;
					}
					for(ManufacturingClerks manufacturingClerks:manufacturingClerksList) {
						if (manufacturingClerks.getWorkCenterId() == null) {
							continue;
						}
						centerIdSet.add(manufacturingClerks.getWorkCenterId());
					}
					if(centerIdSet.isEmpty()) {
						return null;
					}
				}
			}
		}
		return centerIdSet;
	}
	
	/**
	 * 填充workcenter其他非数据库属性
	 */
	private void getOtherInfoForCenter(WorkCenter workCenter) {
		if (workCenter.getSupervisor() != null) {
			User superUser = this.userDao.getById(workCenter.getSupervisor());
			workCenter.setSuperName(superUser!=null?((superUser.getFirstName()!=null?superUser.getFirstName():"")+(" "+superUser.getLastName()!=null?superUser.getLastName():"")):"");
		}
		User temp = this.userDao.getById(workCenter.getModifiedBy());
		workCenter.setModifyUser(temp!=null?temp.getLoginName():"");
		if (workCenter.getWarehouseId() != null) {
			Warehouse warehouse = this.warehouseDao.get(workCenter.getWarehouseId());
			workCenter.setWarehouseName(warehouse!=null?warehouse.getName():"");
		}
	}

	/**
	 * 获取Product/Service
	 * 
	 * @return
	 */
	public Map<String, String> getProductAndService(String type) {
		Map<String, String> allcls = new LinkedHashMap<String, String>();
		if("product".equals(type)) {
			List<ProductClass> productcls = productClassDao.findAll(Page.ASC,"name");
			for (int i = 0; i < productcls.size(); i++) {
				allcls.put(productcls.get(i).getClsId() + ":"
						+ QaGroupAssignedType.PRODUCT.value() + ":"
						+ productcls.get(i).getName(), productcls.get(i).getName());
			}
		} else if("service".equals(type)){
			List<ServiceClass> servicecls = serviceClassDao.findAll(Page.ASC,"name");
			
			
			for (int i = 0; i < servicecls.size(); i++) {
				allcls.put(servicecls.get(i).getClsId() + ":"
						+ QaGroupAssignedType.SERVICE.value() + ":"
						+ servicecls.get(i).getName(), servicecls.get(i).getName());
			}
		}
		return allcls;
	}

	/**
	 * 根据centerId获取关联的WorkCenterAssigned对象列表
	 * 
	 * @param groupId
	 */
	@SuppressWarnings("unchecked")
	public List<WorkCenterAssigned> getWorkCenterAssignedList(
			String sessWorkCenterId) {
		List<WorkCenterAssigned> workCenterAssignedList2 = (List<WorkCenterAssigned>) SessionUtil
				.getRow(SessionConstant.WorkCenterAssigned.value(),
						sessWorkCenterId);
		if (workCenterAssignedList2 == null) {
			if (StringUtil.isNumeric(sessWorkCenterId)) {
				workCenterAssignedList2 = this.workCenterAssignedDao.findBy(
						"workCenter.id", Integer.parseInt(sessWorkCenterId));
				if (workCenterAssignedList2 != null
						&& workCenterAssignedList2.size() > 0) {
					for (WorkCenterAssigned workCenterAssigned : workCenterAssignedList2) {
						if (WorkCenterAssignedType.PRODUCT.value().equals(
								workCenterAssigned.getItemType())&&workCenterAssigned.getClsId()!=null) {
							workCenterAssigned.setClsName(productClassDao
									.getById(workCenterAssigned.getClsId())
									.getName());
						} else if (WorkCenterAssignedType.SERVICE.value()
								.equals(workCenterAssigned.getItemType())&&workCenterAssigned.getClsId()!=null) {
							workCenterAssigned.setClsName(serviceClassDao
									.getById(workCenterAssigned.getClsId())
									.getName());
						}
					}
				}
			}

		}
		SessionUtil.insertRow(SessionConstant.WorkCenterAssigned.value(),
				sessWorkCenterId, workCenterAssignedList2);
		return workCenterAssignedList2;
	}
	
	/**
	 * 有operationId和resourceId获取OperationResource对象
	 */
	public OperationResource findByOpAndRE(Integer operationId,Integer resourceId) {
		return this.operationResourceDao.findByOpAndRE(operationId,resourceId);
	}
	
	/**
	 * 根据条件查找User并分页显示
	 * @param usersPage 分页对象
	 */
	public Page<User> searchUserPage(Page<User> usersPage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<User> pagerUtil = new PagerUtil<User>();
		usersPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!usersPage.isOrderBySetted()) {
			usersPage.setOrderBy("id");
			usersPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		usersPage.setPageSize(10);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			usersPage = this.userDao.getAll(usersPage);
		} else {
			usersPage = this.userDao.searchUser(usersPage, filters);
		}
		return usersPage;
	}
	
	/**
	 * 根据条件查找User并分页显示
	 * @param usersPage 分页对象
	 */
	public Page<User> searchUserPage(Page<User> usersPage,UserSrchDTO srch) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<User> pagerUtil = new PagerUtil<User>();
		usersPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!usersPage.isOrderBySetted()) {
			usersPage.setOrderBy("id");
			usersPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		usersPage.setPageSize(10);
		// 获得查询条件
		usersPage = this.userDao.searchUser(usersPage, srch);
		return usersPage;
	}
	
	
	/**
	 * 数据库中除了routeId对应的route有没有默认有效的route
	 */
	public boolean isHasOtherDefaultRoute(Integer routeId) {
		List<Route> list = this.routeDao.findBy("defaultFlag", "Y");
		if(list!=null&&list.size()>0) {
			if(routeId==null) {
				return true;
			} else if(list.size()==1&&list.get(0).getId().intValue()==routeId.intValue()) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 根据workorder查找适合的route
	 */
	public List<Route> findRouteForWO(Integer clsId,String itemType,Integer warehouseId) {
		return this.routeDao.findRoute(clsId, itemType,warehouseId);
	}
	
	/**
	 * 根据workCenter查找适合的route
	 */
	public List<Route> findRouteForWO(Integer workCenterId,Integer warehouseId) {
		return this.routeDao.findRoute(workCenterId,warehouseId);
	}
	
	public boolean isHasTheRole(String roleName) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		if (!Constants.USERNAME_ADMIN.equals(userName)) {
			return userRoleDao.checkIsContainsManagerRole(roleName);
		}
		return true;
	}
	
	/**
	 * 查找作为内部订单的operation
	 */
	public Operation getInnerOPeration() {
		return this.operationDao.getById(-1);
	}
	
	/**
	 * 查看这两个部门是不是Gene和Oligo
	 */
	public boolean workCenterJudge(Integer originalCenterId,Integer targetCenterId) {
		WorkCenter originalCenter = this.workCenterDao.getById(originalCenterId);
		WorkCenter targetCenter = this.workCenterDao.getById(targetCenterId);
		if(originalCenter!=null&&"Gene Department".equals(originalCenter.getName())&&targetCenter!=null&&"Oligo Department".equals(targetCenter.getName())) {
			return true;
		}
		return false;
	}
}
