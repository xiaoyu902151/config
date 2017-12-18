package com.bxsurvey.sys.depart.service;

import java.util.List;

import net.framework.httpModel.PageResults;
import net.framework.httpModel.Tree;
import net.sf.json.JSONObject;

import com.bxsurvey.sys.depart.model.SysDepart;


public interface SysDepartServiceI {
	public void save(SysDepart depart);
	public SysDepart findByFid(String fid);
	public void update(SysDepart depart);
	public void deleteById(String ids);
	public PageResults<SysDepart> findListForJson(PageResults<SysDepart> page, SysDepart depart);
	public SysDepart getObjectById(Integer id);
	public List<SysDepart> findAll();
	public List<SysDepart> findAll(Integer departId);
	public List<Tree> getDangerAccountTree(String contextPath);
}
