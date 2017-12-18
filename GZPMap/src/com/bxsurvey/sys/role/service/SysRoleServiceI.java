package com.bxsurvey.sys.role.service;

import java.util.List;

import net.framework.httpModel.PageResults;
import net.framework.httpModel.Tree;

import com.bxsurvey.sys.role.model.SysRole;
import com.bxsurvey.sys.user.model.SysUser;
import com.bxsurvey.sys.user.model.SysUserRole;


public interface SysRoleServiceI {

	public void save(SysRole role, String rs);
	public void update(SysRole role, String rs);
	public void deleteById(String ids);
	public PageResults<SysRole> getListForJson(PageResults<SysRole> page, SysRole role);
	public List<Tree> findRoleResourceTree(String roleId);
	public List<Tree> findAllTree();
	public List<Tree> findOwerTreeByRoleId(Integer id);
	
	public SysRole getRoleByUserId(Integer userId);
	public SysRole findById(String id);
}
