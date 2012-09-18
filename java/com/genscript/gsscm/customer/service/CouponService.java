package com.genscript.gsscm.customer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.customer.dao.CouponDao;
import com.genscript.gsscm.customer.entity.Coupon;

/**
 * 
 * @author zhangyong
 *
 */
@Service
@Transactional
public class CouponService {

	@Autowired
	private CouponDao couponDao;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Coupon showEditCoupon (String id) throws Exception {
		Integer userId = SessionUtil.getUserId();
		Map<String, Object> sessionMap = (HashMap<String, Object>) SessionUtil
			.getRow(SessionConstant.CouponSetting.value(), String.valueOf(userId));
		if (sessionMap != null && sessionMap.containsKey(id)) {
			return (Coupon)sessionMap.get(id);
		} else {
			return couponDao.findUniqueBy("id", Integer.parseInt(id.trim()));
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public void delSessionCoupon (String toDel) throws Exception {
		Integer userId = SessionUtil.getUserId();
		Map<String, Object> sessCouponMap = (Map<String, Object>) SessionUtil
			.getRow(SessionConstant.CouponSetting.value(), String.valueOf(userId));
		if(sessCouponMap == null){
			sessCouponMap = new LinkedHashMap<String, Object>();
		}
		if (StringUtils.isNotBlank(toDel)) {
			String[] strs = toDel.split("-");
			List<String> delIdList = Arrays.asList(strs);
			for(String str : delIdList){
				if(!StringUtils.isNumeric(str)){
					sessCouponMap.remove(str);
				}else{
					sessCouponMap.put(str, null);
				}
			}
			SessionUtil.insertRow(SessionConstant.CouponSetting.value(), String.valueOf(userId), sessCouponMap);
		}
	}
	
	
	/**
	 * 保存coupon到session中，此保存未对数据库操作
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String saveCouponToSession (String id, String opType, String couponCode, 
			String couponName, String couponValue, String couponComments, 
			Coupon paramCoupon) throws Exception {
		if (couponCode == null || ("").equals(couponCode.trim()) 
				|| !StringUtils.isNumeric(couponCode.trim())
				|| couponCode.trim().length() > 20) {
			throw new Exception();
		}
		Coupon coupon = new Coupon();
		coupon.setCode(couponCode.trim());
		coupon.setName(couponName);
		coupon.setValue(Integer.parseInt(couponValue));
		coupon.setComments(couponComments);
		if (id == null || ("").equals(id.toString().trim())) {
			id = SessionUtil.generateTempId();
		} else if (paramCoupon != null && paramCoupon.getId() != null) {
			Coupon couponById = couponDao.findUniqueBy("id", paramCoupon.getId());
			if (couponById == null) {
				throw new Exception();
			}
			coupon.setId(couponById.getId()); 
			coupon.setCreationDate(couponById.getCreationDate());
		}
		Integer userId = SessionUtil.getUserId();
		Map<String, Object> sessionMap = (HashMap<String, Object>) SessionUtil
			.getRow(SessionConstant.CouponSetting.value(), String.valueOf(userId));
		if (sessionMap == null) {
			sessionMap = new HashMap<String, Object>();
		}
		sessionMap.put(id,coupon);
		SessionUtil.insertRow(SessionConstant.CouponSetting.value(),
				String.valueOf(userId), sessionMap);
		return id;
	}
	
	/**
	 * 保存之前放到session中的coupon，删除在列表页面中删除的coupons,对数据库操作
	 * @param userId
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void saveCoupons (Integer userId) throws Exception {
		Map<String, Object> sessCouponMap = (Map<String, Object>) SessionUtil
				.getRow(SessionConstant.CouponSetting.value(), String.valueOf(userId));
		if(sessCouponMap != null && !sessCouponMap.isEmpty()){
			List<Integer> delIds = new ArrayList<Integer>();
			List<Coupon> couponList = new ArrayList<Coupon>();
			for (Iterator<String> iter = sessCouponMap.keySet().iterator(); iter.hasNext();) {
				String key = iter.next();
				Coupon coupon = (Coupon) sessCouponMap.get(key);
				if(coupon == null && StringUtils.isNumeric(key)){
					delIds.add(Integer.parseInt(key));
				} else {
					couponList.add(coupon);
				}
			}
			if(delIds != null && delIds.size() > 0){
				couponDao.delCoupons(delIds);
			}
			if(couponList != null && couponList.size() > 0){
				for(Coupon coupon : couponList){
					if (coupon.getCreationDate() == null) {
						coupon.setCreatedBy(userId);
						Date now = new Date();
						coupon.setCreationDate(now);
						couponDao.save(coupon);
					} else {
						List<Coupon> checklist = this.checkCouponCode(coupon.getCode());
						if (checklist != null && checklist.size() == 1) {
							Coupon checkCoupon = checklist.get(0);
							if (checkCoupon.getCode().equals(coupon.getCode())) {
								checkCoupon.setName(coupon.getName());
								checkCoupon.setValue(coupon.getValue());
								checkCoupon.setComments(coupon.getComments());
								couponDao.save(checkCoupon);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 检查coupon code的唯一性
	 * @param couponCode
	 * @return
	 */
	public List<Coupon> checkCouponCode (String couponCode) throws Exception {
		return couponDao.findCouponByCode(couponCode);
	}
	
	/**
	 * 显示coupon列表
	 * @author lizhang
	 */
	public Page<Coupon> findCouponList(Page<Coupon> couponPage,List<PropertyFilter> filters) {
		couponPage = this.couponDao.findPage(couponPage, filters);
		return couponPage;
	}
}
