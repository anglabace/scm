package com.genscript.gsscm.contact.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.contact.entity.ContactGrants;
import com.genscript.gsscm.contact.service.ContactService;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = "grant_list", location = "contact/contact_grant_list.jsp"),
		@Result(name = "grant_edit", location = "contact/contact_grant_edit.jsp") })
public class ContactGrantAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6534283335416418911L;
	private String sessContactNo;
	private ContactGrants contactGrant;
	private String grantKey;
	private Map<String, ContactGrants> grantsMap;// 用于返回列表页面时显示数据
	private String exprDate;//用于接受页面String，再转为Date
	private String issueDate;//用于接受页面String，再转为Date
	@Autowired
	private ContactService contactService;

	public String index() {
		return "contact_pub_grant";
	}

	/**
	 * 对session中的ContactGrants， 和数据库里查出来的ContactGrants进行合并， 具体算法参见SessionUtil;
	 * 把合并后的数据以Map形式返回给用户.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String list() {
		System.out.println("sessContactNo===============" + sessContactNo);
		Map<String, ContactGrants> dbGrantsMap = null;
		PagerUtil<ContactGrants> parmPager = new PagerUtil<ContactGrants>();
		Page<ContactGrants> page = parmPager.getRequestPage();
		// 设置默认排序
		if (!page.isOrderBySetted()) {
			page.setOrderBy("grantId");
			page.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null || page.getPageSize().intValue() < 1) {
			page.setPageSize(10);
		}
		// 如果sessContactNo为数字则取得数据中的Grants, 否则数据库中的Grants没有记录不用去查询
		if (StringUtils.isNumeric(this.sessContactNo)) {
			page = this.contactService.getGrantsList(page, Integer
					.valueOf(sessContactNo));
			List<ContactGrants> dbGrantsList = page.getResult();
			dbGrantsMap = new LinkedHashMap<String, ContactGrants>();
			for (ContactGrants dto : dbGrantsList) {
				dbGrantsMap.put(dto.getGrantId() + "", dto);
			}
		}
		// 取得session中的Grants
		Map<String, ContactGrants> sessGrantsMap = (Map<String, ContactGrants>) SessionUtil
				.getRow(SessionConstant.ContactGrants.value(), sessContactNo);
		grantsMap = SessionUtil.mergeList(sessGrantsMap, dbGrantsMap, page
				.getPageNo());
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		return "grant_list";
	}

	/**
	 * 修改ContactGrants时调用 应该传递grantKey, sessContactNo等属性过来.
	 * 
	 * @return
	 */
	public String edit() {
		System.out.println("Contact grantKey: " + this.grantKey);
		if (StringUtils.isBlank(this.grantKey)) {//新增时

		} else {//修改时
			Object obj = SessionUtil.getOneRow(SessionConstant.ContactGrants
					.value(), this.sessContactNo, this.grantKey);
			if (obj != null) {
				contactGrant = (ContactGrants) obj;
			} else {
				contactGrant = this.contactService.getGrants(Integer
						.valueOf(this.grantKey));
			}
		}
		return "grant_edit";
	}

	/**
	 * 保存ContactGrants时调用 应该传递grantKey,contactGrant等值.此时只是暂存于session中，并没有操作数据库
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		System.out.println("save contactGrant: " + contactGrant);
		System.out.println("sessContactNo: " + sessContactNo);
		System.out.println("pubKey: " + grantKey);
		if (StringUtils.isBlank(this.grantKey)) {
			if (contactGrant.getGrantId() == null
					|| contactGrant.getGrantId().intValue() == 0) {
				grantKey = SessionUtil.generateTempId();
			} else {
				grantKey = contactGrant.getGrantId() + "";
			}
		}
		//把页面传来的字符串值转为Date;
		if (StringUtils.isNotBlank(this.exprDate)) {
			contactGrant.setExprDate(DateUtils.formatStr2Date(exprDate,
					"yyyy-MM-dd"));
		}
		//把页面传来的字符串值转为Date;
		if (StringUtils.isNotBlank(this.issueDate)) {
			contactGrant.setIssueDate(DateUtils.formatStr2Date(issueDate,
					"yyyy-MM-dd"));
		}
		if (StringUtils.isBlank(contactGrant.getState())) {
			contactGrant.setState(ServletActionContext.getRequest().getParameter("state"));
		}
		// 保存或更新Contact模块下的Contacts标签.
		SessionUtil.updateOneRow(SessionConstant.ContactGrants.value(),
				sessContactNo, grantKey, contactGrant);
		return NONE;
	}

	/**
	 * 批量删除多个ContactGrants, 如果删除的是刚刚新增到session并没有提交数据库的，则直接从session中清除出去
	 * 如果删除的是数据库里已存在的，则修改该条记录在session中的对应值为null.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String delete() {
		System.out.println("sessContactNo===============" + sessContactNo);
		// 取得要删除的contact id字符串
		String delIdStr = ServletActionContext.getRequest().getParameter("grant_id_str");
		System.out.println("delIdStr: " + delIdStr);
		if (StringUtils.isNotEmpty(delIdStr)) {
			String[] delKeyArray = delIdStr.split(",");
			grantsMap = (Map<String, ContactGrants>) SessionUtil.getRow(
					SessionConstant.ContactGrants.value(), sessContactNo);
			if (grantsMap == null) {
				grantsMap = new HashMap<String, ContactGrants>();
				SessionUtil.insertRow(SessionConstant.ContactGrants.value(),
						sessContactNo, grantsMap);
			}
			for (int i = 0; i < delKeyArray.length; i++) {
				System.out.println("delKeyArray[i]: " + delKeyArray[i]);
				if (StringUtils.isNumeric(delKeyArray[i])) {
					grantsMap.put(delKeyArray[i], null);
				} else {
					grantsMap.remove(delKeyArray[i]);
				}
			}
			SessionUtil.updateRow(SessionConstant.ContactGrants.value(),
					sessContactNo, grantsMap);
			Struts2Util.renderText("success");
		}
		return NONE;
	}

	public String getSessContactNo() {
		return sessContactNo;
	}

	public void setSessContactNo(String sessContactNo) {
		this.sessContactNo = sessContactNo;
	}

	public Map<String, ContactGrants> getGrantsMap() {
		return grantsMap;
	}

	public void setGrantsMap(Map<String, ContactGrants> grantsMap) {
		this.grantsMap = grantsMap;
	}

	public ContactGrants getContactGrant() {
		return contactGrant;
	}

	public void setContactGrant(ContactGrants contactGrant) {
		this.contactGrant = contactGrant;
	}

	public String getGrantKey() {
		return grantKey;
	}

	public void setGrantKey(String grantKey) {
		this.grantKey = grantKey;
	}

	public String getExprDate() {
		return exprDate;
	}

	public void setExprDate(String exprDate) {
		this.exprDate = exprDate;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

}
