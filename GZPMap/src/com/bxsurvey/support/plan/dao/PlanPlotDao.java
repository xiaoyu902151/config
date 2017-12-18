package com.bxsurvey.support.plan.dao;

import java.util.List;

import net.framework.base.dao.BaseDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.bxsurvey.support.plan.model.PlanOrganPeople;
import com.bxsurvey.support.plan.model.PlanPlan;
import com.bxsurvey.support.plan.model.PlanPlot;

@Repository
public class PlanPlotDao extends BaseDao<PlanPlot, Integer>{
	
	public void saveByJson(JSONObject json,Integer planId) {
		PlanPlot plot = new PlanPlot();
		plot.setPlotName(json.getString("plotName"));
		plot.setPlanType(json.getString("planType"));
		plot.setPlotGeo(json.getString("plotGeo"));
		plot.setBz(json.getString("bz"));
		PlanPlan planPlan = new PlanPlan();
		planPlan.setId(planId);
		plot.setPlanPlan(planPlan);
		String plotId = json.getString("id");
		if(plotId != null && !plotId.equals("null") && !plotId.equals(""))
		{
			plot.setId(Integer.parseInt(plotId));
			this.update(plot);
		}
		else{
			this.save(plot);
		}
	}
	
	public void saveByJsonArry(String plotNodes,Integer planId){
		JSONArray plotTreeArray = JSONArray.fromObject(plotNodes);
		for (int i = 0; i < plotTreeArray.size(); i++) {
			JSONObject json = JSONObject.fromObject(plotTreeArray.get(i));
			JSONArray standItemArray = JSONArray.fromObject(json.get("data"));
			for(int j=0;j<standItemArray.size();j++){
				JSONObject subJson = JSONObject.fromObject(standItemArray.get(j));
				this.saveByJson(subJson, planId);
			}
		}
	}
	
	public List findByPlanId(String hql){
		return this.getListMapbyQuery(hql);
	}
	
	public void deleteByPlanID(Integer planId){
		String hql="delete PlanPlot as p where p.planPlan.id='"+planId+"'";
	    this.executeHql(hql);
	}
}
