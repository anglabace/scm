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
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.order.entity.PromotionBean;
import com.genscript.gsscm.systemsetting.service.SystemSettingService;

@Results({
		@Result(name = "showCreateForm", location = "systemsetting/quoteorder_promotion_create_form.jsp"),
		@Result(name = "showEditForm", location = "systemsetting/quoteorder_promotion_create_form.jsp") })
public class QuoteOrderPromotionAction extends BaseAction<PromotionBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7495528818751912585L;
	private String opType;
	private String id;
	private String prmtCode_view;
	private String operation_method;
	private String specialSell;
	private String editable;
	private PromotionBean promotion;
	private Map<String, List<PbDropdownListOptions>> dropDownList;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;

	@Autowired
	private PublicService publicService;
	@Autowired
	private SystemSettingService systemSettingService;

	public String showCreateForm() throws Exception {
		setOpType("add");
		editable = "Y";
		initDropDownList();
		return "showCreateForm";
	}

    public String validDupCode() throws Exception {
        String code = Struts2Util.getParameter("code")==null? "" : Struts2Util.getParameter("code");
        String result = "Y";
        if(!"".equals(code)){
            if(systemSettingService.findBypromotionCode(code) != null ){
              result = "N";
            }
        }
        Struts2Util.renderText(result);
        return null;
    }

	@Override
	public String input() throws Exception {
		initDropDownList();
		setOpType("update");
		editable = "Y";
		if(systemSettingService.isPromotionApprove(promotion.getPrmtCode())){
			editable = "N";
		}
		specialSell = "N";
		if (promotion.getDiscType() != null && promotion.getDiscPrice() != null
				&& promotion.getDiscType().substring(4, 5).equals("1")
				|| (promotion.getSpecialDiscPercent() != null)) {
			specialSell = "Y";
		}
		promotion.setCateTotalAmount(promotion.getCateTotalAmount()!=null?promotion.getCateTotalAmount().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setDiscAmount(promotion.getDiscAmount()!=null?promotion.getDiscAmount().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setDiscCateAmount(promotion.getDiscCateAmount()!=null?promotion.getDiscCateAmount().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setDiscOrderTotal(promotion.getDiscOrderTotal()!=null?promotion.getDiscOrderTotal().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setDiscPrice(promotion.getDiscPrice()!=null?promotion.getDiscPrice().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setOrderTotalMin1(promotion.getOrderTotalMin1()!=null?promotion.getOrderTotalMin1().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setOrderTotalMin2(promotion.getOrderTotalMin2()!=null?promotion.getOrderTotalMin2().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setOrderTotalMin3(promotion.getOrderTotalMin3()!=null?promotion.getOrderTotalMin3().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setDiscCatePercent(promotion.getDiscCatePercent()!=null?promotion.getDiscCatePercent().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setDiscPercent(promotion.getDiscPercent()!=null?promotion.getDiscPercent().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		return "showEditForm";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String save() throws Exception {
		Integer userId = SessionUtil.getUserId();
		System.out.println("Promotion: " + promotion);
		if(StringUtils.isNotEmpty(promotion.getDiscCategory())&&promotion.getDiscCategory().indexOf(",")!=-1) {
			promotion.setDiscCategory(promotion.getDiscCategory().replace(",","").trim());
		}
		if (promotion.getCumulateFlag() != null && promotion.getCumulateFlag().equalsIgnoreCase("false")) {
			promotion.setCumulateFlag(null);
		}
		String discAmountPercentType = Struts2Util.getParameter("discAmountPercentType");
		if("Percent".equalsIgnoreCase(discAmountPercentType)){
			promotion.setDiscCateAmount(null);
		}else if("Amount".equalsIgnoreCase(discAmountPercentType)){
			promotion.setDiscCatePercent(null);
		}
		promotion.setCateTotalAmount(promotion.getCateTotalAmount()!=null?promotion.getCateTotalAmount().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setDiscAmount(promotion.getDiscAmount()!=null?promotion.getDiscAmount().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setDiscCateAmount(promotion.getDiscCateAmount()!=null?promotion.getDiscCateAmount().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setDiscOrderTotal(promotion.getDiscOrderTotal()!=null?promotion.getDiscOrderTotal().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setDiscPrice(promotion.getDiscPrice()!=null?promotion.getDiscPrice().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setOrderTotalMin1(promotion.getOrderTotalMin1()!=null?promotion.getOrderTotalMin1().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setOrderTotalMin2(promotion.getOrderTotalMin2()!=null?promotion.getOrderTotalMin2().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setOrderTotalMin3(promotion.getOrderTotalMin3()!=null?promotion.getOrderTotalMin3().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setDiscCatePercent(promotion.getDiscCatePercent()!=null?promotion.getDiscCatePercent().setScale(2, BigDecimal.ROUND_HALF_UP):null);
		promotion.setDiscPercent(promotion.getDiscPercent()!=null?promotion.getDiscPercent().setScale(2, BigDecimal.ROUND_HALF_UP):null);
//		if (promotion.getDiscType().equals("1")) {
//			promotion.setDiscPercent(Double
//					.parseDouble(Struts2Util.getParameter("discPercent_t1")));
//			promotion.setOrderTotalMin(Double
//					.parseDouble(Struts2Util.getParameter("orderTotalMin_t1")));
//		} else if (promotion.getDiscType().equals("2")) {
//			promotion.setOrderTotalMin(Double
//					.parseDouble(Struts2Util.getParameter("orderTotalMin_t2")));
//		} else {
//			if (StringUtils.isNotBlank(Struts2Util
//					.getParameter("discPercent_t3")))
//				promotion
//						.setDiscPercent(Double
//								.parseDouble(Struts2Util
//										.getParameter("discPercent_t3")));
//			if (StringUtils.isNotBlank(Struts2Util
//					.getParameter("orderTotalMin_t3")))
//				promotion.setOrderTotalMin(Double
//						.parseDouble(Struts2Util
//								.getParameter("orderTotalMin_t3")));
//		}
		promotion.setStatus("ACTIVE");
		Map<String, Object> sessionMap = (HashMap<String, Object>) SessionUtil
				.getRow(SessionConstant.PromotionSetting.value(),
						String.valueOf(userId));
		if (sessionMap == null) {
			sessionMap = new LinkedHashMap<String, Object>();
		}
		if (opType.equals("add") && StringUtils.isBlank(id)) {
			id = SessionUtil.generateTempId();
			promotion.setGsCoId(1);
			sessionMap.put(id, promotion);
		} else if (opType.equals("update") && !StringUtils.isNumeric(id)) {
			sessionMap.put(id, promotion);
		} else {
			sessionMap.put(promotion.getPrmtId() + "", promotion);
		}
		SessionUtil.insertRow(SessionConstant.PromotionSetting.value(),
				String.valueOf(userId), sessionMap);
		Struts2Util.renderText("SUCCESS" + "," + id);
		return NONE;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void prepareModel() throws Exception {
		Integer userId = SessionUtil.getUserId();
		if (StringUtils.isNotEmpty(id)) {
			if (!StringUtils.isNumeric(id)) {
				promotion = (PromotionBean) SessionUtil.getOneRow(
						SessionConstant.PromotionSetting.value(),
						String.valueOf(userId), id);
			} else {
				Map<String, Object> sessionMap = (HashMap<String, Object>) SessionUtil
						.getRow(SessionConstant.PromotionSetting.value(),
								String.valueOf(userId));
				if (sessionMap != null && !sessionMap.isEmpty()) {
					if (sessionMap.containsKey(id)) {
						promotion = (PromotionBean) sessionMap.get(id);
					} else {
						getPromotionDetail();
					}
				} else {
					getPromotionDetail();
				}
			}
		} else if(StringUtils.isNotEmpty(prmtCode_view)) {
			promotion = this.systemSettingService.findBypromotionCode(prmtCode_view);
		} else {
			promotion = new PromotionBean();
		}

	}

	private void getPromotionDetail() {
		if(StringUtils.isNumeric(id)){
			Integer promotionId = Integer.parseInt(id);
			promotion = systemSettingService.getPromotionDetail(promotionId);
		}
	}

	/**
	 * 初始化public drop down list
	 */
	private void initDropDownList() {
		dropDownList = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownList.put("CUST_PRIORITY_LEVEL",
				publicService.getDropdownList("CUST_PRIORITY_LEVEL"));
		dropDownList.put("PROMOTION_APPLY_TYPE",
				publicService.getDropdownList("PROMOTION_APPLY_TYPE"));
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.RFM_VALUE);
		speclListName.add(SpecDropDownListName.CUSTOMER_ROLE);
		speclListName.add(SpecDropDownListName.COUNTRY_NAME);
		speclListName.add(SpecDropDownListName.TERRITORY);
		speclListName.add(SpecDropDownListName.ORIGINAL_SOURCE);
		speclListName.add(SpecDropDownListName.CATEGORY);
		speclListName.add(SpecDropDownListName.SHIP_METHOD);
		specDropDownList = publicService.getSpecDropDownMap(speclListName);
	}

	@Override
	public PromotionBean getModel() {
		return promotion;
	}

	@Override
	public String list() throws Exception {
		return null;
	}

	@Override
	public String delete() throws Exception {
		return null;
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

	public Map<String, List<PbDropdownListOptions>> getDropDownList() {
		return dropDownList;
	}

	public void setDropDownList(
			Map<String, List<PbDropdownListOptions>> dropDownList) {
		this.dropDownList = dropDownList;
	}

	public PromotionBean getPromotion() {
		return promotion;
	}

	public void setPromotion(PromotionBean promotion) {
		this.promotion = promotion;
	}

	public String getSpecialSell() {
		return specialSell;
	}

	public void setSpecialSell(String specialSell) {
		this.specialSell = specialSell;
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
		return specDropDownList;
	}

	public void setSpecDropDownList(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
		this.specDropDownList = specDropDownList;
	}

	public String getPrmtCode_view() {
		return prmtCode_view;
	}

	public void setPrmtCode_view(String prmtCode_view) {
		this.prmtCode_view = prmtCode_view;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}
}
