package com.bxsurvey.danger.DangerZg.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bxsurvey.comd.ComdExpert.model.ComdExpert;
import com.bxsurvey.danger.DangerZg.dao.DangerZgDao;
import com.bxsurvey.danger.DangerZg.model.DangerZg;
import com.bxsurvey.danger.DangerZg.service.DangerZgServiceI;

@Service("dangerZgService")
public class DangerZgService implements DangerZgServiceI {

	@Resource
	private DangerZgDao dangerZgDao;

	/**
	 * 通过主键获取数据
	 */
	public DangerZg getObjectById(Integer id) {
		return dangerZgDao.get(id);
	}

	@Override
	public int updateByObjectId(String objectId, String x, String y,
			String plotGeo) {
		int first = plotGeo.indexOf("\"attributes");
		int second = plotGeo.indexOf(",\"symbol");
		String s1 = plotGeo.substring(0,first);
		String s2 = plotGeo.substring(second+1);
		List<DangerZg> list = dangerZgDao.find("from DangerZg d where d.fId = ?", objectId);
		int res = 1;
		for(int i = 0; i < list.size(); i++){
			DangerZg dz = list.get(i);
			dz.setX(x);
			dz.setY(y);
			dz.setPlotGeo(s1+s2);
			dangerZgDao.update(dz);
		}
		return res;
	}
	
	public List<DangerZg> getZGByXY(double distance,BigDecimal x,BigDecimal y) {
		BigDecimal minX = x.subtract(new BigDecimal(distance));
		BigDecimal maxX = x.add(new BigDecimal(distance));
		BigDecimal minY = y.subtract(new BigDecimal(distance));
		BigDecimal maxY = y.add(new BigDecimal(distance));
		List<DangerZg> list = dangerZgDao.findListByXYminOrmax(minX.toString(), maxX.toString(), minY.toString(), maxY.toString(),distance);
		return list;
	}

	/**
	 * 查询所有
	 */
	public List<DangerZg> getAllZg() {
		return dangerZgDao.findAll();
	}

}
