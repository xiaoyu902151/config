package cn.tarena.ht.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.UserService;
import cn.tarena.ht.tool.MD5HashPassword;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/toLogin.action")
	public String toLogin(){
		
		//跳转到登陆页面
		return "/sysadmin/login/login";
	}
	
	
	/**
	 * 1.获取用户输入的用户名和密码
	 * 2.获取subject对象进行登陆操作
	 * 3.通过令牌将用户名和密码进行包装
	 * 4.捕获异常信息,告知用户
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/login.action")
	public String login(String userName,String password,Model model,HttpSession httpSession){
		//1.判断用户名和密码是否是有效数据
		if(StringUtils.isEmpty(userName)|| StringUtils.isEmpty(password)){
			
			//提示用户名或密码不能为空
			model.addAttribute("errorInfo", "用户名或密码不能为空");
			
			//转向到登陆页面
			return "/sysadmin/login/login";
		}
		
		//2.获取subject对象     表示"用户"
		Subject subject = SecurityUtils.getSubject();
		
		//3.将用户名和密码进行包装
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		
		//4捕获异常信息
		try {
			//用户登录
			subject.login(token);
			
			//获取用户信息
			User user = (User) subject.getPrincipal();
			httpSession.setAttribute("userSession", user);
			return "redirect:/home.action";
			
		} catch (AuthenticationException e) {
			e.printStackTrace();
			model.addAttribute("errorInfo", "用户名或密码错误");
			
			//转向到登陆页面
			return "/sysadmin/login/login";
		}
	}
	
	
	
	/*@RequestMapping("/login.action")
	public String login(String userName,String password,Model model,HttpSession httpSession){
		
		//1.判断用户名和密码是否是有效数据
		if(StringUtils.isEmpty(userName)|| StringUtils.isEmpty(password)){
			
			//提示用户名或密码不能为空
			model.addAttribute("errorInfo", "用户名或密码不能为空");
			
			//转向到登陆页面
			return "/sysadmin/login/login";
		}
		
		//通过加密算法进行加密
		password = MD5HashPassword.getMd5Hash(password, userName);
		
		//2.根据用户名和密码查询用户对象
		User user  = userService.findUserByUP(userName,password);
		
		//3.判断user对象是否为null
		if( user==null){
			//说明用户名和密码错误
			model.addAttribute("errorInfo", "用户名或密码错误");
			//转向到登陆页面
			return "/sysadmin/login/login";
		}
		
		//4证明用户名和密码正确  将user对象存储到Session域中
		httpSession.setAttribute("userSession", user);
		return "redirect:/home.action";
	}
	*/
	
	//系统的登出操作
	@RequestMapping(" /logout")
	public String logout(HttpSession httpSession){
		
		httpSession.removeAttribute("userSession");
		return "/sysadmin/login/login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
