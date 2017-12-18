package com.bxsurvey.sys.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.framework.base.controller.BaseController;
import net.framework.httpModel.Json;
import net.framework.httpModel.PageResults;
import net.framework.utils.Encrypt;
import net.framework.utils.ExceptionUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxsurvey.sys.module.model.SysModule;
import com.bxsurvey.sys.role.model.SysRole;
import com.bxsurvey.sys.user.model.SysUser;
import com.bxsurvey.sys.user.model.SysUserRole;
import com.bxsurvey.sys.user.model.SysUserRoleId;
import com.bxsurvey.sys.user.service.SysUserServiceI;

/**
 * 
 ***********************************************
 * Copyright (c) 2014 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.controller.BxSysUserController.java 
 * Created On: 2014-10-15 上午10:48:30
 * Description: 用户表 view控制器
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Controller
@RequestMapping("/admin/user")
public class SysUserController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);
	@Autowired
	private SysUserServiceI sysUserService;
	
	/*
	 * 添加
	 */
	@RequestMapping(params="save",produces ="text/html;charset=utf-8")
	@ResponseBody
	public Json save(HttpServletRequest request) {
		Json j = new Json();
		try {
			SysUser sysUser = getObject(SysUser.class, "sysUser", request);
			sysUser.setRegDate(new Date());
			SysUser user = (SysUser) request.getSession().getAttribute("userBO");//获取用户对象
			sysUser.setSysUser(user);
			SysUser existUser= sysUserService.save(sysUser);
			if(existUser ==null){
				j.setSuccess(true);
				j.setMsg("添加成功!");
			}else{
				j.setSuccess(false);
				j.setMsg("账号已存在!");
			}
			
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("添加失败!");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	/*
	 * 重置密码
	 */
	@RequestMapping(params="resetPasswork",produces ="text/html;charset=utf-8")
	@ResponseBody
	public Json resetPasswork(HttpServletRequest request,String oldPasswork,String newPasswork,String userId) {
		Json j = new Json();
		try {	
			SysUser sysUser = sysUserService.findById(userId);//获取用户对象
			if (oldPasswork != null) {
				oldPasswork= Encrypt.e(oldPasswork);// 原密码加密
			}
			if (newPasswork != null) {
				newPasswork= Encrypt.e(newPasswork);// 新密码加密
			}
			if(oldPasswork.equalsIgnoreCase(sysUser.getPassword())){
				sysUser.setPassword(newPasswork);
				sysUserService.update(sysUser);
				j.setSuccess(true);
				j.setMsg("密码修改成功!");
			}else{
				j.setSuccess(false);
				j.setMsg("原密码错误!");
			}
				

		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("修改失败!");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	/**
	 * 编辑
	 * @param bxSysOgran
	 * @return
	 */
	@RequestMapping(params="update",produces ="text/html;charset=utf-8")
	@ResponseBody
	public Json update(HttpServletRequest request) {
		Json j = new Json();
		try {
			SysUser sysUser = getObject(SysUser.class, "sysUser", request);
			SysUser user = (SysUser) request.getSession().getAttribute("userBO");//获取用户对象
			sysUser.setSysUser(user);
			sysUserService.update(sysUser);
			j.setSuccess(true);
			j.setMsg("修改成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("修改失败!");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(params="delete")
	@ResponseBody
	public Json delete(String ids) {
		Json j = new Json();
		try {
			SysUser user = (SysUser) request.getSession().getAttribute("userBO");//获取用户对象
			String[] idsArray= ids.split(",");
			for(String id:idsArray){
				if(user.getUserId().toString().equals(id)){
					j.setSuccess(false);
					j.setMsg("不能删除自身账号!");
					return j;
				}
			}
			sysUserService.deleteById(ids);
			j.setSuccess(true);
			j.setMsg("删除成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败!");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	/**
	 * 列表查询
	 * @return
	 */
	@RequestMapping(params="searchGrid")
	@ResponseBody
	public JSONObject searchGrid(String pageNumber, String rowNumber,String sortName,String sortOrder, HttpServletRequest request) {
		
		int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
		int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
		String orderBy = sortName == null  ? "regDate":sortName;
		String order = sortOrder == null  ? PageResults.ASC:sortOrder;
		
		PageResults<SysUser> pageResults = new PageResults<SysUser>();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		pageResults.setOrderBy(orderBy);
		pageResults.setOrder(order);
	
		try {
			SysUser sysUser = getObject(SysUser.class, "sysUser", request);
			SysUser user=(SysUser) request.getSession().getAttribute("userBO");
			sysUser.setSysUser(user);
			pageResults = sysUserService.getListForJson(pageResults, sysUser);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ajaxGrid(pageResults);
	}
	/**
	 * 角色授权
	 * @return
	 */
	@RequestMapping(params = "saveRoles")
	@ResponseBody
	public Json putRoles(String params) {
		Json j = new Json();
		try {
			List<SysUserRole> sysUserRoles = new ArrayList<SysUserRole>();
			JSONArray jsonArray = JSONArray.fromObject(params) ;
			List<SysUserRoleId> list = JSONArray.toList(jsonArray,SysUserRoleId.class);
			for(SysUserRoleId id:list){
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setId(id);
				sysUserRoles.add(sysUserRole);
			}
			sysUserService.putRoles(sysUserRoles);
			j.setSuccess(true);
			j.setMsg("授权成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("授权失败!");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	/**
	 * 
	 * Title: getRoleList
	 * Description: 获取可选角色列表和已选角色列表
	 * Created On: 2014-12-1 下午1:34:28
	 * @author ldw 
	 * @param userId
	 * @return
	 */
	@RequestMapping(params = "getRoleList")
	@ResponseBody
	public Json getRoleList(String userId) {
		Json j = new Json();
		Map<String,Object> data = new HashMap<String,Object>();
		try {
			if(StringUtils.isNotBlank(userId)) {
				data.put("userId", userId);
				List<SysRole> roleList = sysUserService.getAllRole();
				List<SysRole> userRoleList = sysUserService.getBxSysRoleListByUserId(userId);
				if(roleList != null && !roleList.equals("")) {
					data.put("roleList", roleList);
				}
				if(userRoleList != null && !userRoleList.equals("")) {
					data.put("userRoleList", userRoleList);
				}
				
				JSONObject ret = renderJsonForMap(data);
				j.setObj(ret);
				j.setSuccess(true);
				j.setMsg("查询成功!");
			}
			
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("查询失败!");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	/**
	 * 
	 * Title: getAll
	 * Description: 获取所有用户数据
	 * Created On: 2015-4-7 下午2:54:27
	 * @author ldw 
	 * @return
	 */
	@RequestMapping(params = "getAll")
	@ResponseBody
	public JSONObject getAll() {
		List<SysUser> list = sysUserService.getAll();
		return renderJson(list);
	}
	
	@RequestMapping(params = "updateView")
	@ResponseBody
	public ModelAndView updateView(String userId){
		ModelAndView md = new ModelAndView();
		SysUser sysUser = sysUserService.findById(userId);
		md.addObject("sysUser",sysUser);
		md.setViewName("admin/user/updateView");
		return md;
	}
	
	@RequestMapping(params = "addView")
	@ResponseBody
	public ModelAndView addView(){
		ModelAndView md = new ModelAndView();
		md.setViewName("admin/user/addView");
		return md;
	}
	
	@RequestMapping(params = "roleView")
	@ResponseBody
	public ModelAndView roleView(String userId,HttpServletRequest request){
		ModelAndView md = new ModelAndView();
		Map<String,Object> data = new HashMap<String,Object>();
		try {
			if(StringUtils.isNotBlank(userId)) {
				data.put("userId", userId);
				md.addObject("userId",userId);
			  int sysRoleId=	((SysRole) request.getSession().getAttribute("sysRole")).getRoleId();//得出创建者
				int sysUserId= ((SysUser) request.getSession().getAttribute("userBO")).getUserId();//得出创建者
				List<SysRole> roleList = sysUserService.getLoginRole(sysRoleId,sysUserId);
				List<SysRole> userRoleList = sysUserService.getBxSysRoleListByUserId(userId);
				if(roleList != null && !roleList.equals("")) {
					md.addObject("roleList",roleList);
				}
				if(userRoleList != null && !userRoleList.equals("")) {
					md.addObject("userRoleList",userRoleList);
				}
			}
		}
		catch(Exception e){
			md.addObject("userId",userId);
			md.addObject("roleList",new ArrayList<SysRole>());
			md.addObject("userRoleList",new ArrayList<SysRole>());
		}
		md.setViewName("admin/user/roleView");
		return md;
	}		
	
	@RequestMapping(params = "findUserAuthorityByPID")
	@ResponseBody
	public Json findUserAuthorityByPID(HttpServletRequest request,String pid)
	{
		Json j = new Json();
		HttpSession session = request.getSession();
		SysUser user = (SysUser)session.getAttribute("userBO");
		if(user == null) {
			return j;
		}
		Map<String,Object> data = new HashMap<String,Object>();
		List<SysModule> sysModuleList = sysUserService.findUserAuthorityByPID(user.getUserId(),pid);
		data.put("sysModuleList",sysModuleList);
		JSONObject ret = renderJsonForMap(data);
		j.setObj(ret);
		j.setSuccess(true);		
		return j;
	}
	
	@RequestMapping(params = "findUserAuthorityByBtnId")
	@ResponseBody
	public Json findUserAuthorityByBtnId(HttpServletRequest request,String pid,String bid)
	{
		Json j = new Json();
		HttpSession session = request.getSession();
		SysUser user = (SysUser)session.getAttribute("userBO");
		if(user == null) {
			return j;
		}
		Map<String,Object> data = new HashMap<String,Object>();
		List<SysModule> sysModuleList = sysUserService.findUserAuthorityByBtnId(user.getUserId(),pid,bid);
		if(sysModuleList != null && sysModuleList.size() > 0)
			j.setSuccess(true);	
		else
			j.setSuccess(false);
		return j;
	}
	
	@RequestMapping(params = "getCurrentUser")
	@ResponseBody
	public Json getCurrentUser(HttpServletRequest request)
	{
		Json j = new Json();
		HttpSession session = request.getSession();
		SysUser user = (SysUser)session.getAttribute("userBO");
		if(user == null) 
			j.setSuccess(false);
		else{
			j.setSuccess(true);
			j.setObj(user);
		}
		return j;
	}
}
