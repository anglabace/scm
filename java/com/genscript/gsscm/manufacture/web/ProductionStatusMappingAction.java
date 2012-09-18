package com.genscript.gsscm.manufacture.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.manufacture.entity.Operation;
import com.genscript.gsscm.manufacture.entity.ProductionStatusMapping;
import com.genscript.gsscm.manufacture.entity.WorkCenter;
import com.genscript.gsscm.manufacture.entity.WorkGroup;
import com.genscript.gsscm.manufacture.service.ProductionStatusMappingService;
import com.genscript.gsscm.manufacture.service.SetupService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "customer_monitor_list", location = "manufacture/production_search_list.jsp"),
		   @Result(name = "save_from", location = "manufacture/production_save.jsp")})
public class ProductionStatusMappingAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1494645302777722965L;

	/**
	 * 
	 */
	
	@Autowired
	private ProductionStatusMappingService productionStatusMappingService;
	@Autowired
	private SetupService setupService;
	@Autowired
	private ExceptionService exceptionUtil;
	private Page<ProductionStatusMapping> productionStatusMappingPage;
	private List<WorkCenter> workCenterList;
	private List<Operation> operationList;
	private List<WorkGroup> workGroupList;
	private Integer wo_peptide_info_look;	
	private Integer wo_sendMail_flag;
	private String quoteNos;
	private String statusReason;
	private String comment;
	private String status;
	private Integer workCenterId;
	private ProductionStatusMapping productionStatusMapping;
	private Integer id;
	private String productionStatus;
	private Integer workId;

	
	
	


	public Integer getWorkId() {
		return workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}

	public String getProductionStatus() {
		return productionStatus;
	}

	public void setProductionStatus(String productionStatus) {
		this.productionStatus = productionStatus;
	}

	public List<Operation> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<Operation> operationList) {
		this.operationList = operationList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWorkCenterId() {
		return workCenterId;
	}

	public void setWorkCenterId(Integer workCenterId) {
		this.workCenterId = workCenterId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ProductionStatusMapping getProductionStatusMapping() {
		return productionStatusMapping;
	}

	public void setProductionStatusMapping(
			ProductionStatusMapping productionStatusMapping) {
		this.productionStatusMapping = productionStatusMapping;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getQuoteNos() {
		return quoteNos;
	}

	public void setQuoteNos(String quoteNos) {
		this.quoteNos = quoteNos;
	}

	public Integer getWo_sendMail_flag() {
		return wo_sendMail_flag;
	}

	public void setWo_sendMail_flag(Integer wo_sendMail_flag) {
		this.wo_sendMail_flag = wo_sendMail_flag;
	}

	public List<WorkCenter> getWorkCenterList() {
		return workCenterList;
	}

	public void setWorkCenterList(List<WorkCenter> workCenterList) {
		this.workCenterList = workCenterList;
	}

	public Page<ProductionStatusMapping> getProductionStatusMappingPage() {
		return productionStatusMappingPage;
	}

	public void setProductionStatusMappingPage(
			Page<ProductionStatusMapping> productionStatusMappingPage) {
		this.productionStatusMappingPage = productionStatusMappingPage;
	}
	

	public Integer getWo_peptide_info_look() {
		return wo_peptide_info_look;
	}

	public void setWo_peptide_info_look(Integer wo_peptide_info_look) {
		this.wo_peptide_info_look = wo_peptide_info_look;
	}
	

	public List<WorkGroup> getWorkGroupList() {
		return workGroupList;
	}

	public void setWorkGroupList(List<WorkGroup> workGroupList) {
		this.workGroupList = workGroupList;
	}
	/**
	 * 分页
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		try {	
		productionStatusMappingPage = productionStatusMappingService.search(productionStatusMappingPage);
		this.workCenterList = this.setupService.getAllWorkCenter(Constants.ROLE_WOPROCESS_MANAGER);
//		if(workCenterList!=null) {
//			for(WorkCenter workCenter:workCenterList) {
//				if(workCenter.getName().toLowerCase().equals("peptide department")) {
//					wo_peptide_info_look = 1;
//				}
//				if(workCenter.getName().toLowerCase().equals("antibody department")) {
//					wo_sendMail_flag = 1;
//				}
//			}
//		}
		if(workCenterList!=null&&workCenterList.size()>0) {
			workGroupList = this.setupService.getGroupListByCenter(workCenterList.get(0).getId(),Constants.ROLE_WOPROCESS_MANAGER);
		}
		
		} catch(Exception e ) {
			e.printStackTrace();
		}
		//存入request
		ServletActionContext.getRequest().setAttribute("pagerInfo",productionStatusMappingPage);
		return "customer_monitor_list";
	}
	/**
	 * 删除 
	 */
	public String deleteProductionId() {
		try {
			if (quoteNos != null) {
				// 判断当前用户是否含有销售经理角色
//				boolean salesManager = privilegeService
//						.checkIsSalesManagerRole();
//				if (!salesManager) {
//					Struts2Util.renderText("error");
//					return null;
//				}
					productionStatusMappingService.delProduction(quoteNos);
					Struts2Util.renderText("success");
				} else {
					Struts2Util.renderText("error");
				}
		} catch (Exception ex) {
			Struts2Util.renderText("error");
		}
		return null;
	}
	
	/**
	 * 跳转保存页面
	 */
	
	public String result() throws Exception{
		
		this.workCenterList = this.setupService.getAllWorkCenter(Constants.ROLE_WOPROCESS_MANAGER);
		if(workCenterList!=null&&workCenterList.size()>0) {
			workGroupList = this.setupService.getGroupListByCenter(workCenterList.get(0).getId(),Constants.ROLE_WOPROCESS_MANAGER);
		}
		if (this.id == null) {
			
		} else {
			productionStatusMapping = productionStatusMappingService.getProductionStatusMapping(id);
			productionStatus=productionStatusMapping.getProductionStatus();
		}
		return "save_from";
	}
	/**
	 * 保存
	 * @return
	 * @throws Exception
	 */
	public String save(){		
		Map<String, Object> rt = new HashMap<String, Object>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userId = session.get(StrutsActionContant.USER_ID);
		try {
			productionStatusMapping.setModifiedBy(Integer.parseInt(userId.toString()));
			productionStatusMapping.setModifyDate(new Date());
			productionStatusMapping.setCreatedBy(Integer.parseInt(userId.toString()));
			productionStatusMapping.setCreationDate(new Date());
			productionStatus=Struts2Util.getParameter("production_status");
			productionStatusMapping.setProductionStatus(productionStatus);	
			System.out.println("productionStatus============="+productionStatus);
			//判断是新增还是修改
			if (productionStatusMapping != null && productionStatusMapping.getId() == null) {
				// 判断当前用户是否含有销售经理角色
//				boolean salesManager = privilegeService
//						.checkIsSalesManagerRole();
//				if (!salesManager) {
//					Struts2Util.renderText("error");
//					return null;
//				}																
					productionStatusMappingService.saveProduction(productionStatusMapping);
					rt.put("message", "Save productionStatusMapping sucessfully.");
				} else {
					productionStatusMappingService.updateProduction(productionStatusMapping);
					rt.put("message", "Update productionStatusMapping sucessfully.");
				}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}
	/**
	 * 下拉列表
	 * @return
	 */
	public String selProduction(){
		Map<String, Object> rt = new HashMap<String, Object>();
		operationList=productionStatusMappingService.getProductionList(workCenterId);
		rt.put("list",operationList);
		rt.put("message","SUCCESS");
		Struts2Util.renderJson(rt);
		return null;
	}
}
