/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.genscript.gsscm.product.web;

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
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.RequestApproveType;
import com.genscript.gsscm.common.constant.SessionPdtServ;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.product.dto.ComponentDTO;
import com.genscript.gsscm.product.entity.Component;
import com.genscript.gsscm.product.entity.ProductListBean;
import com.genscript.gsscm.product.service.ProductService;

/**
 * @author jinsite
 */
@Results({
        @Result(name = "list", location = "product/product/breakdownList.jsp"),
        @Result(name = "input", location = "product/product/breakdown_add.jsp")
})
public class ComponentAction extends BaseAction<ComponentDTO> {

    @Autowired
    private ProductService productService;
    private Integer psId;
    private String sessionPSID;
    private Map<String, ComponentDTO> breakdownList = new HashMap<String, ComponentDTO>();
    private List<ProductListBean> pStdPrice;
    private Page<ProductListBean> ppStdPrice;

    /**
     * @return
     * @throws Exception
     * @Function List the component
     * @Author Lichun Cui
     * @Date 2010-09-30
     */
    @Override
    public String list() throws Exception {
        if (psId != null) {
            sessionPSID = String.valueOf(psId);
        }
        // breakdownList=(Map<String, ComponentDTO>) SessionUtil.getRow(SessionPdtServ.ComponentList.value(), sessionPSID);
        breakdownList = (Map<String, ComponentDTO>) SessionUtil.getRow(SessionPdtServ.ComponentList.value(), sessionPSID);
        System.out.println("================breakdownList Size:" + (breakdownList == null ? 0 : breakdownList.size()));
        if (breakdownList == null) {
            breakdownList = new HashMap<String, ComponentDTO>();
            Page<Component> page = new Page<Component>();
            page.setPageSize(10000);
            page.setOrderBy("listSeq");
            page.setOrder(Page.ASC);
            Page<ComponentDTO> pagelist = productService.getComponentList(page, psId);
            List<ComponentDTO> list = pagelist.getResult();
            int count = list.size();
            for (int i = 0; i < count; i++) {
                ComponentDTO temp = list.get(i);
                System.out.println("======================>ID:" + temp.getId());
                breakdownList.put(temp.getId().toString(), temp);
            }
            if (breakdownList != null && breakdownList.size() > 0)
                SessionUtil.insertRow(SessionPdtServ.ComponentList.value(), sessionPSID, breakdownList);
        }
        return "list";
    }

    /**
     * @return
     * @throws Exception
     * @Function: show the component input page
     * @Author: Lichun Cui
     * @Date: 2010-09-30
     */
    @Override
    public String input() throws Exception {
        if (psId != null) {
            sessionPSID = String.valueOf(psId);
        }
        // 获得分页请求相关数据：如第几页
        PagerUtil<ProductListBean> pagerUtil = new PagerUtil<ProductListBean>();
        ppStdPrice = pagerUtil.getRequestPage();
        // 设置默认每页显示记录条数
        if (ppStdPrice.getPageSize() == null
                || ppStdPrice.getPageSize().intValue() < 1) {
        	ppStdPrice.setPageSize(15);
        }
        List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
        if (!ppStdPrice.isOrderBySetted()) {
        	ppStdPrice.setOrderBy("productId");
        	ppStdPrice.setOrder(Page.DESC);
        }
        ppStdPrice = this.productService.searchPageProductList(ppStdPrice, filters);
        // 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
        PageDTO pagerInfo = pagerUtil.formPage(ppStdPrice);
        ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
        // SessionUtil.deleteRow(SessionPdtServ.ComponentList.value(), sessionPSID);
        // SessionUtil.deleteRow(SessionPdtServ.DelComponentList.value(), sessionPSID);
       /* String catNo = Struts2Util.getParameter("catNo");
        catNo = StringUtils.isNotBlank(catNo) && StringUtils.isNotEmpty(catNo) ? catNo.trim() : "";
        String name = Struts2Util.getParameter("name");
        name = StringUtils.isNotBlank(name) && StringUtils.isNotEmpty(name) ? name.trim() : "";
        String noList = Struts2Util.getParameter("noList");
        ServletActionContext.getRequest().setAttribute("noListStr", noList);
        noList = StringUtils.isNotBlank(noList) && StringUtils.isNotEmpty(noList) ? noList.trim() : "";

        List<String> noLists = new ArrayList<String>();
        String[] arrList = noList.split(",");
        noLists.addAll(Arrays.asList(arrList));
        if (StringUtils.isNotBlank(catNo) || StringUtils.isNotBlank(name))
            pStdPrice = productService.searchProductList(catNo, name, noLists);//12.24号mingrs更改，数据应该从v_allProduct中读取。*/
        return "input";
    }

    /**
     * Function: Save the component to Session
     *
     * @return null
     * @throws Exception Author: Lichun Cui
     *                   Date: 2010-09-30
     */
    @Override
    public String save() throws Exception {
        String catStr = Struts2Util.getParameter("catStr");
        System.out.println(catStr);
        if (psId != null) {
            sessionPSID = String.valueOf(psId);
        }

        breakdownList = ((Map<String, ComponentDTO>) SessionUtil.getRow(SessionPdtServ.ComponentList.value(), sessionPSID));
        if (StringUtils.isNotBlank(catStr) && StringUtils.isNotEmpty(catStr)) {
            if (breakdownList == null) {
                breakdownList = (new HashMap<String, ComponentDTO>());
            }

            String[] catStrArr = catStr.split("<==>");
            for (int i = 0; i < catStrArr.length; i++) {
                System.out.println("======catStrArr:" + catStrArr[i]);
                ComponentDTO temp = new ComponentDTO();
                temp.setListSeq(breakdownList.size() + 1);
                String[] tempArr = catStrArr[i].split("<;>");
                if (StringUtils.isNotBlank(tempArr[0]) && StringUtils.isNotEmpty(tempArr[0])) {
                    temp.setCpntCatalogNo(tempArr[0]);
                }
              //  System.out.println(tempArr[0]);
              //  System.out.println(tempArr[1]); //这里就是字符串
                if (StringUtils.isNotBlank(tempArr[1]) && StringUtils.isNotEmpty(tempArr[1])) {
                    String tempstr = "";
                    String tempstr2 = "";
                    //首先判断tempArr[1]里面是否有 &字符 如果有继续..
                    //     if (tempArr[1].matches("<@>")) {
                    boolean isExistXXX = tempArr[1].contains("<@>");
                    if (isExistXXX) {
                     //   System.out.println("wo kao  还真有啊。。。");
                        int nl = tempArr[1].split("<@>").length;
                        String ss[] = tempArr[1].split("<@>");
                     //   System.out.println(nl + "     =========个");
                        for (int g = 0; g < nl; g++) {
                            tempstr += ss[g] + "&";
                        }
                        tempstr2 = tempstr.substring(0, tempstr.length() - 1);
                    } else {
                        tempstr2 = tempArr[1];
                    }
                   //System.out.println("tempstr============" + tempstr2);
                    temp.setItem(tempstr2);
                }
                if (StringUtils.isNotBlank(tempArr[2]) && StringUtils.isNotEmpty(tempArr[2]) && StringUtils.isNumeric(tempArr[2])) {
                    temp.setLeadTime(Integer.parseInt(tempArr[2]));
                }
                if (StringUtils.isNotBlank(tempArr[3]) && StringUtils.isNotEmpty(tempArr[3])) {
                    temp.setSymbol(tempArr[3]);
                }

                if (StringUtils.isNotBlank(tempArr[4]) && StringUtils.isNotEmpty(tempArr[4])) {
                    temp.setPrice(Double.parseDouble(tempArr[4]));
                }
                if (StringUtils.isNotBlank(tempArr[5]) && StringUtils.isNotEmpty(tempArr[5])) {
                    temp.setQuantity(Double.parseDouble(tempArr[5]));
                }
                if (psId != null) {
                    temp.setProductId(psId);
                }
            //    System.out.println("=========~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~================");
                breakdownList.put(SessionUtil.generateTempId(), temp);
            }
        }

        SessionUtil.insertRow(SessionPdtServ.ComponentList.value(), sessionPSID, breakdownList);
        breakdownList = (Map<String, ComponentDTO>) SessionUtil.getRow(SessionPdtServ.ComponentList.value(), sessionPSID);
        System.out.println("=========================Save breakdownList List:" + (breakdownList == null ? 0 : breakdownList.size()));
        return null;
    }

    /**
     * Function: Delete the component form session
     *
     * @return null
     * @throws Exception Author: Lichun Cui
     *                   Date: 2010-09-30
     */
    @Override
    public String delete() throws Exception {
        if (psId != null)
            sessionPSID = String.valueOf(psId);
        breakdownList = ((Map<String, ComponentDTO>) SessionUtil.getRow(SessionPdtServ.ComponentList.value(), sessionPSID));
        List<Integer> delList = new ArrayList<Integer>();
        String delIdStrs = ServletActionContext.getRequest().getParameter("itemId");
        String[] delIdStr = delIdStrs.split(",");
        for (int i = 0; i < delIdStr.length; i++) {
            System.out.println("========================>Delete ID:" + delIdStr[i]);
            breakdownList.remove(delIdStr[i]);
            if (StringUtils.isNumeric(delIdStr[i])) {
                delList.add(Integer.parseInt(delIdStr[i]));
            }
        }
        SessionUtil.insertRow(SessionPdtServ.DelComponentList.value(), sessionPSID, delList);
        SessionUtil.insertRow(SessionPdtServ.ComponentList.value(), sessionPSID, breakdownList);

        return null;
    }

    @Override
    protected void prepareModel() throws Exception {

    }

    /**
     * @return the psId
     */
    public Integer getPsId() {
        return psId;
    }

    /**
     * @param psId the psId to set
     */
    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    /**
     * @return the sessionPSID
     */
    public String getSessionPSID() {
        return sessionPSID;
    }

    /**
     * @param sessionPSID the sessionPSID to set
     */
    public void setSessionPSID(String sessionPSID) {
        this.sessionPSID = sessionPSID;
    }


    public List<ProductListBean> getPStdPrice() {
        return pStdPrice;
    }

    public void setPStdPrice(List<ProductListBean> stdPrice) {
        pStdPrice = stdPrice;
    }

    /**
     * @return the breakdownList
     */
    public Map<String, ComponentDTO> getBreakdownList() {
        return breakdownList;
    }

    /**
     * @param breakdownList the breakdownList to set
     */
    public void setBreakdownList(Map<String, ComponentDTO> breakdownList) {
        this.breakdownList = breakdownList;
    }

	public Page<ProductListBean> getPpStdPrice() {
		return ppStdPrice;
	}

	public void setPpStdPrice(Page<ProductListBean> ppStdPrice) {
		this.ppStdPrice = ppStdPrice;
	}

}
