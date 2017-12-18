package com.bxsurvey.sys.operateLog.dao;

import java.util.List;
import net.framework.base.dao.BaseDao;
import net.framework.httpModel.PageResults;
import net.framework.utils.StringUtil;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bxsurvey.sys.user.model.SysLog;
import com.bxsurvey.sys.user.model.SysUser;
/**
 * 
 ***********************************************
 * Copyright (c) 2014 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.dao.BxSysUserDao.java 
 * Created On: 2014-10-15 上午10:40:22
 * Description: 用户管理dao
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Repository
public class SysOperateLogDao extends BaseDao<SysLog, Integer> {
	/**
	 * 
	 * Title: getObjListForPage
	 * Description: 主要用于easyui datagrid 查询
	 * Created On: 2015-3-16 上午9:58:30
	 * @author ldw 
	 * @param page
	 * @param t
	 * @return
	 */
	public PageResults<SysLog> getObjListForPage(PageResults<SysLog> page, SysLog t) {
		Criteria criteria = this.getCriteria();
		Example example = Example.create(t);
		example.enableLike(MatchMode.ANYWHERE);// 匹配模式,使用模糊查询必填项。
		example.excludeNone();// 空的不做查询条件
		example.excludeZeroes();// 0不要查询
		example.ignoreCase(); // 不区分大小写
		criteria.add(example);
		//查询条件
		if(!"".equals(t.getUser().getLoginName())&&t.getUser().getUserId()!=null){
			criteria.add(Restrictions.eq("user.userId", t.getUser().getUserId()));
		}
		
		
		Long totalCount =(Long) criteria.setProjection(Projections.rowCount()).uniqueResult(); 
		criteria.setProjection(null);
		List list = null;
		if (!StringUtil.trimToEmpty(page.getOrder()).equals("") && !StringUtil.trimToEmpty(page.getOrderBy()).equals(""))
			if(PageResults.ASC.equals(page.getOrder()))
				list = criteria.addOrder(Order.asc(page.getOrderBy())).setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize()).list();
			else
				list = criteria.addOrder(Order.desc(page.getOrderBy())).setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize()).list();
		else
			list = criteria.setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize()).list();
        page.setTotalCount(totalCount);
        page.setResult(list);
		return page;
	}
	/**
	 * 删除
	 * @param id
	 */
	public void deleteSysLogById(Integer id) {
		
		//SysUser user = this.get(id);
		//user.setValidity(false);
		//this.saveOrUpdate(user);
		this.deleteById(id);
	}
}
