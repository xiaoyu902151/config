package com.bxsurvey.login.controller;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bxsurvey.sys.module.model.SysModule;
import com.bxsurvey.sys.user.model.SysModuleTemp;
import com.bxsurvey.sys.user.model.SysUser;
import com.bxsurvey.sys.user.service.SysUserServiceI;
@Controller
@RequestMapping("/home")
public class HomeController {
	private static final String HOME_UI = "/home/home";// 
	private static final String NORTH_UI = "/home/layout/north";// 
	@Autowired
	private SysUserServiceI sysUserService;
	
	@RequestMapping(params = "homeUI")
	public ModelAndView homeUI(HttpServletRequest request) {
		ModelAndView md = new ModelAndView();
		HttpSession session = request.getSession();
		SysUser user = (SysUser)session.getAttribute("userBO");
		if(user == null) {
			md.setViewName("redirect:login.do?loginUI");
			return md;
		}
		Integer userId = user.getUserId();
		List<SysModule>  northModule = sysUserService.findUserAuthorityByType(userId,new Integer(2));
		md.addObject("northModules", northModule);
		md.addObject("selectModules", 11);
		md.addObject("user", user);
		md.setViewName(HOME_UI);
		return md;
	}
	
	@RequestMapping(params = "northUI")
	public String northUI(HttpServletRequest request) {
		/*ModelAndView md = new ModelAndView();
		
		List<SysModule> northModule = new ArrayList<SysModule>();
		SysModule m1 = new SysModule("主页","/home.do?homeUI");
		northModule.add(m1);
		SysModule m2 = new SysModule("后台管理","/menu.do?adminUI");
		northModule.add(m2);
		md.addObject("northModules", northModule);
		md.setViewName(NORTH_UI);*/
		/*if(this.getLoginUser() == null) {
			md.setViewName("redirect:/login.do?loginUI");
		} else {
			SysUser user = (SysUser)request.getSession().getAttribute("userBO");
			List<SysModule> northModule=  moduleDao.findNorthModule(user.getUserId());
			md.addObject("northModules", northModule);
			md.setViewName("/pages/home/home");
		}*/
		return NORTH_UI;
	}
}
