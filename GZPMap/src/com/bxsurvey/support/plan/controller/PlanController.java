package com.bxsurvey.support.plan.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.framework.base.controller.BaseController;
import net.framework.httpModel.Json;
import net.framework.httpModel.PageResults;
import net.framework.httpModel.Tree;
import net.framework.utils.ExceptionUtil;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxsurvey.support.plan.model.PlanPlan;
import com.bxsurvey.support.plan.service.PlanOrganServiceI;
import com.bxsurvey.support.plan.service.PlanPlanServiceI;
import com.bxsurvey.support.plan.service.PlanPlotServiceI;
import com.bxsurvey.support.plan.service.PlanStandServiceI;

@Controller
@RequestMapping("/support/plan")
public class PlanController extends BaseController{
	private static Logger log = Logger.getLogger(PlanController.class);
	
	@Autowired
	private PlanPlanServiceI planPlanService;//调度

	@Autowired
	private PlanOrganServiceI planOrganService;
	@Autowired
	private PlanPlotServiceI planPlotService;
	@Autowired
	private PlanStandServiceI planStandService;
	
	@RequestMapping(params = "updateView")
	@ResponseBody
	public ModelAndView updateView(String id){
		ModelAndView md = new ModelAndView();
        PlanPlan plan = planPlanService.findById(id);
        md.addObject("plan",plan);
		md.setViewName("support/plan/updateView");
		return md;
	}
	@RequestMapping(params = "browseView")
	@ResponseBody
	public ModelAndView browseView(String id,String type){
		ModelAndView md = new ModelAndView();
        PlanPlan plan = planPlanService.findById(id);
        md.addObject("plan",plan);
        md.addObject("type",type);
		md.setViewName("support/plan/browseView");
		return md;
	}
	
	@RequestMapping(params = "addView")
	@ResponseBody
	public ModelAndView addView(){
		ModelAndView md = new ModelAndView();
		md.setViewName("support/plan/addView");
		return md;
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

		PageResults<PlanPlan> pageResults = new PageResults<PlanPlan>();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		pageResults.setOrderBy(orderBy);
		pageResults.setOrder(order);
		try {
			PlanPlan obj = getObject(PlanPlan.class, "obj", request);
			pageResults = planPlanService.getListForJson(pageResults,obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ajaxGrid(pageResults);
	}
	
	
	/*
	 * 添加
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public Json save(HttpServletRequest request, String afs,String peopleNodes,String standNodes,String plotNodes,String id) {
		Json j = new Json();
		try {
			PlanPlan obj = getObject(PlanPlan.class, "obj", request);
			String planName = obj.getPlanName();
			String planNum = obj.getPlanNum();
			
			PlanPlan planPlan1 = planPlanService.findbyPlanName(planName,id);
			//预案名称不存在
			if(planPlan1 == null){
				PlanPlan planPlan2 = planPlanService.findbyPlanNum(planNum,id);
				//预案编号不存在
				if(planPlan2 == null){
					planPlanService.save(obj,afs,peopleNodes,standNodes,plotNodes);
					//0代表保存成功
					j.setState(0);
					j.setMsg("添加成功!");
				}else{
					//预案编号已存在
					j.setState(2);
					j.setMsg("预案编号已存在!");
				}
			}else{
				//预案名称已存在
				j.setState(1);
				j.setMsg("预案名称已存在!");
			}
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
	public Json update(HttpServletRequest request, String afs, String dfs,String peopleNodes,String delPeoples,String standNodes,String plotNodes,String delStand,String delPlot,String id) {
		Json j = new Json();
		try {
			PlanPlan obj = getObject(PlanPlan.class, "obj", request);
			String planName = obj.getPlanName();
			String planNum = obj.getPlanNum();
			
			PlanPlan planPlan1 = planPlanService.findbyPlanName(planName,id);
			//预案名称不存在
			if(planPlan1 == null){
				PlanPlan planPlan2 = planPlanService.findbyPlanNum(planNum,id);
				//预案编号不存在
				if(planPlan2 == null){
					planPlanService.update(obj,afs,dfs,peopleNodes,delPeoples,standNodes,plotNodes,delStand,delPlot);
					//0代表保存成功
					j.setState(0);
					j.setMsg("修改成功!");
				}else{
					//预案编号已存在
					j.setState(2);
					j.setMsg("预案编号已存在!");
				}
			}else{
				//预案名称已存在
				j.setState(1);
				j.setMsg("预案名称已存在!");
			}
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
			planPlanService.deleteById(ids);
			j.setSuccess(true);
			j.setMsg("删除成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	
	@RequestMapping(params = "searchGridNoPage")
	@ResponseBody
	public JSONObject searchGridNoPage(HttpServletRequest request) {
		List<PlanPlan>  list= null;
		try {
			PlanPlan obj = getObject(PlanPlan.class, "obj", request);
			list = planPlanService.getListNoPage(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderJson(list);
	}
	
	
	@RequestMapping(params = "getAffixByPlanId")
	@ResponseBody
	public Json getAffixByPlanId(HttpServletRequest request,String planId) {
		Json j = new Json();
		try {
			List list = planPlanService.getAffixByPlanId(planId);
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
	
	@RequestMapping(params = "getOrganByPlanId")
	@ResponseBody
	public Json getOrganByPlanId(HttpServletRequest request,String planId) {
		Json j = new Json();
		try {
			List<Tree> nodes = planOrganService.getTreeByPlanId(planId);
			JSONObject ret = renderJson(nodes);
			j.setSuccess(true);
			j.setMsg("删除成功!");
			j.setObj(ret);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}

	@RequestMapping(params = "getStandByPlanId")
	@ResponseBody
	public Json getStandByPlanId(HttpServletRequest request,String planId) {
		Json j = new Json();
		try {
			List<Tree> nodes = planStandService.getTreeByPlanId(planId);
			JSONObject ret = renderJson(nodes);
			j.setSuccess(true);
			j.setMsg("删除成功!");
			j.setObj(ret);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}		
		return j;
	}
	
	@RequestMapping(params = "getPlotByPlanId")
	@ResponseBody
	public Json getPlotByPlanId(HttpServletRequest request,String planId) {
		Json j = new Json();
		try {
			List<Tree> nodes = planPlotService.getTreeByPlanId(planId);
			//JSONObject ret = renderJson(nodes);
			j.setSuccess(true);
			j.setMsg("删除成功!");
			j.setObj(nodes);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	
	@RequestMapping(params = "getPlotListByPlanId")
	@ResponseBody
	public Json getPlotListByPlanId(HttpServletRequest request,String planId,String planType) {
		Json j = new Json();
		try {
			List list = planPlotService.getListByPlanId(planId, planType);
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
