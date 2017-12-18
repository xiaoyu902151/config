package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Role;

public interface RoleService {
	public List<Role> findAll();

	public void saveRole(Role role);
	
	//根据userId查询角色信息Id
	public List<String> findRoleListByUserId(String userId);
	
	//根据roleId查询模块信息
	public List<String> findRoleModuleList(String roleId);
	
	//保存角色和模块关联关系
	public void saveRoleModule(String roleId, String[] moduleIds);
}
