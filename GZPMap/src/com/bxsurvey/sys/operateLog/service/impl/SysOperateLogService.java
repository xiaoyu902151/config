package com.bxsurvey.sys.operateLog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import net.framework.httpModel.PageResults;
import net.framework.utils.Encrypt;



import com.bxsurvey.sys.module.dao.SysModuleDao;
import com.bxsurvey.sys.module.model.SysModule;
import com.bxsurvey.sys.operateLog.dao.SysOperateLogDao;
import com.bxsurvey.sys.operateLog.service.SysOperateLogServiceI;
import com.bxsurvey.sys.role.dao.SysRoleDao;
import com.bxsurvey.sys.role.model.SysRole;
import com.bxsurvey.sys.user.dao.SysUserDao;
import com.bxsurvey.sys.user.dao.SysUserRoleDao;
import com.bxsurvey.sys.user.model.SysLog;
import com.bxsurvey.sys.user.model.SysUser;
import com.bxsurvey.sys.user.model.SysUserRole;
import com.bxsurvey.sys.user.service.SysUserServiceI;
/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.service.sys.impl.SysUserService.java 
 * Created On: 2015-4-23 上午11:01:42
 * Description:  用户表业务接口实现类
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Service("sysOperateLogService")
public class SysOperateLogService implements SysOperateLogServiceI {

	@Resource
	private SysOperateLogDao sysOperateLogDao;
	@Resource
	private SysUserDao sysUserDao;
	
	/*
	 * 获取用户列表
	 */
	public PageResults<SysLog> getListForJson(PageResults<SysLog> page,
			SysLog user) {
		if(!"".equals(user.getUser().getLoginName())){
		  SysUser sysUser=	sysUserDao.findUserByName(user.getUser().getLoginName());
		  user.getUser().setUserId(sysUser.getUserId());
		}
		 
		PageResults<SysLog> pages = sysOperateLogDao.getObjListForPage(page, user);
		return pages;
	}
	/*
	 * 根据ID删除log
	 */
	public void deleteById(String ids) {
		String[] idsStr = ids.split(",");
		for (String id : idsStr) {
			sysOperateLogDao.deleteSysLogById(Integer.parseInt(id));
		}
	}
	
}
