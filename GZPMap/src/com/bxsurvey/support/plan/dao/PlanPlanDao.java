package com.bxsurvey.support.plan.dao;

import net.framework.base.dao.BaseDao;
import org.springframework.stereotype.Repository;

import com.bxsurvey.support.degknowlage.model.DangerKnowlage;
import com.bxsurvey.support.plan.model.PlanPlan;

@Repository
public class PlanPlanDao extends BaseDao<PlanPlan, Integer> {
	public void deteleObjectById(Integer id) {
		PlanPlan plan = this.get(id);
		this.delete(plan);
	}
}
