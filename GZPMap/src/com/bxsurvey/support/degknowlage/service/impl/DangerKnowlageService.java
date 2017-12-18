package com.bxsurvey.support.degknowlage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.framework.httpModel.PageResults;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.bxsurvey.comd.ComdExpert.service.impl.ComdExpertService;
import com.bxsurvey.support.degknowlage.dao.DangerKnowlageAffixDao;
import com.bxsurvey.support.degknowlage.dao.DangerKnowlageDao;
import com.bxsurvey.support.degknowlage.model.DangerKnowlage;
import com.bxsurvey.support.degknowlage.model.DangerKnowlageAffix;
import com.bxsurvey.support.degknowlage.service.DangerKnowlageServiceI;
import com.bxsurvey.support.law.model.Law;

@Service
public class DangerKnowlageService implements  DangerKnowlageServiceI{
	@SuppressWarnings("unused")
	private Logger log=Logger.getLogger(DangerKnowlageService.class);
	
	@Autowired
	private DangerKnowlageDao dangerKnowlageDao;
	
	@Autowired
	private DangerKnowlageAffixDao dangerKnowlageAffixDao;
	@Override
	public void save(DangerKnowlage knowlage,String afs) {
		if(knowlage != null) {
			//设置一些默认的值
		}
		Integer knowlageId = (Integer)dangerKnowlageDao.save(knowlage);
		JSONArray array = JSONArray.fromObject(afs);
		for (int i = 0; i < array.size(); i++) {
			JSONObject json = JSONObject.fromObject(array.get(i));
			DangerKnowlageAffix affix = new DangerKnowlageAffix();
			affix.setName(json.getString("name"));
			affix.setPath(json.getString("path"));
			affix.setFileAfter(json.getString("fileAfter"));
			affix.setValidity(true);
			affix.setWriter("admin");
			affix.setUploadTime(new Date());
			affix.setKnowlageId(knowlageId);
			dangerKnowlageAffixDao.save(affix);
		}
	}

	@Override
	public void update(DangerKnowlage knowlage, String afs, String dfs) {
		dangerKnowlageDao.update(knowlage);
		Integer knowlageId = (Integer)knowlage.getId();
		JSONArray saveOfUpdate = JSONArray.fromObject(afs);
		for (int i = 0; i < saveOfUpdate.size(); i++) {
			JSONObject json = JSONObject.fromObject(saveOfUpdate.get(i));
			DangerKnowlageAffix affix = new DangerKnowlageAffix();
			affix.setName(json.getString("name"));
			affix.setPath(json.getString("path"));
			affix.setFileAfter(json.getString("fileAfter"));
			affix.setValidity(true);
			affix.setWriter("admin");
			affix.setUploadTime(new Date());
			affix.setKnowlageId(knowlageId);
			String affixId = json.getString("id");
			if(affixId != null && !affixId.equals("null") && !affixId.equals(""))
			{
				affix.setId(Integer.parseInt(affixId));
				dangerKnowlageAffixDao.update(affix);
			}
			else{
				dangerKnowlageAffixDao.save(affix);
			}
		}
		JSONArray del = JSONArray.fromObject(dfs);
		for (int i = 0; i < del.size(); i++) {
			JSONObject json = JSONObject.fromObject(del.get(i));
			String affixId = json.getString("id");
			dangerKnowlageAffixDao.deleteById(Integer.parseInt(affixId));
		}
	}

	@Override
	public void deleteById(String ids) {
		String[] str = ids.split(",");
		for(String id : str) {
			dangerKnowlageDao.deteleObjectById(Integer.parseInt(id));
		}
	}

	@Override
	public PageResults<DangerKnowlage> getListForJson(
			PageResults<DangerKnowlage> page, DangerKnowlage obj) {
		
		return dangerKnowlageDao.getObjListForPage(page, obj);
	}

	@Override
	public DangerKnowlage findById(String id) {
		return dangerKnowlageDao.get(Integer.parseInt(id));
	}

	@Override
	public List<DangerKnowlageAffix> findAffixByKnowlageId(String knowlageId) {
		DangerKnowlageAffix example = new DangerKnowlageAffix();
		example.setKnowlageId(Integer.parseInt(knowlageId));
		return dangerKnowlageAffixDao.findByExample(example);
	}

	/**
	 * 根据中文名查询
	 */
	public DangerKnowlage findByChName(String chName,String id) {
		List<Object> param = new ArrayList<Object>();
		param.add(chName);
		
		StringBuilder hql = new StringBuilder();
		hql.append(" select d from DangerKnowlage d where d.chName = ? ");
		if(id != null && id != ""){
			hql.append(" and d.id != ? ");
			param.add(Integer.parseInt(id));
		}
		
		List<DangerKnowlage> list = dangerKnowlageDao.find(hql.toString(), param);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据英文名查询
	 */
	public DangerKnowlage findEnName(String enName,String id) {
		List<Object> param = new ArrayList<Object>();
		param.add(enName);
		
		StringBuilder hql = new StringBuilder();
		hql.append(" select d from DangerKnowlage d where d.enName = ? ");
		if(id != null && id != ""){
			hql.append(" and d.id != ? ");
			param.add(Integer.parseInt(id));
		}
		
		List<DangerKnowlage> list = dangerKnowlageDao.find(hql.toString(), param);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据货物编号查询
	 */
	public DangerKnowlage findByHwbh(String hwbh,String id) {
		List<Object> param = new ArrayList<Object>();
		param.add(hwbh);
		
		StringBuilder hql = new StringBuilder();
		hql.append(" select d from DangerKnowlage d where d.hwbh = ? ");
		if(id != null && id != ""){
			hql.append(" and d.id != ? ");
			param.add(Integer.parseInt(id));
		}
		
		List<DangerKnowlage> list = dangerKnowlageDao.find(hql.toString(), param);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
