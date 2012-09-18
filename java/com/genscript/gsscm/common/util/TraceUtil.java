package com.genscript.gsscm.common.util;

import org.apache.log4j.MDC;

public abstract class TraceUtil {
	
	public static final String TRACE_ID_KEY = "traceId";

	/**
	 * 开始Trace, 默认生成本次Trace的ID(8字符长)并放入MDC.
	 */
	public static void beginTrace() {
		String traceId = IdUtils.randomBase62();
		MDC.put(TRACE_ID_KEY, traceId);
	}

	/**
	 * 结束一次Trace, 清除traceId.
	 */
	public static void endTrace() {
		MDC.remove(TRACE_ID_KEY);
	}
}
