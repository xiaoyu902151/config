package com.bxsurvey.support.degknowlage.dao;

import net.framework.base.dao.BaseDao;

import org.springframework.stereotype.Repository;


import com.bxsurvey.support.degknowlage.model.DangerKnowlage;

@Repository
public class DangerKnowlageDao extends BaseDao<DangerKnowlage, Integer>{
	public void deteleObjectById(Integer id) {
		DangerKnowlage konwlage = this.get(id);
		this.delete(konwlage);
	}
}
