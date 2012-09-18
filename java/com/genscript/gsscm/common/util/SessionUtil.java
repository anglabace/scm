package com.genscript.gsscm.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.constant.SessionPdtServ;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.opensymphony.xwork2.ActionContext;

/**
 * @description: 操作Session方法工具类
 * @author: Golf
 * @createDate: 2010/7/24 1:36 PM
 */
public class SessionUtil {

	/**
	 * 获得SESSION中map中的一个对象
	 * 
	 * @param tblName
	 *            map对象名称
	 * @param primaryKey
	 *            对象的唯一属性
	 * @return Object
	 */
	@SuppressWarnings("unchecked")
	public static Object getRow(String tblName, String primaryKey) {
		if (tblName == null || primaryKey == null) {
			return null; 
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (!session.containsKey(tblName)) {
			return null;
		}
		
		Map<String, Object> map = (Hashtable<String, Object>) session
				.get(tblName);
		if (!map.isEmpty()) {
			for (Iterator<String> iter = map.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				if (key.equals(primaryKey)) {
					return map.get(key);
				}
			}
		}
		return null;
	}

	/**
	 * 获得Map对象
	 * 
	 * @return HashMap
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getRow(String tblName, int number,String sessNo) {
		Map<String, Object> sessionMap;
       // System.out.println("wo jin lai le ");
		if (number == 0) {
			sessionMap = (HashMap<String, Object>) SessionUtil.getRow(tblName,
					sessNo);
		} else {
			sessionMap = (HashMap<String, Object>) SessionUtil.getRow(tblName,
					String.valueOf(number));
		}

  //      System.out.println("w ------------ "+sessionMap);
		return sessionMap;
	}

	/**
	 * 获得一组对象
	 * 
	 * @param tblName
	 *            map对象
	 * @param primaryKeyList
	 *            唯一属性列表
	 * @return 对象列表
	 */
	@SuppressWarnings("null")
	public static List<Object> getRows(String tblName,
			List<String> primaryKeyList) {
		List<Object> list = null;
		for (String val : primaryKeyList) {
			Object object = getRow(tblName, val);
			if (object != null) {
				list.add(object);
			}
		}
		return list;
	}

	/**
	 * 更新map里的对象
	 * 
	 * @param tblName
	 *            map对象
	 * @param primaryKey
	 * @param object
	 *            待更新的对象
	 * 
	 */
	public static void updateRow(String tblName, String primaryKey,
			Object object) {
		SessionUtil.insertRow(tblName, primaryKey, object);
	/*	Object oldObject = getRow(tblName, primaryKey);
		if (oldObject == null) {
			throw new RuntimeException("Not Found Key '" + tblName
					+ "' Exception");
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		Map<String, Object> map = (Map<String, Object>) session.get(tblName);
		map.put(primaryKey, object);
		session.put(tblName, map);*/
	}

	/**
	 * 根据一组key删除Map对象里的一组对象
	 * 
	 * @param tblName
	 * @param primaryKeyList
	 */
	@SuppressWarnings("unchecked")
	public static void deleteRows(String tblName, List<String> primaryKeyList) {
		List<Object> list = getRows(tblName, primaryKeyList);
		if (list != null && list.size() > 0) {
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			Map<String, Object> map = (Hashtable<String, Object>) session
					.get(tblName);
			for (Object key : list) {
				map.remove(key);
			}
		}
	}

	/**
	 * 删除map中的一个对象
	 * 
	 * @param tblName
	 * @param primaryKey
	 *            map对象的一个key
	 */
	@SuppressWarnings("unchecked")
	public static void deleteRow(String tblName, String primaryKey) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (!session.containsKey(tblName)) {
			return;
		}
		Map<String, Object> map = (Hashtable<String, Object>) session
				.get(tblName);
		if (!map.isEmpty()) {
			for (Iterator<String> iter = map.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				if (primaryKey.equals(key)) {
					iter.remove();
					map.remove(key);
				}
			}
		}
	}

	/**
	 * 在SESSION中新建Map对象
	 * 
	 * @param tblName
	 * @param primaryKey
	 * @param object
	 */
	@SuppressWarnings("unchecked")
	public static void insertRow(String tblName, String primaryKey,
			Object object) {
		if (tblName == null || primaryKey == null || object == null) {
			throw new RuntimeException("Missing the arguments");
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.containsKey(tblName)) {
			Map<String, Object> map = (Hashtable<String, Object>) session
					.get(tblName);
			map.put(primaryKey, object);
		} else {
			Hashtable<String, Object> ht = new Hashtable<String, Object>();
			ht.put(primaryKey, object);
			session.put(tblName, ht);
		}
	}

	/**
	 * 新增对象
	 * 
	 * @return
	 */
	public static void insertRow(String tblName, int number, String sessNo,
			Object object) {
		if (number == 0) {
			SessionUtil.insertRow(tblName, sessNo, object);
		} else {
			SessionUtil.insertRow(tblName, String.valueOf(number), object);
		}
	}

	/**
	 * 生产随机唯一字符串
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String generateTempId() throws Exception {
		return EncrypeUtil.encrypeString(EncrypeUtil.get5Radom());
	}

	/**
	 * 获得登录用户ID
	 * 
	 * @return
	 */
	public static Integer getUserId() {
		return (Integer) ActionContext.getContext().getSession().get(
				StrutsActionContant.USER_ID);
	}

	/**
	 * 获得登录用户名
	 * 
	 * @return
	 */
	public static String getUserName() {
		return (String) ActionContext.getContext().getSession().get(
				StrutsActionContant.USER_NAME);
	}

	/**
	 * 获得 子list 单条记录
	 * 
	 * @author zouyulu
	 * @param tblName
	 * @param no
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object getOneRow(String tblName, String no, String key) {
		Map<String, Object> map = (Map<String, Object>) SessionUtil.getRow(
				tblName, no);
		if (map != null && map.containsKey(key)) {
			return map.get(key);
		}
		return null;
	}

	/**
	 * 更新 子list单条记录(不改变原有顺序)
	 * 
	 * @author zouyulu
	 * @param tblName
	 * @param no
	 * @param key
	 * @param updateObject
	 */
	@SuppressWarnings("unchecked")
	public static void updateOneRow(String tblName, String no, String key,
			Object updateObject) {
		Map<String, Object> map = (Map<String, Object>) SessionUtil.getRow(
				tblName, no);
		if (map == null) {
			map = new LinkedHashMap<String, Object>();
			SessionUtil.insertRow(tblName, no, map);
		}
		map.put(key, updateObject);
		SessionUtil.updateRow(tblName, no, map);
	}

	/**
	 * 删除 子list单条记录
	 * 
	 * @author zouyulu
	 * @param tblName
	 * @param no
	 * @param key
	 * @param updateObject
	 */
	@SuppressWarnings("unchecked")
	public static void deleteOneRow(String tblName, String no, String key) {
		Map<String, Object> map = (Map<String, Object>) SessionUtil.getRow(
				tblName, no);
		if(map == null){
			map = new LinkedHashMap<String, Object>();
			SessionUtil.insertRow(tblName, no, map);
		}
		if(StringUtils.isNumeric(key)){
			map.put(key, null);
		}else if(map.containsKey(key)){
			map.remove(key);
		}
		SessionUtil.updateRow(tblName, no, map);
	}
	
	/**
	 * 转化List为map, 键值为list对象中的主键
	 * 
	 * @author zouyulu
	 * @param <T>
	 * @param list
	 * @param primaryKeyName
	 * @return
	 */
	public static <T> Map<String, T> convertList2Map(List<T> list,
			String primaryKeyName) {
		Map<String, T> retMap = new LinkedHashMap<String, T>();
		if (list == null || StringUtils.isEmpty(primaryKeyName)) {
			return retMap;
		}
		for (int i = 0; i < list.size(); i++) {
			String str = ToStringBuilder.reflectionToString(list.get(i));
			int startPos = StringUtils.indexOf(str, primaryKeyName + "=")
					+ primaryKeyName.length() + 1;
			int endPos = StringUtils.indexOf(str, ",", startPos);
			String primaryValue = StringUtils.substring(str, startPos, endPos);
			retMap.put(primaryValue, list.get(i));
		}
		return retMap;
	}
	
	/**
	 * 转化Set为map, 键值为Set对象中的主键
	 * 
	 * @author lizhang
	 * @param <T>
	 * @param set
	 * @param primaryKeyName
	 * @return
	 */
	public static <T> Map<String, T> convertSet2Map(Set<T> set,
			String primaryKeyName) {
		Map<String, T> retMap = new LinkedHashMap<String, T>();
		if (set == null || StringUtils.isEmpty(primaryKeyName)) {
			return retMap;
		}
		Iterator<T> it = set.iterator();
		while(it.hasNext()) {
			String str = ToStringBuilder.reflectionToString(it.next());
			int startPos = StringUtils.indexOf(str, primaryKeyName + "=")
					+ primaryKeyName.length() + 1;
			int endPos = StringUtils.indexOf(str, ",", startPos);
			String primaryValue = StringUtils.substring(str, startPos, endPos);
			retMap.put(primaryValue, it.next());
		}
		return retMap;
	}
	
	/**
	 * 转化Set为list
	 * 
	 * @author lizhang
	 * @param <T>
	 * @param set
	 * @return
	 */
	public static <T> List<T> convertSet2List(Set<T> set) {
		List<T> retList = new LinkedList<T>();
		if (set == null||set.isEmpty()) {
			return retList;
		}
		Iterator<T> it = set.iterator();
		while(it.hasNext()) {
			retList.add(it.next());
		}
		return retList;
	}


	/**
	 * 合并session中的map和数据库的map方法
	 * 
	 * @param sessContactMap
	 * 
	 * @param dbContactMap
	 * 
	 * @return
	 */
	public static <T> Map<String, T> mergeList(Map<String, T> sessMap,
			Map<String, T> dbMap, Integer isSessionTop) {
		if (sessMap == null || sessMap.isEmpty()) {
			return dbMap;
		}
		if (dbMap == null || dbMap.isEmpty()) {
			return sessMap;
		}
		Map<String, T> resultMap = new LinkedHashMap<String, T>();
		Map<String, T> sessAddMap = new LinkedHashMap<String, T>();
		Map<String, T> sessEditMap = new LinkedHashMap<String, T>();
		List<String> sessEditKeyList = new ArrayList<String>();
		List<String> sessDelKeyList = new ArrayList<String>();
		for (Iterator<String> iter = sessMap.keySet().iterator(); iter
				.hasNext();) {
			String key = iter.next();
			T val = (T) sessMap.get(key);
			if (!StringUtil.isNumeric(key)) {
				sessAddMap.put(key, val);
			} else if (StringUtils.isNumeric(key) && val == null) {
				sessDelKeyList.add(key);
			} else if (StringUtil.isNumeric(key)) {
				sessEditKeyList.add(key);
				sessEditMap.put(key, val);
			}
		}

		for (Iterator<String> iter = dbMap.keySet().iterator(); iter.hasNext();) {
			String key = iter.next();
			if (sessDelKeyList.contains(key)) {
				continue;
			} else if (sessEditKeyList.contains(key)) {
				resultMap.put(key, sessEditMap.get(key));
			} else {
				resultMap.put(key, (T) dbMap.get(key));
			}
		}

		// 大于1时候，新增加的不进行merge.
		if (isSessionTop > 1) {
			return resultMap;
		} else if (isSessionTop == 1) {
			sessAddMap.putAll(resultMap);
			return sessAddMap;
		} else {
			resultMap.putAll(sessAddMap);
			return resultMap;
		}
	}
	
	/**
	 * 合并session中的map和page中的result方法
	 * 
	 * @param tblName
	 * 
	 * @param page<T>
	 * 
	 * @return page
	 */
	@SuppressWarnings("unchecked")
	public static <T> Page<T> mergePageList(Page<T> page,String tblName){
		Object obj = SessionUtil.getRow(SessionPdtServ.CatalogProductCategory.value(), tblName);
		if(page.getPageNo()==1){
			Map<String,T> productCategoryMap = (Map<String, T>) obj;
			for(Map.Entry<String, T> entry: productCategoryMap.entrySet()){
				T productCategory = productCategoryMap.get(entry.getKey());
				if(productCategory!=null){
					page.getResult().add(productCategory);
				}
			}
		}
		return page;
	}
        /**
         * Function: convert map to list
         * @param <T>
         * @param map
         * @return list
         */
        public static <T> List<T> convertMap2List(Map<String,T> map){
            if(map==null)
                return null;
            List<T> list=new ArrayList<T>();
            Iterator<String> iterator=map.keySet().iterator();
            while(iterator.hasNext()){
                String key=iterator.next();
                list.add(map.get(key));
            }
            return list;
        }
   
}