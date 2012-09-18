package com.genscript.gsscm.customer.web;

import java.awt.Color;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.*;

import com.genscript.gsscm.customer.dto.*;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.service.OrderService;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.entity.AccessAnalysis;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.product.dto.ProductViewDTO;
import com.genscript.gsscm.serv.dto.ServiceViewDTO;
import com.opensymphony.xwork2.ActionSupport;

@Results({
        @Result(location = "customer/customer_web_behavior.jsp"),
        @Result(name = "pageView", location = "customer/customer_pageView.jsp"),
        @Result(name = "productView", location = "customer/customer_productView.jsp"),
        @Result(name = "serviceView", location = "customer/customer_serviceView.jsp"),
        @Result(name = "clickStream", location = "customer/customer_clickStream.jsp"),
        @Result(name = "sampleRequest", location = "customer/customer_simpleRequest.jsp")
}
)
public class CustomerWebBehaviorAction extends ActionSupport {
    private static final long serialVersionUID = -3694975735786655453L;
    @Autowired
    private CustomerService customerService;
    private Page<PageViewDTO> pageViewDTOList = null;
    private Page<ProductViewDTO> custProductViewDTOList = null;
    private Page<ServiceViewDTO> custServiceViewList = null;
    private Page<SampleRequestDTO> sampleRequestDTOList = null;
    private Page<AnalysisDTO> pageAnalysisDTO;
    private Page<AccessAnalysis> pageAccessAnalysis;
    private Page<AccessAnalysis> pageCustServiceAccessAnalysis;
    private SampleRequestDTO sampleRequestDTO;
    private Page<SampleRequestDTO> pageSampleRequestDTO;
    private Page<AccessAnalysis> pageclickStream;
    private AccessStatDTO accessStatDTO;
    private AccessAnalysis accessAnalysis;
    private Integer rowId = 0;
    private WebBehaviorPeriodDTO webBehaviorPeriodDTO;
    private String custNo;
    private String beginDate;
    private String endDate;
    private int period;
    private List<AnalysisReport> analysisReportList;
    @Autowired
    private DozerBeanMapper dozer;

    @Autowired
    private OrderService orderService;

    @Override
    public String execute() {
        if (StringUtils.isNotBlank(custNo) && StringUtils.isNumeric(custNo)) {
            Integer cust_no = Integer.parseInt(ServletActionContext.getRequest().getParameter("custNo"));
            ServletActionContext.getRequest().setAttribute("cust_no", cust_no);
        }
        return SUCCESS;
    }

    public String getSimple() {
        return "simple";
    }

    /**
     * 通过页面获取CustNo,设置page对象参数,取得pageView数据
     *
     * @return null;
     * @author zhangyang
     */
    public String getPageViewList() {
        // 使用HashMap对象把取得的数据以键值的方式存放
        // Map<String, Object> rt = new HashMap<String, Object>();
        ServletActionContext.getRequest().setAttribute("count", 0);
        try {
            // 获得页面的Customer_No
            if (StringUtils.isNotBlank(custNo) && StringUtils.isNumeric(custNo)) {
                Integer cust_no = Integer.parseInt(ServletActionContext.getRequest()
                        .getParameter("cust_no"));
                // 获得PageUtil对象用于获取
                PagerUtil<AccessAnalysis> pageUtil = new PagerUtil<AccessAnalysis>();
                Integer pageNo = pageUtil.getRequestPage().getPageNo();
                //设置rowId变量，用来做为显示每页的记录号
                if (pageNo != 0) {
                    rowId = (pageNo - 1) * 5 + 1;
                }
                // 通过PageUtil获取某类的分页信息
                pageAccessAnalysis = pageUtil.getRequestPage();
                // 设置分页的参数
                if (!pageAccessAnalysis.isOrderBySetted()) {
                    pageAccessAnalysis.setOrderBy("custNo");
                    pageAccessAnalysis.setOrder(Page.ASC);
                    pageAccessAnalysis.setPageNo(pageNo);
                }
                pageAccessAnalysis.setPageSize(5);
                // 获取pageView数据列表信息
                pageViewDTOList = this.customerService.getAccessPageView(
                        pageAccessAnalysis, cust_no);
                // 此循环让显示的日期以yyyy-MM-dd的形式显示
                for (int i = 0; i < pageViewDTOList.getResult().size(); i++) {
                    String firstView = pageViewDTOList.getResult().get(i)
                            .getFirstView().substring(0, 10);
                    String lastView = pageViewDTOList.getResult().get(i)
                            .getLastView().substring(0, 10);
                    pageViewDTOList.getResult().get(i).setFirstView(firstView);
                    pageViewDTOList.getResult().get(i).setLastView(lastView);
                }
                // rt.put("message", pageViewDTOList);
                // 把显示数据放在request里，通过页面获取
                Integer count = pageViewDTOList.getResult().size();
                ServletActionContext.getRequest().setAttribute("count", count);
                ServletActionContext.getRequest().setAttribute("pagerInfo", pageViewDTOList);
                ServletActionContext.getRequest().setAttribute("rowId", rowId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 把HashMap对象以Json的方式发送到页面
        // Struts2Util.renderJson(rt);
        return "pageView";
    }

    /**
     * 通过页面获取CustNo,设置page对象参数,取得productView数据 return null;
     *
     * @author zhangyang
     */
    public String getProductViewList() {
        ServletActionContext.getRequest().setAttribute("count", 0);
        try {
            // 获得页面的Customer_No
            if (StringUtils.isNotBlank(custNo) && StringUtils.isNumeric(custNo)) {
                Integer cust_no = Integer.parseInt(ServletActionContext.getRequest()
                        .getParameter("cust_no"));
                // 获得PageUtil对象用于获取
                PagerUtil<AccessAnalysis> pageUtil = new PagerUtil<AccessAnalysis>();
                Integer pageNo = pageUtil.getRequestPage().getPageNo();
                //设置rowId变量，用来做为显示每页的记录号
                if (pageNo != 0) {
                    rowId = (pageNo - 1) * 5 + 1;
                }
                // 通过PageUtil获取某类的分页信息
                pageAccessAnalysis = pageUtil.getRequestPage();
                if (!pageAccessAnalysis.isOrderBySetted()) {
                    pageAccessAnalysis.setOrderBy("custNo");
                    pageAccessAnalysis.setOrder(Page.ASC);
                    pageAccessAnalysis.setPageNo(pageNo);
                }
                pageAccessAnalysis.setPageSize(5);
                // 获取ProductView数据列表信息
                custProductViewDTOList = this.customerService.getAccessProductView(
                        pageAccessAnalysis, cust_no);
                // 此循环让显示的日期以yyyy-MM-dd的形式显示
                for (int i = 0; i < custProductViewDTOList.getResult().size(); i++) {
                    String firstView = custProductViewDTOList.getResult().get(i)
                            .getFirstView().substring(0, 10);
                    String lastView = custProductViewDTOList.getResult().get(i)
                            .getLastView().substring(0, 10);
                    custProductViewDTOList.getResult().get(i).setFirstView(
                            firstView);
                    custProductViewDTOList.getResult().get(i).setLastView(lastView);
                }
                // 获取每页显示的记录
                Integer pageCount = custProductViewDTOList.getResult().size();
                ServletActionContext.getRequest().setAttribute("count", pageCount);
                ServletActionContext.getRequest().setAttribute("pagerInfo",
                        custProductViewDTOList);
                ServletActionContext.getRequest().setAttribute("rowId", rowId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "productView";
    }

    /**
     * 通过获取custNO 设置 simpleRequest对象
     * 周刚 2011 4 -27
     */


    public SampleRequestDTO getSampleRequestDTO() {
        return sampleRequestDTO;
    }

    public void setSampleRequestDTO(SampleRequestDTO sampleRequestDTO) {
        this.sampleRequestDTO = sampleRequestDTO;
    }


    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }


    /**
     * zhougang       2011 4 - 28
     * <p/>
     * 未分页时使用
     * @return
     * @throws Exception
     */
    /* public String getSampleRequestViewList2() throws Exception {

            Integer cust_no = Integer.parseInt(ServletActionContext.getRequest().getParameter("cust_no"));
            // ServletActionContext.getRequest().setAttribute("count", 0);
            List allSampleRequestlist;
            allSampleRequestlist = orderService.getSampleRequestByCustNo(cust_no);

            sampleRequestDTOList = new ArrayList<SampleRequestDTO>();
            if (allSampleRequestlist != null) {
                for (Object anAllSampleRequestlist : allSampleRequestlist) {
                    Object obj[] = (Object[]) anAllSampleRequestlist;
                    sampleRequestDTO = new SampleRequestDTO();
                    if (!"".equals(obj[0]) && obj[0] != null) {
                        sampleRequestDTO.setSeqNo(Integer.parseInt(obj[0].toString()));
                    }
                    if (!"".equals(obj[6]) && obj[6] != null) {
                        sampleRequestDTO.setDeliveryDate(obj[6].toString());
                    }
                    if (!"".equals(obj[2]) && obj[2] != null) {
                        sampleRequestDTO.setRequestDate(obj[2].toString());
                    }

                    if (!"".equals(obj[1]) && obj[1] != null) {
                        sampleRequestDTO.setSampleName(obj[1].toString());
                    }
                    if (!"".equals(obj[7]) && obj[7] != null) {   //product_id -->url
                        System.out.println(obj[7]);
                        sampleRequestDTO.setUrl(orderService.getUrlByProductId(Integer.parseInt(obj[7].toString())));
                    }
                    if (!"".equals(obj[3]) && obj[3] != null) {
                        sampleRequestDTO.setNote(obj[3].toString());
                    }
                    sampleRequestDTOList.add(sampleRequestDTO);
                }
            }
            PagerUtil<SampleRequestDTO> pageUtil = new PagerUtil<SampleRequestDTO>();
            Integer pageNo = pageUtil.getRequestPage().getPageNo();
            if (pageNo != 0) {
                rowId = (pageNo - 1) * 5 + 1;
            }
            pageSampleRequestDTO = pageUtil.getRequestPage();

            if (!pageSampleRequestDTO.isOrderBySetted()) {
                pageSampleRequestDTO.setOrderBy("seqNo");
                pageSampleRequestDTO
                        .setOrder(Page.ASC);
                pageSampleRequestDTO.setPageNo(pageNo);
            }
            pageSampleRequestDTO.setPageSize(5);
            //    sampleRequestDTOList = this.customerService.getAccessServiceView(
            //          pageSampleRequestDTO, cust_no);

            ServletActionContext.getRequest().setAttribute("sampleRequestDTOList", sampleRequestDTOList);

            return "sampleRequest";
        }
    */

    /**
     * 分页方法：
     * 2011 4 28
     * 周刚
     * getSampleRequestViewList
     */
    public String getSampleRequestViewList() {
        Integer cust_no=0;
        ServletActionContext.getRequest().setAttribute("count", 0);
        String strcustNO=ServletActionContext.getRequest().getParameter("cust_no");
        if(strcustNO!=null && !"".equals(strcustNO)){
              cust_no= Integer.parseInt(strcustNO);
        }
        PagerUtil<SampleRequestDTO> pagerUtil = new PagerUtil<SampleRequestDTO>();
        Integer pageNo = pagerUtil.getRequestPage().getPageNo();
        if (pageNo != 0) {
            rowId = (pageNo - 1) * 5 + 1;
        }
        pageSampleRequestDTO = pagerUtil.getRequestPage();
        if (!pageSampleRequestDTO.isOrderBySetted()) {
            pageSampleRequestDTO.setOrderBy("seqNo");
            pageSampleRequestDTO.setOrder(Page.ASC);
            pageSampleRequestDTO.setPageNo(pageNo);
        }
        pageSampleRequestDTO.setPageSize(5);
        sampleRequestDTOList = this.orderService.getSampleRequestDTOPage(pageSampleRequestDTO, cust_no);
        Integer pageCount = sampleRequestDTOList.getResult().size();

        ServletActionContext.getRequest().setAttribute("count", pageCount);
        ServletActionContext.getRequest().setAttribute("pagerInfo", sampleRequestDTOList);
        return "sampleRequest";
    }

    public Page<SampleRequestDTO> getSampleRequestDTOList() {
        return sampleRequestDTOList;
    }

    public void setSampleRequestDTOList(Page<SampleRequestDTO> sampleRequestDTOList) {
        this.sampleRequestDTOList = sampleRequestDTOList;
    }

    /**
     * 通过页面获取CustNo,设置page对象参数,取得serviceView数据
     * return null;
     *
     * @author zhangyang
     */
    public String getServiceViewList() {
        // 获得页面的Customer_No
        ServletActionContext.getRequest().setAttribute("count", 0);
        if (StringUtils.isNotBlank(custNo) && StringUtils.isNumeric(custNo)) {
            Integer cust_no = Integer.parseInt(ServletActionContext.getRequest().getParameter("cust_no"));
            System.out.println("-----------------serviceNo : " + cust_no);
            // 获得PageUtil对象用于获取
            PagerUtil<AccessAnalysis> pageUtil = new PagerUtil<AccessAnalysis>();
            Integer pageNo = pageUtil.getRequestPage().getPageNo();
            //设置rowId变量，用来做为显示每页的记录号
            if (pageNo != 0) {
                rowId = (pageNo - 1) * 5 + 1;
            }
            // 通过PageUtil获取某类的分页信息
            pageCustServiceAccessAnalysis = pageUtil.getRequestPage();
            if (!pageCustServiceAccessAnalysis.isOrderBySetted()) {
                pageCustServiceAccessAnalysis.setOrderBy("custNo");
                pageCustServiceAccessAnalysis
                        .setOrder(Page.ASC);
                pageCustServiceAccessAnalysis.setPageNo(pageNo);
            }
            pageCustServiceAccessAnalysis.setPageSize(5);
            // 获取ServiceView数据列表信息
            custServiceViewList = this.customerService.getAccessServiceView(
                    pageCustServiceAccessAnalysis, cust_no);
            // 此循环让显示的日期以yyyy-MM-dd的形式显示
            for (int i = 0; i < custServiceViewList.getResult().size(); i++) {
                String firstView = custServiceViewList.getResult().get(i)
                        .getFirstView().substring(0, 10);
                String lastView = custServiceViewList.getResult().get(i)
                        .getLastView().substring(0, 10);
                custServiceViewList.getResult().get(i).setFirstView(firstView);
                custServiceViewList.getResult().get(i).setLastView(lastView);
            }
            // 获取每页显示的记录
            Integer pageCount = custServiceViewList.getResult().size();
            ServletActionContext.getRequest().setAttribute("count", pageCount);
            ServletActionContext.getRequest().setAttribute("pagerInfo", custServiceViewList);
            ServletActionContext.getRequest().setAttribute("rowId", rowId);
        }
        return "serviceView";
    }

    /**
     * 显示accessStaticAction
     * null
     *
     * @author zhangyang
     */
    public void viewAccessStaticAct() {

        Map<String, Object> rt = new HashMap<String, Object>();
        // 获得页面的Customer_No
        Integer cust_no = Integer.parseInt(ServletActionContext.getRequest().getParameter("cust_no"));
        // 获取viewAccessStaticAct数据信息
        accessStatDTO = this.customerService.getAccessStat(cust_no);
        rt.put("message", accessStatDTO);
        //以Json的形式返回到页面
        Struts2Util.renderJson(rt);
    }

    /**
     * 获取点击量数据列表信息
     *
     * @return String
     * @author zhangyang
     */
    public String getClickStream() {
        ServletActionContext.getRequest().setAttribute("count", 0);
        try {
            // 获得页面的Customer_No
            if (StringUtils.isNotBlank(custNo) && StringUtils.isNumeric(custNo)) {
                Integer cust_no = Integer.parseInt(ServletActionContext.getRequest()
                        .getParameter("cust_no"));
                String dateFrom = ServletActionContext.getRequest().getParameter("clickFrom");
                String dateTo = ServletActionContext.getRequest().getParameter("clickTo");
                // 获得PageUtil对象用于获取
                PagerUtil<AccessAnalysis> pageUtil = new PagerUtil<AccessAnalysis>();
                Integer pageNo = pageUtil.getRequestPage().getPageNo();
                //设置rowId变量，用来做为显示每页的记录号
                if (pageNo != 0) {
                    rowId = (pageNo - 1) * 5 + 1;
                }
                // 通过PageUtil获取某类的分页信息
                pageclickStream = pageUtil.getRequestPage();
                if (!pageclickStream.isOrderBySetted()) {
                    pageclickStream.setOrderBy("custNo");
                    pageclickStream.setOrder(Page.ASC);
                    pageclickStream.setPageNo(pageNo);
                }
                pageclickStream.setPageSize(5);
                // 获取ClickStream数据列表信息
                AnalysisSrchDTO analysisSrchDTO = new AnalysisSrchDTO();
                analysisSrchDTO.setCustNo(cust_no);
                //把页面获取的日期放入DTO，在来查找从哪一天到哪一天的记录
                analysisSrchDTO.setDateFrom(dateFrom);
                analysisSrchDTO.setDateTo(dateTo);
                pageAnalysisDTO = this.customerService.searchAccess(
                        analysisSrchDTO, pageclickStream);
                Integer pageCount = pageAnalysisDTO.getResult().size();
                ServletActionContext.getRequest().setAttribute("count", pageCount);
                ServletActionContext.getRequest().setAttribute("pagerInfo", pageAnalysisDTO);
                ServletActionContext.getRequest().setAttribute("rowId", rowId);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "clickStream";

    }

    public String getAnalysisReport() throws Exception {
        PagerUtil<AnalysisReport> pagerUtil = new PagerUtil<AnalysisReport>();
        Page<AnalysisReport> pageReport = pagerUtil.getRequestPage();
        if (!pageReport.isOrderBySetted()) {
            pageReport.setOrderBy("fromDate");
            pageReport.setOrder(Page.ASC);
        }
//		pageReport.set
        pageReport.setTotalCount(-1L);
        PageDTO pageReportDTO = dozer.map(pageReport, PageDTO.class);
        AnalysisReportSrchDTO srchDTO = new AnalysisReportSrchDTO();
        if (StringUtils.isNotBlank(custNo) && StringUtils.isNumeric(custNo))
            srchDTO.setCustNo(Integer.parseInt(custNo));
        srchDTO.setBeginDate(beginDate);
        srchDTO.setEndDate(endDate);
        srchDTO.setPeriod(period);
        System.out.println("srchDTO: " + srchDTO);
        analysisReportList = customerService.searchAnalysis(pageReportDTO, srchDTO);
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (int i = 0; i < analysisReportList.size(); i++) {
            if (analysisReportList.get(i).getVisit() == 0) {
                dataSet.addValue(null, "", String.valueOf(i + 1));
            } else {
                dataSet.addValue(analysisReportList.get(i).getVisit(), "", String.valueOf(i + 1));
            }
        }
        JFreeChart chart = null;

        if (period == 1) {
            chart = ChartFactory.createBarChart(null, "Day", "Visits", dataSet, PlotOrientation.VERTICAL, false, false, false);
        } else if (period == 7) {
            chart = ChartFactory.createBarChart(null, "Week", "Visits", dataSet, PlotOrientation.VERTICAL, false, false, false);
        } else if (period == 30) {
            chart = ChartFactory.createBarChart(null, "Month", "Visits", dataSet, PlotOrientation.VERTICAL, false, false, false);
        } else if (period == 90) {
            chart = ChartFactory.createBarChart(null, "Quarter", "Visits", dataSet, PlotOrientation.VERTICAL, false, false, false);
        } else {
            chart = ChartFactory.createBarChart(null, "Year", "Visits", dataSet, PlotOrientation.VERTICAL, false, false, false);
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

        try {
            //String rootPath = ServletActionContext.getRequest().getSession().getServletContext().getResource("/").toString();
            fos = new FileOutputStream("/usr/local/jboss-5.1.0.GA/server/default/deploy/ROOT.war" + "/images/temp/" + picName + ".png");
            ChartUtilities.writeChartAsPNG(fos, chart, 730, 200, null);
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AnalysisReportListDTO list = new AnalysisReportListDTO();
        list.setAnalysisReportList(analysisReportList);
        list.setDataFileName(picName);
        Struts2Util.renderJson(list);
        return NONE;
    }


    public WebBehaviorPeriodDTO getWebBehaviorPeriodDTO() {
        webBehaviorPeriodDTO = new WebBehaviorPeriodDTO();
        GregorianCalendar currentDate = new GregorianCalendar();
        Calendar localTime = Calendar.getInstance();
        Date now = localTime.getTime();
        final String SINCE_DATE = "2009-01-01;";
        String today = com.genscript.gsscm.common.util.DateUtils.formatDate2Str(localTime.getTime(), "yyyy-MM-dd");
        int month = localTime.get(Calendar.MONTH);
        String year = String.valueOf(localTime.get(Calendar.YEAR));
        currentDate.add(GregorianCalendar.MONTH, -1);
        Date lastMonth = currentDate.getTime();
        webBehaviorPeriodDTO.setFirstDate(SINCE_DATE + today);
        String lastYear = String.valueOf(localTime.get(Calendar.YEAR) - 1);
        webBehaviorPeriodDTO.setLastYear(lastYear + "-01-01;" + lastYear + "-12-31");
        webBehaviorPeriodDTO.setThisYear(year + "-01-01;" + today);
        String lastMon = com.genscript.gsscm.common.util.DateUtils.getPreviousMonday();
        String lastSun = com.genscript.gsscm.common.util.DateUtils.getPreviousSunday();
        webBehaviorPeriodDTO.setLastWeek(lastMon + ";" + lastSun);
        String thisMon = com.genscript.gsscm.common.util.DateUtils.getCurrentMonday();
        webBehaviorPeriodDTO.setThisWeek(thisMon + ";" + today);
        String lastMonthStr = com.genscript.gsscm.common.util.DateUtils.getLastMonth();
        String lastMonthDayStr = com.genscript.gsscm.common.util.DateUtils.getLastDayOfMonth(lastMonth);
        webBehaviorPeriodDTO.setLastMonth(lastMonthStr + ";" + lastMonthDayStr);
        String thisMonthFirstDay = com.genscript.gsscm.common.util.DateUtils.getFirstDayOfMonth(now);
        String thisMonthLastDay = com.genscript.gsscm.common.util.DateUtils.getLastDayOfMonth(now);
        webBehaviorPeriodDTO.setThisMonth(thisMonthFirstDay + ";" + thisMonthLastDay);

        if (month >= 10) {
            webBehaviorPeriodDTO.setThisQuarter(year + "-10-01;" + today);
        } else if (month >= 7) {
            webBehaviorPeriodDTO.setThisQuarter(year + "-07-01;" + today);
        } else if (month >= 4) {
            webBehaviorPeriodDTO.setThisQuarter(year + "-04-01;" + today);
        } else {
            webBehaviorPeriodDTO.setThisQuarter(year + "-01-01;" + today);
        }

        if (month >= 10) {
            webBehaviorPeriodDTO.setLastQuarter(new StringBuilder().append(year).append("-07-01;").append(year).append("-09-30").toString());
        } else if (month >= 7) {
            webBehaviorPeriodDTO.setLastQuarter(new StringBuilder().append(year).append("-04-01;").append(year).append("-06-30").toString());
        } else if (month >= 4) {
            webBehaviorPeriodDTO.setLastQuarter(new StringBuilder().append(year).append("-01-01;").append(year).append("-03-31").toString());
        } else {
            webBehaviorPeriodDTO.setLastQuarter(new StringBuilder().append(localTime.get(Calendar.YEAR) - 1).append("-10-01;").append(localTime.get(Calendar.YEAR) - 1).append("-12-31").toString());
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -5);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        String last6Months = DateUtils.formatDate2Str(cal.getTime(), "yyyy-MM-dd");
        webBehaviorPeriodDTO.setLastSixMonths(new StringBuilder().append(last6Months).append(";").append(today).toString());
        return webBehaviorPeriodDTO;
    }

    public void setWebBehaviorPeriodDTO(WebBehaviorPeriodDTO webBehaviorPeriodDTO) {
        this.webBehaviorPeriodDTO = webBehaviorPeriodDTO;
    }

    public String getCustNo() {
        return custNo;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public List<AnalysisReport> getAnalysisReportList() {
        return analysisReportList;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public Page<PageViewDTO> getPageViewDTOList() {
        return pageViewDTOList;
    }

    public void setPageViewDTOList(Page<PageViewDTO> pageViewDTOList) {
        this.pageViewDTOList = pageViewDTOList;
    }

    public Page<ProductViewDTO> getCustProductViewDTOList() {
        return custProductViewDTOList;
    }

    public void setCustProductViewDTOList(
            Page<ProductViewDTO> custProductViewDTOList) {
        this.custProductViewDTOList = custProductViewDTOList;
    }

    public Page<ServiceViewDTO> getCustServiceViewList() {
        return custServiceViewList;
    }

    public void setCustServiceViewList(Page<ServiceViewDTO> custServiceViewList) {
        this.custServiceViewList = custServiceViewList;
    }

    public Page<AnalysisDTO> getPageAnalysisDTO() {
        return pageAnalysisDTO;
    }

    public void setPageAnalysisDTO(Page<AnalysisDTO> pageAnalysisDTO) {
        this.pageAnalysisDTO = pageAnalysisDTO;
    }

    public Page<AccessAnalysis> getPageAccessAnalysis() {
        return pageAccessAnalysis;
    }

    public void setPageAccessAnalysis(Page<AccessAnalysis> pageAccessAnalysis) {
        this.pageAccessAnalysis = pageAccessAnalysis;
    }

    public Page<SampleRequestDTO> getPageSampleRequestDTO() {
        return pageSampleRequestDTO;
    }

    public void setPageSampleRequestDTO(Page<SampleRequestDTO> pageSampleRequestDTO) {
        this.pageSampleRequestDTO = pageSampleRequestDTO;
    }

    public Page<AccessAnalysis> getPageCustServiceAccessAnalysis() {
        return pageCustServiceAccessAnalysis;
    }

    public void setPageCustServiceAccessAnalysis(
            Page<AccessAnalysis> pageCustServiceAccessAnalysis) {
        this.pageCustServiceAccessAnalysis = pageCustServiceAccessAnalysis;
    }

    public Page<AccessAnalysis> getPageclickStream() {
        return pageclickStream;
    }

    public void setPageclickStream(Page<AccessAnalysis> pageclickStream) {
        this.pageclickStream = pageclickStream;
    }

    public AccessStatDTO getAccessStatDTO() {
        return accessStatDTO;
    }

    public void setAccessStatDTO(AccessStatDTO accessStatDTO) {
        this.accessStatDTO = accessStatDTO;
    }

    public AccessAnalysis getAccessAnalysis() {
        return accessAnalysis;
    }

    public void setAccessAnalysis(AccessAnalysis accessAnalysis) {
        this.accessAnalysis = accessAnalysis;
    }

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

}
