package com.genscript.gsscm.customer.web;

import java.io.*;
import java.util.*;

import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.constant.OperationType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.ExcelUtil;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionEmergeUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.CustGrantsDTO;
import com.genscript.gsscm.customer.dto.CustPubsDTO;
import com.genscript.gsscm.customer.entity.CustomerGrants;
import com.genscript.gsscm.customer.entity.CustomerPublications;
import com.genscript.gsscm.customer.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.io.File.separator;

@Results({
        @Result(name = "listPubGrant", location = "customer/customer_publication_grant.jsp"),
        @Result(name = "listPub", location = "customer/customer_publication.jsp"),
        @Result(name = "listGrant", location = "customer/customer_grant.jsp"),
        @Result(name = "showGrantEditForm", location = "customer/customer_grant_createform.jsp"),
        @Result(name = "showPubEditForm", location = "customer/customer_publication_createform.jsp"),
        @Result(name = "showGrantCreateForm", location = "customer/customer_grant_createform.jsp"),
        @Result(name = "showPubCreateForm", location = "customer/customer_publication_createform.jsp"),
        @Result(name = "upLoadExcel", location = "customer/customer_grant.jsp")

})
public class CustPubGrantAction extends BaseAction<Object> {

    private static final long serialVersionUID = 6861374036569322593L;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ExceptionService exceptionUtil;
    @Autowired
    private DozerBeanMapper dozer;
    private int custNo;
    private String sessCustNo;
    private String grantIdStr;
    private String pubIdStr;
    private Page<CustGrantsDTO> pageGrants;
    private Page<CustPubsDTO> pagePubs;
    private CustPubsDTO custPubDTO;
    private CustGrantsDTO custGrantDTO;


    //-------------------------------------------------------uploading
    private static final int BUFFER_SIZE = 16 * 1024;

    private File xls;
    private String xlsFileName;
    private boolean success;
    private String message;
    public static String FILE_STORAGE = "fileStorage";
    public static String DOCUMENT_ITEM_STORAGE = "documentItemStorage";
    /**
     * save all grants
     *
     * @throws   Exception
     * @return listGrant
     */
    public String saveALlGrants() throws Exception {
        try {
            customerService.saveCustGrants(sessCustNo, custNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "listGrant";
    }

    /**
     * saveAllPublishs
     *
     * @return zhougang
     * @throws Exception
     */
    public String saveAllPublishs() throws Exception {
        try {
            customerService.saveCustPublishs(sessCustNo, custNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "listPub";
    }


    //输出信息到response
    private void printMessage() throws IOException {
        ActionContext ctx = ActionContext.getContext();
        HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
        response.setContentType("text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (success) {
                sb.append("Success for uploading!");
            } else {
                sb.append("Error for uploading ");
            }
            pw = response.getWriter();
            pw.print(sb.toString());

        } catch (IOException e) {
            logger.error("error ！");
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
                pw = null;
            }
        }
    }

    /**
     * //导入EXCEL文件
     *
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String upLoadExcel_Publish() throws Exception {
        Map<String, Object> rt = new HashMap<String, Object>();
        try {
            if (xlsFileName == null || xlsFileName.equals("")) {
                success = false;
                printMessage();
                return NONE;
            }
            if (xls.length() <= 0) {
                success = false;
                message = "Uploading file error!";
                return NONE;
            }
            InputStream is = new FileInputStream(xls);
            HttpServletRequest request = ServletActionContext.getRequest();
            File tempFile = new File(getDocumentItemStorage(request), "Publish" + separator + randomNameFile(xlsFileName));
            FileUtils.forceMkdir(tempFile.getParentFile());
            OutputStream os = new FileOutputStream(tempFile);
            int len = 0;
            byte[] buffer = new byte[1024];
            while (-1 != (len = is.read(buffer))) {
                os.write(buffer, 0, len);
            }
            is.close();
            os.close();
            success = true;
            message = "Uploading file success!";

            if (success) {
                printMessage();
                SessionUtil.deleteRow(SessionConstant.CustPubList.value(), String.valueOf(custNo));
                ExcelUtil eu = new ExcelUtil();
                FileInputStream fileInputStream = null;
                Map<String, Object> custPubs = null;
                fileInputStream = new FileInputStream(tempFile.getPath());
                List listFile = eu.read_new(fileInputStream);
                fileInputStream.close();
                listFile.remove(0);
                custPubs = SessionUtil.getRow(SessionConstant.CustPubList.value(), custNo, sessCustNo);

                if (custPubs == null) {
                    custPubs = new HashMap<String, Object>();
                }
                for (Object aListFile : listFile) {
                    CustPubsDTO custdtd = new CustPubsDTO();
                    String[] al = aListFile.toString().substring(1, aListFile.toString().length() - 1).split(",");

                    if (al[0] != null) {
                        custdtd.setTitle(al[0]);
                    } else {
                        custdtd.setTitle(al[0]);
                    }


                    if (al[1] != null) {
                        custdtd.setCoAuthor(al[1]);
                    } else {
                        custdtd.setCoAuthor("");
                    }

                    if (al[2] != null) {
                        custdtd.setEmail(al[2]);
                    } else {
                        custdtd.setEmail("");
                    }

                    if (al[3] != null) {
                        custdtd.setPublicationName(al[3]);
                    } else {
                        custdtd.setPublicationName("");
                    }

                    if (al[4] != null) {
                        custdtd.setUrl(al[4]);
                    } else {
                        custdtd.setUrl("");
                    }

                    if (al[5] != null) {
                        custdtd.setAbst(al[5]);
                    } else {
                        custdtd.setAbst("");
                    }

                    if (al[6] != null) {
                        custdtd.setRelatedArea(al[6]);
                    } else {
                        custdtd.setRelatedArea("");
                    }

                    if (al[7] != null) {
                        custdtd.setKeyWords(al[7]);
                    } else {
                        custdtd.setKeyWords("");
                    }

                    custdtd.setIssueDate(new Date());
                    custdtd.setModifiedBy(SessionUtil.getUserId());
                    custdtd.setModifyDate(new Date());
                    custdtd.setCreationDate(new Date());
                    custdtd.setCreatedBy(SessionUtil.getUserId());
                    custdtd.setCustNo(custNo);
                    String id = ServletActionContext.getRequest().getParameter("id");   //接受点击列表传来的值得id.
                    if (id == null) {
                        custdtd.setId(null);
                        custdtd.setOperationType(OperationType.ADD);
                        String idStr = SessionUtil.generateTempId();
                        custdtd.setPubIdStr(idStr);
                        custPubs.put(idStr, custdtd);
                    }
                    if (StringUtils.isEmpty(id) && custdtd.getPubIdStr() == null) {
                        custdtd.setId(null);
                        custdtd.setOperationType(OperationType.ADD);
                        String idStr = SessionUtil.generateTempId();
                        custdtd.setPubIdStr(idStr);
                        custPubs.put(idStr, custdtd);
                    }
                    if (custdtd.getPubIdStr() != null
                            && !StringUtils.isNumeric(custdtd.getPubIdStr())) {
                        custPubs.put(custdtd.getPubIdStr(), custdtd);
                    }
                    if (StringUtils.isNotEmpty(id) && StringUtils.isNumeric(id)) {
                        custdtd.setOperationType(OperationType.EDIT);
                        custdtd.setId(Integer.parseInt(id));
                        custPubs.put(id, custdtd);
                    }
                }
                SessionUtil.insertRow(SessionConstant.CustPubList.value(), custNo,
                        sessCustNo, custPubs);
            }

        } catch (Exception e) {
            WSException exDTO = exceptionUtil.getExceptionDetails(e);
            exceptionUtil.logException(exDTO, this.getClass(), e,
                    new Exception().getStackTrace()[0].getMethodName(),
                    "INTF0203", SessionUtil.getUserId());
            rt.put("hasException", "Y");
            rt.put("exception", exDTO);

        }
        return NONE;

    }


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

    public static String randomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }


    public static String getFileStorage(HttpServletRequest request, String desc) {
        return request.getSession().getServletContext().getRealPath(
                FILE_STORAGE + separator + desc);
    }

    public static String getDocumentItemStorage(HttpServletRequest request) {
        return getFileStorage(request, DOCUMENT_ITEM_STORAGE);
    }


    /**
     * //导入EXCEL文件
     *
     * @return
     * @throws Exception
     * @throws java.io.IOException
     */
    @SuppressWarnings("unchecked")
    public String upLoadExcel_grant() throws IOException {
        Map<String, Object> rt = new HashMap<String, Object>();
        try {
            if (xlsFileName == null || xlsFileName.equals("")) {
                success = false;
                printMessage();
                return NONE;
            }
            if (xls.length() <= 0) {
                success = false;
                message = "Uploading file error!";
                return NONE;
            }
            InputStream is = new FileInputStream(xls);
            HttpServletRequest request = ServletActionContext.getRequest();
            File tempFile = new File(getDocumentItemStorage(request), "grant" + separator + randomNameFile(xlsFileName));
            FileUtils.forceMkdir(tempFile.getParentFile());
            OutputStream os = new FileOutputStream(tempFile);
            int len = 0;
            byte[] buffer = new byte[1024];
            while (-1 != (len = is.read(buffer))) {
                os.write(buffer, 0, len);
            }
            is.close();
            os.close();
            success = true;
            message = "Uploading file success!";
            if (success) {
                printMessage();
                SessionUtil.deleteRow(SessionConstant.CustGrantList.value(), String.valueOf(custNo));
                ExcelUtil eu = new ExcelUtil();
                FileInputStream fileInputStream = null;
                Map<String, Object> custGrants = null;
                fileInputStream = new FileInputStream(tempFile.getPath());
                List listFile = eu.read_new(fileInputStream);
                fileInputStream.close();
                listFile.remove(0);
                custGrants = SessionUtil.getRow(SessionConstant.CustGrantList.value(), custNo, sessCustNo);
                if (custGrants == null) {
                    custGrants = new HashMap<String, Object>();
                }
                for (Object aListFile : listFile) {
                    CustGrantsDTO custdtd = new CustGrantsDTO();
                    String[] al = aListFile.toString().substring(1, aListFile.toString().length() - 1).split(",");
                    if (al[0] != null) {
                        custdtd.setProjectNo(al[0]);
                    } else {
                        custdtd.setProjectNo("");
                    }
                    if (al[1] != null) {
                        custdtd.setProjectName(al[1]);
                    } else {
                        custdtd.setProjectName("");
                    }
                    if (al[2] != null) {
                        custdtd.setCategory(al[2]);
                    } else {
                        custdtd.setEmail(al[3]);
                    }
                    if (StringUtils.isNotEmpty(al[4])) {
                        custdtd.setIssueDate(DateUtils.formatStr2Date(
                                al[4], "yyyy-MM-dd"));
                    }
                    if (StringUtils.isNotEmpty(al[5])) {
                        custdtd.setExprDate(DateUtils.formatStr2Date(
                                al[5], "yyyy-MM-dd"));
                    }
                    if (al[6] != null) {
                        custdtd.setPi(al[6]);
                    } else {
                        custdtd.setPi("");
                    }
                    if (al[7] != null) {
                        custdtd.setOrganization(al[7]);
                    } else {
                        custdtd.setOrganization("");
                    }

                    String ala = al[8];
                    if (ala != null) {
                        custdtd.setState(al[8]);
                    } else {
                        custdtd.setState("");
                    }
                    if (al[9] != null) {
                        custdtd.setCountry(al[9]);
                    } else {
                        custdtd.setCountry("");
                    }
                    if (al[10] != null) {
                        custdtd.setAbst(al[10]);
                    } else {
                        custdtd.setAbst("");
                    }
                    if (al[11] != null) {
                        custdtd.setSubProjectNo(al[11]);
                    } else {
                        custdtd.setSubProjectNo("");
                    }
                    if (al[12] != null) {
                        custdtd.setFundingIc(al[12]);
                    } else {
                        custdtd.setFundingIc("");
                    }
                    if (al[13] != null) {
                        custdtd.setAmount(Double.valueOf(al[13]));
                    } else {
                        custdtd.setAmount(0.0);
                    }
                    String id = ServletActionContext.getRequest().getParameter("grant_id");   //接受点击列表传来的值得id
                    String idStr = "";
                    if (id == null) {
                        custdtd.setGrantId(null);
                        custdtd.setOperationType(OperationType.ADD);
                        idStr = SessionUtil.generateTempId();
                        custdtd.setGrantIdStr(idStr);
                        custGrants.put(idStr, custdtd);
                    }
                    if (StringUtils.isEmpty(id)
                            && custdtd.getGrantIdStr() == null) {
                        custdtd.setGrantId(null);
                        custdtd.setOperationType(OperationType.ADD);
                        idStr = SessionUtil.generateTempId();
                        custdtd.setGrantIdStr(idStr);
                        custGrants.put(idStr, custdtd);
                    }

                    if (custdtd.getGrantIdStr() != null
                            && !StringUtils.isNumeric(custdtd.getGrantIdStr())) {
                        custGrants.put(custdtd.getGrantIdStr(), custdtd);

                    }
                    if (StringUtils.isNotEmpty(id) && StringUtils.isNumeric(id)) {
                        custdtd.setOperationType(OperationType.EDIT);
                        custdtd.setGrantId(Integer.parseInt(id));
                        custGrants.put(id, custdtd);
                    }

                }
                SessionUtil.insertRow(SessionConstant.CustGrantList.value(),
                        custNo, sessCustNo, custGrants);
            }
        } catch (Exception
                e) {
            WSException exDTO = exceptionUtil.getExceptionDetails(e);
            exceptionUtil.logException(exDTO, this.getClass(), e,
                    new Exception().getStackTrace()[0].getMethodName(),
                    "INTF0203", SessionUtil.getUserId());
            rt.put("hasException", "Y");
            rt.put("exception", exDTO);
        }
        return NONE;
    }


    public String listPubGrant() throws Exception {
        return "listPubGrant";
    }

    @SuppressWarnings("unchecked")
    public String listGrant() throws Exception {
        PagerUtil<CustGrantsDTO> pagerUtil = new PagerUtil<CustGrantsDTO>();
        pageGrants = new Page<CustGrantsDTO>();
        pageGrants = pagerUtil.getRequestPage();
        if (!pageGrants.isOrderBySetted()) {
            pageGrants.setOrderBy("grantId");
            pageGrants.setOrder(Page.DESC);
        }
        pageGrants.setPageSize(12);
        Page<CustomerGrants> page = dozer.map(pageGrants, Page.class);
        pageGrants = customerService.listCustGrants(page, custNo);
        Map<String, CustGrantsDTO> dbGrantMap = new LinkedHashMap<String, CustGrantsDTO>();
        List<CustGrantsDTO> retGrantList = pageGrants.getResult();
        for (int i = 0; i < retGrantList.size(); i++) {
            dbGrantMap.put(String.valueOf(retGrantList.get(i).getGrantId()),
                    retGrantList.get(i));
        }
        // 取得session中的Grant List
        Map<String, Object> custGrants = SessionUtil.getRow(
                SessionConstant.CustGrantList.value(), custNo, sessCustNo);
        SessionEmergeUtil<CustGrantsDTO> sessionEmergeUtil = new SessionEmergeUtil<CustGrantsDTO>();
        Map<String, CustGrantsDTO> grantMap = sessionEmergeUtil
                .convertMap(custGrants);
        grantMap = sessionEmergeUtil.mergeList(grantMap, dbGrantMap, pageGrants
                .getPageNo());
        List<CustGrantsDTO> grantList = sessionEmergeUtil
                .getFilterList(grantMap);
//        if(grantList!=null){
//        System.out.println("size======="+grantList.size());
//        }
        pageGrants.setResult(grantList);
        ServletActionContext.getRequest().setAttribute("pagerInfo", pageGrants);
        return "listGrant";
    }

    @SuppressWarnings("unchecked")
    public String listPub() throws Exception {
        // Publication
        PagerUtil<CustPubsDTO> pagerUtilPub = new PagerUtil<CustPubsDTO>();
        pagePubs = new Page<CustPubsDTO>();
        pagePubs = pagerUtilPub.getRequestPage();
        if (!pagePubs.isOrderBySetted()) {
            pagePubs.setOrderBy("id");
            pagePubs.setOrder(Page.DESC);
        }
        pagePubs.setPageSize(12);
        Page<CustomerPublications> pageP = dozer.map(pagePubs, Page.class);
        pagePubs = customerService.getCustPublicationsList(pageP, custNo);
        Map<String, CustPubsDTO> dbPubMap = new LinkedHashMap<String, CustPubsDTO>();
        // 取得session中的Publication List
        Map<String, Object> custPubs = SessionUtil.getRow(
                SessionConstant.CustPubList.value(), custNo, sessCustNo);
        List<CustPubsDTO> retPubList = pagePubs.getResult();
        for (int i = 0; i < retPubList.size(); i++) {
            dbPubMap.put(String.valueOf(retPubList.get(i).getId()), retPubList
                    .get(i));
        }
        SessionEmergeUtil<CustPubsDTO> sessEmergeUtil = new SessionEmergeUtil<CustPubsDTO>();
        Map<String, CustPubsDTO> pubMap = sessEmergeUtil.convertMap(custPubs);
        pubMap = sessEmergeUtil.mergeList(pubMap, dbPubMap, pagePubs
                .getPageNo());
        List<CustPubsDTO> pubList = sessEmergeUtil.getFilterList(pubMap);
        pagePubs.setResult(pubList);
        ServletActionContext.getRequest().setAttribute("pagerInfo", pagePubs);
        return "listPub";
    }


    public String showGrantEditForm() throws Exception {
        Struts2Util.getRequest().setAttribute("type", "edit");
        return "showGrantEditForm";
    }

    public String showPubEditForm() throws Exception {
        Struts2Util.getRequest().setAttribute("type", "edit");
        return "showPubEditForm";
    }

    public String showGrantCreateForm() throws Exception {
        return "showGrantCreateForm";
    }

    public String showPubCreateForm() throws Exception {
        return "showPubCreateForm";
    }

    public String saveGrant() throws Exception {
        System.out.println("^^^++++++saveGrant+++++++^^^^^^^^^^^");
        Map<String, Object> custGrants = null;
        // 取得session中的Grant List
        System.out.println("custNo, sessCustNo: " + custNo + ", " + sessCustNo);
        System.out.println("SessionConstant.CustGrantList.value(): " + SessionConstant.CustGrantList.value());
        custGrants = SessionUtil.getRow(SessionConstant.CustGrantList.value(),
                custNo, sessCustNo);
        System.out.println("grantIdStr: " + grantIdStr);
        if (custGrants != null && !custGrants.isEmpty()) {
            if (custGrants.containsKey(grantIdStr)) {
                custGrantDTO = (CustGrantsDTO) custGrants.get(grantIdStr);
            } else {
                custGrantDTO = new CustGrantsDTO();
            }
        } else {
            custGrantDTO = new CustGrantsDTO();
        }

        custGrantDTO.setCategory(ServletActionContext.getRequest()
                .getParameter("grant_category"));
        custGrantDTO.setFundingIc(ServletActionContext.getRequest()
                .getParameter("grant_fundingIC"));
        custGrantDTO.setProjectNo(ServletActionContext.getRequest()
                .getParameter("grant_projNum"));
        custGrantDTO.setSubProjectNo(ServletActionContext.getRequest()
                .getParameter("grant_subProj"));
        custGrantDTO.setProjectName(ServletActionContext.getRequest()
                .getParameter("grant_projTitle"));
        custGrantDTO.setAbst(ServletActionContext.getRequest().getParameter(
                "grant_projAbst"));
        custGrantDTO.setPi(ServletActionContext.getRequest().getParameter(
                "grant_piName"));
        custGrantDTO.setEmail(ServletActionContext.getRequest().getParameter(
                "grant_contactEmail"));
        custGrantDTO.setOrganization(ServletActionContext.getRequest()
                .getParameter("grant_orgName"));
        custGrantDTO.setState(ServletActionContext.getRequest().getParameter(
                "grant_state"));
        custGrantDTO.setCountry(ServletActionContext.getRequest().getParameter(
                "grant_country"));
        String amountStr = ServletActionContext.getRequest().getParameter(
                "grant_amount");
        if (StringUtils.isNotEmpty(amountStr)) {
            custGrantDTO.setAmount(Double.valueOf(amountStr));
        }
        String exprDateStr = ServletActionContext.getRequest().getParameter(
                "grant_exprDate");
        System.out.println("exprDateStr: " + exprDateStr);
        if (StringUtils.isNotEmpty(exprDateStr)) {
            custGrantDTO.setExprDate(DateUtils.formatStr2Date(exprDateStr,
                    "yyyy-MM-dd"));
        }
        String issueDateStr = ServletActionContext.getRequest().getParameter(
                "grant_issueDate");
        System.out.println("issueDateStr: " + issueDateStr);
        if (StringUtils.isNotEmpty(issueDateStr)) {
            custGrantDTO.setIssueDate(DateUtils.formatStr2Date(issueDateStr,
                    "yyyy-MM-dd"));
        }
        if (custGrants == null) {
            custGrants = new HashMap<String, Object>();
        }
        // Map<String,Object> sessionMap = new HashMap<String, Object>();
        String id = ServletActionContext.getRequest().getParameter("grant_id");
        if (StringUtils.isEmpty(id) && custGrantDTO.getGrantIdStr() == null) {
            custGrantDTO.setGrantId(null);
            custGrantDTO.setOperationType(OperationType.ADD);
            String idStr = SessionUtil.generateTempId();
            custGrantDTO.setGrantIdStr(idStr);
            custGrants.put(idStr, custGrantDTO);
        }
        if (custGrantDTO.getGrantIdStr() != null
                && !StringUtils.isNumeric(custGrantDTO.getGrantIdStr())) {
            custGrants.put(custGrantDTO.getGrantIdStr(), custGrantDTO);
        }
        if (StringUtils.isNotEmpty(id) && StringUtils.isNumeric(id)) {
            custGrantDTO.setOperationType(OperationType.EDIT);
            custGrantDTO.setGrantId(Integer.parseInt(id));
            custGrants.put(id, custGrantDTO);
        }
        SessionUtil.insertRow(SessionConstant.CustGrantList.value(), custNo,
                sessCustNo, custGrants);
        System.out.println("custGrantDTO: " + custGrantDTO);
        System.out.println("^^^++++++saveGrant+++++++^^^^^^^^^^^");
        return NONE;
    }

    public String savePub() throws Exception {
        Map<String, Object> custPubs = null;
        // 取得session中的Grant List
        System.out.println("custNo, sessCustNo: " + custNo + ", " + sessCustNo);
        custPubs = SessionUtil.getRow(SessionConstant.CustPubList.value(),
                custNo, sessCustNo);
        System.out.println("pubIdStr: " + pubIdStr);
        if (custPubs != null && !custPubs.isEmpty()) {
            if (custPubs.containsKey(pubIdStr)) {
                custPubDTO = (CustPubsDTO) custPubs.get(pubIdStr);
            } else {
                custPubDTO = new CustPubsDTO();
            }
        } else {
            custPubDTO = new CustPubsDTO();
        }
        custPubDTO.setTitle(ServletActionContext.getRequest().getParameter(
                "pub_title"));
        custPubDTO.setPublicationName(ServletActionContext.getRequest()
                .getParameter("pub_publicationName"));
        String issueDateStr = ServletActionContext.getRequest().getParameter(
                "pub_issueDate");
        custPubDTO.setIssueDate(com.genscript.gsscm.common.util.DateUtils
                .formatStr2Date(issueDateStr));
        custPubDTO.setCoAuthor(ServletActionContext.getRequest().getParameter(
                "pub_coAuthor"));
        custPubDTO.setAbst(ServletActionContext.getRequest().getParameter(
                "pub_abst"));
        custPubDTO.setRelatedArea(ServletActionContext.getRequest()
                .getParameter("pub_relatedArea"));
        custPubDTO.setKeyWords(ServletActionContext.getRequest().getParameter(
                "pub_keyWords"));
        custPubDTO.setEmail(ServletActionContext.getRequest().getParameter(
                "pub_email"));
        custPubDTO.setUrl(ServletActionContext.getRequest().getParameter(
                "pub_url"));
        String id = ServletActionContext.getRequest().getParameter("pub_id");
        if (custPubs == null) {
            custPubs = new HashMap<String, Object>();
        }
        // Map<String,Object> sessionMap = new HashMap<String, Object>();
        if (StringUtils.isEmpty(id) && custPubDTO.getPubIdStr() == null) {
            custPubDTO.setId(null);
            custPubDTO.setOperationType(OperationType.ADD);
            String idStr = SessionUtil.generateTempId();
            custPubDTO.setPubIdStr(idStr);
            custPubs.put(idStr, custPubDTO);
        }
        if (custPubDTO.getPubIdStr() != null
                && !StringUtils.isNumeric(custPubDTO.getPubIdStr())) {
            custPubs.put(custPubDTO.getPubIdStr(), custPubDTO);
        }
        if (StringUtils.isNotEmpty(id) && StringUtils.isNumeric(id)) {
            custPubDTO.setOperationType(OperationType.EDIT);
            custPubDTO.setId(Integer.parseInt(id));
            custPubs.put(id, custPubDTO);
        }
        SessionUtil.insertRow(SessionConstant.CustPubList.value(), custNo,
                sessCustNo, custPubs);
        System.out.println("custPubs: " + custPubs);
        return NONE;
    }

    public String delPub() throws Exception {
        SessionEmergeUtil<CustPubsDTO> sessionEmergeUtil = new SessionEmergeUtil<CustPubsDTO>();
        sessionEmergeUtil.deleteSessionObj("pubIds", custNo, sessCustNo,
                SessionConstant.CustPubList,
                "com.genscript.gsscm.customer.dto.CustPubsDTO");
        return NONE;
    }

    public String delGrant() throws Exception {
        SessionEmergeUtil<CustGrantsDTO> sessionEmergeUtil = new SessionEmergeUtil<CustGrantsDTO>();
        sessionEmergeUtil.deleteSessionObj("grantIds", custNo, sessCustNo,
                SessionConstant.CustGrantList,
                "com.genscript.gsscm.customer.dto.CustGrantsDTO");
        return NONE;
    }

    public void setSessCustNo(String sessCustNo) {
        this.sessCustNo = sessCustNo;
    }

    public String getSessCustNo() {
        return sessCustNo;
    }

    public void setCustNo(Integer custNo) {
        this.custNo = custNo;
    }

    public Integer getCustNo() {
        return custNo;
    }

    public Page<CustPubsDTO> getPagePubs() {
        return pagePubs;
    }

    public Page<CustGrantsDTO> getPageGrants() {
        return pageGrants;
    }

    public void setPageGrants(Page<CustGrantsDTO> pageGrants) {
        this.pageGrants = pageGrants;
    }

    public void setPagePubs(Page<CustPubsDTO> pagePubs) {
        this.pagePubs = pagePubs;
    }

    @Override
    public String list() throws Exception {

        return null;
    }

    @Override
    public String input() throws Exception {

        return null;
    }

    @Override
    public String save() throws Exception {

        return null;
    }

    @Override
    public String delete() throws Exception {

        return null;
    }

    @Override
    protected void prepareModel() throws Exception {


    }

    public String getGrantIdStr() {
        return grantIdStr;
    }

    public void setGrantIdStr(String grantIdStr) {
        this.grantIdStr = grantIdStr;
    }

    public CustPubsDTO getCustPubDTO() {
        return custPubDTO;
    }

    public void setCustPubDTO(CustPubsDTO custPubDTO) {
        this.custPubDTO = custPubDTO;
    }

    public String getPubIdStr() {
        return pubIdStr;
    }

    public void setPubIdStr(String pubIdStr) {
        this.pubIdStr = pubIdStr;
    }

    public CustGrantsDTO getCustGrantDTO() {
        return custGrantDTO;
    }

    public void setCustGrantDTO(CustGrantsDTO custGrantDTO) {
        this.custGrantDTO = custGrantDTO;
    }


    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public ExceptionService getExceptionUtil() {
        return exceptionUtil;
    }

    public void setExceptionUtil(ExceptionService exceptionUtil) {
        this.exceptionUtil = exceptionUtil;
    }

    public DozerBeanMapper getDozer() {
        return dozer;
    }

    public void setDozer(DozerBeanMapper dozer) {
        this.dozer = dozer;
    }

    public File getXls() {
        return xls;
    }

    public void setXls(File xls) {
        this.xls = xls;
    }

    public String getXlsFileName() {
        return xlsFileName;
    }

    public void setXlsFileName(String xlsFileName) {
        this.xlsFileName = xlsFileName;
    }

    public String getXlsfileName() {
        return xlsFileName;
    }

    public void setXlsfileName(String xlsFileName) {
        this.xlsFileName = xlsFileName;
    }


    public String getMessage() {
        return message;
    }


}
