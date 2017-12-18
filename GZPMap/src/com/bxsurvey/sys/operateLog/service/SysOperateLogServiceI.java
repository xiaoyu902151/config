package com.bxsurvey.sys.operateLog.service;

import java.util.List;

import com.bxsurvey.sys.module.model.SysModule;
import com.bxsurvey.sys.role.model.SysRole;
//import com.bxsurvey.sys.role.model.SysRole;
import com.bxsurvey.sys.user.model.SysLog;
import com.bxsurvey.sys.user.model.SysUser;
import com.bxsurvey.sys.user.model.SysUserRole;

import net.framework.httpModel.PageResults;



public interface SysOperateLogServiceI {
	public PageResults<SysLog> getListForJson(PageResults<SysLog> page,
			SysLog sysLog);
	public void deleteById(String ids);
}
