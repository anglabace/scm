package com.genscript.gsscm.order.web;

import static java.io.File.separator;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.common.util.PdfUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.dto.OrderPrintDTO;
import com.genscript.gsscm.order.entity.OrderTemplate;
import com.genscript.gsscm.order.entity.PaymentVoucher;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

@Results({
		@Result(name = "order_print", location = "order/order_print.jsp"),
		@Result(name = "order_template", location = "order/order_template.jsp"),
		@Result(name = "order_reopen", location = "order/order_reopen.jsp") })
public class OrderExtraAction extends ActionSupport implements
		ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8029698034152652101L;
	@Autowired
	private OrderService orderService;
	private OrderMainDTO order;
	private String sessOrderNo;
	private String orderNo;
	// save template时是否覆盖已有同名的template.
	private String overrideFlag;
	private String tmplName;
	private List<OrderTemplate> templateList;
	private Integer maxTemplate = 6;
	// reopen order
	private String statusReason;
	private String orderStatus;
	private String statusNote;

	// print
	private OrderPrintDTO orderPrintDTO;

	// servletContext
	private ServletContext context;

	private static String FILE_STORAGE = "documents";
	private static String FE = "excel";
	@Autowired
	private ExceptionService exceptionUtil;

	/**
	 * Select Option下拉框中的Print Order.
	 * 
	 * @return
	 */
	public void print() {
		// order = (OrderMainDTO) SessionUtil.getRow(
		// SessionConstant.Order.value(), this.sessOrderNo);
		// return "order_print";
		String responsePdfName = "Order-" + this.getOrderNo() + ".pdf";
		// 3. 这是提供下载的
		System.out.println(context);
		System.out.println(this.getOrderNo());
		System.out.println(orderPrintDTO);
		String pdfName = this.orderService.mergePdfFiles(
				Integer.parseInt(this.getOrderNo()), orderPrintDTO, context);
		if (pdfName != null) {
			PdfUtils.downloadFile(pdfName, responsePdfName);
		}
	}

	/**
	 * 在Order列表页面点击下载Oligo信息
	 * 
	 * @author Zhang Yong add date 2011-09-27
	 */
	public void downLoadOligoInfo() {
//		String custNo = Struts2Util.getParameter("custNo");
		String fileName = "excel" + new Date().getTime() + ".xls";
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			File tempFile = new File(getDocumentItemStorage(request)
					+ separator + fileName);
			FileUtils.forceMkdir(tempFile.getParentFile());

			WritableWorkbook workbook = Workbook.createWorkbook(tempFile);
			WritableSheet sheet = workbook.createSheet("sheetname", 0);
			List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
			List<PropertyFilter> poNofilters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest(), "searcher_");
			Set<Integer> orderNoSet = null;
			if (poNofilters != null && poNofilters.size() > 0) {
				Page<PaymentVoucher> pageOrderMain = new Page<PaymentVoucher>();
				pageOrderMain.setPageSize(20);
				orderNoSet = orderService.getOrderNoSetByPoNo(pageOrderMain, poNofilters);
			}
			filters.add(new PropertyFilter("EQS_status", OrderItemStatusType.CC.value()));
			sheet = orderService.getOrderOligoInfo(orderNoSet, filters, sheet);
			workbook.write();
			workbook.close();
			// 下载
			HttpServletResponse response = Struts2Util.getResponse();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			java.io.OutputStream os = response.getOutputStream();
			String file = getDocumentItemStorage(request) + separator
					+ fileName;
			java.io.FileInputStream fis = new java.io.FileInputStream(file);
			byte[] b = new byte[1024];
			int j = 0;
			while ((j = fis.read(b)) > 0) {
				os.write(b, 0, j);
			}
			fis.close();
			os.flush();
			response.flushBuffer();
			os.close();
		} catch (Exception e) {
			System.out.println("Error exec!");
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			Struts2Util.renderText(exDTO.getMessageDetail() + "\n"
					+ exDTO.getAction());
			e.printStackTrace();
		}
	}

	public static String getFileStorage(HttpServletRequest request, String sss) {
		return request.getSession().getServletContext()
				.getRealPath(FILE_STORAGE + separator + sss);
	}

	public static String getDocumentItemStorage(HttpServletRequest request) {
		return getFileStorage(request, FE);
	}

	/**
	 * Select Option下拉框中的Repeat As New Order, 产生一个新的Order.
	 * 
	 * @return
	 */
	public String repeat() {
		Integer newOrderNo = orderService.repeatOrder(
				Integer.valueOf(this.sessOrderNo), SessionUtil.getUserId());
		Struts2Util.renderJson(newOrderNo);
		return null;
	}

	/**
	 * Select Option下拉框中的save as my Template.进行的保存操作.
	 * 
	 * @return
	 */
	public String saveTemplate() {
		try {
			OrderTemplate orderTemplate = new OrderTemplate();
			orderTemplate.setTmplName(this.getTmplName());
			orderTemplate.setOwner(SessionUtil.getUserId());
			orderTemplate.setSrcOrderNo(Integer.valueOf(this.sessOrderNo));
			this.orderService.saveOrderTemplate(orderTemplate,
					SessionUtil.getUserId(), overrideFlag);
			Struts2Util.renderJson("successful");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Select Option下拉框中的save as my Template.
	 * 
	 * @return
	 */
	public String searchTemplate() {
		templateList = orderService.getTemplateList(SessionUtil.getUserId());
		return "order_template";
	}

	/**
	 * Order Template中删除一个或多个Template.
	 * 
	 * @return
	 */
	public String delTemplate() {
		List<Integer> tmplIdList = new ArrayList<Integer>();
		String[] delIdArray = Struts2Util.getRequest().getParameterValues(
				"tmplId");
		for (String id : delIdArray) {
			if (StringUtils.isNumeric(id)) {
				tmplIdList.add(Integer.valueOf(id));
			}
		}
		this.orderService.delOrderTemplate(tmplIdList);
		Struts2Util.renderJson("successful");
		return null;
	}

	/**
	 * Reopen一个已经Canceled Order， 更新Order的状态为RV(Order On Review), Item状态不变.
	 * 如果statusReason为null, 则是进入页面.
	 * 
	 * @return
	 */
	public String reopen() throws Exception {
		if (this.statusReason != null) {// 响应操作
			String result = orderService.reopenOrder(statusReason, orderStatus,
					statusNote, sessOrderNo);
			Struts2Util.renderJson(result);
			return null;
		}
		return "order_reopen";// 进入页面
	}

	public String getSessOrderNo() {
		return sessOrderNo;
	}

	public void setSessOrderNo(String sessOrderNo) {
		this.sessOrderNo = sessOrderNo;
	}

	public String getOverrideFlag() {
		return overrideFlag;
	}

	public void setOverrideFlag(String overrideFlag) {
		this.overrideFlag = overrideFlag;
	}

	public String getTmplName() {
		return tmplName;
	}

	public void setTmplName(String tmplName) {
		this.tmplName = tmplName;
	}

	public List<OrderTemplate> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List<OrderTemplate> templateList) {
		this.templateList = templateList;
	}

	public OrderMainDTO getOrder() {
		return order;
	}

	public void setOrder(OrderMainDTO order) {
		this.order = order;
	}

	public Integer getMaxTemplate() {
		return maxTemplate;
	}

	public void setMaxTemplate(Integer maxTemplate) {
		this.maxTemplate = maxTemplate;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public String getStatusNote() {
		return statusNote;
	}

	public void setStatusNote(String statusNote) {
		this.statusNote = statusNote;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderPrintDTO getOrderPrintDTO() {
		return orderPrintDTO;
	}

	public void setOrderPrintDTO(OrderPrintDTO orderPrintDTO) {
		this.orderPrintDTO = orderPrintDTO;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		this.context = arg0;

	}

}
