package com.bxsurvey.support.law.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bxsurvey.support.law.model.Law;

import net.framework.base.dao.BaseDao;
import net.framework.httpModel.PageResults;
import net.framework.utils.StringUtil;

/**
 *
 * Description:法律管理dao
 * @author wzr
 * @date 2016-8-25上午9:27:01
 *
 */
@Repository
public class LawDao extends BaseDao<Law, Integer> {

	public void deleteLawById(Integer id) {
		
		this.deleteById(id);
	}

	public void updateLaw(Law law) {
		this.update(law);
		
	}

	/**
	 * 根据类型和名字列表查询
	 */
	public PageResults<Law> findListByType(PageResults<Law> page,
			Law obj, String type) {
		Criteria criteria = this.getCriteria();
		Example example = Example.create(obj);
		example.enableLike(MatchMode.ANYWHERE);// 匹配模式,使用模糊查询必填项。
		example.excludeNone();// 空的不做查询条件
		example.excludeZeroes();// 0不要查询
		example.ignoreCase(); // 不区分大小写
		criteria.add(example);
		
		criteria.add(Restrictions.eq("sysParams.paramsId", Integer.parseInt(type)));
		
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


}
