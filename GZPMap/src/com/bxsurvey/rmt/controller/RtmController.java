package com.bxsurvey.rmt.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.framework.base.controller.BaseController;
import net.framework.httpModel.PageResults;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bxsurvey.rmt.model.RtmWarning;
import com.bxsurvey.rmt.service.RtmWarnServiceI;

/**
 * 
 * Description:在线监测数据控制器
 * @author wzr
 * @date 2016-9-9下午1:58:05
 *
 */
@Controller
@RequestMapping("/rtm")
public class RtmController extends BaseController {
	private static Logger log = Logger.getLogger(RtmController.class);
	
	@Resource
	private RtmWarnServiceI rWarnService;
	
	/**
	 * 查询所有实时数据
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping(params ="searchDaGridByPage")
	@ResponseBody
	public JSONObject searchDaGridByPage(String page, String rows, HttpServletRequest request) {
		int pageNo = Integer.parseInt((page == null || page =="0") ? "1":page);
		int pageSize = Integer.parseInt((rows == null || rows =="0") ? "10":rows);
		PageResults<RtmWarning> pageResults = new PageResults<RtmWarning>();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		try {
			//RtmWarning rtmWarning = getObject(RtmWarning.class,"RtmWarning",request);
		    pageResults = rWarnService.searchDaGrid(pageResults);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		 JSONObject ajaxGrid = ajaxGrid(pageResults);
		 return ajaxGrid;
	}
	
	
	/*
	 * 获取所有的报警数据(不带分页)
	 */
	@RequestMapping(params="findSsbjData")
	@ResponseBody
	public JSONObject findSsbjData(){
		List<RtmWarning> list = rWarnService.findSsbjData();
		return renderJson(list);
	}
	

}
