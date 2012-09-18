package com.genscript.gsscm.serv.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.SessionPdtServ;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.serv.dto.PriceFormulaCriteriasDTO;
import com.genscript.gsscm.serv.dto.PriceFormulaDTO;
import com.genscript.gsscm.serv.dto.PriceFormulaItemDTO;
import com.genscript.gsscm.serv.dto.PriceRuleAttrValueMappingDTO;
import com.genscript.gsscm.serv.dto.PriceRulesDTO;
import com.genscript.gsscm.serv.entity.PriceFormulaCriterias;
import com.genscript.gsscm.serv.entity.PriceFormulaItems;
import com.genscript.gsscm.serv.entity.PriceRuleAttrValueMapping;
import com.genscript.gsscm.serv.entity.PriceRules;
import com.genscript.gsscm.serv.entity.ServiceAttributes;
import com.genscript.gsscm.serv.service.ServiceAttributesService;
import com.genscript.gsscm.serv.service.ServicePriceRuleService;
import com.genscript.gsscm.ws.WSException;

@Results( {@Result(name = "success", location = "service/setups/price_rule_search_list.jsp"),
	       @Result(name = "getPriceRuleDetail", location = "service/setups/edit_rule.jsp"),
	       @Result(name = "formulaInput", location = "service/setups/formula_edit.jsp"),
	       @Result(name = "formulaItemIput", location = "service/setups/formula_item.jsp"),
	       @Result(name = "choosePriceRuleOfRuleGroup", location = "service/setups/rule_group_choosePriceRule.jsp")
	       })
public class PriceRuleAction extends BaseAction<PriceRulesDTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private ServicePriceRuleService priceRuleService;
	@Autowired
	private ServiceAttributesService attributesService;
	@Autowired
	private PublicService publicService;
	
	private PriceRulesDTO entity;
	private Integer id;
	private String sessionPriceRuleId;
	private String sessionFormulaItemId;
	
	private PriceFormulaDTO priceFormulasDTO;
	private PriceRules priceRules;
	private List<PriceFormulaItemDTO> priceFormulaItemsList;
	
	private List<ServiceAttributes> attributesList;
	private PriceRuleAttrValueMapping attriMapping;
	private List<PriceRuleAttrValueMapping> attriMappingList;
	
	private String ruleType;//用于list页面数据查询参数
	private Integer clsId;//用于list页面数据查询参数
	
	private PriceFormulaItems priceFormulaItem;
	private PriceFormulaCriterias priceFormulaCriterias;
	
	private Page<PriceRules> page;
	
	private List<DropDownDTO> dropDownDTO;
	private List<PbDropdownListOptions> optionItemList;
	private List<PbDropdownListOptions> operationItemList;
	private String operation_method;
	
	private String sessionRuleGroupId;
	
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public PriceRulesDTO getModel() {
		/**
		 * 向跳转页面绑定PriceRulesDTO类型的输出数据。
		 */
		return entity;
	}
	
	
	public PriceRulesDTO getEntity() {
		return entity;
	}

	public void setEntity(PriceRulesDTO entity) {
		this.entity = entity;
	}
	
	@Override
	protected void prepareModel() throws Exception {
		if(id!=null){
			entity = this.priceRuleService.getPriceRuleById(id);
		}else{
			entity = new PriceRulesDTO();
		}
	}
	
	@Override
	public String delete() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			String dataStr = ServletActionContext.getRequest().getParameter("delStr");
			if(dataStr!=null){
				List<String> idList = Arrays.asList(dataStr.split(","));
				this.priceRuleService.delPriceRules(idList);
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
		Object obj = null;
		if(sessionPriceRuleId!=null){ 
		   	 obj = SessionUtil.getRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId);
		   	 if(obj!=null){
		   		PriceRulesDTO priceRulesDTO  = (PriceRulesDTO) obj;
		   		if(priceRulesDTO.getPriceRules()!=null){
		   			entity.setPriceRules(priceRulesDTO.getPriceRules());
		   			for(int i=0;i<priceRulesDTO.getDelPriceAttr().size();i++){
		   				for(int j=0;j<entity.getPriceAttrValMapDTOList().size();j++){
		   					if(priceRulesDTO.getDelPriceAttr().get(i).equals(entity.getPriceAttrValMapDTOList().get(j).getPriceRuleValue().getId())){
		   						entity.getPriceAttrValMapDTOList().remove(j);
		   					}
		   				}
		   			}
		   			for(int i=0;i<priceRulesDTO.getPriceAttrValMapDTOList().size();i++){
		   				if(priceRulesDTO.getPriceAttrValMapDTOList().get(i).getPriceRuleValue()!=null){
		   					String isAdd= "0";
		   					for(int k=0;k<entity.getPriceAttrValMapDTOList().size();k++){
		   						if(entity.getPriceAttrValMapDTOList().get(k).getPriceRuleValue().getId()!=null&&priceRulesDTO.getPriceAttrValMapDTOList().get(i).getSessionId().equals(entity.getPriceAttrValMapDTOList().get(k).getPriceRuleValue().getId().toString())){
		   							entity.getPriceAttrValMapDTOList().set(k,priceRulesDTO.getPriceAttrValMapDTOList().get(i));
		   							isAdd="1";
		   						}
		   					}
		   					if(isAdd.equals("0")){
		   						priceRulesDTO.getPriceAttrValMapDTOList().get(i).getPriceRuleValue().setId(null);
		   						entity.getPriceAttrValMapDTOList().add(priceRulesDTO.getPriceAttrValMapDTOList().get(i));
		   					}
		   				}
		   			}
		   			
		   			for(int i=0;i<priceRulesDTO.getDelPriceForms().size();i++){
		   				for(int j=0;j<entity.getPriceFormulasDTOList().size();j++){
		   					if(priceRulesDTO.getDelPriceForms().get(i).equals(entity.getPriceFormulasDTOList().get(j).getPriceFormulas().getId())){
		   						entity.getPriceFormulasDTOList().remove(j);
		   					}
		   				}
		   			}
		   			for(int j=0;j<priceRulesDTO.getPriceFormulasDTOList().size();j++){
		   				if(priceRulesDTO.getPriceFormulasDTOList().get(j).getPriceFormulas()!=null){
		   					String isAdd= "0";
		   					for(int k=0;k<entity.getPriceFormulasDTOList().size();k++){
			   					if(entity.getPriceFormulasDTOList().get(k).getPriceFormulas().getId()!=null&&priceRulesDTO.getPriceFormulasDTOList().get(j).getSessionId().equals(entity.getPriceFormulasDTOList().get(k).getPriceFormulas().getId().toString())){
			   						entity.getPriceFormulasDTOList().set(k, priceRulesDTO.getPriceFormulasDTOList().get(j));
			   						isAdd="1";
			   					}
			   				}
		   					if(isAdd.equals("0")){
		   						priceRulesDTO.getPriceFormulasDTOList().get(j).getPriceFormulas().setId(null);
		   						entity.getPriceFormulasDTOList().add(priceRulesDTO.getPriceFormulasDTOList().get(j));
		   					}
		   				}
		   			}
		   		}
		   		
		   	 }
		}else{
			if(id==null){
				sessionPriceRuleId=SessionUtil.generateTempId();
				//*********** Add By Zhang Yong Start *****************************//
				//释放application中的订单锁
				OrderLockRelease realeseOrderLock = new OrderLockRelease();
				realeseOrderLock.releaseOrderLock(); 
				//*********** Add By Zhang Yong End *****************************//
			}else{
				sessionPriceRuleId = id.toString();
				//*********** Add By Zhang Yong Start *****************************//
				//判断将要修改的单号是否正在被操作
				String editUrl = "serv/price_rule!input.action?id="+id;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
				}
				//*********** Add By Zhang Yong End *****************************//
			}
			SessionUtil.deleteRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId);
			for(int i=0;i<entity.getPriceFormulasDTOList().size();i++){
				SessionUtil.deleteRow(SessionPdtServ.Criterias.value(), entity.getPriceFormulasDTOList().get(i).getPriceFormulas().getId().toString());
				SessionUtil.deleteRow(SessionPdtServ.DelCriterias.value(),  entity.getPriceFormulasDTOList().get(i).getPriceFormulas().getId().toString());
				SessionUtil.deleteRow(SessionPdtServ.DelPriceFormulaItem.value(), entity.getPriceFormulasDTOList().get(i).getPriceFormulas().getId().toString());
				SessionUtil.deleteRow(SessionPdtServ.PriceFormulaItem.value(),  entity.getPriceFormulasDTOList().get(i).getPriceFormulas().getId().toString());
			}
		}
		dropDownDTO = publicService.getSpecDropDownList(SpecDropDownListName.SERVICE_CLASSIFICATION);
		if(id!=null||(entity.getPriceRules()!=null&&entity.getPriceRules().getClsId()!=null)){
			this.attributesList = this.attributesService.getAttributeListByclsId(entity.getPriceRules().getClsId());
		}else{
			if(dropDownDTO!=null){
				this.attributesList = this.attributesService.getAttributeListByclsId(Integer.valueOf(dropDownDTO.get(0).getId()));	
			}else{
				this.attributesList = null;
			}
		}
		if(obj==null){
			PriceRulesDTO priceRulesDTO = new PriceRulesDTO();
			priceRulesDTO.setPriceRules(entity.getPriceRules());
			SessionUtil.insertRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId, priceRulesDTO);
		}
		optionItemList = this.publicService.getDropdownList(SpecDropDownListName.SERVICE_ATTRIBUTE_MAPPING_OPERATOR.value());
		return "getPriceRuleDetail";
	}

	public String priceRuleOfpriceRuleGroup(){
		String callBackName =  ServletActionContext.getRequest().getParameter("callBackName");
		String groupId =  ServletActionContext.getRequest().getParameter("groupId");
		// 获得分页请求相关数据：如第几页
		PagerUtil<PriceRules> pagerUtil = new PagerUtil<PriceRules>();
		page = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null
				|| page.getPageSize().intValue() < 1) {
			page.setPageSize(10);
		}
		if (!page.isOrderBySetted()) {
			page.setOrderBy("modifyDate");
			page.setOrder(Page.DESC);
		}
		page = this.priceRuleService.searchPriceRuleListByGroupId(page, clsId,groupId);
		/*
		 * 在rule group 中增加price rule 使用下面代码；
		 */
		Object obj = SessionUtil.getRow(SessionPdtServ.ServicePriceOfRuleGroup.value(), sessionRuleGroupId);
		if(obj!=null){
			Map<String,String> priceRuleMap = (Map<String, String>) obj;
			for(Map.Entry<String, String> entry: priceRuleMap.entrySet()){
				for(int i =0;i<page.getResult().size();i++){
					if(page.getResult().get(i).getId().toString().equals(priceRuleMap.get(entry.getKey()))){
						page.getResult().remove(i);
					}
				}
			}
		}
		/*
		 * end
		 */
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		dropDownDTO = publicService.getSpecDropDownList(SpecDropDownListName.SERVICE_CLASSIFICATION);
		return callBackName;
	}
	
	@Override
	public String list() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<PriceRules> pagerUtil = new PagerUtil<PriceRules>();
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
		if(ruleType!=null&&!"".equals(ruleType)){
			PropertyFilter filter = new PropertyFilter("EQS_ruleType", ruleType);
			filters.add(filter);
		}
		if (!page.isOrderBySetted()) {
			page.setOrderBy("modifyDate");
			page.setOrder(Page.DESC);
		}
		page = this.priceRuleService.searchPriceRuleList(page, filters);
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		dropDownDTO = publicService.getSpecDropDownList(SpecDropDownListName.SERVICE_CLASSIFICATION);
		
		return SUCCESS;
		
	}

	/*
	 * add Service Attribute Mapping to session
	 * @param PriceRuleAttrValueMapping
	 */
	public String addPriceRuleAttrValueMappingSession(){
		Map<String, Object> rt = new HashMap<String, Object>();
		String mappingKey;
		try {
			mappingKey = SessionUtil.generateTempId();
			Object obj = SessionUtil.getRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId);
		   	if(obj!=null){
		   		String attributerName = ServletActionContext.getRequest().getParameter("attributerName");
		   		PriceRulesDTO dto = (PriceRulesDTO)obj;
		   		PriceRuleAttrValueMappingDTO mappDTO = new PriceRuleAttrValueMappingDTO();
		   		mappDTO.setPriceRuleValue(attriMapping);
		   		mappDTO.setSessionId(mappingKey);
		   		mappDTO.setAttributerName(attributerName);
		   		dto.getPriceAttrValMapDTOList().add(mappDTO);
		   		SessionUtil.updateRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId, dto);
		   		rt.put("message", SUCCESS);
				rt.put("id", mappingKey);
		   	}else{
		   		rt.put("message", "Session is Expired!!!");
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
	
	/*
	 * del Service Attribute Mapping to session
	 */
	public String delPriceRuleAttrValueMappingSession(){
		Map<String, Object> rt = new HashMap<String, Object>();
		String dataStr = ServletActionContext.getRequest().getParameter("delStr");
		if(dataStr!=null){
			String[] del = dataStr.split(",");
			Object obj = SessionUtil.getRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId);
			List<Integer> delList;
			if(obj!=null){
				PriceRulesDTO dto = (PriceRulesDTO)obj;
				if(dto.getDelPriceAttr()!=null){
					delList = dto.getDelPriceAttr();
				}else{
					delList = new ArrayList<Integer>();
				}
				for(int i=0;i<del.length;i++){
					String isNull = "0";
					for(int j =0;j<dto.getPriceAttrValMapDTOList().size();j++){
						if(del[i].equals(dto.getPriceAttrValMapDTOList().get(j).getSessionId())){
							dto.getPriceAttrValMapDTOList().get(j).setPriceRuleValue(null);
							isNull="1";
						}
					}
					if(isNull.equals("0")){
						delList.add(Integer.valueOf(del[i]));
					}
				}
				if(!delList.isEmpty()){
					SessionUtil.updateRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId, dto);
				}
				rt.put("message", SUCCESS);
			}else{
				rt.put("message", "Session is Expired!!!");
			}
		}else{
			rt.put("message", ERROR);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.genscript.gsscm.common.web.BaseAction#save()
	 */
	
	@Override
	public String save() throws Exception {
		Integer userId = SessionUtil.getUserId();
		Map<String, Object> rt = new HashMap<String, Object>();
		try{
			//*********** Add By Zhang Yong Start *****************************//
			//校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
			if (sessionPriceRuleId != null && !("").equals(sessionPriceRuleId)) {
				String editUrl = "serv/price_rule!input.action?id="+sessionPriceRuleId;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
					rt.put("message", "Save fail,the price_rule is editing by "+operation_method);
					rt.put("id", entity.getPriceRules().getId());
					SessionUtil.deleteRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId);
					Struts2Util.renderJson(rt);
					return null;
				}
			}
			//*********** Add By Zhang Yong End *****************************//	
			Object obj = SessionUtil.getRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId);
			if(obj!=null){
				entity = (PriceRulesDTO) obj;
			}
			entity.setPriceRules(priceRules);
			this.priceRuleService.savePriceRule(entity,userId);
			rt.put("message", SUCCESS);
			rt.put("id", entity.getPriceRules().getId());
			SessionUtil.deleteRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId);
			//*********** Add By Zhang Yong Start *****************************//
			//释放同步锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock(); 
			//*********** Add By Zhang Yong End *****************************//	
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

	
	/*
	 * formula input
	 */
	public String formulaInput() throws Exception{
		String formulasKey;
		String formulasId = ServletActionContext.getRequest().getParameter("formulasId");
		Pattern pattern = Pattern.compile("[0-9]*");
		if(formulasId==null||"".equals(formulasId)||!pattern.matcher(formulasId).matches()){
			this.priceFormulasDTO = new PriceFormulaDTO();
		}else{
			this.priceFormulasDTO = this.priceRuleService.getFormulaDetail(Integer.valueOf(formulasId));
		}
		Object obj = SessionUtil.getRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId);
		if(obj!=null){
			PriceFormulaDTO dto = new PriceFormulaDTO();
			entity = (PriceRulesDTO) obj;
			for(int i=0;i<entity.getPriceFormulasDTOList().size();i++){
				if(entity.getPriceFormulasDTOList().get(i).getSessionId().equals(formulasId)){
					dto = entity.getPriceFormulasDTOList().get(i);
					
				}
			}
			entity.setPriceRules(priceRules);
			clsId = entity.getPriceRules().getClsId();
			SessionUtil.updateRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId, entity);
			if(dto!=null){
				if(dto.getPriceFormulas()!=null){
					this.priceFormulasDTO.setPriceFormulas(dto.getPriceFormulas());
					this.priceFormulasDTO.setCreatedByText(dto.getCreatedByText());
					this.priceFormulasDTO.setModifiedByText(dto.getModifiedByText());
				}
				
				for(PriceFormulaCriteriasDTO cDTO :dto.getPriceFormlaCriteriasDTO()){
					if(cDTO.getPriceFormulasCriterias()!=null){
						this.priceFormulasDTO.getPriceFormlaCriteriasDTO().add(cDTO);
					}
				}
				for(int i=0;i<dto.getDelPriceFormlaCriterias().size();i++){
	   				for(int j=0;j<priceFormulasDTO.getPriceFormlaCriteriasDTO().size();j++){
	   					if(dto.getDelPriceFormlaCriterias().get(i).equals(priceFormulasDTO.getPriceFormlaCriteriasDTO().get(j).getPriceFormulasCriterias().getId())){
	   						priceFormulasDTO.getPriceFormlaCriteriasDTO().remove(j);
	   					}
	   				}
	   			}
			}
		}
		if(priceFormulasDTO.getPriceFormulas()!=null){
			if(priceFormulasDTO.getPriceFormulas().getId()!=null){
				formulasKey = priceFormulasDTO.getPriceFormulas().getId().toString();
			}else if(formulasId!=null){
				formulasKey = formulasId;
			}else{
				formulasKey = SessionUtil.generateTempId();
			}
			
		}else{
			formulasKey = SessionUtil.generateTempId();
		}
		this.priceFormulasDTO.setSessionId(formulasKey);
		this.sessionFormulaItemId = formulasKey;
		/*SessionUtil.deleteRow(SessionPdtServ.PriceFormulaItem.value(),sessionFormulaItemId);
		SessionUtil.deleteRow(SessionPdtServ.DelPriceFormulaItem.value(),sessionFormulaItemId);
		SessionUtil.deleteRow(SessionPdtServ.Criterias.value(),sessionFormulaItemId);
		SessionUtil.deleteRow(SessionPdtServ.DelCriterias.value(),sessionFormulaItemId);*/
		attributesList = this.attributesService.getAttributeListByclsId(clsId);
		if(attributesList!=null&&!attributesList.isEmpty()&&attributesList.get(0)!=null&&attributesList.get(0).getId()!=null){
			if(pattern.matcher(sessionPriceRuleId).matches()){
				this.attriMappingList = this.priceRuleService.searchPriceRuleAttrValueMappingByAttributeIdAndRuleId(Integer.valueOf(sessionPriceRuleId),attributesList.get(0).getId());
			}
		}
		if(obj!=null){
			PriceRulesDTO dto = (PriceRulesDTO) obj;
			for(PriceRuleAttrValueMappingDTO mappingDTO : dto.getPriceAttrValMapDTOList()){
				if(mappingDTO!=null&&mappingDTO.getPriceRuleValue()!=null&&attributesList!=null&&!attributesList.isEmpty()&&attributesList.get(0).getId().equals(mappingDTO.getPriceRuleValue().getAttributeId())){
					attriMappingList.add(mappingDTO.getPriceRuleValue());
				}
			}
		}
		return "formulaInput";
	}
	
	/*
	 * del session PriceFormulaItem and DelPriceFormulaItem
	 */
	public String delItemSession(){
		Map<String, Object> rt = new HashMap<String, Object>();
		SessionUtil.deleteRow(SessionPdtServ.PriceFormulaItem.value(),sessionFormulaItemId);
		SessionUtil.deleteRow(SessionPdtServ.DelPriceFormulaItem.value(),sessionFormulaItemId);
		rt.put("message",SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/*
	 * save formula to session
	 */
	@SuppressWarnings("unchecked")
	public String saveFormulaToSession(){
		Map<String, Object> rt = new HashMap<String, Object>();
		String formulasKey = ServletActionContext.getRequest().getParameter("formulasKey");
		try {
	   		Object obj1 = SessionUtil.getRow(SessionPdtServ.PriceFormulaItem.value(), sessionFormulaItemId);
	   		if(obj1!=null){
	   			Map<String,PriceFormulaItems> priceFormulaItemsMap = (Map<String, PriceFormulaItems>) obj1;
	   			for(Map.Entry<String, PriceFormulaItems> entry: priceFormulaItemsMap.entrySet()){
	   				PriceFormulaItems priceFormulaItems = priceFormulaItemsMap.get(entry.getKey());
	   				if(priceFormulaItems!=null){
	   					priceFormulasDTO.getPriceFormulaItems().add(priceFormulaItems);
	   				}
	   			}
	   		}
	   		obj1 = SessionUtil.getRow(SessionPdtServ.DelPriceFormulaItem.value(), sessionFormulaItemId);
	   		if(obj1!=null){
	   			List<Integer> delPdtList = (List<Integer>)obj1;
				if(!delPdtList.isEmpty()){
					priceFormulasDTO.setDelPriceFormulaItems(delPdtList);
				}
	   		}
	   	    obj1 = SessionUtil.getRow(SessionPdtServ.Criterias.value(), sessionFormulaItemId);
	   		if(obj1!=null){
	   			Map<String,PriceFormulaCriterias> priceFormulaCriteriasMap = (Map<String, PriceFormulaCriterias>) obj1;
	   			for(Map.Entry<String, PriceFormulaCriterias> entry: priceFormulaCriteriasMap.entrySet()){
	   				PriceFormulaCriterias priceFormulaCriterias = priceFormulaCriteriasMap.get(entry.getKey());
	   				if(priceFormulaCriterias!=null){
	   					PriceFormulaCriteriasDTO dto = new PriceFormulaCriteriasDTO();
	   					dto.setPriceFormulasCriterias(priceFormulaCriterias);
	   					priceFormulasDTO.getPriceFormlaCriteriasDTO().add(dto);
	   				}
	   			}
	   		}
	   		obj1 = SessionUtil.getRow(SessionPdtServ.DelCriterias.value(), sessionFormulaItemId);
	   		if(obj1!=null){
	   			List<Integer> delPdtList = (List<Integer>)obj1;
				if(!delPdtList.isEmpty()){
					priceFormulasDTO.setDelPriceFormlaCriterias(delPdtList);
				}
	   		}
			Object obj = SessionUtil.getRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId);
		   	if(obj!=null){
		   		String isAdd = "0";
		   		PriceRulesDTO dto = (PriceRulesDTO)obj;
		   		if(dto.getPriceFormulasDTOList().size()>0){
		   			System.out.println("dtosize="+dto.getPriceFormulasDTOList().get(0).getPriceFormlaCriteriasDTO().size());
		   		}
		   		for(int i=0;i<dto.getPriceFormulasDTOList().size();i++){
		   			System.out.println("dto.sessionId="+dto.getPriceFormulasDTOList().get(i).getSessionId());
		   			System.out.println("priceFormulasDTO.getSessionId()="+priceFormulasDTO.getSessionId());
			   		if(dto.getPriceFormulasDTOList().get(i).getSessionId().equals(priceFormulasDTO.getSessionId())){
			   			dto.getPriceFormulasDTOList().get(i).setPriceFormulas(priceFormulasDTO.getPriceFormulas());
			   			dto.getPriceFormulasDTOList().get(i).setSessionId(formulasKey);
			   			isAdd = "1";
			   		}
			   	}
		   		if(isAdd.equals("0")){
		   			priceFormulasDTO.setSessionId(formulasKey);
		   			dto.getPriceFormulasDTOList().add(priceFormulasDTO);
		   		}
		   		System.out.println("dtosize="+dto.getPriceFormulasDTOList().get(0).getPriceFormlaCriteriasDTO().size());
		   		SessionUtil.updateRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId, dto);
		   		rt.put("message", SUCCESS);
				rt.put("id", formulasKey);
		   	}else{
		   		rt.put("message", "Session is Expired!!!");
		   	}
		   	if(obj!=null){
		   		entity = (PriceRulesDTO) obj;
		   	}
		   	System.out.println("size="+priceFormulasDTO.getPriceFormlaCriteriasDTO().size());
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
	
	/*
	 * del Service formula to session
	 */
	public String delFormulaSession(){
		Map<String, Object> rt = new HashMap<String, Object>();
		String dataStr = ServletActionContext.getRequest().getParameter("delStr");
		if(dataStr!=null){
			String[] del = dataStr.split(",");
			Object obj = SessionUtil.getRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId);
			List<Integer> delList;
			if(obj!=null){
				PriceRulesDTO dto = (PriceRulesDTO)obj;
				if(dto.getDelPriceForms()!=null){
					delList = dto.getDelPriceForms();
				}else{
					delList = new ArrayList<Integer>();
				}
				for(int i=0;i<del.length;i++){
					String isNull = "0";
					for(int j =0;j<dto.getPriceFormulasDTOList().size();j++){
						if(del[i].equals(dto.getPriceFormulasDTOList().get(j).getSessionId())){
							if(dto.getPriceFormulasDTOList().get(j).getPriceFormulas().getId()!=null){
								delList.add(Integer.valueOf(del[i]));
								isNull="1";
							}
							dto.getPriceFormulasDTOList().get(j).setPriceFormulas(null);
						}
					}
					if(isNull.equals("0")){
						delList.add(Integer.valueOf(del[i]));
					}
				}
				if(!delList.isEmpty()){
					SessionUtil.updateRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId, dto);
				}
				rt.put("message", SUCCESS);
			}else{
				rt.put("message", "Session is Expired!!!");
			}
		}else{
			rt.put("message", ERROR);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/*
	 * add criteria to session  
	 */
	public String addCriteriaSession(){
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			String itemKey= SessionUtil.generateTempId();
			SessionUtil.updateOneRow(SessionPdtServ.Criterias.value(), this.sessionFormulaItemId, itemKey, priceFormulaCriterias);
			rt.put("message", SUCCESS);
			rt.put("id", itemKey);
		} catch (Exception e) {
			rt.put("message", ERROR);
			e.printStackTrace();
		}
		 Object obj1 = SessionUtil.getRow(SessionPdtServ.Criterias.value(), sessionFormulaItemId);
	   		if(obj1!=null){
	   			Map<String,PriceFormulaCriterias> priceFormulaCriteriasMap = (Map<String, PriceFormulaCriterias>) obj1;
	   			for(Map.Entry<String, PriceFormulaCriterias> entry: priceFormulaCriteriasMap.entrySet()){
	   				PriceFormulaCriterias priceFormulaCriterias = priceFormulaCriteriasMap.get(entry.getKey());
	   				if(priceFormulaCriterias!=null){
	   					PriceFormulaCriteriasDTO dto = new PriceFormulaCriteriasDTO();
	   					dto.setPriceFormulasCriterias(priceFormulaCriterias);
	   				}
	   			}
	   		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/*
	 * del Service Criteria to session
	 */
	@SuppressWarnings("unchecked")
	public String delCriteriaSession(){
		Map<String, Object> rt = new HashMap<String, Object>();
		String dataStr = ServletActionContext.getRequest().getParameter("delStr");
		if(dataStr!=null){
			String[] del = dataStr.split(",");
			Object obj = SessionUtil.getRow(SessionPdtServ.DelCriterias.value(), sessionFormulaItemId);
			List<Integer> delList;
			if(obj!=null){
				delList = (List<Integer>)obj;
			}else{
				delList = new ArrayList<Integer>();
			}
			for(int i=0;i<del.length;i++){
				if(SessionUtil.getOneRow(SessionPdtServ.Criterias.value(), sessionFormulaItemId, del[i])==null){
					delList.add(Integer.valueOf(del[i]));
				}else{
					Object map = SessionUtil.getOneRow(SessionPdtServ.Criterias.value(), sessionFormulaItemId, del[i]);
					PriceFormulaItems item = (PriceFormulaItems)map;
					if(item.getId()==null){
						SessionUtil.updateOneRow(SessionPdtServ.Criterias.value(), sessionFormulaItemId, del[i],null );
					}else{
						delList.add(Integer.valueOf(del[i]));
					}
				}
			}
			if(!delList.isEmpty()){
				if(obj==null){
					SessionUtil.insertRow(SessionPdtServ.DelCriterias.value(), sessionFormulaItemId, delList);
				}else{
					SessionUtil.updateRow(SessionPdtServ.DelCriterias.value(), sessionFormulaItemId, delList);
				}
			}
			Object obj1 = SessionUtil.getRow(SessionPdtServ.DelCriterias.value(), sessionFormulaItemId);
	   		if(obj1!=null){
	   			List<Integer> delPdtList = (List<Integer>)obj1;
				if(!delPdtList.isEmpty()){
					System.out.println(delPdtList.get(0));
				}
	   		}
			rt.put("message", SUCCESS);
		}else{
			rt.put("message", ERROR);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	/*
	 * Service formula item;
	 */
	public String formulaItemIput(){
		Object obj = SessionUtil.getRow(SessionPdtServ.PriceFormulaItem.value(), this.sessionFormulaItemId);
		if(obj!=null){
			this.priceFormulaItemsList = new ArrayList<PriceFormulaItemDTO>();
			Map<String,PriceFormulaItems> priceFormulaItemsMap = (Map<String, PriceFormulaItems>) obj;
			for(Map.Entry<String, PriceFormulaItems> entry: priceFormulaItemsMap.entrySet()){
				if(priceFormulaItemsMap.get(entry.getKey())!=null){
					PriceFormulaItemDTO dto = new PriceFormulaItemDTO();
					dto.setPriceFormulaItem(priceFormulaItemsMap.get(entry.getKey()));
					if(dto.getPriceFormulaItem().getType().equals("Value")){
						ServiceAttributes att =this.attributesService.getAttributeDetailById(Integer.valueOf(dto.getPriceFormulaItem().getValue()));
						if(att!=null){
							dto.setValueName(att.getAttributeName());
						}
					}
					List<PriceFormulaItemDTO> dtoList = new ArrayList<PriceFormulaItemDTO>();
					String isFla = "0";
					for(int i=0;i<priceFormulaItemsList.size();i++){
						if(isFla.equals("0")){
							if(priceFormulaItemsList.get(i).getPriceFormulaItem().getSeqNo()<dto.getPriceFormulaItem().getSeqNo()){
								dtoList.add(priceFormulaItemsList.get(i));
							}else{
								dtoList.add(dto);
								dtoList.add(priceFormulaItemsList.get(i));
								isFla = "1";
							}
						}else{
							dtoList.add(priceFormulaItemsList.get(i));
						}
					}
					if(isFla.equals("0")){
						dtoList.add(dto);
					}
					this.priceFormulaItemsList=dtoList;
				}
			}
		}else{
			this.priceFormulaItemsList = this.priceRuleService.searchPriceFormulaItemsByFormulaId(id);
			if(priceFormulaItemsList!=null&&!priceFormulaItemsList.isEmpty()){
				Map<String,PriceFormulaItems> priceFormulaItemsMap =  new HashMap<String, PriceFormulaItems>();  
				for(PriceFormulaItemDTO priceFormulaItemsDTO : priceFormulaItemsList){
					priceFormulaItemsMap.put(priceFormulaItemsDTO.getPriceFormulaItem().getId().toString(), priceFormulaItemsDTO.getPriceFormulaItem());
				}
				SessionUtil.insertRow(SessionPdtServ.PriceFormulaItem.value(), sessionFormulaItemId, priceFormulaItemsMap);
			}
		}
/*		this.priceFormulaItemsList = this.priceRuleService.searchPriceFormulaItemsByFormulaId(id);
		if(obj!=null){
			Map<String,PriceFormulaItems> priceFormulaItemsMap = (Map<String, PriceFormulaItems>) obj;
			for(Map.Entry<String, PriceFormulaItems> entry: priceFormulaItemsMap.entrySet()){
				PriceFormulaItems priceFormulaItems = priceFormulaItemsMap.get(entry.getKey());
				if(priceFormulaItems!=null){
					for(int i=0;i<priceFormulaItemsList.size();i++){
						if(priceFormulaItems.getId()!=null){
							if(priceFormulaItemsList.get(i).getPriceFormulaItem().getId().equals(priceFormulaItems.getId())){
								priceFormulaItemsList.get(i).getPriceFormulaItem().setSeqNo(priceFormulaItems.getSeqNo());
							}
						}
					}
					PriceFormulaItemDTO dto = new PriceFormulaItemDTO();
					dto.setPriceFormulaItem(priceFormulaItems);
					if(dto.getPriceFormulaItem().getType().equals("Value")){
						ServiceAttributes att =this.attributesService.getAttributeDetailById(Integer.valueOf(priceFormulaItems.getValue()));
						if(att!=null){
							dto.setValueName(att.getAttributeName());
						}
					}
					this.priceFormulaItemsList.add(dto);
				}
			}
		}
		obj = SessionUtil.getRow(SessionPdtServ.DelPriceFormulaItem.value(), this.sessionFormulaItemId);
		if(obj!=null){
   			List<Integer> delList = (List<Integer>)obj;
			for(int i=0;i<delList.size();i++){
				for(int j=0;j<priceFormulaItemsList.size();j++){
					if(priceFormulaItemsList.get(j).getPriceFormulaItem().getId().equals(delList.get(i))){
						priceFormulaItemsList.remove(j);
					}
				}
			}
   		}
		int k;
		for(int i=0;i<this.priceFormulaItemsList.size();i++){
			k=i;
			for(int j = 0;j<this.priceFormulaItemsList.size();i++){
				if(priceFormulaItemsList.get(j).getPriceFormulaItem().getSeqNo()<priceFormulaItemsList.get(i).getPriceFormulaItem().getSeqNo()){
					k=j;
				}
			}
			if(k>i){
				Integer seq = priceFormulaItemsList.get(k).getPriceFormulaItem().getSeqNo();
				
				 * 这里排序还要写
				 
			}
		}*/
		optionItemList = this.publicService.getDropdownList(SpecDropDownListName.DEFINE_FORMULA_ARGUMENT_TYPE.value());
		operationItemList = this.publicService.getDropdownList(SpecDropDownListName.PRICE_RULE_OPERATION.value());
		attributesList = this.attributesService.getAttributeListByclsId(clsId);
		return "formulaItemIput";
	}
	
	/*
	 * Confirm service formula item to formula session;
	 */
	public String confirmFromulaItem(){
		Map<String, Object> rt = new HashMap<String, Object>();
		Object obj = SessionUtil.getRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId);
		Object obj1 = SessionUtil.getRow(SessionPdtServ.PriceFormulaItem.value(), sessionFormulaItemId);
		if(obj!=null){
			PriceRulesDTO dto = (PriceRulesDTO)obj;
			for(int i=0;i<dto.getPriceFormulasDTOList().size();i++){
				if(dto.getPriceFormulasDTOList().get(i).getSessionId().equals(this.sessionFormulaItemId)){
					if(obj1!=null){
						Map<String,PriceFormulaItems> priceFormulaItemsMap = (Map<String, PriceFormulaItems>) obj1;
						for(Map.Entry<String, PriceFormulaItems> entry: priceFormulaItemsMap.entrySet()){
							PriceFormulaItems priceFormulaItems = priceFormulaItemsMap.get(entry.getKey());
							if(priceFormulaItems!=null){
								dto.getPriceFormulasDTOList().get(i).getPriceFormulaItems().add(priceFormulaItems);
							}
						}
					}
				}
			}
			SessionUtil.updateRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId, dto);
			rt.put("message", SUCCESS);
	   	}else{
	   		rt.put("message", "Session is Expired!!!");
	   	}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/*
	 * save ServiceFormula item to session
	 */
	public String saveFormulaItem(){
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			String itemKey= SessionUtil.generateTempId();
			SessionUtil.updateOneRow(SessionPdtServ.PriceFormulaItem.value(), sessionFormulaItemId, itemKey, priceFormulaItem);
			rt.put("message", SUCCESS);
			rt.put("id", itemKey);
		} catch (Exception e) {
			rt.put("message", ERROR);
			e.printStackTrace();
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/*
	 * save up or down service price formula item to session
	 */
	public String saveSeqFormulaItem(){
		String selId = ServletActionContext.getRequest().getParameter("selId");
		String nextId = ServletActionContext.getRequest().getParameter("nextId");
		Map<String, Object> rt = new HashMap<String, Object>();
		if(selId!=null&&nextId!=null){
			Object objSel = SessionUtil.getOneRow(SessionPdtServ.PriceFormulaItem.value(), this.sessionFormulaItemId, selId);
			Object objNext = SessionUtil.getOneRow(SessionPdtServ.PriceFormulaItem.value(), this.sessionFormulaItemId, nextId);
			if(objSel!=null&&objNext!=null){
				PriceFormulaItems subStepSel = ( PriceFormulaItems)objSel;
				PriceFormulaItems subStepNext = ( PriceFormulaItems)objNext;
				Integer id = subStepSel.getSeqNo();
				subStepSel.setSeqNo(subStepNext.getSeqNo());
				subStepNext.setSeqNo(id);
				SessionUtil.updateOneRow(SessionPdtServ.PriceFormulaItem.value(), this.sessionFormulaItemId, selId, subStepSel);
				SessionUtil.updateOneRow(SessionPdtServ.PriceFormulaItem.value(), this.sessionFormulaItemId, nextId, subStepNext);
			}
			rt.put("message", SUCCESS);
			
		}else{
			rt.put("message", "ERROR");
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/*
	 * del Service formula item to session
	 */
	@SuppressWarnings("unchecked")
	public String delFormulaItem(){
		System.out.println("come here");
		Map<String, Object> rt = new HashMap<String, Object>();
		String dataStr = ServletActionContext.getRequest().getParameter("delStr");
		if(dataStr!=null){
			String[] del = dataStr.split(",");
			Object obj = SessionUtil.getRow(SessionPdtServ.DelPriceFormulaItem.value(), sessionFormulaItemId);
			List<Integer> delList;
			if(obj!=null){
				delList = (List<Integer>)obj;
			}else{
				delList = new ArrayList<Integer>();
			}
			
			for(int i=0;i<del.length;i++){
				System.out.println("come here");
				if(SessionUtil.getOneRow(SessionPdtServ.PriceFormulaItem.value(), sessionFormulaItemId, del[i])!=null){
					System.out.println("come here");
					Object map = SessionUtil.getOneRow(SessionPdtServ.PriceFormulaItem.value(), sessionFormulaItemId, del[i]);
					PriceFormulaItems item = (PriceFormulaItems)map;
					SessionUtil.updateOneRow(SessionPdtServ.PriceFormulaItem.value(), sessionFormulaItemId, del[i],null );
					if(item.getId()!=null){
						delList.add(Integer.valueOf(del[i]));
					}
					Object obj1 = SessionUtil.getRow(SessionPdtServ.PriceFormulaItem.value(), sessionFormulaItemId);
					if(obj1!=null){
						System.out.println("come here");
						Map<String,PriceFormulaItems> priceFormulaItemsMap = (Map<String, PriceFormulaItems>) obj1;
						System.out.println("come here");
						int k=0;
						for(Map.Entry<String, PriceFormulaItems> entry: priceFormulaItemsMap.entrySet()){
							System.out.println("k="+k);
							k++;
							if(priceFormulaItemsMap.get(entry.getKey())!=null&&priceFormulaItemsMap.get(entry.getKey()).getSeqNo()>item.getSeqNo()){
								priceFormulaItemsMap.get(entry.getKey()).setSeqNo(priceFormulaItemsMap.get(entry.getKey()).getSeqNo()-1);
								SessionUtil.updateOneRow(SessionPdtServ.PriceFormulaItem.value(), sessionFormulaItemId, entry.getKey(), priceFormulaItemsMap.get(entry.getKey()));
							}
						}
					}
				}
			}
			if(!delList.isEmpty()){
				if(obj==null){
					SessionUtil.insertRow(SessionPdtServ.DelPriceFormulaItem.value(), sessionFormulaItemId, delList);
				}else{
					SessionUtil.updateRow(SessionPdtServ.DelPriceFormulaItem.value(), sessionFormulaItemId, delList);
				}
			}
			System.out.println("end");
			rt.put("message", SUCCESS);
		}else{
			rt.put("message", ERROR);
		}
		Struts2Util.renderJson(rt);
		System.out.println("over");
		return null;
	}
	
	public String clsIdChange(){
		Map<String, Object> rt = new HashMap<String, Object>();
		Object obj = SessionUtil.getRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId);
		if(obj!=null){
			PriceRulesDTO priceRulesDTO = (PriceRulesDTO)obj;
			if((priceRulesDTO.getPriceAttrValMapDTOList()==null||priceRulesDTO.getPriceAttrValMapDTOList().isEmpty())&&(priceRulesDTO.getPriceFormulasDTOList()==null||priceRulesDTO.getPriceFormulasDTOList().isEmpty())){
				this.attributesList = this.attributesService.getAttributeListByclsId(clsId);
				rt.put("attributesList", attributesList);
				rt.put("message", SUCCESS);
			}else{
				rt.put("message", ERROR);
			}
		}else{
			rt.put("message", ERROR);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	public String searchMappingValueOfCriteria(){
		Pattern pattern = Pattern.compile("[0-9]*");
		Map<String, Object> rt = new HashMap<String, Object>();
		Object obj = SessionUtil.getRow(SessionPdtServ.ServicePriceRule.value(), sessionPriceRuleId);
		String attributesId = ServletActionContext.getRequest().getParameter("attributesId");
		String ruleId = ServletActionContext.getRequest().getParameter("ruleId");
		if(pattern.matcher(attributesId).matches()&&pattern.matcher(ruleId).matches()){
			this.attriMappingList = this.priceRuleService.searchPriceRuleAttrValueMappingByAttributeIdAndRuleId(Integer.valueOf(ruleId),Integer.valueOf(attributesId));
		}else{
			this.attriMappingList = new ArrayList<PriceRuleAttrValueMapping>();
		}
		if(obj!=null){
			PriceRulesDTO dto = (PriceRulesDTO) obj;
			for(PriceRuleAttrValueMappingDTO mappingDTO : dto.getPriceAttrValMapDTOList()){
				if(mappingDTO!=null&&mappingDTO.getPriceRuleValue()!=null&&attributesId.equals(mappingDTO.getPriceRuleValue().getAttributeId().toString())){
					attriMappingList.add(mappingDTO.getPriceRuleValue());
				}
			}
		}
		rt.put("attriMappingList", attriMappingList);
		rt.put("message", SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}
	
	public Page<PriceRules> getPage() {
		return page;
	}

	public void setPage(Page<PriceRules> page) {
		this.page = page;
	}

	public List<DropDownDTO> getDropDownDTO() {
		return dropDownDTO;
	}

	public void setDropDownDTO(List<DropDownDTO> dropDownDTO) {
		this.dropDownDTO = dropDownDTO;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public Integer getClsId() {
		return clsId;
	}

	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}

	public List<ServiceAttributes> getAttributesList() {
		return attributesList;
	}

	public void setAttributesList(List<ServiceAttributes> attributesList) {
		this.attributesList = attributesList;
	}

	public List<PbDropdownListOptions> getOptionItemList() {
		return optionItemList;
	}

	public void setOptionItemList(List<PbDropdownListOptions> optionItemList) {
		this.optionItemList = optionItemList;
	}

	public String getSessionPriceRuleId() {
		return sessionPriceRuleId;
	}

	public void setSessionPriceRuleId(String sessionPriceRuleId) {
		this.sessionPriceRuleId = sessionPriceRuleId;
	}

	public PriceRuleAttrValueMapping getAttriMapping() {
		return attriMapping;
	}

	public void setAttriMapping(PriceRuleAttrValueMapping attriMapping) {
		this.attriMapping = attriMapping;
	}

	public PriceFormulaDTO getPriceFormulasDTO() {
		return priceFormulasDTO;
	}

	public void setPriceFormulasDTO(PriceFormulaDTO priceFormulasDTO) {
		this.priceFormulasDTO = priceFormulasDTO;
	}

	public PriceRules getPriceRules() {
		return priceRules;
	}

	public void setPriceRules(PriceRules priceRules) {
		this.priceRules = priceRules;
	}

	public List<PriceFormulaItemDTO> getPriceFormulaItemsList() {
		return priceFormulaItemsList;
	}

	public void setPriceFormulaItemsList(
			List<PriceFormulaItemDTO> priceFormulaItemsList) {
		this.priceFormulaItemsList = priceFormulaItemsList;
	}

	public PriceFormulaItems getPriceFormulaItem() {
		return priceFormulaItem;
	}

	public void setPriceFormulaItem(PriceFormulaItems priceFormulaItem) {
		this.priceFormulaItem = priceFormulaItem;
	}

	public String getSessionFormulaItemId() {
		return sessionFormulaItemId;
	}

	public void setSessionFormulaItemId(String sessionFormulaItemId) {
		this.sessionFormulaItemId = sessionFormulaItemId;
	}

	public PriceFormulaCriterias getPriceFormulaCriterias() {
		return priceFormulaCriterias;
	}

	public void setPriceFormulaCriterias(PriceFormulaCriterias priceFormulaCriterias) {
		this.priceFormulaCriterias = priceFormulaCriterias;
	}

	public List<PriceRuleAttrValueMapping> getAttriMappingList() {
		return attriMappingList;
	}

	public void setAttriMappingList(List<PriceRuleAttrValueMapping> attriMappingList) {
		this.attriMappingList = attriMappingList;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}

	public String getSessionRuleGroupId() {
		return sessionRuleGroupId;
	}

	public void setSessionRuleGroupId(String sessionRuleGroupId) {
		this.sessionRuleGroupId = sessionRuleGroupId;
	}

	public List<PbDropdownListOptions> getOperationItemList() {
		return operationItemList;
	}

	public void setOperationItemList(List<PbDropdownListOptions> operationItemList) {
		this.operationItemList = operationItemList;
	}
	
}
