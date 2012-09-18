package com.genscript.gsscm.privilege.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genscript.gsscm.customer.entity.Departments;
import com.genscript.gsscm.report.dao.ReportDao;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.privilege.dto.UserSrchDTO;
import com.genscript.gsscm.privilege.entity.User;

@Repository
public class UserDao extends HibernateDao<User, Integer> {
	private static final String GET_LOGIN_NAME = "select u.loginName from User u where u.userId=?";
	private static final String SELECT_USER_BY_LOGIN_NAME = "from User where loginName like ?";
    @Autowired
    private ReportDao reportDao;
	
	public String getLoginName(final Integer userId){
		return findUnique(GET_LOGIN_NAME, userId);
	}

	@SuppressWarnings("unchecked")
	public Page<User> getUserList(Page<User> page) {
		List<User> userList = null;
		String hql = "from User";
		Query q = createQuery(hql);
		setPageParameter(q, page);
		userList = q.list();
		page.setResult(userList);
		return page;
	}
	
	public Page<User> searchUser(Page<User> page, UserSrchDTO srch) {
		if (srch == null) {
			srch = new UserSrchDTO();
		}
		Map<String, Object> map = new HashMap<String, Object>();		
		String hql = "from User u where 1=1 and u.status='ACTIVE'";
		if (srch.getEmployeeId() != null
				&& srch.getEmployeeId().intValue() != 0) {
			hql += " AND u.employee.employeeId=:employeeId";
			map.put("employeeId", srch.getEmployeeId());
		}
		if (srch.getEmployeeName()!=null && srch.getEmployeeName().trim().length()>0) {	
			hql += " AND concat(concat(u.firstName,' '),u.lastName) like :employeeName";
			map.put("employeeName", "%"+srch.getEmployeeName()+"%");
		}
		if (srch.getLoginName()!=null && srch.getLoginName().trim().length()>0) {	
			hql += " AND u.loginName like :loginName";
			map.put("loginName", "%"+srch.getLoginName()+"%");
		}
		if(srch.getDeptId()!=null ) {
			hql += " AND u.deptId=:deptId";
			map.put("deptId", srch.getDeptId());
		}
		if (page.getOrder()!=null && page.getOrder().trim().length()>0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		page = this.findPage(page, hql, map);
		return page;
	}

    public Page<User> searchUserOther(Page<User> page, List<PropertyFilter> filters) {
        String hql = "from User where 1=1 ";
		Map<String, Object> map = new HashMap<String, Object>();
        for (PropertyFilter filter : filters) {
            if ("employeeName".equals(filter.getPropertyName())) {
                hql += " and employee.employeeName like :employeeName";
                map.put("employeeName", "%" + filter.getPropertyValue() + "%");
            }else if ("department".equals(filter.getPropertyName())) {
                hql += " and employee.department like :department";
                map.put("department", "%" + filter.getPropertyValue() + "%");
            }else if ("userId".equals(filter.getPropertyName())) {
                hql += " and userId = :userId";
                map.put("userId", Integer.valueOf(filter.getPropertyValue().toString()));
            }
        }
		if (page.getOrder()!=null && page.getOrder().trim().length()>0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		page = this.findPage(page, hql, map);
		return page;
	}
	
	public Page<User> searchUser(Page<User> page, List<PropertyFilter> filters) {
        String sql = "select {us.*} from system.users us,system.department department where us.dept_id=department.dept_Id";
        this.getSession().createSQLQuery(sql).addEntity("us", User.class);
		return findPage(page, filters);
	}

    public Page<User> searchClerkUser(Page<User> page, List<PropertyFilter> filters) {
        String sql = "select {us.*} from system.users us left join system.department department on us.dept_id=department.dept_Id where 1=1  ";
        String sql1 = "select us.user_id from system.users us left join system.department department on us.dept_id=department.dept_Id where 1=1  ";
        String cri = "";
        Map<String, Object> map = new HashMap<String, Object>();
        for(PropertyFilter filter : filters ){
            if("loginName".equals(filter.getPropertyName())){
                cri += " and us.login_name like '%"+ filter.getPropertyValue()+"%'";
            }else if("userId".equals(filter.getPropertyName())){
                cri += " and us.user_id ="+ filter.getPropertyValue();
            }else if("deptName".equals(filter.getPropertyName())){
                cri += " and department.name like '%"+ filter.getPropertyValue()+"%'";
            }
        }

        int pageIndex = page.getPageNo();
        int pageSize = page.getPageSize();
        //pageIndex 为当前页数，pageSize为页显示数
        final int items = (pageIndex-1) * pageSize;
        Query query = this.getSession().createSQLQuery(sql + cri).addEntity("us", User.class);
        //定义从第几条开始查询
        query.setFirstResult(items);
        //定义返回的纪录数
        query.setMaxResults(pageSize);

        String totalCount = this.getSession().createSQLQuery("select count(*) from (" + sql1 + cri + " ) tmp_count_t ").uniqueResult().toString();
        page.setTotalCount(Long.parseLong(totalCount));
        query.setMaxResults(pageSize);
        page.setResult(query.list());
        return page;
	}
	
	/**
	 * 通过登录名模糊查询用户
	 * @author zhangyong
	 * @param loginName
	 * @return
	 */
	public List<User> findUserByLoginName (String loginName) {
		return this.find(SELECT_USER_BY_LOGIN_NAME, "%" + loginName + "%");
	}
	
	/**
	 * 查询id和loginName
	 * @author zhangyong
	 * @param page
	 * @param loginName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page findProSupPage (Page page, String loginName) {
		String hql = "select distinct(a.projectSupportId), b.loginName from ProjectSupportAssignment a, User b where a.projectSupportId = b.userId ";
		Map<String,Object> map = new HashMap<String,Object>();
		if (loginName != null && !("").equals(loginName.trim())) {
			hql += " and b.loginName like:loginName";
			map.put("loginName", "%"+loginName+"%");
		}
		page = this.findPage(page, hql, map);
		Long totalRecord = findProSupPage(loginName);
		page.setTotalCount(totalRecord);
		return page;
	}
	
	public Long findProSupPage (String loginName) {
		String hql = "select count(distinct a.projectSupportId) from ProjectSupportAssignment a, User b where a.projectSupportId = b.userId ";
		Map<String,Object> map = new HashMap<String,Object>();
		if (loginName != null && !("").equals(loginName.trim())) {
			hql += " and b.loginName like:loginName";
			map.put("loginName", "%"+loginName+"%");
		}
		List<Object> list = this.find(hql, map);
		if (list != null && list.size() > 0) {
			return Long.parseLong(list.get(0).toString());
		}
		return 0l;
	}
	
	/**
	 * 通过登录名查询
	 * @author zhangyong
	 * @param loginName
	 * @return
	 */
	public User findByLoginName (String loginName) {
		String hql = "from User where loginName = ?";
		List<User> userlist = this.find(hql, loginName);
		if (userlist != null && userlist.size() > 0) {
			return userlist.get(0);
		}
		return null;
	}
	
	/**
	 * 通过主键查询
	 * @author zhangyong
	 * @param userId
	 * @return
	 */
	public User findByUserId (Integer userId) {
		return this.findUniqueBy("userId", userId);
	}
}
