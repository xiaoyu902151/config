package com.bxsurvey.rmt.service;

import java.util.List;

import net.framework.httpModel.PageResults;
import net.sf.json.JSONObject;

import com.bxsurvey.rmt.model.RtmWarning;

public interface RtmWarnServiceI {

	PageResults<RtmWarning> searchDaGrid(PageResults<RtmWarning> pageResults);

	List<RtmWarning> findSsbjData();

}
