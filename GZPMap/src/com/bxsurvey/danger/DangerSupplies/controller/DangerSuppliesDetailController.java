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

import com.bxsurvey.comd.ComdEvent.model.ComdEvent;
//import com.bxsurvey.danger.DangerMt.service.DangerMtServiceI;
import com.bxsurvey.danger.DangerSupplies.model.DangerSuppliesDetail;
import com.bxsurvey.danger.DangerSupplies.service.DangerSuppliesDetailServiceI;
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
@RequestMapping("/danger/supplies/detail")
public class DangerSuppliesDetailController extends BaseController {
	private static Logger log = Logger
			.getLogger(DangerSuppliesDetailController.class);
	@Autowired
	private DangerSuppliesDetailServiceI dangerSuppliesDetailService;
	
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
			DangerSuppliesDetail obj = getObject(DangerSuppliesDetail.class, "obj", request);
			obj.setWriteTime(new Date());
			dangerSuppliesDetailService.save(obj);
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
			DangerSuppliesDetail obj = getObject(DangerSuppliesDetail.class, "obj", request);
			dangerSuppliesDetailService.update(obj);
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
			dangerSuppliesDetailService.deleteById(ids);
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
		String orderBy = sortName == null  ? "id":sortName;
		String order = sortOrder == null  ? PageResults.ASC:sortOrder;

		PageResults<DangerSuppliesDetail> pageResults = new PageResults<DangerSuppliesDetail>();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		pageResults.setOrderBy(orderBy);
		pageResults.setOrder(order);
		try {
			DangerSuppliesDetail obj = getObject(DangerSuppliesDetail.class, "obj", request);
			obj.setValidity(true);
			pageResults = dangerSuppliesDetailService.getListForJson(pageResults,obj);
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
	@RequestMapping(params = "searchGridBySuppliesId")
	@ResponseBody
	public JSONObject searchGridBySuppliesId(Integer suppliesId,String sortName,String sortOrder, 
			HttpServletRequest request) {
		String orderBy = sortName == null  ? "id":sortName;
		String order = sortOrder == null  ? PageResults.ASC:sortOrder;
		List<DangerSuppliesDetail> list = dangerSuppliesDetailService.getListBySuppliesID(suppliesId, orderBy, order);
		return renderJson(list);
	}
	
	@RequestMapping(params = "searchGridNoPage")
	@ResponseBody
	public JSONObject searchGridNoPage(HttpServletRequest request) {
		List<DangerSuppliesDetail>  list= null;
		try {
			DangerSuppliesDetail obj = getObject(DangerSuppliesDetail.class, "obj", request);
			list= dangerSuppliesDetailService.getListNoPage(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderJson(list);
	}
}
