package com.bxsurvey.rmt.dao;

import java.util.List;

import net.framework.base.dao.BaseDao;
import net.framework.httpModel.PageResults;

import org.springframework.stereotype.Repository;

import com.bxsurvey.rmt.model.RtmWarning;

@Repository
public class RtmWarningDao extends BaseDao<RtmWarning, Integer> {

	public PageResults<RtmWarning> searchDaGridByPage(
			PageResults<RtmWarning> pageResults) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("select rw from RtmWarning rw");
		List<RtmWarning> list = this.findByPageList(sb.toString(), pageResults.getPageNo(), pageResults.getPageSize(),null);
		pageResults.setTotalCount(list.size());
		pageResults.setResult(list);
		return pageResults;
	}

	/*
	 * 获取所有的报警数据(不带分页)
	 */
	public List<RtmWarning> getWarningList() {
		//等级4为告警,根据设备获取时间最新的一条告警数据
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * from ( ");
		sql.append(" select rtm.*,row_number() over (partition by  rtm.device_id order by rtm.warn_time desc) rn ");
		sql.append(" from rtm_warning rtm) t where t.rn =1 and t.warning_level =4 ");
		List<RtmWarning> list = this.findListbySql(sql.toString());
		return list;
	}

}
