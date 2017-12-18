package com.bxsurvey.sys.video.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.framework.httpModel.PageResults;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bxsurvey.sys.video.model.SysVideo;
import com.bxsurvey.sys.video.vo.SysVideoVO;

/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.service.sys.SysVideoServiceI.java 
 * Created On: 2015-5-15 上午10:21:45
 * Description: 监控视频业务接口
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
public interface SysVideoServiceI {
	public void save(SysVideo video);
	public void update(SysVideo video);
	public void deleteById(String ids);
	public PageResults findListForJson(PageResults page, SysVideo video);
	public PageResults getSeachForJson(PageResults page,String name,String departId);
	public SysVideo getObjectById(Integer id);
//	public JSONArray findVVideoMapServiceListForJson();
	public List<SysVideo> getSuppliesByXY(double dis, BigDecimal bx, BigDecimal by);

//	public List<SysVideoVO> findVediosVOByPortNameAndDepartName(String departName);
//	public List<SysVideo> findVediosByPortNameAndDepartName(String departName);

}
