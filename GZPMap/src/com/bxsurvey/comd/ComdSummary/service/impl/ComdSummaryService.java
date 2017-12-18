package com.bxsurvey.comd.ComdSummary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxsurvey.comd.ComdSummary.dao.ComdSummaryDao;
import com.bxsurvey.comd.ComdSummary.model.ComdSummary;
import com.bxsurvey.comd.ComdSummary.service.ComdSummaryServiceI;

@Service
public class ComdSummaryService implements ComdSummaryServiceI{
	@Autowired
	private ComdSummaryDao comdSummaryDao;

	/**
	 * 保存对象
	 */
	public void save(ComdSummary obj) {
		comdSummaryDao.save(obj);
		
	}

}
