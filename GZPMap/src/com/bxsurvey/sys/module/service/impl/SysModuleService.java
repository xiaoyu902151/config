package com.bxsurvey.sys.module.service.impl;

import java.util.List;

import javax.annotation.Resource;
import net.framework.httpModel.PageResults;
import org.springframework.stereotype.Service;

import com.bxsurvey.sys.module.dao.SysModuleDao;
import com.bxsurvey.sys.module.model.SysModule;
import com.bxsurvey.sys.module.service.SysModuleServiceI;

/**
 * 
 ***********************************************
 * Copyright (c) 2014 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.service.BxSysUserService.java 
 * Created On: 2014-10-15 上午10:42:01
 * Description: 模块表业务逻辑操作
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Service("sysModuleService")
public class SysModuleService implements SysModuleServiceI{
	@Resource
	private SysModuleDao moduleDao;

	public void save(SysModule obj) {
		moduleDao.save(obj);
	}

	public void update(SysModule module) {
//		if(moduleDao.getObjectById(module.getModuleId()) != null){
//			moduleDao.merge(module);
//		} else {
//			moduleDao.save(module);
//		}
		moduleDao.saveOrUpdate(module);
	}

	public void deleteById(String ids) {
		String[] idsStr = ids.split(",");
		for(String id : idsStr) {
			moduleDao.deleteModuleById(id);
		}
	}

	public PageResults<SysModule> getListForJson(PageResults<SysModule> page, SysModule obj) {
		return moduleDao.getObjListForPage(page, obj);
	}
	
	public List<String> findPathListByUserId(Integer userId) {
		return moduleDao.findPathListByUserId(userId);
	}

	@Override
	public SysModule findById(String id) {
		//Integer[] ids = new Integer[1];
		//ids[0] = Integer.parseInt(id);
		String[] ids = new String[1];
		ids[0] = id;
		List<SysModule> res = moduleDao.findByIds(ids);
		if(res.size() >0)
			return res.get(0);
		else
			return null;
	}
}
