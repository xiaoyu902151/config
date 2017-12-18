package com.bxsurvey.support.plan.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.framework.base.dao.BaseDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.bxsurvey.danger.DangerSupplies.vo.StandVO;
import com.bxsurvey.support.plan.model.PlanPlan;
import com.bxsurvey.support.plan.model.PlanStand;

@Repository
public class PlanStandDao extends BaseDao<PlanStand,Integer> {
	public void saveByJson(JSONObject json,Integer planId) {
		PlanStand stand = new PlanStand();
		stand.setPlanType(json.getString("planType"));
		stand.setSuppliesType(json.getString("suppliesType"));
		stand.setOneLevelStand(Integer.parseInt(json.getString("oneLevelStand")));
		stand.setOneLevelStand(Integer.parseInt(json.getString("oneLevelStand")));
		stand.setTwoLevelStand(Integer.parseInt(json.getString("twoLevelStand")));
		stand.setThreeLevelStand(Integer.parseInt(json.getString("threeLevelStand")));
		PlanPlan planPlan = new PlanPlan();
		planPlan.setId(planId);
		stand.setPlanPlan(planPlan);
		String standId = json.getString("id");
		if(standId != null && !standId.equals("null") && !standId.equals(""))
		{
			stand.setId(Integer.parseInt(standId));
			this.update(stand);
		}
		else{
			this.save(stand);
		}
	}
	
	public void saveByJsonArry(String standNodes,Integer planId){
		JSONArray standTreeArray = JSONArray.fromObject(standNodes);
		for (int i = 0; i < standTreeArray.size(); i++) {
			JSONObject json = JSONObject.fromObject(standTreeArray.get(i));
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
		String hql="delete PlanStand as p where p.planPlan.id='"+planId+"'";
	    this.executeHql(hql);
	}
	
	
	public Map<String,StandVO> getListByPlanAndType(String planId, String planType,String level) {
		String hql ="";
		Map<String,StandVO> res = new HashMap<String,StandVO>();
		if(level.equals("0"))
			hql ="Select ps.suppliesType,sp.paramsName as suppliesTypeDis,ps.oneLevelStand as stand from PlanStand ps,SysParams sp   where ps.suppliesType =  sp.paramsId and ps.planType = '"+planType+"' and ps.planPlan.id = "+ planId+"";
		else if(level.equals("1"))
			hql ="Select ps.suppliesType,sp.paramsName as suppliesTypeDis,ps.twoLevelStand as stand from PlanStand ps,SysParams sp   where ps.suppliesType =  sp.paramsId and ps.planType = '"+planType+"' and ps.planPlan.id = "+ planId+"";
		else
			hql ="Select ps.suppliesType,sp.paramsName as suppliesTypeDis,ps.threeLevelStand as stand from PlanStand ps,SysParams sp  where ps.suppliesType =  sp.paramsId and ps.planType = '"+planType+"' and ps.planPlan.id = "+ planId+"";
		List list = getListMapbyQuery(hql);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] tm = (Object[]) iterator.next();
        	String type = tm[0].toString();
        	String typeName = tm[1].toString();
        	String need = tm[2].toString();
        	StandVO stand = new StandVO(need,false,type,typeName);
        	res.put(type, stand);
		}
		return res;
	}
}
