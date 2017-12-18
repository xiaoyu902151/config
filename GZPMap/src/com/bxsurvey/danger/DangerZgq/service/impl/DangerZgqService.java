package com.bxsurvey.danger.DangerZgq.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bxsurvey.danger.DangerZgq.dao.DangerZgqDao;
import com.bxsurvey.danger.DangerZgq.model.DangerZgq;
import com.bxsurvey.danger.DangerZgq.service.DangerZgqServiceI;

@Service("dangerZgqService")
public class DangerZgqService implements DangerZgqServiceI{
	
	@Resource
	private DangerZgqDao dangerZgqDao;

	/**
	 * 通过主键获取数据
	 */
	public DangerZgq getObjectById(Integer id) {
		return dangerZgqDao.get(id);
	}
	
	
}
