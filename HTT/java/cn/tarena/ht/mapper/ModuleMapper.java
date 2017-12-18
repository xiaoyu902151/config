package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.Module;

public interface ModuleMapper {
	public List<Module> findAll();
	
	//删除模块信息
	public void deleteModules(String[] moduleIds);
	
	//修改状态
	public void updateState(@Param("moduleIds") String[] moduleIds,@Param("state") int state);
	
	//新增模块信息
	public void saveModule(Module module);
	
	
	
	
	
}
