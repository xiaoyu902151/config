package com.bxsurvey.support.plan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.framework.httpModel.Tree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxsurvey.support.plan.dao.PlanStandDao;
import com.bxsurvey.support.plan.model.PlanPlot;
import com.bxsurvey.support.plan.service.PlanStandServiceI;

@Service
public class PlanStandService implements PlanStandServiceI{

	@Autowired
	private PlanStandDao planStandDao;
	
	@Override
	public List<PlanPlot> getListByPlanId(String planId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tree> getTreeByPlanId(String planId) {
		List<Tree> res = new ArrayList<Tree>();
		
		Tree root = new Tree();
		root.setName("响应要求");
		root.setId("0");
		root.setOpen(true);
		root.setPid("");
		root.setData(new ArrayList());
		root.setIsParent(true);
		res.add(root);
		String hql ="Select ps.id,ps.planType,pet.eventName as planTypeDis,ps.suppliesType,sp.paramsName as suppliesTypeDis,ps.oneLevelStand,ps.twoLevelStand,ps.threeLevelStand,ps.planPlan.id from PlanStand ps,PlanEventType pet,SysParams sp where ps.planType = pet.id and ps.suppliesType =  sp.paramsId and ps.planPlan.id = "+ planId+" Order by ps.planType ASC";
		List list = planStandDao.findByPlanId(hql);
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
        	String suppliesType = tm[3].toString();
        	map.put("suppliesType", suppliesType);
        	String suppliesTypeDis = tm[4].toString();
        	map.put("suppliesTypeDis", suppliesTypeDis);
        	String oneLevelStand = tm[5].toString();
        	map.put("oneLevelStand", oneLevelStand);
        	String twoLevelStand = tm[6].toString();
        	map.put("twoLevelStand", twoLevelStand);
        	String threeLevelStand = tm[7].toString();
        	map.put("threeLevelStand", threeLevelStand);
        	String planIdTemp  = tm[8].toString();
        	map.put("planId", planIdTemp);
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
	    			name = planTypeDis;
	    			sid = id;
	    			data = new ArrayList<Map<String,String>>();
	        }
        	data.add(map);
        	last = planTypeDis;
        }
		if(list.size() >0 )
		{
			Tree tempTree = new Tree();
			tempTree.setName(last);
			tempTree.setId(sid);
			tempTree.setPid("0");
			tempTree.setData(data);
			res.add(tempTree);
		}
		return res;
	}



}
