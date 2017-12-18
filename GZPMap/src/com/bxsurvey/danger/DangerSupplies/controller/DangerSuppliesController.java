package com.bxsurvey.danger.DangerSupplies.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.framework.base.controller.BaseController;
import net.framework.httpModel.Json;
import net.framework.httpModel.PageResults;
import net.framework.utils.ExceptionUtil;
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

//import com.bxsurvey.danger.DangerMt.service.DangerMtServiceI;
import com.bxsurvey.danger.DangerSupplies.model.DangerSupplies;
import com.bxsurvey.danger.DangerSupplies.service.DangerSuppliesServiceI;
import com.bxsurvey.support.plan.service.PlanStandServiceI;
//import com.bxsurvey.danger.DangerZgq.model.DangerZgq;
//import com.bxsurvey.danger.DangerZgq.service.DangerZgqServiceI;
//import com.bxsurvey.sys.depart.model.SysDepart;
//import com.bxsurvey.sys.depart.service.SysDepartServiceI;
//import com.bxsurvey.sys.params.dao.SysParamsDao;
//import com.bxsurvey.sys.params.model.SysParams;
import com.bxsurvey.sys.module.model.SysModule;

/**
 * 
 *********************************************** 
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. All Rights Reserved.
 * FileName：com.bxsurvey.controller.danger.DangerKfdcqController.java Created
 * On: 2015-5-13 下午2:19:37 Description: 应急物资情况view控制器
 * 
 * @author ldw
 * @version 1.0
 *********************************************** 
 */
@Controller
@RequestMapping("/danger/supplies")
public class DangerSuppliesController extends BaseController {
	private static Logger log = Logger
			.getLogger(DangerSuppliesController.class);
	@Autowired
	private DangerSuppliesServiceI dangerSuppliesService;
	
	@Autowired
	private PlanStandServiceI planStandService;
	private static String paramsType = "SUPPLIES";
	private static String viewUrl = "pages/danger/supplies/view";// 数据查看
	private static String list = "pages/danger/supplies/list";// 数据列表

	/*
	 * 返回新增页面
	 */
	@RequestMapping(params = "addView")
	public ModelAndView addView() {
		ModelAndView modelAndView = new ModelAndView(viewUrl);
		return modelAndView;
	}
	/*
	 * 添加
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public Json save(HttpServletRequest request) {
		Json j = new Json();
		try {
			DangerSupplies obj = getObject(DangerSupplies.class, "obj", request);
			obj.setWriteTime(new Date());
			dangerSuppliesService.save(obj);
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
	 * 
	 * @param bxSysOgran
	 * @return
	 */
	@RequestMapping(params = "update")
	@ResponseBody
	public Json update(HttpServletRequest request) {
		Json j = new Json();
		try {
			DangerSupplies obj = getObject(DangerSupplies.class, "obj", request);
			dangerSuppliesService.update(obj);
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
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "delete")
	@ResponseBody
	public Json delete(String ids) {
		Json j = new Json();
		try {
			dangerSuppliesService.deleteById(ids);
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
	 * 
	 * @return
	 */
	@RequestMapping(params = "searchGrid")
	@ResponseBody
	public JSONObject searchGrid(String pageNumber, String rowNumber,String sortName,String sortOrder, 
			HttpServletRequest request) {
		int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
		int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
		String orderBy = sortName == null  ? "suppliesId":sortName;
		String order = sortOrder == null  ? PageResults.ASC:sortOrder;

		PageResults<DangerSupplies> pageResults = new PageResults<DangerSupplies>();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		pageResults.setOrderBy(orderBy);
		pageResults.setOrder(order);
		try {
			DangerSupplies obj = getObject(DangerSupplies.class, "obj", request);
			obj.setValidity(true);
			pageResults = dangerSuppliesService.getListForJson(pageResults,obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ajaxGrid(pageResults);
	}
	/**
	 * 列表查询
	 * 
	 * @return
	 */
	@RequestMapping(params = "searchSuppliesById")
	@ResponseBody
	public JSONObject searchSuppliesById(String suppliesId,HttpServletRequest request) {
		DangerSupplies  ds = null;
		try{
			ds = dangerSuppliesService.getObjectById(new Integer(suppliesId));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return bean2JSONObject(ds);
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
	@RequestMapping(params ="getSuppliesByXY")
	@ResponseBody
	public String getSuppliesByXY(HttpServletRequest request,String distance,String x,String y,String planId,String planType,String level) {
		JSONObject resp = new JSONObject();
		try {
			double dis = Double.valueOf(distance);			
			BigDecimal bx = new BigDecimal(x);
			BigDecimal by = new BigDecimal(y);
			resp = dangerSuppliesService.getSuppliesByXY(dis,bx,by,planId,planType,level);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = resp.toString();
		
		return result;
	}
	
	/**
	 * 
	 * Title: getSuppliesByService
	 * Description: 通过service来查询周边物资
	 * Created On: 2015-7-3 上午10:54:11
	 * @author ldw 
	 * @return
	 */
	@RequestMapping(value = "getSuppliesByService", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getSuppliesByService() {
		JSONArray resp = new JSONArray();
		try {
			String req = IOUtils.toString(request.getInputStream());
			if(StringUtils.isNotBlank(req)) {
				JSONArray array = JSONArray.fromObject(req);
				resp = dangerSuppliesService.getSuppliesByService(array);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp.toString();
	}
	
	/*
	 * Title: getSuppliesByService
	 * Description: 查出所有物资
	 * Created On: 2015-10-9 下午16:15:11
	 * @author czj
	 * @return
	 */
  @RequestMapping(value="getAllSupplies")
  public ModelAndView getAllSupplies(){
	  ModelAndView mav = new ModelAndView();
	  List<DangerSupplies> list = dangerSuppliesService.getAllSupplies();
	  mav.addObject("list",list);
	  return mav;
  }
  
  
 }
