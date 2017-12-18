package com.bxsurvey.sys.user.dao;

import java.util.List;

import net.framework.base.dao.BaseDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.bxsurvey.sys.user.model.SysUserRole;
/**
 * 
 ***********************************************
 * Copyright (c) 2014 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.dao.BxSysUserRoleDao.java 
 * Created On: 2014-12-1 上午9:50:03
 * Description: 用户角色dao
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Repository
public class SysUserRoleDao extends BaseDao<SysUserRole, Integer> {
	private static Logger logger = Logger.getLogger(SysUserRoleDao.class);
	
	/**
	 * 保存用户角色ID
	 * @param bxSysUserRole
	 */
	public void saveBxSysUserRole(SysUserRole bxSysUserRole) {
		this.save(bxSysUserRole);
	}
	/**
	 * 删除用户角色ID
	 * @param id
	 */
	public void deleteBxSysUserRoleById(Integer id) {
		this.deleteById(id);
	}
	/**
	 * 编辑用户角色ID
	 * @param bxSysUserRole
	 */
	public void updateBxSysUserRole(SysUserRole bxSysUserRole) {
		this.update(bxSysUserRole);
	}
	/**
	 * 
	 * Title: deleteBxSysUserRoleListByUserId
	 * Description: 通过用户ID删除相关数据
	 * Created On: 2014-12-1 下午3:30:31
	 * @author ldw 
	 * @param userId
	 */
	public void deleteBxSysUserRoleListByUserId(Integer userId) {
		StringBuilder hql = new StringBuilder();
		hql.append("DELETE FROM SysUserRole where id.userId = " + String.valueOf(userId));
		this.executeHql(hql.toString());
	}
	
	public void saveUserRole(SysUserRole Role)
	{
		StringBuilder hql = new StringBuilder();
		hql.append("insert into sys_user_role (user_id,role_id) values("+Role.getId().getUserId()+","+Role.getId().getRoleId()+")");
		this.executeSql(hql.toString());
	}
	
	
	//根据用户的id来获取用户的角色id
	public List<SysUserRole> getRoleIdByUserId(Integer userId){
		
		List<SysUserRole> list = getSession().createQuery(" from com.bxsurvey.sys.user.model.SysUserRole where user_id = ? ").setInteger(0, userId).list();
		
		return list;
	}
	

	
}
