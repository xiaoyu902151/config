package net.framework.interceptors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.framework.utils.RequestUtil;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bxsurvey.danger.DangerSupplies.controller.DangerSuppliesController;
import com.bxsurvey.sys.module.service.SysModuleServiceI;
import com.bxsurvey.sys.user.model.SysUser;

/**
 * 
 *********************************************** 
 * Copyright (c) 2014 Hengte Technology Co.,Ltd. All Rights Reserved.
 * FileName：com.bxsurvey.interceptors.LoginFilter.java Created On: 2014-12-11
 * 上午10:16:43 Description: 权限控制
 * 
 * @author ldw
 * @version 1.0
 *********************************************** 
 */
public class LoginFilter implements Filter {
	private static Logger log = Logger.getLogger(LoginFilter.class);
	private List<String> filterRoot;
	private List<String> noFilterRoot;
	private static SysModuleServiceI sysModuleService;
	
	static {
		sysModuleService = OnlineListner.getCtx().getBean(SysModuleServiceI.class);
	}
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		SysUser user = (SysUser) session.getAttribute("userBO");//获取用户对象
		String requestPath = RequestUtil.getRequestPath(request);
		log.info("当**前**访**问**地**址："+requestPath);
		String path = request.getContextPath();// 本地前缀
		String loginUrl = "login.do?loginUI";//登录url

		String uri = request.getRequestURI();// 访问的url

		if (!"".equals(path)) {
			uri = uri.substring(uri.indexOf("/", 1));//过滤字符
			
		}
		
		if(!filterRoot.contains(uri) && !noFilterRoot.contains(requestPath)) {
			//1.判断如果不用过滤就直接进入该页面
			filterChain.doFilter(request, response);
		} else {
			if(user == null) {
				//2.判断用户是否登陆或者sessuin过期跳转登录页面
				session.setAttribute("userWarn", "用户未登录或者sessuin过期");
				response.sendRedirect(path + "/" + loginUrl);
				return ;
			} else {
				requestPath = requestPath.substring(requestPath.indexOf("/", 0));
				if(noFilterRoot.contains(requestPath)) {
					//3.判断是否匹配权限过滤才能访问的页面
					List<String> modules = sysModuleService.findPathListByUserId(user.getUserId());
					if(!modules.contains(requestPath)) {
						session.setAttribute("exceptionMessage", "您没有权限浏览本页面！");
						response.sendRedirect(path + "/pages/error/exceptionMessage.jsp");
					} else {
						filterChain.doFilter(request, response);
					}
				} else if(filterRoot.contains(uri)) {
					//4.判断是否需要登录才能访问的页面
					filterChain.doFilter(request, response);
				}  
			}
		}
	}
	
	/**
	 * (non-Javadoc)
	 * Title: init
	 * Description: 初始化需要过滤的url
	 * Created On: 2015-3-23 上午9:52:39
	 * @author ldw 
	 * @param config
	 * @throws ServletException
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		log.info("******************************LoginFilter过滤器初始化");
		String xmlPath = config.getServletContext().getRealPath("/WEB-INF/classes/net/framework/interceptors/");
		filterRoot = new ArrayList<String>();
		noFilterRoot = new ArrayList<String>();
		try {
			filterRoot = createArrayList(filterRoot, xmlPath+"/filterList.xml");
			noFilterRoot = createArrayList(noFilterRoot, xmlPath+"/noFilterList.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> createArrayList(List<String> root, String path) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File(path));
		Element ele = doc.getRootElement();
		List list = ele.elements();
		for (int i = 0; i < list.size(); i++) {
			Element element = (Element)list.get(i);
			String s = element.getTextTrim();
			root.add(s);
		}
		
		return root;
	}
}
