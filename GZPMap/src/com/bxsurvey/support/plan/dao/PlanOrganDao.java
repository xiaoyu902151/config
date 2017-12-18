package com.bxsurvey.support.plan.dao;

import java.util.ArrayList;
import java.util.List;

import net.framework.base.dao.BaseDao;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.bxsurvey.support.plan.model.PlanOrgan;
import com.bxsurvey.support.plan.model.PlanPlan;

@Repository
public class PlanOrganDao extends BaseDao<PlanOrgan, Integer> {
	public Integer saveByJson(JSONObject json,Integer pid,Integer planId) {
		PlanOrgan organ = new PlanOrgan();
		organ.setName(json.getString("name"));
		organ.setPid(pid);
		PlanPlan planPlan = new PlanPlan();
		planPlan.setId(planId);
		organ.setPlanPlan(planPlan);
//		organ.setPlanId(planId);
		Integer organId = (Integer)this.save(organ);
		return organId;
	}
	
	public void saveByJsonArry(String standNodes,Integer planId){
		
	}
	
	public List<PlanOrgan> findByPlanId(String planId){
		StringBuilder hql = new StringBuilder();
		hql.append("select p from PlanOrgan p where p.planPlan.id = ?");
		List<Object> param = new ArrayList<Object>();
		param.add(Integer.parseInt(planId));
		return this.find(hql.toString(), param);
	}
	
	public void deleteByPlanID(Integer planId){
		String organ="delete PlanOrgan as p where p.planPlan.id='"+planId+"'";
		String people= "delete PlanOrganPeople as pp where pp.planOrgan.id in(select id from PlanOrgan po where po.planPlan.id='"+planId+"')";
		this.executeHql(people);
		this.executeHql(organ);
	    
	}
}
