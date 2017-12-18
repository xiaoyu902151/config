package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.User;

public interface UserService {
	public List<User> findAll();
	
	//删除用户信息
	public void deleteUsers(String[] userIds);
	
	//修改用户的状态
	public void updateState(String[] userIds, int state);
	
	//用户的新增
	public void saveUser(User user);
	
	public User findUserById(String userId);
	
	//更新User数据
	public void updateUser(User user);
	
	//保存 用户角色分配
	public void saveUserRole(String userId, String[] roleIds);
	
	//根据用户名和密码查询用户信息
	public User findUserByUP(String userName, String password);

	//根据用户名查询信息
	public User findUserByUserName(String username);
	
	//根据userId查询模块名称
	public List<String> findPListByUserId(String userId);
	
}
