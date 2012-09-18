package com.genscript.gsscm.serv.web;

import java.util.ArrayList;
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
import com.genscript.gsscm.common.constant.SessionPdtServ;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.serv.dto.PriceRuleGroupsDTO;
import com.genscript.gsscm.serv.entity.PriceRuleGroups;
import com.genscript.gsscm.serv.service.ServicePriceRuleService;
import com.genscript.gsscm.ws.WSException;

@Results({@Result(name = "success", location = "service/setups/price_rule_group_search_list.jsp"),
	      @Result(name = "editOrNew", location = "service/setups/edit_rule_group.jsp")
		})
public class PriceRuleGroupAction  extends BaseAction<PriceRuleGroupsDTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4568333284890029060L;

	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private ServicePriceRuleService priceRuleService;
	@Autowired
	private PublicService publicService;
	
	private Page<PriceRuleGroups> page;
	private PriceRuleGroupsDTO entity;
	private Integer groupId;
	private String sessionRuleGroupId;
	private List<DropDownDTO> dropDownDTO;
	
	private Integer clsId;//用于list页面数据查询参数
	
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	public PriceRuleGroupsDTO getModel() {
		/**
		 * 向跳转页面绑定ServiceAttributes类型的输出数据。
		 */
		return entity;
	}
	
	public PriceRuleGroupsDTO getEntity() {
		return entity;
	}
	
	public void setEntity(PriceRuleGroupsDTO entity){
		this.entity = entity;
	}

	@Override
	public String delete() throws Exception {
		Integer userId = SessionUtil.getUserId();
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			String dataStr = ServletActionContext.getRequest().getParameter("delStr");
			if(dataStr!=null){
				List<String> idList = Arrays.asList(dataStr.split(","));
				this.priceRuleService.delPriceRuleGroups(idList, userId);
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
		if(groupId==null){
			sessionRuleGroupId = SessionUtil.generateTempId();
		}else{
			sessionRuleGroupId = groupId.toString();
		}
		SessionUtil.deleteRow(SessionPdtServ.ServicePriceOfRuleGroup.value(), this.sessionRuleGroupId);
		dropDownDTO = publicService.getSpecDropDownList(SpecDropDownListName.SERVICE_CLASSIFICATION);
		return "editOrNew";
	}

	@Override
	public String list() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<PriceRuleGroups> pagerUtil = new PagerUtil<PriceRuleGroups>();
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
			page.setOrderBy("groupId");
			page.setOrder(Page.DESC);
		}
		page = this.priceRuleService.searchPriceRuleGroups(page, filters);
		dropDownDTO = publicService.getSpecDropDownList(SpecDropDownListName.SERVICE_CLASSIFICATION);
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(groupId!=null){
			entity = this.priceRuleService.getPriceRuleGroupsDetail(groupId);
		}else{
			entity = new PriceRuleGroupsDTO();
		}
	}

	@Override
	public String save() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		Integer userId = SessionUtil.getUserId();
		try{
			if(this.entity!=null){
				Object obj = SessionUtil.getRow(SessionPdtServ.ServicePriceOfRuleGroup.value(), this.sessionRuleGroupId);
				if(obj!=null){
					Map<String,String> priceRuleMap = (Map<String, String>) obj;
					List<Integer> priceRuleList = new ArrayList<Integer>();
					for(Map.Entry<String, String> entry: priceRuleMap.entrySet()){
						if(priceRuleMap.get(entry.getKey())!=null){
							Integer  priceRuleId = Integer.valueOf(priceRuleMap.get(entry.getKey()));
							priceRuleList.add(priceRuleId);
						}
					}
					SessionUtil.deleteRow(SessionPdtServ.ServicePriceOfRuleGroup.value(), sessionRuleGroupId);
					if(!priceRuleList.isEmpty()){
						entity.setAddPriceRuleList(priceRuleList);
					}
				}
				
				obj = SessionUtil.getRow(SessionPdtServ.ServiceDelPriceOfRuleGroup.value(), sessionRuleGroupId);
				if(obj !=null){
					List<Integer> delList = (List<Integer>)obj;
					if(!delList.isEmpty()){
						System.out.println("delList="+delList.size());
						entity.setDelPriceRuleList(delList);
					}
					SessionUtil.deleteRow(SessionPdtServ.ServiceDelPriceOfRuleGroup.value(), sessionRuleGroupId);
				}
			    this.priceRuleService.savePriceRulesGroup(entity, userId);
			    rt.put("id", entity.getPriceRuleGropus().getGroupId());
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
	
	public String savePriceRuleToRuleGroupSession(){
		Map<String, Object> rt = new HashMap<String, Object>();
		String priceRuleIds = ServletActionContext.getRequest().getParameter("priceRuleIds");
		String[] priceRuleId = priceRuleIds.split(",");
		for(String pr : priceRuleId){
			SessionUtil.updateOneRow(SessionPdtServ.ServicePriceOfRuleGroup.value(), this.sessionRuleGroupId, pr, pr);
		}
		rt.put("message", SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}
	
	public String delPriceRuleToruleGroupSession(){
		Map<String, Object> rt = new HashMap<String, Object>();
		String delStr = ServletActionContext.getRequest().getParameter("delStr");
		if(delStr!=null){
			List<Integer> delList;
			String[] del = delStr.split(",");
			Object obj = SessionUtil.getRow(SessionPdtServ.ServiceDelPriceOfRuleGroup.value(), this.sessionRuleGroupId);
			if(obj!=null){
				delList = (List<Integer>)obj;
			}else{
				delList = new ArrayList<Integer>();
			}
			for(int i=0;i<del.length;i++){
				if(SessionUtil.getOneRow(SessionPdtServ.ServicePriceOfRuleGroup.value(), sessionRuleGroupId, del[i])==null){
					delList.add(Integer.valueOf(del[i]));
				}else{
					SessionUtil.updateOneRow(SessionPdtServ.ServicePriceOfRuleGroup.value(), sessionRuleGroupId, del[i],null );
				}
			}
			if(!delList.isEmpty()){
				if(obj==null){
					SessionUtil.insertRow(SessionPdtServ.ServiceDelPriceOfRuleGroup.value(), sessionRuleGroupId, delList);
				}else{
					SessionUtil.updateRow(SessionPdtServ.ServiceDelPriceOfRuleGroup.value(), sessionRuleGroupId, delList);
				}
			}
			rt.put("message", SUCCESS);
		}else{
			rt.put("message", ERROR);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	public String searchGroupByServiceType(){
		Map<String, Object> rt = new HashMap<String, Object>();
		String clsId = ServletActionContext.getRequest().getParameter("clsId");
		if(clsId!=null){
			List<PriceRuleGroups> group = this.priceRuleService.searchPriceRuleGroupListByClsId(Integer.valueOf(clsId));
			rt.put("group", group);
			rt.put("message", SUCCESS);
		}else{
			rt.put("message", ERROR);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	public String clsIdChange(){
		Map<String, Object> rt = new HashMap<String, Object>();
		Object obj = SessionUtil.getRow(SessionPdtServ.ServicePriceOfRuleGroup.value(), sessionRuleGroupId);
		if(obj==null){
			rt.put("message", SUCCESS);
		}else{
			rt.put("message", ERROR);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	public Page<PriceRuleGroups> getPage() {
		return page;
	}

	public List<DropDownDTO> getDropDownDTO() {
		return dropDownDTO;
	}

	public void setDropDownDTO(List<DropDownDTO> dropDownDTO) {
		this.dropDownDTO = dropDownDTO;
	}

	public String getSessionRuleGroupId() {
		return sessionRuleGroupId;
	}

	public void setSessionRuleGroupId(String sessionRuleGroupId) {
		this.sessionRuleGroupId = sessionRuleGroupId;
	}

	public Integer getClsId() {
		return clsId;
	}

	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}
	
}
