package com.bxsurvey.danger.DangerSupplies.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.framework.httpModel.PageResults;
import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import com.bxsurvey.danger.DangerSupplies.dao.DangerSuppliesDetailDao;
import com.bxsurvey.danger.DangerSupplies.model.DangerSuppliesDetail;
import com.bxsurvey.danger.DangerSupplies.service.DangerSuppliesDetailServiceI;

@Service("dangerSuppliesDetailService")
public class DangerSuppliesDetailService implements DangerSuppliesDetailServiceI {

	@Resource
	private DangerSuppliesDetailDao suppliesDetailDao;
	@Override
	public void save(DangerSuppliesDetail suppliesDetail) {
//		if(suppliesDetail != null)
//		{
//			suppliesDetail.setWriter("");//
//		}
		suppliesDetailDao.save(suppliesDetail);
	}

	@Override
	public void update(DangerSuppliesDetail suppliesDetail) {
		suppliesDetailDao.update(suppliesDetail);		
	}

	@Override
	public void deleteById(String ids) {
		String[] str = ids.split(",");
		for(String id : str) {
			suppliesDetailDao.deleteById(new Integer(id));
		}
		
	}

	@Override
	public PageResults<DangerSuppliesDetail> getListForJson(
			PageResults<DangerSuppliesDetail> page, DangerSuppliesDetail obj) {
		return suppliesDetailDao.getObjListForPage(page, obj);
	}

	@Override
	public DangerSuppliesDetail getObjectById(Integer id) {
		return suppliesDetailDao.get(id);
	}

	@Override
	public List<DangerSuppliesDetail> getListBySuppliesID(Integer suppliesId,String orderBy,String order) {
		return suppliesDetailDao.getListBySuppliesID(suppliesId,orderBy,order);
	}

	@Override
	public List<DangerSuppliesDetail> getListNoPage(DangerSuppliesDetail obj) {
		return suppliesDetailDao.findByExample(obj);
	}

}
