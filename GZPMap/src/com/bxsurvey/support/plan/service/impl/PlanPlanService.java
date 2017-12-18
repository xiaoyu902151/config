package com.bxsurvey.support.plan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.framework.httpModel.PageResults;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxsurvey.support.degknowlage.model.DangerKnowlage;
import com.bxsurvey.support.plan.dao.PlanAffixDao;
import com.bxsurvey.support.plan.dao.PlanOrganDao;
import com.bxsurvey.support.plan.dao.PlanOrganPeopleDao;
import com.bxsurvey.support.plan.dao.PlanPlanDao;
import com.bxsurvey.support.plan.dao.PlanPlotDao;
import com.bxsurvey.support.plan.dao.PlanStandDao;
import com.bxsurvey.support.plan.model.PlanAffix;
import com.bxsurvey.support.plan.model.PlanPlan;
import com.bxsurvey.support.plan.service.PlanPlanServiceI;

@Service
public class PlanPlanService implements PlanPlanServiceI {
	@SuppressWarnings("unused")
	private Logger log=Logger.getLogger(PlanPlanService.class);
	
	@Autowired
	private PlanPlanDao planPlanDao;
	
	@Autowired
	private PlanAffixDao planAffixDao;
	
	@Autowired
	private PlanOrganDao planOrganDao;
	
	@Autowired
	private PlanOrganPeopleDao planOrganPeopleDao;

	@Autowired
	private PlanPlotDao planPlotDao;
	
	@Autowired
	private PlanStandDao planStandDao;
	@Override
	public void save(PlanPlan plan,String afs,String peopleNodes,String standNodes,String plotNodes) {
		if(plan != null) {
			//设置一些默认的值
		}
		Integer planId = (Integer)planPlanDao.save(plan);
		JSONArray fileArray = JSONArray.fromObject(afs);
		for (int i = 0; i < fileArray.size(); i++) {
			JSONObject json = JSONObject.fromObject(fileArray.get(i));
			PlanAffix affix = new PlanAffix();
			affix.setName(json.getString("name"));
			affix.setPath(json.getString("path"));
			affix.setFileAfter(json.getString("fileAfter"));
			affix.setValidity(true);
			affix.setWriter("admin");
			affix.setUploadTime(new Date());
			PlanPlan planPlan = new PlanPlan();
			planPlan.setId(planId);
			affix.setPlanPlan(planPlan);
//			affix.setPlanId(planId);
			planAffixDao.save(affix);
		}
		peopleNodes = (peopleNodes == null ?"[]":peopleNodes);
		saveOrgan(peopleNodes,null,planId);
		standNodes = (standNodes == null ?"[]":standNodes);
		saveStandsByJsonArry(standNodes,planId,"[]");
		plotNodes = (plotNodes == null ?"[]":plotNodes);
		savePlotByJsonArry(plotNodes,planId,"[]");
	}
	
	public void saveStandsByJsonArry(String standNodes,Integer planId,String delStand){
		JSONArray standTreeArray = JSONArray.fromObject(standNodes);
		for (int i = 0; i < standTreeArray.size(); i++) {
			JSONObject json = JSONObject.fromObject(standTreeArray.get(i));
			JSONArray standItemArray = JSONArray.fromObject(json.get("data"));
			for(int j=0;j<standItemArray.size();j++){
				JSONObject subJson = JSONObject.fromObject(standItemArray.get(j));
				planStandDao.saveByJson(subJson, planId);
			}
		}
		
		JSONArray del = JSONArray.fromObject(delStand);
		for (int i = 0; i < del.size(); i++) {
			JSONObject json = JSONObject.fromObject(del.get(i));
			String standId = json.getString("id");
			planStandDao.deleteById(Integer.parseInt(standId));
		}
	}
	
	public void savePlotByJsonArry(String plotNodes,Integer planId,String delPlot){
		JSONArray plotTreeArray = JSONArray.fromObject(plotNodes);
		for (int i = 0; i < plotTreeArray.size(); i++) {
			JSONObject json = JSONObject.fromObject(plotTreeArray.get(i));
			JSONArray plotItemArray = JSONArray.fromObject(json.get("data"));
			for(int j=0;j<plotItemArray.size();j++){
				JSONObject subJson = JSONObject.fromObject(plotItemArray.get(j));
				planPlotDao.saveByJson(subJson, planId);
			}
		}
		
		JSONArray del = JSONArray.fromObject(delPlot);
		for (int i = 0; i < del.size(); i++) {
			JSONObject json = JSONObject.fromObject(del.get(i));
			String standId = json.getString("id");
			planPlotDao.deleteById(Integer.parseInt(standId));
		}
	}
	
	public void saveOrgan(String peopleNodes,Integer pid,Integer planId){
		JSONArray organArray = JSONArray.fromObject(peopleNodes);
		for (int i = 0; i < organArray.size(); i++) {
			JSONObject json = JSONObject.fromObject(organArray.get(i));
			Integer organId = planOrganDao.saveByJson(json,pid,planId);
			JSONArray peopleArray = JSONArray.fromObject(json.get("data"));
			for(int j=0;j<peopleArray.size();j++){
				JSONObject subJson = JSONObject.fromObject(peopleArray.get(j));
				planOrganPeopleDao.saveByJson(subJson,organId);
			}
			if(Boolean.parseBoolean(json.get("isParent").toString())){//判断是否存在子节点
				String children = json.get("children").toString();
				saveOrgan(children,organId,planId);//递归保存机构树
			}
		}
		

	}

	@Override
	public void update(PlanPlan plan,String afs,String dfs,String peopleNodes,String delPeoples,String standNodes,String plotNodes,String delStand,String delPlot) {
		planPlanDao.update(plan);
		Integer planId = (Integer)plan.getId();
		JSONArray saveOfUpdate = JSONArray.fromObject(afs);
		for (int i = 0; i < saveOfUpdate.size(); i++) {
			JSONObject json = JSONObject.fromObject(saveOfUpdate.get(i));
			PlanAffix affix = new PlanAffix();
			affix.setName(json.getString("name"));
			affix.setPath(json.getString("path"));
			affix.setFileAfter(json.getString("fileAfter"));
			affix.setValidity(true);
			affix.setWriter("admin");
			affix.setUploadTime(new Date());
			PlanPlan planPlan = new PlanPlan();
			planPlan.setId(planId);
			affix.setPlanPlan(planPlan);
//			affix.setPlanId(planId);
			String affixId = json.getString("id");
			if(affixId != null && !affixId.equals("null") && !affixId.equals(""))
			{
				affix.setId(Integer.parseInt(affixId));
				planAffixDao.update(affix);
			}
			else{
				planAffixDao.save(affix);
			}
		}
		JSONArray del = JSONArray.fromObject(dfs);
		for (int i = 0; i < del.size(); i++) {
			JSONObject json = JSONObject.fromObject(del.get(i));
			String affixId = json.getString("id");
			planAffixDao.deleteById(Integer.parseInt(affixId));
		}
		planOrganDao.deleteByPlanID(planId);
		peopleNodes = (peopleNodes == null ?"[]":peopleNodes);
		saveOrgan(peopleNodes,null,planId);
		//planStandDao.deleteByPlanID(planId);
		standNodes = (standNodes == null ?"[]":standNodes);
		saveStandsByJsonArry(standNodes,planId,delStand);
		//planPlotDao.deleteByPlanID(planId);
		plotNodes = (plotNodes == null ?"[]":plotNodes);
		savePlotByJsonArry(plotNodes,planId,delPlot);
	}

	@Override
	public void deleteById(String ids) {
		String[] str = ids.split(",");
		for(String id : str) {
			planPlanDao.deleteById(new Integer(id));
		}
	}

	@Override
	public PageResults<PlanPlan> getListForJson(PageResults<PlanPlan> page,
			PlanPlan obj) {
		return planPlanDao.getObjListForPage(page, obj);
	}

	@Override
	public List<PlanPlan> getListNoPage(PlanPlan obj) {
		return planPlanDao.findByExample(obj);
	}

	@Override
	public PlanPlan findById(String id) {
		return planPlanDao.get(Integer.parseInt(id));
	}

	@Override
	public List<PlanAffix> getAffixByPlanId(String planId) {
		StringBuilder hql = new StringBuilder();
		hql.append("select p from PlanAffix p where p.planPlan.id = ?");
		List<Object> param = new ArrayList<Object>();
		param.add(Integer.parseInt(planId));
		return planAffixDao.find(hql.toString(), param);
	}

	/**
	 * 根据预案名称查询
	 */
	public PlanPlan findbyPlanName(String planName,String id) {
		StringBuilder hql = new StringBuilder();
		hql.append("select p from PlanPlan p where p.planName = ? ");
		
		List<Object> param = new ArrayList<Object>();
		param.add(planName);
		
		if(id != null && id != ""){
			hql.append(" and p.id != ? ");
			param.add(Integer.valueOf(id));
		}
		List<PlanPlan> list = planPlanDao.find(hql.toString(), param);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据预案编号查询
	 */
	public PlanPlan findbyPlanNum(String planNum,String id) {
		StringBuilder hql = new StringBuilder();
		hql.append("select p from PlanPlan p where p.planNum = ? ");
		
		List<Object> param = new ArrayList<Object>();
		param.add(planNum);
		
		if(id != null && id != ""){
			hql.append(" and p.id != ? ");
			param.add(Integer.valueOf(id));
		}
		List<PlanPlan> list = planPlanDao.find(hql.toString(), param);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
