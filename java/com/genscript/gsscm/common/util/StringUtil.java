package com.genscript.gsscm.common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang.xwork.StringUtils;

public class StringUtil {

	public static boolean isNumeric(String s) {
		if (StringUtils.isBlank(s)) {
			return false;
		}
		return s.matches("[-+]?\\d*\\.?\\d+");
	}

	public static String implode(String[] ary, String delim) {
		String out = "";
		for (int i = 0; i < ary.length; i++) {
			if (i != 0) {
				out += delim;
			}
			out += ary[i];
		}
		return out;
	}

	/**
	 * 当输入的内容含有"'"时将它转换为"''"即可查询 当输入的内容含有"/"时将它转换为"//"即可查询
	 * 当输入的内容含有"%"时将它转换为"/%"即可查询 当输入的内容含有"_"时将它转换为"/_"即可查询
	 */
	public static String escapeString(String sValue) {
		if (sValue == null || "".equals(sValue)) {
			return sValue;
		}
		if (sValue.contains("'") || sValue.contains("/")
				|| sValue.contains("%") || sValue.contains("_")) {
			StringBuffer str = new StringBuffer();
			for (int i = 0; i < sValue.length(); i++) {
				char ca = sValue.charAt(i);
				switch (ca) {
				case '\'':
					str.append("''");
					break;
				case '/':
					str.append("//");
					break;
				case '%':
					str.append("/%");
					break;
				case '_':
					str.append("/_");
					break;
				default:
					str.append(ca);
					break;
				}
			}
			return str.toString();
		}
		return sValue;
	}

	/**
	 * 获得请求URL的字符编码格式, 不同的浏览器它他的URL编码格式是不一样的
	 * @author wangsf
	 * @since 2010-12-21
	 * @param str
	 * @return 找到合适的则返回"UTF8", "GBK"或返回null.
	 * @throws UnsupportedEncodingException
	 */
	public static String getURLEncoding(String str)
			throws UnsupportedEncodingException {
		String encodings[] = { "UTF8", "GBK" };
		for (String encoding : encodings) {
			String coded = new String(str.getBytes("iso-8859-1"), encoding);
			String decode = new String(coded.getBytes(encoding), "iso-8859-1");
			if (decode.equals(str)) {
				System.out.println("encoding: " + encoding);
				return encoding;
			}
		}
		return null;
	}
	
	/**
	 * 获得指定长度的随机数字串
	 * @param length
	 * @return
	 */
	public static String getIntRandom(int length) {
		  int[] array=new int[length];
		  StringBuilder str = new StringBuilder();
		        for(int i = 0; i < length;  i ++){
		            array[i] = (int)(Math.random()*10);
		            str.append(array[i]);
		        }
		        return str.toString();  
		 }
	
	/**
	 * 获得指定长度的随机字母串
	 * @param length
	 * @return
	 */
	 public static String getCharRandom(int length) {
	  int[] array=new int[length];
	  char[] chars = new char[length];
	  StringBuilder str = new StringBuilder();
	        for(int i = 0; i < length;  i ++){
	         while (true) {
	          array[i] = (int)(Math.random()*1000);
	          if ((array[i] > 64 && array[i] < 91) || 
	            (array[i] > 96 && array[i] < 123)) break;
	         }            
	            chars[i] = (char) array[i];
	            str.append(chars[i]);
	        }
	        return str.toString();   
	 }

	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public static List removeDuplicateWithOrder(List list) {
	        Set set = new HashSet();
	        List newList = new ArrayList();

	        for (Iterator iter = list.iterator(); iter.hasNext();) {
	            Object element = iter.next();
	            if (set.add(element))
	                newList.add(element);
	        }
	        return newList;
	    }

    public static String subStringByLength(String str,int  length){
    	if(org.apache.commons.lang.StringUtils.isNotBlank(str) && str.length() > length)
    		return str.substring(0,length).trim();
    	else
    		return str;

    }
    
    public static int calculateSeqLength(String seq){
    	String p = seq.replaceAll("\\{.*?}", "X");
    	return p.length();
    }
    
    /**
     * 过滤传进来的参数，只留数字，无小数点
     * @author Zhang Yong
     * add date 2011-10-09
     * @param param
     * @return
     */
    public static Integer getNumber (String param) {
    	if (param == null) {
    		return null;
    	}
    	String number = Pattern.compile("[^0-9]").matcher(param).replaceAll("").trim();
    	if (!number.equals("")) {
    		return Integer.parseInt(number);
    	}
    	return null;
    }

    /**
     * 删除input字符串中的html格式
     *
     * @param input
     * @param length
     * @return
     */
    public static String splitAndFilterString(String input, int length) {
        if (input == null || input.trim().equals("")) {
            return "";
        }
        // 去掉所有html元素,
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                "<[^>]*>", "");
        str = str.replaceAll("[(/>)<]", "");
        int len = str.length();
        if (len <= length) {
            return str;
        } else {
            str = str.substring(0, length);
            str += "......";
        }
        return str;
    }
    
    /**
     * 将List以regex连接成字符串
     * @author Zhang Yong
     * add date 2011-12-01
     * @param list
     * @param regex
     * @return
     */
    public static String listToString (@SuppressWarnings("rawtypes") List list, String regex) {
    	String _return = "";
    	if (list == null || list.isEmpty()) {
    		return _return;
    	}
    	regex = regex == null?"":regex;
    	StringBuffer sbf = new StringBuffer();
    	for (int i=0;i<list.size();i++) {
    		if (i==(list.size()-1)) {
        		sbf.append(list.get(i)==null?"":list.get(i).toString());
    		} else {
        		sbf.append(list.get(i)==null?"":list.get(i).toString()).append(regex);
    		}
    	}
    	_return = sbf.toString();
    	return _return;
    }
}
