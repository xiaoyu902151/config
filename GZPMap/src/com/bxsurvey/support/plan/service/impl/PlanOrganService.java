package com.bxsurvey.support.plan.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.framework.httpModel.Tree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxsurvey.support.plan.dao.PlanOrganDao;
import com.bxsurvey.support.plan.dao.PlanOrganPeopleDao;
import com.bxsurvey.support.plan.dao.PlanPlotDao;
import com.bxsurvey.support.plan.model.PlanOrgan;
import com.bxsurvey.support.plan.model.PlanOrganPeople;
import com.bxsurvey.support.plan.model.PlanPlot;
import com.bxsurvey.support.plan.service.PlanOrganServiceI;
@Service
public class PlanOrganService implements PlanOrganServiceI{

	@Autowired
	private PlanOrganDao planOrganDao;
	
	@Autowired
	private PlanOrganPeopleDao planOrganPeopleDao;

	@Override
	public List<Tree> getTreeByPlanId(String planId) {
		List<Tree> res = new ArrayList<Tree>();
		
		Tree root = new Tree();
		root.setName("应急组织机构");
		root.setId("0");
		root.setOpen(true);
		root.setPid("");
		root.setData(new ArrayList());
		root.setIsParent(true);
		res.add(root);
		List<PlanOrgan> organList = planOrganDao.findByPlanId(planId);
		for(int i =0;i<organList.size();i++){
			Tree tempTree = new Tree();
			PlanOrgan tempOrgan = (PlanOrgan)organList.get(i);
			tempTree.setName(tempOrgan.getName());
			tempTree.setId(tempOrgan.getId().toString());
			String pid = tempOrgan.getPid() == null ? "0":tempOrgan.getPid().toString();
			tempTree.setPid(pid);
			tempTree.setOpen(true);
			List<PlanOrganPeople> data = planOrganPeopleDao.findByOrganId(tempOrgan.getId());
			tempTree.setData(data);
			res.add(tempTree);
		}
		return res;
	}


}
