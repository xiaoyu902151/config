package com.bxsurvey.support.law.service;

import net.framework.httpModel.PageResults;

import com.bxsurvey.support.law.model.Law;

public interface LawServiceI {

	public PageResults<Law> findListForJson(PageResults<Law> pageResults,	Law obj);

	public void save(Law obj);

	public void deleteById(String ids);

	public Law findById(String id);

	public void update(Law law);

	public PageResults<Law> findListByType(PageResults<Law> pageResults,
			Law obj, String type);

	public Law findByName(String lawName,String id);


}
