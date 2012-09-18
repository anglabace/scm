package com.genscript.gsscm.common.util;
/**
 * 处理SOAP传过来的值, 进行适当的转化。
 * @author wangsf
 *
 */
public class SoapUtil {
	/**
	 * 如果id值为0, 则返回null, 否则返回id本身.
	 * @param id
	 * @return
	 */
	public static Integer getIntegerFromSOAP(Integer id) {
		if (id == null || id.intValue() == 0) {
			return null;
		} else {
			return id;
		}
	}
}
