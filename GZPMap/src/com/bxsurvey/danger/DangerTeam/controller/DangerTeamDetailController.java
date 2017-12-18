package com.bxsurvey.danger.DangerTeam.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.framework.base.controller.BaseController;
import net.framework.httpModel.Json;
import net.framework.httpModel.PageResults;
import net.framework.utils.ExceptionUtil;
//import net.framework.utils.JsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxsurvey.danger.DangerSupplies.model.DangerSuppliesDetail;
import com.bxsurvey.danger.DangerTeam.model.DangerTeam;
import com.bxsurvey.danger.DangerTeam.model.DangerTeamDetail;
import com.bxsurvey.danger.DangerTeam.service.DangerTeamDetailServiceI;
//import com.bxsurvey.danger.DangerTeam.model.SeachTeam;
import com.bxsurvey.danger.DangerTeam.service.DangerTeamServiceI;
//import com.bxsurvey.sys.depart.model.SysDepart;
//import com.bxsurvey.sys.depart.service.SysDepartServiceI;
//import com.bxsurvey.sys.departPerson.model.SysDepartPerson;
//import com.bxsurvey.sys.departPerson.service.SysDepartPersonServiceI;
/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.controller.danger.DangerTypeqController.java 
 * Created On: 2015-5-13 下午2:19:37
 * Description: 危险源种类view控制器
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Controller
@RequestMapping("/danger/team/detail")
public class DangerTeamDetailController extends BaseController {
	private static Logger log = Logger.getLogger(DangerTeamDetailController.class);
	@Autowired
	private DangerTeamDetailServiceI dangerTeamDetailService;
//	@Autowired
//	private SysDepartServiceI sysDepartService;
//	@Autowired
//	private SysDepartPersonServiceI sysDepartPersonService;
	
	private static String viewUrl = "pages/danger/team/view";//数据查看
	private static String list = "pages/danger/team/list";//数据列表
	/*
	 * 返回新增页面
	 */

	
	/*
	 * 添加
	 */
	@RequestMapping(params="save")
	@ResponseBody
	public Json save(HttpServletRequest request) {
		Json j = new Json();
		try {
			DangerTeamDetail teamDetail = getObject(DangerTeamDetail.class, "team", request);
			//team.setWriteTime(new Date());
			dangerTeamDetailService.save(teamDetail);
			j.setSuccess(true);
			j.setMsg("添加成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("添加失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
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
			DangerTeamDetail teamDetail = getObject(DangerTeamDetail.class, "team", request);
			dangerTeamDetailService.update(teamDetail);
			j.setSuccess(true);
			j.setMsg("修改成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("修改失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
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
			dangerTeamDetailService.deleteById(ids);
			j.setSuccess(true);
			j.setMsg("删除成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
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
		String orderBy = sortName == null  ? "id":sortName;
		String order = sortOrder == null  ? PageResults.ASC:sortOrder;

		PageResults<DangerTeamDetail> pageResults = new PageResults<DangerTeamDetail>();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		pageResults.setOrderBy(orderBy);
		pageResults.setOrder(order);
		try {
			DangerTeamDetail teamDetail = getObject(DangerTeamDetail.class, "team", request);
//			team.setValidity(true);
			pageResults = dangerTeamDetailService.getListForJson(pageResults,teamDetail);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ajaxGrid(pageResults);
	}
	
	/**
	 * 列表查询
	 * 
	 * @return
	 */
	@RequestMapping(params = "searchGridByTeamId")
	@ResponseBody
	public JSONObject searchGridByTeamId(Integer teamId,String sortName,String sortOrder, 
			HttpServletRequest request) {
		String orderBy = sortName == null  ? "id":sortName;
		String order = sortOrder == null  ? PageResults.ASC:sortOrder;
		List<DangerTeamDetail> list = dangerTeamDetailService.getListByTeamId(teamId, orderBy, order);
		return renderJson(list);
	}
	
	
	@RequestMapping(params = "searchGridNoPage")
	@ResponseBody
	public JSONObject searchGridNoPage(HttpServletRequest request) {
		List<DangerTeamDetail>  list= null;
		try {
			DangerTeamDetail obj = getObject(DangerTeamDetail.class, "obj", request);
			list= dangerTeamDetailService.getListNoPage(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderJson(list);
	}

}
