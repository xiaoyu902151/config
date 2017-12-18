package com.bxsurvey.sys.video.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxsurvey.danger.DangerTeam.model.DangerTeam;
import com.bxsurvey.danger.DangerZgq.model.DangerZgq;
import com.bxsurvey.sys.depart.model.SysDepart;
import com.bxsurvey.sys.depart.service.SysDepartServiceI;
import com.bxsurvey.sys.video.model.SysVideo;
import com.bxsurvey.sys.video.service.SysVideoServiceI;
import com.bxsurvey.sys.video.vo.SysVideoVO;
import net.framework.base.controller.BaseController;
import net.framework.httpModel.Json;
import net.framework.httpModel.PageResults;
//import net.framework.httpModel.easyui.Datagrid;
import net.framework.utils.ExceptionUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.controller.danger.DangerZgqController.java 
 * Created On: 2015-5-13 下午2:19:37
 * Description: 贮罐区view控制器
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Controller
@RequestMapping("/admin/video")
public class SysVideoController extends BaseController {
	private static Logger log = Logger.getLogger(SysVideoController.class);
	@Autowired
	private SysVideoServiceI sysVideoService;
	@Autowired
	private SysDepartServiceI sysDepartService;
	
	@Autowired
	private static String viewUrl = "pages/admin/video/view";//数据查看
	private static String list = "pages/admin/video/list";//数据列表
	/*
	 * 返回新增页面
	 */
	@RequestMapping(params="addView")
	public ModelAndView addView() {
		ModelAndView modelAndView = new ModelAndView(viewUrl);
		return modelAndView;
	}
	/*
	 * 返回list页面
	 */
	@RequestMapping(params="list")
	public ModelAndView list() {
		ModelAndView md = new ModelAndView(list);
		List<SysDepart> list = sysDepartService.findAll();
		md.addObject("departs", list);
		return md;
	}
	/*
	 * 返回编辑页面
	 */
	@RequestMapping(params="updateView")
	public ModelAndView updateView(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			SysVideo obj = getObject(SysVideo.class, "obj", request);
			obj = sysVideoService.getObjectById(obj.getId());
			if(obj.getSysDepart().getDepartId() != null) {
				SysDepart depart = sysDepartService.getObjectById(obj.getSysDepart().getDepartId());
				modelAndView.addObject("depart", depart);
			}
			
			modelAndView.addObject("video", obj);
			modelAndView.setViewName(viewUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/*
	 * 添加
	 */
	@RequestMapping(params="save")
	@ResponseBody
	public Json save(HttpServletRequest request) {
		Json j = new Json();
		try {
			SysVideo obj = getObject(SysVideo.class, "obj", request);
			sysVideoService.save(obj);
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
			SysVideo obj = getObject(SysVideo.class, "obj", request);
			sysVideoService.update(obj);
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
			sysVideoService.deleteById(ids);
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
	public JSONObject searchGrid(String pageNumber, String rowNumber,String sortName,String sortOrder,HttpServletRequest request) {
		int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
		int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
		String orderBy = sortName == null  ? "id":sortName;
		String order = sortOrder == null  ? PageResults.ASC:sortOrder;

		PageResults pageResults = new PageResults();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		pageResults.setOrderBy(orderBy);
		pageResults.setOrder(order);
		try {
			SysVideo obj =  getObject(SysVideo.class, "obj", request);
			pageResults = sysVideoService.findListForJson(pageResults, obj);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ajaxGrid(pageResults);
	}
	
	/**
	 * 根据id查询
	 * @param departId
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "searchVideoById")
	@ResponseBody
	public JSONObject searchVideoById(String videoId,HttpServletRequest request){
		SysVideo video = null;
		try {
			video = sysVideoService.getObjectById(new Integer(videoId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean2JSONObject(video);
	}
	
	
	@RequestMapping(params ="getSuppliesByXY")
	@ResponseBody
	public JSONObject getSuppliesByXY(HttpServletRequest request,String distance,String x,String y) {
		List<SysVideo> list = null;
		try {
			double dis = Double.valueOf(distance);			
			BigDecimal bx = new BigDecimal(x);
			BigDecimal by = new BigDecimal(y);
			list = sysVideoService.getSuppliesByXY(dis,bx,by);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderJson(list);
	}
	
	/**
	 * 列表查询
	 * @return
	 */
//	@RequestMapping(params="searchMapData")
//	@ResponseBody
//	public String searchMapData(String page, String rows, HttpServletRequest request) {
//			return sysVideoService.findVVideoMapServiceListForJson().toString();
//	}
	
	/***
	 * 
	 * @Description: 根据条件(港口，公司)查询视频
	 * @param @return   
	 * @return JSONObject  
	 * @throws
	 * @author czj
	 * @date 2016-1-26
	 */
//	@RequestMapping(params="findVediosByPortNameAndDepartName")
//	@ResponseBody
//	public JSONObject findVediosByPortNameAndDepartName(HttpServletRequest request){
//		String departName=request.getParameter("departName");
//		List<SysVideoVO> list = sysVideoService.findVediosVOByPortNameAndDepartName(departName);
//		Datagrid datagrid = Datagrid.createDatagrid(list);
//		return datagrid.toJSONObject();		
//	}
	
	/***
	 * 
	 * @Description: 根据条件(港口，公司)统计视频个数
	 * @param @param departName
	 * @param @return   
	 * @return Integer  
	 * @throws
	 * @author czj
	 * @date 2016-1-26
	 */
//	@RequestMapping(value="countVediosByPortNameAndDepartName")
//	@ResponseBody
//	public Integer countVediosByPortNameAndDepartName(String departName){
//		List<SysVideo> list = sysVideoService.findVediosByPortNameAndDepartName(departName);
//		return list.size();	
//	}
	
	
}
