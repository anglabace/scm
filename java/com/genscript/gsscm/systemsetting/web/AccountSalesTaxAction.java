package com.genscript.gsscm.systemsetting.web;
import java.util.ArrayList;
import java.util.Date;
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
import com.genscript.gsscm.basedata.entity.PbCountry;
import com.genscript.gsscm.basedata.entity.PbCurrency;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.entity.PbLanguage;
import com.genscript.gsscm.basedata.entity.PbState;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.systemsetting.service.AccountSalesTaxService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * @Description:setting/accounting/Territories, Currencies & Taxes模块维护控制类
 * @Copyright: Copyright genscrpit (c)2010
 * @author: lizhang
 * @version: 2.0
 * @date:   2011-4-8
 * 
 * Modification History:
 * Date			Author			Version			Description
 * 2011-4-8     lizhang          2.0             renewal
 * -------------------------------------------------------------
 *
 */
@Results({
	@Result(name = "sales_tax_rates_search", location = "systemsetting/account/sales_tax_rates_search.jsp"),
	@Result(name = "sales_tax_rates_list" ,location = "systemsetting/account/sales_tax_rates_list.jsp"),
	@Result(name = "tax_rate_info" , location = "systemsetting/account/tax_rate_info.jsp"),
	@Result(name = "state_list" , location = "systemsetting/account/state_list.jsp"),
	@Result(name = "sales_rate_state_info" ,location="systemsetting/account/sales_rate_state_info.jsp")
}		
)

public class AccountSalesTaxAction extends BaseAction<Object>{
	private static final long serialVersionUID = 1L;
	/**自动装载实例**/
	@Autowired
	private AccountSalesTaxService accountSalesTaxService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private ExceptionService exceptionUtil;
	
	/**action变量**/
	private Integer id;
	private String sessionId;
	private String stateSessionId;
	private Integer stateId;
	private String operation_method;
	private String allChoiceVal;
	private PbCountry pbCountry;
	private PbState pbState;
	private Page<PbCountry> countryPage;
	private List<PbCountry> countryList;
	private List<PbCurrency> currencyList;
	private List<PbLanguage> languageList;
	private Map<String,PbState> stateMap;
	private Map<String, List<PbDropdownListOptions>> dropDownMap;

	
	
	/************************************action method****************************************/
	
	/**
	 * 进入模块初始执行方法
	 */
	public String input() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove(SessionConstant.StateList.value());
		return "sales_tax_rates_search";
	}
	
	/**
	 * 列表显示
	 * @return
	 */
	public String list() {
		countryPage = this.accountSalesTaxService.searchCountryPage(countryPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				countryPage);
		return "sales_tax_rates_list";
	}
	
	/**
	 * 显示添加或更新页面
	 * @return
	 */
	public String load() {
		List<DropDownListName> listName = new ArrayList<DropDownListName>();
		listName.add(DropDownListName.CONTINENT);
		dropDownMap = publicService.getDropDownMap(listName);
		currencyList = this.accountSalesTaxService.getAllCurrency();
		languageList = this.accountSalesTaxService.getAllLanguage();
		try {
			if(id!=null) {
				// 判断将要修改的单号是否正在被操作
				String editUrl = "systemsetting/account_sales_tax!load.action?id=" + id;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
				}
			}

			if(id!=null) {
				sessionId = String.valueOf(id);
				pbCountry = this.accountSalesTaxService.findById(id);
			} else {
				sessionId = SessionUtil.generateTempId();
				pbCountry = new PbCountry();
				pbCountry.setCreationDate(new Date());
				pbCountry.setCreatedBy(SessionUtil.getUserId());
			}
			pbCountry.setModifiedBy(SessionUtil.getUserId());
			pbCountry.setModifiedName(SessionUtil.getUserName());
			pbCountry.setModifyDate(new Date());
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
		} catch(Exception e) {
			e.printStackTrace();
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
		}
		return "tax_rate_info";
	}
	/**
	 * state列表显示
	 */
	public String stateList() {
		if(id!=null) {
			stateMap = (Map<String,PbState>)SessionUtil.getRow(SessionConstant.StateList.value(), String.valueOf(id));
			if(stateMap==null) {
				List<PbState> stateList = this.accountSalesTaxService.getStateByCountryId(id);
				stateMap = SessionUtil.convertList2Map(stateList, "stateId");
				SessionUtil.insertRow(SessionConstant.StateList.value(),
						String.valueOf(id), stateMap);
			}
		}
		return "state_list";
	}
	
	/**
	 * 加载state
	 */
	public String loadState() {
		try {
			if(StringUtils.isNotEmpty(sessionId)&&StringUtils.isNumeric(sessionId)) {
				pbCountry = this.accountSalesTaxService.findById(Integer.parseInt(sessionId));
			} 
			if(StringUtils.isNotEmpty(stateSessionId)) {
				stateMap = (Map<String,PbState>)SessionUtil.getRow(SessionConstant.StateList.value(), String.valueOf(sessionId));
				if(stateMap!=null) {
					pbState = stateMap.get(stateSessionId);
				}
				if(pbState==null&&StringUtil.isNumeric(stateSessionId)) {
					pbState = this.accountSalesTaxService.findStateById(Integer.parseInt(stateSessionId));
				}
			}
			if(pbState==null) {
				pbState = new PbState();
				pbState.setCreationDate(new Date());
				pbState.setCreatedBy(SessionUtil.getUserId());
				stateSessionId = SessionUtil.generateTempId();
			}
			pbState.setModifiedBy(SessionUtil.getUserId());
			pbState.setModifyDate(new Date());
			pbState.setModifiedName(SessionUtil.getUserName());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return "sales_rate_state_info";
	}
	
	/**
	 * 保存或更新state
	 */
	public String saveState() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotEmpty(sessionId)) {
				Map<String,PbState> stateMap = (Map<String,PbState>)SessionUtil.getRow(SessionConstant.StateList.value(), String.valueOf(sessionId));
				if(stateMap==null) {
					stateMap = new LinkedHashMap<String,PbState>();
				}
				stateMap.put(stateSessionId, pbState);
				SessionUtil.insertRow(SessionConstant.StateList.value(), String.valueOf(sessionId), stateMap);
			}
			
			rt.put("message", "Success");
		} catch(Exception ex) {
			ex.printStackTrace();
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 删除state
	 */
	public String deleteState() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.accountSalesTaxService.deleteState(allChoiceVal,sessionId);
		}  catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return NONE;
	}
	
	/**
	 * 保存或更新
	 * @return
	 */
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.accountSalesTaxService.saveCountry(pbCountry, sessionId);
			SessionUtil.deleteRow(SessionConstant.StateList.value(), sessionId);
			rt.put("message", "Success");
		}catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return NONE;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			this.accountSalesTaxService.deleteCountry(allChoiceVal);
		}  catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return NONE;
	}


	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public List<PbCurrency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<PbCurrency> currencyList) {
		this.currencyList = currencyList;
	}

	public List<PbLanguage> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<PbLanguage> languageList) {
		this.languageList = languageList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}

	public PbCountry getPbCountry() {
		return pbCountry;
	}

	public void setPbCountry(PbCountry pbCountry) {
		this.pbCountry = pbCountry;
	}

	public Page<PbCountry> getCountryPage() {
		return countryPage;
	}

	public void setCountryPage(Page<PbCountry> countryPage) {
		this.countryPage = countryPage;
	}

	public List<PbCountry> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<PbCountry> countryList) {
		this.countryList = countryList;
	}

	public Map<String, PbState> getStateMap() {
		return stateMap;
	}

	public void setStateMap(Map<String, PbState> stateMap) {
		this.stateMap = stateMap;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public PbState getPbState() {
		return pbState;
	}

	public void setPbState(PbState pbState) {
		this.pbState = pbState;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}

	public void setDropDownMap(Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}

	public String getStateSessionId() {
		return stateSessionId;
	}

	public void setStateSessionId(String stateSessionId) {
		this.stateSessionId = stateSessionId;
	}

	public String getAllChoiceVal() {
		return allChoiceVal;
	}

	public void setAllChoiceVal(String allChoiceVal) {
		this.allChoiceVal = allChoiceVal;
	}

}
