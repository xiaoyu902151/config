package com.bxsurvey.support.degknowlage.service;

import java.util.List;

import net.framework.httpModel.PageResults;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bxsurvey.support.degknowlage.model.DangerKnowlage;
import com.bxsurvey.support.degknowlage.model.DangerKnowlageAffix;
public interface DangerKnowlageServiceI {
	public void save(DangerKnowlage knowlage,String afs);
	public void update(DangerKnowlage knowlage, String afs, String dfs);
	public void deleteById(String ids);
	public PageResults<DangerKnowlage> getListForJson(PageResults<DangerKnowlage> page, DangerKnowlage obj);
    public DangerKnowlage findById(String id);
    public List<DangerKnowlageAffix> findAffixByKnowlageId(String knowlageId);
	public DangerKnowlage findByChName(String chName,String id);
	public DangerKnowlage findEnName(String enName,String id);
	public DangerKnowlage findByHwbh(String hwbh,String id);
}
