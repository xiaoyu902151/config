package com.bxsurvey.support.plan.service;

import java.util.List;

import net.framework.httpModel.Tree;

import com.bxsurvey.support.plan.model.PlanOrganPeople;
import com.bxsurvey.support.plan.model.PlanPlot;


public interface PlanOrganServiceI {
	public List<Tree> getTreeByPlanId(String planId);

}
