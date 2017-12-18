package com.bxsurvey.danger.DangerTeam.dao;

import java.util.List;

import net.framework.base.dao.BaseDao;
import net.framework.httpModel.PageResults;
import net.framework.httpModel.page.Page;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bxsurvey.danger.DangerSupplies.model.DangerSuppliesDetail;
import com.bxsurvey.danger.DangerTeam.model.DangerTeam;
import com.bxsurvey.danger.DangerTeam.model.DangerTeamDetail;

/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.dao.danger.DangerTeamDao.java 
 * Created On: 2015-5-12 下午6:56:11
 * Description: 应急救援队伍dao
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Repository
public class DangerTeamDetailDao extends BaseDao<DangerTeamDetail, Integer> {
	/*
	 * 新增
	 */
	public void saveTeam(DangerTeamDetail team) {
		this.save(team);
	}
	/*
	 * 编辑
	 */
	public void updateTeam(DangerTeamDetail team) {
		this.update(team);
	}
	/*
	 * 删除
	 */
	public void deleteTeamById(Integer id) {
		DangerTeamDetail team = this.get(id);
		this.delete(team);
		//team.setValidity(false);
		//this.update(team);
	}

	public List<DangerTeamDetail> getListByTeamId(Integer teamId,String orderBy,String order)
	{
		StringBuilder hql = new StringBuilder();
		if(order.toUpperCase().equals("ASC"))
			hql.append("from DangerTeamDetail detail where detail.dangerTeam.id = ?  order by "+ orderBy +" ASC");
		else
			hql.append("from DangerTeamDetail detail where detail.dangerTeam.id = ?  order by "+ orderBy +" DESC");
		
		List<DangerTeamDetail> list = this.find(hql.toString(), teamId);
		return list;
	}
}
