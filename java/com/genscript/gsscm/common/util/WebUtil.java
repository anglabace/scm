package com.genscript.gsscm.common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.genscript.gsscm.common.UrlUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.entity.PbSearchAttribute;
import com.genscript.gsscm.basedata.entity.PbSearchCondition;

public class WebUtil {

	// -- Content Type 定义 --//
	public static final String TEXT_TYPE = "text/plain";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";

	private WebUtil() {

	}

	@SuppressWarnings("unchecked")
	public static Map getParametersStartingWith(HttpServletRequest request,
			String prefix) {
		Enumeration paramNames = request.getParameterNames();
		Map params = new TreeMap();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = getParameterValueWithName(request, paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}

	/**
	 * Build Quote and Order list search conditions for customer module
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<PropertyFilter> buildQuorderConditions(
			HttpServletRequest request) {
		Enumeration paramNames = request.getParameterNames();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("custNo".equals(paramName)) {
				String custNoVal = request.getParameter(paramName);
				PropertyFilter propertyFilter = new PropertyFilter(
						"EQI_custNo", custNoVal);
				filterList.add(propertyFilter);
			} else if ("status".equals(paramName)) {
				String statusVal = request.getParameter(paramName);
				if (statusVal != null && statusVal.length() > 0) {
					PropertyFilter propertyFilter = new PropertyFilter(
							"EQS_status", statusVal);
					filterList.add(propertyFilter);
				}
			} else if ("fromAmount".equals(paramName)) {
				String fromAmountVal = request.getParameter("fromAmount");
				if (fromAmountVal != null && fromAmountVal.length() > 0) {
					PropertyFilter fromPropertyFilter = new PropertyFilter(
							"GEN_amount", fromAmountVal);
					filterList.add(fromPropertyFilter);
				}
			} else if ("toAmount".equals(paramName)) {
				String toAmountVal = request.getParameter("toAmount");
				if (toAmountVal != null && toAmountVal.length() > 0) {
					PropertyFilter toPropertyFilter = new PropertyFilter(
							"LEN_amount", toAmountVal);
					filterList.add(toPropertyFilter);
				}
			} else if ("fromDate".equals(paramName)) {
				String fromDateVal = request.getParameter("fromDate");
				StringBuffer fromdate = new StringBuffer(fromDateVal).append(
						" ").append("00:00:00");
				String a = fromdate.toString();
				// System.out.println(">>>>>>>>>>>>>>>>>>fromdate================"
				// + a);
				String codeTypeVal = request.getParameter("codeType");
				PropertyFilter fromPropertyFilter;
				if (codeTypeVal.equals("quote")) {
					if (fromDateVal != null && fromDateVal.length() > 0) {
						fromPropertyFilter = new PropertyFilter(
								"GED_quoteDate", a);
						filterList.add(fromPropertyFilter);
					}
				} else {
					if (fromDateVal != null && fromDateVal.length() > 0) {
						fromPropertyFilter = new PropertyFilter(
								"GED_orderDate", a);
						filterList.add(fromPropertyFilter);
					}
				}
			} else if ("toDate".equals(paramName)) {
				String codeTypeVal = request.getParameter("codeType");
				String toDateVal = request.getParameter("toDate");
				StringBuffer toDate = new StringBuffer(toDateVal).append(" ")
						.append("23:59:59");
				String a = toDate.toString();
				// System.out.println(">>>>>>>>>>>>>>>>>>toDate================"
				// + a);
				PropertyFilter toPropertyFilter;
				if (codeTypeVal.equals("quote")) {
					if (toDateVal != null && toDateVal.length() > 0) {
						toPropertyFilter = new PropertyFilter("LED_quoteDate",
								a);
						filterList.add(toPropertyFilter);
					}
				} else {
					if (toDateVal != null && toDateVal.length() > 0) {
						toPropertyFilter = new PropertyFilter("LED_orderDate",
								a);
						filterList.add(toPropertyFilter);
					}
				}
			}
		}
		System.out.println("filterList: " + filterList);
		return filterList;
	}

	/**
	 * Build my search conditions
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static PbSearchCondition[] buildMySrchConditions(
			HttpServletRequest request) {
		Enumeration paramNames = request.getParameterNames();
		List<PbSearchCondition> pbSearchConditionList = new ArrayList<PbSearchCondition>();
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("attr".equals(paramName)) {
				String[] attrValues = getParameterValueWithName(request,
						paramName);
				if (attrValues == null || attrValues.length == 0) {
					// Do nothing, no values found at all.
				} else {
					int n = attrValues.length;
					for (int i = 0; i < n; i++) {
						PbSearchAttribute pbSearchAttribute = new PbSearchAttribute();
						pbSearchAttribute.setAttributeId(Integer
								.parseInt(attrValues[i]));
						PbSearchCondition pbSearchCondition = new PbSearchCondition();
						pbSearchCondition
								.setPbSearchAttribute(pbSearchAttribute);
						pbSearchCondition.setOperator(request
								.getParameterValues("opr")[i]);
						pbSearchCondition.setValue1(request
								.getParameterValues("valueA")[i]);
						pbSearchCondition.setValue2(request
								.getParameterValues("valueB")[i]);
						pbSearchConditionList.add(pbSearchCondition);
					}
				}

			}

		}
		return (PbSearchCondition[]) pbSearchConditionList
				.toArray((new PbSearchCondition[pbSearchConditionList.size()]));
	}

	private static String[] getParameterValueWithName(
			HttpServletRequest request, String paramName) {
		String[] values = request.getParameterValues(paramName);
		if (values == null || values.length == 0) {

		} else if (values.length > 1) {

		}
		return values;
	}

	/**
	 * 根据按PropertyFilter命名规则的Request参数,创建PropertyFilter列表.
	 * 默认Filter属性名前缀为filter_.
	 * 
	 * @see #buildPropertyFilters(HttpServletRequest, String)
	 */
	public static List<PropertyFilter> buildPropertyFilters(
			final HttpServletRequest request) {
		return buildPropertyFilters(request, "filter_");
	}

	/**
	 * filter_EQS_name filter_LIKES_name_OR_email
	 */
	@SuppressWarnings("unchecked")
	public static List<PropertyFilter> buildPropertyFilters(
			final HttpServletRequest request, final String filterPrefix) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		Map<String, Object> filterParamMap = getParametersStartingWith(request,
				filterPrefix);
		for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = "";
			try {
				if (filterName.equals("LIKES_organizationName")) {
					value = StringEscapeUtils.escapeHtml((String) entry
							.getValue());
				} else {
					value = (String) entry.getValue();
				}
			} catch (Exception ex) {
				String[] values = ((String[]) entry.getValue());
				if (values != null && values.length > 0) {
					value = values[0];
				}
			}
			if (StringUtils.isNotBlank(value)) {
				PropertyFilter filter = new PropertyFilter(filterName, value);

				filterList.add(filter);
			}
		}
		return filterList;
	}

	boolean isCnorEn(char c) {
		if ((c >= 0x0391 && c <= 0xFFE5) || (c >= 0x0000 && c <= 0x00FF)) // 英文字符
			return true;
		return false;
	}

	public boolean gbk(String str) {
		char[] chars = str.toCharArray();
		boolean isGB2312 = false;
		for (int i = 0; i < chars.length; i++) {
			byte[] bytes = ("" + chars[i]).getBytes();
			if (bytes.length == 2) {
				int[] ints = new int[2];
				ints[0] = bytes[0] & 0xff;
				ints[1] = bytes[1] & 0xff;
				if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40
						&& ints[1] <= 0xFE) {
					isGB2312 = true;
					break;
				}
			}
		}
		return isGB2312;
	}

	/**
	 * 设置客户端缓存过期时间 Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response,
			long expiresSeconds) {
		// Http 1.0 header
		response.setDateHeader("Expires", System.currentTimeMillis()
				+ expiresSeconds * 1000);
		// Http 1.1 header
		response.setHeader("Cache-Control", "private, max-age="
				+ expiresSeconds);
	}

	/**
	 * 设置客户端无缓存Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 0);
		response.addHeader("Pragma", "no-cache");
		// Http 1.1 header
		response.setHeader("Cache-Control", "no-cache");
	}

	/**
	 * 设置LastModified Header.
	 */
	public static void setLastModifiedHeader(HttpServletResponse response,
			long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	/**
	 * 设置Etag Header.
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	/**
	 * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
	 * <p/>
	 * 如果无修改, checkIfModify返回false ,设置304 not modify status.
	 * 
	 * @param lastModified
	 *            内容的最后修改时间.
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request,
			HttpServletResponse response, long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 根据浏览器 If-None-Match Header, 计算Etag是否已无效.
	 * <p/>
	 * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
	 * 
	 * @param etag
	 *            内容的ETag.
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request,
			HttpServletResponse response, String etag) {
		String headerValue = request.getHeader("If-None-Match");
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!"*".equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(
						headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader("ETag", etag);
				return false;
			}
		}
		return true;
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 
	 * @param fileName
	 *            下载后的文件名.
	 */
	public static void setFileDownloadHeader(HttpServletResponse response,
			String fileName) {
		try {
			// 中文文件名支持
			String encodedfileName = new String(fileName.getBytes(),
					"ISO8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ encodedfileName + "\"");
		} catch (UnsupportedEncodingException e) {
		}
	}
}
