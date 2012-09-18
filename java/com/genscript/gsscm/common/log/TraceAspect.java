package com.genscript.gsscm.common.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genscript.gsscm.common.util.TraceUtil;

@Aspect
public class TraceAspect {
	
	/**
	 * 定义Customer Service层方法
	 */
	@Pointcut("execution(public * com.genscript.gsscm.customer.service.*Service.*(..)) ")
	public void customerServiceMethod() {
	}

	/**
	 * 为WebService入口方法加入TraceID控制.
	 */
	@Around("customerServiceMethod()")
	public Object custTraceAround(ProceedingJoinPoint pjp) throws Throwable {
		TraceUtil.beginTrace();
		try {
			return pjp.proceed();
		} finally {
			TraceUtil.endTrace();
		}
	}
	
	/**
	 * 定义ERP Service层方法
	 */
	@Pointcut("execution(public * com.genscript.gsscm.epicorwebservice.service.*Service.*(..)) ")
	public void erpServiceMethod() {
	}

	/**
	 * 为WebService入口方法加入TraceID控制.
	 */
	@Around("erpServiceMethod()")
	public Object erpTraceAround(ProceedingJoinPoint pjp) throws Throwable {
		TraceUtil.beginTrace();
		try {
			return pjp.proceed();
		} finally {
			TraceUtil.endTrace();
		}
	}

	/**
	 * 对有@Traced标记的方法,记录其执行参数及返回结果.
	 */
	@Around("execution(@Traced * *(..))")
	public Object logAground(ProceedingJoinPoint pjp) throws Throwable {
		Class<?> sourceClass = pjp.getSignature().getDeclaringType();
		Logger logger = LoggerFactory.getLogger(sourceClass);
		Object result = null;

		try {
			logger.debug(pjp.toShortString());
			result = pjp.proceed();
			return result;
		} finally {
			if (result != null && logger.isDebugEnabled()) {
				logger.debug("{} return {}", pjp.getSignature().toShortString(), result.toString());
			}
		}
	}
}
