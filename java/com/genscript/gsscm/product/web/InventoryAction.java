/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genscript.gsscm.product.web;

import com.genscript.gsscm.basedata.dto.AllCountryDTO;
import com.genscript.gsscm.basedata.dto.AllStateDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.product.dto.ProductDTO;
import com.genscript.gsscm.product.dto.ProductStockStatDTO;
import com.genscript.gsscm.product.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import com.genscript.gsscm.common.constant.SessionPdtServ;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.product.dto.RestrictShipDTO;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Function: Add, Modify and Delete Inventory Information
 * Date: 2010-09-20
 * @author Lichun Cui
 */
@Results({
    @Result(name = "showStockStat", location = "product/product/pdtServ_inventory.jsp"),
    @Result(name = "input", location = "product/product/pdtServ_input.jsp"),
    @Result(name = "edit", location = "product/product/pdtServ_edit.jsp")
})
public class InventoryAction extends BaseAction<ProductStockStatDTO> {

    private Integer psId;
    private String sessionPSID;
    private String type;
    @Autowired
    private ProductService productService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private DozerBeanMapper dozer;
    private ProductStockStatDTO stockStatDTO;
    private String catalogNo;
    private ProductDTO product;
    private List<Warehouse> warehouseList;
    private Map<String, RestrictShipDTO> restrictShipList = new HashMap<String, RestrictShipDTO>();
    private List<Integer> delList = new ArrayList<Integer>();
    private RestrictShipDTO restrictShip;
    // private List<Integer> editList = new ArrayList<Integer>();

    @Override
    public String list() throws Exception {

        if (psId != null) {
            sessionPSID = String.valueOf(psId);
            setProduct(productService.getProductDetail(psId));
            if (productService.getProductRestrictShipList(psId) != null) {
                for (int i = 0; i < productService.getProductRestrictShipList(psId).size(); i++) {
                    restrictShipList.put(productService.getProductRestrictShipList(psId).get(i).getId().toString(), dozer.map(productService.getProductRestrictShipList(psId).get(i), RestrictShipDTO.class));
                }
            }
            stockStatDTO = (productService.getProductStockStat(psId));
            if (SessionUtil.getRow(SessionPdtServ.RestrictShipList.value(), String.valueOf(psId)) != null) {
                setRestrictShipList((Map<String, RestrictShipDTO>) SessionUtil.getRow(SessionPdtServ.RestrictShipList.value(), sessionPSID));
            } else {
                SessionUtil.insertRow(SessionPdtServ.RestrictShipList.value(), sessionPSID, restrictShipList);
            }
            setWarehouseList(productService.getWarehouseList());
            warehouseList.get(0).getStorageList().get(0).getName();
        } else {
            product = new ProductDTO();
            setWarehouseList(productService.getWarehouseList());
            warehouseList.get(0).getStorageList().get(0).getName();
            setRestrictShipList((Map<String, RestrictShipDTO>) SessionUtil.getRow(SessionPdtServ.RestrictShipList.value(), sessionPSID));
        }
        return "showStockStat";
    }

    @Override
    public String input() throws Exception {
        if (psId != null) {
            sessionPSID = String.valueOf(psId);

        }
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
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public String edit() throws Exception {
        if (psId != null) {
            sessionPSID = String.valueOf(psId);

        }
        restrictShipList = ((Map<String, RestrictShipDTO>) SessionUtil.getRow(SessionPdtServ.RestrictShipList.value(), sessionPSID));
        String idStr = Struts2Util.getParameter("shipInfo");
        if (restrictShipList != null) {
            setRestrictShip((RestrictShipDTO) restrictShipList.get(idStr));
        }
        if (psId != null) {
            setProduct(productService.getProductDetail(psId));
        }
        ServletActionContext.getRequest().setAttribute("idStr", idStr);
        return "edit";
    }

    public String saveEdit() throws Exception {
        if (psId != null) {
            sessionPSID = String.valueOf(psId);

        }
        System.out.println("========================sessionPSID:" + sessionPSID);
        if (SessionUtil.getRow(SessionPdtServ.RestrictShipList.value(), sessionPSID) != null) {
            restrictShipList = ((Map<String, RestrictShipDTO>) SessionUtil.getRow(SessionPdtServ.RestrictShipList.value(), sessionPSID));
        }
        String idStr = Struts2Util.getParameter("shipAreaId");
        System.out.println("ID String:" + idStr);
        RestrictShipDTO temp = restrictShipList.get(idStr);
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
        restrictShipList.remove(idStr);
        restrictShipList.put(idStr, temp);
        SessionUtil.updateRow(SessionPdtServ.RestrictShipList.value(), sessionPSID, restrictShipList);
        return null;
    }

    @Override
    public String save() throws Exception {
        if (psId != null) {
            sessionPSID = String.valueOf(psId);

        }
        String areaListStr = Struts2Util.getParameter("areaList");
        String[] areaListArr = areaListStr.split("##");
        Map<String, RestrictShipDTO> list;
        if (SessionUtil.getRow(SessionPdtServ.RestrictShipList.value(), sessionPSID) != null) {
            list = (Map<String, RestrictShipDTO>) SessionUtil.getRow(SessionPdtServ.RestrictShipList.value(), sessionPSID);
        } else {
            list = new HashMap<String, RestrictShipDTO>();
        }
        for (int i = 0; i < areaListArr.length; i++) {
            RestrictShipDTO temp = new RestrictShipDTO();
            JSONObject jsonobj = JSONObject.fromObject(areaListArr[i]);
            temp.setCountry((String) jsonobj.get("countryCode"));
            temp.setCountryName((String) jsonobj.get("countryName"));
            temp.setState((String) jsonobj.get("stateCode"));
            temp.setStateName((String) jsonobj.get("stateName"));
            temp.setZipCode((String) jsonobj.get("zipCode"));
            temp.setProductId(psId);
            String effFrom = Struts2Util.getParameter("effFrom");
            System.out.println("==================effForm:" + effFrom);
            if (effFrom != null && !effFrom.isEmpty()) {
                temp.setEffFrom(DateUtils.formatStr2Date(effFrom, "yyyy-MM-dd"));
            }
            String effTo = Struts2Util.getParameter("effTo");
            System.out.println("==================effTo:" + effTo);
            if (effTo != null && !effTo.isEmpty()) {
                temp.setEffTo(DateUtils.formatStr2Date(effTo, "yyyy-MM-dd"));
            }
            list.put(SessionUtil.generateTempId(), temp);

        }
        SessionUtil.insertRow(SessionPdtServ.RestrictShipList.value(), sessionPSID, list);
        //  System.out.println("========================"+Struts2Util.getParameter("areaList"));
        //throw new UnsupportedOperationException("Not supported yet.");
        return null;
    }

    @Override
    public String delete() throws Exception {
        if (psId != null) {
            sessionPSID = String.valueOf(psId);

        }
        if (SessionUtil.getRow(SessionPdtServ.RestrictShipList.value(), sessionPSID) != null) {
            restrictShipList = ((Map<String, RestrictShipDTO>) SessionUtil.getRow(SessionPdtServ.RestrictShipList.value(), sessionPSID));
        }
        String delIdStrs = ServletActionContext.getRequest().getParameter("delIdStr");
        String[] delIdStr = delIdStrs.split(",");
        for (int i = 0; i < delIdStr.length; i++) {
            System.out.println("==============================delete String:" + delIdStr[i]);
            restrictShipList.remove(delIdStr[i]);
            if (StringUtils.isNumeric(delIdStr[i])) {
                delList.add(Integer.parseInt(delIdStr[i]));
            }


        }
        // restrictShipList.put("delIdStr", delList);
        SessionUtil.insertRow(SessionPdtServ.DelRestrictShipList.value(), sessionPSID, delList);
        SessionUtil.insertRow(SessionPdtServ.RestrictShipList.value(), sessionPSID, restrictShipList);
        return null;
    }

    @Override
    protected void prepareModel() throws Exception {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    public Integer getPsId() {
        return psId;
    }

    public void setPsId(Integer psId) {
        this.psId = psId;
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
     * @return the stockStatDTO
     */
    public ProductStockStatDTO getStockStatDTO() {
        return stockStatDTO;
    }

    /**
     * @param stockStatDTO the stockStatDTO to set
     */
    public void setStockStatDTO(ProductStockStatDTO stockStatDTO) {
        this.stockStatDTO = stockStatDTO;
    }

    /**
     * @return the product
     */
    public ProductDTO getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    /**
     * @return the restrictShipList
     */
    public Map<String, RestrictShipDTO> getRestrictShipList() {
        return restrictShipList;
    }

    /**
     * @param restrictShipList the restrictShipList to set
     */
    public void setRestrictShipList(Map<String, RestrictShipDTO> restrictShipList) {
        this.restrictShipList = restrictShipList;
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
     * @return the restrictShip
     */
    public RestrictShipDTO getRestrictShip() {
        return restrictShip;
    }

    /**
     * @param restrictShip the restrictShip to set
     */
    public void setRestrictShip(RestrictShipDTO restrictShip) {
        this.restrictShip = restrictShip;
    }

    /**
     * @return the warehouseList
     */
    public List<Warehouse> getWarehouseList() {
        return warehouseList;
    }

    /**
     * @param warehouseList the warehouseList to set
     */
    public void setWarehouseList(List<Warehouse> warehouseList) {
        this.warehouseList = warehouseList;
    }

    /**
     * @return the sessionPSId
     */
    public String getSessionPSID() {
        return sessionPSID;
    }

    /**
     * @param sessionPSId the sessionPSId to set
     */
    public void setSessionPSID(String sessionPSID) {
        this.sessionPSID = sessionPSID;
    }
}
