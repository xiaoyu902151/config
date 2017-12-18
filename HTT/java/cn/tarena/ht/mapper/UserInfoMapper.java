package cn.tarena.ht.mapper;

import java.util.List;

import cn.tarena.ht.pojo.UserInfo;

public interface UserInfoMapper {
	
	//关联删除userInfo信息
	public void deleteUserInfos(String[] userIds);
	
	//查询全部的userInfo信息
	public List<UserInfo> findManagerList();
	
	//用户新增操作
	public void saveUserInfo(UserInfo userInfo);
	
	//修改userinfo表
	public void updateUser(UserInfo userInfo);
}
