package com.bxsurvey.danger.DangerTeam.controller;

import java.io.IOException;
import java.math.BigDecimal;
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

import com.bxsurvey.danger.DangerTeam.model.DangerTeam;
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
@RequestMapping("/danger/team")
public class DangerTeamController extends BaseController {
	private static Logger log = Logger.getLogger(DangerTeamController.class);
	@Autowired
	private DangerTeamServiceI dangerTeamService;
//	@Autowired
//	private SysDepartServiceI sysDepartService;
//	@Autowired
//	private SysDepartPersonServiceI sysDepartPersonService;
	
	private static String viewUrl = "pages/danger/team/view";//数据查看
	private static String list = "pages/danger/team/list";//数据列表
	/*
	 * 返回新增页面
	 */
//	@RequestMapping(params="addView")
//	public ModelAndView addView() {
//		ModelAndView md = new ModelAndView(viewUrl);
//		return md;
//	}
	/*
	 * 返回list页面
	 */
//	@RequestMapping(params="list")
//	public ModelAndView list() {
//		ModelAndView md = new ModelAndView(list);
//		List<SysDepart> list = sysDepartService.findAll();
//		md.addObject("departs", list);
//		return md;
//	}
	/*
	 * 返回编辑页面
	 */
//	@RequestMapping(params="updateView")
//	public ModelAndView updateView(HttpServletRequest request) {
//		ModelAndView md = new ModelAndView();
//		try {
//			DangerTeam team = getObject(DangerTeam.class, "team", request);
//			team = dangerTeamService.getObjectById(team.getTeamId());
//			if(team.getDepartId() != null) {
//				SysDepart depart = sysDepartService.getObjectById(team.getDepartId());
//				md.addObject("depart", depart);
//			}
//			
//			if(StringUtils.isNotBlank(team.getPersons())) {
//				String[] personIds = team.getPersons().split(",");
//				List<Integer> ids = new ArrayList<Integer>();
//				for(String personId : personIds) {
//					ids.add(Integer.parseInt(personId));
//				}
//				List<SysDepartPerson> persons = sysDepartPersonService.findListInStringIds(ids);
//				md.addObject("persons", persons);
//			}
//			
//			md.addObject("team", team);
//			md.setViewName(viewUrl);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return md;
//	}
	
	/*
	 * 添加
	 */
	@RequestMapping(params="save")
	@ResponseBody
	public Json save(HttpServletRequest request) {
		Json j = new Json();
		try {
			DangerTeam team = getObject(DangerTeam.class, "team", request);
			team.setWriteTime(new Date());
			dangerTeamService.save(team);
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
			DangerTeam team = getObject(DangerTeam.class, "team", request);
			dangerTeamService.update(team);
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
			dangerTeamService.deleteById(ids);
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
		String orderBy = sortName == null  ? "teamId":sortName;
		String order = sortOrder == null  ? PageResults.ASC:sortOrder;

		PageResults<DangerTeam> pageResults = new PageResults<DangerTeam>();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		pageResults.setOrderBy(orderBy);
		pageResults.setOrder(order);
		try {
			DangerTeam team = getObject(DangerTeam.class, "team", request);
			team.setValidity(true);
			pageResults = dangerTeamService.getListForJson(pageResults,team);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ajaxGrid(pageResults);
	}
	
	/**
	 * 
	 * Title: getSuppliesByXY
	 * Description:通过xy来查询周边物资
	 * 接收格式：{"serviceUrl": "", "distance": "2000","objId": "","x": "12363613.311","y": "2447855.513"}
	 * Created On: 2015-7-3 上午10:53:47
	 * @author ldw 
	 * @return
	 */
	@RequestMapping(params ="getTeamsByXY", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public JSONObject getTeamsByXY(HttpServletRequest request,String distance,String x,String y) {
		List<DangerTeam> list = null;
		try {
			double dis = Double.valueOf(distance);			
			BigDecimal bx = new BigDecimal(x);
			BigDecimal by = new BigDecimal(y);
			list = dangerTeamService.getTeamsByXY(dis,bx,by);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return renderJson(list);
	}
	
//	@SuppressWarnings("static-access")
//	@RequestMapping(params="getPersonById")
//	@ResponseBody
//	public JSONArray getPersonBydePartId(HttpServletRequest request)
//	{
//		Integer id=new Integer(request.getParameter("departId"));
//		JsonUtil ju=new JsonUtil();
//		List<DangerTeam> li=dangerTeamService.getPersonByDepartId(id);
//		return ju.toJSONArray(li);
//	}
	
	
	
}
