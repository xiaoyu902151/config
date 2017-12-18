package com.bxsurvey.support.plan.service;

import java.util.List;

import com.bxsurvey.support.plan.model.PlanAffix;
import com.bxsurvey.support.plan.model.PlanPlan;

import net.framework.httpModel.PageResults;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public interface PlanPlanServiceI {
	public void save(PlanPlan planPlanDao, String afs,String peopleNodes,String standNodes,String plotNodes);
	public void update(PlanPlan planPlanDao, String afs, String dfs,String peopleNodes,String delPeoples,String standNodes,String plotNodes,String delStand,String delPlot);
	public void deleteById(String ids);
	public PageResults<PlanPlan> getListForJson(PageResults<PlanPlan> page, PlanPlan obj);
	public List<PlanPlan> getListNoPage(PlanPlan obj);
	public PlanPlan findById(String id);
	public List<PlanAffix> getAffixByPlanId(String planId);
	public PlanPlan findbyPlanName(String planName,String id);
	public PlanPlan findbyPlanNum(String planNum,String id);
}
