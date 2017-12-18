package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.User;

public interface UserMapper {
	//关联查询用户信息
	public List<User> findAll();
	
	//删除用户信息
	public void deleteUsers(String[] userIds);
	
	//修改用户状态
	public void updateState(@Param("userIds")String[] userIds,@Param("state")int state);
	
	//用户新增
	public void saveUser(User user);
	
	//根据id查询信息
	public User findUserById(String userId);
	
	//修改user表
	public void updateUser(User user);
	
	
	//保存用户和角色的分配信息
	@Insert("insert into role_user_p(role_id,user_id) values(#{roleId},#{userId})")
	public void saveUserRole(@Param("userId")String userId,@Param("roleId")String roleId);
	
	//删除之前的数据
	@Delete("delete from role_user_p where user_id = #{userId}")
	public void deleteUserRoleByUserId(String userId);
	
	
	//根据用户名和密码查询数据
	public User findUserByUP(@Param("userName") String userName,@Param("password")String password);
	
	//根据用户名查询信息
	public User findUserByUserName(String username);
	
	//根据userId查询模块信息
	public List<String> findPListByUserId(String userId);
	
	
	
	
	
	
}
