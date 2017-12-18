package com.bxsurvey.sys.params.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bxsurvey.sys.params.model.SysParamsTest;

import net.framework.base.dao.BaseDao;

@Repository
public class SysParamsTestDao extends BaseDao<SysParamsTest, Integer>{
	
	/*
	 * 通过id查找数据
	 */
	public List<SysParamsTest> findbyTypeId(Integer id) {
		SysParamsTest sysParams = new SysParamsTest();
		sysParams.setPid(id);
		return this.findByExample(sysParams);
	}

	/**
	 * 根据节点id查询对应的数据
	 */
	public List<SysParamsTest> getParamsList(Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append("select p from SysParamsTest p where p.pid=?");
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(id);
		return this.find(hql.toString(), param);
	}

}
