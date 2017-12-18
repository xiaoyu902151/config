package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.UserInfoMapper;
import cn.tarena.ht.mapper.UserMapper;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.tool.MD5HashPassword;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public List<User> findAll() {
		
		return userMapper.findAll();
	}

	
	@Override
	public void deleteUsers(String[] userIds) {
		//先删除从表数据,再删主表,这样能够在有主外键约束的表中进行正确删除
		userInfoMapper.deleteUserInfos(userIds);
		
		//删除主表数据
		userMapper.deleteUsers(userIds);
	}


	@Override
	public void updateState(String[] userIds, int state) {
		
		userMapper.updateState(userIds,state);
		
	}


	@Override
	public void saveUser(User user) {
		
		String id = UUID.randomUUID().toString();
		//准备完整的user数据
		user.setUserId(id);
		user.setCreateTime(new Date());
		String password = MD5HashPassword.getMd5Hash(user.getPassword(), user.getUsername());
		user.setPassword(password);
		userMapper.saveUser(user);
		
		UserInfo userInfo = user.getUserInfo();
		userInfo.setUserInfoId(id);
		userInfo.setCreateTime(user.getCreateTime());
		
		userInfoMapper.saveUserInfo(userInfo);
		
	}


	@Override
	public User findUserById(String userId) {
		
		return userMapper.findUserById(userId);
	}


	@Override
	public void updateUser(User user) {
		
		UserInfo userInfo = user.getUserInfo();
		
		//添加修改日期
		user.setUpdateTime(new Date());
		
		//info对象添加修改日期
		userInfo.setUpdateTime(user.getUpdateTime());
		//为UserInfoId赋值
		userInfo.setUserInfoId(user.getUserId());
		
		userMapper.updateUser(user);
		userInfoMapper.updateUser(userInfo);
		
	}


	@Override
	public void saveUserRole(String userId, String[] roleIds) {
		
		//为了反之重复提交问题 先删除数据
		userMapper.deleteUserRoleByUserId(userId);
		//分次插入数据库
		for (String roleId : roleIds) {
			userMapper.saveUserRole(userId,roleId);
		}
		
	}

	
	@Override
	public User findUserByUP(String userName, String password) {
		
		return userMapper.findUserByUP(userName,password);
	}


	@Override
	public User findUserByUserName(String username) {
		
		return userMapper.findUserByUserName(username);
	}


	@Override
	public List<String> findPListByUserId(String userId) {
	
		return userMapper.findPListByUserId(userId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
