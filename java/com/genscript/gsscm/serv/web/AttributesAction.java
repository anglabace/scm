package com.genscript.gsscm.serv.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.serv.entity.ServiceAttributes;
import com.genscript.gsscm.serv.service.ServiceAttributesService;
import com.genscript.gsscm.ws.WSException;

@Results( {@Result(name = "success", location = "service/setups/attributes_search_list.jsp"),
		   @Result(name = "editOrNew", location = "service/setups/edit_attributes.jsp")})
public class AttributesAction  extends BaseAction<ServiceAttributes>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2528181729175460399L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private ServiceAttributesService attributes;
	@Autowired
	private PublicService publicService;
	
	private ServiceAttributes entity;
	private Integer id;
	
	private Integer clsId;//用于list页面数据查询参数
	
	private Page<ServiceAttributes> page;
	
	private List<DropDownDTO> dropDownDTO;
	
	//获取从其他列表页面点过来的请求--Zhang Yong
	private String operation_method;
	
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public ServiceAttributes getModel() {
		/**
		 * 向跳转页面绑定ServiceAttributes类型的输出数据。
		 */
		return entity;
	}
	
	
	public ServiceAttributes getEntity() {
		return entity;
	}

	public void setEntity(ServiceAttributes entity) {
		this.entity = entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id!=null){
			entity = this.attributes.getAttributeDetailById(id);
		}else{
			entity = new ServiceAttributes();
		}
	}
	
	@Override
	public String delete() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			String dataStr = ServletActionContext.getRequest().getParameter("delStr");
			if(dataStr!=null){
				List<String> idList = Arrays.asList(dataStr.split(","));
				this.attributes.delAttribute(idList);
				rt.put("message",SUCCESS);
			}else{
				rt.put("message","This date is null!!!");
			}
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0203",
					SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);			
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	@Override
	public String input() throws Exception {
		if (id != null && !("").equals(id)) {
			//*********** Add By Zhang Yong Start *****************************//
			//判断将要修改的单号是否正在被操作
			String editUrl = "serv/attributes!input.action?id="+id;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
			}
			//*********** Add By Zhang Yong End *****************************//
		} else {
			//*********** Add By Zhang Yong Start *****************************//
			//释放application中的订单锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock(); 
			//*********** Add By Zhang Yong End *****************************//
		}
		dropDownDTO = publicService.getSpecDropDownList(SpecDropDownListName.SERVICE_CLASSIFICATION);
		return "editOrNew";
	}

	@Override
	public String list() throws Exception {
		String callBack = Struts2Util.getParameter("callBack");
		// 获得分页请求相关数据：如第几页
		PagerUtil<ServiceAttributes> pagerUtil = new PagerUtil<ServiceAttributes>();
		page = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null
				|| page.getPageSize().intValue() < 1) {
			page.setPageSize(20);
		}
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		if(clsId!=null&&!"".equals(clsId)){
			PropertyFilter filter = new PropertyFilter("EQI_clsId", clsId);
			filters.add(filter);
		}
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		page = this.attributes.searchAttributeList(page, filters);
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		dropDownDTO = publicService.getSpecDropDownList(SpecDropDownListName.SERVICE_CLASSIFICATION);
		if(callBack==null){
			return SUCCESS;
		}else{
			return callBack;
		}
	}

	@Override
	public String save() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		try{
			if(this.entity!=null){
				//*********** Add By Zhang Yong Start *****************************//
				//校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
				if (entity.getId() != null && entity.getId() != 0) {
					String editUrl = "serv/attributes!input.action?id="+entity.getId();
					OrderLockRelease orderLockRelease = new OrderLockRelease();
					String byUser = orderLockRelease.checkOrderStatus(editUrl);
					if (byUser != null) {
						operation_method = byUser;
						rt.put("message", SUCCESS);
						Struts2Util.renderJson(rt);
						return null;
					}
				}
				//*********** Add By Zhang Yong End *****************************//	
				this.attributes.saveAttribute(entity);
				rt.put("message", SUCCESS);
			}else{
				rt.put("message", "This date is null!!");
			}
		}catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0203",
					SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);	
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	public Page<ServiceAttributes> getPage() {
		return page;
	}

	public List<DropDownDTO> getDropDownDTO() {
		return dropDownDTO;
	}

	public void setDropDownDTO(List<DropDownDTO> dropDownDTO) {
		this.dropDownDTO = dropDownDTO;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operationMethod) {
		operation_method = operationMethod;
	}

	public Integer getClsId() {
		return clsId;
	}

	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}

}
