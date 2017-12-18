package com.bxsurvey.sys.operateLog.controller;

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
import com.bxsurvey.sys.operateLog.service.SysOperateLogServiceI;
import com.bxsurvey.sys.role.model.SysRole;
import com.bxsurvey.sys.user.model.SysLog;
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
@RequestMapping("/admin/operateLog")
public class OperateLogController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(OperateLogController.class);
	@Autowired
	private SysOperateLogServiceI sysOperateLogService;
	
	/**
	 * 列表查询
	 * @return
	 */
	@RequestMapping(params="searchGrid")
	@ResponseBody
	public JSONObject searchGrid(String pageNumber, String rowNumber,String sortName,String sortOrder, HttpServletRequest request) {
		
		int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
		int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
		String orderBy = sortName == null  ? "operateTime":sortName;
		String order = sortOrder == null  ? PageResults.DESC:sortOrder;
		
		PageResults<SysLog> pageResults = new PageResults<SysLog>();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		pageResults.setOrderBy(orderBy);
		pageResults.setOrder(order);
	
		try {
			SysLog sysUser = getObject(SysLog.class, "user", request);
			pageResults = sysOperateLogService.getListForJson(pageResults, sysUser);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ajaxGrid(pageResults);
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
			sysOperateLogService.deleteById(ids);
			j.setSuccess(true);
			j.setMsg("删除成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败!");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
}
