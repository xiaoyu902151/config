package com.bxsurvey.login.controller;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/menu")
public class MenuController {
	private static final String ADMIN_UI = "/admin/admin_layout";// 
	@Autowired
	private SysUserServiceI sysUserService;

	@RequestMapping(params = "adminUI")
	public ModelAndView adminUI(HttpServletRequest request) {
		ModelAndView md = new ModelAndView();
		HttpSession session = request.getSession();
		SysUser user = (SysUser)session.getAttribute("userBO");
		if(user == null) {
			md.setViewName("redirect:login.do?loginUI");
			return md;
		}
		Integer userId = user.getUserId();
		List<SysModule>  northModule = sysUserService.findUserAuthorityByType(userId,new Integer(2));
		List<SysModule>  sysModuleList = sysUserService.findUserAuthorityByPID(user.getUserId(),"12");
		md.addObject("northModules", northModule);
		md.addObject("sysModuleList", sysModuleList);
		md.addObject("selectModules", 12);
		md.addObject("user", user);
		md.setViewName(ADMIN_UI);
		return md;
	}
	
	
	@RequestMapping(params = "drillUI")
	public ModelAndView drillUI(HttpServletRequest request) {
		ModelAndView md = new ModelAndView();
		HttpSession session = request.getSession();
		SysUser user = (SysUser)session.getAttribute("userBO");
		if(user == null) {
			md.setViewName("redirect:login.do?loginUI");
			return md;
		}
		Integer userId = user.getUserId();
		List<SysModule>  northModule = sysUserService.findUserAuthorityByType(userId,new Integer(2));
		List<SysModule>  sysModuleList = sysUserService.findUserAuthorityByPID(user.getUserId(),"12");
		md.addObject("northModules", northModule);
		md.addObject("sysModuleList", sysModuleList);
		md.addObject("selectModules", 13);
		md.addObject("user", user);
		md.setViewName("/ems/emsHome");
		return md;
	}
	
	@RequestMapping(params = "comdUI")
	public ModelAndView comdUIUI(HttpServletRequest request,String params) {
		ModelAndView md = new ModelAndView();
		HttpSession session = request.getSession();
		SysUser user = (SysUser)session.getAttribute("userBO");
		if(user == null) {
			md.setViewName("redirect:login.do?loginUI");
			return md;
		}
		try{
			Integer userId = user.getUserId();
			List<SysModule>  northModule = sysUserService.findUserAuthorityByType(userId,new Integer(2));
			List<SysModule>  sysModuleList = sysUserService.findUserAuthorityByPID(user.getUserId(),"12");
			md.addObject("northModules", northModule);
			md.addObject("sysModuleList", sysModuleList);
			md.addObject("selectModules", 11);
			
			byte[] data = params.getBytes("iso8859-1");
			String pdata = new String (data,"UTF-8");
			
			params.replaceAll("\"", "\\\"");
			md.addObject("params",pdata);
			md.addObject("user", user);
			md.setViewName("/comd/comdHome");
		}catch(Exception e){
			
		}
		return md;
	}
	
	@RequestMapping(params = "supportUI")
	public ModelAndView supportUI(HttpServletRequest request) {
		ModelAndView md = new ModelAndView();
		HttpSession session = request.getSession();
		SysUser user = (SysUser)session.getAttribute("userBO");
		if(user == null) {
			md.setViewName("redirect:login.do?loginUI");
			return md;
		}
		Integer userId = user.getUserId();
		List<SysModule>  northModule = sysUserService.findUserAuthorityByType(userId,new Integer(2));
		List<SysModule>  sysModuleList = sysUserService.findUserAuthorityByPID(user.getUserId(),"16");
		md.addObject("northModules", northModule);
		md.addObject("sysModuleList", sysModuleList);
		md.addObject("selectModules", 16);
		md.addObject("user", user);
		md.setViewName("/support/support_layout");
		return md;
	}
}
