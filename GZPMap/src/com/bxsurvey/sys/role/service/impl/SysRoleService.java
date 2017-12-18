package com.bxsurvey.sys.role.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.framework.httpModel.PageResults;
import net.framework.httpModel.Tree;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxsurvey.sys.module.dao.SysModuleDao;
import com.bxsurvey.sys.module.model.SysModule;
import com.bxsurvey.sys.role.dao.SysRoleDao;
import com.bxsurvey.sys.role.dao.SysRoleModuleDao;
import com.bxsurvey.sys.role.model.SysRole;
import com.bxsurvey.sys.role.model.SysRoleModule;
import com.bxsurvey.sys.role.model.SysRoleModuleId;
import com.bxsurvey.sys.role.service.SysRoleServiceI;
import com.bxsurvey.sys.user.dao.SysUserRoleDao;
import com.bxsurvey.sys.user.model.SysUser;
import com.bxsurvey.sys.user.model.SysUserRole;

/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.service.sys.impl.SysRoleService.java 
 * Created On: 2015-4-23 下午4:10:29
 * Description: 角色表业务接口实现
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Service("sysRoleService")
public class SysRoleService implements SysRoleServiceI{
	@Resource
	private SysRoleDao roleDao;
	@Resource
	private SysModuleDao moduleDao;
	@Resource
	private SysRoleModuleDao roleModuleDao;
	@Autowired(required=false)
	private HttpServletRequest request;
	
	
	/**
	 * 保存角色信息
	 * @param bxSysOgran
	 */
	public void save(SysRole role, String rs) {
		Integer roleId = (Integer)roleDao.saveForSerializable(role);
		//保存角色资源关联关系
		JSONArray array = JSONArray.fromObject(rs);
		for (int i = 0; i < array.size(); i++) {
			JSONObject json = JSONObject.fromObject(array.get(i));
			SysRoleModule roleMenu = new SysRoleModule();
			SysRoleModuleId id  = new SysRoleModuleId();
			id.setModuleId(json.get("ressId").toString());
			id.setRoleId(roleId);
			roleMenu.setId(id);
			roleModuleDao.saveSysRoleModuleObject(roleMenu);
		}
		
	}
	/**
	 * 编辑角色信息
	 * @param bxSysOgran
	 */
	public void update(SysRole role, String rs) {
		roleDao.update(role);
		/**
		 * 删除现有的角色菜单关系
		 */
		roleModuleDao.deleteBxSysRoleModuleListByRoleId(role.getRoleId());
		/**
		 * 更新角色资源关联表
		 */
		JSONArray array = JSONArray.fromObject(rs);
		for (int i = 0; i < array.size(); i++) {
			JSONObject json = JSONObject.fromObject(array.get(i));
			SysRoleModule roleMenu = new SysRoleModule();
			SysRoleModuleId id  = new SysRoleModuleId();
			id.setModuleId(json.get("ressId").toString());
			id.setRoleId(role.getRoleId());
			roleMenu.setId(id);
			roleModuleDao.saveSysRoleModuleObject(roleMenu);
		}
	}
	/**
	 * 删除角色信息
	 * @param ids
	 */
	public void deleteById(String ids) {
		String[] idsStr = ids.split(",");
		for(String id : idsStr) {
			roleDao.deleteById(Integer.parseInt(id));
		}
	}
	/**
	 * 列表查询
	 * @param page
	 * @param bxSysOgran
	 * @return
	 */
	public PageResults<SysRole> getListForJson(PageResults<SysRole> page, SysRole role) {
		return roleDao.getObjListForPage(page, role);
	}
	/**
	 * 
	 * Title: findRoleResourceTree
	 * Description: 查询总资源和已选择资源
	 * Created On: 2014-12-1 下午5:04:46
	 * @author ldw 
	 * @param roleId
	 * @return
	 */
	
	public List<Tree> findRoleResourceTree(String roleId){
		List<Tree> nodes = new ArrayList<Tree>();
		
//		if(!"1".equals(roleId)){
//			
//			
//			//根据角色id获取父角色id
//			Integer rolePid = roleDao.getRolePid(Integer.valueOf(roleId));
//			System.out.println("rolePid : " + rolePid );
//			//根据父角色来获取模块
//			List<SysModule> module = roleDao.getModule(rolePid);
//			
//			//根据父角色模块封装成tree
//			for(SysModule m : module){
//				
//				Tree tree = new Tree();
//				tree.setPid(m.getModulePid());
//				String moStr = m.getModuleId();
//				tree.setId(moStr);
//				tree.setName(m.getModuleName());
//				tree.setIsParent(true);
//				tree.put("open", true);
//				
//				nodes.add(tree);
//			}
//		}else{
		   SysRole loginRole=  (SysRole) request.getSession().getAttribute("sysRole");//当前用户的role对象
		   SysUser sysUser=  (SysUser) request.getSession().getAttribute("userBO");
			//查询该角色拥有的资源
			List<SysModule> roleResourceList = moduleDao.getRoleResourceByRoleId(roleId);
			List<SysModule> resourceList=null;
			if(sysUser.getSysUser()==null){//超级管理员用户
				//查询总资源树
				resourceList = moduleDao.getAllModule();
			}else{
				//查询登录用户该角色拥有的资源
				resourceList = moduleDao.getRoleResourceByRoleId(loginRole.getRoleId().toString());
			}
			
			for(SysModule r : resourceList)
			{
				Tree tree = new Tree();
				tree.setPid(r.getModulePid());
				String moStr = r.getModuleId();
				tree.setId(moStr);
				tree.setName(r.getModuleName());
				tree.setIsParent(true);
				tree.put("open", true);
				if(roleResourceList != null && !roleResourceList.equals("")) {
					for (SysModule resource : roleResourceList) {
						String reStr = resource.getModuleId();
						if (reStr.equals(moStr)) {
							tree.put("checked", true);
						}
					}
				}
				nodes.add(tree);
			}
//			
//		}
		
		return nodes;
	}
	
	public List<Tree> findAllTree() {
		List<Tree> nodes = new ArrayList<Tree>();
		//查询总资源树
		//List<SysModule> resourceList = moduleDao.getAllModule();
		//查询相应角色的资源树
		SysRole loginRole=  (SysRole) request.getSession().getAttribute("sysRole");//当前用户的role对象
		List<SysModule> resourceList =null;
        if(loginRole.getParamsId()==1){
        	resourceList=moduleDao.getAllModule();
		}else{
			resourceList=moduleDao.getRoleResourceByRoleId(loginRole.getRoleId().toString());
		}
		 
		for(SysModule r : resourceList)
		{
			Tree tree = new Tree();
			tree.setPid(r.getModulePid());
			String moStr = r.getModuleId();
			tree.setId(moStr);
			tree.setName(r.getModuleName());
			tree.setIsParent(true);
			tree.put("open", true);
			nodes.add(tree);
		}
		return nodes;
	}
	
	public List<Tree> findOwerTreeByRoleId(Integer id) {
		List<Tree> nodes = new ArrayList<Tree>();
		//查询总资源树
		
		
		
		List<SysModule> resourceList = moduleDao.findOwerModuleByRoleId(id);
		for(SysModule r : resourceList)
		{
			Tree tree = new Tree();
			tree.setPid(r.getModulePid());
			String moStr = r.getModuleId();
			tree.setId(moStr);
			tree.setName(r.getModuleName());
			tree.setIsParent(true);
			tree.put("open", true);
			nodes.add(tree);
		}
		return nodes;
	}
	
	public SysRole getRoleByUserId(Integer userId){
		List<SysRole> list=roleDao.findRoleListByUserId(userId);
		if(list!=null){
			return list.get(0);
		}else{
			return null;
		}
		
		
	}
	@Override
	public SysRole findById(String id) {
		Integer[] ids = new Integer[1];
		ids[0] = Integer.parseInt(id);
		List<SysRole> res = roleDao.findByIds(ids);
		if(res.size() >0)
			return res.get(0);
		else
			return null;
	}
}
