package com.bxsurvey.login.controller;

import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.framework.base.controller.BaseController;
import net.framework.httpModel.GenerateImg;
import net.framework.httpModel.Json;
//import net.framework.httpModel.SysMenuBO;
import net.framework.utils.Encrypt;
import net.framework.utils.ExceptionUtil;
import net.framework.utils.RequestUtil;
import net.framework.utils.json.JsonUtil;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxsurvey.login.service.LoginServiceI;
import com.bxsurvey.sys.module.dao.SysModuleDao;
import com.bxsurvey.sys.role.dao.SysRoleDao;
import com.bxsurvey.sys.role.model.SysRole;
import com.bxsurvey.sys.role.service.SysRoleServiceI;
import com.bxsurvey.sys.user.dao.SysUserDao;
import com.bxsurvey.sys.user.model.SysUser;

/**
 * 用户操作
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	private static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private LoginServiceI loginService;
	@Autowired
	private SysRoleServiceI sysRoleService;
	@Autowired
	private SysModuleDao moduleDao;
	
	private static final String LOGIN_UI = "login/login";// 登录页面
	
	/**
	 * 
	 * Title: loginUI Description: 进入后台登录UI Created On: 2015-1-12 上午9:31:46
	 * 
	 * @author ldw
	 * @return
	 */
	@RequestMapping(params = "loginUI")
	public String loginUI() {
		return LOGIN_UI;
	}

	/**
	 * 
	 * Title: getImg Description: 获取验证码图片 Created On: 2014-12-18 上午10:14:26
	 * 
	 * @author ldw
	 * @param request
	 * @param response
	 * @throws Exception
	 */
/*	@RequestMapping(params = "getImg")
	public void getImg(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 每次创建session前首先清除旧session
		// 清空旧session
		request.getSession().invalidate();
		// 获取cookie，并让 cookie过期
		Cookie cookie = request.getCookies()[0];
		cookie.setMaxAge(0);
		// 获取Session
		HttpSession session = request.getSession();
		// 设置响应内容
		response.setContentType("image/jpeg");
		// 获取输出流
		ServletOutputStream sos = response.getOutputStream();
		// 设置浏览器不要缓存此图片
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 将图片数据输出到response对象
		GenerateImg generateImg = new GenerateImg();
		sos.write(generateImg.getImg(session));
		sos.close();
	}*/

	/**
	 * 后台登录
	 * 
	 * @return
	 */
/*	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public ModelAndView login(String name, String pwd, String checkCode, HttpServletRequest request) {
		ModelAndView md = new ModelAndView();
		HttpSession session = request.getSession();
		String loginName = name.trim();
		// 过滤用户名特殊字符
		String specialCharacter = "[@#$%^&()+|{}':;',\\[\\]<>/=.]";
		if (null != loginName && !"".equals(loginName)) {
			String checkUsername = loginName;
			try {
				checkUsername = URLDecoder.decode(
						URLDecoder.decode(checkUsername, "UTF-8"), "UTF-8");
			} catch (Exception e) {
				logger.info("转码异常!");
			}
			Pattern pattern = Pattern.compile(specialCharacter);
			Matcher matcher = pattern.matcher(checkUsername);
			// 对value过滤特殊字符
			if (matcher.find()) {
				md.addObject("usernameErrMsg", "用户名有特殊字符，请重新输入");
				md.setViewName(LOGIN_UI);
				return md;
			}
		}
		// 过滤验证码特殊字符
		if (null != checkCode && !"".equals(checkCode)) {
			String checkVerifyCode = checkCode;
			try {
				checkVerifyCode = URLDecoder.decode(
						URLDecoder.decode(checkVerifyCode, "UTF-8"), "UTF-8");
			} catch (Exception e) {
				logger.info("转码异常!");
			}
			Pattern pattern = Pattern.compile(specialCharacter);
			Matcher matcher = pattern.matcher(checkVerifyCode);
			// 对value过滤特殊字符
			if (matcher.find()) {
				md.addObject("usernameErrMsg", "验证码有特殊字符，请重新输入");
				md.setViewName(LOGIN_UI);
				return md;
			}
		}
		// 判断登录密码验证码是否为空
		if (!validateField(loginName, pwd, checkCode, request)) {
			md.setViewName(LOGIN_UI);
			return md;
		}

		SysUser user = new SysUser();
		user = userDao.findUserByName(loginName);

		//Integer loginCount = null;// 登录失败次数

		if (user == null) {
			md.addObject("usernameErrMsg", "用户不存在，请重新登录");
			md.setViewName(LOGIN_UI);
			return md;
		} else {
			if (user.getValidity().equals("2")) {
				md.addObject("usernameErrMsg", "账户已停用，请联系管理员");
				md.setViewName(LOGIN_UI);
				return md;
			}

			String pwd2 = "";
			if (StringUtils.isNotBlank(pwd)) {
				pwd2 = Encrypt.e(pwd);
			}

			if (StringUtils.isNotBlank(pwd2) && pwd2.equals(user.getPassword())) {
				session.setAttribute("userBO", user);
				session.setMaxInactiveInterval(1000);
				md.setViewName("redirect:../home.do?homeUI");
				return md;
			} else {
				md.addObject("usernameErrMsg", "帐户名或登录密码不正确，请重新输入");
				md.setViewName(LOGIN_UI);
				return md;
			}
		}
	}*/
	
	
	/**
	 * 后台异步登录
	 * @author wzr
	 * @param name
	 * @param pwd
	 * @param checkCode
	 * @param request
	 * @return
	 */
	@RequestMapping(params="check", method = RequestMethod.POST)
	@ResponseBody
	public Json login(String name, String pwd, String checkCode, HttpServletRequest request) {
		Json j = new Json();
		HttpSession session = request.getSession();
		String loginName = name.trim();
		// 过滤用户名特殊字符
		String specialCharacter = "[@#$%^&()+|{}':;',\\[\\]<>/=.]";
		if (null != loginName && !"".equals(loginName)) {
			String checkUsername = loginName;
			try {
				checkUsername = URLDecoder.decode(
						URLDecoder.decode(checkUsername, "UTF-8"), "UTF-8");
			} catch (Exception e) {
				logger.info("转码异常!");
			}
			Pattern pattern = Pattern.compile(specialCharacter);
			Matcher matcher = pattern.matcher(checkUsername);
			// 对value过滤特殊字符
			if (matcher.find()) {
				j.setMsg("用户名有特殊字符，请重新输入");
				j.setSuccess(false);
				return j;
			}
		}
		// 过滤验证码特殊字符
		if (null != checkCode && !"".equals(checkCode)) {
			String checkVerifyCode = checkCode;
			try {
				checkVerifyCode = URLDecoder.decode(
						URLDecoder.decode(checkVerifyCode, "UTF-8"), "UTF-8");
			} catch (Exception e) {
				logger.info("转码异常!");
			}
			Pattern pattern = Pattern.compile(specialCharacter);
			Matcher matcher = pattern.matcher(checkVerifyCode);
			// 对value过滤特殊字符
			if (matcher.find()) {
				j.setMsg("验证码有特殊字符，请重新输入");
				j.setSuccess(false);
				return j;
			}
		}
		// 判断登录密码验证码是否为空
		if (!validateField(loginName, pwd, checkCode, j)) {
			return j;
		}

		SysUser user = loginService.findUserByName(loginName);

		//Integer loginCount = null;// 登录失败次数

		if (user == null) {
			j.setMsg("用户不存在，请重新登录");
			j.setSuccess(false);
			return j;
		} else {
			if (user.getValidity().equals("2")) {
				j.setMsg("账户已停用，请联系管理员");
				j.setSuccess(false);
				return j;
			}

			String pwd2 = "";
			if (StringUtils.isNotBlank(pwd)) {
				pwd2 = Encrypt.e(pwd);
			}

			if (StringUtils.isNotBlank(pwd2) && pwd2.equals(user.getPassword())) {
				SysRole sysRole= sysRoleService.getRoleByUserId(user.getUserId());
				session.setAttribute("sysRole", sysRole);
				session.setAttribute("userBO", user);
				session.setMaxInactiveInterval(1000);
				j.setSuccess(true);
				return j;
			} else {
				j.setMsg("密码不正确，请重新登录");
				j.setSuccess(false);
				return j;
			}
		}
	}


	/**
	 * 
	 * Title: validateField Description: 验证登录 Created On: 2014-12-18 下午2:59:06
	 * 
	 * @author ldw
	 * @param username
	 * @param password
	 * @param checkcode
	 * @param request
	 * @return
	 */
//	public boolean validateField(String username, String password,
//			String checkcode, HttpServletRequest request) {
//		Boolean flag = true;
//		HttpSession session = request.getSession();
//		if ((username == null || username.trim().length() == 0)) {
//			session.setAttribute("usernameErrMsg", "用户名不能为空"); // 1 用户名不能为空
//			flag = false;
//		}
//		if (password == null || password.trim().length() == 0) {
//			session.setAttribute("usernameErrMsg", "密码不能为空"); // 1 密码不能为空
//			flag = false;
//		}
//		if (checkcode == null || checkcode.trim().length() == 0) {
//			session.setAttribute("usernameErrMsg", "验证码不能为空"); // 1 验证码不能为空
//			flag = false;
//		} else {
//			String sessionCode = (String) request.getSession().getAttribute(
//					"check_code");
//			if (!checkcode.equalsIgnoreCase(sessionCode)) {
//				session.setAttribute("usernameErrMsg", "验证码不正确");// 2 验证码不正确
//				flag = false;
//			}
//		}
//		return flag;
//	}
	
	/**
	 * 异步登陆时，校验登陆是否为空
	 * @author wzr
	 * @param username
	 * @param password
	 * @param checkcode
	 * @param json
	 * @return
	 */
	public boolean validateField(String username, String password,
			String checkcode, Json json) {
		if ((username == null || username.trim().length() == 0)) {
			json.setMsg("用户名不能为空"); // 1 用户名不能为空
			return false;
		}
		if (password == null || password.trim().length() == 0) {
			json.setMsg("密码不能为空");// 1 密码不能为空
			return false;
		}
	/*	if (checkcode == null || checkcode.trim().length() == 0) {
			json.setMsg("验证码不能为空");// 1 验证码不能为空
			return false;
		} else {
			String sessionCode = (String) request.getSession().getAttribute(
					"check_code");
			if (!checkcode.equalsIgnoreCase(sessionCode)) {
				json.setMsg("验证码不正确");// 2 验证码不正确
				return false;
			}
		}*/
		return true;
	}

	/**
	 * 
	 * Title: lotout Description: 用户注销 Created On: 2014-12-8 上午9:10:39
	 * 
	 * @author ldw
	 * @return*/
	@RequestMapping(params = "logout")
	public ModelAndView logout(HttpServletRequest request) {
		
		ModelAndView md = new ModelAndView();
		request.getSession().invalidate();
		Cookie[] cookies = request.getCookies();
		if (cookies != null || cookies.length > 0) {
			Cookie c = cookies[0];
			c.setMaxAge(0);
		}
		md.setViewName("login/logout");

		return md;
	}
	
	/**
	 * 
	 * Title: pwdChange Description: 密码修改 Created On: 2015-1-12 上午10:18:25
	 * 
	 * @author ldw
	 * @param request
	 * @return
	 */
	/*
	@RequestMapping(params = "pwdChange")
	@ResponseBody
	public JSONObject pwdChange(HttpServletRequest request) {
		Json json = new Json();
		String oldPwd = request.getParameter("oldpwd");
		String newPwd = request.getParameter("newpwd");
		HttpSession session = request.getSession();
		SysUser user = (SysUser) session.getAttribute("userBO");

		json.setSuccess(true);
		json.setMsg("密码修改成功！");
		if (oldPwd != null || !oldPwd.equals("")) {
			if (user.getPassword().equals(Encrypt.e(oldPwd))) {
				user.setPassword(Encrypt.e(oldPwd));
				userDao.save(user);
			} else {

			}
		} else {
			json.setSuccess(false);
			json.setMsg("原密码不能为空！");
		}

		return json.toJSONObject();
	}
	 */
	/**
	 * 
	 * @Title: logoutPre 
	 * @Description: 注销（退出登录）
	 * @param @param request
	 * @param @return 
	 * @return JSONObject 
	 * @throws
	 */
	/*@RequestMapping("logout")
	@ResponseBody
	public Json logoutPre(HttpServletRequest request) {
		
		request.getSession().invalidate();
		Cookie[] cookies = request.getCookies(); 
		if(cookies != null && cookies.length > 0) { 
			Cookie c = cookies[0]; c.setMaxAge(0); 
		}
		return JsonUtil.GenerateSuccessJson("注销成功！");
	}*/

	
}
