package com.bxsurvey.login.service.impl;

import javax.security.auth.spi.LoginModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxsurvey.login.service.LoginServiceI;
import com.bxsurvey.sys.user.dao.SysUserDao;
import com.bxsurvey.sys.user.model.SysUser;
import com.bxsurvey.util.OperateAnno;
@Service
public class LoginService implements LoginServiceI {
	@Autowired
	private SysUserDao userDao;

	@Override
	@OperateAnno(operateClass=SysUser.class,operateName="登录操作")
	public SysUser findUserByName(String name) {
		return userDao.findUserByName(name);
	}
	
	
}
