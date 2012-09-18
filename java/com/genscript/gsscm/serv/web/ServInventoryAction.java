/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genscript.gsscm.serv.web;

import com.genscript.gsscm.serv.*;
import com.genscript.gsscm.basedata.dto.AllCountryDTO;
import com.genscript.gsscm.basedata.dto.AllStateDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.product.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import com.genscript.gsscm.common.constant.SessionPdtServ;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.serv.dto.ServiceDTO;
import com.genscript.gsscm.serv.dto.ServiceRestrictShipDTO;
import com.genscript.gsscm.serv.dto.ServiceStockStatDTO;
import com.genscript.gsscm.serv.service.ServService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jinsite
 */
@Results({
    @Result(name = "showStockStat", location = "service/service/pdtServ_inventory.jsp"),
    @Result(name = "input", location = "service/service/pdtServ_input.jsp"),
    @Result(name = "edit", location = "service/service/pdtServ_edit.jsp")
})
public class ServInventoryAction extends BaseAction<ServiceDTO> {

    private Integer psId;
    private String sessionServiceId;
    private String type;
    @Autowired
    private ServService servService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PublicService publicService;
  
    private String catalogNo;
    private ServiceDTO service;
    private List<Warehouse> warehouseList;
    private Map<String, ServiceRestrictShipDTO> restrictShipList = new HashMap<String, ServiceRestrictShipDTO>();
    private List<Integer> delList = new ArrayList<Integer>();
    private ServiceRestrictShipDTO restrictShip;
    private ServiceStockStatDTO stockStateDTO;
    // private List<Integer> editList = new ArrayList<Integer>();

    @Override
    public String list() throws Exception {
        if (psId != null) {
            sessionServiceId = String.valueOf(psId);
            setService(servService.getServDetail(getPsId()));
        }
        if (service != null) {
            stockStateDTO = servService.getServiceStockStat(psId);
            Map<String, ServiceRestrictShipDTO> tempMap = (Map<String, ServiceRestrictShipDTO>) SessionUtil.getRow(SessionPdtServ.ServiceRestrictShipList.value(), sessionServiceId);
            if (tempMap != null) {// Judging the Map is not null
                restrictShipList = tempMap;// ((Map<String,ServiceRestrictShipDTO>) SessionUtil.getRow(SessionPdtServ.ServiceRestrictShipList.value(), sessionServiceId));
            } else {
                List<ServiceRestrictShipDTO> serRestrictShipList = servService.getServiceRestrictShipList(psId);
                if (serRestrictShipList != null && serRestrictShipList.size() > 0) {//Judging the serRestrictShipList is null or size ge 0.
                    for (int i = 0; i < serRestrictShipList.size(); i++) {
                        restrictShipList.put(serRestrictShipList.get(i).getId().toString(), serRestrictShipList.get(i));
                    }
                }
                SessionUtil.insertRow(SessionPdtServ.ServiceRestrictShipList.value(), sessionServiceId, restrictShipList);
            }
        } else {
            restrictShipList = (Map<String, ServiceRestrictShipDTO>) SessionUtil.getRow(SessionPdtServ.ServiceRestrictShipList.value(), sessionServiceId);
            service = new ServiceDTO();
        }
        warehouseList = productService.getWarehouseList();
        return "showStockStat";
    }

    @Override
    public String input() throws Exception {
        if (psId != null) {
            sessionServiceId = String.valueOf(psId);
        }
        //SessionUtil.deleteRow(SessionPdtServ.ServiceRestrictShipList.value(), sessionServiceId);
        //SessionUtil.deleteRow(SessionPdtServ.DelServiceRestrictShipList.value(), sessionServiceId);
        List<AllCountryDTO> list = publicService.getAllCountryState();
        String countryStates = "[";
        for (int i = 0; i < list.size(); i++) {
            List<AllStateDTO> states = list.get(i).getAllStateDTOs();
            String statesStr = "";
            if (states != null && states.size() > 0) {
                statesStr += "\"allStateDTOs\":[";
                for (int j = 0; j < states.size(); j++) {
                    statesStr += "{\"name\":\"" + states.get(j).getName() + "\",\"stateCode\":\"" + states.get(j).getStateCode() + "\"},";
                }
                statesStr = statesStr.substring(0, statesStr.length() - 1) + "],";
            }
            countryStates += "{" + statesStr + "\"countryCode\":\"" + list.get(i).getCountryCode() + "\",\"name\":\"" + list.get(i).getName() + "\"},";
        }
        countryStates = countryStates.substring(0, countryStates.length() - 1) + "]";
        ServletActionContext.getRequest().setAttribute("countryStates", countryStates);
        return "input";
    }

    public String edit() throws Exception {
        if (psId != null) {
            sessionServiceId = String.valueOf(psId);
        }
        restrictShipList = (Map<String, ServiceRestrictShipDTO>) SessionUtil.getRow(SessionPdtServ.ServiceRestrictShipList.value(), sessionServiceId);
        String idStr = Struts2Util.getParameter("shipInfo");
        if (restrictShipList != null) {
            restrictShip = restrictShipList.get(idStr);
        }
        if (psId != null) {
            service = (servService.getServDetail(psId));
        }
        ServletActionContext.getRequest().setAttribute("idStr", idStr);
        return "edit";
    }

    public String saveEdit() throws Exception {
        if (psId != null) {
            sessionServiceId = String.valueOf(psId);
        }
        if (SessionUtil.getRow(SessionPdtServ.ServiceRestrictShipList.value(), sessionServiceId) != null) {
            restrictShipList = (Map<String, ServiceRestrictShipDTO>) SessionUtil.getRow(SessionPdtServ.ServiceRestrictShipList.value(), sessionServiceId);
        }
        String idStr = Struts2Util.getParameter("shipAreaId");
        // System.out.println("ID String:" + idStr);
        ServiceRestrictShipDTO temp = getRestrictShipList().get(idStr);
        String effFrom = Struts2Util.getParameter("effFrom").replaceAll("\\s+-\\s+-\\s+", "");
        if (effFrom != null && StringUtils.isNotBlank(effFrom) && StringUtils.isNotEmpty(effFrom)) {
            Date effFromDate = DateUtils.formatStr2Date(effFrom, "yyyy-MM-dd");
            temp.setEffFrom(effFromDate);
        } else {
            temp.setEffFrom(null);
        }
        String effTo = Struts2Util.getParameter("effTo").replaceAll("\\s+-\\s+-\\s+", "");
        if (effTo != null && StringUtils.isNotBlank(effTo) && StringUtils.isNotEmpty(effTo)) {
            Date effToDate = DateUtils.formatStr2Date(effTo, "yyyy-MM-dd");
            temp.setEffTo(effToDate);
        } else {
            temp.setEffTo(null);
        }
        getRestrictShipList().remove(idStr);
        getRestrictShipList().put(idStr, temp);
        SessionUtil.updateRow(SessionPdtServ.ServiceRestrictShipList.value(), sessionServiceId, getRestrictShipList());
        return null;
    }

    @Override
    public String save() throws Exception {
        if (psId != null) {
            sessionServiceId = String.valueOf(psId);
        }
        String areaListStr = Struts2Util.getParameter("areaList");
        String[] areaListArr = areaListStr.split("##");
        Map<String, ServiceRestrictShipDTO> list;
        if (SessionUtil.getRow(SessionPdtServ.ServiceRestrictShipList.value(), sessionServiceId) != null) {
            list = (Map<String, ServiceRestrictShipDTO>) SessionUtil.getRow(SessionPdtServ.ServiceRestrictShipList.value(), sessionServiceId);
        } else {
            list = new HashMap<String, ServiceRestrictShipDTO>();
        }
        for (int i = 0; i < areaListArr.length; i++) {
            ServiceRestrictShipDTO temp = new ServiceRestrictShipDTO();
            JSONObject jsonobj = JSONObject.fromObject(areaListArr[i]);
            temp.setCountry((String) jsonobj.get("countryCode"));
            temp.setCountryName((String) jsonobj.get("countryName"));
            temp.setState((String) jsonobj.get("stateCode"));
            temp.setStateName((String) jsonobj.get("stateName"));
            temp.setZipCode((String) jsonobj.get("zipCode"));
            if (psId != null) {
                temp.setServiceId(getPsId());
            }
            String effFrom = Struts2Util.getParameter("effFrom");
            if (effFrom != null && !effFrom.isEmpty()) {
                temp.setEffFrom(DateUtils.formatStr2Date(effFrom, "yyyy-MM-dd"));
            }
            String effTo = Struts2Util.getParameter("effTo");
            if (effTo != null && !effTo.isEmpty()) {
                temp.setEffTo(DateUtils.formatStr2Date(effTo, "yyyy-MM-dd"));
            }
            list.put(SessionUtil.generateTempId(), temp);
        }
        SessionUtil.insertRow(SessionPdtServ.ServiceRestrictShipList.value(), sessionServiceId, list);
        return null;
    }

    @Override
    public String delete() throws Exception {
        if (psId != null) {
            sessionServiceId = String.valueOf(psId);
        }
        restrictShipList = ((Map<String, ServiceRestrictShipDTO>) SessionUtil.getRow(SessionPdtServ.ServiceRestrictShipList.value(), sessionServiceId));
       if(restrictShipList==null)
           return null;
        String delIdStrs = ServletActionContext.getRequest().getParameter("delIdStr");
        String[] delIdStr = delIdStrs.split(",");
        delList=new ArrayList<Integer>();
        for (int i = 0; i < delIdStr.length; i++) {
            System.out.println("delIdStr:"+delIdStr[i]);
            restrictShipList.remove(delIdStr[i]);
            if (StringUtils.isNumeric(delIdStr[i].trim())) {
                System.out.println("delIdStr 1:"+delIdStr[i]);
                delList.add(Integer.parseInt(delIdStr[i]));
            }
        }
        System.out.println("delList:"+delList);
        SessionUtil.insertRow(SessionPdtServ.DelServiceRestrictShipList.value(), sessionServiceId, delList);
        SessionUtil.insertRow(SessionPdtServ.ServiceRestrictShipList.value(), sessionServiceId, restrictShipList);
        return null;
    }

    @Override
    protected void prepareModel() throws Exception {
        // throw new UnsupportedOperationException("Not supported yet.");
        
    }

    /**
     * @return the warehouseList
     */
    public List<Warehouse> getWarehouseList() {
        return warehouseList;
    }

    /**
     * @return the restrictShipList
     */
    public Map<String, ServiceRestrictShipDTO> getRestrictShipList() {
        return restrictShipList;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
     * @return the restrictShip
     */
    public ServiceRestrictShipDTO getRestrictShip() {
        return restrictShip;
    }

    /**
     * @param restrictShip the restrictShip to set
     */
    public void setRestrictShip(ServiceRestrictShipDTO restrictShip) {
        this.restrictShip = restrictShip;
    }

    /**
     * @return the stockStateDTO
     */
    public ServiceStockStatDTO getStockStateDTO() {
        return stockStateDTO;
    }

    /**
     * @param stockStateDTO the stockStateDTO to set
     */
    public void setStockStateDTO(ServiceStockStatDTO stockStateDTO) {
        this.stockStateDTO = stockStateDTO;
    }

    /**
     * @return the catalogNo
     */
    public String getCatalogNo() {
        return catalogNo;
    }

    /**
     * @param catalogNo the catalogNo to set
     */
    public void setCatalogNo(String catalogNo) {
        this.catalogNo = catalogNo;
    }

    /**
     * @return the service
     */
    public ServiceDTO getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(ServiceDTO service) {
        this.service = service;
    }

    /**
     * @return the sessionSericeId
     */
    public String getSessionServiceId() {
        return sessionServiceId;
    }

    /**
     * @param sessionSericeId the sessionSericeId to set
     */
    public void setSessionServiceId(String sessionServiceId) {
        this.sessionServiceId = sessionServiceId;
    }
}
