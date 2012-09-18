package com.genscript.gsscm.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class AddressUtil {
 public static String getFullAddress(String addrLine1, String addrLine2, String addrLine3, String city, String state, String zipCode, String country) {
	StringBuilder sb = new StringBuilder();
	List<String> strList = new ArrayList<String>();
	addrLine2 = StringUtils.isNotEmpty(addrLine2)?" "+addrLine2:"";
	addrLine3 = StringUtils.isNotEmpty(addrLine3)?" "+addrLine3:"";
	String addrLine = sb.append(addrLine1).append(addrLine2).append(addrLine3).toString();
	if(addrLine != null){
		strList.add(addrLine);
	}
	if(city != null){
		strList.add(city);
	}
	String statez = StringUtils.isNotEmpty(state) && StringUtils.isNotEmpty(zipCode)?state+" " + zipCode:state;
	if(StringUtils.isNotEmpty(statez)){
		strList.add(statez);
	}
	if(StringUtils.isNotEmpty(country)){
		strList.add(country);
	}
	return StringUtil.implode(strList.toArray(new String[strList.size()]), ", ");
 }
 
 public static String getFullAddress(String addrLine1, String addrLine2, String addrLine3, String city, String state, String zipCode, String country, String orgName) {
		List<String> strList = new ArrayList<String>();
		if(StringUtils.isNotEmpty(orgName)){
			strList.add(orgName);
		}
		strList.add(addrLine1);
		if(StringUtils.isNotEmpty(addrLine2)){
			strList.add(addrLine2);
		}
		if(StringUtils.isNotEmpty(addrLine3)){
			strList.add(addrLine3);
		}
		String cityStr = "";
		if (!StringUtils.isEmpty(city)) {
			cityStr = city;
		}
		if (!StringUtils.isEmpty(state)) {
			if (cityStr.equalsIgnoreCase("")) {
				cityStr = state + " "
						+ zipCode;
			} else {
				cityStr = cityStr + ", " + state + " "
						+ zipCode;
			}
		}
		if (!StringUtils.isEmpty(country)) {
			if (cityStr.equalsIgnoreCase("")) {
				cityStr = country;
			} else {
				cityStr = cityStr + ", " + country;
			}
		}
//		if(city != null){
//			strList.add(city);
//		}
//		String statez = StringUtils.isNotEmpty(state) && StringUtils.isNotEmpty(zipCode)?state+" " + zipCode:state;
//		if(StringUtils.isNotEmpty(statez)){
//			strList.add(statez);
//		}
//		if(StringUtils.isNotEmpty(country)){
//			strList.add(country);
//		}
		strList.add(cityStr);
		
		return StringUtil.implode(strList.toArray(new String[strList.size()]), "|");
	 }
 
 public static String getUniqueAddress(String firstName, String midName, String lastName, String state, String country){
	 StringBuilder sb = new StringBuilder();
	 if(!StringUtils.isEmpty(firstName)){
		 sb.append(firstName);
	 }
	 if(!StringUtils.isEmpty(midName)){
		 sb.append(" ").append(midName);
	 }
	 if(!StringUtils.isEmpty(lastName)){
		 sb.append(" ").append(lastName);
	 }
	 if(!StringUtils.isEmpty(state)){
		 sb.append(" ").append(state);
	 }
	 if(!StringUtils.isEmpty(country)){
		 sb.append(" ").append(country);
	 }
	 return sb.toString();
 }
 
 public static String getFullName(String firstName, String midName, String lastName){
	 StringBuilder sb = new StringBuilder();
	 if(!StringUtils.isEmpty(firstName)){
		 sb.append(firstName);
	 }
	 if(!StringUtils.isEmpty(midName)){
		 sb.append(" ").append(midName);
	 }
	 if(!StringUtils.isEmpty(lastName)){
		 sb.append(" ").append(lastName);
	 }
	 return sb.toString();
 }
 
}
