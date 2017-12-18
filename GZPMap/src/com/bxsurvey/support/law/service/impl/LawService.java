package com.bxsurvey.support.law.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.framework.httpModel.PageResults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxsurvey.support.law.dao.LawDao;
import com.bxsurvey.support.law.model.Law;
import com.bxsurvey.support.law.service.LawServiceI;

@Service("lawService")
public class LawService implements LawServiceI{
	@Autowired
	private LawDao lawDao;

	/**
	 * 列表查询
	 */
	public PageResults<Law> findListForJson(
			PageResults<Law> pageResults, Law obj) {
		return lawDao.getObjListForPage(pageResults, obj);
	}
	
	/**
	 * 根据类型和名字列表查询
	 */
	public PageResults<Law> findListByType(PageResults<Law> page,
			Law obj, String type){
		return lawDao.findListByType(page,obj,type);
	}

	/**
	 * 添加
	 */
	public void save(Law obj) {
		lawDao.save(obj);
		
	}

	/**
	 * 删除
	 */
	public void deleteById(String ids) {
		String[] idsStr = ids.split(",");
		for (String id : idsStr) {
			lawDao.deleteLawById(Integer.parseInt(id));
		}
		
	}

	/**
	 * 根据id查找对象
	 */
	public Law findById(String id) {
		return lawDao.get(Integer.parseInt(id));
	}

	/**
	 * 更新修改
	 */
	public void update(Law law) {
		lawDao.updateLaw(law);
		
		
	}

	/**
	 * 根据名称查询
	 */
	public Law findByName(String lawName,String id) {
		StringBuilder hql = new StringBuilder();
		hql.append("select l from Law l where l.lawName = ? ");
		List<Object> param = new ArrayList<Object>();
		param.add(lawName);
		if(id != null && id != ""){
			hql.append(" and l.id != ? ");
			param.add(Integer.valueOf(id));
		}
		
		List<Law> list = lawDao.find(hql.toString(), param);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}


}
