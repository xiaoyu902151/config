package com.bxsurvey.danger.DangerTeam.service;

import java.util.List;

import net.framework.httpModel.PageResults;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bxsurvey.danger.DangerTeam.model.DangerTeamDetail;
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
public interface DangerTeamDetailServiceI {
	public void save(DangerTeamDetail type);
	public void update(DangerTeamDetail type);
	public void deleteById(String ids);
	public PageResults<DangerTeamDetail> getListForJson(PageResults<DangerTeamDetail> page, DangerTeamDetail obj);
	public DangerTeamDetail getObjectById(Integer id);
	public List<DangerTeamDetail> getListByTeamId(Integer teamId,String orderBy,String order);
	public List<DangerTeamDetail> getListNoPage(DangerTeamDetail obj);
}
