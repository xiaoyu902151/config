package com.bxsurvey.comd.ComdExpert.service.impl;

import java.math.BigDecimal;
import java.util.List;

import net.framework.httpModel.PageResults;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxsurvey.comd.ComdExpert.dao.ComdExpertDao;
import com.bxsurvey.comd.ComdExpert.model.ComdExpert;
import com.bxsurvey.comd.ComdExpert.service.ComdExpertServiceI;
import com.bxsurvey.danger.DangerSupplies.model.DangerSupplies;



/** 
 * @ClassName: ComdScheduService 
 * @Description: 事件调度服务实现类
 * @author cqc
 * @date 2015-7-20 下午2:23:31 
 *  
 */
@Service
public class ComdExpertService implements ComdExpertServiceI {
	@SuppressWarnings("unused")
	private Logger log=Logger.getLogger(ComdExpertService.class);

	@Autowired
	private ComdExpertDao comdExpertDao;

	public List<ComdExpert> getExpertsByXY(double distance,BigDecimal x,BigDecimal y) {
		BigDecimal minX = x.subtract(new BigDecimal(distance));
		BigDecimal maxX = x.add(new BigDecimal(distance));
		BigDecimal minY = y.subtract(new BigDecimal(distance));
		BigDecimal maxY = y.add(new BigDecimal(distance));
		
		List<ComdExpert> list = comdExpertDao.findListByXYminOrmax(minX.toString(), maxX.toString(), minY.toString(), maxY.toString(),distance);		
		
		return list;
	}

	@Override
	public PageResults<ComdExpert> getListForJson(PageResults<ComdExpert> page,
			ComdExpert obj) {
		return comdExpertDao.getObjListForPage(page, obj);
	}

	@Override
	public void save(ComdExpert experts) {
		// TODO Auto-generated method stub
		if(experts != null) {
//			experts.setVaccine(0);
			experts.setValidity(true);
		}
		comdExpertDao.save(experts);
	}

	@Override
	public void update(ComdExpert experts) {
		// TODO Auto-generated method stub
		comdExpertDao.update(experts);
	}

	@Override
	public void deleteById(String ids) {
		// TODO Auto-generated method stub
		String[] str = ids.split(",");
		for(String id : str) {
			comdExpertDao.deteleObjectById(Integer.parseInt(id));
		}
	}

	/**
	 * 列表查询，不分页
	 */
	public List<ComdExpert> getListNoPage(ComdExpert obj) {
		return comdExpertDao.findByExample(obj);
		
	}



	
}
