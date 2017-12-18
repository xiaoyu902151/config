package com.bxsurvey.support.plan.service;

import java.util.List;
import java.util.Map;

import net.framework.httpModel.Tree;

import com.bxsurvey.support.plan.model.PlanPlot;

public interface PlanStandServiceI {
	public List<PlanPlot> getListByPlanId(String planId);
	public List<Tree> getTreeByPlanId(String planId);
}
