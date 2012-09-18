package com.genscript.gsscm.customer.web;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.HostIpUtil;
import com.genscript.gsscm.common.MyX509TrustManager;
import com.genscript.gsscm.common.util.*;
import com.genscript.gsscm.order.dto.OrderMainBeanDTO;
import com.genscript.gsscm.order.dto.QuoteMainBeanDTO;
import com.genscript.gsscm.ws.WSException;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;
import com.lowagie.text.Element;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.xwork.ObjectUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.order.entity.OrderMainBean;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.quote.entity.QuoteMainBean;
import com.genscript.gsscm.quote.service.QuoteService;
import com.opensymphony.xwork2.ActionSupport;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static java.io.File.separator;

@Results({
        @Result(name = "showOrderListByCustNo", location = "customer/customer_order_info.jsp"),
        @Result(name = "showQuoteListByCustNo", location = "customer/customer_quote_info.jsp"),
        @Result(name = "showQuorderReport", location = "customer/quorder_report.jsp"),
        @Result(name = "createorderReportSuccess", location = "customer_quote_info.jsp")
})
public class CustQuOrderAction extends ActionSupport {

    private static final long serialVersionUID = 9034986519475061141L;
    private int custNo;
    private Page<OrderMainBean> pageOrderMain = new Page<OrderMainBean>(20);
    private Page<QuoteMainBean> pageQuoteMain = new Page<QuoteMainBean>(20);


    private Page<QuoteMainBeanDTO> pageQuoteMainDto = new Page<QuoteMainBeanDTO>(20);
    private Page<OrderMainBeanDTO> pageOrderMainDto = new Page<OrderMainBeanDTO>(20);

    @Autowired
    private ExceptionService exceptionUtil;

    private List<DropDownListDTO> statusList;
    private String searchType;
    private Long orderProductTotal;
    private Long quoteProductTotal;
    private Long orderServiceTotal;
    private Long quoteServiceTotal;
    private Double toAmount;
    private HostIpUtil hostip = new HostIpUtil();
    private Double fromAmount;
    private Date toDate;
    private Date fromDate;
    private String status;
    @Autowired
    private QuoteService quoteService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PublicService publicService;
    private String codeType;


    public static String getExtName(String fileName) {
        if (fileName.lastIndexOf(".") == -1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }

    public static String randomNameFile(String fileName) {
        if (fileName.lastIndexOf(".") == -1) {
            return randomString(5);
        }
        return randomString(5) + "." + getExtName(fileName);
    }

    public Page<QuoteMainBeanDTO> getPageQuoteMainDto() {
        return pageQuoteMainDto;
    }

    public void setPageQuoteMainDto(Page<QuoteMainBeanDTO> pageQuoteMainDto) {
        this.pageQuoteMainDto = pageQuoteMainDto;
    }

    public static String randomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }


    public static String FILE_STORAGE = "fileStorage";
    public static String DOCUMENT_ITEM_STORAGE = "documentItemStorage";

    public static String getFileStorage(HttpServletRequest request, String desc) {
        return request.getSession().getServletContext().getRealPath(
                FILE_STORAGE + separator + desc);
    }

    public static String getDocumentItemStorage(HttpServletRequest request) {
        return getFileStorage(request, DOCUMENT_ITEM_STORAGE);
    }

    /**
     * print all by pdf type
     *
     * @return
     * @throws Exception
     */

    public void print() throws Exception {

        HttpURLConnection con = null;
        HttpsURLConnection connection = null;
        URL url = null;
        Process p = null;
        String cmd = PdfUtils.getPdfProcessName2();
        String ports = "";
        try {
            String sessionid = Struts2Util.getRequest().getSession().getId();
          //  System.out.println("sessionid==" + sessionid);
            if (Struts2Util.getRequest().getServerPort() != 80) {
                ports = ":" + Struts2Util.getRequest().getServerPort();
            }
            String basePath = Struts2Util.getRequest().getScheme() + "://"
                    + Struts2Util.getRequest().getServerName() + ports
                    + Struts2Util.getRequest().getContextPath() + "/";
            String urlstr = "";
            urlstr = basePath + "customer/cust_qu_order!showQuorderReport.action?custNo=" + custNo + "&codeType=" + codeType + "";
            if (status != null) {
                urlstr += "&status=" + status;
            }
            if (toAmount != null) {
                urlstr += "&toAmount=" + toAmount;
            }
            if (fromAmount != null) {
                urlstr += "&fromAmount=" + fromAmount;
            }
            if (toDate != null) {
                urlstr += "&toDate=" + toDate;
            }
            if (fromDate != null) {
                urlstr += "&fromDate=" + fromDate;
            }
            int size = 0;
            byte[] buf = new byte[1024];
            BufferedInputStream bis = null;
            url = new URL(urlstr);
            if (basePath.startsWith("https://")) {
                // 创建SSLContext对象，并使用我们指定的信任管理器初始化
                TrustManager[] tm = {
                        new MyX509TrustManager()
                };
                SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
                sslContext.init(null, tm, new java.security.SecureRandom());
                // 从上述SSLContext对象中得到SSLSocketFactory对象
                SSLSocketFactory ssf = sslContext.getSocketFactory();
                HostnameVerifier hv = new HostnameVerifier() {
                    public boolean verify(String urlHostName, SSLSession session) {
                        System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
                        return true;
                    }
                };
                HttpsURLConnection.setDefaultHostnameVerifier(hv);
                connection = (HttpsURLConnection) url
                        .openConnection();
                connection.setSSLSocketFactory(ssf);
                connection.setRequestProperty("Cookie", "JSESSIONID=" + sessionid);
                connection.connect();
                bis = new BufferedInputStream(connection.getInputStream());
              //  System.out.println("Warnddddddddddddddddddddddddddddddding！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！ ");
            } else {
                con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("Cookie", "JSESSIONID=" + sessionid);
                con.connect();
              //  System.out.println("Warnddddddddd！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！ ");
                bis = new BufferedInputStream(con.getInputStream());
            }
          //  System.out.println("1111111111111111111111111111");
            StringBuffer strb = new StringBuffer();
            while ((size = bis.read(buf)) != -1) {
                strb.append(new String(buf, 0, size));
            }
            ProcessBuilder pb = null;
            FileWriter writer = null;
            HttpServletRequest request = ServletActionContext.getRequest();

            File htmlfile = new File(getDocumentItemStorage(request), "html" + separator + randomNameFile("showQuorderReport.html"));
            FileUtils.forceMkdir(htmlfile.getParentFile());
            File pdffile = new File(getDocumentItemStorage(request), "pdf" + separator + randomNameFile("showQuorderReport.pdf"));
            FileUtils.forceMkdir(pdffile.getParentFile());//这里是创建目录
            if (!htmlfile.exists()) {
                if (htmlfile.createNewFile()) {
              //      System.out.println("temp file is create !");
                    writer = new FileWriter(htmlfile);
                    //System.out.println("2222222222222222222222222222" + writer.toString());
                    writer.write(strb.toString());
                    //System.out.print("writer==" + writer);
                    writer.flush();
                    writer.close();            //先将页面信息写入新创建的html文件格式文件中，
                    // System.out.println("打印页面的内容==" + strb.toString());
                }
            }
            if (!pdffile.exists()) {
                if (pdffile.createNewFile()) {
                    System.out.println("temp file for pdffile is create !");
                }
            }
            String htmlfile2 = htmlfile.getParentFile() + separator + htmlfile.getName();
            String pdffile2 = pdffile.getParentFile() + separator + pdffile.getName();
            //System.out.println(htmlfile2);
            //System.out.println(pdffile2);
            if (htmlfile.exists() && pdffile.exists()) {   //确定两个文件都在
             //   System.out.println("all  is exists!");
                pb = new ProcessBuilder(cmd, htmlfile2, pdffile2);
              //  System.out.println(pb.toString());
                pb.redirectErrorStream(true);
                p = pb.start(); //然后将html文件转换到pdf文件里面去 这样就可以完成打印功能了。

                InputStreamReader ir = new InputStreamReader(p.getInputStream());
                LineNumberReader input = new LineNumberReader(ir);
                String line;
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            }
            // 提示下载
            HttpServletResponse response = Struts2Util.getResponse();
            response.setContentType("APPLICATION/DOWNLOAD;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + "Report--" + (new Random()).nextInt() + ".pdf");
            java.io.OutputStream os = response.getOutputStream();
            java.io.FileInputStream fis = new java.io.FileInputStream(pdffile2);
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
            con.disconnect();
        } catch (Exception e) {
            if (p != null)
                p.destroy();
            System.out.println("Error exec!");
            WSException exDTO = exceptionUtil.getExceptionDetails(e);
            exceptionUtil.logException(exDTO, this.getClass(), e,
                    new Exception().getStackTrace()[0].getMethodName(),
                    "INTF0203", SessionUtil.getUserId());
            Struts2Util.renderText(exDTO.getMessageDetail() + "\n" + exDTO.getAction());
            e.printStackTrace();
        }

    }

    public Page<OrderMainBeanDTO> getPageOrderMainDto() {
        return pageOrderMainDto;
    }

    public void setPageOrderMainDto(Page<OrderMainBeanDTO> pageOrderMainDto) {
        this.pageOrderMainDto = pageOrderMainDto;
    }

    public HostIpUtil getHostip() {
        return hostip;
    }

    public void setHostip(HostIpUtil hostip) {
        this.hostip = hostip;
    }

    public static String getFILE_STORAGE() {
        return FILE_STORAGE;
    }

    public static void setFILE_STORAGE(String FILE_STORAGE) {
        CustQuOrderAction.FILE_STORAGE = FILE_STORAGE;
    }

    public static String getDOCUMENT_ITEM_STORAGE() {
        return DOCUMENT_ITEM_STORAGE;
    }

    public static void setDOCUMENT_ITEM_STORAGE(String DOCUMENT_ITEM_STORAGE) {
        CustQuOrderAction.DOCUMENT_ITEM_STORAGE = DOCUMENT_ITEM_STORAGE;
    }

    /**
     * new showQuorderListByCustNo
     *
     * @return
     * @throws Exception
     */
    public String showQuorderListByCustNo() throws Exception {
        System.out.println(">>>>>>>>>>>>>>");
        List<PropertyFilter> propertyFilterList = WebUtil
                .buildQuorderConditions(ServletActionContext.getRequest());
        String p_no=ServletActionContext.getRequest().getParameter("p_no");
        System.out.println(p_no);
        Integer pageNo=0;
        if(p_no ==null){ 
        	pageNo=1;
        }else{
        	pageNo=Integer.parseInt(p_no);
        }
        // 显示Qutation标签
        if (codeType.equals("quote")) {
            PagerUtil<QuoteMainBeanDTO> pagerUtil = new PagerUtil<QuoteMainBeanDTO>();
            pageQuoteMainDto = pagerUtil.getRequestPage();
            pageQuoteMainDto.setPageSize(20);
            pageQuoteMainDto.setOrder(Page.DESC);
            pageQuoteMainDto.setOrderBy("quoteNo");
            pageQuoteMain.setPageNo(pageNo);
            pageQuoteMainDto = quoteService.searchQuote_new(custNo, pageQuoteMain,
                    propertyFilterList);
            List<QuoteMainBeanDTO> quoteMainList = pageQuoteMainDto.getResult();
            List<Integer> quoteNoList = new ArrayList<Integer>();
            if (quoteMainList != null && !quoteMainList.isEmpty()) {
                for (QuoteMainBeanDTO bean : quoteMainList) {
                    quoteNoList.add(bean.getQuoteNo());
                }
            }
            if (quoteNoList != null && quoteNoList.size() > 0) {
                quoteProductTotal = quoteService.getTotalProductsQuoted(custNo, quoteNoList);
                quoteServiceTotal = quoteService.getTotalServicesQuoted(custNo, quoteNoList);
            } else {
                quoteProductTotal = 0L;
                quoteServiceTotal = 0L;
            }
            List<SpecDropDownListName> specDropDownListNames = new ArrayList<SpecDropDownListName>();
            specDropDownListNames.add(SpecDropDownListName.QUOTE_STATUS_LIST);
            statusList = publicService
                    .getSpecDropDownList(specDropDownListNames);
            ServletActionContext.getRequest().setAttribute("pagerInfo", pageQuoteMain);
            return "showQuoteListByCustNo";
        }
        // 显示Order标签
        else if (codeType.equals("order")) {
            PagerUtil<OrderMainBeanDTO> pagerUtil = new PagerUtil<OrderMainBeanDTO>();
            pageOrderMainDto = pagerUtil.getRequestPage();
            pageOrderMainDto.setPageSize(20);
            pageOrderMainDto.setOrder(Page.DESC);
            pageOrderMain.setPageNo(pageNo);
            pageOrderMainDto.setOrderBy("orderNo");
            pageOrderMainDto = orderService.searchOrder_new(custNo, pageOrderMain, propertyFilterList);

            List<OrderMainBeanDTO> orderMainList = pageOrderMainDto.getResult();
            List<Integer> orderNoList = new ArrayList<Integer>();
            if (orderMainList != null && !orderMainList.isEmpty()) {
                for (OrderMainBeanDTO orderMainBeanDTO : orderMainList) {
                    orderNoList.add(orderMainBeanDTO.getOrderNo());
                }
            }
            if (orderNoList.size() > 0) {
                orderProductTotal = orderService.getTotalProductsOrdered(custNo, orderNoList);
                orderServiceTotal = orderService.getTotalServicesOrdered(custNo, orderNoList);
            } else {
                orderProductTotal = 0L;
                orderServiceTotal = 0L;
            }
            List<SpecDropDownListName> specDropDownListNames = new ArrayList<SpecDropDownListName>();
            specDropDownListNames.add(SpecDropDownListName.ORDER_STATUS_LIST);
            statusList = publicService
                    .getSpecDropDownList(specDropDownListNames);
            ServletActionContext.getRequest().setAttribute("pagerInfo", pageOrderMain);
            return "showOrderListByCustNo";
        }
        return null;
    }

    /**
     * 显示该Customer下的所有Qutation与Order信息以列表方法展现
     *
     * @return "showOrderListByCustNo"
     *         location="customer/customer_order_info.jsp"
     *         "showQuoteListByCustNo"
     *         location="customer/customer_quote_info.jsp"
     * @throws Exception
     */
    /*   public String showQuorderListByCustNo() throws Exception {
        List<PropertyFilter> propertyFilterList = WebUtil
                .buildQuorderConditions(ServletActionContext.getRequest());
        // 显示Qutation标签
        if (codeType.equals("quote")) {
            PagerUtil<QuoteMainBean> pagerUtil = new PagerUtil<QuoteMainBean>();
            pageQuoteMain = pagerUtil.getRequestPage();
            pageQuoteMain.setPageSize(20);
            pageQuoteMain.setOrder(Page.DESC);
            pageQuoteMain.setOrderBy("quoteNo");
            pageQuoteMain = quoteService.searchQuote(pageQuoteMain,
                    propertyFilterList);
            List<QuoteMainBean> quoteMainList = pageQuoteMain.getResult();
            List<Integer> quoteNoList = new ArrayList<Integer>();
            if (quoteMainList != null && !quoteMainList.isEmpty()) {
                for (QuoteMainBean bean : quoteMainList) {
                    quoteNoList.add(bean.getQuoteNo());
                }
            }
            if (quoteNoList != null && quoteNoList.size() > 0) {
                quoteProductTotal = quoteService.getTotalProductsQuoted(custNo, quoteNoList);
                quoteServiceTotal = quoteService.getTotalServicesQuoted(custNo, quoteNoList);
            } else {
                quoteProductTotal = 0L;
                quoteServiceTotal = 0L;
            }
            List<SpecDropDownListName> specDropDownListNames = new ArrayList<SpecDropDownListName>();
            specDropDownListNames.add(SpecDropDownListName.QUOTE_STATUS_LIST);
            statusList = publicService
                    .getSpecDropDownList(specDropDownListNames);
            ServletActionContext.getRequest().setAttribute("pagerInfo", pageQuoteMain);
            return "showQuoteListByCustNo";
        }
        // 显示Order标签
        else if (codeType.equals("order")) {
            PagerUtil<OrderMainBean> pagerUtil = new PagerUtil<OrderMainBean>();
            pageOrderMain = pagerUtil.getRequestPage();
            pageOrderMain.setPageSize(20);
            pageOrderMain.setOrder(Page.DESC);
            pageOrderMain.setOrderBy("orderNo");
            pageOrderMain = orderService.searchOrder(pageOrderMain,
                    propertyFilterList);

            List<OrderMainBean> orderMainList = pageOrderMain.getResult();
            List<Integer> orderNoList = new ArrayList<Integer>();
            if (orderMainList != null && !orderMainList.isEmpty()) {
                for (OrderMainBean bean : orderMainList) {
                    orderNoList.add(bean.getOrderNo());
                }
            }
            if (orderNoList != null && orderNoList.size() > 0) {
                orderProductTotal = orderService.getTotalProductsOrdered(custNo, orderNoList);
                orderServiceTotal = orderService.getTotalServicesOrdered(custNo, orderNoList);
            } else {
                orderProductTotal = 0L;
                orderServiceTotal = 0L;
            }
            List<SpecDropDownListName> specDropDownListNames = new ArrayList<SpecDropDownListName>();
            specDropDownListNames.add(SpecDropDownListName.ORDER_STATUS_LIST);
            statusList = publicService
                    .getSpecDropDownList(specDropDownListNames);
            ServletActionContext.getRequest().setAttribute("pagerInfo", pageOrderMain);
            return "showOrderListByCustNo";
        }
        return null;
    }*/

    /**
     * 显示Customer下 Quotation 与Order的Report报表
     *
     * @return "showQuorderReport" location="customer/quorder_report.jsp"
     * @throws Exception
     */
    public String showQuorderReport() throws Exception {
        List<PropertyFilter> propertyFilterList = WebUtil
                .buildQuorderConditions(ServletActionContext.getRequest());
        if (codeType.equals("quote")) {
            pageQuoteMainDto.setOrder(Page.DESC);
            pageQuoteMainDto.setOrderBy("quoteNo");
            pageQuoteMainDto = quoteService.searchQuote_new(custNo, pageQuoteMain,
                    propertyFilterList);
            return "showQuorderReport";
        } else if (codeType.equals("order")) {
            pageOrderMainDto.setOrder(Page.DESC);
            pageOrderMainDto.setOrderBy("orderNo");
            pageOrderMainDto = orderService.searchOrder_new(custNo, pageOrderMain,
                    propertyFilterList);
            return "showQuorderReport";
        }
        return null;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public Page<OrderMainBean> getPageOrderMain() {
        return pageOrderMain;
    }

    public void setPageOrderMain(Page<OrderMainBean> pageOrderMain) {
        this.pageOrderMain = pageOrderMain;
    }

    public Page<QuoteMainBean> getPageQuoteMain() {
        return pageQuoteMain;
    }

    public void setPageQuoteMain(Page<QuoteMainBean> pageQuoteMain) {
        this.pageQuoteMain = pageQuoteMain;
    }

    public List<DropDownListDTO> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<DropDownListDTO> statusList) {
        this.statusList = statusList;
    }

    public String getCodeType() {
        return codeType;
    }

    public Long getOrderProductTotal() {
        return orderProductTotal;
    }

    public void setOrderProductTotal(Long orderProductTotal) {
        this.orderProductTotal = orderProductTotal;
    }

    public Long getQuoteProductTotal() {
        return quoteProductTotal;
    }

    public void setQuoteProductTotal(Long quoteProductTotal) {
        this.quoteProductTotal = quoteProductTotal;
    }

    public Long getOrderServiceTotal() {
        return orderServiceTotal;
    }

    public void setOrderServiceTotal(Long orderServiceTotal) {
        this.orderServiceTotal = orderServiceTotal;
    }

    public Long getQuoteServiceTotal() {
        return quoteServiceTotal;
    }

    public void setQuoteServiceTotal(Long quoteServiceTotal) {
        this.quoteServiceTotal = quoteServiceTotal;
    }

    public Double getToAmount() {
        return toAmount;
    }

    public Double getFromAmount() {
        return fromAmount;
    }

    public Date getToDate() {
        return toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setToAmount(Double toAmount) {
        this.toAmount = toAmount;
    }

    public void setFromAmount(Double fromAmount) {
        this.fromAmount = fromAmount;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setCustNo(Integer custNo) {
        this.custNo = custNo;
    }

    public QuoteService getQuoteService() {
        return quoteService;
    }

    public void setQuoteService(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public PublicService getPublicService() {
        return publicService;
    }

    public void setPublicService(PublicService publicService) {
        this.publicService = publicService;
    }


    public Integer getCustNo() {
        return custNo;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
