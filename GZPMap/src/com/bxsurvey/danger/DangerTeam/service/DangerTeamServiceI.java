package com.bxsurvey.danger.DangerTeam.service;

import java.math.BigDecimal;
import java.util.List;

import net.framework.httpModel.PageResults;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bxsurvey.danger.DangerSupplies.model.DangerSupplies;
import com.bxsurvey.danger.DangerTeam.model.DangerTeam;
//import com.bxsurvey.danger.DangerTeam.model.SeachTeam;

/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.service.danger.DangerKfdcServiceI.java 
 * Created On: 2015-5-12 下午6:34:41
 * Description: 应急队伍信息(企业)业务接口
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
public interface DangerTeamServiceI {
	public void save(DangerTeam type);
	public void update(DangerTeam type);
	public void deleteById(String ids);
//	public PageResults findListForJson(PageResults page,SeachTeam seachTeam);
	public PageResults<DangerTeam> getListForJson(PageResults<DangerTeam> page, DangerTeam obj);
	public DangerTeam getObjectById(Integer id);
	public List<DangerTeam> getTeamsByXY(double distance,BigDecimal x,BigDecimal y);
	public List<DangerTeam> getPersonByDepartId(Integer departId);

}
