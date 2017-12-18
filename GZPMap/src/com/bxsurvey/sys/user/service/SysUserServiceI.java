package com.bxsurvey.sys.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bxsurvey.sys.module.model.SysModule;
import com.bxsurvey.sys.role.model.SysRole;
//import com.bxsurvey.sys.role.model.SysRole;
import com.bxsurvey.sys.user.model.SysUser;
import com.bxsurvey.sys.user.model.SysUserRole;

import net.framework.httpModel.PageResults;



public interface SysUserServiceI {
	public SysUser save(SysUser user);
	public void update(SysUser sysUser);
	public void deleteById(String ids);
	public PageResults<SysUser> getListForJson(PageResults<SysUser> page,
			SysUser user);
	public List<SysUser> getAll();
	public void putRoles(List<SysUserRole> list);
	public List<SysRole> getAllRole();
	public List<SysRole> getBxSysRoleListByUserId(String userId);
	public SysRole getRoleByUser(SysUser user);
	public List<SysUserRole> getRoleIdByUserId(Integer id);
	public SysUser findById(String id);
	public List<SysModule> findUserAuthorityByPID(Integer userId,String pid);
	public List<SysModule> findUserAuthorityByType(Integer userId,Integer type);
	public List<SysModule> findUserAuthorityByBtnId(Integer userId,String pid,String bid);
	public List<SysRole> getLoginRole(int roleId,int userId);
}
