package com.bxsurvey.rmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.framework.httpModel.PageResults;

import org.springframework.stereotype.Service;

import com.bxsurvey.rmt.dao.RtmWarningDao;
import com.bxsurvey.rmt.model.RtmWarning;
import com.bxsurvey.rmt.service.RtmWarnServiceI;

@Service
public class RtmWarnService implements RtmWarnServiceI{
	@Resource
	private RtmWarningDao rwDao;

	/**
	 * 查询所有实时数据
	 */
	public PageResults<RtmWarning> searchDaGrid(
			PageResults<RtmWarning> pageResults) {
		return rwDao.searchDaGridByPage(pageResults);
		
	}

	/*
	 * 获取所有的报警数据(不带分页)
	 */
	public List<RtmWarning> findSsbjData() {
		List<RtmWarning> warningList = rwDao.getWarningList();
		
		return warningList;
	}

}
