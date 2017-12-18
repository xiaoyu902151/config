package net.framework.utils;

import javax.servlet.http.HttpServletRequest;

import com.bxsurvey.sys.user.model.SysUser;

/**
 * request工具类
 * 
 * @author donic
 * 
 */
public class RequestUtil {
	public final static  String USER_TOURIST = "tourist";//游客角色
	public final static  String USER_MANAGER = "manager";//企业角色
	public final static  String USER_ADMIN = "admin";//管理员角色
	
	
	/** 
	 * @Title: getRootPath 
	 * @Description: 获取项目根路径
	 * @param @param request
	 * @param @return 
	 * @return String 
	 * @throws 
	 */
	public static String getRootPath(HttpServletRequest request){
		return request.getSession().getServletContext().getContextPath();
	}
	
	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath=null;
		if(request.getQueryString()==null){
			requestPath = request.getRequestURI();
		}else{
			requestPath = request.getRequestURI() + "?" + request.getQueryString();
		}
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length());// 去掉项目路径
		return requestPath;
	}

	/**
	 * 获取当前登录用户
	 * @param request
	 * @return
	 */
	
	public static SysUser getCurrentUser(HttpServletRequest request){
		return (SysUser) request.getSession().getAttribute("userBO");
	}
	
	/**
	 * 
	 * Title: getSessionObject
	 * Description: 获取session里面的对象
	 * Created On: 2015-2-2 上午10:13:20
	 * @author ldw 
	 * @param request
	 * @param name 键名
	 * @return
	 */
	public static Object getSessionObject(HttpServletRequest request, String name) {
		return request.getSession().getAttribute(name);
	}
}
