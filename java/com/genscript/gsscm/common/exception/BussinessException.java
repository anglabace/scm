package com.genscript.gsscm.common.exception;

import java.util.ArrayList;
import java.util.List;

public class BussinessException extends BaseAppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6155400349722247786L;
	public static final String ORG_REPEATNAME_CODE = "SE0303";
	public static final String ORGDIV_REPEATNAME_CODE = "SE0304";
	public static final String DIVDEPT_REPEATNAME_CODE = "SE0305";
	public static final String ORDER_TMPL_DULP_NAME_CODE = "SE0306";
	public static final String CUST_SP_CATALOGNO_DULP_CODE = "SE0307";
	public static final String SHIP_PKG_ERROR_WAREHOUSEID = "SE0308";
	public static final String SHIP_PKG_ERROR_SHIPTO = "SE0309";
	public static final String SHIP_PKG_ERROR_SHIPMETHOD = "SE0310";
	public static final String SHIP_PKG_ERROR_LEADTIME = "SE0311";
	public static final String SHIP_PKG_ERROR_PACKAGETYPE = "SE0312";
	public static final String CUST_SP_CATALOGID_DIS_CODE = "SE0340";
	public static final String EXCH_RATE_NOT_FOUNTD = "SE0341";
	public static final String EXCH_RATE_IS_NULL = "SE0345";
	public static final String SUB_ITEM_IS_NULL = "SE0346";
	public static final String ADD_ITEM_ERROR = "SE0347";
	public static final String CONVERT_TBD_ERROR = "SE0348";
	
	/**
	 * delete catalog error: has some product categories;
	 */
	public static final String DEL_CATALOG_ERROR_HASPDTCATE = "SE0315";
	/**
	 * delete catalog error: has some service categories;
	 */
	public static final String DEL_CATALOG_ERROR_HASSERCATE = "SE0316";
	/**
	 * delete catalog error: has some approve request;
	 */
	public static final String DEL_CATALOG_ERROR_HASAPPREQ = "SE0317";
	/**
	 * delete product category error: has some sub product categories;
	 */
	public static final String DEL_PDTCATE_ERROR_HASSUB = "SE0318";
	/**
	 * delete service category error: has some sub service categories;
	 */
	public static final String DEL_SERVICE_ERROR_HASSUB = "SE0343";
	/**
	 * delete product category error: has some products;
	 */
	public static final String DEL_PDTCATE_ERROR_HASPDTS = "SE0319";
	/**
	 * delete service category error: has some services;
	 */
	public static final String DEL_SERVICE_ERROR_HASPDTS = "SE0344";
	/**
	 * delete product category error: has some approve request;
	 */
	public static final String DEL_PDTCATE_ERROR_HASAPPREQ = "SE0320";
	/**
	 * delete service category error: has some approve request;
	 */
	public static final String DEL_SERVICE_ERROR_HASAPPREQ = "SE0342";
	/**
	 * calculate Shipping package时获得shipRate报错.
	 */
	public static final String ERR_CAL_PACKING_MORESHIPRATE = "SE0321";
	public static final String ERR_CAL_PACKING_NOSHIPRATE = "SE0322";
	public static final String ERR_CAL_PACKING_PKGWEIGHT = "SE0323";
	public static final String ERR_ORDERTMPL_COUNT = "SE0324";
	public static final String ERR_QUOTETMPL_COUNT = "SE0325";
	public static final String ERR_CATALOGID_UNIQUE = "SE0326";
	/**
	 * delete service category error: has some sub service categories;
	 */
	public static final String ERR_DEL_SERVCATE_HASSUB = "SE0327";
	/**
	 * delete service category error: has some services;
	 */
	public static final String ERR_DEL_SERVCATE_HASSERVS = "SE0328";
	/**
	 * delete service category error: has some approve request;
	 */
	public static final String ERR_DEL_SERVCATE_HASAPPREQ = "SE0329";
	/*
	 * save cataloge error : defaultflag 已经有“Y”值，不可有重复的“Y”；
	 */
	public static final String ERR_CATALOGDEFAULTFLAG_REPEATY="SE0330";
	/*
	 * save cataloge error : status 不准是INACTIVE
	 */
	public static final String ERR_CATALOGDEFAULTFLAG_STATUS_NOTMATCH="SE0331";
	/*
	 * 新建service时 catalog_no 不许重复；
	 */
	public static final String ERR_SERVICE_CATALOGNO_UNIQUE = "SE0333";
	/*
	 * 新建product 时 catalog_no 不许重复；
	 */
	public static final String ERR_PRODUCT_CATALOGNO_UNIQUE = "SE0334";
	/*
	 * 新建serviceCategory 时 category_no 不许重复;
	 */
	public static final String ERR_SERVICECATEGORY_CATEGORYNO_UNIQUE = "SE0335";
	/*
	 * 新建productCategory 时 category_no 不许重复；
	 */
	public static final String ERR_PRODUCTCATEGORY_CATEGORYNO_UNIQUE = "SE0336";
	/**
	 * Manufacture模块The resource no can't be duplicated.
	 */
	public static final String ERR_RESOURCENO_UNIQUE = "SE0337";
	/**
	 * Cannot auto calculate the package when the shipAddress, warehouseId, shipMethod, packageType is not the same.
	 */
	public static final String INF_CAL_PACKING_DIFF = "SI0201";
	public static final String INF_PROMOTION_CANNOT_APPLY = "SI0202";
	public List<String> replaceParamValues;
	public BussinessException(String code) {
		super(code);
	}
	public BussinessException(String code, String replaceValue) {
		super(code);
		replaceParamValues = new ArrayList<String>();
		replaceParamValues.add(replaceValue);
	}
	/**
	 * @return the replaceParamValues
	 */
	public List<String> getReplaceParamValues() {
		return replaceParamValues;
	}
	/**
	 * @param replaceParamValues the replaceParamValues to set
	 */
	public void setReplaceParamValues(List<String> replaceParamValues) {
		this.replaceParamValues = replaceParamValues;
	}


}
