package com.bxsurvey.support.plan.dao;

import java.util.ArrayList;
import java.util.List;

import net.framework.base.dao.BaseDao;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.bxsurvey.support.plan.model.PlanOrgan;
import com.bxsurvey.support.plan.model.PlanOrganPeople;

@Repository
public class PlanOrganPeopleDao extends BaseDao<PlanOrganPeople, Integer> {
	public void saveByJson(JSONObject subJson,Integer organId) {
		PlanOrganPeople people = new PlanOrganPeople();
		people.setName(subJson.getString("name"));
		people.setSex(subJson.getString("sex"));
		people.setPhone(subJson.getString("phone"));
		people.setDuty(subJson.getString("duty"));
		PlanOrgan planOrgan = new PlanOrgan();
		planOrgan.setId(organId);
		people.setPlanOrgan(planOrgan);
		this.save(people);
	}
	
	public List<PlanOrganPeople> findByOrganId(Integer organId){
		StringBuilder hql = new StringBuilder();
		hql.append("select p from PlanOrganPeople p where p.planOrgan.id = ?");
		List<Object> param = new ArrayList<Object>();
		param.add(organId);
		return this.find(hql.toString(), param);
	}

}
