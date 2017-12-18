package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.RoleMapper;
import cn.tarena.ht.pojo.Role;
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Role> findAll() {
		
		return roleMapper.findAll();
	}

	@Override
	public void saveRole(Role role) {
		//为role赋值Id
		role.setRoleId(UUID.randomUUID().toString());
		role.setCreateTime(new Date());
		
		roleMapper.saveRole(role);
	}

	@Override
	public List<String> findRoleListByUserId(String userId) {
		
		return roleMapper.findRoleListByUserId(userId);
	}

	@Override
	public List<String> findRoleModuleList(String roleId) {
		
		return roleMapper.findRoleModuleList(roleId);
	}

	@Override
	public void saveRoleModule(String roleId, String[] moduleIds) {
		//为了避免重复提交 先删除后新增
		roleMapper.deleteRoleModule(roleId);
		
		//保存角色和模块的关联关系
		for (String moduleId : moduleIds) {
			roleMapper.saveRoleModule(roleId,moduleId);
		}
		
	}

}
