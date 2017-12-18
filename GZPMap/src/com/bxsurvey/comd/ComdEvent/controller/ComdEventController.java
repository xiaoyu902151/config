package com.bxsurvey.comd.ComdEvent.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.bxsurvey.comd.ComdEvent.model.ComdEvent;
import com.bxsurvey.comd.ComdEvent.service.ComdEventServiceI;
import com.bxsurvey.comd.ComdExpert.model.ComdExpert;



@Controller
@RequestMapping("/comd/event")
public class ComdEventController extends BaseController{
private static Logger log = Logger.getLogger(ComdEventController.class);
	
	@Autowired
	private ComdEventServiceI comdEventService;//
	
	
	@RequestMapping(params = "getExpertsByXY", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public JSONObject getExpertsByXY(HttpServletRequest request,String distance,String x,String y) {
		List<ComdEvent> list = new ArrayList<ComdEvent>();
		try {
			double dis = Double.valueOf(distance);			
			BigDecimal bx = new BigDecimal(x);
			BigDecimal by = new BigDecimal(y);
			list = comdEventService.getEventsByXY(dis,bx,by);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderJson(list);
	}
	
	
	@RequestMapping(params = "save")
	@ResponseBody
	public Json save(HttpServletRequest request) {
		Json j = new Json();
		try {
			ComdEvent obj = getObject(ComdEvent.class, "obj", request);
			Integer id = comdEventService.save(obj);
			j.setSuccess(true);
			j.setMsg("添加成功!");
			j.setObj(id);
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
			ComdEvent obj = getObject(ComdEvent.class, "obj", request);
			comdEventService.update(obj);
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
			comdEventService.deleteById(ids);
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
	 * 列表查询,增加时间查询
	 * 
	 * @return
	 */
	@RequestMapping(params = "searchGrid")
	@ResponseBody
	public JSONObject searchGrid(String pageNumber, String rowNumber,String sortName,String sortOrder, 
			HttpServletRequest request,String startTime,String endTime) {
		int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
		int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
		String orderBy = sortName == null  ? "id":sortName;
		String order = sortOrder == null  ? PageResults.ASC:sortOrder;

		PageResults<ComdEvent> pageResults = new PageResults<ComdEvent>();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		pageResults.setOrderBy(orderBy);
		pageResults.setOrder(order);
		try {
			ComdEvent obj = getObject(ComdEvent.class, "obj", request);
			pageResults = comdEventService.getListForJson(pageResults,obj,startTime,endTime);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ajaxGrid(pageResults);
	}
	
	
	@RequestMapping(params = "searchGridNoPage")
	@ResponseBody
	public JSONObject searchGridNoPage(HttpServletRequest request) {
		List<ComdEvent>  list= null;
		try {
			ComdEvent obj = getObject(ComdEvent.class, "obj", request);
			list= comdEventService.getListNoPage(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return renderJson(list);
	}
}
