package com.genscript.gsscm.customer.web;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.dto.AnalysisReport;
import com.genscript.gsscm.customer.dto.CustSalesStaticsDTO;
import com.genscript.gsscm.customer.dto.SalesStaticsSrchDTO;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.order.dto.SalesOrderedDTO;
import com.opensymphony.xwork2.ActionSupport;
@Results( {
	@Result(name = "showSalesOrdered", location = "customer/item_list.jsp")
})
public class CustSalesStaticsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5216577254640829637L;
	
	@Autowired
	private CustomerService customerService;
	
	private Date fromDate;//开始时间
	private Date toDate;//结束时间
	private String salesPeriod;//
	private Integer top;//查询前几名sales
	private Integer periodType;//统计图片时间点
	private Integer custNo;
	private String type;
	
	private List<SalesOrderedDTO> salesOrderedList;
	
	public String showSalesOrdered() throws Exception {
		salesOrderedList = customerService.getMostOrdered(custNo, top, type);
		Struts2Util.renderJson(salesOrderedList);
		return null;
	}
	
	public String showOrdersBy() throws Exception {
		salesOrderedList = customerService.getOrdersBy(custNo, type);
		Struts2Util.renderJson(salesOrderedList);
		return null;
	}
	
	public String showSalesStatics() throws Exception {
		if(custNo!=null){
			if(salesPeriod!=null){
				if(salesPeriod.equals("lastWeek")){
					fromDate = DateUtils.formatStr2Date(DateUtils.getPreviousMonday(),DateUtils.C_DATE_PATTON_DEFAULT);
					toDate = DateUtils.formatStr2Date(DateUtils.getPreviousSunday(),DateUtils.C_DATE_PATTON_DEFAULT);
				}else if(salesPeriod.equals("thisWeek")){
					fromDate = DateUtils.formatStr2Date(DateUtils.getCurrentMonday(),DateUtils.C_DATE_PATTON_DEFAULT);
					toDate = DateUtils.getWeekEndDay(new Date());
				}else if(salesPeriod.equals("lastMonth")){
					fromDate = DateUtils.formatStr2Date(DateUtils.getLastMonth(),DateUtils.C_DATE_PATTON_DEFAULT);
					toDate = DateUtils.formatStr2Date(DateUtils.getLastDayOfMonth(fromDate),DateUtils.C_DATE_PATTON_DEFAULT);
				}else if(salesPeriod.equals("thisMonth")){
					fromDate = DateUtils.formatStr2Date(DateUtils.getFirstDayOfMonth(new Date()),DateUtils.C_DATE_PATTON_DEFAULT);
					toDate = DateUtils.formatStr2Date(DateUtils.getLastDayOfMonth(new Date()),DateUtils.C_DATE_PATTON_DEFAULT);
				}else if(salesPeriod.equals("lastQuarter")){
					fromDate = DateUtils.getQuarterStartDay(DateUtils.defineMonthBefore2Date(new Date(),-3));
					toDate = DateUtils.getQuarterEndDay(DateUtils.defineMonthBefore2Date(new Date(),-3));
				}else if(salesPeriod.equals("thisQuarter")){
					fromDate = DateUtils.getQuarterStartDay(new Date());
					toDate = DateUtils.getQuarterEndDay(new Date());
				}else if(salesPeriod.equals("last6Months")){
					fromDate = DateUtils.getLast6MonthsStartDay(DateUtils.defineMonthBefore2Date(new Date(),-6));
					toDate = DateUtils.getLast6MonthsEndDay(DateUtils.defineMonthBefore2Date(new Date(),-6));
				}else if(salesPeriod.equals("lastYear")){
					fromDate = DateUtils.getYearStartDay(DateUtils.defineMonthBefore2Date(new Date(),-12));
					toDate = DateUtils.getYearEndDay(DateUtils.defineMonthBefore2Date(new Date(),-12));
				}else if(salesPeriod.equals("thisYear")){
					fromDate = DateUtils.getYearStartDay(new Date());
					toDate = DateUtils.getYearEndDay(new Date());
				}
			}
			toDate = DateUtils.defineDayBefore2Date(toDate,1);
			StringBuilder sb = new StringBuilder();
			if(periodType == 1){
				sb.append("Daily Sales Statistics from ");
			}else if(periodType == 7){
				sb.append("Weekly Sales Statistics from ");
			}else if(periodType == 30){
				sb.append("Monthly Sales Statistics from ");
			}else if(periodType == 90){
				sb.append("Quarterly Sales Statistics from ");
			}else{
				sb.append("Yearly Sales Statistics from ");
			}
			sb.append(DateUtils.formatDate2Str(fromDate, "yyyy-MM-dd"));
			sb.append(" to ").append(DateUtils.formatDate2Str(toDate, "yyyy-MM-dd"));
			CustSalesStaticsDTO dto = new CustSalesStaticsDTO();
			dto.setPicName(getSalesStaticsPicName());
			dto.setShowContent(sb.toString());
			System.out.println("end " + dto);
			Struts2Util.renderJson(dto);
			return null;
		}
		return "showProductSales";
	}

	private String getSalesStaticsPicName() throws Exception {
		List<AnalysisReport> analysisReportList;
		SalesStaticsSrchDTO srchDTO = new SalesStaticsSrchDTO();
		srchDTO.setCustNo(custNo);
		srchDTO.setBeginDate(fromDate);
		srchDTO.setEndDate(toDate);
		srchDTO.setPeriod(periodType);
		System.out.println("srchDTO: " + srchDTO);
		analysisReportList = customerService.searchSalesStatistics(srchDTO);
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		for(int i=0; i< analysisReportList.size(); i++) {
			if(analysisReportList.get(i).getVisit() == 0){
				dataSet.addValue(null, "", String.valueOf(i+1));
			}else{
				dataSet.addValue(analysisReportList.get(i).getVisit(), "", String.valueOf(i+1));
			}
		}
		JFreeChart chart = null;
		String UNIT = "Hundred";
		
		if(periodType == 1){
			chart = ChartFactory.createBarChart(null, "Day", UNIT, dataSet, PlotOrientation.VERTICAL, false, false, false);
		}else if(periodType == 7){
			chart = ChartFactory.createBarChart(null, "Week", UNIT, dataSet, PlotOrientation.VERTICAL, false, false, false);
		}else if(periodType == 30){
			chart = ChartFactory.createBarChart(null, "Month", UNIT, dataSet, PlotOrientation.VERTICAL, false, false, false);
		}else if(periodType == 90){
			chart = ChartFactory.createBarChart(null, "Quarter", UNIT, dataSet, PlotOrientation.VERTICAL, false, false, false);
		}else{
			chart = ChartFactory.createBarChart(null, "Year", UNIT, dataSet, PlotOrientation.VERTICAL, false, false, false);
		}
//		Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);
//		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
//		chart.setAntiAlias(false);
		CategoryPlot plot = chart.getCategoryPlot();//设置图的高级属性 
		CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesPaint(0, Color.BLUE);
//		BarRenderer3D renderer = new BarRenderer3D();//3D属性修改
//		CategoryAxis domainAxis = plot.getDomainAxis();//对X轴做操作 
//		ValueAxis rAxis = plot.getRangeAxis();//对Y轴做操作 
//		domainAxis.setLabelFont(labelFont);
//		domainAxis.setTickLabelPaint(Color.GREEN);//X轴的标题文字颜色
//		domainAxis.setAxisLinePaint(Color.BLUE);//X轴横线颜色 
//		rAxis.setLabelPaint(Color.GREEN);
//		rAxis.setTickMarkPaint(Color.BLUE);

		plot.setBackgroundPaint(Color.LIGHT_GRAY);
		plot.setDomainGridlinePaint(Color.BLACK); 
		plot.setDomainGridlinesVisible(true); 
		//设置网格横线颜色 
		plot.setRangeGridlinePaint(Color.BLACK); 
		plot.setRangeGridlinesVisible(true); 
		String picName = SessionUtil.generateTempId();
		FileOutputStream fos = null;
		
		try{
			//String rootPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("\\");
			System.out.println(picName+"<<<<<<<<<<<<<<<<<<<");
			//System.out.println(System.getProperty("user.dir").replace("\\", "/"));
			System.out.println(this.getClass().getResource("").getPath().replaceAll("%20", " "));
			fos = new FileOutputStream("/usr/local/jboss-5.1.0.GA/server/default/deploy/ROOT.war"+"/images/temp/"+ picName + ".png");
			//fos = new FileOutputStream(rootPath+"/images/temp/"+ picName + ".png");
			ChartUtilities.writeChartAsPNG(fos, chart, 730, 200, null);
		}finally{
			try{
				if(fos!=null)
					fos.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return picName;
	}
	
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getPeriodType() {
		return periodType;
	}

	public void setPeriodType(Integer periodType) {
		this.periodType = periodType;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public String getSalesPeriod() {
		return salesPeriod;
	}

	public void setSalesPeriod(String salesPeriod) {
		this.salesPeriod = salesPeriod;
	}

	public List<SalesOrderedDTO> getSalesOrderedList() {
		return salesOrderedList;
	}

	public void setSalesOrderedList(List<SalesOrderedDTO> salesOrderedList) {
		this.salesOrderedList = salesOrderedList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if(top==null){
			top=5;
		}
		this.type = type;
	}
	
}
