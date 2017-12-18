package com.bxsurvey.danger.DangerTeam.dao;

import java.util.List;

import net.framework.base.dao.BaseDao;
import net.framework.httpModel.PageResults;
import net.framework.httpModel.page.Page;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bxsurvey.danger.DangerTeam.model.DangerTeam;

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
public class DangerTeamDao extends BaseDao<DangerTeam, Integer> {
	/*
	 * 新增
	 */
	public void saveTeam(DangerTeam team) {
		this.save(team);
	}
	/*
	 * 编辑
	 */
	public void updateTeam(DangerTeam team) {
		this.update(team);
	}
	/*
	 * 删除
	 */
	public void deleteTeamById(Integer id) {
		DangerTeam team = this.get(id);
		this.delete(team);
		//team.setValidity(false);
		//this.update(team);
	}
	/*
	 * 列表查询
	 */
//	public PageResults findListForJson(PageResults page,SeachTeam seachTeam) {
//		Criteria criteria = getSession().createCriteria(DangerTeam.class);
//		
//		criteria.add(Restrictions.eq("validity", true));//过滤已被删除的数据
//		
//		if( seachTeam.getDepartId() >= 0){
//			
//			criteria.add(Restrictions.eq("departId", seachTeam.getDepartId()));
//			if(!("".equals(seachTeam.getTeamName()))){
//				
//				criteria.add(Restrictions.ilike("teamName", "%" + seachTeam.getTeamName() + "%"));
//			}
//		}else{
//			//根据名称搜索
//			if(null != seachTeam.getTeamName()){
//				criteria.add(Restrictions.ilike("teamName", "%" + seachTeam.getTeamName() + "%"));
//			}
//		}
//		
//		
//		
//		Long totalCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();// 获取数据数量
//		criteria.setProjection(null);
//		List list = criteria.setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize()).list();
//		page.setTotalCount(totalCount);
//		page.setResult(list);
//		return page;
//	}
	
	
	
	/**
	 * @param team  
	 * @Title: findTeamsByDepartIdDatagrid 
	 * @Description: 根据部门id获取应急救援队伍
	 * @param @param departId
	 * @param @param page
	 * @param @return 
	 * @return Datagrid 
	 * @throws 
	 */
	/*public Datagrid findTeamsByDepartIdDatagrid(DangerTeam team, Integer departId, Page page) {
		team.setValidity(true);
		Criteria criteria = this.getCriteria()
				.add(Restrictions.eq("departId", departId));
		this.addExampleForCriteria(criteria, team);
		Long totalCount=this.countByCriteria(criteria);
		this.getCriteriaExtendPage(criteria, page);
		Datagrid datagrid=Datagrid.createDatagrid(totalCount,criteria.list());
		return datagrid;
	}*/
	
	/**
	 * 
	 * Title: findListByXYminOrmax
	 * Description: 通过最大最小坐标查询范围
	 * Created On: 2015-7-6 上午9:36:38
	 * @author ldw 
	 * @param minX
	 * @param maxX
	 * @param minY
	 * @param maxY
	 * @return
	 */
	public List<DangerTeam> findListByXYminOrmax(String minX, String maxX, String minY, String maxY) {
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.eq("validity", true))
				.add(Restrictions.ge("x", minX))
				.add(Restrictions.le("x", maxX))
				.add(Restrictions.ge("y", minY))
				.add(Restrictions.le("y", maxY));

		return criteria.list();
	}
	public List<DangerTeam> findListByXYminOrmax(String minX, String maxX, String minY, String maxY,double r) {
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.eq("validity", true))
				.add(Restrictions.ge("x", minX))
				.add(Restrictions.le("x", maxX))
				.add(Restrictions.ge("y", minY))
				.add(Restrictions.le("y", maxY));

		try {
			return pack(Double.parseDouble(minX),Double.parseDouble(minY),Double.parseDouble(maxX),Double.parseDouble(maxY),r,criteria.list());
		} catch (Exception e) {
			throw new RuntimeException("捕捉不到 特定范围内的数据");
		}
	}	
	public List<DangerTeam> getPersonByDepartId(Integer departId)
	{
		Criteria criteria=this.getCriteria();
		criteria.add(Restrictions.eq("departId",departId));
		return criteria.list();
	}
}
