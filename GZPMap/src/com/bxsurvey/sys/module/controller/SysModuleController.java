package com.bxsurvey.sys.module.controller;

import javax.servlet.http.HttpServletRequest;
import net.framework.base.controller.BaseController;
import net.framework.httpModel.Json;
import net.framework.httpModel.PageResults;
import net.framework.utils.ExceptionUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxsurvey.sys.module.model.SysModule;
import com.bxsurvey.sys.module.service.SysModuleServiceI;
import com.bxsurvey.sys.user.model.SysUser;

/**
 * 
 ***********************************************
 * Copyright (c) 2014 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.controller.BxSysUserController.java 
 * Created On: 2014年11月24日 16:36:59
 * Description: 模块表 view控制器
 * @author cqc 
 * @version 1.0
 ***********************************************
 */
@Controller
@RequestMapping("/admin/module")
public class SysModuleController extends BaseController {
	private static Logger logger = Logger.getLogger(SysModuleController.class);
	@Autowired
	private SysModuleServiceI sysModuleService;
	/**
	 * 添加
	 * @param bxSysOgran
	 * @return
	 */
	@RequestMapping(params="save")
	@ResponseBody
	public Json save(HttpServletRequest request) {
		Json j = new Json();
		try {
			SysModule sysModule = getObject(SysModule.class, "sysModule", request);
			sysModuleService.save(sysModule);
			j.setSuccess(true);
			j.setMsg("添加成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("添加失败!");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	
	/**
	 * 编辑
	 * @param bxSysOgran
	 * @return
	 */
	@RequestMapping(params="update")
	@ResponseBody
	public Json update(HttpServletRequest request) {
		Json j = new Json();
		try {
			SysModule sysModule = getObject(SysModule.class, "sysModule", request);
			sysModuleService.update(sysModule);
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
			sysModuleService.deleteById(ids);
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

		PageResults<SysModule> pageResults = new PageResults<SysModule>();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		pageResults.setOrderBy(orderBy);
		pageResults.setOrder(order);
		try {
			SysModule sysModule = getObject(SysModule.class, "sysModule", request);
			pageResults = sysModuleService.getListForJson(pageResults, sysModule);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ajaxGrid(pageResults);
	}
	@RequestMapping(params = "updateView")
	@ResponseBody
	public ModelAndView updateView(String moduleId){
		ModelAndView md = new ModelAndView();
		SysModule sysModule = sysModuleService.findById(moduleId);
		md.addObject("sysModule",sysModule);
		md.setViewName("admin/module/updateView");
		return md;
	}
	
	@RequestMapping(params = "addView")
	@ResponseBody
	public ModelAndView addView(){
		ModelAndView md = new ModelAndView();
		md.setViewName("admin/module/addView");
		return md;
	}
}
