package com.genscript.gsscm.systemsetting.web;

import com.genscript.gsscm.shipment.entity.ShipRateCustomerBasic;
import com.genscript.gsscm.shipment.entity.ShipRateTotalRange;
import com.genscript.gsscm.shipment.entity.ShipRateWeightRange;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.shipment.dto.ShipMethodDTO;
import com.genscript.gsscm.shipment.entity.ShipRate;
import com.genscript.gsscm.shipment.entity.ShipZone;
import com.genscript.gsscm.systemsetting.dto.ShipRateDTO;
import com.genscript.gsscm.systemsetting.dto.ShipZoneDTO;
import com.genscript.gsscm.systemsetting.service.SystemSettingService;
import com.genscript.gsscm.ws.WSException;
import java.math.BigDecimal;

@Results({
    @Result(name = "createForm", location = "systemsetting/shipping_create_form.jsp"),
    @Result(name = "editForm", location = "systemsetting/shipping_create_form.jsp"),
    @Result(name = "listZone", location = "systemsetting/shipping_method_zone_list.jsp"),
    @Result(name = "listRate", location = "systemsetting/shipping_method_rate_list.jsp"),
    @Result(name = "showCreateZoneForm", location = "systemsetting/shipping_method_zone_create_form.jsp"),
    @Result(name = "showEditZoneForm", location = "systemsetting/shipping_method_zone_create_form.jsp"),
    @Result(name = "showCreateRateForm", location = "systemsetting/shipping_method_rate_create_form.jsp"),
    @Result(name = "showEditRateForm", location = "systemsetting/shipping_method_rate_create_form.jsp"),
    @Result(name = "showCharge", location = "systemsetting/shipping_method_charge.jsp"),
    @Result(name = "listTotalRange", location = "systemsetting/shipping_method_total_range_list.jsp"),
    @Result(name = "createTotalRange", location = "systemsetting/shipping_method_total_range_form.jsp"),
    @Result(name = "listTotalWeight", location = "systemsetting/shipping_method_weight_range_list.jsp"),
    @Result(name = "createTotalWeight", location = "systemsetting/shipping_method_weight_range_form.jsp")
})
public class ShippingMethodAction extends BaseAction<ShipMethodDTO> {

    /**
     *
     */
    private static final long serialVersionUID = 3198276892397386175L;
    @Autowired
    private PublicService publicService;
    @Autowired
    private SystemSettingService systemSettingService;
    @Autowired
    private DozerBeanMapper dozer;
    @Autowired
    private ExceptionService exceptionUtil;
    private int activeTabIndex;
    private Map<String, String> method1Map;
    private Map<String, String> method2Map;
    private String opType;
    private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;
    private Map<String, List<PbDropdownListOptions>> dropDownList;
    private Map<String, String> statusMap;
    private String id;
    private String idStr;
    private ShipMethodDTO shipMethodDTO;
    //private Integer pageNo;
    private Integer warehouseId;
    private List<ShipRateDTO> retRateList;
    private List<ShipZoneDTO> retZoneList;
    private String standardZoneValue;
    private String standardRateValue;
    private ShipZoneDTO zoneDetail;
    private ShipRateDTO rateDetail;
    private ShipRateCustomerBasic shipRateCustomerBasic;
    private Map<String, ShipRateTotalRange> shipRateTotalRangeMap;
    private Map<String, ShipRateWeightRange> shipRateWeightRangeMap;
    private ShipRateTotalRange shipRateTotalRange;
    private ShipRateWeightRange shipRateWeightRange;
    
  //获取从其他列表页面点过来的请求--Zhang Yong
	private String operation_method;

    /**新建ShipMethod方法
     * @return
     * @throws Exception
     */
    public String createForm() throws Exception {
        Map<String, Map<String, String>> retMap = getComparativeShippingMethod(0, 0);
        method1Map = retMap.get("cmprMapOne");
        method2Map = retMap.get("cmprMapTwo");
        setOpType("add");
        this.setId(SessionUtil.generateTempId());
        initDropDownList();
        return "createForm";
    }

    /**
     * Function: show charge page
     * @return
     * @throws Exception
     * Date: 2010-10-13
     * Author: Lichun Cui
     */
    public String showCharge() throws Exception {
        ShipMethodDTO methodDTO = (ShipMethodDTO) SessionUtil.getRow(SessionConstant.ShippingMethodSetting.value(), id);
        if (methodDTO != null) {
            shipRateCustomerBasic = (methodDTO.getShipRateCustomerBasic());
           // System.out.println("============================= ShipMethodDTO: " + methodDTO);
           // System.out.println("============================= ShipRateCustomerBasic: " + shipRateCustomerBasic);
        }
        return "showCharge";
    }

    /**
     * Function: list hte total range information
     * @return
     * @throws Exception
     * Date: 2010-10-13
     * Auhor: Lichun Cui
     */
    public String listTotalRange() throws Exception {
        if (id != null) {
            idStr = String.valueOf(id);
        }
        ShipMethodDTO methodDTO = (ShipMethodDTO) SessionUtil.getRow(SessionConstant.ShippingMethodSetting.value(), idStr);
        shipRateTotalRangeMap = (Map<String, ShipRateTotalRange>) SessionUtil.getRow(SessionConstant.ShipRateTotalRangeList.value(), idStr);
        if (methodDTO != null) {
            //SessionUtil.insertRow(SessionConstant.ShipRateTotalRangeList.value(), idStr, shipRateTotalRangeMap);
            shipRateCustomerBasic = methodDTO.getShipRateCustomerBasic();
            if (shipRateCustomerBasic != null) {
                ServletActionContext.getRequest().setAttribute("sepTotForAddr", shipRateCustomerBasic.getSepTotForAddr());
            }
        }
        if (shipRateTotalRangeMap == null) {
            if (methodDTO != null) {
                shipRateTotalRangeMap = (SessionUtil.convertList2Map(methodDTO.getShipRateTotalRangeList(), "id"));
                SessionUtil.insertRow(SessionConstant.ShipRateTotalRangeList.value(), idStr, shipRateTotalRangeMap);
            }
        }
        return "listTotalRange";
    }

    /**
     * Function: save the total range information
     * @return
     * @throws Exception
     * Date: 2010-10-13
     * Auhor: Lichun Cui
     */
    public String saveTotalRange() throws Exception {
        String op_type = Struts2Util.getParameter("op_type");
        String totalFrom = Struts2Util.getParameter("totalFrom");
        String totalTo = Struts2Util.getParameter("totalTo");
        String charge = Struts2Util.getParameter("charge");
        String chargePct = Struts2Util.getParameter("chargePct");
        //String id = Struts2Util.getParameter("id");
        String range_id = Struts2Util.getParameter("range_id");
        if (id != null) {
            idStr = String.valueOf(id);
        }
        shipRateTotalRangeMap = (Map<String, ShipRateTotalRange>) SessionUtil.getRow(SessionConstant.ShipRateTotalRangeList.value(), idStr);
        if (shipRateTotalRangeMap == null) {
            shipRateTotalRangeMap = new HashMap<String, ShipRateTotalRange>();
        }
        if (StringUtils.isNotEmpty(op_type) && op_type.trim().endsWith("add")) {
            shipRateTotalRange = new ShipRateTotalRange();
            if (StringUtils.isNotBlank(totalFrom) && StringUtils.isNotEmpty(totalFrom)) {
                shipRateTotalRange.setTotalFrom(Double.parseDouble(totalFrom));
            }
            if (StringUtils.isNotBlank(totalTo) && StringUtils.isNotEmpty(totalTo)) {
                shipRateTotalRange.setTotalTo(Double.parseDouble(totalTo));
            }
            if (StringUtils.isNotBlank(charge) && StringUtils.isNotEmpty(charge)) {
                shipRateTotalRange.setCharge(Double.parseDouble(totalTo));
            }
            if (StringUtils.isNotBlank(chargePct) && StringUtils.isNotEmpty(chargePct)) {
                shipRateTotalRange.setChargePct(Double.parseDouble(chargePct));
            }
            shipRateTotalRangeMap.put(SessionUtil.generateTempId(), shipRateTotalRange);
        }
        if (StringUtils.isNotBlank(range_id) && StringUtils.isNotBlank(op_type) && op_type.trim().endsWith("edit")) {
            if (shipRateTotalRangeMap.containsKey(range_id)) {
                shipRateTotalRange = shipRateTotalRangeMap.get(range_id);
                if (StringUtils.isNotBlank(totalFrom) && StringUtils.isNotEmpty(totalFrom)) {
                    shipRateTotalRange.setTotalFrom(Double.parseDouble(totalFrom));
                }
                if (StringUtils.isNotBlank(totalTo) && StringUtils.isNotEmpty(totalTo)) {
                    shipRateTotalRange.setTotalTo(Double.parseDouble(totalTo));
                }
                if (StringUtils.isNotBlank(charge) && StringUtils.isNotEmpty(charge)) {
                    shipRateTotalRange.setCharge(Double.parseDouble(charge));
                }
                if (StringUtils.isNotBlank(chargePct) && StringUtils.isNotEmpty(chargePct)) {
                    shipRateTotalRange.setChargePct(Double.parseDouble(chargePct));
                }
            }
        }
        SessionUtil.insertRow(SessionConstant.ShipRateTotalRangeList.value(), idStr, shipRateTotalRangeMap);
        return null;
    }

    /**
     * Function: show  the total range form page
     * @return
     * @throws Exception
     * Date: 2010-10-13
     * Auhor: Lichun Cui
     */
    public String createTotalRange() throws Exception {
        if (id != null) {
            idStr = String.valueOf(id);
        }
        String range_id = Struts2Util.getParameter("range_id");
        shipRateTotalRangeMap = (Map<String, ShipRateTotalRange>) SessionUtil.getRow(SessionConstant.ShipRateTotalRangeList.value(), idStr);
        if (StringUtils.isNotBlank(range_id) && shipRateTotalRangeMap != null && shipRateTotalRangeMap.containsKey(range_id)) {
            shipRateTotalRange = shipRateTotalRangeMap.get(range_id);
            ServletActionContext.getRequest().setAttribute("op_type", "edit");
            ServletActionContext.getRequest().setAttribute("range_id", range_id);
        }
        return "createTotalRange";
    }

    /**
     * Function: list the weight range information
     * @return
     * @throws Exception
     * Date: 2010-10-13
     * Auhor: Lichun Cui
     */
    public String listWeightRange() throws Exception {
        if (id != null) {
            idStr = String.valueOf(id);
        }
        ShipMethodDTO methodDTO = (ShipMethodDTO) SessionUtil.getRow(SessionConstant.ShippingMethodSetting.value(), id);
        shipRateWeightRangeMap = (Map<String, ShipRateWeightRange>) SessionUtil.getRow(SessionConstant.ShipRateWeightRangeList.value(), idStr);
        if (shipRateWeightRangeMap == null) {
            if (methodDTO != null) {
                shipRateWeightRangeMap = (SessionUtil.convertList2Map(methodDTO.getShipRateWeightRangeList(), "id"));
                SessionUtil.insertRow(SessionConstant.ShipRateWeightRangeList.value(), idStr, shipRateWeightRangeMap);
            }
        }
        return "listTotalWeight";
    }

    /**
     * Function: show the weight range form
     * @return
     * @throws Exception
     * Date: 2010-10-13
     * Auhor: Lichun Cui
     */
    public String createWeightRange() throws Exception {
        String weight_id = Struts2Util.getParameter("weight_id");
        shipRateWeightRangeMap = (Map<String, ShipRateWeightRange>) SessionUtil.getRow(SessionConstant.ShipRateWeightRangeList.value(), idStr);
        if (StringUtils.isNotBlank(weight_id) && shipRateWeightRangeMap != null && shipRateWeightRangeMap.containsKey(weight_id)) {
            shipRateWeightRange = shipRateWeightRangeMap.get(weight_id);
            ServletActionContext.getRequest().setAttribute("op_type", "edit");
            ServletActionContext.getRequest().setAttribute("weight_id", weight_id);
        }
        return "createTotalWeight";
    }

    /**
     * Function: del the total range or weight range data from session
     * @return
     * @throws Exception
     * Date: 2010-10-13
     * Auhor: Lichun Cui
     */
    public String delInSession4Charge() throws Exception {
        if (id != null) {
            idStr = String.valueOf(id);
        }
        String method_ids = Struts2Util.getParameter("to_del");
        String type = Struts2Util.getParameter("type");
        if (StringUtils.isNotBlank(method_ids) && StringUtils.isNotBlank(type)) {
            method_ids = method_ids.substring(0, method_ids.length() - 1);
            String[] ids = method_ids.split("-");
            if (type.trim().equals("RateTotalRange")) {
                List<Integer> delIds = new ArrayList<Integer>();
                shipRateTotalRangeMap = (Map<String, ShipRateTotalRange>) SessionUtil.getRow(SessionConstant.ShipRateTotalRangeList.value(), idStr);
                if (shipRateTotalRangeMap != null) {
                    for (int i = 0; i < ids.length; i++) {
                        if (shipRateTotalRangeMap.containsKey(ids[i])) {
                            shipRateTotalRangeMap.remove(ids[i]);
                            if (StringUtils.isNumeric(ids[i])) {
                                delIds.add(Integer.parseInt(ids[i]));
                            }
                        }
                    }
                }
                SessionUtil.insertRow(SessionConstant.DelShipRateTotalRangeList.value(), idStr, delIds);
                SessionUtil.insertRow(SessionConstant.ShipRateTotalRangeList.value(), idStr, shipRateTotalRangeMap);
            }
            if (type.trim().equals("RateWeightRange")) {
                List<Integer> delIds = new ArrayList<Integer>();
                shipRateWeightRangeMap = (Map<String, ShipRateWeightRange>) SessionUtil.getRow(SessionConstant.ShipRateWeightRangeList.value(), idStr);
                if (shipRateWeightRangeMap != null) {
                    for (int i = 0; i < ids.length; i++) {
                        if (shipRateWeightRangeMap.containsKey(ids[i])) {
                            shipRateWeightRangeMap.remove(ids[i]);
                            if (StringUtils.isNumeric(ids[i])) {
                                delIds.add(Integer.parseInt(ids[i]));
                            }
                        }
                    }
                }
                SessionUtil.insertRow(SessionConstant.DelShipRateWeightRangeList.value(), idStr, delIds);
                SessionUtil.insertRow(SessionConstant.ShipRateWeightRangeList.value(), idStr, shipRateWeightRangeMap);
            }
        }
        return null;
    }

    /**
     * Function: save the weight range information
     * @return
     * @throws Exception
     * Date: 2010-10-13
     * Auhor: Lichun Cui
     */
    public String saveWeightRange() throws Exception {
        // String id = Struts2Util.getParameter("id");
        String weight_id = Struts2Util.getParameter("weight_id");
        String op_type = Struts2Util.getParameter("op_type");
        String weightFrom = Struts2Util.getParameter("weightFrom");
        String weightTo = Struts2Util.getParameter("weightTo");
        String charge = Struts2Util.getParameter("charge");
        if (id != null) {
            idStr = String.valueOf(id);
        }
        shipRateWeightRangeMap = (Map<String, ShipRateWeightRange>) SessionUtil.getRow(SessionConstant.ShipRateWeightRangeList.value(), idStr);
        if (shipRateWeightRangeMap == null) {
            shipRateWeightRangeMap = new HashMap<String, ShipRateWeightRange>();
        }
        if (StringUtils.isNotBlank(op_type) && op_type.trim().endsWith("add")) {
            shipRateWeightRange = new ShipRateWeightRange();
            if (StringUtils.isNotBlank(weightFrom)) {
                shipRateWeightRange.setWeightFrom(Double.parseDouble(weightFrom));
            }
            if (StringUtils.isNotBlank(weightTo)) {
                shipRateWeightRange.setWeightTo(Double.parseDouble(weightTo));
            }
            if (StringUtils.isNotBlank(charge)) {
                shipRateWeightRange.setCharge(Double.parseDouble(charge));
            }
            shipRateWeightRangeMap.put(SessionUtil.generateTempId(), shipRateWeightRange);
        }
        if (StringUtils.isNotBlank(op_type) && op_type.trim().endsWith("edit") && StringUtils.isNotBlank(weight_id)) {
            if (shipRateWeightRangeMap.containsKey(weight_id)) {
                shipRateWeightRange = shipRateWeightRangeMap.get(weight_id);
                if (StringUtils.isNotBlank(weightFrom)) {
                    shipRateWeightRange.setWeightFrom(Double.parseDouble(weightFrom));
                }
                if (StringUtils.isNotBlank(weightTo)) {
                    shipRateWeightRange.setWeightTo(Double.parseDouble(weightTo));
                }
                if (StringUtils.isNotBlank(charge)) {
                    shipRateWeightRange.setCharge(Double.parseDouble(charge));
                }
            }
        }
        SessionUtil.insertRow(SessionConstant.ShipRateWeightRangeList.value(), idStr, shipRateWeightRangeMap);
        return null;
    }

    @Override
    public String list() throws Exception {
        return null;
    }

    /* ShipMethod编辑主方法
     * @see com.genscript.gsscm.common.web.BaseAction#input()
     */
    @Override
    public String input() throws Exception {
    	if (id != null) {
    		//*********** Add By Zhang Yong Start *****************************//
			//判断将要修改的单号是否正在被操作
			String editUrl = "shipping_method!input.action?id="+id;
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
        setOpType("edit");
        initDropDownList();
        Map<String, Map<String, String>> retMap;
        Integer mId = shipMethodDTO.getMethodId();
        Integer cmprMethod1 = shipMethodDTO.getCmprMethod1();
        if (mId == null) {
            mId = 0;
        }
        if (cmprMethod1 == null) {
            cmprMethod1 = 0;
        }

        retMap = getComparativeShippingMethod(mId, cmprMethod1);

        method1Map = retMap.get("cmprMapOne");
        method2Map = retMap.get("cmprMapTwo");
        //System.out.println("method1Map: " + method1Map);
        return "editForm";
    }

    /**Rate显示页面主方法
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String listRate() throws Exception {
        String warehouseSlt = Struts2Util.getParameter("warehouseId");
        if (StringUtils.isNotEmpty(warehouseSlt)) {
            warehouseId = Integer.parseInt(warehouseSlt);
            System.out.println("warehouseId: " + warehouseId);
            PagerUtil<ShipRate> pagerUtil = new PagerUtil<ShipRate>();
            Page<ShipRate> page = new Page<ShipRate>();
            page = pagerUtil.getRequestPage();
            page.setPageSize(10);
            if (Struts2Util.getParameter("p_no") != null) {
                page.setPageNo(Integer.parseInt(Struts2Util.getParameter("p_no")));
            } else {
                page.setPageNo(1);
            }
            shipMethodDTO = (ShipMethodDTO) SessionUtil.getRow(
                    SessionConstant.ShippingMethodSetting.value(), id);
            if (shipMethodDTO != null) {
                standardRateValue = shipMethodDTO.getStandardRateValue();
            }
            if (warehouseId != null) {
                List<ShipRateDTO> rateList = new ArrayList<ShipRateDTO>();
                System.out.println("ID: " + id);


                Map<String, ShipRateDTO> rateMap = (Map<String, ShipRateDTO>) SessionUtil.getRow(SessionConstant.RateListSetting.value(), id);
                if (rateMap != null && !rateMap.isEmpty()) {
                    for (Iterator<String> iter = rateMap.keySet().iterator(); iter.hasNext();) {
                        String key = iter.next();
                        ShipRateDTO val = (ShipRateDTO) rateMap.get(key);
                        if (val != null
                                && val.getWarehouseId().equals(warehouseId)) {
                            rateList.add(val);
                        }
                    }
                }

                page.setTotalCount(Long.parseLong(Integer.valueOf(
                        rateList.size()).toString()));
                int startIndex = page.getPageSize() * (page.getPageNo() - 1);
                int toIndex = getToIndex(page.getPageSize(), rateList.size(),
                        page.getPageNo());
                retRateList = rateList.subList(startIndex, toIndex);

            }
            ServletActionContext.getRequest().setAttribute("pagerInfo", page);
        }
        return "listRate";
    }

    /**ShipZone列表显示主方法
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String listZone() throws Exception {
        PagerUtil<ShipZone> pagerUtil = new PagerUtil<ShipZone>();
        Page<ShipZone> page = new Page<ShipZone>();
        page = pagerUtil.getRequestPage();
        page.setPageSize(10);
        if (Struts2Util.getParameter("p_no") != null) {
            page.setPageNo(Integer.parseInt(Struts2Util.getParameter("p_no")));
        } else {
            page.setPageNo(1);
        }
        if (page.getTotalCount() < 0) {
            page.setTotalCount(0L);
        }
        List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
        speclListName.add(SpecDropDownListName.WAREHOUSE);
        specDropDownList = publicService.getSpecDropDownMap(speclListName);
        String warehouseSlt = Struts2Util.getParameter("warehouseSlt");
        if (StringUtils.isNotEmpty(warehouseSlt)) {
            warehouseId = Integer.parseInt(warehouseSlt);
            System.out.println("warehouseId: " + warehouseId);

            shipMethodDTO = (ShipMethodDTO) SessionUtil.getRow(
                    SessionConstant.ShippingMethodSetting.value(), id);
            if (shipMethodDTO != null) {
                standardZoneValue = shipMethodDTO.getStandardZoneValue();
            }
            if (warehouseId != null) {
                List<ShipZoneDTO> zoneList = new ArrayList<ShipZoneDTO>();
                System.out.println("ID: " + id);


                Map<String, ShipZoneDTO> zoneMap = (Map<String, ShipZoneDTO>) SessionUtil.getRow(SessionConstant.ZoneListSetting.value(), id);
                if (zoneMap != null && !zoneMap.isEmpty()) {
                    for (Iterator<String> iter = zoneMap.keySet().iterator(); iter.hasNext();) {
                        String key = iter.next();
                        ShipZoneDTO val = (ShipZoneDTO) zoneMap.get(key);
                        if (val != null
                                && val.getWarehouseId().equals(warehouseId)) {
                            zoneList.add(val);
                        }
                    }

                    page.setTotalCount(Long.parseLong(Integer.valueOf(
                            zoneList.size()).toString()));
                    int startIndex = page.getPageSize()
                            * (page.getPageNo() - 1);
                    int toIndex = getToIndex(page.getPageSize(),
                            zoneList.size(), page.getPageNo());
                    retZoneList = zoneList.subList(startIndex, toIndex);

//					ServletActionContext.getRequest().setAttribute("pagerInfo", page);
                }
            }
        }
        ServletActionContext.getRequest().setAttribute("pagerInfo", page);
        return "listZone";
    }

    /**新建ShipZone方法
     * @return
     * @throws Exception
     */
    public String showCreateZoneForm() throws Exception {
        setOpType("add");
        initShipZoneDropDownList();
        idStr = SessionUtil.generateTempId();
        return "showCreateZoneForm";
    }

    /**编辑ShipZone方法
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String showEditZoneForm() throws Exception {
        setOpType("edit");
        initShipZoneDropDownList();
        String zoneId = Struts2Util.getParameter("zoneId");
        System.out.println("ID: " + id);
        Map<String, ShipZoneDTO> zoneMap = (Map<String, ShipZoneDTO>) SessionUtil.getRow(SessionConstant.ZoneListSetting.value(), id);
        if (zoneMap != null && !zoneMap.isEmpty()) {
            if (zoneMap.containsKey(zoneId)) {
                zoneDetail = zoneMap.get(zoneId);
            }
        }
        return "showEditZoneForm";
    }

    /**新建ShipRate方法
     * @return
     * @throws Exception
     */
    public String showCreateRateForm() throws Exception {
        setOpType("add");
        idStr = SessionUtil.generateTempId();
        return "showCreateRateForm";
    }

    /**编辑ShipRate方法
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String showEditRateForm() throws Exception {
        setOpType("edit");
        String rateId = Struts2Util.getParameter("rateId");
        System.out.println("ID: " + id);
        Map<String, ShipRateDTO> rateMap = (Map<String, ShipRateDTO>) SessionUtil.getRow(SessionConstant.RateListSetting.value(), id);
        if (rateMap != null && !rateMap.isEmpty()) {
            if (rateMap.containsKey(rateId)) {
                rateDetail = rateMap.get(rateId);
            }
        }
        return "showEditRateForm";
    }

    /**删除Session中记录方法
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String delInSession() throws Exception {
        String type = ServletActionContext.getRequest().getParameter("type");
        String toDel = ServletActionContext.getRequest().getParameter("toDel");
        Map<String, ShipZoneDTO> zoneMap = null;
        Map<String, ShipZoneDTO> updateZoneMap = null;
        Map<String, ShipRateDTO> rateMap = null;
        Map<String, ShipRateDTO> updateRateMap = null;
        if (type.equals("Zone")) {
            zoneMap = (Map<String, ShipZoneDTO>) SessionUtil.getRow(SessionConstant.ZoneListSetting.value(), id);
            updateZoneMap = (Map<String, ShipZoneDTO>) SessionUtil.getRow(SessionConstant.UpdateZoneListSetting.value(), id);
            if (updateZoneMap == null) {
                updateZoneMap = new HashMap<String, ShipZoneDTO>();
            }
        } else {
            rateMap = (Map<String, ShipRateDTO>) SessionUtil.getRow(SessionConstant.RateListSetting.value(), id);
            updateRateMap = (Map<String, ShipRateDTO>) SessionUtil.getRow(SessionConstant.RateListSetting.value(), id);
            if (updateRateMap == null) {
                updateRateMap = new HashMap<String, ShipRateDTO>();
            }
        }
        if (StringUtils.isNotBlank(toDel)) {
            String[] strs = toDel.split("-");
            List<String> delIdList = Arrays.asList(strs);
            for (String str : delIdList) {
                if (!StringUtils.isNumeric(str)) {
                    if (type.equals("Zone")) {
                        zoneMap.remove(str);
                    } else {
                        rateMap.remove(str);
                    }
                } else {
                    if (type.equals("Zone")) {
                        zoneMap.put(str, null);
                        updateZoneMap.put(str, null);
                    } else {
                        rateMap.put(str, null);
                        updateRateMap.put(str, null);
                    }
                }
            }
        }
        if (type.equals("Zone")) {
            SessionUtil.insertRow(SessionConstant.ZoneListSetting.value(), id, zoneMap);
            SessionUtil.insertRow(SessionConstant.UpdateZoneListSetting.value(), id, updateZoneMap);
        } else {
            SessionUtil.insertRow(SessionConstant.RateListSetting.value(), id, rateMap);
            SessionUtil.insertRow(SessionConstant.UpdateRateListSetting.value(), id, updateRateMap);
        }
        Struts2Util.renderText("SUCCESS");
        return NONE;
    }

    /**删除ShipMethod方法
     * @return
     * @throws Exception
     */
    public String delMethod() throws Exception {
        String toDel = ServletActionContext.getRequest().getParameter("toDel");
        Integer userId = SessionUtil.getUserId();
        if (StringUtils.isNotBlank(toDel)) {
            List<Integer> shipMethodDelIdList = new ArrayList<Integer>();
            String[] strs = toDel.split("-");
            List<String> delIdList = Arrays.asList(strs);
            for (String str : delIdList) {
                shipMethodDelIdList.add(Integer.parseInt(str));
            }
            try {
                List<String> cannotDelIdList = systemSettingService.delShipMethodList(shipMethodDelIdList);
                StringBuilder sb = new StringBuilder();
                sb.append("SUCCESS");
                if (cannotDelIdList != null && !cannotDelIdList.isEmpty()) {
                    sb.append("##");
                    for (String str : cannotDelIdList) {
                        sb.append(str).append(", ");
                    }
                }
                sb.substring(0, sb.length() - 2);
                Struts2Util.renderText(sb.toString());
            } catch (Exception e) {
                WSException exDTO = exceptionUtil.getExceptionDetails(e);
                exceptionUtil.logException(exDTO, this.getClass(), e, new Exception().getStackTrace()[0].getMethodName(), "INTF0101", userId);
                Struts2Util.renderText(exDTO.getMessageDetail());
            }
        }
        return NONE;
    }

    /* ShipMethod标签总体保存
     * @see com.genscript.gsscm.common.web.BaseAction#save()
     */
    @SuppressWarnings("unchecked")
    @Override
    public String save() throws Exception {
    	//*********** Add By Zhang Yong Start *****************************//
		//校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
		if (id != null && !("").equals(id)) {
			String editUrl = "shipping_method!input.action?id="+id;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
				Struts2Util.renderText("Save shipping_method fail,the shipping_method is editing by "+operation_method);
		    	return NONE;
			}
		}
		//*********** Add By Zhang Yong End *****************************//	
    	
    	
    	
        Integer userId = SessionUtil.getUserId();
       // shipMethodDTO = (ShipMethodDTO) SessionUtil.getRow(SessionConstant.ShippingMethodSetting.value(), id);
        System.out.println("==================shipMethodDTO: " + shipMethodDTO);
        if (shipMethodDTO.getPrintFlag() != null && shipMethodDTO.getPrintFlag().equals("on")) {
            shipMethodDTO.setPrintFlag("Y");
        } else {
            shipMethodDTO.setPrintFlag("N");
        }
        if (shipMethodDTO.getExternalFlag() != null && shipMethodDTO.getExternalFlag().equals("on")) {
            shipMethodDTO.setExternalFlag("Y");
        } else {
            shipMethodDTO.setExternalFlag("N");
        }
        if (id.indexOf(",") >= 0) {
            id = StringUtils.substringAfterLast(id, ", ");
        }
        ShipMethodDTO MethodDTO = (ShipMethodDTO) SessionUtil.getRow(SessionConstant.ShippingMethodSetting.value(), id);
        if (MethodDTO != null) {
            shipMethodDTO.setShipRateCustomerBasic(MethodDTO.getShipRateCustomerBasic());
            shipMethodDTO.setShipRateTotalRangeList(MethodDTO.getShipRateTotalRangeList());
            shipMethodDTO.setShipRateWeightRangeList(MethodDTO.getShipRateWeightRangeList());
            shipMethodDTO.setTotalRangeDelIdList(MethodDTO.getTotalRangeDelIdList());
            shipMethodDTO.setWeightRangeDelIdList(MethodDTO.getWeightRangeDelIdList());
        }

        Map<String, ShipZoneDTO> zoneMap = (Map<String, ShipZoneDTO>) SessionUtil.getRow(SessionConstant.UpdateZoneListSetting.value(), id);
        List<Integer> shipZoneDelIdList = new ArrayList<Integer>();
        List<ShipZone> shipZoneList = new ArrayList<ShipZone>();
        if (zoneMap != null && !zoneMap.isEmpty()) {
            for (Iterator<String> iter = zoneMap.keySet().iterator(); iter.hasNext();) {
                String key = iter.next();
                ShipZoneDTO val = (ShipZoneDTO) zoneMap.get(key);
                if (val == null && StringUtils.isNumeric(key)) {
                    shipZoneDelIdList.add(Integer.parseInt(key));
                } else {
                    shipZoneList.add(dozer.map(val, ShipZone.class));
                }
            }
        }
        Map<String, ShipRateDTO> rateMap = (Map<String, ShipRateDTO>) SessionUtil.getRow(SessionConstant.UpdateRateListSetting.value(), id);
        List<Integer> shipRateDelIdList = new ArrayList<Integer>();
        List<ShipRate> shipRateList = new ArrayList<ShipRate>();
        if (rateMap != null && !rateMap.isEmpty()) {
            for (Iterator<String> iter = rateMap.keySet().iterator(); iter.hasNext();) {
                String key = iter.next();
                ShipRateDTO val = (ShipRateDTO) rateMap.get(key);
                if (val == null && StringUtils.isNumeric(key)) {
                    shipRateDelIdList.add(Integer.parseInt(key));
                } else {
                    shipRateList.add(dozer.map(val, ShipRate.class));
                }
            }
        }
        Object map = SessionUtil.getRow(SessionConstant.ShipRateTotalRangeList.value(), id);
        List<ShipRateTotalRange> shipRateTotalRangeList = SessionUtil.convertMap2List((Map<String, ShipRateTotalRange>) map);
        Object obj = SessionUtil.getRow(SessionConstant.DelShipRateTotalRangeList.value(), id);
        shipMethodDTO.setShipRateTotalRangeList(shipRateTotalRangeList);
        shipMethodDTO.setTotalRangeDelIdList((List<Integer>) obj);
        map = SessionUtil.getRow(SessionConstant.ShipRateWeightRangeList.value(), id);
        List<ShipRateWeightRange> shipRateWeightRangeList = SessionUtil.convertMap2List((Map<String, ShipRateWeightRange>) map);
        obj = SessionUtil.getRow(SessionConstant.DelShipRateWeightRangeList.value(), id);
        shipMethodDTO.setShipRateWeightRangeList(shipRateWeightRangeList);
        SessionUtil.deleteRow(SessionConstant.ShipRateWeightRangeList.value(), id);
        SessionUtil.deleteRow(SessionConstant.ShipRateTotalRangeList.value(), id);
        shipMethodDTO.setWeightRangeDelIdList((List<Integer>) obj);
        shipRateCustomerBasic = new ShipRateCustomerBasic();
        String perBoxChrg = Struts2Util.getParameter("perBoxChrg");
        String perBoxAppTp = Struts2Util.getParameter("perBoxAppTp");
        String perItemChrg = Struts2Util.getParameter("perItemChrg");
        String perItemAppTp = Struts2Util.getParameter("perItemAppTp");
        String perPndChrg = Struts2Util.getParameter("perPndChrg");
        String wtChargeType = Struts2Util.getParameter("wtChargeType");
        String wtChrgAppTp = Struts2Util.getParameter("wtChrgAppTp");
        String actualChrgPct = Struts2Util.getParameter("actualChrgPct");
        String actualChrgTp = Struts2Util.getParameter("actualChrgTp");
        String sepTotForAddr = Struts2Util.getParameter("sepTotForAddr");
        if (StringUtils.isNotBlank(perBoxChrg)) {
            shipRateCustomerBasic.setPerBoxChrg(BigDecimal.valueOf(Double.parseDouble(perBoxChrg)));
        }
        if (StringUtils.isNotBlank(perBoxAppTp)) {
            shipRateCustomerBasic.setPerBoxAppTp(perBoxAppTp);
        }
        if (StringUtils.isNotBlank(perItemChrg)) {
            shipRateCustomerBasic.setPerItemChrg(BigDecimal.valueOf(Double.parseDouble(perItemChrg)));
        }
        if (StringUtils.isNotBlank(perItemAppTp)) {
            shipRateCustomerBasic.setPerItemAppTp(perItemAppTp);
        }
        if (StringUtils.isNotBlank(wtChargeType) && StringUtils.isNotBlank(perPndChrg) && wtChargeType.trim().equals("per")) {
            shipRateCustomerBasic.setPerPndChrg(BigDecimal.valueOf(Double.parseDouble(perPndChrg)));
        }
        if (StringUtils.isNotBlank(wtChargeType)) {
            shipRateCustomerBasic.setWtChargeType(wtChargeType);
        }
        if (StringUtils.isNotBlank(wtChargeType) && StringUtils.isNotBlank(wtChrgAppTp) && wtChargeType.trim().equals("range")) {
            shipRateCustomerBasic.setWtChrgAppTp(wtChrgAppTp);
        }
        if (StringUtils.isNotBlank(actualChrgPct)) {
            shipRateCustomerBasic.setActualChrgPct(BigDecimal.valueOf(Double.parseDouble(actualChrgPct)));
        }
        if (StringUtils.isNotBlank(actualChrgTp)) {
            shipRateCustomerBasic.setActualChrgTp(actualChrgTp);
        }
        if (StringUtils.isNotBlank(sepTotForAddr)) {
            shipRateCustomerBasic.setSepTotForAddr(sepTotForAddr);
        }
        //String actualChrgTp=Struts2Util.getParameter("");
        //11&=SUBSEQUENT&=11&=SUBSEQUENT&=&=range&wtChrgAppTp=SUBSEQUENT&=11&=SUBSEQUENT&shipRateCustomerBasic.sepTotForAddr=N
        shipMethodDTO.setShipRateCustomerBasic(shipRateCustomerBasic);
        try {
            Integer shipMethodId = systemSettingService.saveShipMethod(shipMethodDTO, shipZoneList, shipRateList, shipZoneDelIdList, shipRateDelIdList, userId);

            StringBuilder sb = new StringBuilder();
            sb.append("SUCCESS#").append(shipMethodId);
            Struts2Util.renderText(sb.toString());
        } catch (Exception e) {
            WSException exDTO = exceptionUtil.getExceptionDetails(e);
            exceptionUtil.logException(exDTO, this.getClass(), e, new Exception().getStackTrace()[0].getMethodName(), "INTF0101", userId);
            Struts2Util.renderText(exDTO.getMessageDetail());
        }

        return NONE;
    }

    /**保存ShipZone到Session中
     * @return
     * @throws Exception
     */
    public String saveSessionZone() throws Exception {
        if (opType.equals("add") || !StringUtils.isNumeric(Struts2Util.getParameter("zoneId"))) {
            zoneDetail.setWarehouseId(warehouseId);
            System.out.println("ID: " + id);
            System.out.println("zoneId" + Struts2Util.getParameter("zoneId"));
            if (StringUtils.isNotBlank(Struts2Util.getParameter("id")) && StringUtils.isNumeric(Struts2Util.getParameter("id"))) {
                zoneDetail.setShipMethodId(Integer.parseInt(Struts2Util.getParameter("id")));
            }
            zoneDetail.setIdStr(Struts2Util.getParameter("zoneId"));
            SessionUtil.updateOneRow(SessionConstant.ZoneListSetting.value(), id, Struts2Util.getParameter("zoneId"), zoneDetail);
            SessionUtil.updateOneRow(SessionConstant.UpdateZoneListSetting.value(), id, Struts2Util.getParameter("zoneId"), zoneDetail);
        } else {
            System.out.println("warehouseId: " + warehouseId);
            int zoneId = Integer.parseInt(Struts2Util.getParameter("zoneId"));
            zoneDetail.setWarehouseId(warehouseId);
            if (StringUtils.isNotBlank(Struts2Util.getParameter("id")) && StringUtils.isNumeric(Struts2Util.getParameter("id"))) {
                zoneDetail.setShipMethodId(Integer.parseInt(id));
            }
            zoneDetail.setId(zoneId);
            zoneDetail.setIdStr(Struts2Util.getParameter("zoneId"));
            SessionUtil.updateOneRow(SessionConstant.ZoneListSetting.value(), id, zoneId + "", zoneDetail);
            SessionUtil.updateOneRow(SessionConstant.UpdateZoneListSetting.value(), id, zoneId + "", zoneDetail);
        }
        System.out.println("zoneDetail: " + zoneDetail);
        Struts2Util.renderText("SUCCESS");
        return NONE;
    }

    /**保存ShipRate到Session中
     * @return
     * @throws Exception
     */
    public String saveSessionRate() throws Exception {
        if (opType.equals("add") || !StringUtils.isNumeric(Struts2Util.getParameter("rateId"))) {
            System.out.println("warehouseId: " + warehouseId);
            rateDetail.setWarehouseId(warehouseId);
            System.out.println("ID: " + id);
            System.out.println("rateId" + Struts2Util.getParameter("rateId"));
            if (StringUtils.isNotBlank(Struts2Util.getParameter("id")) && StringUtils.isNumeric(Struts2Util.getParameter("id"))) {
                rateDetail.setShipMethodId(Integer.parseInt(Struts2Util.getParameter("id")));
            }
            rateDetail.setIdStr(Struts2Util.getParameter("rateId"));
            SessionUtil.updateOneRow(SessionConstant.RateListSetting.value(), id, Struts2Util.getParameter("rateId"), rateDetail);
            SessionUtil.updateOneRow(SessionConstant.UpdateRateListSetting.value(), id, Struts2Util.getParameter("rateId"), rateDetail);
        } else {
            System.out.println("warehouseId: " + warehouseId);
            int rateId = Integer.parseInt(Struts2Util.getParameter("rateId"));
            rateDetail.setWarehouseId(warehouseId);
            if (StringUtils.isNotBlank(Struts2Util.getParameter("id")) && StringUtils.isNumeric(Struts2Util.getParameter("id"))) {
                rateDetail.setShipMethodId(Integer.parseInt(id));
            }
            rateDetail.setId(rateId);
            rateDetail.setIdStr(Struts2Util.getParameter("rateId"));
            SessionUtil.updateOneRow(SessionConstant.RateListSetting.value(), id, rateId + "", rateDetail);
            SessionUtil.updateOneRow(SessionConstant.UpdateRateListSetting.value(), id, rateId + "", rateDetail);
        }
        System.out.println("rateDetail: " + rateDetail);
        Struts2Util.renderText("SUCCESS");
        return NONE;
    }

    /**取得ShipRate下拉框方法并Copy ShipRateList到Session中
     * @return
     * @throws Exception
     */
    public String getShipRateDropDown() throws Exception {
        String zoneId = Struts2Util.getParameter("zoneId");
        String zoneName = Struts2Util.getParameter("zoneName");
        SessionUtil.deleteRow(SessionConstant.RateListSetting.value(), id);
        SessionUtil.deleteRow(SessionConstant.UpdateRateListSetting.value(), id);
        SessionUtil.deleteRow(SessionConstant.standardRateValue.value(), id);
        if (StringUtils.isBlank(zoneId)) {
            SessionUtil.deleteRow(SessionConstant.ZoneListSetting.value(), id);
            SessionUtil.deleteRow(SessionConstant.UpdateZoneListSetting.value(), id);
            SessionUtil.deleteRow(SessionConstant.standardZoneValue.value(), id);
            Struts2Util.renderText("CLEAR");
            return NONE;
        }
        List<DropDownListDTO> dropDownListDTOList = systemSettingService.getShipRateByZone(Integer.parseInt(zoneId));
        List<DropDownDTO> dropDownDTOs = dropDownListDTOList.get(0).getDropDownDTOs();
        Map<String, String> rateOptMap = new HashMap<String, String>();
        if (dropDownDTOs != null && dropDownDTOs.size() > 0) {
            for (DropDownDTO dropDownDTO : dropDownDTOs) {
                rateOptMap.put(dropDownDTO.getId(), dropDownDTO.getName());
            }
        }
        Map<String, ShipZoneDTO> zoneMap = new LinkedHashMap<String, ShipZoneDTO>();
//		List<ShipZoneDTO> zoneList = new ArrayList<ShipZoneDTO>();
        List<ShipZone> shipZoneList = dropDownListDTOList.get(0).getShipZoneList();
        if (shipZoneList != null && shipZoneList.size() > 0) {
            for (ShipZone shipZone : shipZoneList) {
                ShipZoneDTO shipZoneDTO = dozer.map(shipZone, ShipZoneDTO.class);
                shipZoneDTO.setIdStr(SessionUtil.generateTempId());
                shipZoneDTO.setId(null);
//				zoneList.add(shipZoneDTO);
                zoneMap.put(shipZoneDTO.getIdStr(), shipZoneDTO);
            }
        }
        SessionUtil.insertRow(SessionConstant.ZoneListSetting.value(), id, zoneMap);
        SessionUtil.insertRow(SessionConstant.UpdateZoneListSetting.value(), id, zoneMap);
        SessionUtil.insertRow(SessionConstant.standardZoneValue.value(), id, zoneName);
        if (rateOptMap != null && !rateOptMap.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Iterator<String> iter = rateOptMap.keySet().iterator(); iter.hasNext();) {
                String key = iter.next();
                String val = rateOptMap.get(key);
                sb.append(key).append("|").append(val).append("#");
            }
            Struts2Util.renderText(sb.toString());
        }
        return NONE;
    }

    public String getShipRateList() throws Exception {
        String zoneId = Struts2Util.getParameter("zoneId");
        String rateId = Struts2Util.getParameter("rateId");
        String rateName = Struts2Util.getParameter("rateName");
        if (StringUtils.isBlank(rateId)) {
            SessionUtil.deleteRow(SessionConstant.RateListSetting.value(), id);
            SessionUtil.deleteRow(SessionConstant.UpdateRateListSetting.value(), id);
            SessionUtil.deleteRow(SessionConstant.standardRateValue.value(), id);
            Struts2Util.renderText("CLEAR");
        } else {
            List<DropDownListDTO> dropDownListDTOList = systemSettingService.getShipRateByZone(Integer.parseInt(zoneId));
            List<DropDownDTO> dropDownDTOs = dropDownListDTOList.get(0).getDropDownDTOs();
            if (dropDownDTOs != null && dropDownDTOs.size() > 0) {
                List<ShipRate> shipRateList = new ArrayList<ShipRate>();
                for (DropDownDTO dropDownDTO : dropDownDTOs) {
                    if (dropDownDTO.getId().equals(rateId)) {
                        shipRateList = dropDownDTO.getShipRateList();
                        break;
                    }
                }
                Map<String, ShipRateDTO> rateMap = new LinkedHashMap<String, ShipRateDTO>();
//				List<ShipRateDTO> rateList = new ArrayList<ShipRateDTO>();
                if (shipRateList != null && !shipRateList.isEmpty()) {
                    for (ShipRate shipRate : shipRateList) {
                        ShipRateDTO shipRateDTO = dozer.map(shipRate, ShipRateDTO.class);
                        shipRateDTO.setIdStr(SessionUtil.generateTempId());
                        shipRateDTO.setId(null);
//						rateList.add(shipRateDTO);
                        rateMap.put(shipRateDTO.getIdStr(), shipRateDTO);
                    }
                }
                SessionUtil.insertRow(SessionConstant.RateListSetting.value(), id, rateMap);
                SessionUtil.insertRow(SessionConstant.UpdateRateListSetting.value(), id, rateMap);
                SessionUtil.insertRow(SessionConstant.standardRateValue.value(), id, rateName);
            }
            Struts2Util.renderText("SUCCESS");
        }
        return NONE;
    }

    public String getTrackUrl() throws Exception {
        String carrier = Struts2Util.getParameter("carrier");
        List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
        speclListName.add(SpecDropDownListName.SHIP_CARRIERS);
        specDropDownList = publicService.getSpecDropDownMap(speclListName);

        List<DropDownDTO> dropDownListDTO = specDropDownList.get(SpecDropDownListName.SHIP_CARRIERS).getDropDownDTOs();
        for (DropDownDTO dropDownDTO : dropDownListDTO) {
            if (dropDownDTO.getId().equals(carrier)) {
                Struts2Util.renderText(dropDownDTO.getValue());
            }
        }

        return NONE;
    }

    /**
     * @param page
     * @param zoneList
     * @return
     */
    private int getToIndex(int pageSize, int totalCount, int pageNo) {
        int count = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            count++;
        }
        int toIndex = 0;
        if (pageNo < count) {
            toIndex = pageSize * pageNo;
        } else {
            toIndex = totalCount;
        }
        return toIndex;
    }

    @Override
    public ShipMethodDTO getModel() {
        return shipMethodDTO;
    }

    @Override
    public String delete() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (StringUtils.isNotEmpty(id)) {
            if (!StringUtils.isNumeric(id)) {
                shipMethodDTO = new ShipMethodDTO();
                System.out.println("shipMethodDTO: " + shipMethodDTO);
            } else {
                shipMethodDTO = systemSettingService.getShipMethodDetail(Integer.parseInt(id));
                List<ShipZone> zlist = shipMethodDTO.getShipZoneList();
                List<ShipRate> rlist = shipMethodDTO.getShipRateList();
                //System.out.println("Zone List: " + list);
                Map<String, ShipZoneDTO> shipZoneMap = new LinkedHashMap<String, ShipZoneDTO>();
                Map<String, ShipRateDTO> shipRateMap = new LinkedHashMap<String, ShipRateDTO>();
                if (zlist != null && zlist.size() > 0) {
                    for (ShipZone shipZone : zlist) {
                        ShipZoneDTO shipZoneDTO = dozer.map(shipZone, ShipZoneDTO.class);
                        shipZoneDTO.setIdStr(shipZone.getId() + "");
                        shipZoneMap.put(shipZone.getId() + "", shipZoneDTO);
                    }
                    SessionUtil.insertRow(SessionConstant.ZoneListSetting.value(), id, shipZoneMap);
                }
                if (rlist != null && rlist.size() > 0) {
                    for (ShipRate shipRate : rlist) {
                        ShipRateDTO shipRateDTO = dozer.map(shipRate, ShipRateDTO.class);
                        shipRateDTO.setIdStr(shipRate.getId() + "");
                        shipRateMap.put(shipRate.getId() + "", shipRateDTO);
                    }
                    SessionUtil.insertRow(SessionConstant.RateListSetting.value(), id, shipRateMap);
                }
                shipMethodDTO.setShipZoneList(null);
                shipMethodDTO.setShipRateList(null);
                SessionUtil.insertRow(SessionConstant.ShippingMethodSetting.value(), id, shipMethodDTO);
                SessionUtil.insertRow(SessionConstant.standardZoneValue.value(), id, shipMethodDTO.getStandardZoneValue());
                SessionUtil.insertRow(SessionConstant.standardRateValue.value(), id, shipMethodDTO.getStandardRateValue());
            }
        } else {
            shipMethodDTO = new ShipMethodDTO();
        }

    }

    /**
     * 初始化public drop down list
     */
    private void initDropDownList() {
        dropDownList = new HashMap<String, List<PbDropdownListOptions>>();
        dropDownList.put("SHIP_METHOD_INSURANCE_BASE",
                publicService.getDropdownList("SHIP_METHOD_INSURANCE_BASE"));
        List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
        speclListName.add(SpecDropDownListName.SHIP_CARRIERS);
        speclListName.add(SpecDropDownListName.SHIP_ZONE);
        specDropDownList = publicService.getSpecDropDownMap(speclListName);
        if (shipMethodDTO != null && shipMethodDTO.getStandardZone() != null) {
            List<DropDownListDTO> dropDownListDTOList = systemSettingService.getShipRateByZone(shipMethodDTO.getStandardZone());
            for (DropDownListDTO dropDownListDTO : dropDownListDTOList) {
                specDropDownList.put(SpecDropDownListName.RATE_LIST, dropDownListDTO);
            }
        } else {
            List<DropDownDTO> dropDownDTOs = new ArrayList<DropDownDTO>();
            DropDownListDTO dto = new DropDownListDTO();
            dto.setDropDownDTOs(dropDownDTOs);
            specDropDownList.put(SpecDropDownListName.RATE_LIST, dto);
        }
        statusMap = new LinkedHashMap<String, String>();
        statusMap.put("ACTIVE", "ACTIVE");
        statusMap.put("INACTIVE", "INACTIVE");
    }

    private void initShipZoneDropDownList() {
        List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
        speclListName.add(SpecDropDownListName.COUNTRY_NAME);
        specDropDownList = publicService.getSpecDropDownMap(speclListName);
    }

    private Map<String, Map<String, String>> getComparativeShippingMethod(int methodId, int cmprMethodId) {
        List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
        speclListName.add(SpecDropDownListName.COMPR_SHIP_METHOD);
        List<DropDownListDTO> list = publicService.getSpecDropDownList(speclListName);
        Map<String, Map<String, String>> retMap = new HashMap<String, Map<String, String>>();
        if (list != null && !list.isEmpty()) {
            Map<String, String> cmprMapOne = new LinkedHashMap<String, String>();
            Map<String, String> cmprMapTwo = new LinkedHashMap<String, String>();
            List<DropDownDTO> dropDownDTOs = list.get(0).getDropDownDTOs();
            try {
                for (DropDownDTO dto : dropDownDTOs) {
                    String id = dto.getId();
                    if (id != null && Integer.parseInt(id) != methodId) {
                        cmprMapOne.put(dto.getId(), new StringBuilder().append(dto.getValue()).append(" - ").append(dto.getName()).toString());
                    }
                    if (dto.getId() != null && cmprMethodId != 0) {
                        if (!(dto.getId().equals(methodId)) && !(dto.getId().equals(cmprMethodId))) {
                            cmprMapTwo.put(dto.getId(), new StringBuilder().append(dto.getValue()).append(" - ").append(dto.getName()).toString());
                        }
                    }
                }
                if (cmprMapTwo != null && cmprMapTwo.isEmpty()) {
                    cmprMapTwo.putAll(cmprMapOne);

                    for (Iterator<String> iter = cmprMapTwo.keySet().iterator(); iter.hasNext();) {
                        cmprMapTwo.remove(iter.next());
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            retMap.put("cmprMapOne", cmprMapOne);
            retMap.put("cmprMapTwo", cmprMapTwo);
        }
        return retMap;
    }

    public Map<String, String> getMethod1Map() {
        return method1Map;
    }

    public void setMethod1Map(Map<String, String> method1Map) {
        this.method1Map = method1Map;
    }

    public Map<String, String> getMethod2Map() {
        return method2Map;
    }

    public void setMethod2Map(Map<String, String> method2Map) {
        this.method2Map = method2Map;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public int getActiveTabIndex() {
        return activeTabIndex;
    }

    public void setActiveTabIndex(int activeTabIndex) {
        this.activeTabIndex = activeTabIndex;
    }

    public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
        return specDropDownList;
    }

    public void setSpecDropDownList(
            Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
        this.specDropDownList = specDropDownList;
    }

    public Map<String, String> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<String, String> statusMap) {
        this.statusMap = statusMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ShipMethodDTO getShipMethodDTO() {
        return shipMethodDTO;
    }

    public void setShipMethodDTO(ShipMethodDTO shipMethodDTO) {
        this.shipMethodDTO = shipMethodDTO;
    }

    public Map<String, List<PbDropdownListOptions>> getDropDownList() {
        return dropDownList;
    }

    public void setDropDownList(
            Map<String, List<PbDropdownListOptions>> dropDownList) {
        this.dropDownList = dropDownList;
    }

    public List<ShipRateDTO> getRetRateList() {
        return retRateList;
    }

    public void setRetRateList(List<ShipRateDTO> retRateList) {
        this.retRateList = retRateList;
    }

    public List<ShipZoneDTO> getRetZoneList() {
        return retZoneList;
    }

    public void setRetZoneList(List<ShipZoneDTO> retZoneList) {
        this.retZoneList = retZoneList;
    }

    public String getStandardZoneValue() {
        return standardZoneValue;
    }

    public void setStandardZoneValue(String standardZoneValue) {
        this.standardZoneValue = standardZoneValue;
    }

    public String getStandardRateValue() {
        return standardRateValue;
    }

    public void setStandardRateValue(String standardRateValue) {
        this.standardRateValue = standardRateValue;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public ShipZoneDTO getZoneDetail() {
        return zoneDetail;
    }

    public void setZoneDetail(ShipZoneDTO zoneDetail) {
        this.zoneDetail = zoneDetail;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public ShipRateDTO getRateDetail() {
        return rateDetail;
    }

    public void setRateDetail(ShipRateDTO rateDetail) {
        this.rateDetail = rateDetail;
    }

    /**
     * @return the shipRateCustomerBasic
     */
    public ShipRateCustomerBasic getShipRateCustomerBasic() {
        return shipRateCustomerBasic;
    }

    /**
     * @param shipRateCustomerBasic the shipRateCustomerBasic to set
     */
    public void setShipRateCustomerBasic(ShipRateCustomerBasic shipRateCustomerBasic) {
        this.shipRateCustomerBasic = shipRateCustomerBasic;
    }

    /**
     * @return the shipRateTotalRange
     */
    public ShipRateTotalRange getShipRateTotalRange() {
        return shipRateTotalRange;
    }

    /**
     * @param shipRateTotalRange the shipRateTotalRange to set
     */
    public void setShipRateTotalRange(ShipRateTotalRange shipRateTotalRange) {
        this.shipRateTotalRange = shipRateTotalRange;
    }

    /**
     * @return the shipRateWeightRange
     */
    public ShipRateWeightRange getShipRateWeightRange() {
        return shipRateWeightRange;
    }

    /**
     * @param shipRateWeightRange the shipRateWeightRange to set
     */
    public void setShipRateWeightRange(ShipRateWeightRange shipRateWeightRange) {
        this.shipRateWeightRange = shipRateWeightRange;
    }

    /**
     * @return the shipRateTotalRangeMap
     */
    public Map<String, ShipRateTotalRange> getShipRateTotalRangeMap() {
        return shipRateTotalRangeMap;
    }

    /**
     * @param shipRateTotalRangeMap the shipRateTotalRangeMap to set
     */
    public void setShipRateTotalRangeMap(Map<String, ShipRateTotalRange> shipRateTotalRangeMap) {
        this.shipRateTotalRangeMap = shipRateTotalRangeMap;
    }

    /**
     * @return the shipRateWeightRangeMap
     */
    public Map<String, ShipRateWeightRange> getShipRateWeightRangeMap() {
        return shipRateWeightRangeMap;
    }

    /**
     * @param shipRateWeightRangeMap the shipRateWeightRangeMap to set
     */
    public void setShipRateWeightRangeMap(Map<String, ShipRateWeightRange> shipRateWeightRangeMap) {
        this.shipRateWeightRangeMap = shipRateWeightRangeMap;
    }

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operationMethod) {
		operation_method = operationMethod;
	}
}
