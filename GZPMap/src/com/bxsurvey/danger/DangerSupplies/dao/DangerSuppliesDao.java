package com.bxsurvey.danger.DangerSupplies.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.framework.base.dao.BaseDao;
import net.framework.httpModel.PageResults;
//import net.framework.httpModel.easyui.Datagrid;
import net.framework.httpModel.page.Page;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import com.bxsurvey.danger.DangerSupplies.model.DangerSupplies;
import com.bxsurvey.danger.DangerSupplies.model.ViewDangerSupplies;
/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.dao.danger.DangerSuppliesDao.java 
 * Created On: 2015-5-12 下午6:48:00
 * Description: 物资储备表dao
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Repository
public class DangerSuppliesDao extends BaseDao<DangerSupplies, Integer> {
	
	public void saveObject(DangerSupplies supplies) {
		this.save(supplies);
	}
	
	public void updateObject(DangerSupplies supplies) {
		this.saveOrUpdate(supplies);
	}
	
	public void deteleObjectById(Integer id) {
		DangerSupplies supplies = this.get(id);
		this.delete(supplies);
//		supplies.setValidity(false);
//		this.saveOrUpdate(supplies);
	}
	
	/*
	 * 列表查询
	 */
//	public PageResults findListForJson(PageResults page,SeachSupplies seachSupplies) {
//		Criteria criteria = getSession().createCriteria(DangerSupplies.class);
//		criteria.add(Restrictions.eq("validity", true));//判断是否可用
//		
//		if(seachSupplies.getDepartId() >= 0 || seachSupplies.getVaccine() >= 0){
//			
//			if(!"".equals(seachSupplies.getName())){
//				criteria.add(Restrictions.ilike("name","%" + seachSupplies.getName() +"%"));
//			}
//			if(seachSupplies.getDepartId() >= 0){
//				criteria.add(Restrictions.eq("sysDepartv.departId", seachSupplies.getDepartId()));
//			}
//			if(seachSupplies.getVaccine() >= 0){
//				criteria.add(Restrictions.eq("vaccine", seachSupplies.getVaccine()));
//			}
//		}else{
//			if(null != seachSupplies.getName()){
//				criteria.add(Restrictions.ilike("name","%" + seachSupplies.getName() +"%"));
//			}
//		}
//		
//		
//		
//		
//		Long totalCount =(Long) criteria.setProjection(Projections.rowCount()).uniqueResult(); 
//		criteria.setProjection(null); 
//        List list = criteria.setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize()).list();
//        page.setTotalCount(totalCount);
//        page.setResult(list);
//		return page;
//	}


	/** 
	 * @Title: findsuppliesDaoByDepartIdDatagrid 
	 * @Description: 根据企业id分页查询物资储备列表
	 * @param @param departId
	 * @param @param page
	 * @param @return 
	 * @return Datagrid 
	 * @throws 
	 */
	/*public Datagrid findsuppliesDaoByDepartIdDatagrid(DangerSupplies supplies,Integer departId,
			Page page) {
		Criteria criteria = this.getCriteria();
		this.addExampleForCriteria(criteria, supplies);
		criteria.add(Restrictions.eq("departId", departId));
			
		Long totalCount=this.countByCriteria(criteria);
		this.getCriteriaExtendPage(criteria, page);
		return Datagrid.createDatagrid(totalCount, criteria.list()); 
	}*/
	/**
	 * 
	 * Title: getByProperty
	 * Description: 通过条件查询
	 * Created On: 2015-7-3 下午3:32:35
	 * @author ldw 
	 * @param map
	 * @return
	 */
	public DangerSupplies getByProperty(Map<String, Object> map) {
		Criteria criteria = this.getCriteria();
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			Object value = map.get(key);
			criteria.add(Restrictions.eq(key, value));
		}
		List<DangerSupplies> list = criteria.list();
		if(list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
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
	public List<DangerSupplies> findListByXYminOrmax(String minX, String maxX, String minY, String maxY) {
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.eq("validity", true))
				.add(Restrictions.eq("vaccine", 1))
				.add(Restrictions.ge("x", minX))
				.add(Restrictions.le("x", maxX))
				.add(Restrictions.ge("y", minY))
				.add(Restrictions.le("y", maxY));

		return criteria.list();
	}
	

	
	public List<DangerSupplies> findListByXYminOrmax(String minX, String maxX, String minY, String maxY,double r) {
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.eq("validity", true))
				.add(Restrictions.eq("vaccine", 1))
				.add(Restrictions.ge("x", minX))
				.add(Restrictions.le("x", maxX))
				.add(Restrictions.ge("y", minY))
				.add(Restrictions.le("y", maxY));


		try {
			return pack(Double.parseDouble(minX),Double.parseDouble(minY),Double.parseDouble(maxX),Double.parseDouble(maxY),r,criteria.list());
		} catch (Exception e) {
			throw new RuntimeException("捕捉不到 特定范围内的数据");
		}
	}
				
	/*
	 * 查找所有的supplies
	 */
	public List<DangerSupplies> getAllSupplies() {
		StringBuilder hql = new StringBuilder();
		hql.append("select ds from DangerSupplies ds");
		 List<DangerSupplies> list = this.find(hql.toString());
		 return list;
	}
	
	
	public List<Map<String,Object>> countBySuppliesType(String ids,String rtID){
		StringBuffer sb = new StringBuffer();
		sb.append("select sp.params_name as type_name,sp.params_id as type_id,sum(ds.num) as sum_num, 20 as need ");
		sb.append(" from danger_supplies ds,sys_params sp ");
		sb.append(" where ds.supplies_type = sp.params_id  and ds.id in( "+ids+") and sp.params_id <> '"+rtID+"' ");
		sb.append(" GROUP BY sp.params_name ,sp.params_id");
		List<Map<String, Object>> res = this.findForJdbc(sb.toString());
		return res;
	}
	
	public List<Map<String,Object>> findBySuppliesType(String ids,String suppliesType,String rtID){
		StringBuffer sb = new StringBuffer();
		sb.append("select ds.* ");
		sb.append(" from danger_supplies ds,sys_params sp ");
		sb.append(" where ds.supplies_type = sp.params_id  and ds.id in( "+ids+")  and sp.params_name <> '"+rtID+"' and ds.supplies_type = "+ suppliesType + " ");
		List<Map<String, Object>> res = this.findForJdbc(sb.toString());
		return res;
	}
	
}
