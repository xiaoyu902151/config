package com.bxsurvey.login.service;

import com.bxsurvey.sys.user.model.SysUser;

public interface LoginServiceI {
	public SysUser findUserByName(String name);
}
