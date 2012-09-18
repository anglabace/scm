package com.genscript.gsscm.accounting.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.PageDTO;

public class Tools<T> {

	/**
	 * 获取前台的参数，并放到map中
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> buildMap(final HttpServletRequest request) {
        Map<String,Object> resultMap = new HashMap<String,Object>();		
		Enumeration paramNames = request.getParameterNames();
		Map params = new TreeMap();
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			resultMap.put(paramName, request.getParameter(paramName));
			}
		return resultMap;
	}
	
	public  PageDTO formPage(Page<T> page) {
		PageDTO dto = new PageDTO();
		dto.setAutoCount(page.isAutoCount());
		dto.setOrder(page.getOrder());
		dto.setOrderBy(page.getOrderBy());
		dto.setPageNo(page.getPageNo());
		dto.setPageSize(page.getPageSize());
		dto.setTotalCount(page.getTotalCount());
		// TODO 总页数:
//		if (dto.getTotalCount() != null) {
//			int iTemp = dto.getTotalCount().intValue() / dto.getPageSize();
//			int totalPage = (dto.getTotalCount().intValue() % dto.getPageSize()) == 0 ? iTemp : (iTemp + 1);
//			dto.setTotalPage(Long.valueOf(totalPage));
//		}
		return dto;
	}
	
	/**
	 * 得到当前时间，并转化成日期
	 * @param flag
	 * @return
	 */
	public static String getCurrentTime(String format){
		String today = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = new Date();
		today = sdf.format(d);
		return today;
	}
	
	public static String getFormatTime(String date){
		String today = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 
		today = sdf.format(date);
		return today;
	}
	
	public static Date getFormatDate(String date){
		Date day = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    try{
	    
		day = sdf.parse(date);
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		return day;
	}
	/**
	 * fangquan
	 * 2011-12-02
	 * 计算相差天数
	 * @param billingTime
	 * @return
	 * @throws Exception
	 */
	 public static long CompareData(Date billingTime) throws Exception {   
		       final long DAY = 24l * 60l * 60l * 1000l; // 计算一年的毫秒数   
		       Date d = new Date();    
		       Date d1 = billingTime;
		       long days=(d.getTime()-d1.getTime())/DAY;
		       System.out.println((d.getTime() - d1.getTime()) / DAY);  
		        return days;
		    }   

	
	public static String formatDate(Date d){
		String time = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		time = sdf.format(d);
		return time;
	}
	
	
	/**
	 * 给数组添加一个值
	 * @param arr
	 * @param value
	 * @return
	 */
	public static String[] add(String[] arr,String value){
		String result[] = null;
		if(arr == null){
			result = new String[1];
			result[0] = value;
		}else{
		result = new String[arr.length+1];
		for(int i=0;i<arr.length;i++){
			result[i] = arr[i];
		}
		result[arr.length] = value;
		}
		return result;
	}
	
	public static  Integer String2Integer(String source)
	{
		Integer target=null;
		if(source.equals("")){
			return 0;
		}
		try{
		target=Integer.parseInt(source);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			target=new Integer(0);			
		}
		
		return target;
	 }
	
	public static  Double String2Double(String source)
	{
		Double target=null;
		
		try{
		target=Double.valueOf(source);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			target=new Double(0);			
		}
		
		return target;
	 }
	
	public static  Float String2Float(String source)
	{
		Float target=null;
		
		try{
		target=Float.valueOf(source);
		}
		catch(Exception e)
		{
//			e.printStackTrace();
			System.out.println("数字转化异常");
			target=new Float(0);			
		}
		
		return target;
	 }
	
	/**
	 * 保留小数点后2位
	 * @return
	 */
	public static String formatFloat(float f){
		DecimalFormat df2  = new DecimalFormat("###.00");
		String s = df2.format(f);
		if(s.indexOf(".")==0){
			s = "0"+s;
		}
		return s;
	}
	
	
	/**
	 * 保留小数点后2位
	 * @return
	 */
	public static String formatFloat2(Float f){
		if(f == null){
			return "0.00";
		}
		DecimalFormat df2  = new DecimalFormat("###.00");
		String s = df2.format(f);
		if(s.indexOf(".")==0){
			s = "0"+s;
		}
		return s;
	}
	
	/**
	 * 保留最后2为小数,并
	 * @param source
	 * @return
	 */
	public static  String String2String2(String source)
	{
		Float target=null;
		String result = "";
		try{
		target=Float.valueOf(source);
		result = formatFloat(target);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			target=new Float(0);			
		}
		return result;
	 }
	
//public static void main(String[] args) {
//	String s ="1.34634234f";
//	
//	System.out.println(String2String2(s));
//}
	
	
}
