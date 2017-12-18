package com.bxsurvey.support.degknowlage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.framework.base.controller.BaseController;
import net.framework.httpModel.Json;
import net.framework.utils.ExceptionUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.bxsurvey.support.plan.service.PlanPlotServiceI;

@Controller
@RequestMapping("/support/plot")
public class PlanPlotController extends BaseController  {
	private static Logger log = Logger.getLogger(PlanPlotController.class);
	
	@Autowired
	private PlanPlotServiceI planPlotService;//调度

	
	@RequestMapping(params = "getAllPlotByPlanId")
	@ResponseBody
	public Json getAllPlotByPlanId(HttpServletRequest request,String planId,String planType) {
		Json j = new Json();
		try {
			List list = planPlotService.getListByPlanId(planId,planType);
			j.setSuccess(true);
			j.setMsg("删除成功!");
			j.setObj(list);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	

}
