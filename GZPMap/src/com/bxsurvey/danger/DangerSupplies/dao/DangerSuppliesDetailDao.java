package com.bxsurvey.danger.DangerSupplies.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.framework.base.dao.BaseDao;
import net.framework.httpModel.PageResults;
//import net.framework.httpModel.easyui.Datagrid;
import net.framework.httpModel.page.Page;
import net.sf.json.JSONArray;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;



import com.bxsurvey.danger.DangerSupplies.model.DangerSuppliesDetail;
import com.bxsurvey.sys.user.model.SysUser;

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
public class DangerSuppliesDetailDao extends BaseDao<DangerSuppliesDetail, Integer> {
	
	public void saveObject(DangerSuppliesDetail suppliesDetail) {
		this.save(suppliesDetail);
	}
	
	public void updateObject(DangerSuppliesDetail suppliesDetail) {
		this.saveOrUpdate(suppliesDetail);
	}
	
	public void deteleObjectById(Integer id) {
		DangerSuppliesDetail suppliesDetail = this.get(id);
		this.delete(suppliesDetail);
//		supplies.setValidity(false);
//		this.saveOrUpdate(supplies);
	}
	
	/**
	 * 
	 * Title: getByProperty
	 * Description: 通过条件查询
	 * Created On: 2015-7-3 下午3:32:35
	 * @author ldw 
	 * @param map
	 * @return
	 */
	public DangerSuppliesDetail getByProperty(Map<String, Object> map) {
		Criteria criteria = this.getCriteria();
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			Object value = map.get(key);
			criteria.add(Restrictions.eq(key, value));
		}
		List<DangerSuppliesDetail> list = criteria.list();
		if(list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	public List<DangerSuppliesDetail> getListBySuppliesID(Integer suppliesID,String orderBy,String order)
	{
		StringBuilder hql = new StringBuilder();
		if(order.toUpperCase().equals("ASC"))
			hql.append("from DangerSuppliesDetail detail where detail.dangerSupplies.id = ?  order by "+ orderBy +" ASC");
		else
			hql.append("from DangerSuppliesDetail detail where detail.dangerSupplies.id = ?  order by "+ orderBy +" DESC");
		
		List<DangerSuppliesDetail> list = this.find(hql.toString(), suppliesID);
		return list;
	}
	

	public List<Map<String,Object>> countBySuppliesDetailType(String ids,String rtID){
		StringBuffer sb = new StringBuffer();
		sb.append("select sp.params_name as type_name,sp.params_id as type_id,sum(dsd.num) as sum_num, 20 as need ");
		sb.append(" from danger_supplies_detail dsd,sys_params sp ,danger_supplies ds");
		sb.append(" where dsd.detail_type = sp.params_id and ds.id = dsd.supplies_id  and dsd.supplies_id in( "+ids+")  and  ds.supplies_type = "+rtID);
		sb.append(" GROUP BY sp.params_name,sp.params_id ");
		List<Map<String, Object>> res = this.findForJdbc(sb.toString());
		return res;
	}
	
	public List<Map<String,Object>> findBySuppliesDetailType(String ids,String suppliesType,String rtID){
		StringBuffer sb = new StringBuffer();
		sb.append("select ds.name as name,ds.id as id,ds.x as x,ds.y as y, sum(dsd.num) num ");
		sb.append(" from danger_supplies_detail dsd,sys_params sp,danger_supplies ds ");
		sb.append(" where dsd.detail_type = sp.params_id  and ds.id = dsd.supplies_id and dsd.supplies_id in( "+ids+")  and  ds.supplies_type = "+rtID+" and dsd.detail_type = "+ suppliesType + " ");
		sb.append(" GROUP BY ds.id,ds.x,ds.y,ds.name,dsd.detail_type");
		List<Map<String, Object>> res = this.findForJdbc(sb.toString());
		return res;
	}	
}
