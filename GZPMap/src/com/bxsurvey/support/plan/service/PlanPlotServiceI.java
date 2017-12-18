package com.bxsurvey.support.plan.service;

import java.util.List;

import net.framework.httpModel.Tree;

import com.bxsurvey.support.plan.model.PlanPlot;


public interface PlanPlotServiceI {
	public List<PlanPlot> getListByPlanId(String planId,String planType);
	public List<Tree> getTreeByPlanId(String planId);
}
