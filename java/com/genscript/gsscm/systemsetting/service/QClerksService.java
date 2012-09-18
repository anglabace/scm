package com.genscript.gsscm.systemsetting.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.constant.QaGroupAssignedType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.manufacture.dao.QaClerkDao;
import com.genscript.gsscm.manufacture.dao.QaGroupAssignedDao;
import com.genscript.gsscm.manufacture.dao.QaGroupDao;
import com.genscript.gsscm.manufacture.entity.QaClerk;
import com.genscript.gsscm.manufacture.entity.QaGroup;
import com.genscript.gsscm.manufacture.entity.QaGroupAssigned;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.ProductClassDao;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.serv.dao.ServiceClassDao;
import com.genscript.gsscm.serv.entity.ServiceClass;

@Service
@Transactional
public class QClerksService {
	@Autowired
	private QaGroupDao qaGroupDao;
	@Autowired
	private QaClerkDao qaClerkDao;
	@Autowired
    private ProductClassDao productClassDao;
    @Autowired
    private ServiceClassDao serviceClassDao;
    @Autowired
    private QaGroupAssignedDao qaGroupAssignedDao;
    @Autowired
    private UserDao userDao;

	
	/**
	 * 分页查询QaGroup
	 * @param qaGroupPage 分页对象
	 */
	public Page<QaGroup> searchQaGroupPage(Page<QaGroup> qaGroupPage ) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<QaGroup> pagerUtil = new PagerUtil<QaGroup>();
		qaGroupPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!qaGroupPage.isOrderBySetted()) {
			qaGroupPage.setOrderBy("id");
			qaGroupPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		qaGroupPage.setPageSize(15);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			qaGroupPage = this.qaGroupDao.getAll(qaGroupPage);
		} else {
			Criterion criterion = null;
			int len = filters.size();
			for (int i = 0; i < len; i++) {
				PropertyFilter pf = filters.get(i);
				if (pf.getPropertyName().equals("clerkName")) {
					List<Integer> groupIdList = this.qaClerkDao.getGroupList(StringUtil.escapeString(pf.getPropertyValue().toString()));
					if(groupIdList==null||groupIdList.size()==0) {
						criterion = Restrictions.isNull("id");
					} else {
						criterion = Restrictions.in("id", groupIdList);
					}
					filters.remove(pf);
					break;
				}
			}
			qaGroupPage = this.qaGroupDao.findPageByFilter(qaGroupPage, filters,criterion);
		}
		if(qaGroupPage!=null&&qaGroupPage.getResult()!=null) {
			for(QaGroup qaGroup:qaGroupPage.getResult()) {
				User user = this.userDao.getById(qaGroup.getModifiedBy());
				 qaGroup.setModifyUser(user.getLoginName());
			}
		}
		
		return qaGroupPage;
	}
	
	/**
	 * 根据groupId获取QaGroup对象
	 * @param groupId
	 */
	public QaGroup findById(Integer groupId) {
		QaGroup qaGroup = this.qaGroupDao.getById(groupId);
		if(qaGroup!=null&&qaGroup.getSupervisor()!=null) {
			User user = this.userDao.getById(qaGroup.getSupervisor());
			qaGroup.setSuperName(user==null?null:user.getLoginName());
		}
		return qaGroup;
	}
	
	/**
	 * 保存QaGroup对象
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public void saveQaGroup(QaGroup qaGroup,String sessionGroupId) {
		if(qaGroup!=null&&qaGroup.getId()!=null) {
			this.qaGroupAssignedDao.deleteByGroupId(qaGroup.getId());
		}
		if(qaGroup.getId()==null) {
			qaGroup.setCreatedBy(SessionUtil.getUserId());
		}
		this.qaGroupDao.save(qaGroup);
		Map<String,QaClerk> qaClerkMap = (Map<String,QaClerk>)SessionUtil.getRow(SessionConstant.QACLERKLIST.value(), sessionGroupId);
		List<QaGroupAssigned>  QaGroupAssignedList = (List<QaGroupAssigned>)SessionUtil.getRow(SessionConstant.QaGroupAssigned.value(), sessionGroupId);
		if(QaGroupAssignedList!=null&&QaGroupAssignedList.size()>0) {
			for(QaGroupAssigned qaGroupAssigned:QaGroupAssignedList) {
				qaGroupAssigned.setQaGroup(qaGroup);
				qaGroupAssigned.setId(null);
				this.qaGroupAssignedDao.save(qaGroupAssigned);
			}
		}
		if(qaClerkMap!=null&&qaClerkMap.size()>0) {
			Iterator it = qaClerkMap.entrySet().iterator();
			while(it.hasNext()) {
				Map.Entry m=(Map.Entry)it.next();
				QaClerk qaClerk = (QaClerk)m.getValue();
				if(qaClerk==null) {
					this.qaClerkDao.delete(Integer.parseInt((String)m.getKey()));
				} else {
					qaClerk.setGroupId(qaGroup.getId());
					this.qaClerkDao.save(qaClerk);
				}

			}
		}
		
		SessionUtil.deleteRow(SessionConstant.QACLERKLIST.value(), sessionGroupId);
		SessionUtil.deleteRow(SessionConstant.QaGroupAssigned.value(), sessionGroupId);	
	}
	
	/**
	 * 删除QaGroup对象
	 */
	public void deleteQaGroup(String allChoiceVal) {
		for(String groupId:allChoiceVal.split("-")) {
			this.qaClerkDao.deleteByGroupId(Integer.parseInt(groupId));
			this.qaGroupAssignedDao.deleteByGroupId(Integer.parseInt(groupId));
			this.qaGroupDao.delete(Integer.parseInt(groupId));
				
		}
	}
	
	/**
	 * 保存QaClerk对象到session
	 * @param qaClerk 
	 * @param sessionGroupId qaClerk关联的QaGroup在session中的key
	 * @param clerkId qaClerk在session中保存的key
	 */
	@SuppressWarnings("unchecked")
	public void saveClerkToSession(QaClerk qaClerk,String sessionGroupId,String id) throws Exception{
		if(id==null||"".equals(id)) {
			id = SessionUtil.generateTempId();
			qaClerk.setCreatedBy(SessionUtil.getUserId());
		}
		Map<String,QaClerk> qaClerkMap = (Map<String,QaClerk>)SessionUtil.getRow(SessionConstant.QACLERKLIST.value(), sessionGroupId);
		if(qaClerkMap==null) {
			qaClerkMap = new LinkedHashMap<String,QaClerk>();
		}
		qaClerkMap.put(id, qaClerk);
		SessionUtil.insertRow(SessionConstant.QACLERKLIST.value(), sessionGroupId, qaClerkMap);
	}
	
	/**
	 * 删除session中的qaClerk对象
	 * @param allChoiceVal 保存要删除clerkId
	 * @param sessionGroupId qaClerk关联的QaGroup在session中的key
	 */
	@SuppressWarnings("unchecked")
	public void deleteClerks(String allChoiceVal,String sessionGroupId) {
		Map<String,QaClerk> qaClerkMap = (Map<String,QaClerk>)SessionUtil.getRow(SessionConstant.QACLERKLIST.value(), sessionGroupId);
		if(allChoiceVal!=null&&allChoiceVal.endsWith(",")) {
			allChoiceVal = allChoiceVal.substring(0, allChoiceVal.length()-1);
		}
		for(String id:allChoiceVal.split(",")) {
			if(StringUtil.isNumeric(id)) {
				qaClerkMap.put(id, null);
			} else {
				qaClerkMap.remove(id);
			}
		}
		SessionUtil.insertRow(SessionConstant.QACLERKLIST.value(), sessionGroupId, qaClerkMap);
	}
	
	
	/**
	 * 根据groupId获取关联的QaGroupAssigned对象列表
	 * @param groupId
	 */
	@SuppressWarnings("unchecked")
	public List<QaGroupAssigned> getQaGroupAssignedList(String sessionGroupId) {
		List<QaGroupAssigned> QaGroupAssignedList2 = (List<QaGroupAssigned>)SessionUtil.getRow(SessionConstant.QaGroupAssigned.value(), sessionGroupId);
		if(QaGroupAssignedList2==null) {
			if(StringUtil.isNumeric(sessionGroupId)) {
				QaGroupAssignedList2 = this.qaGroupAssignedDao.findBy("qaGroup.id", Integer.parseInt(sessionGroupId));
				if(QaGroupAssignedList2!=null&&QaGroupAssignedList2.size()>0) {
					for(QaGroupAssigned qaGroupAssigned:QaGroupAssignedList2) {
						if(QaGroupAssignedType.PRODUCT.value().equals(qaGroupAssigned.getItemType())) {
							qaGroupAssigned.setClsName(productClassDao.getById(qaGroupAssigned.getClsId()).getName());
						} else if(QaGroupAssignedType.SERVICE.value().equals(qaGroupAssigned.getItemType())) {
							qaGroupAssigned.setClsName(serviceClassDao.getById(qaGroupAssigned.getClsId()).getName());
						}
					}
				}
			}
			
		}
		if(QaGroupAssignedList2==null) {
			QaGroupAssignedList2 = new ArrayList<QaGroupAssigned>();
		}
		SessionUtil.insertRow(SessionConstant.QaGroupAssigned.value(), sessionGroupId, QaGroupAssignedList2);
		return QaGroupAssignedList2;
	}
	
	/**
	 * 根据groupId获取关联的QaClerk对象列表
	 * @param groupId
	 */
	public Map<String,QaClerk> getQaClerkMap(Integer groupId) {
		List<QaClerk> qaClerkList = this.qaClerkDao.findBy("groupId", groupId);
		QaGroup qaGroup = this.qaGroupDao.getById(groupId);
		StringBuffer productServiceStrBuf = new StringBuffer();
		List<QaGroupAssigned> qaGroupAssignedList = this.getQaGroupAssignedList(String.valueOf(groupId));
		if(qaGroupAssignedList!=null&&qaGroupAssignedList.size()>0) {
			for(QaGroupAssigned qaGroupAssigned:qaGroupAssignedList) {
				productServiceStrBuf.append(qaGroupAssigned.getClsName()).append(",");
			}
		}
		Map<String,QaClerk> qaClerkMap = new LinkedHashMap<String,QaClerk>();
		if(qaClerkList!=null&&qaClerkList.size()>0) {
			for(QaClerk qaClerk:qaClerkList) {
				User user = this.userDao.getById(qaClerk.getUserId());
				qaClerk.setClerkName(user!=null?user.getLoginName():"");
				qaClerk.setGroupName(qaGroup.getGroupName());
				qaClerk.setProductService(productServiceStrBuf.length()==0?null:productServiceStrBuf.substring(0, productServiceStrBuf.length()-1));
				
				qaClerkMap.put(String.valueOf(qaClerk.getId()), qaClerk);
			
			}
		}
		SessionUtil.insertRow(SessionConstant.QACLERKLIST.value(), String.valueOf(groupId), qaClerkMap);
		return qaClerkMap;
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
			usersPage = this.userDao.searchClerkUser(usersPage, filters);
		}
		return usersPage;
	}
	
	/**
	 * 获得QaClerk对象其他属性的值(clerkName,superName)
	 */
	public QaClerk getOtherParam(QaClerk qaClerk) {
		if(qaClerk!=null&&qaClerk.getClerkName()==null) {
			User clerkUser = this.userDao.getById(qaClerk.getUserId());
			User supervisor = this.userDao.getById(qaClerk.getSupervisor());
			qaClerk.setClerkName(clerkUser.getLoginName());
			qaClerk.setSuperName(supervisor.getLoginName());
		}
		return qaClerk;
	}

}
