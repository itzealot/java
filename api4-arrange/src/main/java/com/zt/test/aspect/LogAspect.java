package com.zt.test.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 * 
 * @author zt
 *
 */
@Aspect
@Component
public class LogAspect {

	/**
	 * 环绕切面表达式
	 * 
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("(execution (public * org.jasig.cas..*.*(..))) && !(execution( * org.jasig.cas..*.set*(..)))")
	public Object traceMethod(final ProceedingJoinPoint point) throws Throwable {
		Object returnVal = null;
		final Logger log = getLog(point);
		final String methodName = point.getSignature().getName();

		try {
			if (log.isTraceEnabled()) {
				final Object[] args = point.getArgs();
				final String arguments;
				if (args == null || args.length == 0) {
					arguments = "";
				} else {
					arguments = Arrays.deepToString(args);
				}
				log.trace("Entering method [" + methodName + " with arguments [" + arguments + "]");
			}
			returnVal = point.proceed();
			return returnVal;
		} finally {
			if (log.isTraceEnabled()) {
				log.trace("Leaving method [" + methodName + "] with return value ["
						+ (returnVal != null ? returnVal.toString() : "null") + "].");
			}
		}
	}

	protected Logger getLog(final JoinPoint joinPoint) {
		final Object target = joinPoint.getTarget();

		if (target != null) {
			return LoggerFactory.getLogger(target.getClass());
		}

		return LoggerFactory.getLogger(getClass());
	}

}
