package com.bxsurvey.danger.DangerTeam.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.framework.httpModel.PageResults;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.bxsurvey.danger.DangerSupplies.model.DangerSupplies;
import com.bxsurvey.danger.DangerTeam.dao.DangerTeamDao;
import com.bxsurvey.danger.DangerTeam.model.DangerTeam;
import com.bxsurvey.danger.DangerTeam.service.DangerTeamServiceI;
import com.bxsurvey.sys.video.model.SysVideo;

/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.service.danger.impl.DangerSuppliesService.java 
 * Created On: 2015-5-14 下午2:32:23
 * Description: 应急救援队伍业务实现类
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Service("dangerTeamService")
public class DangerTeamService implements DangerTeamServiceI {
	@Resource
	private DangerTeamDao teamDao;
	/*
	 * 新增
	 */
	public void save(DangerTeam team) {
		if(team != null) {
			team.setValidity(true);
		}
		teamDao.save(team);
	}
	/*
	 * 编辑
	 */
	public void update(DangerTeam team) {
		teamDao.update(team);
	}
	/*
	 * 删除
	 */
	public void deleteById(String ids) {
		String[] str = ids.split(",");
		for(String id : str) {
			teamDao.deleteTeamById(Integer.parseInt(id));
		}
	}
	/*
	 * 列表查询
	 */
//	public PageResults findListForJson(PageResults page,SeachTeam seachTeam) {
//		return teamDao.findListForJson(page,seachTeam);
//	}
	
	public PageResults<DangerTeam> getListForJson(PageResults<DangerTeam> page, DangerTeam obj){
		return teamDao.getObjListForPage(page,obj);
	}
	/*
	 * 通过ID获取数据
	 */
	public DangerTeam getObjectById(Integer id) {
		return teamDao.get(id);
	}
	public List<DangerTeam> getTeamsByXY(double distance,BigDecimal x,BigDecimal y) {
		BigDecimal minX = x.subtract(new BigDecimal(distance));
		BigDecimal maxX = x.add(new BigDecimal(distance));
		BigDecimal minY = y.subtract(new BigDecimal(distance));
		BigDecimal maxY = y.add(new BigDecimal(distance));
		List<DangerTeam> list = teamDao.findListByXYminOrmax(minX.toString(), maxX.toString(), minY.toString(), maxY.toString(),distance);
		return list;
	}
	public List<DangerTeam> getPersonByDepartId(Integer departId) {
		List<DangerTeam> dajon=teamDao.getPersonByDepartId(departId);
		List<DangerTeam> two=new ArrayList<DangerTeam>();
		Map<String,DangerTeam> saix=new HashMap<String,DangerTeam>();
		for(int i=0;i<dajon.size();i++)
		{
			DangerTeam child=dajon.get(i);
			String chief=child.getChief();
			if(!saix.containsKey(chief)) //去掉姓名有些重复的
			{
				saix.put(chief, child);
				two.add(child);
			}
		}
		return  two;
	}




}
