package com.genscript.gsscm.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.genscript.gsscm.common.SessionBaseDTO;
import com.genscript.gsscm.common.constant.OperationType;
import com.genscript.gsscm.common.constant.SessionConstant;

/**
 * @description: 操作Session合并方法工具类
 * @author: Golf
 * @createDate: 2010/7/24 1:36 PM
 */

public class SessionEmergeUtil<T extends SessionBaseDTO> {

	/**
	 * 合并session中的map和数据库的map方法
	 * 
	 * @param sessContactMap
	 * 
	 * @param dbContactMap
	 * 
	 * @return
	 */
	public Map<String, T> mergeList(Map<String, T> sessContactMap,
			Map<String, T> dbContactMap, Integer isSessionTop) {
		if (sessContactMap == null || sessContactMap.isEmpty()) {
			return dbContactMap;
		}
		if (dbContactMap == null || dbContactMap.isEmpty()) {
			return sessContactMap;
		}
		Map<String, T> resultMap = new LinkedHashMap<String, T>();
		Map<String, T> sessAddMap = new LinkedHashMap<String, T>();
		Map<String, T> sessEditMap = new LinkedHashMap<String, T>();
		List<String> sessEditKeyList = new ArrayList<String>();
		List<String> sessDelKeyList = new ArrayList<String>();
		for (Iterator<String> iter = sessContactMap.keySet().iterator(); iter
				.hasNext();) {
			String key = iter.next();
			T val = (T) sessContactMap.get(key);
			if (!StringUtil.isNumeric(key)) {
				sessAddMap.put(key, val);
			} else if ((StringUtils.isNumeric(key) && val == null)
					|| (val.getOperationType() != null && val
							.getOperationType().equals(
									OperationType.DEL.value()))) {
				sessDelKeyList.add(key);
			} else if (StringUtil.isNumeric(key)) {
				sessEditKeyList.add(key);
				sessEditMap.put(key, val);
			}
		}

		for (Iterator<String> iter = dbContactMap.keySet().iterator(); iter
				.hasNext();) {
			String key = iter.next();
			if (sessDelKeyList.contains(key)) {
				continue;
			} else if (sessEditKeyList.contains(key)) {
				resultMap.put(key, sessEditMap.get(key));
			} else {
				resultMap.put(key, (T) dbContactMap.get(key));
			}
		}

		//大于1时候，新增加的不进行merge.
		if(isSessionTop > 1){
			return resultMap;
		}else if (isSessionTop == 1) {
			sessAddMap.putAll(resultMap);
			return sessAddMap;
		} else {
			resultMap.putAll(sessAddMap);
			return resultMap;
		}
	}
	
	/** 转换Object类型的Map为DTO类型的Map
	 * @param originMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, T> convertMap(Map<String, Object> originMap){
		if (originMap != null && !originMap.isEmpty()) {
			Map<String, T> resultMap = new LinkedHashMap<String, T>();
			for (Iterator<String> iter = originMap.keySet().iterator(); iter
			.hasNext();) {
				String key = iter.next();
				T val = (T) originMap.get(key);
				if (key == null || key.equals("null")) {
					continue;
				}
				if (val == null || val.equals("null")) {
					continue;
				}
				resultMap.put(key, val);
			}
			return resultMap;
		}
		return null;
	}
	
	/** 返回过滤了已删除的list
	 * 
	 * @param map
	 * 
	 * @return
	 */
	public List<T> getFilterList(Map<String, T> map){
		List<T> list = null;
		if (!map.isEmpty()) {
			list = new ArrayList<T>();
			for (Iterator<String> iter = map.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				T val = (T) map.get(key);
				if (val != null
						&& val.getOperationType() != null
						&& (val.getOperationType()
								.equals(OperationType.DEL))) {
					continue;
				}
				list.add(val);		
			}
			return list;
		}
		return null;
	}
	
	/** 删除内存中的对象的公用方法
	 * @param delIdS
	 * @param custNo
	 * @param sessCustNo
	 * @param sessConstant
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings("unchecked")
	public void deleteSessionObj(String delIdS, int custNo, String sessCustNo, SessionConstant sessConstant, String className) {
		String delIdStr = ServletActionContext.getRequest().getParameter(delIdS);
		System.out.println("delIdStr: " + delIdStr);
		if (StringUtils.isNotEmpty(delIdStr)) {
			String[] strs = delIdStr.split(",");
			List<String> delIdList = Arrays.asList(strs);
			Map<String, Object> map = null;
			// 取得session中的map
			System.out.println("custNo, sessCustNo: " + custNo + ", " + sessCustNo);
			map = SessionUtil.getRow(sessConstant.value(), custNo, sessCustNo);
			System.out.println("Map； " + map);
			if(map == null){
				map = new HashMap<String, Object>();
			}
			for(String str : delIdList){
				if(StringUtils.isNumeric(str)){
					if(map!=null && map.containsKey(str)){
						T dto = (T)map.get(str);
						dto.setOperationType(OperationType.DEL);
						map.put(str, dto);
					}else{
						T dto = null;
						try {
							dto = (T)Class.forName(className).newInstance();
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dto.setOperationType(OperationType.DEL);
						map.put(str, dto);
					}
				}else{
					map.remove(str);
				}
			}
			System.out.println("map: "+map);
			SessionUtil.insertRow(sessConstant.value(), custNo, sessCustNo, map);
		}
	}
}
