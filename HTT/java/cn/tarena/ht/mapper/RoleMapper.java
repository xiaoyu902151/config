package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.tarena.ht.pojo.Role;

public interface RoleMapper {
	/*@Select
	@Update    单表操作时可以通过注解形式进行查询操作
	@Delete
	@Insert*/
	
	@Select("select * from role_p ")
	public List<Role> findAll();
	
	public void saveRole(Role role);
	
	@Select("select role_id from role_user_p where user_id = #{userId}")
	public List<String> findRoleListByUserId(String userId);
	
	//查询模块信息根据roleId
	@Select("select module_id from role_module_p where role_id = #{roleId}")
	public List<String> findRoleModuleList(String roleId);
	
	@Insert("insert into role_module_p(module_id,role_id) values(#{moduleId},#{roleId})")
	public void saveRoleModule(@Param("roleId") String roleId,@Param("moduleId") String moduleId);
	
	@Delete("delete from role_module_p where role_id = #{roleId}")
	public void deleteRoleModule(String roleId);
	
	
	
}
