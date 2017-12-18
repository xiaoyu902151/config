package com.bxsurvey.sys.depart.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;

import com.bxsurvey.sys.depart.model.SysDepart;
import net.framework.base.dao.BaseDao;
import net.framework.httpModel.PageResults;
/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.dao.sys.SysDepartDao.java 
 * Created On: 2015-5-5 上午9:01:50
 * Description: 组织机构表dao
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Repository
public class SysDepartDao extends BaseDao<SysDepart, Integer> {
	/*
	 * 添加
	 */
	public void saveSysDepart(SysDepart depart) {
		this.save(depart);
	}
	/*
	 * 编辑
	 */
	public void updateSysDepart(SysDepart depart) {
		this.update(depart);
	}
	/*
	 * 删除
	 */
	public void deleteSysDepartById(Integer id) {
		this.deleteById(id);
		
	}
	/*
	 * 查询列表
	 */
	public PageResults<SysDepart> findListForJson(PageResults<SysDepart> page, SysDepart depart) {
		Criteria criteria = this.createCriteria(depart);
		criteria.add(Restrictions.eq("departValidity", true));//判断是否可用
		Long totalCount =(Long) criteria.setProjection(Projections.rowCount()).uniqueResult(); 
		criteria.setProjection(null); 
        List list = criteria.setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize()).list();
        page.setTotalCount(totalCount);
        page.setResult(list);
		return page;
	}
//	/** 
//	 * @Title: getbyDeviceId 
//	 * @Description: 根据设备id找部门
//	 * @param @param deviceId
//	 * @param @return 
//	 * @return SysDepart 
//	 * @throws 
//	 */
//	public SysDepart getByDeviceId(int deviceId) {
//		DetachedCriteria device = DetachedCriteria.forClass(DangerDevi.class, "device")
//				.add(Restrictions.eq("deviceId", deviceId))
//				.add(Property.forName("device.sysDepart.departId").eqProperty("depart.departId"));
//		
//		Criteria depart= this.getSession().createCriteria(this.entityClass, "depart")
//				.add(Subqueries.exists(device.setProjection(Projections.property("device.sysDepart.departId"))));
//			
//		return (SysDepart) depart.uniqueResult();
//	}
	
	/** 
	 * @Title: search 
	 * @Description: 关键字搜索
	 * @param @param kw
	 * @param @return 
	 * @return List<SysDepart> 
	 * @throws 
	 */
	public List<SysDepart> search(String kw) {
		SysDepart departExp=new SysDepart();
		departExp.setDepartValidity(true);
		departExp.setDepartName(kw);
		return this.findByExample(departExp);
	}
	
	public List<SysDepart> findAllExit(Integer departId) {
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.eq("departValidity", true));//判断是否可用
//		criteria.add(Restrictions.eq("departId",departId));
		return criteria.list();
	}
	
	public SysDepart findByFid(String fid) {
		SysDepart departExp=new SysDepart();
		departExp.setDepartValidity(true);
		return this.findByExample(departExp).get(0);
	}
	
//	public  List<SysDepart> getDepartsByPort(List<DangerMt> MtList){
//		StringBuilder sb = new StringBuilder();
//		sb.append("select DISTINCT(s) from SysDepart s,DangerDepartMt ddm  where s.departId = ddm.id.departId ")
//		   .append("and ddm.id.mtId IN (");
//		for(DangerMt dm: MtList){
//			sb.append(dm.getMtId()+",");
//		}
//		sb.deleteCharAt(sb.lastIndexOf(","));
//		sb.append(")");
//		List<SysDepart> list = this.find(sb.toString());
//		return list;	
//	}
}
