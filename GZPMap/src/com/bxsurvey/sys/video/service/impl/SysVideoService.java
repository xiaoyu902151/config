package com.bxsurvey.sys.video.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.framework.httpModel.PageResults;
import net.framework.utils.json.JsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bxsurvey.danger.DangerSupplies.model.DangerSupplies;
import com.bxsurvey.sys.depart.model.SysDepart;
import com.bxsurvey.sys.params.model.SysParams;
import com.bxsurvey.sys.video.dao.SysVideoDao;
//import com.bxsurvey.sys.video.dao.VVideoMapServiceDao;
import com.bxsurvey.sys.video.model.SysVideo;
import com.bxsurvey.sys.video.service.SysVideoServiceI;
//import com.bxsurvey.sys.video.view.VVideoMapService;
import com.bxsurvey.sys.video.vo.SysVideoVO;

/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.service.sys.impl.SysVideoService.java 
 * Created On: 2015-5-15 上午10:24:57
 * Description: 监控视频业务实现类
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Service("sysVideoService")
public class SysVideoService implements SysVideoServiceI {
	@Resource
	private SysVideoDao videoDao;
//	@Resource
//	private VVideoMapServiceDao videoMapServiceDao;
	/*
	 * 新增
	 */
	public void save(SysVideo video) {
		videoDao.save(video);
	}
	/*
	 * 编辑
	 */
	public void update(SysVideo video) {
		videoDao.update(video);
	}
	/*
	 * 删除
	 */
	public void deleteById(String ids) {
		String[] str = ids.split(",");
		for(int i = 0; i < str.length; i++) {
			videoDao.deleteViewById(Integer.parseInt(str[i]));
		}
	}
	/*
	 * 列表查询
	 */
	public PageResults findListForJson(PageResults page, SysVideo video) {
		return videoDao.findListForJson(page, video);
	}
	
	
	public PageResults getSeachForJson(PageResults page,String name,String departId){
		
		return videoDao.getSeachForJson(page,name,departId);
	}
	
	/*
	 * 通过ID获取数据
	 */
	public SysVideo getObjectById(Integer id) {
		return videoDao.get(id);
	}
//	public JSONArray findVVideoMapServiceListForJson() {
//		List<VVideoMapService>  list=videoMapServiceDao.findVVideoMapServiceList();
//		JSONArray json= JsonUtil.toJSONArray(list);
//		return json;
//	}
	
	/* 
	 * @Description : 根据条件(港口和公司名称)查询摄像头信息，返回SysVideoVO
	 * @author : czj
	 * @data : 2016-1-26
	 */
//	public List<SysVideoVO> findVediosVOByPortNameAndDepartName(String departName) {
//		List<SysVideoVO> listVO = new ArrayList<SysVideoVO>();
//		List<SysVideo> list = videoDao.findVediosByPortNameAndDepartName(departName);
//		for(SysVideo sv:list){
//			SysVideoVO sysVideoVO = new SysVideoVO();
//			sysVideoVO = sysVideoVO.getSysVideoVO(sv);
//			listVO.add(sysVideoVO);
//		}
//		return listVO;
//	}
	
	/* 
	 * @Description : TODO
	 * @author : czj
	 * @data : 2016-2-1
	 */
	public List<SysVideo> findVediosByPortNameAndDepartName(String departName) {
		List<SysVideo> list = videoDao.findVediosByPortNameAndDepartName(departName);
		return list;
	}
	@Override
	public List<SysVideo> getSuppliesByXY(double distance,BigDecimal x,BigDecimal y) {
		BigDecimal minX = x.subtract(new BigDecimal(distance));
		BigDecimal maxX = x.add(new BigDecimal(distance));
		BigDecimal minY = y.subtract(new BigDecimal(distance));
		BigDecimal maxY = y.add(new BigDecimal(distance));
		List<SysVideo> list = videoDao.findListByXYminOrmax(minX.toString(), maxX.toString(), minY.toString(), maxY.toString(),distance);
		return list;
	}


}