package com.bxsurvey.support.plan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.framework.httpModel.Tree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxsurvey.support.plan.dao.PlanPlotDao;
import com.bxsurvey.support.plan.model.PlanPlot;
import com.bxsurvey.support.plan.service.PlanPlotServiceI;
@Service
public class PlanPlotService implements PlanPlotServiceI{

	@Autowired
	private PlanPlotDao planPlotDao;
	@Override
	public List<PlanPlot> getListByPlanId(String planId,String planType) {
		
		StringBuilder hql = new StringBuilder();
		hql.append(" select p from PlanPlot p where p.planPlan.id = ? ");
		hql.append(" and p.planType = ? ");
		List<Object> param = new ArrayList<Object>();
		param.add(planId);
		param.add(planType);
		return planPlotDao.find(hql.toString(), param);
	}

	@Override
	public List<Tree> getTreeByPlanId(String planId) {
		List<Tree> res = new ArrayList<Tree>();
		Tree root = new Tree();
		root.setName("疏导方案");
		root.setId("0");
		root.setOpen(true);
		root.setPid("");
		root.setData(new ArrayList());
		root.setIsParent(true);
		res.add(root);
		String hql ="Select pp.id,pp.planType,pet.eventName as planTypeDis,pp.plotName,pp.planPlan.id,pp.plotGeo,pp.bz from PlanPlot pp,PlanEventType pet  where pp.planType = pet.id and pp.planPlan.id = "+ planId+" Order by pp.planType ASC";
		List list = planPlotDao.findByPlanId(hql);
		String name = "";
		String sid = "";
		String last = "";
		Map<String,String> map = new HashMap<String,String>();
		List<Map<String,String>> data = new ArrayList<Map<String,String>>();
		int i =0;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			map = new HashMap<String,String>();
			Object[] tm = (Object[]) iterator.next();
        	String id = tm[0].toString();
        	map.put("id", id);
        	String planType = tm[1].toString();
        	map.put("planType", planType);
        	String planTypeDis = tm[2].toString();
        	map.put("planTypeDis", planTypeDis);
        	String plotName = tm[3].toString();
        	map.put("plotName", plotName);
        	String planIdTemp = tm[4].toString();
        	map.put("planId", planIdTemp);
        	String plotGeo = tm[5].toString();
        	map.put("plotGeo", plotGeo);
        	String bz = tm[6].toString();
        	map.put("bz", bz);
        	if(name.equals("")){
        		name = planTypeDis;
	        	sid = planType;
        	}
        	else if(!name.equals(planTypeDis)){
        		Tree tempTree = new Tree();
        		tempTree.setName(name);
    			tempTree.setId(planType);
    			tempTree.setPid(Integer.toHexString(i++));
    			tempTree.setData(data);
    			res.add(tempTree);
    			data = new ArrayList<Map<String,String>>();
	        	name = planTypeDis;
	        	sid = id;
        	}
        	data.add(map);
        	last = planTypeDis;
		}
		if(list.size() >0 )
		{
			Tree tempTree = new Tree();
			tempTree.setId(sid);
			tempTree.setName(last);
			tempTree.setPid("0");
			tempTree.setData(data);
			res.add(tempTree);
		}
		return res;
	}

}
