package com.bxsurvey.comd.ComdExpert.dao;

import java.util.List;

import net.framework.base.dao.BaseDao;

import net.framework.httpModel.page.Page;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bxsurvey.comd.ComdExpert.model.ComdExpert;
import com.bxsurvey.danger.DangerSupplies.model.DangerSupplies;




/** 
 * @ClassName: ComdExpertDao 
 * @Description: 专家Dao
 * @author cqc
 * @date 2015-6-30 上午9:12:55 
 *  
 */
@Repository
public class ComdExpertDao extends BaseDao<ComdExpert, Integer> {

	/**
	 * @param expert 
	 * @param page  
	 * @Title: findAllDatagrid 
	 * @Description: 查询专家列表
	 * @param @return 
	 * @return Datagrid 
	 * @throws 
	 */
//	public Datagrid findDatagrid(ComdExpert expert, Page page) {
//		Criteria criteria= this.getCriteria();
//		this.addExampleForCriteria(criteria, expert);
//				
//		long totalCount=this.countByCriteria(criteria);
//		this.getCriteriaExtendPage(criteria,page);
//		return Datagrid.createDatagrid(totalCount,criteria.list());
//	}
	
	/**
	 * @param expert 
	 * @param page  
	 * @Title: findAllDatagrid 
	 * @Description: 查询专家列表
	 * @param @return 
	 * @return Datagrid 
	 * @throws 
	 */
//	public Datagrid getExpertList() {
//		Criteria criteria= this.getCriteria();	
//		int count=criteria.list().size();
//		return Datagrid.createDatagrid((long)count,criteria.list());
//	}
	
	
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
	public List<ComdExpert> findListByXYminOrmax(String minX, String maxX, String minY, String maxY) {
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.ge("x", minX))
				.add(Restrictions.le("x", maxX))
				.add(Restrictions.ge("y", minY))
				.add(Restrictions.le("y", maxY));

		return criteria.list();
	}
	public List<ComdExpert> findListByXYminOrmax(String minX, String maxX, String minY, String maxY,double r) {
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
		ComdExpert expert = this.get(id);
		this.delete(expert);
//		expert.setValidity(false);
//		this.saveOrUpdate(expert);
	}
}
