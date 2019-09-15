/**
 * 
 */
package wolf_j.com.github.relation.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author wolf-J
 *
 */
@Aspect
@Component
public class ControllerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerInterceptor.class);

	@Pointcut("execution(public * wolf_j.com.github.relation.controller.*.*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping") // 两个..代表所有子目录，最后括号里的两个..代表所有参数
	public void logPointCut() {
	}

	@Before("logPointCut()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		LOGGER.info("RequestAddress : " + request.getRequestURL());
		LOGGER.info("HTTP METHOD : " + request.getMethod());
		LOGGER.info("IP : " + request.getRemoteAddr());
		LOGGER.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		LOGGER.info("Parameters : " + Arrays.toString(joinPoint.getArgs()));

	}

	@AfterReturning(returning = "returnedObject", pointcut = "logPointCut()") // returning的值和doAfterReturning的参数名一致
	public void doAfterReturning(Object returnedObject) throws Throwable {

		LOGGER.info(
				"current time : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(System.currentTimeMillis()));
		LOGGER.info("ReturnedObject : " + JSON.toJSONString(returnedObject));
	}

	@After("logPointCut()")
	public void doAfter(JoinPoint joinPoint) throws Throwable {
		LOGGER.info("After PointCut");
	}

	@Around("logPointCut()")
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object returnedObj = proceedingJoinPoint.proceed();// returnedObj 为方法的返回值
		LOGGER.info("Spent Time : " + (System.currentTimeMillis() - startTime));
		return returnedObj;
	}

}
