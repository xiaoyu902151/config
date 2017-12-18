package com.bxsurvey.comd.ComdEvent.service.impl;

import java.math.BigDecimal;
import java.util.List;

import net.framework.httpModel.PageResults;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxsurvey.comd.ComdEvent.dao.ComdEventDao;
import com.bxsurvey.comd.ComdEvent.model.ComdEvent;
import com.bxsurvey.comd.ComdEvent.service.ComdEventServiceI;

@Service
public class ComdEventService  implements ComdEventServiceI {
	@SuppressWarnings("unused")
	private Logger log=Logger.getLogger(ComdEventService.class);

	@Autowired
	private ComdEventDao comdEventDao;
	@Override
	public Integer save(ComdEvent event) {
		if(event != null) {
			
		}
		Integer id = (Integer)comdEventDao.save(event);
		return id;
	}

	@Override
	public void update(ComdEvent event) {
		
		comdEventDao.update(event);
	}

	@Override
	public void deleteById(String ids) {
		String[] str = ids.split(",");
		for(String id : str) {
			comdEventDao.deteleObjectById(new Integer(id));
		}		
	}

	@Override
	public List<ComdEvent> getEventsByXY(double distance, BigDecimal x,
			BigDecimal y) {
		BigDecimal minX = x.subtract(new BigDecimal(distance));
		BigDecimal maxX = x.add(new BigDecimal(distance));
		BigDecimal minY = y.subtract(new BigDecimal(distance));
		BigDecimal maxY = y.add(new BigDecimal(distance));
		
		List<ComdEvent> list = comdEventDao.findListByXYminOrmax(minX.toString(), maxX.toString(), minY.toString(), maxY.toString(),distance);		
		
		return list;
	}

	@Override
	public PageResults<ComdEvent> getListForJson(PageResults<ComdEvent> page,
			ComdEvent obj,String startTime,String endTime) {
		
		return comdEventDao.getObjListForPage(page, obj,startTime,endTime);
	}

	@Override
	public List<ComdEvent> getListNoPage(ComdEvent obj) {
		return comdEventDao.findByExample(obj);
	}

	//根据id获取对象
	public ComdEvent getEventById(String eventId) {
		return comdEventDao.get(Integer.valueOf(eventId));
	}

}
