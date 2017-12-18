package net.framework.aop;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：net.framework.aop.SysLogService.java 
 * Created On: 2015-4-28 下午3:02:20
 * Description: aop类，可以执行各种业务之后生成日志并添加到数据库中
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Component
@Aspect
public class SysLogService {
	@Before("execution(* com.bxsurvey.*.service..*(..))")
	public void beforeGetDo(JoinPoint j) {
//		System.out.println("添加前插入");
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//
//		SysUser user = (SysUser)request.getSession().getAttribute("userBO");
//		//System.out.println("用户名：" + user.getLoginName());
//		Object obj[] = j.getArgs();
//		for (Object o : obj) {
//			System.out.println("调用的类名：" + o.getClass().getName());
//		}
//		//System.out.println("aop的方法名：" + j.getSignature().getName());// 这个是获得方法名
	}
	
}
