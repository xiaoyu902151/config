package com.bxsurvey.danger.DangerZg.service;

import java.math.BigDecimal;
import java.util.List;

import com.bxsurvey.danger.DangerZg.model.DangerZg;

public interface DangerZgServiceI {

	public DangerZg getObjectById(Integer dangerZgId);
	
	public int updateByObjectId(String objectId,String x,String y,String plotGeo);

	public List<DangerZg> getZGByXY(double distance,BigDecimal x,BigDecimal y);

	public List<DangerZg> getAllZg();
}
