package com.genscript.gsscm.manufacture.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.constant.WorkOrderStatus;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.manufacture.dto.WoBatcheDTO;
import com.genscript.gsscm.manufacture.dto.WorkOrderDTO;
import com.genscript.gsscm.manufacture.entity.QaClerk;
import com.genscript.gsscm.manufacture.entity.QaGroup;
import com.genscript.gsscm.manufacture.entity.WoBatche;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.manufacture.service.WorkOrderEntryService;
import com.genscript.gsscm.manufacture.service.WorkOrderProcService;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.serv.dto.ServiceDTO;
import com.genscript.gsscm.serv.service.ServService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = "search_from", location = "manufacture/workorder_qc_search.jsp"),
		@Result(name = "generate_qa_batch", location = "manufacture/generate_qa_batch.jsp"),
		@Result(name = "generate_qa_batch_error", location = "manufacture/generate_qa_batch_error.jsp"),
		@Result(name = "search_result", location = "manufacture/workorder_qc_result.jsp"),
		@Result(name = "workorder_qc_proc_order", location = "manufacture/workorder_qc_proc_order.jsp") })
public class WorkorderQcAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8362147274629373630L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private WorkOrderEntryService workOrderEntryService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private ServService servService;
	@Autowired
	private ProductService productService;
	@Autowired
	private WorkOrderProcService workOrderProcService;
	private List<QaGroup> qcGroupList;
	private List<QaClerk> qcClerkList;
	private Map<String, List<PbDropdownListOptions>> dropDownMap;
	private Page<WorkOrder> workOrderPage;
	private String orderNoStrs;// 要打包的所有订单的订单号
	private List<WorkOrderDTO> workOrderList;// 所有要打包的订单列表
	private List<WorkOrderDTO> workOrderErrorList;// 所有状态为close的打包订单
	private WoBatcheDTO woBatcheDto;
	private List<Integer> workOrderNoList;

	/**
	 * 进入WorkOrder Processing的主页面
	 * 
	 * @return
	 */
	public String search() {
		try {
			this.qcGroupList = this.workOrderEntryService.getAllQaGroup(Constants.ROLE_QC_MANAGER);
			if(qcGroupList!=null&&qcGroupList.size()>0) {
				qcClerkList = this.workOrderEntryService.getClerkListByQa(qcGroupList.get(0).getId(),Constants.ROLE_QC_MANAGER);
			}
			List<DropDownListName> listName = new ArrayList<DropDownListName>();
			listName.add(DropDownListName.WORK_ORDER_STATUS);
			dropDownMap = publicService.getDropDownMap(listName);
			List<WoBatche> woBatcheList = this.workOrderEntryService
					.getAllWoBatche();
			ServletActionContext.getRequest().setAttribute("woBatcheList",
					woBatcheList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "search_from";
	}

	/**
	 * 分页查找
	 */
	public String list() {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<WorkOrder> pagerUtil = new PagerUtil<WorkOrder>();
			workOrderPage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!workOrderPage.isOrderBySetted()) {
				workOrderPage.setOrderBy("creationDate,altOrderNo");
				workOrderPage.setOrder(Page.DESC+","+Page.ASC);
			}
			// 设置默认每页显示记录条数
			workOrderPage.setPageSize(15);
			// 获得查询条件
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(ServletActionContext.getRequest());
			String startOrderDate = Struts2Util.getParameter("start_orderDate");
			String endOrderDate = Struts2Util.getParameter("end_orderDate");
			if(StringUtils.isNotBlank(endOrderDate)) {
				PropertyFilter endDate = new PropertyFilter("EQS_endOrderDate",endOrderDate);
				filters.add(endDate);
			}
			if(StringUtils.isNotBlank(startOrderDate)) {
				PropertyFilter startDate = new PropertyFilter("EQS_startOrderDate",startOrderDate);
				filters.add(startDate);
			}
			workOrderPage = this.workOrderEntryService.searchWorkOrderPage(
					workOrderPage, filters, "QA",Constants.ROLE_QC_MANAGER,false);
			workOrderPage = this.workOrderEntryService
					.qcViewOtherInfo(workOrderPage);
			workOrderPage = this.workOrderEntryService.getDescription(workOrderPage);
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					workOrderPage);
		} catch (Exception ex) {
			return "search_result";
		}
		return "search_result";
	}

	/**
	 * 显示Processing Order页面.
	 * 
	 * @serialData 2010-12-02
	 * @author wangsf
	 * @return
	 */
	public String showProcessOrder() {
		return "workorder_qc_proc_order";
	}

	/**
	 * 处理WorkOrder.
	 * 
	 * @return
	 */
	public String processOrder() {
		// Product|Servcie Qc Status.
		String goodsQcStatus = Struts2Util.getParameter("goodsQcStatus");
		String docQcStatus = Struts2Util.getParameter("docQcStatus");
		String goodsQcRejectReason = Struts2Util
				.getParameter("goodsQcRejectReason");
		String docQcRejectReason = Struts2Util
				.getParameter("docQcRejectReason");
		List<String> orderNoList = Arrays.asList(orderNoStrs.split(","));
		workOrderNoList = new ArrayList<Integer>();
		for(String orderNo:orderNoList) {
			workOrderNoList.add(Integer.parseInt(orderNo));
		}
		this.workOrderEntryService.processOrderByQc(workOrderNoList,
				goodsQcStatus, docQcStatus, goodsQcRejectReason,
				docQcRejectReason, SessionUtil.getUserId());
		Struts2Util.renderText("Success");
		return null;
	}

	/**
	 * 显示订单打包操作窗口
	 * 
	 * @author lizhang
	 * @return
	 */
	public String showOperBatch() {
		woBatcheDto = new WoBatcheDTO();
		workOrderList = new ArrayList<WorkOrderDTO>();
		workOrderErrorList = new ArrayList<WorkOrderDTO>();
		if (orderNoStrs != null && !("").equals(orderNoStrs)) {
			String itemType = null;
			Integer clsId = null;
			woBatcheDto.setOrderNoStr(orderNoStrs);
			for (String orderNo : orderNoStrs.split(",")) {
				WorkOrderDTO workOrderDto = this.workOrderEntryService
						.getWorkOrder(Integer.parseInt(orderNo));
				if (workOrderDto != null) {
					if ("Closed".equals(workOrderDto.getStatus())) {
						workOrderList.add(workOrderDto);
					} else {
						workOrderErrorList.add(workOrderDto);
					}
				}
			}
			if (workOrderErrorList.size() > 0) {
				return "generate_qa_batch_error";
			}
			String batchType = null;
			if ("products".equals(itemType)) {
				ProductClass productClass = productService
						.getProductClass(clsId);
				batchType = productClass == null ? null : productClass
						.getName();
			} else if ("services".equals(itemType)) {
				ServiceDTO serviceDto = servService.getServDetail(clsId);
				batchType = serviceDto == null ? null : serviceDto.getName();
			}
			woBatcheDto.setBatchType(batchType);
			woBatcheDto.setBatchFunction("QA");
			woBatcheDto.setStatus("Delivered");
		}

		return "generate_qa_batch";
	}

	/**
	 * 确认打包
	 * 
	 * @author lizhang
	 * @return
	 */
	public String confrim() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			rt.put("message", this.workOrderProcService
					.saveWoBatche(woBatcheDto));
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

	public List<QaGroup> getQcGroupList() {
		return qcGroupList;
	}

	public void setQcGroupList(List<QaGroup> qcGroupList) {
		this.qcGroupList = qcGroupList;
	}

	public List<QaClerk> getQcClerkList() {
		return qcClerkList;
	}

	public void setQcClerkList(List<QaClerk> qcClerkList) {
		this.qcClerkList = qcClerkList;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}

	public void setDropDownMap(
			Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}

	public String getOrderNoStrs() {
		return orderNoStrs;
	}

	public void setOrderNoStrs(String orderNoStrs) {
		this.orderNoStrs = orderNoStrs;
	}

	public List<WorkOrderDTO> getWorkOrderList() {
		return workOrderList;
	}

	public void setWorkOrderList(List<WorkOrderDTO> workOrderList) {
		this.workOrderList = workOrderList;
	}

	public List<WorkOrderDTO> getWorkOrderErrorList() {
		return workOrderErrorList;
	}

	public void setWorkOrderErrorList(List<WorkOrderDTO> workOrderErrorList) {
		this.workOrderErrorList = workOrderErrorList;
	}

	public WoBatcheDTO getWoBatcheDto() {
		return woBatcheDto;
	}

	public void setWoBatcheDto(WoBatcheDTO woBatcheDto) {
		this.woBatcheDto = woBatcheDto;
	}

	public Page<WorkOrder> getWorkOrderPage() {
		return workOrderPage;
	}

	public void setWorkOrderPage(Page<WorkOrder> workOrderPage) {
		this.workOrderPage = workOrderPage;
	}

	public List<Integer> getWorkOrderNoList() {
		return workOrderNoList;
	}

	public void setWorkOrderNoList(List<Integer> workOrderNoList) {
		this.workOrderNoList = workOrderNoList;
	}
}
