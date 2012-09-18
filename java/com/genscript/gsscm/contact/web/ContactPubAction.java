package com.genscript.gsscm.contact.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.contact.entity.ContactPublications;
import com.genscript.gsscm.contact.service.ContactService;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = "pub_list", location = "contact/contact_pub_list.jsp"),
		@Result(name = "pub_edit", location = "contact/contact_pub_edit.jsp"),
		@Result(name = "contact_pub_grant", location = "contact/contact_pub_grant.jsp") })
public class ContactPubAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6534283335416418911L;
	private String sessContactNo;
	private ContactPublications contactPub;
	private String pubKey;
	private Map<String, ContactPublications> pubsMap;// 用于返回列表页面时显示数据
	@Autowired
	private ContactService contactService;

	public String index() {
		return "contact_pub_grant";
	}

	/**
	 * 对session中的ContactPublications， 和数据库里查出来的ContactPublications进行合并，
	 * 具体算法参见SessionUtil; 把合并后的数据以Map形式返回给用户.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String list() {
		Map<String, ContactPublications> dbPubsMap = null;
		PagerUtil<ContactPublications> parmPager = new PagerUtil<ContactPublications>();
		Page<ContactPublications> page = parmPager.getRequestPage();
		// 设置默认排序
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null || page.getPageSize().intValue() < 1) {
			page.setPageSize(10);
		}
		// 如果sessContactNo为数字则取得数据中的Pubs, 否则数据库中的Pubs没有记录不用去查询
		if (StringUtils.isNumeric(this.sessContactNo)) {
			page = this.contactService.getPubsList(page, Integer
					.valueOf(sessContactNo));
			List<ContactPublications> dbPubsList = page.getResult();
			dbPubsMap = new LinkedHashMap<String, ContactPublications>();
			for (ContactPublications dto : dbPubsList) {
				dbPubsMap.put(dto.getId() + "", dto);
			}
		}
		// 取得session中的Pubs
		Map<String, ContactPublications> sessPubsMap = (Map<String, ContactPublications>) SessionUtil
				.getRow(SessionConstant.ContactPubs.value(), sessContactNo);
		pubsMap = SessionUtil.mergeList(sessPubsMap, dbPubsMap, page
				.getPageNo());
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		return "pub_list";
	}

	/**
	 * 新增或修改ContactPublications时调用, 应该传递pubKey, sessContactNo等属性过来.
	 * 
	 * @return
	 */
	public String edit() {
		System.out.println("Contact pubKey: " + this.pubKey);
		if (StringUtils.isBlank(this.pubKey)) {//新增时

		} else {//修改时
			Object obj = SessionUtil.getOneRow(SessionConstant.ContactPubs
					.value(), this.sessContactNo, this.pubKey);
			if (obj != null) {
				contactPub = (ContactPublications) obj;
			} else {
				contactPub = this.contactService.getPubs(Integer
						.valueOf(this.pubKey));
			}
		}
		return "pub_edit";
	}

	/**
	 * 保存ContactPublications时调用 应该传递pubKey,contactPub等值.此时只是暂存于session中，并没有操作数据库
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		System.out.println("save contactPub: " + contactPub);
		System.out.println("sessContactNo: " + sessContactNo);
		System.out.println("pubKey: " + pubKey);
		if (StringUtils.isBlank(this.pubKey)) {
			if (contactPub.getId() == null
					|| contactPub.getId().intValue() == 0) {
				pubKey = SessionUtil.generateTempId();
			} else {
				pubKey = contactPub.getId() + "";
			}
		}
		// 保存或更新Contact模块下的Contacts标签.
		SessionUtil.updateOneRow(SessionConstant.ContactPubs.value(),
				sessContactNo, pubKey, contactPub);
		return NONE;
	}

	/**
	 * 批量删除多个ContactPublications, 如果删除的是刚刚新增到session并没有提交数据库的，则直接从session中清除出去
	 * 如果删除的是数据库里已存在的，则修改该条记录在session中的对应值为null.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String delete() {
		System.out.println("sessContactNo===============" + sessContactNo);
		// 取得要删除的contact id字符串
		String delIdStr = ServletActionContext.getRequest().getParameter("pub_id_str");
		System.out.println("delIdStr: " + delIdStr);
		if (StringUtils.isNotEmpty(delIdStr)) {
			String[] delKeyArray = delIdStr.split(",");
			pubsMap = (Map<String, ContactPublications>) SessionUtil.getRow(
					SessionConstant.ContactPubs.value(), sessContactNo);
			if (pubsMap == null) {
				pubsMap = new HashMap<String, ContactPublications>();
				SessionUtil.insertRow(SessionConstant.ContactPubs.value(),
						sessContactNo, pubsMap);
			}
			for (int i = 0; i < delKeyArray.length; i++) {
				System.out.println("delKeyArray[i]: " + delKeyArray[i]);
				if (StringUtils.isNumeric(delKeyArray[i])) {
					pubsMap.put(delKeyArray[i], null);
				} else {
					pubsMap.remove(delKeyArray[i]);
				}
			}
			SessionUtil.updateRow(SessionConstant.ContactPubs.value(),
					sessContactNo, pubsMap);
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

	public Map<String, ContactPublications> getPubsMap() {
		return pubsMap;
	}

	public void setPubsMap(Map<String, ContactPublications> pubsMap) {
		this.pubsMap = pubsMap;
	}

	public ContactPublications getContactPub() {
		return contactPub;
	}

	public void setContactPub(ContactPublications contactPub) {
		this.contactPub = contactPub;
	}

	public String getPubKey() {
		return pubKey;
	}

	public void setPubKey(String pubKey) {
		this.pubKey = pubKey;
	}

}
