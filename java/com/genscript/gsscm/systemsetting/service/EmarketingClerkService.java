/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.genscript.gsscm.systemsetting.service;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.privilege.dao.EmployeeDao;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.ProductClassDao;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.serv.dao.ServiceClassDao;
import com.genscript.gsscm.serv.entity.ServiceClass;
import com.genscript.gsscm.system.dao.EmarketingClerkDao;
import com.genscript.gsscm.system.dao.EmarketingGroupAssignedDao;
import com.genscript.gsscm.system.dao.EmarketingGroupDao;
import com.genscript.gsscm.system.entity.EmarketingClerk;
import com.genscript.gsscm.system.entity.EmarketingGroup;
import com.genscript.gsscm.system.entity.EmarketingGroupAssigned;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jinsite
 */
@Service
@Transactional
public class EmarketingClerkService {
    @Autowired
    private EmarketingClerkDao emarketingClerkDao;
    @Autowired
    private EmarketingGroupDao emarketingGroupDao;
    @Autowired
    private EmarketingGroupAssignedDao emarketingGroupAssignedDao;
    @Autowired
    private ProductClassDao productClassDao;
    @Autowired
    private ServiceClassDao serviceClassDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private EmployeeDao employeeDao;

    /**
     * Search EmarketingGroup
     * @param page
     * @param filters
     * @return
     */
    public Page<EmarketingGroup> searchEmarketingGroup(Page<EmarketingGroup> page, List<PropertyFilter> filters){        
        return emarketingGroupDao.findPage(page, filters);
    }
    /**
     * Search EmarketingGroup 
     * @param page
     * @return
     */
    public Page<EmarketingGroup> searchEmarketingGroup(Page<EmarketingGroup> page){
        return  emarketingGroupDao.findPage(page);
    }
    /**
     * Search EmarketingGroup By HQL
     * @param page
     * @param hql
     * @param values
     * @return
     */
    private  Page<EmarketingGroup> searchEmarketingGroup(Page<EmarketingGroup> page,String hql,Object... values){
        return emarketingGroupDao.findPage(page, hql, values);
    }
    /**
     * Search EmarketingGroup
     * @param page
     * @param filters
     * @param values
     * @return
     */
    public Page<EmarketingGroup> searchEmarketingGroup(Page<EmarketingGroup> page,List<PropertyFilter> filters, String[] values){
        String where="";
        for(PropertyFilter filter: filters){
            where+=" and group1."+filter.getPropertyName()+"= '"+ filter.getPropertyValue().toString()+"'";
        }
        where+=" and assigned.itemType='"+ values[2]+"'";
        where+=" and assigned.clsId="+ values[1];

       return searchEmarketingGroup(page,"select distinct group1 from EmarketingGroup group1, EmarketingGroupAssigned assigned where group1.groupId = assigned.marketingGroupId  "+where);

    }
    /**
     * Search EmarketingGroup 
     * @param page
     * @param filters
     * @param values
     * @return
     */
    public Page<EmarketingGroup> searchEmarketingGroup(Page<EmarketingGroup> page,List<PropertyFilter> filters, Map<String,Object> values){
        return null;
    }
    
    /**
     * Get EmarketingGroupAssigned By GroupId.
     * @param group_id
     * @return
     */
    @Transactional(readOnly=true)
    public List<EmarketingGroupAssigned> getEmarketingGroupAssignedByGroupId(Integer group_id){
        List<EmarketingGroupAssigned> list =emarketingGroupAssignedDao.findBy("marketingGroupId", group_id);
        for(int i=0;i<list.size();i++){
            if(list.get(i).getItemType().equalsIgnoreCase("SERVICE")){
                    list.get(i).setClsName(serviceClassDao.get(list.get(i).getClsId()).getName());
             }
            if(list.get(i).getItemType().equalsIgnoreCase("PRODUCT")){
                 list.get(i).setClsName(productClassDao.get(list.get(i).getClsId()).getName());
             }
         }

        return list;
    }

    /**
     * Get EmployeeName By ClerkId
     * @param clerkId
     * @return
     */
    public String getEmployeeName(int userId){
        return userDao.getById(userId).getEmployee().getEmployeeName();
    }
   /**
    * Get Product and Service names and ids.
    * @return
    */
    public Map<String,String> getProductAndService(){
        List<ProductClass> productcls = productClassDao.getAll();
        List<ServiceClass> servicecls = serviceClassDao.getAll();
        Map<String,String> allcls=new HashMap<String, String>();
        for(int i=0;i<productcls.size();i++){
            allcls.put(productcls.get(i).getName(), productcls.get(i).getClsId()+":PRODUCT");
        }
        for(int i=0;i<servicecls.size();i++){
            allcls.put(servicecls.get(i).getName(), servicecls.get(i).getClsId()+":SERVICE");
        }
        return allcls;
    }

    /**
     * Get Product/Service Name By GroupId
     * @param groupId
     * @return
     */
    public String getNames(Integer groupId){
        List<EmarketingGroupAssigned> list=getEmarketingGroupAssignedByClerkId(groupId);
        String names="";
        for(int i=0;i<list.size();i++){
            if(list.get(i).getItemType().equals("SERVICE")){
                  names+=serviceClassDao.get(list.get(i).getClsId()).getName()+",";
                }
            if(list.get(i).getItemType().equals("PRODUCT")){
                names+= productClassDao.get(list.get(i).getClsId()).getName()+",";
            }
        }
        return names.length()>0?names.substring(0, names.length()-1):"";
    }

    /**
     * Get EmarketingGroupAssigned
     * @param groupId
     * @return
     */
    private List<EmarketingGroupAssigned> getEmarketingGroupAssignedByClerkId(Integer groupId) {
      return emarketingGroupAssignedDao.findBy("marketingGroupId", groupId);
    }
    /**
     * Get All Users
     * @param usersPage
     * @return
     */
    public Page<User> getUsers(Page<User> usersPage){
        // 获得分页请求相关数据：如第几页
		PagerUtil<User> pagerUtil = new PagerUtil<User>();
		usersPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!usersPage.isOrderBySetted()) {
			usersPage.setOrderBy("userId");
			usersPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		usersPage.setPageSize(10);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			usersPage = this.userDao.getAll(usersPage);
		} else {
			usersPage = this.userDao.searchUserOther(usersPage, filters);
		}
		return usersPage;
    }
       /**
     *  Get All Clerks as Supervisor
     * @return
     */
    public List<User> getSupervisor(Integer groupId) {
        List<User> users = userDao.find("Select distinct user from User user, EmarketingClerk clerk where user.userId=clerk.clerkId and clerk.marketingGroup="+groupId);
        return users;
    }

    /**
     * Save the EmarketingGroup
     * @param group
     */
    public void savaEmarketingGroup(EmarketingGroup group){
        emarketingGroupDao.save(group);
    }
    /**
     * Save EmarketingGroupAssigned
     * @param groupId
     * @param list
     */
    public void saveEmarketingGroupAssigned(Integer groupId,List<EmarketingGroupAssigned> list){
        if(list==null||groupId==null){
            return;
        }
        for(EmarketingGroupAssigned assigned:list){
            if(assigned==null){
                return;
            }
            assigned.setMarketingGroupId(groupId);
            emarketingGroupAssignedDao.save(assigned);
        }
    }
    public void saveEmarketingClerk(Integer groupId,List<EmarketingClerk> list){
         if(list==null||groupId==null){
            return;
        }
        for(EmarketingClerk clerk:list){
            if(clerk==null){
                return;
            }
            clerk.setMarketingGroup(groupId);
           emarketingClerkDao.save(clerk);
        }
    }
    /**
     * Delete EmarketingGroupAssigned by Id
     * @param list
     */
    public void deleteEmarketingGroupAssigned(List<Integer> list){
        if(list==null){
            return ;
        }
        for(Integer id:list){
            emarketingGroupAssignedDao.delete(id);
        }
    }
    /**
     * Delete EmarketingClerk by id
     * @param list
     */
    public void deleteEmarketingClerk(List<Integer> list){
        if(list==null){
            return ;
        }
        for(Integer id:list){
            emarketingClerkDao.delete(id);
        }
    }
    /**
     * Get EmarketingGroup by groupId
     * @param groupId
     * @return
     */
    public EmarketingGroup getEmarketingGroup(Integer groupId){
        return emarketingGroupDao.get(groupId);
    }

    /**
     * Get an tEmarketingClerk by clerkId
     * @param clerkId
     * @return
     */
    public EmarketingClerk getEmarketingClerk(Integer clerkId){
        return emarketingClerkDao.get(clerkId);
    }

    /**
     * Get All EmarketingClerks in group.
     * @param groupId
     * @return
     */
    public List<EmarketingClerk> getEmarketingClerkList(Integer groupId){
       return  emarketingClerkDao.find("select distinct clerk from EmarketingClerk clerk where clerk.marketingGroup="+groupId);
    }
    /**
     *
     * Check the EmarketingClerk exist in group EmarketingClerk.
     * @param groupId
     * @param clerkId
     * @return
     */
    public boolean isExistEmarketingClerk(Integer groupId,Integer clerkId){
        List<EmarketingClerk> list=getEmarketingClerkList(groupId) ;
        for(EmarketingClerk clerk:list){
            if(clerk.getClerkId().equals(clerkId)){
                return true;
            }
        }

        return false;
    }

}
