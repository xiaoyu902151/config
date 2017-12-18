package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.jws.WebParam.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.ModuleMapper;
import cn.tarena.ht.pojo.Module;
@Service
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	private ModuleMapper moduleMapepr;
	
	@Override
	public List<Module> findAll() {
		
		return moduleMapepr.findAll();
	}

	@Override
	public void deleteModules(String[] moduleIds) {
		
		//关联删除操作  删除模块和角色的中间表数据
		
		moduleMapepr.deleteModules(moduleIds);
		
	}

	@Override
	public void updateState(String[] moduleIds, int state) {
		
		moduleMapepr.updateState(moduleIds,state);
		
	}

	@Override
	public void saveModule(Module module) {
		module.setModuleId(UUID.randomUUID().toString());
		module.setCreateTime(new Date());
		
		moduleMapepr.saveModule(module);
		
	}

}
