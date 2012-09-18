package com.genscript.gsscm.systemsetting.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.entity.Coupon;
import com.genscript.gsscm.customer.service.CouponService;
import com.genscript.gsscm.order.entity.PromotionBean;
import com.genscript.gsscm.ws.WSException;

/**
 * 
 * @author zhangyong
 *
 */
@Results({
	@Result(name = "showEditForm", location = "systemsetting/quoteorder_coupon_create_form.jsp") })
public class QuoteOrderCouponAction extends BaseAction<PromotionBean> {

	private static final long serialVersionUID = 4578173529015342113L;
	private String id;
	private String opType;
	private String couponCode;
	private String couponName;
	private String couponValue;
	private String couponComments;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private CouponService couponService;
	private Coupon coupon;
	private String message;
	
	@Override
	public String input() {
		opType = "update";
		try {
			coupon = couponService.showEditCoupon(id);
			couponCode = coupon.getCode();
			couponName = coupon.getName();
			couponValue = coupon.getValue() + "";
			couponComments = coupon.getComments();	
		} catch (Exception ex) {
		}
		return "showEditForm";
	}
	
	public String showCreateForm () throws Exception {
		opType = "add";
		return "showEditForm";
	}

	
	@Override
	public String delete() throws Exception {
		String toDel = ServletActionContext.getRequest().getParameter("toDel");
		try {
			couponService.delSessionCoupon(toDel);
			Struts2Util.renderText("SUCCESS");
		} catch (Exception ex) {
			Struts2Util.renderText("FAiLURE");
		}
		return NONE;
	}
	
	@Override
	public String list() throws Exception {
		
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		
		
	}

	@Override
	public String save() throws Exception {
		id = Struts2Util.getParameter("id");
		opType = Struts2Util.getParameter("opType");
		couponCode = Struts2Util.getParameter("couponCode");
		couponName = Struts2Util.getParameter("couponName");
		couponValue = Struts2Util.getParameter("couponValue");
		couponComments = Struts2Util.getParameter("couponComments");
		try {
			id = couponService.saveCouponToSession(id, opType, 
					couponCode, couponName, couponValue, couponComments, coupon);
			message = "Success";
		} catch (Exception ex) {
			message = "Failure";
		}
		return "showEditForm";
	}
	
	public String checkCouponCode () {
		String message = null;
		try {
			couponCode = Struts2Util.getParameter("couponCode");
			List<Coupon> couponList = couponService.checkCouponCode(couponCode);
			if (couponList == null || couponList.size() == 0) {
				message = "success";
			} else {
				message = "failure";
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
		}
		Struts2Util.renderJson(message);
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}

	public String getCouponComments() {
		return couponComments;
	}

	public void setCouponComments(String couponComments) {
		this.couponComments = couponComments;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}