package com.bxsurvey.comd.ComdEvent.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

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

import com.bxsurvey.comd.ComdEvent.model.ComdEvent;



@Repository
public class ComdEventDao extends BaseDao<ComdEvent, Integer>{
	/**
	 * 
	 * Title: findListByXYminOrmax
	 * Description: 通过最大最小坐标查询范围
	 * Created On: 2015-7-6 上午9:36:38
	 * @author ldw 
	 * @param minX
	 * @param maxX
	 * @param minY
	 * @param maxY
	 * @return
	 */
	public List<ComdEvent> findListByXYminOrmax(String minX, String maxX, String minY, String maxY) {
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.ge("x", minX))
				.add(Restrictions.le("x", maxX))
				.add(Restrictions.ge("y", minY))
				.add(Restrictions.le("y", maxY));

		return criteria.list();
	}
	public List<ComdEvent> findListByXYminOrmax(String minX, String maxX, String minY, String maxY,double r) {
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.ge("x", minX))
				.add(Restrictions.le("x", maxX))
				.add(Restrictions.ge("y", minY))
				.add(Restrictions.le("y", maxY));

		try {
			return pack(Double.parseDouble(minX),Double.parseDouble(minY),Double.parseDouble(maxX),Double.parseDouble(maxY),r,criteria.list());
		} catch (Exception e) {
			throw new RuntimeException("捕捉不到 特定范围内的数据");
		}
	}	
	
	
	public void deteleObjectById(Integer id) {
		ComdEvent event = this.get(id);
		this.delete(event);
	}
	
	/**
	 * 列表查询,增加时间查询
	 * 
	 */
	public PageResults<ComdEvent> getObjListForPage(
			PageResults<ComdEvent> page, ComdEvent obj, String startTime,
			String endTime) {
		Criteria criteria = this.getCriteria();
		Example example = Example.create(obj);
		example.enableLike(MatchMode.ANYWHERE);// 匹配模式,使用模糊查询必填项。
		example.excludeNone();// 空的不做查询条件
		example.excludeZeroes();// 0不要查询
		example.ignoreCase(); // 不区分大小写
		criteria.add(example);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//增加时间查询判断
		if(startTime!=null&&startTime!=""){
			try {
				Date startTime1 = sdf.parse(startTime);
				criteria.add(Restrictions.gt("ocrTime", startTime1));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		if(endTime!=null&&endTime!=""){
			try {
				Date endTime1 = sdf.parse(endTime);
				criteria.add(Restrictions.lt("ocrTime", endTime1));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
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
}
