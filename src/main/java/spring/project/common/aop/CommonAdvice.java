package spring.project.common.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class CommonAdvice {
	
	@Around("execution(public * spring.project..*.*(..))")
	public Object enterLog(ProceedingJoinPoint pjp) throws Throwable{
		long startTime = System.currentTimeMillis();
		String className = pjp.getTarget().getClass().getSimpleName();
		String methodName = pjp.getSignature().getName();
		log.info("============================");
		log.info(className + "." + methodName + " : " + Arrays.toString(pjp.getArgs()));
		Object result = pjp.proceed();
		long endTime = System.currentTimeMillis();
		log.info(className + "." + methodName + " : " + (endTime-startTime));
		log.info("============================");
		// 처리문에 대한 실행
		return result;
	}

}
