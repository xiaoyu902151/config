package com.bxsurvey.danger.DangerZg.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bxsurvey.comd.ComdExpert.model.ComdExpert;
import com.bxsurvey.danger.DangerZg.model.DangerZg;
import com.bxsurvey.danger.DangerZgq.model.DangerZgq;

import net.framework.base.dao.BaseDao;

@Repository
public class DangerZgDao extends BaseDao<DangerZg, Integer> {
	public List<DangerZg> findListByXYminOrmax(String minX, String maxX, String minY, String maxY,double r) {
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
	
}
