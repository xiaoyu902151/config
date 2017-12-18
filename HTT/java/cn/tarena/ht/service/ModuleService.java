package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Module;

public interface ModuleService {
	public List<Module> findAll();
	
	//删除模块信息
	public void deleteModules(String[] moduleIds);
	
	//修改状态
	public void updateState(String[] moduleIds, int state);
	
	//新增模块信息
	public void saveModule(Module module);
	
}
