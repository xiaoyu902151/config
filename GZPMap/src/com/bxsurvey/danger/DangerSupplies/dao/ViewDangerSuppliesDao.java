package com.bxsurvey.danger.DangerSupplies.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bxsurvey.danger.DangerSupplies.model.ViewDangerSupplies;

import net.framework.base.dao.BaseDao;


@Repository
public class ViewDangerSuppliesDao extends BaseDao<ViewDangerSupplies, Integer> {

	public List<ViewDangerSupplies> findViewDangerSuppliesByXY(String minX, String maxX, String minY, String maxY,double r)
	{
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.eq("validity", true))
				.add(Restrictions.eq("vaccine", 1))
				.add(Restrictions.ge("x", minX))
				.add(Restrictions.le("x", maxX))
				.add(Restrictions.ge("y", minY))
				.add(Restrictions.le("y", maxY));
		criteria.addOrder(Order.asc("orderNum"));
		criteria.addOrder(Order.asc("suppliesTypeName"));
		criteria.addOrder(Order.asc("suppliesId"));
		try {
			return pack(Double.parseDouble(minX),Double.parseDouble(minY),Double.parseDouble(maxX),Double.parseDouble(maxY),r,criteria.list());
		} catch (Exception e) {
			throw new RuntimeException("捕捉不到 特定范围内的数据");
		}
	}
}
