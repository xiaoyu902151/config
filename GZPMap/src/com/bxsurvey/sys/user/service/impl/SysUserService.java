package com.bxsurvey.sys.user.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import net.framework.httpModel.PageResults;
import net.framework.utils.Encrypt;



import com.bxsurvey.sys.module.dao.SysModuleDao;
import com.bxsurvey.sys.module.model.SysModule;
import com.bxsurvey.sys.role.dao.SysRoleDao;
import com.bxsurvey.sys.role.model.SysRole;
import com.bxsurvey.sys.user.dao.SysUserDao;
import com.bxsurvey.sys.user.dao.SysUserRoleDao;
import com.bxsurvey.sys.user.model.SysUser;
import com.bxsurvey.sys.user.model.SysUserRole;
import com.bxsurvey.sys.user.service.SysUserServiceI;
import com.bxsurvey.util.OperateAnno;
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
@Service("sysUserService")
public class SysUserService implements SysUserServiceI {

	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private SysRoleDao roleDao;
	@Resource
	private SysUserRoleDao userRoleDao;
	@Resource
	private SysModuleDao sysModuleDao;
	/*
	 * 添加
	 */
	@OperateAnno(operateClass=SysUser.class,operateName="添加操作")
	public SysUser save(SysUser user) {
		if (user.getPassword() != null) {
			user.setPassword(Encrypt.e(user.getPassword()));// 密码加密
		}
		SysUser existUser=sysUserDao.findUserByName(user.getLoginName());
		if (existUser== null) {
			sysUserDao.save(user);
		}
		return existUser;
	}
	/*
	 * 重置密码
	 */
	public SysUser resetPasswork(SysUser user) {
		if (user.getPassword() != null) {
			user.setPassword(Encrypt.e(user.getPassword()));// 密码加密
		}
		SysUser existUser=sysUserDao.findUserByName(user.getLoginName());
		if (existUser== null) {
			sysUserDao.save(user);
		}
		return existUser;
	}
	/*
	 * 编辑
	 */
	@OperateAnno(operateClass=SysUser.class,operateName="更新操作")
	public void update(SysUser sysUser) {
		SysUser user = sysUserDao.get(sysUser.getUserId());
		if (user.getPassword().equals(sysUser.getPassword())) {
			sysUserDao.merge(sysUser);
		} else {
			sysUser.setPassword(Encrypt.e(sysUser.getPassword()));// 密码加密
			sysUserDao.merge(sysUser);
		}
	}
	/*
	 * 根据ID删除用户
	 */
	@OperateAnno(operateClass=SysUser.class,operateName="删除操作")
	public void deleteById(String ids) {
		String[] idsStr = ids.split(",");
		for (String id : idsStr) {
			sysUserDao.deleteSysUserById(Integer.parseInt(id));
		}
	}
	/*
	 * 获取用户列表
	 */
	
	public PageResults<SysUser> getListForJson(PageResults<SysUser> page,
			SysUser user) {
		PageResults<SysUser> pages = sysUserDao.getObjListForPage(page, user);
		return pages;
	}
	/*
	 * 获取所有用户
	 */
	public List<SysUser> getAll() {
		return sysUserDao.find("FROM SysUser");
	}
	@Override
	public SysUser findById(String id) {
		Integer[] ids = new Integer[1];
		ids[0] = Integer.parseInt(id);
		List<SysUser> res = sysUserDao.findByIds(ids);
		if(res.size() >0)
			return res.get(0);
		else
			return null;
	}
	
	/*
	 * 角色授权
	 */
	public void putRoles(List<SysUserRole> list) {
		// 清理相关权限
		if (list.size() > 0) {
			userRoleDao.deleteBxSysUserRoleListByUserId(list.get(0)
					.getId().getUserId());
			// 加入修改的权限
			for (SysUserRole bxSysUserRole : list) {
				userRoleDao.saveUserRole(bxSysUserRole);
			}
		}
	
	}

	/*
	 * 获取所有角色值
	 */
	public List<SysRole> getAllRole() {
		return roleDao.getAllRole();
	}
	/*
	 * 获取登录者的角色值
	 */
	public List<SysRole> getLoginRole(int roleId,int userId) {
		return roleDao.getLoginRole(roleId,userId);
	}
	/*
	 * 通过用户ID获取角色值
	 */
	public List<SysRole> getBxSysRoleListByUserId(String userId) {
		
		return roleDao.findRoleListByUserId(Integer.valueOf(userId));
	}

	/*
	 * 根据用户获取角色
	 */
	public SysRole getRoleByUser(SysUser user) {
		StringBuilder hql=new StringBuilder();
		hql.append("SELECT r FROM SysUserRole ur ,SysRole r WHERE r.id=ur.id.roleId AND ur.id.userId=?");
		return roleDao.find(hql.toString(),user.getUserId()).get(0);
	}
	
	/**
	 * 获取用户角色
	 * 
	 */
	public List<SysUserRole> getRoleIdByUserId(Integer id){
		
		return userRoleDao.getRoleIdByUserId(id);
	}
	@Override
	public List<SysModule> findUserAuthorityByPID(Integer userId, String pid) {
		// TODO Auto-generated method stub
		return sysModuleDao.findUserAuthorityByPID(userId,pid);
	}
	@Override
	public List<SysModule> findUserAuthorityByType(Integer userId,Integer type) {
		// TODO Auto-generated method stub
		return sysModuleDao.findUserAuthorityByType(userId,type);
	}
	@Override
	public List<SysModule> findUserAuthorityByBtnId(Integer userId,String pid,String bid) {
		// TODO Auto-generated method stub
		return sysModuleDao.findUserAuthorityByBtnId(userId,pid,bid);
	}
}
