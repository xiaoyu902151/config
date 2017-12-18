package com.bxsurvey.comd.ComdSummary.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.framework.base.controller.BaseController;
import net.framework.httpModel.Json;
import net.framework.utils.ExceptionUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bxsurvey.comd.ComdEvent.model.ComdEvent;
import com.bxsurvey.comd.ComdEvent.service.ComdEventServiceI;
import com.bxsurvey.comd.ComdExpert.model.ComdExpert;
import com.bxsurvey.comd.ComdSummary.model.ComdSummary;
import com.bxsurvey.comd.ComdSummary.service.ComdSummaryServiceI;

@Controller
@RequestMapping("/comd/summary")
public class ComdSummaryController extends BaseController {
	private static Logger log = Logger.getLogger(ComdSummaryController.class);
	
	@Autowired
	private ComdSummaryServiceI comdSummaryService;
	@Autowired
	private ComdEventServiceI comdEventService;
	
	/*
	 * 保存对象
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public Json save(HttpServletRequest request,String eventId) {
		Json j = new Json();
		try {
			ComdSummary obj = getObject(ComdSummary.class, "obj", request);
			//根据id获取ComdEvent对象
			ComdEvent ce = comdEventService.getEventById(eventId);
			ce.setStatus("1");
			obj.setComdEvent(ce);
			comdSummaryService.save(obj);
			j.setSuccess(true);
			j.setMsg("添加成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("添加失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}

}
