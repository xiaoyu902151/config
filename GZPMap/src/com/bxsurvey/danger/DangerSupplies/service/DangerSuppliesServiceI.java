package com.bxsurvey.danger.DangerSupplies.service;

import java.math.BigDecimal;
import java.util.List;

import net.framework.httpModel.PageResults;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bxsurvey.danger.DangerSupplies.model.DangerSupplies;




/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.service.danger.DangerKfdcServiceI.java 
 * Created On: 2015-5-12 下午6:34:41
 * Description: 罐区物资储备情况业务接口
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
public interface DangerSuppliesServiceI {
	public void save(DangerSupplies supplies);
	public void update(DangerSupplies supplies);
	public void deleteById(String ids);
	public PageResults<DangerSupplies> getListForJson(PageResults<DangerSupplies> page, DangerSupplies obj);
	public DangerSupplies getObjectById(Integer suppliesId);
	public JSONArray getSuppliesByService(JSONArray array);
	public JSONObject getSuppliesByXY(double distance,BigDecimal x,BigDecimal y,String planId,String planType,String level);
	public List<DangerSupplies> getAllSupplies();
	

}
