package com.bxsurvey.sys.module.dao;

import java.util.ArrayList;
import java.util.List;

import net.framework.base.dao.BaseDao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bxsurvey.sys.module.model.SysModule;
//import com.bxsurvey.sys.role.model.SysRoleModule;
//import com.bxsurvey.sys.role.model.SysRoleModuleId;

/**
 * 
 ***********************************************
 * Copyright (c) 2014 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.dao.BxSysModuleDao.java 
 * Created On: 2014-10-25 上午11:37:35
 * Description: 模块管理dao
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Repository
public class SysModuleDao extends BaseDao<SysModule, String> {
	
	public void deleteModuleById(String id) {
		Query query = this.getSession().createQuery("delete from SysModule module where module.moduleId = " + id);
		query.executeUpdate();
	}

	public SysModule getObjectById(String id) {
		StringBuilder hql = new StringBuilder();
		hql.append("from SysModule module where module.moduleId = ?");
		List<SysModule> list = this.find(hql.toString(), id);
		
		if(list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	/**
	 * 
	 * Title: findModuleListByUserId
	 * Description: 通过用户ID查询到模块集合//SELECT DISTINCT m.* FROM bx_sys_module m , bx_sys_user_role ur ,  bx_sys_role_module rm WHERE ur.UserID = 1 AND ur.ROLEID = rm.ROLEID AND rm.ID = m.ID;
	 * Created On: 2014-12-3 上午8:35:17
	 * @author ldw 
	 * @param userId
	 * @return 
	 */
	public List<SysModule> findModuleListByUserId(Integer userId) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT DISTINCT module FROM SysModule module , SysUserRole ur ,  SysRoleModule rm WHERE ur.id.userId = " + String.valueOf(userId))
			.append(" AND ur.id.roleId = rm.id.roleId AND rm.id.moduleId = module.moduleId order by module.moduleId");
		List<SysModule> list = this.find(hql.toString());
		if(list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}
	/**
	 * 
	 * Title: getAllModule
	 * Description: 获取所有模块
	 * Created On: 2014-12-1 下午1:43:57
	 * @author ldw 
	 * @return
	 */
	public List<SysModule> getAllModule() {
		Criteria cc = this.getSession().createCriteria(SysModule.class);
		cc.add(Restrictions.eq("moduleValidity", true));
		return (List<SysModule>)cc.list();		
	}
	
	/**
	 * 
	 * Title: getOwerModuleByRoleId
	 * Description: 获取部分模块
	 * Created On: 2015-05-19
	 * @author psh
	 * @return
	 */
	
	public List<SysModule> findOwerModuleByRoleId(Integer id) {
		Criteria cc = this.getSession().createCriteria(SysModule.class);
		cc.add(Restrictions.eq("moduleValidity", true));
		return (List<SysModule>)cc.list();		
		
		//List<SysRoleModule> sysRoleModule = getSession()
		//									.createQuery(" from com.bxsurvey.sys.role.model.SysRoleModule where role_id = ?")
		//									.setInteger(0, id).list();
		
//		List<SysModule> sysModule = null;
		
//		if(sysRoleModule != null){
//			sysModule = new ArrayList<SysModule>();
//			
//			
//			for(int i = 0;i < sysRoleModule.size();i++){
//				
//				for(int j = i - 1;j >= 0;j--){
//					if(!(sysRoleModule.get(i).equals(sysRoleModule.get(j)))){
//						
//						sysModule.add(findModuleById(sysRoleModule.get(i).getId().getModuleId()));
//					}
//					
//				}
//				if(0 == i){
//					SysRoleModule sysRoleModule2 = sysRoleModule.get(i);
//					SysRoleModuleId id2 = sysRoleModule2.getId();
//					String moduleId = id2.getModuleId();
//					System.out.println("moduleId" + moduleId);
//					
//					sysModule.add(findModuleById(sysRoleModule.get(i).getId().getModuleId()));
//				}
//				
//			}
//			
//		}
		/*if(sysRoleModule != null){
			
			sysModule = new ArrayList<SysModule>();
			
			for(int i = 0;i < sysRoleModule.size(); i ++ ){
				
				 SysModule moudule = findModuleByModuleId(Integer.valueOf(sysRoleModule.get(i).getId().getModuleId())).get(0);
				
				
				sysModule.add(moudule);
			}
		}
		
		
		return sysModule;*/
	}
	
	private List<SysModule> findModuleByModuleId(Integer moduleId){
		
		return (List<SysModule>)((getSession().createQuery(" from com.bxsurvey.sys.module.model.SysModule where module_id = ?").setInteger(0, moduleId).list()));
		
		
	}
	
	/**
	 * 
	 * Title: getOwerModule
	 * Description: 获取部分模块
	 * Created On: 2016-05-19 
	 * @author psh
	 * @return
	 */
	public List<SysModule> getOwerModule() {
		Criteria cc = this.getSession().createCriteria(SysModule.class);
		cc.add(Restrictions.eq("moduleValidity", true));
		return (List<SysModule>)cc.list();		
	}
	/**
	 * 
	 * Title: getRoleResourceByRoleId
	 * Description: 通过角色ID获取相关的菜单
	 * Created On: 2014-12-1 下午5:14:16
	 * @author ldw 
	 * @param roleId
	 * @return
	 */
	public List<SysModule> getRoleResourceByRoleId(String roleId) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT DISTINCT module FROM SysModule module, SysRoleModule roleModule WHERE roleModule.id.roleId = ? AND roleModule.id.moduleId = module.moduleId");
		List<SysModule> list = this.find(hql.toString(), Integer.valueOf(roleId));
		if(!list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}
	
	public List<String> findPathListByUserId(Integer userId) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT DISTINCT module.modulePath FROM SysModule module , SysUserRole ur ,  SysRoleModule rm WHERE ur.id.userId = " + String.valueOf(userId))
			.append(" AND ur.id.roleId = rm.id.roleId AND rm.id.moduleId = module.moduleId");
		List<String> list = this.getSession().createQuery(hql.toString()).list();
		if(list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

	/**
	 * 
	 * @Title: findNorthModule 
	 * @Description: 获取banner的菜单
	 * @param @return 
	 * @return List<SysModule> 
	 * @throws
	 */
	public List<SysModule> findNorthModule(Integer userId) {
//		Criteria criteria =this.getCriteria();
//		criteria.add(Restrictions.eq("modulePid", "1"));
//		criteria.add(Restrictions.eq("moduleValidity", true));
//		return criteria.list();
		
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT DISTINCT module FROM SysModule module , SysUserRole ur ,  SysRoleModule rm WHERE ur.id.userId = " + String.valueOf(userId))
			.append(" AND ur.id.roleId = rm.id.roleId AND rm.id.moduleId = module.moduleId AND module.modulePid = 1 AND module.moduleValidity = 1 order by module.moduleIndex");
		List<SysModule> list = this.find(hql.toString());
		if(list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}
	
	/**
	 * 
	 * Title: findModuleListByUserId
	 * Description: 通过用户ID查询到模块集合//SELECT DISTINCT m.* FROM bx_sys_module m , bx_sys_user_role ur ,  bx_sys_role_module rm WHERE ur.UserID = 1 AND ur.ROLEID = rm.ROLEID AND rm.ID = m.ID;
	 * Created On: 2014-12-3 上午8:35:17
	 * @author ldw 
	 * @param userId
	 * @return 
	 */
	public List<SysModule> findUserAuthorityByPID(Integer userId,String pid) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT DISTINCT module FROM SysModule module , SysUserRole ur ,  SysRoleModule rm WHERE ur.id.userId = " + String.valueOf(userId))
			.append(" AND ur.id.roleId = rm.id.roleId AND rm.id.moduleId = module.moduleId and module.moduleValidity = 1 and module.modulePid= '"+pid +"' order by module.moduleIndex ASC");
		List<SysModule> list = this.find(hql.toString());
		if(list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}
	public List<SysModule> findUserAuthorityByType(Integer userId,Integer type) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT DISTINCT module FROM SysModule module , SysUserRole ur ,  SysRoleModule rm WHERE ur.id.userId = " + String.valueOf(userId))
			.append(" AND ur.id.roleId = rm.id.roleId AND rm.id.moduleId = module.moduleId and module.moduleValidity = 1 and module.moduleType= "+type +" order by module.moduleIndex DESC");
		List<SysModule> list = this.find(hql.toString());
		if(list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}
	
	public List<SysModule> findUserAuthorityByBtnId(Integer userId,String pid,String bid) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT DISTINCT module FROM SysModule module , SysUserRole ur ,  SysRoleModule rm WHERE ur.id.userId = " + String.valueOf(userId))
			.append(" AND ur.id.roleId = rm.id.roleId AND rm.id.moduleId = module.moduleId and module.moduleValidity = 1 and module.modulePid= '"+pid +"' and module.moduleValue = '"+bid+"' order by module.moduleIndex ASC");
		List<SysModule> list = this.find(hql.toString());
		if(list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}
}
