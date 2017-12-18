package com.bxsurvey.sys.module.service;

import java.util.List;

import com.bxsurvey.sys.module.model.SysModule;

import net.framework.httpModel.PageResults;

/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.service.sys.SysModuleServiceI.java 
 * Created On: 2015-5-5 上午9:34:40
 * Description: 资源表业务接口
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
public interface SysModuleServiceI {
	/*
	 * 添加
	 */
	public void save(SysModule obj);
	/*
	 * 编辑
	 */
	public void update(SysModule module);
	/*
	 * 删除
	 */
	public void deleteById(String ids);
	/*
	 * 列表查询
	 */
	public PageResults<SysModule> getListForJson(PageResults<SysModule> page, SysModule obj);
	
	public List<String> findPathListByUserId(Integer userId);
	public SysModule findById(String id);
}
