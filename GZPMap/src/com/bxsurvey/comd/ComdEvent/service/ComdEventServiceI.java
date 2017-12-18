package com.bxsurvey.comd.ComdEvent.service;

import java.math.BigDecimal;
import java.util.List;

import com.bxsurvey.comd.ComdEvent.model.ComdEvent;

import net.framework.httpModel.PageResults;



public interface ComdEventServiceI {
	public Integer save(ComdEvent event);
	public void update(ComdEvent event);
	public void deleteById(String ids);
	public List<ComdEvent> getEventsByXY(double distance,BigDecimal x,BigDecimal y);
	public PageResults<ComdEvent> getListForJson(PageResults<ComdEvent> page, ComdEvent obj,String startTime,String endTime);
	public List<ComdEvent> getListNoPage(ComdEvent obj);
	public ComdEvent getEventById(String eventId);

}
