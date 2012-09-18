package com.genscript.gsscm.report.web;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.dao.SpecDropDownListDao;
import com.genscript.gsscm.basedata.dto.CountryDTO;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.HostIpUtil;
import com.genscript.gsscm.common.MyX509TrustManager;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.dao.SalesRepDao;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.customer.entity.SalesTerritory;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.product.dao.ProductCategoryDao;
import com.genscript.gsscm.product.entity.ProductCategory;
import com.genscript.gsscm.quote.entity.QuoteMain;
import com.genscript.gsscm.report.dao.ReportDao;
import com.genscript.gsscm.report.dto.ReportDataDto;
import com.genscript.gsscm.report.service.ReportDataService;
import com.genscript.gsscm.serv.dao.ServiceCategoryDao;
import com.genscript.gsscm.serv.entity.ServiceCategory;
import com.genscript.gsscm.system.dao.DepartmentSystemDao;
import com.genscript.gsscm.system.entity.DepartmentSystem;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.beanutils.BeanUtils;
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

import javax.net.ssl.*;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 6/30/11
 * Time: 5:13 PM
 * quotation summary 查询
 */
@Results({
        @Result(name = "quotation_summary", location = "report/quotation_summary.jsp"),
        @Result(name = "sales_summary", location = "report/sales_order_summary.jsp"),
        @Result(name = "quotation_detail", location = "report/quotation_detail.jsp"),
        @Result(name = "sales_detail", location = "report/sales_order_detaill.jsp"),
        @Result(name = "sales_tax_profit", location = "report/sales_tax_profit.jsp"),
        @Result(name = "customer_payment", location = "report/customer_payment.jsp"),
        @Result(name = "sales_by_territory", location = "report/sales_by_territory.jsp"),
        @Result(name = "sales_by_Sales_Rep", location = "report/sales_by_Sales_Rep.jsp"),
        @Result(name = "sales_by_org", location = "report/sales_by_org.jsp"),
        @Result(name = "sales_by_country", location = "report/sales_by_country.jsp"),
        @Result(name = "sales_points", location = "report/sales_points.jsp"),
        @Result(name = "purchase_order_detail", location = "report/purchase_order_detail.jsp"),
        @Result(name = "work_order_summary", location = "report/work_order_summary.jsp"),
        @Result(name = "work_order_detail", location = "report/work_order_detail.jsp"),
        @Result(name = "receipt_detail", location = "report/receipt_detail.jsp"),
        @Result(name = "shipment_detail", location = "report/shipment_detail.jsp"),
        @Result(name = "coupon_gift", location = "report/coupon_gift.jsp"),
        @Result(name = "reportDataTable", location = "report/reportDataTable.jsp"),
        @Result(name = "reportDataTableForQS", location = "report/reportDataTableForQS.jsp"),
        @Result(name = "product_ser_sales", location = "report/product_ser_sales.jsp"),
        @Result(name = "reportDataPic", location = "report/reportDataPic.jsp"),
        @Result(name = "reportExport", location = "report/report_Excel.jsp"),
        @Result(name = "reportExportForQS", location = "report/report_Excel_ForQS.jsp")
})
public class ReportAction extends ActionSupport{
    @Autowired
    private ReportDataService reportDataService;
    @Autowired
	private ExceptionService exceptionUtil;
    private ReportDataDto reportDataDto;
    private Page<ReportDataDto> reportDataDtoPage;
    private HostIpUtil hostip = new HostIpUtil();
    //反射调用Service方法
    private String serviceMethod;
    //跳转页面
    private String actionPage;
    @Autowired
    private DepartmentSystemDao departmenSystemtDao;
    @Autowired
    private SalesRepDao salesRepDao;
    @Autowired
    private SpecDropDownListDao specDropDownListDao;
    @Autowired
	private PublicService publicService;
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Autowired
    private ReportDao reportDao;
    @Autowired
    private ServiceCategoryDao serviceCategoryDao;
    private List<DepartmentSystem> departmentses;
    private List<SalesRep> users;
    private List<DropDownDTO> organizations;
    private List<CountryDTO> countryList;
    private List<DropDownDTO> salesTerritories;
    //Product/Service Sales Summary
    private List<DropDownDTO> busUnits;
    private List<DropDownDTO> categories;
    private List<DropDownDTO> psLines;
    private List<DropDownDTO> vendorList;
    private List<DropDownDTO> workCenters;
    private List<DropDownDTO> workGroups;
    private List<DropDownDTO> receivingClerks;
    private List<DropDownDTO> shippingClerks;
    private List<DropDownDTO> shipperClerks;
    private List<DropDownDTO> salesManagers;
    private List<DropDownDTO> techManagers;
    private List<DropDownDTO> projectManagers;
    private List<DropDownDTO> shippingCarriers;
    private List<DropDownDTO> productServiceTypes;


     //quotation Summary 查询
    public String getQuotaionReportDate() throws Exception{
        if (null != reportDataDto) {
            //获得分页请求相关数据：如第几页
            PagerUtil<ReportDataDto> pagerUtil = new PagerUtil<ReportDataDto>();
            reportDataDtoPage = pagerUtil.getRequestPage();
            // 设置默认每页显示记录条数
            reportDataDtoPage.setPageSize(15);
            reportDataDto = reportDataService.getQuotationReportDate(reportDataDtoPage, reportDataDto, false);
            ServletActionContext.getRequest().setAttribute("pagerInfo", reportDataDtoPage);
            ServletActionContext.getRequest().setAttribute("reportData", reportDataDto.getReportData());
            ServletActionContext.getRequest().setAttribute("columns", reportDataDto.getColumn());
            ServletActionContext.getRequest().setAttribute("method", "getQuotaionReportDate");
            //reportDataDto.setReportPicName(searchAnalysis());
            return "reportDataTableForQS";
        }else{
            reportDataDto = new ReportDataDto();
            return "quotation_summary";
        }
    }

    //Quotation Detail 查询
    public String getQuotationDetail() throws Exception{
        if (null != reportDataDto) {
            //获得分页请求相关数据：如第几页
            PagerUtil<ReportDataDto> pagerUtil = new PagerUtil<ReportDataDto>();
            reportDataDtoPage = pagerUtil.getRequestPage();
            // 设置默认每页显示记录条数
            reportDataDtoPage.setPageSize(15);
            reportDataDto = reportDataService.getQuotationDetailReportDate(reportDataDtoPage, reportDataDto, false);
            ServletActionContext.getRequest().setAttribute("pagerInfo", reportDataDtoPage);
            ServletActionContext.getRequest().setAttribute("reportData", reportDataDto.getReportData());
            ServletActionContext.getRequest().setAttribute("columns", reportDataDto.getColumn());
            ServletActionContext.getRequest().setAttribute("method", "getQuotationDetail");
            return "reportDataTable";
        } else {
            reportDataDto = new ReportDataDto();
            return "quotation_detail";
        }
    }

    //Quotation Detail Excel 导出
    public String getQuotationDetailExcelExport() throws Exception{
        reportDataDto = reportDataService.getQuotationDetailReportDate(null, reportDataDto, true);
        ServletActionContext.getRequest().setAttribute("reportData", reportDataDto.getReportData());
        ServletActionContext.getRequest().setAttribute("columns", reportDataDto.getColumn());
        ServletActionContext.getRequest().setAttribute("excelName", "QuotationDetailReport");
        return "reportExport";
    }

    //Quotation Summary Excel 导出
     public String getQuotationSummaryExcelExport() throws Exception{
        reportDataDto = reportDataService.getQuotationReportDate(null, reportDataDto, true);
        ServletActionContext.getRequest().setAttribute("reportData", reportDataDto.getReportData());
        ServletActionContext.getRequest().setAttribute("columns", reportDataDto.getColumn());
        ServletActionContext.getRequest().setAttribute("excelName", "QuotationSummaryReport");
        return "reportExportForQS";
    }

    //Quotation Detail PDF 导出
    public void getQuotationDetailPDFExport() throws Exception{
        pdfExport("getQuotationDetailExcelExport", "quotationDetail");
    }

    //Quotation Summary PDF 导出
    public void getQuotationSummaryPDFExport() throws Exception{
        pdfExport("getQuotationSummaryExcelExport", "quotationSummary");
    }
//------------------------------------------------Sales Order Summary--------------------------------------------------
    //Sales Order Summary 查询
    public String getSalesSummaryReportDate() throws Exception{
        if (null != reportDataDto) {
            //获得分页请求相关数据：如第几页
            PagerUtil<ReportDataDto> pagerUtil = new PagerUtil<ReportDataDto>();
            reportDataDtoPage = pagerUtil.getRequestPage();
            // 设置默认每页显示记录条数
            reportDataDtoPage.setPageSize(15);
            reportDataDto = reportDataService.getSalesSummaryReportDate(reportDataDtoPage, reportDataDto, false);
            ServletActionContext.getRequest().setAttribute("pagerInfo", reportDataDtoPage);
            ServletActionContext.getRequest().setAttribute("reportData", reportDataDto.getReportData());
            ServletActionContext.getRequest().setAttribute("columns", reportDataDto.getColumn());
            ServletActionContext.getRequest().setAttribute("method", "getSalesSummaryReportDate");
            //reportDataDto.setReportPicName(searchAnalysis());
            return "reportDataTable";
        }else{
            reportDataDto = new ReportDataDto();
            return "sales_summary";
        }
    }

    //Sales Order Summary 导出
     public String getSalesSummaryExcelExport() throws Exception{
        reportDataDto = reportDataService.getSalesSummaryReportDate(null, reportDataDto, true);
        ServletActionContext.getRequest().setAttribute("reportData", reportDataDto.getReportData());
        ServletActionContext.getRequest().setAttribute("columns", reportDataDto.getColumn());
        ServletActionContext.getRequest().setAttribute("excelName", "SalesOrderSummaryReport");
        return "reportExport";
    }

    //Sales Order Summary  PDF 导出
    public void getSalesSummaryPDFExport() throws Exception{
        pdfExport("getSalesSummaryExcelExport", "salesSummary");
    }

//------------------------------------------------Sales Order Summary end--------------------------------------------------

//------------------------------------------------Sales Order Detail Begin--------------------------------------------------

    public String getSalesDetail() throws Exception{
        if (null != reportDataDto) {
            //获得分页请求相关数据：如第几页
            PagerUtil<ReportDataDto> pagerUtil = new PagerUtil<ReportDataDto>();
            reportDataDtoPage = pagerUtil.getRequestPage();
            // 设置默认每页显示记录条数
            reportDataDtoPage.setPageSize(15);
            reportDataDto = reportDataService.getSalesDetailReportDate(reportDataDtoPage, reportDataDto, false);
            ServletActionContext.getRequest().setAttribute("pagerInfo", reportDataDtoPage);
            ServletActionContext.getRequest().setAttribute("reportData", reportDataDto.getReportData());
            ServletActionContext.getRequest().setAttribute("columns", reportDataDto.getColumn());
            ServletActionContext.getRequest().setAttribute("method", "getSalesDetail");
            return "reportDataTable";
        } else {
            reportDataDto = new ReportDataDto();
            return "sales_detail";
        }
    }

    public String getSalesDetailExcelExport() throws Exception{
        reportDataDto = reportDataService.getSalesDetailReportDate(null, reportDataDto, true);
        ServletActionContext.getRequest().setAttribute("reportData", reportDataDto.getReportData());
        ServletActionContext.getRequest().setAttribute("columns", reportDataDto.getColumn());
        ServletActionContext.getRequest().setAttribute("excelName", "SalesDetailReport");
        return "reportExport";
    }

    public void getSalesDetailPDFExport() throws Exception{
        pdfExport("getSalesDetailExcelExport", "SalesOrderDetail");
    }

//------------------------------------------------Sales Order Detail end--------------------------------------------------

//------------------------------------------------通用方法 begin--------------------------------------------------
    public String getCommonMethodReportDate() throws Exception{
        if (null != reportDataDto) {
            //获得分页请求相关数据：如第几页
            PagerUtil<ReportDataDto> pagerUtil = new PagerUtil<ReportDataDto>();
            reportDataDtoPage = pagerUtil.getRequestPage();
            // 设置默认每页显示记录条数
            reportDataDtoPage.setPageSize(15);
            Class reportService = reportDataService.getClass();
            Class types[] ={Page.class, ReportDataDto.class, Boolean.class};
            Method method = reportService.getDeclaredMethod(serviceMethod, types);
            Object[] objects = {reportDataDtoPage, reportDataDto, false};
            reportDataDto = (ReportDataDto)method.invoke(reportDataService, objects);
            ServletActionContext.getRequest().setAttribute("pagerInfo", reportDataDtoPage);
            ServletActionContext.getRequest().setAttribute("reportData", reportDataDto.getReportData());
            ServletActionContext.getRequest().setAttribute("columns", reportDataDto.getColumn());
            ServletActionContext.getRequest().setAttribute("method", "getCommonMethodReportDate");
            //reportDataDto.setReportPicName(searchAnalysis());
            return "reportDataTable";
        }else{
            reportDataDto = new ReportDataDto();
            return actionPage;
        }
    }

     public String getCommonMethodExcelExport() throws Exception{
         Class reportService = reportDataService.getClass();
         Class types[] = {Page.class, ReportDataDto.class, Boolean.class};
         Method method = reportService.getDeclaredMethod(serviceMethod, types);
         Object[] objects = {null, reportDataDto, true};
        reportDataDto = (ReportDataDto) method.invoke(reportDataService, objects);
        ServletActionContext.getRequest().setAttribute("reportData", reportDataDto.getReportData());
        ServletActionContext.getRequest().setAttribute("columns", reportDataDto.getColumn());
        ServletActionContext.getRequest().setAttribute("excelName", actionPage);
        return "reportExport";
    }

    public void getCommonMethodPDFExport() throws Exception{
        pdfExport("getCommonMethodExcelExport", actionPage);
    }

    //图表导出数据
    public String getCommonMethodPicAnalysis() throws Exception{
        String statisticsColumn = Struts2Util.getParameter("statisticsColumn") == null ? "total" : Struts2Util.getParameter("statisticsColumn");
        Class reportService = reportDataService.getClass();
         Class types[] = {Page.class, ReportDataDto.class, Boolean.class};
         Method method = reportService.getDeclaredMethod(serviceMethod, types);
         Object[] objects = {null, reportDataDto, true};
        reportDataDto = (ReportDataDto) method.invoke(reportDataService, objects);
        reportDataDto.setReportPicName(searchAnalysis(statisticsColumn));
        return "reportDataPic";
    }

//------------------------------------------------通用方法 end--------------------------------------------------

    //PDF 导出
    @SuppressWarnings("static-access")
    public void pdfExport(String method, String fileName) throws Exception {
        //把请求参数组成String放入post请求中
        StringBuffer params = new StringBuffer("");
        if(null != reportDataDto){
            Map reportDataMap = BeanUtils.describe(reportDataDto);
            Iterator it = reportDataMap.keySet().iterator();
            while (it.hasNext()){
                String dtoName = (String)it.next();
                //reportDataDto 中新加的非String 类型需要在这里特殊处理
                if("class".equals(dtoName)){
                    continue;
                }else if("status".equals(dtoName)){
                    String[] statusList = BeanUtils.getArrayProperty(reportDataDto, dtoName);
                    if (statusList != null) {
                        for (String status : statusList) {
                            params.append("reportDataDto." + dtoName + "=" + status + "&");
                        }
                    }
                }else if("column".equals(dtoName)){
                    String[] columnsList = BeanUtils.getArrayProperty(reportDataDto, dtoName);
                    for(String column : columnsList){
                        params.append("reportDataDto." + dtoName + "=" + column + "&");
                    }
                }else {
                    String dtoValue = BeanUtils.getProperty(reportDataDto, dtoName);
                    if(dtoValue != null && !"null".equals(dtoValue) && !"".equals(dtoValue)){
                        params.append("reportDataDto." + dtoName + "=" + BeanUtils.getProperty(reportDataDto, dtoName) + "&");
                    }
                }
            }
        }
        if(params.length() > 0){
            params = params.deleteCharAt(params.length() -1);
        }
        HttpURLConnection con = null;
        HttpsURLConnection connection = null;
        URL url = null;
        Process p = null;
//        String cmd = "wkhtmltopdf";
        String cmd = "html2pdf";
        String newip = this.hostip.getLocalIP();
        try {
            String sessionid = Struts2Util.getRequest().getSession().getId();
            String port = "";
            if (Struts2Util.getRequest().getServerPort() != 80) {
                port = ":" + Struts2Util.getRequest().getServerPort();
            }
            String basePath = Struts2Util.getRequest().getScheme() + "://"+ Struts2Util.getRequest().getServerName() + port + Struts2Util.getRequest().getContextPath() + "/";
            if(method == null){
                method = serviceMethod;
            }
            url = new URL(basePath + "report/report!" + method + ".action?serviceMethod=" + serviceMethod + "&actionPage=" + actionPage);
            if (basePath.startsWith("https://")) {
                // 创建SSLContext对象，并使用我们指定的信任管理器初始化
                TrustManager[] tm = {new MyX509TrustManager()};
                SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
                sslContext.init(null, tm, new java.security.SecureRandom());
                // 从上述SSLContext对象中得到SSLSocketFactory对象
                SSLSocketFactory ssf = sslContext.getSocketFactory();
                HostnameVerifier hv = new HostnameVerifier() {
                    public boolean verify(String urlHostName, SSLSession session)
                    {
                        System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
                        return true;
                    }
                };
                HttpsURLConnection.setDefaultHostnameVerifier(hv);
                connection = (HttpsURLConnection) url.openConnection();
                connection.setSSLSocketFactory(ssf);
                connection.setRequestProperty("Cookie", "JSESSIONID=" + sessionid);
                //post 数据模拟
                connection.setDoOutput(true);
                //StringBuffer params = new StringBuffer("status=Open&currency=USD");
                byte[] b = params.toString().getBytes();
                connection.getOutputStream().write(b, 0, b.length);
                connection.connect();
            } else {
                con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("Cookie", "JSESSIONID=" + sessionid);
                //post 数据模拟
                con.setDoOutput(true);
                //StringBuffer params = new StringBuffer("reportDataDto.status=Open&reportDataDto.status=CL&reportDataDto.currency=USD");
                byte[] b = params.toString().getBytes();
                con.getOutputStream().write(b, 0, b.length);
                con.connect();
            }
            int size = 0;
            byte[] buf = new byte[1024];
            BufferedInputStream bis = null;
            if (con != null) {
                bis = new BufferedInputStream(con.getInputStream());
            } else {
                bis = new BufferedInputStream(connection.getInputStream());
            }
            StringBuffer strb = new StringBuffer();
            while ((size = bis.read(buf)) != -1) {
                strb.append(new String(buf, 0, size));
            }
            FileWriter writer = null;
//            File file = new File("D:/tmp/"+fileName+".html");
            File file = new File("/tmp/"+fileName+".html");
            writer = new FileWriter(file);
            writer.write(strb.toString());
            writer.flush();
            writer.close();
//            ProcessBuilder pb = new ProcessBuilder(cmd, "D:/tmp/"+fileName+".html", "D:/tmp/"+fileName+".pdf");
            ProcessBuilder pb = new ProcessBuilder(cmd, "/tmp/"+fileName+".html", "/tmp/"+fileName+".pdf");
            pb.redirectErrorStream(true);
            p = pb.start();
            InputStreamReader ir = new InputStreamReader(p.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            // 提示下载
            HttpServletResponse response = Struts2Util.getResponse();
            response.setContentType("APPLICATION/DOWNLOAD;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + (new Random()).nextInt() + ".pdf");// PackingSlip是文件名
            java.io.OutputStream os = response.getOutputStream();
//            java.io.FileInputStream fis = new java.io.FileInputStream("D:/tmp/"+fileName+".pdf");
            java.io.FileInputStream fis = new java.io.FileInputStream("/tmp/"+fileName+".pdf");
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = fis.read(b)) > 0) {
                os.write(b, 0, i);
            }
            fis.close();
            os.flush();
            response.flushBuffer();
            os.close();
            bis.close();
            if (con != null) {
                con.disconnect();
            } else {
                connection.disconnect();
            }
        } catch (Exception e) {
            if (p != null)
                p.destroy();
            System.out.println("Error exec!");
            WSException exDTO = exceptionUtil.getExceptionDetails(e);
            exceptionUtil.logException(exDTO, this.getClass(), e, new Exception().getStackTrace()[0].getMethodName(), "INTF0203", SessionUtil.getUserId());
            Struts2Util.renderText(exDTO.getMessageDetail() + "\n" + exDTO.getAction());
            e.printStackTrace();
        }
    }

    /*
	 * product sales 图片显示;
	 */
	private String searchAnalysis(String statisticsColumn) throws Exception {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        String title = "";
        String name= "";
        String perType = reportDataDto.getPeriodType();
        if ("day".equals(perType)) {
			title = "Quotations for Day";
            name = "Day";
		} else if ("week".equals(perType)) {
			title = "Quotations for Week";
            name = "Week";
		} else if ("month".equals(perType)) {
			title = "Month for Month";
            name = "Month";
		} else if ("quarter".equals(perType)) {
            title = "Quotations for Quarter";
            name = "Quarter";
		} else if ("year".equals(perType)) {
			title = "Quotations for Year";
            name = "Year";
		}
        List analysisReportList = reportDataDto.getReportData();
        for (Object reportData : analysisReportList) {
            /*if (analysisReportList.get(i).getVisit() == 0) {
                   dataSet.addValue(null, "", String.valueOf(i + 1));
               } else*/
            String fromDate = BeanUtils.getProperty(reportData, "fromDate");
            String dateInt = BeanUtils.getProperty(reportData, "dateInt");
            double total = Double.parseDouble(BeanUtils.getProperty(reportData, statisticsColumn));
            if ("day".equals(perType)) {
                dataSet.addValue(total, "", fromDate);
            } else if ("week".equals(perType)) {
                dataSet.addValue(total, "", fromDate.split("-")[0] + "/" + (Integer.parseInt(dateInt) + 1));
            } else if ("month".equals(perType)) {
                dataSet.addValue(total, "", fromDate.split("-")[0] + "/" + dateInt);
            } else if ("quarter".equals(perType)) {
                dataSet.addValue(total, "", fromDate.split("-")[0] + "/" + dateInt);
            } else if ("year".equals(perType)) {
                dataSet.addValue(total, "", "" + dateInt);
            }
        }
		JFreeChart chart = null;

		chart = ChartFactory.createBarChart(null, name, title, dataSet, PlotOrientation.VERTICAL, false, false, false);
		// Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);
		// chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
		// RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		// chart.setAntiAlias(false);
		CategoryPlot plot = chart.getCategoryPlot();// 设置图的高级属性
		CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesPaint(0, Color.BLUE);
		// BarRenderer3D renderer = new BarRenderer3D();//3D属性修改
		// CategoryAxis domainAxis = plot.getDomainAxis();//对X轴做操作
		// ValueAxis rAxis = plot.getRangeAxis();//对Y轴做操作
		// domainAxis.setLabelFont(labelFont);
		// domainAxis.setTickLabelPaint(Color.GREEN);//X轴的标题文字颜色
		// domainAxis.setAxisLinePaint(Color.BLUE);//X轴横线颜色
		// rAxis.setLabelPaint(Color.GREEN);
		// rAxis.setTickMarkPaint(Color.BLUE);

		plot.setBackgroundPaint(Color.LIGHT_GRAY);
		plot.setDomainGridlinePaint(Color.BLACK);
		plot.setDomainGridlinesVisible(true);
		// 设置网格横线颜色
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlinesVisible(true);
		String picName = SessionUtil.generateTempId();
		FileOutputStream fos = null;

		try {
//			String rootPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("\\")+"temp\\";
            String rootPath = "/usr/local/jboss/server/default/deploy/ROOT.war/images/temp/";
			fos = new FileOutputStream(rootPath + picName + ".png");
			ChartUtilities.writeChartAsPNG(fos, chart, 1030, 200, null);
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return picName;
	}
    //Ajax 提交数据
    public String getCategoryData(){
        String segmentType = ServletActionContext.getRequest().getParameter("segmentType");
        if("service".equals(segmentType)){
            psLines = serviceCategoryDao.getCategoryList(3);
            categories = serviceCategoryDao.getCategoryList(2);
            busUnits = serviceCategoryDao.getCategoryList(1);
        }else{
            psLines = productCategoryDao.getCategoryList(3);
            categories = productCategoryDao.getCategoryList(2);
            busUnits = productCategoryDao.getCategoryList(1);
        }
        HashMap<String, List<DropDownDTO>> map = new HashMap<String, List<DropDownDTO>>();
        map.put("psLines", psLines);
        map.put("categories", categories);
        map.put("busUnits", busUnits);
        Struts2Util.renderJson(map);
        return null;
    }

    public ReportDataDto getReportDataDto() {
        return reportDataDto;
    }

    public void setReportDataDto(ReportDataDto reportDataDto) {
        this.reportDataDto = reportDataDto;
    }

    public Page<ReportDataDto> getReportDataDtoPage() {
        return reportDataDtoPage;
    }

    public void setReportDataDtoPage(Page<ReportDataDto> reportDataDtoPage) {
        this.reportDataDtoPage = reportDataDtoPage;
    }

    public String getServiceMethod() {
        return serviceMethod;
    }

    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    public String getActionPage() {
        return actionPage;
    }

    public void setActionPage(String actionPage) {
        this.actionPage = actionPage;
    }

    @SuppressWarnings("unchecked")
    public List<DepartmentSystem> getDepartmentses() {
        departmentses = departmenSystemtDao.findAll("name", "");
        DepartmentSystem deptSys = new DepartmentSystem();
        deptSys.setDeptId(-1);
        deptSys.setCompanyId(-1);
        deptSys.setName("All");
        deptSys.setDescription("All");
        departmentses.add(0, deptSys);
        return departmentses;
    }

    public void setDepartmentses(List<DepartmentSystem> departmentses) {
        this.departmentses = departmentses;
    }

    @SuppressWarnings("unchecked")
    public List<SalesRep> getUsers() {
        users = salesRepDao.findSalesRep("SALES_CONTACT");
        SalesRep salesRep = new SalesRep();
        salesRep.setSalesId(-1);
        salesRep.setResourceName("All");
        users.add(0, salesRep);
        return users;
    }

    public void setUsers(List<SalesRep> users) {
        this.users = users;
    }

    public List<DropDownDTO> getOrganizations() {
        organizations = specDropDownListDao.getSpecDropDownList("ORGANIZATION_TYPE");
        DropDownDTO dto = new DropDownDTO();
        dto.setId("");
        dto.setName("All");
        organizations.add(0, dto);
        return organizations;
    }

    public void setOrganizations(List<DropDownDTO> organizations) {
        this.organizations = organizations;
    }

    public List<CountryDTO> getCountryList() {
        countryList = publicService.getCountryList();
        return countryList;
    }

    public void setCountryList(List<CountryDTO> countryList) {
        this.countryList = countryList;
    }

    public List<DropDownDTO> getSalesTerritories() {
        salesTerritories = specDropDownListDao.getSpecDropDownList("SALES_TERRITORY_OTHER");
        //添加All选项
        DropDownDTO dto = new DropDownDTO();
        dto.setId("");
        dto.setName("All");
        salesTerritories.add(0, dto);
        return salesTerritories;
    }

    public void setSalesTerritories(List<DropDownDTO> salesTerritories) {
        this.salesTerritories = salesTerritories;
    }

    public List<DropDownDTO> getPsLines() {
        if("service".equals(reportDataDto.getSegmentType())){
            psLines = serviceCategoryDao.getCategoryList(3);
        }else{
            psLines = productCategoryDao.getCategoryList(3);
        }
        return psLines;
    }

    public void setPsLines(List<DropDownDTO> psLines) {
        this.psLines = psLines;
    }

    public List<DropDownDTO> getCategories() {
        if("service".equals(reportDataDto.getSegmentType())){
            categories = serviceCategoryDao.getCategoryList(2);
        }else{
            categories = productCategoryDao.getCategoryList(2);
        }
        return categories;
    }

    public void setCategories(List<DropDownDTO> categories) {
        this.categories = categories;
    }

    public List<DropDownDTO> getBusUnits() {
        if("service".equals(reportDataDto.getSegmentType())){
            busUnits = serviceCategoryDao.getCategoryList(1);
        }else{
            busUnits = productCategoryDao.getCategoryList(1);
        }
        return busUnits;
    }

    public void setBusUnits(List<DropDownDTO> busUnits) {
        this.busUnits = busUnits;
    }

    public List<DropDownDTO> getVendorList() {
        vendorList = reportDao.getVendorList();
        DropDownDTO dto = new DropDownDTO();
        dto.setId("-1");
        dto.setName("All");
        vendorList.add(0, dto);
        return vendorList;
    }

    public void setVendorList(List<DropDownDTO> vendorList) {
        this.vendorList = vendorList;
    }

    public List<DropDownDTO> getWorkCenters() {
        workCenters = reportDao.getCommonList("select id, name from WorkCenter order by name");
        /*DropDownDTO dto = new DropDownDTO();
        dto.setId();
        dto.setName("All");
        workCenters.add(0, dto);*/
        return workCenters;
    }

    public void setWorkCenters(List<DropDownDTO> workCenters) {
        this.workCenters = workCenters;
    }

    //Ajax Work Groups
    public String getWorkGroupsByAjax(){
        String work_center_id = ServletActionContext.getRequest().getParameter("work_center_id");
        workGroups = reportDao.getCommonList("select id, name from WorkGroup where work_center_id=" + work_center_id + " order by name ");
        DropDownDTO dto = new DropDownDTO();
        dto.setId("-1");
        dto.setName("All");
        workGroups.add(0, dto);
        Struts2Util.renderJson(workGroups);
        return null;
    }

    public List<DropDownDTO> getWorkGroups() {
//        workGroups = reportDao.getCommonList("select id, name from WorkGroup order by name");
        workGroups = new ArrayList<DropDownDTO>();
        DropDownDTO dto = new DropDownDTO();
        dto.setId("-1");
        dto.setName("All");
        workGroups.add(0, dto);
        return workGroups;
    }

    public void setWorkGroups(List<DropDownDTO> workGroups) {
        this.workGroups = workGroups;
    }

    //Shipping Clerk    , Receiving Clerk
    public List<DropDownDTO> getReceivingClerks() {
        receivingClerks = reportDao.getCommonListBySql("select cl.clerk_id,us.login_name from shipping.shipping_clerks cl,system.users us where cl.clerk_id=us.user_id and cl.clerk_function in ('ALL', 'Receiver') and us.user_id=" + SessionUtil.getUserId() + " order by us.login_name");
        if ((receivingClerks.size() > 0 && "ritat".equals(receivingClerks.get(0).getName())) || SessionUtil.getUserId() == -33) {
            receivingClerks = reportDao.getCommonListBySql("select cl.clerk_id,us.login_name from shipping.shipping_clerks cl,system.users us where cl.clerk_id=us.user_id and cl.clerk_function in ('ALL', 'Receiver') order by us.login_name");
            DropDownDTO dto = new DropDownDTO();
            dto.setId("-1");
            dto.setName("All");
            receivingClerks.add(0, dto);
        }
        return receivingClerks;
    }

    public void setReceivingClerks(List<DropDownDTO> receivingClerks) {
        this.receivingClerks = receivingClerks;
    }

    public List<DropDownDTO> getShipperClerks() {
        shipperClerks = reportDao.getCommonListBySql("select cl.clerk_id,us.login_name from shipping.shipping_clerks cl,system.users us where cl.clerk_id=us.user_id and cl.clerk_function in ('ALL', 'Receiver') and us.user_id=" + SessionUtil.getUserId() + " order by us.login_name");
        if ((shipperClerks.size() > 0 && "ritat".equals(shipperClerks.get(0).getName())) || SessionUtil.getUserId() == -33) {
            shipperClerks = reportDao.getCommonListBySql("select cl.clerk_id,us.login_name from shipping.shipping_clerks cl,system.users us where cl.clerk_id=us.user_id and cl.clerk_function in ('ALL', 'Receiver') order by us.login_name");
            DropDownDTO dto = new DropDownDTO();
            dto.setId("-1");
            dto.setName("All");
            shipperClerks.add(0, dto);
        }
        return shippingClerks;
    }

    public void setShipperClerks(List<DropDownDTO> shipperClerks) {
        this.shipperClerks = shipperClerks;
    }

    public List<DropDownDTO> getShippingClerks() {
        shippingClerks = reportDao.getCommonListBySql("select cl.clerk_id,us.login_name from shipping.shipping_clerks cl,system.users us where cl.clerk_id=us.user_id and cl.clerk_function in ('ALL', 'Receiver') and us.user_id=" + SessionUtil.getUserId() + " order by us.login_name");
        if ((shippingClerks.size() > 0 && "ritat".equals(shippingClerks.get(0).getName())) || SessionUtil.getUserId() == -33) {
            shippingClerks = reportDao.getCommonListBySql("select cl.clerk_id,us.login_name from shipping.shipping_clerks cl,system.users us where cl.clerk_id=us.user_id and cl.clerk_function in ('ALL', 'Receiver') order by us.login_name");
        }
        DropDownDTO dto = new DropDownDTO();
        dto.setId("-1");
        dto.setName("All");
        shippingClerks.add(0, dto);
        return shippingClerks;
    }

    public void setShippingClerks(List<DropDownDTO> shippingClerks) {
        this.shippingClerks = shippingClerks;
    }

    public List<DropDownDTO> getSalesManagers() {
        salesManagers = reportDao.getCommonListBySql("select sales_id,resource_name from customer.sales_rep where function='SALES_CONTACT'");
        DropDownDTO dto = new DropDownDTO();
        dto.setId("");
        dto.setName("All");
        salesManagers.add(0, dto);
        return salesManagers;
    }

    public void setSalesManagers(List<DropDownDTO> salesManagers) {
        this.salesManagers = salesManagers;
    }

    public List<DropDownDTO> getTechManagers() {
        techManagers = reportDao.getCommonListBySql("select sales_id,resource_name from customer.sales_rep where function='TECH_SUPPORT'");
        DropDownDTO dto = new DropDownDTO();
        dto.setId("");
        dto.setName("All");
        techManagers.add(0, dto);
        return techManagers;
    }

    public void setTechManagers(List<DropDownDTO> techManagers) {
        this.techManagers = techManagers;
    }

    public List<DropDownDTO> getProjectManagers() {
        projectManagers = reportDao.getCommonListBySql("select distinct us.user_id,us.login_name from system.users us,order.quote qu where qu.project_manager=us.user_id order by us.login_name");
        DropDownDTO dto = new DropDownDTO();
        dto.setId("");
        dto.setName("All");
        projectManagers.add(0, dto);
        return projectManagers;
    }

    public void setProjectManagers(List<DropDownDTO> projectManagers) {
        this.projectManagers = projectManagers;
    }

    public List<DropDownDTO> getShippingCarriers() {
        shippingCarriers = reportDao.getCommonListBySql("select carrier_id,name from shipping.ship_carriers group by name");
        DropDownDTO dto = new DropDownDTO();
        dto.setId("-1");
        dto.setName("All");
        shippingCarriers.add(0, dto);
        return shippingCarriers;
    }

    public void setShippingCarriers(List<DropDownDTO> shippingCarriers) {
        this.shippingCarriers = shippingCarriers;
    }

    public List<DropDownDTO> getProductServiceTypes() {
        productServiceTypes = reportDao.getCommonListBySql("select concat_ws('@@',cast(cls_id as CHAR),'PRODUCT') as cls_type,name from product.product_classification\n" +
                "union select concat_ws('@@', cast(cls_id as char),'SERVICE') as cls_type,name from product.service_classification");
        DropDownDTO dto = new DropDownDTO();
        dto.setId("");
        dto.setName("All");
        productServiceTypes.add(0, dto);
        return productServiceTypes;
    }

    public void setProductServiceTypes(List<DropDownDTO> productServiceTypes) {
        this.productServiceTypes = productServiceTypes;
    }
}
