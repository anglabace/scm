package com.genscript.gsscm.systemsetting.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.entity.Source;
import com.genscript.gsscm.systemsetting.service.SystemSettingService;

@Results({
        @Result(name = "showCreateForm", location = "systemsetting/quoteorder_source_create_form.jsp"),
        @Result(name = "showEditForm", location = "systemsetting/quoteorder_source_create_form.jsp")})
public class QuoteOrderSourceAction extends BaseAction<Source> {

    /**
     *
     */
    private static final long serialVersionUID = -3077035408581548924L;
    private String opType;
    private String id;
    private Source source;
    private float totalCost;
    private List<DropDownListDTO> specDropDownist;
    @Autowired
    private PublicService publicService;
    @Autowired
    private SystemSettingService systemSettingService;

    //获取从其他列表页面点过来的请求--Zhang Yong
    private String operation_method;

    public String showCreateForm() throws Exception {
        setOpType("add");
        List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
        speclListName.add(SpecDropDownListName.CATALOG);
        specDropDownist = publicService.getSpecDropDownList(speclListName);
        return "showCreateForm";
    }


    /**
     * 从数据库中获得source的详细信息
     */
    private void getSourceDetail() {
        if (StringUtils.isNumeric(id)) {
            Integer sourceId = Integer.parseInt(id);
            source = systemSettingService.getSourceDetail(sourceId);
        }

    }

    @SuppressWarnings("unchecked")
    public String save() throws Exception {
        //*********** Add By Zhang Yong Start *****************************//
        //校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
        if (id != null && !("").equals(id)) {
            String editUrl = "quote_order_source!input.action?id=" + id;
            OrderLockRelease orderLockRelease = new OrderLockRelease();
            String byUser = orderLockRelease.checkOrderStatus(editUrl);
            if (byUser != null) {
                operation_method = byUser;
                Struts2Util.renderText("Save Customer Purchase Order fail,the Customer Purchase Order is editing by " + operation_method);
                return NONE;
            }
        }
        //*********** Add By Zhang Yong End *****************************//

        Integer userId = SessionUtil.getUserId();
        System.out.println("Source: " + source);
        Map<String, Object> sessionMap = (HashMap<String, Object>) SessionUtil
                .getRow(SessionConstant.SourceSetting.value(),
                        String.valueOf(userId));
        if (sessionMap == null) {
            sessionMap = new LinkedHashMap<String, Object>();
        }
        if (opType.equals("add") && StringUtils.isBlank(id)) {
            id = SessionUtil.generateTempId();
            sessionMap.put(id, source);
        } else if (opType.equals("update") && !StringUtils.isNumeric(id)) {
            sessionMap.put(id, source);
        } else {
            sessionMap.put(source.getSourceId() + "", source);
        }
        SessionUtil.insertRow(SessionConstant.SourceSetting.value(),
                String.valueOf(userId), sessionMap);
        Struts2Util.renderText("SUCCESS" + "," + id);
        return NONE;
    }

    @Override
    public Source getModel() {
        return source;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<DropDownListDTO> getSpecDropDownist() {
        return specDropDownist;
    }

    public void setSpecDropDownist(List<DropDownListDTO> specDropDownist) {
        this.specDropDownist = specDropDownist;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String list() throws Exception {
        return null;
    }

    @Override
    public String input() throws Exception {
        if (id != null && !("").equals(id)) {
            //*********** Add By Zhang Yong Start *****************************//
            //判断将要修改的单号是否正在被操作
            String editUrl = "quote_order_source!input.action?id=" + id;
            OrderLockRelease orderLockRelease = new OrderLockRelease();
            String byUser = orderLockRelease.checkOrderStatus(editUrl);
            if (byUser != null) {
                operation_method = byUser;
            }
            //*********** Add By Zhang Yong End *****************************//
        } else {
            //*********** Add By Zhang Yong Start *****************************//
            //释放application中的订单锁
            OrderLockRelease realeseOrderLock = new OrderLockRelease();
            realeseOrderLock.releaseOrderLock();
            //*********** Add By Zhang Yong End *****************************//
        }

        setOpType("update");
        if (source != null) {
        	BigDecimal zero = new BigDecimal(0);
            if (source.getListCost() != null && source.getPostageCost() != null && source.getPrintingCost() != null) {
            	totalCost = (source.getPostageCost()==null?zero:source.getPostageCost()).add(source.getListCost()!=null?source.getListCost():zero)
				.add(source.getMailingCost()!=null?source.getMailingCost():zero).add(source.getPrintingCost()!=null?source.getPrintingCost():zero)
				.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
            }
        }
        List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
        speclListName.add(SpecDropDownListName.CATALOG);
        specDropDownist = publicService.getSpecDropDownList(speclListName);
        return "showEditForm";
    }

    @Override
    public String delete() throws Exception {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void prepareModel() throws Exception {
        Integer userId = SessionUtil.getUserId();
        if (StringUtils.isNotEmpty(id)) {
            if (!StringUtils.isNumeric(id)) {
                source = (Source) SessionUtil.getOneRow(
                        SessionConstant.SourceSetting.value(),
                        String.valueOf(userId), id);
            } else {
                Map<String, Object> sessionMap = (HashMap<String, Object>) SessionUtil
                        .getRow(SessionConstant.SourceSetting.value(),
                                String.valueOf(userId));
                if (sessionMap != null && !sessionMap.isEmpty()) {
                    if (sessionMap.containsKey(id)) {
                        source = (Source) sessionMap.get(id);
                    } else {
                        getSourceDetail();
                    }
                } else {
                    getSourceDetail();
                }
            }
        } else {
            source = new Source();
        }
    }


    public String getOperation_method() {
        return operation_method;
    }


    public void setOperation_method(String operationMethod) {
        operation_method = operationMethod;
    }
}
