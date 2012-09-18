package com.genscript.gsscm.systemsetting.service;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.EmailGroup;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.systemsetting.dao.EmailGroupDao;
import com.genscript.gsscm.systemsetting.dto.EmailGroupDTO;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 6/21/11
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class EmailGroupService {
     @Autowired
     private EmailGroupDao emailGroupDao;
     @Autowired
	private UserDao userDao;

    /**
	 * 根据条件查找ManufacturingClerks并分页显示
	 * @param mailGroupPage 分页对象
	 * @return
	 */
	public Page<EmailGroup> searchEmailGroupPage(Page<EmailGroup> mailGroupPage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<EmailGroup> pagerUtil = new PagerUtil<EmailGroup>();
		mailGroupPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!mailGroupPage.isOrderBySetted()) {
			mailGroupPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		mailGroupPage.setPageSize(15);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			mailGroupPage = this.emailGroupDao.getAll(mailGroupPage);
		}else {
			mailGroupPage = this.emailGroupDao.findPageByFilter(mailGroupPage, filters);
		}
		return mailGroupPage;
	}

    public EmailGroup findById(int id){
        EmailGroup emailGroup = emailGroupDao.get(id);
        return emailGroup;
    }

    public void saveEmailGroup(EmailGroup emailGroup) throws Exception{
        emailGroupDao.save(emailGroup);
    }

    public void deleteEmailGroup(String ids) throws Exception{
        for (String id : ids.split(",")) {
            emailGroupDao.batchDelEmaiGroup(Integer.valueOf(id));
        }
    }

    public EmailGroup getEmailGroupByName(String groupName){
        EmailGroup emailGroup = emailGroupDao.getEmailGroupByName(groupName);
        return emailGroup;
    }
}
