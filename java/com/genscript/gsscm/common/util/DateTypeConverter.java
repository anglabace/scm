package com.genscript.gsscm.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


import org.apache.log4j.Logger;
import org.apache.struts2.util.StrutsTypeConverter;


/**
 * @description: Struts2时间类型转换器
 * @author user
 * @createDate: 2010/10/20 9:49 AM
 */
public class DateTypeConverter extends StrutsTypeConverter {

	private static final Logger log = Logger.getLogger(DateTypeConverter.class);
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	
	//目前支持的时间类型转换格式种类
	 public static final DateFormat[] ACCEPT_DATE_FORMATS = {  
		             new SimpleDateFormat(DEFAULT_DATE_FORMAT),
		             new SimpleDateFormat("yyyy-MM-dd"),  
		             new SimpleDateFormat("yyyy/MM/dd") };  
	
	 public DateTypeConverter() {  
		     } 
	 
	@SuppressWarnings("unchecked")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values[0] == null || values[0].trim().equals("")){
			return null;
		}
		for (DateFormat format : ACCEPT_DATE_FORMATS) {
			try {
				return format.parse(values[0]);
			}catch (ParseException e) {
				continue; 
			}catch (RuntimeException e) {
				continue;
			}
		}
		log.debug("can not format date string:" + values[0]);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String convertToString(Map context, Object o) {
		if (o instanceof Date) {
			SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			try {
				return format.format((Date) o);
			}catch (RuntimeException e) {
				return "";
			}
		}
		return "";
	}
	
	/**
	 * 将时间按yyyy-MM-dd格式输出为字符串
	 * @author lizhang
	 * @param o
	 * @return
	 */
	public static String convertToString(Object o,DateFormat dateFormat) {
		if (o instanceof Date) {
			SimpleDateFormat format = (SimpleDateFormat)dateFormat;
			try {
				return format.format((Date) o);
			}catch (RuntimeException e) {
				return "";
			}
		}
		return "";
	}

}
