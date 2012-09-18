package com.genscript.gsscm.systemsetting.web;

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
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dto.SourceDTO;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.PageOutputUtil;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.customer.dto.QuoteOrderCouponDTO;
import com.genscript.gsscm.customer.entity.Coupon;
import com.genscript.gsscm.customer.entity.Source;
import com.genscript.gsscm.customer.service.CouponService;
import com.genscript.gsscm.order.entity.PromotionBean;
import com.genscript.gsscm.quoteorder.dto.PromotionDTO;
import com.genscript.gsscm.systemsetting.dto.QuoteOrderPromotionDTO;
import com.genscript.gsscm.systemsetting.dto.QuoteOrderSourceDTO;
import com.genscript.gsscm.systemsetting.service.SystemSettingService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
	@Result(location = "systemsetting/quoteorder_main_form.jsp")
	
  }
)
public class QuoteOrderAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5317097318336126490L;
	@Autowired
	private SystemSettingService systemSettingService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private ExceptionService exceptionUtil;
	private Map<String, Source> resultMap;
	private String type;



	/* System setting quote/order页面调用主方法
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		Map<String, Source> dbMap = null;
		Integer userId = SessionUtil.getUserId();
		PagerUtil<Source> pagerUtil = new PagerUtil<Source>();
		String sourceCode = ServletActionContext.getRequest().getParameter("filter_LIKES_code");
		String sourceName = ServletActionContext.getRequest().getParameter("filter_LIKES_name");
		String sourceDesc = ServletActionContext.getRequest().getParameter("filter_LIKES_description");
		Page<Source> page = new Page<Source>();
		page = pagerUtil.getRequestPage();
		page.setPageSize(10);
		if (!page.isOrderBySetted()) {
			page.setOrderBy("sourceId");
			page.setOrder(Page.DESC);
		}
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		if(filters == null){
			page = systemSettingService.searchSource(page);
		}else{
			page = systemSettingService.searchSource(page, filters);
		}
		dbMap = new LinkedHashMap<String, Source>();
		for(Source source : page.getResult()){
			dbMap.put(source.getSourceId()+"", source);
		}
		Map<String, Source> sessSourceMap = (Map<String, Source>) SessionUtil
		.getRow(SessionConstant.SourceSetting.value(), String.valueOf(userId));
		Map<String, Source> returnMap = searchSessionSource(
				sourceCode, sourceName, sourceDesc, sessSourceMap);
		resultMap = SessionUtil.mergeList(returnMap, dbMap, page.getPageNo());
		String ajax = ServletActionContext.getRequest().getParameter("ajax");
		if(StringUtils.isNotBlank(ajax) && ajax.equals("yes")){
			QuoteOrderSourceDTO dto = new QuoteOrderSourceDTO();
			dto.setMap(resultMap);
			dto.setTotal(page.getTotalCount().intValue());
			PageOutputUtil pageUtil = new PageOutputUtil(page, "javascript:searchSource('{?}')", true);
			dto.setPager(pageUtil.show());
			Struts2Util.renderJson(dto);
		}
		System.out.println("resultMap: " + resultMap);
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		SessionUtil.deleteRow(SessionConstant.PromotionSetting.value(), String.valueOf(userId));
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String delRow() throws Exception {
		String type = ServletActionContext.getRequest().getParameter("type");
		String toDel = ServletActionContext.getRequest().getParameter("toDel");
		Integer userId = SessionUtil.getUserId();
		Map<String, Object> sessSourceMap = null;
		if(type.equals("Source")){
			sessSourceMap = (Map<String, Object>) SessionUtil
		.getRow(SessionConstant.SourceSetting.value(), String.valueOf(userId));
		}else{
			sessSourceMap = (Map<String, Object>) SessionUtil
			.getRow(SessionConstant.PromotionSetting.value(), String.valueOf(userId));
		}
		if(sessSourceMap == null){
			sessSourceMap = new LinkedHashMap<String, Object>();
		}
		if (StringUtils.isNotBlank(toDel)) {
			String[] strs = toDel.split("-");
			List<String> delIdList = Arrays.asList(strs);
			for(String str : delIdList){
				if(!StringUtils.isNumeric(str)){
					sessSourceMap.remove(str);
				}else{			
					sessSourceMap.put(str, null);
				}
			}
		}
		if(type.equals("Source")){
			SessionUtil.insertRow(SessionConstant.SourceSetting.value(), String.valueOf(userId), sessSourceMap);
		}else{
			SessionUtil.insertRow(SessionConstant.PromotionSetting.value(), String.valueOf(userId), sessSourceMap);
		}
		Struts2Util.renderText("SUCCESS");
		return NONE;
	}
	
	@SuppressWarnings("unchecked")
	public String saveSource() throws Exception {
		Map<String, Object> sessSourceMap = null;
		Map<String, Object> rt = new HashMap<String, Object>();
		Integer userId = SessionUtil.getUserId();
		sessSourceMap = (Map<String, Object>) SessionUtil
		.getRow(SessionConstant.SourceSetting.value(), String.valueOf(userId));
		if(sessSourceMap != null && !sessSourceMap.isEmpty()){
			List<Integer> delIds = new ArrayList<Integer>();
			List<SourceDTO> sourceDTOList = new ArrayList<SourceDTO>();
			for (Iterator<String> iter = sessSourceMap.keySet().iterator(); iter
			.hasNext();) {
				String key = iter.next();
				Source val = (Source) sessSourceMap.get(key);
				if(val == null && StringUtils.isNumeric(key)){
					delIds.add(Integer.parseInt(key));
				}else{
					sourceDTOList.add(dozer.map(val, SourceDTO.class));
				}
			}
			try {
				List<String> cannotDelIdList = systemSettingService.saveSource(sourceDTOList, delIds, userId);
				StringBuilder sb = new StringBuilder();
				String cannotDelStr = "";
				sb.append("SUCCESS");
				if(cannotDelIdList != null && !cannotDelIdList.isEmpty()){
					sb.append("##");
					for(String str : cannotDelIdList){
						sb.append(str).append(", ");
					}
					cannotDelStr = sb.substring(0, sb.lastIndexOf(","));
				}
				Struts2Util.renderText(cannotDelStr);
				SessionUtil.deleteRow(SessionConstant.SourceSetting.value(), String.valueOf(userId));
			} catch (Exception e) {
				WSException exDTO = exceptionUtil.getExceptionDetails(e);
				exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
						.getStackTrace()[0].getMethodName(), "INTF0101", userId);
				rt.put("hasException", "Y");
				rt.put("exception", exDTO);
			}
		}
		return NONE;
	}
	
	@SuppressWarnings("unchecked")
	public String savePromotion() throws Exception {
		Map<String, Object> sessPromotionMap = null;
		Map<String, Object> rt = new HashMap<String, Object>();
		Integer userId = SessionUtil.getUserId();
		sessPromotionMap = (Map<String, Object>) SessionUtil
		.getRow(SessionConstant.PromotionSetting.value(), String.valueOf(userId));
		if(sessPromotionMap != null && !sessPromotionMap.isEmpty()){
			List<Integer> delIds = new ArrayList<Integer>();
			List<PromotionDTO> promotionDTOList = new ArrayList<PromotionDTO>();
			for (Iterator<String> iter = sessPromotionMap.keySet().iterator(); iter
			.hasNext();) {
				String key = iter.next();
				PromotionBean val = (PromotionBean) sessPromotionMap.get(key);
				if(val == null && StringUtils.isNumeric(key)){
					delIds.add(Integer.parseInt(key));
				}else{
					promotionDTOList.add(dozer.map(val, PromotionDTO.class));
				}
			}
			try {
				List<String> cannotDelIdList = systemSettingService.savePromotion(promotionDTOList, delIds, userId);
				StringBuilder sb = new StringBuilder();
				String cannotDelStr = "";
				Struts2Util.renderText("SUCCESS");
				if(cannotDelIdList != null && !cannotDelIdList.isEmpty()){
					sb.append("##");
					for(String str : cannotDelIdList){
						sb.append(str).append(", ");
					}
					cannotDelStr = sb.substring(0, sb.lastIndexOf(","));
				}
				Struts2Util.renderText(cannotDelStr);
				SessionUtil.deleteRow(SessionConstant.PromotionSetting.value(), String.valueOf(userId));
			} catch (Exception e) {
				WSException exDTO = exceptionUtil.getExceptionDetails(e);
				exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
						.getStackTrace()[0].getMethodName(), "INTF0101", userId);
				rt.put("hasException", "Y");
				rt.put("exception", exDTO);
				Struts2Util.renderText(exDTO.getMessageDetail());
			}
		}
		return NONE;
	}
	
	public String saveCoupon () throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		Integer userId = SessionUtil.getUserId();
		try {
			couponService.saveCoupons(userId);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0101", userId);
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
			Struts2Util.renderText(exDTO.getMessageDetail());
		}
		Struts2Util.renderText("SUCCESS");
		SessionUtil.deleteRow(SessionConstant.CouponSetting.value(), String.valueOf(userId));
		return NONE;
	}
	
	private Map<String, Source> searchSessionSource(
			String sourceCode, String sourceName, String sourceDesc,
			Map<String, Source> sessSourceMap) {
		Map<String, Source> returnMap = null;
		if(sessSourceMap != null && !sessSourceMap.isEmpty()){
			returnMap = new LinkedHashMap<String, Source>();
			for (Iterator<String> iter = sessSourceMap.keySet().iterator(); iter
			.hasNext();) {
				String key = iter.next();
				Source val = (Source) sessSourceMap.get(key);
				if(val == null && StringUtils.isNumeric(key)){
					returnMap.put(key, val);
					continue;
				}else if(val == null && !StringUtils.isNumeric(key)){
					throw new RuntimeException(
					"Source System Setting Session has Error !");
				}
				if(StringUtils.isNotBlank(sourceCode) && !(val.getCode().contains(sourceCode))){
					continue;
				}
				if(StringUtils.isNotBlank(sourceName) && !(val.getName().contains(sourceName))){
					continue;
				}
				if(StringUtils.isNotBlank(sourceDesc) && !(val.getDescription().contains(sourceDesc))){
					continue;
				}
				returnMap.put(key, val);
			}
		}
		return returnMap;
	}
	
	private Map<String, PromotionBean> searchSessionPromotion(
			String promotionCode, String promotionDesc,
			Map<String, PromotionBean> sessPromotionMap) {
		Map<String, PromotionBean> returnMap = null;
		if(sessPromotionMap != null && !sessPromotionMap.isEmpty()){
			returnMap = new LinkedHashMap<String, PromotionBean>();
			for (Iterator<String> iter = sessPromotionMap.keySet().iterator(); iter
			.hasNext();) {
				String key = iter.next();
				PromotionBean val = (PromotionBean) sessPromotionMap.get(key);
				if(val == null && StringUtils.isNumeric(key)){
					returnMap.put(key, val);
					continue;
				}else if(val == null && !StringUtils.isNumeric(key)){
					throw new RuntimeException(
					"Promotion System Setting Session has Error !");
				}
				if(StringUtils.isNotBlank(promotionCode) && !(val.getPrmtCode().contains(promotionCode))){
					continue;
				}
				if(StringUtils.isNotBlank(promotionDesc) && !(val.getDescription().contains(promotionDesc))){
					continue;
				}
				returnMap.put(key, val);
			}
		}
		return returnMap;
	}

	@SuppressWarnings("unchecked")
	public String listPromotion() throws Exception {
		Map<String, PromotionBean> dbMap = null;
		Map<String, PromotionBean> prmtResultMap = new HashMap<String, PromotionBean>();
		Integer userId = SessionUtil.getUserId();
		PagerUtil<PromotionBean> pagerUtil = new PagerUtil<PromotionBean>();
		String promotionCode = ServletActionContext.getRequest().getParameter("filter_LIKES_prmtCode");
		String promotionDesc = ServletActionContext.getRequest().getParameter("filter_LIKES_description");
		Page<PromotionBean> page = new Page<PromotionBean>();
		page = pagerUtil.getRequestPage();
		page.setPageSize(10);
		if (!page.isOrderBySetted()) {
			page.setOrderBy("prmtId");
			page.setOrder(Page.DESC);
		}
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		if(filters == null){
			page = systemSettingService.searchPromotion(page);
		}else{
			page = systemSettingService.searchPromotion(page, filters);
		}
		dbMap = new LinkedHashMap<String, PromotionBean>();
		for(PromotionBean promotionBean : page.getResult()){
			dbMap.put(promotionBean.getPrmtId()+"", promotionBean);
		}
		Map<String, PromotionBean> sessPromotionMap = (Map<String, PromotionBean>) SessionUtil
		.getRow(SessionConstant.PromotionSetting.value(), String.valueOf(userId));
		Map<String, PromotionBean> returnMap = searchSessionPromotion(
				promotionCode, promotionDesc, sessPromotionMap);
		prmtResultMap = SessionUtil.mergeList(returnMap, dbMap, page.getPageNo());
		String ajax = ServletActionContext.getRequest().getParameter("ajax");
		if(StringUtils.isNotBlank(ajax) && ajax.equals("yes")){
			QuoteOrderPromotionDTO dto = new QuoteOrderPromotionDTO();
			dto.setMap(prmtResultMap);
			dto.setTotal(page.getTotalCount().intValue());
			PageOutputUtil pageUtil = new PageOutputUtil(page, "javascript:searchPromotion('{?}')", true);
			dto.setPager(pageUtil.show());
			Struts2Util.renderJson(dto);
		}
		return NONE;
	}
	
	private Map<String, Coupon> searchSessionCoupon(
			String couponCode,	Map<String, Coupon> sessCouponMap) {
		Map<String, Coupon> returnMap = null;
		if(sessCouponMap != null && !sessCouponMap.isEmpty()){
			returnMap = new LinkedHashMap<String, Coupon>();
			for (Iterator<String> iter = sessCouponMap.keySet().iterator(); iter
			.hasNext();) {
				String key = iter.next();
				Coupon val = (Coupon) sessCouponMap.get(key);
				if(val == null && StringUtils.isNumeric(key)){
					returnMap.put(key, val);
					continue;
				}else if(val == null && !StringUtils.isNumeric(key)){
					throw new RuntimeException(
					"Promotion System Setting Session has Error !");
				}
				if(StringUtils.isNotBlank(couponCode) && !(val.getCode().contains(couponCode))){
					continue;
				}
				returnMap.put(key, val);
			}
		}
		return returnMap;
	}
	
	/**
	 * @author zhangyong
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String listCoupon() throws Exception {
		Map<String, Coupon> dbMap = null;
		Map<String, Coupon> prmtResultMap = new HashMap<String, Coupon>();
		Integer userId = SessionUtil.getUserId();
		PagerUtil<Coupon> pagerUtil = new PagerUtil<Coupon>();
		Page<Coupon> page = new Page<Coupon>();
		String couponCode = ServletActionContext.getRequest().getParameter("filter_LIKES_code");
		page = pagerUtil.getRequestPage();
		page.setPageSize(10);
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		page = systemSettingService.searchCoupon(page, filters);
		
		dbMap = new LinkedHashMap<String, Coupon>();
		for(Coupon coupon : page.getResult()){
			dbMap.put(coupon.getId()+"", coupon);
		}
		Map<String, Coupon> sessPromotionMap = (Map<String, Coupon>) SessionUtil
				.getRow(SessionConstant.CouponSetting.value(), String.valueOf(userId));
		Map<String, Coupon> returnMap = searchSessionCoupon(
				couponCode, sessPromotionMap);
		prmtResultMap = SessionUtil.mergeList(returnMap, dbMap, page.getPageNo());
		String ajax = ServletActionContext.getRequest().getParameter("ajax");
		if(StringUtils.isNotBlank(ajax) && ajax.equals("yes")){
			QuoteOrderCouponDTO dto = new QuoteOrderCouponDTO();
			dto.setMap(prmtResultMap);
			dto.setTotal(page.getTotalCount().intValue());
			PageOutputUtil pageUtil = new PageOutputUtil(page, "javascript:searchCoupon('{?}')", true);
			dto.setPager(pageUtil.show());
			Struts2Util.renderJson(dto);
		}
		return NONE;
	}
	
	@SuppressWarnings("unchecked")
	public String checkSession() throws Exception {
		if(StringUtils.isNotEmpty(type)){
			Integer userId = SessionUtil.getUserId();
			if(type.equals("Promotion")){
				Map<String, PromotionBean> sessPromotionMap = (Map<String, PromotionBean>) SessionUtil
				.getRow(SessionConstant.PromotionSetting.value(), String.valueOf(userId));
				if(sessPromotionMap != null && !sessPromotionMap.isEmpty()){
					Struts2Util.renderText("Y");
				}else{
					Struts2Util.renderText("N");
				}
			}else if(type.equals("Source")){
				Map<String, Source> sessSourceMap = (Map<String, Source>) SessionUtil
				.getRow(SessionConstant.SourceSetting.value(), String.valueOf(userId));
				if(sessSourceMap != null && !sessSourceMap.isEmpty()){
					Struts2Util.renderText("Y");
				}else{
					Struts2Util.renderText("N");
				}
			}
		}
		return NONE;
	}
	
	public String clearSession() throws Exception {
		if(StringUtils.isNotEmpty(type)){
			Integer userId = SessionUtil.getUserId();
			if(type.equals("Promotion")){
				SessionUtil
				.deleteRow(SessionConstant.PromotionSetting.value(), String.valueOf(userId));
				SessionUtil.deleteRow("PromotionSessionCount", userId.toString());
			}else if(type.equals("Source")){
				SessionUtil
				.deleteRow(SessionConstant.SourceSetting.value(), String.valueOf(userId));
				SessionUtil.deleteRow("SourceSessionCount", userId.toString());
			}
			Struts2Util.renderText("SUCCESS");
		}
		return NONE;
	}
	
	public Map<String, Source> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Source> resultMap) {
		this.resultMap = resultMap;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
