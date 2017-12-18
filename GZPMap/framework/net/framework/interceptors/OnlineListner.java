package net.framework.interceptors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：net.framework.interceptors.OnlineListner.java 
 * Created On: 2015-5-7 上午11:16:13
 * Description: 监听器
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
public class OnlineListner implements ServletContextListener, HttpSessionListener {
	private static Logger log = Logger.getLogger(OnlineListner.class);
	public static ApplicationContext ctx = null;
	private static Long loginCount = (long) 0;
	public void contextDestroyed(ServletContextEvent evt) {
		
	}
	/*
	 * 服务器初始化,获取spring上下文
	 */
	public void contextInitialized(ServletContextEvent evt) {
		log.info("******************************实例化ApplicationContext");
		ctx = WebApplicationContextUtils.getWebApplicationContext(evt.getServletContext());
	}
	
	public static ApplicationContext getCtx() {
		return ctx;
	}
	
	/*
	 * 记录登录信息
	 */
	public void sessionCreated(HttpSessionEvent evt) {
//		loginCount++;
//		System.out.println("登录主机数：" + loginCount);
	}
	/*
	 * 销毁登录信息
	 */
	public void sessionDestroyed(HttpSessionEvent evt) {
//		loginCount--;
//		System.out.println("登录主机数：" + loginCount);
	}

}
