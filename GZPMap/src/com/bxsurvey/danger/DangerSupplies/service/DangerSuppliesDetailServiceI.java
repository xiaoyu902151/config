package com.bxsurvey.danger.DangerSupplies.service;


import java.util.List;

import net.framework.httpModel.PageResults;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bxsurvey.danger.DangerSupplies.model.DangerSuppliesDetail;




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
public interface DangerSuppliesDetailServiceI {
	public void save(DangerSuppliesDetail suppliesDetail);
	public void update(DangerSuppliesDetail suppliesDetail);
	public void deleteById(String ids);
	public PageResults<DangerSuppliesDetail> getListForJson(PageResults<DangerSuppliesDetail> page, DangerSuppliesDetail obj);
	public DangerSuppliesDetail getObjectById(Integer id);
	public List<DangerSuppliesDetail> getListBySuppliesID(Integer suppliesId,String orderBy,String order);
    public List<DangerSuppliesDetail> getListNoPage(DangerSuppliesDetail obj);
}
