package com.bxsurvey.sys.role.dao;

import net.framework.base.dao.BaseDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.bxsurvey.sys.role.model.SysRoleModule;

/**
 * 
 ***********************************************
 * Copyright (c) 2014 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.dao.BxSysRoleModuleDao.java 
 * Created On: 2014-12-1 上午9:26:37
 * Description: 角色菜单表dao
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Repository
public class SysRoleModuleDao extends BaseDao<SysRoleModule, Integer> {
	private static Logger logger = Logger.getLogger(SysRoleModuleDao.class);
	
	/**
	 * 保存角色菜单ID
	 * @param bxSysOgran
	 */
	public void saveBxSysRoleModule(SysRoleModule bxSysRoleModule) {
		this.save(bxSysRoleModule);
	}
	/**
	 * 删除角色菜单ID
	 * @param id
	 */
	public void deleteBxSysRoleModuleById(Integer id) {
		this.deleteById(id);
	}
	/**
	 * 编辑角色菜单ID
	 * @param bxSysOgran
	 */
	public void updateBxSysRoleModule(SysRoleModule bxSysRoleModule) {
		this.update(bxSysRoleModule);
	}
	/**
	 * 
	 * Title: deleteBxSysRoleModuleListByUserId
	 * Description: 通过角色ID删除相应的数据
	 * Created On: 2014-12-2 下午2:14:33
	 * @author ldw 
	 * @param roleid
	 */
	public void deleteBxSysRoleModuleListByRoleId(Integer roleid) {
		StringBuilder hql = new StringBuilder();
		hql.append("DELETE FROM SysRoleModule where id.roleId = " + String.valueOf(roleid));
		this.executeHql(hql.toString());
	}
	
	public void saveSysRoleModuleObject(SysRoleModule bxSysRoleModule)
	{
		StringBuilder hql=new StringBuilder();
		hql.append("insert into sys_role_module(role_id,module_id) values("+bxSysRoleModule.getId().getRoleId()+"," +bxSysRoleModule.getId().getModuleId()+")");
		this.executeSql(hql.toString());
	}
}
