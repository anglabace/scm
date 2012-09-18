package com.genscript.gsscm.manufacture.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.manufacture.entity.GroupResource;
import com.genscript.gsscm.manufacture.entity.Operation;
import com.genscript.gsscm.manufacture.entity.OperationResource;
import com.genscript.gsscm.manufacture.entity.Resource;
import com.genscript.gsscm.manufacture.entity.WorkCenter;
import com.genscript.gsscm.manufacture.service.SetupService;
import com.genscript.gsscm.privilege.dto.UserDTO;
import com.genscript.gsscm.privilege.entity.Role;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = "search_from", location = "manufacture/operation_search_form.jsp"),
		@Result(name = "search_result", location = "manufacture/operation_search_result.jsp"),
		@Result(name = "operation_add", location = "manufacture/operation_add.jsp"),
		@Result(name = "operation_edit", location = "manufacture/operation_edit.jsp"),
		@Result(name = "operation_edit", location = "manufacture/operation_edit.jsp"),
		@Result(name = "operation_resource_list", location = "manufacture/operation_resource_list.jsp") ,
		@Result(name = "route_operation_select", location = "manufacture/route_operation_select.jsp"),		
		@Result(name = "workorder_operation_search", location = "manufacture/workorder_operation_search.jsp")
})
public class OperationAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6534283335416418911L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private SetupService setupService;
	@Autowired
	private PrivilegeService privilegeService;
	private Operation operation;
	private Integer id;
	private Page<Operation> operationPage;
	private String[] delOperationId;
	private List<UserDTO> superList;
	private List<Integer> groupIdList;
	private List<Integer> dettachGroupIdList;
	private List<WorkCenter> centerList;
	// Operation Resource相关
	private Map<String, OperationResource> operationResMap;
	private String resKey;
	private String sessOperationId;
	private List<String> delResIdList;
	private List<Integer> resourceIdList;
	private List<Double> quantityList;
	// 用于批量更新seqNo.
	private List<Integer> seqNoList;
	private List<String> keyList;

	private String operation_method;
	private String quantity;
	
	private String selectFlg;//是否在选择页面显示
	private String roleName;
	
	/**
	 * 进入Operation的主页面
	 * 
	 * @return
	 */
	public String search() {
		this.centerList = this.setupService.getAllWorkCenter();
		return "search_from";
	}

	/**
	 * 进入新增页面
	 */
	public String add() {
		this.doInput();
		operation = new Operation();
		operation.setSetupTime(0);
		operation.setModifiedBy(SessionUtil.getUserId());
		operation.setModifyUser(SessionUtil.getUserName());
		return "operation_add";
	}

	/**
	 * 进入修改页面.
	 * 
	 * @return
	 */
	public String edit() {
		//*********** Add By Zhang Yong Start *****************************//
		if (this.id != null && !("").equals(this.id)) {
			//判断将要修改的单号是否正在被操作
			String editUrl = "operation!edit.action?id="+id;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
			}
		} else {
			//释放application中的订单锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock(); 
		}
		//*********** Add By Zhang Yong End *****************************//
		this.doInput();
		operation = this.setupService.getOperation(this.id);
		this.sessOperationId = Integer.toString(this.id);
		return "operation_edit";
	}

	public void doInput() {
		// 取得数据库中的
		this.centerList = this.setupService.getAllWorkCenter();
		Role role = privilegeService.getSuperRole();
		if (role != null) {
			this.superList = privilegeService.getUserListByRole(role
					.getRoleId());
		} else {
			this.superList = new ArrayList<UserDTO>();
		}
	}

	/**
	 * 分页查找
	 */
	public String list() {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<Operation> pagerUtil = new PagerUtil<Operation>();
			operationPage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!operationPage.isOrderBySetted()) {
				operationPage.setOrderBy("name");
				operationPage.setOrder(Page.ASC);
			}
			// 设置默认每页显示记录条数
			operationPage.setPageSize(20);
			// 获得查询条件
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			operationPage = setupService.searchOperationPage(operationPage,
					filters,selectFlg);
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					operationPage);
			if (Struts2Util.getParameter("toPage") != null) {
				//workorder_operation_search在WorkOrder页面中选择Operation
				//搜索和结果页现在是一个页面了.
				this.centerList = this.setupService.getAllWorkCenter(roleName);
				System.out.println("toPage: " + Struts2Util.getParameter("toPage"));
				return Struts2Util.getParameter("toPage");
			}
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.remove(SessionConstant.OperationResource.value());
		} catch (Exception ex) {
			return "search_result";
		}
		return "search_result";
	}
	
	/**
	 * 供Route页面使用.
	 */
	public String selectForRoute() {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<Operation> pagerUtil = new PagerUtil<Operation>();
			operationPage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!operationPage.isOrderBySetted()) {
				operationPage.setOrderBy("id");
				operationPage.setOrder(Page.DESC);
			}
			// 设置默认每页显示记录条数
			operationPage.setPageSize(20);
			// 获得查询条件
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			if (filters == null || filters.isEmpty()) {
				// 默认列表的结果集(含分页信息)
				operationPage = setupService.searchOperationPageForRoute(operationPage,
						null);
			} else {
				// 有查询条件的结果集(含分页信息)
				operationPage = setupService.searchOperationPageForRoute(operationPage,
						filters);
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					operationPage);
		} catch (Exception ex) {
			return "route_operation_select";
		}
		return "route_operation_select";
	}

	/**
	 * 新增或修改一个Operation
	 */
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			//*********** Add By Zhang Yong Start *****************************//
			//校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
			if (this.operation!= null && this.operation.getId() != null) {
				String editUrl = "operation!edit.action?id="+this.operation.getId();
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
					rt.put("message", "Save fail,the operation is editing by "+operation_method);
					Struts2Util.renderJson(rt);
					return null;
				}
			}
			//*********** Add By Zhang Yong End *****************************//	
			this.attachResource();
			this.setupService.saveOperation(operation, SessionUtil.getUserId());
			// 清除session
			SessionUtil.deleteRow(SessionConstant.OperationResource.value(),
					this.operation.getId() + "");
			rt.put("message", "Save operation sucessfully.");
			//*********** Add By Zhang Yong Start *****************************//
			//释放同步锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock(); 
			//*********** Add By Zhang Yong End *****************************//
		} catch (Exception ex) {
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
	 * 
	 * 批量删除Operation.
	 */
	public String delete() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			List<Integer> idList = new ArrayList<Integer>();
			for (String temp : this.delOperationId) {
				idList.add(Integer.valueOf(temp));
			}
			this.setupService.delOperation(idList);
			rt.put("message", "Delete operation sucessfully !");
		} catch (Exception ex) {
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
	 * 进入一个Opreations的Resource list页面.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getResourceList() {
		Map<String, OperationResource> dbMap = null;
		Map<String, OperationResource> sessMap = (Map<String, OperationResource>) SessionUtil
		.getRow(SessionConstant.OperationResource.value(),
				String.valueOf(this.sessOperationId));
		if(sessMap==null) {
			// 如果sessOperationId为数字则取得数据中的, 否则数据库中没有记录不用去查询
			if (StringUtils.isNumeric(this.sessOperationId)) {
				List<OperationResource> dbList = this.setupService
						.getAllOperationResource(Integer
								.parseInt(this.sessOperationId));
				dbMap = new LinkedHashMap<String, OperationResource>();
				for (OperationResource dto : dbList) {
					dbMap.put(dto.getId() + "", dto);
				}
			}
			SessionUtil.insertRow(SessionConstant.OperationResource.value(),
					this.sessOperationId, dbMap);
		} else {
			dbMap = sessMap;
		}
		
		operationResMap = dbMap;
		return "operation_resource_list";
	}

	@SuppressWarnings("unchecked")
	public String deleteResource() {
		this.operationResMap = (Map<String, OperationResource>) SessionUtil
				.getRow(SessionConstant.OperationResource.value(),
						this.sessOperationId);
		for (String key : this.delResIdList) {
			if (StringUtils.isNumeric(key)) {
				operationResMap.put(key, null);
			} else {
				operationResMap.remove(key);
			}

		}
		Struts2Util.renderText("success");
		return NONE;
	}

	@SuppressWarnings("unchecked")
	public String saveResource() throws Exception {
		Map<String, OperationResource> sessMap = (Map<String, OperationResource>) SessionUtil
		.getRow(SessionConstant.OperationResource.value(),
				this.sessOperationId);
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			int trCount = Integer.parseInt(Struts2Util.getParameter("trCount"));
			for (int i = 0; i < this.resourceIdList.size(); i++) {
				boolean flg = true;
				if(sessMap!=null&&sessMap.size()>0) {
					for(Map.Entry<String, OperationResource> entry:sessMap.entrySet()) {
						OperationResource operationResource = entry.getValue();
						if(operationResource!=null&&operationResource.getResource()!=null&&operationResource.getResource().getResourceId().intValue()==this.resourceIdList.get(i).intValue()) {
							operationResource.setQuantity(new BigDecimal((operationResource.getQuantity()==null?0:operationResource.getQuantity().doubleValue())+this.quantityList.get(i)).setScale(2, BigDecimal.ROUND_HALF_UP));
							flg = false;
							break;
						}
					}
				}
				if(flg) {
					trCount++;
					String tempId = SessionUtil.generateTempId();
					Resource resource = this.setupService
							.getResource(this.resourceIdList.get(i));
					OperationResource gr = new OperationResource();
					gr.setResource(resource);
					gr.setSeqNo(trCount);
					gr.setQuantity(new BigDecimal(this.quantityList.get(i)).setScale(2, BigDecimal.ROUND_HALF_UP));
					sessMap.put(tempId, gr);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		SessionUtil.updateRow(SessionConstant.OperationResource.value(),
				this.sessOperationId,sessMap);
		retMap.put("message", "success");
		Struts2Util.renderJson(retMap);
		return null;
	}

	/**
	 * 保存Operation同时保存OperationResource.
	 */
	@SuppressWarnings("unchecked")
	private void attachResource() {
		List<OperationResource> groupResList = new ArrayList<OperationResource>();
		List<Integer> delIdList = new ArrayList<Integer>();
		Map<String, OperationResource> sessMap = (Map<String, OperationResource>) SessionUtil
				.getRow(SessionConstant.OperationResource.value(),
						this.sessOperationId);
		if (sessMap == null) {
			return;
		}
		for (Entry<String, OperationResource> entry : sessMap.entrySet()) {
			OperationResource groupRes = (OperationResource) entry.getValue();
			// 本例中对session中的数据只有新增和删除操作
			if (StringUtils.isNumeric(entry.getKey())) {// 数据库已有的
				if (groupRes == null) {// 执行了临时的删除操作
					delIdList.add(Integer.parseInt(entry.getKey()));
				} else {
					// TODO 修改部分的处理暂不考虑
					groupResList.add(groupRes);// 修改的
				}
			} else {
				groupRes.setId(null);
				groupResList.add(groupRes);// 新增的
			}
		}
		this.operation.setDelOperationResIdList(delIdList);
		this.operation.setOperationResList(groupResList);
	}

	@SuppressWarnings("unchecked")
	public String updateSeqNo() {
		// 取得session中的
		Map<String, OperationResource> sessMap = (Map<String, OperationResource>) SessionUtil
				.getRow(SessionConstant.OperationResource.value(),
						this.sessOperationId);
		for (int k = 0; k < this.keyList.size(); k++) {
			OperationResource ors = sessMap.get(keyList.get(k));
			ors.setSeqNo(this.seqNoList.get(k));
			SessionUtil.updateOneRow(SessionConstant.OperationResource.value(),
					this.sessOperationId, keyList.get(k), ors);
		}
		Struts2Util.renderText("success");
		return NONE;
	}
	
	public String updateQty() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		// 取得session中的
		Map<String, OperationResource> sessMap = (Map<String, OperationResource>) SessionUtil
				.getRow(SessionConstant.OperationResource.value(),
						this.sessOperationId);
		OperationResource ors = sessMap.get(resKey);
		ors.setQuantity(new BigDecimal(quantity).setScale(2, BigDecimal.ROUND_HALF_UP));
		SessionUtil.updateOneRow(SessionConstant.OperationResource.value(),
				this.sessOperationId, resKey, ors);
		Struts2Util.renderJson(retMap);
		return NONE;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public Page<Operation> getOperationPage() {
		return operationPage;
	}

	public void setOperationPage(Page<Operation> operationPage) {
		this.operationPage = operationPage;
	}

	public String[] getDelOperationId() {
		return delOperationId;
	}

	public void setDelOperationId(String[] delOperationId) {
		this.delOperationId = delOperationId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<UserDTO> getSuperList() {
		return superList;
	}

	public void setSuperList(List<UserDTO> superList) {
		this.superList = superList;
	}

	public List<Integer> getGroupIdList() {
		return groupIdList;
	}

	public void setGroupIdList(List<Integer> groupIdList) {
		this.groupIdList = groupIdList;
	}

	public List<Integer> getDettachGroupIdList() {
		return dettachGroupIdList;
	}

	public void setDettachGroupIdList(List<Integer> dettachGroupIdList) {
		this.dettachGroupIdList = dettachGroupIdList;
	}

	public List<WorkCenter> getCenterList() {
		return centerList;
	}

	public void setCenterList(List<WorkCenter> centerList) {
		this.centerList = centerList;
	}

	public String getSessOperationId() {
		return sessOperationId;
	}

	public void setSessOperationId(String sessOperationId) {
		this.sessOperationId = sessOperationId;
	}

	public Map<String, OperationResource> getOperationResMap() {
		return operationResMap;
	}

	public void setOperationResMap(
			Map<String, OperationResource> operationResMap) {
		this.operationResMap = operationResMap;
	}

	public String getResKey() {
		return resKey;
	}

	public void setResKey(String resKey) {
		this.resKey = resKey;
	}

	public List<String> getDelResIdList() {
		return delResIdList;
	}

	public void setDelResIdList(List<String> delResIdList) {
		this.delResIdList = delResIdList;
	}

	public List<Integer> getResourceIdList() {
		return resourceIdList;
	}

	public void setResourceIdList(List<Integer> resourceIdList) {
		this.resourceIdList = resourceIdList;
	}

	public List<Double> getQuantityList() {
		return quantityList;
	}

	public void setQuantityList(List<Double> quantityList) {
		this.quantityList = quantityList;
	}

	public List<Integer> getSeqNoList() {
		return seqNoList;
	}

	public void setSeqNoList(List<Integer> seqNoList) {
		this.seqNoList = seqNoList;
	}

	public List<String> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<String> keyList) {
		this.keyList = keyList;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}



	public String getSelectFlg() {
		return selectFlg;
	}

	public void setSelectFlg(String selectFlg) {
		this.selectFlg = selectFlg;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
