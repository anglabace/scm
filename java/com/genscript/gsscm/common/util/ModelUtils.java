package com.genscript.gsscm.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Model或dto对象操作工具类
 * @author wangsf
 *
 */
public class ModelUtils {

	/**
	 * 合并src对象到tag对象中， src和tag对象应该是同一种类型.
	 * @param src
	 * @param tag
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object mergerModel(final Object src, Object tag) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Method[] srcMethodArray = src.getClass().getDeclaredMethods();
		Method[] tagMethodArray = tag.getClass().getDeclaredMethods();
		for (Method srcMethod : srcMethodArray) {
			//选取src对象和tag对象共有方法的对应属性进行赋值合并
			if (srcMethod.getName().startsWith("get") && getMethodByName(srcMethod.getName(), tagMethodArray) != null) {
           	    Object ret = srcMethod.invoke(src);
           	    if (ret != null) {
    				String temp = srcMethod.getName().replaceAll("get", "set");
    				String returnType = srcMethod.getReturnType().getSimpleName();
    				if (returnType.equals("Integer") && ret.toString().equals("-1")) {//Integer型是-1的话， 把合并后的结果置为null.
    					ret = null;
    				}
    				Method setMethod = getMethodByName(temp, tagMethodArray);
                    if (setMethod != null) {                	
                    	setMethod.invoke(tag, ret);//把新值设置到目标对象.
                    }
           	    }                
			}
		}
		return tag;
	}

	private static Method getMethodByName(String methodName, Method[] methodArray) {
		Method retMethod = null;
		for (Method method : methodArray) {
			if (method.getName().equals(methodName)) {
				retMethod = method;
				break;
			}
		}
		return retMethod;
	}
	

}
