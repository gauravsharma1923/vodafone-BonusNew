
package com.spice.bonus.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import java.util.Arrays;

@Aspect

@Component
public class LoggingAspect {
	private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

	@Around("execution(* com.spice.bonus.dao.*.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object className = joinPoint.getTarget().getClass().getName();
		LOGGER.info(className + "-> Method:" + joinPoint.getSignature().getName() + "() begins with :"
				+ Arrays.toString(joinPoint.getArgs()));
		try {
			final StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			Object result = joinPoint.proceed();
			stopWatch.stop();
			LOGGER.info(className + "-> Method: " + joinPoint.getSignature().getName() + "() ends with :" + result
					+ ",Execution time: " + stopWatch.getTotalTimeSeconds() + "seconds");
			return result;
		} catch (IllegalArgumentException e) {
			LOGGER.error(className + "-> Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in :"
					+ joinPoint.getSignature().getName() + "()");
			throw e;
		}
	}
}
