package com.bxsurvey.sys.role.dao;

import java.util.ArrayList;
import java.util.List;

import net.framework.base.dao.BaseDao;
import net.framework.httpModel.PageResults;
import net.framework.utils.StringUtil;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bxsurvey.sys.module.model.SysModule;
import com.bxsurvey.sys.role.model.SysRole;
import com.bxsurvey.sys.role.model.SysRoleModule;
import com.bxsurvey.sys.user.model.SysUser;
/**
 * 
 ***********************************************
 * Copyright (c) 2014 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.dao.BxSysRoleDao.java 
 * Created On: 2014-10-25 上午9:58:20
 * Description: 角色管理dao
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Repository
public class SysRoleDao extends BaseDao<SysRole, Integer> {
	/**
	 * 通过用户ID获取角色列表
	 * @param userId
	 * @return
	 */
	public List<SysRole> findRoleListByUserId(Integer userId) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT DISTINCT role FROM SysRole role, SysUserRole userRole WHERE userRole.id.userId = ? AND userRole.id.roleId = role.roleId");
		
		List<SysRole> list = this.find(hql.toString(), userId);
		if(!list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}
	/**
	 * 
	 * Title: getAllBxSysRole
	 * Description: 获取所有角色列表
	 * Created On: 2014-12-1 下午1:45:57
	 * @author ldw 
	 * @return
	 */
	public List<SysRole> getAllRole() {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM SysRole role");
		return this.find(hql.toString());
	}
	/**
	 * 
	 * Title: getAllBxSysRole
	 * Description: 获取所有角色列表
	 * Created On: 2014-12-1 下午1:45:57
	 * @author ldw 
	 * @return
	 */
	public List<SysRole> getLoginRole(int roleId,int userId) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM SysRole role where role.roleId=? or role.sysUser.userId=?");
		return this.find(hql.toString(),roleId,userId);
	}
	public  List<String> getRoleCategoryByUser(SysUser user) {
		Integer userId = user.getUserId();
		List<SysRole> roles = findRoleListByUserId(userId);
		List<String> roleCategorys = new ArrayList<String>();
		/*for(SysRole role:roles){
			if(role.getParamsId() != null){
				SysParams param = this.findUniqueBy(SysParams.class, "SysParams.paramsId", role.getParamsId());
				roleCategorys.add(param.getParamsValue());
			}
		}*/
		return roleCategorys;
	}
	/*
	 * 保存角色
	 */
	public Integer saveForSerializable(SysRole role) {
		// TODO Auto-generated method stub
		return (Integer) this.getSession().save(role);
	}
	
	//根据角色id来查询的父角色id
	public Integer getRolePid(Integer childId){
		
		SysRole sysRole =(SysRole) getSession().createQuery(" from com.bxsurvey.sys.role.model.SysRole where role_id = ?").setInteger(0, childId).list().get(0);
		
		System.out.println("sysRoleDao sysRole:" + sysRole);
		return sysRole.getRolePid();
	}
	
	//根据父id获取资源
	public List<SysModule> getModule(Integer parentId){
		
		List<SysRoleModule> sysRoleModule = getSession().createQuery("from com.bxsurvey.sys.role.model.SysRoleModule where role_id = ? ").setInteger(0, parentId).list();
		
		List<SysModule> listSysModule = new ArrayList<SysModule>();
		
		
		//根据模块id来找出一个个模块
		for(SysRoleModule  srm :  sysRoleModule){
			
				//根据模块id来查询出模块
			listSysModule.add((SysModule)getSession().createQuery("from com.bxsurvey.sys.module.model.SysModule where module_id = ?").setString(0, srm.getId().getModuleId()).list().get(0));
		
		}
		return listSysModule;
	
	}
	
	/**
	 * 
	 * Title: getObjListForPage
	 * Description: 主要用于easyui datagrid 查询
	 * Created On: 2015-3-16 上午9:58:30
	 * @author ldw 
	 * @param page
	 * @param t
	 * @return
	 */
	public PageResults<SysRole> getObjListForPage(PageResults<SysRole> page, SysRole t) {
		Criteria criteria = this.getCriteria();
		Example example = Example.create(t);
		example.enableLike(MatchMode.ANYWHERE);// 匹配模式,使用模糊查询必填项。
		example.excludeNone();// 空的不做查询条件
		example.excludeZeroes();// 0不要查询
		example.ignoreCase(); // 不区分大小写
		criteria.add(example);
		//criteria.add(Restrictions.or(Restrictions.eq("sysUser.userId", t.getSysUser().getUserId()), Restrictions.eq("roleId", t.getRoleId())));
		criteria.add(Restrictions.eq("sysUser.userId", t.getSysUser().getUserId()));
		Long totalCount =(Long) criteria.setProjection(Projections.rowCount()).uniqueResult(); 
		criteria.setProjection(null);
		List list = null;
		if (!StringUtil.trimToEmpty(page.getOrder()).equals("") && !StringUtil.trimToEmpty(page.getOrderBy()).equals(""))
			if(PageResults.ASC.equals(page.getOrder()))
				list = criteria.addOrder(Order.asc(page.getOrderBy())).setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize()).list();
			else
				list = criteria.addOrder(Order.desc(page.getOrderBy())).setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize()).list();
		else
			list = criteria.setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize()).list();
        page.setTotalCount(totalCount);
        page.setResult(list);
		return page;
	}
}
