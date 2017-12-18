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

import com.bxsurvey.danger.DangerTeam.dao.DangerTeamDetailDao;
import com.bxsurvey.danger.DangerTeam.model.DangerTeamDetail;
import com.bxsurvey.danger.DangerTeam.service.DangerTeamDetailServiceI;

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
@Service("dangerTeamDetailService")
public class DangerTeamDetailService implements DangerTeamDetailServiceI {
	@Resource
	private DangerTeamDetailDao teamDetailDao;
	/*
	 * 新增
	 */
	public void save(DangerTeamDetail teamDetail) {
		if(teamDetail != null) {
			//teamDetail.setValidity(true);
		}
		teamDetailDao.save(teamDetail);
	}
	/*
	 * 编辑
	 */
	public void update(DangerTeamDetail teamDetail) {
		teamDetailDao.update(teamDetail);
	}
	/*
	 * 删除
	 */
	public void deleteById(String ids) {
		String[] str = ids.split(",");
		for(String id : str) {
			teamDetailDao.deleteTeamById(Integer.parseInt(id));
		}
	}

	
	public PageResults<DangerTeamDetail> getListForJson(PageResults<DangerTeamDetail> page, DangerTeamDetail obj){
		return teamDetailDao.getObjListForPage(page,obj);
	}
	@Override
	public DangerTeamDetail getObjectById(Integer id) {
		return teamDetailDao.get(id);
	}
	@Override
	public List<DangerTeamDetail> getListByTeamId(Integer teamId,String orderBy,String order) {
		return teamDetailDao.getListByTeamId(teamId,orderBy,order);
	}
	@Override
	public List<DangerTeamDetail> getListNoPage(DangerTeamDetail obj) {
		return teamDetailDao.findByExample(obj);
	}

}
